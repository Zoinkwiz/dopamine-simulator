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

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardCatalogue;
import com.dopaminesimulator.cards.CardOrigin;
import com.dopaminesimulator.cards.CardOrigins;
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.NpcCardArt;
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.cards.Region;
import com.dopaminesimulator.core.Balance;
import com.dopaminesimulator.core.DopamineEngine;
import com.dopaminesimulator.core.DopamineEvent;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.ui.CardRenderer;
import com.dopaminesimulator.feats.FeatTrack;
import com.dopaminesimulator.feats.Feat;
import com.dopaminesimulator.feats.Feats;
import com.dopaminesimulator.incremental.BigNumbers;
import com.dopaminesimulator.incremental.InsightPerk;
import com.dopaminesimulator.incremental.Perks;
import com.dopaminesimulator.incremental.Prestige;
import java.util.function.Consumer;
import java.util.function.Function;
import com.dopaminesimulator.core.IncomeTracker;
import com.dopaminesimulator.core.PointListener;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.core.RewardType;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.packs.PackTier;
import com.dopaminesimulator.points.ClickState;
import com.dopaminesimulator.points.GnomeFood;
import com.dopaminesimulator.points.PointSource;
import com.dopaminesimulator.systems.CollectionService;
import com.dopaminesimulator.systems.BannerService;
import com.dopaminesimulator.systems.GnomeFoodService;
import com.dopaminesimulator.systems.PackService;
import com.dopaminesimulator.systems.PassService;
import com.dopaminesimulator.systems.PassSystem;
import com.dopaminesimulator.systems.AchievementSystem;
import com.dopaminesimulator.systems.FeatSystem;
import com.dopaminesimulator.systems.PointSystem;
import com.dopaminesimulator.ui.CardArtService;
import com.dopaminesimulator.ui.GameIcons;
import com.dopaminesimulator.ui.ModelLab;
import com.google.inject.Provides;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.inject.Inject;
import javax.swing.SwingUtilities;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Hitsplat;
import net.runelite.api.HitsplatID;
import net.runelite.api.Player;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.api.events.StatChanged;
import com.dopaminesimulator.cards.CardCollection;
import com.dopaminesimulator.cards.Dust;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.ItemStack;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.loottracker.LootReceived;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.AsyncBufferedImage;
import net.runelite.client.util.ImageUtil;
import net.runelite.http.api.loottracker.LootRecordType;

@Slf4j
@PluginDescriptor(
	name = "Dopamine Simulator",
	description = "Collect cards and buy upgrades as you play",
	tags = {"cards", "collection", "progression", "packs", "meta", "tcg", "idle"}
)
public class DopamineSimulatorPlugin extends Plugin
{
	private static final int SAVE_INTERVAL_TICKS = 100;

	private static final int CROSS_REGION_SENTINEL = 100;

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private SaveManager saveManager;

	private static final long RESET_CONFIRM_MS = 30_000L;

	private long resetRequestedAt;

	private String pendingResetCommand;

	private final AchievementSystem achievementSystem = new AchievementSystem();

	@Inject
	private DopamineSimulatorConfig config;

	@Inject
	private RevealSoundService revealSounds;

	@Inject
	private net.runelite.client.input.MouseManager mouseManager;

	@Inject
	@Getter
	private CardArtService cardArtService;

	@Inject
	@Getter
	private GameIcons gameIcons;

	@Getter
	private DopamineEngine engine;

	@Getter
	private RewardQueue rewards;

	@Getter
	private CollectionService collection;

	@Getter
	private PackService packService;
	private GnomeFoodService foodService;

	@Getter
	private PassService passService;

	@Getter
	private BannerService bannerService;

	@Getter
	private IncomeTracker incomeTracker;

	@Getter
	private ClickState clickState;

	private DopamineSimulatorPanel panel;
	private NavigationButton navButton;
	private DopamineOverlay overlay;
	private PackRevealOverlay revealOverlay;
	private FloatingTextOverlay floatingTextOverlay;
	private SurgeInfoBox surgeInfoBox;

	private ModelLab modelLab;

	private CardViewerOverlay cardViewer;

	private CardSceneOverlay cardScene;

	private final net.runelite.client.input.MouseAdapter viewerDismiss =
		new net.runelite.client.input.MouseAdapter()
		{
			@Override
			public java.awt.event.MouseEvent mousePressed(java.awt.event.MouseEvent event)
			{
				if (cardViewer == null || !cardViewer.isOpen())
				{
					return event;
				}

				if (cardViewer.containsCanvas(event.getX(), event.getY()))
				{
					if (javax.swing.SwingUtilities.isRightMouseButton(event))
					{
						cardViewer.flip();
					}
					else
					{
						cardViewer.click();
					}
				}
				else
				{
					cardViewer.close();
					mouseManager.unregisterMouseListener(this);
				}
				event.consume();
				return event;
			}
		};

	private void closeCardViewer()
	{
		if (cardViewer != null && cardViewer.isOpen())
		{
			cardViewer.close();
			mouseManager.unregisterMouseListener(viewerDismiss);
		}
	}

