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

public enum DefenceCards implements CardGroup
{
	BRONZE_ARMOUR("Bronze armour", Rarity.COMMON, ItemID.BRONZE_MED_HELM, -1),
	HARD_LEATHER_BODY("Hard leather body", Rarity.COMMON, ItemID.HARDLEATHER_BODY, -1),
	IRON_ARMOUR("Iron armour", Rarity.COMMON, ItemID.IRON_MED_HELM, -1),
	SPINY_HELMET("Spiny helmet", Rarity.COMMON, ItemID.WALLBEAST_SPIKE_HELMET, -1),
	STEEL_ARMOUR("Steel armour", Rarity.COMMON, ItemID.STEEL_MED_HELM, -1),
	BLACK_ARMOUR("Black armour", Rarity.COMMON, ItemID.BLACK_MED_HELM, -1),
	OAK_SHIELD("Oak shield", Rarity.COMMON, ItemID.OAK_SHIELD, -1),
	SLAYER_HELM("Slayer helm", Rarity.COMMON, ItemID.SLAYER_HELM, -1),
	WHITE_ARMOUR("White armour", Rarity.COMMON, ItemID.WHITE_MED_HELM, -1),
	COIF("Coif", Rarity.UNCOMMON, ItemID.COIF, -1),
	HARD_LEATHER_SHIELD("Hard leather shield", Rarity.UNCOMMON, ItemID.LEATHER_SHIELD, -1),
	INITIATE_ARMOUR("Initiate armour", Rarity.UNCOMMON, ItemID.BASIC_TK_HELM, -1),
	MIRROR_SHIELD("Mirror shield", Rarity.UNCOMMON, ItemID.SLAYER_MIRROR_SHIELD, -1),
	MITHRIL_ARMOUR("Mithril armour", Rarity.UNCOMMON, ItemID.MITHRIL_MED_HELM, -1),
	SHAYZIEN_ARMOUR("Shayzien armour", Rarity.UNCOMMON, ItemID.SHAYZIEN_HELM_5, -1),
	STUDDED_LEATHER_BODY("Studded leather body", Rarity.UNCOMMON, ItemID.STUDDED_BODY, -1),
	STUDDED_LEATHER_CHAPS("Studded leather chaps", Rarity.UNCOMMON, ItemID.STUDDED_CHAPS, -1),
	YAK_HIDE("Yak-hide", Rarity.UNCOMMON, ItemID.YAK_HIDE_ARMOUR_BODY, -1),
	ADAMANT_ARMOUR("Adamant armour", Rarity.UNCOMMON, ItemID.ADAMANT_MED_HELM, -1),
	INQUISITOR_S_ARMOUR("Inquisitor's armour", Rarity.UNCOMMON, ItemID.INQUISITORS_HELM, -1),
	PROSELYTE_ARMOUR("Proselyte armour", Rarity.UNCOMMON, ItemID.BASIC_TK_RANK2_HELM, -1),
	SNAKESKIN_ARMOUR("Snakeskin armour", Rarity.UNCOMMON, ItemID.SNAKESKIN_BODY, -1),
	SNAKESKIN_SHIELD("Snakeskin shield", Rarity.UNCOMMON, ItemID.SNAKESKIN_SHIELD, -1),
	WILLOW_SHIELD("Willow shield", Rarity.UNCOMMON, ItemID.WILLOW_SHIELD, -1),
	SAMURAI_ARMOUR("Samurai armour", Rarity.UNCOMMON, ItemID.SAMURAI_HAT, -1),
	FIGHTER_TORSO("Fighter torso", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_FIGHTER_TORSO, -1),
	GREEN_DRAGONHIDE_BODY("Green dragonhide body", Rarity.RARE, ItemID.DRAGONHIDE_BODY, -1),
	GREEN_DRAGONHIDE_CHAPS("Green dragonhide chaps", Rarity.RARE, ItemID.DRAGONHIDE_CHAPS, -1),
	GREEN_DRAGONHIDE_SHIELD("Green dragonhide shield", Rarity.RARE, ItemID.GREEN_DHIDE_SHIELD, -1),
	GREEN_DRAGONHIDE_VAMBRACES("Green dragonhide vambraces", Rarity.RARE, ItemID.DRAGON_VAMBRACES, -1),
	MAGIC_SHIELD("Magic shield", Rarity.RARE, ItemID.MAGIC_SHIELD, -1),
	MAPLE_SHIELD("Maple shield", Rarity.RARE, ItemID.MAPLE_SHIELD, -1),
	PENANCE_GLOVES("Penance gloves", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_GLOVES, -1),
	RANGER_BOOTS("Ranger boots", Rarity.RARE, ItemID.BOOTS_RANGER, -1),
	RANGER_GLOVES("Ranger gloves", Rarity.RARE, ItemID.RANGER_GLOVES, -1),
	REDWOOD_SHIELD("Redwood shield", Rarity.RARE, ItemID.REDWOOD_SHIELD, -1),
	ROBIN_HOOD_HAT("Robin Hood hat", Rarity.RARE, ItemID.ROBINHOODHAT, -1),
	ROCK_SHELL_ARMOUR("Rock-shell armour", Rarity.RARE, ItemID.DAGGANOTH_MELEE_HELM, -1),
	RUNE_ARMOUR("Rune armour", Rarity.RARE, ItemID.RUNE_MED_HELM, -1),
	RUNNER_BOOTS("Runner boots", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_RUNNER_BOOTS, -1),
	SPINED_ARMOUR("Spined armour", Rarity.RARE, ItemID.DAGGANOTH_RANGED_BODY, -1),
	SUNFIRE_FANATIC_ARMOUR("Sunfire Fanatic armour", Rarity.RARE, ItemID.SUNFIRE_BODY, -1),
	V_S_SHIELD("V's shield", Rarity.RARE, ItemID.V_SHIELD, -1),
	YEW_SHIELD("Yew shield", Rarity.RARE, ItemID.YEW_SHIELD, -1),
	FIGHTER_HAT("Fighter hat", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_FIGHTER_HAT, -1),
	FREMENNIK_HELMETS("Fremennik helmets", Rarity.RARE, ItemID.VIKING_HELMET_CRUSH, -1),
	HEALER_HAT("Healer hat", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_HEALER_HAT, -1),
	RANGER_HAT("Ranger hat", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_RANGER_HAT, -1),
	RUNNER_HAT("Runner hat", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_RUNNER_HAT, -1),
	SPIRIT_SHIELD("Spirit Shield", Rarity.RARE, ItemID.SPIRIT_SHIELD, -1),
	BLOODRAGER_SET("Bloodrager set", Rarity.RARE, ItemID.BLOOD_MOON_HELM, -1),
	BLUE_DRAGONHIDE_BODY("Blue dragonhide body", Rarity.RARE, ItemID.BLUE_DRAGONHIDE_BODY, -1),
	BLUE_DRAGONHIDE_CHAPS("Blue dragonhide chaps", Rarity.RARE, ItemID.BLUE_DRAGONHIDE_CHAPS, -1),
	BLUE_DRAGONHIDE_SHIELD("Blue dragonhide shield", Rarity.RARE, ItemID.BLUE_DHIDE_SHIELD, -1),
	BLUE_DRAGONHIDE_VAMBRACES("Blue dragonhide vambraces", Rarity.RARE, ItemID.BLUE_DRAGON_VAMBRACES, -1),
	GRANITE_ARMOUR("Granite armour", Rarity.RARE, ItemID.GRANITE_HELM, -1),
	ZOMBIE_HELMET("Zombie helmet", Rarity.RARE, ItemID.ZOMBIE_HELMET, -1),
	HELM_OF_NEITIZNOT("Helm of Neitiznot", Rarity.RARE, ItemID.FRIS_KINGLY_HELM, -1),
	DRAGON_ARMOUR("Dragon armour", Rarity.EPIC, ItemID.DRAGON_MED_HELM, -1),
	MIXED_HIDE_LEGS("Mixed hide legs", Rarity.EPIC, ItemID.HIDE_LEGS, -1),
	MIXED_HIDE_TOP_CAPE_AND_BOOTS("Mixed hide top, cape, and boots", Rarity.EPIC, ItemID.HIDE_TOP, -1),
	OBSIDIAN_ARMOUR("Obsidian armour", Rarity.EPIC, ItemID.OBSIDIAN_PLATEBODY, -1),
	ODIUM_MALEDICTION_WARDS("Odium & Malediction wards", Rarity.EPIC, ItemID.ODIUM_WARD, -1),
	PENANCE_SKIRT("Penance skirt", Rarity.EPIC, ItemID.BARBASSAULT_PENANCE_RANGER_LEGS, -1),
	RED_DRAGONHIDE_BODY("Red dragonhide body", Rarity.EPIC, ItemID.RED_DRAGONHIDE_BODY, -1),
	RED_DRAGONHIDE_CHAPS("Red dragonhide chaps", Rarity.EPIC, ItemID.RED_DRAGONHIDE_CHAPS, -1),
	RED_DRAGONHIDE_SHIELD("Red dragonhide shield", Rarity.EPIC, ItemID.RED_DHIDE_SHIELD, -1),
	RED_DRAGONHIDE_VAMBRACES("Red dragonhide vambraces", Rarity.EPIC, ItemID.RED_DRAGON_VAMBRACES, -1),
	TOKTZ_KET_XIL("Toktz-Ket-Xil", Rarity.EPIC, ItemID.TZHAAR_SPIKESHIELD, -1),
	N3RD_AGE_FIGHTER_ARMOUR("3rd Age fighter armour", Rarity.EPIC, ItemID.TRAIL_FIGHTER_HELM, -1),
	N3RD_AGE_RANGE_ARMOUR("3rd Age range armour", Rarity.EPIC, ItemID.TRAIL_RANGER_TORSO, -1),
	BANDOS_ARMOUR("Bandos armour", Rarity.EPIC, ItemID.BANDOS_CHESTPLATE, -1),
	ARMADYL_ARMOUR("Armadyl armour", Rarity.EPIC, ItemID.ARMADYL_HELMET, -1),
	BLACK_DRAGONHIDE_BODY("Black dragonhide body", Rarity.EPIC, ItemID.BLACK_DRAGONHIDE_BODY, -1),
	BLACK_DRAGONHIDE_CHAPS("Black dragonhide chaps", Rarity.EPIC, ItemID.BLACK_DRAGONHIDE_CHAPS, -1),
	BLACK_DRAGONHIDE_SHIELD("Black dragonhide shield", Rarity.EPIC, ItemID.BLACK_DHIDE_SHIELD, -1),
	BLACK_DRAGONHIDE_VAMBRACES("Black dragonhide vambraces", Rarity.EPIC, ItemID.BLACK_DRAGON_VAMBRACES, -1),
	BLESSED_SPIRIT_SHIELD("Blessed spirit shield", Rarity.EPIC, ItemID.BLESSED_SPIRIT_SHIELD, -1),
	BOOTS_OF_BRIMSTONE("Boots of Brimstone", Rarity.EPIC, ItemID.BOOTS_OF_BRIMSTONE, -1),
	CRYSTAL_ARMOUR("Crystal armour", Rarity.EPIC, ItemID.CRYSTAL_HELMET, -1),
	CRYSTAL_SHIELD("Crystal shield", Rarity.EPIC, ItemID.CRYSTAL_SHIELD, -1),
	DHAROK_S_ARMOUR("Dharok's armour", Rarity.EPIC, ItemID.BARROWS_DHAROK_HEAD, -1),
	GOD_DRAGONHIDE_ARMOUR("God dragonhide armour", Rarity.EPIC, ItemID.TRAIL_GUTHIX_CHEST, -1),
	GUTHAN_S_ARMOUR("Guthan's armour", Rarity.EPIC, ItemID.BARROWS_GUTHAN_HEAD, -1),
	HUEYCOATL_HIDE_BODY("Hueycoatl hide body", Rarity.EPIC, ItemID.HUEY_BODY, -1),
	HUEYCOATL_HIDE_CHAPS("Hueycoatl hide chaps", Rarity.EPIC, ItemID.HUEY_CHAPS, -1),
	HUEYCOATL_HIDE_COIF("Hueycoatl hide coif", Rarity.EPIC, ItemID.HUEY_COIF, -1),
	HUEYCOATL_HIDE_VAMBRACES("Hueycoatl hide vambraces", Rarity.EPIC, ItemID.HUEY_VAMBRACES, -1),
	KARIL_S_LEATHER_ARMOUR("Karil's leather armour", Rarity.EPIC, ItemID.BARROWS_KARIL_BODY, -1),
	NEITIZNOT_FACEGUARD("Neitiznot faceguard", Rarity.EPIC, ItemID.NEITIZNOT_FACEGUARD, -1),
	TORAG_S_ARMOUR("Torag's armour", Rarity.EPIC, ItemID.BARROWS_TORAG_HEAD, -1),
	VERAC_S_ARMOUR("Verac's armour", Rarity.EPIC, ItemID.BARROWS_VERAC_HEAD, -1),
	DRAGONFIRE_SHIELD("Dragonfire shield", Rarity.EPIC, ItemID.DRAGONFIRE_SHIELD, -1),
	ECHO_BOOTS("Echo boots", Rarity.EPIC, ItemID.ECHO_BOOTS, -1),
	ELYSIAN_SPIRIT_SHIELD("Elysian spirit shield", Rarity.EPIC, ItemID.ELYSIAN, -1),
	ENCHANTED_ZENYTE_JEWELLERY("Enchanted zenyte jewellery", Rarity.EPIC, ItemID.ZENYTE_RING_ENCHANTED, -1),
	GUARDIAN_BOOTS("Guardian boots", Rarity.EPIC, ItemID.GUARDIAN_BOOTS, -1),
	JUSTICIAR_ARMOUR("Justiciar armour", Rarity.EPIC, ItemID.JUSTICIAR_FACEGUARD, -1),
	PEGASIAN_BOOTS("Pegasian boots", Rarity.EPIC, ItemID.PEGASIAN_BOOTS, -1),
	PRIMORDIAL_BOOTS("Primordial boots", Rarity.EPIC, ItemID.PRIMORDIAL_BOOTS, -1),
	SERPENTINE_HELM("Serpentine helm", Rarity.EPIC, ItemID.SERPENTINE_HELM_CHARGED, -1),
	VIRTUS_ROBES("Virtus robes", Rarity.EPIC, ItemID.VIRTUS_TOP, -1),
	OATHPLATE_ARMOUR("Oathplate armour", Rarity.EPIC, ItemID.OATHPLATE_HELM, -1),
	FORTIFIED_MASORI_ARMOUR("Fortified Masori armour", Rarity.LEGENDARY, ItemID.MASORI_BODY_FORTIFIED, -1),
	MASORI_ARMOUR("Masori armour", Rarity.LEGENDARY, ItemID.MASORI_BODY, -1),
	TORVA_ARMOUR("Torva armour", Rarity.LEGENDARY, ItemID.TORVA_HELM, -1),
	ZARYTE_VAMBRACES("Zaryte vambraces", Rarity.LEGENDARY, ItemID.ZARYTE_VAMBRACES, -1),
	AMULET_OF_RANCOUR("Amulet of Rancour", Rarity.LEGENDARY, ItemID.AMULET_OF_RANCOUR, -1),
	CONFLICTION_GAUNTLETS("Confliction gauntlets", Rarity.LEGENDARY, ItemID.CONFLICTION_GAUNTLETS, -1),
	NECKLACE_OF_RUPTURE("Necklace of Rupture", Rarity.LEGENDARY, ItemID.NECKLACE_OF_RUPTURE, -1);

	private final Card card;

	DefenceCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.DEFENCE, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
