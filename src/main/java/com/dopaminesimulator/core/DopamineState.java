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

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardCatalogue;
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Dust;
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.feats.FeatTrack;
import com.dopaminesimulator.packs.PackTier;
import com.dopaminesimulator.incremental.InsightPerk;
import com.dopaminesimulator.incremental.Perks;
import com.dopaminesimulator.points.PointSource;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DopamineState
{
	private double points;
	private double lifetimePoints;

	/**
	 * The best sustained passive income this run has reached, per hour, with food
	 * surges divided back out. This is what a click is paid from.
	 *
	 * It is a high-water mark rather than a live rate because a live rate measures
	 * the last half hour, not how strong the build is: it decays while idle and the
	 * first click back prunes the window, which left the button paying its floor of
	 * one exactly when a player returned. A build does not get weaker while nobody
	 * is looking at it. Resets on prestige, along with the income it describes.
	 */
	private double peakPassivePerHour;
	private Map<String, Integer> sourceUpgrades = new HashMap<>();

	private long tick;

	private long lastEarningTick = Long.MIN_VALUE;

	private long totalPacksOpened;

	private int packsSinceLastRare;
	private double lifetimeWeightedXp;
	private Map<String, Integer> cardCounts = new HashMap<>();

	private Map<Rarity, Integer> shards = new EnumMap<>(Rarity.class);

	private Set<String> shinyCards = new LinkedHashSet<>();

	private Set<String> gildedCards = new LinkedHashSet<>();

	private Set<CardSet> completedSets = EnumSet.noneOf(CardSet.class);

	private Map<FeatTrack, Long> featProgress = new EnumMap<>(FeatTrack.class);

	private Set<String> npcsKilled = new LinkedHashSet<>();

	private Set<String> skillsMaxed = new LinkedHashSet<>();

	private Set<String> achievements = new LinkedHashSet<>();

	private int passSeason = 1;

	private double passXp;

	private boolean passPremium;

	private Set<Integer> passClaimedFree = new LinkedHashSet<>();

	private Set<Integer> passClaimedPremium = new LinkedHashSet<>();

	private int wildcards;

	private long dust;

	private Map<String, Integer> ascensions = new HashMap<>();

	private Set<String> unlockedBacks = new LinkedHashSet<>();

	private String selectedBack = "STANDARD";

	private Map<Rarity, String> bannerCards = new EnumMap<>(Rarity.class);

	private Map<Rarity, Integer> bannerPityByRarity = new EnumMap<>(Rarity.class);

	private int bannerPulls;

	private int passSeasonKey;

	private int bannerKey;

	private int insight;

	private Map<String, Integer> perkRanks = new LinkedHashMap<>();

	private int prestigeCount;

	public void ensureInitialised()
	{
		if (sourceUpgrades == null)
		{
			sourceUpgrades = new HashMap<>();
		}
		if (shinyCards == null)
		{
			shinyCards = new LinkedHashSet<>();
		}
		if (gildedCards == null)
		{
			gildedCards = new LinkedHashSet<>();
		}
		if (cardCounts == null)
		{
			cardCounts = new HashMap<>();
		}
		if (shards == null)
		{
			shards = new EnumMap<>(Rarity.class);
		}
		migrateToDust();
		if (ascensions == null)
		{
			ascensions = new HashMap<>();
		}
		if (completedSets == null)
		{
			completedSets = EnumSet.noneOf(CardSet.class);
		}
		if (featProgress == null)
		{
			featProgress = new EnumMap<>(FeatTrack.class);
		}
		if (npcsKilled == null)
		{
			npcsKilled = new LinkedHashSet<>();
		}
		if (skillsMaxed == null)
		{
			skillsMaxed = new LinkedHashSet<>();
		}
		if (achievements == null)
		{
			achievements = new LinkedHashSet<>();
		}
		if (passClaimedFree == null)
		{
			passClaimedFree = new LinkedHashSet<>();
		}
		if (passClaimedPremium == null)
		{
			passClaimedPremium = new LinkedHashSet<>();
		}
		if (passSeason < 1)
		{
			passSeason = 1;
		}
		if (featProgress != null)
		{

			featProgress.remove(null);
		}
		if (perkRanks == null)
		{
			perkRanks = new LinkedHashMap<>();
		}
		if (unlockedBacks == null)
		{
			unlockedBacks = new LinkedHashSet<>();
		}
		if (bannerCards == null)
		{
			bannerCards = new EnumMap<>(Rarity.class);
		}
		if (bannerPityByRarity == null)
		{
			bannerPityByRarity = new EnumMap<>(Rarity.class);
		}
		unlockedBacks.add("STANDARD");
		if (selectedBack == null || selectedBack.isEmpty())
		{
			selectedBack = "STANDARD";
		}

		// Must stay below every null guard above, or an older save fails to load.
		cardCounts = new ConcurrentHashMap<>(cardCounts);
		sourceUpgrades = new ConcurrentHashMap<>(sourceUpgrades);
		ascensions = new ConcurrentHashMap<>(ascensions);
		perkRanks = new ConcurrentHashMap<>(perkRanks);
		shinyCards = concurrentSet(shinyCards);
		gildedCards = concurrentSet(gildedCards);
	}

	public boolean unlockBack(String id)
	{
		return unlockedBacks.add(id);
	}

	public boolean hasBack(String id)
	{
		return unlockedBacks.contains(id);
	}

	public void prestige(int runInsight)
	{
		insight = Math.max(insight, runInsight);
		prestigeCount++;
		points = 0d;
		lifetimePoints = 0d;
		peakPassivePerHour = 0d;
		sourceUpgrades.clear();

		// The reset is the only rethink there is. Handing every rank back here is
		// what stops a rank being worn for a discount and then taken off again.
		respec();
	}

	// Only ever raises, so a rank bought partway through a run tops the run up
	// rather than dragging it back to what a fresh one would have started with.
	public void applyHeadStart()
	{
		points = Math.max(points, Perks.seedPoints(this));
		int levels = Perks.headStartLevels(this);
		if (levels > 0)
		{
			for (PointSource source : PointSource.values())
			{
				if (source != PointSource.CLICK)
				{
					raiseUpgradeTo(source, levels);
				}
			}
		}
		int clicks = Perks.headStartClickLevels(this);
		if (clicks > 0)
		{
			raiseUpgradeTo(PointSource.CLICK, clicks);
		}
	}

	private void raiseUpgradeTo(PointSource source, int level)
	{
		if (getSourceUpgradeLevel(source) < level)
		{
			sourceUpgrades.put(source.name(), level);
		}
	}

	private static Set<String> concurrentSet(Set<String> from)
	{
		Set<String> out = ConcurrentHashMap.newKeySet();
		out.addAll(from);
		return out;
	}

	public int getPerkRank(InsightPerk perk)
	{
		return perkRanks == null ? 0 : perkRanks.getOrDefault(perk.name(), 0);
	}

	public int getSpentInsight()
	{
		int spent = 0;
		for (InsightPerk perk : InsightPerk.values())
		{
			spent += getPerkRank(perk) * perk.getCostPerRank();
		}
		return spent;
	}

	public int getFreeInsight()
	{
		return Math.max(0, insight - getSpentInsight());
	}

	public boolean allocate(InsightPerk perk)
	{
		int rank = getPerkRank(perk);
		if (rank >= perk.getMaxRanks() || getFreeInsight() < perk.getCostPerRank())
		{
			return false;
		}
		perkRanks.put(perk.name(), rank + 1);

		// A head start bought a moment after resetting has to arrive at once, or
		// the rank reads as doing nothing until the run after next.
		applyHeadStart();
		return true;
	}

	public void respec()
	{
		perkRanks.clear();
	}

	public String getBannerCard(Rarity rarity)
	{
		return bannerCards.get(rarity);
	}

	public void setBannerCard(Rarity rarity, String cardId)
	{
		bannerCards.put(rarity, cardId);
	}

	public int getBannerPity(Rarity rarity)
	{
		Integer value = bannerPityByRarity.get(rarity);
		return value == null ? 0 : value;
	}

	public void setBannerPity(Rarity rarity, int value)
	{
		bannerPityByRarity.put(rarity, value);
	}

	private void migrateToDust()
	{
		int carried = 0;
		for (Rarity rarity : Rarity.values())
		{
			carried += getShards(rarity);
		}
		if (carried > 0)
		{
			shards.clear();
		}
		if (wildcards > 0)
		{
			carried += wildcards * Dust.PER_WILDCARD;
			wildcards = 0;
		}
		dust += carried;
	}

	public int getAscension(String collection)
	{
		return ascensions.getOrDefault(collection, 0);
	}

	// Every ascension ever, across every collection. Drives the ascendant rotation.
	public int getTotalAscensions()
	{
		int total = 0;
		for (Integer level : ascensions.values())
		{
			total += level == null ? 0 : level;
		}
		return total;
	}

	public void ascend(String collection)
	{
		ascensions.merge(collection, 1, Integer::sum);
	}

	public void clearCopies(String cardId)
	{
		invalidate(cardId);
		cardCounts.remove(cardId);
	}

	public void addDust(long amount)
	{
		if (amount > 0)
		{
			dust += amount;
		}
	}

	public boolean spendDust(long amount)
	{
		if (amount <= 0 || dust < amount)
		{
			return false;
		}
		dust -= amount;
		return true;
	}

	public boolean spendShards(Rarity rarity, int amount)
	{
		if (getShards(rarity) < amount)
		{
			return false;
		}
		shards.put(rarity, getShards(rarity) - amount);
		return true;
	}

	public void addWildcards(int amount)
	{
		if (amount > 0)
		{
			wildcards += amount;
		}
	}

	public void addPassXp(double amount)
	{
		if (amount > 0d)
		{
			passXp += amount;
		}
	}

	public boolean isPassTierClaimed(int tier, boolean premium)
	{
		return (premium ? passClaimedPremium : passClaimedFree).contains(tier);
	}

	public boolean claimPassTier(int tier, boolean premium)
	{
		return (premium ? passClaimedPremium : passClaimedFree).add(tier);
	}

	public void startNextSeason()
	{
		passSeason++;
		passXp = 0d;
		passPremium = false;
		passClaimedFree.clear();
		passClaimedPremium.clear();
	}

	public boolean hasAchievement(String id)
	{
		return achievements.contains(id);
	}

	public boolean awardAchievement(String id)
	{
		return achievements.add(id);
	}

	public void resetFeats()
	{
		featProgress.clear();
		npcsKilled.clear();
		skillsMaxed.clear();
		achievements.clear();
	}

	public long getFeatProgress(FeatTrack track)
	{
		Long value = featProgress.get(track);
		return value == null ? 0L : value;
	}

	public void addFeatProgress(FeatTrack track, long amount)
	{
		if (amount <= 0L)
		{
			return;
		}
		featProgress.merge(track, amount, Long::sum);
	}

	public void raiseFeatProgress(FeatTrack track, long value)
	{
		if (value > getFeatProgress(track))
		{
			featProgress.put(track, value);
		}
	}

	public boolean recordNpcKilled(String name)
	{
		if (name == null || name.isEmpty())
		{
			return false;
		}
		return npcsKilled.add(name);
	}

	public boolean recordSkillMaxed(String skill)
	{
		return skill != null && !skill.isEmpty() && skillsMaxed.add(skill);
	}
	public boolean isIdle()
	{
		return lastEarningTick == Long.MIN_VALUE
			|| tick - lastEarningTick >= Balance.IDLE_AFTER_TICKS;
	}

	public void addPoints(double amount)
	{
		if (amount <= 0d)
		{
			return;
		}
		points += amount;
		lifetimePoints += amount;
	}
	public boolean spendPoints(double cost)
	{
		if (cost > points)
		{
			return false;
		}
		points -= cost;
		return true;
	}
	public int getSourceUpgradeLevel(PointSource source)
	{
		return sourceUpgrades.getOrDefault(source.name(), 0);
	}
	public void addSourceUpgrades(PointSource source, int levels)
	{
		sourceUpgrades.merge(source.name(), levels, Integer::sum);
	}

	public boolean isSourceUnlocked(PointSource source)
	{
		return source.isUnlockedAt(lifetimePoints);
	}

	public PointSource nextLockedSource()
	{
		for (PointSource source : PointSource.values())
		{
			if (!isSourceUnlocked(source))
			{
				return source;
			}
		}
		return null;
	}
	public boolean isPackUnlocked(PackTier tier)
	{
		return tier.isUnlockedAt(lifetimePoints);
	}

	public int getCopies(String cardId)
	{
		return cardCounts.getOrDefault(cardId, 0);
	}
	public boolean owns(String cardId)
	{
		return getCopies(cardId) > 0;
	}
	public void addCopy(String cardId)
	{
		addCopies(cardId, 1);
	}

	public void addCopies(String cardId, int copies)
	{
		invalidate(cardId);
		if (copies > 0)
		{
			cardCounts.merge(cardId, copies, Integer::sum);
		}
	}

	public int getShards(Rarity rarity)
	{
		return shards.getOrDefault(rarity, 0);
	}
	public void addShards(Rarity rarity, int count)
	{
		shards.merge(rarity, count, Integer::sum);
	}

	public int getUniqueCardsOwned()
	{
		return (int) cardCounts.values().stream().filter(c -> c != null && c > 0).count();
	}
	public int getStars(String cardId)
	{
		Card card = CardCatalogue.byId(cardId);
		if (card == null)
		{
			return 0;
		}

		if (card.getSet().isUnlockSet())
		{
			return getCopies(cardId) > 0 ? 1 : 0;
		}
		return card.getRarity().starsFor(getCopies(cardId));
	}
	public int getTotalStars()
	{
		int total = 0;
		for (Card card : CardCatalogue.all())
		{
			total += getStars(card.getId());
		}
		return total;
	}
	public int getStarsInSet(CardSet set)
	{
		int total = 0;
		for (Card card : CardCatalogue.bySet(set))
		{
			total += starValue(card.getId());
		}
		return total;
	}

	public long getWeightedStarsInSet(CardSet set)
	{
		if (weightedStars == null)
		{
			weightedStars = new EnumMap<>(CardSet.class);
		}
		Long cached = weightedStars.get(set);
		if (cached != null)
		{
			return cached;
		}
		long total = 0;
		for (Card card : CardCatalogue.bySet(set))
		{
			total += (long) starValue(card.getId()) * card.getRarity().starWeight();
		}
		weightedStars.put(set, total);
		return total;
	}

	private transient Map<CardSet, Long> weightedStars;

	private void invalidate(String cardId)
	{
		if (weightedStars == null)
		{
			return;
		}
		Card card = CardCatalogue.byId(cardId);
		if (card != null)
		{
			weightedStars.remove(card.getSet());
		}
	}

	public int starValue(String cardId)
	{
		int stars = getStars(cardId);
		if (isShiny(cardId))
		{
			stars *= Balance.SHINY_STAR_MULTIPLIER;
		}
		if (isGilded(cardId))
		{
			stars += stars * Balance.GILDED_STAR_BONUS_PERCENT / 100;
		}
		return stars;
	}

	public boolean isGilded(String cardId)
	{
		return gildedCards.contains(cardId);
	}

	public boolean makeGilded(String cardId)
	{
		invalidate(cardId);
		return gildedCards.add(cardId);
	}

	public int getGildedCount()
	{
		return gildedCards.size();
	}

	public boolean isShiny(String cardId)
	{
		return shinyCards.contains(cardId);
	}

	public boolean makeShiny(String cardId)
	{
		invalidate(cardId);
		return shinyCards.add(cardId);
	}

	public int getShinyCount()
	{
		return shinyCards.size();
	}
	public boolean isSetComplete(CardSet set)
	{
		return CardCatalogue.bySet(set).stream().map(Card::getId).allMatch(this::owns);
	}
}
