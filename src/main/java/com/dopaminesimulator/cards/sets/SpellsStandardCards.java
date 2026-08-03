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

public enum SpellsStandardCards implements CardGroup
{
	LUMBRIDGE_HOME_TELEPORT("Lumbridge Home Teleport", Rarity.COMMON, -1, SpriteID.Magicon2.LUMBRIDGE_HOME_TELEPORT),
	MINIGAME_TELEPORT("Minigame Teleport", Rarity.COMMON, -1, 7479),
	WIND_STRIKE("Wind Strike", Rarity.COMMON, -1, SpriteID.Magicon.WIND_STRIKE),
	CONFUSE("Confuse", Rarity.COMMON, -1, SpriteID.Magicon.CONFUSE),
	ENCHANT_CROSSBOW_BOLT_OPAL("Enchant Crossbow Bolt (Opal)", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_BRONZE_TIPPED_OPAL_ENCHANTED, -1),
	WATER_STRIKE("Water Strike", Rarity.COMMON, -1, SpriteID.Magicon.WATER_STRIKE),
	ENCHANT_CROSSBOW_BOLT_SAPPHIRE("Enchant Crossbow Bolt (Sapphire)", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_SAPPHIRE_ENCHANTED, -1),
	LVL_1_ENCHANT("Lvl-1 Enchant", Rarity.COMMON, -1, SpriteID.Magicon.LVL_1_ENCHANT),
	EARTH_STRIKE("Earth Strike", Rarity.COMMON, -1, SpriteID.Magicon.EARTH_STRIKE),
	WEAKEN("Weaken", Rarity.COMMON, -1, SpriteID.Magicon.WEAKEN),
	FIRE_STRIKE("Fire Strike", Rarity.COMMON, -1, SpriteID.Magicon.FIRE_STRIKE),
	ENCHANT_CROSSBOW_BOLT_JADE("Enchant Crossbow Bolt (Jade)", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_BLURITE_TIPPED_JADE_ENCHANTED, -1),
	BONES_TO_BANANAS("Bones to Bananas", Rarity.COMMON, -1, SpriteID.Magicon.BONES_TO_BANANAS),
	WIND_BOLT("Wind Bolt", Rarity.COMMON, -1, SpriteID.Magicon.WIND_BOLT),
	CURSE("Curse", Rarity.COMMON, -1, SpriteID.Magicon.CURSE),
	BIND("Bind", Rarity.UNCOMMON, -1, SpriteID.Magicon2.BIND),
	LOW_LEVEL_ALCHEMY("Low Level Alchemy", Rarity.UNCOMMON, -1, SpriteID.Magicon.LOW_LEVEL_ALCHEMY),
	WATER_BOLT("Water Bolt", Rarity.UNCOMMON, -1, SpriteID.Magicon.WATER_BOLT),
	ENCHANT_CROSSBOW_BOLT_PEARL("Enchant Crossbow Bolt (Pearl)", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_IRON_TIPPED_PEARL_ENCHANTED, -1),
	VARROCK_TELEPORT("Varrock Teleport", Rarity.UNCOMMON, -1, SpriteID.Magicon.VARROCK_TELEPORT),
	ENCHANT_CROSSBOW_BOLT_EMERALD("Enchant Crossbow Bolt (Emerald)", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_EMERALD_ENCHANTED, -1),
	LVL_2_ENCHANT("Lvl-2 Enchant", Rarity.UNCOMMON, -1, SpriteID.Magicon.LVL_2_ENCHANT),
	EARTH_BOLT("Earth Bolt", Rarity.UNCOMMON, -1, SpriteID.Magicon.EARTH_BOLT),
	ENCHANT_CROSSBOW_BOLT_RED_TOPAZ("Enchant Crossbow Bolt (Red Topaz)", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_STEEL_TIPPED_REDTOPAZ_ENCHANTED, -1),
	LUMBRIDGE_TELEPORT("Lumbridge Teleport", Rarity.UNCOMMON, -1, SpriteID.Magicon.LUMBRIDGE_TELEPORT),
	TELEKINETIC_GRAB("Telekinetic Grab", Rarity.UNCOMMON, -1, SpriteID.Magicon.TELEKINETIC_GRAB),
	FIRE_BOLT("Fire Bolt", Rarity.UNCOMMON, -1, SpriteID.Magicon.FIRE_BOLT),
	FALADOR_TELEPORT("Falador Teleport", Rarity.UNCOMMON, -1, SpriteID.Magicon.FALADOR_TELEPORT),
	CRUMBLE_UNDEAD("Crumble Undead", Rarity.UNCOMMON, -1, SpriteID.Magicon.CRUMBLE_UNDEAD),
	TELEPORT_TO_HOUSE("Teleport to House", Rarity.RARE, -1, SpriteID.Magicon2.TELEPORT_TO_HOUSE),
	WIND_BLAST("Wind Blast", Rarity.RARE, -1, SpriteID.Magicon.WIND_BLAST),
	MONSTER_INSPECT("Monster Inspect", Rarity.RARE, -1, 368),
	SUPERHEAT_ITEM("Superheat Item", Rarity.RARE, -1, SpriteID.Magicon.SUPERHEAT_ITEM),
	CAMELOT_TELEPORT("Camelot Teleport", Rarity.RARE, -1, SpriteID.Magicon.CAMELOT_TELEPORT),
	WATER_BLAST("Water Blast", Rarity.RARE, -1, SpriteID.Magicon.WATER_BLAST),
	KOUREND_CASTLE_TELEPORT("Kourend Castle Teleport", Rarity.RARE, -1, SpriteID.Magicon2.TELEPORT_TO_KOUREND),
	ENCHANT_CROSSBOW_BOLT_RUBY("Enchant Crossbow Bolt (Ruby)", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_RUBY_ENCHANTED, -1),
	LVL_3_ENCHANT("Lvl-3 Enchant", Rarity.RARE, -1, SpriteID.Magicon.LVL_3_ENCHANT),
	IBAN_BLAST("Iban Blast", Rarity.RARE, -1, SpriteID.Magicon.IBAN_BLAST),
	MAGIC_DART("Magic Dart", Rarity.RARE, -1, SpriteID.Magicon2.MAGIC_DART),
	SNARE("Snare", Rarity.RARE, -1, SpriteID.Magicon2.SNARE),
	ARDOUGNE_TELEPORT("Ardougne Teleport", Rarity.RARE, -1, SpriteID.Magicon.ARDOUGNE_TELEPORT),
	EARTH_BLAST("Earth Blast", Rarity.RARE, -1, SpriteID.Magicon.EARTH_BLAST),
	CIVITAS_ILLA_FORTIS_TELEPORT("Civitas illa Fortis Teleport", Rarity.RARE, -1, SpriteID.Magicon2.CIVITAS_ILLA_FORTIS_TELEPORT),
	HIGH_LEVEL_ALCHEMY("High Level Alchemy", Rarity.RARE, -1, SpriteID.Magicon.HIGH_LEVEL_ALCHEMY),
	CHARGE_WATER_ORB("Charge Water Orb", Rarity.RARE, -1, SpriteID.Magicon.CHARGE_WATER_ORB),
	SUMMON_BOAT("Summon Boat", Rarity.RARE, -1, 6948),
	ENCHANT_CROSSBOW_BOLT_DIAMOND("Enchant Crossbow Bolt (Diamond)", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_DIAMOND_ENCHANTED, -1),
	LVL_4_ENCHANT("Lvl-4 Enchant", Rarity.RARE, -1, SpriteID.Magicon.LVL_4_ENCHANT),
	WATCHTOWER_TELEPORT("Watchtower Teleport", Rarity.RARE, -1, SpriteID.Magicon.WATCHTOWER_TELEPORT),
	FIRE_BLAST("Fire Blast", Rarity.RARE, -1, SpriteID.Magicon.FIRE_BLAST),
	BONES_TO_PEACHES("Bones to Peaches", Rarity.EPIC, -1, SpriteID.Magicon2.BONES_TO_PEACHES),
	CHARGE_EARTH_ORB("Charge Earth Orb", Rarity.EPIC, -1, SpriteID.Magicon.CHARGE_EARTH_ORB),
	CLAWS_OF_GUTHIX("Claws of Guthix", Rarity.EPIC, -1, SpriteID.Magicon.CLAWS_OF_GUTHIX),
	FLAMES_OF_ZAMORAK("Flames of Zamorak", Rarity.EPIC, -1, SpriteID.Magicon.FLAMES_OF_ZAMORAK),
	SARADOMIN_STRIKE("Saradomin Strike", Rarity.EPIC, -1, SpriteID.Magicon.SARADOMIN_STRIKE),
	TROLLHEIM_TELEPORT("Trollheim Teleport", Rarity.EPIC, -1, SpriteID.Magicon2.TROLLHEIM_TELEPORT),
	WIND_WAVE("Wind Wave", Rarity.EPIC, -1, SpriteID.Magicon.WIND_WAVE),
	CHARGE_FIRE_ORB("Charge Fire Orb", Rarity.EPIC, -1, SpriteID.Magicon.CHARGE_FIRE_ORB),
	APE_ATOLL_TELEPORT("Ape Atoll Teleport", Rarity.EPIC, -1, SpriteID.Magicon2.TELEPORT_TO_APE_ATOLL),
	WATER_WAVE("Water Wave", Rarity.EPIC, -1, SpriteID.Magicon.WATER_WAVE),
	CHARGE_AIR_ORB("Charge Air Orb", Rarity.EPIC, -1, SpriteID.Magicon.CHARGE_AIR_ORB),
	VULNERABILITY("Vulnerability", Rarity.EPIC, -1, SpriteID.Magicon.VULNERABILITY),
	TELEPORT_TO_BOAT("Teleport to Boat", Rarity.EPIC, -1, 6947),
	ENCHANT_CROSSBOW_BOLT_DRAGONSTONE("Enchant Crossbow Bolt (Dragonstone)", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_DRAGONSTONE_ENCHANTED, -1),
	LVL_5_ENCHANT("Lvl-5 Enchant", Rarity.EPIC, -1, SpriteID.Magicon.LVL_5_ENCHANT),
	EARTH_WAVE("Earth Wave", Rarity.EPIC, -1, SpriteID.Magicon.EARTH_WAVE),
	ENFEEBLE("Enfeeble", Rarity.EPIC, -1, SpriteID.Magicon.ENFEEBLE),
	TELEOTHER_LUMBRIDGE("Teleother Lumbridge", Rarity.EPIC, -1, SpriteID.Magicon2.TELEOTHER_LUMBRIDGE),
	FIRE_WAVE("Fire Wave", Rarity.EPIC, -1, SpriteID.Magicon.FIRE_WAVE),
	ENTANGLE("Entangle", Rarity.EPIC, -1, SpriteID.Magicon2.ENTANGLE),
	CHARGE("Charge", Rarity.LEGENDARY, -1, SpriteID.Magicon2.CHARGE),
	STUN("Stun", Rarity.LEGENDARY, -1, SpriteID.Magicon.STUN),
	WIND_SURGE("Wind Surge", Rarity.LEGENDARY, -1, SpriteID.Magicon2.WIND_SURGE),
	TELEOTHER_FALADOR("Teleother Falador", Rarity.LEGENDARY, -1, SpriteID.Magicon2.TELEOTHER_FALADOR),
	TELE_BLOCK("Tele Block", Rarity.LEGENDARY, -1, SpriteID.Magicon2.TELE_BLOCK),
	TELEPORT_TO_TARGET("Teleport to Target", Rarity.LEGENDARY, -1, SpriteID.Magicon2.TELEPORT_TO_BOUNTY_TARGET),
	WATER_SURGE("Water Surge", Rarity.LEGENDARY, -1, SpriteID.Magicon2.WATER_SURGE),
	ENCHANT_CROSSBOW_BOLT_ONYX("Enchant Crossbow Bolt (Onyx)", Rarity.LEGENDARY, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_ONYX_ENCHANTED, -1),
	LVL_6_ENCHANT("Lvl-6 Enchant", Rarity.LEGENDARY, -1, SpriteID.Magicon2.LVL_6_ENCHANT),
	EARTH_SURGE("Earth Surge", Rarity.LEGENDARY, -1, SpriteID.Magicon2.EARTH_SURGE),
	TELEOTHER_CAMELOT("Teleother Camelot", Rarity.LEGENDARY, -1, SpriteID.Magicon2.TELEOTHER_CAMELOT),
	LVL_7_ENCHANT("Lvl-7 Enchant", Rarity.LEGENDARY, -1, SpriteID.Magicon2.LVL_7_ENCHANT),
	FIRE_SURGE("Fire Surge", Rarity.LEGENDARY, -1, SpriteID.Magicon2.FIRE_SURGE);

	private final Card card;

	SpellsStandardCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.SPELLS_STANDARD, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
