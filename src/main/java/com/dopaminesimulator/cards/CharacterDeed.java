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

import lombok.Getter;

@Getter
public enum CharacterDeed
{
	VERZIK("characters-verzik-vitur", "Theatre of Blood: Hard Mode",
		0.014d, 100, CardSet.RAIDS_DROPS),
	VANESCULA("characters-vanescula-drakan", "Theatre of Blood",
		0.012d, 110, CardSet.RAIDS_DROPS),
	AMASCUT("characters-amascut", "Tombs of Amascut, raid level 300+",
		0.012d, 110, CardSet.RAIDS_DROPS),
	MAISA("characters-maisa", "Tombs of Amascut, below raid level 300",
		0.010d, 130, CardSet.RAIDS_DROPS),
	SEREN("characters-seren", "The Corrupted Gauntlet",
		0.008d, 160, CardSet.BOSSES),
	ILFEEN("characters-ilfeen", "The Gauntlet",
		0.008d, 160, CardSet.BOSSES),
	KONAR("characters-konar-quo-maten", "A slayer task from Konar",
		0.010d, 130, CardSet.SLAYER),
	ZILYANA("characters-commander-zilyana", "Killing Commander Zilyana",
		0.0015d, 900, CardSet.BOSSES);

	private final String cardId;
	private final String deed;
	private final double chance;
	private final int pity;
	private final CardSet themedSet;

	CharacterDeed(String cardId, String deed, double chance, int pity, CardSet themedSet)
	{
		this.cardId = cardId;
		this.deed = deed;
		this.chance = chance;
		this.pity = pity;
		this.themedSet = themedSet;
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

	public static CharacterDeed byCardId(String cardId)
	{
		for (CharacterDeed deed : values())
		{
			if (deed.cardId.equals(cardId))
			{
				return deed;
			}
		}
		return null;
	}
}
