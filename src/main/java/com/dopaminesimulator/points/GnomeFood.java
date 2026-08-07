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
package com.dopaminesimulator.points;

import java.util.Locale;
import java.util.Random;
import lombok.Getter;
import net.runelite.api.gameval.ItemID;

@Getter
public enum GnomeFood
{

	CHOCCHIP_CRUNCHIES("Chocchip crunchies", ItemID.CHOCCHIP_CRUNCHIES, 30, 0L,
		"60 clicks, free"),

	SPICY_CRUNCHIES("Spicy crunchies", ItemID.SPICY_CRUNCHIES, 20, 10_000L,
		"Clicks pay 5x for 10s"),

	WORM_HOLE("Worm hole", ItemID.WORM_HOLE, 16, 60_000L,
		"All sources 4x for 60s"),

	TOAD_CRUNCHIES("Toad crunchies", ItemID.TOAD_CRUNCHIES, 12, 0L,
		"Dust"),

	FRUIT_BLAST("Fruit blast", ItemID.FRUIT_BLAST, 10, 0L,
		"Pass XP"),

	// The one dish that is worth nothing. It only costs the player who bites.
	WORM_BATTA("Worm batta", ItemID.WORM_BATTA, 8, 0L,
		"Bite it and everything pays half for 60s"),

	TANGLED_TOADS_LEGS("Tangled toads' legs", ItemID.TANGLED_TOADS_LEGS, 7, 0L,
		"A free pack"),

	WIZARD_BLIZZARD("Wizard blizzard", ItemID.WIZARD_BLIZZARD, 5, 0L,
		"Points, amount varies");

	public static final double FRENZY_MULTIPLIER = 5d;

	public static final double WORM_HOLE_MULTIPLIER = 4d;

	public static final double SOUR_MULTIPLIER = 0.5d;

	public static final long SOUR_MS = 60_000L;

	public static final int CRUNCHIES_CLICKS = 200;

	public static final double FRUIT_BLAST_PASS_XP = 30d;

	private final String displayName;
	private final int itemId;
	private final int weight;
	private final long durationMs;
	private final String blurb;

	/** Chat command that serves this dish on demand, e.g. {@code ::wormhole}. */
	private final String command;

	private static final int TOTAL_WEIGHT;

	static
	{
		int total = 0;
		for (GnomeFood food : values())
		{
			total += food.weight;
		}
		TOTAL_WEIGHT = total;
	}

	GnomeFood(String displayName, int itemId, int weight, long durationMs, String blurb)
	{
		this.displayName = displayName;
		this.itemId = itemId;
		this.weight = weight;
		this.durationMs = durationMs;
		this.blurb = blurb;
		this.command = displayName.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
	}

	public static GnomeFood byCommand(String command)
	{
		for (GnomeFood food : values())
		{
			if (food.command.equalsIgnoreCase(command))
			{
				return food;
			}
		}
		return null;
	}

	public boolean lasts()
	{
		return durationMs > 0L;
	}

	/** The dish that pays nothing and sours whoever eats it. */
	public boolean isTrap()
	{
		return this == WORM_BATTA;
	}

	public double clickMultiplier()
	{
		return this == SPICY_CRUNCHIES ? FRENZY_MULTIPLIER : 1d;
	}

	public double incomeMultiplier()
	{
		return this == WORM_HOLE ? WORM_HOLE_MULTIPLIER : 1d;
	}

	public static GnomeFood roll(Random random)
	{
		int pick = random.nextInt(TOTAL_WEIGHT);
		for (GnomeFood food : values())
		{
			pick -= food.weight;
			if (pick < 0)
			{
				return food;
			}
		}
		return CHOCCHIP_CRUNCHIES;
	}
}
