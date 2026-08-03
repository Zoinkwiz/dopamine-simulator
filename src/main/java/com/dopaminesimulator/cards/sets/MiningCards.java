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

public enum MiningCards implements CardGroup
{
	BRONZE_PICKAXE("Bronze pickaxe", Rarity.COMMON, ItemID.BRONZE_PICKAXE, -1),
	CLAY("Clay", Rarity.COMMON, ItemID.CLAY, -1),
	COPPER_ORE("Copper ore", Rarity.COMMON, ItemID.COPPER_ORE, -1),
	IRON_PICKAXE("Iron pickaxe", Rarity.COMMON, ItemID.IRON_PICKAXE, -1),
	RUNE_ESSENCE("Rune essence", Rarity.COMMON, ItemID.BLANKRUNE, -1),
	TIN_ORE("Tin ore", Rarity.COMMON, ItemID.TIN_ORE, -1),
	STEEL_PICKAXE("Steel pickaxe", Rarity.COMMON, ItemID.STEEL_PICKAXE, -1),
	BLURITE_ORE("Blurite ore", Rarity.COMMON, ItemID.BLURITE_ORE, -1),
	LIMESTONE("Limestone", Rarity.COMMON, ItemID.LIMESTONE, -1),
	SIZE_1_STARS("Size-1 stars", Rarity.COMMON, ItemID.STAR_DUST_175, -1),
	BLACK_PICKAXE("Black pickaxe", Rarity.COMMON, ItemID.BLACK_PICKAXE, -1),
	BARRONITE("Barronite", Rarity.COMMON, ItemID.CAMDOZAAL_BARRONITE_SHARD, -1),
	IRON_ORE("Iron ore", Rarity.COMMON, ItemID.IRON_ORE, -1),
	MINING_GLOVES("Mining gloves", Rarity.UNCOMMON, ItemID.MGUILD_GLOVES, -1),
	SILVER_ORE("Silver ore", Rarity.UNCOMMON, ItemID.SILVER_ORE, -1),
	SIZE_2_STARS("Size-2 stars", Rarity.UNCOMMON, ItemID.STAR_DUST_175, -1),
	MITHRIL_PICKAXE("Mithril pickaxe", Rarity.UNCOMMON, ItemID.MITHRIL_PICKAXE, -1),
	VOLCANIC_ASH("Volcanic ash", Rarity.UNCOMMON, ItemID.FOSSIL_VOLCANIC_ASH, -1),
	LEAD_ORE("Lead ore", Rarity.UNCOMMON, ItemID.LEAD_ORE, -1),
	COAL("Coal", Rarity.UNCOMMON, ItemID.COAL, -1),
	MOTHERLODE_MINE_PAYDIRT("Motherlode Mine paydirt", Rarity.UNCOMMON, ItemID.PAYDIRT, -1),
	PURE_ESSENCE("Pure essence", Rarity.UNCOMMON, ItemID.BLANKRUNE_HIGH, -1),
	SIZE_3_STARS("Size-3 stars", Rarity.UNCOMMON, ItemID.STAR_DUST_175, -1),
	ADAMANT_PICKAXE("Adamant pickaxe", Rarity.UNCOMMON, ItemID.ADAMANT_PICKAXE, -1),
	SANDSTONE("Sandstone", Rarity.UNCOMMON, ItemID.ENAKH_SANDSTONE_LARGE, -1),
	DENSE_ESSENCE("Dense essence", Rarity.UNCOMMON, ItemID.ARCEUUS_ESSENCE_BLOCK, -1),
	MOTHERLODE_MINE("Motherlode Mine", Rarity.UNCOMMON, ItemID.PAYDIRT, -1),
	GEM_ROCKS("Gem rocks", Rarity.RARE, ItemID.UNCUT_RED_TOPAZ, -1),
	GOLD("Gold", Rarity.RARE, ItemID.GOLD_ORE, -1),
	SIZE_4_STARS("Size-4 stars", Rarity.RARE, ItemID.STAR_DUST_175, -1),
	CALCIFIED_ROCKS("Calcified rocks", Rarity.RARE, ItemID.CALCIFIED_DEPOSIT, -1),
	GILDED_PICKAXE("Gilded pickaxe", Rarity.RARE, ItemID.TRAIL_GILDED_PICKAXE, -1),
	RUNE_PICKAXE("Rune pickaxe", Rarity.RARE, ItemID.RUNE_PICKAXE, -1),
	VOLCANIC_SULPHUR("Volcanic sulphur", Rarity.RARE, ItemID.LOVAKENGJ_SULPHUR, -1),
	GRANITE("Granite", Rarity.RARE, ItemID.ENAKH_GRANITE_MEDIUM, -1),
	RUBIUM_SPLINTERS("Rubium splinters", Rarity.RARE, ItemID.RUBIUM_SPLINTERS, -1),
	SIZE_5_STARS("Size-5 stars", Rarity.RARE, ItemID.STAR_DUST_175, -1),
	SUNSTONE("Sunstone", Rarity.RARE, 34020, -1),
	MITHRIL_ORE("Mithril ore", Rarity.RARE, ItemID.MITHRIL_ORE, -1),
	SUPERIOR_MINING_GLOVES("Superior mining gloves", Rarity.RARE, ItemID.MGUILD_GLOVES_SUPERIOR, -1),
	MOTHERLODE_MINE_UPPER_AREA("Motherlode Mine upper area", Rarity.RARE, ItemID.PAYDIRT, -1),
	LOVAKENGJ_BLAST_MINE("Lovakengj Blast Mine", Rarity.RARE, -1, SpriteID.Mapfunction.MINING_SITE),
	VOLCANIC_MINE("Volcanic Mine", Rarity.RARE, -1, SpriteID.Mapfunction.MINING_SITE),
	DAEYALT_ESSENCE("Daeyalt essence", Rarity.EPIC, ItemID.BLANKRUNE_DAEYALT, -1),
	SIZE_6_STARS("Size-6 stars", Rarity.EPIC, ItemID.STAR_DUST_175, -1),
	N3RD_AGE_PICKAXE("3rd age pickaxe", Rarity.EPIC, ItemID._3A_PICKAXE, -1),
	DRAGON_PICKAXE("Dragon pickaxe", Rarity.EPIC, ItemID.DRAGON_PICKAXE, -1),
	INFERNAL_PICKAXE("Infernal pickaxe", Rarity.EPIC, ItemID.INFERNAL_PICKAXE, -1),
	TAINTED_ESSENCE_CHUNK("Tainted essence chunk", Rarity.EPIC, ItemID.SCAR_TAINTED_ESSENCE, -1),
	LOVAKITE_ORE("Lovakite ore", Rarity.EPIC, ItemID.LOVAKITE_ORE, -1),
	RUBIUM_GEODE("Rubium geode", Rarity.EPIC, ItemID.RUBIUM_GEODE, -1),
	ADAMANTITE_ORE("Adamantite ore", Rarity.EPIC, ItemID.ADAMANTITE_ORE, -1),
	EXPERT_MINING_GLOVES("Expert mining gloves", Rarity.EPIC, ItemID.MGUILD_GLOVES_EXPERT, -1),
	SIZE_7_STARS("Size-7 stars", Rarity.EPIC, ItemID.STAR_DUST_175, -1),
	SOFT_CLAY("Soft clay", Rarity.EPIC, ItemID.SOFTCLAY, -1),
	CRYSTAL_PICKAXE("Crystal pickaxe", Rarity.EPIC, ItemID.CRYSTAL_PICKAXE, -1),
	BASALT("Basalt", Rarity.EPIC, ItemID.BASALT, -1),
	TE_EFH_URT_SALTS("Te, Efh & Urt Salts", Rarity.EPIC, ItemID.RED_SALT, -1),
	NICKEL_ORE("Nickel ore", Rarity.EPIC, ItemID.NICKEL_ORE, -1),
	ANCIENT_ESSENCE("Ancient essence", Rarity.EPIC, ItemID.ANCIENT_ESSENCE_INV03, -1),
	INFERNAL_SHALE("Infernal shale", Rarity.EPIC, ItemID.INFERNAL_SHALE, -1),
	MINING_GUILD("Mining Guild", Rarity.EPIC, -1, SpriteID.Mapfunction.MINING_SITE),
	SIZE_8_STARS("Size-8 stars", Rarity.LEGENDARY, ItemID.STAR_DUST_175, -1),
	RUNITE_ORE("Runite ore", Rarity.LEGENDARY, ItemID.RUNITE_ORE, -1),
	SIZE_9_STARS("Size-9 stars", Rarity.LEGENDARY, ItemID.STAR_DUST_175, -1),
	AMETHYST("Amethyst", Rarity.LEGENDARY, ItemID.AMETHYST, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_MINING, -1);

	private final Card card;

	MiningCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.MINING, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
