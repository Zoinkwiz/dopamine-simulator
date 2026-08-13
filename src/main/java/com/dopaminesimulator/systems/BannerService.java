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
import com.dopaminesimulator.cards.CardOrigin;
import com.dopaminesimulator.cards.CardOrigins;
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.packs.PackTier;
import com.dopaminesimulator.pass.SeasonClock;
import java.util.List;
import java.util.Random;

public class BannerService
{
	public static final int HARD_PITY = 100;
	public static final int SOFT_PITY_FROM = 75;
	public static final double BASE_RATE = 0.006d;
	public static final double SOFT_PITY_STEP = 0.035d;

	public static final Rarity[] BANNERS = {Rarity.RARE, Rarity.EPIC, Rarity.LEGENDARY};

	static
	{
		// Every banner needs its own exclusives. A banner rarity with an empty pool
		// used to fall back to ordinary pack cards, which quietly made that banner a
		// worse pack. Fail at startup instead of at the pull.
		for (Rarity rarity : BANNERS)
		{
			if (CardCatalogue.byOriginAndRarity(CardOrigin.GACHA, rarity).isEmpty())
			{
				throw new IllegalStateException("The " + rarity + " banner has no gacha"
					+ " cards to feature. Give it a pool in CardOrigins or drop the"
					+ " banner: it cannot fall back to pack cards.");
			}
		}
	}

	private final Random random;
	private final PackService packs;
	private final CollectionService collection;

	public BannerService(Random random, PackService packs, CollectionService collection)
	{
		this.random = random;
		this.packs = packs;
		this.collection = collection;
	}

	public double pullCost(Rarity rarity)
	{
		switch (rarity)
		{
			case RARE:
				return 25_000d;
			case EPIC:
				return 90_000d;
			default:
				return 300_000d;
		}
	}

	public String bannerName(Rarity rarity)
	{
		switch (rarity)
		{
			case RARE:
				return "Curious Findings";
			case EPIC:
				return "Storied Relics";
			default:
				return "Mythic Invocation";
		}
	}

	public PackTier packFor(Rarity rarity)
	{
		switch (rarity)
		{
			case RARE:
				return PackTier.GILDED;
			case EPIC:
				return PackTier.CURATED;
			default:
				return PackTier.PRISMATIC;
		}
	}

	public int featuredCopies(Rarity rarity)
	{
		return CardOrigin.GACHA.copiesPerAward(rarity);
	}

	public int featuredStars(Rarity rarity)
	{
		return rarity.starsFor(featuredCopies(rarity));
	}

	public boolean rollIfExpired(DopamineState state, long nowMs)
	{
		int key = SeasonClock.bannerKey(nowMs);
		if (state.getBannerKey() == key)
		{
			return false;
		}

		boolean first = state.getBannerKey() == 0;
		state.setBannerKey(key);
		for (Rarity rarity : BANNERS)
		{
			if (!first || state.getBannerCard(rarity) == null)
			{
				roll(state, rarity);
			}
		}
		return !first;
	}

	public Card featured(DopamineState state, Rarity rarity)
	{
		String id = state.getBannerCard(rarity);
		Card card = id == null ? null : CardCatalogue.byId(id);

		// A save written before the pools split names a card this banner no longer
		// offers. Re-roll it rather than hand out another source's exclusive.
		return card == null || !fits(card, rarity) ? roll(state, rarity) : card;
	}

	public double rateAt(int pity)
	{
		if (pity >= HARD_PITY - 1)
		{
			return 1d;
		}
		if (pity < SOFT_PITY_FROM)
		{
			return BASE_RATE;
		}
		return Math.min(1d, BASE_RATE + (pity - SOFT_PITY_FROM + 1) * SOFT_PITY_STEP);
	}

	public boolean canPull(DopamineState state, Rarity rarity)
	{
		return state.getPoints() >= pullCost(rarity)
			&& state.getStars(featured(state, rarity).getId()) < Rarity.MAX_STARS;
	}

	public Card pull(DopamineState state, Rarity rarity, CardSet targetSet, RewardQueue rewards)
	{
		if (!canPull(state, rarity) || !state.spendPoints(pullCost(rarity)))
		{
			return null;
		}

		Card prize = featured(state, rarity);
		state.setBannerPulls(state.getBannerPulls() + 1);
		packs.openFree(state, packFor(rarity), targetSet, 1, rewards);

		if (random.nextDouble() < rateAt(state.getBannerPity(rarity)))
		{
			state.setBannerPity(rarity, 0);
			rewards.push(Reward.bannerWin(prize));
			collection.grant(state, prize, rewards, false, featuredCopies(rarity));

			// The card stays up. A banner is a window you can keep pulling at, so
			// only the clock in rollIfExpired takes it down.
			return prize;
		}

		state.setBannerPity(rarity, state.getBannerPity(rarity) + 1);
		return null;
	}

	public Card roll(DopamineState state, Rarity rarity)
	{
		List<Card> pool = featuredPool(rarity);
		Card chosen = pool.get(random.nextInt(pool.size()));
		state.setBannerCard(rarity, chosen.getId());
		return chosen;
	}

	/**
	 * Every banner features its own gacha exclusives and nothing else. There is no
	 * fallback to the pack pool on purpose: a banner that could quietly feature an
	 * ordinary card would make the whole mechanic a lottery for things you could
	 * have bought. The static block above is what guarantees the pool is there.
	 */
	public List<Card> featuredPool(Rarity rarity)
	{
		return CardCatalogue.byOriginAndRarity(CardOrigin.GACHA, rarity);
	}

	private boolean fits(Card card, Rarity rarity)
	{
		return card.getRarity() == rarity && CardOrigins.of(card) == CardOrigin.GACHA;
	}
}
