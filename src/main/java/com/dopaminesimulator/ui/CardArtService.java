/*
 * Copyright (c) 2026, Zoinkwiz <https://github.com/Zoinkwiz>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.dopaminesimulator.ui;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardSet;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.util.AsyncBufferedImage;

@Singleton
public class CardArtService
{
	private final ItemManager itemManager;
	private final SpriteManager spriteManager;
	private final Map<String, AsyncBufferedImage> items = new ConcurrentHashMap<>();
	private final Map<String, BufferedImage> sprites = new ConcurrentHashMap<>();
	private final Set<String> requested = ConcurrentHashMap.newKeySet();
	private final Set<String> settled = ConcurrentHashMap.newKeySet();
	private final Map<String, Runnable> waiters = new ConcurrentHashMap<>();
	private final Map<CardSet, BufferedImage> badges = new ConcurrentHashMap<>();
	private final Set<CardSet> badgesRequested = ConcurrentHashMap.newKeySet();
	@Inject
	public CardArtService(ItemManager itemManager, SpriteManager spriteManager)
	{
		this.itemManager = itemManager;
		this.spriteManager = spriteManager;
		CardRenderer.setBadgeSource(this::badgeFor);
	}

	public BufferedImage get(Card card)
	{
		if (card.getSpriteId() > 0)
		{
			BufferedImage cached = sprites.get(card.getId());
			if (cached == null)
			{
				requestSprite(card, null);
			}
			return cached;
		}
		if (card.getItemId() <= 0)
		{
			return null;
		}
		AsyncBufferedImage image = items.computeIfAbsent(card.getId(),
			id -> itemManager.getImage(card.getItemId()));
		return image == null || image.getWidth() <= 0 ? null : image;
	}

	public void onLoaded(Card card, Runnable callback)
	{
		String id = card.getId();
		if (settled.contains(id))
		{
			return;
		}
		if (card.getSpriteId() > 0)
		{
			requestSprite(card, callback);
			return;
		}
		if (card.getItemId() <= 0)
		{
			return;
		}
		AsyncBufferedImage image = items.computeIfAbsent(id,
			key -> itemManager.getImage(card.getItemId()));
		if (image == null)
		{
			return;
		}
		waiters.put(id, callback);
		if (!requested.add(id))
		{
			return;
		}
		image.onLoaded(() ->
		{
			settled.add(id);
			deliver(id);
		});
	}

	private void deliver(String id)
	{
		Runnable waiting = waiters.remove(id);
		if (waiting != null)
		{
			waiting.run();
		}
	}

	public BufferedImage badgeFor(CardSet set)
	{
		int sprite = set == null ? -1 : set.badgeSpriteId();
		if (sprite <= 0)
		{
			return null;
		}
		BufferedImage cached = badges.get(set);
		if (cached == null && badgesRequested.add(set))
		{
			spriteManager.getSpriteAsync(sprite, 0, image ->
			{
				if (image != null)
				{
					badges.put(set, image);
				}
			});
		}
		return cached;
	}

	private void requestSprite(Card card, Runnable callback)
	{
		String id = card.getId();
		if (callback != null)
		{
			waiters.put(id, callback);
		}
		if (!requested.add(id))
		{
			return;
		}
		spriteManager.getSpriteAsync(card.getSpriteId(), 0, image ->
		{
			sprites.put(id, image);
			settled.add(id);
			deliver(id);
		});
	}
}
