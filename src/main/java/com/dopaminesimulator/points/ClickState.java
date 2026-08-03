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

public class ClickState
{
	public static final double SURGE_UNLOCK_AT = 25_000d;

	public static final double BASE_SURGE_CHANCE_PER_TICK = 0.00125d;

	// Bounded: four of the seven dishes pay a share of total income.
	public static final double SURGE_RATE_CAP = 1.75d;

	public static final int MAX_LEVEL = 20;

	public static final long SHOW_FOR_MS = 4_000L;

	private long surgeEndsAt;
	private long showUntil;
	private long lastSurgeStartedAt;
	private GnomeFood active;

	public static double surgeChancePerTick(int clickLevel)
	{
		return surgeChancePerTick(clickLevel, 1d);
	}

	public static double surgeChancePerTick(int clickLevel, double perkRate)
	{
		return BASE_SURGE_CHANCE_PER_TICK * rateMultiplier(clickLevel) * perkRate;
	}

	public static double rateMultiplier(int clickLevel)
	{
		int level = Math.max(0, Math.min(MAX_LEVEL, clickLevel));
		return 1d + (SURGE_RATE_CAP - 1d) * level / MAX_LEVEL;
	}

	public static double surgesPerHour(int clickLevel)
	{
		return surgeChancePerTick(clickLevel) * 6_000d;
	}

	public boolean isSurging(long nowMs)
	{
		return nowMs < surgeEndsAt;
	}

	public GnomeFood getActive(long nowMs)
	{
		return isSurging(nowMs) ? active : null;
	}

	public void start(GnomeFood food, long nowMs)
	{
		active = food;
		lastSurgeStartedAt = nowMs;
		surgeEndsAt = food.lasts() ? nowMs + food.getDurationMs() : 0L;
		showUntil = Math.max(surgeEndsAt, nowMs + SHOW_FOR_MS);
	}

	public GnomeFood getShown(long nowMs)
	{
		return nowMs < showUntil ? active : null;
	}

	public double multiplier(long nowMs)
	{
		GnomeFood food = getActive(nowMs);
		return food == null ? 1d : food.clickMultiplier();
	}

	public double incomeMultiplier(long nowMs)
	{
		GnomeFood food = getActive(nowMs);
		return food == null ? 1d : food.incomeMultiplier();
	}

	public double secondsRemaining(long nowMs)
	{
		return Math.max(0d, (surgeEndsAt - nowMs) / 1000d);
	}

	public void clear()
	{
		surgeEndsAt = 0L;
		showUntil = 0L;
		lastSurgeStartedAt = 0L;
		active = null;
	}
}
