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

public enum SpellsLunarCards implements CardGroup
{
	LUNAR_HOME_TELEPORT("Lunar Home Teleport", Rarity.COMMON, -1, SpriteID.Magicon2.LUMBRIDGE_HOME_TELEPORT),
	MINIGAME_TELEPORT("Minigame Teleport", Rarity.COMMON, -1, 7479),
	BAKE_PIE("Bake Pie", Rarity.EPIC, -1, SpriteID.LunarMagicOn.BAKE_PIE),
	GEOMANCY("Geomancy", Rarity.EPIC, -1, SpriteID.LunarMagicOn.GEOMANCY),
	CURE_PLANT("Cure Plant", Rarity.EPIC, -1, SpriteID.LunarMagicOn.CURE_PLANT),
	MONSTER_EXAMINE("Monster Examine", Rarity.EPIC, -1, SpriteID.LunarMagicOn.MONSTER_EXAMINE),
	ASTRAL_CONTACT("Astral Contact", Rarity.EPIC, -1, SpriteID.LunarMagicOn.NPC_CONTACT),
	CURE_OTHER("Cure Other", Rarity.EPIC, -1, SpriteID.LunarMagicOn.CURE_OTHER),
	HUMIDIFY("Humidify", Rarity.EPIC, -1, SpriteID.LunarMagicOn.HUMIDIFY),
	MOONCLAN_TELEPORT("Moonclan Teleport", Rarity.EPIC, -1, SpriteID.LunarMagicOn.MOONCLAN_TELEPORT),
	TELE_GROUP_MOONCLAN("Tele Group Moonclan", Rarity.EPIC, -1, SpriteID.LunarMagicOn.TELE_GROUP_MOONCLAN),
	CURE_ME("Cure Me", Rarity.EPIC, -1, SpriteID.LunarMagicOn.CURE_ME),
	HUNTER_KIT("Hunter Kit", Rarity.EPIC, -1, SpriteID.LunarMagicOn.HUNTER_KIT),
	OURANIA_TELEPORT("Ourania Teleport", Rarity.EPIC, -1, SpriteID.LunarMagicOn.OURANIA_TELEPORT),
	WATERBIRTH_TELEPORT("Waterbirth Teleport", Rarity.EPIC, -1, SpriteID.LunarMagicOn.WATERBIRTH_TELEPORT),
	TELE_GROUP_WATERBIRTH("Tele Group Waterbirth", Rarity.EPIC, -1, SpriteID.LunarMagicOn.TELE_GROUP_WATERBIRTH),
	CURE_GROUP("Cure Group", Rarity.EPIC, -1, SpriteID.LunarMagicOn.CURE_GROUP),
	BARBARIAN_TELEPORT("Barbarian Teleport", Rarity.EPIC, -1, SpriteID.LunarMagicOn.BARBARIAN_TELEPORT),
	STAT_SPY("Stat Spy", Rarity.EPIC, -1, SpriteID.LunarMagicOn.STAT_SPY),
	SPIN_FLAX("Spin Flax", Rarity.EPIC, -1, SpriteID.LunarMagicOn.SPIN_FLAX),
	TELE_GROUP_BARBARIAN("Tele Group Barbarian", Rarity.EPIC, -1, SpriteID.LunarMagicOn.TELE_GROUP_BARBARIAN),
	SUPERGLASS_MAKE("Superglass Make", Rarity.EPIC, -1, SpriteID.LunarMagicOn.SUPERGLASS_MAKE),
	KHAZARD_TELEPORT("Khazard Teleport", Rarity.EPIC, -1, SpriteID.LunarMagicOn.KHAZARD_TELEPORT),
	TAN_LEATHER("Tan Leather", Rarity.EPIC, -1, SpriteID.LunarMagicOn.TAN_LEATHER),
	DREAM("Dream", Rarity.EPIC, -1, SpriteID.LunarMagicOn.DREAM),
	TELE_GROUP_KHAZARD("Tele Group Khazard", Rarity.EPIC, -1, SpriteID.LunarMagicOn.TELE_GROUP_KHAZARD),
	STRING_JEWELLERY("String Jewellery", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.STRING_JEWELLERY),
	STAT_RESTORE_POT_SHARE("Stat Restore Pot Share", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.STAT_RESTORE_POT_SHARE),
	MAGIC_IMBUE("Magic Imbue", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.MAGIC_IMBUE),
	FERTILE_SOIL("Fertile Soil", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.FERTILE_SOIL),
	BOOST_POTION_SHARE("Boost Potion Share", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.BOOST_POTION_SHARE),
	FISHING_GUILD_TELEPORT("Fishing Guild Teleport", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.FISHING_GUILD_TELEPORT),
	TELEPORT_TO_TARGET("Teleport to Target", Rarity.LEGENDARY, -1, SpriteID.Magicon2.TELEPORT_TO_BOUNTY_TARGET),
	PLANK_MAKE("Plank Make", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.PLANK_MAKE),
	TELE_GROUP_FISHING_GUILD("Tele Group Fishing Guild", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.TELE_GROUP_FISHING_GUILD),
	CATHERBY_TELEPORT("Catherby Teleport", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.CATHERBY_TELEPORT),
	TELE_GROUP_CATHERBY("Tele Group Catherby", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.TELE_GROUP_CATHERBY),
	ICE_PLATEAU_TELEPORT("Ice Plateau Teleport", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.ICE_PLATEAU_TELEPORT),
	RECHARGE_DRAGONSTONE("Recharge Dragonstone", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.RECHARGE_DRAGONSTONE),
	TELE_GROUP_ICE_PLATEAU("Tele Group Ice Plateau", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.TELE_GROUP_ICE_PLATEAU),
	ENERGY_TRANSFER("Energy Transfer", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.ENERGY_TRANSFER),
	HEAL_OTHER("Heal Other", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.HEAL_OTHER),
	VENGEANCE_OTHER("Vengeance Other", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.VENGEANCE_OTHER),
	VENGEANCE("Vengeance", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.VENGEANCE),
	HEAL_GROUP("Heal Group", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.HEAL_GROUP),
	SPELLBOOK_SWAP("Spellbook Swap", Rarity.LEGENDARY, -1, SpriteID.LunarMagicOn.SPELLBOOK_SWAP);

	private final Card card;

	SpellsLunarCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.SPELLS_LUNAR, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
