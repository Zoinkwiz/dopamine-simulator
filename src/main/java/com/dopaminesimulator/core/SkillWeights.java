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
package com.dopaminesimulator.core;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class SkillWeights
{
	private static final double BASELINE_XP_PER_HOUR = 60_000d;
	private static final double DEFAULT_WEIGHT = 1.0d;
	private static final Map<String, Double> WEIGHTS = new HashMap<>();
	static
	{
		rate("AGILITY", 15_000);
		rate("RUNECRAFT", 25_000);
		rate("SLAYER", 25_000);
		rate("MINING", 40_000);
		rate("FISHING", 35_000);
		rate("WOODCUTTING", 45_000);
		rate("HUNTER", 55_000);
		rate("ATTACK", 60_000);
		rate("STRENGTH", 60_000);
		rate("DEFENCE", 60_000);
		rate("HITPOINTS", 60_000);
		rate("RANGED", 65_000);
		rate("THIEVING", 60_000);
		rate("MAGIC", 80_000);
		rate("SMITHING", 100_000);
		rate("FARMING", 100_000);
		rate("CRAFTING", 150_000);
		rate("FIREMAKING", 150_000);
		rate("COOKING", 200_000);
		rate("HERBLORE", 250_000);
		rate("FLETCHING", 300_000);
		rate("PRAYER", 300_000);
		rate("CONSTRUCTION", 300_000);
	}
	private SkillWeights()
	{
	}
	private static void rate(String skill, double xpPerHour)
	{
		WEIGHTS.put(skill, BASELINE_XP_PER_HOUR / xpPerHour);
	}
	public static double weightFor(String skillName)
	{
		if (skillName == null)
		{
			return DEFAULT_WEIGHT;
		}
		return WEIGHTS.getOrDefault(skillName.toUpperCase(Locale.ROOT), DEFAULT_WEIGHT);
	}
	public static double weightedXp(String skillName, long rawXp)
	{
		return rawXp * weightFor(skillName);
	}
}
