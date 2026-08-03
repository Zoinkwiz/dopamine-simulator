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

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DopamineEngine
{
	private final List<DopamineSystem> systems = new ArrayList<>();

    @Getter
    private final RewardQueue rewards;

	@Setter
    @Getter
    private DopamineState state;

    public DopamineEngine(DopamineState state, RewardQueue rewards)
	{
		this.state = state;
		this.rewards = rewards;
	}

	public DopamineEngine register(DopamineSystem system)
	{
		systems.add(system);
		return this;
	}

    public void accept(DopamineEvent event)
	{
		if (event.getType() == EventType.TICK)
		{
			state.setTick(state.getTick() + 1);
		}
		else if (event.getType() == EventType.XP_GAINED)
		{
			state.setLifetimeWeightedXp(state.getLifetimeWeightedXp()
				+ SkillWeights.weightedXp(event.getKey(), event.getAmount()));
		}
		for (DopamineSystem system : systems)
		{
			system.handle(state, event, rewards);
		}
	}
}
