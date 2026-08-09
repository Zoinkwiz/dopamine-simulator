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
import lombok.Getter;

@Getter
public class NpcCardArt
{
	private static final Map<String, NpcCardArt> BY_ID = new LinkedHashMap<>();

	static
	{
		for (CardData.NpcArtEntry entry : CardData.loadNpcArt())
		{
			BY_ID.put(entry.id, new NpcCardArt(entry));
		}
	}

	private final String displayName;
	private final int npcId;
	private final int animationId;

	private final int zoom;
	private final int verticalOffset;
	private final int backdropColour;
	private final int sceneryModelId;
	private final int sceneryZoom;
	private final int accentColour;
	private final String epithet;
	private final String roleTag;
	private final String mechanicTag;
	private final int metalDark;
	private final int metalMid;
	private final int metalLight;
	private final int plateColour;
	private final int glowColour;

	private final int pillarModelId;
	private final int pillarModelIdRight;
	private final int pillarZoom;
	private final int pillarRotation;
	private final int pillarRotationRight;
	private final int foreModelId;
	private final int foreZoom;
	private final int foreRotation;
	private final int altNpcId;
	private final int altAnimationId;
	private final int altZoom;
	private final int altVerticalOffset;

	private final boolean bloodDrips;
	private final String backMotif;

	private NpcCardArt(CardData.NpcArtEntry entry)
	{
		this.displayName = entry.displayName;
		this.npcId = entry.npcId;
		this.animationId = entry.animationId;
		this.zoom = entry.zoom;
		this.verticalOffset = entry.verticalOffset;
		this.backdropColour = entry.backdropColour;
		this.sceneryModelId = entry.sceneryModelId;
		this.sceneryZoom = entry.sceneryZoom;
		this.accentColour = entry.accentColour;
		this.epithet = entry.epithet;
		this.roleTag = entry.roleTag;
		this.mechanicTag = entry.mechanicTag;
		this.metalDark = entry.metalDark;
		this.metalMid = entry.metalMid;
		this.metalLight = entry.metalLight;
		this.plateColour = entry.plateColour;
		this.glowColour = entry.glowColour;
		this.pillarModelId = entry.pillarModelId;
		this.pillarModelIdRight = entry.pillarModelIdRight;
		this.pillarZoom = entry.pillarZoom;
		this.pillarRotation = entry.pillarRotation;
		this.pillarRotationRight = entry.pillarRotationRight;
		this.foreModelId = entry.foreModelId;
		this.foreZoom = entry.foreZoom;
		this.foreRotation = entry.foreRotation;
		this.altNpcId = entry.altNpcId;
		this.altAnimationId = entry.altAnimationId;
		this.altZoom = entry.altZoom;
		this.altVerticalOffset = entry.altVerticalOffset;
		this.bloodDrips = entry.bloodDrips;
		this.backMotif = entry.backMotif;
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
