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

import com.dopaminesimulator.core.Balance;
import com.dopaminesimulator.core.DopamineEvent;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.DopamineSystem;
import com.dopaminesimulator.core.EventType;
import com.dopaminesimulator.core.PointListener;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.core.SkillWeights;
import com.dopaminesimulator.cards.CardAffinity;
import com.dopaminesimulator.feats.Feats;
import com.dopaminesimulator.cards.CollectionBonus;
import com.dopaminesimulator.points.PointSource;
import com.dopaminesimulator.incremental.Milestones;
import java.util.function.DoubleSupplier;

public class PointSystem implements DopamineSystem
{
	private static final String PASSIVE_DETAIL = "Standing about";
	private final PointListener listener;

	private final DoubleSupplier foodMultiplier;

	public PointSystem()
	{
		this(PointListener.NOOP);
	}

	public PointSystem(PointListener listener)
	{
		this(listener, () -> 1d);
	}

	public PointSystem(PointListener listener, DoubleSupplier foodMultiplier)
	{
		this.listener = listener == null ? PointListener.NOOP : listener;
		this.foodMultiplier = foodMultiplier == null ? () -> 1d : foodMultiplier;
	}

	@Override
	public String getName()
	{
		return "Points";
	}
	@Override
	public void handle(DopamineState state, DopamineEvent event, RewardQueue rewards)
	{
		PointSource source = PointSource.forEvent(event.getType());
		if (source == null || !state.isSourceUnlocked(source))
		{
			return;
		}
		double units = unitsFor(state, event);
		if (units <= 0d)
		{
			return;
		}
		double affinity = CardAffinity.multiplierFor(state, event);
		double points;
		if (source == PointSource.CLICK)
		{

			points = units;
		}
		else
		{
			points = source.pointsFor(units, state.getSourceUpgradeLevel(source),
					state.getInsight())
				* Milestones.globalMultiplier(state.getLifetimePoints(), state)
				* CollectionBonus.multiplierFor(state, source)
				* Feats.multiplierFor(state)
				* foodMultiplier.getAsDouble()
				* affinity;
		}
		state.addPoints(points);
		if (source != PointSource.IDLING)
		{
			state.setLastEarningTick(state.getTick());
		}
		listener.onPointsGained(source, detailOf(event, affinity), points, state.getTick());
	}
	private double unitsFor(DopamineState state, DopamineEvent event)
	{
		switch (event.getType())
		{
			case CLICK:
				return event.getAmount();
			case XP_GAINED:
				return SkillWeights.weightedXp(event.getKey(), event.getAmount());
			case NPC_KILLED:
				return Balance.COMBAT_UNITS_BASE
					+ Math.sqrt(event.getAmount()) * Balance.COMBAT_UNITS_LEVEL_SCALE;
			case LOOT_RECEIVED:
				return Math.sqrt(event.getAmount());
			case DISTANCE_TRAVELLED:
				return Math.min(event.getAmount(), Balance.MAX_TILES_PER_TICK);
			case HEALTH_RESTORED:
				return event.getAmount();

			case LEVEL_UP:
				return Balance.LEVEL_UP_UNITS;

			case DAMAGE_TAKEN:
				return event.getAmount();

			case TICK:
				return state.isIdle() ? 1d : 0d;
			default:
				return 0d;
		}
	}
	private String detailOf(DopamineEvent event, double affinity)
	{
		if (event.getType() == EventType.TICK)
		{
			return PASSIVE_DETAIL;
		}

		String key = event.getKey() == null || event.getKey().isEmpty() ? "Unknown" : event.getKey();
		return affinity > 1d ? key + " ★" : key;
	}
}
