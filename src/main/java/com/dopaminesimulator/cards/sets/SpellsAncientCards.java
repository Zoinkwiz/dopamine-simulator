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

public enum SpellsAncientCards implements CardGroup
{
	EDGEVILLE_HOME_TELEPORT("Edgeville Home Teleport", Rarity.COMMON, -1, SpriteID.Magicon2.LUMBRIDGE_HOME_TELEPORT),
	MINIGAME_TELEPORT("Minigame Teleport", Rarity.COMMON, -1, 7479),
	SMOKE_RUSH("Smoke Rush", Rarity.RARE, -1, SpriteID.Magicon2.SMOKE_RUSH),
	SHADOW_RUSH("Shadow Rush", Rarity.RARE, -1, SpriteID.Magicon2.SHADOW_RUSH),
	PADDEWWA_TELEPORT("Paddewwa Teleport", Rarity.RARE, -1, SpriteID.Magicon2.PADDEWWA_TELEPORT),
	BLOOD_RUSH("Blood Rush", Rarity.RARE, -1, SpriteID.Magicon2.BLOOD_RUSH),
	ICE_RUSH("Ice Rush", Rarity.RARE, -1, SpriteID.Magicon2.ICE_RUSH),
	SENNTISTEN_TELEPORT("Senntisten Teleport", Rarity.EPIC, -1, SpriteID.Magicon2.SENNTISTEN_TELEPORT),
	SMOKE_BURST("Smoke Burst", Rarity.EPIC, -1, SpriteID.Magicon2.SMOKE_BURST),
	SHADOW_BURST("Shadow Burst", Rarity.EPIC, -1, SpriteID.Magicon2.SHADOW_BURST),
	KHARYRLL_TELEPORT("Kharyrll Teleport", Rarity.EPIC, -1, SpriteID.Magicon2.KHARYRLL_TELEPORT),
	BLOOD_BURST("Blood Burst", Rarity.EPIC, -1, SpriteID.Magicon2.BLOOD_BURST),
	ICE_BURST("Ice Burst", Rarity.EPIC, -1, SpriteID.Magicon2.ICE_BURST),
	LASSAR_TELEPORT("Lassar Teleport", Rarity.EPIC, -1, SpriteID.Magicon2.LASSAR_TELEPORT),
	SMOKE_BLITZ("Smoke Blitz", Rarity.EPIC, -1, SpriteID.Magicon2.SMOKE_BLITZ),
	SHADOW_BLITZ("Shadow Blitz", Rarity.EPIC, -1, SpriteID.Magicon2.SHADOW_BLITZ),
	DAREEYAK_TELEPORT("Dareeyak Teleport", Rarity.EPIC, -1, SpriteID.Magicon2.DAREEYAK_TELEPORT),
	BLOOD_BLITZ("Blood Blitz", Rarity.LEGENDARY, -1, SpriteID.Magicon2.BLOOD_BLITZ),
	ICE_BLITZ("Ice Blitz", Rarity.LEGENDARY, -1, SpriteID.Magicon2.ICE_BLITZ),
	CARRALLANGER_TELEPORT("Carrallanger Teleport", Rarity.LEGENDARY, -1, SpriteID.Magicon2.CARRALLANGAR_TELEPORT),
	TELEPORT_TO_TARGET("Teleport to Target", Rarity.LEGENDARY, -1, SpriteID.Magicon2.TELEPORT_TO_BOUNTY_TARGET),
	SMOKE_BARRAGE("Smoke Barrage", Rarity.LEGENDARY, -1, SpriteID.Magicon2.SMOKE_BARRAGE),
	SHADOW_BARRAGE("Shadow Barrage", Rarity.LEGENDARY, -1, SpriteID.Magicon2.SHADOW_BARRAGE),
	ANNAKARL_TELEPORT("Annakarl Teleport", Rarity.LEGENDARY, -1, SpriteID.Magicon2.ANNAKARL_TELEPORT),
	BLOOD_BARRAGE("Blood Barrage", Rarity.LEGENDARY, -1, SpriteID.Magicon2.BLOOD_BARRAGE),
	ICE_BARRAGE("Ice Barrage", Rarity.LEGENDARY, -1, SpriteID.Magicon2.ICE_BARRAGE),
	GHORROCK_TELEPORT("Ghorrock Teleport", Rarity.LEGENDARY, -1, SpriteID.Magicon2.GHORROCK_TELEPORT);

	private final Card card;

	SpellsAncientCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.SPELLS_ANCIENT, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
