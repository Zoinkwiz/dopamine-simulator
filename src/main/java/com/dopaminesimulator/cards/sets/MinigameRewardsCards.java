/*
 * Copyright (c) 2026, Haavardaw <https://github.com/Haavardaw>
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

public enum MinigameRewardsCards implements CardGroup
{

	// Barbarian Assault
	PENANCE_QUEEN("Pet penance queen", Rarity.LEGENDARY, ItemID.PENANCEPET, -1),
	BARBASSAULT_PENANCE_FIGHTER_HAT("Fighter hat", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_FIGHTER_HAT, -1),
	BARBASSAULT_PENANCE_RANGER_HAT("Ranger hat", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_RANGER_HAT, -1),
	BARBASSAULT_PENANCE_RUNNER_HAT("Runner hat", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_RUNNER_HAT, -1),
	BARBASSAULT_PENANCE_HEALER_HAT("Healer hat", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_HEALER_HAT, -1),
	BARBASSAULT_PENANCE_FIGHTER_TORSO("Fighter torso", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_FIGHTER_TORSO, -1),
	BARBASSAULT_PENANCE_RANGER_LEGS("Penance skirt", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_RANGER_LEGS, -1),
	BARBASSAULT_PENANCE_RUNNER_BOOTS("Runner boots", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_RUNNER_BOOTS, -1),
	BARBASSAULT_PENANCE_GLOVES("Penance gloves", Rarity.RARE, ItemID.BARBASSAULT_PENANCE_GLOVES, -1),
	GRANITE_HELM("Granite helm", Rarity.RARE, ItemID.GRANITE_HELM, -1),
	GRANITE_BODY("Granite body", Rarity.RARE, ItemID.GRANITE_BODY, -1),

	// Barracuda trials
	STORMY_KEY("Stormy key", Rarity.COMMON, ItemID.STORMY_KEY, -1),
	BARREL_STAND("Barrel stand", Rarity.COMMON, ItemID.BARREL_STAND, -1),
	RALPHS_FABRIC_ROLL("Ralph's fabric roll", Rarity.COMMON, ItemID.RALPHS_FABRIC_ROLL, -1),
	FETID_KEY("Fetid key", Rarity.UNCOMMON, ItemID.FETID_KEY, -1),
	CAPTURED_WIND_MOTE("Captured wind mote", Rarity.UNCOMMON, ItemID.CAPTURED_WIND_MOTE, -1),
	GURTOBS_FABRIC_ROLL("Gurtob's fabric roll", Rarity.UNCOMMON, ItemID.GURTOBS_FABRIC_ROLL, -1),
	SERRATED_KEY("Serrated key", Rarity.RARE, ItemID.SERRATED_KEY, -1),
	HEART_OF_ITHELL("Heart of Ithell", Rarity.RARE, ItemID.HEART_OF_ITHELL, -1),
	GWYNAS_FABRIC_ROLL("Gwyna's fabric roll", Rarity.RARE, ItemID.GWYNAS_FABRIC_ROLL, -1),

	// Brimhaven Agility Arena
	AGILITY_ARENA_TICKET("Agility arena ticket", Rarity.COMMON, ItemID.AGILITYARENA_TICKET_NEW, -1),
	BRIMHAVEN_VOUCHER("Brimhaven voucher", Rarity.COMMON, ItemID.AGILITYARENA_VOUCHER, -1),
	PIRATE_HOOK("Pirate's hook", Rarity.EPIC, ItemID.PIRATEHOOK, -1),
	GRACEFUL_HOOD_BRIMHAVEN("Brimhaven graceful hood", Rarity.UNCOMMON, ItemID.GRACEFUL_HOOD_SKILLCAPECOLOUR, -1),
	GRACEFUL_TOP_BRIMHAVEN("Brimhaven graceful top", Rarity.UNCOMMON, ItemID.GRACEFUL_TOP_SKILLCAPECOLOUR, -1),
	GRACEFUL_LEGS_BRIMHAVEN("Brimhaven graceful legs", Rarity.UNCOMMON, ItemID.GRACEFUL_LEGS_SKILLCAPECOLOUR, -1),
	GRACEFUL_GLOVES_BRIMHAVEN("Brimhaven graceful gloves", Rarity.UNCOMMON, ItemID.GRACEFUL_GLOVES_SKILLCAPECOLOUR, -1),
	GRACEFUL_BOOTS_BRIMHAVEN("Brimhaven graceful boots", Rarity.UNCOMMON, ItemID.GRACEFUL_BOOTS_SKILLCAPECOLOUR, -1),
	GRACEFUL_CAPE_BRIMHAVEN("Brimhaven graceful cape", Rarity.UNCOMMON, ItemID.GRACEFUL_CAPE_SKILLCAPECOLOUR, -1),

	// Castle Wars
	CASTLEWARS_DECORATIVE_HELM_RED("Red decorative helm", Rarity.COMMON, ItemID.CASTLEWARS_MED_HELM, -1),
	CASTLEWARS_DECORATIVE_FULL_HELM_RED("Red decorative full helm", Rarity.COMMON, ItemID.CASTLEWARS_FULL_HELM, -1),
	CASTLEWARS_DECORATIVE_PLATEBODY_RED("Red decorative body", Rarity.COMMON, ItemID.CASTLEWARS_ARMOUR_BODY, -1),
	CASTLEWARS_DECORATIVE_SWORD_RED("Red decorative sword", Rarity.COMMON, ItemID.CASTLEWARS_SWORD, -1),
	CASTLEWARS_DECORATIVE_SHIELD_RED("Red decorative shield", Rarity.COMMON, ItemID.CASTLEWARS_SHIELD, -1),
	CASTLEWARS_DECORATIVE_PLATELEGS_RED("Red decorative legs", Rarity.COMMON, ItemID.CASTLEWARS_ARMOUR_LEGS, -1),
	CASTLEWARS_DECORATIVE_PLATESKIRT_RED("Red decorative skirt", Rarity.COMMON, ItemID.CASTLEWARS_ARMOUR_SKIRT, -1),
	CASTLEWARS_DECORATIVE_BOOTS_RED("Red decorative boots", Rarity.COMMON, ItemID.CASTLEWARS_BOOTS, -1),
	CASTLEWARS_DECORATIVE_HELM_WHITE("White decorative helm", Rarity.UNCOMMON, ItemID.CASTLEWARS_MED_HELM_2, -1),
	CASTLEWARS_DECORATIVE_FULL_HELM_WHITE("White decorative full helm", Rarity.UNCOMMON, ItemID.CASTLEWARS_FULL_HELM_2, -1),
	CASTLEWARS_DECORATIVE_PLATEBODY_WHITE("White decorative body", Rarity.UNCOMMON, ItemID.CASTLEWARS_ARMOUR_BODY_2, -1),
	CASTLEWARS_DECORATIVE_SWORD_WHITE("White decorative sword", Rarity.UNCOMMON, ItemID.CASTLEWARS_SWORD_2, -1),
	CASTLEWARS_DECORATIVE_SHIELD_WHITE("White decorative shield", Rarity.UNCOMMON, ItemID.CASTLEWARS_SHIELD_2, -1),
	CASTLEWARS_DECORATIVE_PLATELEGS_WHITE("White decorative legs", Rarity.UNCOMMON, ItemID.CASTLEWARS_ARMOUR_LEGS_2, -1),
	CASTLEWARS_DECORATIVE_PLATESKIRT_WHITE("White decorative skirt", Rarity.UNCOMMON, ItemID.CASTLEWARS_ARMOUR_SKIRT_2, -1),
	CASTLEWARS_DECORATIVE_BOOTS_WHITE("White decorative boots", Rarity.UNCOMMON, ItemID.CASTLEWARS_BOOTS_2, -1),
	CASTLEWARS_DECORATIVE_HELM_GOLD("Gold decorative helm", Rarity.EPIC, ItemID.CASTLEWARS_MED_HELM_3, -1),
	CASTLEWARS_DECORATIVE_FULL_HELM_GOLD("Gold decorative full helm", Rarity.EPIC, ItemID.CASTLEWARS_FULL_HELM_3, -1),
	CASTLEWARS_DECORATIVE_PLATEBODY_GOLD("Gold decorative body", Rarity.EPIC, ItemID.CASTLEWARS_ARMOUR_BODY_3, -1),
	CASTLEWARS_DECORATIVE_SWORD_GOLD("Gold decorative sword", Rarity.EPIC, ItemID.CASTLEWARS_SWORD_3, -1),
	CASTLEWARS_DECORATIVE_SHIELD_GOLD("Gold decorative shield", Rarity.EPIC, ItemID.CASTLEWARS_SHIELD_3, -1),
	CASTLEWARS_DECORATIVE_PLATELEGS_GOLD("Gold decorative legs", Rarity.EPIC, ItemID.CASTLEWARS_ARMOUR_LEGS_3, -1),
	CASTLEWARS_DECORATIVE_PLATESKIRT_GOLD("Gold decorative skirt", Rarity.EPIC, ItemID.CASTLEWARS_ARMOUR_SKIRT_3, -1),
	CASTLEWARS_DECORATIVE_BOOTS_GOLD("Gold decorative boots", Rarity.EPIC, ItemID.CASTLEWARS_BOOTS_3, -1),
	CASTLEWARS_HOOD_SARADOMIN("Castlewars hood (Saradomin)", Rarity.COMMON, ItemID.CASTLEWARS_HOOD_SARADOMIN_PRIZE, -1),
	CASTLEWARS_CLOAK_SARADOMIN("Castlewars cloak (Saradomin)", Rarity.COMMON, ItemID.CASTLEWARS_CLOAK_SARADOMIN, -1),
	CASTLEWARS_HOOD_ZAMORAK("Castlewars hood (Zamorak)", Rarity.COMMON, ItemID.CASTLEWARS_HOOD_ZAMORAK_PRIZE, -1),
	CASTLEWARS_CLOAK_ZAMORAK("Castlewars cloak (Zamorak)", Rarity.COMMON, ItemID.CASTLEWARS_CLOAK_ZAMORAK, -1),
	SARADOMIN_BANNER("Saradomin banner", Rarity.RARE, ItemID.CASTLEWARS_SARADOMIN_BANNER, -1),
	ZAMORAK_BANNER("Zamorak banner", Rarity.RARE, ItemID.CASTLEWARS_ZAMORAK_BANNER, -1),
	CASTLEWARS_ARMOUR_MAGE_HAT("Decorative magic hat", Rarity.UNCOMMON, ItemID.CASTLEWARS_MAGE_HAT, -1),
	CASTLEWARS_ARMOUR_MAGE_TOP("Decorative magic robe top", Rarity.UNCOMMON, ItemID.CASTLEWARS_MAGE_TOP, -1),
	CASTLEWARS_ARMOUR_MAGE_SKIRT("Decorative magic robe legs", Rarity.UNCOMMON, ItemID.CASTLEWARS_MAGE_LEGS, -1),
	CASTLEWARS_RANGE_TOP("Decorative ranged top", Rarity.UNCOMMON, ItemID.CASTLEWARS_RANGE_TOP, -1),
	CASTLEWARS_RANGE_LEGS("Decorative ranged legs", Rarity.UNCOMMON, ItemID.CASTLEWARS_RANGE_LEGS, -1),
	CASTLEWARS_RANGE_QUIVER("Decorative quiver", Rarity.UNCOMMON, ItemID.CASTLEWARS_RANGE_QUIVER, -1),
	SARADOMIN_HALO("Saradomin halo", Rarity.RARE, ItemID.CASTLEWARS_SARADOMIN_HALO, -1),
	ZAMORAK_HALO("Zamorak halo", Rarity.RARE, ItemID.CASTLEWARS_ZAMORAK_HALO, -1),
	GUTHIX_HALO("Guthix halo", Rarity.RARE, ItemID.CASTLEWARS_GUTHIX_HALO, -1),

	// Fishing Trawler
	ANGLER_HAT("Angler hat", Rarity.RARE, ItemID.TRAWLER_REWARD_HAT, -1),
    ANGLER_TOP("Angler top", Rarity.RARE, ItemID.TRAWLER_REWARD_TOP, -1),
	ANGLER_WADERS("Angler waders", Rarity.RARE, ItemID.TRAWLER_REWARD_LEGS, -1),
	ANGLER_BOOTS("Angler boots", Rarity.RARE, ItemID.TRAWLER_REWARD_BOOTS, -1),

	// Giants' Foundry
	SMITHS_TUNIC("Smiths tunic", Rarity.RARE, ItemID.SMITHING_UNIFORM_TORSO, -1),
	SMITHS_TROUSERS("Smiths trousers", Rarity.RARE, ItemID.SMITHING_UNIFORM_LEGS, -1),
	SMITHS_BOOTS("Smiths boots", Rarity.RARE, ItemID.SMITHING_UNIFORM_BOOTS, -1),
	SMITHS_GLOVES("Smiths gloves", Rarity.RARE, ItemID.SMITHING_UNIFORM_GLOVES, -1),
	COLOSSAL_BLADE("Colossal blade", Rarity.RARE, ItemID.GIANTS_FOUNDRY_COLOSSAL_BLADE, -1),
	DOUBLE_AMMO_MOULD("Double ammo mould", Rarity.UNCOMMON, ItemID.DOUBLE_AMMO_MOULD, -1),
	KOVACS_GROG("Kovac's grog", Rarity.UNCOMMON, ItemID.KOVACS_GROG, -1),
	SMITHING_CATALYST("Smithing catalyst", Rarity.COMMON, ItemID.SMITHING_CATALYST, -1),
	ORE_PACK("Ore pack", Rarity.UNCOMMON, ItemID.GIANTS_FOUNDRY_ORE_PACK, -1),

	// Gnome Restaurant
	GRAND_SEED_POD("Grand seed pod", Rarity.RARE, ItemID.ALUFT_SEED_POD, -1),
	GNOME_SCARF("Gnome scarf", Rarity.RARE, ItemID.ALUFT_GNOME_SCARF, -1),
	GNOME_GOGGLES("Gnome goggles", Rarity.RARE, ItemID.ALUFT_GNOME_GOGGLES, -1),
	MINT_CAKE("Mint cake", Rarity.RARE, ItemID.ALUFT_GNOME_MINT_CAKE, -1),

	// Guardians of the Rift
	ABYSSAL_PROTECTOR("Abyssal protector", Rarity.EPIC, ItemID.ABYSSALPET, -1),
	ABYSSAL_PEARLS("Abyssal pearls", Rarity.COMMON, ItemID.ABYSSAL_PEARL, -1),
	CATALYTIC_TALISMAN("Catalytic talisman", Rarity.UNCOMMON, ItemID.CATALYTIC_TALISMAN, -1),
	ABYSSAL_NEEDLE("Abyssal needle", Rarity.RARE, ItemID.ABYSSAL_NEEDLE, -1),
	ABYSSAL_GREEN_DYE("Abyssal green dye", Rarity.RARE, ItemID.ABYSSAL_GREEN_DYE, -1),
	ABYSSAL_BLUE_DYE("Abyssal blue dye", Rarity.RARE, ItemID.ABYSSAL_BLUE_DYE, -1),
	ABYSSAL_RED_DYE("Abyssal red dye", Rarity.RARE, ItemID.ABYSSAL_RED_DYE, -1),
	HAT_OF_THE_EYE("Hat of the eye", Rarity.RARE, ItemID.HAT_OF_THE_EYE, -1),
	ROBE_TOP_OF_THE_EYE("Robe top of the eye", Rarity.RARE, ItemID.ROBE_TOP_OF_THE_EYE, -1),
	ROBE_BOTTOMS_OF_THE_EYE("Robe bottoms of the eye", Rarity.RARE, ItemID.ROBE_BOTTOM_OF_THE_EYE, -1),
	BOOTS_OF_THE_EYE("Boots of the eye", Rarity.RARE, ItemID.BOOTS_OF_THE_EYE, -1),
	RING_OF_THE_ELEMENTS("Ring of the elements", Rarity.RARE, ItemID.RING_OF_ELEMENTS, -1),
	ABYSSAL_LANTERN("Abyssal lantern", Rarity.RARE, ItemID.ABYSSAL_LANTERN, -1),
	GUARDIANS_EYE("Guardian's eye", Rarity.EPIC, ItemID.GUARDIANS_EYE, -1),
	INTRICATE_POUCH("Intricate pouch", Rarity.UNCOMMON, ItemID.GOTR_INTRICATE_POUCH, -1),
	LOST_BAG("Lost bag", Rarity.RARE, ItemID.GOTR_LOST_BAG, -1),
	TARNISHED_LOCKET("Tarnished locket", Rarity.RARE, ItemID.GOTR_TARNISHED_LOCKET, -1),

	// Hallowed Sepulchre
	HALLOWED_MARK("Hallowed mark", Rarity.COMMON, ItemID.HALLOWED_MARK, -1),
	HALLOWED_TOKEN("Hallowed token", Rarity.COMMON, ItemID.HALLOWED_TOKEN, -1),
	HALLOWED_GRAPPLE("Hallowed grapple", Rarity.UNCOMMON, ItemID.HALLOWED_GRAPPLE, -1),
	HALLOWED_FOCUS("Hallowed focus", Rarity.UNCOMMON, ItemID.HALLOWED_FOCUS, -1),
	HALLOWED_SYMBOL("Hallowed symbol", Rarity.UNCOMMON, ItemID.HALLOWED_SYMBOL, -1),
	HALLOWED_HAMMER("Hallowed hammer", Rarity.UNCOMMON, ItemID.HALLOWED_HAMMER, -1),
	HALLOWED_RING("Hallowed ring", Rarity.UNCOMMON, ItemID.HALLOWED_RING, -1),
	DARK_DYE("Dark dye", Rarity.UNCOMMON, ItemID.DARK_DYE, -1),
	DARK_ACORN("Dark acorn", Rarity.RARE, ItemID.DARK_ACORN, -1),
	STRANGE_OLD_LOCKPICK("Strange old lockpick (full)", Rarity.UNCOMMON, ItemID.STRANGE_OLD_LOCKPICK_FULL, -1),
	RING_OF_ENDURANCE("Ring of endurance (uncharged)", Rarity.EPIC, ItemID.RING_OF_ENDURANCE_UNCHARGED, -1),
	MYSTERIOUS_PAGE_1("Mysterious page 1", Rarity.UNCOMMON, ItemID.HALLOWED_FLOOR1_PAGE, -1),
	MYSTERIOUS_PAGE_2("Mysterious page 2", Rarity.UNCOMMON, ItemID.HALLOWED_FLOOR2_PAGE, -1),
	MYSTERIOUS_PAGE_3("Mysterious page 3", Rarity.UNCOMMON, ItemID.HALLOWED_FLOOR3_PAGE, -1),
	MYSTERIOUS_PAGE_4("Mysterious page 4", Rarity.UNCOMMON, ItemID.HALLOWED_FLOOR4_PAGE, -1),
	MYSTERIOUS_PAGE_5("Mysterious page 5", Rarity.UNCOMMON, ItemID.HALLOWED_FLOOR5_PAGE, -1),

	// Last Man Standing
	DEADMANS_CHEST("Deadman's chest", Rarity.RARE, ItemID.BR_DEADMAN_BODY, -1),
	DEADMANS_LEGS("Deadman's legs", Rarity.RARE, ItemID.BR_DEADMAN_LEGS, -1),
	DEADMANS_CAPE("Deadman's cape", Rarity.RARE, ItemID.BR_DEADMAN_CAPE, -1),
	ARMADYL_HALO("Armadyl halo", Rarity.RARE, ItemID.ARMADYL_HALO, -1),
	BANDOS_HALO("Bandos halo", Rarity.RARE, ItemID.BANDOS_HALO, -1),
	SEREN_HALO("Seren halo", Rarity.RARE, ItemID.SEREN_HALO, -1),
	ANCIENT_HALO("Ancient halo", Rarity.RARE, ItemID.ZAROS_HALO, -1),
	BRASSICA_HALO("Brassica halo", Rarity.RARE, ItemID.BRASSICA_HALO, -1),
	GOLDEN_ARMADYL_SPECIAL_ATTACK("Golden armadyl special attack", Rarity.UNCOMMON, ItemID.BH_AGS_SPEC, -1),
	GOLDEN_BANDOS_SPECIAL_ATTACK("Golden bandos special attack", Rarity.UNCOMMON, ItemID.BH_BGS_SPEC, -1),
	GOLDEN_SARADOMIN_SPECIAL_ATTACK("Golden saradomin special attack", Rarity.UNCOMMON, ItemID.BH_SGS_SPEC, -1),
	GOLDEN_ZAMORAK_SPECIAL_ATTACK("Golden zamorak special attack", Rarity.UNCOMMON, ItemID.BH_ZGS_SPEC, -1),
	VICTORS_CAPE_1("Victor's cape (1)", Rarity.UNCOMMON, ItemID.BR_CAPE_1, -1),
	VICTORS_CAPE_10("Victor's cape (10)", Rarity.RARE, ItemID.BR_CAPE_10, -1),
	VICTORS_CAPE_50("Victor's cape (50)", Rarity.RARE, ItemID.BR_CAPE_50, -1),
	VICTORS_CAPE_100("Victor's cape (100)", Rarity.RARE, ItemID.BR_CAPE_100, -1),
	VICTORS_CAPE_500("Victor's cape (500)", Rarity.EPIC, ItemID.BR_CAPE_500, -1),
	VICTORS_CAPE_1000("Victor's cape (1000)", Rarity.EPIC, ItemID.BR_CAPE_1000, -1),
	GRANITE_CLAMP("Granite clamp", Rarity.UNCOMMON, ItemID.GRANITE_CLAMP, -1),
	ORNATE_MAUL_HANDLE("Ornate maul handle", Rarity.UNCOMMON, ItemID.GRANITE_MAUL_UPGRADE, -1),
	STEAM_STAFF_UPGRADE_KIT("Steam staff upgrade kit", Rarity.UNCOMMON, ItemID.STEAM_STAFF_UPGRADE_KIT, -1),
	LAVA_STAFF_UPGRADE_KIT("Lava staff upgrade kit", Rarity.UNCOMMON, ItemID.LAVA_STAFF_UPGRADE_KIT, -1),
	DRAGON_PICKAXE_UPGRADE_KIT("Dragon pickaxe upgrade kit", Rarity.UNCOMMON, ItemID.DRAGON_PICKAXE_UPGRADE_KIT, -1),
	WARD_UPGRADE_KIT("Ward upgrade kit", Rarity.UNCOMMON, ItemID.WARD_UPGRADE_KIT, -1),
	GREEN_DARK_BOW_PAINT("Green dark bow paint", Rarity.UNCOMMON, ItemID.BH_GREEN_PAINT, -1),
	YELLOW_DARK_BOW_PAINT("Yellow dark bow paint", Rarity.UNCOMMON, ItemID.BH_YELLOW_PAINT, -1),
	WHITE_DARK_BOW_PAINT("White dark bow paint", Rarity.UNCOMMON, ItemID.BH_WHITE_PAINT, -1),
	BLUE_DARK_BOW_PAINT("Blue dark bow paint", Rarity.UNCOMMON, ItemID.BH_BLUE_PAINT, -1),
	VOLCANIC_WHIP_MIX("Volcanic whip mix", Rarity.UNCOMMON, ItemID.BH_LAVA_PAINT, -1),
	FROZEN_WHIP_MIX("Frozen whip mix", Rarity.UNCOMMON, ItemID.BH_ICE_PAINT, -1),
	GUTHIXIAN_ICON("Guthixian icon", Rarity.RARE, ItemID.ICON_OF_GUTHIX, -1),
	SWIFT_BLADE("Swift blade", Rarity.RARE, ItemID.SWIFT_BLADE, -1),

	// Magic Training Arena
	BEGINNER_WAND("Beginner wand", Rarity.UNCOMMON, ItemID.MAGICTRAINING_WAND_BEG, -1),
	APPRENTICE_WAND("Apprentice wand", Rarity.UNCOMMON, ItemID.MAGICTRAINING_WAND_APPR, -1),
	TEACHER_WAND("Teacher wand", Rarity.UNCOMMON, ItemID.MAGICTRAINING_WAND_TEACH, -1),
	MASTER_WAND("Master wand", Rarity.RARE, ItemID.MAGICTRAINING_WAND_MASTER, -1),
	INFINITY_HAT("Infinity hat", Rarity.RARE, ItemID.MAGICTRAINING_INFINITYHAT, -1),
	INFINITY_TOP("Infinity top", Rarity.RARE, ItemID.MAGICTRAINING_INFINITYTOP, -1),
	INFINITY_BOTTOMS("Infinity bottoms", Rarity.RARE, ItemID.MAGICTRAINING_INFINITYBOTTOM, -1),
	INFINITY_BOOTS("Infinity boots", Rarity.UNCOMMON, ItemID.MAGICTRAINING_INFINITYBOOTS, -1),
	INFINITY_GLOVES("Infinity gloves", Rarity.UNCOMMON, ItemID.MAGICTRAINING_INFINITYGLOVES, -1),
	MAGES_BOOK("Mage's book", Rarity.RARE, ItemID.MAGICTRAINING_BOOKOFMAGIC, -1),
	BONES_TO_PEACHES("Bones to peaches", Rarity.UNCOMMON, ItemID.MAGICTRAINING_PEACHSPELL, -1),

	// Mahogany Homes
	SUPPLY_CRATE("Supply crate", Rarity.UNCOMMON, ItemID.CONSTRUCTION_SUPPLY_CRATE, -1),
	CARPENTERS_HELMET("Carpenter's helmet", Rarity.RARE, ItemID.CONSTRUCTION_HAT, -1),
	CARPENTERS_SHIRT("Carpenter's shirt", Rarity.RARE, ItemID.CONSTRUCTION_SHIRT, -1),
	CARPENTERS_TROUSERS("Carpenter's trousers", Rarity.RARE, ItemID.CONSTRUCTION_TROUSERS, -1),
	CARPENTERS_BOOTS("Carpenter's boots", Rarity.RARE, ItemID.CONSTRUCTION_BOOTS, -1),
	AMYS_SAW("Amy's saw", Rarity.RARE, ItemID.WEARABLE_SAW, -1),
	PLANK_SACK("Plank sack", Rarity.RARE, ItemID.PLANK_SACK, -1),
	HOSIDIUS_BLUEPRINTS("Hosidius blueprints", Rarity.EPIC, ItemID.HOSIDIUS_BLUEPRINTS, -1),

	// Mastering Mixology
	PRESCRIPTION_GOGGLES("Prescription goggles", Rarity.RARE, ItemID.MM_ALCHEMIST_HAT, -1),
	ALCHEMIST_LABCOAT("Alchemist labcoat", Rarity.UNCOMMON, ItemID.MM_ALCHEMIST_BODY, -1),
	ALCHEMIST_PANTS("Alchemist pants", Rarity.UNCOMMON, ItemID.MM_ALCHEMIST_LEGS, -1),
	ALCHEMIST_GLOVES("Alchemist gloves", Rarity.UNCOMMON, ItemID.MM_ALCHEMIST_GLOVES, -1),
	ALCHEMISTS_AMULET("Alchemist's amulet", Rarity.RARE, ItemID.AMULET_OF_CHEMISTRY_IMBUED_CHARGED_DUMMY, -1),
	REAGENT_POUCH("Reagent pouch", Rarity.EPIC, ItemID.MM_SECONDARY_POUCH, -1),
	CHUGGING_BARREL_DISASSEMBLED("Chugging barrel (disassembled)", Rarity.EPIC, ItemID.MM_PREPOT_DEVICE_DISASSEMBLED, -1),

	// Pest Control
	VOID_KNIGHT_MACE("Void knight mace", Rarity.RARE, ItemID.PEST_VOID_KNIGHT_MACE, -1),
	VOID_KNIGHT_TOP("Void knight top", Rarity.RARE, ItemID.PEST_VOID_KNIGHT_TOP, -1),
	VOID_KNIGHT_ROBE("Void knight robe", Rarity.RARE, ItemID.PEST_VOID_KNIGHT_ROBES, -1),
	VOID_KNIGHT_GLOVES("Void knight gloves", Rarity.RARE, ItemID.PEST_VOID_KNIGHT_GLOVES, -1),
	VOID_MAGE_HELM("Void mage helm", Rarity.RARE, ItemID.GAME_PEST_MAGE_HELM, -1),
	VOID_MELEE_HELM("Void melee helm", Rarity.RARE, ItemID.GAME_PEST_MELEE_HELM, -1),
	VOID_RANGER_HELM("Void ranger helm", Rarity.RARE, ItemID.GAME_PEST_ARCHER_HELM, -1),
	VOID_SEAL("Void seal(8)", Rarity.UNCOMMON, ItemID.PEST_SEAL_8, -1),
	ELITE_VOID_TOP("Elite void top", Rarity.RARE, ItemID.ELITE_VOID_KNIGHT_TOP, -1),
	ELITE_VOID_ROBE("Elite void robe", Rarity.RARE, ItemID.ELITE_VOID_KNIGHT_ROBES, -1),

	// Rogues' Den
	ROGUE_MASK("Rogue mask", Rarity.UNCOMMON, ItemID.ROGUESDEN_HELM, -1),
	ROGUE_TOP("Rogue top", Rarity.UNCOMMON, ItemID.ROGUESDEN_BODY, -1),
	ROGUE_TROUSERS("Rogue trousers", Rarity.UNCOMMON, ItemID.ROGUESDEN_LEGS, -1),
	ROGUE_BOOTS("Rogue boots", Rarity.UNCOMMON, ItemID.ROGUESDEN_BOOTS, -1),
	ROGUE_GLOVES("Rogue gloves", Rarity.UNCOMMON, ItemID.ROGUESDEN_GLOVES, -1),

	// Shades of Mort'ton
	AMULET_OF_THE_DAMNED("Amulet of the damned (full)", Rarity.UNCOMMON, ItemID.DAMNED_AMULET, -1),
	FLAMTAER_BAG("Flamtaer bag", Rarity.UNCOMMON, ItemID.FLAMTAER_BAG, -1),
	FINE_CLOTH("Fine cloth", Rarity.COMMON, ItemID.FINE_CLOTH, -1),
	BRONZE_LOCKS("Bronze locks", Rarity.UNCOMMON, ItemID.SHADES_LOCK_BRONZE, -1),
	STEEL_LOCKS("Steel locks", Rarity.UNCOMMON, ItemID.SHADES_LOCK_STEEL, -1),
	BLACK_LOCKS("Black locks", Rarity.UNCOMMON, ItemID.SHADES_LOCK_BLACK, -1),
	SILVER_LOCKS("Silver locks", Rarity.UNCOMMON, ItemID.SHADES_LOCK_SILVER, -1),
	GOLD_LOCKS("Gold locks", Rarity.RARE, ItemID.SHADES_LOCK_GOLD, -1),
	ZEALOTS_HELM("Zealot's helm", Rarity.RARE, ItemID.SHADES_PRAYER_HELM, -1),
	ZEALOTS_ROBE_TOP("Zealot's robe top", Rarity.RARE, ItemID.SHADES_PRAYER_TOP, -1),
	ZEALOTS_ROBE_BOTTOM("Zealot's robe bottom", Rarity.RARE, ItemID.SHADES_PRAYER_BOTTOM, -1),
	ZEALOTS_BOOTS("Zealot's boots", Rarity.RARE, ItemID.SHADES_PRAYER_BOOTS, -1),
	TREE_WIZARDS_JOURNAL("Tree wizards' journal", Rarity.UNCOMMON, ItemID.SHADES_SWAMP_DIARY, -1),
	BLOODY_NOTES("Bloody notes", Rarity.UNCOMMON, ItemID.SHADES_BLOOD_DIARY, -1),

	// Soul Wars
	LIL_CREATOR("Lil' creator", Rarity.EPIC, ItemID.SOULWARSPET_BLUE, -1),
	SOUL_CAPE("Soul cape", Rarity.RARE, ItemID.SOUL_CAPE_BLUE, -1),
	ECTOPLASMATOR("Ectoplasmator", Rarity.UNCOMMON, ItemID.SOUL_WARS_ECTOPLASMATOR, -1),


	// Temple Trekking
	LUMBERJACK_HAT("Lumberjack hat", Rarity.UNCOMMON, ItemID.RAMBLE_LUMBERJACK_HAT, -1),
	LUMBERJACK_TOP("Lumberjack top", Rarity.UNCOMMON, ItemID.RAMBLE_LUMBERJACK_TOP, -1),
	LUMBERJACK_LEGS("Lumberjack legs", Rarity.UNCOMMON, ItemID.RAMBLE_LUMBERJACK_LEGS, -1),
	LUMBERJACK_BOOTS("Lumberjack boots", Rarity.UNCOMMON, ItemID.RAMBLE_LUMBERJACK_BOOTS, -1),

	// Tithe Farm
	FARMERS_STRAWHAT("Farmer's strawhat", Rarity.RARE, ItemID.TITHE_REWARD_HAT_FEMALE, -1),
	FARMERS_SHIRT("Farmer's shirt", Rarity.RARE, ItemID.TITHE_REWARD_TORSO_FEMALE, -1),
	FARMERS_BORO_TROUSERS("Farmer's boro trousers", Rarity.RARE, ItemID.TITHE_REWARD_LEGS_MALE, -1),
	FARMERS_BOOTS("Farmer's boots", Rarity.RARE, ItemID.TITHE_REWARD_FEET_MALE, -1),
	SEED_BOX("Seed box", Rarity.RARE, ItemID.SEED_BOX, -1),
	GRICOLLERS_CAN("Gricoller's can", Rarity.RARE, ItemID.ZEAH_WATERINGCAN, -1),
	HERB_SACK("Herb sack", Rarity.RARE, ItemID.SLAYER_HERB_SACK, -1),

	// Trouble Brewing
	BLUE_NAVAL_SHIRT("Blue naval shirt", Rarity.UNCOMMON, ItemID.BREW_UNIFORM_BLUE, -1),
	BLUE_TRICORN_HAT("Blue tricorn hat", Rarity.UNCOMMON, ItemID.BREW_TRICORN_BLUE, -1),
	BLUE_NAVY_SLACKS("Blue navy slacks", Rarity.UNCOMMON, ItemID.BREW_NAVY_SLACKS_BLUE, -1),
	GREEN_NAVAL_SHIRT("Green naval shirt", Rarity.UNCOMMON, ItemID.BREW_UNIFORM_GREEN, -1),
	GREEN_TRICORN_HAT("Green tricorn hat", Rarity.UNCOMMON, ItemID.BREW_TRICORN_GREEN, -1),
	GREEN_NAVY_SLACKS("Green navy slacks", Rarity.UNCOMMON, ItemID.BREW_NAVY_SLACKS_GREEN, -1),
	RED_NAVAL_SHIRT("Red naval shirt", Rarity.UNCOMMON, ItemID.BREW_UNIFORM_RED, -1),
	RED_TRICORN_HAT("Red tricorn hat", Rarity.UNCOMMON, ItemID.BREW_TRICORN_RED, -1),
	RED_NAVY_SLACKS("Red navy slacks", Rarity.UNCOMMON, ItemID.BREW_NAVY_SLACKS_RED, -1),
	BROWN_NAVAL_SHIRT("Brown naval shirt", Rarity.UNCOMMON, ItemID.BREW_UNIFORM_BROWN, -1),
	BROWN_TRICORN_HAT("Brown tricorn hat", Rarity.UNCOMMON, ItemID.BREW_TRICORN_BROWN, -1),
	BROWN_NAVY_SLACKS("Brown navy slacks", Rarity.UNCOMMON, ItemID.BREW_NAVY_SLACKS_BROWN, -1),
	BLACK_NAVAL_SHIRT("Black naval shirt", Rarity.UNCOMMON, ItemID.BREW_UNIFORM_BLACK, -1),
	BLACK_TRICORN_HAT("Black tricorn hat", Rarity.UNCOMMON, ItemID.BREW_TRICORN_BLACK, -1),
	BLACK_NAVY_SLACKS("Black navy slacks", Rarity.UNCOMMON, ItemID.BREW_NAVY_SLACKS_BLACK, -1),
	PURPLE_NAVAL_SHIRT("Purple naval shirt", Rarity.UNCOMMON, ItemID.BREW_UNIFORM_PURPLE, -1),
	PURPLE_TRICORN_HAT("Purple tricorn hat", Rarity.UNCOMMON, ItemID.BREW_TRICORN_PURPLE, -1),
	PURPLE_NAVY_SLACKS("Purple navy slacks", Rarity.UNCOMMON, ItemID.BREW_NAVY_SLACKS_PURPLE, -1),
	GREY_NAVAL_SHIRT("Grey naval shirt", Rarity.UNCOMMON, ItemID.BREW_UNIFORM_GREY, -1),
	GREY_TRICORN_HAT("Grey tricorn hat", Rarity.UNCOMMON, ItemID.BREW_TRICORN_GREY, -1),
	GREY_NAVY_SLACKS("Grey navy slacks", Rarity.UNCOMMON, ItemID.BREW_NAVY_SLACKS_GREY, -1),
	CUTTHROAT_FLAG("Cutthroat flag", Rarity.RARE, ItemID.BREW_FLAG_1, -1),
	GILDED_SMILE_FLAG("Gilded smile flag", Rarity.RARE, ItemID.BREW_FLAG_2, -1),
	BRONZE_FIST_FLAG("Bronze fist flag", Rarity.RARE, ItemID.BREW_FLAG_3, -1),
	LUCKY_SHOT_FLAG("Lucky shot flag", Rarity.RARE, ItemID.BREW_FLAG_4, -1),
	TREASURE_FLAG("Treasure flag", Rarity.RARE, ItemID.BREW_FLAG_5, -1),
	PHASMATYS_FLAG("Phasmatys flag", Rarity.RARE, ItemID.BREW_FLAG_6, -1),
	THE_STUFF("The stuff", Rarity.COMMON, ItemID.BREW_HYPER_YEAST, -1),
	RED_RUM("Red rum", Rarity.COMMON, ItemID.BREW_RED_RUM, -1),
	BLUE_RUM("Blue rum", Rarity.COMMON, ItemID.BREW_BLUE_RUM, -1),

	// Vale Totems
	FLETCHING_KNIFE("Fletching knife", Rarity.RARE, ItemID.FLETCHING_KNIFE, -1),
	BOW_STRING_SPOOL("Bow string spool", Rarity.RARE, ItemID.BOWSTRING_SPOOL, -1),
	ENT_BRANCH("Ent branch", Rarity.UNCOMMON, ItemID.ENT_BRANCH, -1),
	GREENMAN_MASK("Greenman mask", Rarity.RARE, ItemID.GREENMAN_MASK, -1),

	// Volcanic Mine
	ASH_COVERED_TOME("Ash covered tome", Rarity.RARE, ItemID.FOSSIL_MINE_ULTRASOIL_BOOK, -1),
	LARGE_WATER_CONTAINER("Large water container", Rarity.RARE, ItemID.FOSSIL_MINE_WATER_CONTAINER_DUMMY, -1),
	VOLCANIC_MINE_TELEPORT("Volcanic mine teleport", Rarity.COMMON, ItemID.FOSSIL_TABLET_VOLCANOTELEPORT, -1),
	DRAGON_PICKAXE_BROKEN("Dragon pickaxe (broken)", Rarity.RARE, ItemID.WBR_DRAGON_PICKAXE_BROKEN, -1);



	private final Card card;

	MinigameRewardsCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.MINIGAME_REWARDS, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
