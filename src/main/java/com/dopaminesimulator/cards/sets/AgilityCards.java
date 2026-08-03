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

public enum AgilityCards implements CardGroup
{
	DRAYNOR_VILLAGE_ROOFTOP_COURSE("Draynor Village Rooftop Course", Rarity.COMMON, ItemID.GRACE, -1),
	GNOME_STRONGHOLD_AGILITY_COURSE("Gnome Stronghold Agility Course", Rarity.COMMON, ItemID.SWAMP_TOAD, -1),
	GNOMEBALL_GAME("Gnomeball game", Rarity.COMMON, ItemID.BALL_GNOMEBALL_GAME, -1),
	LOW_LEVEL_AGILITY_ARENA_OBSTACLES("Low-level Agility Arena obstacles", Rarity.COMMON, ItemID.AGILITYARENA_TICKET, -1),
	SHAYZIEN_BASIC_COURSE("Shayzien Basic Course", Rarity.COMMON, ItemID.SHAYZIEN_HELM_1, -1),
	FALADOR_LOW_WALL_CLIMB("Falador low wall climb", Rarity.COMMON, ItemID.AGILITY_CLIMB, -1),
	CROSS_THE_RIVER_LUM_TO_AL_KHARID("Cross the River Lum to Al Kharid", Rarity.COMMON, ItemID.AGILITY_BALANCE, -1),
	CORSAIR_COVE_SHORTCUT("Corsair Cove shortcut", Rarity.COMMON, ItemID.AGILITY_JUMP, -1),
	ROPE_SWING_TO_MOSS_GIANT_ISLAND("Rope-swing to Moss Giant Island", Rarity.COMMON, ItemID.AGILITY_JUMP_AREA, -1),
	SCALE_FALADOR_WALL("Scale Falador wall", Rarity.COMMON, ItemID.AGILITY_CLIMB, -1),
	STEPPING_STONES_IN_KARAMJA_DUNGEON("Stepping stones in Karamja Dungeon", Rarity.COMMON, ItemID.AGILITY_JUMP_AREA, -1),
	JUMP_FENCE_SOUTH_OF_VARROCK("Jump fence south of Varrock", Rarity.COMMON, ItemID.AGILITY_JUMP, -1),
	SCALE_GOBLIN_VILLAGE_WALL("Scale Goblin village wall", Rarity.COMMON, ItemID.AGILITY_BALANCE, -1),
	CORSAIR_COVE_DUNGEON_SHORTCUT("Corsair Cove Dungeon shortcut", Rarity.COMMON, ItemID.AGILITY_JUMP, -1),
	MONKEY_BARS_UNDER_EDGEVILLE("Monkey bars under Edgeville", Rarity.COMMON, ItemID.AGILITY_SWING_AREA, -1),
	YANILLE_AGILITY_SHORTCUT("Yanille Agility shortcut", Rarity.COMMON, ItemID.AGILITY_CONTORTION, -1),
	KOUREND_CATACOMBS_AGILITY_SHORTCUT("Kourend Catacombs Agility shortcut", Rarity.COMMON, ItemID.AGILITY_CONTORTION, -1),
	SLAYER_TOWER_BANSHEE_SHORTCUT("Slayer Tower Banshee shortcut", Rarity.COMMON, ItemID.AGILITY_JUMP, -1),
	AL_KHARID_ROOFTOP_COURSE("Al Kharid Rooftop Course", Rarity.UNCOMMON, ItemID.GRACE, -1),
	COAL_TRUCK_LOG_BALANCE("Coal Truck log balance", Rarity.UNCOMMON, ItemID.AGILITY_BALANCE, -1),
	GRAND_EXCHANGE_AGILITY_SHORTCUT("Grand Exchange Agility shortcut", Rarity.UNCOMMON, ItemID.AGILITY_CONTORTION, -1),
	KARAMJA_CROSSING_SOUTH_OF_VOLCANO("Karamja crossing south of volcano", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	SCALE_YANILLE_WALL("Scale Yanille wall", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	ESCAPE_FROM_THE_WATER_OBELISK_ISLAND("Escape from the Water Obelisk island", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	SCALE_THE_OBSERVATORY_CLIFF("Scale the Observatory cliff", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	NEMUS_RETREAT_WALL_CLIMBS("Nemus Retreat wall climbs", Rarity.UNCOMMON, ItemID.AGILITY_CONTORTION, -1),
	BURGH_DE_ROTT_FENCE("Burgh de Rott fence", Rarity.UNCOMMON, ItemID.AGILITY_JUMP, -1),
	EAGLES_PEAK_AGILITY_SHORTCUT("Eagles' Peak Agility shortcut", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	WEREWOLF_SKULLBALL_GAME("Werewolf Skullball game", Rarity.UNCOMMON, ItemID.SKULL, -1),
	FALADOR_AGILITY_SHORTCUT("Falador Agility shortcut", Rarity.UNCOMMON, ItemID.AGILITY_CONTORTION, -1),
	KOUREND_CATACOMBS_PILLAR_JUMP_SHORTCUT("Kourend Catacombs pillar jump shortcut", Rarity.UNCOMMON, ItemID.AGILITY_BALANCE, -1),
	MOUNT_KARUULM_LOWER_CLIFFSIDE_CLIMB("Mount Karuulm lower cliffside climb", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	AGILITY_PYRAMID("Agility Pyramid", Rarity.UNCOMMON, ItemID.AGILITY_PYRAMID_GOLD_PYRAMID, -1),
	CORSAIR_COVE_RESOURCE_AREA_SHORTCUT("Corsair Cove Resource Area shortcut", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	PENGUIN_AGILITY_COURSE("Penguin Agility Course", Rarity.UNCOMMON, ItemID.PENG_SUIT_UNWOUND, -1),
	STEPPING_STONES_IN_SOUTH_EASTERN_KARAMJA("Stepping stones in south-eastern Karamja", Rarity.UNCOMMON, ItemID.AGILITY_JUMP_AREA, -1),
	VARROCK_ROOFTOP_COURSE("Varrock Rooftop Course", Rarity.UNCOMMON, ItemID.GRACE, -1),
	DRAYNOR_MANOR_STONES_TO_CHAMPIONS_GUILD("Draynor Manor stones to Champions' Guild", Rarity.UNCOMMON, ItemID.AGILITY_BALANCE, -1),
	SCALE_THE_CATHERBY_CLIFF("Scale the Catherby cliff", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	SHILO_VILLAGE_RIVER_CROSSING("Shilo Village river crossing", Rarity.UNCOMMON, ItemID.AGILITY_JUMP, -1),
	ARDOUGNE_LOG_BALANCE_SHORTCUT("Ardougne log balance shortcut", Rarity.UNCOMMON, ItemID.AGILITY_BALANCE, -1),
	NEMUS_RETREAT_UNDERGROUND_TUNNEL("Nemus Retreat underground tunnel", Rarity.UNCOMMON, ItemID.AGILITY_CONTORTION, -1),
	KOUREND_CATACOMBS_CONTORTION_SHORTCUT("Kourend Catacombs contortion shortcut", Rarity.UNCOMMON, ItemID.AGILITY_CONTORTION, -1),
	PIPE_SQUEEZE_IN_KARAMJA_DUNGEON("Pipe squeeze in Karamja Dungeon", Rarity.UNCOMMON, ItemID.AGILITY_CONTORTION_AREA, -1),
	BARBARIAN_OUTPOST_AGILITY_COURSE("Barbarian Outpost Agility Course", Rarity.UNCOMMON, ItemID.STEEL_BATTLEAXE, -1),
	VARROCK_PALACE_GARDEN_TRELLIS_SHORTCUT("Varrock Palace Garden trellis shortcut", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	NEMUS_RETREAT_STEPPING_STONES("Nemus Retreat stepping stones", Rarity.UNCOMMON, ItemID.AGILITY_JUMP, -1),
	GNOME_STRONGHOLD_SHORTCUT("Gnome Stronghold shortcut", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	AL_KHARID_MINING_PIT_CLIFFSIDE_SCRAMBLE("Al Kharid Mining pit cliffside scramble", Rarity.UNCOMMON, ItemID.AGILITY_CLIMB, -1),
	CANIFIS_ROOFTOP_COURSE("Canifis Rooftop Course", Rarity.RARE, ItemID.GRACE, -1),
	HOSIDIUS_LAKE_ISLE_JUMP("Hosidius lake isle jump", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	TLATI_RAINFOREST_LOG_BALANCE("Tlati Rainforest log balance", Rarity.RARE, ItemID.AGILITY_BALANCE, -1),
	AUBURNVALE_ROCK_SCRAMBLE("Auburnvale rock scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	TROLLHEIM_EASY_CLIFFSIDE_SCRAMBLE("Trollheim easy cliffside scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	DRAYNOR_NARROW_TUNNEL("Draynor narrow tunnel", Rarity.RARE, ItemID.AGILITY_CONTORTION, -1),
	DWARVEN_MINE_NARROW_CREVICE("Dwarven Mine narrow crevice", Rarity.RARE, ItemID.AGILITY_CONTORTION, -1),
	TROLLHEIM_MEDIUM_CLIFFSIDE_SCRAMBLE("Trollheim medium cliffside scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	FREMENNIK_SLAYER_DUNGEON_SPIKED_BLADES_JUMP("Fremennik Slayer Dungeon spiked blades jump", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	TLATI_RAINFOREST_CLIFFSIDE_SCRAMBLE("Tlati Rainforest cliffside scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	TROLLHEIM_ADVANCED_CLIFFSIDE_SCRAMBLE("Trollheim advanced cliffside scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	AUBURN_VALLEY_LOG_BALANCES("Auburn Valley log balances", Rarity.RARE, ItemID.AGILITY_BALANCE, -1),
	ELF_AREA_LOG_BALANCE("Elf area log balance", Rarity.RARE, ItemID.AGILITY_BALANCE_AREA, -1),
	HOSIDIUS_RIVER_JUMP("Hosidius river jump", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	PROUDSPIRE_LOWER_SCRAMBLE("Proudspire lower scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	SHAYZIEN_ADVANCED_COURSE("Shayzien Advanced Course", Rarity.RARE, ItemID.SHAYZIEN_HELM_5, -1),
	COSMIC_TEMPLE_MEDIUM_NARROW_WALKWAY("Cosmic Temple - medium narrow walkway", Rarity.RARE, ItemID.AGILITY_CONTORTION, -1),
	DEEP_WILDERNESS_NARROW_TUNNEL("Deep Wilderness - narrow tunnel", Rarity.RARE, ItemID.AGILITY_CONTORTION, -1),
	TONALI_CAVERN_SHORTCUTS("Tonali Cavern shortcuts", Rarity.RARE, ItemID.AGILITY_BALANCE, -1),
	RALOS_RISE_ROCK_CLIMB("Ralos' Rise rock climb", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	TROLLHEIM_HARD_CLIFFSIDE_SCRAMBLE("Trollheim hard cliffside scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	APE_ATOLL_AGILITY_COURSE("Ape Atoll Agility Course", Rarity.RARE, ItemID.MM_MONKEY_GREEGREE_FOR_SMALL_NINJA_MONKEY, -1),
	LOG_BALANCE_TO_FREMENNIK_PROVINCE("Log balance to Fremennik Province", Rarity.RARE, ItemID.AGILITY_BALANCE, -1),
	ARCEUUS_ESSENCE_MINE_BOULDER_LEAP("Arceuus essence mine boulder leap", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	CONTORTION_IN_YANILLE_DUNGEON_SMALL_ROOM("Contortion in Yanille Dungeon small room", Rarity.RARE, ItemID.AGILITY_CONTORTION_AREA, -1),
	COLOSSAL_WYRM_BASIC_COURSE("Colossal Wyrm Basic Course", Rarity.RARE, ItemID.VARLAMORE_WYRM_AGILITY_TERMITE, -1),
	CRYSTAL_BOWS("Crystal bows", Rarity.RARE, ItemID.CRYSTAL_BOW, -1),
	FALADOR_ROOFTOP_COURSE("Falador Rooftop Course", Rarity.RARE, ItemID.GRACE, -1),
	STEPPING_STONE_INTO_MORYTANIA_NEAR_THE_NATURE_GROTTO("Stepping stone into Morytania near the Nature Grotto", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	SUMMER_SHORE_ROCK_SCRAMBLE("Summer Shore rock scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	PIPE_FROM_EDGEVILLE_DUNGEON_TO_VARROCK_SEWERS("Pipe from Edgeville dungeon to Varrock Sewers", Rarity.RARE, ItemID.AGILITY_CONTORTION, -1),
	ANGLERS_RETREAT_STEPPING_STONE("Anglers' Retreat stepping stone", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	ARCEUUS_ESSENCE_MINE_EASTERN_SCRAMBLE("Arceuus essence mine eastern scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	GREAT_CONCH_EASTERN_PLATEAU_ROCK_SCRAMBLE_SOUTH_WEST_SIDE("Great Conch eastern plateau rock scramble (south-west side)", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	HALLOWED_SEPULCHRE_FLOOR_1("Hallowed Sepulchre (Floor 1)", Rarity.RARE, ItemID.HALLOWED_MARK_25, -1),
	WILDERNESS_COURSE("Wilderness Course", Rarity.RARE, ItemID.SKULL, -1),
	MOTHERLODE_MINE_DARK_TUNNEL("Motherlode Mine dark tunnel", Rarity.RARE, ItemID.AGILITY_CONTORTION, -1),
	WESTERN_ALDARIN_CLIFF_SCRAMBLE("Western Aldarin cliff scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	WYRMSCRAIG_CATHEDRAL_CLIFFSIDE_SCRAMBLE("Wyrmscraig Cathedral cliffside scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	CAPE_CONCH_ROCK_SCRAMBLE("Cape Conch rock scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	STEPPING_STONE_BY_MISCELLANIA_DOCKS("Stepping stone by Miscellania docks", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	BRIMHAVEN_DUNGEON_EASTERN_STEPPING_STONES("Brimhaven Dungeon eastern stepping stones", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	ROCK_CLIMB_IN_THE_TEMPLE_OF_THE_EYE("Rock climb in the Temple of the Eye", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	GREAT_CONCH_EASTERN_PLATEAU_ROCK_SCRAMBLE_NORTH_EAST_SIDE("Great Conch eastern plateau rock scramble (north-east side)", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	MONKEY_BARS_UNDER_YANILLE("Monkey bars under Yanille", Rarity.RARE, ItemID.AGILITY_SWING_AREA, -1),
	RELLEKKA_EAST_FENCE_SHORTCUT("Rellekka east fence shortcut", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	PORT_PHASMATYS_ECTOPOOL_SHORTCUT("Port Phasmatys ectopool shortcut", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	STEPPING_STONE_IN_THE_NORTH_OF_WYRMSCRAIG("Stepping stone in the north of Wyrmscraig", Rarity.RARE, ItemID.AGILITY_JUMP, -1),
	ELVEN_OVERPASS_EASY_CLIFFSIDE_SCRAMBLE("Elven overpass easy cliffside scramble", Rarity.RARE, ItemID.AGILITY_CLIMB, -1),
	CAVE_TO_THE_STALKER_DEN("Cave to the Stalker Den", Rarity.RARE, -1, SpriteID.Mapfunction.AGILITY_SHORT_CUT),
	ASGARNIAN_ICE_DUNGEON_FROZEN_THRONE_TUNNEL("Asgarnian Ice Dungeon frozen throne tunnel", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	ESTUARY_CROSSING_ON_MOS_LE_HARMLESS("Estuary crossing on Mos Le'Harmless", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	PILLARS_IN_THE_WINTERTODT_S_PRISON("Pillars in the Wintertodt's Prison", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	SEERS_VILLAGE_ROOFTOP_COURSE("Seers' Village Rooftop Course", Rarity.EPIC, ItemID.GRACE, -1),
	STEPPING_STONE_IN_THE_TOMBS_OF_AMASCUT("Stepping stone in the Tombs of Amascut", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	WEREWOLF_AGILITY_COURSE("Werewolf Agility Course", Rarity.EPIC, ItemID.WAA_STICK, -1),
	WILDERNESS_FROM_THE_GOD_WARS_DUNGEON_AREA_CLIMB("Wilderness from the God Wars Dungeon area climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	FREMENNIK_SLAYER_DUNGEON_NARROW_CREVICE("Fremennik Slayer Dungeon narrow crevice", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	GREAT_CONCH_STEPPING_STONE("Great Conch stepping stone", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	LAGUNA_AURORAE_STEPPING_STONES("Laguna Aurorae stepping stones", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	SLAYER_TOWER_MEDIUM_SPIKED_CHAIN_CLIMB("Slayer Tower medium spiked chain climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	VAMPYRIUM_ROCK_CLIMBS("Vampyrium Rock Climbs", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	COLOSSAL_WYRM_ADVANCED_COURSE("Colossal Wyrm Advanced Course", Rarity.EPIC, ItemID.VARLAMORE_WYRM_AGILITY_TERMITE, -1),
	HALLOWED_SEPULCHRE_FLOOR_2("Hallowed Sepulchre (Floor 2)", Rarity.EPIC, ItemID.HALLOWED_MARK_25, -1),
	HALLOWED_SEPULCHRE_GRAPPLE_SWING("Hallowed Sepulchre - Grapple swing", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	MOUNT_KARUULM_UPPER_CLIFFSIDE_CLIMB("Mount Karuulm upper cliffside climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	STEPPING_STONE_IN_THE_SOUTH_OF_WYRMSCRAIG("Stepping stone in the south of Wyrmscraig", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	STEPPING_STONES_IN_THE_NECROPOLIS("Stepping stones in the Necropolis", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	DARKMEYER_WALL_CLIMB("Darkmeyer wall climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	FORTHOS_DUNGEON_SPIKED_BLADES_JUMP("Forthos Dungeon spiked blades jump", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	TAVERLEY_DUNGEON_LESSER_DEMON_FENCE_SHORTCUT("Taverley dungeon lesser demon fence shortcut", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	ROPE_TO_THE_FOSSIL_ISLAND_VOLCANO("Rope to the Fossil Island volcano", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	STALKER_DEN_STEPPING_STONE("Stalker Den stepping stone", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	TROLLHEIM_WILDERNESS_ROUTE("Trollheim Wilderness route", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	VAMPYRIUM_FOREST_CRAWL("Vampyrium Forest Crawl", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	REVENANT_CAVE_JUMP_EASY("Revenant Cave jump (easy)", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	TEMPLE_ON_THE_SALVE_TO_MORYTANIA_SHORTCUT("Temple on the Salve to Morytania shortcut", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	COSMIC_TEMPLE_ADVANCED_NARROW_WALKWAY("Cosmic Temple advanced narrow walkway", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	LUMBRIDGE_SWAMP_TO_THE_DESERT("Lumbridge Swamp to the Desert", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	TAVERLEY_WALL_SHORTCUT("Taverley wall shortcut", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	HEROES_GUILD_TUNNEL("Heroes' Guild tunnel", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	YANILLE_DUNGEON_S_RUBBLE_CLIMB("Yanille Dungeon's rubble climb", Rarity.EPIC, ItemID.AGILITY_CLIMB_AREA, -1),
	ELVEN_OVERPASS_MEDIUM_CLIFFSIDE_SCRAMBLE("Elven overpass medium cliffside scramble", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	ICE_MOUNTAIN_WESTERN_SCRAMBLE("Ice Mountain western scramble", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	ARCEUUS_ESSENCE_MINE_NORTHERN_SCRAMBLE("Arceuus essence mine northern scramble", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	MAUSOLEUM_BRIDGE_JUMP("Mausoleum bridge jump", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	AL_KHARID_PALACE_SOUTHERN_WINDOW("Al Kharid Palace southern window", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	CAVES_SOUTH_OF_DORGESH_KAAN("Caves south of Dorgesh-Kaan", Rarity.EPIC, ItemID.BULLSEYE_LANTERN_LIT, -1),
	CROSS_CAVE_SOUTH_OF_DORGESH_KAAN("Cross cave south of Dorgesh-Kaan", Rarity.EPIC, ItemID.AGILITY_BALANCE, -1),
	FOSSIL_ISLAND_HARDWOOD_SHORTCUT("Fossil island hardwood shortcut", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	POLLNIVNEACH_ROOFTOP_COURSE("Pollnivneach Rooftop Course", Rarity.EPIC, ItemID.GRACE, -1),
	RING_OF_ENDURANCE("Ring of Endurance", Rarity.EPIC, ItemID.RING_OF_ENDURANCE, -1),
	TAVERLEY_DUNGEON_SHORTCUTS_TO_BLUE_DRAGONS("Taverley Dungeon shortcuts to blue dragons", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	GU_TANOTH_WALL_CLIMB("Gu'Tanoth wall climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	POLLNIVNEACH_STEPPING_STONE("Pollnivneach stepping stone", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	PROUDSPIRE_UPPER_SCRAMBLE("Proudspire upper scramble", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	SLAYER_TOWER_ADVANCED_SPIKED_CHAIN_CLIMB("Slayer Tower advanced spiked chain climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	BARROWS_WALL_JUMP("Barrows wall-jump", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	BASIC_ASGARNIAN_ICE_DUNGEON_WYVERN_TUNNEL("Basic Asgarnian Ice Dungeon wyvern tunnel", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	CHAOS_TEMPLE_STEPPING_STONE("Chaos temple Stepping Stone", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	HALLOWED_SEPULCHRE_FLOOR_3("Hallowed Sepulchre (Floor 3)", Rarity.EPIC, ItemID.HALLOWED_MARK_25, -1),
	STEPPING_STONE_TO_THE_PRIVATE_MINING_AREA_IN_WYRMSCRAIG("Stepping stone to the private mining area in Wyrmscraig", Rarity.EPIC, ItemID.AGILITY_JUMP_AREA, -1),
	STRONGHOLD_SLAYER_CAVE_NARROW_TUNNEL("Stronghold Slayer Cave narrow tunnel", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	ARCEUUS_ESSENCE_MINE_WESTERN_DESCENT("Arceuus essence mine western descent", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	CHASM_OF_FIRE_PLATFORMS("Chasm of Fire platforms", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	TROLL_STRONGHOLD_WALL_CLIMB("Troll Stronghold wall-climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	VAMPYRIUM_PILLAR_JUMPING("Vampyrium Pillar Jumping", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	FOSSIL_ISLAND_ZIPLINE("Fossil Island zipline", Rarity.EPIC, ItemID.AGILITY_SWING, -1),
	LAVA_DRAGON_ISLE_JUMP("Lava Dragon Isle jump", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	MEIYERDITCH_LABORATORY_TUNNELS("Meiyerditch Laboratory tunnels", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	MOKHAIOTL_PIT_JUMP("Mokhaiotl pit jump", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	PRIFDDINAS_AGILITY_COURSE("Prifddinas Agility Course", Rarity.EPIC, ItemID.PRIF_CRYSTAL_SHARD, -1),
	REVENANT_CAVE_JUMP_MEDIUM("Revenant Cave jump (medium)", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	DARKFROST_CLIFF_SCRAMBLE("Darkfrost cliff scramble", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	ISLAND_CROSSING_NEAR_ZUL_ANDRA("Island crossing near Zul-Andra", Rarity.EPIC, ItemID.AGILITY_JUMP, -1),
	WILDERNESS_SLAYER_DUNGEON_CREVICES("Wilderness Slayer Dungeon Crevices", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	IORWERTH_DUNGEON_NORTHERN_SHORTCUT("Iorwerth Dungeon northern shortcut", Rarity.EPIC, ItemID.AGILITY_CONTORTION, -1),
	VAMPYRIUM_ROCK_SLIDES("Vampyrium Rock Slides", Rarity.EPIC, ItemID.AGILITY_BALANCE_AREA, -1),
	KHARAZI_JUNGLE_VINE_CLIMB("Kharazi Jungle vine climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	SHILO_VILLAGE_ROCK_CLIMB("Shilo Village rock climb", Rarity.EPIC, ItemID.AGILITY_CLIMB, -1),
	ACCESS_THE_GOD_WARS_DUNGEON_AREA_VIA_THE_AGILITY_ROUTE("Access the God Wars Dungeon area via the Agility route", Rarity.EPIC, -1, SpriteID.Mapfunction.AGILITY_SHORT_CUT),
	ENTER_THE_BANDOS_AREA_OF_THE_GOD_WARS_DUNGEON("Enter the Bandos area of the God Wars Dungeon", Rarity.EPIC, -1, SpriteID.Mapfunction.AGILITY_SHORT_CUT),
	ENTER_THE_SARADOMIN_AREA_OF_THE_GOD_WARS_DUNGEON("Enter the Saradomin area of the God Wars Dungeon", Rarity.EPIC, -1, SpriteID.Mapfunction.AGILITY_SHORT_CUT),
	RELLEKKA_ROOFTOP_COURSE("Rellekka Rooftop Course", Rarity.LEGENDARY, ItemID.GRACE, -1),
	TAVERLEY_DUNGEON_SPIKED_BLADES_JUMP("Taverley Dungeon spiked blades jump", Rarity.LEGENDARY, ItemID.AGILITY_JUMP, -1),
	SLAYER_TOWER_IVY_CLIMB("Slayer Tower ivy climb", Rarity.LEGENDARY, ItemID.AGILITY_CLIMB, -1),
	WATERBIRTH_DUNGEON_CREVICE("Waterbirth Dungeon crevice", Rarity.LEGENDARY, ItemID.AGILITY_CONTORTION, -1),
	ADEPT_ASGARNIAN_ICE_DUNGEON_WYVERN_TUNNEL("Adept Asgarnian Ice Dungeon wyvern tunnel", Rarity.LEGENDARY, ItemID.AGILITY_JUMP, -1),
	HALLOWED_SEPULCHRE_FLOOR_4("Hallowed Sepulchre (Floor 4)", Rarity.LEGENDARY, ItemID.HALLOWED_MARK_25, -1),
	LAVA_MAZE_NORTHERN_JUMP("Lava Maze northern jump", Rarity.LEGENDARY, ItemID.AGILITY_JUMP, -1),
	CHASM_OF_FIRE_CHAIN("Chasm of Fire Chain", Rarity.LEGENDARY, ItemID.AGILITY_CLIMB, -1),
	GRIMSTONE_UNEVEN_STONE_LEDGES_CLIMB("Grimstone uneven stone ledges climb", Rarity.LEGENDARY, ItemID.AGILITY_CLIMB, -1),
	CRANDOR_ROCK_CLIMB("Crandor rock-climb", Rarity.LEGENDARY, ItemID.AGILITY_CONTORTION, -1),
	DEEPFIN_POINT_DUNGEON_CREVICE("Deepfin Point dungeon crevice", Rarity.LEGENDARY, ItemID.AGILITY_CONTORTION, -1),
	IORWERTH_DUNGEON_SOUTHERN_SHORTCUT("Iorwerth Dungeon southern shortcut", Rarity.LEGENDARY, ItemID.AGILITY_CONTORTION, -1),
	ELVEN_OVERPASS_ADVANCED_CLIFFSIDE_SCRAMBLE("Elven overpass advanced cliffside scramble", Rarity.LEGENDARY, ItemID.AGILITY_CLIMB, -1),
	WATERBIRTH_ISLAND_ROCK_CLIMB("Waterbirth Island rock-climb", Rarity.LEGENDARY, ItemID.AGILITY_CLIMB, -1),
	DARKMEYER_WALL_JUMP("Darkmeyer wall jump", Rarity.LEGENDARY, ItemID.AGILITY_JUMP, -1),
	KALPHITE_LAIR_SHORTCUT("Kalphite Lair shortcut", Rarity.LEGENDARY, ItemID.AGILITY_CONTORTION, -1),
	BRIMHAVEN_DUNGEON_VINE_TO_BABY_GREEN_DRAGONS("Brimhaven Dungeon vine to baby green dragons", Rarity.LEGENDARY, ItemID.AGILITY_CLIMB, -1),
	KARUULM_DUNGEON_PIPE("Karuulm Dungeon pipe", Rarity.LEGENDARY, ItemID.AGILITY_CONTORTION, -1),
	REVENANT_CAVE_JUMP_HARD("Revenant Cave jump (hard)", Rarity.LEGENDARY, ItemID.AGILITY_JUMP, -1),
	ARDOUGNE_ROOFTOP_COURSE("Ardougne Rooftop Course", Rarity.LEGENDARY, ItemID.GRACE, -1),
	VIYELDI_CAVES_CLIFF_CLIMB("Viyeldi Caves cliff climb", Rarity.LEGENDARY, ItemID.AGILITY_CLIMB, -1),
	HALLOWED_SEPULCHRE_FLOOR_5("Hallowed Sepulchre (Floor 5)", Rarity.LEGENDARY, ItemID.HALLOWED_MARK_25, -1),
	MEIYERDITCH_LABORATORIES_ADVANCED_SHORTCUT("Meiyerditch Laboratories advanced shortcut", Rarity.LEGENDARY, ItemID.AGILITY_CONTORTION, -1),
	VIYELDI_CAVES_CREVICE("Viyeldi caves crevice", Rarity.LEGENDARY, ItemID.AGILITY_CONTORTION, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_AGILITY, -1);

	private final Card card;

	AgilityCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.AGILITY, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
