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

import com.dopaminesimulator.points.PointSource;
import com.dopaminesimulator.points.PointSource;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Map;
import lombok.Value;

public class IncomeTracker implements PointListener
{
	private static final long WINDOW_TICKS = 3000L;

	@Value
	public static class Entry
	{
		PointSource source;
		double amount;
		long tick;
	}

	private final Deque<Entry> entries = new ArrayDeque<>();
	private final double[] totals = new double[PointSource.values().length];

	// Swapped whole, never edited, so Swing never reads a half-written state.
	private volatile double[] published = new double[PointSource.values().length];
	private volatile long oldestTick;
	private volatile boolean empty = true;

	private long latestTick;

	@Override
	public void onPointsGained(PointSource source, String detail, double amount, long tick)
	{
		latestTick = Math.max(latestTick, tick);
		entries.addLast(new Entry(source, amount, tick));
		totals[source.ordinal()] += amount;
		prune();
		publish();
	}

	private void prune()
	{
		while (!entries.isEmpty() && latestTick - entries.peekFirst().getTick() > WINDOW_TICKS)
		{
			Entry gone = entries.removeFirst();
			totals[gone.getSource().ordinal()] -= gone.getAmount();
		}
	}

	private void publish()
	{
		published = totals.clone();
		oldestTick = entries.isEmpty() ? 0L : entries.peekFirst().getTick();
		empty = entries.isEmpty();
	}

	public void reset()
	{
		entries.clear();
		java.util.Arrays.fill(totals, 0d);
		latestTick = 0;
		publish();
	}

	public double perHour(PointSource source, long currentTick)
	{
		double[] snapshot = published;
		if (empty)
		{
			return 0d;
		}
		long elapsed = Math.max(1L, currentTick - oldestTick);
		double total = 0d;
		if (source == null)
		{
			for (double value : snapshot)
			{
				total += value;
			}
		}
		else
		{
			total = snapshot[source.ordinal()];
		}
		return total / elapsed * Balance.TICKS_PER_HOUR;
	}

	public double totalPerHour(long currentTick)
	{
		return perHour(null, currentTick);
	}

	public Map<PointSource, Double> breakdown(long currentTick)
	{
		double[] snapshot = published;
		Map<PointSource, Double> rates = new EnumMap<>(PointSource.class);
		if (empty)
		{
			for (PointSource source : PointSource.values())
			{
				rates.put(source, 0d);
			}
			return rates;
		}
		long elapsed = Math.max(1L, currentTick - oldestTick);
		for (PointSource source : PointSource.values())
		{
			rates.put(source, snapshot[source.ordinal()] / elapsed * Balance.TICKS_PER_HOUR);
		}
		return rates;
	}
}
