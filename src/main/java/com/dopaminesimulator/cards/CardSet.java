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
import net.runelite.api.gameval.SpriteID;

@Getter
public enum CardSet
{
	QUESTS("Quests", "Every quest in the game"),
	SKILLS("Skills", "Skill unlocks"),
	BOSSES("Bosses", "Including raids"),
	BOSS_DROPS("Boss Drops", "Collection log drops from every boss"),
	RAIDS_DROPS("Raids Drops", "Collection log drops from every raid"),
	CLUE_DROPS("Clue Rewards", "Collection log rewards from every clue tier."),
	MINIGAME_REWARDS("Minigames Rewards", "Collection log rewards from every minigame."),
	OTHER_COLLECTIONLOG("Other Collection Log", "Collection log rewards from the Other section."),
	ITEMS("Items", "Gear and common items"),
	MINIGAMES("Minigames", "Minigames and their rewards"),
	SLAYER("Slayer", "Task monsters and slayer unlocks"),
	DIARIES("Diaries", "Achievement diaries by area"),

	AGILITY("Agility", "Courses and shortcuts"),
	ATTACK("Attack", "Weapons"),
	CONSTRUCTION("Construction", "Rooms and furniture"),
	COOKING("Cooking", "Food and drink"),
	CRAFTING("Crafting", "Jewellery, leather, glass and pottery"),
	DEFENCE("Defence", "Armour"),
	FARMING("Farming", "Seeds, crops and trees"),
	FIREMAKING("Firemaking", "Logs and fires"),
	FISHING("Fishing", "Fish and equipment"),
	FLETCHING("Fletching", "Bows, bolts and darts"),
	HERBLORE("Herblore", "Herbs and potions"),
	HUNTER("Hunter", "Creatures and traps"),
	MAGIC("Magic", "Spells and staves"),
	MINING("Mining", "Ores, rocks and pickaxes"),
	PRAYER("Prayer", "Prayers, bones and gear"),
	RANGED("Ranged", "Ranged weapons and armour"),
	RUNECRAFT("Runecraft", "Runes, altars and pouches"),
	SAILING("Sailing", "Ships, ports and crew"),
	SMITHING("Smithing", "Bars and smithed items"),
	STRENGTH("Strength", "Strength unlocks"),
	THIEVING("Thieving", "Stalls, chests and pockets"),
	WOODCUTTING("Woodcutting", "Trees and axes"),
	AREAS("Areas", "Regions and places"),

	SPELLS_STANDARD("Standard Spells", "The standard spellbook"),
	SPELLS_ANCIENT("Ancient Magicks", "The ancient spellbook"),
	SPELLS_LUNAR("Lunar Spells", "The lunar spellbook"),
	SPELLS_ARCEUUS("Arceuus Spells", "The Arceuus spellbook");

	private final String displayName;
	private final String description;

	CardSet(String displayName, String description)
	{
		this.displayName = displayName;
		this.description = description;
	}

	public boolean isUnlockSet()
	{
		return ordinal() >= AGILITY.ordinal();
	}

	public int badgeSpriteId()
	{
		switch (this)
		{

			case QUESTS: return SpriteID.SideIcons.QUEST;
			case SKILLS: return SpriteID.SideIcons.STATS;
			case BOSSES: return SpriteID.PvpwIcons.PLAYER_KILLER_SKULL;
			case BOSS_DROPS: return SpriteID.IconActivities25x25.COLLECTIONS_LOGGED;
			case RAIDS_DROPS: return SpriteID.IconBoss25x25.CHAMBERS_OF_XERIC;
			case CLUE_DROPS: return SpriteID.IconActivities25x25.CLUE_SCROLL_ALL;
			case MINIGAME_REWARDS: return SpriteID.IconActivities25x25.RIFTS_CLOSED;
			case ITEMS: return SpriteID.SideIcons.INVENTORY;
			case MINIGAMES: return SpriteID.SideIcons.MINIGAMES;
			case DIARIES: return SpriteID.SideIcons.ACHIEVEMENT_DIARIES;
			case AREAS: return SpriteID.Mapfunction.DUNGEON;
			case SPELLS_STANDARD: return SpriteID.SideIcons.MAGIC;
			case SPELLS_ANCIENT: return SpriteID.SideIcons.SPELLBOOK_ANCIENT_MAGICKS;
			case SPELLS_LUNAR: return SpriteID.SideIcons.SPELLBOOK_LUNAR;
			case SPELLS_ARCEUUS: return SpriteID.SideIcons.SPELLBOOK_ARCEUUS;
			case ATTACK: return SpriteID.Staticons.ATTACK;
			case STRENGTH: return SpriteID.Staticons.STRENGTH;
			case DEFENCE: return SpriteID.Staticons.DEFENCE;
			case RANGED: return SpriteID.Staticons.RANGED;
			case PRAYER: return SpriteID.Staticons.PRAYER;
			case MAGIC: return SpriteID.Staticons.MAGIC;
			case AGILITY: return SpriteID.Staticons.AGILITY;
			case HERBLORE: return SpriteID.Staticons.HERBLORE;
			case THIEVING: return SpriteID.Staticons.THIEVING;
			case CRAFTING: return SpriteID.Staticons.CRAFTING;
			case FLETCHING: return SpriteID.Staticons.FLETCHING;
			case MINING: return SpriteID.Staticons.MINING;
			case SMITHING: return SpriteID.Staticons.SMITHING;
			case FISHING: return SpriteID.Staticons.FISHING;
			case COOKING: return SpriteID.Staticons.COOKING;
			case FIREMAKING: return SpriteID.Staticons.FIREMAKING;
			case WOODCUTTING: return SpriteID.Staticons.WOODCUTTING;
			case RUNECRAFT: return SpriteID.Staticons2.RUNECRAFT;
			case SLAYER: return SpriteID.Staticons2.SLAYER;
			case HUNTER: return SpriteID.Staticons2.HUNTER;
			case FARMING: return SpriteID.Staticons2.FARMING;
			case CONSTRUCTION: return SpriteID.Staticons2.CONSTRUCTION;
			case SAILING: return SpriteID.Staticons2.SAILING;
			default: return -1;
		}
	}
}
