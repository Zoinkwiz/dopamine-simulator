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
package com.dopaminesimulator;

import com.dopaminesimulator.core.DopamineEvent;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.points.ClickState;
import com.dopaminesimulator.points.GnomeFood;
import com.dopaminesimulator.points.PointSource;
import com.dopaminesimulator.systems.PointSystem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SurgeTest
{
	private static final long NOW = 1_000_000L;

	private DopamineState unlocked()
	{
		DopamineState state = new DopamineState();
		state.ensureInitialised();
		state.setLifetimePoints(10_000_000d);
		return state;
	}

	private ClickState serving(GnomeFood food)
	{
		ClickState clicks = new ClickState();
		if (food != null)
		{
			clicks.start(food, NOW);
		}
		return clicks;
	}

	/** What one event pays with the dish running. */
	private double payout(GnomeFood food, DopamineEvent event)
	{
		ClickState clicks = serving(food);
		DopamineState state = unlocked();
		new PointSystem(null, () -> clicks.incomeMultiplier(NOW))
			.handle(state, event, new RewardQueue());
		return state.getPoints();
	}

	@Test
	public void aWormHoleLiftsEverySourceIncludingClicking()
	{
		// the dish reads "All sources 4x", and clicking is the source a player is
		// actually looking at when they eat one. It used to be the only source the
		// multiplier skipped, so on a fresh profile the 4x showed up nowhere.
		assertEquals("clicking must get the same lift the dish advertises",
			GnomeFood.WORM_HOLE_MULTIPLIER,
			serving(GnomeFood.WORM_HOLE).clickPayoutMultiplier(NOW), 1e-9d);

		for (DopamineEvent event : new DopamineEvent[]{
			DopamineEvent.xp("Attack", 10_000),
			DopamineEvent.kill("Goblin", 50),
			DopamineEvent.loot("Goblin", 100_000),
			DopamineEvent.distance(10),
			DopamineEvent.healthRestored(20),
			DopamineEvent.damageTaken(20),
		})
		{
			double plain = payout(null, event);
			double fed = payout(GnomeFood.WORM_HOLE, event);
			assertEquals(PointSource.forEvent(event.getType()) + " missed the worm hole",
				GnomeFood.WORM_HOLE_MULTIPLIER, fed / plain, 1e-9d);
		}
	}

	@Test
	public void aFrenzyStaysAClickDishAndAWormHoleStaysAnEveryoneDish()
	{
		assertEquals("the frenzy pays clicks and only clicks",
			GnomeFood.FRENZY_MULTIPLIER,
			serving(GnomeFood.SPICY_CRUNCHIES).clickPayoutMultiplier(NOW), 1e-9d);
		assertEquals("and must not touch the other sources", 1d,
			serving(GnomeFood.SPICY_CRUNCHIES).incomeMultiplier(NOW), 1e-9d);
	}

	@Test
	public void theInstantDishesDoNotLeaveAMultiplierRunning()
	{
		for (GnomeFood food : GnomeFood.values())
		{
			if (food.lasts())
			{
				continue;
			}
			assertEquals(food + " pays out once, so nothing should linger", 1d,
				serving(food).clickPayoutMultiplier(NOW), 1e-9d);
		}
	}

	@Test
	public void everyDishThatLastsIsWorthSomethingWhileItRuns()
	{
		for (GnomeFood food : GnomeFood.values())
		{
			if (!food.lasts())
			{
				continue;
			}
			assertTrue(food + " runs for " + food.getDurationMs()
					+ "ms and multiplies nothing",
				serving(food).clickPayoutMultiplier(NOW) > 1d
					|| serving(food).incomeMultiplier(NOW) > 1d);
		}
	}
}
