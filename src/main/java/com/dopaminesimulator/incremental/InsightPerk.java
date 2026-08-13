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
package com.dopaminesimulator.incremental;

import java.awt.Color;
import lombok.Getter;

@Getter
public enum InsightPerk
{
	SEED_MONEY("Seed Money", "Start each run with points",
		5, 1, new Color(0xFF, 0xC8, 0x45)),

	QUICK_STUDY("Quick Study", "Start each run with source levels",
		5, 1, new Color(0x42, 0xA5, 0xF5)),

	MUSCLE_MEMORY("Muscle Memory", "Start each run with click levels",
		4, 1, new Color(0xFF, 0x98, 0x1F)),

	HAGGLER("Haggler", "Packs cost less",
		5, 1, new Color(0x66, 0xBB, 0x6A)),

	GRINDSTONE("Grindstone", "More dust from spare copies",
		5, 1, new Color(0x9E, 0x9E, 0x9E)),

	LUSTRE("Lustre", "Higher shiny chance",
		5, 1, new Color(0xAB, 0x47, 0xBC)),

	GLEAM("Gleam", "Higher gilded chance",
		5, 1, new Color(0xFF, 0xB3, 0x00)),

	LANDMARKS("Landmarks", "Milestones are worth more",
		5, 1, new Color(0x26, 0xC6, 0xDA)),

	BANQUET("Banquet", "Dishes appear more often",
		4, 1, new Color(0xFF, 0x70, 0x43));

	private final String displayName;
	private final String description;
	private final int maxRanks;
	private final int costPerRank;
	private final Color colour;

	InsightPerk(String displayName, String description, int maxRanks, int costPerRank,
				Color colour)
	{
		this.displayName = displayName;
		this.description = description;
		this.maxRanks = maxRanks;
		this.costPerRank = costPerRank;
		this.colour = colour;
	}

	public int fullCost()
	{
		return maxRanks * costPerRank;
	}

	public static int costOfEverything()
	{
		int total = 0;
		for (InsightPerk perk : values())
		{
			total += perk.fullCost();
		}
		return total;
	}

	public String effectAt(int rank)
	{
		switch (this)
		{
			case SEED_MONEY:
				return rank == 0 ? "none"
					: BigNumbers.format(seedPoints(rank)) + " points";
			case QUICK_STUDY:
				return "+" + rank * 2 + " levels";
			case MUSCLE_MEMORY:
				return "+" + rank * 2 + " click levels";
			case HAGGLER:
				return "-" + rank * 6 + "% cost";
			case GRINDSTONE:
				return "+" + rank * 25 + "% dust";
			case LUSTRE:
				return rank == 0 ? "1 in 1000" : "1 in " + (int) Math.round(1000d * rateOf(rank));
			case GLEAM:
				return rank == 0 ? "1 in 400" : "1 in " + (int) Math.round(400d * rateOf(rank));
			case LANDMARKS:
				return "+" + String.format("%.1f", rank * 1.5d) + "% each";
			default:
				return "+" + rank * 8 + "% more often";
		}
	}

	static double rateOf(int rank)
	{
		return Math.pow(0.85d, rank);
	}

	static double seedPoints(int rank)
	{
		return rank <= 0 ? 0d : Math.pow(10d, 1 + rank);
	}
}
