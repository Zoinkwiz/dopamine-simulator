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

import com.dopaminesimulator.cards.CardOrigins;
import com.dopaminesimulator.cosmetics.CardBack;
import java.awt.Color;
import lombok.Getter;

@Getter
public enum PassTheme
{
	EXPEDITION("Expedition", "Packs, and plenty of them",
		new Color(0x66, 0xBB, 0x6A), CardBack.VERDANT),
	VAULT("Vault", "Dust to spend where you choose",
		new Color(0xFF, 0xD5, 0x4F), CardBack.BULLION),
	GALLERY("Gallery", "Cosmetics, shinies and gilding",
		new Color(0x7C, 0xE6, 0xD6), CardBack.TIDE),
	FORGE("Forge", "Fewer rewards, bigger ones",
		new Color(0xC1, 0x53, 0x2A), CardBack.EMBER);

	private final String displayName;
	private final String description;
	private final Color colour;
	private final CardBack back;

	PassTheme(String displayName, String description, Color colour, CardBack back)
	{
		this.displayName = displayName;
		this.description = description;
		this.colour = colour;
		this.back = back;
	}

	public static PassTheme forSeason(int season)
	{
		PassTheme[] all = values();
		return all[Math.floorMod(season - 1, all.length)];
	}

	public PassReward free(int tier, int season)
	{
		switch (this)
		{
			case EXPEDITION:
				if (BattlePass.isMilestone(tier))
				{
					return PassReward.packs(BattlePass.packFor(season, 1), 2);
				}
				return PassReward.packs(BattlePass.packFor(season, -1), 3);

			case VAULT:
				if (BattlePass.isMilestone(tier))
				{
					return PassReward.wildcards(1);
				}
				return PassReward.shards(BattlePass.shardFor(season, 0), 60L * season);

			case GALLERY:
				if (tier == BattlePass.TIERS / 2)
				{
					return PassReward.cardBack(back);
				}
				if (BattlePass.isMilestone(tier))
				{
					return PassReward.gilded();
				}
				return PassReward.packs(BattlePass.packFor(season, -1), 1);

			default:
				if (BattlePass.isMilestone(tier))
				{
					return PassReward.packs(BattlePass.packFor(season, 2), 1);
				}
				if (tier % 5 == 0)
				{
					return PassReward.shards(BattlePass.shardFor(season, 1), 80L * season);
				}
				return PassReward.packs(BattlePass.packFor(season, 0), 1);
		}
	}

	public PassReward premium(int tier, int season)
	{
		// The top of the premium track is the season's own card, and the only place
		// in the game it exists. Every theme ends the same way on purpose: whatever
		// else a season is about, finishing it is what a region is worth.
		if (tier == BattlePass.TIERS)
		{
			return PassReward.card(CardOrigins.seasonCard(season));
		}

		switch (this)
		{
			case EXPEDITION:
				if (BattlePass.isMilestone(tier))
				{
					return PassReward.packs(BattlePass.packFor(season, 2), 3);
				}
				return PassReward.packs(BattlePass.packFor(season, 0), 2);

			case VAULT:
				if (BattlePass.isMilestone(tier))
				{
					return PassReward.wildcards(2);
				}
				if (tier % 5 == 0)
				{
					return PassReward.shards(BattlePass.shardFor(season, 2), 100L * season);
				}
				return PassReward.shards(BattlePass.shardFor(season, 1), 50L * season);

			case GALLERY:
				if (tier % 25 == 0)
				{
					return PassReward.shiny();
				}
				if (BattlePass.isMilestone(tier))
				{
					return PassReward.gilded();
				}
				return PassReward.packs(BattlePass.packFor(season, 1), 1);

			default:
				if (BattlePass.isMilestone(tier))
				{
					return PassReward.packs(BattlePass.packFor(season, 3), 2);
				}
				if (tier % 5 == 0)
				{
					return PassReward.wildcards(1);
				}
				return PassReward.packs(BattlePass.packFor(season, 1), 1);
		}
	}
}
