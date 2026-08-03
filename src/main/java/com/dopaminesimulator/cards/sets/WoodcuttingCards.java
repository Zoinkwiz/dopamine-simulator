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

public enum WoodcuttingCards implements CardGroup
{
	ACHEY_TREES("Achey trees", Rarity.COMMON, ItemID.ACHEY_TREE_LOGS, -1),
	BRONZE_AXE("Bronze axe", Rarity.COMMON, ItemID.BRONZE_AXE, -1),
	BRONZE_FELLING_AXE("Bronze felling axe", Rarity.COMMON, ItemID.BRONZE_AXE_2H, -1),
	FORESTRY_KIT("Forestry kit", Rarity.COMMON, ItemID.FORESTRY_KIT, -1),
	IRON_AXE("Iron axe", Rarity.COMMON, ItemID.IRON_AXE, -1),
	IRON_FELLING_AXE("Iron felling axe", Rarity.COMMON, ItemID.IRON_AXE_2H, -1),
	NORMAL_TREES("Normal trees", Rarity.COMMON, ItemID.LOGS, -1),
	STEEL_AXE("Steel axe", Rarity.COMMON, ItemID.STEEL_AXE, -1),
	STEEL_FELLING_AXE("Steel felling axe", Rarity.COMMON, ItemID.STEEL_AXE_2H, -1),
	LIGHT_JUNGLE("Light jungle", Rarity.COMMON, ItemID.THATCHING_SPAR_LIGHT, -1),
	BLACK_AXE("Black axe", Rarity.COMMON, ItemID.BLACK_AXE, -1),
	BLACK_FELLING_AXE("Black felling axe", Rarity.COMMON, ItemID.BLACK_AXE_2H, -1),
	LOG_CANOE("Log canoe", Rarity.COMMON, ItemID.CANOEING_PADDLE, -1),
	OAK_TREES("Oak trees", Rarity.COMMON, ItemID.OAK_LOGS, -1),
	MEDIUM_JUNGLE("Medium jungle", Rarity.UNCOMMON, ItemID.THATCHING_SPAR_MED, -1),
	MITHRIL_AXE("Mithril axe", Rarity.UNCOMMON, ItemID.MITHRIL_AXE, -1),
	MITHRIL_FELLING_AXE("Mithril felling axe", Rarity.UNCOMMON, ItemID.MITHRIL_AXE_2H, -1),
	DUGOUT_CANOE("Dugout canoe", Rarity.UNCOMMON, ItemID.CANOEING_PADDLE, -1),
	WILLOW_TREES("Willow trees", Rarity.UNCOMMON, ItemID.WILLOW_LOGS, -1),
	ADAMANT_AXE("Adamant axe", Rarity.UNCOMMON, ItemID.ADAMANT_AXE, -1),
	ADAMANT_FELLING_AXE("Adamant felling axe", Rarity.UNCOMMON, ItemID.ADAMANT_AXE_2H, -1),
	DENSE_JUNGLE("Dense jungle", Rarity.UNCOMMON, ItemID.THATCHING_SPAR_DENSE, -1),
	FORESTER_S_RATION("Forester's ration", Rarity.UNCOMMON, ItemID.FORESTRY_RATION, -1),
	SECATEURS_ATTACHMENT("Secateurs Attachment", Rarity.UNCOMMON, ItemID.FORESTRY_SECATEURS_ATTACHMENT, -1),
	TEAK_TREES("Teak trees", Rarity.UNCOMMON, ItemID.TEAK_LOGS, -1),
	JATOBA_TREES("Jatoba trees", Rarity.RARE, ItemID.JATOBA_LOGS, -1),
	GILDED_AXE("Gilded axe", Rarity.RARE, ItemID.TRAIL_GILDED_AXE, -1),
	RUNE_AXE("Rune axe", Rarity.RARE, ItemID.RUNE_AXE, -1),
	RUNE_FELLING_AXE("Rune felling axe", Rarity.RARE, ItemID.RUNE_AXE_2H, -1),
	JUNIPER_TREES("Juniper trees", Rarity.RARE, ItemID.JUNIPER_LOGS, -1),
	STABLE_DUGOUT_CANOE("Stable dugout canoe", Rarity.RARE, ItemID.CANOEING_PADDLE, -1),
	LUMBERJACK_AND_FORESTRY_OUTFIT("Lumberjack and Forestry outfit", Rarity.RARE, ItemID.RAMBLE_LUMBERJACK_TOP, -1),
	HOLLOW_TREES("Hollow trees", Rarity.RARE, ItemID.HOLLOW_BARK, -1),
	MAPLE_TREES("Maple trees", Rarity.RARE, ItemID.MAPLE_LOGS, -1),
	CLOTHES_POUCH("Clothes Pouch", Rarity.RARE, ItemID.FORESTRY_CLOTHES_POUCH, -1),
	MAHOGANY_TREES("Mahogany trees", Rarity.RARE, ItemID.MAHOGANY_LOGS, -1),
	ARCTIC_PINE_TREES("Arctic Pine trees", Rarity.RARE, ItemID.ARCTIC_PINE_LOG, -1),
	FREMENNIK_ROUND_SHIELD("Fremennik round shield", Rarity.RARE, ItemID.FREMMENIK_ROUND_SHIELD, -1),
	SPLIT_ARCTIC_PINE_LOGS("Split arctic pine logs", Rarity.RARE, ItemID.ARCTIC_PINE_SPLIT, -1),
	WAKA_CANOE("Waka canoe", Rarity.RARE, ItemID.CANOEING_PADDLE, -1),
	YEW_TREES("Yew trees", Rarity.EPIC, ItemID.YEW_LOGS, -1),
	N3RD_AGE_AXE("3rd age axe", Rarity.EPIC, ItemID._3A_AXE, -1),
	N3RD_AGE_FELLING_AXE("3rd age felling axe", Rarity.EPIC, ItemID._3A_AXE_2H, -1),
	DRAGON_AXE("Dragon axe", Rarity.EPIC, ItemID.DRAGON_AXE, -1),
	DRAGON_FELLING_AXE("Dragon felling axe", Rarity.EPIC, ItemID.DRAGON_AXE_2H, -1),
	INFERNAL_AXE("Infernal axe", Rarity.EPIC, ItemID.INFERNAL_AXE, -1),
	BLISTERWOOD_TREE("Blisterwood tree", Rarity.EPIC, ItemID.BLISTERWOOD_LOGS, -1),
	SULLIUSCEP_MUSHROOM_TREES("Sulliuscep mushroom trees", Rarity.EPIC, ItemID.FOSSIL_SULLIUSCEP_CAP, -1),
	CAMPHOR_TREES("Camphor trees", Rarity.EPIC, ItemID.CAMPHOR_LOGS, -1),
	NATURE_OFFERINGS("Nature Offerings", Rarity.EPIC, ItemID.NATURE_OFFERINGS, -1),
	CRYSTAL_AXE("Crystal axe", Rarity.EPIC, ItemID.CRYSTAL_AXE, -1),
	CRYSTAL_FELLING_AXE("Crystal felling axe", Rarity.EPIC, ItemID.CRYSTAL_AXE_2H, -1),
	MAGIC_TREES("Magic trees", Rarity.EPIC, ItemID.MAGIC_LOGS, -1),
	STURDY_HARNESS("Sturdy Harness", Rarity.EPIC, ItemID.FORESTRY_STURDY_HARNESS, -1),
	BLOODWOOD_TREES("Bloodwood trees", Rarity.EPIC, ItemID.BUCKET_OF_BLOODWOOD_SAP, -1),
	INFECTED_TREE_ROOTS("Infected tree roots", Rarity.LEGENDARY, ItemID.DEMON_TEAR, -1),
	IRONWOOD_TREES("Ironwood trees", Rarity.LEGENDARY, ItemID.IRONWOOD_LOGS, -1),
	REDWOOD_TREES("Redwood trees", Rarity.LEGENDARY, ItemID.REDWOOD_LOGS, -1),
	ROSEWOOD_TREES("Rosewood trees", Rarity.LEGENDARY, ItemID.ROSEWOOD_LOGS, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_WOODCUTTING, -1);

	private final Card card;

	WoodcuttingCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.WOODCUTTING, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
