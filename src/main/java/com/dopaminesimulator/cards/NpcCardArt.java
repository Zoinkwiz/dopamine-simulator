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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Value;

@Value
public class NpcCardArt
{
	String displayName;

	int npcId;

	int animationId;

	int zoom;

	int verticalOffset;

	int backdropColour;

	int sceneryModelId;

	int sceneryZoom;

	int accentColour;

	String epithet;

	String roleTag;

	String mechanicTag;

	private static final Map<String, NpcCardArt> BY_ID = new LinkedHashMap<>();

	static
	{
		register("characters-verzik-vitur"     , "Verzik Vitur"     ,  8250, 8051, 3100, 33, 0x2A0A12, 30835, 2709, 0xFFC46A, "THE MATRIARCH · VER SINHAZA", "RAID BOSS", "DAWNBRINGER REQUIRED");
		register("characters-vanescula-drakan" , "Vanescula Drakan" ,  9574, 8701, 1290, 42, 0x1E1030,  6261,  573, 0xC98BFF, "VYREWATCH · DARKMEYER", "VAMPYRE", "MYREQUE ALLY");
		register("characters-seren"            , "Seren"            ,  8775, 8372, 1555, 33, 0x0E2430,  1747,  963, 0x9BF0FF, "THE GODDESS · PRIFDDINAS", "ELVEN GOD", "CRYSTAL BORN");
		register("characters-konar-quo-maten"  , "Konar quo Maten"  ,  8623, 8219, 1560, 33, 0x2A1A0E, 32266,  277, 0xFFB067, "SLAYER MASTER · MOUNT KARUULM", "SLAYER", "BRIMSTONE KEYS");
		register("characters-amascut"          , "Amascut"          , 11696,  808, 1700, 33, 0x2C2210,  6259,  573, 0xFFD98A, "THE DEVOURER · TOMBS", "DESERT GOD", "TOMBS OF AMASCUT");
		register("characters-ilfeen"           , "Ilfeen"           ,  8676,  808, 1250, 33, 0x102A18,  2168,  215, 0x8CF0C0, "CRYSTAL SINGER · ISAFDAR", "ELF", "CRYSTAL RECHARGE");
		register("characters-maisa"            , "Maisa"            ,  3876,  808, 1290, 42, 0x2A2416,  1593,  932, 0xFFDFA0, "THE ENVOY · NARDAH", "ALLY", "BENEATH CURSED SANDS");

		register("characters-commander-zilyana", "Commander Zilyana",  2205, 6966, 1855, 33, 0x14203A,  1470,  246, 0x9EC8FF, "SARADOMIN GENERAL · GWD", "RAID BOSS", "ZILYANA HILT");
	}

	private static final boolean SCENERY_ENABLED = false;

	private static void register(String cardId, String displayName, int npcId, int animationId,
								 int zoom, int vy, int backdrop, int sceneryModelId,
								 int sceneryZoom, int accent, String epithet, String roleTag,
								 String mechanicTag)
	{
		BY_ID.put(cardId, new NpcCardArt(displayName, npcId, animationId, zoom, vy,
			backdrop, SCENERY_ENABLED ? sceneryModelId : -1, sceneryZoom,
			accent, epithet, roleTag, mechanicTag));
	}

	public static NpcCardArt forCard(Card card)
	{
		return card == null ? null : BY_ID.get(card.getId());
	}

	public static NpcCardArt byId(String cardId)
	{
		if (cardId == null)
		{
			return null;
		}
		NpcCardArt exact = BY_ID.get(cardId);
		if (exact != null)
		{
			return exact;
		}
		for (Map.Entry<String, NpcCardArt> entry : BY_ID.entrySet())
		{
			if (entry.getKey().startsWith("characters-" + cardId)
				|| entry.getKey().contains("-" + cardId))
			{
				return entry.getValue();
			}
		}
		return null;
	}

	public static String idFor(NpcCardArt art)
	{
		for (Map.Entry<String, NpcCardArt> entry : BY_ID.entrySet())
		{
			if (entry.getValue() == art)
			{
				return entry.getKey();
			}
		}
		return null;
	}

	public static Collection<String> ids()
	{
		return BY_ID.keySet();
	}
}
