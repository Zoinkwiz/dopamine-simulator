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

public enum StrengthCards implements CardGroup
{
	BRONZE_WARHAMMER("Bronze warhammer", Rarity.COMMON, ItemID.BRONZE_WARHAMMER, -1),
	IRON_WARHAMMER("Iron warhammer", Rarity.COMMON, ItemID.IRON_WARHAMMER, -1),
	BLACK_HALBERD("Black halberd", Rarity.COMMON, ItemID.BLACK_HALBERD, -1),
	STEEL_WARHAMMER("Steel warhammer", Rarity.COMMON, ItemID.STEEL_WARHAMMER, -1),
	WHITE_HALBERD("White halberd", Rarity.COMMON, ItemID.WHITE_HALBERD, -1),
	BLACK_WARHAMMER("Black warhammer", Rarity.COMMON, ItemID.BLACK_WARHAMMER, -1),
	MITHRIL_HALBERD("Mithril halberd", Rarity.COMMON, ItemID.MITHRIL_HALBERD, -1),
	WHITE_WARHAMMER("White warhammer", Rarity.COMMON, ItemID.WHITE_WARHAMMER, -1),
	ADAMANT_HALBERD("Adamant halberd", Rarity.COMMON, ItemID.ADAMANT_HALBERD, -1),
	MITHRIL_WARHAMMER("Mithril warhammer", Rarity.UNCOMMON, ItemID.MITHRIL_WARHAMMER, -1),
	RUNE_HALBERD("Rune halberd", Rarity.UNCOMMON, ItemID.RUNE_HALBERD, -1),
	ADAMANT_WARHAMMER("Adamant warhammer", Rarity.UNCOMMON, ItemID.ADAMNT_WARHAMMER, -1),
	DRAGON_HALBERD("Dragon halberd", Rarity.UNCOMMON, ItemID.DRAGON_HALBERD, -1),
	RUNE_WARHAMMER("Rune warhammer", Rarity.RARE, ItemID.RUNE_WARHAMMER, -1),
	DRAGON_WARHAMMER("Dragon warhammer", Rarity.EPIC, ItemID.DRAGON_WARHAMMER, -1),
	TZHAAR_KET_OM("TzHaar-Ket-Om", Rarity.EPIC, ItemID.TZHAAR_MAUL, -1);

	private final Card card;

	StrengthCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.STRENGTH, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
