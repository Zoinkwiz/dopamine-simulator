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
package com.dopaminesimulator.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public final class CardOrigins
{
	private static final Map<String, CardOrigin> BY_ID = new HashMap<>();
	private static final Map<CardOrigin, List<Card>> BY_ORIGIN = new EnumMap<>(CardOrigin.class);

	private static final Map<String, List<Card>> POOLS = new LinkedHashMap<>();

	static
	{
		for (CardData.OriginEntry entry : CardData.loadOrigins())
		{
			List<Card> members = new ArrayList<>(entry.members.size());
			for (String id : entry.members)
			{
				Card card = CardCatalogue.byId(id);
				if (card == null)
				{
					throw new IllegalStateException(entry.pool + " claims " + id
						+ ", which is not a card in cards.json. An exclusive that stops"
						+ " existing takes its only source with it.");
				}
				members.add(card);
			}

			POOLS.put(entry.pool, claim(CardOrigin.valueOf(entry.origin), members));
		}
	}

	private CardOrigins()
	{
	}

	public static CardOrigin of(Card card)
	{
		return card == null ? CardOrigin.PACKS : of(card.getId());
	}

	public static CardOrigin of(String cardId)
	{
		return BY_ID.getOrDefault(cardId, CardOrigin.PACKS);
	}

	public static boolean isExclusive(Card card)
	{
		return of(card).isExclusive();
	}

	public static List<Card> from(CardOrigin origin)
	{
		return BY_ORIGIN.getOrDefault(origin, Collections.emptyList());
	}

	public static List<Card> pool(String name)
	{
		return POOLS.getOrDefault(name, Collections.emptyList());
	}

	public static Card seasonCard(int season)
	{
		return rotate(pool("SEASONAL"), season);
	}

	public static Card prestigeCard(int prestigeCount)
	{
		return rotate(pool("MASTERY"), prestigeCount);
	}

	public static Card ascensionCard(int totalAscensions)
	{
		return rotate(pool("ASCENDANT"), totalAscensions);
	}

	// Rotations are 1-based and wrap, so a player past the end of a list starts
	// banking spare copies of it rather than falling off a cliff.
	private static Card rotate(List<Card> pool, int nth)
	{
		if (pool.isEmpty() || nth < 1)
		{
			return null;
		}
		return pool.get(Math.floorMod(nth - 1, pool.size()));
	}

	private static List<Card> claim(CardOrigin origin, List<Card> members)
	{
		List<Card> claimed = new ArrayList<>(members.size());
		for (Card card : members)
		{
			CardOrigin clash = BY_ID.put(card.getId(), origin);
			if (clash != null)
			{
				throw new IllegalStateException(card.getName() + " is claimed by both "
					+ clash + " and " + origin + ". A card has exactly one source, or"
					+ " the pack filter cannot decide whether to hide it.");
			}
			claimed.add(card);
		}
		List<Card> locked = Collections.unmodifiableList(claimed);

		// A source can claim in several batches — the gacha has one pool per banner
		// — so these accumulate rather than replacing what came before.
		BY_ORIGIN.merge(origin, locked, (held, added) ->
		{
			List<Card> both = new ArrayList<>(held);
			both.addAll(added);
			return Collections.unmodifiableList(both);
		});
		return locked;
	}
}
