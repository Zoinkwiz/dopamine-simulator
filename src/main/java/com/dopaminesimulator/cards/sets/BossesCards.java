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

public enum BossesCards implements CardGroup
{
	OBOR("Obor", Rarity.COMMON, -1, SpriteID.IconBoss25x25.OBOR),
	BRYOPHYTA("Bryophyta", Rarity.COMMON, -1, SpriteID.IconBoss25x25.BRYOPHYTA),
	GIANT_MOLE("Giant Mole", Rarity.COMMON, -1, SpriteID.IconBoss25x25.GIANT_MOLE),
	TEMPOROSS("Tempoross", Rarity.COMMON, -1, SpriteID.IconBoss25x25.TEMPOROSS),
	WINTERTODT("Wintertodt", Rarity.COMMON, -1, SpriteID.IconBoss25x25.WINTERTODT),
	BARROWS("Barrows", Rarity.COMMON, -1, SpriteID.IconBoss25x25.BARROWS_CHESTS),
	SARACHNIS("Sarachnis", Rarity.COMMON, -1, SpriteID.IconBoss25x25.SARACHNIS),
	HESPORI("Hespori", Rarity.COMMON, -1, SpriteID.IconBoss25x25.HESPORI),
	SCURRIUS("Scurrius", Rarity.COMMON, -1, SpriteID.IconBoss25x25.SCURRIUS),
	DERANGED_ARCHAEOLOGIST("Deranged Archaeologist", Rarity.COMMON, -1, SpriteID.IconBoss25x25.DERANGED_ARCHAEOLOGIST),
	CHAOS_FANATIC("Chaos Fanatic", Rarity.COMMON, -1, SpriteID.IconBoss25x25.CHAOS_FANATIC),
	CRAZY_ARCHAEOLOGIST("Crazy Archaeologist", Rarity.COMMON, -1, SpriteID.IconBoss25x25.CRAZY_ARCHAEOLOGIST),
	KALPHITE_QUEEN("Kalphite Queen", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.KALPHITE_QUEEN),
	KING_BLACK_DRAGON("King Black Dragon", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.KING_BLACK_DRAGON),
	SKOTIZO("Skotizo", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.SKOTIZO),
	ZALCANO("Zalcano", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.ZALCANO),
	KRAKEN("Kraken", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.KRAKEN),
	GROTESQUE_GUARDIANS("Grotesque Guardians", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.GROTESQUE_GUARDIANS),
	SCORPIA("Scorpia", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.SCORPIA),
	CHAOS_ELEMENTAL("Chaos Elemental", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.CHAOS_ELEMENTAL),
	DAGANNOTH_REX("Dagannoth Rex", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.DAGANNOTH_REX),
	DAGANNOTH_PRIME("Dagannoth Prime", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.DAGANNOTH_PRIME),
	DAGANNOTH_SUPREME("Dagannoth Supreme", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.DAGANNOTH_SUPREME),
	MIMIC("Mimic", Rarity.UNCOMMON, -1, SpriteID.IconBoss25x25.MIMIC),
	ZULRAH("Zulrah", Rarity.RARE, -1, SpriteID.IconBoss25x25.ZULRAH),
	VORKATH("Vorkath", Rarity.RARE, -1, SpriteID.IconBoss25x25.VORKATH),
	ABYSSAL_SIRE("Abyssal Sire", Rarity.RARE, -1, SpriteID.IconBoss25x25.ABYSSAL_SIRE),
	THERMONUCLEAR_SMOKE_DEVIL("Thermonuclear Smoke Devil", Rarity.RARE, -1, SpriteID.IconBoss25x25.THERMONUCLEAR_SMOKE_DEVIL),
	CERBERUS("Cerberus", Rarity.RARE, -1, SpriteID.IconBoss25x25.CERBERUS),
	GENERAL_GRAARDOR("General Graardor", Rarity.RARE, -1, SpriteID.IconBoss25x25.GENERAL_GRAARDOR),
	K_RIL_TSUTSAROTH("K'ril Tsutsaroth", Rarity.RARE, -1, SpriteID.IconBoss25x25.KRIL_TSUTSAROTH),
	COMMANDER_ZILYANA("Commander Zilyana", Rarity.RARE, -1, SpriteID.IconBoss25x25.COMMANDER_ZILYANA),
	KREE_ARRA("Kree'arra", Rarity.RARE, -1, SpriteID.IconBoss25x25.KREEARRA),
	ALCHEMICAL_HYDRA("Alchemical Hydra", Rarity.RARE, -1, SpriteID.IconBoss25x25.ALCHEMICAL_HYDRA),
	CALLISTO("Callisto", Rarity.RARE, -1, SpriteID.IconBoss25x25.ARTIO_CALLISTO),
	VET_ION("Vet'ion", Rarity.RARE, -1, SpriteID.IconBoss25x25.CALVARION_VETION),
	VENENATIS("Venenatis", Rarity.RARE, -1, SpriteID.IconBoss25x25.SPINDEL_VENENATIS),
	THE_GAUNTLET("The Gauntlet", Rarity.RARE, -1, SpriteID.IconBoss25x25.THE_GAUNTLET),
	CORPOREAL_BEAST("Corporeal Beast", Rarity.EPIC, -1, SpriteID.IconBoss25x25.CORPOREAL_BEAST),
	THE_NIGHTMARE("The Nightmare", Rarity.EPIC, -1, SpriteID.IconBoss25x25.NIGHTMARE),
	PHANTOM_MUSPAH("Phantom Muspah", Rarity.EPIC, -1, SpriteID.IconBoss25x25.PHANTOM_MUSPAH),
	NEX("Nex", Rarity.EPIC, -1, SpriteID.IconBoss25x25.NEX),
	DUKE_SUCELLUS("Duke Sucellus", Rarity.EPIC, -1, SpriteID.IconBoss25x25.DUKE_SUCELLUS),
	THE_LEVIATHAN("The Leviathan", Rarity.EPIC, -1, SpriteID.IconBoss25x25.THE_LEVIATHAN),
	THE_WHISPERER("The Whisperer", Rarity.EPIC, -1, SpriteID.IconBoss25x25.THE_WHISPERER),
	VARDORVIS("Vardorvis", Rarity.EPIC, -1, SpriteID.IconBoss25x25.VARDORVIS),
	ARAXXOR("Araxxor", Rarity.EPIC, -1, SpriteID.IconBoss25x25.ARAXXOR),
	THE_CORRUPTED_GAUNTLET("The Corrupted Gauntlet", Rarity.EPIC, -1, SpriteID.IconBoss25x25.THE_CORRUPTED_GAUNTLET),
	CHAMBERS_OF_XERIC("Chambers of Xeric", Rarity.LEGENDARY, -1, SpriteID.IconBoss25x25.CHAMBERS_OF_XERIC),
	CHAMBERS_OF_XERIC_CHALLENGE_MODE("Chambers of Xeric: Challenge Mode", Rarity.LEGENDARY, ItemID.TWISTED_BOW, -1),
	THEATRE_OF_BLOOD("Theatre of Blood", Rarity.LEGENDARY, -1, SpriteID.IconBoss25x25.THEATRE_OF_BLOOD),
	TOMBS_OF_AMASCUT("Tombs of Amascut", Rarity.LEGENDARY, -1, SpriteID.IconBoss25x25.TOMBS_OF_AMASCUT),
	YAMA("Yama", Rarity.LEGENDARY, -1, SpriteID.IconBoss25x25.YAMA),
	ROYAL_TITANS("Royal Titans", Rarity.LEGENDARY, -1, SpriteID.IconBoss25x25.ROYAL_TITANS);

	private final Card card;

	BossesCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.BOSSES, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
