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

import lombok.Getter;

import java.awt.Color;

@Getter
public enum Rarity
{
	COMMON("Common", new Color(0xA6, 0xB2, 0xC4), 0.6000d),
	UNCOMMON("Uncommon", new Color(0x4C, 0xAF, 0x50), 0.2700d),
	RARE("Rare", new Color(0x42, 0xA5, 0xF5), 0.1000d),
	EPIC("Epic", new Color(0xAB, 0x47, 0xBC), 0.0270d),
	LEGENDARY("Legendary", new Color(0xFF, 0xB3, 0x00), 0.0030d);

	private final String displayName;
	private final Color colour;
	private final double packWeight;

	Rarity(String displayName, Color colour, double packWeight)
	{
		this.displayName = displayName;
		this.colour = colour;
		this.packWeight = packWeight;
	}

	public boolean isPityWorthy()
	{
		return ordinal() >= RARE.ordinal();
	}

	public static final int MAX_STARS = 10;

	public int[] starThresholds()
	{
		switch (this)
		{
			case COMMON:
				return new int[]{1, 2, 3, 5, 8, 12, 17, 24, 33, 45};
			case UNCOMMON:
				return new int[]{1, 2, 3, 4, 6, 9, 13, 18, 25, 34};
			case RARE:
				return new int[]{1, 2, 3, 4, 6, 8, 11, 15, 20, 26};
			case EPIC:
				return new int[]{1, 2, 3, 4, 5, 6, 8, 11, 14, 18};
			default:
				return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		}
	}
	public int copiesForMaxStars()
	{
		return starThresholds()[MAX_STARS - 1];
	}
	public int starsFor(int copies)
	{
		int[] thresholds = starThresholds();
		int stars = 0;
		for (int threshold : thresholds)
		{
			if (copies >= threshold)
			{
				stars++;
			}
		}
		return stars;
	}
	public int copiesForNextStar(int copies)
	{
		int[] thresholds = starThresholds();
		for (int threshold : thresholds)
		{
			if (copies < threshold)
			{
				return threshold;
			}
		}
		return 0;
	}

	// Solved from measured pack yields so cost per star falls as tiers rise.
	public int starWeight()
	{
		switch (this)
		{
			case COMMON:
				return 1;
			case UNCOMMON:
				return 2;
			case RARE:
				return 5;
			case EPIC:
				return 17;
			default:
				return 135;
		}
	}

	public Rarity next()
	{
		return this == LEGENDARY ? null : values()[ordinal() + 1];
	}

}
