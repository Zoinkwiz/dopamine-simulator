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

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public enum CharacterFollower
{
	VERZIK("characters-verzik-vitur", "Theatre of Blood: Hard Mode",
		0.014d, 100, "Theatre of Blood"),
	VANESCULA("characters-vanescula-drakan", "Theatre of Blood",
		0.012d, 110, "Theatre of Blood"),
	AMASCUT("characters-amascut", "Tombs of Amascut, raid level 300+",
		0.012d, 110, "Tombs of Amascut"),
	MAISA("characters-maisa", "Tombs of Amascut, below raid level 300",
		0.010d, 130, "Tombs of Amascut"),
	SEREN("characters-seren", "The Corrupted Gauntlet",
		0.008d, 160, null, Pools.GAUNTLET),
	ILFEEN("characters-ilfeen", "The Gauntlet",
		0.008d, 160, null, Pools.GAUNTLET),
	KONAR("characters-konar-quo-maten", "A slayer task from Konar",
		0.010d, 130, "Slayer Boss Drops"),
	ZILYANA("characters-commander-zilyana", "Killing Commander Zilyana",
		0.0015d, 900, "God Wars Uniques");

	private static final class Pools
	{
		private static final String[] GAUNTLET = {
			"bosses-the-gauntlet",
			"bosses-the-corrupted-gauntlet",
			"boss_drops-gauntlet-cape",
			"boss_drops-youngllef",
			"boss_drops-crystal-armour-seed",
			"boss_drops-crystal-weapon-seed",
			"boss_drops-enhanced-crystal-weapon-seed",
			"attack-blade-of-saeldor",
		};
	}

	private final String cardId;
	private final String earnedFrom;
	private final double chance;
	private final int pity;
	private final String collection;
	private final String[] extraCards;

	CharacterFollower(String cardId, String earnedFrom, double chance, int pity, String collection)
	{
		this(cardId, earnedFrom, chance, pity, collection, null);
	}

	CharacterFollower(String cardId, String earnedFrom, double chance, int pity, String collection,
				  String[] extraCards)
	{
		this.cardId = cardId;
		this.earnedFrom = earnedFrom;
		this.chance = chance;
		this.pity = pity;
		this.collection = collection;
		this.extraCards = extraCards;
	}

	public List<Card> pool()
	{
		List<Card> pool = new ArrayList<>();
		if (collection != null)
		{
			for (CardCollection candidate : CardCollection.all())
			{
				if (candidate.getName().equals(collection))
				{
					pool.addAll(candidate.getCards());
					break;
				}
			}
		}
		if (extraCards != null)
		{
			for (String id : extraCards)
			{
				Card card = CardCatalogue.byId(id);
				if (card != null)
				{
					pool.add(card);
				}
			}
		}
		return pool;
	}

	public NpcCardArt getArt()
	{
		return NpcCardArt.byId(cardId);
	}

	public String getCharacterName()
	{
		NpcCardArt art = getArt();
		return art == null ? cardId : art.getDisplayName();
	}

	public static CharacterFollower byCardId(String cardId)
	{
		for (CharacterFollower follower : values())
		{
			if (follower.cardId.equals(cardId))
			{
				return follower;
			}
		}
		return null;
	}
}
