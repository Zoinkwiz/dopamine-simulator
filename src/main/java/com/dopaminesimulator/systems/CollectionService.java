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
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Dust;
import com.dopaminesimulator.core.Balance;
import com.dopaminesimulator.incremental.Perks;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.core.RewardQueue;
import java.util.Random;

public class CollectionService
{
	private final Random random;

	public CollectionService()
	{
		this(new Random());
	}

	public CollectionService(Random random)
	{
		this.random = random;
	}

	private static final int EPIC_SHARDS_PER_LEGENDARY_DUPE = 3;
	private static final int SHARDS_PER_DUPE = 1;
	public boolean grant(DopamineState state, Card card, RewardQueue rewards)
	{
		return grant(state, card, rewards, false);
	}

	public boolean grant(DopamineState state, Card card, RewardQueue rewards, boolean fromFusion)
	{
		return grant(state, card, rewards, fromFusion, 1);
	}

	public boolean grant(DopamineState state, Card card, RewardQueue rewards, boolean fromFusion,
		int copies)
	{
		int granted = Math.max(1, copies);
		boolean becameShiny = rollShiny(state, card, rewards);
		boolean becameGilded = rollGilded(state, card, rewards);
		if (state.owns(card.getId()))
		{
			int starsBefore = state.getStars(card.getId());
			int maxCopies = card.getSet().isUnlockSet()
				? 1 : card.getRarity().copiesForMaxStars();
			int held = state.getCopies(card.getId());

			int useful = Math.max(0, Math.min(granted, maxCopies - held));
			int overflow = granted - useful;
			if (useful > 0)
			{
				state.addCopies(card.getId(), useful);
			}
			int dust = (int) Math.round(overflow * Dust.fromOverflow(card.getRarity())
				* Perks.dust(state));
			state.addDust(dust);

			int starsAfter = state.getStars(card.getId());
			if (starsAfter > starsBefore)
			{
				rewards.push(Reward.starUp(card, starsAfter).withCopies(granted)
					.withVariant(becameShiny, becameGilded));
			}
			else
			{
				rewards.push(Reward.duplicate(card, dust).withCopies(granted)
					.withVariant(becameShiny, becameGilded));
			}
			return false;
		}
		state.addCopies(card.getId(), granted);
		rewards.push((fromFusion ? Reward.fusion(card) : Reward.newCard(card))
			.withCopies(granted).withVariant(becameShiny, becameGilded));
		checkSetCompletion(state, card.getSet(), rewards);
		return true;
	}
	private boolean rollShiny(DopamineState state, Card card, RewardQueue rewards)
	{
		if (state.isShiny(card.getId()))
		{
			return false;
		}

		if (random.nextInt(Perks.shinyOneIn(state, Balance.SHINY_ONE_IN)) == 0 && state.makeShiny(card.getId()))
		{
			rewards.push(Reward.shiny(card));
			return true;
		}
		return false;
	}

	private boolean rollGilded(DopamineState state, Card card, RewardQueue rewards)
	{
		if (state.isGilded(card.getId()))
		{
			return false;
		}

		if (random.nextInt(Perks.gildedOneIn(state, Balance.GILDED_ONE_IN)) == 0 && state.makeGilded(card.getId()))
		{
			rewards.push(Reward.gilded(card));
			return true;
		}
		return false;
	}

	private void checkSetCompletion(DopamineState state, CardSet set, RewardQueue rewards)
	{
		if (state.getCompletedSets().contains(set))
		{
			return;
		}
		if (state.isSetComplete(set))
		{
			state.getCompletedSets().add(set);
			rewards.push(Reward.setComplete(set));
		}
	}
}
