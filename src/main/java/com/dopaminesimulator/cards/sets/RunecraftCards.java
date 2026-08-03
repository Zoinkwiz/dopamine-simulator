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

public enum RunecraftCards implements CardGroup
{
	AIR_RUNES("Air runes", Rarity.COMMON, ItemID.AIRRUNE, -1),
	SMALL_POUCH("Small pouch", Rarity.COMMON, ItemID.RCU_POUCH_SMALL, -1),
	MIND_RUNES("Mind runes", Rarity.COMMON, ItemID.MINDRUNE, -1),
	WATER_RUNES("Water runes", Rarity.COMMON, ItemID.WATERRUNE, -1),
	MIST_RUNES("Mist runes", Rarity.COMMON, ItemID.MISTRUNE, -1),
	EARTH_RUNES("Earth runes", Rarity.COMMON, ItemID.EARTHRUNE, -1),
	DUST_RUNES("Dust runes", Rarity.COMMON, ItemID.DUSTRUNE, -1),
	MUD_RUNES("Mud runes", Rarity.COMMON, ItemID.MUDRUNE, -1),
	FIRE_RUNES("Fire runes", Rarity.COMMON, ItemID.FIRERUNE, -1),
	SMOKE_RUNES("Smoke runes", Rarity.COMMON, ItemID.SMOKERUNE, -1),
	STEAM_RUNES("Steam runes", Rarity.COMMON, ItemID.STEAMRUNE, -1),
	BODY_RUNES("Body runes", Rarity.UNCOMMON, ItemID.BODYRUNE, -1),
	LAVA_RUNES("Lava runes", Rarity.UNCOMMON, ItemID.LAVARUNE, -1),
	COLOSSAL_POUCH("Colossal pouch", Rarity.UNCOMMON, ItemID.RCU_POUCH_COLOSSAL, -1),
	MEDIUM_POUCH("Medium pouch", Rarity.UNCOMMON, ItemID.RCU_POUCH_MEDIUM, -1),
	COSMIC_RUNES("Cosmic runes", Rarity.UNCOMMON, ItemID.COSMICRUNE, -1),
	GUARDIANS_OF_THE_RIFT("Guardians of the Rift", Rarity.UNCOMMON, ItemID.TOTE_AMULET, -1),
	SUNFIRE_RUNES("Sunfire runes", Rarity.UNCOMMON, ItemID.SUNFIRERUNE, -1),
	CHAOS_RUNES("Chaos runes", Rarity.UNCOMMON, ItemID.CHAOSRUNE, -1),
	ASTRAL_RUNES("Astral runes", Rarity.RARE, ItemID.ASTRALRUNE, -1),
	SWAMPBARK_BOOTS_AND_GAUNTLETS("Swampbark boots and gauntlets", Rarity.RARE, ItemID.SWAMPBARK_GREAVES, -1),
	NATURE_RUNES("Nature runes", Rarity.RARE, ItemID.NATURERUNE, -1),
	SWAMPBARK_HELM("Swampbark helm", Rarity.RARE, ItemID.SWAMPBARK_HELM, -1),
	SWAMPBARK_BODY_AND_LEGS("Swampbark body and legs", Rarity.RARE, ItemID.SWAMPBARK_BODY, -1),
	BURNT_PAGE_VIA_DESICCATED_PAGES("Burnt page (via desiccated pages)", Rarity.RARE, ItemID.WINT_BURNT_PAGE, -1),
	LARGE_POUCH("Large pouch", Rarity.RARE, ItemID.RCU_POUCH_LARGE, -1),
	SOAKED_PAGE_VIA_DESICCATED_PAGES("Soaked page (via desiccated pages)", Rarity.RARE, ItemID.SOAKED_PAGE, -1),
	SOILED_PAGE_VIA_DESICCATED_PAGES("Soiled page (via desiccated pages)", Rarity.RARE, ItemID.SOILED_PAGE, -1),
	SUNSTONE_GOLEMS("Sunstone Golems", Rarity.RARE, 34020, -1),
	LAW_RUNES("Law runes", Rarity.RARE, ItemID.LAWRUNE, -1),
	AVERNIC_TREADS("Avernic treads", Rarity.EPIC, ItemID.AVERNIC_TREADS_MAX, -1),
	ETERNAL_BOOTS("Eternal boots", Rarity.EPIC, ItemID.ETERNAL_BOOTS, -1),
	PEGASIAN_BOOTS("Pegasian boots", Rarity.EPIC, ItemID.PEGASIAN_BOOTS, -1),
	PRIMORDIAL_BOOTS("Primordial boots", Rarity.EPIC, ItemID.PRIMORDIAL_BOOTS, -1),
	DEATH_RUNES("Death runes", Rarity.EPIC, ItemID.DEATHRUNE, -1),
	GIANT_POUCH("Giant pouch", Rarity.EPIC, ItemID.RCU_POUCH_GIANT, -1),
	BLOOD_RUNES("Blood runes", Rarity.EPIC, ItemID.BLOODRUNE, -1),
	BLOODBARK_BOOTS_AND_GAUNTLETS("Bloodbark boots and gauntlets", Rarity.EPIC, ItemID.BLOODBARK_GREAVES, -1),
	BLOODBARK_HELM("Bloodbark helm", Rarity.EPIC, ItemID.BLOODBARK_HELM, -1),
	BLOODBARK_BODY_AND_LEGS("Bloodbark body and legs", Rarity.LEGENDARY, ItemID.BLOODBARK_BODY, -1),
	AETHER_RUNES("Aether runes", Rarity.LEGENDARY, ItemID.AETHERRUNE, -1),
	SOUL_RUNES("Soul runes", Rarity.LEGENDARY, ItemID.SOULRUNE, -1),
	WRATH_RUNES("Wrath runes", Rarity.LEGENDARY, ItemID.WRATHRUNE, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_RUNECRAFTING, -1);

	private final Card card;

	RunecraftCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.RUNECRAFT, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
