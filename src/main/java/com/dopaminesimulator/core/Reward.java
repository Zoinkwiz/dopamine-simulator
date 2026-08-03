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
package com.dopaminesimulator.core;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.feats.Achievement;
import com.dopaminesimulator.feats.Feat;
import com.dopaminesimulator.points.PointSource;
import lombok.Value;

@Value
public class Reward
{
	RewardType type;
	String title;
	String detail;
	Card card;
	Rarity rarity;

	CardSet set;

	long amount;

	int copies;

	boolean shiny;

	boolean gilded;
	public Reward withCopies(int granted)
	{
		return new Reward(type, title, detail, card, rarity, set, amount, Math.max(1, granted),
			shiny, gilded);
	}

	public Reward withVariant(boolean becameShiny, boolean becameGilded)
	{
		return new Reward(type, title, detail, card, rarity, set, amount, copies,
			becameShiny, becameGilded);
	}

	public static Reward newCard(Card card)
	{
		return new Reward(RewardType.NEW_CARD, card.getName(),
			"New " + card.getRarity().getDisplayName() + " card",
			card, card.getRarity(), card.getSet(), 1, 1, false, false);
	}
	public static Reward duplicate(Card card, int dust)
	{
		return new Reward(RewardType.DUPLICATE, card.getName(), "Duplicate: +" + dust + " dust",
			card, card.getRarity(), card.getSet(), dust, 1, false, false);
	}

	public static Reward shiny(Card card)
	{
		return new Reward(RewardType.SHINY, card.getName(), "Shiny!",
			card, card.getRarity(), card.getSet(), 0, 1, false, false);
	}

	public static Reward gilded(Card card)
	{
		return new Reward(RewardType.GILDED, card.getName(), "Gilded!",
			card, card.getRarity(), card.getSet(), 0, 1, false, false);
	}

	public static Reward starUp(Card card, int stars)
	{
		return new Reward(RewardType.STAR_UP, card.getName(),
			stars + (stars == 1 ? " star" : " stars"),
			card, card.getRarity(), card.getSet(), stars, 1, false, false);
	}
	public static Reward fusion(Card card)
	{
		return new Reward(RewardType.FUSION, card.getName(),
			"Fused into a " + card.getRarity().getDisplayName(),
			card, card.getRarity(), card.getSet(), 1, 1, false, false);
	}
	public static Reward feat(Feat feat, int tier)
	{
		return new Reward(RewardType.FEAT, feat.tierName(tier) + " " + feat.getDisplayName(),
			"Rank " + tier + " of " + feat.maxTier(), null, null, null, tier, 1, false, false);
	}

	public static Reward achievement(Achievement achievement)
	{
		return new Reward(RewardType.ACHIEVEMENT, achievement.getDisplayName(),
			achievement.getDescription(), null, null, null, 0, 1, false, false);
	}

	public static Reward bannerWin(Card card)
	{
		return new Reward(RewardType.BANNER_WIN, card.getName(), "Featured",
			card, card.getRarity(), card.getSet(), 0, 1, false, false);
	}

	public static Reward setComplete(CardSet set)
	{
		return new Reward(RewardType.SET_COMPLETE, set.getDisplayName() + " complete!",
			"Every card in the set collected", null, null, set, 1, 1, false, false);
	}

	public static Reward sourceUnlocked(PointSource source)
	{
		return new Reward(RewardType.SOURCE_UNLOCKED, source.getDisplayName() + " unlocked",
			source.getDescription() + " now earns points", null, null, null, 1, 1, false, false);
	}
}
