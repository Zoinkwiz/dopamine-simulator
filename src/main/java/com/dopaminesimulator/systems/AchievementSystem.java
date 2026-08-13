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
package com.dopaminesimulator.systems;

import com.dopaminesimulator.cards.CardCollection;
import com.dopaminesimulator.cards.Dust;
import com.dopaminesimulator.core.DopamineEvent;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.DopamineSystem;
import com.dopaminesimulator.core.EventType;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.feats.Achievement;
import com.dopaminesimulator.feats.Feat;
import com.dopaminesimulator.feats.Feats;
import com.dopaminesimulator.packs.PackTier;
import com.dopaminesimulator.points.PointSource;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class AchievementSystem implements DopamineSystem
{
	private static final int MARATHON_TICKS = 24_000;
	private static final int TOUCH_GRASS_TICKS = 48_000;
	private static final int IDLE_TICKS = 6_000;
	private static final int SPRINT_TILES = 2_000;
	private static final int UNTOUCHED_KILLS = 50;
	private static final int GLASS_JAW_DAMAGE = 500;
	private static final int VARIETY_SKILLS = 10;
	private static final int NIGHT_SHIFT_SOURCES = 6;
	private static final int MASHER_CLICKS = 1_000;
	private static final int PACK_RAT_PACKS = 1_000;
	private static final int DEDICATION_RANK = 10;
	private static final int WILDCARDS_PER_ACHIEVEMENT = 1;

	private final Set<String> skillsThisSession = new HashSet<>();
	private final Set<EventType> earnersThisSession = EnumSet.noneOf(EventType.class);
	private long sessionTicks;
	private long idleTicks;
	private long sessionTiles;
	private long sessionDamage;
	private long sessionClicks;
	private int killsSinceHit;

	public void newSession()
	{
		skillsThisSession.clear();
		earnersThisSession.clear();
		sessionTicks = 0L;
		idleTicks = 0L;
		sessionTiles = 0L;
		sessionDamage = 0L;
		sessionClicks = 0L;
		killsSinceHit = 0;
	}

	@Override
	public String getName()
	{
		return "Achievements";
	}

	@Override
	public void handle(DopamineState state, DopamineEvent event, RewardQueue rewards)
	{
		PointSource source = PointSource.forEvent(event.getType());
		boolean earning = source != null && state.isSourceUnlocked(source);
		if (earning && event.getType() != EventType.TICK)
		{
			earnersThisSession.add(event.getType());
		}

		switch (event.getType())
		{
			case NPC_KILLED:
				killsSinceHit++;
				award(state, Achievement.FIRST_BLOOD, rewards);
				break;
			case LOOT_RECEIVED:
				award(state, Achievement.POCKET_CHANGE, rewards);
				break;
			case LEVEL_UP:
				award(state, Achievement.DING, rewards);
				break;
			case XP_GAINED:
				skillsThisSession.add(event.getKey());
				break;
			case DISTANCE_TRAVELLED:
				sessionTiles += event.getAmount();
				break;
			case DAMAGE_TAKEN:
				sessionDamage += event.getAmount();
				killsSinceHit = 0;
				break;
			case CLICK:
				sessionClicks++;
				break;
			case TICK:
				sessionTicks++;
				if (earning && state.isIdle())
				{
					idleTicks++;
				}
				checkState(state, rewards);
				break;
			default:
				break;
		}

		checkSession(state, rewards);
	}

	private void checkSession(DopamineState state, RewardQueue rewards)
	{
		if (skillsThisSession.size() >= VARIETY_SKILLS)
		{
			award(state, Achievement.VARIETY_ACT, rewards);
		}
		if (earnersThisSession.size() >= NIGHT_SHIFT_SOURCES)
		{
			award(state, Achievement.NIGHT_SHIFT, rewards);
		}
		if (sessionTiles >= SPRINT_TILES)
		{
			award(state, Achievement.SPRINTER, rewards);
		}
		if (sessionDamage >= GLASS_JAW_DAMAGE)
		{
			award(state, Achievement.GLASS_JAW, rewards);
		}
		if (killsSinceHit >= UNTOUCHED_KILLS)
		{
			award(state, Achievement.UNTOUCHABLE, rewards);
		}
		if (sessionClicks >= MASHER_CLICKS)
		{
			award(state, Achievement.BUTTON_MASHER, rewards);
		}
		if (sessionTicks >= MARATHON_TICKS)
		{
			award(state, Achievement.MARATHON, rewards);
		}
		if (sessionTicks >= TOUCH_GRASS_TICKS)
		{
			award(state, Achievement.TOUCH_GRASS, rewards);
		}
		if (idleTicks >= IDLE_TICKS)
		{
			award(state, Achievement.IDLE_HANDS, rewards);
		}
	}

	private void checkState(DopamineState state, RewardQueue rewards)
	{
		long packs = state.getTotalPacksOpened();
		if (packs >= 1)
		{
			award(state, Achievement.OPENING_NIGHT, rewards);
		}
		if (packs >= PACK_RAT_PACKS)
		{
			award(state, Achievement.PACK_RAT, rewards);
		}
		if (state.isPackUnlocked(PackTier.MYTHIC))
		{
			award(state, Achievement.HIGH_ROLLER, rewards);
		}
		if (!state.getShinyCards().isEmpty())
		{
			award(state, Achievement.SPARKLE, rewards);
		}
		if (!state.getGildedCards().isEmpty())
		{
			award(state, Achievement.GILT_TRIP, rewards);
		}
		for (String id : state.getShinyCards())
		{
			if (state.isGilded(id))
			{
				award(state, Achievement.DOUBLE_UP, rewards);
				break;
			}
		}
		if (state.isEarlyEpicPulled())
		{
			award(state, Achievement.BEGINNERS_LUCK, rewards);
		}
		if (!state.hasAchievement(Achievement.FULL_HOUSE.name()))
		{
			for (CardCollection collection : CardCollection.all())
			{
				if (collection.tierIn(state) >= CardCollection.TIER_STARS.length)
				{
					award(state, Achievement.FULL_HOUSE, rewards);
					break;
				}
			}
		}
		checkFeats(state, rewards);
	}

	private void checkFeats(DopamineState state, RewardQueue rewards)
	{
		boolean everyone = true;
		for (Feat feat : Feat.values())
		{
			int rank = Feats.tierOf(state, feat);
			everyone &= rank >= 1;
			if (rank >= DEDICATION_RANK)
			{
				award(state, Achievement.DEDICATION, rewards);
			}
		}
		if (everyone)
		{
			award(state, Achievement.WELL_ROUNDED, rewards);
		}
	}

	private void award(DopamineState state, Achievement achievement, RewardQueue rewards)
	{
		if (state.awardAchievement(achievement.name()))
		{

			state.addDust((long) WILDCARDS_PER_ACHIEVEMENT * Dust.PER_WILDCARD);
			rewards.push(Reward.achievement(achievement));
		}
	}
}
