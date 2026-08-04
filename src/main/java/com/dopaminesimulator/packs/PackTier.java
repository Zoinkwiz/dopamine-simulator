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
package com.dopaminesimulator.packs;

import com.dopaminesimulator.cards.Rarity;
import lombok.Getter;

import java.awt.Color;

@Getter
public enum PackTier
{

	// One card each, priced at what a card used to cost inside a multi-card pack.
	// Same throughput, but the wait between one reveal and the next stops growing
	// with the tier: it used to run from half an hour at Scrap to nearly four at
	// Mythic, where three cards arriving at once is really one thing happening.
	SCRAP("Scrap Pack", 1_000d, 1, 1, 1.0d, null, Rarity.COMMON, false, 0d,
		new Color(0x9E, 0x9E, 0x9E),
		"1 card. Common."),
	STANDARD("Standard Pack", 1_300d, 1, 1, 1.25d, null, Rarity.UNCOMMON, false, 2_000d,
		new Color(0x64, 0xB5, 0xF6),
		"1 card. Common or Uncommon."),
	GILDED("Gilded Pack", 3_300d, 1, 1, 1.0d, Rarity.UNCOMMON, Rarity.RARE, false, 15_000d,
		new Color(0x66, 0xBB, 0x6A),
		"1 card. Uncommon or Rare."),
	CURATED("Curated Pack", 10_600d, 1, 3, 1.0d, Rarity.UNCOMMON, Rarity.RARE, true,
		60_000d, new Color(0x26, 0xC6, 0xDA),
		"1 card from a chosen set. Uncommon or Rare."),
	PRISMATIC("Prismatic Pack", 37_500d, 1, 4, 1.5d, Rarity.RARE, Rarity.EPIC,
		false, 250_000d, new Color(0xAB, 0x47, 0xBC),
		"1 card. Rare or Epic."),
	ASCENDANT("Ascendant Pack", 140_000d, 1, 4, 1.6d, Rarity.EPIC,
		Rarity.LEGENDARY, false, 1_500_000d, new Color(0xFF, 0xB3, 0x00),
		"1 card. Epic or Legendary."),
	MYTHIC("Mythic Pack", 580_000d, 1, 2, 1.0d, Rarity.LEGENDARY, Rarity.LEGENDARY,
		false, 6_000_000d, new Color(0xFF, 0x70, 0x43),
		"1 card. Always Legendary.");
	public static final int MAX_COPIES = 5;

	private final String displayName;
	private final double cost;
	private final int cardCount;
	private final int bulkCopies;
	private final double luck;
	private final Rarity floor;
	private final Rarity ceiling;
	private final boolean targetsSet;
	private final double unlockAtLifetimePoints;
	private final Color colour;
	private final String description;
	PackTier(String displayName, double cost, int cardCount, int bulkCopies, double luck,
			 Rarity floor, Rarity ceiling, boolean targetsSet, double unlockAtLifetimePoints,
			 Color colour, String description)
	{
		this.displayName = displayName;
		this.cost = cost;
		this.cardCount = cardCount;
		this.bulkCopies = bulkCopies;
		this.luck = luck;
		this.floor = floor;
		this.ceiling = ceiling;
		this.targetsSet = targetsSet;
		this.unlockAtLifetimePoints = unlockAtLifetimePoints;
		this.colour = colour;
		this.description = description;
	}

    public boolean isUnlockedAt(double lifetimePoints)
	{
		return lifetimePoints >= unlockAtLifetimePoints;
	}

	public Rarity lowestRarity()
	{
		return floor == null ? Rarity.COMMON : floor;
	}

	public int copiesFor(Rarity rarity)
	{
		double scale = rarity.copiesForMaxStars()
			/ (double) lowestRarity().copiesForMaxStars();

		int fifthOfTrack = Math.max(1, rarity.copiesForMaxStars() / 5);
		int ceiling = Math.min(MAX_COPIES, fifthOfTrack);
		return Math.max(1, Math.min(ceiling, (int) Math.round(bulkCopies * scale)));
	}

	public double getCostPerCopy()
	{
		return cost / (cardCount * (double) copiesFor(lowestRarity()));
	}
}
