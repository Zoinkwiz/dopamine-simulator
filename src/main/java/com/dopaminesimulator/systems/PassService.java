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
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Dust;
import com.dopaminesimulator.cards.Region;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.pass.BattlePass;
import com.dopaminesimulator.pass.PassReward;
import com.dopaminesimulator.pass.SeasonClock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PassService
{
	private final Random random;
	private final PackService packs;

	public PassService(Random random, PackService packs)
	{
		this.random = random;
		this.packs = packs;
	}

	public boolean rollIfExpired(DopamineState state, long nowMs)
	{
		int key = SeasonClock.seasonKey(nowMs);
		if (state.getPassSeasonKey() == key)
		{
			return false;
		}

		boolean first = state.getPassSeasonKey() == 0;
		state.setPassSeasonKey(key);
		if (first)
		{
			return false;
		}

		state.startNextSeason();
		return true;
	}

	public boolean canClaim(DopamineState state, int tier, boolean premium)
	{
		if (tier < 1 || tier > BattlePass.TIERS)
		{
			return false;
		}
		if (premium && !state.isPassPremium())
		{
			return false;
		}
		if (state.isPassTierClaimed(tier, premium))
		{
			return false;
		}
		return BattlePass.tierAt(state.getPassXp(), state.getPassSeason()) >= tier;
	}

	public boolean claim(DopamineState state, int tier, boolean premium, CardSet targetSet,
		RewardQueue rewards)
	{
		if (!canClaim(state, tier, premium) || !state.claimPassTier(tier, premium))
		{
			return false;
		}

		PassReward reward = premium
			? BattlePass.premiumReward(tier, state.getPassSeason())
			: BattlePass.freeReward(tier, state.getPassSeason());
		apply(state, reward, targetSet, rewards);
		return true;
	}

	public int claimAll(DopamineState state, CardSet targetSet, RewardQueue rewards)
	{
		int claimed = 0;
		for (int tier = 1; tier <= BattlePass.TIERS; tier++)
		{
			claimed += claim(state, tier, false, targetSet, rewards) ? 1 : 0;
			claimed += claim(state, tier, true, targetSet, rewards) ? 1 : 0;
		}
		return claimed;
	}

	public boolean buyPremium(DopamineState state)
	{
		if (state.isPassPremium())
		{
			return false;
		}
		return state.spendPoints(BattlePass.premiumCost(state.getPassSeason()))
			&& setPremium(state);
	}

	public boolean canStartNextSeason(DopamineState state)
	{
		return BattlePass.tierAt(state.getPassXp(), state.getPassSeason()) >= BattlePass.TIERS;
	}

	public boolean startNextSeason(DopamineState state)
	{
		if (!canStartNextSeason(state))
		{
			return false;
		}
		state.startNextSeason();
		return true;
	}

	public List<PassReward> unclaimed(DopamineState state)
	{
		List<PassReward> pending = new ArrayList<>();
		int reached = BattlePass.tierAt(state.getPassXp(), state.getPassSeason());
		for (int tier = 1; tier <= reached; tier++)
		{
			if (!state.isPassTierClaimed(tier, false))
			{
				pending.add(BattlePass.freeReward(tier, state.getPassSeason()));
			}
			if (state.isPassPremium() && !state.isPassTierClaimed(tier, true))
			{
				pending.add(BattlePass.premiumReward(tier, state.getPassSeason()));
			}
		}
		return pending;
	}

	private boolean setPremium(DopamineState state)
	{
		state.setPassPremium(true);
		return true;
	}

	private void apply(DopamineState state, PassReward reward, CardSet targetSet,
		RewardQueue rewards)
	{
		switch (reward.getKind())
		{
			case PACK:
				packs.openRegional(state, reward.getPack(),
					Region.forSeason(state.getPassSeason()), (int) reward.getAmount(), rewards);
				break;
			case SHARDS:

				state.addDust(reward.getAmount()
					* Dust.fromOverflow(reward.getRarity()));
				break;
			case SHINY:
				upgrade(state, rewards, true);
				break;
			case GILDED:
				upgrade(state, rewards, false);
				break;
			case CARD_BACK:
				state.unlockBack(reward.back().name());
				break;
			case WILDCARD:
				state.addDust(reward.getAmount() * Dust.PER_WILDCARD);
				break;
			default:
				break;
		}
	}

	private void upgrade(DopamineState state, RewardQueue rewards, boolean shiny)
	{
		List<Card> owned = new ArrayList<>();
		for (Card card : CardCatalogue.all())
		{
			if (state.owns(card.getId())
				&& (shiny ? !state.isShiny(card.getId()) : !state.isGilded(card.getId())))
			{
				owned.add(card);
			}
		}
		if (owned.isEmpty())
		{
			return;
		}

		Card card = owned.get(random.nextInt(owned.size()));
		if (shiny && state.makeShiny(card.getId()))
		{
			rewards.push(Reward.shiny(card));
		}
		else if (!shiny && state.makeGilded(card.getId()))
		{
			rewards.push(Reward.gilded(card));
		}
	}
}
