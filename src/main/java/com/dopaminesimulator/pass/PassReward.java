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
package com.dopaminesimulator.pass;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardOrigin;
import com.dopaminesimulator.cards.Dust;
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.cosmetics.CardBack;
import com.dopaminesimulator.incremental.BigNumbers;
import com.dopaminesimulator.packs.PackTier;
import java.awt.Color;
import lombok.Value;

@Value
public class PassReward
{
	PassRewardKind kind;
	PackTier pack;
	Rarity rarity;
	long amount;

	Card card;

	public static PassReward packs(PackTier tier, int count)
	{
		return new PassReward(PassRewardKind.PACK, tier, null, count, null);
	}

	public static PassReward shards(Rarity rarity, long amount)
	{
		return new PassReward(PassRewardKind.SHARDS, null, rarity, amount, null);
	}

	public static PassReward shiny()
	{
		return new PassReward(PassRewardKind.SHINY, null, null, 1, null);
	}

	public static PassReward gilded()
	{
		return new PassReward(PassRewardKind.GILDED, null, null, 1, null);
	}

	public static PassReward cardBack(CardBack back)
	{
		return new PassReward(PassRewardKind.CARD_BACK, null, null, back.ordinal(), null);
	}

	public static PassReward wildcards(int count)
	{
		return new PassReward(PassRewardKind.WILDCARD, null, null, count, null);
	}

	public static PassReward card(Card card)
	{
		return new PassReward(PassRewardKind.CARD, null, card.getRarity(), 1, card);
	}

	public CardBack back()
	{
		return CardBack.values()[(int) Math.max(0,
			Math.min(CardBack.values().length - 1, amount))];
	}

	public String describe()
	{
		switch (kind)
		{
			case PACK:
				return amount + "x " + pack.getDisplayName();
			case SHARDS:
				return BigNumbers.format(amount * Dust.fromOverflow(rarity)) + " dust";
			case SHINY:
				return "Make a card shiny";
			case GILDED:
				return "Gild a card";
			case CARD_BACK:
				return back().getDisplayName() + " card back";
			case CARD:
				return card.getName();
			default:
				return BigNumbers.format(amount * Dust.PER_WILDCARD) + " dust";
		}
	}

	public Color colour()
	{
		switch (kind)
		{
			case PACK:
				return pack.getColour();
			case SHARDS:
				return new Color(0xC9, 0xB8, 0xE8);
			case SHINY:
				return new Color(0x7C, 0xE6, 0xD6);
			case CARD_BACK:
				return back().getTrim();
			case CARD:
				return CardOrigin.PASS.getColour();
			case WILDCARD:
				return new Color(0xC9, 0xB8, 0xE8);
			default:
				return new Color(0xD9, 0xA8, 0x33);
		}
	}
}
