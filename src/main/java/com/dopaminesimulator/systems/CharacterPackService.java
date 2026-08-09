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

/**
 * Opens the packs earned by doing the thing a character is known for.
 *
 * <p>A pack always gives something: a card or two from related content, and the character
 * itself on a rare roll. The roll is bounded - after enough packs the character is given
 * outright, so the tail of the distribution cannot run forever.
 */
public class CharacterPackService
{
	private static final int THEMED_CARDS = 2;

	private final Random random;
	private final CollectionService collection;

	public CharacterPackService(Random random, CollectionService collection)
	{
		this.random = random;
		this.collection = collection;
	}

	/** The cards a pack gave, character first if it landed. Empty if there was no pack to open. */
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
			// Pity keeps counting only while the character is still missing. Once it is held,
			// a guarantee would just hand over duplicates.
			state.setCharacterPity(deed.getCardId(), owned ? 0 : opened);
		}

		for (int i = 0; i < THEMED_CARDS; i++)
		{
			Card themed = randomFrom(deed);
			if (themed != null)
			{
				pulled.add(themed);
				collection.grant(state, themed, rewards, false, 1);
			}
		}
		return pulled;
	}

	/** Packs still to open for a character before the guarantee. */
	public int packsUntilPity(DopamineState state, CharacterDeed deed)
	{
		return Math.max(0, deed.getPity() - state.getCharacterPity(deed.getCardId()));
	}

	private Card randomFrom(CharacterDeed deed)
	{
		List<Card> pool = CardCatalogue.bySet(deed.getThemedSet());
		if (pool == null || pool.isEmpty())
		{
			return null;
		}
		return pool.get(random.nextInt(pool.size()));
	}
}
