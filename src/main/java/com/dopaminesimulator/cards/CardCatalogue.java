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

import com.dopaminesimulator.cards.sets.*;

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
		// A set missing from the registry above would silently have no cards.
		for (CardSet set : CardSet.values())
		{
			bySet.put(set, new ArrayList<>());
		}

		List<Card> all = new ArrayList<>();
		for (CardGroup entry : AgilityCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : AreasCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : AttackCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : BossesCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : ConstructionCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : CookingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : CraftingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : DefenceCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : DiariesCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : FarmingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : FiremakingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : FishingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : FletchingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : HerbloreCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : HunterCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : ItemsCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : MagicCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : MinigamesCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : MiningCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : PrayerCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : QuestsCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : RangedCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : RunecraftCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : SailingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : SkillsCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : SlayerCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : SmithingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : SpellsAncientCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : SpellsArceuusCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : SpellsLunarCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : SpellsStandardCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : StrengthCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : ThievingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : WoodcuttingCards.values())
		{
			all.add(entry.getCard());
		}
		for (CardGroup entry : BossesCollCards.values())
		{
			all.add(entry.getCard());
		}

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

	public static int size()
	{
		return CARDS.size();
	}
}
