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

import com.dopaminesimulator.core.DopamineState;

public final class Feats
{
	private Feats()
	{
	}

	public static long progressOf(DopamineState state, Feat feat)
	{
		return state.getFeatProgress(feat.getTrack());
	}

	public static int tierOf(DopamineState state, Feat feat)
	{
		return feat.tierFor(progressOf(state, feat));
	}

	public static int tiersEarned(DopamineState state)
	{
		int total = 0;
		for (Feat feat : Feat.values())
		{
			total += tierOf(state, feat);
		}
		return total;
	}

	// Rank n is worth n shares, so a first hour cannot hand over most of it.
	public static double multiplierFor(DopamineState state)
	{
		double bonus = 0d;
		for (Feat feat : Feat.values())
		{
			int tier = tierOf(state, feat);

			bonus += tier * (tier + 1) / 2d * Feat.BONUS_PER_RANK_SHARE;
		}
		return 1d + bonus;
	}

	public static String titleFor(DopamineState state)
	{
		int earned = tiersEarned(state);
		int max = Feat.totalTiers();
		if (earned >= max)
		{
			return "Completionist";
		}
		if (earned >= max * 3 / 4)
		{
			return "Veteran";
		}
		if (earned >= max / 2)
		{
			return "Adventurer";
		}
		if (earned >= max / 4)
		{
			return "Apprentice";
		}
		return earned > 0 ? "Novice" : "Unproven";
	}
}
