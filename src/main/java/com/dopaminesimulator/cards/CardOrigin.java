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

import java.awt.Color;
import lombok.Getter;

/**
 * Where a card can come from. Orthogonal to {@link CardSet}: a card keeps the set
 * it was born in, because ids are built from the set name and an id that moves
 * takes every copy a player holds with it.
 *
 * Everything is {@link #PACKS} unless {@link CardOrigins} says otherwise.
 */
@Getter
public enum CardOrigin
{
	PACKS("Packs", "Found in packs",
		"Opened from any pack.", new Color(0xA6, 0xB2, 0xC4)),

	GACHA("Mythic", "Mythic Invocation only",
		"Won from the Mythic Invocation banner. Packs never hold these.",
		new Color(0xFF, 0x70, 0x43)),

	PASS("Seasonal", "Season pass only",
		"Claimed at the end of a season pass. One region each season.",
		new Color(0x26, 0xC6, 0xDA)),

	PRESTIGE("Mastery", "Prestige only",
		"Awarded for prestiging. One skill per reset.",
		new Color(0xFF, 0xD5, 0x4F)),

	ASCENSION("Ascendant", "Ascension only",
		"Awarded for ascending a maxed collection.",
		new Color(0xAB, 0x47, 0xBC)),

	FOLLOWERS("Followers", "Their own pack",
		"Earned by doing the thing they are known for. Only their own pack holds them.",
		new Color(0xF0, 0xC4, 0x6A));

	private final String displayName;
	private final String shortHint;
	private final String description;
	private final Color colour;

	CardOrigin(String displayName, String shortHint, String description, Color colour)
	{
		this.displayName = displayName;
		this.shortHint = shortHint;
		this.description = description;
		this.colour = colour;
	}

	public boolean isExclusive()
	{
		return this != PACKS;
	}

	/**
	 * How many copies one award from this source is worth, as a share of the star
	 * track. Sources that fire rarely pay more per award than sources that repeat.
	 *
	 * Season cards sit in an unlock set, where a single copy is the whole card and
	 * the rest would only turn into dust.
	 */
	public int copiesPerAward(Rarity rarity)
	{
		int track = rarity.copiesForMaxStars();
		switch (this)
		{
			case GACHA:
				return Math.max(1, track * 6 / 10);
			case PRESTIGE:
			case ASCENSION:
				return Math.max(1, track * 3 / 10);
			default:
				return 1;
		}
	}
}
