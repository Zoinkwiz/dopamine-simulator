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
import net.runelite.api.gameval.SpriteID;

public enum SkillsCards implements CardGroup
{
	ATTACK_BRONZE("Attack", "skills-attack-bronze", Rarity.COMMON, SpriteID.Staticons.ATTACK),
	DEFENCE_BRONZE("Defence", "skills-defence-bronze", Rarity.COMMON, SpriteID.Staticons.DEFENCE),
	STRENGTH_BRONZE("Strength", "skills-strength-bronze", Rarity.COMMON, SpriteID.Staticons.STRENGTH),
	HITPOINTS_BRONZE("Hitpoints", "skills-hitpoints-bronze", Rarity.COMMON, SpriteID.Staticons.HITPOINTS),
	RANGED_BRONZE("Ranged", "skills-ranged-bronze", Rarity.COMMON, SpriteID.Staticons.RANGED),
	PRAYER_BRONZE("Prayer", "skills-prayer-bronze", Rarity.COMMON, SpriteID.Staticons.PRAYER),
	MAGIC_BRONZE("Magic", "skills-magic-bronze", Rarity.COMMON, SpriteID.Staticons.MAGIC),
	COOKING_BRONZE("Cooking", "skills-cooking-bronze", Rarity.COMMON, SpriteID.Staticons.COOKING),
	WOODCUTTING_BRONZE("Woodcutting", "skills-woodcutting-bronze", Rarity.COMMON, SpriteID.Staticons.WOODCUTTING),
	FIREMAKING_BRONZE("Firemaking", "skills-firemaking-bronze", Rarity.COMMON, SpriteID.Staticons.FIREMAKING),
	FLETCHING_BRONZE("Fletching", "skills-fletching-bronze", Rarity.COMMON, SpriteID.Staticons.FLETCHING),
	FISHING_BRONZE("Fishing", "skills-fishing-bronze", Rarity.COMMON, SpriteID.Staticons.FISHING),
	CRAFTING_BRONZE("Crafting", "skills-crafting-bronze", Rarity.COMMON, SpriteID.Staticons.CRAFTING),
	SMITHING_BRONZE("Smithing", "skills-smithing-bronze", Rarity.COMMON, SpriteID.Staticons.SMITHING),
	MINING_BRONZE("Mining", "skills-mining-bronze", Rarity.COMMON, SpriteID.Staticons.MINING),
	THIEVING_BRONZE("Thieving", "skills-thieving-bronze", Rarity.COMMON, SpriteID.Staticons.THIEVING),
	HERBLORE_BRONZE("Herblore", "skills-herblore-bronze", Rarity.COMMON, SpriteID.Staticons.HERBLORE),
	SLAYER_BRONZE("Slayer", "skills-slayer-bronze", Rarity.COMMON, SpriteID.Staticons2.SLAYER),
	FARMING_BRONZE("Farming", "skills-farming-bronze", Rarity.COMMON, SpriteID.Staticons2.FARMING),
	HUNTER_BRONZE("Hunter", "skills-hunter-bronze", Rarity.COMMON, SpriteID.Staticons2.HUNTER),
	SAILING_BRONZE("Sailing", "skills-sailing-bronze", Rarity.COMMON, SpriteID.Staticons2.SAILING),
	AGILITY_BRONZE("Agility", "skills-agility-bronze", Rarity.COMMON, SpriteID.Staticons.AGILITY),
	CONSTRUCTION_BRONZE("Construction", "skills-construction-bronze", Rarity.COMMON, SpriteID.Staticons2.CONSTRUCTION),
	RUNECRAFT_BRONZE("Runecraft", "skills-runecraft-bronze", Rarity.COMMON, SpriteID.Staticons2.RUNECRAFT),
	ATTACK_STEEL("Attack", "skills-attack-steel", Rarity.UNCOMMON, SpriteID.Staticons.ATTACK),
	DEFENCE_STEEL("Defence", "skills-defence-steel", Rarity.UNCOMMON, SpriteID.Staticons.DEFENCE),
	STRENGTH_STEEL("Strength", "skills-strength-steel", Rarity.UNCOMMON, SpriteID.Staticons.STRENGTH),
	HITPOINTS_STEEL("Hitpoints", "skills-hitpoints-steel", Rarity.UNCOMMON, SpriteID.Staticons.HITPOINTS),
	RANGED_STEEL("Ranged", "skills-ranged-steel", Rarity.UNCOMMON, SpriteID.Staticons.RANGED),
	PRAYER_STEEL("Prayer", "skills-prayer-steel", Rarity.UNCOMMON, SpriteID.Staticons.PRAYER),
	MAGIC_STEEL("Magic", "skills-magic-steel", Rarity.UNCOMMON, SpriteID.Staticons.MAGIC),
	COOKING_STEEL("Cooking", "skills-cooking-steel", Rarity.UNCOMMON, SpriteID.Staticons.COOKING),
	WOODCUTTING_STEEL("Woodcutting", "skills-woodcutting-steel", Rarity.UNCOMMON, SpriteID.Staticons.WOODCUTTING),
	FIREMAKING_STEEL("Firemaking", "skills-firemaking-steel", Rarity.UNCOMMON, SpriteID.Staticons.FIREMAKING),
	FLETCHING_STEEL("Fletching", "skills-fletching-steel", Rarity.UNCOMMON, SpriteID.Staticons.FLETCHING),
	FISHING_STEEL("Fishing", "skills-fishing-steel", Rarity.UNCOMMON, SpriteID.Staticons.FISHING),
	CRAFTING_STEEL("Crafting", "skills-crafting-steel", Rarity.UNCOMMON, SpriteID.Staticons.CRAFTING),
	SMITHING_STEEL("Smithing", "skills-smithing-steel", Rarity.UNCOMMON, SpriteID.Staticons.SMITHING),
	MINING_STEEL("Mining", "skills-mining-steel", Rarity.UNCOMMON, SpriteID.Staticons.MINING),
	THIEVING_STEEL("Thieving", "skills-thieving-steel", Rarity.UNCOMMON, SpriteID.Staticons.THIEVING),
	HERBLORE_STEEL("Herblore", "skills-herblore-steel", Rarity.UNCOMMON, SpriteID.Staticons.HERBLORE),
	SLAYER_STEEL("Slayer", "skills-slayer-steel", Rarity.UNCOMMON, SpriteID.Staticons2.SLAYER),
	FARMING_STEEL("Farming", "skills-farming-steel", Rarity.UNCOMMON, SpriteID.Staticons2.FARMING),
	HUNTER_STEEL("Hunter", "skills-hunter-steel", Rarity.UNCOMMON, SpriteID.Staticons2.HUNTER),
	SAILING_STEEL("Sailing", "skills-sailing-steel", Rarity.UNCOMMON, SpriteID.Staticons2.SAILING),
	AGILITY_STEEL("Agility", "skills-agility-steel", Rarity.UNCOMMON, SpriteID.Staticons.AGILITY),
	CONSTRUCTION_STEEL("Construction", "skills-construction-steel", Rarity.UNCOMMON, SpriteID.Staticons2.CONSTRUCTION),
	RUNECRAFT_STEEL("Runecraft", "skills-runecraft-steel", Rarity.UNCOMMON, SpriteID.Staticons2.RUNECRAFT),
	ATTACK_MITHRIL("Attack", "skills-attack-mithril", Rarity.RARE, SpriteID.Staticons.ATTACK),
	DEFENCE_MITHRIL("Defence", "skills-defence-mithril", Rarity.RARE, SpriteID.Staticons.DEFENCE),
	STRENGTH_MITHRIL("Strength", "skills-strength-mithril", Rarity.RARE, SpriteID.Staticons.STRENGTH),
	HITPOINTS_MITHRIL("Hitpoints", "skills-hitpoints-mithril", Rarity.RARE, SpriteID.Staticons.HITPOINTS),
	RANGED_MITHRIL("Ranged", "skills-ranged-mithril", Rarity.RARE, SpriteID.Staticons.RANGED),
	PRAYER_MITHRIL("Prayer", "skills-prayer-mithril", Rarity.RARE, SpriteID.Staticons.PRAYER),
	MAGIC_MITHRIL("Magic", "skills-magic-mithril", Rarity.RARE, SpriteID.Staticons.MAGIC),
	COOKING_MITHRIL("Cooking", "skills-cooking-mithril", Rarity.RARE, SpriteID.Staticons.COOKING),
	WOODCUTTING_MITHRIL("Woodcutting", "skills-woodcutting-mithril", Rarity.RARE, SpriteID.Staticons.WOODCUTTING),
	FIREMAKING_MITHRIL("Firemaking", "skills-firemaking-mithril", Rarity.RARE, SpriteID.Staticons.FIREMAKING),
	FLETCHING_MITHRIL("Fletching", "skills-fletching-mithril", Rarity.RARE, SpriteID.Staticons.FLETCHING),
	FISHING_MITHRIL("Fishing", "skills-fishing-mithril", Rarity.RARE, SpriteID.Staticons.FISHING),
	CRAFTING_MITHRIL("Crafting", "skills-crafting-mithril", Rarity.RARE, SpriteID.Staticons.CRAFTING),
	SMITHING_MITHRIL("Smithing", "skills-smithing-mithril", Rarity.RARE, SpriteID.Staticons.SMITHING),
	MINING_MITHRIL("Mining", "skills-mining-mithril", Rarity.RARE, SpriteID.Staticons.MINING),
	THIEVING_MITHRIL("Thieving", "skills-thieving-mithril", Rarity.RARE, SpriteID.Staticons.THIEVING),
	HERBLORE_MITHRIL("Herblore", "skills-herblore-mithril", Rarity.RARE, SpriteID.Staticons.HERBLORE),
	SLAYER_MITHRIL("Slayer", "skills-slayer-mithril", Rarity.RARE, SpriteID.Staticons2.SLAYER),
	FARMING_MITHRIL("Farming", "skills-farming-mithril", Rarity.RARE, SpriteID.Staticons2.FARMING),
	HUNTER_MITHRIL("Hunter", "skills-hunter-mithril", Rarity.RARE, SpriteID.Staticons2.HUNTER),
	SAILING_MITHRIL("Sailing", "skills-sailing-mithril", Rarity.RARE, SpriteID.Staticons2.SAILING),
	AGILITY_MITHRIL("Agility", "skills-agility-mithril", Rarity.RARE, SpriteID.Staticons.AGILITY),
	CONSTRUCTION_MITHRIL("Construction", "skills-construction-mithril", Rarity.RARE, SpriteID.Staticons2.CONSTRUCTION),
	RUNECRAFT_MITHRIL("Runecraft", "skills-runecraft-mithril", Rarity.RARE, SpriteID.Staticons2.RUNECRAFT),
	ATTACK_ADAMANT("Attack", "skills-attack-adamant", Rarity.EPIC, SpriteID.Staticons.ATTACK),
	DEFENCE_ADAMANT("Defence", "skills-defence-adamant", Rarity.EPIC, SpriteID.Staticons.DEFENCE),
	STRENGTH_ADAMANT("Strength", "skills-strength-adamant", Rarity.EPIC, SpriteID.Staticons.STRENGTH),
	HITPOINTS_ADAMANT("Hitpoints", "skills-hitpoints-adamant", Rarity.EPIC, SpriteID.Staticons.HITPOINTS),
	RANGED_ADAMANT("Ranged", "skills-ranged-adamant", Rarity.EPIC, SpriteID.Staticons.RANGED),
	PRAYER_ADAMANT("Prayer", "skills-prayer-adamant", Rarity.EPIC, SpriteID.Staticons.PRAYER),
	MAGIC_ADAMANT("Magic", "skills-magic-adamant", Rarity.EPIC, SpriteID.Staticons.MAGIC),
	COOKING_ADAMANT("Cooking", "skills-cooking-adamant", Rarity.EPIC, SpriteID.Staticons.COOKING),
	WOODCUTTING_ADAMANT("Woodcutting", "skills-woodcutting-adamant", Rarity.EPIC, SpriteID.Staticons.WOODCUTTING),
	FIREMAKING_ADAMANT("Firemaking", "skills-firemaking-adamant", Rarity.EPIC, SpriteID.Staticons.FIREMAKING),
	FLETCHING_ADAMANT("Fletching", "skills-fletching-adamant", Rarity.EPIC, SpriteID.Staticons.FLETCHING),
	FISHING_ADAMANT("Fishing", "skills-fishing-adamant", Rarity.EPIC, SpriteID.Staticons.FISHING),
	CRAFTING_ADAMANT("Crafting", "skills-crafting-adamant", Rarity.EPIC, SpriteID.Staticons.CRAFTING),
	SMITHING_ADAMANT("Smithing", "skills-smithing-adamant", Rarity.EPIC, SpriteID.Staticons.SMITHING),
	MINING_ADAMANT("Mining", "skills-mining-adamant", Rarity.EPIC, SpriteID.Staticons.MINING),
	THIEVING_ADAMANT("Thieving", "skills-thieving-adamant", Rarity.EPIC, SpriteID.Staticons.THIEVING),
	HERBLORE_ADAMANT("Herblore", "skills-herblore-adamant", Rarity.EPIC, SpriteID.Staticons.HERBLORE),
	SLAYER_ADAMANT("Slayer", "skills-slayer-adamant", Rarity.EPIC, SpriteID.Staticons2.SLAYER),
	FARMING_ADAMANT("Farming", "skills-farming-adamant", Rarity.EPIC, SpriteID.Staticons2.FARMING),
	HUNTER_ADAMANT("Hunter", "skills-hunter-adamant", Rarity.EPIC, SpriteID.Staticons2.HUNTER),
	SAILING_ADAMANT("Sailing", "skills-sailing-adamant", Rarity.EPIC, SpriteID.Staticons2.SAILING),
	AGILITY_ADAMANT("Agility", "skills-agility-adamant", Rarity.EPIC, SpriteID.Staticons.AGILITY),
	CONSTRUCTION_ADAMANT("Construction", "skills-construction-adamant", Rarity.EPIC, SpriteID.Staticons2.CONSTRUCTION),
	RUNECRAFT_ADAMANT("Runecraft", "skills-runecraft-adamant", Rarity.EPIC, SpriteID.Staticons2.RUNECRAFT),
	ATTACK_RUNE("Attack", "skills-attack-rune", Rarity.LEGENDARY, SpriteID.Staticons.ATTACK),
	DEFENCE_RUNE("Defence", "skills-defence-rune", Rarity.LEGENDARY, SpriteID.Staticons.DEFENCE),
	STRENGTH_RUNE("Strength", "skills-strength-rune", Rarity.LEGENDARY, SpriteID.Staticons.STRENGTH),
	HITPOINTS_RUNE("Hitpoints", "skills-hitpoints-rune", Rarity.LEGENDARY, SpriteID.Staticons.HITPOINTS),
	RANGED_RUNE("Ranged", "skills-ranged-rune", Rarity.LEGENDARY, SpriteID.Staticons.RANGED),
	PRAYER_RUNE("Prayer", "skills-prayer-rune", Rarity.LEGENDARY, SpriteID.Staticons.PRAYER),
	MAGIC_RUNE("Magic", "skills-magic-rune", Rarity.LEGENDARY, SpriteID.Staticons.MAGIC),
	COOKING_RUNE("Cooking", "skills-cooking-rune", Rarity.LEGENDARY, SpriteID.Staticons.COOKING),
	WOODCUTTING_RUNE("Woodcutting", "skills-woodcutting-rune", Rarity.LEGENDARY, SpriteID.Staticons.WOODCUTTING),
	FIREMAKING_RUNE("Firemaking", "skills-firemaking-rune", Rarity.LEGENDARY, SpriteID.Staticons.FIREMAKING),
	FLETCHING_RUNE("Fletching", "skills-fletching-rune", Rarity.LEGENDARY, SpriteID.Staticons.FLETCHING),
	FISHING_RUNE("Fishing", "skills-fishing-rune", Rarity.LEGENDARY, SpriteID.Staticons.FISHING),
	CRAFTING_RUNE("Crafting", "skills-crafting-rune", Rarity.LEGENDARY, SpriteID.Staticons.CRAFTING),
	SMITHING_RUNE("Smithing", "skills-smithing-rune", Rarity.LEGENDARY, SpriteID.Staticons.SMITHING),
	MINING_RUNE("Mining", "skills-mining-rune", Rarity.LEGENDARY, SpriteID.Staticons.MINING),
	THIEVING_RUNE("Thieving", "skills-thieving-rune", Rarity.LEGENDARY, SpriteID.Staticons.THIEVING),
	HERBLORE_RUNE("Herblore", "skills-herblore-rune", Rarity.LEGENDARY, SpriteID.Staticons.HERBLORE),
	SLAYER_RUNE("Slayer", "skills-slayer-rune", Rarity.LEGENDARY, SpriteID.Staticons2.SLAYER),
	FARMING_RUNE("Farming", "skills-farming-rune", Rarity.LEGENDARY, SpriteID.Staticons2.FARMING),
	HUNTER_RUNE("Hunter", "skills-hunter-rune", Rarity.LEGENDARY, SpriteID.Staticons2.HUNTER),
	SAILING_RUNE("Sailing", "skills-sailing-rune", Rarity.LEGENDARY, SpriteID.Staticons2.SAILING),
	AGILITY_RUNE("Agility", "skills-agility-rune", Rarity.LEGENDARY, SpriteID.Staticons.AGILITY),
	CONSTRUCTION_RUNE("Construction", "skills-construction-rune", Rarity.LEGENDARY, SpriteID.Staticons2.CONSTRUCTION),
	RUNECRAFT_RUNE("Runecraft", "skills-runecraft-rune", Rarity.LEGENDARY, SpriteID.Staticons2.RUNECRAFT);

	// Five per skill, all named for the skill; only the frame and the id differ.
	private final Card card;

	SkillsCards(String name, String id, Rarity rarity, int spriteId)
	{
		this.card = CardGroup.withId(CardSet.SKILLS, id, name, rarity, -1, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
