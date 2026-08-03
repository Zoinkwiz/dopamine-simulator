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

public final class Milestones
{

	private static final double[] THRESHOLDS = {
		10_000d, 17_000d, 29_000d, 50_000d, 85_000d, 145_000d,
		248_000d, 423_000d, 722_000d, 1_230_000d, 2_100_000d, 3_600_000d,
		6_100_000d, 10_400_000d, 17_800_000d, 30_400_000d, 51_800_000d,
		88_500_000d, 151_000_000d, 258_000_000d, 440_000_000d, 751_000_000d,
		1_280_000_000d, 2_190_000_000d
	};

	private static final double BONUS_EACH = 0.07d;

	public static final int MAX_MILESTONES = THRESHOLDS.length;
	private Milestones()
	{
	}

	public static int reached(double lifetimePoints)
	{
		int count = 0;
		for (double threshold : THRESHOLDS)
		{
			if (lifetimePoints >= threshold)
			{
				count++;
			}
		}
		return count;
	}
	public static double globalMultiplier(double lifetimePoints)
	{
		return globalMultiplier(lifetimePoints, null);
	}

	public static double globalMultiplier(double lifetimePoints,
		com.dopaminesimulator.core.DopamineState state)
	{
		return 1d + reached(lifetimePoints) * (BONUS_EACH + Perks.perMilestoneBonus(state));
	}

	public static double nextAt(double lifetimePoints)
	{
		for (double threshold : THRESHOLDS)
		{
			if (lifetimePoints < threshold)
			{
				return threshold;
			}
		}
		return 0d;
	}
}
