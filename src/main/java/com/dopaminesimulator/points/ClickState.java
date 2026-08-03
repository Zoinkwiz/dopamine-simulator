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

	// Bounded: four of the eight dishes pay a share of total income.
	public static final double SURGE_RATE_CAP = 1.75d;

	public static final int MAX_LEVEL = 20;

	/**
	 * How long a dish sits on the plate. Nothing it does reaches the player until
	 * they click it inside this window, and a dish left alone is simply lost.
	 */
	public static final long SERVE_WINDOW_MS = 10_000L;

	private GnomeFood plated;
	private long platedUntil;

	private GnomeFood active;
	private long surgeEndsAt;
	private long lastSurgeStartedAt;
	private long sourEndsAt;

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

	/** Puts a dish on the plate. It does nothing at all until it is eaten. */
	public void serve(GnomeFood food, long nowMs)
	{
		plated = food;
		platedUntil = nowMs + SERVE_WINDOW_MS;
	}

	/** The dish waiting to be clicked, if the window has not run out. */
	public GnomeFood getPlated(long nowMs)
	{
		return nowMs < platedUntil ? plated : null;
	}

	public boolean isPlated(long nowMs)
	{
		return getPlated(nowMs) != null;
	}

	public double plateSecondsRemaining(long nowMs)
	{
		return Math.max(0d, (platedUntil - nowMs) / 1000d);
	}

	/** What the click button is showing: the plate first, then whatever is running. */
	public GnomeFood getShown(long nowMs)
	{
		GnomeFood waiting = getPlated(nowMs);
		return waiting != null ? waiting : getActive(nowMs);
	}

	/**
	 * Eats whatever is on the plate. A dish that lasts starts running from here
	 * rather than from when it was served, and a trap sours instead. Returns the
	 * dish eaten so the caller can pay it out, or null if the plate was empty.
	 */
	public GnomeFood eat(long nowMs)
	{
		GnomeFood food = getPlated(nowMs);
		if (food == null)
		{
			return null;
		}

		// Cleared off the plate, so one serving is only ever eaten once.
		plated = null;
		platedUntil = nowMs;

		if (food.isTrap())
		{
			sourEndsAt = nowMs + GnomeFood.SOUR_MS;
		}
		else if (food.lasts())
		{
			active = food;
			lastSurgeStartedAt = nowMs;
			surgeEndsAt = nowMs + food.getDurationMs();
		}
		return food;
	}

	public boolean isSoured(long nowMs)
	{
		return nowMs < sourEndsAt;
	}

	public double sourMultiplier(long nowMs)
	{
		return isSoured(nowMs) ? GnomeFood.SOUR_MULTIPLIER : 1d;
	}

	public double sourSecondsRemaining(long nowMs)
	{
		return Math.max(0d, (sourEndsAt - nowMs) / 1000d);
	}

	public double multiplier(long nowMs)
	{
		GnomeFood food = getActive(nowMs);
		return food == null ? 1d : food.clickMultiplier();
	}

	// A sour rides on top of whatever is being served, so a dish eaten during one
	// is still worth eating, just worth half of what it says.
	public double incomeMultiplier(long nowMs)
	{
		GnomeFood food = getActive(nowMs);
		return (food == null ? 1d : food.incomeMultiplier()) * sourMultiplier(nowMs);
	}

	// What a click is worth right now. Clicking is a source, so a dish that lifts
	// every source lifts it too, on top of any dish aimed at clicking itself.
	public double clickPayoutMultiplier(long nowMs)
	{
		return multiplier(nowMs) * incomeMultiplier(nowMs);
	}

	public double secondsRemaining(long nowMs)
	{
		return Math.max(0d, (surgeEndsAt - nowMs) / 1000d);
	}

	public void clear()
	{
		plated = null;
		platedUntil = 0L;
		active = null;
		surgeEndsAt = 0L;
		lastSurgeStartedAt = 0L;
		sourEndsAt = 0L;
	}
}
