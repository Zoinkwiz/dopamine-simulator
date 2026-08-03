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

public enum DiariesCards implements CardGroup
{
	ARDOUGNE_EASY("Ardougne Easy", Rarity.COMMON, ItemID.ARDY_CAPE_EASY, -1),
	DESERT_EASY("Desert Easy", Rarity.COMMON, ItemID.DESERT_AMULET_EASY, -1),
	FALADOR_EASY("Falador Easy", Rarity.COMMON, ItemID.FALADOR_SHIELD_EASY, -1),
	FREMENNIK_EASY("Fremennik Easy", Rarity.COMMON, ItemID.FREMENNIK_BOOTS_EASY, -1),
	KANDARIN_EASY("Kandarin Easy", Rarity.COMMON, ItemID.SEERS_HEADBAND_EASY, -1),
	KARAMJA_EASY("Karamja Easy", Rarity.COMMON, ItemID.ATJUN_GLOVES_EASY, -1),
	KOUREND_KEBOS_EASY("Kourend & Kebos Easy", Rarity.COMMON, ItemID.ZEAH_BLESSING_EASY, -1),
	LUMBRIDGE_DRAYNOR_EASY("Lumbridge & Draynor Easy", Rarity.COMMON, ItemID.LUMBRIDGE_RING_EASY, -1),
	MORYTANIA_EASY("Morytania Easy", Rarity.COMMON, ItemID.MORYTANIA_LEGS_EASY, -1),
	VARROCK_EASY("Varrock Easy", Rarity.COMMON, ItemID.VARROCK_ARMOUR_EASY, -1),
	WESTERN_PROVINCES_EASY("Western Provinces Easy", Rarity.COMMON, ItemID.WESTERN_BANNER_EASY, -1),
	WILDERNESS_EASY("Wilderness Easy", Rarity.COMMON, ItemID.WILDERNESS_SWORD_EASY, -1),
	ARDOUGNE_MEDIUM("Ardougne Medium", Rarity.UNCOMMON, ItemID.ARDY_CAPE_MEDIUM, -1),
	DESERT_MEDIUM("Desert Medium", Rarity.UNCOMMON, ItemID.DESERT_AMULET_MEDIUM, -1),
	FALADOR_MEDIUM("Falador Medium", Rarity.UNCOMMON, ItemID.FALADOR_SHIELD_MEDIUM, -1),
	FREMENNIK_MEDIUM("Fremennik Medium", Rarity.UNCOMMON, ItemID.FREMENNIK_BOOTS_MEDIUM, -1),
	KANDARIN_MEDIUM("Kandarin Medium", Rarity.UNCOMMON, ItemID.SEERS_HEADBAND_MEDIUM, -1),
	KARAMJA_MEDIUM("Karamja Medium", Rarity.UNCOMMON, ItemID.ATJUN_GLOVES_MED, -1),
	KOUREND_KEBOS_MEDIUM("Kourend & Kebos Medium", Rarity.UNCOMMON, ItemID.ZEAH_BLESSING_MEDIUM, -1),
	LUMBRIDGE_DRAYNOR_MEDIUM("Lumbridge & Draynor Medium", Rarity.UNCOMMON, ItemID.LUMBRIDGE_RING_MEDIUM, -1),
	MORYTANIA_MEDIUM("Morytania Medium", Rarity.UNCOMMON, ItemID.MORYTANIA_LEGS_MEDIUM, -1),
	VARROCK_MEDIUM("Varrock Medium", Rarity.UNCOMMON, ItemID.VARROCK_ARMOUR_MEDIUM, -1),
	WESTERN_PROVINCES_MEDIUM("Western Provinces Medium", Rarity.UNCOMMON, ItemID.WESTERN_BANNER_MEDIUM, -1),
	WILDERNESS_MEDIUM("Wilderness Medium", Rarity.UNCOMMON, ItemID.WILDERNESS_SWORD_MEDIUM, -1),
	ARDOUGNE_HARD("Ardougne Hard", Rarity.RARE, ItemID.ARDY_CAPE_HARD, -1),
	DESERT_HARD("Desert Hard", Rarity.RARE, ItemID.DESERT_AMULET_HARD, -1),
	FALADOR_HARD("Falador Hard", Rarity.RARE, ItemID.FALADOR_SHIELD_HARD, -1),
	FREMENNIK_HARD("Fremennik Hard", Rarity.RARE, ItemID.FREMENNIK_BOOTS_HARD, -1),
	KANDARIN_HARD("Kandarin Hard", Rarity.RARE, ItemID.SEERS_HEADBAND_HARD, -1),
	KARAMJA_HARD("Karamja Hard", Rarity.RARE, ItemID.ATJUN_GLOVES_HARD, -1),
	KOUREND_KEBOS_HARD("Kourend & Kebos Hard", Rarity.RARE, ItemID.ZEAH_BLESSING_HARD, -1),
	LUMBRIDGE_DRAYNOR_HARD("Lumbridge & Draynor Hard", Rarity.RARE, ItemID.LUMBRIDGE_RING_HARD, -1),
	MORYTANIA_HARD("Morytania Hard", Rarity.RARE, ItemID.MORYTANIA_LEGS_HARD, -1),
	VARROCK_HARD("Varrock Hard", Rarity.RARE, ItemID.VARROCK_ARMOUR_HARD, -1),
	WESTERN_PROVINCES_HARD("Western Provinces Hard", Rarity.RARE, ItemID.WESTERN_BANNER_HARD, -1),
	WILDERNESS_HARD("Wilderness Hard", Rarity.RARE, ItemID.WILDERNESS_SWORD_HARD, -1),
	ARDOUGNE_ELITE("Ardougne Elite", Rarity.EPIC, ItemID.ARDY_CAPE_ELITE, -1),
	DESERT_ELITE("Desert Elite", Rarity.EPIC, ItemID.DESERT_AMULET_ELITE, -1),
	FALADOR_ELITE("Falador Elite", Rarity.EPIC, ItemID.FALADOR_SHIELD_ELITE, -1),
	FREMENNIK_ELITE("Fremennik Elite", Rarity.EPIC, ItemID.FREMENNIK_BOOTS_ELITE, -1),
	KANDARIN_ELITE("Kandarin Elite", Rarity.EPIC, ItemID.SEERS_HEADBAND_ELITE, -1),
	KARAMJA_ELITE("Karamja Elite", Rarity.EPIC, ItemID.ATJUN_GLOVES_ELITE, -1),
	KOUREND_KEBOS_ELITE("Kourend & Kebos Elite", Rarity.EPIC, ItemID.ZEAH_BLESSING_ELITE, -1),
	LUMBRIDGE_DRAYNOR_ELITE("Lumbridge & Draynor Elite", Rarity.EPIC, ItemID.LUMBRIDGE_RING_ELITE, -1),
	MORYTANIA_ELITE("Morytania Elite", Rarity.EPIC, ItemID.MORYTANIA_LEGS_ELITE, -1),
	VARROCK_ELITE("Varrock Elite", Rarity.EPIC, ItemID.VARROCK_ARMOUR_ELITE, -1),
	WESTERN_PROVINCES_ELITE("Western Provinces Elite", Rarity.EPIC, ItemID.WESTERN_BANNER_ELITE, -1),
	WILDERNESS_ELITE("Wilderness Elite", Rarity.EPIC, ItemID.WILDERNESS_SWORD_ELITE, -1),
	ACHIEVEMENT_DIARY_CAPE("Achievement Diary Cape", Rarity.LEGENDARY, ItemID.SKILLCAPE_AD_TRIMMED, -1);

	private final Card card;

	DiariesCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.DIARIES, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
