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

public enum CookingCards implements CardGroup
{
	ANCHOVIES("Anchovies", Rarity.COMMON, ItemID.ANCHOVIES, -1),
	BREAD("Bread", Rarity.COMMON, ItemID.BREAD, -1),
	CHAMBERS_OF_XERIC_GUANIC_BAT("Chambers of Xeric - Guanic bat", Rarity.COMMON, ItemID.RAIDS_BAT0_COOKED, -1),
	CHAMBERS_OF_XERIC_PYSK_FISH("Chambers of Xeric - Pysk fish", Rarity.COMMON, ItemID.RAIDS_FISH0_COOKED, -1),
	CHICKEN("Chicken", Rarity.COMMON, ItemID.COOKED_CHICKEN, -1),
	KARAMBWAN("Karambwan", Rarity.COMMON, ItemID.TBWT_POORLY_COOKED_KARAMBWAN, -1),
	MEAT("Meat", Rarity.COMMON, ItemID.COOKED_MEAT, -1),
	RABBIT("Rabbit", Rarity.COMMON, ItemID.COOKED_RABBIT, -1),
	SARDINE("Sardine", Rarity.COMMON, ItemID.SARDINE, -1),
	SHRIMP("Shrimp", Rarity.COMMON, ItemID.SHRIMP, -1),
	SINEW_MADE_BY_DRYING_BEEF_OR_BEAR_MEAT_ON_A_RANGE("Sinew - made by drying beef or bear meat on a range", Rarity.COMMON, ItemID.XBOWS_SINEW, -1),
	UGTHANKI_KEBAB("Ugthanki kebab", Rarity.COMMON, ItemID.UGTHANKI_KEBAB, -1),
	HERRING("Herring", Rarity.COMMON, ItemID.HERRING, -1),
	FRUIT_BLAST("Fruit Blast", Rarity.COMMON, ItemID.FRUIT_BLAST, -1),
	BAKED_POTATO("Baked potato", Rarity.COMMON, ItemID.POTATO_BAKED, -1),
	RITUAL_OFFERING_GUPPY("Ritual Offering - Guppy", Rarity.COMMON, ItemID.GUPPY, -1),
	PINEAPPLE_PUNCH("Pineapple Punch", Rarity.COMMON, ItemID.PINEAPPLE_PUNCH, -1),
	SPICY_SAUCE_TOPPING_INGREDIENT_GARLIC_GNOME_SPICE("Spicy sauce (topping ingredient)Garlic + gnome spice", Rarity.COMMON, ItemID.BOWL_CHILLI, -1),
	MACKEREL("Mackerel", Rarity.COMMON, ItemID.MACKEREL, -1),
	REDBERRY_PIE("Redberry pie", Rarity.COMMON, ItemID.REDBERRY_PIE, -1),
	TOAD_CRUNCHIES("Toad crunchies", Rarity.COMMON, ItemID.TOAD_CRUNCHIES, -1),
	CHILLI_CON_CARNE_TOPPING_MEAT_SPICY_SAUCE("Chilli con carne (topping)Meat & spicy sauce", Rarity.COMMON, ItemID.BOWL_CHILLI_CARNE, -1),
	ROASTED_BIRD_MEAT("Roasted bird meat", Rarity.COMMON, ItemID.SPIT_ROASTED_BIRD_MEAT, -1),
	SPICY_CRUNCHIES("Spicy crunchies", Rarity.COMMON, ItemID.SPICY_CRUNCHIES, -1),
	THIN_SNAIL("Thin snail", Rarity.COMMON, ItemID.SNAIL_CORPSE_COOKED1, -1),
	SCRAMBLED_EGG_TOPPING_INGREDIENT("Scrambled egg (topping ingredient)", Rarity.COMMON, ItemID.BOWL_EGG_SCRAMBLED, -1),
	T_BONE_STEAK("T-bone Steak", Rarity.COMMON, ItemID.TBONE_STEAK, -1),
	CIDER("Cider", Rarity.COMMON, ItemID.CIDER, -1),
	WORM_CRUNCHIES("Worm crunchies", Rarity.COMMON, ItemID.WORM_CRUNCHIES, -1),
	CHAMBERS_OF_XERIC_PRAEL_BAT("Chambers of Xeric - Prael bat", Rarity.COMMON, ItemID.RAIDS_BAT1_COOKED, -1),
	CHAMBERS_OF_XERIC_SUPHI_FISH("Chambers of Xeric - Suphi fish", Rarity.COMMON, ItemID.RAIDS_FISH1_COOKED, -1),
	TROUT("Trout", Rarity.COMMON, ItemID.TROUT, -1),
	CHOCOLATE_CHIP_CRUNCHIES("Chocolate chip crunchies", Rarity.COMMON, ItemID.CHOCCHIP_CRUNCHIES, -1),
	ROASTED_RABBIT("Roasted rabbit", Rarity.COMMON, ItemID.SPIT_ROASTED_RABBIT_MEAT, -1),
	SPIDER("Spider", Rarity.COMMON, ItemID.TBW_SPIDER_ON_STICK_COOKED, -1),
	LEAN_SNAIL("Lean snail", Rarity.COMMON, ItemID.SNAIL_CORPSE_COOKED2, -1),
	COD("Cod", Rarity.COMMON, ItemID.COD, -1),
	WIZARD_BLIZZARD("Wizard Blizzard", Rarity.COMMON, ItemID.WIZARD_BLIZZARD, -1),
	DWARVEN_STOUT("Dwarven Stout", Rarity.COMMON, ItemID.DWARVEN_STOUT, -1),
	MEAT_PIE("Meat pie", Rarity.UNCOMMON, ItemID.MEAT_PIE, -1),
	NETTLE_TEA("Nettle tea", Rarity.UNCOMMON, ItemID.CUP_OF_NETTLETEA, -1),
	PIKE("Pike", Rarity.UNCOMMON, ItemID.PIKE, -1),
	RITUAL_OFFERING_CAVEFISH("Ritual Offering - Cavefish", Rarity.UNCOMMON, ItemID.CAVEFISH, -1),
	SHORT_GREEN_GUY_SGG("Short Green Guy (SGG)", Rarity.UNCOMMON, ItemID.SGG, -1),
	CREAM("Cream", Rarity.UNCOMMON, ItemID.POT_OF_CREAM, -1),
	GIANT_CRAB_MEAT("Giant crab meat", Rarity.UNCOMMON, ItemID.HUNDRED_PIRATE_GIANT_CRAB_MEAT_5, -1),
	RED_CRAB("Red crab", Rarity.UNCOMMON, ItemID.RED_CRAB, -1),
	ROASTED_BEAST_MEAT("Roasted beast meat", Rarity.UNCOMMON, ItemID.SPIT_ROASTED_BEAST_MEAT, -1),
	FAT_SNAIL("Fat snail", Rarity.UNCOMMON, ItemID.SNAIL_CORPSE_COOKED3, -1),
	SCRAMBLED_EGG_AND_TOMATO_TOPPING("Scrambled egg and tomato (topping)", Rarity.UNCOMMON, ItemID.BOWL_EGG_TOMATO, -1),
	WILD_KEBBIT("Wild kebbit", Rarity.UNCOMMON, ItemID.WILDKEBBIT_COOKED, -1),
	ASGARNIAN_ALE("Asgarnian Ale", Rarity.UNCOMMON, ItemID.ASGARNIAN_ALE, -1),
	FRUIT_BATTA("Fruit batta", Rarity.UNCOMMON, ItemID.FRUIT_BATTA, -1),
	SALMON("Salmon", Rarity.UNCOMMON, ItemID.SALMON, -1),
	STEW("Stew", Rarity.UNCOMMON, ItemID.STEW, -1),
	TOAD_BATTA("Toad batta", Rarity.UNCOMMON, ItemID.TOAD_BATTA, -1),
	WORM_BATTA("Worm batta", Rarity.UNCOMMON, ItemID.WORM_BATTA, -1),
	SLIMY_EEL("Slimy eel", Rarity.UNCOMMON, ItemID.MORT_SLIMEY_EEL_COOKED, -1),
	SWEETCORN("Sweetcorn", Rarity.UNCOMMON, ItemID.SWEETCORN_COOKED, -1),
	VEGETABLE_BATTA("Vegetable batta", Rarity.UNCOMMON, ItemID.VEGETABLE_BATTA, -1),
	CHEESE_AND_TOMATO_BATTA("Cheese and tomato batta", Rarity.UNCOMMON, ItemID.CHEESE_TOM_BATTA, -1),
	GREENMAN_S_ALE("Greenman's Ale", Rarity.UNCOMMON, ItemID.GREENMANS_ALE, -1),
	MUD_PIE("Mud pie", Rarity.UNCOMMON, ItemID.MUD_PIE, -1),
	APPLE_PIE("Apple pie", Rarity.UNCOMMON, ItemID.APPLE_PIE, -1),
	BREAM("Bream", Rarity.UNCOMMON, ItemID.BREAM_FISH_COOKED, -1),
	CHAMBERS_OF_XERIC_GIRAL_BAT("Chambers of Xeric - Giral bat", Rarity.UNCOMMON, ItemID.RAIDS_BAT2_COOKED, -1),
	CHAMBERS_OF_XERIC_LECKISH_FISH("Chambers of Xeric - Leckish fish", Rarity.UNCOMMON, ItemID.RAIDS_FISH2_COOKED, -1),
	MOSSY_LIZARD("Mossy Lizard", Rarity.UNCOMMON, ItemID.COOKED_LIZARD, -1),
	ROASTED_CHOMPY("Roasted chompy", Rarity.UNCOMMON, ItemID.COOKED_CHOMPY, -1),
	TUNA("Tuna", Rarity.UNCOMMON, ItemID.TUNA, -1),
	WORM_HOLE("Worm hole", Rarity.UNCOMMON, ItemID.WORM_HOLE, -1),
	FISHCAKES("Fishcakes", Rarity.UNCOMMON, ItemID.HUNDRED_PIRATE_FISHCAKE, -1),
	LARUPIA("Larupia", Rarity.UNCOMMON, ItemID.LARUPIA_COOKED, -1),
	BARB_TAILED_KEBBIT("Barb-tailed kebbit", Rarity.UNCOMMON, ItemID.BARBKEBBIT_COOKED, -1),
	DRUNK_DRAGON("Drunk Dragon", Rarity.UNCOMMON, ItemID.DRUNK_DRAGON, -1),
	CHOCOLATE_SATURDAY("Chocolate Saturday", Rarity.UNCOMMON, ItemID.CHOCOLATE_SATURDAY, -1),
	RITUAL_OFFERING_TETRA("Ritual Offering - Tetra", Rarity.UNCOMMON, ItemID.TETRA, -1),
	GARDEN_PIE("Garden pie", Rarity.UNCOMMON, ItemID.GARDEN_PIE, -1),
	WIZARD_S_MIND_BOMB("Wizard's Mind Bomb", Rarity.UNCOMMON, ItemID.WIZARDS_MIND_BOMB, -1),
	FORESTER_S_RATION("Forester's ration", Rarity.UNCOMMON, ItemID.FORESTRY_RATION, -1),
	PLAIN_PIZZA("Plain pizza", Rarity.UNCOMMON, ItemID.PLAIN_PIZZA, -1),
	RAINBOW_FISH("Rainbow fish", Rarity.UNCOMMON, ItemID.HUNTING_FISH_SPECIAL, -1),
	VEGETABLE_BALL("Vegetable ball", Rarity.UNCOMMON, ItemID.VEG_BALL, -1),
	WINE("Wine", Rarity.UNCOMMON, ItemID.JUG_WINE, -1),
	DAMIANA_TEA("Damiana tea", Rarity.UNCOMMON, ItemID.CUP_DAMIANA_TEA, -1),
	BLURBERRY_SPECIAL("Blurberry Special", Rarity.UNCOMMON, ItemID.BLURBERRY_SPECIAL, -1),
	BUTTER("Butter", Rarity.UNCOMMON, ItemID.POT_OF_BUTTER, -1),
	CAVE_EEL("Cave eel", Rarity.UNCOMMON, ItemID.CAVE_EEL, -1),
	BAKED_POTATO_WITH_BUTTER("Baked potato with butter", Rarity.UNCOMMON, ItemID.POTATO_BUTTER, -1),
	DRAGON_BITTER("Dragon Bitter", Rarity.UNCOMMON, ItemID.DRAGON_BITTER, -1),
	CAKE("Cake", Rarity.RARE, ItemID.CAKE, -1),
	LOBSTER("Lobster", Rarity.RARE, ItemID.LOBSTER, -1),
	TANGLED_TOADS_LEGS("Tangled toads' legs", Rarity.RARE, ItemID.TANGLED_TOADS_LEGS, -1),
	BAKED_POTATO_WITH_CHILLI_CON_CARNE("Baked potato with chilli con carne", Rarity.RARE, ItemID.POTATO_CHILLI_CARNE, -1),
	GRAAHK("Graahk", Rarity.RARE, ItemID.GRAAHK_COOKED, -1),
	JUBBLY("Jubbly", Rarity.RARE, ItemID._100_JUBBLY_MEAT_COOKED, -1),
	CHOCOLATE_BOMB("Chocolate bomb", Rarity.RARE, ItemID.CHOCOLATE_BOMB, -1),
	FRIED_ONION_TOPPING_INGREDIENT("Fried onion (topping ingredient)", Rarity.RARE, ItemID.BOWL_ONION_FRIED, -1),
	BASS("Bass", Rarity.RARE, ItemID.BASS, -1),
	MOONLIGHT_MEAD("Moonlight Mead", Rarity.RARE, ItemID.MOONLIGHT_MEAD, -1),
	CHAMBERS_OF_XERIC_BRAWK_FISH("Chambers of Xeric - Brawk fish", Rarity.RARE, ItemID.RAIDS_FISH3_COOKED, -1),
	CHAMBERS_OF_XERIC_PHLUXIA_BAT("Chambers of Xeric - Phluxia bat", Rarity.RARE, ItemID.RAIDS_BAT3_COOKED, -1),
	MEAT_PIZZA("Meat pizza", Rarity.RARE, ItemID.MEAT_PIZZA, -1),
	SWORDFISH("Swordfish", Rarity.RARE, ItemID.SWORDFISH, -1),
	FRIED_MUSHROOM_TOPPING_INGREDIENT("Fried mushroom (topping ingredient)", Rarity.RARE, ItemID.BOWL_MUSHROOM_FRIED, -1),
	RITUAL_OFFERING_CATFISH("Ritual Offering - Catfish", Rarity.RARE, ItemID.CATFISH, -1),
	BAKED_POTATO_WITH_BUTTER_AND_CHEESE("Baked potato with butter and cheese", Rarity.RARE, ItemID.POTATO_CHEESE, -1),
	FISH_PIE("Fish pie", Rarity.RARE, ItemID.FISH_PIE, -1),
	BLUE_CRAB("Blue crab", Rarity.RARE, ItemID.BLUE_CRAB, -1),
	CHEESE("Cheese", Rarity.RARE, ItemID.CHEESE, -1),
	AXEMAN_S_FOLLY("Axeman's Folly", Rarity.RARE, ItemID.AXEMANS_FOLLY, -1),
	CHOCOLATE_CAKE("Chocolate cake", Rarity.RARE, ItemID.CHOCOLATE_CAKE, -1),
	BAKED_POTATO_WITH_EGG_AND_TOMATO("Baked potato with egg and tomato", Rarity.RARE, ItemID.POTATO_EGG_TOMATO, -1),
	KYATT("Kyatt", Rarity.RARE, ItemID.KYATT_COOKED, -1),
	BOTANICAL_PIE("Botanical pie", Rarity.RARE, ItemID.BOTANICAL_PIE, -1),
	LAVA_EEL("Lava eel", Rarity.RARE, ItemID.LAVA_EEL, -1),
	CHEF_S_DELIGHT("Chef's Delight", Rarity.RARE, ItemID.CHEFS_DELIGHT, -1),
	ANCHOVY_PIZZA("Anchovy pizza", Rarity.RARE, ItemID.ANCHOVIE_PIZZA, -1),
	SWORDTIP_SQUID("Swordtip squid", Rarity.RARE, ItemID.SWORDTIP_SQUID, -1),
	FRIED_MUSHROOM_AND_ONION_TOPPING_FRIED_BITTERCAP_MUSHROOM_FRIED_ONION("Fried mushroom and onion (topping)Fried bittercap mushroom & fried onion", Rarity.RARE, ItemID.BOWL_MUSHROOM_ONION, -1),
	PITTA_BREAD("Pitta bread", Rarity.RARE, ItemID.PITTA_BREAD, -1),
	PYRE_FOX("Pyre fox", Rarity.RARE, ItemID.FENNECFOX_COOKED, -1),
	SLAYER_S_RESPITE("Slayer's Respite", Rarity.RARE, ItemID.SLAYERS_RESPITE, -1),
	CHAMBERS_OF_XERIC_KRYKET_BAT("Chambers of Xeric - Kryket bat", Rarity.EPIC, ItemID.RAIDS_BAT4_COOKED, -1),
	CHAMBERS_OF_XERIC_MYCIL_FISH("Chambers of Xeric - Mycil fish", Rarity.EPIC, ItemID.RAIDS_FISH4_COOKED, -1),
	CURRY("Curry", Rarity.EPIC, ItemID.CURRY, -1),
	MUSHROOM_PIE("Mushroom pie", Rarity.EPIC, ItemID.MUSHROOM_PIE, -1),
	MONKFISH("Monkfish", Rarity.EPIC, ItemID.MONKFISH, -1),
	BAKED_POTATO_WITH_MUSHROOM_AND_ONION("Baked potato with mushroom and onion", Rarity.EPIC, ItemID.POTATO_MUSHROOM_ONION, -1),
	PINEAPPLE_PIZZA("Pineapple pizza", Rarity.EPIC, ItemID.PINEAPPLE_PIZZA, -1),
	WINE_OF_ZAMORAK("Wine of Zamorak", Rarity.EPIC, ItemID.WINE_OF_ZAMORAK, -1),
	TUNA_AND_SWEETCORN_TOPPING_TUNA_COOKED_SWEETCORN("Tuna and sweetcorn (topping)Tuna & cooked sweetcorn", Rarity.EPIC, ItemID.BOWL_TUNA_SWEETCORN, -1),
	BAKED_POTATO_WITH_TUNA_AND_SWEETCORN("Baked potato with tuna and sweetcorn", Rarity.EPIC, ItemID.POTATO_TUNA_SWEETCORN, -1),
	SUNLIGHT_ANTELOPE("Sunlight antelope", Rarity.EPIC, ItemID.ANTELOPESUN_COOKED, -1),
	GIANT_KRILL("Giant krill", Rarity.EPIC, ItemID.GIANT_KRILL, -1),
	ADMIRAL_PIE("Admiral pie", Rarity.EPIC, ItemID.ADMIRAL_PIE, -1),
	JUMBO_SQUID("Jumbo squid", Rarity.EPIC, ItemID.JUMBO_SQUID, -1),
	DISSECT_SACRED_EELS("Dissect sacred eels", Rarity.EPIC, ItemID.SNAKEBOSS_EEL, -1),
	DRAGONFRUIT_PIE("Dragonfruit pie", Rarity.EPIC, ItemID.DRAGONFRUIT_PIE, -1),
	HADDOCK("Haddock", Rarity.EPIC, ItemID.HADDOCK, -1),
	CHAMBERS_OF_XERIC_MURNG_BAT("Chambers of Xeric - Murng bat", Rarity.EPIC, ItemID.RAIDS_BAT5_COOKED, -1),
	CHAMBERS_OF_XERIC_ROQED_FISH("Chambers of Xeric - Roqed fish", Rarity.EPIC, ItemID.RAIDS_FISH5_COOKED, -1),
	RAINBOW_CRAB("Rainbow crab", Rarity.EPIC, ItemID.RAINBOW_CRAB_A, -1),
	YELLOWFIN("Yellowfin", Rarity.EPIC, ItemID.YELLOWFIN, -1),
	SHARK("Shark", Rarity.LEGENDARY, ItemID.SHARK, -1),
	DASHING_KEBBIT("Dashing kebbit", Rarity.LEGENDARY, ItemID.DASHINGKEBBIT_COOKED, -1),
	SEA_TURTLE("Sea turtle", Rarity.LEGENDARY, ItemID.SEATURTLE, -1),
	HALIBUT("Halibut", Rarity.LEGENDARY, ItemID.HALIBUT, -1),
	ANGLERFISH("Anglerfish", Rarity.LEGENDARY, ItemID.ANGLERFISH, -1),
	WILD_PIE("Wild pie", Rarity.LEGENDARY, ItemID.WILD_PIE, -1),
	BLUEFIN("Bluefin", Rarity.LEGENDARY, ItemID.BLUEFIN, -1),
	CHAMBERS_OF_XERIC_KYREN_FISH("Chambers of Xeric - Kyren fish", Rarity.LEGENDARY, ItemID.RAIDS_FISH6_COOKED, -1),
	CHAMBERS_OF_XERIC_PSYKK_BAT("Chambers of Xeric - Psykk bat", Rarity.LEGENDARY, ItemID.RAIDS_BAT6_COOKED, -1),
	DARK_CRAB("Dark crab", Rarity.LEGENDARY, ItemID.DARK_CRAB, -1),
	MANTA_RAY("Manta ray", Rarity.LEGENDARY, ItemID.MANTARAY, -1),
	MARLIN("Marlin", Rarity.LEGENDARY, ItemID.MARLIN, -1),
	MOONLIGHT_ANTELOPE("Moonlight antelope", Rarity.LEGENDARY, ItemID.ANTELOPEMOON_COOKED, -1),
	SUMMER_PIE("Summer pie", Rarity.LEGENDARY, ItemID.SUMMER_PIE, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_COOKING, -1);

	private final Card card;

	CookingCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.COOKING, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
