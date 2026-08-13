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
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.cards.Region;
import com.dopaminesimulator.core.Balance;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.incremental.Perks;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.packs.PackTier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PackService
{
	public static final int LUCKY_PACKS = 50;

	private final Random random;
	private final CollectionService collection;
	public PackService(Random random, CollectionService collection)
	{
		this.random = random;
		this.collection = collection;
	}
	public double costOf(DopamineState state, PackTier tier)
	{
		return tier.getCost() * Perks.packCost(state);
	}
	public boolean canBuy(DopamineState state, PackTier tier)
	{
		return state.isPackUnlocked(tier) && state.getPoints() >= costOf(state, tier);
	}
	public List<Card> buy(DopamineState state, PackTier tier, CardSet targetSet, RewardQueue rewards)
	{
		if (!canBuy(state, tier))
		{
			return Collections.emptyList();
		}
		if (!state.spendPoints(costOf(state, tier)))
		{
			return Collections.emptyList();
		}
		return open(state, tier, targetSet, rewards);
	}

	public List<Card> openFree(DopamineState state, PackTier tier, CardSet targetSet, int count,
		RewardQueue rewards)
	{
		List<Card> all = new ArrayList<>();
		for (int i = 0; i < count; i++)
		{
			all.addAll(open(state, tier, targetSet, rewards));
		}
		return all;
	}

	public List<Card> openRegional(DopamineState state, PackTier tier, Region region, int count,
		RewardQueue rewards)
	{
		List<Card> all = new ArrayList<>();
		for (int i = 0; i < count; i++)
		{
			all.addAll(open(state, tier, null, region, rewards));
		}
		return all;
	}

	private List<Card> open(DopamineState state, PackTier tier, CardSet targetSet,
		RewardQueue rewards)
	{
		return open(state, tier, targetSet, null, rewards);
	}

	private List<Card> open(DopamineState state, PackTier tier, CardSet targetSet, Region region,
		RewardQueue rewards)
	{
		state.setTotalPacksOpened(state.getTotalPacksOpened() + 1);
		state.setRunPacksOpened(state.getRunPacksOpened() + 1);
		double luck = tier.getLuck();
		List<Card> pulled = new ArrayList<>(tier.getCardCount());
		boolean satisfiedPity = false;
		for (int i = 0; i < tier.getCardCount(); i++)
		{
			Rarity rarity = rollRarity(luck, tier.getFloor(), tier.getCeiling());
			satisfiedPity |= rarity.isPityWorthy();
			pulled.add(randomCardOf(rarity, tier, targetSet, region));
		}
		if (!satisfiedPity && state.getPacksSinceLastRare() + 1 >= Balance.PITY_PACKS
			&& (tier.getCeiling() == null || tier.getCeiling().ordinal() >= Rarity.RARE.ordinal()))
		{
			pulled.set(pulled.size() - 1, randomCardOf(Rarity.RARE, tier, targetSet, region));
			satisfiedPity = true;
		}
		state.setPacksSinceLastRare(satisfiedPity ? 0 : state.getPacksSinceLastRare() + 1);

		if (state.getRunPacksOpened() <= LUCKY_PACKS)
		{
			for (Card card : pulled)
			{
				if (card.getRarity().ordinal() >= Rarity.EPIC.ordinal())
				{
					state.setEarlyEpicPulled(true);
					break;
				}
			}
		}

		for (Card card : pulled)
		{
			collection.grant(state, card, rewards, false, tier.copiesFor(card.getRarity()));
		}
		return pulled;
	}
	public List<Card> buyMany(DopamineState state, PackTier tier, CardSet targetSet, int count,
		RewardQueue rewards)
	{
		List<Card> all = new ArrayList<>();
		for (int i = 0; i < count; i++)
		{
			List<Card> pulled = buy(state, tier, targetSet, rewards);
			if (pulled.isEmpty())
			{
				break;
			}
			all.addAll(pulled);
		}
		return all;
	}
	public int packsUntilPity(DopamineState state)
	{
		return Math.max(0, Balance.PITY_PACKS - state.getPacksSinceLastRare());
	}

	private Rarity rollRarity(double luck, Rarity floor, Rarity ceiling)
	{
		double[] weights = new double[Rarity.values().length];
		double total = 0d;
		for (Rarity rarity : Rarity.values())
		{
			if (floor != null && rarity.ordinal() < floor.ordinal())
			{
				continue;
			}
			if (ceiling != null && rarity.ordinal() > ceiling.ordinal())
			{
				continue;
			}
			double weight = rarity.getPackWeight();
			if (rarity.isPityWorthy() && (floor == null || rarity.ordinal() > floor.ordinal()))
			{
				weight *= luck;
			}
			weights[rarity.ordinal()] = weight;
			total += weight;
		}

		if (total <= 0d)
		{
			return floor == null ? Rarity.COMMON : floor;
		}
		double roll = random.nextDouble() * total;
		double cumulative = 0d;
		for (Rarity rarity : Rarity.values())
		{
			cumulative += weights[rarity.ordinal()];
			if (roll < cumulative)
			{
				return rarity;
			}
		}
		return floor == null ? Rarity.COMMON : floor;
	}
	private Card randomCardOf(Rarity rarity, PackTier tier, CardSet targetSet, Region region)
	{
		List<Card> pool = null;
		if (region != null)
		{
			pool = new ArrayList<>();
			for (Card card : region.pool())
			{
				if (card.getRarity() == rarity)
				{
					pool.add(card);
				}
			}
		}
		if (pool == null || pool.isEmpty())
		{
			pool = tier.isTargetsSet() && targetSet != null
				? CardCatalogue.packPool(targetSet, rarity)
				: CardCatalogue.packPool(rarity);
		}

		// Both fallbacks stay inside the pack pool. Reaching for the whole catalogue
		// here is what would put a Mythic Invocation card in a Scrap Pack.
		if (pool.isEmpty())
		{
			pool = CardCatalogue.packPool(rarity);
		}

		return pool.get(random.nextInt(pool.size()));
	}
}
