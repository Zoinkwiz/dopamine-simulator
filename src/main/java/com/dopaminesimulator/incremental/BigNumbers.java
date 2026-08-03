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

public final class BigNumbers
{
	private static final String[] SUFFIXES = {
		"", "K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp", "Oc", "No", "Dc"
	};
	private BigNumbers()
	{
	}
	public static String format(double value)
	{
		if (Double.isNaN(value) || Double.isInfinite(value))
		{
			return "∞";
		}
		if (value < 0)
		{
			return "-" + format(-value);
		}
		if (value < 1000d)
		{
			if (value > 0d && value < 0.1d)
			{
				return "<0.1";
			}
			return value < 10d && value != Math.floor(value)
				? String.format("%.1f", value)
				: String.valueOf((long) value);
		}
		int tier = (int) (Math.log10(value) / 3d);
		if (tier >= SUFFIXES.length)
		{
			return String.format("%.2fe%d", value / Math.pow(10, Math.floor(Math.log10(value))),
				(long) Math.floor(Math.log10(value)));
		}
		double scaled = value / Math.pow(1000d, tier);
		String formatted = scaled >= 100d ? String.format("%.0f", scaled)
			: scaled >= 10d ? String.format("%.1f", scaled)
				: String.format("%.2f", scaled);
		return formatted + SUFFIXES[tier];
	}

	/**
	 * Reads back what {@link #format} writes, so "2.5m" is a number a player can
	 * type. Returns NaN for anything that is not one.
	 */
	public static double parse(String text)
	{
		if (text == null)
		{
			return Double.NaN;
		}
		String trimmed = text.trim().replace(",", "");
		if (trimmed.isEmpty())
		{
			return Double.NaN;
		}

		double scale = 1d;
		for (int tier = SUFFIXES.length - 1; tier > 0; tier--)
		{
			String suffix = SUFFIXES[tier];
			if (trimmed.length() > suffix.length()
				&& trimmed.regionMatches(true, trimmed.length() - suffix.length(),
					suffix, 0, suffix.length()))
			{
				scale = Math.pow(1000d, tier);
				trimmed = trimmed.substring(0, trimmed.length() - suffix.length());
				break;
			}
		}

		try
		{
			return Double.parseDouble(trimmed) * scale;
		}
		catch (NumberFormatException ignored)
		{
			return Double.NaN;
		}
	}
}
