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

public final class Prestige
{

	public static final double MIN_LIFETIME = 10_000_000d;

	public static final int PER_RESET = 1;

	private Prestige()
	{
	}

	// Running out and having bought everything are the same event.
	public static int maximum()
	{
		return InsightPerk.costOfEverything();
	}

	public static boolean isMaxed(int insightHeld)
	{
		return insightHeld >= maximum();
	}

	public static boolean canPrestige(double lifetimePoints)
	{
		return lifetimePoints >= MIN_LIFETIME;
	}

	public static double pointsUntilPrestige(double lifetimePoints)
	{
		return Math.max(0d, MIN_LIFETIME - lifetimePoints);
	}

	public static int gainFrom(double lifetimePoints, int insightHeld)
	{
		return canPrestige(lifetimePoints) && !isMaxed(insightHeld) ? PER_RESET : 0;
	}
}
