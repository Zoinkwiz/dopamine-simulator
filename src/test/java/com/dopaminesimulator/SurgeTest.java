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

	/** A dish served and eaten, which is the only way one ever does anything. */
	private ClickState serving(GnomeFood food)
	{
		ClickState clicks = plated(food);
		if (food != null)
		{
			clicks.eat(NOW);
		}
		return clicks;
	}

	/** A dish sat in front of the player, not yet clicked. */
	private ClickState plated(GnomeFood food)
	{
		ClickState clicks = new ClickState();
		if (food != null)
		{
			clicks.serve(food, NOW);
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
	public void noDishDoesAnythingUntilItIsEaten()
	{
		for (GnomeFood food : GnomeFood.values())
		{
			ClickState clicks = plated(food);
			assertEquals(food + " must pay nothing while it sits on the plate", 1d,
				clicks.clickPayoutMultiplier(NOW), 1e-9d);
			assertTrue(food + " must not count as a running surge", !clicks.isSurging(NOW));
			assertTrue(food + " must not have soured anybody", !clicks.isSoured(NOW));
			assertEquals(food + " must be on the plate", food, clicks.getPlated(NOW));
		}
	}

	@Test
	public void aDishLeftAloneForTheWholeWindowIsLost()
	{
		for (GnomeFood food : GnomeFood.values())
		{
			ClickState clicks = plated(food);
			long last = NOW + ClickState.SERVE_WINDOW_MS - 1L;
			assertEquals(food + " must still be eatable at the last moment",
				food, clicks.getPlated(last));

			long after = NOW + ClickState.SERVE_WINDOW_MS;
			assertEquals(food + " must be off the plate", null, clicks.getPlated(after));
			assertEquals("a click after the window eats nothing", null, clicks.eat(after));
			assertTrue("so nothing runs", !clicks.isSurging(after));
			assertTrue("and nothing sours", !clicks.isSoured(after));
			assertEquals(1d, clicks.clickPayoutMultiplier(after), 1e-9d);
		}
	}

	@Test
	public void aDishThatLastsRunsFromTheClickNotFromTheServing()
	{
		ClickState clicks = plated(GnomeFood.WORM_HOLE);
		long ate = NOW + ClickState.SERVE_WINDOW_MS - 1L;
		assertEquals(GnomeFood.WORM_HOLE, clicks.eat(ate));

		long lastMs = ate + GnomeFood.WORM_HOLE.getDurationMs() - 1L;
		assertTrue("it must run its full length from the bite", clicks.isSurging(lastMs));
		assertEquals(GnomeFood.WORM_HOLE_MULTIPLIER,
			clicks.clickPayoutMultiplier(lastMs), 1e-9d);
		assertTrue("and stop there",
			!clicks.isSurging(ate + GnomeFood.WORM_HOLE.getDurationMs()));
	}

	@Test
	public void aWormBattaOnlyCostsThePlayerWhoBitesIt()
	{
		ClickState clicks = plated(GnomeFood.WORM_BATTA);
		assertEquals("the bite is what sours", GnomeFood.WORM_BATTA, clicks.eat(NOW));
		assertEquals("and it halves the click that took it",
			GnomeFood.SOUR_MULTIPLIER, clicks.clickPayoutMultiplier(NOW), 1e-9d);

		long lastSourMs = NOW + GnomeFood.SOUR_MS - 1L;
		assertTrue("the sour runs for the full minute", clicks.isSoured(lastSourMs));
		assertEquals(GnomeFood.SOUR_MULTIPLIER, clicks.incomeMultiplier(lastSourMs), 1e-9d);
		assertTrue("and then lets go", !clicks.isSoured(NOW + GnomeFood.SOUR_MS));
		assertEquals(1d, clicks.clickPayoutMultiplier(NOW + GnomeFood.SOUR_MS), 1e-9d);
	}

	@Test
	public void oneServingCanOnlyBeEatenOnce()
	{
		for (GnomeFood food : GnomeFood.values())
		{
			ClickState clicks = plated(food);
			assertEquals(food, clicks.eat(NOW));

			// A clicker gets clicked. A second click must not eat the same dish
			// again, or a trap would never let a fast clicker go.
			assertEquals(food + " was eaten twice", null, clicks.eat(NOW + 100L));
			assertTrue("the sour still ends a minute after the bite",
				!clicks.isSoured(NOW + GnomeFood.SOUR_MS));
		}
	}

	@Test
	public void aSourHalvesTheDishItIsEatenWith()
	{
		ClickState clicks = plated(GnomeFood.WORM_BATTA);
		clicks.eat(NOW);
		clicks.serve(GnomeFood.WORM_HOLE, NOW);
		clicks.eat(NOW);

		assertEquals("a worm hole eaten while soured pays half what it says",
			GnomeFood.WORM_HOLE_MULTIPLIER * GnomeFood.SOUR_MULTIPLIER,
			clicks.clickPayoutMultiplier(NOW), 1e-9d);
	}

	@Test
	public void theInstantDishesDoNotLeaveAMultiplierRunning()
	{
		for (GnomeFood food : GnomeFood.values())
		{
			// The trap has no duration either, but a sour is exactly the thing it
			// leaves behind. aWormBattaOnlyCostsThePlayerWhoBitesIt covers it.
			if (food.lasts() || food.isTrap())
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
