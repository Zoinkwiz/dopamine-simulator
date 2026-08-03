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

import com.dopaminesimulator.core.DopamineEvent;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.DopamineSystem;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.core.SkillWeights;
import com.dopaminesimulator.feats.Feat;
import com.dopaminesimulator.feats.FeatTrack;
import com.dopaminesimulator.feats.Feats;
import java.util.EnumMap;
import java.util.Map;

public class FeatSystem implements DopamineSystem
{
	private static final int MAX_LEVEL = 99;

	@Override
	public String getName()
	{
		return "Feats";
	}

	@Override
	public void handle(DopamineState state, DopamineEvent event, RewardQueue rewards)
	{
		Map<FeatTrack, Integer> before = tiersByTrack(state);

		switch (event.getType())
		{
			case NPC_KILLED:
				state.addFeatProgress(FeatTrack.KILLS, 1L);
				if (state.recordNpcKilled(event.getKey()))
				{
					state.raiseFeatProgress(FeatTrack.DISTINCT_NPCS,
						state.getNpcsKilled().size());
				}
				break;
			case LOOT_RECEIVED:
				state.addFeatProgress(FeatTrack.TOTAL_LOOT, event.getAmount());
				state.raiseFeatProgress(FeatTrack.BIGGEST_DROP, event.getAmount());
				break;
			case LEVEL_UP:
				if (event.getAmount() >= MAX_LEVEL && state.recordSkillMaxed(event.getKey()))
				{
					state.raiseFeatProgress(FeatTrack.SKILLS_MAXED,
						state.getSkillsMaxed().size());
				}
				break;
			case DISTANCE_TRAVELLED:
				state.addFeatProgress(FeatTrack.TILES, event.getAmount());
				break;
			case DAMAGE_TAKEN:
				state.addFeatProgress(FeatTrack.DAMAGE_TAKEN, event.getAmount());
				break;
			case HEALTH_RESTORED:
				state.addFeatProgress(FeatTrack.HEALTH_RESTORED, event.getAmount());
				break;
			case XP_GAINED:
				state.addFeatProgress(FeatTrack.WEIGHTED_XP,
					(long) SkillWeights.weightedXp(event.getKey(), event.getAmount()));
				break;
			case TICK:
				state.addFeatProgress(FeatTrack.TICKS_PLAYED, 1L);
				break;
			default:
				return;
		}

		announce(state, before, rewards);
	}

	private Map<FeatTrack, Integer> tiersByTrack(DopamineState state)
	{
		Map<FeatTrack, Integer> tiers = new EnumMap<>(FeatTrack.class);
		for (Feat feat : Feat.values())
		{
			tiers.put(feat.getTrack(), Feats.tierOf(state, feat));
		}
		return tiers;
	}

	private void announce(DopamineState state, Map<FeatTrack, Integer> before, RewardQueue rewards)
	{
		for (Feat feat : Feat.values())
		{
			int was = before.getOrDefault(feat.getTrack(), 0);
			int now = Feats.tierOf(state, feat);
			if (now > was)
			{
				rewards.push(Reward.feat(feat, now));
			}
		}
	}
}
