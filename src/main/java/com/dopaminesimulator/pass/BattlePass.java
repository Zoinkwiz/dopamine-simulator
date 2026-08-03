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
package com.dopaminesimulator.pass;

import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.packs.PackTier;

public final class BattlePass
{
	public static final int TIERS = 50;

	private static final double XP_BASE = 150d;
	private static final double XP_PER_TIER = 35d;
	private static final double SEASON_XP_GROWTH = 0.05d;

	private static final double PREMIUM_BASE = 12_000d;
	private static final double PREMIUM_GROWTH = 1.18d;

	private BattlePass()
	{
	}

	public static double xpForTier(int tier, int season)
	{
		return (XP_BASE + XP_PER_TIER * tier) * (1d + SEASON_XP_GROWTH * (season - 1));
	}

	public static double xpForSeason(int season)
	{
		double total = 0d;
		for (int tier = 1; tier <= TIERS; tier++)
		{
			total += xpForTier(tier, season);
		}
		return total;
	}

	public static int tierAt(double xp, int season)
	{
		double spent = 0d;
		for (int tier = 1; tier <= TIERS; tier++)
		{
			spent += xpForTier(tier, season);
			if (xp < spent)
			{
				return tier - 1;
			}
		}
		return TIERS;
	}

	public static double xpIntoTier(double xp, int season)
	{
		double spent = 0d;
		for (int tier = 1; tier <= TIERS; tier++)
		{
			double cost = xpForTier(tier, season);
			if (xp < spent + cost)
			{
				return xp - spent;
			}
			spent += cost;
		}
		return 0d;
	}

	public static double premiumCost(int season)
	{
		return PREMIUM_BASE * Math.pow(PREMIUM_GROWTH, season - 1);
	}

	public static boolean isMilestone(int tier)
	{
		return tier % 10 == 0;
	}

	public static PassReward freeReward(int tier, int season)
	{
		return PassTheme.forSeason(season).free(tier, season);
	}

	public static PassReward premiumReward(int tier, int season)
	{
		return PassTheme.forSeason(season).premium(tier, season);
	}

	static PackTier packFor(int season, int step)
	{
		PackTier[] tiers = PackTier.values();
		int index = Math.max(0, Math.min(tiers.length - 1, season - 1 + step + 1));
		return tiers[index];
	}

	static Rarity shardFor(int season, int step)
	{
		Rarity[] rarities = Rarity.values();
		int index = Math.max(0, Math.min(rarities.length - 1, season - 1 + step));
		return rarities[index];
	}
}
