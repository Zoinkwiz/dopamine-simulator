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

public enum HunterCards implements CardGroup
{
	CHAMBERS_OF_XERIC_GUANIC_BAT("Chambers of Xeric - Guanic bat", Rarity.COMMON, ItemID.RAIDS_BAT0_RAW, -1),
	COMMON_KEBBIT_FUR_CLOTHING("Common kebbit fur clothing", Rarity.COMMON, ItemID.HUNTING_CAMOFLAUGE_ROBE_WOOD, -1),
	CRIMSON_SWIFTS("Crimson swifts", Rarity.COMMON, ItemID.HUNTGUIDE_JUNGLE_BIRD, -1),
	POLAR_KEBBIT_FUR_CLOTHING("Polar kebbit fur clothing", Rarity.COMMON, ItemID.HUNTING_CAMOFLAUGE_ROBE_POLAR, -1),
	POLAR_KEBBITS("Polar kebbits", Rarity.COMMON, ItemID.HUNTGUIDE_POLAR_BEAST, -1),
	COMMON_KEBBITS("Common kebbits", Rarity.COMMON, ItemID.HUNTGUIDE_WOOD_BEAST, -1),
	FELDIP_WEASEL_FUR_CLOTHING("Feldip weasel fur clothing", Rarity.COMMON, ItemID.HUNTING_CAMOFLAUGE_ROBE_JUNGLE, -1),
	GOLDEN_WARBLERS("Golden warblers", Rarity.COMMON, ItemID.HUNTGUIDE_DESERT_BIRD, -1),
	NORMAL_BIRDHOUSE_TRAP("Normal Birdhouse Trap", Rarity.COMMON, ItemID.BIRDHOUSE_NORMAL, -1),
	FELDIP_WEASELS("Feldip weasels", Rarity.COMMON, ItemID.HUNTGUIDE_JUNGLE_BEAST, -1),
	COPPER_LONGTAILS("Copper longtails", Rarity.COMMON, ItemID.HUNTGUIDE_WOOD_BIRD, -1),
	DESERT_DEVIL_FUR_CLOTHING("Desert devil fur clothing", Rarity.COMMON, ItemID.HUNTING_CAMOFLAUGE_ROBE_DESERT, -1),
	CERULEAN_TWITCHES("Cerulean twitches", Rarity.COMMON, ItemID.HUNTGUIDE_POLAR_BIRD, -1),
	DESERT_DEVILS("Desert devils", Rarity.COMMON, ItemID.HUNTGUIDE_DESERT_BEAST, -1),
	OAK_BIRDHOUSE_TRAP("Oak Birdhouse Trap", Rarity.COMMON, ItemID.BIRDHOUSE_OAK, -1),
	CHAMBERS_OF_XERIC_PRAEL_BAT("Chambers of Xeric - Prael bat", Rarity.COMMON, ItemID.RAIDS_BAT1_RAW, -1),
	RUBY_HARVEST_BUTTERFLIES("Ruby harvest butterflies", Rarity.COMMON, ItemID.HUNTGUIDE_RED_BUTTERFLY, -1),
	BABY_IMPLINGS("Baby implings", Rarity.COMMON, ItemID.II_CAPTURED_IMPLING_1, -1),
	TROPICAL_WAGTAILS("Tropical wagtails", Rarity.COMMON, ItemID.HUNTGUIDE_COLOURED_BIRD, -1),
	MOSSY_LIZARDS_TRAPS("Mossy Lizards traps", Rarity.UNCOMMON, ItemID.RAW_LIZARD, -1),
	RED_CRABS("Red crabs", Rarity.UNCOMMON, ItemID.RED_CRAB, -1),
	YOUNG_IMPLINGS("Young implings", Rarity.UNCOMMON, ItemID.II_CAPTURED_IMPLING_2, -1),
	WILD_KEBBITS("Wild kebbits", Rarity.UNCOMMON, ItemID.HUNTGUIDE_HUNTING_BEAST, -1),
	LUCKY_RABBIT_FOOT("Lucky rabbit foot", Rarity.UNCOMMON, ItemID.HUNTING_STRUNG_RABBIT_FOOT, -1),
	WILLOW_BIRDHOUSE_TRAP("Willow Birdhouse Trap", Rarity.UNCOMMON, ItemID.BIRDHOUSE_WILLOW, -1),
	SAPPHIRE_GLACIALIS_BUTTERFLIES("Sapphire glacialis butterflies", Rarity.UNCOMMON, ItemID.HUNTGUIDE_BLUE_BUTTERFLY, -1),
	FERRETS("Ferrets", Rarity.UNCOMMON, ItemID.HUNTING_FERRET, -1),
	GIANT_EAGLES("Giant eagles", Rarity.UNCOMMON, ItemID.HUNTGUIDE_GIANT_EAGLE, -1),
	WHITE_RABBITS("White rabbits", Rarity.UNCOMMON, ItemID.HUNTGUIDE_RABBIT, -1),
	GOURMET_IMPLINGS("Gourmet implings", Rarity.UNCOMMON, ItemID.II_CAPTURED_IMPLING_3, -1),
	LARUPIA_FUR_CLOTHING("Larupia fur clothing", Rarity.UNCOMMON, ItemID.HUNTING_HAT_JAGUAR, -1),
	SWAMP_LIZARDS("Swamp lizards", Rarity.UNCOMMON, ItemID.GREEN_SALAMANDER, -1),
	CHAMBERS_OF_XERIC_GIRAL_BAT("Chambers of Xeric - Giral bat", Rarity.UNCOMMON, ItemID.RAIDS_BAT2_RAW, -1),
	SPINED_LARUPIAS("Spined larupias", Rarity.UNCOMMON, ItemID.HUNTING_HAT_JAGUAR, -1),
	BARB_TAILED_KEBBITS("Barb-tailed kebbits", Rarity.UNCOMMON, ItemID.HUNTGUIDE_BARBTAILED_BEAST, -1),
	TEAK_BIRDHOUSE_TRAP("Teak Birdhouse Trap", Rarity.UNCOMMON, ItemID.BIRDHOUSE_TEAK, -1),
	BLUEGILL("Bluegill", Rarity.UNCOMMON, ItemID.AERIAL_FISHING_BLUEGILL, -1),
	CORMORANT_S_GLOVE("Cormorant's glove", Rarity.UNCOMMON, ItemID.AERIAL_FISHING_GLOVES_NO_BIRD, -1),
	SNOWY_KNIGHT_BUTTERFLIES("Snowy knight butterflies", Rarity.UNCOMMON, ItemID.HUNTGUIDE_WHITE_BUTTERFLY, -1),
	EARTH_IMPLINGS("Earth implings", Rarity.UNCOMMON, ItemID.II_CAPTURED_IMPLING_4, -1),
	PRICKLY_KEBBITS("Prickly kebbits", Rarity.UNCOMMON, ItemID.HUNTGUIDE_RAZOR_BEAST, -1),
	GRAAHK_HIDE_CLOTHING("Graahk hide clothing", Rarity.UNCOMMON, ItemID.HUNTING_HAT_LEOPARD, -1),
	EMBERTAILED_JERBOA("Embertailed jerboa", Rarity.UNCOMMON, ItemID.HUNTGUIDE_JERBOA, -1),
	SPOTTED_CAPES("Spotted capes", Rarity.RARE, ItemID.HUNTING_LIGHT_CAPE, -1),
	HORNED_GRAAHKS("Horned graahks", Rarity.RARE, ItemID.HUNTING_HAT_LEOPARD, -1),
	ESSENCE_IMPLINGS("Essence implings", Rarity.RARE, ItemID.II_CAPTURED_IMPLING_5, -1),
	SPOTTED_KEBBITS("Spotted kebbits", Rarity.RARE, ItemID.HUNTGUIDE_SPEEDY_BEAST, -1),
	MAPLE_BIRDHOUSE_TRAP("Maple Birdhouse Trap", Rarity.RARE, ItemID.BIRDHOUSE_MAPLE, -1),
	BLACK_WARLOCK_BUTTERFLIES("Black warlock butterflies", Rarity.RARE, ItemID.HUNTGUIDE_BLACK_BUTTERFLY, -1),
	CHAMBERS_OF_XERIC_PHLUXIA_BAT("Chambers of Xeric - Phluxia bat", Rarity.RARE, ItemID.RAIDS_BAT3_RAW, -1),
	ORANGE_SALAMANDERS("Orange salamanders", Rarity.RARE, ItemID.ORANGE_SALAMANDER, -1),
	BLUE_CRABS("Blue crabs", Rarity.RARE, ItemID.BLUE_CRAB, -1),
	MAHOGANY_BIRDHOUSE_TRAP("Mahogany Birdhouse Trap", Rarity.RARE, ItemID.BIRDHOUSE_MAHOGANY, -1),
	RAZOR_BACKED_KEBBITS("Razor-backed kebbits", Rarity.RARE, ItemID.HUNTGUIDE_RAZOR2_BEAST, -1),
	ECLECTIC_IMPLINGS("Eclectic implings", Rarity.RARE, ItemID.II_CAPTURED_IMPLING_6, -1),
	HORN_OF_PLENTY("Horn of Plenty", Rarity.RARE, ItemID.HORN_OF_PLENTY, -1),
	SUNLIGHT_HUNTERS_CROSSBOW("Sunlight hunters' crossbow", Rarity.RARE, ItemID.HUNTING_CROSSBOW_SUNLIGHT, -1),
	COMMON_TENCH("Common tench", Rarity.RARE, ItemID.AERIAL_FISHING_COMMON_TENCH, -1),
	SABRE_TOOTHED_KEBBITS("Sabre-toothed kebbits", Rarity.RARE, ItemID.HUNTGUIDE_SABRE_BEAST, -1),
	KYATT_FUR_CLOTHING("Kyatt fur clothing", Rarity.RARE, ItemID.HUNTING_HAT_TIGER, -1),
	CHINCHOMPAS("Chinchompas", Rarity.RARE, ItemID.HUNTGUIDE_CHINCHOMPA_CAPTURED, -1),
	GLOVES_OF_SILENCE("Gloves of Silence", Rarity.RARE, ItemID.HUNTING_SILENT_GLOVES, -1),
	SABRE_TOOTHED_KYATTS("Sabre-toothed kyatts", Rarity.RARE, ItemID.HUNTING_HAT_TIGER, -1),
	DARK_KEBBITS("Dark kebbits", Rarity.RARE, ItemID.HUNTGUIDE_SILENT_BEAST, -1),
	PYRE_FOXES("Pyre foxes", Rarity.RARE, ItemID.HUNTGUIDE_FENNECFOX, -1),
	NATURE_IMPLINGS("Nature implings", Rarity.RARE, ItemID.II_CAPTURED_IMPLING_7, -1),
	RED_SALAMANDERS("Red salamanders", Rarity.RARE, ItemID.RED_SALAMANDER, -1),
	YEW_BIRDHOUSE_TRAP("Yew Birdhouse Trap", Rarity.RARE, ItemID.BIRDHOUSE_YEW, -1),
	CHAMBERS_OF_XERIC_KRYKET_BAT("Chambers of Xeric - Kryket bat", Rarity.EPIC, ItemID.RAIDS_BAT4_RAW, -1),
	MANIACAL_MONKEYS("Maniacal monkeys", Rarity.EPIC, ItemID.MM2_MONKEY, -1),
	WYRMSCRAIG_GOAT_PIT("Wyrmscraig Goat Pit", Rarity.EPIC, 34017, -1),
	RED_CHINCHOMPAS("Red chinchompas", Rarity.EPIC, ItemID.HUNTGUIDE_CHINCHOMPA_BIG_CAPTURED, -1),
	MAGPIE_IMPLINGS("Magpie implings", Rarity.EPIC, ItemID.II_CAPTURED_IMPLING_8, -1),
	SUNLIGHT_MOTHS("Sunlight moths", Rarity.EPIC, ItemID.HUNTGUIDE_SUNLIGHT_MOTH, -1),
	SPOTTIER_CAPES("Spottier capes", Rarity.EPIC, ItemID.HUNTING_LIGHTER_CAPE, -1),
	BLACK_SALAMANDERS("Black salamanders", Rarity.EPIC, ItemID.BLACK_SALAMANDER, -1),
	MOTTLED_EEL("Mottled eel", Rarity.EPIC, ItemID.AERIAL_FISHING_MOTTLED_EEL, -1),
	DASHING_KEBBITS("Dashing kebbits", Rarity.EPIC, ItemID.HUNTGUIDE_SPEEDY2_BEAST, -1),
	IMPS("Imps", Rarity.EPIC, ItemID.HUNTGUIDE_IMP, -1),
	SUNLIGHT_ANTELOPE("Sunlight antelope", Rarity.EPIC, ItemID.HUNTGUIDE_SUN_ANTLERS, -1),
	BLACK_CHINCHOMPAS("Black chinchompas", Rarity.EPIC, ItemID.CERT_RANGE_ICON_DUMMY, -1),
	MAGIC_BIRDHOUSE_TRAP("Magic Birdhouse Trap", Rarity.EPIC, ItemID.BIRDHOUSE_MAGIC, -1),
	NINJA_IMPLINGS("Ninja implings", Rarity.EPIC, ItemID.II_CAPTURED_IMPLING_9, -1),
	CHAMBERS_OF_XERIC_MURNG_BAT("Chambers of Xeric - Murng bat", Rarity.EPIC, ItemID.RAIDS_BAT5_RAW, -1),
	MOONLIGHT_MOTHS("Moonlight moths", Rarity.EPIC, ItemID.HUNTGUIDE_MOONLIGHT_MOTH, -1),
	LETVEK("Letvek", Rarity.EPIC, ItemID.HUNTGUIDE_LETVEK, -1),
	RAINBOW_CRABS("Rainbow crabs", Rarity.EPIC, ItemID.RAINBOW_CRAB_A, -1),
	TECU_SALAMANDERS("Tecu salamanders", Rarity.EPIC, ItemID.MOUNTAIN_SALAMANDER, -1),
	CRYSTAL_IMPLINGS("Crystal implings", Rarity.LEGENDARY, ItemID.II_CAPTURED_IMPLING_12, -1),
	HERBIBOAR("Herbiboar", Rarity.LEGENDARY, ItemID.HUNTGUIDE_HERBIBOAR, -1),
	STYMPHIKES("Stymphikes", Rarity.LEGENDARY, ItemID.HUNTGUIDE_STYMPHIKE, -1),
	DRAGON_IMPLINGS("Dragon implings", Rarity.LEGENDARY, ItemID.II_CAPTURED_IMPLING_10, -1),
	GREATER_SIREN("Greater siren", Rarity.LEGENDARY, ItemID.AERIAL_FISHING_GREATER_SIREN, -1),
	LUCKY_IMPLINGS("Lucky implings", Rarity.LEGENDARY, ItemID.II_CAPTURED_IMPLING_11, -1),
	REDWOOD_BIRDHOUSE_TRAP("Redwood Birdhouse Trap", Rarity.LEGENDARY, ItemID.BIRDHOUSE_REDWOOD, -1),
	CHAMBERS_OF_XERIC_PSYKK_BAT("Chambers of Xeric - Psykk bat", Rarity.LEGENDARY, ItemID.RAIDS_BAT6_RAW, -1),
	MOONLIGHT_ANTELOPE("Moonlight antelope", Rarity.LEGENDARY, ItemID.HUNTGUIDE_MOON_ANTLERS, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_HUNTING, -1);

	private final Card card;

	HunterCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.HUNTER, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
