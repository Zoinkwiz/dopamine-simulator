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

public enum FiremakingCards implements CardGroup
{
	ABYSSAL_LANTERN_WITH_NORMAL_LOGS("Abyssal Lantern (with normal logs)", Rarity.COMMON, ItemID.ABYSSAL_LANTERN_NORMAL, -1),
	ACHEY_LOGS("Achey logs", Rarity.COMMON, ItemID.ACHEY_TREE_LOGS, -1),
	CANDLES("Candles", Rarity.COMMON, ItemID.UNLIT_CANDLE, -1),
	NORMAL_LOGS("Normal logs", Rarity.COMMON, ItemID.LOGS, -1),
	TORCHES("Torches", Rarity.COMMON, ItemID.TORCH_UNLIT, -1),
	CANDLE_LANTERNS("Candle lanterns", Rarity.COMMON, ItemID.CANDLE_LANTERN_LIT, -1),
	PYRE_LOGS("Pyre logs", Rarity.COMMON, ItemID.LOGS_PYRE, -1),
	OIL_LAMPS("Oil lamps", Rarity.COMMON, ItemID.OIL_LAMP_LIT, -1),
	ABYSSAL_LANTERN_WITH_OAK_LOGS("Abyssal Lantern (with oak logs)", Rarity.COMMON, ItemID.ABYSSAL_LANTERN_OAK, -1),
	OAK_LOGS("Oak logs", Rarity.COMMON, ItemID.OAK_LOGS, -1),
	IRON_SPITS("Iron spits", Rarity.UNCOMMON, ItemID.SPIT_IRON, -1),
	OAK_PYRE_LOGS("Oak pyre logs", Rarity.UNCOMMON, ItemID.OAK_LOGS_PYRE, -1),
	OIL_LANTERNS("Oil lanterns", Rarity.UNCOMMON, ItemID.OIL_LANTERN_LIT, -1),
	ABYSSAL_LANTERN_WITH_WILLOW_LOGS("Abyssal Lantern (with willow logs)", Rarity.UNCOMMON, ItemID.ABYSSAL_LANTERN_WILLOW, -1),
	WILLOW_LOGS("Willow logs", Rarity.UNCOMMON, ItemID.WILLOW_LOGS, -1),
	HARPIE_BUG_LANTERNS("Harpie bug lanterns", Rarity.UNCOMMON, ItemID.SLAYER_BUGLAN_ON, -1),
	TEAK_LOGS("Teak logs", Rarity.UNCOMMON, ItemID.TEAK_LOGS, -1),
	WILLOW_PYRE_LOGS("Willow pyre logs", Rarity.UNCOMMON, ItemID.WILLOW_LOGS_PYRE, -1),
	JATOBA_LOGS("Jatoba logs", Rarity.RARE, ItemID.JATOBA_LOGS, -1),
	TEAK_PYRE_LOGS("Teak pyre logs", Rarity.RARE, ItemID.TEAK_LOGS_PYRE, -1),
	ARCTIC_PINE_LOGS("Arctic Pine logs", Rarity.RARE, ItemID.ARCTIC_PINE_LOG, -1),
	ABYSSAL_LANTERN_WITH_MAPLE_LOGS("Abyssal Lantern (with maple logs)", Rarity.RARE, ItemID.ABYSSAL_LANTERN_MAPLE, -1),
	MAPLE_LOGS("Maple logs", Rarity.RARE, ItemID.MAPLE_LOGS, -1),
	ARCTIC_PINE_PYRE_LOGS("Arctic Pine pyre logs", Rarity.RARE, ItemID.ARCTIC_PINE_LOGS_PYRE, -1),
	BULLSEYE_LANTERNS("Bullseye lanterns", Rarity.RARE, ItemID.BULLSEYE_LANTERN_LIT, -1),
	EMERALD_LANTERNS("Emerald lanterns", Rarity.RARE, ItemID.BULLSEYE_LANTERN_LIT_LUNAR_QUEST, -1),
	SAPPHIRE_LANTERNS("Sapphire lanterns", Rarity.RARE, ItemID.TOG_SAPPHIRE_LANTERN_LIT, -1),
	BRUMA_TORCHES("Bruma torches", Rarity.RARE, ItemID.WINT_TORCH, -1),
	MAHOGANY_LOGS("Mahogany logs", Rarity.RARE, ItemID.MAHOGANY_LOGS, -1),
	MAPLE_PYRE_LOGS("Maple pyre logs", Rarity.RARE, ItemID.MAPLE_LOGS_PYRE, -1),
	WINTERTODT("Wintertodt", Rarity.RARE, ItemID.WINT_BRUMA_KINDLING, -1),
	MAHOGANY_PYRE_LOGS("Mahogany pyre logs", Rarity.RARE, ItemID.MAHOGANY_LOGS_PYRE, -1),
	ABYSSAL_LANTERN_WITH_YEW_LOGS("Abyssal Lantern (with yew logs)", Rarity.EPIC, ItemID.ABYSSAL_LANTERN_YEW, -1),
	YEW_LOGS("Yew logs", Rarity.EPIC, ItemID.YEW_LOGS, -1),
	ABYSSAL_LANTERN_WITH_BLISTERWOOD_LOGS("Abyssal Lantern (with blisterwood logs)", Rarity.EPIC, ItemID.ABYSSAL_LANTERN_BLISTERWOOD, -1),
	BLISTERWOOD_LOGS("Blisterwood logs", Rarity.EPIC, ItemID.BLISTERWOOD_LOGS, -1),
	CAVE_GOBLIN_MINING_HELMETS("Cave goblin mining helmets", Rarity.EPIC, ItemID.CAVE_GOBLIN_MINING_HELMET_LIT, -1),
	YEW_PYRE_LOGS("Yew pyre logs", Rarity.EPIC, ItemID.YEW_LOGS_PYRE, -1),
	CAMPHOR_LOGS("Camphor logs", Rarity.EPIC, ItemID.CAMPHOR_LOGS, -1),
	FIRE_OF_DEHUMIDIFICATION("Fire of Dehumidification", Rarity.EPIC, ItemID.MY2ARM_DUMMY_FIRE_GHAST, -1),
	FIRE_OF_ETERNAL_LIGHT("Fire of Eternal Light", Rarity.EPIC, ItemID.MY2ARM_DUMMY_FIRE_LIGHT, -1),
	FIRE_OF_NOURISHMENT("Fire of Nourishment", Rarity.EPIC, ItemID.MY2ARM_DUMMY_FIRE_HERB, -1),
	FIRE_OF_UNSEASONAL_WARMTH("Fire of Unseasonal Warmth", Rarity.EPIC, ItemID.MY2ARM_DUMMY_FIRE_GWD, -1),
	CAMPHOR_PYRE_LOGS("Camphor pyre logs", Rarity.EPIC, ItemID.CAMPHOR_LOGS_PYRE, -1),
	ABYSSAL_LANTERN_WITH_MAGIC_LOGS("Abyssal Lantern (with magic logs)", Rarity.EPIC, ItemID.ABYSSAL_LANTERN_MAGIC, -1),
	MAGIC_LOGS("Magic logs", Rarity.EPIC, ItemID.MAGIC_LOGS, -1),
	IRONWOOD_LOGS("Ironwood logs", Rarity.LEGENDARY, ItemID.IRONWOOD_LOGS, -1),
	MAGIC_PYRE_LOGS("Magic pyre logs", Rarity.LEGENDARY, ItemID.MAGIC_LOGS_PYRE, -1),
	INFERNAL_AXE("Infernal axe", Rarity.LEGENDARY, ItemID.INFERNAL_AXE, -1),
	IRONWOOD_PYRE_LOGS("Ironwood pyre logs", Rarity.LEGENDARY, ItemID.IRONWOOD_LOGS_PYRE, -1),
	ABYSSAL_LANTERN_WITH_REDWOOD_LOGS("Abyssal Lantern (with redwood logs)", Rarity.LEGENDARY, ItemID.ABYSSAL_LANTERN_REDWOOD, -1),
	REDWOOD_LOGS("Redwood logs", Rarity.LEGENDARY, ItemID.REDWOOD_LOGS, -1),
	ROSEWOOD_LOGS("Rosewood logs", Rarity.LEGENDARY, ItemID.ROSEWOOD_LOGS, -1),
	REDWOOD_PYRE_LOGS("Redwood pyre logs", Rarity.LEGENDARY, ItemID.REDWOOD_LOGS_PYRE, -1),
	ROSEWOOD_PYRE_LOGS("Rosewood pyre logs", Rarity.LEGENDARY, ItemID.ROSEWOOD_LOGS_PYRE, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_FIREMAKING, -1);

	private final Card card;

	FiremakingCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.FIREMAKING, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
