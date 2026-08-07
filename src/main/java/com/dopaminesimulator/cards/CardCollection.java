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
package com.dopaminesimulator.cards;

import com.dopaminesimulator.cards.sets.*;
import lombok.Getter;

import com.dopaminesimulator.core.DopamineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public final class CardCollection
{
	public static final double BONUS_PER_COLLECTION = 0.07d;

	public static final double FULL_COLLECTION_BONUS = 1.5d;

	public static final double BONUS_PER_ASCENSION = 0.05d;

	public static final int[] TIER_STARS = {1, 3, 6, 10};

	public static final String[] TIER_NAMES = {"Bronze", "Silver", "Gold", "Diamond"};
	private static final List<CardCollection> ALL = new ArrayList<>();
	private static final Map<String, List<CardCollection>> BY_CARD = new LinkedHashMap<>();
	private final String name;
	private final CardSet set;
	private final String description;
	private final List<Card> cards;
	private CardCollection(String name, CardSet set, String description, List<? extends CardGroup> members)
	{
		this.name = name;
		this.set = set;
		this.description = description;
		List<Card> resolved = new ArrayList<>(members.size());
		for (CardGroup member : members)
		{
			if (member.getCard().getSet() != set)
			{
				throw new IllegalStateException(name + " lists " + member
					+ ", which is in " + member.getCard().getSet() + " rather than " + set);
			}
			resolved.add(member.getCard());
		}
		this.cards = Collections.unmodifiableList(resolved);
	}

	static
	{

		for (CardGroup entry : DiariesCards.values())
		{
			if (entry.getCard().getSet() != CardSet.DIARIES)
			{
				continue;
			}
			String area = areaOf(entry.getCard().getName());
			if (area == null || contains(area + " Diaries"))
			{
				continue;
			}
			List<CardGroup> tiers = new ArrayList<>();
			for (CardGroup sibling : DiariesCards.values())
			{
				if (sibling.getCard().getSet() == CardSet.DIARIES
					&& area.equals(areaOf(sibling.getCard().getName())))
				{
					tiers.add(sibling);
				}
			}
			define(area + " Diaries", CardSet.DIARIES, "All four difficulty tiers.", tiers);
		}
		define("The Elf Saga", CardSet.QUESTS, "The elf quest series.", QuestsCards.PLAGUE_CITY,
			QuestsCards.BIOHAZARD, QuestsCards.UNDERGROUND_PASS, QuestsCards.REGICIDE, QuestsCards.ROVING_ELVES,
			QuestsCards.MOURNING_S_END_PART_I, QuestsCards.MOURNING_S_END_PART_II, QuestsCards.SONG_OF_THE_ELVES);
		define("The Myreque", CardSet.QUESTS, "The Myreque quest series.",
			QuestsCards.IN_SEARCH_OF_THE_MYREQUE, QuestsCards.IN_AID_OF_THE_MYREQUE, QuestsCards.DARKNESS_OF_HALLOWVALE,
			QuestsCards.A_TASTE_OF_HOPE, QuestsCards.SINS_OF_THE_FATHER, QuestsCards.THE_BLOOD_MOON_RISES);
		define("The Mahjarrat", CardSet.QUESTS, "Quests involving the Mahjarrat.",
			QuestsCards.DESERT_TREASURE_I, QuestsCards.DESERT_TREASURE_II_THE_FALLEN_EMPIRE, QuestsCards.THE_CURSE_OF_ARRAV,
			QuestsCards.DEFENDER_OF_VARROCK, QuestsCards.WHILE_GUTHIX_SLEEPS, QuestsCards.SECRETS_OF_THE_NORTH);
		define("Gnome Business", CardSet.QUESTS, "The gnome quest series.", QuestsCards.TREE_GNOME_VILLAGE,
			QuestsCards.THE_GRAND_TREE, QuestsCards.THE_EYES_OF_GLOUPHRIE, QuestsCards.THE_PATH_OF_GLOUPHRIE,
			QuestsCards.MONKEY_MADNESS_I, QuestsCards.MONKEY_MADNESS_II);
		define("Fremennik Trials", CardSet.QUESTS, "The Fremennik quest series.",
			QuestsCards.THE_FREMENNIK_TRIALS, QuestsCards.THE_FREMENNIK_ISLES, QuestsCards.THE_FREMENNIK_EXILES);

		define("Dragon Slayers", CardSet.QUESTS, "Both Dragon Slayer quests.", QuestsCards.DRAGON_SLAYER_I,
			QuestsCards.DRAGON_SLAYER_II);
		define("Pirate Tales", CardSet.QUESTS, "The pirate quest series.", QuestsCards.PIRATE_S_TREASURE,
			QuestsCards.RUM_DEAL, QuestsCards.CABIN_FEVER, QuestsCards.THE_GREAT_BRAIN_ROBBERY);
		define("Great Kourend", CardSet.QUESTS, "The Great Kourend quest series.",
			QuestsCards.CLIENT_OF_KOUREND, QuestsCards.THE_QUEEN_OF_THIEVES, QuestsCards.THE_DEPTHS_OF_DESPAIR,
			QuestsCards.TALE_OF_THE_RIGHTEOUS, QuestsCards.THE_FORSAKEN_TOWER, QuestsCards.THE_ASCENT_OF_ARCEUUS,
			QuestsCards.A_KINGDOM_DIVIDED);
		define("Varlamore", CardSet.QUESTS, "The Varlamore quest series.", QuestsCards.CHILDREN_OF_THE_SUN,
			QuestsCards.TWILIGHT_S_PROMISE, QuestsCards.PERILOUS_MOONS, QuestsCards.THE_FINAL_DAWN);

		define("Recipe for Disaster", CardSet.QUESTS, "Recipe for Disaster and its ten subquests.",
			QuestsCards.RECIPE_FOR_DISASTER, QuestsCards.RECIPE_FOR_DISASTER_ANOTHER_COOK_S_QUEST,
			QuestsCards.RECIPE_FOR_DISASTER_MOUNTAIN_DWARF, QuestsCards.RECIPE_FOR_DISASTER_WARTFACE_BENTNOZE,
			QuestsCards.RECIPE_FOR_DISASTER_PIRATE_PETE, QuestsCards.RECIPE_FOR_DISASTER_LUMBRIDGE_GUIDE,
			QuestsCards.RECIPE_FOR_DISASTER_EVIL_DAVE, QuestsCards.RECIPE_FOR_DISASTER_SKRACH_UGLOGWEE,
			QuestsCards.RECIPE_FOR_DISASTER_SIR_AMIK_VARZE, QuestsCards.RECIPE_FOR_DISASTER_KING_AWOWOGEI,
			QuestsCards.RECIPE_FOR_DISASTER_CULINAROMANCER);

		define("Combat Skills", CardSet.SKILLS, "The seven combat skills.", SkillsCards.ATTACK_BRONZE,
			SkillsCards.STRENGTH_BRONZE, SkillsCards.DEFENCE_BRONZE, SkillsCards.HITPOINTS_BRONZE, SkillsCards.RANGED_BRONZE, SkillsCards.MAGIC_BRONZE, SkillsCards.PRAYER_BRONZE);
		define("Gathering Skills", CardSet.SKILLS, "The five gathering skills.", SkillsCards.MINING_BRONZE,
			SkillsCards.FISHING_BRONZE, SkillsCards.WOODCUTTING_BRONZE, SkillsCards.FARMING_BRONZE, SkillsCards.HUNTER_BRONZE);
		define("Artisan Skills", CardSet.SKILLS, "The eight artisan skills.", SkillsCards.SMITHING_BRONZE,
			SkillsCards.CRAFTING_BRONZE, SkillsCards.FLETCHING_BRONZE, SkillsCards.COOKING_BRONZE, SkillsCards.FIREMAKING_BRONZE, SkillsCards.HERBLORE_BRONZE,
			SkillsCards.CONSTRUCTION_BRONZE, SkillsCards.RUNECRAFT_BRONZE);
		define("Support Skills", CardSet.SKILLS, "The three support skills.", SkillsCards.AGILITY_BRONZE,
			SkillsCards.THIEVING_BRONZE, SkillsCards.SLAYER_BRONZE);
		define("God Wars Dungeon", CardSet.BOSSES, "The four generals and Nex.",
			BossesCards.GENERAL_GRAARDOR, BossesCards.K_RIL_TSUTSAROTH, BossesCards.COMMANDER_ZILYANA, BossesCards.KREE_ARRA,
			BossesCards.NEX);
		define("Dagannoth Kings", CardSet.BOSSES, "All three Dagannoth Kings.", BossesCards.DAGANNOTH_REX,
			BossesCards.DAGANNOTH_PRIME, BossesCards.DAGANNOTH_SUPREME);
		define("Wilderness Bosses", CardSet.BOSSES, "Bosses found in the Wilderness.", BossesCards.CALLISTO,
			BossesCards.VET_ION, BossesCards.VENENATIS, BossesCards.CHAOS_ELEMENTAL, BossesCards.CHAOS_FANATIC,
			BossesCards.CRAZY_ARCHAEOLOGIST, BossesCards.SCORPIA, BossesCards.KING_BLACK_DRAGON);
		define("The Desert Awakening", CardSet.BOSSES, "The four Desert Treasure II bosses.",
			BossesCards.DUKE_SUCELLUS, BossesCards.THE_LEVIATHAN, BossesCards.THE_WHISPERER, BossesCards.VARDORVIS);
		define("Raids", CardSet.BOSSES, "All three raids.", BossesCards.CHAMBERS_OF_XERIC,
			BossesCards.THEATRE_OF_BLOOD, BossesCards.TOMBS_OF_AMASCUT);
		define("Slayer Bosses", CardSet.BOSSES, "Bosses that appear as Slayer tasks.",
			BossesCards.ABYSSAL_SIRE, BossesCards.CERBERUS, BossesCards.KRAKEN, BossesCards.THERMONUCLEAR_SMOKE_DEVIL,
			BossesCards.ALCHEMICAL_HYDRA, BossesCards.GROTESQUE_GUARDIANS, BossesCards.ARAXXOR);
		define("Skilling Bosses", CardSet.BOSSES, "Bosses trained as skilling activities.",
			BossesCards.TEMPOROSS, BossesCards.WINTERTODT, BossesCards.ZALCANO, BossesCards.HESPORI);
		define("Low-Level Slayer", CardSet.SLAYER, "Slayer tasks up to level 52.", SlayerCards.CRAWLING_HAND,
			SlayerCards.BANSHEE, SlayerCards.ROCKSLUG, SlayerCards.COCKATRICE, SlayerCards.PYREFIEND, SlayerCards.BASILISK,
			SlayerCards.INFERNAL_MAGE, SlayerCards.BLOODVELD, SlayerCards.JELLY);
		define("Mid-Level Slayer", CardSet.SLAYER, "Slayer tasks from level 55 to 80.", SlayerCards.TUROTH,
			SlayerCards.ABERRANT_SPECTRE, SlayerCards.DUST_DEVIL, SlayerCards.KURASK, SlayerCards.GARGOYLE, SlayerCards.NECHRYAEL,
			SlayerCards.CAVE_HORROR, SlayerCards.SKELETAL_WYVERN, SlayerCards.WYRM);

		define("High-Level Slayer", CardSet.SLAYER, "Slayer tasks from level 77 upwards.",
			SlayerCards.DARK_BEAST, SlayerCards.ABYSSAL_DEMON, SlayerCards.SMOKE_DEVIL, SlayerCards.DRAKE, SlayerCards.HYDRA,
			SlayerCards.BRUTAL_BLACK_DRAGON, SlayerCards.ARAXYTE, SlayerCards.CAVE_KRAKEN, SlayerCards.ANCIENT_WYVERN);

		define("The Scimitar Ladder", CardSet.ITEMS, "The scimitar tier list.", ItemsCards.IRON_SCIMITAR,
			ItemsCards.STEEL_SCIMITAR, ItemsCards.MITHRIL_SCIMITAR, ItemsCards.ADAMANT_SCIMITAR, ItemsCards.RUNE_SCIMITAR,
			ItemsCards.DRAGON_SCIMITAR);
		define("Every Log", CardSet.ITEMS, "Every type of logs.", ItemsCards.LOGS, ItemsCards.OAK_LOGS,
			ItemsCards.WILLOW_LOGS, ItemsCards.MAPLE_LOGS, ItemsCards.YEW_LOGS, ItemsCards.MAGIC_LOGS, ItemsCards.REDWOOD_LOGS);
		define("Every Ore", CardSet.ITEMS, "Every type of ore.", ItemsCards.COPPER_ORE, ItemsCards.TIN_ORE,
			ItemsCards.IRON_ORE, ItemsCards.COAL, ItemsCards.GOLD_ORE, ItemsCards.MITHRIL_ORE, ItemsCards.ADAMANTITE_ORE,
			ItemsCards.RUNITE_ORE);
		define("Chambers of Xeric", CardSet.RAIDS_DROPS, "Drops from the Chambers of Xeric.", RaidsDropsCards.OLM_PET,
			RaidsDropsCards.METAMORPHIC_DUST, RaidsDropsCards.TWISTED_BOW, RaidsDropsCards.ELDER_MAUL, RaidsDropsCards.KODAI_INSIGNIA,
			RaidsDropsCards.DRAGON_CLAWS, RaidsDropsCards.ANCESTRAL_HAT, RaidsDropsCards.ANCESTRAL_ROBE_TOP, RaidsDropsCards.ANCESTRAL_ROBE_BOTTOM,
			RaidsDropsCards.DINHS_BULWARK, RaidsDropsCards.RAIDS_PRAYERSCROLL, RaidsDropsCards.RAIDS_PRAYERSCROLL_AUGURY,
			RaidsDropsCards.DRAGON_HUNTER_CROSSBOW, RaidsDropsCards.TWISTED_BUCKLER, RaidsDropsCards.RAIDS_PRAYERSCROLL_PRESERVE,
			RaidsDropsCards.RAIDS_ANCIENT_RELIC, RaidsDropsCards.ONYX, RaidsDropsCards.ANCESTRAL_ROBES_TWISTED_KIT,
			RaidsDropsCards.COX_CHALLENGE_CAPE_T1, RaidsDropsCards.COX_CHALLENGE_CAPE_T2, RaidsDropsCards.COX_CHALLENGE_CAPE_T3,
			RaidsDropsCards.COX_CHALLENGE_CAPE_T4, RaidsDropsCards.COX_CHALLENGE_CAPE_T5);
		define("Theatre of Blood", CardSet.RAIDS_DROPS, "Drops from the Theatre of Blood.", RaidsDropsCards.VERZIKPET,
			RaidsDropsCards.SCYTHE_OF_VITUR_UNCHARGED, RaidsDropsCards.GHRAZI_RAPIER, RaidsDropsCards.SANGUINESTI_STAFF_UNCHARGED,
			RaidsDropsCards.JUSTICIAR_FACEGUARD, RaidsDropsCards.JUSTICIAR_CHESTGUARD, RaidsDropsCards.JUSTICIAR_LEG_GUARDS,
			RaidsDropsCards.INFERNAL_DEFENDER_HILT, RaidsDropsCards.VIAL_BLOOD, RaidsDropsCards.SINHAZA_SHROUD_TIER1,
			RaidsDropsCards.SINHAZA_SHROUD_TIER2, RaidsDropsCards.SINHAZA_SHROUD_TIER3, RaidsDropsCards.SINHAZA_SHROUD_TIER4,
			RaidsDropsCards.SINHAZA_SHROUD_TIER5, RaidsDropsCards.SANGUINE_DUST, RaidsDropsCards.HOLY_ORNAMENT_KIT,
			RaidsDropsCards.SANGUINE_ORNAMENT_KIT);
		define("Tombs of Amascut", CardSet.RAIDS_DROPS, "Drops from the Tombs of Amascut.", RaidsDropsCards.WARDENPET_TUMEKEN,
			RaidsDropsCards.TUMEKENS_SHADOW_UNCHARGED, RaidsDropsCards.ELIDINIS_WARD, RaidsDropsCards.MASORI_MASK, RaidsDropsCards.MASORI_BODY,
			RaidsDropsCards.MASORI_CHAPS, RaidsDropsCards.LIGHTBEARER, RaidsDropsCards.OSMUMTENS_FANG, RaidsDropsCards.THREAD_OF_ELIDINIS,
			RaidsDropsCards.BREACH_OF_THE_SCARAB, RaidsDropsCards.EYE_OF_THE_CORRUPTOR, RaidsDropsCards.JEWEL_OF_THE_SUN, RaidsDropsCards.JEWEL_OF_AMASCUT,
			RaidsDropsCards.ELIDINIS_WARD_ORNAMENT_KIT, RaidsDropsCards.OSMUMTENS_FANG_ORNAMENT_KIT, RaidsDropsCards.AVAS_ASSEMBLER_ORNAMENT_KIT,
			RaidsDropsCards.TOA_RUNE_CACHE, RaidsDropsCards.ICTHLARINS_SHROUD_1, RaidsDropsCards.ICTHLARINS_SHROUD_2, RaidsDropsCards.ICTHLARINS_SHROUD_3,
			RaidsDropsCards.ICTHLARINS_SHROUD_4, RaidsDropsCards.ICTHLARINS_SHROUD_5, RaidsDropsCards.TOA_PET_MORPH_AKKHA, RaidsDropsCards.TOA_PET_MORPH_BABA,
			RaidsDropsCards.TOA_PET_MORPH_KEPHRI, RaidsDropsCards.TOA_PET_MORPH_ZEBAK, RaidsDropsCards.TOA_PET_MORPH_WARDENS);

		define("Beginner Clues", CardSet.CLUE_DROPS, "Drops from Beginner Clue Scrolls.", ClueCards.MOLE_SLIPPERS,
				ClueCards.FROG_SLIPPERS, ClueCards.BEAR_SLIPPERS, ClueCards.DEMON_SLIPPERS, ClueCards.JESTER_CAPE,
				ClueCards.SHOULDER_PARROT, ClueCards.MONK_ROBETOP_T, ClueCards.MONK_ROBEBOTTOM_T, ClueCards.AMULET_OF_DEFENCE_T,
				ClueCards.SANDWICH_LADY_HAT, ClueCards.SANDWICH_LADY_TOP, ClueCards.SANDWICH_LADY_BOTTOM,
				ClueCards.RUNE_SCIMITAR_ORNAMENT_KIT_GUTHIX, ClueCards.RUNE_SCIMITAR_ORNAMENT_KIT_SARADOMIN,
				ClueCards.RUNE_SCIMITAR_ORNAMENT_KIT_ZAMORAK);

		define("Easy Clues", CardSet.CLUE_DROPS, "Drops from Easy Clue Scrolls.", ClueCards.WILDERNESS_CAPE_ZERO,
				ClueCards.WILDERNESS_CAPE_I, ClueCards.WILDERNESS_CAPE_X, ClueCards.CAPE_OF_SKULLS,
				ClueCards.CHEFS_HAT_GOLD, ClueCards.GOLDEN_APRON, ClueCards.WOODEN_SHIELD_GOLD,
				ClueCards.BLACK_FULL_HELM_TRIM, ClueCards.BLACK_PLATEBODY_TRIM, ClueCards.BLACK_PLATELEGS_TRIM,
				ClueCards.BLACK_PLATESKIRT_TRIM, ClueCards.BLACK_KITESHIELD_TRIM, ClueCards.BLACK_FULL_HELM_GOLD,
				ClueCards.BLACK_PLATEBODY_GOLD, ClueCards.BLACK_PLATELEGS_GOLD, ClueCards.BLACK_PLATESKIRT_GOLD,
				ClueCards.BLACK_KITESHIELD_GOLD, ClueCards.BLACK_HERALDIC_KITESHIELD1, ClueCards.BLACK_HERALDIC_KITESHIELD2,
				ClueCards.BLACK_HERALDIC_KITESHIELD3, ClueCards.BLACK_HERALDIC_KITESHIELD4, ClueCards.BLACK_HERALDIC_KITESHIELD5,
				ClueCards.TRAIL_HERALDIC_HELM_1_BLACK, ClueCards.TRAIL_HERALDIC_HELM_2_BLACK, ClueCards.TRAIL_HERALDIC_HELM_3_BLACK,
				ClueCards.TRAIL_HERALDIC_HELM_4_BLACK, ClueCards.TRAIL_HERALDIC_HELM_5_BLACK, ClueCards.BLACK_PLATEBODY_H1,
				ClueCards.BLACK_PLATEBODY_H2, ClueCards.BLACK_PLATEBODY_H3, ClueCards.BLACK_PLATEBODY_H4,
				ClueCards.BLACK_PLATEBODY_H5, ClueCards.STEEL_FULLHELM_TRIM, ClueCards.STEEL_PLATEBODY_TRIM,
				ClueCards.STEEL_PLATELEGS_TRIM, ClueCards.STEEL_PLATESKIRT_TRIM, ClueCards.STEEL_KITESHIELD_TRIM,
				ClueCards.STEEL_FULLHELM_GOLD, ClueCards.STEEL_PLATEBODY_GOLD, ClueCards.STEEL_PLATELEGS_GOLD,
				ClueCards.STEEL_PLATESKIRT_GOLD, ClueCards.STEEL_KITESHIELD_GOLD, ClueCards.IRON_PLATEBODY_TRIM,
				ClueCards.IRON_PLATELEGS_TRIM, ClueCards.IRON_PLATESKIRT_TRIM, ClueCards.IRON_KITESHIELD_TRIM,
				ClueCards.IRON_FULLHELM_TRIM, ClueCards.IRON_PLATEBODY_GOLD, ClueCards.IRON_PLATELEGS_GOLD,
				ClueCards.IRON_PLATESKIRT_GOLD, ClueCards.IRON_KITESHIELD_GOLD, ClueCards.IRON_FULLHELM_GOLD,
				ClueCards.BRONZE_PLATEBODY_TRIM, ClueCards.BRONZE_PLATELEGS_TRIM, ClueCards.BRONZE_PLATESKIRT_TRIM,
				ClueCards.BRONZE_KITESHIELD_TRIM, ClueCards.BRONZE_FULLHELM_TRIM, ClueCards.BRONZE_PLATEBODY_GOLD,
				ClueCards.BRONZE_PLATELEGS_GOLD, ClueCards.BRONZE_PLATESKIRT_GOLD, ClueCards.BRONZE_KITESHIELD_GOLD,
				ClueCards.BRONZE_FULLHELM_GOLD, ClueCards.STUDDED_BODY_TRIM_GOLD, ClueCards.STUDDED_CHAPS_TRIM_GOLD,
				ClueCards.STUDDED_BODY_TRIM_FUR, ClueCards.STUDDED_CHAPS_TRIM_FUR, ClueCards.LEATHER_BODY_GOLD,
				ClueCards.LEATHER_CHAPS_GOLD, ClueCards.BLUEWIZHAT_TRIM_GOLD, ClueCards.WIZARDS_ROBE_TRIM_GOLD,
				ClueCards.BLUE_SKIRT_TRIM_GOLD, ClueCards.BLUEWIZHAT_TRIM, ClueCards.WIZARDS_ROBE_TRIM,
				ClueCards.BLUE_SKIRT_TRIM, ClueCards.BLACK_WIZARD_HAT_GOLD, ClueCards.BLACK_WIZARDS_ROBE_GOLD,
				ClueCards.BLACK_SKIRT_GOLD, ClueCards.BLACK_WIZARD_HAT_TRIM, ClueCards.BLACK_WIZARDS_ROBE_TRIM,
				ClueCards.BLACK_SKIRT_TRIM, ClueCards.MONK_ROBETOP_GOLD, ClueCards.MONKROBEBOTTOM_GOLD,
				ClueCards.TRAIL_SARADOMIN_ROBE_T, ClueCards.TRAIL_SARADOMIN_ROBE_L, ClueCards.TRAIL_GUTHIX_ROBE_T,
				ClueCards.TRAIL_GUTHIX_ROBE_L, ClueCards.TRAIL_ZAMORAK_ROBE_T, ClueCards.TRAIL_ZAMORAK_ROBE_L,
				ClueCards.TRAIL_ANCIENT_ROBE_T, ClueCards.TRAIL_ANCIENT_ROBE_L, ClueCards.TRAIL_ARMADYL_ROBE_T,
				ClueCards.TRAIL_ARMADYL_ROBE_L, ClueCards.TRAIL_BANDOS_ROBE_T, ClueCards.TRAIL_BANDOS_ROBE_L,
				ClueCards.BOB_SHIRT_RED, ClueCards.BOB_SHIRT_GREEN, ClueCards.BOB_SHIRT_BLUE,
				ClueCards.BOB_SHIRT_BLACK, ClueCards.BOB_SHIRT_PURPLE, ClueCards.HIGHWAYMAN_MASK,
				ClueCards.BERRET_BLUE, ClueCards.BERRET_BLACK, ClueCards.BERRET_WHITE,
				ClueCards.BERRET_RED, ClueCards.POWDERED_WIG, ClueCards.BEANIE_HAT,
				ClueCards.IMP_MASK, ClueCards.GOBLIN_MASK, ClueCards.TRAIL_SLEEPING_CAP,
				ClueCards.FLARED_TROUSERS, ClueCards.TRAIL_PANTALOONS, ClueCards.BLACK_CANE,
				ClueCards.STAFF_OF_BOB_THE_CAT, ClueCards.TRAIL_ELEGANT_SHIRT_MALE_R, ClueCards.TRAIL_ELEGANT_SHIRT_FEMALE_R,
				ClueCards.TRAIL_ELEGANT_PANTS_MALE_R, ClueCards.TRAIL_ELEGANT_PANTS_FEMALE_R, ClueCards.TRAIL_ELEGANT_SHIRT_MALE_G,
				ClueCards.TRAIL_ELEGANT_SHIRT_FEMALE_G, ClueCards.TRAIL_ELEGANT_PANTS_MALE_G, ClueCards.TRAIL_ELEGANT_PANTS_FEMALE_G,
				ClueCards.TRAIL_ELEGANT_SHIRT_MALE_B, ClueCards.TRAIL_ELEGANT_SHIRT_FEMALE_B, ClueCards.TRAIL_ELEGANT_PANTS_MALE_B,
				ClueCards.TRAIL_ELEGANT_PANTS_FEMALE_B, ClueCards.AMULET_OF_MAGIC_TRIM, ClueCards.AMULET_OF_POWER_TRIM,
				ClueCards.HAM_JOINT, ClueCards.RAIN_BOW, ClueCards.WILLOW_COMP_BOW);

		define("Medium Clues", CardSet.CLUE_DROPS, "Drops from Medium Clue Scrolls.", ClueCards.BOOTS_RANGER,
				ClueCards.BOOTS_WIZARD, ClueCards.HOLY_SANDALS, ClueCards.CLIMBING_BOOTS_GOLD,
				ClueCards.SPIKED_MANACLES, ClueCards.ADAMANT_FULLHELM_TRIM, ClueCards.ADAMANT_PLATEBODY_TRIM,
				ClueCards.ADAMANT_PLATELEGS_TRIM, ClueCards.ADAMANT_PLATESKIRT_TRIM, ClueCards.ADAMANT_KITESHIELD_TRIM,
				ClueCards.ADAMANT_FULLHELM_GOLD, ClueCards.ADAMANT_PLATEBODY_GOLD, ClueCards.ADAMANT_PLATELEGS_GOLD,
				ClueCards.ADAMANT_PLATESKIRT_GOLD, ClueCards.ADAMANT_KITESHIELD_GOLD, ClueCards.ADAMANT_HERALDIC_KITESHIELD1,
				ClueCards.ADAMANT_HERALDIC_KITESHIELD2, ClueCards.ADAMANT_HERALDIC_KITESHIELD3, ClueCards.ADAMANT_HERALDIC_KITESHIELD4,
				ClueCards.ADAMANT_HERALDIC_KITESHIELD5, ClueCards.TRAIL_HERALDIC_HELM_1_ADAMANT, ClueCards.TRAIL_HERALDIC_HELM_2_ADAMANT,
				ClueCards.TRAIL_HERALDIC_HELM_3_ADAMANT, ClueCards.TRAIL_HERALDIC_HELM_4_ADAMANT, ClueCards.TRAIL_HERALDIC_HELM_5_ADAMANT,
				ClueCards.ADAMANT_PLATEBODY_H1, ClueCards.ADAMANT_PLATEBODY_H2, ClueCards.ADAMANT_PLATEBODY_H3,
				ClueCards.ADAMANT_PLATEBODY_H4, ClueCards.ADAMANT_PLATEBODY_H5, ClueCards.MITHRIL_FULLHELM_GOLD,
				ClueCards.MITHRIL_PLATEBODY_GOLD, ClueCards.MITHRIL_PLATELEGS_GOLD, ClueCards.MITHRIL_PLATESKIRT_GOLD,
				ClueCards.MITHRIL_KITESHIELD_GOLD, ClueCards.MITHRIL_FULLHELM_TRIM, ClueCards.MITHRIL_PLATEBODY_TRIM,
				ClueCards.MITHRIL_PLATELEGS_TRIM, ClueCards.MITHRIL_PLATESKIRT_TRIM, ClueCards.MITHRIL_KITESHIELD_TRIM,
				ClueCards.GREEN_DRAGONHIDE_BODY_GOLD, ClueCards.GREEN_DRAGONHIDE_CHAPS_GOLD, ClueCards.GREEN_DRAGONHIDE_BODY_TRIM,
				ClueCards.GREEN_DRAGONHIDE_CHAPS_TRIM, ClueCards.TRAIL_SARADOMIN_MITRE, ClueCards.TRAIL_SARADOMIN_CLOAK,
				ClueCards.TRAIL_GUTHIX_MITRE, ClueCards.TRAIL_GUTHIX_CLOAK, ClueCards.TRAIL_ZAMORAK_MITRE,
				ClueCards.TRAIL_ZAMORAK_CLOAK, ClueCards.TRAIL_ANCIENT_MITRE, ClueCards.TRAIL_ANCIENT_CLOAK,
				ClueCards.TRAIL_ANCIENT_SCARF, ClueCards.TRAIL_ANCIENT_STAFF, ClueCards.TRAIL_ARMADYL_MITRE,
				ClueCards.TRAIL_ARMADYL_CLOAK, ClueCards.TRAIL_ARMADYL_SCARF, ClueCards.TRAIL_ARMADYL_STAFF,
				ClueCards.TRAIL_BANDOS_MITRE, ClueCards.TRAIL_BANDOS_CLOAK, ClueCards.TRAIL_BANDOS_SCARF,
				ClueCards.TRAIL_BANDOS_STAFF, ClueCards.BOATER_RED, ClueCards.BOATER_GREEN,
				ClueCards.BOATER_ORANGE, ClueCards.BOATER_BLACK, ClueCards.BOATER_BLUE,
				ClueCards.BOATER_PINK, ClueCards.BOATER_PURPLE, ClueCards.BOATER_WHITE,
				ClueCards.HEADBAND_RED, ClueCards.HEADBAND_BLACK, ClueCards.HEADBAND_BROWN,
				ClueCards.HEADBAND_WHITE, ClueCards.HEADBAND_BLUE, ClueCards.HEADBAND_GOLD,
				ClueCards.HEADBAND_PINK, ClueCards.HEADBAND_GREEN, ClueCards.TRAIL_CRIER_HAT,
				ClueCards.TOWN_CRIER_COAT, ClueCards.TOWN_CRIER_BELL, ClueCards.ADAMANT_CANE,
				ClueCards.ARCEUUS_BANNER, ClueCards.PISCARILIUS_BANNER, ClueCards.HOSIDIUS_BANNER,
				ClueCards.SHAYZIEN_BANNER, ClueCards.LOVAKENGJ_BANNER, ClueCards.CABBAGE_SHIELD,
				ClueCards.BLACK_UNICORN_MASK, ClueCards.WHITE_UNICORN_MASK, ClueCards.CAT_MASK,
				ClueCards.PENGUIN_MASK, ClueCards.LEPRECHAUN_HAT, ClueCards.BLACK_LEPRECHAUN_HAT,
				ClueCards.WOLF_MASK, ClueCards.WOLF_CLOAK, ClueCards.TRAIL_ELEGANT_SHIRT_MALE_P,
				ClueCards.TRAIL_ELEGANT_SHIRT_FEMALE_P, ClueCards.TRAIL_ELEGANT_PANTS_MALE_P, ClueCards.TRAIL_ELEGANT_PANTS_FEMALE_P,
				ClueCards.TRAIL_ELEGANT_SHIRT_MALE, ClueCards.TRAIL_ELEGANT_SHIRT_FEMALE, ClueCards.TRAIL_ELEGANT_PANTS_MALE,
				ClueCards.TRAIL_ELEGANT_PANTS_FEMALE, ClueCards.TRAIL_ELEGANT_SHIRT_MALE_PINK, ClueCards.TRAIL_ELEGANT_SHIRT_FEMALE_PINK,
				ClueCards.TRAIL_ELEGANT_PANTS_MALE_PINK, ClueCards.TRAIL_ELEGANT_PANTS_FEMALE_PINK, ClueCards.TRAIL_ELEGANT_SHIRT_MALE_GOLD,
				ClueCards.TRAIL_ELEGANT_SHIRT_FEMALE_GOLD, ClueCards.TRAIL_ELEGANT_PANTS_MALE_GOLD, ClueCards.TRAIL_ELEGANT_PANTS_FEMALE_GOLD,
				ClueCards.GNOMISH_FIRELIGHTER, ClueCards.TRAIL_AMULET_OF_STRENGTH, ClueCards.YEW_COMP_BOW);


		define("Hard Clues", CardSet.CLUE_DROPS, "Drops from Hard Clue Scrolls.", 	ClueCards.ROBIN_HOOD_HAT, ClueCards.DRAGON_BOOTS_ORNAMENT_KIT, ClueCards.RUNE_DEFENDER_ORNAMENT_KIT,
				ClueCards.TZHAAR_KET_OM_ORNAMENT_KIT, ClueCards.BERSERKER_NECKLACE_ORNAMENT_KIT, ClueCards.RUNE_FULLHELM_TRIM,
				ClueCards.RUNE_PLATEBODY_TRIM, ClueCards.RUNE_PLATELEGS_TRIM, ClueCards.RUNE_PLATESKIRT_TRIM,
				ClueCards.RUNE_KITESHIELD_TRIM, ClueCards.RUNE_FULLHELM_GOLD, ClueCards.RUNE_PLATEBODY_GOLD,
				ClueCards.RUNE_PLATELEGS_GOLD, ClueCards.RUNE_PLATESKIRT_GOLD, ClueCards.RUNE_KITESHIELD_GOLD,
				ClueCards.RUNE_FULL_HELM_ZAMORAK, ClueCards.RUNE_PLATEBODY_ZAMORAK, ClueCards.RUNE_PLATELEGS_ZAMORAK,
				ClueCards.RUNE_PLATESKIRT_ZAMORAK, ClueCards.RUNE_KITESHIELD_ZAMORAK, ClueCards.RUNE_FULL_HELM_GUTHIX,
				ClueCards.RUNE_PLATEBODY_GUTHIX, ClueCards.RUNE_PLATELEGS_GUTHIX, ClueCards.RUNE_PLATESKIRT_GUTHIX,
				ClueCards.RUNE_KITESHIELD_GUTHIX, ClueCards.RUNE_FULL_HELM_SARADOMIN, ClueCards.RUNE_PLATEBODY_SARADOMIN,
				ClueCards.RUNE_PLATELEGS_SARADOMIN, ClueCards.RUNE_PLATESKIRT_SARADOMIN, ClueCards.RUNE_KITESHIELD_SARADOMIN,
				ClueCards.RUNE_FULL_HELM_ANCIENT, ClueCards.RUNE_PLATEBODY_ANCIENT, ClueCards.RUNE_PLATELEGS_ANCIENT,
				ClueCards.RUNE_PLATESKIRT_ANCIENT, ClueCards.RUNE_KITESHIELD_ANCIENT, ClueCards.RUNE_FULL_HELM_ARMADYL,
				ClueCards.RUNE_PLATEBODY_ARMADYL, ClueCards.RUNE_PLATELEGS_ARMADYL, ClueCards.RUNE_PLATESKIRT_ARMADYL,
				ClueCards.RUNE_KITESHIELD_ARMADYL, ClueCards.RUNE_FULL_HELM_BANDOS, ClueCards.RUNE_PLATEBODY_BANDOS,
				ClueCards.RUNE_PLATELEGS_BANDOS, ClueCards.RUNE_PLATESKIRT_BANDOS, ClueCards.RUNE_KITESHIELD_BANDOS,
				ClueCards.RUNE_HERALDIC_KITESHIELD1, ClueCards.RUNE_HERALDIC_KITESHIELD2, ClueCards.RUNE_HERALDIC_KITESHIELD3,
				ClueCards.RUNE_HERALDIC_KITESHIELD4, ClueCards.RUNE_HERALDIC_KITESHIELD5, ClueCards.TRAIL_HERALDIC_HELM_1_RUNE,
				ClueCards.TRAIL_HERALDIC_HELM_2_RUNE, ClueCards.TRAIL_HERALDIC_HELM_3_RUNE, ClueCards.TRAIL_HERALDIC_HELM_4_RUNE,
				ClueCards.TRAIL_HERALDIC_HELM_5_RUNE,	ClueCards.RUNE_PLATEBODY_H1, ClueCards.RUNE_PLATEBODY_H2, ClueCards.RUNE_PLATEBODY_H3,
				ClueCards.RUNE_PLATEBODY_H4, ClueCards.RUNE_PLATEBODY_H5, ClueCards.TRAIL_SARADOMIN_COIF,
				ClueCards.TRAIL_SARADOMIN_CHEST, ClueCards.TRAIL_SARADOMIN_CHAPS, ClueCards.TRAIL_SARADOMIN_VAMBRACES,
				ClueCards.TRAIL_SARADOMIN_BOOTS, ClueCards.BLESSED_DHIDE_SHIELD_SARADOMIN, ClueCards.TRAIL_GUTHIX_COIF,
				ClueCards.TRAIL_GUTHIX_CHEST, ClueCards.TRAIL_GUTHIX_CHAPS, ClueCards.TRAIL_GUTHIX_VAMBRACES,
				ClueCards.TRAIL_GUTHIX_BOOTS, ClueCards.BLESSED_DHIDE_SHIELD_GUTHIX, ClueCards.TRAIL_ZAMORAK_COIF,
				ClueCards.TRAIL_ZAMORAK_CHEST, ClueCards.TRAIL_ZAMORAK_CHAPS, ClueCards.TRAIL_ZAMORAK_VAMBRACES,
				ClueCards.TRAIL_ZAMORAK_BOOTS, ClueCards.BLESSED_DHIDE_SHIELD_ZAMORAK, ClueCards.TRAIL_BANDOS_COIF,
				ClueCards.TRAIL_BANDOS_CHEST, ClueCards.TRAIL_BANDOS_CHAPS, ClueCards.TRAIL_BANDOS_VAMBRACES,
				ClueCards.TRAIL_BANDOS_BOOTS, ClueCards.BLESSED_DHIDE_SHIELD_BANDOS, ClueCards.TRAIL_ARMADYL_COIF,
				ClueCards.TRAIL_ARMADYL_CHEST, ClueCards.TRAIL_ARMADYL_CHAPS, ClueCards.TRAIL_ARMADYL_VAMBRACES,
				ClueCards.TRAIL_ARMADYL_BOOTS, ClueCards.BLESSED_DHIDE_SHIELD_ARMADYL, ClueCards.TRAIL_ANCIENT_COIF,
				ClueCards.TRAIL_ANCIENT_CHEST, ClueCards.TRAIL_ANCIENT_CHAPS, ClueCards.TRAIL_ANCIENT_VAMBRACES,
				ClueCards.TRAIL_ANCIENT_BOOTS, 	ClueCards.BLESSED_DHIDE_SHIELD_ANCIENT, ClueCards.RED_DRAGONHIDE_BODY_TRIM, ClueCards.RED_DRAGONHIDE_CHAPS_TRIM,
				ClueCards.RED_DRAGONHIDE_BODY_GOLD, ClueCards.RED_DRAGONHIDE_CHAPS_GOLD, ClueCards.BLUE_DRAGONHIDE_BODY_TRIM,
				ClueCards.BLUE_DRAGONHIDE_CHAPS_TRIM, ClueCards.BLUE_DRAGONHIDE_BODY_GOLD, ClueCards.BLUE_DRAGONHIDE_CHAPS_GOLD,
				ClueCards.ENCHANTED_HAT, ClueCards.ENCHANTED_TOP, ClueCards.ENCHANTED_ROBE,
				ClueCards.TRAIL_SARADOMIN_SCARF, ClueCards.TRAIL_SARADOMIN_STAFF, ClueCards.TRAIL_GUTHIX_SCARF,
				ClueCards.TRAIL_GUTHIX_STAFF, ClueCards.TRAIL_ZAMORAK_SCARF, ClueCards.TRAIL_ZAMORAK_STAFF,
				ClueCards.ZOMBIE_HEAD, ClueCards.CYCLOPS_HEAD, ClueCards.TRAIL_PIRATE_HAT,
				ClueCards.CAVALIER_RED, ClueCards.CAVALIER_WHITE, ClueCards.CAVALIER_NAVY,
				ClueCards.CAVALIER_TAN, ClueCards.CAVALIER_DARK, ClueCards.CAVALIER_BLACK,
				ClueCards.PITH_HELMET, ClueCards.EXPLORER_BACKPACK, ClueCards.THIEVING_BAG,
				ClueCards.DRAGONMASK_GREEN, ClueCards.DRAGONMASK_BLUE, ClueCards.DRAGONMASK_RED,
				ClueCards.DRAGONMASK_BLACK, ClueCards.NUNCHUCKS, ClueCards.DUAL_SAI,
				ClueCards.RUNE_CANE, ClueCards.TRAIL_GLORY, ClueCards.MAGIC_COMP_BOW);

		define("Elite Clues", CardSet.CLUE_DROPS, "Drops from Elite Clue Scrolls.", ClueCards.FURY_ORNAMENT_KIT,
				ClueCards.DRAGON_CHAINBODY_ORNAMENT_KIT, ClueCards.DRAGON_LEGS_SKIRT_ORNAMENT_KIT, ClueCards.DRAGON_SQ_SHIELD_ORNAMENT_KIT,
				ClueCards.DRAGON_FULL_HELM_ORNAMENT_KIT, ClueCards.DRAGON_SCIMITAR_ORNAMENT_KIT,
				ClueCards.LIGHT_INFINITY_COLOUR_KIT, ClueCards.DARK_INFINITY_COLOUR_KIT, ClueCards.HOLY_WRAPS,
				ClueCards.RANGER_GLOVES, ClueCards.RANGERS_TUNIC, ClueCards.RANGERS_TIGHTS,
				ClueCards.BLACK_DRAGONHIDE_BODY_GOLD, ClueCards.BLACK_DRAGONHIDE_CHAPS_GOLD, ClueCards.BLACK_DRAGONHIDE_BODY_TRIM,
				ClueCards.BLACK_DRAGONHIDE_CHAPS_TRIM, 	ClueCards.ROYAL_CROWN, ClueCards.ROYAL_SCEPTRE, ClueCards.ROYAL_GOWN_TOP,
				ClueCards.ROYAL_GOWN_BOTTOM, ClueCards.MUSKETEER_HAT, ClueCards.MUSKETEER_TABARD,
				ClueCards.MUSKETEER_PANTS, ClueCards.DARK_TUXEDO_JACKET, ClueCards.DARK_TROUSERS,
				ClueCards.DARK_TUXEDO_SHOES, ClueCards.DARK_TUXEDO_CUFFS, ClueCards.DARK_BOW_TIE,
				ClueCards.LIGHT_TUXEDO_JACKET, ClueCards.LIGHT_TROUSERS, ClueCards.LIGHT_TUXEDO_SHOES,
				ClueCards.LIGHT_TUXEDO_CUFFS, ClueCards.LIGHT_BOW_TIE, ClueCards.ARCEUUS_SCARF,
				ClueCards.HOSIDIUS_SCARF, ClueCards.PISCARILIUS_SCARF, 	ClueCards.SHAYZIEN_SCARF, ClueCards.LOVAKENGJ_SCARF, ClueCards.DRAGONMASK_BRONZE,
				ClueCards.DRAGONMASK_IRON, ClueCards.DRAGONMASK_STEEL, ClueCards.DRAGONMASK_MITH,
				ClueCards.DRAGONMASK_ADAMANT, ClueCards.DRAGONMASK_RUNE, ClueCards.KATANA,
				ClueCards.DRAGON_CANE, ClueCards.BRIEFCASE, ClueCards.BUCKET_HELM,
				ClueCards.BLACKSMITH_HELM, ClueCards.DEERSTALKER, ClueCards.AFRO,
				ClueCards.BIG_PIRATE_HAT, ClueCards.TOP_HAT, ClueCards.MONOCLE,
				ClueCards.SAGACIOUS_SPECTACLES, ClueCards.FREMENNIK_KILT, ClueCards.GIANT_BOOT,
				ClueCards.URIS_HAT);

		define("Master Clues", CardSet.CLUE_DROPS, "Drops from Master Clue Scrolls.", 	ClueCards.BLOODHOUND,
				ClueCards.ARMADYL_GODSWORD_ORNAMENT_KIT, ClueCards.BANDOS_GODSWORD_ORNAMENT_KIT,
				ClueCards.SARADOMIN_GODSWORD_ORNAMENT_KIT, ClueCards.ZAMORAK_GODSWORD_ORNAMENT_KIT, ClueCards.OCCULT_ORNAMENT_KIT,
				ClueCards.TORTURE_ORNAMENT_KIT, ClueCards.ANGUISH_ORNAMENT_KIT, ClueCards.DRAGON_DEFENDER_ORNAMENT_KIT,
				ClueCards.DRAGON_KITESHIELD_ORNAMENT_KIT, ClueCards.DRAGON_PLATEBODY_ORNAMENT_KIT, ClueCards.TORMENTED_ORNAMENT_KIT,
				ClueCards.HOOD_OF_DARKNESS, ClueCards.ROBE_TOP_OF_DARKNESS, ClueCards.ROBE_BOTTOM_OF_DARKNESS,
				ClueCards.GLOVES_OF_DARKNESS, ClueCards.BOOTS_OF_DARKNESS, ClueCards.SAMURAI_KASA,
				ClueCards.SAMURAI_SHIRT, ClueCards.SAMURAI_GREAVES, ClueCards.SAMURAI_BOOTS, ClueCards.SAMURAI_GLOVES,
				ClueCards.ANKOU_MASK, ClueCards.ANKOU_TOP, ClueCards.ANKOU_GLOVES, ClueCards.ANKOU_SOCKS,
				ClueCards.ANKOUS_LEGGINGS, ClueCards.MUMMYS_HEAD, ClueCards.MUMMYS_FEET,
				ClueCards.MUMMYS_HANDS, ClueCards.MUMMYS_LEGS, ClueCards.MUMMYS_BODY,
				ClueCards.SHAYZIEN_HOOD, ClueCards.HOSIDIUS_HOOD, ClueCards.ARCEUUS_HOOD,
				ClueCards.PISCARILIUS_HOOD, ClueCards.LOVAKENGJ_HOOD, ClueCards.LESSER_DEMON_MASK,
				ClueCards.GREATER_DEMON_MASK, ClueCards.BLACK_DEMON_MASK, ClueCards.JUNGLE_DEMON_MASK,
				ClueCards.OLD_DEMON_MASK, ClueCards.LEFT_EYE_PATCH, ClueCards.BOWL_WIG,
				ClueCards.ALE_OF_THE_GODS, ClueCards.OBSIDIAN_CAPE_R, ClueCards.HALF_MOON_SPECTACLES,
				ClueCards.FANCY_TIARA);

		define("Megarares Clues", CardSet.CLUE_DROPS, "Megarare clue drops from Hard, Elite and Master Clue Scrolls.",
				ClueCards.THIRD_AGE_PICKAXE, ClueCards.THIRD_AGE_AXE, ClueCards.THIRD_AGE_LONGSWORD,
				ClueCards.THIRD_AGE_WAND, ClueCards.THIRD_AGE_CLOAK, ClueCards.THIRD_AGE_BOW,
				ClueCards.THIRD_AGE_RANGE_COIF, ClueCards.THIRD_AGE_RANGE_TOP, ClueCards.THIRD_AGE_RANGE_LEGS,
				ClueCards.THIRD_AGE_VAMBRACES, ClueCards.THIRD_AGE_ROBE_TOP, ClueCards.THIRD_AGE_ROBE_BOTTOM,
				ClueCards.THIRD_AGE_MAGE_HAT, ClueCards.THIRD_AGE_AMULET, ClueCards.THIRD_AGE_PLATESKIRT,
				ClueCards.THIRD_AGE_PLATELEGS, ClueCards.THIRD_AGE_PLATEBODY, ClueCards.THIRD_AGE_FULL_HELMET,
				ClueCards.THIRD_AGE_KITESHIELD, ClueCards.THIRD_AGE_DRUIDIC_ROBE_BOTTOMS, ClueCards.THIRD_AGE_DRUIDIC_ROBE_TOP,
				ClueCards.THIRD_AGE_DRUIDIC_STAFF, ClueCards.THIRD_AGE_DRUIDIC_CLOAK, ClueCards.RING_3RD_AGE,
				ClueCards.GILDED_SCIMITAR, ClueCards.GILDED_BOOTS, ClueCards.GILDED_PLATEBODY,
				ClueCards.GILDED_PLATELEGS, ClueCards.GILDED_PLATESKIRT, ClueCards.GILDED_FULL_HELM,
				ClueCards.GILDED_KITESHIELD, ClueCards.GILDED_MED_HELM, ClueCards.GILDED_CHAINBODY,
				ClueCards.GILDED_SQ_SHIELD, ClueCards.GILDED_2H_SWORD, ClueCards.GILDED_SPEAR,
				ClueCards.GILDED_HASTA, ClueCards.GILDED_COIF, ClueCards.GILDED_DHIDE_VAMBRACES,
				ClueCards.GILDED_DHIDE_BODY, ClueCards.GILDED_DHIDE_CHAPS, ClueCards.GILDED_PICKAXE,
				ClueCards.GILDED_AXE, ClueCards.GILDED_SPADE, ClueCards.BUCKET_HELM_GOLD,
				ClueCards.RING_OF_COINS, ClueCards.NATURE_RING, ClueCards.LAVA_DRAGON_MASK);

		define("Shared Clue Rewards", CardSet.CLUE_DROPS, "Clue rewards that are shared between multiple tiers.",
				ClueCards.SARADOMIN_PAGE_1, ClueCards.ZAMORAK_PAGE_1, ClueCards.GUTHIX_PAGE_1,
				ClueCards.BANDOS_PAGE_1, ClueCards.ARMADYL_PAGE_1, ClueCards.ANCIENT_PAGE_1,
				ClueCards.SARADOMIN_PAGE_2, ClueCards.ZAMORAK_PAGE_2, ClueCards.GUTHIX_PAGE_2,
				ClueCards.BANDOS_PAGE_2, ClueCards.ARMADYL_PAGE_2, ClueCards.ANCIENT_PAGE_2,
				ClueCards.SARADOMIN_PAGE_3, ClueCards.ZAMORAK_PAGE_3, ClueCards.GUTHIX_PAGE_3,
				ClueCards.BANDOS_PAGE_3, ClueCards.ARMADYL_PAGE_3, ClueCards.ANCIENT_PAGE_3,
				ClueCards.SARADOMIN_PAGE_4, ClueCards.ZAMORAK_PAGE_4, ClueCards.GUTHIX_PAGE_4,
				ClueCards.BANDOS_PAGE_4, ClueCards.ARMADYL_PAGE_4, ClueCards.ANCIENT_PAGE_4,
				ClueCards.HOLY_BLESSING, ClueCards.UNHOLY_BLESSING, ClueCards.PEACEFUL_BLESSING,
				ClueCards.HONOURABLE_BLESSING, ClueCards.WAR_BLESSING, ClueCards.ANCIENT_BLESSING,
				ClueCards.NARDAH_TELEPORT, ClueCards.MOS_LEHARMLESS_TELEPORT, ClueCards.MORTTON_TELEPORT,
				ClueCards.FELDIP_HILLS_TELEPORT, ClueCards.LUNAR_ISLE_TELEPORT, ClueCards.DIGSITE_TELEPORT,
				ClueCards.PISCATORIS_TELEPORT, ClueCards.PEST_CONTROL_TELEPORT, ClueCards.TAI_BWO_WANNAI_TELEPORT,
				ClueCards.LUMBERYARD_TELEPORT, ClueCards.IORWERTH_CAMP_TELEPORT, ClueCards.MASTER_SCROLL_BOOK_EMPTY,
				ClueCards.FIRELIGHTER_RED, ClueCards.FIRELIGHTER_GREEN, ClueCards.FIRELIGHTER_BLUE,
				ClueCards.FIRELIGHTER_PURPLE, ClueCards.FIRELIGHTER_WHITE, ClueCards.CHARGE_DRAGONSTONE_JEWELLERY_SCROLL,
				ClueCards.PURPLE_SWEETS);


		// Minigame Rewards
		define("Barbarian Assault", CardSet.MINIGAME_REWARDS, "Rewards from Barbarian Assault.",
				MinigameRewardsCards.PENANCE_QUEEN, MinigameRewardsCards.BARBASSAULT_PENANCE_FIGHTER_HAT, MinigameRewardsCards.BARBASSAULT_PENANCE_RANGER_HAT,
				MinigameRewardsCards.BARBASSAULT_PENANCE_RUNNER_HAT, MinigameRewardsCards.BARBASSAULT_PENANCE_HEALER_HAT, MinigameRewardsCards.BARBASSAULT_PENANCE_FIGHTER_TORSO,
				MinigameRewardsCards.BARBASSAULT_PENANCE_RANGER_LEGS, MinigameRewardsCards.BARBASSAULT_PENANCE_RUNNER_BOOTS, MinigameRewardsCards.BARBASSAULT_PENANCE_GLOVES,
				MinigameRewardsCards.GRANITE_HELM, MinigameRewardsCards.GRANITE_BODY);

		define("Barracuda Trials", CardSet.MINIGAME_REWARDS, "Rewards from Barracuda Trials.",
				MinigameRewardsCards.STORMY_KEY, MinigameRewardsCards.BARREL_STAND, MinigameRewardsCards.RALPHS_FABRIC_ROLL,
				MinigameRewardsCards.FETID_KEY, MinigameRewardsCards.CAPTURED_WIND_MOTE, MinigameRewardsCards.GURTOBS_FABRIC_ROLL,
				MinigameRewardsCards.SERRATED_KEY, MinigameRewardsCards.HEART_OF_ITHELL, MinigameRewardsCards.GWYNAS_FABRIC_ROLL);

		define("Brimhaven Agility Arena", CardSet.MINIGAME_REWARDS, "Rewards from the Brimhaven Agility Arena.",
				MinigameRewardsCards.AGILITY_ARENA_TICKET, MinigameRewardsCards.BRIMHAVEN_VOUCHER, MinigameRewardsCards.PIRATE_HOOK,
				MinigameRewardsCards.GRACEFUL_HOOD_BRIMHAVEN, MinigameRewardsCards.GRACEFUL_TOP_BRIMHAVEN, MinigameRewardsCards.GRACEFUL_LEGS_BRIMHAVEN,
				MinigameRewardsCards.GRACEFUL_GLOVES_BRIMHAVEN, MinigameRewardsCards.GRACEFUL_BOOTS_BRIMHAVEN, MinigameRewardsCards.GRACEFUL_CAPE_BRIMHAVEN);

		define("Castle Wars", CardSet.MINIGAME_REWARDS, "Rewards from Castle Wars.",
				MinigameRewardsCards.CASTLEWARS_DECORATIVE_HELM_RED, MinigameRewardsCards.CASTLEWARS_DECORATIVE_FULL_HELM_RED, MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATEBODY_RED,
				MinigameRewardsCards.CASTLEWARS_DECORATIVE_SWORD_RED, MinigameRewardsCards.CASTLEWARS_DECORATIVE_SHIELD_RED, MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATELEGS_RED,
				MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATESKIRT_RED, MinigameRewardsCards.CASTLEWARS_DECORATIVE_BOOTS_RED, MinigameRewardsCards.CASTLEWARS_DECORATIVE_HELM_WHITE,
				MinigameRewardsCards.CASTLEWARS_DECORATIVE_FULL_HELM_WHITE, MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATEBODY_WHITE, MinigameRewardsCards.CASTLEWARS_DECORATIVE_SWORD_WHITE,
				MinigameRewardsCards.CASTLEWARS_DECORATIVE_SHIELD_WHITE, MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATELEGS_WHITE, MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATESKIRT_WHITE,
				MinigameRewardsCards.CASTLEWARS_DECORATIVE_BOOTS_WHITE, MinigameRewardsCards.CASTLEWARS_DECORATIVE_HELM_GOLD, MinigameRewardsCards.CASTLEWARS_DECORATIVE_FULL_HELM_GOLD,
				MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATEBODY_GOLD, MinigameRewardsCards.CASTLEWARS_DECORATIVE_SWORD_GOLD, MinigameRewardsCards.CASTLEWARS_DECORATIVE_SHIELD_GOLD,
				MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATELEGS_GOLD, MinigameRewardsCards.CASTLEWARS_DECORATIVE_PLATESKIRT_GOLD, MinigameRewardsCards.CASTLEWARS_DECORATIVE_BOOTS_GOLD,
				MinigameRewardsCards.CASTLEWARS_HOOD_SARADOMIN, MinigameRewardsCards.CASTLEWARS_CLOAK_SARADOMIN, MinigameRewardsCards.CASTLEWARS_HOOD_ZAMORAK,
				MinigameRewardsCards.CASTLEWARS_CLOAK_ZAMORAK, MinigameRewardsCards.SARADOMIN_BANNER, MinigameRewardsCards.ZAMORAK_BANNER,
				MinigameRewardsCards.CASTLEWARS_ARMOUR_MAGE_HAT, MinigameRewardsCards.CASTLEWARS_ARMOUR_MAGE_TOP, MinigameRewardsCards.CASTLEWARS_ARMOUR_MAGE_SKIRT,
				MinigameRewardsCards.CASTLEWARS_RANGE_TOP, MinigameRewardsCards.CASTLEWARS_RANGE_LEGS, MinigameRewardsCards.CASTLEWARS_RANGE_QUIVER,
				MinigameRewardsCards.SARADOMIN_HALO, MinigameRewardsCards.ZAMORAK_HALO, MinigameRewardsCards.GUTHIX_HALO);

		define("Fishing Trawler", CardSet.MINIGAME_REWARDS, "Rewards from Fishing Trawler.",
				MinigameRewardsCards.ANGLER_HAT, MinigameRewardsCards.ANGLER_TOP, MinigameRewardsCards.ANGLER_WADERS,
				MinigameRewardsCards.ANGLER_BOOTS);

		define("Giants' Foundry", CardSet.MINIGAME_REWARDS, "Rewards from Giants' Foundry.",
				MinigameRewardsCards.SMITHS_TUNIC, MinigameRewardsCards.SMITHS_TROUSERS, MinigameRewardsCards.SMITHS_BOOTS,
				MinigameRewardsCards.SMITHS_GLOVES, MinigameRewardsCards.COLOSSAL_BLADE, MinigameRewardsCards.DOUBLE_AMMO_MOULD,
				MinigameRewardsCards.KOVACS_GROG, MinigameRewardsCards.SMITHING_CATALYST, MinigameRewardsCards.ORE_PACK);

		define("Gnome Restaurant", CardSet.MINIGAME_REWARDS, "Rewards from Gnome Restaurant.",
				MinigameRewardsCards.GRAND_SEED_POD, MinigameRewardsCards.GNOME_SCARF, MinigameRewardsCards.GNOME_GOGGLES,
				MinigameRewardsCards.MINT_CAKE);

		define("Guardians of the Rift", CardSet.MINIGAME_REWARDS, "Rewards from Guardians of the Rift.",
				MinigameRewardsCards.ABYSSAL_PROTECTOR, MinigameRewardsCards.ABYSSAL_PEARLS, MinigameRewardsCards.CATALYTIC_TALISMAN,
				MinigameRewardsCards.ABYSSAL_NEEDLE, MinigameRewardsCards.ABYSSAL_GREEN_DYE, MinigameRewardsCards.ABYSSAL_BLUE_DYE,
				MinigameRewardsCards.ABYSSAL_RED_DYE, MinigameRewardsCards.HAT_OF_THE_EYE, MinigameRewardsCards.ROBE_TOP_OF_THE_EYE,
				MinigameRewardsCards.ROBE_BOTTOMS_OF_THE_EYE, MinigameRewardsCards.BOOTS_OF_THE_EYE, MinigameRewardsCards.RING_OF_THE_ELEMENTS,
				MinigameRewardsCards.ABYSSAL_LANTERN, MinigameRewardsCards.GUARDIANS_EYE, MinigameRewardsCards.INTRICATE_POUCH,
				MinigameRewardsCards.LOST_BAG, MinigameRewardsCards.TARNISHED_LOCKET);

		define("Hallowed Sepulchre", CardSet.MINIGAME_REWARDS, "Rewards from the Hallowed Sepulchre.",
				MinigameRewardsCards.HALLOWED_MARK, MinigameRewardsCards.HALLOWED_TOKEN, MinigameRewardsCards.HALLOWED_GRAPPLE,
				MinigameRewardsCards.HALLOWED_FOCUS, MinigameRewardsCards.HALLOWED_SYMBOL, MinigameRewardsCards.HALLOWED_HAMMER,
				MinigameRewardsCards.HALLOWED_RING, MinigameRewardsCards.DARK_DYE, MinigameRewardsCards.DARK_ACORN,
				MinigameRewardsCards.STRANGE_OLD_LOCKPICK, MinigameRewardsCards.RING_OF_ENDURANCE, MinigameRewardsCards.MYSTERIOUS_PAGE_1,
				MinigameRewardsCards.MYSTERIOUS_PAGE_2, MinigameRewardsCards.MYSTERIOUS_PAGE_3, MinigameRewardsCards.MYSTERIOUS_PAGE_4,
				MinigameRewardsCards.MYSTERIOUS_PAGE_5);

		define("Last Man Standing", CardSet.MINIGAME_REWARDS, "Rewards from Last Man Standing.",
				MinigameRewardsCards.DEADMANS_CHEST, MinigameRewardsCards.DEADMANS_LEGS, MinigameRewardsCards.DEADMANS_CAPE,
				MinigameRewardsCards.ARMADYL_HALO, MinigameRewardsCards.BANDOS_HALO, MinigameRewardsCards.SEREN_HALO,
				MinigameRewardsCards.ANCIENT_HALO, MinigameRewardsCards.BRASSICA_HALO, MinigameRewardsCards.GOLDEN_ARMADYL_SPECIAL_ATTACK,
				MinigameRewardsCards.GOLDEN_BANDOS_SPECIAL_ATTACK, MinigameRewardsCards.GOLDEN_SARADOMIN_SPECIAL_ATTACK, MinigameRewardsCards.GOLDEN_ZAMORAK_SPECIAL_ATTACK,
				MinigameRewardsCards.VICTORS_CAPE_1, MinigameRewardsCards.VICTORS_CAPE_10, MinigameRewardsCards.VICTORS_CAPE_50,
				MinigameRewardsCards.VICTORS_CAPE_100, MinigameRewardsCards.VICTORS_CAPE_500, MinigameRewardsCards.VICTORS_CAPE_1000,
				MinigameRewardsCards.GRANITE_CLAMP, MinigameRewardsCards.ORNATE_MAUL_HANDLE, MinigameRewardsCards.STEAM_STAFF_UPGRADE_KIT,
				MinigameRewardsCards.LAVA_STAFF_UPGRADE_KIT, MinigameRewardsCards.DRAGON_PICKAXE_UPGRADE_KIT, MinigameRewardsCards.WARD_UPGRADE_KIT,
				MinigameRewardsCards.GREEN_DARK_BOW_PAINT, MinigameRewardsCards.YELLOW_DARK_BOW_PAINT, MinigameRewardsCards.WHITE_DARK_BOW_PAINT,
				MinigameRewardsCards.BLUE_DARK_BOW_PAINT, MinigameRewardsCards.VOLCANIC_WHIP_MIX, MinigameRewardsCards.FROZEN_WHIP_MIX,
				MinigameRewardsCards.GUTHIXIAN_ICON, MinigameRewardsCards.SWIFT_BLADE);

		define("Magic Training Arena", CardSet.MINIGAME_REWARDS, "Rewards from the Magic Training Arena.",
				MinigameRewardsCards.BEGINNER_WAND, MinigameRewardsCards.APPRENTICE_WAND, MinigameRewardsCards.TEACHER_WAND,
				MinigameRewardsCards.MASTER_WAND, MinigameRewardsCards.INFINITY_HAT, MinigameRewardsCards.INFINITY_TOP,
				MinigameRewardsCards.INFINITY_BOTTOMS, MinigameRewardsCards.INFINITY_BOOTS, MinigameRewardsCards.INFINITY_GLOVES,
				MinigameRewardsCards.MAGES_BOOK, MinigameRewardsCards.BONES_TO_PEACHES);

		define("Mahogany Homes", CardSet.MINIGAME_REWARDS, "Rewards from Mahogany Homes.",
				MinigameRewardsCards.SUPPLY_CRATE, MinigameRewardsCards.CARPENTERS_HELMET, MinigameRewardsCards.CARPENTERS_SHIRT,
				MinigameRewardsCards.CARPENTERS_TROUSERS, MinigameRewardsCards.CARPENTERS_BOOTS, MinigameRewardsCards.AMYS_SAW,
				MinigameRewardsCards.PLANK_SACK, MinigameRewardsCards.HOSIDIUS_BLUEPRINTS);

		define("Mastering Mixology", CardSet.MINIGAME_REWARDS, "Rewards from Mastering Mixology.",
				MinigameRewardsCards.PRESCRIPTION_GOGGLES, MinigameRewardsCards.ALCHEMIST_LABCOAT, MinigameRewardsCards.ALCHEMIST_PANTS,
				MinigameRewardsCards.ALCHEMIST_GLOVES, MinigameRewardsCards.ALCHEMISTS_AMULET, MinigameRewardsCards.REAGENT_POUCH,
				MinigameRewardsCards.CHUGGING_BARREL_DISASSEMBLED);

		define("Pest Control", CardSet.MINIGAME_REWARDS, "Rewards from Pest Control.",
				MinigameRewardsCards.VOID_KNIGHT_MACE, MinigameRewardsCards.VOID_KNIGHT_TOP, MinigameRewardsCards.VOID_KNIGHT_ROBE,
				MinigameRewardsCards.VOID_KNIGHT_GLOVES, MinigameRewardsCards.VOID_MAGE_HELM, MinigameRewardsCards.VOID_MELEE_HELM,
				MinigameRewardsCards.VOID_RANGER_HELM, MinigameRewardsCards.VOID_SEAL, MinigameRewardsCards.ELITE_VOID_TOP,
				MinigameRewardsCards.ELITE_VOID_ROBE);

		define("Rogues' Den", CardSet.MINIGAME_REWARDS, "Rewards from Rogues' Den.",
				MinigameRewardsCards.ROGUE_MASK, MinigameRewardsCards.ROGUE_TOP, MinigameRewardsCards.ROGUE_TROUSERS,
				MinigameRewardsCards.ROGUE_BOOTS, MinigameRewardsCards.ROGUE_GLOVES);

		define("Shades of Mort'ton", CardSet.MINIGAME_REWARDS, "Rewards from Shades of Mort'ton.",
				MinigameRewardsCards.AMULET_OF_THE_DAMNED, MinigameRewardsCards.FLAMTAER_BAG, MinigameRewardsCards.FINE_CLOTH,
				MinigameRewardsCards.BRONZE_LOCKS, MinigameRewardsCards.STEEL_LOCKS, MinigameRewardsCards.BLACK_LOCKS,
				MinigameRewardsCards.SILVER_LOCKS, MinigameRewardsCards.GOLD_LOCKS, MinigameRewardsCards.ZEALOTS_HELM,
				MinigameRewardsCards.ZEALOTS_ROBE_TOP, MinigameRewardsCards.ZEALOTS_ROBE_BOTTOM, MinigameRewardsCards.ZEALOTS_BOOTS,
				MinigameRewardsCards.TREE_WIZARDS_JOURNAL, MinigameRewardsCards.BLOODY_NOTES);

		define("Soul Wars", CardSet.MINIGAME_REWARDS, "Rewards from Soul Wars.",
				MinigameRewardsCards.LIL_CREATOR, MinigameRewardsCards.SOUL_CAPE, MinigameRewardsCards.ECTOPLASMATOR);

		define("Temple Trekking", CardSet.MINIGAME_REWARDS, "Rewards from Temple Trekking.",
				MinigameRewardsCards.LUMBERJACK_HAT, MinigameRewardsCards.LUMBERJACK_TOP, MinigameRewardsCards.LUMBERJACK_LEGS,
				MinigameRewardsCards.LUMBERJACK_BOOTS);

		define("Tithe Farm", CardSet.MINIGAME_REWARDS, "Rewards from Tithe Farm.",
				MinigameRewardsCards.FARMERS_STRAWHAT, MinigameRewardsCards.FARMERS_SHIRT, MinigameRewardsCards.FARMERS_BORO_TROUSERS,
				MinigameRewardsCards.FARMERS_BOOTS, MinigameRewardsCards.SEED_BOX, MinigameRewardsCards.GRICOLLERS_CAN,
				MinigameRewardsCards.HERB_SACK);

		define("Trouble Brewing", CardSet.MINIGAME_REWARDS, "Rewards from Trouble Brewing.",
				MinigameRewardsCards.BLUE_NAVAL_SHIRT, MinigameRewardsCards.BLUE_TRICORN_HAT, MinigameRewardsCards.BLUE_NAVY_SLACKS,
				MinigameRewardsCards.GREEN_NAVAL_SHIRT, MinigameRewardsCards.GREEN_TRICORN_HAT, MinigameRewardsCards.GREEN_NAVY_SLACKS,
				MinigameRewardsCards.RED_NAVAL_SHIRT, MinigameRewardsCards.RED_TRICORN_HAT, MinigameRewardsCards.RED_NAVY_SLACKS,
				MinigameRewardsCards.BROWN_NAVAL_SHIRT, MinigameRewardsCards.BROWN_TRICORN_HAT, MinigameRewardsCards.BROWN_NAVY_SLACKS,
				MinigameRewardsCards.BLACK_NAVAL_SHIRT, MinigameRewardsCards.BLACK_TRICORN_HAT, MinigameRewardsCards.BLACK_NAVY_SLACKS,
				MinigameRewardsCards.PURPLE_NAVAL_SHIRT, MinigameRewardsCards.PURPLE_TRICORN_HAT, MinigameRewardsCards.PURPLE_NAVY_SLACKS,
				MinigameRewardsCards.GREY_NAVAL_SHIRT, MinigameRewardsCards.GREY_TRICORN_HAT, MinigameRewardsCards.GREY_NAVY_SLACKS,
				MinigameRewardsCards.CUTTHROAT_FLAG, MinigameRewardsCards.GILDED_SMILE_FLAG, MinigameRewardsCards.BRONZE_FIST_FLAG,
				MinigameRewardsCards.LUCKY_SHOT_FLAG, MinigameRewardsCards.TREASURE_FLAG, MinigameRewardsCards.PHASMATYS_FLAG,
				MinigameRewardsCards.THE_STUFF, MinigameRewardsCards.RED_RUM, MinigameRewardsCards.BLUE_RUM);

		define("Vale Totems", CardSet.MINIGAME_REWARDS, "Rewards from Vale Totems.",
				MinigameRewardsCards.FLETCHING_KNIFE, MinigameRewardsCards.BOW_STRING_SPOOL,
				MinigameRewardsCards.ENT_BRANCH, MinigameRewardsCards.GREENMAN_MASK);

		define("Volcanic Mine", CardSet.MINIGAME_REWARDS, "Rewards from Volcanic Mine.",
				MinigameRewardsCards.ASH_COVERED_TOME, MinigameRewardsCards.LARGE_WATER_CONTAINER, MinigameRewardsCards.VOLCANIC_MINE_TELEPORT,
				MinigameRewardsCards.DRAGON_PICKAXE_BROKEN);

		// Other section of Collection Log
		define("Aerial Fishing", CardSet.OTHER_COLLECTIONLOG, "Rewards from Aerial Fishing.",
				OtherCollectionLog.GOLDEN_TENCH, OtherCollectionLog.PEARL_FISHING_ROD, OtherCollectionLog.PEARL_FLY_FISHING_ROD,
				OtherCollectionLog.PEARL_BARBARIAN_ROD, OtherCollectionLog.FISH_SACK);

		define("Boat Paints", CardSet.OTHER_COLLECTIONLOG, "Rewards from Boat Paints.",
				OtherCollectionLog.BARRACUDA_PAINT, OtherCollectionLog.SHARK_PAINT, OtherCollectionLog.INKY_PAINT,
				OtherCollectionLog.ANGLERS_PAINT, OtherCollectionLog.SALVORS_PAINT, OtherCollectionLog.ARMADYLEAN_PAINT,
				OtherCollectionLog.ZAMORAKIAN_PAINT, OtherCollectionLog.GUTHIXIAN_PAINT, OtherCollectionLog.SARADOMINIST_PAINT,
				OtherCollectionLog.MERCHANTS_PAINT, OtherCollectionLog.SANDY_PAINT);

		define("Camdozaal", CardSet.OTHER_COLLECTIONLOG, "Rewards from Camdozaal.",
				OtherCollectionLog.BARRONITE_MACE, OtherCollectionLog.BARRONITE_HEAD, OtherCollectionLog.BARRONITE_HANDLE,
				OtherCollectionLog.BARRONITE_GUARD, OtherCollectionLog.ANCIENT_GLOBE, OtherCollectionLog.ANCIENT_LEDGER,
				OtherCollectionLog.ANCIENT_ASTROSCOPE, OtherCollectionLog.ANCIENT_TREATISE, OtherCollectionLog.ANCIENT_CARCANET,
				OtherCollectionLog.IMCANDO_HAMMER);

		define("Champion's Challenge", CardSet.OTHER_COLLECTIONLOG, "Rewards from Champion's Challenge.",
				OtherCollectionLog.EARTH_WARRIOR_CHAMPION_SCROLL, OtherCollectionLog.GHOUL_CHAMPION_SCROLL, OtherCollectionLog.GIANT_CHAMPION_SCROLL,
				OtherCollectionLog.GOBLIN_CHAMPION_SCROLL, OtherCollectionLog.HOBGOBLIN_CHAMPION_SCROLL, OtherCollectionLog.IMP_CHAMPION_SCROLL,
				OtherCollectionLog.JOGRE_CHAMPION_SCROLL, OtherCollectionLog.LESSER_DEMON_CHAMPION_SCROLL, OtherCollectionLog.SKELETON_CHAMPION_SCROLL,
				OtherCollectionLog.ZOMBIE_CHAMPION_SCROLL, OtherCollectionLog.CHAMPIONS_CAPE);

		define("Chompy Bird Hunting", CardSet.OTHER_COLLECTIONLOG, "Rewards from Chompy Bird Hunting.",
				OtherCollectionLog.CHOMPY_CHICK, OtherCollectionLog.OGRE_BOWMAN_HAT, OtherCollectionLog.BOWMAN_HAT,
				OtherCollectionLog.OGRE_YEOMAN_HAT, OtherCollectionLog.YEOMAN_HAT, OtherCollectionLog.OGRE_MARKSMAN_HAT,
				OtherCollectionLog.MARKSMAN_HAT, OtherCollectionLog.OGRE_WOODSMAN_HAT, OtherCollectionLog.WOODSMAN_HAT,
				OtherCollectionLog.OGRE_FORESTER_HAT, OtherCollectionLog.FORESTER_HAT, OtherCollectionLog.OGRE_BOWMASTER_HAT,
				OtherCollectionLog.BOWMASTER_HAT, OtherCollectionLog.OGRE_EXPERT_HAT, OtherCollectionLog.EXPERT_HAT,
				OtherCollectionLog.OGRE_DRAGON_ARCHER_HAT, OtherCollectionLog.DRAGON_ARCHER_HAT, OtherCollectionLog.EXPERT_OGRE_DRAGON_ARCHER_HAT,
				OtherCollectionLog.EXPERT_DRAGON_ARCHER_HAT);

		define("Colossal Wyrm Agility", CardSet.OTHER_COLLECTIONLOG, "Rewards from Colossal Wyrm Agility.",
				OtherCollectionLog.COLOSSAL_WYRM_TELEPORT_SCROLL, OtherCollectionLog.CALCIFIED_ACORN, OtherCollectionLog.GRACEFUL_HOOD_WYRM,
				OtherCollectionLog.GRACEFUL_CAPE_WYRM, OtherCollectionLog.GRACEFUL_TOP_WYRM, OtherCollectionLog.GRACEFUL_LEGS_WYRM,
				OtherCollectionLog.GRACEFUL_GLOVES_WYRM, OtherCollectionLog.GRACEFUL_BOOTS_WYRM);

		define("Creature Creation", CardSet.OTHER_COLLECTIONLOG, "Rewards from Creature Creation.",
				OtherCollectionLog.TEA_FLASK, OtherCollectionLog.PLAIN_SATCHEL, OtherCollectionLog.GREEN_SATCHEL,
				OtherCollectionLog.RED_SATCHEL, OtherCollectionLog.BLACK_SATCHEL, OtherCollectionLog.GOLD_SATCHEL,
				OtherCollectionLog.RUNE_SATCHEL);

		define("Cyclopes", CardSet.OTHER_COLLECTIONLOG, "Rewards from Cyclopes.",
				OtherCollectionLog.BRONZE_DEFENDER, OtherCollectionLog.IRON_DEFENDER, OtherCollectionLog.STEEL_DEFENDER,
				OtherCollectionLog.BLACK_DEFENDER, OtherCollectionLog.MITHRIL_DEFENDER, OtherCollectionLog.ADAMANT_DEFENDER,
				OtherCollectionLog.RUNE_DEFENDER, OtherCollectionLog.DRAGON_DEFENDER);

		define("Elder Chaos Druids", CardSet.OTHER_COLLECTIONLOG, "Rewards from Elder Chaos Druids.",
				OtherCollectionLog.ELDER_CHAOS_TOP, OtherCollectionLog.ELDER_CHAOS_ROBE, OtherCollectionLog.ELDER_CHAOS_HOOD);

		define("Forestry", CardSet.OTHER_COLLECTIONLOG, "Rewards from Forestry.",
				OtherCollectionLog.FOX_WHISTLE, OtherCollectionLog.GOLDEN_PHEASANT_EGG, OtherCollectionLog.FORESTRY_HAT,
				OtherCollectionLog.FORESTRY_TOP, OtherCollectionLog.FORESTRY_LEGS, OtherCollectionLog.FORESTRY_BOOTS,
				OtherCollectionLog.TWITCHERS_GLOVES, OtherCollectionLog.FUNKY_SHAPED_LOG, OtherCollectionLog.LOG_BASKET,
				OtherCollectionLog.LOG_BRACE, OtherCollectionLog.CLOTHES_POUCH_BLUEPRINT, OtherCollectionLog.CAPE_POUCH,
				OtherCollectionLog.FELLING_AXE_HANDLE, OtherCollectionLog.PHEASANT_HAT, OtherCollectionLog.PHEASANT_LEGS,
				OtherCollectionLog.PHEASANT_BOOTS, OtherCollectionLog.PHEASANT_CAPE, OtherCollectionLog.PETAL_GARLAND,
				OtherCollectionLog.STURDY_BEEHIVE_PARTS);

		define("Fossil Island Notes", CardSet.OTHER_COLLECTIONLOG, "Rewards from Fossil Island Notes.",
				OtherCollectionLog.SCRIBBLED_NOTE, OtherCollectionLog.PARTIAL_NOTE, OtherCollectionLog.ANCIENT_NOTE,
				OtherCollectionLog.ANCIENT_WRITINGS, OtherCollectionLog.EXPERIMENTAL_NOTE, OtherCollectionLog.PARAGRAPH_OF_TEXT,
				OtherCollectionLog.MUSTY_SMELLING_NOTE, OtherCollectionLog.HASTILY_SCRAWLED_NOTE, OtherCollectionLog.OLD_WRITING,
				OtherCollectionLog.SHORT_NOTE);

		define("Glough's Experiments", CardSet.OTHER_COLLECTIONLOG, "Rewards from Glough's Experiments.",
				OtherCollectionLog.ZENYTE_SHARD, OtherCollectionLog.LIGHT_FRAME, OtherCollectionLog.HEAVY_FRAME,
				OtherCollectionLog.BALLISTA_LIMBS, OtherCollectionLog.MONKEY_TAIL, OtherCollectionLog.BALLISTA_SPRING);

		define("Hunter Guild", CardSet.OTHER_COLLECTIONLOG, "Rewards from Hunter Guild.",
				OtherCollectionLog.QUETZIN, OtherCollectionLog.HUNTSMANS_KIT, OtherCollectionLog.GUILD_HUNTER_HEADWEAR,
				OtherCollectionLog.GUILD_HUNTER_TOP, OtherCollectionLog.GUILD_HUNTER_LEGS,
				OtherCollectionLog.GUILD_HUNTER_BOOTS);

		define("Lost Schematics", CardSet.OTHER_COLLECTIONLOG, "Rewards from Lost Schematics.",
				OtherCollectionLog.SALVAGING_STATION_SCHEMATIC, OtherCollectionLog.GALE_CATCHER_SCHEMATIC,
				OtherCollectionLog.ETERNAL_BRAZIER_SCHEMATIC, OtherCollectionLog.ROSEWOOD_CARGO_HOLD_SCHEMATIC,
				OtherCollectionLog.ROSEWOOD_HULL_SCHEMATIC, OtherCollectionLog.ROSEWOOD_COTTON_SAILS_SCHEMATIC,
				OtherCollectionLog.DRAGON_TILLER_SCHEMATIC, OtherCollectionLog.DRAGON_KEEL_SCHEMATIC,
				OtherCollectionLog.DRAGON_SALVAGING_HOOK_SCHEMATIC, OtherCollectionLog.DRAGON_CANNON_SCHEMATIC,
				OtherCollectionLog.BALLISTIC_ATTRACTOR_SCHEMATIC, OtherCollectionLog.BOSUNS_WORKBENCH_SCHEMATIC);

		define("Monkey Backpacks", CardSet.OTHER_COLLECTIONLOG, "Rewards from Monkey Backpacks.",
				OtherCollectionLog.KARAMJAN_MONKEY, OtherCollectionLog.KRUK_JR, OtherCollectionLog.MANIACAL_MONKEY,
				OtherCollectionLog.PRINCELY_MONKEY, OtherCollectionLog.SKELETON_MONKEY,
				OtherCollectionLog.ZOMBIE_MONKEY);

		define("Motherlode Mine", CardSet.OTHER_COLLECTIONLOG, "Rewards from Motherlode Mine.",
				OtherCollectionLog.COAL_BAG, OtherCollectionLog.GEM_BAG, OtherCollectionLog.PROSPECTOR_HELMET,
				OtherCollectionLog.PROSPECTOR_JACKET, OtherCollectionLog.PROSPECTOR_LEGS,
				OtherCollectionLog.PROSPECTOR_BOOTS);

		define("My Notes", CardSet.OTHER_COLLECTIONLOG, "Rewards from My Notes.",
				OtherCollectionLog.ANCIENT_PAGE_1, OtherCollectionLog.ANCIENT_PAGE_2, OtherCollectionLog.ANCIENT_PAGE_3,
				OtherCollectionLog.ANCIENT_PAGE_4, OtherCollectionLog.ANCIENT_PAGE_5, OtherCollectionLog.ANCIENT_PAGE_6,
				OtherCollectionLog.ANCIENT_PAGE_7, OtherCollectionLog.ANCIENT_PAGE_8, OtherCollectionLog.ANCIENT_PAGE_9,
				OtherCollectionLog.ANCIENT_PAGE_10, OtherCollectionLog.ANCIENT_PAGE_11, OtherCollectionLog.ANCIENT_PAGE_12,
				OtherCollectionLog.ANCIENT_PAGE_13, OtherCollectionLog.ANCIENT_PAGE_14, OtherCollectionLog.ANCIENT_PAGE_15,
				OtherCollectionLog.ANCIENT_PAGE_16, OtherCollectionLog.ANCIENT_PAGE_17, OtherCollectionLog.ANCIENT_PAGE_18,
				OtherCollectionLog.ANCIENT_PAGE_19, OtherCollectionLog.ANCIENT_PAGE_20, OtherCollectionLog.ANCIENT_PAGE_21,
				OtherCollectionLog.ANCIENT_PAGE_22, OtherCollectionLog.ANCIENT_PAGE_23, OtherCollectionLog.ANCIENT_PAGE_24,
				OtherCollectionLog.ANCIENT_PAGE_25, OtherCollectionLog.ANCIENT_PAGE_26);

		define("Ocean Encounters", CardSet.OTHER_COLLECTIONLOG, "Rewards from Ocean Encounters.",
				OtherCollectionLog.TINY_PEARL, OtherCollectionLog.SMALL_PEARL, OtherCollectionLog.SHINY_PEARL,
				OtherCollectionLog.BRIGHT_PEARL, OtherCollectionLog.BIG_PEARL, OtherCollectionLog.HUGE_PEARL,
				OtherCollectionLog.ENORMOUS_PEARL, OtherCollectionLog.SHIMMERING_PEARL, OtherCollectionLog.GLISTENING_PEARL,
				OtherCollectionLog.BRILLIANT_PEARL, OtherCollectionLog.RADIANT_PEARL);

		define("Random Events", CardSet.OTHER_COLLECTIONLOG, "Rewards from Random Events.",
				OtherCollectionLog.CAMO_TOP, OtherCollectionLog.CAMO_BOTTOMS, OtherCollectionLog.CAMO_HELMET,
				OtherCollectionLog.LEDERHOSEN_TOP, OtherCollectionLog.LEDERHOSEN_SHORTS, OtherCollectionLog.LEDERHOSEN_HAT,
				OtherCollectionLog.ZOMBIE_SHIRT, OtherCollectionLog.ZOMBIE_TROUSERS, OtherCollectionLog.ZOMBIE_MASK,
				OtherCollectionLog.ZOMBIE_GLOVES, OtherCollectionLog.ZOMBIE_BOOTS, OtherCollectionLog.MIME_MASK,
				OtherCollectionLog.MIME_TOP, OtherCollectionLog.MIME_LEGS, OtherCollectionLog.MIME_GLOVES,
				OtherCollectionLog.MIME_BOOTS, OtherCollectionLog.FROG_TOKEN, OtherCollectionLog.STALE_BAGUETTE,
				OtherCollectionLog.BEEKEEPERS_HAT, OtherCollectionLog.BEEKEEPERS_TOP, OtherCollectionLog.BEEKEEPERS_LEGS,
				OtherCollectionLog.BEEKEEPERS_GLOVES, OtherCollectionLog.BEEKEEPERS_BOOTS);

		define("Revenants", CardSet.OTHER_COLLECTIONLOG, "Rewards from Revenants.",
				OtherCollectionLog.VIGGORAS_CHAINMACE, OtherCollectionLog.CRAWS_BOW, OtherCollectionLog.THAMMARONS_SCEPTRE,
				OtherCollectionLog.AMULET_OF_AVARICE, OtherCollectionLog.BRACELET_OF_ETHEREUM,
				OtherCollectionLog.ANCIENT_CRYSTAL, OtherCollectionLog.ANCIENT_RELIC,
				OtherCollectionLog.ANCIENT_EFFIGY, OtherCollectionLog.ANCIENT_MEDALLION,
				OtherCollectionLog.ANCIENT_STATUETTE, OtherCollectionLog.ANCIENT_TOTEM,
				OtherCollectionLog.ANCIENT_EMBLEM, OtherCollectionLog.REVENANT_CAVE_TELEPORT,
				OtherCollectionLog.REVENANT_ETHER);

		define("Rooftop Agility", CardSet.OTHER_COLLECTIONLOG, "Rewards from Rooftop Agility.",
				OtherCollectionLog.MARK_OF_GRACE, OtherCollectionLog.GRACEFUL_HOOD,
				OtherCollectionLog.GRACEFUL_CAPE, OtherCollectionLog.GRACEFUL_TOP,
				OtherCollectionLog.GRACEFUL_LEGS, OtherCollectionLog.GRACEFUL_GLOVES,
				OtherCollectionLog.GRACEFUL_BOOTS);

		define("Sailing Miscellaneous", CardSet.OTHER_COLLECTIONLOG, "Rewards from Sailing Miscellaneous.",
				OtherCollectionLog.DRAGON_METAL_SHEET, OtherCollectionLog.DRAGON_NAILS,
				OtherCollectionLog.DRAGON_CANNONBALL, OtherCollectionLog.ECHO_PEARL,
				OtherCollectionLog.SWIFT_ALBATROSS_FEATHER, OtherCollectionLog.NARWHAL_HORN,
				OtherCollectionLog.RAY_BARBS, OtherCollectionLog.BROKEN_DRAGON_HOOK,
				OtherCollectionLog.BOTTLED_STORM, OtherCollectionLog.DRAGON_CANNON_BARREL,
				OtherCollectionLog.BOAT_BOTTLE, OtherCollectionLog.FACILITY_BOTTLE);

		define("Sea Treasures", CardSet.OTHER_COLLECTIONLOG, "Rewards from Sea Treasures.",
				OtherCollectionLog.MEDALLION_FRAGMENT_1, OtherCollectionLog.MEDALLION_FRAGMENT_2,
				OtherCollectionLog.MEDALLION_FRAGMENT_3, OtherCollectionLog.MEDALLION_FRAGMENT_4,
				OtherCollectionLog.MEDALLION_FRAGMENT_5, OtherCollectionLog.MEDALLION_FRAGMENT_6,
				OtherCollectionLog.MEDALLION_FRAGMENT_7, OtherCollectionLog.MEDALLION_FRAGMENT_8,
				OtherCollectionLog.SAILORS_AMULET_INERT, OtherCollectionLog.RUSTY_LOCKET,
				OtherCollectionLog.MOULDY_BLOCK, OtherCollectionLog.DULL_KNIFE,
				OtherCollectionLog.BROKEN_COMPASS, OtherCollectionLog.RUSTY_COIN,
				OtherCollectionLog.BROKEN_SEXTANT, OtherCollectionLog.MOULDY_DOLL,
				OtherCollectionLog.SMASHED_MIRROR);

		define("Shayzien Armour", CardSet.OTHER_COLLECTIONLOG, "Rewards from Shayzien Armour.",
				OtherCollectionLog.SHAYZIEN_GLOVES_1, OtherCollectionLog.SHAYZIEN_BOOTS_1,
				OtherCollectionLog.SHAYZIEN_HELM_1, OtherCollectionLog.SHAYZIEN_GREAVES_1,
				OtherCollectionLog.SHAYZIEN_PLATEBODY_1, OtherCollectionLog.SHAYZIEN_GLOVES_2,
				OtherCollectionLog.SHAYZIEN_BOOTS_2, OtherCollectionLog.SHAYZIEN_HELM_2,
				OtherCollectionLog.SHAYZIEN_GREAVES_2, OtherCollectionLog.SHAYZIEN_PLATEBODY_2,
				OtherCollectionLog.SHAYZIEN_GLOVES_3, OtherCollectionLog.SHAYZIEN_BOOTS_3,
				OtherCollectionLog.SHAYZIEN_HELM_3, OtherCollectionLog.SHAYZIEN_GREAVES_3,
				OtherCollectionLog.SHAYZIEN_PLATEBODY_3, OtherCollectionLog.SHAYZIEN_GLOVES_4,
				OtherCollectionLog.SHAYZIEN_BOOTS_4, OtherCollectionLog.SHAYZIEN_HELM_4,
				OtherCollectionLog.SHAYZIEN_GREAVES_4, OtherCollectionLog.SHAYZIEN_PLATEBODY_4,
				OtherCollectionLog.SHAYZIEN_GLOVES_5, OtherCollectionLog.SHAYZIEN_BOOTS_5,
				OtherCollectionLog.SHAYZIEN_HELM_5, OtherCollectionLog.SHAYZIEN_GREAVES_5,
				OtherCollectionLog.SHAYZIEN_BODY_5);

		define("Shooting Stars", CardSet.OTHER_COLLECTIONLOG, "Rewards from Shooting Stars.",
				OtherCollectionLog.CELESTIAL_RING, OtherCollectionLog.STAR_FRAGMENT);

		define("Slayer", CardSet.OTHER_COLLECTIONLOG, "Rewards from Slayer.",
				OtherCollectionLog.CRAWLING_HAND, OtherCollectionLog.COCKATRICE_HEAD,
				OtherCollectionLog.BASILISK_HEAD, OtherCollectionLog.KURASK_HEAD,
				OtherCollectionLog.ABYSSAL_HEAD, OtherCollectionLog.IMBUED_HEART,
				OtherCollectionLog.ETERNAL_GEM, OtherCollectionLog.DUST_BATTLESTAFF,
				OtherCollectionLog.MIST_BATTLESTAFF, OtherCollectionLog.ABYSSAL_WHIP,
				OtherCollectionLog.GRANITE_MAUL, OtherCollectionLog.MUDSKIPPER_HAT,
				OtherCollectionLog.FLIPPERS, OtherCollectionLog.BRINE_SABRE,
				OtherCollectionLog.LEAF_BLADED_SWORD, OtherCollectionLog.LEAF_BLADED_BATTLEAXE,
				OtherCollectionLog.BLACK_MASK, OtherCollectionLog.GRANITE_LONGSWORD,
				OtherCollectionLog.GRANITE_BOOTS, OtherCollectionLog.WYVERN_VISAGE,
				OtherCollectionLog.GRANITE_LEGS, OtherCollectionLog.GRANITE_HELM,
				OtherCollectionLog.DRACONIC_VISAGE, OtherCollectionLog.BRONZE_BOOTS,
				OtherCollectionLog.IRON_BOOTS, OtherCollectionLog.STEEL_BOOTS,
				OtherCollectionLog.BLACK_BOOTS, OtherCollectionLog.MITHRIL_BOOTS,
				OtherCollectionLog.ADAMANT_BOOTS, OtherCollectionLog.RUNE_BOOTS,
				OtherCollectionLog.DRAGON_BOOTS, OtherCollectionLog.UNCHARGED_TRIDENT,
				OtherCollectionLog.DARK_BOW, OtherCollectionLog.BROKEN_DRAGON_HASTA,
				OtherCollectionLog.MYSTIC_HAT_LIGHT, OtherCollectionLog.MYSTIC_ROBE_TOP_LIGHT,
				OtherCollectionLog.MYSTIC_ROBE_BOTTOM_LIGHT, OtherCollectionLog.MYSTIC_GLOVES_LIGHT,
				OtherCollectionLog.MYSTIC_BOOTS_LIGHT, OtherCollectionLog.MYSTIC_HAT_DARK,
				OtherCollectionLog.MYSTIC_ROBE_TOP_DARK, OtherCollectionLog.MYSTIC_ROBE_BOTTOM_DARK,
				OtherCollectionLog.MYSTIC_GLOVES_DARK, OtherCollectionLog.MYSTIC_BOOTS_DARK,
				OtherCollectionLog.MYSTIC_HAT_DUSK, OtherCollectionLog.MYSTIC_ROBE_TOP_DUSK,
				OtherCollectionLog.MYSTIC_ROBE_BOTTOM_DUSK, OtherCollectionLog.MYSTIC_GLOVES_DUSK,
				OtherCollectionLog.MYSTIC_BOOTS_DUSK, OtherCollectionLog.BASILISK_JAW,
				OtherCollectionLog.AQUANITE_TENDON, OtherCollectionLog.DAGONHAI_HAT,
				OtherCollectionLog.DAGONHAI_ROBE_TOP, OtherCollectionLog.DAGONHAI_ROBE_BOTTOM,
				OtherCollectionLog.BLOOD_SHARD, OtherCollectionLog.ANCIENT_CEREMONIAL_MASK,
				OtherCollectionLog.ANCIENT_CEREMONIAL_TOP, OtherCollectionLog.ANCIENT_CEREMONIAL_LEGS,
				OtherCollectionLog.ANCIENT_CEREMONIAL_GLOVES, OtherCollectionLog.ANCIENT_CEREMONIAL_BOOTS,
				OtherCollectionLog.WARPED_SCEPTRE, OtherCollectionLog.SULPHUR_BLADES,
				OtherCollectionLog.TELEPORT_ANCHORING_SCROLL, OtherCollectionLog.ARANEA_BOOTS,
				OtherCollectionLog.GLACIAL_TEMOTLI, OtherCollectionLog.PENDANT_OF_ATES,
				OtherCollectionLog.FROZEN_TEAR, OtherCollectionLog.EARTHBOUND_TECPATL,
				OtherCollectionLog.ANTLER_GUARD, OtherCollectionLog.ALCHEMISTS_SIGNET,
				OtherCollectionLog.BROKEN_ANTLER, OtherCollectionLog.HORN_OF_PLENTY,
				OtherCollectionLog.GRYPHON_FEATHER, OtherCollectionLog.VENATOR_TOOTH,
				OtherCollectionLog.VENATOR_FANG);

		define("Tormented Demons", CardSet.OTHER_COLLECTIONLOG, "Rewards from Tormented Demons.",
				OtherCollectionLog.TORMENTED_SYNAPSE, OtherCollectionLog.BURNING_CLAW,
				OtherCollectionLog.GUTHIXIAN_TEMPLE_TELEPORT);

		define("TzHaar", CardSet.OTHER_COLLECTIONLOG, "Rewards from TzHaar.",
				OtherCollectionLog.OBSIDIAN_CAPE, OtherCollectionLog.TOKTZ_KET_XIL,
				OtherCollectionLog.TZHAAR_KET_OM, OtherCollectionLog.TOKTZ_XIL_AK,
				OtherCollectionLog.TOKTZ_XIL_EK, OtherCollectionLog.TOKTZ_MEJ_TAL,
				OtherCollectionLog.TOKTZ_XIL_UL, OtherCollectionLog.OBSIDIAN_HELMET,
				OtherCollectionLog.OBSIDIAN_PLATEBODY, OtherCollectionLog.OBSIDIAN_PLATELEGS);

		define("Miscellaneous", CardSet.OTHER_COLLECTIONLOG, "Miscellaneous collection log rewards.",
				OtherCollectionLog.HERBI, OtherCollectionLog.DRAGON_WARHAMMER,  OtherCollectionLog.BIG_SWORDFISH,
				OtherCollectionLog.BIG_SHARK, OtherCollectionLog.BIG_BASS, OtherCollectionLog.GIANT_BLUE_KRILL,
				OtherCollectionLog.GOLDEN_HADDOCK, OtherCollectionLog.ORANGEFIN, OtherCollectionLog.HUGE_HALIBUT,
				OtherCollectionLog.PURPLEFIN, OtherCollectionLog.SWIFT_MARLIN, OtherCollectionLog.LONG_BONE,
				OtherCollectionLog.CURVED_BONE, OtherCollectionLog.ECUMENICAL_KEY, OtherCollectionLog.PHARAOHS_SCEPTRE_UNCHARGED,
				OtherCollectionLog.DARK_TOTEM_BASE, OtherCollectionLog.DARK_TOTEM_MIDDLE, OtherCollectionLog.DARK_TOTEM_TOP,
				OtherCollectionLog.CHEWED_BONES, OtherCollectionLog.DRAGON_FULL_HELM, OtherCollectionLog.SHIELD_LEFT_HALF,
				OtherCollectionLog.DRAGON_METAL_SLICE, OtherCollectionLog.DRAGON_METAL_LUMP, OtherCollectionLog.DRAGON_LIMBS,
				OtherCollectionLog.DRAGON_SPEAR, OtherCollectionLog.AMULET_OF_ETERNAL_GLORY, OtherCollectionLog.SHAMAN_MASK,
				OtherCollectionLog.EVIL_CHICKEN_HEAD, OtherCollectionLog.EVIL_CHICKEN_WINGS, OtherCollectionLog.EVIL_CHICKEN_LEGS,
				OtherCollectionLog.EVIL_CHICKEN_FEET, OtherCollectionLog.MINING_GLOVES, OtherCollectionLog.SUPERIOR_MINING_GLOVES,
				OtherCollectionLog.EXPERT_MINING_GLOVES, OtherCollectionLog.RIGHT_SKULL_HALF, OtherCollectionLog.LEFT_SKULL_HALF,
				OtherCollectionLog.TOP_OF_SCEPTRE, OtherCollectionLog.BOTTOM_OF_SCEPTRE, OtherCollectionLog.MOSSY_KEY,
				OtherCollectionLog.GIANT_KEY, OtherCollectionLog.HESPORI_SEED, OtherCollectionLog.FRESH_CRAB_CLAW,
				OtherCollectionLog.FRESH_CRAB_SHELL, OtherCollectionLog.XERICS_TALISMAN_INERT, OtherCollectionLog.MASK_OF_RANUL,
				OtherCollectionLog.ELVEN_SIGNET, OtherCollectionLog.CRYSTAL_GRAIL, OtherCollectionLog.ENHANCED_CRYSTAL_TELEPORT_SEED,
				OtherCollectionLog.DRAGONSTONE_FULL_HELM, OtherCollectionLog.DRAGONSTONE_PLATEBODY, OtherCollectionLog.DRAGONSTONE_PLATELEGS,
				OtherCollectionLog.DRAGONSTONE_GAUNTLETS, OtherCollectionLog.DRAGONSTONE_BOOTS, OtherCollectionLog.MERFOLK_TRIDENT,
				OtherCollectionLog.ORANGE_EGG_SAC, OtherCollectionLog.BLUE_EGG_SAC, OtherCollectionLog.BROKEN_ZOMBIE_AXE,
				OtherCollectionLog.BROKEN_ZOMBIE_HELMET, OtherCollectionLog.HELMET_OF_THE_MOON, OtherCollectionLog.SQUID_BEAK,
				OtherCollectionLog.JEWELLERS_CHISEL);



		// PVM etc
		define("God Wars Uniques", CardSet.BOSS_DROPS, "Drops from the four God Wars generals.",
			BossDropsCards.ARMADYL_CHESTPLATE, BossDropsCards.ARMADYL_HELMET, BossDropsCards.ARMADYL_CROSSBOW,
			BossDropsCards.BANDOS_CHESTPLATE, BossDropsCards.BANDOS_TASSETS, BossDropsCards.SARADOMIN_SWORD,
			BossDropsCards.ZAMORAKIAN_SPEAR, BossDropsCards.STAFF_OF_THE_DEAD);

		define("The Nex Drop Table", CardSet.BOSS_DROPS, "Drops from Nex.",
			BossDropsCards.TORVA_FULL_HELM_DAMAGED, BossDropsCards.TORVA_PLATEBODY_DAMAGED,
			BossDropsCards.TORVA_PLATELEGS_DAMAGED, BossDropsCards.ZARYTE_VAMBRACES, BossDropsCards.NIHIL_HORN,
			BossDropsCards.ANCIENT_HILT);

		define("The Nightmare", CardSet.BOSS_DROPS, "Drops from the Nightmare.",
			BossDropsCards.INQUISITORS_MACE, BossDropsCards.NIGHTMARE_STAFF, BossDropsCards.HARMONISED_ORB,
			BossDropsCards.VOLATILE_ORB, BossDropsCards.ELDRITCH_ORB);

		define("Desert Treasure II Drops", CardSet.BOSS_DROPS, "Drops from the Forgotten Four.",
			BossDropsCards.ULTOR_VESTIGE, BossDropsCards.MAGUS_VESTIGE, BossDropsCards.VENATOR_VESTIGE,
			BossDropsCards.BELLATOR_VESTIGE, BossDropsCards.AWAKENERS_ORB, BossDropsCards.VIRTUS_MASK,
			BossDropsCards.VIRTUS_ROBE_TOP, BossDropsCards.VIRTUS_ROBE_BOTTOM);

		define("Araxxor", CardSet.BOSS_DROPS, "Drops from Araxxor.", BossDropsCards.ARAXYTE_FANG,
			BossDropsCards.NOXIOUS_POINT, BossDropsCards.NOXIOUS_BLADE, BossDropsCards.NOXIOUS_POMMEL,
			BossDropsCards.JAR_OF_VENOM);

		define("Slayer Boss Drops", CardSet.BOSS_DROPS, "Uniques from bosses that appear as Slayer tasks.",
			BossDropsCards.ABYSSAL_WHIP, BossDropsCards.ABYSSAL_DAGGER, BossDropsCards.KRAKEN_TENTACLE,
			BossDropsCards.HYDRAS_CLAW, BossDropsCards.HYDRA_TAIL, BossDropsCards.HYDRA_LEATHER,
			BossDropsCards.OCCULT_NECKLACE, BossDropsCards.SMOKE_BATTLESTAFF, BossDropsCards.GRANITE_HAMMER);

		define("Cerberus", CardSet.BOSS_DROPS,
			"The three crystals, the smouldering stone, and what else the hellhound keeps.",
			BossDropsCards.PRIMORDIAL_CRYSTAL, BossDropsCards.PEGASIAN_CRYSTAL, BossDropsCards.ETERNAL_CRYSTAL,
			BossDropsCards.SMOULDERING_STONE, BossDropsCards.HELLPUPPY, BossDropsCards.JAR_OF_SOULS,
			BossDropsCards.KEY_MASTER_TELEPORT);

		define("Wilderness Boss Rings", CardSet.BOSS_DROPS, "The three rings from the Wilderness bosses.",
			BossDropsCards.RING_OF_THE_GODS, BossDropsCards.TREASONOUS_RING, BossDropsCards.TYRANNICAL_RING);

		define("Zulrah", CardSet.BOSS_DROPS, "Zulrah's drop table.", BossDropsCards.TANZANITE_FANG,
			BossDropsCards.MAGIC_FANG, BossDropsCards.SERPENTINE_VISAGE, BossDropsCards.UNCUT_ONYX,
			BossDropsCards.ZULRAHS_SCALES, BossDropsCards.JAR_OF_SWAMP);

		// TODO: put this one into a different category or delete it?
		define("Dragon Equipment", CardSet.ITEMS, "Dragon gear.", ItemsCards.DRAGON_SCIMITAR);

		define("Skilling Boss Drops", CardSet.BOSS_DROPS, "Uniques from Tempoross, Wintertodt and Hespori.",
			BossDropsCards.DRAGON_HARPOON, BossDropsCards.TOME_OF_WATER_EMPTY, BossDropsCards.TOME_OF_FIRE_EMPTY,
			BossDropsCards.BOTTOMLESS_COMPOST_BUCKET, BossDropsCards.TINY_TEMPOR, BossDropsCards.PHOENIX);
		define("The Rune Pouch", CardSet.ITEMS, "Every rune.", ItemsCards.AIR_RUNE, ItemsCards.WATER_RUNE,
			ItemsCards.EARTH_RUNE, ItemsCards.FIRE_RUNE, ItemsCards.MIND_RUNE, ItemsCards.BODY_RUNE, ItemsCards.CHAOS_RUNE,
			ItemsCards.COSMIC_RUNE, ItemsCards.NATURE_RUNE, ItemsCards.LAW_RUNE, ItemsCards.DEATH_RUNE, ItemsCards.BLOOD_RUNE,
			ItemsCards.SOUL_RUNE, ItemsCards.ASTRAL_RUNE, ItemsCards.WRATH_RUNE, ItemsCards.MIST_RUNE, ItemsCards.DUST_RUNE,
			ItemsCards.MUD_RUNE, ItemsCards.SMOKE_RUNE, ItemsCards.STEAM_RUNE, ItemsCards.LAVA_RUNE, ItemsCards.SUNFIRE_RUNE,
			ItemsCards.AETHER_RUNE);

		define("Tools", CardSet.ITEMS, "Basic skilling tools.", ItemsCards.TINDERBOX, ItemsCards.ROPE,
			ItemsCards.HAMMER, ItemsCards.CHISEL, ItemsCards.KNIFE, ItemsCards.SPADE);
		define("The Herb Patch", CardSet.ITEMS, "Every herb, cleaned.", ItemsCards.GUAM_LEAF,
			ItemsCards.MARRENTILL, ItemsCards.TARROMIN, ItemsCards.HARRALANDER, ItemsCards.RANARR_WEED, ItemsCards.TOADFLAX,
			ItemsCards.IRIT_LEAF, ItemsCards.AVANTOE, ItemsCards.HUASCA, ItemsCards.KWUARM, ItemsCards.SNAPDRAGON, ItemsCards.CADANTINE,
			ItemsCards.LANTADYME, ItemsCards.DWARF_WEED, ItemsCards.TORSTOL);
		define("Every Catch", CardSet.ITEMS, "Raw fish across the levels.", ItemsCards.RAW_SHRIMPS,
			ItemsCards.RAW_ANCHOVIES, ItemsCards.RAW_SARDINE, ItemsCards.RAW_HERRING, ItemsCards.RAW_MACKEREL,
			ItemsCards.RAW_TROUT, ItemsCards.RAW_COD, ItemsCards.RAW_PIKE, ItemsCards.RAW_SALMON, ItemsCards.RAW_TUNA,
			ItemsCards.RAW_LOBSTER, ItemsCards.RAW_BASS, ItemsCards.RAW_SWORDFISH, ItemsCards.RAW_MONKFISH,
			ItemsCards.RAW_KARAMBWAN, ItemsCards.RAW_SHARK, ItemsCards.RAW_SEA_TURTLE, ItemsCards.RAW_MANTA_RAY,
			ItemsCards.RAW_ANGLERFISH, ItemsCards.RAW_DARK_CRAB);
		define("Combat Minigames", CardSet.MINIGAMES, "Combat minigames.", MinigamesCards.BARBARIAN_ASSAULT,
			MinigamesCards.PEST_CONTROL, MinigamesCards.CASTLE_WARS, MinigamesCards.LAST_MAN_STANDING, MinigamesCards.SOUL_WARS,
			MinigamesCards.NIGHTMARE_ZONE);
		define("Skilling Minigames", CardSet.MINIGAMES, "Skilling minigames.", MinigamesCards.BLAST_FURNACE,
			MinigamesCards.TITHE_FARM, MinigamesCards.PYRAMID_PLUNDER, MinigamesCards.BRIMHAVEN_AGILITY_ARENA, MinigamesCards.ROGUES_DEN,
			MinigamesCards.TROUBLE_BREWING, MinigamesCards.MAGE_TRAINING_ARENA, MinigamesCards.GNOME_RESTAURANT, MinigamesCards.VOLCANIC_MINE,
			MinigamesCards.GUARDIANS_OF_THE_RIFT);
		define("The Caves", CardSet.MINIGAMES, "The Fight Caves and the Inferno.", MinigamesCards.FIGHT_CAVES,
			MinigamesCards.INFERNO);

	}
	private static void define(String name, CardSet set, String description, CardGroup... members)
	{
		define(name, set, description, java.util.Arrays.asList(members));
	}

	private static void define(String name, CardSet set, String description, List<? extends CardGroup> members)
	{
		CardCollection collection = new CardCollection(name, set, description, members);
		ALL.add(collection);
		for (Card card : collection.cards)
		{
			BY_CARD.computeIfAbsent(card.getId(), k -> new ArrayList<>()).add(collection);
		}
	}
	private static String areaOf(String diaryName)
	{
		int split = diaryName.lastIndexOf(' ');
		if (split <= 0)
		{
			return null;
		}
		String tier = diaryName.substring(split + 1);
		boolean isTier = "Easy".equals(tier) || "Medium".equals(tier)
			|| "Hard".equals(tier) || "Elite".equals(tier);

		return isTier ? diaryName.substring(0, split) : null;
	}
	private static boolean contains(String name)
	{
		for (CardCollection collection : ALL)
		{
			if (collection.name.equals(name))
			{
				return true;
			}
		}
		return false;
	}

	public static List<CardCollection> all()
	{
		return Collections.unmodifiableList(ALL);
	}

	public static List<CardCollection> inSet(CardSet set)
	{
		return BY_SET.getOrDefault(set, Collections.emptyList());
	}

	private static final Map<CardSet, List<CardCollection>> BY_SET = indexBySet();

	private static Map<CardSet, List<CardCollection>> indexBySet()
	{
		Map<CardSet, List<CardCollection>> index = new EnumMap<>(CardSet.class);
		for (CardCollection collection : ALL)
		{
			index.computeIfAbsent(collection.set, k -> new ArrayList<>()).add(collection);
		}
		index.replaceAll((k, v) -> Collections.unmodifiableList(v));
		return index;
	}

	public static List<CardCollection> forCard(Card card)
	{
		return BY_CARD.getOrDefault(card.getId(), Collections.emptyList());
	}

	public static double multiplierFor(DopamineState state, CardSet set)
	{
		return multiplierFor(state, Collections.singletonList(set));
	}

	public static double multiplierFor(DopamineState state, List<CardSet> sets)
	{
		int tiers = 0;
		int possible = 0;
		int collections = 0;
		double ascended = 0d;
		for (CardSet set : sets)
		{
			tiers += tiersIn(state, set);
			possible += maxTiersIn(set);
			for (CardCollection collection : inSet(set))
			{
				ascended += collection.bonusFromAscension(state);
				collections++;
			}
		}
		if (possible == 0)
		{
			return 1d;
		}

		double done = (double) tiers / possible;
		double perCollection = collections == 0 ? 0d : ascended / collections;
		// Added, not compounded, and measured by share done rather than count.
		return 1d + FULL_COLLECTION_BONUS * done + perCollection;
	}

	public double bonusFromAscension(DopamineState state)
	{
		return state.getAscension(name) * BONUS_PER_ASCENSION;
	}
	public static int tiersIn(DopamineState state, CardSet set)
	{
		int tiers = 0;
		for (CardCollection collection : inSet(set))
		{
			tiers += collection.tierIn(state);
		}
		return tiers;
	}

	public static int maxTiersIn(CardSet set)
	{
		return inSet(set).size() * TIER_STARS.length;
	}

	public int size()
	{
		return cards.size();
	}
	public int ownedIn(DopamineState state)
	{
		int owned = 0;
		for (Card card : cards)
		{
			if (state.owns(card.getId()))
			{
				owned++;
			}
		}
		return owned;
	}

	public boolean isMaxed(DopamineState state)
	{
		if (cards.isEmpty())
		{
			return false;
		}
		for (Card card : cards)
		{
			if (state.getStars(card.getId()) < Rarity.MAX_STARS)
			{
				return false;
			}
		}
		return true;
	}

	public long ascensionCost(DopamineState state)
	{
		long base = 0L;
		for (Card card : cards)
		{
			base += Dust.costToMax(card.getRarity());
		}
		return Math.round(base * 0.25d * Math.pow(1.5d, state.getAscension(name)));
	}

	public boolean isComplete(DopamineState state)
	{
		return tierIn(state) > 0;
	}

	public int tierIn(DopamineState state)
	{
		if (cards.isEmpty())
		{
			return 0;
		}

		int lowest = Integer.MAX_VALUE;
		for (Card card : cards)
		{
			if (!state.owns(card.getId()))
			{
				return 0;
			}
			lowest = Math.min(lowest, state.getStars(card.getId()));
		}

		int tier = 0;
		for (int threshold : TIER_STARS)
		{
			if (lowest >= threshold)
			{
				tier++;
			}
		}
		return tier;
	}

	public String tierNameIn(DopamineState state)
	{
		int tier = tierIn(state);
		return tier <= 0 ? "Incomplete" : TIER_NAMES[Math.min(tier, TIER_NAMES.length) - 1];
	}

	public int starsForNextTier(DopamineState state)
	{
		int tier = tierIn(state);
		return tier >= TIER_STARS.length ? 0 : TIER_STARS[tier];
	}

	@Override
	public String toString()
	{
		return name;
	}
}
