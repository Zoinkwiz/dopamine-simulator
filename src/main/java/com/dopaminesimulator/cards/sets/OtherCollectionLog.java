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

public enum OtherCollectionLog implements CardGroup
{

	// Aerial Fishing
	GOLDEN_TENCH("Golden tench", Rarity.RARE, ItemID.GOLDEN_TENCH, -1),
	PEARL_FISHING_ROD("Pearl fishing rod", Rarity.UNCOMMON, ItemID.FISHINGROD_PEARL, -1),
	PEARL_FLY_FISHING_ROD("Pearl fly fishing rod", Rarity.UNCOMMON, ItemID.FISHINGROD_PEARL_FLY, -1),
	PEARL_BARBARIAN_ROD("Pearl barbarian rod", Rarity.UNCOMMON, ItemID.FISHINGROD_PEARL_BRUT, -1),
	FISH_SACK("Fish sack", Rarity.RARE, ItemID.FISH_SACK, -1),
	ANGLER_HAT("Angler hat", Rarity.RARE, ItemID.TRAWLER_REWARD_HAT, -1),
	ANGLER_TOP("Angler top", Rarity.RARE, ItemID.TRAWLER_REWARD_TOP, -1),
	ANGLER_WADERS("Angler waders", Rarity.RARE, ItemID.TRAWLER_REWARD_LEGS, -1),
	ANGLER_BOOTS("Angler boots", Rarity.RARE, ItemID.TRAWLER_REWARD_BOOTS, -1),

	// Skipping All Pets section as every pet is already on their table

