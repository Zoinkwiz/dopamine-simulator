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

import com.dopaminesimulator.cards.sets.AreasCards;
import com.dopaminesimulator.cards.sets.BossesCards;
import com.dopaminesimulator.cards.sets.MinigamesCards;
import com.dopaminesimulator.cards.sets.RaidsDropsCards;
import com.dopaminesimulator.cards.sets.SkillsCards;
import com.dopaminesimulator.cards.sets.SlayerCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The whole exclusivity map, in one place.
 *
 * Cards listed here are pulled out of every pack pool and can only be had from the
 * source that claims them. Everything not listed stays in packs. Membership is
 * written as enum constants rather than id strings so a card that gets renamed or
 * removed fails the build instead of quietly becoming unobtainable.
 *
 * Most lists are also a rotation: the order below is the order players earn them
 * in. The gacha pools are the exception — a banner features one of its own at
 * random, so their order carries no meaning.
 */
public final class CardOrigins
{
	private static final Map<String, CardOrigin> BY_ID = new HashMap<>();
	private static final Map<CardOrigin, List<Card>> BY_ORIGIN = new EnumMap<>(CardOrigin.class);

	/**
	 * The three banners all draw from the raids collection log, because that set is
	 * already the game's own megarare table and it carries a clean rarity spread to
	 * split across them. Items used to hold these; it was reorganised down to
	 * ordinary goods, which is a better home for the pack pool anyway.
	 *
	 * Curious Findings, the cheapest banner: the odd drops rather than best-in-slot.
	 */
	private static final List<Card> CURIOUS = claim(CardOrigin.GACHA,
		RaidsDropsCards.ONYX,
		RaidsDropsCards.DINHS_BULWARK,
		RaidsDropsCards.TWISTED_BUCKLER,
		RaidsDropsCards.JUSTICIAR_FACEGUARD,
		RaidsDropsCards.JUSTICIAR_CHESTGUARD,
		RaidsDropsCards.JUSTICIAR_LEG_GUARDS,
		RaidsDropsCards.ELIDINIS_WARD,
		RaidsDropsCards.LIGHTBEARER,
		RaidsDropsCards.BREACH_OF_THE_SCARAB,
		RaidsDropsCards.EYE_OF_THE_CORRUPTOR,
		RaidsDropsCards.JEWEL_OF_THE_SUN,
		RaidsDropsCards.JEWEL_OF_AMASCUT,
		RaidsDropsCards.COX_CHALLENGE_CAPE_T1);

	/** Storied Relics, the middle banner. The named endgame pieces. */
	private static final List<Card> STORIED = claim(CardOrigin.GACHA,
		RaidsDropsCards.DRAGON_CLAWS,
		RaidsDropsCards.ANCESTRAL_HAT,
		RaidsDropsCards.ANCESTRAL_ROBE_TOP,
		RaidsDropsCards.ANCESTRAL_ROBE_BOTTOM,
		RaidsDropsCards.GHRAZI_RAPIER,
		RaidsDropsCards.SANGUINESTI_STAFF_UNCHARGED,
		RaidsDropsCards.INFERNAL_DEFENDER_HILT,
		RaidsDropsCards.OSMUMTENS_FANG,
		RaidsDropsCards.MASORI_MASK,
		RaidsDropsCards.MASORI_BODY,
		RaidsDropsCards.MASORI_CHAPS,
		RaidsDropsCards.DRAGON_HUNTER_CROSSBOW,
		RaidsDropsCards.RAIDS_PRAYERSCROLL);

	/**
	 * The megarares. The Mythic Invocation banner features one of these at a time,
	 * which is the only way any of them enters a collection. All eight raid
	 * legendaries, pets included — the pets are the purest chase in the game.
	 */
	private static final List<Card> MYTHIC = claim(CardOrigin.GACHA,
		RaidsDropsCards.TWISTED_BOW,
		RaidsDropsCards.SCYTHE_OF_VITUR_UNCHARGED,
		RaidsDropsCards.TUMEKENS_SHADOW_UNCHARGED,
		RaidsDropsCards.ELDER_MAUL,
		RaidsDropsCards.KODAI_INSIGNIA,
		RaidsDropsCards.OLM_PET,
		RaidsDropsCards.VERZIKPET,
		RaidsDropsCards.WARDENPET_TUMEKEN);

	/**
	 * One region per season, claimed at the top of the pass. Thirteen of them against
	 * eleven {@link Region}s, so the two cycles drift apart rather than one gating the
	 * other — every card comes round inside thirteen seasons either way.
	 */
	private static final List<Card> SEASONAL = claim(CardOrigin.PASS,
		AreasCards.MISTHALIN,
		AreasCards.ASGARNIA,
		AreasCards.KANDARIN,
		AreasCards.MORYTANIA,
		AreasCards.KHARIDIAN_DESERT,
		AreasCards.FREMENNIK_PROVINCE,
		AreasCards.KARAMJA,
		AreasCards.KEBOS_LOWLANDS,
		AreasCards.TIRANNWN,
		AreasCards.GREAT_KOUREND,
		AreasCards.VARLAMORE,
		AreasCards.FELDIP_HILLS,
		AreasCards.TROLL_COUNTRY);

