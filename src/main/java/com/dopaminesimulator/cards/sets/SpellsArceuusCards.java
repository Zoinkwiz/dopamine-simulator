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

public enum SpellsArceuusCards implements CardGroup
{
	ARCEUUS_HOME_TELEPORT("Arceuus Home Teleport", Rarity.COMMON, -1, 1251),
	MINIGAME_TELEPORT("Minigame Teleport", Rarity.COMMON, -1, 7479),
	ARCEUUS_LIBRARY_TELEPORT("Arceuus Library Teleport", Rarity.COMMON, -1, SpriteID.MagicNecroOn.ARCEUUS_LIBRARY_TELEPORT),
	BASIC_REANIMATION("Basic Reanimation", Rarity.COMMON, -1, SpriteID.MagicNecroOn.BASIC_REANIMATION),
	DRAYNOR_MANOR_TELEPORT("Draynor Manor Teleport", Rarity.COMMON, -1, SpriteID.MagicNecroOn.DRAYNOR_MANOR_TELEPORT),
	BATTLEFRONT_TELEPORT("Battlefront Teleport", Rarity.UNCOMMON, -1, 1255),
	MIND_ALTAR_TELEPORT("Mind Altar Teleport", Rarity.UNCOMMON, -1, SpriteID.MagicNecroOn.MIND_ALTAR_TELEPORT),
	RESPAWN_TELEPORT("Respawn Teleport", Rarity.UNCOMMON, -1, SpriteID.MagicNecroOn.RESPAWN_TELEPORT),
	GHOSTLY_GRASP("Ghostly Grasp", Rarity.UNCOMMON, -1, SpriteID.MagicNecroOn.GHOSTLY_GRASP),
	RESURRECT_LESSER_GHOST("Resurrect Lesser Ghost", Rarity.UNCOMMON, -1, SpriteID.MagicNecroOn.RESURRECT_LESSER_GHOST),
	RESURRECT_LESSER_SKELETON("Resurrect Lesser Skeleton", Rarity.UNCOMMON, -1, 1271),
	RESURRECT_LESSER_ZOMBIE("Resurrect Lesser Zombie", Rarity.UNCOMMON, -1, 1300),
	SALVE_GRAVEYARD_TELEPORT("Salve Graveyard Teleport", Rarity.RARE, -1, 1258),
	ADEPT_REANIMATION("Adept Reanimation", Rarity.RARE, -1, SpriteID.MagicNecroOn.ADEPT_REANIMATION),
	INFERIOR_DEMONBANE("Inferior Demonbane", Rarity.RARE, -1, SpriteID.MagicNecroOn.INFERIOR_DEMONBANE),
	SHADOW_VEIL("Shadow Veil", Rarity.RARE, -1, SpriteID.MagicNecroOn.SHADOW_VEIL),
	FENKENSTRAIN_S_CASTLE_TELEPORT("Fenkenstrain's Castle Teleport", Rarity.RARE, -1, SpriteID.MagicNecroOn.FENKENSTRAINS_CASTLE_TELEPORT),
	DARK_LURE("Dark Lure", Rarity.RARE, -1, SpriteID.MagicNecroOn.DARK_LURE),
	SKELETAL_GRASP("Skeletal Grasp", Rarity.RARE, -1, SpriteID.MagicNecroOn.SKELETAL_GRASP),
	RESURRECT_SUPERIOR_GHOST("Resurrect Superior Ghost", Rarity.RARE, -1, 2979),
	RESURRECT_SUPERIOR_SKELETON("Resurrect Superior Skeleton", Rarity.RARE, -1, SpriteID.MagicNecroOn.RESURRECT_SUPERIOR_SKELETON),
	RESURRECT_SUPERIOR_ZOMBIE("Resurrect Superior Zombie", Rarity.RARE, -1, 2983),
	MARK_OF_DARKNESS("Mark of Darkness", Rarity.RARE, -1, SpriteID.MagicNecroOn.MARK_OF_DARKNESS),
	WEST_ARDOUGNE_TELEPORT("West Ardougne Teleport", Rarity.EPIC, -1, SpriteID.MagicNecroOn.WEST_ARDOUGNE_TELEPORT),
	SUPERIOR_DEMONBANE("Superior Demonbane", Rarity.EPIC, -1, SpriteID.MagicNecroOn.SUPERIOR_DEMONBANE),
	LESSER_CORRUPTION("Lesser Corruption", Rarity.EPIC, -1, SpriteID.MagicNecroOn.LESSER_CORRUPTION),
	HARMONY_ISLAND_TELEPORT("Harmony Island Teleport", Rarity.EPIC, -1, SpriteID.MagicNecroOn.HARMONY_ISLAND_TELEPORT),
	VILE_VIGOUR("Vile Vigour", Rarity.EPIC, -1, SpriteID.MagicNecroOn.VILE_VIGOUR),
	DEGRIME("Degrime", Rarity.EPIC, -1, SpriteID.MagicNecroOn.DEGRIME),
	CEMETERY_TELEPORT("Cemetery Teleport", Rarity.EPIC, -1, SpriteID.MagicNecroOn.CEMETERY_TELEPORT),
	EXPERT_REANIMATION("Expert Reanimation", Rarity.EPIC, -1, SpriteID.MagicNecroOn.EXPERT_REANIMATION),
	WARD_OF_ARCEUUS("Ward of Arceuus", Rarity.EPIC, -1, SpriteID.MagicNecroOn.WARD_OF_ARCEUUS),
	RESURRECT_GREATER_GHOST("Resurrect Greater Ghost", Rarity.EPIC, -1, 2980),
	RESURRECT_GREATER_SKELETON("Resurrect Greater Skeleton", Rarity.EPIC, -1, 2982),
	RESURRECT_GREATER_ZOMBIE("Resurrect Greater Zombie", Rarity.EPIC, -1, SpriteID.MagicNecroOn.RESURRECT_GREATER_ZOMBIE),
	RESURRECT_CROPS("Resurrect Crops", Rarity.EPIC, -1, SpriteID.MagicNecroOn.RESURRECT_CROPS),
	UNDEAD_GRASP("Undead Grasp", Rarity.EPIC, -1, SpriteID.MagicNecroOn.UNDEAD_GRASP),
	DEATH_CHARGE("Death Charge", Rarity.LEGENDARY, -1, SpriteID.MagicNecroOn.DEATH_CHARGE),
	DARK_DEMONBANE("Dark Demonbane", Rarity.LEGENDARY, -1, SpriteID.MagicNecroOn.DARK_DEMONBANE),
	BARROWS_TELEPORT("Barrows Teleport", Rarity.LEGENDARY, -1, SpriteID.MagicNecroOn.BARROWS_TELEPORT),
	DEMONIC_OFFERING("Demonic Offering", Rarity.LEGENDARY, -1, SpriteID.MagicNecroOn.DEMONIC_OFFERING),
	GREATER_CORRUPTION("Greater Corruption", Rarity.LEGENDARY, -1, SpriteID.MagicNecroOn.GREATER_CORRUPTION),
	TELEPORT_TO_TARGET("Teleport to Target", Rarity.LEGENDARY, -1, SpriteID.Magicon2.TELEPORT_TO_BOUNTY_TARGET),
	APE_ATOLL_TELEPORT("Ape Atoll Teleport", Rarity.LEGENDARY, -1, SpriteID.MagicNecroOn.APE_ATOLL_TELEPORT),
	MASTER_REANIMATION("Master Reanimation", Rarity.LEGENDARY, -1, SpriteID.MagicNecroOn.MASTER_REANIMATION),
	SINISTER_OFFERING("Sinister Offering", Rarity.LEGENDARY, -1, SpriteID.MagicNecroOn.SINISTER_OFFERING);

	private final Card card;

	SpellsArceuusCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.SPELLS_ARCEUUS, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
