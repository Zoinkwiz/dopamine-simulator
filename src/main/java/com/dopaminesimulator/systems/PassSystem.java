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
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.core.SkillWeights;

public class PassSystem implements DopamineSystem
{

	public static final double XP_PER_POINT = 50d;

	private static final double PER_LEVEL = 40d;

	@Override
	public String getName()
	{
		return "Battle Pass";
	}

	@Override
	public void handle(DopamineState state, DopamineEvent event, RewardQueue rewards)
	{
		switch (event.getType())
		{
			case XP_GAINED:
				state.addPassXp(SkillWeights.weightedXp(event.getKey(), event.getAmount())
					/ XP_PER_POINT);
				break;
			case LEVEL_UP:
				state.addPassXp(PER_LEVEL);
				break;
			default:
				break;
		}
	}
}
