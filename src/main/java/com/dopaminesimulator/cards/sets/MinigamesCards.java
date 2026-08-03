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
package com.dopaminesimulator.cards.sets;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardGroup;
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Rarity;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.SpriteID;

public enum MinigamesCards implements CardGroup
{
	BARBARIAN_ASSAULT("Barbarian Assault", Rarity.COMMON, ItemID.BARBASSAULT_PENANCE_FIGHTER_TORSO, -1),
	PEST_CONTROL("Pest Control", Rarity.COMMON, ItemID.PEST_VOID_KNIGHT_TOP, -1),
	CASTLE_WARS("Castle Wars", Rarity.COMMON, ItemID.CASTLEWARS_CLOAK_SARADOMIN, -1),
	BLAST_FURNACE("Blast Furnace", Rarity.COMMON, ItemID.GAUNTLETS_OF_GOLDSMITHING, -1),
	TITHE_FARM("Tithe Farm", Rarity.COMMON, ItemID.TITHE_REWARD_TORSO_MALE, -1),
	PYRAMID_PLUNDER("Pyramid Plunder", Rarity.COMMON, ItemID.PHARAOHS_SCEPTRE, -1),
	BRIMHAVEN_AGILITY_ARENA("Brimhaven Agility Arena", Rarity.COMMON, ItemID.AGILITYARENA_TICKET, -1),
	ROGUES_DEN("Rogues' Den", Rarity.UNCOMMON, ItemID.ROGUESDEN_BODY, -1),
	TROUBLE_BREWING("Trouble Brewing", Rarity.UNCOMMON, ItemID.ASGARNIAN_ALE, -1),
	MAGE_TRAINING_ARENA("Mage Training Arena", Rarity.UNCOMMON, ItemID.MAGICTRAINING_INFINITYHAT, -1),
	GNOME_RESTAURANT("Gnome Restaurant", Rarity.UNCOMMON, ItemID.BALL_GNOMEBALL_GAME, -1),
	VOLCANIC_MINE("Volcanic Mine", Rarity.UNCOMMON, ItemID.FOSSIL_VOLCANIC_ASH, -1),
	GUARDIANS_OF_THE_RIFT("Guardians of the Rift", Rarity.UNCOMMON, ItemID.ABYSSAL_LANTERN, -1),
	LAST_MAN_STANDING("Last Man Standing", Rarity.RARE, ItemID.BLIGHTED_KARAMBWAN, -1),
	SOUL_WARS("Soul Wars", Rarity.RARE, ItemID.SOUL_CAPE_RED, -1),
	NIGHTMARE_ZONE("Nightmare Zone", Rarity.RARE, ItemID.IMBUED_HEART, -1),
	FIGHT_CAVES("Fight Caves", Rarity.EPIC, ItemID.TZHAAR_CAPE_FIRE, -1),
	INFERNO("Inferno", Rarity.LEGENDARY, ItemID.INFERNAL_CAPE, -1);

	private final Card card;

	MinigamesCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.MINIGAMES, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
