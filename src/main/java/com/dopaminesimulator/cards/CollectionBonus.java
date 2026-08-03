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

import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.points.PointSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class CollectionBonus
{
	private static final double PER_ROOT_STAR = 0.04d;

	private static final Map<PointSource, List<CardSet>> SETS = new EnumMap<>(PointSource.class);
	private static final Map<CardSet, PointSource> OWNER = new EnumMap<>(CardSet.class);

	static
	{

		feed(PointSource.EXPERIENCE, CardSet.SKILLS, CardSet.SMITHING, CardSet.MINING,
			CardSet.RUNECRAFT, CardSet.SPELLS_LUNAR);
		feed(PointSource.COMBAT, CardSet.BOSSES, CardSet.ATTACK, CardSet.STRENGTH,
			CardSet.RANGED, CardSet.MAGIC, CardSet.SPELLS_STANDARD, CardSet.SPELLS_ANCIENT);
		feed(PointSource.RECOVERY, CardSet.SLAYER, CardSet.COOKING, CardSet.HERBLORE,
			CardSet.FARMING);
		feed(PointSource.TRAVEL, CardSet.DIARIES, CardSet.AGILITY, CardSet.SAILING,
			CardSet.AREAS);
		feed(PointSource.WEALTH, CardSet.ITEMS, CardSet.THIEVING, CardSet.HUNTER);
		feed(PointSource.CLICK, CardSet.MINIGAMES, CardSet.CRAFTING, CardSet.FLETCHING,
			CardSet.FIREMAKING);
		feed(PointSource.IDLING, CardSet.QUESTS, CardSet.FISHING, CardSet.WOODCUTTING);

		feed(PointSource.SUFFERING, CardSet.PRAYER, CardSet.DEFENCE, CardSet.CONSTRUCTION,
			CardSet.SPELLS_ARCEUUS);
	}

	private CollectionBonus()
	{
	}

	// Every set must appear once here, or its cards feed no source at all.
	private static void feed(PointSource source, CardSet... sets)
	{
		List<CardSet> list = new ArrayList<>();
		for (CardSet set : sets)
		{
			list.add(set);
			OWNER.put(set, source);
		}
		SETS.put(source, Collections.unmodifiableList(list));
	}

	public static List<CardSet> setsFor(PointSource source)
	{
		return SETS.getOrDefault(source, Collections.singletonList(CardSet.QUESTS));
	}

	public static CardSet setFor(PointSource source)
	{
		return setsFor(source).get(0);
	}

	public static PointSource sourceFor(CardSet set)
	{
		return OWNER.getOrDefault(set, PointSource.IDLING);
	}

	public static double multiplierFor(DopamineState state, PointSource source)
	{
		List<CardSet> sets = setsFor(source);
		long stars = 0;
		for (CardSet set : sets)
		{
			stars += state.getWeightedStarsInSet(set);
		}
		return (1d + Math.sqrt(stars) * PER_ROOT_STAR)
			* CardCollection.multiplierFor(state, sets);
	}

	public static int percentFor(DopamineState state, PointSource source)
	{
		return (int) Math.round((multiplierFor(state, source) - 1d) * 100d);
	}
}