	/** One skill mastered per prestige. Twenty-four resets to hold them all. */
	private static final List<Card> MASTERY = claim(CardOrigin.PRESTIGE,
		SkillsCards.ATTACK_RUNE,
		SkillsCards.STRENGTH_RUNE,
		SkillsCards.DEFENCE_RUNE,
		SkillsCards.HITPOINTS_RUNE,
		SkillsCards.RANGED_RUNE,
		SkillsCards.MAGIC_RUNE,
		SkillsCards.PRAYER_RUNE,
		SkillsCards.SLAYER_RUNE,
		SkillsCards.AGILITY_RUNE,
		SkillsCards.THIEVING_RUNE,
		SkillsCards.HERBLORE_RUNE,
		SkillsCards.FARMING_RUNE,
		SkillsCards.HUNTER_RUNE,
		SkillsCards.MINING_RUNE,
		SkillsCards.SMITHING_RUNE,
		SkillsCards.FISHING_RUNE,
		SkillsCards.COOKING_RUNE,
		SkillsCards.FIREMAKING_RUNE,
		SkillsCards.WOODCUTTING_RUNE,
		SkillsCards.CRAFTING_RUNE,
		SkillsCards.FLETCHING_RUNE,
		SkillsCards.CONSTRUCTION_RUNE,
		SkillsCards.RUNECRAFT_RUNE,
		SkillsCards.SAILING_RUNE);

	/**
	 * Sealed behind achievements: the tasks nobody wants, which is what an
	 * achievement is. Twenty-two achievements against five cards, so finishing the
	 * list finishes the sub-collection.
	 *
	 * These were the diary elites at first. Diaries and Areas both feed TRAVEL, so
	 * sealing both put +180% of one point source behind two systems at once while
	 * three other sources got nothing. Slayer feeds RECOVERY, which had none.
	 */
	private static final List<Card> SEALED = claim(CardOrigin.ACHIEVEMENT,
		SlayerCards.DARK_BEAST,
		SlayerCards.SMOKE_DEVIL,
		SlayerCards.ARAXYTE,
		SlayerCards.HYDRA,
		SlayerCards.SKILLCAPE);

	/** The trophies for burning a maxed collection down. */
	private static final List<Card> ASCENDANT = claim(CardOrigin.ASCENSION,
		BossesCards.CHAMBERS_OF_XERIC,
		BossesCards.THEATRE_OF_BLOOD,
		BossesCards.TOMBS_OF_AMASCUT,
		MinigamesCards.INFERNO,
		BossesCards.YAMA,
		BossesCards.ROYAL_TITANS);

	private CardOrigins()
	{
	}

	public static CardOrigin of(Card card)
	{
		return card == null ? CardOrigin.PACKS : of(card.getId());
	}

	public static CardOrigin of(String cardId)
	{
		return BY_ID.getOrDefault(cardId, CardOrigin.PACKS);
	}

	public static boolean isExclusive(Card card)
	{
		return of(card).isExclusive();
	}

	public static List<Card> from(CardOrigin origin)
	{
		return BY_ORIGIN.getOrDefault(origin, Collections.emptyList());
	}

	public static Card seasonCard(int season)
	{
		return rotate(SEASONAL, season);
	}

	public static Card prestigeCard(int prestigeCount)
	{
		return rotate(MASTERY, prestigeCount);
	}

	public static Card achievementCard(int achievementsEarned)
	{
		return rotate(SEALED, achievementsEarned);
	}

	public static Card ascensionCard(int totalAscensions)
	{
		return rotate(ASCENDANT, totalAscensions);
	}

	// Rotations are 1-based and wrap, so a player past the end of a list starts
	// banking spare copies of it rather than falling off a cliff.
	private static Card rotate(List<Card> pool, int nth)
	{
		if (pool.isEmpty() || nth < 1)
		{
			return null;
		}
		return pool.get(Math.floorMod(nth - 1, pool.size()));
	}

	private static List<Card> claim(CardOrigin origin, CardGroup... members)
	{
		List<Card> claimed = new ArrayList<>(members.length);
		for (CardGroup member : members)
		{
			Card card = member.getCard();
			CardOrigin clash = BY_ID.put(card.getId(), origin);
			if (clash != null)
			{
				throw new IllegalStateException(card.getName() + " is claimed by both "
					+ clash + " and " + origin + ". A card has exactly one source, or"
					+ " the pack filter cannot decide whether to hide it.");
			}
			claimed.add(card);
		}
		List<Card> locked = Collections.unmodifiableList(claimed);

		// A source can claim in several batches — the gacha has one pool per banner
		// — so these accumulate rather than replacing what came before.
		BY_ORIGIN.merge(origin, locked, (held, added) ->
		{
			List<Card> both = new ArrayList<>(held);
			both.addAll(added);
			return Collections.unmodifiableList(both);
		});
		return locked;
	}
}
