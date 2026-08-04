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
import net.runelite.api.gameval.SpriteID;

public enum BossDropsCards implements CardGroup
{
	// Drops that appear on more than one boss's collection log.
	ABYSSAL_WHIP("Abyssal whip", Rarity.RARE, ItemID.ABYSSAL_WHIP, -1), // Abyssal Sire and Abyssal Demons
	ABYSSAL_HEAD("Abyssal head", Rarity.RARE, ItemID.POH_TROPHYDROP_ABYSSALDEMON, -1), // Abyssal Sire and Abyssal Demons
	ABYSSAL_DAGGER("Abyssal dagger", Rarity.RARE, ItemID.ABYSSAL_DAGGER, -1), // Abyssal Sire and Abyssal Demons
	HYDRA_TAIL("Hydra tail", Rarity.RARE, ItemID.HYDRA_TAIL, -1), // Alchemical Hydra and Hydra
	HYDRAS_FANG("Hydra's fang", Rarity.RARE, ItemID.HYDRA_FANG, -1), // Alchemical Hydra and Hydra
	HYDRAS_EYE("Hydra's eye", Rarity.RARE, ItemID.HYDRA_EYE, -1), // Alchemical Hydra and Hydra
	HYDRAS_HEART("Hydra's heart", Rarity.RARE, ItemID.HYDRA_HEART, -1), // Alchemical Hydra and Hydra
	DRAGON_KNIFE("Dragon knife", Rarity.UNCOMMON, ItemID.DRAGON_KNIFE, -1), // Alchemical Hydra, Hydra, Wyrm and Drake
	DRAGON_THROWNAXE("Dragon thrownaxe", Rarity.UNCOMMON, ItemID.DRAGON_THROWNAXE, -1), // Alchemical Hydra, Hydra, Wyrm and Drake
	ARAXYTE_VENOM_SAC("Araxyte venom sac", Rarity.UNCOMMON, ItemID.ARAXYTE_VENOM_SACK, -1), // Araxxor and Araxytes
	ARAXYTE_HEAD("Araxyte head", Rarity.RARE, ItemID.POH_ARAXYTE_HEAD, -1), // Araxxor and Araxytes
	DRAGON_PICKAXE("Dragon pickaxe", Rarity.RARE, ItemID.DRAGON_PICKAXE, -1), //  Chaos ele, Callisto, Vet'ion, Venenatis, Artio, Calvar'ion, Spindel, KQ and KBD
	DRAGON_2H_SWORD("Dragon 2h sword", Rarity.RARE, ItemID.DRAGON_2H_SWORD, -1), // Chaos ele, Scorpia, KQ, Callisto, Vet'ion, Venenatis, Artio, Calvar'ion and Spindel
	GODSWORD_SHARD_1("Godsword shard 1", Rarity.RARE, ItemID.GODWARS_GODSWORD_BLADE1, -1), // All GWD
	GODSWORD_SHARD_2("Godsword shard 2", Rarity.RARE, ItemID.GODWARS_GODSWORD_BLADE2, -1), // All GWD
	GODSWORD_SHARD_3("Godsword shard 3", Rarity.RARE, ItemID.GODWARS_GODSWORD_BLADE3, -1), // All GWD
	DRAGON_AXE("Dragon axe", Rarity.RARE, ItemID.DRAGON_AXE, -1), // Wintertodt + DKS
	VIRTUS_MASK("Virtus mask", Rarity.EPIC, ItemID.VIRTUS_MASK, -1), // Leviathan, Duke, Vardorvis and Whisperer
	VIRTUS_ROBE_TOP("Virtus robe top", Rarity.EPIC, ItemID.VIRTUS_TOP, -1), // Leviathan, Duke, Vardorvis and Whisperer
	VIRTUS_ROBE_BOTTOM("Virtus robe bottom", Rarity.EPIC, ItemID.VIRTUS_LEGS, -1), // Leviathan, Duke, Vardorvis and Whisperer
	CHROMIUM_INGOT("Chromium ingot", Rarity.RARE, ItemID.CHROMIUM_INGOT, -1), // Leviathan, Duke, Vardorvis and Whisperer
	AWAKENERS_ORB("Awakener's orb", Rarity.RARE, ItemID.DT2_AWAKENERS_ORB, -1), // Leviathan, Duke, Vardorvis and Whisperer
	UNCUT_ONYX("Uncut onyx", Rarity.RARE, ItemID.UNCUT_ONYX, -1), // Colosseum, Zulrah, Skotizo, Moon key, Zalcano, Elf chest (yes there are two more, fake droprates)
	DRAGON_CHAINBODY("Dragon chainbody", Rarity.RARE, ItemID.DRAGON_CHAINBODY, -1), // KQ, Thermy, Smoke Devil, Barb Assault and Dust Devil
	DRACONIC_VISAGE("Draconic visage", Rarity.EPIC, ItemID.DRAGONFIRE_VISAGE, -1), // KBD, Vorkath and buncha dragons
	NIHIL_SHARD("Nihil shard", Rarity.UNCOMMON, ItemID.NIHIL_SHARD, -1), // Nex, Blood Reavers, Spiritual Mages, Rangers and Warriors
	ANCIENT_SHARD("Ancient shard", Rarity.UNCOMMON, ItemID.CATA_SHARD, -1), // Skotizo and Catacombs monsters
	DRAGON_HARPOON("Dragon harpoon", Rarity.RARE, ItemID.DRAGON_HARPOON, -1), // Tempoross and Wyrms
	OCCULT_NECKLACE("Occult necklace", Rarity.RARE, ItemID.OCCULT_NECKLACE, -1), // Thermy and Smoke Devils
	PET_CHAOS_ELEMENTAL("Pet chaos elemental", Rarity.RARE, ItemID.CHAOSELEPET, -1), // Chaos Elemental and Chaos Fanatic
	// Abyssal Sire
	ABYSSAL_ORPHAN("Abyssal orphan", Rarity.EPIC, ItemID.ABYSSALSIRE_PET, -1),
	UNSIRED("Unsired", Rarity.RARE, ItemID.ABYSSALSIRE_UNSIRED, -1),
	BLUDGEON_SPINE("Bludgeon spine", Rarity.RARE, ItemID.ABYSSAL_BLUDGEON_1, -1),
	BLUDGEON_CLAW("Bludgeon claw", Rarity.RARE, ItemID.ABYSSAL_BLUDGEON_2, -1),
	BLUDGEON_AXON("Bludgeon axon", Rarity.RARE, ItemID.ABYSSAL_BLUDGEON_3, -1),
	JAR_OF_MIASMA("Jar of miasma", Rarity.RARE, ItemID.JAR_OF_MIASMA, -1),
	// Alchemical Hydra
	IKKLE_HYDRA("Ikkle hydra", Rarity.EPIC, ItemID.HYDRAPET, -1),
	HYDRAS_CLAW("Hydra's claw", Rarity.EPIC, ItemID.HYDRA_CLAW, -1),
	HYDRA_LEATHER("Hydra leather", Rarity.EPIC, ItemID.HYDRA_LEATHER, -1),
	JAR_OF_CHEMICALS("Jar of chemicals", Rarity.EPIC, ItemID.JAR_OF_CHEMICALS, -1),
	ALCHEMICAL_HYDRA_HEADS("Alchemical hydra heads", Rarity.RARE, ItemID.POH_ALCHEMICAL_HYDRA_HEAD, -1),
	// Amoxliatl
	MOXI("Moxi", Rarity.EPIC, ItemID.AMOXLIATLPET, -1),
	GLACIAL_TEMOTLI("Glacial temotli", Rarity.COMMON, ItemID.GLACIAL_TEMOTLI, -1),
	PENDANT_OF_ATES_INERT("Pendant of ates (inert)", Rarity.COMMON, ItemID.PENDANT_OF_ATES_EMPTY, -1),
	FROZEN_TEAR("Frozen tear", Rarity.COMMON, ItemID.FROZEN_TEAR, -1),
	// Araxxor
	NID("Nid", Rarity.EPIC, ItemID.ARAXXORPET, -1),
	SPIDER_CAVE_TELEPORT("Spider cave teleport", Rarity.UNCOMMON, ItemID.TELEPORTSCROLL_SPIDERCAVE, -1),
	ARAXYTE_FANG("Araxyte fang", Rarity.EPIC, ItemID.ARAXYTE_FANG, -1),
	NOXIOUS_POINT("Noxious point", Rarity.EPIC, ItemID.NOXIOUS_HALBERD_PART_1, -1),
	NOXIOUS_BLADE("Noxious blade", Rarity.EPIC, ItemID.NOXIOUS_HALBERD_PART_2, -1),
	NOXIOUS_POMMEL("Noxious pommel", Rarity.EPIC, ItemID.NOXIOUS_HALBERD_PART_3, -1),
	JAR_OF_VENOM("Jar of venom", Rarity.EPIC, ItemID.JAR_OF_VENOM, -1),
	COAGULATED_VENOM("Coagulated venom", Rarity.UNCOMMON, ItemID.ARAXXOR_PET_MORPH, -1),
	// Barrows Chests
	KARILS_COIF("Karil's coif", Rarity.RARE, ItemID.BARROWS_KARIL_HEAD, -1),
	AHRIMS_HOOD("Ahrim's hood", Rarity.RARE, ItemID.BARROWS_AHRIM_HEAD, -1),
	DHAROKS_HELM("Dharok's helm", Rarity.RARE, ItemID.BARROWS_DHAROK_HEAD, -1),
	GUTHANS_HELM("Guthan's helm", Rarity.RARE, ItemID.BARROWS_GUTHAN_HEAD, -1),
	TORAGS_HELM("Torag's helm", Rarity.RARE, ItemID.BARROWS_TORAG_HEAD, -1),
	VERACS_HELM("Verac's helm", Rarity.RARE, ItemID.BARROWS_VERAC_HEAD, -1),
	KARILS_LEATHERTOP("Karil's leathertop", Rarity.RARE, ItemID.BARROWS_KARIL_BODY, -1),
	AHRIMS_ROBETOP("Ahrim's robetop", Rarity.RARE, ItemID.BARROWS_AHRIM_BODY, -1),
	DHAROKS_PLATEBODY("Dharok's platebody", Rarity.RARE, ItemID.BARROWS_DHAROK_BODY, -1),
	GUTHANS_PLATEBODY("Guthan's platebody", Rarity.RARE, ItemID.BARROWS_GUTHAN_BODY, -1),
	TORAGS_PLATEBODY("Torag's platebody", Rarity.RARE, ItemID.BARROWS_TORAG_BODY, -1),
	VERACS_BRASSARD("Verac's brassard", Rarity.RARE, ItemID.BARROWS_VERAC_BODY, -1),
	KARILS_LEATHERSKIRT("Karil's leatherskirt", Rarity.RARE, ItemID.BARROWS_KARIL_LEGS, -1),
	AHRIMS_ROBESKIRT("Ahrim's robeskirt", Rarity.RARE, ItemID.BARROWS_AHRIM_LEGS, -1),
	DHAROKS_PLATELEGS("Dharok's platelegs", Rarity.RARE, ItemID.BARROWS_DHAROK_LEGS, -1),
	GUTHANS_CHAINSKIRT("Guthan's chainskirt", Rarity.RARE, ItemID.BARROWS_GUTHAN_LEGS, -1),
	TORAGS_PLATELEGS("Torag's platelegs", Rarity.RARE, ItemID.BARROWS_TORAG_LEGS, -1),
	VERACS_PLATESKIRT("Verac's plateskirt", Rarity.RARE, ItemID.BARROWS_VERAC_LEGS, -1),
	KARILS_CROSSBOW("Karil's crossbow", Rarity.RARE, ItemID.BARROWS_KARIL_WEAPON, -1),
	AHRIMS_STAFF("Ahrim's staff", Rarity.RARE, ItemID.BARROWS_AHRIM_WEAPON, -1),
	DHAROKS_GREATAXE("Dharok's greataxe", Rarity.RARE, ItemID.BARROWS_DHAROK_WEAPON, -1),
	GUTHANS_WARSPEAR("Guthan's warspear", Rarity.RARE, ItemID.BARROWS_GUTHAN_WEAPON, -1),
	TORAGS_HAMMERS("Torag's hammers", Rarity.RARE, ItemID.BARROWS_TORAG_WEAPON, -1),
	VERACS_FLAIL("Verac's flail", Rarity.RARE, ItemID.BARROWS_VERAC_WEAPON, -1),
	BOLT_RACK("Bolt rack", Rarity.UNCOMMON, ItemID.BARROWS_KARIL_AMMO, -1),
	// Brutus
	BEEF("Beef", Rarity.RARE, ItemID.COWBOSSPET, -1),
	MOOLETA("Mooleta", Rarity.COMMON, ItemID.MOOLETA, -1),
	BOTTOMLESS_MILK_BUCKET_EMPTY("Bottomless milk bucket (empty)", Rarity.COMMON, ItemID.BOTTOMLESS_MILK_BUCKET, -1),
	COW_SLIPPERS("Cow slippers", Rarity.COMMON, ItemID.COW_SLIPPERS, -1),
	// Bryophyta
	BRYOPHYTAS_ESSENCE("Bryophyta's essence", Rarity.RARE, ItemID.GB_MOSS_ESSENCE, -1),
	// Callisto and Artio
	CALLISTO_CUB("Callisto cub", Rarity.EPIC, ItemID.CALLISTO_PET, -1),
	TYRANNICAL_RING("Tyrannical ring", Rarity.RARE, ItemID.HEAVY_RING, -1),
	CLAWS_OF_CALLISTO("Claws of callisto", Rarity.RARE, ItemID.WBR_CALLISTO_CLAWS, -1),
	VOIDWAKER_HILT("Voidwaker hilt", Rarity.EPIC, ItemID.WBR_VOIDWAKER_HILT, -1),
	// Cerberus
	HELLPUPPY("Hellpuppy", Rarity.EPIC, ItemID.HELL_PET, -1),
	ETERNAL_CRYSTAL("Eternal crystal", Rarity.EPIC, ItemID.ETERNAL_CRYSTAL, -1),
	PEGASIAN_CRYSTAL("Pegasian crystal", Rarity.EPIC, ItemID.PEGASIAN_CRYSTAL, -1),
	PRIMORDIAL_CRYSTAL("Primordial crystal", Rarity.EPIC, ItemID.PRIMORDIAL_CRYSTAL, -1),
	JAR_OF_SOULS("Jar of souls", Rarity.EPIC, ItemID.JAR_OF_SOULS, -1),
	SMOULDERING_STONE("Smouldering stone", Rarity.RARE, ItemID.SMOULDERING_STONE, -1),
	KEY_MASTER_TELEPORT("Key master teleport", Rarity.UNCOMMON, ItemID.TELEPORTSCROLL_CERBERUS, -1),
	// Chaos Fanatic
	ODIUM_SHARD_1("Odium shard 1", Rarity.RARE, ItemID.ODIUM_SHARD1, -1),
	MALEDICTION_SHARD_1("Malediction shard 1", Rarity.RARE, ItemID.MALEDICTION_SHARD1, -1),
	// Commander Zilyana
	PET_ZILYANA("Pet zilyana", Rarity.EPIC, ItemID.SARADOMINPET, -1),
	ARMADYL_CROSSBOW("Armadyl crossbow", Rarity.EPIC, ItemID.ACB, -1),
	SARADOMIN_HILT("Saradomin hilt", Rarity.EPIC, ItemID.GODWARS_GODSWORD_HILT_SARADOMIN, -1),
	SARADOMIN_SWORD("Saradomin sword", Rarity.RARE, ItemID.SARADOMIN_SWORD, -1),
	SARADOMINS_LIGHT("Saradomin's light", Rarity.RARE, ItemID.SARADOMIN_LIGHT, -1),
	// Corporeal Beast
	PET_DARK_CORE("Pet dark core", Rarity.LEGENDARY, ItemID.COREPET, -1),
	ELYSIAN_SIGIL("Elysian sigil", Rarity.LEGENDARY, ItemID.ELYSIAN_SIGIL, -1),
	SPECTRAL_SIGIL("Spectral sigil", Rarity.LEGENDARY, ItemID.SPECTRAL_SIGIL, -1),
	ARCANE_SIGIL("Arcane sigil", Rarity.LEGENDARY, ItemID.ARCANE_SIGIL, -1),
	HOLY_ELIXIR("Holy elixir", Rarity.RARE, ItemID.HOLY_ELIXIR, -1),
	SPIRIT_SHIELD("Spirit shield", Rarity.RARE, ItemID.SPIRIT_SHIELD, -1),
	JAR_OF_SPIRITS("Jar of spirits", Rarity.EPIC, ItemID.JAR_OF_SPIRITS, -1),
	// Crazy Archaeologist
	ODIUM_SHARD_2("Odium shard 2", Rarity.RARE, ItemID.ODIUM_SHARD2, -1),
	MALEDICTION_SHARD_2("Malediction shard 2", Rarity.RARE, ItemID.MALEDICTION_SHARD2, -1),
	FEDORA("Fedora", Rarity.UNCOMMON, ItemID.FEDORA, -1),
	// Dagannoth Kings
	PET_DAGANNOTH_PRIME("Pet dagannoth prime", Rarity.EPIC, ItemID.PRIMEPET, -1),
	PET_DAGANNOTH_SUPREME("Pet dagannoth supreme", Rarity.EPIC, ItemID.SUPREMEPET, -1),
	PET_DAGANNOTH_REX("Pet dagannoth rex", Rarity.EPIC, ItemID.REXPET, -1),
	BERSERKER_RING("Berserker ring", Rarity.RARE, ItemID.BERZERKER_RING, -1),
	ARCHERS_RING("Archers ring", Rarity.RARE, ItemID.RANGER_RING, -1),
	SEERS_RING("Seers ring", Rarity.RARE, ItemID.SEER_RING, -1),
	WARRIOR_RING("Warrior ring", Rarity.RARE, ItemID.WARRIOR_RING, -1),
	SEERCULL("Seercull", Rarity.UNCOMMON, ItemID.DAGANOTH_CAVE_MAGIC_SHORTBOW, -1),
	MUD_BATTLESTAFF("Mud battlestaff", Rarity.UNCOMMON, ItemID.MUD_BATTLESTAFF, -1),
	// Deranged Archaeologist
	STEEL_RING("Steel ring", Rarity.UNCOMMON, ItemID.STEEL_RING, -1),
	// Doom of Mokhaiotl
	DOM("Dom", Rarity.EPIC, ItemID.DOMPET, -1),
	AVERNIC_TREADS("Avernic treads", Rarity.LEGENDARY, ItemID.AVERNIC_TREADS, -1),
	EYE_OF_AYAK_UNCHARGED("Eye of ayak (uncharged)", Rarity.EPIC, ItemID.EYE_OF_AYAK_UNCHARGED, -1),
	MOKHAIOTL_CLOTH("Mokhaiotl cloth", Rarity.EPIC, ItemID.MOKHAIOTL_CLOTH, -1),
	MOKHAIOTL_WAYSTONE("Mokhaiotl waystone", Rarity.UNCOMMON, ItemID.DOM_TELEPORT_ITEM, -1),
	DEMON_TEAR("Demon tear", Rarity.COMMON, ItemID.DEMON_TEAR, -1),
	// Duke Sucellus
	BARON("Baron", Rarity.EPIC, ItemID.DUKESUCELLUSPET, -1),
	EYE_OF_THE_DUKE("Eye of the duke", Rarity.EPIC, ItemID.SOULREAPER_AXE_EYE, -1),
	MAGUS_VESTIGE("Magus vestige", Rarity.EPIC, ItemID.MAGUS_VESTIGE, -1),
	ICE_QUARTZ("Ice quartz", Rarity.RARE, ItemID.ICE_QUARTZ, -1),
	FROZEN_TABLET("Frozen tablet", Rarity.UNCOMMON, ItemID.DUKE_SUCELLUS_TABLET, -1),
	// The Fight Caves
	TZREK_JAD("Tzrek-jad", Rarity.EPIC, ItemID.JAD_PET, -1),
	FIRE_CAPE("Fire cape", Rarity.RARE, ItemID.TZHAAR_CAPE_FIRE, -1),
	// Fortis Colosseum
	SMOL_HEREDIT("Smol heredit", Rarity.LEGENDARY, ItemID.SOLHEREDITPET, -1),
	DIZANAS_QUIVER_UNCHARGED("Dizana's quiver (uncharged)", Rarity.EPIC, ItemID.DIZANAS_QUIVER_UNCHARGED, -1),
	SUNFIRE_FANATIC_CUIRASS("Sunfire fanatic cuirass", Rarity.RARE, ItemID.SUNFIRE_BODY, -1),
	SUNFIRE_FANATIC_CHAUSSES("Sunfire fanatic chausses", Rarity.RARE, ItemID.SUNFIRE_LEGS, -1),
	SUNFIRE_FANATIC_HELM("Sunfire fanatic helm", Rarity.RARE, ItemID.SUNFIRE_HELM, -1),
	ECHO_CRYSTAL("Echo crystal", Rarity.RARE, ItemID.ECHO_CRYSTAL, -1),
	TONALZTICS_OF_RALOS_UNCHARGED("Tonalztics of ralos (uncharged)", Rarity.EPIC, ItemID.TONALZTICS_OF_RALOS_UNCHARGED, -1),
	SUNFIRE_SPLINTERS("Sunfire splinters", Rarity.UNCOMMON, ItemID.SUNFIRESPLINTER, -1),
	// The Gauntlet
	YOUNGLLEF("Youngllef", Rarity.EPIC, ItemID.GAUNTLETPET, -1),
	CRYSTAL_ARMOUR_SEED("Crystal armour seed", Rarity.RARE, ItemID.PRIF_ARMOUR_SEED, -1),
	CRYSTAL_WEAPON_SEED("Crystal weapon seed", Rarity.RARE, ItemID.CRYSTAL_SEED_OLD, -1),
	ENHANCED_CRYSTAL_WEAPON_SEED("Enhanced crystal weapon seed", Rarity.EPIC, ItemID.PRIF_WEAPON_SEED_ENHANCED, -1),
	GAUNTLET_CAPE("Gauntlet cape", Rarity.UNCOMMON, ItemID.GAUNTLET_CRYSTALLINE_CAPE, -1),
	// General Graardor
	PET_GENERAL_GRAARDOR("Pet general graardor", Rarity.EPIC, ItemID.BANDOSPET, -1),
	BANDOS_CHESTPLATE("Bandos chestplate", Rarity.EPIC, ItemID.BANDOS_CHESTPLATE, -1),
	BANDOS_TASSETS("Bandos tassets", Rarity.EPIC, ItemID.BANDOS_SKIRT, -1),
	BANDOS_BOOTS("Bandos boots", Rarity.RARE, ItemID.BANDOS_BOOTS, -1),
	BANDOS_HILT("Bandos hilt", Rarity.EPIC, ItemID.GODWARS_GODSWORD_HILT_BANDOS, -1),
	// Giant Mole
	BABY_MOLE("Baby mole", Rarity.EPIC, ItemID.MOLEPET, -1),
	MOLE_SKIN("Mole skin", Rarity.UNCOMMON, ItemID.MOLE_SKIN, -1),
	MOLE_CLAW("Mole claw", Rarity.UNCOMMON, ItemID.MOLE_CLAW, -1),
	IMMACULATE_MOLE_SKIN("Immaculate mole skin", Rarity.UNCOMMON, ItemID.IMMACULATE_MOLE_SKIN, -1),
	// Grotesque Guardians
	NOON("Noon", Rarity.EPIC, ItemID.DAWNPET, -1),
	BLACK_TOURMALINE_CORE("Black tourmaline core", Rarity.RARE, ItemID.TOURMALINE_CORE, -1),
	GRANITE_GLOVES("Granite gloves", Rarity.RARE, ItemID.GRANITE_GLOVES, -1),
	GRANITE_RING("Granite ring", Rarity.UNCOMMON, ItemID.GRANITE_RING, -1),
	GRANITE_HAMMER("Granite hammer", Rarity.RARE, ItemID.GRANITE_HAMMER, -1),
	JAR_OF_STONE("Jar of stone", Rarity.EPIC, ItemID.JAR_OF_STONE, -1),
	GRANITE_DUST("Granite dust", Rarity.UNCOMMON, ItemID.GRANITE_DUST, -1),
	// Hespori
	BOTTOMLESS_COMPOST_BUCKET("Bottomless compost bucket", Rarity.RARE, ItemID.BOTTOMLESS_COMPOST_BUCKET, -1),
	IASOR_SEED("Iasor seed", Rarity.UNCOMMON, ItemID.IASOR_SEED, -1),
	KRONOS_SEED("Kronos seed", Rarity.UNCOMMON, ItemID.KRONOS_SEED, -1),
	ATTAS_SEED("Attas seed", Rarity.UNCOMMON, ItemID.ATTAS_SEED, -1),
	// The Hueycoatl
	HUBERTE("Huberte", Rarity.EPIC, ItemID.HUEYPET, -1),
	DRAGON_HUNTER_WAND("Dragon hunter wand", Rarity.RARE, ItemID.DRAGONHUNTER_WAND, -1),
	TOME_OF_EARTH_EMPTY("Tome of earth (empty)", Rarity.RARE, ItemID.TOME_OF_EARTH_UNCHARGED, -1),
	SOILED_PAGE("Soiled page", Rarity.UNCOMMON, ItemID.SOILED_PAGE, -1),
	HUEYCOATL_HIDE("Hueycoatl hide", Rarity.RARE, ItemID.HUEY_HIDE, -1),
	HUASCA_SEED("Huasca seed", Rarity.UNCOMMON, ItemID.HUASCA_SEED, -1),
	// The Inferno
	JAL_NIB_REK("Jal-nib-rek", Rarity.LEGENDARY, ItemID.INFERNOPET, -1),
	INFERNAL_CAPE("Infernal cape", Rarity.EPIC, ItemID.INFERNAL_CAPE, -1),
	// Kalphite Queen
	KALPHITE_PRINCESS("Kalphite princess", Rarity.EPIC, ItemID.KQPET_WALKING, -1),
	KQ_HEAD("Kq head", Rarity.RARE, ItemID.POH_TROPHY_KALPHITEQUEEN, -1),
	JAR_OF_SAND("Jar of sand", Rarity.RARE, ItemID.JAR_OF_SAND, -1),
	// King Black Dragon
	PRINCE_BLACK_DRAGON("Prince black dragon", Rarity.EPIC, ItemID.KBDPET, -1),
	KBD_HEADS("Kbd heads", Rarity.RARE, ItemID.POH_TROPHYDROP_KBD, -1),
	// Kraken
	PET_KRAKEN("Pet kraken", Rarity.EPIC, ItemID.KRAKENPET, -1),
	KRAKEN_TENTACLE("Kraken tentacle", Rarity.RARE, ItemID.KRAKEN_TENTACLE, -1),
	TRIDENT_OF_THE_SEAS_FULL("Trident of the seas (full)", Rarity.RARE, ItemID.TOTS, -1),
	JAR_OF_DIRT("Jar of dirt", Rarity.RARE, ItemID.JAR_OF_DIRT, -1),
	// Kree'arra
	PET_KREEARRA("Pet kree'arra", Rarity.EPIC, ItemID.ARMADYLPET, -1),
	ARMADYL_HELMET("Armadyl helmet", Rarity.EPIC, ItemID.ARMADYL_HELMET, -1),
	ARMADYL_CHESTPLATE("Armadyl chestplate", Rarity.EPIC, ItemID.ARMADYL_CHESTPLATE, -1),
	ARMADYL_CHAINSKIRT("Armadyl chainskirt", Rarity.EPIC, ItemID.ARMADYL_SKIRT, -1),
	ARMADYL_HILT("Armadyl hilt", Rarity.EPIC, ItemID.GODWARS_GODSWORD_HILT_ARMADYL, -1),
	// K'ril Tsutsaroth
	PET_KRIL_TSUTSAROTH("Pet k'ril tsutsaroth", Rarity.EPIC, ItemID.ZAMORAKPET, -1),
	STAFF_OF_THE_DEAD("Staff of the dead", Rarity.EPIC, ItemID.SOTD, -1),
	ZAMORAKIAN_SPEAR("Zamorakian spear", Rarity.RARE, ItemID.ZAMORAK_SPEAR, -1),
	STEAM_BATTLESTAFF("Steam battlestaff", Rarity.RARE, ItemID.STEAM_BATTLESTAFF, -1),
	ZAMORAK_HILT("Zamorak hilt", Rarity.EPIC, ItemID.GODWARS_GODSWORD_HILT_ZAMORAK, -1),
	// The Leviathan
	LILVIATHAN("Lil'viathan", Rarity.EPIC, ItemID.LEVIATHANPET, -1),
	LEVIATHANS_LURE("Leviathan's lure", Rarity.EPIC, ItemID.SOULREAPER_AXE_LURE, -1),
	VENATOR_VESTIGE("Venator vestige", Rarity.EPIC, ItemID.VENATOR_VESTIGE, -1),
	SMOKE_QUARTZ("Smoke quartz", Rarity.RARE, ItemID.SMOKE_QUARTZ, -1),
	SCARRED_TABLET("Scarred tablet", Rarity.UNCOMMON, ItemID.LEVIATHAN_TABLET, -1),
	// TODO: The Mad Angel. Left commented because HALLOWFELL, TELEPORTSCROLL_ARDEAGLAIS,
	// MADANGELPET and JAR_OF_LIGHT do not exist in the RuneLite API yet, so this will
	// not compile. Uncomment once they land, and set rarities to match the set.
	// HALLOWFELL("Hallowfell", Rarity.COMMON, ItemID.HALLOWFELL, -1),
	// ARDEAGLAIS_TELEPORT("Ardeaglais teleport", Rarity.COMMON, ItemID.TELEPORTSCROLL_ARDEAGLAIS, -1),
	// GRANITE_DUST_MAD_ANGEL("Granite dust", Rarity.COMMON, ItemID.GRANITE_DUST, -1),
	// AGGY("Aggy", Rarity.COMMON, ItemID.MADANGELPET, -1),
	// JAR_OF_LIGHT("Jar of light", Rarity.COMMON, ItemID.JAR_OF_LIGHT, -1),
	// Maggot King
	MAGGOT_MARQUESS("Maggot marquess", Rarity.EPIC, ItemID.MAGGOTKINGPET, -1),
	CRIMSON_KISTEN("Crimson kisten", Rarity.EPIC, ItemID.CRIMSON_KISTEN, -1),
	ELDER_VENATOR_FANG("Elder venator fang", Rarity.EPIC, ItemID.ELDER_VENATOR_FANG, -1),
	// Moons of Peril
	ECLIPSE_MOON_CHESTPLATE("Eclipse moon chestplate", Rarity.RARE, ItemID.ECLIPSE_MOON_CHESTPLATE, -1),
	ECLIPSE_MOON_TASSETS("Eclipse moon tassets", Rarity.RARE, ItemID.ECLIPSE_MOON_TASSETS, -1),
	ECLIPSE_MOON_HELM("Eclipse moon helm", Rarity.RARE, ItemID.ECLIPSE_MOON_HELM, -1),
	ECLIPSE_ATLATL("Eclipse atlatl", Rarity.RARE, ItemID.ECLIPSE_ATLATL, -1),
	BLUE_MOON_CHESTPLATE("Blue moon chestplate", Rarity.RARE, ItemID.FROST_MOON_CHESTPLATE, -1),
	BLUE_MOON_TASSETS("Blue moon tassets", Rarity.RARE, ItemID.FROST_MOON_TASSETS, -1),
	BLUE_MOON_HELM("Blue moon helm", Rarity.RARE, ItemID.FROST_MOON_HELM, -1),
	BLUE_MOON_SPEAR("Blue moon spear", Rarity.RARE, ItemID.FROSTMOON_SPEAR, -1),
	BLOOD_MOON_CHESTPLATE("Blood moon chestplate", Rarity.RARE, ItemID.BLOOD_MOON_CHESTPLATE, -1),
	BLOOD_MOON_TASSETS("Blood moon tassets", Rarity.RARE, ItemID.BLOOD_MOON_TASSETS, -1),
	BLOOD_MOON_HELM("Blood moon helm", Rarity.RARE, ItemID.BLOOD_MOON_HELM, -1),
	DUAL_MACUAHUITL("Dual macuahuitl", Rarity.RARE, ItemID.DUAL_MACUAHUITL, -1),
	ATLATL_DART("Atlatl dart", Rarity.UNCOMMON, ItemID.ATLATL_DART, -1),
	// Nex
	NEXLING("Nexling", Rarity.EPIC, ItemID.NEXPET, -1),
	ANCIENT_HILT("Ancient hilt", Rarity.EPIC, ItemID.GODWARS_GODSWORD_HILT_ANCIENT, -1),
	NIHIL_HORN("Nihil horn", Rarity.EPIC, ItemID.NIHIL_HORN, -1),
	ZARYTE_VAMBRACES("Zaryte vambraces", Rarity.EPIC, ItemID.ZARYTE_VAMBRACES, -1),
	TORVA_FULL_HELM_DAMAGED("Torva full helm (damaged)", Rarity.EPIC, ItemID.BROKEN_TORVA_HELM, -1),
	TORVA_PLATEBODY_DAMAGED("Torva platebody (damaged)", Rarity.EPIC, ItemID.BROKEN_TORVA_CHEST, -1),
	TORVA_PLATELEGS_DAMAGED("Torva platelegs (damaged)", Rarity.EPIC, ItemID.BROKEN_TORVA_LEGS, -1),
	// The Nightmare
	LITTLE_NIGHTMARE("Little nightmare", Rarity.EPIC, ItemID.NIGHTMAREPET, -1),
	INQUISITORS_MACE("Inquisitor's mace", Rarity.EPIC, ItemID.INQUISITORS_MACE, -1),
	INQUISITORS_GREAT_HELM("Inquisitor's great helm", Rarity.EPIC, ItemID.INQUISITORS_HELM, -1),
	INQUISITORS_HAUBERK("Inquisitor's hauberk", Rarity.EPIC, ItemID.INQUISITORS_BODY, -1),
	INQUISITORS_PLATESKIRT("Inquisitor's plateskirt", Rarity.EPIC, ItemID.INQUISITORS_SKIRT, -1),
	NIGHTMARE_STAFF("Nightmare staff", Rarity.EPIC, ItemID.NIGHTMARE_STAFF, -1),
	VOLATILE_ORB("Volatile orb", Rarity.LEGENDARY, ItemID.VOLATILE_ORB, -1),
	HARMONISED_ORB("Harmonised orb", Rarity.LEGENDARY, ItemID.HARMONISED_ORB, -1),
	ELDRITCH_ORB("Eldritch orb", Rarity.LEGENDARY, ItemID.ELDRITCH_ORB, -1),
	JAR_OF_DREAMS("Jar of dreams", Rarity.EPIC, ItemID.JAR_OF_DREAMS, -1),
	SLEPEY_TABLET("Slepey tablet", Rarity.UNCOMMON, ItemID.SLEPE_TELEPORT_CONSUMABLE, -1),
	PARASITIC_EGG("Parasitic egg", Rarity.RARE, ItemID.NIGHTMARE_CHALLENGE_MORPH, -1),
	// Obor
	HILL_GIANT_CLUB("Hill giant club", Rarity.RARE, ItemID.HILLGIANT_BOSS_CLUB, -1),
	// Phantom Muspah
	MUPHIN("Muphin", Rarity.EPIC, ItemID.MUSPAHPET, -1),
	VENATOR_SHARD("Venator shard", Rarity.RARE, ItemID.VENATOR_SHARD, -1),
	ANCIENT_ICON("Ancient icon", Rarity.RARE, ItemID.ANCIENT_ICON, -1),
	CHARGED_ICE("Charged ice", Rarity.UNCOMMON, ItemID.MUSPAH_PET_MORPH, -1),
	FROZEN_CACHE("Frozen cache", Rarity.UNCOMMON, ItemID.FROZEN_CACHE, -1),
	ANCIENT_ESSENCE("Ancient essence", Rarity.UNCOMMON, ItemID.ANCIENT_ESSENCE, -1),
	// Royal Titans
	BRAN("Bran", Rarity.EPIC, ItemID.RTBRANDAPET, -1),
	DEADEYE_PRAYER_SCROLL("Deadeye prayer scroll", Rarity.RARE, ItemID.DEADEYE_PRAYER_SCROLL, -1),
	MYSTIC_VIGOUR_PRAYER_SCROLL("Mystic vigour prayer scroll", Rarity.RARE, ItemID.MYSTIC_VIGOUR_PRAYER_SCROLL, -1),
	GIANTSOUL_AMULET_UNCHARGED("Giantsoul amulet (uncharged)", Rarity.UNCOMMON, ItemID.GIANTSOUL_AMULET_UNCHARGED, -1),
	ICE_ELEMENT_STAFF_CROWN("Ice element staff crown", Rarity.RARE, ItemID.TWINFLAME_PIECE_1, -1),
	FIRE_ELEMENT_STAFF_CROWN("Fire element staff crown", Rarity.RARE, ItemID.TWINFLAME_PIECE_2, -1),
	DESICCATED_PAGE("Desiccated page", Rarity.UNCOMMON, ItemID.DESICCATED_PAGE, -1),
	// Sarachnis
	SRARACHA("Sraracha", Rarity.EPIC, ItemID.SARACHNISPET, -1),
	JAR_OF_EYES("Jar of eyes", Rarity.RARE, ItemID.JAR_OF_EYES, -1),
	GIANT_EGG_SACFULL("Giant egg sac(full)", Rarity.UNCOMMON, ItemID.HOSDUN_EGG_SAC_FULL, -1),
	SARACHNIS_CUDGEL("Sarachnis cudgel", Rarity.RARE, ItemID.SARACHNIS_CUDGEL, -1),
	PRISTINE_SPIDER_SILK("Pristine spider silk", Rarity.UNCOMMON, ItemID.SLAYER_SPIDER_SILK, -1),
	// Scorpia
	SCORPIAS_OFFSPRING("Scorpia's offspring", Rarity.EPIC, ItemID.SCORPIA_PET, -1),
	ODIUM_SHARD_3("Odium shard 3", Rarity.RARE, ItemID.ODIUM_SHARD3, -1),
	MALEDICTION_SHARD_3("Malediction shard 3", Rarity.RARE, ItemID.MALEDICTION_SHARD3, -1),
	// Scurrius
	SCURRY("Scurry", Rarity.EPIC, ItemID.SCURRIUSPET, -1),
	SCURRIUS_SPINE("Scurrius' spine", Rarity.UNCOMMON, ItemID.RAT_BOSS_SPINE, -1),
	// Shellbane Gryphon
	GULL("Gull", Rarity.EPIC, ItemID.GRYPHONBOSSPET, -1),
	JAR_OF_FEATHERS("Jar of feathers", Rarity.RARE, ItemID.JAR_OF_FEATHERS, -1),
	BELLES_FOLLY_TARNISHED("Belle's folly (tarnished)", Rarity.RARE, ItemID.BELLES_FOLLY_TARNISHED, -1),
	GRYPHON_FEATHER("Gryphon feather", Rarity.COMMON, ItemID.GRYPHON_FEATHER, -1),
	// Skotizo
	SKOTOS("Skotos", Rarity.RARE, ItemID.SKOTIZOPET, -1),
	JAR_OF_DARKNESS("Jar of darkness", Rarity.EPIC, ItemID.JAR_OF_DARKNESS, -1),
	DARK_CLAW("Dark claw", Rarity.RARE, ItemID.CATA_BOSS_CLAW, -1),
	DARK_TOTEM("Dark totem", Rarity.RARE, ItemID.CATA_TOTEM, -1),
	// Tempoross
	TINY_TEMPOR("Tiny tempor", Rarity.EPIC, ItemID.TEMPOROSSPET, -1),
	BIG_HARPOONFISH("Big harpoonfish", Rarity.RARE, ItemID.POH_TROPHYDROP_HARPOONFISH, -1),
	SPIRIT_ANGLER_HEADBAND("Spirit angler headband", Rarity.UNCOMMON, ItemID.SPIRIT_ANGLER_HAT, -1),
	SPIRIT_ANGLER_TOP("Spirit angler top", Rarity.UNCOMMON, ItemID.SPIRIT_ANGLER_TOP, -1),
	SPIRIT_ANGLER_WADERS("Spirit angler waders", Rarity.UNCOMMON, ItemID.SPIRIT_ANGLER_LEGS, -1),
	SPIRIT_ANGLER_BOOTS("Spirit angler boots", Rarity.UNCOMMON, ItemID.SPIRIT_ANGLER_BOOTS, -1),
	TOME_OF_WATER_EMPTY("Tome of water (empty)", Rarity.RARE, ItemID.TOME_OF_WATER_UNCHARGED, -1),
	SOAKED_PAGE("Soaked page", Rarity.UNCOMMON, ItemID.SOAKED_PAGE, -1),
	TACKLE_BOX("Tackle box", Rarity.RARE, ItemID.TACKLE_BOX, -1),
	FISH_BARREL("Fish barrel", Rarity.RARE, ItemID.FISH_BARREL_CLOSED, -1),
	SPIRIT_FLAKES("Spirit flakes", Rarity.UNCOMMON, ItemID.SPIRIT_FLAKES, -1),
	// Thermonuclear Smoke Devil
	PET_SMOKE_DEVIL("Pet smoke devil", Rarity.EPIC, ItemID.SMOKEPET, -1),
	SMOKE_BATTLESTAFF("Smoke battlestaff", Rarity.RARE, ItemID.SMOKE_BATTLESTAFF, -1),
	JAR_OF_SMOKE("Jar of smoke", Rarity.EPIC, ItemID.JAR_OF_SMOKE, -1),
	// Vardorvis
	BUTCH("Butch", Rarity.EPIC, ItemID.VARDORVISPET, -1),
	EXECUTIONERS_AXE_HEAD("Executioner's axe head", Rarity.EPIC, ItemID.SOULREAPER_AXE_HEAD, -1),
	ULTOR_VESTIGE("Ultor vestige", Rarity.EPIC, ItemID.ULTOR_VESTIGE, -1),
	BLOOD_QUARTZ("Blood quartz", Rarity.RARE, ItemID.BLOOD_QUARTZ, -1),
	STRANGLED_TABLET("Strangled tablet", Rarity.UNCOMMON, ItemID.VARDORVIS_TABLET, -1),
	// Venenatis and Spindel
	VENENATIS_SPIDERLING("Venenatis spiderling", Rarity.EPIC, ItemID.VENENATIS_PET, -1),
	TREASONOUS_RING("Treasonous ring", Rarity.RARE, ItemID.SHARP_RING, -1),
	FANGS_OF_VENENATIS("Fangs of venenatis", Rarity.RARE, ItemID.WBR_VENENATIS_FANG, -1),
	VOIDWAKER_GEM("Voidwaker gem", Rarity.EPIC, ItemID.WBR_VOIDWAKER_GEM, -1),
	// Vet'ion and Calvar'ion
	VETION_JR("Vet'ion jr.", Rarity.EPIC, ItemID.VETION_PET, -1),
	RING_OF_THE_GODS("Ring of the gods", Rarity.RARE, ItemID.ROTG, -1),
	SKULL_OF_VETION("Skull of vet'ion", Rarity.RARE, ItemID.WBR_VETION_SKULL, -1),
	VOIDWAKER_BLADE("Voidwaker blade", Rarity.EPIC, ItemID.WBR_VOIDWAKER_BLADE, -1),
	// Vorkath
	VORKI("Vorki", Rarity.EPIC, ItemID.VORKATHPET, -1),
	VORKATHS_HEAD("Vorkath's head", Rarity.RARE, ItemID.VORKATH_HEAD, -1),
	SKELETAL_VISAGE("Skeletal visage", Rarity.EPIC, ItemID.SKELETAL_VISAGE, -1),
	JAR_OF_DECAY("Jar of decay", Rarity.EPIC, ItemID.JAR_OF_DECAY, -1),
	DRAGONBONE_NECKLACE("Dragonbone necklace", Rarity.RARE, ItemID.DRAGONBONE_NECKLACE, -1),
	// The Whisperer
	WISP("Wisp", Rarity.EPIC, ItemID.WHISPERERPET, -1),
	SIRENS_STAFF("Siren's staff", Rarity.EPIC, ItemID.SOULREAPER_AXE_STAFF, -1),
	BELLATOR_VESTIGE("Bellator vestige", Rarity.EPIC, ItemID.BELLATOR_VESTIGE, -1),
	SHADOW_QUARTZ("Shadow quartz", Rarity.RARE, ItemID.SHADOW_QUARTZ, -1),
	SIRENIC_TABLET("Sirenic tablet", Rarity.UNCOMMON, ItemID.WHISPERER_TABLET, -1),
	// Wintertodt
	PHOENIX("Phoenix", Rarity.EPIC, ItemID.PHOENIXPET, -1),
	TOME_OF_FIRE_EMPTY("Tome of fire (empty)", Rarity.RARE, ItemID.TOME_OF_FIRE_UNCHARGED, -1),
	BURNT_PAGE("Burnt page", Rarity.UNCOMMON, ItemID.WINT_BURNT_PAGE, -1),
	PYROMANCER_GARB("Pyromancer garb", Rarity.UNCOMMON, ItemID.PYROMANCER_TOP, -1),
	PYROMANCER_HOOD("Pyromancer hood", Rarity.UNCOMMON, ItemID.PYROMANCER_HOOD, -1),
	PYROMANCER_ROBE("Pyromancer robe", Rarity.UNCOMMON, ItemID.PYROMANCER_BOTTOM, -1),
	PYROMANCER_BOOTS("Pyromancer boots", Rarity.UNCOMMON, ItemID.PYROMANCER_BOOTS, -1),
	WARM_GLOVES("Warm gloves", Rarity.UNCOMMON, ItemID.PYROMANCER_GLOVES, -1),
	BRUMA_TORCH("Bruma torch", Rarity.UNCOMMON, ItemID.WINT_TORCH, -1),
	// Yama
	YAMI("Yami", Rarity.EPIC, ItemID.YAMAPET, -1),
	CHASM_TELEPORT_SCROLL("Chasm teleport scroll", Rarity.UNCOMMON, ItemID.TELEPORTSCROLL_CHASMOFFIRE, -1),
	OATHPLATE_SHARDS("Oathplate shards", Rarity.UNCOMMON, ItemID.OATHPLATE_SHARDS, -1),
	OATHPLATE_HELM("Oathplate helm", Rarity.EPIC, ItemID.OATHPLATE_HELM, -1),
	OATHPLATE_CHEST("Oathplate chest", Rarity.EPIC, ItemID.OATHPLATE_CHEST, -1),
	OATHPLATE_LEGS("Oathplate legs", Rarity.EPIC, ItemID.OATHPLATE_LEGS, -1),
	SOULFLAME_HORN("Soulflame horn", Rarity.EPIC, ItemID.SOULFLAME_HORN, -1),
	RITE_OF_VILE_TRANSFERENCE("Rite of vile transference", Rarity.RARE, ItemID.DEATH_CHARGE_SCROLL, -1),
	FORGOTTEN_LOCKBOX("Forgotten lockbox", Rarity.UNCOMMON, ItemID.FORGOTTEN_LOCKBOX, -1),
	DOSSIER("Dossier", Rarity.UNCOMMON, ItemID.YAMA_DOSSIER, -1),
	BARREL_OF_DEMONIC_TALLOW_FULL("Barrel of demonic tallow (full)", Rarity.UNCOMMON, ItemID.DEMONIC_TALLOW_BARREL_FULL, -1),
	// Zalcano
	SMOLCANO("Smolcano", Rarity.EPIC, ItemID.ZALCANOPET, -1),
	CRYSTAL_TOOL_SEED("Crystal tool seed", Rarity.RARE, ItemID.PRIF_TOOL_SEED, -1),
	ZALCANO_SHARD("Zalcano shard", Rarity.RARE, ItemID.ZALCANO_PICKAXE_KIT, -1),
	// Zulrah
	PET_SNAKELING("Pet snakeling", Rarity.EPIC, ItemID.SNAKEPET, -1),
	TANZANITE_MUTAGEN("Tanzanite mutagen", Rarity.EPIC, ItemID.CYAN_MUTAGEN, -1),
	MAGMA_MUTAGEN("Magma mutagen", Rarity.EPIC, ItemID.RED_MUTAGEN, -1),
	JAR_OF_SWAMP("Jar of swamp", Rarity.EPIC, ItemID.JAR_OF_SWAMP, -1),
	MAGIC_FANG("Magic fang", Rarity.RARE, ItemID.MAGIC_FANG, -1),
	SERPENTINE_VISAGE("Serpentine visage", Rarity.RARE, ItemID.SERPENTINE_VISAGE, -1),
	TANZANITE_FANG("Tanzanite fang", Rarity.RARE, ItemID.BLOWPIPE_FANG, -1),
	ZUL_ANDRA_TELEPORT("Zul-andra teleport", Rarity.UNCOMMON, ItemID.TELEPORTSCROLL_ZULANDRA, -1),
	ZULRAHS_SCALES("Zulrah's scales", Rarity.UNCOMMON, ItemID.SNAKEBOSS_SCALE, -1);

	private final Card card;

	BossDropsCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.BOSS_DROPS, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
