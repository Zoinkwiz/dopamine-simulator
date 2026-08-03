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

public enum RangedCards implements CardGroup
{
	BRONZE_CANNON("Bronze cannon", Rarity.COMMON, ItemID.SAILING_SKILLGUIDE_BRONZE_CANNON, -1),
	BRONZE_CROSSBOW("Bronze crossbow", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BRONZE, -1),
	BRONZE_ITEMS("Bronze items", Rarity.COMMON, ItemID.BRONZE_KNIFE, -1),
	CROSSBOW("Crossbow", Rarity.COMMON, ItemID.CROSSBOW, -1),
	IRON_CANNON("Iron cannon", Rarity.COMMON, ItemID.SAILING_SKILLGUIDE_IRON_CANNON, -1),
	IRON_ITEMS("Iron items", Rarity.COMMON, ItemID.IRON_KNIFE, -1),
	PHOENIX_CROSSBOW("Phoenix crossbow", Rarity.COMMON, ItemID.PHOENIX_CROSSBOW, -1),
	PLAIN_LEATHER_ITEMS("Plain leather items", Rarity.COMMON, ItemID.LEATHER_ARMOUR, -1),
	STANDARD_BOWS("Standard bows", Rarity.COMMON, ItemID.SHORTBOW, -1),
	OAK_BOWS("Oak bows", Rarity.COMMON, ItemID.OAK_SHORTBOW, -1),
	STEEL_CANNON("Steel cannon", Rarity.COMMON, ItemID.SAILING_SKILLGUIDE_STEEL_CANNON, -1),
	STEEL_ITEMS("Steel items", Rarity.COMMON, ItemID.STEEL_KNIFE, -1),
	BLACK_ITEMS("Black items", Rarity.COMMON, ItemID.BLACK_KNIFE, -1),
	BLURITE_CROSSBOW("Blurite crossbow", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BLURITE, -1),
	MITHRIL_CANNON("Mithril cannon", Rarity.UNCOMMON, ItemID.SAILING_SKILLGUIDE_MITHRIL_CANNON, -1),
	MITHRIL_ITEMS("Mithril items", Rarity.UNCOMMON, ItemID.MITHRIL_KNIFE, -1),
	WILLOW_BOWS("Willow bows", Rarity.UNCOMMON, ItemID.WILLOW_SHORTBOW, -1),
	FROG_LEATHER("Frog-leather", Rarity.UNCOMMON, ItemID.DORGESH_FROG_ARMOUR_TOP, -1),
	IRON_CROSSBOW("Iron crossbow", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_IRON, -1),
	DORGESHUUN_CROSSBOW("Dorgeshuun crossbow", Rarity.UNCOMMON, ItemID.DTTD_BONE_CROSSBOW, -1),
	ADAMANT_CANNON("Adamant cannon", Rarity.UNCOMMON, ItemID.SAILING_SKILLGUIDE_ADAMANT_CANNON, -1),
	ADAMANT_ITEMS("Adamant items", Rarity.UNCOMMON, ItemID.ADAMANT_KNIFE, -1),
	AVA_S_ATTRACTOR("Ava's attractor", Rarity.UNCOMMON, ItemID.ANMA_30_REWARD, -1),
	MAPLE_BOWS("Maple bows", Rarity.UNCOMMON, ItemID.MAPLE_SHORTBOW, -1),
	OGRE_COMPOSITE_BOWS("Ogre composite bows", Rarity.UNCOMMON, ItemID.ZOGRE_BOW, -1),
	SWAMP_LIZARD("Swamp lizard", Rarity.UNCOMMON, ItemID.GREEN_SALAMANDER, -1),
	STEEL_CROSSBOW("Steel crossbow", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_STEEL, -1),
	MITHRIL_CROSSBOW("Mithril crossbow", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_MITHRIL, -1),
	RANGERS_TIGHTS("Rangers' tights", Rarity.RARE, ItemID.RANGERS_TIGHTS, -1),
	RANGERS_TUNIC("Rangers' tunic", Rarity.RARE, ItemID.RANGER_TUNIC, -1),
	RUNE_CANNON("Rune cannon", Rarity.RARE, ItemID.SAILING_SKILLGUIDE_RUNE_CANNON, -1),
	RUNE_ITEMS("Rune items", Rarity.RARE, ItemID.RUNE_KNIFE, -1),
	YEW_BOWS("Yew bows", Rarity.RARE, ItemID.YEW_SHORTBOW, -1),
	VOID_KNIGHT_EQUIPMENT("Void Knight equipment", Rarity.RARE, ItemID.PEST_VOID_KNIGHT_TOP, -1),
	CAMPHOR_BLOWPIPE("Camphor blowpipe", Rarity.RARE, ItemID.CAMPHOR_BLOWPIPE, -1),
	CHINCHOMPAS("Chinchompas", Rarity.RARE, ItemID.CHINCHOMPA_CAPTURED, -1),
	ADAMANT_CROSSBOW("Adamant crossbow", Rarity.RARE, ItemID.XBOWS_CROSSBOW_ADAMANTITE, -1),
	AMETHYST_DARTS("Amethyst darts", Rarity.RARE, ItemID.AMETHYST_DART, -1),
	AVA_S_ACCUMULATOR("Ava's accumulator", Rarity.RARE, ItemID.ANMA_50_REWARD, -1),
	BONE_SHORTBOW("Bone shortbow", Rarity.RARE, ItemID.RAT_BONE_BOW, -1),
	CRYSTAL_ARMOUR("Crystal armour", Rarity.RARE, ItemID.CRYSTAL_HELMET, -1),
	CRYSTAL_SHIELD("Crystal shield", Rarity.RARE, ItemID.CRYSTAL_SHIELD, -1),
	HUNTERS_CROSSBOW("Hunters' crossbow", Rarity.RARE, ItemID.HUNTING_CROSSBOW, -1),
	MAGIC_BOWS("Magic bows", Rarity.RARE, ItemID.MAGIC_SHORTBOW, -1),
	ORANGE_SALAMANDER("Orange salamander", Rarity.RARE, ItemID.ORANGE_SALAMANDER, -1),
	SEERCULLS("Seerculls", Rarity.RARE, ItemID.DAGANOTH_CAVE_MAGIC_SHORTBOW, -1),
	CARNIVOROUS_CHINCHOMPAS("Carnivorous chinchompas", Rarity.RARE, ItemID.CHINCHOMPA_BIG_CAPTURED, -1),
	HUNTER_S_SPEAR("Hunter's spear", Rarity.RARE, ItemID.HG_HUNTER_SPEAR, -1),
	IRONWOOD_BLOWPIPE("Ironwood blowpipe", Rarity.RARE, ItemID.IRONWOOD_BLOWPIPE, -1),
	AQUANITE_HOPPERS("Aquanite hoppers", Rarity.EPIC, ItemID.AQUANITE_HOPPER, -1),
	CRAW_S_BOW("Craw's bow", Rarity.EPIC, ItemID.WILD_CAVE_BOW_CHARGED, -1),
	DARK_BOWS("Dark bows", Rarity.EPIC, ItemID.DARKBOW, -1),
	DRAGON_CANNON("Dragon cannon", Rarity.EPIC, ItemID.SAILING_SKILLGUIDE_DRAGON_CANNON, -1),
	DRAGON_DARTS("Dragon darts", Rarity.EPIC, ItemID.DRAGON_DART, -1),
	DRAGON_KNIVES("Dragon knives", Rarity.EPIC, ItemID.DRAGON_KNIFE, -1),
	RED_SALAMANDER("Red salamander", Rarity.EPIC, ItemID.RED_SALAMANDER, -1),
	TOKTZ_XIL_UL("TokTz-Xil-Ul", Rarity.EPIC, ItemID.TZHAAR_THROWINGRING, -1),
	DRAGON_THROWNAXES("Dragon thrownaxes", Rarity.EPIC, ItemID.DRAGON_THROWNAXE, -1),
	RUNITE_CROSSBOW("Runite crossbow", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_RUNITE, -1),
	DRAGON_CROSSBOW("Dragon crossbow", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_DRAGON, -1),
	N3RD_AGE_BOW("3rd Age bow", Rarity.EPIC, ItemID.TRAIL_RANGER_BOW, -1),
	BLACK_CHINCHOMPAS("Black chinchompas", Rarity.EPIC, ItemID.CHINCHOMPA_BLACK, -1),
	LIGHT_BALLISTA("Light ballista", Rarity.EPIC, ItemID.LIGHT_BALLISTA, -1),
	ROSEWOOD_BLOWPIPE("Rosewood blowpipe", Rarity.EPIC, ItemID.ROSEWOOD_BLOWPIPE, -1),
	SUNLIGHT_HUNTERS_CROSSBOW("Sunlight hunters' crossbow", Rarity.EPIC, ItemID.HUNTING_CROSSBOW_SUNLIGHT, -1),
	ARMADYL_CROSSBOW("Armadyl crossbow", Rarity.EPIC, ItemID.ACB, -1),
	AVA_S_ASSEMBLER("Ava's Assembler", Rarity.EPIC, ItemID.AVAS_ASSEMBLER, -1),
	BLACK_SALAMANDER("Black salamander", Rarity.EPIC, ItemID.BLACK_SALAMANDER, -1),
	BOW_OF_FAERDHINEN("Bow of Faerdhinen", Rarity.EPIC, ItemID.BOW_OF_FAERDHINEN, -1),
	CRYSTAL_BOWS("Crystal bows", Rarity.EPIC, ItemID.CRYSTAL_BOW, -1),
	DRAGON_HUNTER_CROSSBOW("Dragon hunter crossbow", Rarity.EPIC, ItemID.DRAGONHUNTER_XBOW, -1),
	DRAGONFIRE_WARD("Dragonfire ward", Rarity.EPIC, ItemID.DRAGONFIRE_WARD, -1),
	GOD_DRAGONHIDE_SHIELDS("God dragonhide shields", Rarity.EPIC, ItemID.BLESSED_DHIDE_SHIELD_GUTHIX, -1),
	KARIL_S_CROSSBOW("Karil's crossbow", Rarity.EPIC, ItemID.BARROWS_KARIL_WEAPON, -1),
	WEBWEAVER_BOW("Webweaver bow", Rarity.EPIC, ItemID.WILD_CAVE_WEBWEAVER_CHARGED, -1),
	DIZANA_S_QUIVER("Dizana's Quiver", Rarity.EPIC, ItemID.DIZANAS_QUIVER_CHARGED, -1),
	ECLIPSE_ATLATL("Eclipse Atlatl", Rarity.EPIC, ItemID.ECLIPSE_ATLATL, -1),
	ECLIPSE_SET("Eclipse set", Rarity.EPIC, ItemID.ECLIPSE_MOON_HELM, -1),
	HEAVY_BALLISTA("Heavy ballista", Rarity.EPIC, ItemID.HEAVY_BALLISTA, -1),
	TONALZTICS_OF_RALOS("Tonalztics of Ralos", Rarity.EPIC, ItemID.TONALZTICS_OF_RALOS_CHARGED, -1),
	TOXIC_BLOWPIPE("Toxic blowpipe", Rarity.EPIC, ItemID.TOXIC_BLOWPIPE, -1),
	TWISTED_BUCKLER("Twisted buckler", Rarity.EPIC, ItemID.TWISTED_BUCKLER, -1),
	SCORCHING_BOWS("Scorching bows", Rarity.EPIC, ItemID.SCORCHING_BOW, -1),
	AVERNIC_TREADS("Avernic treads", Rarity.LEGENDARY, ItemID.AVERNIC_TREADS, -1),
	TECU_SALAMANDER("Tecu salamander", Rarity.LEGENDARY, ItemID.MOUNTAIN_SALAMANDER, -1),
	VENATOR_BOW("Venator bow", Rarity.LEGENDARY, ItemID.VENATOR_BOW, -1),
	ZARYTE_CROSSBOW("Zaryte crossbow", Rarity.LEGENDARY, ItemID.ZARYTE_XBOW, -1),
	TWISTED_BOW("Twisted bow", Rarity.LEGENDARY, ItemID.TWISTED_BOW, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_RANGING, -1);

	private final Card card;

	RangedCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.RANGED, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
