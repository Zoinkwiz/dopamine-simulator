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

import com.dopaminesimulator.core.DopamineEvent;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.EventType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CardAffinity
{
	private static final double PER_STAR = 0.08d;
	private static final Set<CardSet> AFFINITY_SETS =
		EnumSet.of(CardSet.SKILLS, CardSet.BOSSES, CardSet.SLAYER);
	private static final Map<String, List<Card>> BY_SUBJECT = new HashMap<>();
	static
	{
		for (Card card : CardCatalogue.all())
		{
			if (AFFINITY_SETS.contains(card.getSet()))
			{
				BY_SUBJECT.computeIfAbsent(squash(card.getName()), k -> new ArrayList<>()).add(card);
			}
		}
	}
	private CardAffinity()
	{
	}

	public static boolean canHaveAffinity(CardSet set)
	{
		return AFFINITY_SETS.contains(set);
	}
	public static double multiplierFor(DopamineState state, DopamineEvent event)
	{
		if (!appliesTo(event.getType()))
		{
			return 1d;
		}
		int stars = starsForSubject(state, event.getKey());
		return stars <= 0 ? 1d : 1d + stars * PER_STAR;
	}
	public static int starsForSubject(DopamineState state, String subject)
	{
		Card card = cardForSubject(state, subject);
		return card == null ? 0 : state.getStars(card.getId());
	}
	public static Card cardForSubject(DopamineState state, String subject)
	{
		if (subject == null || subject.isEmpty())
		{
			return null;
		}
		List<Card> candidates = BY_SUBJECT.get(squash(subject));
		if (candidates == null)
		{
			return null;
		}
		Card best = null;
		int bestStars = 0;
		for (Card card : candidates)
		{
			int stars = state.getStars(card.getId());
			if (best == null || stars > bestStars)
			{
				best = card;
				bestStars = stars;
			}
		}
		return best;
	}
	public static int percentFor(DopamineState state, Card card)
	{
		if (!canHaveAffinity(card.getSet()))
		{
			return 0;
		}
		return (int) Math.round(state.getStars(card.getId()) * PER_STAR * 100d);
	}
	public static int percentAtStars(int stars)
	{
		return (int) Math.round(stars * PER_STAR * 100d);
	}
	public static String describe(Card card)
	{
		switch (card.getSet())
		{
			case SKILLS:
				return card.getName() + " experience pays more";
			case BOSSES:
			case SLAYER:
				return "Killing " + card.getName() + " pays more";
			default:
				return null;
		}
	}
	private static boolean appliesTo(EventType type)
	{
		return type == EventType.XP_GAINED
			|| type == EventType.NPC_KILLED
			|| type == EventType.LOOT_RECEIVED;
	}
	private static String squash(String name)
	{
		StringBuilder squashed = new StringBuilder(name.length());
		for (int i = 0; i < name.length(); i++)
		{
			char c = name.charAt(i);
			if (Character.isLetterOrDigit(c))
			{
				squashed.append(Character.toUpperCase(c));
			}
		}
		return squashed.toString();
	}

}
