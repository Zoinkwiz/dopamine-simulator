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

public enum FletchingCards implements CardGroup
{
	N15_ARROW_SHAFTS_LOGS("15 Arrow shafts (Logs)", Rarity.COMMON, ItemID.ARROW_SHAFT_5, -1),
	BRONZE_ARROWS("Bronze arrows", Rarity.COMMON, ItemID.BRONZE_ARROW, -1),
	BRONZE_JAVELINS("Bronze javelins", Rarity.COMMON, ItemID.BRONZE_JAVELIN, -1),
	SHORTBOWS("Shortbows", Rarity.COMMON, ItemID.SHORTBOW, -1),
	BRONZE_BRUTAL_ARROWS("Bronze 'brutal' arrows", Rarity.COMMON, ItemID.ZOGRE_BRUTAL_BRONZE, -1),
	BRONZE_BOLTS("Bronze bolts", Rarity.COMMON, ItemID.BOLT, -1),
	BRONZE_WOODEN_CROSSBOWS("Bronze/wooden crossbows", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_STOCK_WOOD, -1),
	BRONZE_DARTS("Bronze darts", Rarity.COMMON, ItemID.BRONZE_DART, -1),
	LONGBOWS("Longbows", Rarity.COMMON, ItemID.LONGBOW, -1),
	OPAL_TIPPED_BRONZE_BOLTS("Opal-tipped bronze bolts", Rarity.COMMON, ItemID.OPAL_BOLT, -1),
	N30_ARROW_SHAFTS_OAK_LOGS("30 Arrow shafts (Oak logs)", Rarity.COMMON, ItemID.ARROW_SHAFT_5, -1),
	IRON_ARROWS("Iron arrows", Rarity.COMMON, ItemID.IRON_ARROW, -1),
	IRON_JAVELINS("Iron javelins", Rarity.COMMON, ItemID.IRON_JAVELIN, -1),
	IRON_BRUTAL_ARROWS("Iron 'brutal' arrows", Rarity.COMMON, ItemID.ZOGRE_BRUTAL_IRON, -1),
	OAK_SHORTBOWS("Oak shortbows", Rarity.UNCOMMON, ItemID.OAK_SHORTBOW, -1),
	OAK_VALE_TOTEMS("Oak vale totems", Rarity.UNCOMMON, ItemID.OAK_LOGS, -1),
	IRON_DARTS("Iron darts", Rarity.UNCOMMON, ItemID.IRON_DART, -1),
	BLURITE_BOLTS("Blurite bolts", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_BLURITE, -1),
	BLURITE_OAK_CROSSBOWS("Blurite/oak crossbows", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_STOCK_OAK, -1),
	OAK_LONGBOWS("Oak longbows", Rarity.UNCOMMON, ItemID.OAK_LONGBOW, -1),
	JADE_TIPPED_BLURITE_BOLTS("Jade-tipped blurite bolts", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_BLURITE_TIPPED_JADE, -1),
	OAK_SHIELD("Oak shield", Rarity.UNCOMMON, ItemID.OAK_SHIELD, -1),
	N45_ARROW_SHAFTS_WILLOW_LOGS("45 Arrow shafts (Willow logs)", Rarity.UNCOMMON, ItemID.ARROW_SHAFT_5, -1),
	OGRE_COMPOSITE_BOWS("Ogre composite bows", Rarity.UNCOMMON, ItemID.ZOGRE_BOW, -1),
	STEEL_ARROWS("Steel arrows", Rarity.UNCOMMON, ItemID.STEEL_ARROW, -1),
	KEBBIT_TIPPED_HUNTING_BOLTS("Kebbit-tipped hunting bolts", Rarity.UNCOMMON, ItemID.HUNTINGBOW_BOLTS, -1),
	STEEL_JAVELINS("Steel javelins", Rarity.UNCOMMON, ItemID.STEEL_JAVELIN, -1),
	STEEL_BRUTAL_ARROWS("Steel 'brutal' arrows", Rarity.UNCOMMON, ItemID.ZOGRE_BRUTAL_STEEL, -1),
	BONE_SHORTBOW("Bone shortbow", Rarity.UNCOMMON, ItemID.RAT_BONE_BOW, -1),
	WILLOW_SHORTBOWS("Willow shortbows", Rarity.UNCOMMON, ItemID.WILLOW_SHORTBOW, -1),
	WILLOW_VALE_TOTEMS("Willow vale totems", Rarity.UNCOMMON, ItemID.WILLOW_LOGS, -1),
	STEEL_DARTS("Steel darts", Rarity.UNCOMMON, ItemID.STEEL_DART, -1),
	BLACK_BRUTAL_ARROWS("Black 'brutal' arrows", Rarity.UNCOMMON, ItemID.ZOGRE_BRUTAL_BLACK, -1),
	IRON_BOLTS("Iron bolts", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_IRON, -1),
	IRON_WILLOW_CROSSBOWS("Iron/willow crossbows", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_STOCK_WILLOW, -1),
	BATTLESTAVES("Battlestaves", Rarity.RARE, ItemID.BATTLESTAFF, -1),
	WILLOW_LONGBOWS("Willow longbows", Rarity.RARE, ItemID.WILLOW_LONGBOW, -1),
	PEARL_TIPPED_IRON_BOLTS("Pearl-tipped iron bolts", Rarity.RARE, ItemID.PEARL_BOLT, -1),
	LONG_KEBBIT_TIPPED_HUNTING_BOLTS("Long kebbit-tipped hunting bolts", Rarity.RARE, ItemID.HUNTINGBOW_BIGBOLTS, -1),
	WILLOW_SHIELD("Willow shield", Rarity.RARE, ItemID.WILLOW_SHIELD, -1),
	SILVER_BOLTS("Silver bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_SILVER, -1),
	N60_ARROW_SHAFTS_MAPLE_LOGS("60 Arrow shafts (Maple logs)", Rarity.RARE, ItemID.ARROW_SHAFT_5, -1),
	MITHRIL_ARROWS("Mithril arrows", Rarity.RARE, ItemID.MITHRIL_ARROW, -1),
	STEEL_BOLTS("Steel bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_STEEL, -1),
	STEEL_TEAK_CROSSBOWS("Steel/teak crossbows", Rarity.RARE, ItemID.XBOWS_CROSSBOW_STOCK_TEAK, -1),
	LIGHT_BALLISTAE("Light ballistae", Rarity.RARE, ItemID.LIGHT_BALLISTA, -1),
	MITHRIL_JAVELINS("Mithril javelins", Rarity.RARE, ItemID.MITHRIL_JAVELIN, -1),
	RED_TOPAZ_TIPPED_STEEL_BOLTS("Red topaz-tipped steel bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_STEEL_TIPPED_REDTOPAZ, -1),
	MITHRIL_BRUTAL_ARROWS("Mithril 'brutal' arrows", Rarity.RARE, ItemID.ZOGRE_BRUTAL_MITHRIL, -1),
	MAPLE_SHORTBOWS("Maple shortbows", Rarity.RARE, ItemID.MAPLE_SHORTBOW, -1),
	MAPLE_VALE_TOTEMS("Maple vale totems", Rarity.RARE, ItemID.MAPLE_LOGS, -1),
	BARBED_TIPPED_BOLTS("Barbed-tipped bolts", Rarity.RARE, ItemID.BARBED_BOLT, -1),
	BROAD_ARROWS("Broad arrows", Rarity.RARE, ItemID.SLAYER_BROAD_ARROWS, -1),
	MITHRIL_DARTS("Mithril darts", Rarity.RARE, ItemID.MITHRIL_DART, -1),
	GREENMAN_STATUE("Greenman statue", Rarity.RARE, ItemID.GREENMAN_STATUE, -1),
	MITHRIL_BOLTS("Mithril bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL, -1),
	MITHRIL_MAPLE_CROSSBOWS("Mithril/maple crossbows", Rarity.RARE, ItemID.XBOWS_CROSSBOW_STOCK_MAPLE, -1),
	BROAD_BOLTS("Broad bolts", Rarity.RARE, ItemID.SLAYER_BROAD_BOLT, -1),
	MAPLE_LONGBOWS("Maple longbows", Rarity.RARE, ItemID.MAPLE_LONGBOW, -1),
	SAPPHIRE_TIPPED_MITHRIL_BOLTS("Sapphire-tipped mithril bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_SAPPHIRE, -1),
	MAPLE_SHIELD("Maple shield", Rarity.RARE, ItemID.MAPLE_SHIELD, -1),
	CAMPHOR_BLOWPIPE("Camphor blowpipe", Rarity.RARE, ItemID.CAMPHOR_BLOWPIPE, -1),
	EMERALD_TIPPED_MITHRIL_BOLTS("Emerald-tipped mithril bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_EMERALD, -1),
	MITHRIL_GRAPPLE_TIPPED_BOLTS("Mithril grapple-tipped bolts", Rarity.RARE, ItemID.XBOWS_GRAPPLE_TIP_BOLT_MITHRIL, -1),
	N75_ARROW_SHAFTS_YEW_LOGS("75 Arrow shafts (Yew logs)", Rarity.EPIC, ItemID.ARROW_SHAFT_5, -1),
	ADAMANT_ARROWS("Adamant arrows", Rarity.EPIC, ItemID.ADAMANT_ARROW, -1),
	HUNTER_S_SPEARS("Hunter's spears", Rarity.EPIC, ItemID.HG_HUNTER_SPEAR, -1),
	ADAMANTITE_BOLTS("Adamantite bolts", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE, -1),
	ADAMANTITE_MAHOGANY_CROSSBOWS("Adamantite/mahogany crossbows", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_STOCK_MAHOGANY, -1),
	ADAMANT_BRUTAL_ARROWS("Adamant 'brutal' arrows", Rarity.EPIC, ItemID.ZOGRE_BRUTAL_ADAMANT, -1),
	ADAMANT_JAVELINS("Adamant javelins", Rarity.EPIC, ItemID.ADAMANT_JAVELIN, -1),
	SUNLIGHT_BOLTS("Sunlight bolts", Rarity.EPIC, ItemID.SUNLIGHT_ANTELOPE_BOLT, -1),
	RUBY_TIPPED_ADAMANTITE_BOLTS("Ruby-tipped adamantite bolts", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_RUBY, -1),
	DIAMOND_TIPPED_ADAMANTITE_BOLTS("Diamond-tipped adamantite bolts", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_DIAMOND, -1),
	YEW_SHORTBOWS("Yew shortbows", Rarity.EPIC, ItemID.YEW_SHORTBOW, -1),
	YEW_VALE_TOTEMS("Yew vale totems", Rarity.EPIC, ItemID.YEW_LOGS, -1),
	ADAMANT_DARTS("Adamant darts", Rarity.EPIC, ItemID.ADAMANT_DART, -1),
	RUNITE_BOLTS("Runite bolts", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE, -1),
	RUNITE_YEW_CROSSBOWS("Runite/yew crossbows", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_STOCK_YEW, -1),
	YEW_LONGBOWS("Yew longbows", Rarity.EPIC, ItemID.YEW_LONGBOW, -1),
	DRAGONSTONE_TIPPED_RUNITE_BOLTS("Dragonstone-tipped runite bolts", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_DRAGONSTONE, -1),
	HEAVY_BALLISTAE("Heavy ballistae", Rarity.EPIC, ItemID.HEAVY_BALLISTA, -1),
	IRONWOOD_BLOWPIPE("Ironwood blowpipe", Rarity.EPIC, ItemID.IRONWOOD_BLOWPIPE, -1),
	MOONLIGHT_BOLTS("Moonlight bolts", Rarity.EPIC, ItemID.MOONLIGHT_ANTELOPE_BOLT, -1),
	YEW_SHIELD("Yew shield", Rarity.EPIC, ItemID.YEW_SHIELD, -1),
	ONYX_TIPPED_RUNITE_BOLTS("Onyx-tipped runite bolts", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_ONYX, -1),
	ATLATL_DARTS("Atlatl darts", Rarity.EPIC, ItemID.ATLATL_DART, -1),
	SCORCHING_BOWS("Scorching bows", Rarity.EPIC, ItemID.SCORCHING_BOW, -1),
	SUNLIGHT_HUNTERS_CROSSBOW("Sunlight hunters' crossbow", Rarity.EPIC, ItemID.HUNTING_CROSSBOW_SUNLIGHT, -1),
	N90_ARROW_SHAFTS_MAGIC_LOGS("90 Arrow shafts (Magic logs)", Rarity.EPIC, ItemID.ARROW_SHAFT_5, -1),
	RUNE_ARROWS("Rune arrows", Rarity.EPIC, ItemID.RUNE_ARROW, -1),
	AMETHYST_BROAD_BOLTS("Amethyst broad bolts", Rarity.EPIC, ItemID.SLAYER_BROAD_BOLT_AMETHYST, -1),
	RUNE_BRUTAL_ARROWS("Rune 'brutal' arrows", Rarity.EPIC, ItemID.ZOGRE_BRUTAL_RUNE, -1),
	RUNE_JAVELINS("Rune javelins", Rarity.EPIC, ItemID.RUNE_JAVELIN, -1),
	DRAGON_MAGIC_CROSSBOWS("Dragon/magic crossbows", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_STOCK_MAGIC, -1),
	TOXIC_BLOWPIPE("Toxic blowpipe", Rarity.EPIC, ItemID.TOXIC_BLOWPIPE_LOADED, -1),
	GREENMAN_CARVING("Greenman carving", Rarity.EPIC, ItemID.GREENMAN_WALL_DECORATION, -1),
	MAGIC_SHORTBOWS("Magic shortbows", Rarity.LEGENDARY, ItemID.MAGIC_SHORTBOW, -1),
	MAGIC_VALE_TOTEMS("Magic vale totems", Rarity.LEGENDARY, ItemID.MAGIC_LOGS, -1),
	RUNE_DARTS("Rune darts", Rarity.LEGENDARY, ItemID.RUNE_DART, -1),
	AMETHYST_ARROWS("Amethyst arrows", Rarity.LEGENDARY, ItemID.AMETHYST_ARROW, -1),
	AMETHYST_JAVELINS("Amethyst javelins", Rarity.LEGENDARY, ItemID.AMETHYST_JAVELIN, -1),
	DRAGON_BOLTS("Dragon bolts", Rarity.LEGENDARY, ItemID.DRAGON_BOLTS, -1),
	ROSEWOOD_BLOWPIPE("Rosewood blowpipe", Rarity.LEGENDARY, ItemID.ROSEWOOD_BLOWPIPE, -1),
	MAGIC_LONGBOWS("Magic longbows", Rarity.LEGENDARY, ItemID.MAGIC_LONGBOW, -1),
	WEBWEAVER_BOW("Webweaver bow", Rarity.LEGENDARY, ItemID.WILD_CAVE_WEBWEAVER_CHARGED, -1),
	MAGIC_SHIELD("Magic shield", Rarity.LEGENDARY, ItemID.MAGIC_SHIELD, -1),
	N105_ARROW_SHAFTS_REDWOOD_LOGS("105 Arrow shafts (Redwood logs)", Rarity.LEGENDARY, ItemID.ARROW_SHAFT_5, -1),
	AMETHYST_DARTS("Amethyst darts", Rarity.LEGENDARY, ItemID.AMETHYST_DART, -1),
	DRAGON_ARROWS("Dragon arrows", Rarity.LEGENDARY, ItemID.DRAGON_ARROW, -1),
	REDWOOD_HIKING_STAFF("Redwood hiking staff", Rarity.LEGENDARY, ItemID.REDWOOD_HIKING_STAFF, -1),
	REDWOOD_VALE_TOTEMS("Redwood vale totems", Rarity.LEGENDARY, ItemID.REDWOOD_LOGS, -1),
	DRAGON_JAVELINS("Dragon javelins", Rarity.LEGENDARY, ItemID.DRAGON_JAVELIN, -1),
	REDWOOD_SHIELD("Redwood shield", Rarity.LEGENDARY, ItemID.REDWOOD_SHIELD, -1),
	DRAGON_DARTS("Dragon darts", Rarity.LEGENDARY, ItemID.DRAGON_DART, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_FLETCHING, -1);

	private final Card card;

	FletchingCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.FLETCHING, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
