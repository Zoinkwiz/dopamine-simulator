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

import lombok.Getter;

import com.dopaminesimulator.cards.sets.DiariesCards;
import com.dopaminesimulator.cards.sets.BossDropsCards;
import com.dopaminesimulator.cards.sets.BossesCards;
import com.dopaminesimulator.cards.sets.ItemsCards;
import com.dopaminesimulator.cards.sets.MinigamesCards;
import com.dopaminesimulator.cards.sets.QuestsCards;
import com.dopaminesimulator.cards.sets.SkillsCards;
import com.dopaminesimulator.cards.sets.SlayerCards;
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
			BossesCards.ALCHEMICAL_HYDRA, BossesCards.GROTESQUE_GUARDIANS);
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
		define("Chambers of Xeric", CardSet.ITEMS, "Uniques from the Chambers of Xeric.",
			ItemsCards.KODAI_WAND, ItemsCards.ANCESTRAL_HAT, ItemsCards.ANCESTRAL_ROBE_TOP, ItemsCards.ELDER_MAUL,
			ItemsCards.DRAGON_CLAWS, ItemsCards.TWISTED_BUCKLER, ItemsCards.DRAGON_HUNTER_CROSSBOW, ItemsCards.DINH_S_BULWARK,
			ItemsCards.DEXTEROUS_PRAYER_SCROLL, ItemsCards.ARCANE_PRAYER_SCROLL, ItemsCards.TWISTED_BOW);

		define("Theatre of Blood", CardSet.ITEMS, "Uniques from the Theatre of Blood.",
			ItemsCards.GHRAZI_RAPIER, ItemsCards.SANGUINESTI_STAFF, ItemsCards.JUSTICIAR_FACEGUARD,
			ItemsCards.JUSTICIAR_CHESTGUARD, ItemsCards.AVERNIC_DEFENDER, ItemsCards.SCYTHE_OF_VITUR);

		define("Tombs of Amascut", CardSet.ITEMS, "Uniques from the Tombs of Amascut.",
			ItemsCards.OSMUMTEN_S_FANG, ItemsCards.LIGHTBEARER, ItemsCards.ELIDINIS_WARD, ItemsCards.MASORI_MASK,
			ItemsCards.MASORI_BODY, ItemsCards.TUMEKEN_S_SHADOW);

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

		define("Dragon Equipment", CardSet.ITEMS, "Dragon gear.", ItemsCards.DRAGON_SCIMITAR,
			ItemsCards.DRAGON_CLAWS, ItemsCards.DRAGON_HUNTER_CROSSBOW);

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
