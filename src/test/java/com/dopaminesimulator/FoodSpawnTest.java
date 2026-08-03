package com.dopaminesimulator;

import com.dopaminesimulator.points.ClickState;
import com.dopaminesimulator.points.GnomeFood;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Drives the serving loop the way onGameTick does, over real ClickState and real
 * GnomeFood.roll, so the rate and the spread are measured rather than assumed.
 */
public class FoodSpawnTest
{
	private static final long TICK_MS = 600L;
	private static final int TICKS_PER_HOUR = 6_000;

	/** The guard and roll from DopamineSimulatorPlugin.rollForSurge, tick by tick. */
	private Map<GnomeFood, Integer> run(int hours, int clickLevel, long seed)
	{
		Map<GnomeFood, Integer> served = new EnumMap<>(GnomeFood.class);
		ClickState clicks = new ClickState();
		Random random = new Random(seed);
		long now = 1_000_000L;

		for (int tick = 0; tick < hours * TICKS_PER_HOUR; tick++)
		{
			now += TICK_MS;
			if (clicks.isSurging(now) || clicks.isPlated(now))
			{
				continue;
			}
			if (random.nextDouble() >= ClickState.surgeChancePerTick(clickLevel, 1d))
			{
				continue;
			}
			GnomeFood food = GnomeFood.roll(random);
			clicks.serve(food, now);
			served.merge(food, 1, Integer::sum);
		}
		return served;
	}

	private int total(Map<GnomeFood, Integer> served)
	{
		return served.values().stream().mapToInt(Integer::intValue).sum();
	}

	@Test
	public void dishesAreServedAtTheAdvertisedRate()
	{
		int hours = 2_000;
		int count = total(run(hours, 0, 12345L));
		double perHour = count / (double) hours;
		double advertised = ClickState.surgesPerHour(0);

		System.out.println("level 0: " + String.format("%.2f", perHour) + " dishes/hr"
			+ " (panel says " + String.format("%.2f", advertised) + ")");

		assertTrue("no dishes served at all in " + hours + " hours", count > 0);
		assertEquals("served rate must match what the panel advertises",
			advertised, perHour, advertised * 0.05d);
	}

	/**
	 * The served rate always lands a little under the per-tick chance, because a
	 * dish that is already running skips the roll. The faster the kitchen, the
	 * more of that there is, so the gap widens with the level rather than closing.
	 */
	@Test
	public void aMaxedClickLevelServesMore()
	{
		for (int level : new int[]{0, 10, ClickState.MAX_LEVEL})
		{
			double served = total(run(2_000, level, 999L)) / 2_000d;
			double advertised = ClickState.surgesPerHour(level);
			System.out.println("level " + level + ": " + String.format("%.2f", served)
				+ " dishes/hr served, panel advertises " + String.format("%.2f", advertised)
				+ " (" + String.format("%+.1f%%", 100d * (served - advertised) / advertised) + ")");

			assertTrue("a higher level must not serve fewer", served >= 7d);
			assertTrue("served rate must not exceed the roll chance", served <= advertised * 1.02d);
			assertTrue("and must not fall far under it", served >= advertised * 0.9d);
		}
	}

	@Test
	public void everyDishOnTheMenuGetsServed()
	{
		Map<GnomeFood, Integer> served = run(20_000, 0, 42L);
		int count = total(served);
		System.out.println("over " + count + " dishes:");
		for (GnomeFood food : GnomeFood.values())
		{
			int times = served.getOrDefault(food, 0);
			double share = 100d * times / count;
			System.out.println(String.format("  %-22s %6d  %5.2f%%  (weight says %5.2f%%)",
				food.getDisplayName(), times, share, expectedShare(food)));
			assertTrue(food + " never came out of the kitchen", times > 0);
			assertEquals(food + " is served at the wrong rate",
				expectedShare(food), share, 0.5d);
		}
	}

	private double expectedShare(GnomeFood food)
	{
		int total = 0;
		for (GnomeFood other : GnomeFood.values())
		{
			total += other.getWeight();
		}
		return 100d * food.getWeight() / total;
	}

	@Test
	public void aPlatedDishOnlyHoldsTheKitchenForItsWindow()
	{
		for (GnomeFood food : GnomeFood.values())
		{
			ClickState clicks = new ClickState();
			long now = 1_000_000L;
			clicks.serve(food, now);

			assertTrue(food + ": the guard must hold while it waits",
				clicks.isPlated(now + ClickState.SERVE_WINDOW_MS - 1L));
			assertTrue(food + ": and let go the moment the window shuts",
				!clicks.isPlated(now + ClickState.SERVE_WINDOW_MS));
			assertTrue(food + ": an uneaten dish must leave nothing running",
				!clicks.isSurging(now + ClickState.SERVE_WINDOW_MS));
		}
	}

	@Test
	public void everyDishGetsTheSameTenSecondsToBeClicked()
	{
		assertEquals("the window players were promised", 10_000L, ClickState.SERVE_WINDOW_MS);

		for (GnomeFood food : GnomeFood.values())
		{
			ClickState clicks = new ClickState();
			long now = 1_000_000L;
			clicks.serve(food, now);

			assertEquals(food + " must be on the plate", food, clicks.getShown(now));
			assertEquals(food + " must still be there at the last moment", food,
				clicks.getShown(now + ClickState.SERVE_WINDOW_MS - 1L));
			assertEquals(food + " must be gone after the window", null,
				clicks.getShown(now + ClickState.SERVE_WINDOW_MS));
		}
	}

	/** Eaten, a dish that lasts keeps the plate for as long as it runs. */
	@Test
	public void anEatenDishStaysOnTheButtonWhileItRuns()
	{
		for (GnomeFood food : GnomeFood.values())
		{
			ClickState clicks = new ClickState();
			long now = 1_000_000L;
			clicks.serve(food, now);
			clicks.eat(now);

			if (food.lasts())
			{
				assertEquals(food + " must hold the button while it runs", food,
					clicks.getShown(now + food.getDurationMs() - 1L));
				assertEquals(food + " must clear when it ends", null,
					clicks.getShown(now + food.getDurationMs()));
			}
			else
			{
				assertEquals(food + " pays out at once, so the plate clears", null,
					clicks.getShown(now));
			}
		}
	}
}
