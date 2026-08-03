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

public enum FarmingCards implements CardGroup
{
	POTATOES("Potatoes", Rarity.COMMON, ItemID.POTATO, -1),
	MARIGOLDS("Marigolds", Rarity.COMMON, ItemID.MARIGOLD, -1),
	BARLEY("Barley", Rarity.COMMON, ItemID.BARLEY, -1),
	HAMMERSTONE_HOPS("Hammerstone hops", Rarity.COMMON, ItemID.HAMMERSTONE_HOPS, -1),
	ONIONS("Onions", Rarity.COMMON, ItemID.ONION, -1),
	CABBAGES("Cabbages", Rarity.COMMON, ItemID.CABBAGE, -1),
	ASGARNIAN_HOPS("Asgarnian hops", Rarity.COMMON, ItemID.ASGARNIAN_HOPS, -1),
	GUAM_LEAF("Guam leaf", Rarity.COMMON, ItemID.GUAM_LEAF, -1),
	REDBERRY_BUSHES("Redberry bushes", Rarity.COMMON, ItemID.REDBERRIES, -1),
	ROSEMARY("Rosemary", Rarity.COMMON, ItemID.ROSEMARY, -1),
	TOMATOES("Tomatoes", Rarity.COMMON, ItemID.TOMATO, -1),
	JUTE_PLANTS("Jute plants", Rarity.COMMON, ItemID.JUTE_FIBRE, -1),
	MARRENTILL("Marrentill", Rarity.COMMON, ItemID.MARENTILL, -1),
	OAK_TREES("Oak trees", Rarity.COMMON, ItemID.OAK_LOGS, -1),
	YANILLIAN_HOPS("Yanillian hops", Rarity.COMMON, ItemID.YANILLIAN_HOPS, -1),
	FLAX("Flax", Rarity.COMMON, ItemID.FLAX, -1),
	TARROMIN("Tarromin", Rarity.COMMON, ItemID.TARROMIN, -1),
	SWEETCORN("Sweetcorn", Rarity.UNCOMMON, ItemID.SWEETCORN, -1),
	KRANDORIAN_HOPS("Krandorian hops", Rarity.UNCOMMON, ItemID.KRANDORIAN_HOPS, -1),
	CADAVABERRY_BUSHES("Cadavaberry bushes", Rarity.UNCOMMON, ItemID.CADAVABERRIES, -1),
	GIANT_SEAWEED("Giant Seaweed", Rarity.UNCOMMON, ItemID.GIANT_SEAWEED, -1),
	MAKE_AND_PLACE_A_SCARECROW("Make and place a scarecrow", Rarity.UNCOMMON, ItemID.SCARECROW_COMPLETE, -1),
	NASTURTIUMS("Nasturtiums", Rarity.UNCOMMON, ItemID.NASTURTIUM, -1),
	WOAD("Woad", Rarity.UNCOMMON, ItemID.WOADLEAF_DUMMY, -1),
	HARRALANDER("Harralander", Rarity.UNCOMMON, ItemID.HARRALANDER, -1),
	LIMPWURT_PLANTS("Limpwurt plants", Rarity.UNCOMMON, ItemID.LIMPWURT_ROOT, -1),
	APPLE_TREES("Apple trees", Rarity.UNCOMMON, ItemID.COOKING_APPLE, -1),
	GOLPAR("Golpar", Rarity.UNCOMMON, ItemID.RAIDS_GOLPAR, -1),
	ELKHORN_CORAL("Elkhorn coral", Rarity.UNCOMMON, ItemID.CORAL_ELKHORN, -1),
	WILDBLOOD_HOPS("Wildblood hops", Rarity.UNCOMMON, ItemID.WILDBLOOD_HOPS, -1),
	GOUTWEED("Goutweed", Rarity.UNCOMMON, ItemID.EADGAR_GOUTWEED_HERB, -1),
	WILLOW_TREES("Willow trees", Rarity.UNCOMMON, ItemID.WILLOW_LOGS, -1),
	STRAWBERRIES("Strawberries", Rarity.UNCOMMON, ItemID.STRAWBERRY, -1),
	RANARR_WEED("Ranarr weed", Rarity.UNCOMMON, ItemID.RANARR_WEED, -1),
	BANANA_TREES("Banana trees", Rarity.UNCOMMON, ItemID.BANANA, -1),
	GOLOVANOVA_FRUIT("Golovanova fruit", Rarity.UNCOMMON, ItemID.HOSIDIUS_TITHE_FRUIT_A, -1),
	TEAK_TREES("Teak trees", Rarity.UNCOMMON, ItemID.TEAK_LOGS, -1),
	DWELLBERRY_BUSHES("Dwellberry bushes", Rarity.UNCOMMON, ItemID.DWELLBERRIES, -1),
	GRAPES("Grapes", Rarity.UNCOMMON, ItemID.GRAPES, -1),
	SULPHUROUS_FERTILISER("Sulphurous fertiliser", Rarity.UNCOMMON, ItemID.BUCKET_SULPHUROUS_FERTILISER, -1),
	HEMP("Hemp", Rarity.UNCOMMON, ItemID.HEMP, -1),
	TOADFLAX("Toadflax", Rarity.UNCOMMON, ItemID.TOADFLAX, -1),
	BUCHU_LEAF("Buchu leaf", Rarity.UNCOMMON, ItemID.RAIDS_BUCHULEAF, -1),
	ORANGE_TREES("Orange trees", Rarity.UNCOMMON, ItemID.ORANGE, -1),
	CURRY_TREES("Curry trees", Rarity.RARE, ItemID.CURRY_LEAF, -1),
	IRIT_LEAF("Irit leaf", Rarity.RARE, ItemID.IRIT_LEAF, -1),
	FARMING_GUILD_LOW_TIER("Farming Guild - Low tier", Rarity.RARE, ItemID.FARMING_GUILD_DOOR_DUMMY, -1),
	MAPLE_TREES("Maple trees", Rarity.RARE, ItemID.MAPLE_LOGS, -1),
	WATERMELONS("Watermelons", Rarity.RARE, ItemID.WATERMELON, -1),
	JANGERBERRY_BUSHES("Jangerberry bushes", Rarity.RARE, ItemID.JANGERBERRIES, -1),
	AVANTOE("Avantoe", Rarity.RARE, ItemID.AVANTOE, -1),
	NATURE_OFFERINGS("Nature Offerings", Rarity.RARE, ItemID.NATURE_OFFERINGS, -1),
	PINEAPPLE_PLANTS("Pineapple plants", Rarity.RARE, ItemID.PINEAPPLE, -1),
	PILLAR_CORAL("Pillar coral", Rarity.RARE, ItemID.CORAL_PILLAR, -1),
	BITTERCAP_MUSHROOMS("Bittercap mushrooms", Rarity.RARE, ItemID.BITTERCAP_MUSHROOM, -1),
	BOLOGANO_FRUIT("Bologano fruit", Rarity.RARE, ItemID.HOSIDIUS_TITHE_FRUIT_B, -1),
	CACTI("Cacti", Rarity.RARE, ItemID.CACTUS_SPINE, -1),
	MAHOGANY_TREES("Mahogany trees", Rarity.RARE, ItemID.MAHOGANY_LOGS, -1),
	NOXIFER("Noxifer", Rarity.RARE, ItemID.RAIDS_NOXIFER, -1),
	KWUARM("Kwuarm", Rarity.RARE, ItemID.KWUARM, -1),
	PAPAYA_TREES("Papaya trees", Rarity.RARE, ItemID.PAPAYA, -1),
	WHITE_LILY("White lily", Rarity.RARE, ItemID.WHITELILLY, -1),
	WHITE_BERRY_BUSHES("White berry bushes", Rarity.RARE, ItemID.WHITE_BERRIES, -1),
	YEW_TREES("Yew trees", Rarity.EPIC, ItemID.YEW_LOGS, -1),
	SNAPE_GRASS("Snape grass", Rarity.EPIC, ItemID.SNAPE_GRASS, -1),
	SNAPDRAGON("Snapdragon", Rarity.EPIC, ItemID.SNAPDRAGON, -1),
	BELLADONNA("Belladonna", Rarity.EPIC, ItemID.NIGHTSHADE, -1),
	POTATO_CACTI("Potato Cacti", Rarity.EPIC, ItemID.CACTUS_POTATO, -1),
	FARMING_GUILD_MID_TIER("Farming Guild - Mid tier", Rarity.EPIC, ItemID.FARMING_GUILD_DOOR_DUMMY, -1),
	HESPORI("Hespori", Rarity.EPIC, ItemID.HESPORI, -1),
	HUASCA("Huasca", Rarity.EPIC, ItemID.HUASCA, -1),
	CAMPHOR_TREES("Camphor trees", Rarity.EPIC, ItemID.CAMPHOR_LOGS, -1),
	CADANTINE("Cadantine", Rarity.EPIC, ItemID.CADANTINE, -1),
	PALM_TREES("Palm trees", Rarity.EPIC, ItemID.COCONUT, -1),
	POISON_IVY_BUSHES("Poison ivy bushes", Rarity.EPIC, ItemID.POISONIVY_BERRIES, -1),
	COTTON_BOLLS("Cotton bolls", Rarity.EPIC, ItemID.COTTON_BOLL, -1),
	CALQUAT_TREES("Calquat trees", Rarity.EPIC, ItemID.CALQUAT_FRUIT, -1),
	LANTADYME("Lantadyme", Rarity.EPIC, ItemID.LANTADYME, -1),
	CRYSTAL_TREES("Crystal trees", Rarity.EPIC, ItemID.PRIF_CRYSTAL_SHARD, -1),
	LOGAVANO_FRUIT("Logavano fruit", Rarity.EPIC, ItemID.HOSIDIUS_TITHE_FRUIT_C, -1),
	MAGIC_TREES("Magic trees", Rarity.EPIC, ItemID.MAGIC_LOGS, -1),
	ANIMA_ATTAS("Anima: Attas", Rarity.EPIC, ItemID.ANIMA_ATTAS, -1),
	ANIMA_IASOR("Anima: Iasor", Rarity.EPIC, ItemID.ANIMA_IASOR, -1),
	ANIMA_KRONOS("Anima: Kronos", Rarity.EPIC, ItemID.ANIMA_KRONOS, -1),
	UMBRAL_CORAL("Umbral coral", Rarity.EPIC, ItemID.CORAL_UMBRAL, -1),
	DWARF_WEED("Dwarf weed", Rarity.EPIC, ItemID.DWARF_WEED, -1),
	IRONWOOD_TREES("Ironwood trees", Rarity.LEGENDARY, ItemID.IRONWOOD_LOGS, -1),
	DRAGONFRUIT_TREES("Dragonfruit trees", Rarity.LEGENDARY, ItemID.DRAGONFRUIT, -1),
	SPIRIT_TREES_1_PLANTED_AT_A_TIME("Spirit trees: 1 planted at a time", Rarity.LEGENDARY, ItemID.SPIRIT_TREE_DUMMY, -1),
	CELASTRUS_TREES("Celastrus trees", Rarity.LEGENDARY, ItemID.CELASTRUS_WOOD, -1),
	FARMING_GUILD_HIGH_TIER("Farming Guild - High tier", Rarity.LEGENDARY, ItemID.FARMING_GUILD_DOOR_DUMMY, -1),
	TORSTOL("Torstol", Rarity.LEGENDARY, ItemID.TORSTOL, -1),
	REDWOOD_TREES("Redwood trees", Rarity.LEGENDARY, ItemID.REDWOOD_LOGS, -1),
	ROSEWOOD_TREES("Rosewood trees", Rarity.LEGENDARY, ItemID.ROSEWOOD_LOGS, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_FARMING, -1);

	private final Card card;

	FarmingCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.FARMING, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
