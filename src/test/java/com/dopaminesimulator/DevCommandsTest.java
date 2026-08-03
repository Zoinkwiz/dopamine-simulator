package com.dopaminesimulator;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardCatalogue;
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.incremental.BigNumbers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The two dev commands are typed, not clicked, so the risk is in what they read
 * off the chat line and in the copies-to-stars arithmetic behind ::completecards.
 */
public class DevCommandsTest
{
	@Test
	public void adddopamineReadsWhatTheGameWrites()
	{
		for (double value : new double[]{1d, 999d, 1_500d, 2_500_000d, 7.5e9, 1.2e15})
		{
			double parsed = BigNumbers.parse(BigNumbers.format(value));
			assertTrue("format/parse must round-trip " + value + ", got " + parsed,
				Math.abs(parsed - value) / value < 0.01d);
		}

		assertEquals(2_500_000d, BigNumbers.parse("2.5m"), 1e-9d);
		assertEquals(2_500_000d, BigNumbers.parse("2.5M"), 1e-9d);
		assertEquals(1_000d, BigNumbers.parse(" 1k "), 1e-9d);
		assertEquals(1_000_000d, BigNumbers.parse("1,000,000"), 1e-9d);
		assertEquals(1_000_000d, BigNumbers.parse("1e6"), 1e-9d);
		assertEquals(5e15, BigNumbers.parse("5Qa"), 1e-9d);
	}

	@Test
	public void adddopamineRefusesWhatIsNotANumber()
	{
		for (String text : new String[]{"", "  ", "m", "K", "lots", "1.2.3", null})
		{
			assertTrue(text + " must not parse", Double.isNaN(BigNumbers.parse(text)));
		}
	}

	@Test
	public void completecardsAsksForExactlyTheCopiesEachStarNeeds()
	{
		for (Rarity rarity : Rarity.values())
		{
			for (int stars = 1; stars <= Rarity.MAX_STARS; stars++)
			{
				int copies = rarity.copiesForStars(stars);
				assertEquals(rarity + " at " + copies + " copies", stars,
					rarity.starsFor(copies));
				assertTrue("one fewer copy must be one fewer star",
					rarity.starsFor(copies - 1) < stars);
			}
			assertEquals(rarity.copiesForStars(Rarity.MAX_STARS), rarity.copiesForMaxStars());
		}
	}

	/** What ::completecards does to a save, run over the real catalogue. */
	@Test
	public void completecardsFillsEverySetToTheStarsAskedFor()
	{
		DopamineState state = new DopamineState();
		state.ensureInitialised();

		for (int stars : new int[]{1, Rarity.MAX_STARS})
		{
			for (Card card : CardCatalogue.all())
			{
				int wanted = card.getSet().isUnlockSet()
					? 1 : card.getRarity().copiesForStars(stars);
				int missing = wanted - state.getCopies(card.getId());
				if (missing > 0)
				{
					state.addCopies(card.getId(), missing);
				}
			}

			for (Card card : CardCatalogue.all())
			{
				int expected = card.getSet().isUnlockSet() ? 1 : stars;
				assertEquals(card.getId() + " should sit at " + expected + " stars",
					expected, state.getStars(card.getId()));
			}
		}

		assertEquals("every card in the catalogue must be owned",
			CardCatalogue.all().size(), state.getUniqueCardsOwned());
	}
}
