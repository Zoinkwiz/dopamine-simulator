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

import lombok.Getter;

import com.dopaminesimulator.core.DopamineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public final class CardCollection
{
	public static final double BONUS_PER_COLLECTION = 0.07d;

	public static final double FULL_COLLECTION_BONUS = 1.5d;

	public static final double BONUS_PER_ASCENSION = 0.05d;

	public static final int[] TIER_STARS = {1, 3, 6, 10};

	public static final String[] TIER_NAMES = {"Bronze", "Silver", "Gold", "Diamond"};
	private static final List<CardCollection> ALL = new ArrayList<>();
	private static final Map<String, List<CardCollection>> BY_CARD = new LinkedHashMap<>();
	private final String name;
	private final CardSet set;
	private final String description;
	private final List<Card> cards;
	private CardCollection(String name, CardSet set, String description, List<Card> members)
	{
		this.name = name;
		this.set = set;
		this.description = description;
		for (Card card : members)
		{
			if (card.getSet() != set)
			{
				throw new IllegalStateException(name + " lists " + card.getId()
					+ ", which is in " + card.getSet() + " rather than " + set);
			}
		}
		this.cards = Collections.unmodifiableList(new ArrayList<>(members));
	}

	static
	{
		for (CardData.CollectionEntry entry : CardData.loadCollections())
		{
			List<Card> members = new ArrayList<>(entry.members.size());
			for (String id : entry.members)
			{
				Card card = CardCatalogue.byId(id);
				if (card == null)
				{
					throw new IllegalStateException(entry.name + " lists " + id
						+ ", which is not a card in cards.json. Collections are stored as"
						+ " ids, so a card removed or renamed leaves this dangling.");
				}
				members.add(card);
			}

			CardCollection collection = new CardCollection(
				entry.name, CardSet.valueOf(entry.set), entry.description, members);
			ALL.add(collection);
			for (Card card : collection.cards)
			{
				BY_CARD.computeIfAbsent(card.getId(), k -> new ArrayList<>()).add(collection);
			}
		}
	}

	public static List<CardCollection> all()
	{
		return Collections.unmodifiableList(ALL);
	}

	public static List<CardCollection> inSet(CardSet set)
	{
		return BY_SET.getOrDefault(set, Collections.emptyList());
	}

	private static final Map<CardSet, List<CardCollection>> BY_SET = indexBySet();

	private static Map<CardSet, List<CardCollection>> indexBySet()
	{
		Map<CardSet, List<CardCollection>> index = new EnumMap<>(CardSet.class);
		for (CardCollection collection : ALL)
		{
			index.computeIfAbsent(collection.set, k -> new ArrayList<>()).add(collection);
		}
		index.replaceAll((k, v) -> Collections.unmodifiableList(v));
		return index;
	}

	public static List<CardCollection> forCard(Card card)
	{
		return BY_CARD.getOrDefault(card.getId(), Collections.emptyList());
	}

	public static double multiplierFor(DopamineState state, CardSet set)
	{
		return multiplierFor(state, Collections.singletonList(set));
	}

	public static double multiplierFor(DopamineState state, List<CardSet> sets)
	{
		int tiers = 0;
		int possible = 0;
		int collections = 0;
		double ascended = 0d;
		for (CardSet set : sets)
		{
			tiers += tiersIn(state, set);
			possible += maxTiersIn(set);
			for (CardCollection collection : inSet(set))
			{
				ascended += collection.bonusFromAscension(state);
				collections++;
			}
		}
		if (possible == 0)
		{
			return 1d;
		}

		double done = (double) tiers / possible;
		double perCollection = collections == 0 ? 0d : ascended / collections;
		// Added, not compounded, and measured by share done rather than count.
		return 1d + FULL_COLLECTION_BONUS * done + perCollection;
	}

	public double bonusFromAscension(DopamineState state)
	{
		return state.getAscension(name) * BONUS_PER_ASCENSION;
	}
	public static int tiersIn(DopamineState state, CardSet set)
	{
		int tiers = 0;
		for (CardCollection collection : inSet(set))
		{
			tiers += collection.tierIn(state);
		}
		return tiers;
	}

	public static int maxTiersIn(CardSet set)
	{
		return inSet(set).size() * TIER_STARS.length;
	}

	public int size()
	{
		return cards.size();
	}
	public int ownedIn(DopamineState state)
	{
		int owned = 0;
		for (Card card : cards)
		{
			if (state.owns(card.getId()))
			{
				owned++;
			}
		}
		return owned;
	}

	public boolean isMaxed(DopamineState state)
	{
		if (cards.isEmpty())
		{
			return false;
		}
		for (Card card : cards)
		{
			if (state.getStars(card.getId()) < Rarity.MAX_STARS)
			{
				return false;
			}
		}
		return true;
	}

	public long ascensionCost(DopamineState state)
	{
		long base = 0L;
		for (Card card : cards)
		{
			base += Dust.costToMax(card.getRarity());
		}
		return Math.round(base * 0.25d * Math.pow(1.5d, state.getAscension(name)));
	}

	public boolean isComplete(DopamineState state)
	{
		return tierIn(state) > 0;
	}

	public int tierIn(DopamineState state)
	{
		if (cards.isEmpty())
		{
			return 0;
		}

		int lowest = Integer.MAX_VALUE;
		for (Card card : cards)
		{
			if (!state.owns(card.getId()))
			{
				return 0;
			}
			lowest = Math.min(lowest, state.getStars(card.getId()));
		}

		int tier = 0;
		for (int threshold : TIER_STARS)
		{
			if (lowest >= threshold)
			{
				tier++;
			}
		}
		return tier;
	}

	public String tierNameIn(DopamineState state)
	{
		int tier = tierIn(state);
		return tier <= 0 ? "Incomplete" : TIER_NAMES[Math.min(tier, TIER_NAMES.length) - 1];
	}

	public int starsForNextTier(DopamineState state)
	{
		int tier = tierIn(state);
		return tier >= TIER_STARS.length ? 0 : TIER_STARS[tier];
	}

	@Override
	public String toString()
	{
		return name;
	}
}
