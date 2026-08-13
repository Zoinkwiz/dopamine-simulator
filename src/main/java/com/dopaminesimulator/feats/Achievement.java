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

import lombok.Getter;

@Getter
public enum Achievement
{
	FIRST_BLOOD("First Blood", "Kill something. Anything.", false),
	POCKET_CHANGE("Pocket Change", "Pick up your first loot", false),
	DING("Ding", "Gain a level with the plugin running", false),
	OPENING_NIGHT("Opening Night", "Open your first pack", false),

	VARIETY_ACT("Variety Act", "Train ten different skills in one session", false),
	MARATHON("Marathon", "Stay logged in for four hours straight", false),
	SPRINTER("Sprinter", "Cover 2,000 tiles in one session", false),
	UNTOUCHABLE("Untouchable", "Kill fifty things in a session without taking a hit", false),
	GLASS_JAW("Glass Jaw", "Take 500 damage in a single session", false),
	NIGHT_SHIFT("Night Shift", "Earn from six different sources in one session", false),

	BEGINNERS_LUCK("Beginner's Luck", "Pull an Epic in the first fifty packs of a run", false),
	SPARKLE("Sparkle", "Find your first shiny", false),
	GILT_TRIP("Gilt Trip", "Find your first gilded card", false),
	DOUBLE_UP("Double Up", "Own a card that is both shiny and gilded", false),
	FULL_HOUSE("Full House", "Take any collection to Diamond", false),
	PACK_RAT("Pack Rat", "Open a thousand packs", false),
	HIGH_ROLLER("High Roller", "Unlock the Mythic Pack", false),

	WELL_ROUNDED("Well Rounded", "Earn a rank in every feat", false),
	DEDICATION("Dedication", "Reach rank ten in any feat", false),

	IDLE_HANDS("Idle Hands", "Spend an hour idle and still get paid", true),
	BUTTON_MASHER("Button Masher", "Click the coin a thousand times in one session", true),
	TOUCH_GRASS("Touch Grass", "Play for eight hours in one sitting", true);

	private final String displayName;
	private final String description;
	private final boolean hidden;

	Achievement(String displayName, String description, boolean hidden)
	{
		this.displayName = displayName;
		this.description = description;
		this.hidden = hidden;
	}
}
