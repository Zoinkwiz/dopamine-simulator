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

import lombok.Value;

@Value
public class DopamineEvent
{
	EventType type;
	String key;

	long amount;

	public static DopamineEvent xp(String skill, long xp)
	{
		return new DopamineEvent(EventType.XP_GAINED, skill, xp);
	}
	public static DopamineEvent loot(String source, long gpValue)
	{
		return new DopamineEvent(EventType.LOOT_RECEIVED, source, gpValue);
	}
	public static DopamineEvent kill(String npcName, int combatLevel)
	{
		return new DopamineEvent(EventType.NPC_KILLED, npcName, Math.max(0, combatLevel));
	}
	public static DopamineEvent levelUp(String skill, int newLevel)
	{
		return new DopamineEvent(EventType.LEVEL_UP, skill, newLevel);
	}
	public static DopamineEvent distance(long tiles)
	{
		return new DopamineEvent(EventType.DISTANCE_TRAVELLED, "Travel", tiles);
	}
	public static DopamineEvent healthRestored(long hitpoints)
	{
		return new DopamineEvent(EventType.HEALTH_RESTORED, "Eating", hitpoints);
	}

	public static DopamineEvent damageTaken(long damage)
	{
		return new DopamineEvent(EventType.DAMAGE_TAKEN, "Suffering", damage);
	}

	public static DopamineEvent click(double worth)
	{
		return new DopamineEvent(EventType.CLICK, "Click", (long) Math.max(1, worth));
	}

	public static DopamineEvent tick()
	{
		return new DopamineEvent(EventType.TICK, "", 1);
	}
}
