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
package com.dopaminesimulator.systems;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardCatalogue;
import com.dopaminesimulator.cards.CharacterDeed;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.RewardQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CharacterPackService
{
	private static final int THEMED_CARDS = 1;
	private static final int BASE_CARDS = 1;

	private final Random random;
	private final CollectionService collection;

	public CharacterPackService(Random random, CollectionService collection)
	{
		this.random = random;
		this.collection = collection;
	}

	public List<Card> open(DopamineState state, CharacterDeed deed, RewardQueue rewards)
	{
		if (deed == null || !state.takeCharacterPack(deed.getCardId()))
		{
			return java.util.Collections.emptyList();
		}

		int opened = state.getCharacterPity(deed.getCardId()) + 1;
		boolean owned = state.getCopies(deed.getCardId()) > 0;
		boolean landed = random.nextDouble() < deed.getChance() || opened >= deed.getPity();

		List<Card> pulled = new ArrayList<>(THEMED_CARDS + 1);
		if (landed)
		{
			Card character = CardCatalogue.byId(deed.getCardId());
			if (character != null)
			{
				pulled.add(character);
				collection.grant(state, character, rewards, false, 1);
			}
			state.setCharacterPity(deed.getCardId(), 0);
		}
		else
		{
			state.setCharacterPity(deed.getCardId(), owned ? 0 : opened);
		}

		for (int i = 0; i < THEMED_CARDS; i++)
		{
			Card themed = pick(state, deed.pool());
			if (themed != null)
			{
				pulled.add(themed);
				collection.grant(state, themed, rewards, false, 1);
			}
		}
		for (int i = 0; i < BASE_CARDS; i++)
		{
			Card filler = pick(state, basePool());
			if (filler != null)
			{
				pulled.add(filler);
				collection.grant(state, filler, rewards, false, 1);
			}
		}
		return pulled;
	}

	public int packsUntilPity(DopamineState state, CharacterDeed deed)
	{
		return Math.max(0, deed.getPity() - state.getCharacterPity(deed.getCardId()));
	}

	private Card pick(DopamineState state, List<Card> pool)
	{
		if (pool.isEmpty())
		{
			return null;
		}
		List<Card> missing = new ArrayList<>(pool.size());
		for (Card card : pool)
		{
			if (state.getCopies(card.getId()) == 0)
			{
				missing.add(card);
			}
		}
		List<Card> from = missing.isEmpty() ? pool : missing;
		return from.get(random.nextInt(from.size()));
	}

	private static List<Card> basePool()
	{
		if (BASE == null)
		{
			List<Card> base = new ArrayList<>();
			for (Card card : CardCatalogue.bySet(com.dopaminesimulator.cards.CardSet.ITEMS))
			{
				if (card.getRarity().ordinal()
					<= com.dopaminesimulator.cards.Rarity.UNCOMMON.ordinal())
				{
					base.add(card);
				}
			}
			BASE = java.util.Collections.unmodifiableList(base);
		}
		return BASE;
	}

	private static List<Card> BASE;
}