	// Boat Paints
	BARRACUDA_PAINT("Barracuda paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_BARRACUDA, -1),
	SHARK_PAINT("Shark paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_SHARK, -1),
	INKY_PAINT("Inky paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_INKY, -1),
	ANGLERS_PAINT("Angler's paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_ANGLERS, -1),
	SALVORS_PAINT("Salvor's paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_SALVORS, -1),
	ARMADYLEAN_PAINT("Armadylean paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_ARMADYLEAN, -1),
	ZAMORAKIAN_PAINT("Zamorakian paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_ZAMORAKIAN, -1),
	GUTHIXIAN_PAINT("Guthixian paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_GUTHIXIAN, -1),
	SARADOMINIST_PAINT("Saradominist paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_SARADOMINIST, -1),
	MERCHANTS_PAINT("Merchant's paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_MERCHANTS, -1),
	SANDY_PAINT("Sandy paint", Rarity.UNCOMMON, ItemID.SAILING_PAINT_SANDY, -1),

	// Camdozaal
	BARRONITE_MACE("Barronite mace", Rarity.RARE, ItemID.BARRONITE_MACE, -1),
	BARRONITE_HEAD("Barronite head", Rarity.UNCOMMON, ItemID.BARRONITE_MACE_1, -1),
	BARRONITE_HANDLE("Barronite handle", Rarity.UNCOMMON, ItemID.BARRONITE_MACE_2, -1),
	BARRONITE_GUARD("Barronite guard", Rarity.UNCOMMON, ItemID.BARRONITE_MACE_3, -1),
	ANCIENT_GLOBE("Ancient globe", Rarity.UNCOMMON, ItemID.CAMDOZAAL_RELIC_1, -1),
	ANCIENT_LEDGER("Ancient ledger", Rarity.UNCOMMON, ItemID.CAMDOZAAL_RELIC_2, -1),
	ANCIENT_ASTROSCOPE("Ancient astroscope", Rarity.UNCOMMON, ItemID.CAMDOZAAL_RELIC_3, -1),
	ANCIENT_TREATISE("Ancient treatise", Rarity.UNCOMMON, ItemID.CAMDOZAAL_RELIC_4, -1),
	ANCIENT_CARCANET("Ancient carcanet", Rarity.UNCOMMON, ItemID.CAMDOZAAL_RELIC_5, -1),
	IMCANDO_HAMMER("Imcando hammer", Rarity.RARE, ItemID.IMCANDO_HAMMER, -1),

	// Champion's Challenge
	EARTH_WARRIOR_CHAMPION_SCROLL("Earth warrior champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_EARTHWARRIOR, -1),
	GHOUL_CHAMPION_SCROLL("Ghoul champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_GHOUL, -1),
	GIANT_CHAMPION_SCROLL("Giant champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_GIANT, -1),
	GOBLIN_CHAMPION_SCROLL("Goblin champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_GOBLIN, -1),
	HOBGOBLIN_CHAMPION_SCROLL("Hobgoblin champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_HOBGOBLIN, -1),
	IMP_CHAMPION_SCROLL("Imp champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_IMP, -1),
	JOGRE_CHAMPION_SCROLL("Jogre champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_JOGRE, -1),
	LESSER_DEMON_CHAMPION_SCROLL("Lesser demon champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_LESSERDEMON, -1),
	SKELETON_CHAMPION_SCROLL("Skeleton champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_SKELETON, -1),
	ZOMBIE_CHAMPION_SCROLL("Zombie champion scroll", Rarity.RARE, ItemID.CHAMPIONS_CHALLENGE_ZOMBIE, -1),
	CHAMPIONS_CAPE("Champion's cape", Rarity.EPIC, ItemID.CHAMPION_CAPE, -1),

	// Chompy Bird Hunting
	CHOMPY_CHICK("Chompy chick", Rarity.EPIC, ItemID.CHOMPYBIRD_PET, -1),
	// Tier 1
	OGRE_BOWMAN_HAT("Ogre bowman", Rarity.COMMON, ItemID.CBHAT1, -1),
	BOWMAN_HAT("Bowman", Rarity.COMMON, ItemID.CBHAT2, -1),
	OGRE_YEOMAN_HAT("Ogre yeoman", Rarity.COMMON, ItemID.CBHAT3, -1),
	YEOMAN_HAT("Yeoman", Rarity.COMMON, ItemID.CBHAT4, -1),
	OGRE_MARKSMAN_HAT("Ogre marksman", Rarity.COMMON, ItemID.CBHAT5, -1),
	MARKSMAN_HAT("Marksman", Rarity.COMMON, ItemID.CBHAT6, -1),
	// Tier 2
	OGRE_WOODSMAN_HAT("Ogre woodsman", Rarity.UNCOMMON, ItemID.CBHAT7, -1),
	WOODSMAN_HAT("Woodsman", Rarity.UNCOMMON, ItemID.CBHAT8, -1),
	OGRE_FORESTER_HAT("Ogre forester", Rarity.UNCOMMON, ItemID.CBHAT9, -1),
	FORESTER_HAT("Forester", Rarity.UNCOMMON, ItemID.CBHAT10, -1),
	OGRE_BOWMASTER_HAT("Ogre bowmaster", Rarity.UNCOMMON, ItemID.CBHAT11, -1),
	BOWMASTER_HAT("Bowmaster", Rarity.UNCOMMON, ItemID.CBHAT12, -1),
	// Tier 3
	OGRE_EXPERT_HAT("Ogre expert", Rarity.RARE, ItemID.CBHAT13, -1),
	EXPERT_HAT("Expert", Rarity.RARE, ItemID.CBHAT14, -1),
	OGRE_DRAGON_ARCHER_HAT("Ogre dragon archer", Rarity.RARE, ItemID.CBHAT15, -1),
	DRAGON_ARCHER_HAT("Dragon archer", Rarity.RARE, ItemID.CBHAT16, -1),
	EXPERT_OGRE_DRAGON_ARCHER_HAT("Expert ogre dragon archer", Rarity.RARE, ItemID.CBHAT17, -1),
	EXPERT_DRAGON_ARCHER_HAT("Expert dragon archer", Rarity.RARE, ItemID.CBHAT18, -1),

	// Colossal Wyrm Agility
	COLOSSAL_WYRM_TELEPORT_SCROLL("Colossal wyrm teleport scroll", Rarity.COMMON, ItemID.TELEPORTSCROLL_COLOSSAL_WYRM, -1),
	CALCIFIED_ACORN("Calcified acorn", Rarity.RARE, ItemID.CALCIFIED_ACORN, -1),
	GRACEFUL_HOOD_WYRM("Varlamore graceful hood", Rarity.UNCOMMON, ItemID.GRACEFUL_HOOD_WYRM, -1),
	GRACEFUL_CAPE_WYRM("Varlamore graceful cape", Rarity.UNCOMMON, ItemID.GRACEFUL_CAPE_WYRM, -1),
	GRACEFUL_TOP_WYRM("Varlamore graceful top", Rarity.UNCOMMON, ItemID.GRACEFUL_TOP_WYRM, -1),
	GRACEFUL_LEGS_WYRM("Varlamore graceful legs", Rarity.UNCOMMON, ItemID.GRACEFUL_LEGS_WYRM, -1),
	GRACEFUL_GLOVES_WYRM("Varlamore graceful gloves", Rarity.UNCOMMON, ItemID.GRACEFUL_GLOVES_WYRM, -1),
	GRACEFUL_BOOTS_WYRM("Varlamore graceful boots", Rarity.UNCOMMON, ItemID.GRACEFUL_BOOTS_WYRM, -1),

	// Creature Creation
	TEA_FLASK("Tea flask", Rarity.UNCOMMON, ItemID.TOL_TEA, -1),
	PLAIN_SATCHEL("Plain satchel", Rarity.UNCOMMON, ItemID.TOL_PLAIN_SACK, -1),
	GREEN_SATCHEL("Green satchel", Rarity.UNCOMMON, ItemID.TOL_GREEN_SACK, -1),
	RED_SATCHEL("Red satchel", Rarity.UNCOMMON, ItemID.TOL_RED_SACK, -1),
	BLACK_SATCHEL("Black satchel", Rarity.UNCOMMON, ItemID.TOL_BLACK_SACK, -1),
	GOLD_SATCHEL("Gold satchel", Rarity.UNCOMMON, ItemID.TOL_GOLD_SACK, -1),
	RUNE_SATCHEL("Rune satchel", Rarity.UNCOMMON, ItemID.TOL_RUNE_SACK, -1),

	// Cyclopes
	BRONZE_DEFENDER("Bronze defender", Rarity.COMMON, ItemID.BRONZE_PARRYINGDAGGER, -1),
	IRON_DEFENDER("Iron defender", Rarity.COMMON, ItemID.IRON_PARRYINGDAGGER, -1),
	STEEL_DEFENDER("Steel defender", Rarity.COMMON, ItemID.STEEL_PARRYINGDAGGER, -1),
	BLACK_DEFENDER("Black defender", Rarity.UNCOMMON, ItemID.BLACK_PARRYINGDAGGER, -1),
	MITHRIL_DEFENDER("Mithril defender", Rarity.UNCOMMON, ItemID.MITHRIL_PARRYINGDAGGER, -1),
	ADAMANT_DEFENDER("Adamant defender", Rarity.UNCOMMON, ItemID.ADAMANT_PARRYINGDAGGER, -1),
	RUNE_DEFENDER("Rune defender", Rarity.RARE, ItemID.RUNE_PARRYINGDAGGER, -1),
	DRAGON_DEFENDER("Dragon defender", Rarity.RARE, ItemID.DRAGON_PARRYINGDAGGER, -1),

	// Elder Chaos Druids
	ELDER_CHAOS_TOP("Elder chaos top", Rarity.RARE, ItemID.ELDERCHAOS_TOP, -1),
	ELDER_CHAOS_ROBE("Elder chaos robe", Rarity.RARE, ItemID.ELDERCHAOS_BOTTOM, -1),
	ELDER_CHAOS_HOOD("Elder chaos hood", Rarity.RARE, ItemID.ELDERCHAOS_HOOD, -1),

	// Forestry
	FOX_WHISTLE("Fox whistle", Rarity.EPIC, ItemID.FORESTRY_FOX_PET_WHISTLE, -1),
	GOLDEN_PHEASANT_EGG("Golden pheasant egg", Rarity.EPIC, ItemID.FORESTRY_PHEASANT_PET_EGG, -1),
	FORESTRY_HAT("Forestry hat", Rarity.UNCOMMON, ItemID.FORESTRY_LUMBERJACK_HAT, -1),
	FORESTRY_TOP("Forestry top", Rarity.UNCOMMON, ItemID.FORESTRY_LUMBERJACK_TOP, -1),
	FORESTRY_LEGS("Forestry legs", Rarity.UNCOMMON, ItemID.FORESTRY_LUMBERJACK_LEGS, -1),
	FORESTRY_BOOTS("Forestry boots", Rarity.UNCOMMON, ItemID.FORESTRY_LUMBERJACK_BOOTS, -1),
	TWITCHERS_GLOVES("Twitcher's gloves", Rarity.RARE, ItemID.FORESTRY_GLOVES, -1),
	FUNKY_SHAPED_LOG("Funky shaped log", Rarity.RARE, ItemID.FORESTRY_FUNKY_SHAPED_LOG, -1),
	LOG_BASKET("Log basket", Rarity.RARE, ItemID.LOG_BASKET_CLOSED, -1),
	LOG_BRACE("Log brace", Rarity.RARE, ItemID.FORESTRY_LOG_BRACE, -1),
	CLOTHES_POUCH_BLUEPRINT("Clothes pouch blueprint", Rarity.RARE, ItemID.FORESTRY_CLOTHES_POUCH_BLUEPRINT, -1),
	CAPE_POUCH("Cape pouch", Rarity.UNCOMMON, ItemID.FORESTRY_CAPE_POUCH, -1),
	FELLING_AXE_HANDLE("Felling axe handle", Rarity.RARE, ItemID.FORESTRY_2H_AXE_HANDLE, -1),
	PHEASANT_HAT("Pheasant hat", Rarity.RARE, ItemID.FORESTRY_PHEASANT_HAT, -1),
	PHEASANT_LEGS("Pheasant legs", Rarity.RARE, ItemID.FORESTRY_PHEASANT_LEGS, -1),
	PHEASANT_BOOTS("Pheasant boots", Rarity.RARE, ItemID.FORESTRY_PHEASANT_BOOTS, -1),
	PHEASANT_CAPE("Pheasant cape", Rarity.RARE, ItemID.FORESTRY_PHEASANT_CAPE, -1),
	PETAL_GARLAND("Petal garland", Rarity.RARE, ItemID.GATHERING_EVENT_ENCHANTED_RITUAL_GARLAND, -1),
	STURDY_BEEHIVE_PARTS("Sturdy beehive parts", Rarity.UNCOMMON, ItemID.FORESTRY_POH_BEEHIVE_PART, -1),

	// Fossil Island Notes
	SCRIBBLED_NOTE("Scribbled note", Rarity.COMMON, ItemID.FOSSIL_NOTE1, -1),
	PARTIAL_NOTE("Partial note", Rarity.COMMON, ItemID.FOSSIL_NOTE2, -1),
	ANCIENT_NOTE("Ancient note", Rarity.UNCOMMON, ItemID.FOSSIL_NOTE3, -1),
	ANCIENT_WRITINGS("Ancient writings", Rarity.UNCOMMON, ItemID.FOSSIL_NOTE4, -1),
	EXPERIMENTAL_NOTE("Experimental note", Rarity.UNCOMMON, ItemID.FOSSIL_NOTE5, -1),
	PARAGRAPH_OF_TEXT("Paragraph of text", Rarity.UNCOMMON, ItemID.FOSSIL_NOTE6, -1),
	MUSTY_SMELLING_NOTE("Musty smelling note", Rarity.RARE, ItemID.FOSSIL_NOTE7, -1),
	HASTILY_SCRAWLED_NOTE("Hastily scrawled note", Rarity.RARE, ItemID.FOSSIL_NOTE8, -1),
	OLD_WRITING("Old writing", Rarity.RARE, ItemID.FOSSIL_NOTE9, -1),
	SHORT_NOTE("Short note", Rarity.RARE, ItemID.FOSSIL_NOTE10, -1),

	// Glough's Experiments
	ZENYTE_SHARD("Zenyte shard", Rarity.RARE, ItemID.ZENYTE_SHARD, -1),
	LIGHT_FRAME("Light frame", Rarity.UNCOMMON, ItemID.BALLISTA_FRAME_LIGHT, -1),
	HEAVY_FRAME("Heavy frame", Rarity.UNCOMMON, ItemID.BALLISTA_FRAME_HEAVY, -1),
	BALLISTA_LIMBS("Ballista limbs", Rarity.UNCOMMON, ItemID.BALLISTA_LIMBS, -1),
	MONKEY_TAIL("Monkey tail", Rarity.UNCOMMON, ItemID.BALLISTA_ROPE, -1),
	BALLISTA_SPRING("Ballista spring", Rarity.UNCOMMON, ItemID.BALLISTA_SPRING, -1),

	// Hunter Guild
	QUETZIN("Quetzin", Rarity.EPIC, ItemID.QUETZALPET, -1),
	HUNTSMANS_KIT("Huntsman's kit", Rarity.RARE, ItemID.HUNTSMANS_KIT, -1),
	GUILD_HUNTER_HEADWEAR("Guild hunter headwear", Rarity.UNCOMMON, ItemID.HG_HUNTER_HOOD, -1),
	GUILD_HUNTER_TOP("Guild hunter top", Rarity.UNCOMMON, ItemID.HG_HUNTER_TOP, -1),
	GUILD_HUNTER_LEGS("Guild hunter legs", Rarity.UNCOMMON, ItemID.HG_HUNTER_LEGS, -1),
	GUILD_HUNTER_BOOTS("Guild hunter boots", Rarity.UNCOMMON, ItemID.HG_HUNTER_BOOTS, -1),

	// Lost Schematics
	SALVAGING_STATION_SCHEMATIC("Salvaging station schematic", Rarity.UNCOMMON, ItemID.LOST_SCHEMATIC_SALVAGING_STATION, -1), // 42 Sailing
	GALE_CATCHER_SCHEMATIC("Gale catcher schematic", Rarity.UNCOMMON, ItemID.LOST_SCHEMATIC_GALE_CATCHER, -1), // 51 Sailing
	ETERNAL_BRAZIER_SCHEMATIC("Eternal brazier schematic", Rarity.RARE, ItemID.LOST_SCHEMATIC_ETERNAL_BRAZIER, -1), // 76 Sailing
	ROSEWOOD_CARGO_HOLD_SCHEMATIC("Rosewood cargo hold schematic", Rarity.UNCOMMON, ItemID.LOST_SCHEMATIC_ROSEWOOD_CARGOHOLD, -1), // 64 Sailing
	ROSEWOOD_HULL_SCHEMATIC("Rosewood hull schematic", Rarity.UNCOMMON, ItemID.LOST_SCHEMATIC_ROSEWOOD_HULL, -1), // 56 Sailing
	ROSEWOOD_COTTON_SAILS_SCHEMATIC("Rosewood & cotton sails schematic", Rarity.EPIC, ItemID.LOST_SCHEMATIC_ROSEWOOD_SAIL, -1), // 81 Sailing
	DRAGON_TILLER_SCHEMATIC("Dragon helm schematic", Rarity.RARE, ItemID.LOST_SCHEMATIC_DRAGON_TILLER, -1), // 72 Sailing
	DRAGON_KEEL_SCHEMATIC("Dragon keel schematic", Rarity.EPIC, ItemID.LOST_SCHEMATIC_DRAGON_KEEL, -1), // 87 Sailing
	DRAGON_SALVAGING_HOOK_SCHEMATIC("Dragon salvaging hook schematic", Rarity.RARE, ItemID.LOST_SCHEMATIC_DRAGON_SALVAGING_HOOK, -1), // 73 Sailing
	DRAGON_CANNON_SCHEMATIC("Dragon cannon schematic", Rarity.RARE, ItemID.LOST_SCHEMATIC_DRAGON_CANNON, -1), // 76 Sailing
	BALLISTIC_ATTRACTOR_SCHEMATIC("Ballistic attractor schematic", Rarity.UNCOMMON, ItemID.LOST_SCHEMATIC_BALLISTIC_ATTRACTOR, -1), // 50 Sailing to build
	BOSUNS_WORKBENCH_SCHEMATIC("Bosun's workbench schematic", Rarity.UNCOMMON, ItemID.LOST_SCHEMATIC_BOSUNS_WORKBENCH, -1), // 52 Sailing Red Reef Quest

	// Monkey Backpacks
	KARAMJAN_MONKEY("Karamjan monkey", Rarity.UNCOMMON, ItemID.MM2_MONKEY_KARAMJA, -1),
	KRUK_JR("Kruk jr", Rarity.RARE, ItemID.MM2_MONKEY_KRUK, -1),
	MANIACAL_MONKEY("Maniacal monkey", Rarity.RARE, ItemID.MM2_MONKEY_MANIACAL, -1),
	PRINCELY_MONKEY("Princely monkey", Rarity.EPIC, ItemID.MM2_MONKEY_AWOWOGEI, -1),
	SKELETON_MONKEY("Skeleton monkey", Rarity.RARE, ItemID.MM2_MONKEY_SKELETON, -1),
	ZOMBIE_MONKEY("Zombie monkey", Rarity.UNCOMMON, ItemID.MM2_MONKEY_ZOMBIE, -1),

	// Motherlode Mine
	COAL_BAG("Coal bag", Rarity.RARE, ItemID.COAL_BAG_DUMMY, -1),
	GEM_BAG("Gem bag", Rarity.RARE, ItemID.GEM_BAG_DUMMY, -1),
	PROSPECTOR_HELMET("Prospector helmet", Rarity.RARE, ItemID.MOTHERLODE_REWARD_HAT, -1),
	PROSPECTOR_JACKET("Prospector jacket", Rarity.RARE, ItemID.MOTHERLODE_REWARD_TOP, -1),
	PROSPECTOR_LEGS("Prospector legs", Rarity.RARE, ItemID.MOTHERLODE_REWARD_LEGS, -1),
	PROSPECTOR_BOOTS("Prospector boots", Rarity.RARE, ItemID.MOTHERLODE_REWARD_BOOTS, -1),

	// My Notes
	ANCIENT_PAGE_1("Ancient page 1", Rarity.COMMON, ItemID.BRUT_DOCUMENT_0, -1),
	ANCIENT_PAGE_2("Ancient page 2", Rarity.COMMON, ItemID.BRUT_DOCUMENT_1, -1),
	ANCIENT_PAGE_3("Ancient page 3", Rarity.COMMON, ItemID.BRUT_DOCUMENT_2, -1),
	ANCIENT_PAGE_4("Ancient page 4", Rarity.COMMON, ItemID.BRUT_DOCUMENT_3, -1),
	ANCIENT_PAGE_5("Ancient page 5", Rarity.COMMON, ItemID.BRUT_DOCUMENT_4, -1),
	ANCIENT_PAGE_6("Ancient page 6", Rarity.COMMON, ItemID.BRUT_DOCUMENT_5, -1),
	ANCIENT_PAGE_7("Ancient page 7", Rarity.COMMON, ItemID.BRUT_DOCUMENT_6, -1),
	ANCIENT_PAGE_8("Ancient page 8", Rarity.COMMON, ItemID.BRUT_DOCUMENT_7, -1),
	ANCIENT_PAGE_9("Ancient page 9", Rarity.COMMON, ItemID.BRUT_DOCUMENT_8, -1),
	ANCIENT_PAGE_10("Ancient page 10", Rarity.COMMON, ItemID.BRUT_DOCUMENT_9, -1),
	ANCIENT_PAGE_11("Ancient page 11", Rarity.COMMON, ItemID.BRUT_DOCUMENT_10, -1),
	ANCIENT_PAGE_12("Ancient page 12", Rarity.COMMON, ItemID.BRUT_DOCUMENT_11, -1),
	ANCIENT_PAGE_13("Ancient page 13", Rarity.COMMON, ItemID.BRUT_DOCUMENT_12, -1),
	ANCIENT_PAGE_14("Ancient page 14", Rarity.COMMON, ItemID.BRUT_DOCUMENT_13, -1),
	ANCIENT_PAGE_15("Ancient page 15", Rarity.COMMON, ItemID.BRUT_DOCUMENT_14, -1),
	ANCIENT_PAGE_16("Ancient page 16", Rarity.COMMON, ItemID.BRUT_DOCUMENT_15, -1),
	ANCIENT_PAGE_17("Ancient page 17", Rarity.COMMON, ItemID.BRUT_DOCUMENT_16, -1),
	ANCIENT_PAGE_18("Ancient page 18", Rarity.COMMON, ItemID.BRUT_DOCUMENT_17, -1),
	ANCIENT_PAGE_19("Ancient page 19", Rarity.COMMON, ItemID.BRUT_DOCUMENT_18, -1),
	ANCIENT_PAGE_20("Ancient page 20", Rarity.COMMON, ItemID.BRUT_DOCUMENT_19, -1),
	ANCIENT_PAGE_21("Ancient page 21", Rarity.COMMON, ItemID.BRUT_DOCUMENT_20, -1),
	ANCIENT_PAGE_22("Ancient page 22", Rarity.COMMON, ItemID.BRUT_DOCUMENT_21, -1),
	ANCIENT_PAGE_23("Ancient page 23", Rarity.COMMON, ItemID.BRUT_DOCUMENT_22, -1),
	ANCIENT_PAGE_24("Ancient page 24", Rarity.COMMON, ItemID.BRUT_DOCUMENT_23, -1),
	ANCIENT_PAGE_25("Ancient page 25", Rarity.COMMON, ItemID.BRUT_DOCUMENT_24, -1),
	ANCIENT_PAGE_26("Ancient page 26", Rarity.COMMON, ItemID.BRUT_DOCUMENT_25, -1),

	// Ocean Encounters
	TINY_PEARL("Tiny pearl", Rarity.COMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_500, -1),
	SMALL_PEARL("Small pearl", Rarity.COMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_1000, -1),
	SHINY_PEARL("Shiny pearl", Rarity.COMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_5000, -1),
	BRIGHT_PEARL("Bright pearl", Rarity.UNCOMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_10000, -1),
	BIG_PEARL("Big pearl", Rarity.UNCOMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_25000, -1),
	HUGE_PEARL("Huge pearl", Rarity.UNCOMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_50000, -1),
	ENORMOUS_PEARL("Enormous pearl", Rarity.UNCOMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_100000, -1),
	SHIMMERING_PEARL("Shimmering pearl", Rarity.UNCOMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_250000, -1),
	GLISTENING_PEARL("Glistening pearl", Rarity.UNCOMMON, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_500000, -1),
	BRILLIANT_PEARL("Brilliant pearl", Rarity.RARE, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_1000000, -1),
	RADIANT_PEARL("Radiant pearl", Rarity.RARE, ItemID.SAILING_CHANCE_ENCOUNTERS_CLAM_PEARL_2500000, -1),

	// Random Events
	CAMO_TOP("Camo top", Rarity.UNCOMMON, ItemID.DRILL_TOP, -1),
	CAMO_BOTTOMS("Camo bottoms", Rarity.UNCOMMON, ItemID.DRILL_BOTTOMS, -1),
	CAMO_HELMET("Camo helmet", Rarity.UNCOMMON, ItemID.DRILL_HELM, -1),
	LEDERHOSEN_TOP("Lederhosen top", Rarity.UNCOMMON, ItemID.LADERHOSEN_TOP, -1),
	LEDERHOSEN_SHORTS("Lederhosen shorts", Rarity.UNCOMMON, ItemID.LADERHOSEN_LEGS, -1),
	LEDERHOSEN_HAT("Lederhosen hat", Rarity.UNCOMMON, ItemID.LADERHOSEN_HAT, -1),
	ZOMBIE_SHIRT("Zombie shirt", Rarity.UNCOMMON, ItemID.MACRO_DIGGER_SHIRT, -1),
	ZOMBIE_TROUSERS("Zombie trousers", Rarity.UNCOMMON, ItemID.MACRO_DIGGER_LEGS, -1),
	ZOMBIE_MASK("Zombie mask", Rarity.UNCOMMON, ItemID.MACRO_DIGGER_MASK, -1),
	ZOMBIE_GLOVES("Zombie gloves", Rarity.UNCOMMON, ItemID.MACRO_DIGGER_GLOVES, -1),
	ZOMBIE_BOOTS("Zombie boots", Rarity.UNCOMMON, ItemID.MACRO_DIGGER_BOOTS, -1),
	MIME_MASK("Mime mask", Rarity.UNCOMMON, ItemID.MACRO_MIME_MASK, -1),
	MIME_TOP("Mime top", Rarity.UNCOMMON, ItemID.MACRO_MIME_TOP, -1),
	MIME_LEGS("Mime legs", Rarity.UNCOMMON, ItemID.MACRO_MIME_LEGS, -1),
	MIME_GLOVES("Mime gloves", Rarity.UNCOMMON, ItemID.MACRO_MIME_GLOVES, -1),
	MIME_BOOTS("Mime boots", Rarity.UNCOMMON, ItemID.MACRO_MIME_BOOTS, -1),
	FROG_TOKEN("Frog token", Rarity.COMMON, ItemID.MACRO_FROG_TOKEN, -1),
	STALE_BAGUETTE("Stale baguette", Rarity.LEGENDARY, ItemID.STALE_BAGUETTE, -1),
	BEEKEEPERS_HAT("Beekeeper's hat, bzzzzz", Rarity.UNCOMMON, ItemID.BEEKEEPER_HAT, -1),
	BEEKEEPERS_TOP("Beekeeper's top, bzzzzz", Rarity.UNCOMMON, ItemID.BEEKEEPER_TOP, -1),
	BEEKEEPERS_LEGS("Beekeeper's legs, bzzzzz", Rarity.UNCOMMON, ItemID.BEEKEEPER_LEGS, -1),
	BEEKEEPERS_GLOVES("Beekeeper's gloves, bzzzzz", Rarity.UNCOMMON, ItemID.BEEKEEPER_GLOVES, -1),
	BEEKEEPERS_BOOTS("Beekeeper's boots, bzzzzz", Rarity.UNCOMMON, ItemID.BEEKEEPER_BOOTS, -1),

	// Revenants
	VIGGORAS_CHAINMACE("Viggora's chainmace", Rarity.EPIC, ItemID.WILD_CAVE_CHAINMACE_UNCHARGED, -1),
	CRAWS_BOW("Craw's bow", Rarity.EPIC, ItemID.WILD_CAVE_BOW_UNCHARGED, -1),
	THAMMARONS_SCEPTRE("Thammaron's sceptre", Rarity.EPIC, ItemID.WILD_CAVE_SCEPTRE_UNCHARGED, -1),
	AMULET_OF_AVARICE("Amulet of avarice", Rarity.RARE, ItemID.WILD_CAVE_AMULET, -1),
	BRACELET_OF_ETHEREUM("Bracelet of ethereum (uncharged)", Rarity.UNCOMMON, ItemID.WILD_CAVE_BRACELET_UNCHARGED, -1),
	ANCIENT_CRYSTAL("Ancient crystal", Rarity.RARE, ItemID.WILD_CAVE_OBELISK_CRYSTAL, -1),
	ANCIENT_RELIC("Ancient relic", Rarity.RARE, ItemID.WILD_CAVE_ARTIFACT_16000, -1),
	ANCIENT_EFFIGY("Ancient effigy", Rarity.RARE, ItemID.WILD_CAVE_ARTIFACT_8000, -1),
	ANCIENT_MEDALLION("Ancient medallion", Rarity.RARE, ItemID.WILD_CAVE_ARTIFACT_4000, -1),
	ANCIENT_STATUETTE("Ancient statuette", Rarity.RARE, ItemID.WILD_CAVE_ARTIFACT_2000, -1),
	ANCIENT_TOTEM("Ancient totem", Rarity.RARE, ItemID.WILD_CAVE_ARTIFACT_1000, -1),
	ANCIENT_EMBLEM("Ancient emblem", Rarity.RARE, ItemID.WILD_CAVE_ARTIFACT_500, -1),
	REVENANT_CAVE_TELEPORT("Revenant cave teleport", Rarity.UNCOMMON, ItemID.TELEPORTSCROLL_REVENANTS, -1),
	REVENANT_ETHER("Revenant ether", Rarity.COMMON, ItemID.WILD_CAVE_SHARD, -1),

	// Rooftop Agility
	MARK_OF_GRACE("Mark of grace", Rarity.COMMON, ItemID.GRACE, -1),
	GRACEFUL_HOOD("Graceful hood", Rarity.UNCOMMON, ItemID.GRACEFUL_HOOD, -1),
	GRACEFUL_CAPE("Graceful cape", Rarity.UNCOMMON, ItemID.GRACEFUL_CAPE, -1),
	GRACEFUL_TOP("Graceful top", Rarity.UNCOMMON, ItemID.GRACEFUL_TOP, -1),
	GRACEFUL_LEGS("Graceful legs", Rarity.UNCOMMON, ItemID.GRACEFUL_LEGS, -1),
	GRACEFUL_GLOVES("Graceful gloves", Rarity.UNCOMMON, ItemID.GRACEFUL_GLOVES, -1),
	GRACEFUL_BOOTS("Graceful boots", Rarity.UNCOMMON, ItemID.GRACEFUL_BOOTS, -1),

	// Sailing Miscellaneous
	DRAGON_METAL_SHEET("Dragon metal sheet", Rarity.RARE, ItemID.DRAGON_SHEET, -1),
	DRAGON_NAILS("Dragon nails", Rarity.RARE, ItemID.NAILS_DRAGON, -1),
	DRAGON_CANNONBALL("Dragon cannonball", Rarity.UNCOMMON, ItemID.DRAGON_CANNONBALL, -1),
	ECHO_PEARL("Echo pearl", Rarity.RARE, ItemID.ECHO_PEARL, -1),
	SWIFT_ALBATROSS_FEATHER("Swift albatross feather", Rarity.UNCOMMON, ItemID.SWIFT_ALBATROSS_FEATHER, -1),
	NARWHAL_HORN("Narwhal horn", Rarity.UNCOMMON, ItemID.NARWHAL_HORN, -1),
	RAY_BARBS("Ray barbs", Rarity.UNCOMMON, ItemID.RAY_BARBS, -1),
	BROKEN_DRAGON_HOOK("Broken dragon hook", Rarity.RARE, ItemID.BROKEN_DRAGON_HOOK, -1),
	BOTTLED_STORM("Bottled storm", Rarity.RARE, ItemID.BOTTLED_STORM, -1),
	DRAGON_CANNON_BARREL("Dragon cannon barrel", Rarity.RARE, ItemID.DRAGON_CANNON_BARREL, -1),
	BOAT_BOTTLE("Boat bottle (empty)", Rarity.UNCOMMON, ItemID.SAILING_BOAT_BOTTLE_EMPTY, -1),
	FACILITY_BOTTLE("Facility bottle (empty)", Rarity.UNCOMMON, ItemID.SAILING_FACILITY_BOTTLE_EMPTY, -1),

	// Sea Treasures
	MEDALLION_FRAGMENT_1("Medallion fragment 1", Rarity.UNCOMMON, ItemID.MOTD_FRAG_1, -1),
	MEDALLION_FRAGMENT_2("Medallion fragment 2", Rarity.UNCOMMON, ItemID.MOTD_FRAG_2, -1),
	MEDALLION_FRAGMENT_3("Medallion fragment 3", Rarity.UNCOMMON, ItemID.MOTD_FRAG_3, -1),
	MEDALLION_FRAGMENT_4("Medallion fragment 4", Rarity.UNCOMMON, ItemID.MOTD_FRAG_4, -1),
	MEDALLION_FRAGMENT_5("Medallion fragment 5", Rarity.UNCOMMON, ItemID.MOTD_FRAG_5, -1),
	MEDALLION_FRAGMENT_6("Medallion fragment 6", Rarity.UNCOMMON, ItemID.MOTD_FRAG_6, -1),
	MEDALLION_FRAGMENT_7("Medallion fragment 7", Rarity.UNCOMMON, ItemID.MOTD_FRAG_7, -1),
	MEDALLION_FRAGMENT_8("Medallion fragment 8", Rarity.UNCOMMON, ItemID.MOTD_FRAG_8, -1),
	SAILORS_AMULET_INERT("Sailors' amulet (inert)", Rarity.UNCOMMON, ItemID.SAILORS_AMULET_EMPTY, -1),
	RUSTY_LOCKET("Rusty locket", Rarity.UNCOMMON, ItemID.SALVAGING_RARE_RUSTY_LOCKET, -1),
	MOULDY_BLOCK("Mouldy block", Rarity.UNCOMMON, ItemID.SALVAGING_RARE_MOULDY_BLOCK, -1),
	DULL_KNIFE("Dull knife", Rarity.UNCOMMON, ItemID.SALVAGING_RARE_DULL_KNIFE, -1),
	BROKEN_COMPASS("Broken compass", Rarity.UNCOMMON, ItemID.SALVAGING_RARE_BROKEN_COMPASS, -1),
	RUSTY_COIN("Rusty coin", Rarity.UNCOMMON, ItemID.SALVAGING_RARE_RUSTY_COIN, -1),
	BROKEN_SEXTANT("Broken sextant", Rarity.UNCOMMON, ItemID.SALVAGING_RARE_BROKEN_SEXTANT, -1),
	MOULDY_DOLL("Mouldy doll", Rarity.UNCOMMON, ItemID.SALVAGING_RARE_MOULDY_DOLL, -1),
	SMASHED_MIRROR("Smashed mirror", Rarity.UNCOMMON, ItemID.SALVAGING_RARE_SMASHED_MIRROR, -1),

	// Shayzien Armour
	//Tier 1
	SHAYZIEN_GLOVES_1("Shayzien gloves (1)", Rarity.COMMON, ItemID.SHAYZIEN_GLOVES_1, -1),
	SHAYZIEN_BOOTS_1("Shayzien boots (1)", Rarity.COMMON, ItemID.SHAYZIEN_BOOTS_1, -1),
	SHAYZIEN_HELM_1("Shayzien helm (1)", Rarity.COMMON, ItemID.SHAYZIEN_HELM_1, -1),
	SHAYZIEN_GREAVES_1("Shayzien greaves (1)", Rarity.COMMON, ItemID.SHAYZIEN_LEGS_1, -1),
	SHAYZIEN_PLATEBODY_1("Shayzien platebody (1)", Rarity.COMMON, ItemID.SHAYZIEN_BODY_1, -1),
	//Tier 2
	SHAYZIEN_GLOVES_2("Shayzien gloves (2)", Rarity.COMMON, ItemID.SHAYZIEN_GLOVES_2, -1),
	SHAYZIEN_BOOTS_2("Shayzien boots (2)", Rarity.COMMON, ItemID.SHAYZIEN_BOOTS_2, -1),
	SHAYZIEN_HELM_2("Shayzien helm (2)", Rarity.COMMON, ItemID.SHAYZIEN_HELM_2, -1),
	SHAYZIEN_GREAVES_2("Shayzien greaves (2)", Rarity.COMMON, ItemID.SHAYZIEN_LEGS_2, -1),
	SHAYZIEN_PLATEBODY_2("Shayzien platebody (2)", Rarity.COMMON, ItemID.SHAYZIEN_BODY_2, -1),
	// Tier 3
	SHAYZIEN_GLOVES_3("Shayzien gloves (3)", Rarity.UNCOMMON, ItemID.SHAYZIEN_GLOVES_3, -1),
	SHAYZIEN_BOOTS_3("Shayzien boots (3)", Rarity.UNCOMMON, ItemID.SHAYZIEN_BOOTS_3, -1),
	SHAYZIEN_HELM_3("Shayzien helm (3)", Rarity.UNCOMMON, ItemID.SHAYZIEN_HELM_3, -1),
	SHAYZIEN_GREAVES_3("Shayzien greaves (3)", Rarity.UNCOMMON, ItemID.SHAYZIEN_LEGS_3, -1),
	SHAYZIEN_PLATEBODY_3("Shayzien platebody (3)", Rarity.UNCOMMON, ItemID.SHAYZIEN_BODY_3, -1),
	// Tier 4
	SHAYZIEN_GLOVES_4("Shayzien gloves (4)", Rarity.UNCOMMON, ItemID.SHAYZIEN_GLOVES_4, -1),
	SHAYZIEN_BOOTS_4("Shayzien boots (4)", Rarity.UNCOMMON, ItemID.SHAYZIEN_BOOTS_4, -1),
	SHAYZIEN_HELM_4("Shayzien helm (4)", Rarity.UNCOMMON, ItemID.SHAYZIEN_HELM_4, -1),
	SHAYZIEN_GREAVES_4("Shayzien greaves (4)", Rarity.UNCOMMON, ItemID.SHAYZIEN_LEGS_4, -1),
	SHAYZIEN_PLATEBODY_4("Shayzien platebody (4)", Rarity.UNCOMMON, ItemID.SHAYZIEN_BODY_4, -1),
	// Tier 5
	SHAYZIEN_GLOVES_5("Shayzien gloves (5)", Rarity.UNCOMMON, ItemID.SHAYZIEN_GLOVES_5, -1),
	SHAYZIEN_BOOTS_5("Shayzien boots (5)", Rarity.UNCOMMON, ItemID.SHAYZIEN_BOOTS_5, -1),
	SHAYZIEN_HELM_5("Shayzien helm (5)", Rarity.UNCOMMON, ItemID.SHAYZIEN_HELM_5, -1),
	SHAYZIEN_GREAVES_5("Shayzien greaves (5)", Rarity.UNCOMMON, ItemID.SHAYZIEN_LEGS_5, -1),
	SHAYZIEN_BODY_5("Shayzien body (5)", Rarity.UNCOMMON, ItemID.SHAYZIEN_BODY_5, -1),

	// Shooting Stars
	CELESTIAL_RING("Celestial ring (uncharged)", Rarity.UNCOMMON, ItemID.CELESTIAL_RING, -1),
	STAR_FRAGMENT("Star fragment", Rarity.UNCOMMON, ItemID.STAR_FRAGMENT, -1),

	// Skipping Skilling Pets section as every pet is already on their table

	// Slayer
	CRAWLING_HAND("Crawling hand", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_CRAWLINGHAND, -1),
	COCKATRICE_HEAD("Cockatrice head", Rarity.UNCOMMON, ItemID.POH_TROPHYDROP_COCKATRICE, -1),
	BASILISK_HEAD("Basilisk head", Rarity.RARE, ItemID.POH_TROPHYDROP_BASILISK, -1),
	KURASK_HEAD("Kurask head", Rarity.RARE, ItemID.POH_TROPHYDROP_KURASK, -1),
	ABYSSAL_HEAD("Abyssal head", Rarity.RARE, ItemID.POH_TROPHYDROP_ABYSSALDEMON, -1),
	IMBUED_HEART("Imbued heart", Rarity.EPIC, ItemID.IMBUED_HEART, -1),
	ETERNAL_GEM("Eternal gem", Rarity.EPIC, ItemID.SLAYER_ETERNAL_GEM, -1),
	DUST_BATTLESTAFF("Dust battlestaff", Rarity.UNCOMMON, ItemID.DUST_BATTLESTAFF, -1),
	MIST_BATTLESTAFF("Mist battlestaff", Rarity.UNCOMMON, ItemID.MIST_BATTLESTAFF, -1),
	ABYSSAL_WHIP("Abyssal whip", Rarity.RARE, ItemID.ABYSSAL_WHIP, -1),
	GRANITE_MAUL("Granite maul", Rarity.RARE, ItemID.GRANITE_MAUL, -1),
	MUDSKIPPER_HAT("Mudskipper hat", Rarity.COMMON, ItemID.MUDSKIPPER_HAT, -1),
	FLIPPERS("Flippers", Rarity.COMMON, ItemID.MUDSKIPPER_FLIPPERS, -1),
	BRINE_SABRE("Brine sabre", Rarity.UNCOMMON, ItemID.OLAF2_BRINE_SABRE, -1),
	LEAF_BLADED_SWORD("Leaf-bladed sword", Rarity.UNCOMMON, ItemID.LEAFBLADED_SWORD, -1),
	LEAF_BLADED_BATTLEAXE("Leaf-bladed battleaxe", Rarity.UNCOMMON, ItemID.LEAFBLADED_BATTLEAXE, -1),
	BLACK_MASK("Black mask (10)", Rarity.RARE, ItemID.HARMLESS_BLACK_MASK_10, -1),
	GRANITE_LONGSWORD("Granite longsword", Rarity.RARE, ItemID.GRANITE_LONGSWORD, -1),
	GRANITE_BOOTS("Granite boots", Rarity.RARE, ItemID.GRANITE_BOOTS, -1),
	WYVERN_VISAGE("Wyvern visage", Rarity.EPIC, ItemID.WYVERN_VISAGE, -1),
	GRANITE_LEGS("Granite legs", Rarity.UNCOMMON, ItemID.GRANITE_LEGS, -1),
	GRANITE_HELM("Granite helm", Rarity.UNCOMMON, ItemID.GRANITE_HELM, -1),
	DRACONIC_VISAGE("Draconic visage", Rarity.EPIC, ItemID.DRAGONFIRE_VISAGE, -1),
	BRONZE_BOOTS("Bronze boots", Rarity.COMMON, ItemID.BRONZE_ARMOURED_BOOTS, -1),
	IRON_BOOTS("Iron boots", Rarity.COMMON, ItemID.IRON_ARMOURED_BOOTS, -1),
	STEEL_BOOTS("Steel boots", Rarity.COMMON, ItemID.STEEL_ARMOURED_BOOTS, -1),
	BLACK_BOOTS("Black boots", Rarity.UNCOMMON, ItemID.BLACK_ARMOURED_BOOTS, -1),
	MITHRIL_BOOTS("Mithril boots", Rarity.UNCOMMON, ItemID.MITHRIL_ARMOURED_BOOTS, -1),
	ADAMANT_BOOTS("Adamant boots", Rarity.UNCOMMON, ItemID.ADAMANT_ARMOURED_BOOTS, -1),
	RUNE_BOOTS("Rune boots", Rarity.UNCOMMON, ItemID.RUNE_ARMOURED_BOOTS, -1),
	DRAGON_BOOTS("Dragon boots", Rarity.UNCOMMON, ItemID.DRAGON_BOOTS, -1),
	UNCHARGED_TRIDENT("Uncharged trident", Rarity.RARE, ItemID.TOTS_UNCHARGED, -1),
	DARK_BOW("Dark bow", Rarity.RARE, ItemID.DARKBOW, -1),
	BROKEN_DRAGON_HASTA("Broken dragon hasta", Rarity.RARE, ItemID.BROKEN_DRAGON_HASTA, -1),
	MYSTIC_HAT_LIGHT("Mystic hat (light)", Rarity.UNCOMMON, ItemID.MYSTIC_HAT_LIGHT, -1),
	MYSTIC_ROBE_TOP_LIGHT("Mystic robe top (light)", Rarity.UNCOMMON, ItemID.MYSTIC_ROBE_TOP_LIGHT, -1),
	MYSTIC_ROBE_BOTTOM_LIGHT("Mystic robe bottom (light)", Rarity.UNCOMMON, ItemID.MYSTIC_ROBE_BOTTOM_LIGHT, -1),
	MYSTIC_GLOVES_LIGHT("Mystic gloves (light)", Rarity.UNCOMMON, ItemID.MYSTIC_GLOVES_LIGHT, -1),
	MYSTIC_BOOTS_LIGHT("Mystic boots (light)", Rarity.UNCOMMON, ItemID.MYSTIC_BOOTS_LIGHT, -1),
	MYSTIC_HAT_DARK("Mystic hat (dark)", Rarity.UNCOMMON, ItemID.MYSTIC_HAT_DARK, -1),
	MYSTIC_ROBE_TOP_DARK("Mystic robe top (dark)", Rarity.UNCOMMON, ItemID.MYSTIC_ROBE_TOP_DARK, -1),
	MYSTIC_ROBE_BOTTOM_DARK("Mystic robe bottom (dark)", Rarity.UNCOMMON, ItemID.MYSTIC_ROBE_BOTTOM_DARK, -1),
	MYSTIC_GLOVES_DARK("Mystic gloves (dark)", Rarity.UNCOMMON, ItemID.MYSTIC_GLOVES_DARK, -1),
	MYSTIC_BOOTS_DARK("Mystic boots (dark)", Rarity.UNCOMMON, ItemID.MYSTIC_BOOTS_DARK, -1),
	MYSTIC_HAT_DUSK("Mystic hat (dusk)", Rarity.EPIC, ItemID.MYSTIC_HAT_DUSK, -1),
	MYSTIC_ROBE_TOP_DUSK("Mystic robe top (dusk)", Rarity.EPIC, ItemID.MYSTIC_ROBE_TOP_DUSK, -1),
	MYSTIC_ROBE_BOTTOM_DUSK("Mystic robe bottom (dusk)", Rarity.EPIC, ItemID.MYSTIC_ROBE_BOTTOM_DUSK, -1),
	MYSTIC_GLOVES_DUSK("Mystic gloves (dusk)", Rarity.EPIC, ItemID.MYSTIC_GLOVES_DUSK, -1),
	MYSTIC_BOOTS_DUSK("Mystic boots (dusk)", Rarity.EPIC, ItemID.MYSTIC_BOOTS_DUSK, -1),
	BASILISK_JAW("Basilisk jaw", Rarity.EPIC, ItemID.BASILISK_JAW, -1),
	AQUANITE_TENDON("Aquanite tendon", Rarity.RARE, ItemID.AQUANITE_TENDON, -1),
	DAGONHAI_HAT("Dagon'hai hat", Rarity.RARE, ItemID.DAGONHAI_HAT, -1),
	DAGONHAI_ROBE_TOP("Dagon'hai robe top", Rarity.RARE, ItemID.DAGONHAI_ROBE_TOP, -1),
	DAGONHAI_ROBE_BOTTOM("Dagon'hai robe bottom", Rarity.RARE, ItemID.DAGONHAI_ROBE_BOTTOM, -1),
	BLOOD_SHARD("Blood shard", Rarity.RARE, ItemID.BLOOD_SHARD, -1),
	ANCIENT_CEREMONIAL_MASK("Ancient ceremonial mask", Rarity.UNCOMMON, ItemID.ANCIENT_CEREMONIAL_MASK, -1),
	ANCIENT_CEREMONIAL_TOP("Ancient ceremonial top", Rarity.UNCOMMON, ItemID.ANCIENT_CEREMONIAL_TOP, -1),
	ANCIENT_CEREMONIAL_LEGS("Ancient ceremonial legs", Rarity.UNCOMMON, ItemID.ANCIENT_CEREMONIAL_LEGS, -1),
	ANCIENT_CEREMONIAL_GLOVES("Ancient ceremonial gloves", Rarity.UNCOMMON, ItemID.ANCIENT_CEREMONIAL_GLOVES, -1),
	ANCIENT_CEREMONIAL_BOOTS("Ancient ceremonial boots", Rarity.UNCOMMON, ItemID.ANCIENT_CEREMONIAL_BOOTS, -1),
	WARPED_SCEPTRE("Warped sceptre (uncharged)", Rarity.RARE, ItemID.WARPED_SCEPTRE_UNCHARGED, -1),
	SULPHUR_BLADES("Sulphur blades", Rarity.RARE, ItemID.SULPHUR_BLADES, -1),
	TELEPORT_ANCHORING_SCROLL("Teleport anchoring scroll", Rarity.EPIC, ItemID.WILDERNESS_BLIP_BLOCKING_SCROLL, -1),
	ARANEA_BOOTS("Aranea boots", Rarity.RARE, ItemID.ARANEA_BOOTS, -1),
	GLACIAL_TEMOTLI("Glacial temotli", Rarity.UNCOMMON, ItemID.GLACIAL_TEMOTLI, -1),
	PENDANT_OF_ATES("Pendant of ates (inert)", Rarity.UNCOMMON, ItemID.PENDANT_OF_ATES_EMPTY, -1),
	FROZEN_TEAR("Frozen tear", Rarity.UNCOMMON, ItemID.FROZEN_TEAR, -1),
	EARTHBOUND_TECPATL("Earthbound tecpatl", Rarity.UNCOMMON, ItemID.EARTHBOUND_TECPATL, -1),
	ANTLER_GUARD("Antler guard", Rarity.UNCOMMON, ItemID.CUSTODIAN_ANTLER_GUARD, -1),
	ALCHEMISTS_SIGNET("Alchemist's signet", Rarity.UNCOMMON, ItemID.ALCHEMIST_RING, -1),
	BROKEN_ANTLER("Broken antler", Rarity.UNCOMMON, ItemID.CUSTODIAN_BROKEN_ANTLER, -1),
	HORN_OF_PLENTY("Horn of plenty (empty)", Rarity.RARE, ItemID.HORN_OF_PLENTY_UNCHARGED, -1),
	GRYPHON_FEATHER("Gryphon feather", Rarity.COMMON, ItemID.GRYPHON_FEATHER, -1),
	VENATOR_TOOTH("Venator tooth", Rarity.UNCOMMON, ItemID.VENATOR_TOOTH, -1),
	VENATOR_FANG("Venator fang", Rarity.UNCOMMON, ItemID.VENATOR_FANG, -1),

	// Tormented Demons
	TORMENTED_SYNAPSE("Tormented synapse", Rarity.EPIC, ItemID.TORMENTED_SYNAPSE, -1),
	BURNING_CLAW("Burning claw", Rarity.EPIC, ItemID.BONE_CLAW, -1),
	GUTHIXIAN_TEMPLE_TELEPORT("Guthixian temple teleport", Rarity.UNCOMMON, ItemID.TELEPORTSCROLL_GUTHIXIAN_TEMPLE, -1),

	// TzHaar
	OBSIDIAN_CAPE("Obsidian cape", Rarity.UNCOMMON, ItemID.TZHAAR_CAPE_OBSIDIAN, -1),
	TOKTZ_KET_XIL("Toktz-ket-xil", Rarity.UNCOMMON, ItemID.TZHAAR_SPIKESHIELD, -1),
	TZHAAR_KET_OM("Tzhaar-ket-om", Rarity.UNCOMMON, ItemID.TZHAAR_MAUL, -1),
	TOKTZ_XIL_AK("Toktz-xil-ak", Rarity.UNCOMMON, ItemID.TZHAAR_SPLITSWORD, -1),
	TOKTZ_XIL_EK("Toktz-xil-ek", Rarity.UNCOMMON, ItemID.TZHAAR_KNIFE, -1),
	TOKTZ_MEJ_TAL("Toktz-mej-tal", Rarity.RARE, ItemID.TZHAAR_STAFF, -1),
	TOKTZ_XIL_UL("Toktz-xil-ul", Rarity.UNCOMMON, ItemID.TZHAAR_THROWINGRING, -1),
	OBSIDIAN_HELMET("Obsidian helmet", Rarity.RARE, ItemID.OBSIDIAN_HELMET, -1),
	OBSIDIAN_PLATEBODY("Obsidian platebody", Rarity.RARE, ItemID.OBSIDIAN_PLATEBODY, -1),
	OBSIDIAN_PLATELEGS("Obsidian platelegs", Rarity.RARE, ItemID.OBSIDIAN_PLATELEGS, -1),

	// Miscellaneous
	HERBI("Herbi", Rarity.EPIC, ItemID.HERBIBOARPET, -1),
	DRAGON_WARHAMMER("Dragon warhammer", Rarity.EPIC, ItemID.DRAGON_WARHAMMER, -1),
	BIG_SWORDFISH("Big swordfish", Rarity.RARE, ItemID.POH_TROPHYDROP_SWORDFISH, -1),
	BIG_SHARK("Big shark", Rarity.RARE, ItemID.POH_TROPHYDROP_SHARK, -1),
	BIG_BASS("Big bass", Rarity.UNCOMMON, ItemID.POH_TROPHYDROP_BASS, -1),
	GIANT_BLUE_KRILL("Giant blue krill", Rarity.RARE, ItemID.POH_TROPHYDROP_GIANT_KRILL, -1),
	GOLDEN_HADDOCK("Golden haddock", Rarity.RARE, ItemID.POH_TROPHYDROP_HADDOCK, -1),
	ORANGEFIN("Orangefin", Rarity.RARE, ItemID.POH_TROPHYDROP_YELLOWFIN, -1),
	HUGE_HALIBUT("Huge halibut", Rarity.RARE, ItemID.POH_TROPHYDROP_HALIBUT, -1),
	PURPLEFIN("Purplefin", Rarity.RARE, ItemID.POH_TROPHYDROP_BLUEFIN, -1),
	SWIFT_MARLIN("Swift marlin", Rarity.RARE, ItemID.POH_TROPHYDROP_MARLIN, -1),
	LONG_BONE("Long bone", Rarity.UNCOMMON, ItemID.DORGESH_CONSTRUCTION_BONE, -1),
	CURVED_BONE("Curved bone", Rarity.RARE, ItemID.DORGESH_CONSTRUCTION_BONE_CURVED, -1),
	ECUMENICAL_KEY("Ecumenical key", Rarity.UNCOMMON, ItemID.ECUMENICAL_KEY, -1),
	PHARAOHS_SCEPTRE_UNCHARGED("Pharaoh's sceptre (uncharged)", Rarity.RARE, ItemID.PHARAOHS_SCEPTRE, -1),
	DARK_TOTEM_BASE("Dark totem base", Rarity.UNCOMMON, ItemID.CATA_TOTEM1, -1),
	DARK_TOTEM_MIDDLE("Dark totem middle", Rarity.UNCOMMON, ItemID.CATA_TOTEM2, -1),
	DARK_TOTEM_TOP("Dark totem top", Rarity.UNCOMMON, ItemID.CATA_TOTEM3, -1),
	CHEWED_BONES("Chewed bones", Rarity.UNCOMMON, ItemID.BRUT_BARBARIAN_BONES, -1),
	DRAGON_FULL_HELM("Dragon full helm", Rarity.EPIC, ItemID.BRUT_DRAGON_FULL_HELM, -1),
	SHIELD_LEFT_HALF("Shield left half", Rarity.RARE, ItemID.DRAGONSHIELD_A, -1),
	DRAGON_METAL_SLICE("Dragon metal slice", Rarity.RARE, ItemID.DRAGON_SLICE, -1),
	DRAGON_METAL_LUMP("Dragon metal lump", Rarity.RARE, ItemID.DRAGON_LUMP, -1),
	DRAGON_LIMBS("Dragon limbs", Rarity.RARE, ItemID.XBOWS_CROSSBOW_LIMBS_DRAGON, -1),
	DRAGON_SPEAR("Dragon spear", Rarity.RARE, ItemID.DRAGON_SPEAR, -1),
	AMULET_OF_ETERNAL_GLORY("Amulet of eternal glory", Rarity.EPIC, ItemID.AMULET_OF_GLORY_INF, -1),
	SHAMAN_MASK("Shaman mask", Rarity.EPIC, ItemID.OGRE_HELMET, -1),
	EVIL_CHICKEN_HEAD("Evil chicken head", Rarity.EPIC, ItemID.EVIL_CHICKEN_HEAD, -1),
	EVIL_CHICKEN_WINGS("Evil chicken wings", Rarity.EPIC, ItemID.EVIL_CHICKEN_WINGS, -1),
	EVIL_CHICKEN_LEGS("Evil chicken legs", Rarity.EPIC, ItemID.EVIL_CHICKEN_LEGS, -1),
	EVIL_CHICKEN_FEET("Evil chicken feet", Rarity.EPIC, ItemID.EVIL_CHICKEN_FEET, -1),
	MINING_GLOVES("Mining gloves", Rarity.UNCOMMON, ItemID.MGUILD_GLOVES, -1),
	SUPERIOR_MINING_GLOVES("Superior mining gloves", Rarity.RARE, ItemID.MGUILD_GLOVES_SUPERIOR, -1),
	EXPERT_MINING_GLOVES("Expert mining gloves", Rarity.RARE, ItemID.MGUILD_GLOVES_EXPERT, -1),
	RIGHT_SKULL_HALF("Right skull half", Rarity.COMMON, ItemID.SOS_HALF_SKULL1, -1),
	LEFT_SKULL_HALF("Left skull half", Rarity.COMMON, ItemID.SOS_HALF_SKULL2, -1),
	TOP_OF_SCEPTRE("Top of sceptre", Rarity.COMMON, ItemID.SOS_HALF_SCEPTRE1, -1),
	BOTTOM_OF_SCEPTRE("Bottom of sceptre", Rarity.COMMON, ItemID.SOS_HALF_SCEPTRE2, -1),
	MOSSY_KEY("Mossy key", Rarity.UNCOMMON, ItemID.MOSSY_KEY, -1),
	GIANT_KEY("Giant key", Rarity.UNCOMMON, ItemID.HILLGIANT_BOSS_KEY, -1),
	HESPORI_SEED("Hespori seed", Rarity.RARE, ItemID.HESPORI_SEED, -1),
	FRESH_CRAB_CLAW("Fresh crab claw", Rarity.COMMON, ItemID.HUNDRED_PIRATE_CRAB_SHELL_CLAW, -1),
	FRESH_CRAB_SHELL("Fresh crab shell", Rarity.COMMON, ItemID.HUNDRED_PIRATE_CRAB_SHELL_HEAD, -1),
	XERICS_TALISMAN_INERT("Xeric's talisman (inert)", Rarity.UNCOMMON, ItemID.XERIC_TALISMAN_EMPTY, -1),
	MASK_OF_RANUL("Mask of ranul", Rarity.RARE, ItemID.HOSDUN_TEMPLE_MASK, -1),
	ELVEN_SIGNET("Elven signet", Rarity.RARE, ItemID.ELVEN_SIGNET, -1),
	CRYSTAL_GRAIL("Crystal grail", Rarity.RARE, ItemID.PRIF_CRYSTAL_GRAIL, -1),
	ENHANCED_CRYSTAL_TELEPORT_SEED("Enhanced crystal teleport seed", Rarity.RARE, ItemID.PRIF_TELEPORT_SEED, -1),
	DRAGONSTONE_FULL_HELM("Dragonstone full helm", Rarity.RARE, ItemID.DRAGONSTONE_HELMET, -1),
	DRAGONSTONE_PLATEBODY("Dragonstone platebody", Rarity.RARE, ItemID.DRAGONSTONE_PLATEBODY, -1),
	DRAGONSTONE_PLATELEGS("Dragonstone platelegs", Rarity.RARE, ItemID.DRAGONSTONE_PLATELEGS, -1),
	DRAGONSTONE_GAUNTLETS("Dragonstone gauntlets", Rarity.RARE, ItemID.DRAGONSTONE_GAUNTLETS, -1),
	DRAGONSTONE_BOOTS("Dragonstone boots", Rarity.RARE, ItemID.DRAGONSTONE_ARMOURED_BOOTS, -1),
	MERFOLK_TRIDENT("Merfolk trident", Rarity.UNCOMMON, ItemID.MERFOLK_TRIDENT, -1),
	ORANGE_EGG_SAC("Orange egg sac", Rarity.UNCOMMON, ItemID.HOSDUN_ORANGE_EGG_SAC, -1),
	BLUE_EGG_SAC("Blue egg sac", Rarity.UNCOMMON, ItemID.HOSDUN_BLUE_EGG_SAC, -1),
	BROKEN_ZOMBIE_AXE("Broken zombie axe", Rarity.UNCOMMON, ItemID.ZOMBIE_AXE_BROKEN, -1),
	BROKEN_ZOMBIE_HELMET("Broken zombie helmet", Rarity.UNCOMMON, ItemID.ZOMBIE_HELMET_BROKEN, -1),
	HELMET_OF_THE_MOON("Helmet of the moon", Rarity.LEGENDARY, ItemID.MOON_HELMET, -1),
	SQUID_BEAK("Squid beak", Rarity.RARE, ItemID.SQUID_BEAK, -1),
	JEWELLERS_CHISEL("Jeweller's chisel", Rarity.UNCOMMON, ItemID.JEWELLERS_CHISEL, -1);




	private final Card card;

	OtherCollectionLog(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.OTHER_COLLECTIONLOG, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
