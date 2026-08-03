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

public enum MagicCards implements CardGroup
{
	OPAL_TIPPED_CROSSBOW_BOLTS("Opal-tipped crossbow bolts", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_BRONZE_TIPPED_OPAL_ENCHANTED, -1),
	SAPPHIRE_TIPPED_CROSSBOW_BOLTS("Sapphire-tipped crossbow bolts", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_SAPPHIRE_ENCHANTED, -1),
	JADE_TIPPED_CROSSBOW_BOLTS("Jade-tipped crossbow bolts", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_BLURITE_TIPPED_JADE_ENCHANTED, -1),
	WIZARD_BOOTS("Wizard boots", Rarity.UNCOMMON, ItemID.BOOTS_WIZARD, -1),
	XERICIAN_ARMOUR("Xerician armour", Rarity.UNCOMMON, ItemID.XERIC_HAT, -1),
	PEARL_TIPPED_CROSSBOW_BOLTS("Pearl-tipped crossbow bolts", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_IRON_TIPPED_PEARL_ENCHANTED, -1),
	EMERALD_TIPPED_CROSSBOW_BOLTS("Emerald-tipped crossbow bolts", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_EMERALD_ENCHANTED, -1),
	RED_TOPAZ_TIPPED_CROSSBOW_BOLTS("Red topaz-tipped crossbow bolts", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_STEEL_TIPPED_REDTOPAZ_ENCHANTED, -1),
	BATTLESTAVES("Battlestaves", Rarity.UNCOMMON, ItemID.BATTLESTAFF, -1),
	BRYOPHYTA_S_STAFF("Bryophyta's staff", Rarity.UNCOMMON, ItemID.NATURE_STAFF_CHARGED, -1),
	ELDER_CHAOS_DRUID_ROBES("Elder chaos druid robes", Rarity.RARE, ItemID.ELDERCHAOS_TOP, -1),
	ENCHANTED_ROBES("Enchanted robes", Rarity.RARE, ItemID.ENCHANTED_ROBETOP, -1),
	MYSTIC_ROBES("Mystic robes", Rarity.RARE, ItemID.MYSTIC_ROBE_TOP, -1),
	MYSTIC_STAVES("Mystic staves", Rarity.RARE, ItemID.MYSTIC_AIR_STAFF, -1),
	ROBES_OF_DARKNESS("Robes of darkness", Rarity.RARE, ItemID.ROBE_DARKNESS_TOP, -1),
	SKELETAL_ARMOUR("Skeletal armour", Rarity.RARE, ItemID.DAGGANOTH_MAGE_BODY, -1),
	SPLITBARK_ARMOUR("Splitbark armour", Rarity.RARE, ItemID.SPLITBARK_BODY, -1),
	VOID_KNIGHT_MACE("Void Knight mace", Rarity.RARE, ItemID.PEST_VOID_KNIGHT_MACE, -1),
	BEGINNER_WAND("Beginner wand", Rarity.RARE, ItemID.MAGICTRAINING_WAND_BEG, -1),
	RUBY_TIPPED_CROSSBOW_BOLTS("Ruby-tipped crossbow bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_RUBY_ENCHANTED, -1),
	ANCIENT_STAFF("Ancient staff", Rarity.RARE, ItemID.STAFF_OF_ZAROS, -1),
	APPRENTICE_WAND("Apprentice wand", Rarity.RARE, ItemID.MAGICTRAINING_WAND_APPR, -1),
	BONE_STAFF("Bone staff", Rarity.RARE, ItemID.RAT_BONE_STAFF, -1),
	INFINITY_ROBES("Infinity robes", Rarity.RARE, ItemID.MAGICTRAINING_INFINITYTOP, -1),
	SLAYER_S_STAFF("Slayer's staff", Rarity.RARE, ItemID.SLAYER_STAFF, -1),
	SWAMPBARK_ARMOUR("Swampbark armour", Rarity.RARE, ItemID.SWAMPBARK_BODY, -1),
	TOME_OF_EARTH("Tome of Earth", Rarity.RARE, ItemID.TOME_OF_EARTH, -1),
	TOME_OF_FIRE("Tome of Fire", Rarity.RARE, ItemID.TOME_OF_FIRE, -1),
	TOME_OF_WATER("Tome of Water", Rarity.RARE, ItemID.TOME_OF_WATER, -1),
	TEACHER_WAND("Teacher wand", Rarity.RARE, ItemID.MAGICTRAINING_WAND_TEACH, -1),
	DIAMOND_TIPPED_CROSSBOW_BOLTS("Diamond-tipped crossbow bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_DIAMOND_ENCHANTED, -1),
	BLOODBARK_ARMOUR("Bloodbark armour", Rarity.EPIC, ItemID.BLOODBARK_BODY, -1),
	GOD_CAPES("God capes", Rarity.EPIC, ItemID.GUTHIX_CAPE, -1),
	GOD_STAVES("God staves", Rarity.EPIC, ItemID.GUTHIX_STAFF, -1),
	MAGE_S_BOOK("Mage's Book", Rarity.EPIC, ItemID.MAGICTRAINING_BOOKOFMAGIC, -1),
	MASTER_WAND("Master wand", Rarity.EPIC, ItemID.MAGICTRAINING_WAND_MASTER, -1),
	THAMMARON_S_SCEPTRE("Thammaron's Sceptre", Rarity.EPIC, ItemID.WILD_CAVE_SCEPTRE_CHARGED, -1),
	TOKTZ_MEJ_TAL("TokTz-Mej-Tal", Rarity.EPIC, ItemID.TZHAAR_STAFF, -1),
	TWINFLAME_STAFF("Twinflame staff", Rarity.EPIC, ItemID.TWINFLAME_STAFF, -1),
	WARPED_SCEPTRE("Warped sceptre", Rarity.EPIC, ItemID.WARPED_SCEPTRE, -1),
	N3RD_AGE_ROBES("3rd Age robes", Rarity.EPIC, ItemID.TRAIL_MAGE_TORSO, -1),
	N3RD_AGE_WAND("3rd Age wand", Rarity.EPIC, ItemID.TRAIL_MAGE_WAND, -1),
	ARCANE_SPECTRAL_SPIRIT_SHIELDS("Arcane & Spectral spirit shields", Rarity.EPIC, ItemID.ARCANE, -1),
	DRAGON_HUNTER_WAND("Dragon Hunter wand", Rarity.EPIC, ItemID.DRAGONHUNTER_WAND, -1),
	LUNAR_ARMOUR("Lunar armour", Rarity.EPIC, ItemID.LUNAR_TORSO, -1),
	MAKE_ANCIENT_WYVERN_SHIELD("Make Ancient Wyvern shield", Rarity.EPIC, ItemID.WYVERN_SHIELD, -1),
	DRAGONSTONE_TIPPED_CROSSBOW_BOLTS("Dragonstone-tipped crossbow bolts", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_DRAGONSTONE_ENCHANTED, -1),
	ACCURSED_SCEPTRE("Accursed Sceptre", Rarity.EPIC, ItemID.WILD_CAVE_ACCURSED_CHARGED, -1),
	AHRIM_S_ROBES("Ahrim's robes", Rarity.EPIC, ItemID.BARROWS_AHRIM_BODY, -1),
	AHRIM_S_STAFF("Ahrim's staff", Rarity.EPIC, ItemID.BARROWS_AHRIM_WEAPON, -1),
	ANCIENT_SCEPTRE("Ancient sceptre", Rarity.EPIC, ItemID.ANCIENT_SCEPTRE, -1),
	DAGON_HAI_ROBES("Dagon'hai robes", Rarity.EPIC, ItemID.DAGONHAI_ROBE_TOP, -1),
	OCCULT_NECKLACE("Occult necklace", Rarity.EPIC, ItemID.OCCULT_NECKLACE, -1),
	WIELD_ANCIENT_WYVERN_SHIELD("Wield Ancient Wyvern shield", Rarity.EPIC, ItemID.WYVERN_SHIELD, -1),
	NIGHTMARE_STAFF_WITHOUT_ORB("Nightmare Staff (without orb)", Rarity.EPIC, ItemID.NIGHTMARE_STAFF, -1),
	ANCESTRAL_ROBES("Ancestral robes", Rarity.EPIC, ItemID.ANCESTRAL_ROBE_TOP, -1),
	ETERNAL_BOOTS("Eternal boots", Rarity.EPIC, ItemID.ETERNAL_BOOTS, -1),
	FROSTMOON_SPEAR("Frostmoon Spear", Rarity.EPIC, ItemID.FROSTMOON_SPEAR, -1),
	FROSTMOON_ROBES("Frostmoon robes", Rarity.EPIC, ItemID.FROST_MOON_HELM, -1),
	SLAYER_S_STAFF_E("Slayer's staff (e)", Rarity.EPIC, ItemID.SLAYER_STAFF_ENCHANTED, -1),
	STAFF_OF_BALANCE("Staff of Balance", Rarity.EPIC, ItemID.STAFF_OF_BALANCE, -1),
	STAFF_OF_LIGHT("Staff of Light", Rarity.EPIC, ItemID.STAFF_OF_LIGHT, -1),
	STAFF_OF_THE_DEAD("Staff of the Dead", Rarity.EPIC, ItemID.SOTD, -1),
	TRIDENT_OF_THE_SEAS("Trident of the Seas", Rarity.EPIC, ItemID.TOTS, -1),
	UPGRADED_ANCIENT_SCEPTRE("Upgraded Ancient Sceptre", Rarity.EPIC, ItemID.ANCIENT_SCEPTRE_SMOKE, -1),
	PURGING_STAFF("Purging staff", Rarity.EPIC, ItemID.PURGING_STAFF, -1),
	TRIDENT_OF_THE_SWAMP("Trident of the Swamp", Rarity.EPIC, ItemID.TOXIC_TOTS_CHARGED, -1),
	ELIDINIS_WARD("Elidinis' Ward", Rarity.LEGENDARY, ItemID.ELIDINIS_WARD, -1),
	KODAI_WAND("Kodai Wand", Rarity.LEGENDARY, ItemID.KODAI_WAND, -1),
	SOULFLAME_HORN("Soulflame Horn", Rarity.LEGENDARY, ItemID.SOULFLAME_HORN, -1),
	NIGHTMARE_STAFF_WITH_ORB("Nightmare Staff (with orb)", Rarity.LEGENDARY, ItemID.NIGHTMARE_STAFF_HARMONISED, -1),
	SANGUINESTI_STAFF("Sanguinesti staff", Rarity.LEGENDARY, ItemID.SANGUINESTI_STAFF, -1),
	EYE_OF_AYAK("Eye of Ayak", Rarity.LEGENDARY, ItemID.EYE_OF_AYAK, -1),
	TUMEKEN_S_SHADOW("Tumeken's Shadow", Rarity.LEGENDARY, ItemID.TUMEKENS_SHADOW, -1),
	ONYX_TIPPED_CROSSBOW_BOLTS("Onyx-tipped crossbow bolts", Rarity.LEGENDARY, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_ONYX_ENCHANTED, -1);

	private final Card card;

	MagicCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.MAGIC, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