	public void openCardViewer(Card card)
	{
		if (cardViewer == null || card == null || engine == null)
		{
			return;
		}
		DopamineState state = engine.getState();
		cardViewer.show(card, state.getStars(card.getId()),
			state.isShiny(card.getId()), state.isGilded(card.getId()));

		mouseManager.unregisterMouseListener(viewerDismiss);
		mouseManager.registerMouseListener(viewerDismiss);
	}

	private final Random random = new Random();

	private final Map<Skill, Integer> lastXp = new EnumMap<>(Skill.class);
	private final Map<Skill, Integer> lastLevel = new EnumMap<>(Skill.class);
	private final Set<PointSource> announcedSources = EnumSet.noneOf(PointSource.class);

	private WorldPoint lastLocation;
	private int lastHitpoints = -1;

	private long loadedAccountHash = Long.MIN_VALUE;
	private int ticksSinceSave;

	@Provides
	DopamineSimulatorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DopamineSimulatorConfig.class);
	}
	@Override
	protected void startUp()
	{
		rewards = new RewardQueue();
		incomeTracker = new IncomeTracker();
		clickState = new ClickState();
		collection = new CollectionService();
		packService = new PackService(random, collection);
		foodService = new GnomeFoodService(random, packService);
		passService = new PassService(random, packService, collection);
		bannerService = new BannerService(random, packService, collection);

		floatingTextOverlay = new FloatingTextOverlay(client, config, gameIcons);
		modelLab = new ModelLab(client);
		PointListener listeners = (source, detail, amount, tick) ->
		{
			incomeTracker.onPointsGained(source, detail, amount, tick);
			floatingTextOverlay.onPointsGained(source, detail, amount, tick);
		};
		engine = new DopamineEngine(SaveManager.freshState(), rewards)
			.register(new PointSystem(listeners,
				() -> clickState == null ? 1d : clickState.incomeMultiplier(System.currentTimeMillis())))
			.register(new FeatSystem())
			.register(achievementSystem)
			.register(new PassSystem());
		panel = new DopamineSimulatorPanel(this, config);
		navButton = NavigationButton.builder()
			.tooltip("Dopamine Simulator")
			.icon(buildIcon())
			.priority(7)
			.panel(panel)
			.build();
		clientToolbar.addNavigation(navButton);
		overlay = new DopamineOverlay(this, config);
		overlayManager.add(overlay);

		cardScene = new CardSceneOverlay();
		overlayManager.add(cardScene);

		revealOverlay = new PackRevealOverlay(client, config, revealSounds, cardArtService,
			() -> engine == null ? SaveManager.freshState() : engine.getState(), cardScene);
		overlayManager.add(revealOverlay);
		CardRenderer.setCopiesLookup(cardId -> engine == null ? 0
			: engine.getState().getCopies(cardId));
		cardViewer = new CardViewerOverlay(client, cardArtService, cardScene);
		overlayManager.add(cardViewer);

		revealOverlay.setOnRevealStart(this::closeCardViewer);
		overlayManager.add(floatingTextOverlay);
		surgeInfoBox = new SurgeInfoBox(buildIcon(), this, config, clickState);
		infoBoxManager.addInfoBox(surgeInfoBox);

		clientThread.invokeLater(() ->
		{
			gameIcons.warm(this::refreshPanel);
			applyPluginIcon();
		});

		rewards.addListener(queue -> refreshPanel());
		clientThread.invokeLater(this::loadStateForCurrentAccount);
	}
	@Override
	protected void shutDown()
	{
		persist();
		overlayManager.remove(overlay);
		overlayManager.remove(revealOverlay);
		overlayManager.remove(floatingTextOverlay);
		infoBoxManager.removeInfoBox(surgeInfoBox);
		clientToolbar.removeNavigation(navButton);
		rewards.clearListeners();
		if (modelLab != null)
		{
			modelLab.close();
			modelLab = null;
		}
		revealOverlay.dispose();
		mouseManager.unregisterMouseListener(viewerDismiss);
		if (cardViewer != null)
		{
			overlayManager.remove(cardViewer);
			cardViewer.dispose();
			cardViewer = null;
		}
		if (cardScene != null)
		{
			overlayManager.remove(cardScene);
			cardScene = null;
		}
		if (panel != null)
		{
			panel.dispose();
		}
		resetTracking();
		loadedAccountHash = Long.MIN_VALUE;
		panel = null;
		overlay = null;
		revealOverlay = null;
		floatingTextOverlay = null;
		surgeInfoBox = null;
		incomeTracker = null;
		clickState = null;
		engine = null;
		rewards = null;
	}
	private void resetTracking()
	{
		lastXp.clear();
		lastLevel.clear();
		announcedSources.clear();
		lastLocation = null;
		lastHitpoints = -1;
	}
	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (modelLab != null)
		{
			modelLab.tick();
		}
		if (engine == null)
		{
			return;
		}
		engine.accept(DopamineEvent.tick());
		trackPeakIncome();
		rollSeasons();
		trackMovement();
		trackHealth();
		rollForSurge();
		checkSourceUnlocks();
		floatingTextOverlay.flushPending();
		if (++ticksSinceSave >= SAVE_INTERVAL_TICKS)
		{
			ticksSinceSave = 0;
			persist();
		}
	}
	private void trackMovement()
	{
		Player local = client.getLocalPlayer();
		if (local == null)
		{
			return;
		}
		WorldPoint current = local.getWorldLocation();
		if (current == null)
		{
			return;
		}
		if (lastLocation != null && lastLocation.getPlane() == current.getPlane())
		{
			int tiles = lastLocation.distanceTo(current);
			if (tiles > 0 && tiles < CROSS_REGION_SENTINEL)
			{
				int stepped = Math.min(tiles, (int) Balance.MAX_TILES_PER_TICK);

				engine.accept(DopamineEvent.distance(stepped));
			}
		}
		lastLocation = current;
	}
	private void trackHealth()
	{
		int current = client.getBoostedSkillLevel(Skill.HITPOINTS);
		if (lastHitpoints >= 0 && current > lastHitpoints)
		{
			engine.accept(DopamineEvent.healthRestored(current - lastHitpoints));
		}

		lastHitpoints = current;
	}

	private void rollForSurge()
	{
		DopamineState state = engine.getState();
		long now = System.currentTimeMillis();

		if (state.getLifetimePoints() < ClickState.SURGE_UNLOCK_AT
			|| clickState.isSurging(now) || clickState.isPlated(now))
		{
			return;
		}

		if (random.nextDouble() >= ClickState.surgeChancePerTick(
			state.getSourceUpgradeLevel(PointSource.CLICK), Perks.dishRate(state)))
		{
			return;
		}

		serve(GnomeFood.roll(random));
	}

	private void serve(GnomeFood food)
	{
		clickState.serve(food, System.currentTimeMillis());

		long seconds = ClickState.SERVE_WINDOW_MS / 1000L;
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			food.isTrap()
				? "<col=ff4040>" + food.getDisplayName() + "</col>! " + food.getBlurb()
					+ ". Leave it alone."
				: "<col=ffb300>" + food.getDisplayName() + "</col>! " + food.getBlurb()
					+ ", if you click it within " + seconds + "s.", null);
		refreshPanel();
	}

	private void eat(long now)
	{
		GnomeFood food = clickState == null ? null : clickState.eat(now);
		if (food == null)
		{
			return;
		}

		if (food.isTrap())
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"<col=ff4040>You bite the " + food.getDisplayName().toLowerCase(Locale.ROOT)
					+ "</col>. Everything pays half for "
					+ GnomeFood.SOUR_MS / 1000L + " seconds.", null);
			return;
		}

		DopamineState state = engine.getState();
		long tick = state.getTick();
		double others = Math.max(0d,
			incomeTracker.totalPerHour(tick) - incomeTracker.perHour(PointSource.CLICK, tick));
		String got = foodService.apply(state, food, others, clickPayout(), rewards);
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			"You eat the <col=ffb300>" + food.getDisplayName().toLowerCase(Locale.ROOT)
				+ "</col>. " + (got == null ? food.getBlurb() : got) + ".", null);
	}

	private void checkSourceUnlocks()
	{
		DopamineState state = engine.getState();
		for (PointSource source : PointSource.values())
		{
			if (state.isSourceUnlocked(source) && announcedSources.add(source))
			{
				if (state.getLifetimePoints() - source.getUnlockAtLifetimePoints() < 5_000d)
				{
					rewards.push(Reward.sourceUnlocked(source));
				}
			}
		}
	}

	@Subscribe
	public void onStatChanged(StatChanged event)
	{
		if (engine == null)
		{
			return;
		}
		Skill skill = event.getSkill();
		Integer previousLevel = lastLevel.put(skill, event.getLevel());
		if (previousLevel != null && event.getLevel() > previousLevel)
		{
			engine.accept(DopamineEvent.levelUp(skill.name(), event.getLevel()));
		}
		syncSkillFeats();
		Integer previousXp = lastXp.put(skill, event.getXp());
		if (previousXp == null || event.getXp() <= previousXp)
		{
			return;
		}
		engine.accept(DopamineEvent.xp(skill.name(), event.getXp() - previousXp));
		refreshPanel();
	}

	private void syncSkillFeats()
	{
		if (engine == null || client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}
		int climberBefore = Feats.tierOf(engine.getState(), Feat.CLIMBER);
		int maxedBefore = Feats.tierOf(engine.getState(), Feat.MAXED);
		long totalXp = 0L;
		int maxed = 0;
		for (Skill skill : Skill.values())
		{
			if (skill == Skill.OVERALL)
			{
				continue;
			}
			totalXp += client.getSkillExperience(skill);
			if (client.getRealSkillLevel(skill) >= MAX_SKILL_LEVEL)
			{
				maxed++;
			}
		}
		DopamineState state = engine.getState();
		state.raiseFeatProgress(FeatTrack.TOTAL_XP, totalXp);
		state.raiseFeatProgress(FeatTrack.SKILLS_MAXED, maxed);

		announceRank(Feat.CLIMBER, climberBefore);
		announceRank(Feat.MAXED, maxedBefore);
	}

	private void announceRank(Feat feat, int before)
	{
		int now = Feats.tierOf(engine.getState(), feat);
		if (now > before)
		{
			rewards.push(Reward.feat(feat, now));
		}
	}

	private static final int MAX_SKILL_LEVEL = 99;
	@Subscribe
	public void onLootReceived(LootReceived event)
	{
		if (engine == null)
		{
			return;
		}

		if (event.getType() == LootRecordType.NPC || event.getType() == LootRecordType.PLAYER)
		{
			engine.accept(DopamineEvent.kill(event.getName(), event.getCombatLevel()));
		}
		long value = 0;
		if (event.getItems() != null)
		{
			for (ItemStack item : event.getItems())
			{
				value += (long) itemManager.getItemPrice(item.getId()) * item.getQuantity();
			}
		}
		if (value > 0)
		{
			engine.accept(DopamineEvent.loot(event.getName(), value));
		}
		refreshPanel();
	}
	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event)
	{
		if (!isPlayable() || event.getActor() != client.getLocalPlayer())
		{
			return;
		}

		Hitsplat hitsplat = event.getHitsplat();
		int amount = hitsplat.getAmount();
		if (amount <= 0 || !isDamage(hitsplat.getHitsplatType()))
		{
			return;
		}

		engine.accept(DopamineEvent.damageTaken(amount));
	}

	private static boolean isDamage(int hitsplatType)
	{
		switch (hitsplatType)
		{
			case HitsplatID.DAMAGE_ME:
			case HitsplatID.DAMAGE_ME_CYAN:
			case HitsplatID.DAMAGE_ME_ORANGE:
			case HitsplatID.DAMAGE_ME_YELLOW:
			case HitsplatID.DAMAGE_ME_WHITE:
			case HitsplatID.POISON:
			case HitsplatID.VENOM:
				return true;
			default:
				return false;
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (engine == null)
		{
			return;
		}
		switch (event.getGameState())
		{
			case LOGGING_IN:
			case HOPPING:
				resetTracking();
				break;
			case LOGGED_IN:
				loadStateForCurrentAccount();
				break;
			case LOGIN_SCREEN:
				persist();
				resetTracking();
				break;
			default:
				break;
		}

		refreshPanel();
	}
	public boolean isPlayable()
	{
		return engine != null && client.getGameState() == GameState.LOGGED_IN;
	}

	private void trackPeakIncome()
	{
		raisePeak(engine.getState(), incomeTracker, clickState == null
			? 1d : clickState.incomeMultiplier(System.currentTimeMillis()));
	}

	static void raisePeak(DopamineState state, IncomeTracker tracker, double foodMultiplier)
	{
		long tick = state.getTick();
		if (!tracker.hasSettled(tick))
		{
			return;
		}

		double live = Math.max(0d,
			tracker.totalPerHour(tick) - tracker.perHour(PointSource.CLICK, tick));

		double sustained = foodMultiplier <= 0d ? live : live / foodMultiplier;
		if (sustained > state.getPeakPassivePerHour())
		{
			state.setPeakPassivePerHour(sustained);
		}
	}

	public double clickPayout()
	{
		if (!isPlayable())
		{
			return 1d;
		}
		double others = engine.getState().getPeakPassivePerHour();
		double surge = clickState == null
			? 1d : clickState.clickPayoutMultiplier(System.currentTimeMillis());
		return Math.max(1d, PointSource.CLICK_COEFFICIENT
			* Math.pow(others, PointSource.CLICK_EXPONENT)) * surge;
	}

	public void click()
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable())
			{
				return;
			}

			eat(System.currentTimeMillis());
			engine.accept(DopamineEvent.click(clickPayout()));
			refreshPanel();
		});
	}
	public void buyPacks(PackTier tier, CardSet targetSet, int count)
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable() || count < 1)
			{
				return;
			}
			if (count == 1)
			{
				for (Card card : packService.buy(engine.getState(), tier, targetSet, rewards))
				{
					announce(card);
				}
				persist();
				refreshPanel();
				return;
			}
			RewardQueue batch = new RewardQueue();
			List<Card> pulled = packService.buyMany(engine.getState(), tier, targetSet, count, batch);
			if (pulled.isEmpty())
			{
				refreshPanel();
				return;
			}
			PackRevealOverlay reveal = revealOverlay;
			if (reveal != null)
			{
				reveal.makeWayForBatch();
			}

			revealHighlights(batch.claimAll());
			summarise(tier, pulled);
			persist();
			refreshPanel();
		});
	}

	private static final int BULK_HIGHLIGHTS = 10;
	private void revealHighlights(List<Reward> batch)
	{
		batch.sort(Comparator.comparingInt(DopamineSimulatorPlugin::significance).reversed());
		List<Reward> highlights = batch.size() > BULK_HIGHLIGHTS
			? batch.subList(0, BULK_HIGHLIGHTS)
			: batch;

		PackRevealOverlay reveal = revealOverlay;
		if (reveal != null)
		{
			reveal.pushBatch(highlights);
		}
		for (Reward reward : highlights)
		{
			if (reward.getCard() != null)
			{
				announce(reward.getCard());
			}
		}
	}

	static int significance(Reward reward)
	{
		int rarity = reward.getRarity() == null ? 0 : reward.getRarity().ordinal();
		switch (reward.getType())
		{
			case BANNER_WIN:
				return 2000;
			case SET_COMPLETE:
				return 1000;
			case NEW_CARD:
				return 500 + rarity;
			case FUSION:
				return 400 + rarity;
			case STAR_UP:
				return 200 + rarity;
			default:
				return rarity;
		}
	}
	private void summarise(PackTier tier, List<Card> pulled)
	{
		if (!config.chatMessageOnNewCard())
		{
			return;
		}
		int packs = Math.max(1, pulled.size() / Math.max(1, tier.getCardCount()));
		long best = pulled.stream()
			.filter(c -> c.getRarity().ordinal() >= Rarity.EPIC.ordinal())
			.count();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			"Opened <col=ffb300>" + packs + "</col> " + tier.getDisplayName() + " packs: "
				+ pulled.size() + " cards, <col=ffb300>" + best + "</col> epic or better.", null);
	}

	public static int maxLevelOf(PointSource source)
	{
		return source == PointSource.CLICK
			? ClickState.MAX_LEVEL : PointSource.MAX_UPGRADE_LEVEL;
	}

	public void buySourceUpgrade(PointSource source, int levels)
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable())
			{
				return;
			}
			DopamineState state = engine.getState();
			int current = state.getSourceUpgradeLevel(source);
			int wanted = Math.min(levels, maxLevelOf(source) - current);
			if (wanted <= 0)
			{
				return;
			}
			int affordable = affordableCount(wanted,
				count -> source.upgradeCostForMany(current, count), state.getPoints());
			if (affordable > 0
				&& state.spendPoints(source.upgradeCostForMany(current, affordable)))
			{
				state.addSourceUpgrades(source, affordable);
				persist();
			}
			refreshPanel();
		});
	}

	private static int affordableCount(int requested, java.util.function.IntToDoubleFunction cost,
									   double budget)
	{
		int count = requested;
		while (count > 0 && cost.applyAsDouble(count) > budget)
		{
			count--;
		}
		return count;
	}

	public void claimPassTier(int tier, boolean premium, CardSet targetSet)
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable())
			{
				return;
			}
			if (passService.claim(engine.getState(), tier, premium, targetSet, rewards))
			{
				persist();
				refreshPanel();
			}
		});
	}

	public void claimAllPassTiers(CardSet targetSet)
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable())
			{
				return;
			}
			int claimed = passService.claimAll(engine.getState(), targetSet, rewards);
			if (claimed > 0)
			{
				persist();
				refreshPanel();
			}
		});
	}

	public void pullBanner(Rarity rarity, CardSet targetSet, int count)
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable())
			{
				return;
			}
			if (count == 1)
			{
				bannerService.pull(engine.getState(), rarity, targetSet, rewards);
				persist();
				refreshPanel();
				return;
			}

			RewardQueue batch = new RewardQueue();
			int pulled = 0;
			for (int i = 0; i < count; i++)
			{
				if (!bannerService.canPull(engine.getState(), rarity))
				{
					break;
				}
				bannerService.pull(engine.getState(), rarity, targetSet, batch);
				pulled++;
			}
			if (pulled == 0)
			{
				refreshPanel();
				return;
			}

			PackRevealOverlay reveal = revealOverlay;
			if (reveal != null)
			{
				reveal.makeWayForBatch();
			}
			revealHighlights(batch.claimAll());
			persist();
			refreshPanel();
		});
	}

	private void rollSeasons()
	{
		DopamineState state = engine.getState();
		long now = System.currentTimeMillis();
		boolean rolled = passService.rollIfExpired(state, now);
		rolled |= bannerService.rollIfExpired(state, now);
		if (!rolled)
		{
			return;
		}

		persist();
		refreshPanel();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			"Dopamine Simulator: a new season has begun - <col=ffb300>"
				+ Region.forSeason(state.getPassSeason()).getSeasonName() + "</col>.", null);
	}

	public void prestige()
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable())
			{
				return;
			}
			DopamineState state = engine.getState();
			double lifetime = state.getLifetimePoints();
			if (!Prestige.canPrestige(lifetime))
			{
				return;
			}

			int gained = Prestige.gainFrom(lifetime, state.getInsight());
			state.prestige(Prestige.insightFor(lifetime));

			Card mastery = CardOrigins.prestigeCard(state.getPrestigeCount());
			if (mastery != null)
			{
				collection.grant(state, mastery, rewards, false,
					CardOrigin.PRESTIGE.copiesPerAward(mastery.getRarity()));
			}
			incomeTracker.reset();
			announcedSources.clear();
			if (revealOverlay != null)
			{
				revealOverlay.clear();
			}
			persist();
			refreshPanel();
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"Dopamine Simulator: reset with <col=ffb300>" + state.getInsight()
					+ "</col> insight" + (gained > 0 ? ", " + gained + " new" : "")
					+ ". Cards kept."
					+ (mastery == null ? "" : " Mastery: <col=ffb300>"
						+ mastery.getName() + "</col>."), null);
		});
	}

	public void allocatePerk(InsightPerk perk)
	{
		clientThread.invoke(() ->
		{
			if (isPlayable() && engine.getState().allocate(perk))
			{
				persist();
				refreshPanel();
			}
		});
	}

	public void spendDustOn(Card card, int copies)
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable() || card == null || copies <= 0)
			{
				return;
			}
			DopamineState state = engine.getState();
			if (card.getSet().isUnlockSet() && state.owns(card.getId()))
			{
				return;
			}
			long cost = (long) Dust.costPerCopy(card.getRarity()) * copies;
			if (!state.spendDust(cost))
			{
				return;
			}
			collection.grant(state, card, rewards, false, copies);
			persist();
			refreshPanel();
		});
	}

	public void ascendCollection(String collectionName)
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable() || collectionName == null)
			{
				return;
			}
			DopamineState state = engine.getState();
			CardCollection collection = null;
			for (CardCollection candidate : CardCollection.all())
			{
				if (candidate.getName().equals(collectionName))
				{
					collection = candidate;
					break;
				}
			}
			if (collection == null || !collection.isMaxed(state))
			{
				return;
			}
			if (!state.spendDust(collection.ascensionCost(state)))
			{
				return;
			}
			for (Card card : collection.getCards())
			{
				state.clearCopies(card.getId());
			}
			state.ascend(collectionName);

			Card trophy = CardOrigins.ascensionCard(state.getTotalAscensions());
			if (trophy != null)
			{
				this.collection.grant(state, trophy, rewards, false,
					CardOrigin.ASCENSION.copiesPerAward(trophy.getRarity()));
			}
			persist();
			refreshPanel();
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"Dopamine Simulator: " + collectionName + " ascended to "
					+ state.getAscension(collectionName) + "."
					+ (trophy == null ? "" : " Trophy: <col=ffb300>"
						+ trophy.getName() + "</col>."), null);
		});
	}

	public void selectCardBack(String id)
	{
		clientThread.invoke(() ->
		{
			if (!isPlayable())
			{
				return;
			}
			DopamineState state = engine.getState();
			if (state.hasBack(id))
			{
				state.setSelectedBack(id);
				persist();
				refreshPanel();
			}
		});
	}

	public void buyPassPremium()
	{
		clientThread.invoke(() ->
		{
			if (isPlayable() && passService.buyPremium(engine.getState()))
			{
				persist();
				refreshPanel();
			}
		});
	}

	public void startNextPassSeason()
	{
		clientThread.invoke(() ->
		{
			if (isPlayable() && passService.startNextSeason(engine.getState()))
			{
				persist();
				refreshPanel();
			}
		});
	}

	public void flash(Reward reward)
	{
		PackRevealOverlay reveal = revealOverlay;
		if (reveal != null)
		{
			reveal.push(reward);
		}
		if (reward.getType() == RewardType.FEAT || reward.getType() == RewardType.ACHIEVEMENT)
		{
			String line = "<col=ffb300>" + reward.getTitle() + "</col> - " + reward.getDetail();
			clientThread.invokeLater(() ->
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", line, null));
		}
		FloatingTextOverlay floating = floatingTextOverlay;
		if (floating != null)
		{
			floating.reward(reward);
		}
	}
	private void announce(Card card)
	{
		if (!config.chatMessageOnNewCard()
			|| card.getRarity().ordinal() < config.minimumChatRarity().ordinal())
		{
			return;
		}
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			"<col=" + hex(card.getRarity()) + ">" + card.getRarity().getDisplayName() + "</col>: "
				+ card.getName(), null);
	}
	private static String hex(Rarity rarity)
	{
		return String.format("%06x", rarity.getColour().getRGB() & 0xFFFFFF);
	}
	private void loadStateForCurrentAccount()
	{
		long accountHash = client.getAccountHash();
		if (accountHash == loadedAccountHash)
		{
			return;
		}
		if (loadedAccountHash != Long.MIN_VALUE)
		{
			saveManager.save(loadedAccountHash, engine.getState());
		}
		engine.setState(saveManager.load(accountHash));
		loadedAccountHash = accountHash;
		incomeTracker.reset();
		clickState.clear();
		announcedSources.clear();
		achievementSystem.newSession();
		if (revealOverlay != null)
		{
			revealOverlay.clear();
		}
		if (floatingTextOverlay != null)
		{
			floatingTextOverlay.clear();
		}

		refreshPanel();
	}
	@Subscribe
	public void onCommandExecuted(CommandExecuted event)
	{
		if ("resetfeats".equalsIgnoreCase(event.getCommand()))
		{
			runReset("resetfeats", "feat ranks",
				state -> Feats.tiersEarned(state) + " feat ranks",
				DopamineState::resetFeats);
		}
		else if ("resetdopamine".equalsIgnoreCase(event.getCommand()))
		{
			runReset("resetdopamine", "everything",
				state -> "every card, upgrade, feat and pass season",
				this::wipe);
		}
		else if ("adddopamine".equalsIgnoreCase(event.getCommand()))
		{
			addDopamine(event.getArguments());
		}
		else if ("completecards".equalsIgnoreCase(event.getCommand()))
		{
			completeCards(event.getArguments());
		}
		else if ("modellab".equalsIgnoreCase(event.getCommand()))
		{
			if (modelLab != null)
			{
				modelLab.command(event.getArguments());
			}
		}
		else if ("cardface".equalsIgnoreCase(event.getCommand()))
		{
			CardViewerOverlay.dumpNextFace();
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"[cardface] next viewer frame written to cardface.png", null);
		}
		else if ("cardlab".equalsIgnoreCase(event.getCommand()))
		{
			previewNpcCard(event.getArguments());
		}
		else
		{
			GnomeFood food = GnomeFood.byCommand(event.getCommand());
			if (food != null)
			{
				serveOnCommand(food);
			}
		}
	}

	private void previewNpcCard(String[] arguments)
	{
		NpcCardArt art = null;
		String key = null;
		int zoom = 0;
		int dx = 0;
		int dy = 0;
		int scenery = 0;
		int sceneryZoom = 0;
		int foil = -1;
		int pzoom = 0;
		int pspread = 0;
		int sdy = 0;
		int prot = -1;
		int prot2 = -1;
		int fzoom = 0;
		int fspread = 0;
		int fdy = 0;
		int layers = -1;

		if (arguments != null)
		{
			for (String argument : arguments)
			{
				int eq = argument.indexOf('=');
				if (eq <= 0)
				{
					NpcCardArt named = NpcCardArt.byId(argument.toLowerCase());
					if (named != null)
					{
						key = argument.toLowerCase();
						art = named;
					}
					continue;
				}
				int value;
				try
				{
					value = Integer.parseInt(argument.substring(eq + 1).trim());
				}
				catch (NumberFormatException ex)
				{
					continue;
				}
				switch (argument.substring(0, eq).trim().toLowerCase())
				{
					case "zoom": zoom = value; break;
					case "dx": dx = value; break;
					case "dy": dy = value; break;
					case "scenery": scenery = value; break;
					case "szoom": sceneryZoom = value; break;
					case "foil": foil = value; break;
					case "pzoom": pzoom = value; break;
					case "pspread": pspread = value; break;
					case "sdy": sdy = value; break;
					case "prot": prot = value; break;
					case "prot2": prot2 = value; break;
					case "fzoom": fzoom = value; break;
					case "fspread": fspread = value; break;
					case "fdy": fdy = value; break;
					case "layers": layers = value; break;
					default: break;
				}
			}
		}

		revealOverlay.tuneModel(zoom, dx, dy, scenery, sceneryZoom, foil, pzoom, pspread, sdy, prot, prot2, fzoom, fspread, fdy, layers);
		if (art == null)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"::cardlab <" + String.join("|", NpcCardArt.ids())
					+ "> [zoom=] [dx=] [dy=] [scenery=|-1 off] [szoom=] [sdy=]"
					+ " [pzoom=] [pspread=] [prot=/prot2=0-2047] [fzoom=] [fspread=] [fdy=] [layers=1scene|2alcove|4pillars|8npc] [foil=0-100]", null);
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"[cardlab] " + revealOverlay.modelTuning(), null);
			return;
		}
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			"[cardlab] " + art.getDisplayName() + " - " + revealOverlay.modelTuning(), null);

		String cardId = NpcCardArt.idFor(art);
		Card card = cardId == null ? null : CardCatalogue.byId(cardId);
		revealOverlay.previewWish(card != null ? card
			: new Card(key, art.getDisplayName(), CardSet.CHARACTERS, Rarity.LEGENDARY, -1, -1));
	}

	private static final double DEV_POINTS = 1_000_000d;

	private void addDopamine(String[] arguments)
	{
		if (!isPlayable())
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"Dopamine Simulator: log in first.", null);
			return;
		}

		double amount = arguments.length > 0 ? BigNumbers.parse(arguments[0]) : DEV_POINTS;
		if (Double.isNaN(amount) || amount <= 0d)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"Dopamine Simulator: <col=ffb300>::adddopamine 2.5m</col>"
					+ " takes a number, with an optional K/M/B suffix.", null);
			return;
		}

		DopamineState state = engine.getState();
		state.addPoints(amount);
		persist();
		refreshPanel();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			"Dopamine Simulator: added <col=ffb300>" + BigNumbers.format(amount)
				+ "</col> points, for <col=ffb300>" + BigNumbers.format(state.getPoints())
				+ "</col>.", null);
	}

	private void completeCards(String[] arguments)
	{
		if (!isPlayable())
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"Dopamine Simulator: log in first.", null);
			return;
		}

		int stars = 1;
		if (arguments.length > 0)
		{
			if ("max".equalsIgnoreCase(arguments[0]))
			{
				stars = Rarity.MAX_STARS;
			}
			else
			{
				double asked = BigNumbers.parse(arguments[0]);
				if (Double.isNaN(asked) || asked < 1d || asked > Rarity.MAX_STARS)
				{
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
						"Dopamine Simulator: <col=ffb300>::completecards</col> takes 1 to "
							+ Rarity.MAX_STARS + " stars, or <col=ffb300>max</col>.", null);
					return;
				}
				stars = (int) asked;
			}
		}

		DopamineState state = engine.getState();
		int filled = 0;
		long copies = 0;
		for (Card card : CardCatalogue.all())
		{
			int wanted = card.getSet().isUnlockSet()
				? 1 : card.getRarity().copiesForStars(stars);
			int missing = wanted - state.getCopies(card.getId());
			if (missing <= 0)
			{
				continue;
			}
			state.addCopies(card.getId(), missing);
			filled++;
			copies += missing;
		}

		persist();
		refreshPanel();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			"Dopamine Simulator: topped up <col=ffb300>" + filled + "</col> cards to "
				+ stars + (stars == 1 ? " star" : " stars")
				+ " with <col=ffb300>" + copies + "</col> copies.", null);
	}

	private void serveOnCommand(GnomeFood food)
	{
		if (!isPlayable())
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"Dopamine Simulator: log in first.", null);
			return;
		}

		serve(food);
		persist();
	}

	private void runReset(String command, String what,
		Function<DopamineState, String> summary, Consumer<DopamineState> reset)
	{
		if (engine == null || !isPlayable())
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"Dopamine Simulator: log in first.", null);
			return;
		}

		DopamineState state = engine.getState();
		long now = System.currentTimeMillis();

		if (!command.equals(pendingResetCommand) || now - resetRequestedAt > RESET_CONFIRM_MS)
		{
			pendingResetCommand = command;
			resetRequestedAt = now;
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
				"Dopamine Simulator: this wipes <col=ffb300>" + summary.apply(state)
					+ "</col> and cannot be undone."
					+ " Run <col=ffb300>::" + command + "</col> again to confirm.", null);
			return;
		}

		pendingResetCommand = null;
		resetRequestedAt = 0L;
		reset.accept(state);
		persist();
		refreshPanel();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
			"Dopamine Simulator: reset " + what + ".", null);
	}

	private void wipe(DopamineState state)
	{
		engine.setState(SaveManager.freshState());
		incomeTracker.reset();
		clickState.clear();
		announcedSources.clear();
		achievementSystem.newSession();
		if (revealOverlay != null)
		{
			revealOverlay.clear();
		}
		if (floatingTextOverlay != null)
		{
			floatingTextOverlay.clear();
		}
	}

	private void persist()
	{
		if (engine != null && loadedAccountHash != Long.MIN_VALUE)
		{
			saveManager.save(loadedAccountHash, engine.getState());
		}
	}
	private void refreshPanel()
	{
		DopamineSimulatorPanel current = panel;
		if (current != null)
		{
			current.refresh();
		}
	}

	private void applyPluginIcon()
	{
		AsyncBufferedImage cookie = itemManager.getImage(ItemID.CHOCCHIP_CRUNCHIES);
		if (cookie == null)
		{
			return;
		}

		cookie.onLoaded(() ->
		{
			SurgeInfoBox box = surgeInfoBox;
			if (box != null)
			{
				box.setImage(ImageUtil.resizeImage(GameIcons.golden(cookie), 32, 32));
			}
		});

		BufferedImage small = ImageUtil.resizeImage(cookie, 16, 16);
		SwingUtilities.invokeLater(() ->
		{
			if (panel == null || navButton == null)
			{
				return;
			}

			clientToolbar.removeNavigation(navButton);
			navButton = NavigationButton.builder()
				.tooltip("Dopamine Simulator")
				.icon(small)
				.priority(7)
				.panel(panel)
				.build();
			clientToolbar.addNavigation(navButton);
		});
	}

	private static BufferedImage buildIcon()
	{
		BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		java.awt.Graphics2D g = image.createGraphics();
		g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
			java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new java.awt.Color(0x2E, 0x2E, 0x2E));
		g.fillRoundRect(2, 1, 9, 13, 3, 3);
		g.setColor(Rarity.LEGENDARY.getColour());
		g.drawRoundRect(2, 1, 9, 13, 3, 3);
		g.setColor(new java.awt.Color(0x42, 0xA5, 0xF5));
		g.fillRoundRect(6, 3, 8, 12, 3, 3);
		g.setColor(new java.awt.Color(0x1E, 0x1E, 0x1E));
		g.drawRoundRect(6, 3, 8, 12, 3, 3);
		g.dispose();
		return image;
	}
}
