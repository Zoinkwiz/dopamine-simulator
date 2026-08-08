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
import java.util.List;
import java.util.Map;

public final class CardCatalogue
{
	private static final List<Card> CARDS;
	private static final Map<String, Card> BY_ID;
	private static final Map<Rarity, List<Card>> BY_RARITY;
	private static final Map<CardSet, List<Card>> BY_SET;

	static
	{
		List<Card> cards = new ArrayList<>(4_096);
		Map<String, Card> byId = new HashMap<>();
		Map<Rarity, List<Card>> byRarity = new EnumMap<>(Rarity.class);
		Map<CardSet, List<Card>> bySet = new EnumMap<>(CardSet.class);
		for (Rarity rarity : Rarity.values())
		{
			byRarity.put(rarity, new ArrayList<>());
		}
		// A set with nothing in cards.json would silently have no cards.
		for (CardSet set : CardSet.values())
		{
			bySet.put(set, new ArrayList<>());
		}

		List<Card> all = CardData.load();

		for (Card card : all)
		{
			cards.add(card);
			if (card.getName() == null || card.getName().trim().isEmpty())
			{
				throw new IllegalStateException("Card with no name in " + card.getSet());
			}
			if (card.getItemId() <= 0 && card.getSpriteId() <= 0)
			{
				throw new IllegalStateException("Card has no art: " + card.getId());
			}
			Card clash = byId.put(card.getId(), card);
			if (clash != null)
			{
				throw new IllegalStateException("Two cards share the id " + card.getId()
					+ ": \"" + clash.getName() + "\" and \"" + card.getName()
					+ "\" in " + card.getSet() + ". Ids come from the display name, so"
					+ " two cards in one set cannot be called the same thing.");
			}
			byRarity.get(card.getRarity()).add(card);
			bySet.get(card.getSet()).add(card);
		}
		for (CardSet set : CardSet.values())
		{
			if (bySet.get(set).isEmpty())
			{
				throw new IllegalStateException(set + " has no cards. Either it wants"
					+ " removing or its enum is missing from this catalogue.");
			}
		}

		CARDS = Collections.unmodifiableList(cards);
		BY_ID = Collections.unmodifiableMap(byId);
		BY_RARITY = Collections.unmodifiableMap(byRarity);
		BY_SET = Collections.unmodifiableMap(bySet);
	}

	private CardCatalogue()
	{
	}

	public static List<Card> all()
	{
		return CARDS;
	}

	public static Card byId(String id)
	{
		return BY_ID.get(id);
	}

	public static List<Card> byRarity(Rarity rarity)
	{
		return BY_RARITY.get(rarity);
	}

	public static List<Card> bySet(CardSet set)
	{
		return BY_SET.get(set);
	}

	public static List<Card> bySetAndRarity(CardSet set, Rarity rarity)
	{
		return BY_SET_AND_RARITY
			.getOrDefault(set, Collections.emptyMap())
			.getOrDefault(rarity, Collections.emptyList());
	}

	private static final Map<CardSet, Map<Rarity, List<Card>>> BY_SET_AND_RARITY = indexed();

	private static Map<CardSet, Map<Rarity, List<Card>>> indexed()
	{
		Map<CardSet, Map<Rarity, List<Card>>> index = new EnumMap<>(CardSet.class);
		for (CardSet set : CardSet.values())
		{
			Map<Rarity, List<Card>> byRarity = new EnumMap<>(Rarity.class);
			for (Card card : BY_SET.get(set))
			{
				byRarity.computeIfAbsent(card.getRarity(), k -> new ArrayList<>()).add(card);
			}
			byRarity.replaceAll((k, v) -> Collections.unmodifiableList(v));
			index.put(set, Collections.unmodifiableMap(byRarity));
		}
		return Collections.unmodifiableMap(index);
	}

	/**
	 * What a pack is allowed to hand out: everything except cards a source has
	 * claimed in {@link CardOrigins}. Every pool a pack draws from goes through
	 * here, fallbacks included, or an exclusive leaks straight back into packs.
	 */
	public static List<Card> packPool(Rarity rarity)
	{
		return PACK_BY_RARITY.getOrDefault(rarity, Collections.emptyList());
	}

	public static List<Card> packPool(CardSet set, Rarity rarity)
	{
		return PACK_BY_SET_AND_RARITY
			.getOrDefault(set, Collections.emptyMap())
			.getOrDefault(rarity, Collections.emptyList());
	}

	public static List<Card> byOriginAndRarity(CardOrigin origin, Rarity rarity)
	{
		return BY_ORIGIN_AND_RARITY
			.getOrDefault(origin, Collections.emptyMap())
			.getOrDefault(rarity, Collections.emptyList());
	}

	private static final Map<Rarity, List<Card>> PACK_BY_RARITY;
	private static final Map<CardSet, Map<Rarity, List<Card>>> PACK_BY_SET_AND_RARITY;
	private static final Map<CardOrigin, Map<Rarity, List<Card>>> BY_ORIGIN_AND_RARITY;

	static
	{
		Map<Rarity, List<Card>> packByRarity = new EnumMap<>(Rarity.class);
		Map<CardSet, Map<Rarity, List<Card>>> packBySet = new EnumMap<>(CardSet.class);
		Map<CardOrigin, Map<Rarity, List<Card>>> byOrigin = new EnumMap<>(CardOrigin.class);
		for (Rarity rarity : Rarity.values())
		{
			packByRarity.put(rarity, new ArrayList<>());
		}

		for (Card card : CARDS)
		{
			CardOrigin origin = CardOrigins.of(card);
			byOrigin
				.computeIfAbsent(origin, k -> new EnumMap<>(Rarity.class))
				.computeIfAbsent(card.getRarity(), k -> new ArrayList<>())
				.add(card);
			if (origin.isExclusive())
			{
				continue;
			}
			packByRarity.get(card.getRarity()).add(card);
			packBySet
				.computeIfAbsent(card.getSet(), k -> new EnumMap<>(Rarity.class))
				.computeIfAbsent(card.getRarity(), k -> new ArrayList<>())
				.add(card);
		}

		// A rarity with nothing left in it would send every roll of that tier to a
		// fallback, which is how exclusives escape.
		for (Rarity rarity : Rarity.values())
		{
			if (packByRarity.get(rarity).isEmpty())
			{
				throw new IllegalStateException("Every " + rarity + " card has been claimed"
					+ " as a source exclusive, so packs have nothing to roll at that tier.");
			}
		}

		PACK_BY_RARITY = lock(packByRarity);
		PACK_BY_SET_AND_RARITY = lockNested(packBySet);
		BY_ORIGIN_AND_RARITY = lockNested(byOrigin);
	}

	private static <K extends Enum<K>> Map<K, List<Card>> lock(Map<K, List<Card>> source)
	{
		source.replaceAll((k, v) -> Collections.unmodifiableList(v));
		return Collections.unmodifiableMap(source);
	}

	private static <K extends Enum<K>, I extends Enum<I>> Map<K, Map<I, List<Card>>> lockNested(
		Map<K, Map<I, List<Card>>> source)
	{
		source.replaceAll((k, v) -> lock(v));
		return Collections.unmodifiableMap(source);
	}

	public static int size()
	{
		return CARDS.size();
	}
}
