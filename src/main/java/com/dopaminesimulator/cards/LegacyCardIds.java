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
package com.dopaminesimulator.cards;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cards that changed set, and so changed id.
 *
 * An id is the save key, so moving a card between sets renames it and every copy
 * a player holds points at nothing. This maps the old key onto the new one at load
 * time. It is a frozen record of what moved, not a live lookup: once an entry is
 * here it stays, because saves older than the move keep turning up.
 *
 * The Items set was reorganised down to ordinary goods and its gear was spread
 * across Raids Drops and the combat sets.
 *
 * Where the card survived in more than one place the pack copy wins, so a
 * migration never hands out a banner exclusive to somebody who did not pull it.
 * Nine of these only survived as an exclusive, and those still migrate: the player
 * already owned the card, and taking it away to protect the banner would be
 * punishing them for a change they did not make.
 */
public final class LegacyCardIds
{
	private static final Map<String, String> MOVED;

	static
	{
		Map<String, String> moved = new LinkedHashMap<>();

		// Survived in the pack pool: migrate there.
		moved.put("items-arcane-prayer-scroll", "raids_drops-arcane-prayer-scroll");
		moved.put("items-avernic-defender", "attack-avernic-defender");
		moved.put("items-dinh-s-bulwark", "attack-dinh-s-bulwark");
		moved.put("items-dragon-hunter-crossbow", "ranged-dragon-hunter-crossbow");
		moved.put("items-elder-maul", "attack-elder-maul");
		moved.put("items-elidinis-ward", "magic-elidinis-ward");
		moved.put("items-ghrazi-rapier", "attack-ghrazi-rapier");
		moved.put("items-osmumten-s-fang", "attack-osmumten-s-fang");
		moved.put("items-sanguinesti-staff", "magic-sanguinesti-staff");
		moved.put("items-scythe-of-vitur", "attack-scythe-of-vitur");
		moved.put("items-tumeken-s-shadow", "magic-tumeken-s-shadow");
		moved.put("items-twisted-bow", "ranged-twisted-bow");
		moved.put("items-twisted-buckler", "ranged-twisted-buckler");

		// Only survived as a Mythic Invocation exclusive. Already-owned copies keep.
		moved.put("items-ancestral-hat", "raids_drops-ancestral-hat");
		moved.put("items-ancestral-robe-top", "raids_drops-ancestral-robe-top");
		moved.put("items-dexterous-prayer-scroll", "raids_drops-dexterous-prayer-scroll");
		moved.put("items-dragon-claws", "raids_drops-dragon-claws");
		moved.put("items-justiciar-chestguard", "raids_drops-justiciar-chestguard");
		moved.put("items-justiciar-faceguard", "raids_drops-justiciar-faceguard");
		moved.put("items-lightbearer", "raids_drops-lightbearer");
		moved.put("items-masori-body", "raids_drops-masori-body");
		moved.put("items-masori-mask", "raids_drops-masori-mask");

		MOVED = Collections.unmodifiableMap(moved);
	}

	private LegacyCardIds()
	{
	}

	/** The id a save should be reading instead, or the same id if nothing moved. */
	public static String current(String cardId)
	{
		String moved = MOVED.get(cardId);
		return moved == null ? cardId : moved;
	}

	public static boolean hasMoved(String cardId)
	{
		return MOVED.containsKey(cardId);
	}

	public static Map<String, String> all()
	{
		return MOVED;
	}
}
