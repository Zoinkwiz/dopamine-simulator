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
package com.dopaminesimulator.feats;

import java.awt.Color;
import lombok.Getter;

@Getter
public enum Feat
{
	BESTIARY("Bestiary", "Different creatures killed",
		FeatTrack.DISTINCT_NPCS,
		new long[]{1, 2, 3, 4, 5, 6, 9, 12, 20, 30, 40, 60, 75, 125, 200, 250, 400, 600, 750,
			1_200}),
	CULLING("Culling", "Total kills",
		FeatTrack.KILLS,
		new long[]{10, 20, 30, 60, 125, 200, 400, 750, 1_250, 2_500, 4_000, 7_500, 15_000,
			25_000, 50_000, 75_000, 150_000, 300_000, 500_000, 1_000_000}),
	JACKPOT("Jackpot", "Largest single drop",
		FeatTrack.BIGGEST_DROP,
		new long[]{50, 125, 300, 750, 2_000, 5_000, 12_500, 30_000, 75_000, 150_000, 400_000,
			1_000_000, 2_500_000, 6_000_000, 15_000_000, 40_000_000, 75_000_000, 250_000_000,
			600_000_000, 1_500_000_000}),
	HOARDER("Hoarder", "Total loot value",
		FeatTrack.TOTAL_LOOT,
		new long[]{1_000, 2_500, 5_000, 10_000, 20_000, 40_000, 100_000, 200_000, 400_000,
			1_000_000, 2_000_000, 4_000_000, 7_500_000, 15_000_000, 40_000_000,
			75_000_000, 150_000_000, 300_000_000, 600_000_000, 1_500_000_000}),
	MAXED("Maxed", "Skills at level 99",
		FeatTrack.SKILLS_MAXED,
		new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 23}),

	CLIMBER("Climber", "Total experience on the account",
		FeatTrack.TOTAL_XP,
		new long[]{10_000, 20_000, 40_000, 80_000, 160_000, 320_000, 640_000,
			1_300_000, 2_500_000, 5_000_000, 10_000_000, 20_000_000, 40_000_000,
			80_000_000, 160_000_000, 320_000_000, 640_000_000, 1_300_000_000L,
			2_500_000_000L, 4_600_000_000L}),
	WANDERER("Wanderer", "Tiles travelled",
		FeatTrack.TILES,
		new long[]{100, 200, 400, 750, 1_500, 2_500, 5_000, 7_500, 15_000, 30_000, 50_000,
			100_000, 200_000, 300_000, 600_000, 1_500_000, 2_000_000, 4_000_000,
			7_500_000, 15_000_000}),
	MASOCHIST("Masochist", "Damage taken",
		FeatTrack.DAMAGE_TAKEN,
		new long[]{50, 100, 200, 300, 500, 1_000, 2_000, 3_000, 5_000, 10_000, 20_000, 30_000,
			50_000, 100_000, 200_000, 300_000, 500_000, 1_000_000, 2_000_000, 3_000_000}),
	GLUTTON("Glutton", "Hitpoints restored",
		FeatTrack.HEALTH_RESTORED,
		new long[]{50, 100, 200, 300, 500, 1_000, 2_000, 3_000, 5_000, 10_000, 15_000, 30_000,
			50_000, 100_000, 150_000, 250_000, 500_000, 1_000_000, 1_500_000, 2_500_000}),
	SCHOLAR("Scholar", "Experience earned while running",
		FeatTrack.WEIGHTED_XP,
		new long[]{5_000, 10_000, 20_000, 30_000, 50_000, 100_000, 150_000, 250_000, 500_000,
			750_000, 1_500_000, 2_500_000, 4_000_000, 7_500_000, 15_000_000, 20_000_000,
			40_000_000, 60_000_000, 150_000_000, 200_000_000}),
	RESIDENT("Resident", "Time logged in",
		FeatTrack.TICKS_PLAYED,
		new long[]{500, 750, 1_500, 2_500, 5_000, 7_500, 15_000, 25_000, 40_000, 75_000, 125_000,
			250_000, 400_000, 750_000, 1_250_000, 2_000_000, 4_000_000, 6_000_000, 12_500_000,
			20_000_000});

	public static final int RANKS = 20;

	public static final String[] BAND_NAMES = {
		"Bronze", "Iron", "Steel", "Mithril", "Adamant", "Rune"
	};

	private static final Color[] TIER_COLOURS = {
		new Color(0xCD, 0x7F, 0x32),
		new Color(0x8A, 0x8A, 0x8F),
		new Color(0xC5, 0xC5, 0xCE),
		new Color(0x5A, 0x6B, 0xC4),
		new Color(0x4C, 0x9A, 0x5E),
		new Color(0x4F, 0xC3, 0xD9)
	};

	private static final Color UNRANKED = new Color(0x4A, 0x4A, 0x50);

	public static final double BONUS_PER_TIER = 0.01d;

	public static final double BONUS_PER_RANK_SHARE = 0.00087d;

	private final String displayName;
	private final String description;
	private final FeatTrack track;
	private final long[] thresholds;

	Feat(String displayName, String description, FeatTrack track, long[] thresholds)
	{
		this.displayName = displayName;
		this.description = description;
		this.track = track;
		this.thresholds = thresholds;
	}

	public int maxTier()
	{
		return thresholds.length;
	}

	public int tierFor(long progress)
	{
		int tier = 0;
		for (long threshold : thresholds)
		{
			if (progress >= threshold)
			{
				tier++;
			}
		}
		return tier;
	}

	public long nextThreshold(long progress)
	{
		for (long threshold : thresholds)
		{
			if (progress < threshold)
			{
				return threshold;
			}
		}
		return 0L;
	}

	public static int bandFor(int tier)
	{
		if (tier <= 0)
		{
			return -1;
		}
		return Math.min(BAND_NAMES.length - 1,
			(Math.min(tier, RANKS) - 1) * BAND_NAMES.length / RANKS);
	}

	public String tierName(int tier)
	{
		int band = bandFor(tier);
		return band < 0 ? "Unranked" : BAND_NAMES[band];
	}

	public static Color tierColour(int tier)
	{
		int band = bandFor(tier);
		return band < 0 ? UNRANKED : TIER_COLOURS[band];
	}

	public static int totalTiers()
	{
		int total = 0;
		for (Feat feat : values())
		{
			total += feat.maxTier();
		}
		return total;
	}
}
