package com.dopaminesimulator;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardCatalogue;
import com.dopaminesimulator.ui.CardArtService;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.game.SpriteManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Why a big Cards view froze the game.
 *
 * <p>SpriteManager.getSpriteAsync misses its cache and hands ClientThread a
 * BooleanSupplier. ClientThread.invokeList removes a task that returns true and
 * <em>keeps</em> one that returns false, retrying it every tick. The supplier
 * getSpriteAsync builds returns false whenever the sprite does not resolve, so
 * a sprite that never resolves is a task that never leaves the queue.
 *
 * <p>That makes a duplicate request permanent tick cost. This drives the real
 * RuneLite classes and counts what ends up in that queue.
 */
public class SpriteRequestLeakTest
{
	private ClientThread clientThread;
	private SpriteManager spriteManager;
	private CardArtService artService;
	private List<Card> spriteCards;

	@Before
	public void setUp() throws Exception
	{
		// isClientThread() false keeps invoke() on the invokeLater path, so tasks
		// queue instead of running. The queue is exactly what we want to measure.
		Client client = (Client) Proxy.newProxyInstance(
			Client.class.getClassLoader(),
			new Class<?>[]{Client.class},
			(proxy, method, callArgs) ->
			{
				if ("isClientThread".equals(method.getName()))
				{
					return false;
				}
				Class<?> type = method.getReturnType();
				if (!type.isPrimitive())
				{
					return null;
				}
				return boolean.class.equals(type) ? Boolean.FALSE : 0;
			});

		clientThread = new ClientThread();
		set(clientThread, "client", client);

		Constructor<SpriteManager> ctor = SpriteManager.class.getDeclaredConstructor(
			Client.class, ClientThread.class,
			Class.forName("net.runelite.client.ui.overlay.infobox.InfoBoxManager"));
		ctor.setAccessible(true);
		spriteManager = ctor.newInstance(client, clientThread, null);

		artService = new CardArtService(null, spriteManager);

		spriteCards = new ArrayList<>();
		for (Card card : CardCatalogue.all())
		{
			if (card.getSpriteId() > 0)
			{
				spriteCards.add(card);
			}
		}
		assertTrue("expected sprite backed cards to exist", spriteCards.size() > 100);
	}

	/** The mechanism, straight from SpriteManager: duplicates pile up. */
	@Test
	public void duplicateRequestsAccumulateInTheClientThreadQueue()
	{
		int sprite = spriteCards.get(0).getSpriteId();
		for (int i = 0; i < 50; i++)
		{
			spriteManager.getSpriteAsync(sprite, 0, image ->
			{
			});
		}
		assertEquals("every duplicate request is another task the client thread"
			+ " retries every tick until the sprite resolves", 50, queued());
	}

	/** The fix: one request per card, however many times the panel rebuilds. */
	@Test
	public void rebuildingDoesNotQueueMoreWork()
	{
		int afterFirst = -1;
		for (int rebuild = 1; rebuild <= 20; rebuild++)
		{
			// what a Cards tab rebuild does: every owned card asks to be told
			// when its art lands
			for (Card card : spriteCards)
			{
				artService.onLoaded(card, () ->
				{
				});
			}
			if (rebuild == 1)
			{
				afterFirst = queued();
				System.out.println("after 1 rebuild:   " + afterFirst + " queued tasks");
			}
		}
		System.out.println("after 20 rebuilds: " + queued() + " queued tasks");

		assertTrue("the first rebuild should request the art it needs", afterFirst > 0);
		assertTrue("a card should not be requested twice", afterFirst <= spriteCards.size());
		assertEquals("rebuilding must not add client thread work, or the queue grows"
			+ " without bound and every tick gets slower", afterFirst, queued());
	}

	/** get() must not reopen the hole onLoaded closed. */
	@Test
	public void paintingRepeatedlyDoesNotQueueMoreWork()
	{
		for (Card card : spriteCards)
		{
			artService.onLoaded(card, () ->
			{
			});
		}
		int afterLoad = queued();

		// paints, which call get() for art that has not arrived yet
		for (int paint = 0; paint < 20; paint++)
		{
			for (Card card : spriteCards)
			{
				artService.get(card);
			}
		}
		assertEquals("get() queued more work on every paint", afterLoad, queued());
	}

	private int queued()
	{
		return ((Collection<?>) get(clientThread, "invokes")).size();
	}

	private static void set(Object target, String name, Object value)
	{
		try
		{
			Field field = target.getClass().getDeclaredField(name);
			field.setAccessible(true);
			field.set(target, value);
		}
		catch (ReflectiveOperationException e)
		{
			throw new IllegalStateException(e);
		}
	}

	private static Object get(Object target, String name)
	{
		try
		{
			Field field = target.getClass().getDeclaredField(name);
			field.setAccessible(true);
			return field.get(target);
		}
		catch (ReflectiveOperationException e)
		{
			throw new IllegalStateException(e);
		}
	}
}
