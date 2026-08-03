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
package com.dopaminesimulator.pass;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public final class SeasonClock
{
	public static final int BANNER_DAYS = 7;

	private SeasonClock()
	{
	}

	private static ZonedDateTime at(long nowMs)
	{
		return Instant.ofEpochMilli(nowMs).atZone(ZoneOffset.UTC);
	}

	public static int seasonKey(long nowMs)
	{
		ZonedDateTime now = at(nowMs);
		return now.getYear() * 12 + now.getMonthValue() - 1;
	}

	public static long seasonEndsAt(long nowMs)
	{
		return at(nowMs).truncatedTo(ChronoUnit.DAYS)
			.withDayOfMonth(1).plusMonths(1).toInstant().toEpochMilli();
	}

	public static int bannerKey(long nowMs)
	{
		return (int) (at(nowMs).toLocalDate().toEpochDay() / BANNER_DAYS);
	}

	public static long bannerEndsAt(long nowMs)
	{
		long day = at(nowMs).toLocalDate().toEpochDay();
		long nextWindowDay = (day / BANNER_DAYS + 1) * BANNER_DAYS;
		return at(nowMs).truncatedTo(ChronoUnit.DAYS)
			.plusDays(nextWindowDay - day).toInstant().toEpochMilli();
	}

	public static String remaining(long endsAt, long nowMs)
	{
		return brief(endsAt, nowMs) + " left";
	}

	public static String brief(long endsAt, long nowMs)
	{
		long left = Math.max(0L, endsAt - nowMs);
		long days = left / 86_400_000L;
		long hours = left / 3_600_000L % 24;
		if (days > 0)
		{
			return days + "d " + hours + "h";
		}
		long minutes = left / 60_000L % 60;
		return hours > 0 ? hours + "h " + minutes + "m" : minutes + "m";
	}
}
