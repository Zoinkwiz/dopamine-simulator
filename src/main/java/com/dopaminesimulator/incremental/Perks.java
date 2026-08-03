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

import com.dopaminesimulator.core.DopamineState;

public final class Perks
{
	private Perks()
	{
	}

	private static int rank(DopamineState state, InsightPerk perk)
	{
		return state == null ? 0 : state.getPerkRank(perk);
	}

	public static double seedPoints(DopamineState state)
	{
		return InsightPerk.seedPoints(rank(state, InsightPerk.SEED_MONEY));
	}

	public static int headStartLevels(DopamineState state)
	{
		return rank(state, InsightPerk.QUICK_STUDY) * 2;
	}

	public static int headStartClickLevels(DopamineState state)
	{
		return rank(state, InsightPerk.MUSCLE_MEMORY) * 5;
	}

	public static double packCost(DopamineState state)
	{
		return Math.max(0.4d, 1d - 0.06d * rank(state, InsightPerk.HAGGLER));
	}

	public static double dust(DopamineState state)
	{
		return 1d + 0.25d * rank(state, InsightPerk.GRINDSTONE);
	}

	public static int shinyOneIn(DopamineState state, int base)
	{
		return Math.max(50, (int) Math.round(
			base * InsightPerk.rateOf(rank(state, InsightPerk.LUSTRE))));
	}

	public static int gildedOneIn(DopamineState state, int base)
	{
		return Math.max(25, (int) Math.round(
			base * InsightPerk.rateOf(rank(state, InsightPerk.GLEAM))));
	}

	public static double perMilestoneBonus(DopamineState state)
	{
		return 0.015d * rank(state, InsightPerk.LANDMARKS);
	}

	public static double dishRate(DopamineState state)
	{
		return 1d + 0.08d * rank(state, InsightPerk.BANQUET);
	}
}
