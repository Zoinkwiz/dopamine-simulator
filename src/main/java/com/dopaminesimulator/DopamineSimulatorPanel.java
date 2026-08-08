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
import com.dopaminesimulator.cards.CardAffinity;
import com.dopaminesimulator.cards.CardCatalogue;
import com.dopaminesimulator.cards.CardCollection;
import com.dopaminesimulator.cards.CardOrigin;
import com.dopaminesimulator.cards.CardOrigins;
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Dust;
import com.dopaminesimulator.cards.CollectionBonus;
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.cards.Region;
import com.dopaminesimulator.cosmetics.CardBack;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.IncomeTracker;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.feats.Achievement;
import com.dopaminesimulator.feats.Feat;
import com.dopaminesimulator.feats.Feats;
import com.dopaminesimulator.incremental.BigNumbers;
import com.dopaminesimulator.incremental.Milestones;
import com.dopaminesimulator.incremental.InsightPerk;
import com.dopaminesimulator.incremental.Prestige;
import com.dopaminesimulator.packs.PackTier;
import com.dopaminesimulator.pass.BattlePass;
import com.dopaminesimulator.pass.PassReward;
import com.dopaminesimulator.pass.PassTheme;
import com.dopaminesimulator.pass.SeasonClock;
import com.dopaminesimulator.systems.BannerService;
import com.dopaminesimulator.systems.PassService;
import com.dopaminesimulator.systems.PassSystem;
import com.dopaminesimulator.points.ClickState;
import com.dopaminesimulator.points.GnomeFood;
import com.dopaminesimulator.points.PointSource;
import com.dopaminesimulator.ui.CardComponent;
import com.dopaminesimulator.ui.ClickButton;
import com.dopaminesimulator.ui.BannerHeader;
import com.dopaminesimulator.ui.FeatRow;
import com.dopaminesimulator.ui.PassHeader;
import com.dopaminesimulator.ui.PassTierRow;
import com.dopaminesimulator.ui.PointsHeader;
import com.dopaminesimulator.ui.ScrollableContent;
import com.dopaminesimulator.ui.SectionHeader;
import com.dopaminesimulator.ui.Segmented;
import com.dopaminesimulator.ui.Skin;
import com.dopaminesimulator.ui.StoneButton;
import com.dopaminesimulator.ui.WrappedLabel;
import com.dopaminesimulator.ui.ShopRow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.materialtabs.MaterialTab;
import net.runelite.client.ui.components.materialtabs.MaterialTabGroup;

public class DopamineSimulatorPanel extends PluginPanel
{
		private static final org.slf4j.Logger LOGGER =
		org.slf4j.LoggerFactory.getLogger(DopamineSimulatorPanel.class);
	private static final int CASCADE_INTERVAL_MS = 110;
	private static final int CARD_MIN_WIDTH = 38;
	private static final int CARD_MAX_WIDTH = 58;
	private static final int CARD_GAP = 3;
	private static final int CARD_MIN_COLUMNS = 3;
	private static final int CARD_MAX_COLUMNS = 10;
	private static final Color GOLD = Skin.ORANGE;
	private static final Object ALL_SETS = new Object();
	private static final int RESIZE_SETTLE_MS = 150;
	private static final int MAX_MATCHES = 250;
	private enum Tab
	{
		PLAY, SHOP, CARDS, FEATS
	}
	private final DopamineSimulatorPlugin plugin;
	private final DopamineSimulatorConfig config;
	private final AtomicBoolean refreshQueued = new AtomicBoolean();

	private Timer cascadeTimer;
	private ClickButton clickButton;
	private final Timer surgeTimer;
	private final Timer resizeTimer;
	private final JPanel playContent = new JPanel();
	private final JPanel shopContent = new JPanel();
	private final JPanel cardsContent = new JPanel();
	private final JPanel featsContent = new JPanel();
	private final JScrollPane scrollPane;
	private Tab selectedTab = Tab.PLAY;
	private Card selectedCard;
	private CardSet selectedSet = CardSet.QUESTS;
	private boolean allSets;
	private CardSet expandedInAll;
	private int buyQuantity = 1;
	private boolean collectionsExpanded;
	private String cardSearch = "";
	private boolean showingAchievements;
	private int shopView;
	private final JTextField searchField = new JTextField();
	DopamineSimulatorPanel(DopamineSimulatorPlugin plugin, DopamineSimulatorConfig config)
	{
		super(false);
		this.plugin = plugin;
		this.config = config;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		setBackground(Skin.BG);
		for (JPanel tabPanel : new JPanel[]{playContent, shopContent, cardsContent, featsContent})
		{
			tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));
			tabPanel.setBackground(Skin.BG);
		}
		JPanel display = new JPanel(new BorderLayout());
		display.setBackground(Skin.BG);
		MaterialTabGroup tabGroup = new MaterialTabGroup(display);
		tabGroup.setLayout(new GridLayout(1, 4, 1, 0));
		tabGroup.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));
		MaterialTab playTab = tab("Play", tabGroup, playContent);
		MaterialTab shopTab = tab("Shop", tabGroup, shopContent);
		MaterialTab cardsTab = tab("Cards", tabGroup, cardsContent);
		MaterialTab featsTab = tab("Feats", tabGroup, featsContent);
		playTab.setOnSelectEvent(() -> selectTab(Tab.PLAY));
		shopTab.setOnSelectEvent(() -> selectTab(Tab.SHOP));
		cardsTab.setOnSelectEvent(() -> selectTab(Tab.CARDS));
		featsTab.setOnSelectEvent(() -> selectTab(Tab.FEATS));

		tabGroup.addTab(playTab);
		tabGroup.addTab(shopTab);
		tabGroup.addTab(cardsTab);
		tabGroup.addTab(featsTab);
		ScrollableContent wrapper = new ScrollableContent();
		wrapper.setLayout(new BorderLayout());
		wrapper.setBackground(Skin.BG);
		wrapper.add(display, BorderLayout.NORTH);
		scrollPane = new JScrollPane(wrapper,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setBackground(Skin.BG);

		add(tabGroup, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		resizeTimer = new Timer(RESIZE_SETTLE_MS, e ->
		{
			if (selectedTab == Tab.CARDS)
			{
				rebuild();
			}
		});
		resizeTimer.setRepeats(false);
		scrollPane.getViewport().addComponentListener(new ComponentAdapter()
		{
			private int lastColumns = -1;
			@Override
			public void componentResized(ComponentEvent e)
			{
				int columns = cardColumns();
				if (columns != lastColumns)
				{
					lastColumns = columns;
					if (selectedTab == Tab.CARDS)
					{
						resizeTimer.restart();
					}
				}
			}
		});
		surgeTimer = new Timer(500, e ->
		{
			if (selectedTab == Tab.CARDS || !plugin.isPlayable())
			{
				return;
			}
			rebuild();
		});
		initSearchField();
		surgeTimer.start();
		tabGroup.select(playTab);
		rebuild();
	}
	private static MaterialTab tab(String name, MaterialTabGroup group, JComponent content)
	{
		MaterialTab created = new MaterialTab(name, group, content)
		{
			@Override
			public Insets getInsets()
			{
				return new Insets(4, 1, 4, 1);
			}

			@Override
			public void setBorder(Border border)
			{
			}

			@Override
			protected void paintComponent(Graphics graphics)
			{
				Graphics2D g = (Graphics2D) graphics.create();
				Skin.smooth(g);
				int w = getWidth();
				int h = getHeight();
				g.setColor(isSelected() ? Skin.CARD_HOVER : Skin.CARD_DEEP);
				g.fillRoundRect(0, 0, w, h, 5, 5);
				if (isSelected())
				{
					g.setColor(Skin.GOLD);
					g.fillRect(2, h - 2, w - 4, 2);
				}
				g.dispose();

				Color wanted = isSelected() ? Skin.GOLD : Skin.MUTED;
				if (!wanted.equals(getForeground()))
				{
					setForeground(wanted);
				}
				super.paintComponent(graphics);
			}
		};
		created.setFont(FontManager.getRunescapeBoldFont());
		created.setOpaque(false);
		created.setHorizontalAlignment(SwingConstants.CENTER);
		return created;
	}

	private boolean selectTab(Tab tab)
	{
		selectedTab = tab;
		SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
		rebuild();
		return true;
	}
	public void refresh()
	{
		if (selectedTab == Tab.CARDS)
		{
			SwingUtilities.invokeLater(() ->
			{
				if (config.autoReveal())
				{
					startCascade();
				}
			});
			return;
		}
		if (!refreshQueued.compareAndSet(false, true))
		{
			return;
		}
		SwingUtilities.invokeLater(() ->
		{
			refreshQueued.set(false);
			rebuild();
		});
	}
	private void rebuild()
	{
		int scrollPosition = scrollPane.getVerticalScrollBar().getValue();
		JPanel target = contentFor(selectedTab);
		target.removeAll();

		if (!plugin.isPlayable())
		{
			buildLockedTab();
		}
		else
		{
			DopamineState state = plugin.getEngine().getState();

			switch (selectedTab)
			{
				case PLAY:
					buildPlayTab(state);
					break;
				case SHOP:
					buildShopTab(state);
					break;
				case CARDS:
					buildCardsTab(state);
					break;
				case FEATS:
					buildFeatsTab(state);
					break;
			}
			if (config.autoReveal())
			{
				startCascade();
			}
		}
		target.revalidate();
		target.repaint();
		SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPosition));
	}

	private void buildLockedTab()
	{
		JPanel target = contentFor(selectedTab);

		JLabel title = new JLabel("Logged out");
		title.setFont(FontManager.getRunescapeBoldFont());
		title.setForeground(GOLD);
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		target.add(title);
		target.add(Box.createVerticalStrut(6));

		target.add(hint("Progress is saved per account. Nothing is earned or spent from "
			+ "the login screen. Log in to carry on."));
	}

	private JPanel contentFor(Tab tab)
	{
		switch (tab)
		{
			case SHOP:
				return shopContent;
			case CARDS:
				return cardsContent;
			case FEATS:
				return featsContent;
			default:
				return playContent;
		}
	}

	private void buildPlayTab(DopamineState state)
	{
		IncomeTracker income = plugin.getIncomeTracker();
		ClickState clicks = plugin.getClickState();
		long now = System.currentTimeMillis();
		GnomeFood serving = clicks == null ? null : clicks.getShown(now);
		boolean surging = serving != null;
		double perHour = income.totalPerHour(state.getTick());
		playContent.add(pointsLine(state));
		playContent.add(Box.createVerticalStrut(8));
		playContent.add(buildClickButton(state, surging));
		playContent.add(Box.createVerticalStrut(8));
		JPanel waiting = revealQueueStrip();
		if (waiting != null)
		{
			playContent.add(waiting);
			playContent.add(Box.createVerticalStrut(8));
		}

		playContent.add(sectionLabel("Sources",
			BigNumbers.format(perHour) + "/hr"));
		playContent.add(hint("Each level makes that source pay more."));
		playContent.add(Box.createVerticalStrut(4));
		playContent.add(buildQuantitySelector());
		playContent.add(Box.createVerticalStrut(5));

		for (PointSource source : PointSource.values())
		{
			if (state.isSourceUnlocked(source))
			{
				playContent.add(upgradeRow(state, source, income));
			}
		}

		JPanel nextUnlock = nextUnlockRow(state);
		if (nextUnlock != null)
		{
			playContent.add(Box.createVerticalStrut(4));
			playContent.add(nextUnlock);
		}

		playContent.add(Box.createVerticalStrut(8));
		playContent.add(milestoneLine(state));
		playContent.add(featLine(state));
		playContent.add(Box.createVerticalStrut(6));
		playContent.add(prestigeBlock(state));
	}

	private ClickButton buildClickButton(DopamineState state, boolean surging)
	{
		if (clickButton == null)
		{
			clickButton = new ClickButton(this::pointsPerClick, plugin::click);
			clickButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		}

		ClickState clicks = plugin.getClickState();
		long now = System.currentTimeMillis();
		GnomeFood serving = clicks == null ? null : clicks.getShown(now);
		clickButton.setIcon(serving == null
			? plugin.getGameIcons().forClick(state.getLifetimePoints())
			: plugin.getGameIcons().forFood(serving));
		clickButton.setSurging(surging);
		clickButton.setDanger(dangerLabel(clicks, now));
		clickButton.setCallout(calloutLabel(clicks, now));
		clickButton.setStatus(null);
		return clickButton;
	}

	private String dangerLabel(ClickState clicks, long now)
	{
		if (clicks == null)
		{
			return null;
		}
		GnomeFood plated = clicks.getPlated(now);
		if (plated != null && plated.isTrap())
		{
			return "DON'T EAT";
		}
		if (clicks.isSoured(now))
		{
			return "SOURED " + String.format("%.0fs", clicks.sourSecondsRemaining(now));
		}
		return null;
	}

	private String calloutLabel(ClickState clicks, long now)
	{
		if (clicks == null)
		{
			return null;
		}
		GnomeFood plated = clicks.getPlated(now);
		if (plated != null)
		{
			return plated.isTrap()
				? null : "EAT " + String.format("%.0fs", clicks.plateSecondsRemaining(now));
		}
		return clicks.isSurging(now) ? "SURGE" : null;
	}
	private double pointsPerClick()
	{
		if (plugin.getEngine() == null)
		{
			return 0d;
		}
		return plugin.clickPayout();
	}
	private JPanel nextUnlockRow(DopamineState state)
	{
		String name = null;
		String detail = null;
		double target = 0d;

		PointSource source = state.nextLockedSource();
		if (source != null)
		{
			name = source.getDisplayName();
			detail = source.getDescription();
			target = source.getUnlockAtLifetimePoints();
		}

		for (PackTier tier : PackTier.values())
		{
			if (state.isPackUnlocked(tier))
			{
				continue;
			}
			double at = tier.getUnlockAtLifetimePoints();
			if (target <= 0d || at < target)
			{
				name = tier.getDisplayName();
				detail = tier.getDescription();
				target = at;
			}
			break;
		}

		if (name == null)
		{
			return null;
		}

		double lifetime = state.getLifetimePoints();
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
		row.setBackground(Skin.CARD_DEEP);
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 2, 0, 0, GOLD),
			BorderFactory.createEmptyBorder(5, 6, 5, 6)));

		JLabel title = new JLabel("Next unlock: " + name);
		title.setFont(FontManager.getRunescapeSmallFont());
		title.setForeground(GOLD);
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.add(title);

		row.add(hint(detail, availableWidth() - 20));
		row.add(Box.createVerticalStrut(3));

		JProgressBar bar = new JProgressBar(0, 1000);
		bar.setValue((int) Math.min(1000, lifetime / target * 1000));
		bar.setStringPainted(true);
		bar.setString(BigNumbers.format(lifetime) + " / " + BigNumbers.format(target)
			+ " lifetime");
		bar.setFont(FontManager.getRunescapeSmallFont());
		bar.setForeground(GOLD);
		bar.setBackground(Skin.BG);
		bar.setAlignmentX(Component.LEFT_ALIGNMENT);
		bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 14));
		row.add(bar);

		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));
		return row;
	}

	private ShopRow upgradeRow(DopamineState state, PointSource source, IncomeTracker income)
	{
		int level = state.getSourceUpgradeLevel(source);
		double cost = source.upgradeCostForMany(level, buyQuantity);
		boolean affordable = state.getPoints() >= cost;
		double rate = income.perHour(source, state.getTick());

		boolean surgeLine = source == PointSource.CLICK;

		boolean maxed = level >= DopamineSimulatorPlugin.maxLevelOf(source);
		String effect = surgeLine
			? String.format("%.1f", ClickState.surgesPerHour(level)) + " dishes/hr"
				+ (maxed ? "  •  fully served" : "  →  " + String.format("%.1f",
					ClickState.surgesPerHour(Math.min(ClickState.MAX_LEVEL,
						level + buyQuantity))))
			: (rate > 0 ? BigNumbers.format(rate) + "/hr  •  " : "")
				+ "x" + String.format("%.2f",
					PointSource.multiplierForLevel(level, state.getInsight()))
				+ "  →  x" + String.format("%.2f",
					PointSource.multiplierForLevel(level + buyQuantity, state.getInsight()));

		ShopRow row = new ShopRow(
			source.getDisplayName(),
			effect,
			maxed ? 0d : cost,
			source.getColour(),
			maxed ? "MAX" : String.valueOf(level),
			affordable && !maxed,
			maxed ? 1d : state.getPoints() / cost,
			r -> plugin.buySourceUpgrade(source, buyQuantity));
		row.setIcon(plugin.getGameIcons().forSource(source));
		CardSet set = CollectionBonus.setFor(source);
		double fromCards = CollectionBonus.multiplierFor(state, source);
		row.setToolTipText(surgeLine
			? source.getDescription()
				+ "  •  level " + level
				+ ", serving " + String.format("%.1f", ClickState.surgesPerHour(level))
				+ " dishes an hour of a possible "
				+ String.format("%.1f", ClickState.BASE_SURGE_CHANCE_PER_TICK
					* ClickState.SURGE_RATE_CAP * 6_000d)
				+ "  •  serves one of " + GnomeFood.values().length + " gnome dishes"
			: source.getDescription()
				+ "  •  level " + level
				+ ", each adding "
				+ Math.round((PointSource.UPGRADE_GAIN_GROWTH - 1d) * 100d) + "%"
				+ "  •  " + multiplierText(fromCards) + " from " + set.getDisplayName() + " cards"
				+ "  •  " + multiplierText(
					PointSource.multiplierForLevel(level, state.getInsight()) * fromCards)
				+ " total");
		return sized(row);
	}
	private Segmented shopToggle()
	{
		Segmented row = new Segmented(new String[]{"Packs", "Pass", "Banner"}, shopView, index ->
		{
			shopView = index;
			rebuild();
		});
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		return row;
	}

	private void buildBannerTab(DopamineState state)
	{
		BannerService banner = plugin.getBannerService();

		shopContent.add(hint("Featured cards change every " + SeasonClock.BANNER_DAYS
			+ " days. Every pull opens a pack, the featured card is guaranteed within "
			+ BannerService.HARD_PITY + " pulls, and pity carries across rotations."));
		shopContent.add(Box.createVerticalStrut(8));

		for (Rarity rarity : BannerService.BANNERS)
		{
			shopContent.add(bannerBlock(state, banner, rarity));
			shopContent.add(Box.createVerticalStrut(10));
		}
	}

	private JPanel bannerBlock(DopamineState state, BannerService banner, Rarity rarity)
	{
		Card featured = banner.featured(state, rarity);
		int pity = state.getBannerPity(rarity);
		double cost = banner.pullCost(rarity);

		JPanel block = new JPanel();
		block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
		block.setBackground(Skin.BG);
		block.setAlignmentX(Component.LEFT_ALIGNMENT);

		BufferedImage art = plugin.getCardArtService().get(featured);
		if (art == null)
		{
			plugin.getCardArtService().onLoaded(featured,
				() -> SwingUtilities.invokeLater(this::refresh));
		}

		long now = System.currentTimeMillis();
		BannerHeader header = new BannerHeader(featured, rarity, banner.bannerName(rarity),
			art, pity, BannerService.HARD_PITY, banner.rateAt(pity),
			SeasonClock.remaining(SeasonClock.bannerEndsAt(now), now));
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		header.setToolTipText(featured.getName() + " from " + featured.getSet().getDisplayName());
		block.add(header);
		block.add(Box.createVerticalStrut(4));

		String detail = "A win takes " + featured.getName() + " straight to "
			+ banner.featuredStars(rarity) + " stars, and every pull opens a "
			+ banner.packFor(rarity).getDisplayName() + " regardless.";
		BufferedImage icon = art;

		ShopRow one = new ShopRow(
			"Pull",
			"Wins it to " + banner.featuredStars(rarity) + "★",
			cost,
			rarity.getColour(),
			"1",
			state.getPoints() >= cost,
			state.getPoints() / cost,
			r -> plugin.pullBanner(rarity, selectedSet, 1));
		one.setIcon(icon);
		one.setToolTipText(detail);
		block.add(sized(one));
		block.add(Box.createVerticalStrut(3));

		ShopRow ten = new ShopRow(
			"Pull x10",
			"Ten pulls at once",
			cost * 10,
			rarity.getColour(),
			"10",
			state.getPoints() >= cost * 10,
			state.getPoints() / (cost * 10),
			r -> plugin.pullBanner(rarity, selectedSet, 10));
		ten.setIcon(icon);
		ten.setToolTipText(detail);
		block.add(sized(ten));
		return block;
	}

	private void buildPassTab(DopamineState state)
	{
		int season = state.getPassSeason();
		int tier = BattlePass.tierAt(state.getPassXp(), season);
		PassService pass = plugin.getPassService();

		Region region = Region.forSeason(season);
		Region next = Region.forSeason(season + 1);
		PassTheme theme = PassTheme.forSeason(season);
		long now = System.currentTimeMillis();
		double into = BattlePass.xpIntoTier(state.getPassXp(), season);
		double need = tier >= BattlePass.TIERS ? 0d : BattlePass.xpForTier(tier + 1, season);

		PassHeader header = new PassHeader(season, region.getArea(), region.getColour(),
			tier, BattlePass.TIERS, into, need, state.isPassPremium(),
			SeasonClock.brief(SeasonClock.seasonEndsAt(now), now));
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		header.setToolTipText(region.getSeasonName() + ". " + theme.getDescription()
			+ ". Next month brings " + next.getArea() + ".");
		shopContent.add(header);
		shopContent.add(Box.createVerticalStrut(5));
		shopContent.add(hint(region.getArea() + " cards and packs. Earned from experience, "
			+ (long) PassSystem.XP_PER_POINT + " xp a point, with no multipliers. Unclaimed"
			+ " rewards are lost when the month ends."));
		shopContent.add(Box.createVerticalStrut(8));

		int pending = pass.unclaimed(state).size();
		if (pending > 0)
		{
			StoneButton claim = new StoneButton("Claim " + pending + " reward" + (pending == 1 ? "" : "s"));
			claim.setAlignmentX(Component.LEFT_ALIGNMENT);
			claim.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
			claim.addActionListener(e -> plugin.claimAllPassTiers(selectedSet));
			shopContent.add(claim);
			shopContent.add(Box.createVerticalStrut(6));
		}

		if (!state.isPassPremium())
		{
			double cost = BattlePass.premiumCost(season);
			shopContent.add(sized(new ShopRow(
				"Unlock Premium Track",
				"Doubles every tier this season",
				cost,
				GOLD,
				"+",
				state.getPoints() >= cost,
				state.getPoints() / cost,
				r -> plugin.buyPassPremium())));
			shopContent.add(Box.createVerticalStrut(6));
		}

		if (pass.canStartNextSeason(state))
		{
			StoneButton advance = new StoneButton("Start season " + (season + 1)
				+ ": " + next.getArea());
			advance.withAccent(next.getColour());
			advance.setAlignmentX(Component.LEFT_ALIGNMENT);
			advance.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
			advance.addActionListener(e -> plugin.startNextPassSeason());
			shopContent.add(advance);
			shopContent.add(Box.createVerticalStrut(6));
		}

		for (int t = 1; t <= BattlePass.TIERS; t++)
		{
			shopContent.add(passRow(state, t, tier));
		}
	}

	private PassTierRow passRow(DopamineState state, int tier, int reached)
	{
		int season = state.getPassSeason();
		PassReward free = BattlePass.freeReward(tier, season);
		PassReward premium = BattlePass.premiumReward(tier, season);

		PassTierRow row = new PassTierRow(tier, BattlePass.isMilestone(tier), reached >= tier,
			tier == 1, tier == BattlePass.TIERS, Region.forSeason(season).getColour(),
			free, premium,
			state.isPassTierClaimed(tier, false), state.isPassTierClaimed(tier, true),
			state.isPassPremium(), rewardIcon(free), rewardIcon(premium),
			isPremium -> plugin.claimPassTier(tier, isPremium, selectedSet));
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.setToolTipText("Tier " + tier + "  •  free: " + free.describe()
			+ "  •  premium: " + premium.describe());
		return row;
	}

	private BufferedImage rewardIcon(PassReward reward)
	{
		return reward.getPack() == null ? null : plugin.getGameIcons().forPack(reward.getPack());
	}

	private void buildShopTab(DopamineState state)
	{
		shopContent.add(pointsLine(state));
		shopContent.add(Box.createVerticalStrut(6));
		shopContent.add(shopToggle());
		shopContent.add(Box.createVerticalStrut(8));

		if (shopView == 1)
		{
			buildPassTab(state);
			return;
		}
		if (shopView == 2)
		{
			buildBannerTab(state);
			return;
		}

		shopContent.add(sectionLabel("Packs"));
		shopContent.add(hint("Bigger packs hold more cards and better odds. "
			+ "Curated draws only from the set chosen on the Cards tab."));
		shopContent.add(Box.createVerticalStrut(4));
		shopContent.add(buildQuantitySelector());
		shopContent.add(Box.createVerticalStrut(5));

		for (PackTier tier : PackTier.values())
		{
			shopContent.add(packRow(state, tier));
		}
		shopContent.add(Box.createVerticalStrut(8));
		shopContent.add(hint("Rare guaranteed within "
			+ plugin.getPackService().packsUntilPity(state) + " packs."));
	}
	private ShopRow packRow(DopamineState state, PackTier tier)
	{
		boolean unlocked = state.isPackUnlocked(tier);
		double unitCost = plugin.getPackService().costOf(state, tier);
		double cost = unitCost * buyQuantity;
		boolean affordable = unlocked && state.getPoints() >= cost;
		if (!unlocked)
		{
			return sized(new ShopRow("???", "Reach "
				+ BigNumbers.format(tier.getUnlockAtLifetimePoints()) + " lifetime points",
				tier.getUnlockAtLifetimePoints(), Color.DARK_GRAY, "?",
				false, state.getLifetimePoints() / tier.getUnlockAtLifetimePoints(), null));
		}
		int totalCards = tier.getCardCount() * buyQuantity;

		StringBuilder effect = new StringBuilder(totalCards + " cards");
		if (tier.getFloor() != null)
		{
			effect.append("  •  ").append(tier.getFloor().getDisplayName()).append("+");
		}
		if (tier.getLuck() > 1d)
		{
			effect.append("  •  +").append(Math.round((tier.getLuck() - 1d) * 100d))
				.append("% top odds");
		}
		String name = tier.isTargetsSet()
			? tier.getDisplayName() + " (" + selectedSet.getDisplayName() + ")"
			: tier.getDisplayName();
		ShopRow row = new ShopRow(
			buyQuantity > 1 ? name + " x" + buyQuantity : name,
			effect.toString(),
			cost,
			tier.getColour(),
			String.valueOf(totalCards),
			affordable,
			state.getPoints() / cost,
			r -> plugin.buyPacks(tier, selectedSet, buyQuantity));
		row.setIcon(plugin.getGameIcons().forPack(tier));
		row.setToolTipText(tier.getDescription()
			+ "  \u2022  " + BigNumbers.format(tier.getCostPerCopy()) + " per copy"
			+ (buyQuantity > 1
				? "  \u2022  " + BigNumbers.format(unitCost) + " each, "
					+ BigNumbers.format(cost) + " for " + buyQuantity
				: "")
			+ "  \u2022  " + (tier.isTargetsSet()
				? "draws only from " + selectedSet.getDisplayName()
				: "draws from every set"));
		return sized(row);
	}
	private void buildCardsTab(DopamineState state)
	{
		int stars = state.getTotalStars();
		int maxStars = CardCatalogue.size() * Rarity.MAX_STARS;
		double complete = maxStars == 0 ? 0d : stars * 100d / maxStars;

		JLabel header = new JLabel(String.format("%.1f%% complete", complete));
		header.setFont(FontManager.getRunescapeBoldFont());
		header.setForeground(GOLD);
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		cardsContent.add(header);
		cardsContent.add(Box.createVerticalStrut(3));

		JProgressBar overall = new JProgressBar(0, 1000);
		overall.setValue((int) Math.round(complete * 10d));
		overall.setStringPainted(true);
		overall.setString(state.getUniqueCardsOwned() + "/" + CardCatalogue.size() + " owned");
		overall.setFont(FontManager.getRunescapeSmallFont());
		overall.setForeground(GOLD);
		overall.setBackground(Skin.CARD_DEEP);
		overall.setAlignmentX(Component.LEFT_ALIGNMENT);
		overall.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
		cardsContent.add(overall);
		cardsContent.add(Box.createVerticalStrut(5));
		cardsContent.add(hint(BigNumbers.format(stars) + " of " + BigNumbers.format(maxStars)
			+ " stars" + variantSummary(state)));
		cardsContent.add(Box.createVerticalStrut(8));
		cardsContent.add(buildSetSelector(state));
		JComponent backs = buildBackSelector(state);
		if (backs != null)
		{
			cardsContent.add(Box.createVerticalStrut(4));
			cardsContent.add(backs);
		}
		cardsContent.add(Box.createVerticalStrut(4));
		cardsContent.add(buildSearchBox());
		cardsContent.add(Box.createVerticalStrut(8));
		buildSelectedSet(state);
	}

	private JPanel buildSearchBox()
	{
		JPanel row = new JPanel(new BorderLayout(4, 0));
		row.setBackground(Skin.BG);
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.add(searchField, BorderLayout.CENTER);

		if (!cardSearch.isEmpty())
		{
			StoneButton clear = new StoneButton("x");
			clear.setMargin(new Insets(0, 4, 0, 4));
			clear.addActionListener(e ->
			{
				searchField.setText("");
				searchField.requestFocusInWindow();
			});
			row.add(clear, BorderLayout.EAST);
		}

		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
		return row;
	}

	private void initSearchField()
	{
		searchField.setFont(FontManager.getRunescapeSmallFont());
		searchField.setBackground(Skin.CARD_DEEP);
		searchField.setForeground(Skin.WHITE);
		searchField.setCaretColor(Skin.WHITE);
		searchField.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
		searchField.setToolTipText("Filter the cards shown by name");
		searchField.getDocument().addDocumentListener(new DocumentListener()
		{
			private void changed()
			{
				String text = searchField.getText().trim();
				if (text.equals(cardSearch))
				{
					return;
				}
				cardSearch = text;
				selectedCard = null;
				SwingUtilities.invokeLater(() ->
				{
					rebuild();
					searchField.requestFocusInWindow();
				});
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				changed();
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				changed();
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				changed();
			}
		});
	}

	private List<Card> visibleCards()
	{
		List<Card> all = allSets ? CardCatalogue.all() : CardCatalogue.bySet(selectedSet);
		if (cardSearch.isEmpty())
		{
			return all;
		}

		String needle = cardSearch.toLowerCase();
		List<Card> matches = new ArrayList<>();
		for (Card card : all)
		{
			if (card.getName().toLowerCase().contains(needle))
			{
				matches.add(card);
			}
		}
		return matches;
	}

	private static String variantSummary(DopamineState state)
	{
		StringBuilder text = new StringBuilder();
		if (state.getDust() > 0)
		{
			text.append("  •  ").append(BigNumbers.format(state.getDust()))
				.append(" dust");
		}
		if (state.getShinyCount() > 0)
		{
			text.append("  •  ").append(state.getShinyCount()).append(" shiny");
		}
		if (state.getGildedCount() > 0)
		{
			text.append("  •  ").append(state.getGildedCount()).append(" gilded");
		}
		return text.toString();
	}

	private JPanel buildSetSelector(DopamineState state)
	{
		JPanel row = new JPanel(new BorderLayout(4, 0));
		row.setBackground(Skin.BG);
		row.setAlignmentX(Component.LEFT_ALIGNMENT);

		CardSet[] sets = CardSet.values();
		Object[] options = new Object[sets.length + 1];
		options[0] = ALL_SETS;
		System.arraycopy(sets, 0, options, 1, sets.length);

		JComboBox<Object> picker = new JComboBox<>(options);
		picker.setSelectedItem(allSets ? ALL_SETS : selectedSet);
		picker.setFont(FontManager.getRunescapeSmallFont());
		picker.setBackground(Skin.CARD_DEEP);
		picker.setForeground(Skin.WHITE);
		picker.setFocusable(false);
		picker.setRenderer(new DefaultListCellRenderer()
		{
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index,
				boolean selected, boolean focused)
			{
				super.getListCellRendererComponent(list, value, index, selected, focused);
				setFont(FontManager.getRunescapeSmallFont());
				if (value == ALL_SETS)
				{
					setText("All Sets   " + state.getUniqueCardsOwned() + "/"
						+ CardCatalogue.size());
					return this;
				}
				CardSet set = (CardSet) value;
				setText(set.getDisplayName() + "   " + ownedIn(state, set) + "/"
					+ CardCatalogue.bySet(set).size());
				return this;
			}
		});
		picker.addActionListener(e ->
		{
			Object chosen = picker.getSelectedItem();
			if (chosen == null)
			{
				return;
			}
			boolean wantsAll = chosen == ALL_SETS;
			if (wantsAll == allSets && (wantsAll || chosen == selectedSet))
			{
				return;
			}
			allSets = wantsAll;
			if (!wantsAll)
			{
				selectedSet = (CardSet) chosen;
			}
			selectedCard = null;
			rebuild();
		});

		row.add(picker, BorderLayout.CENTER);
		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
		return row;
	}

	private static String multiplierText(double multiplier)
	{
		return "x" + String.format(multiplier >= 10d ? "%.0f" : "%.2f", multiplier);
	}
	private void buildSelectedSet(DopamineState state)
	{
		if (allSets)
		{
			buildAllSets(state);
			return;
		}
		List<Card> cards = visibleCards();
		int owned = ownedIn(state, selectedSet);
		int columns = cardColumns();
		int cardWidth = cardWidthFor(columns);
		cardsContent.add(sectionLabel(selectedSet.getDisplayName(),
			cardSearch.isEmpty()
				? owned + "/" + cards.size() + " cards"
				: cards.size() + " matching"));
		PointSource powers = CollectionBonus.sourceFor(selectedSet);
		JLabel effect = new JLabel(powers.getDisplayName() + " "
			+ multiplierText(CollectionBonus.multiplierFor(state, powers)));
		effect.setFont(FontManager.getRunescapeSmallFont());
		effect.setForeground(GOLD);
		effect.setAlignmentX(Component.LEFT_ALIGNMENT);
		effect.setIconTextGap(4);
		BufferedImage sourceIcon = plugin.getGameIcons().forSource(powers);
		if (sourceIcon != null)
		{
			effect.setIcon(new ImageIcon(sourceIcon));
		}
		cardsContent.add(effect);
		cardsContent.add(Box.createVerticalStrut(6));
		if (cardSearch.isEmpty())
		{
			buildCollections(state);
		}

		if (cards.isEmpty())
		{
			cardsContent.add(hint("No cards in " + selectedSet.getDisplayName()
				+ " match \"" + cardSearch + "\"."));
			return;
		}
		buildCardGrid(state, cards, columns, cardWidth);
	}

	private void buildAllSets(DopamineState state)
	{
		List<Card> cards = visibleCards();
		int columns = cardColumns();
		int cardWidth = cardWidthFor(columns);
		cardsContent.add(sectionLabel("All Sets",
			cardSearch.isEmpty()
				? state.getUniqueCardsOwned() + "/" + CardCatalogue.size() + " cards"
				: cards.size() + " matching"));

		cardsContent.add(hint("Curated packs and banner pulls still draw from "
			+ selectedSet.getDisplayName() + "."));
		cardsContent.add(Box.createVerticalStrut(6));

		if (cards.isEmpty())
		{
			cardsContent.add(hint("No card in any set matches \"" + cardSearch + "\"."));
			return;
		}

		Map<CardSet, List<Card>> bySet = new EnumMap<>(CardSet.class);
		for (Card card : cards)
		{
			bySet.computeIfAbsent(card.getSet(), k -> new ArrayList<>()).add(card);
		}

		if (!cardSearch.isEmpty())
		{
			buildAllSetsMatches(state, bySet, cards.size(), columns, cardWidth);
			return;
		}

		for (CardSet set : CardSet.values())
		{
			List<Card> inSet = bySet.get(set);
			if (inSet == null)
			{
				continue;
			}
			boolean open = set == expandedInAll;
			cardsContent.add(setToggle(state, set, inSet, open));
			cardsContent.add(Box.createVerticalStrut(3));
			if (open)
			{
				buildCardGrid(state, inSet, columns, cardWidth);
				cardsContent.add(Box.createVerticalStrut(4));
			}
		}
	}

	private StoneButton setToggle(DopamineState state, CardSet set, List<Card> inSet, boolean open)
	{
		int owned = ownedIn(state, inSet);
		StoneButton toggle = new StoneButton((open ? "▾ " : "▸ ") + set.getDisplayName()
			+ "    " + owned + "/" + inSet.size());
		toggle.withAccent(owned == inSet.size() ? GOLD : Skin.WHITE);
		toggle.setHorizontalAlignment(SwingConstants.LEFT);
		toggle.setMargin(new Insets(3, 5, 3, 5));
		toggle.setAlignmentX(Component.LEFT_ALIGNMENT);
		toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		toggle.addActionListener(e ->
		{
			expandedInAll = open ? null : set;
			selectedCard = null;
			rebuild();
		});
		return toggle;
	}

	private void buildAllSetsMatches(DopamineState state, Map<CardSet, List<Card>> bySet,
		int total, int columns, int cardWidth)
	{
		int shown = 0;
		for (CardSet set : CardSet.values())
		{
			List<Card> inSet = bySet.get(set);
			if (inSet == null)
			{
				continue;
			}
			if (shown >= MAX_MATCHES)
			{
				break;
			}
			List<Card> drawn = inSet.size() > MAX_MATCHES - shown
				? inSet.subList(0, MAX_MATCHES - shown)
				: inSet;
			cardsContent.add(sectionLabel(set.getDisplayName(),
				ownedIn(state, inSet) + "/" + inSet.size()));
			cardsContent.add(Box.createVerticalStrut(3));
			buildCardGrid(state, drawn, columns, cardWidth);
			cardsContent.add(Box.createVerticalStrut(4));
			shown += drawn.size();
		}
		if (shown < total)
		{
			cardsContent.add(hint("Showing " + shown + " of " + total
				+ " matches. Narrow the search to see the rest."));
		}
	}

	private void buildCardGrid(DopamineState state, List<Card> cards, int columns, int cardWidth)
	{
		for (int start = 0; start < cards.size(); start += columns)
		{
			int end = Math.min(start + columns, cards.size());
			List<Card> row = cards.subList(start, end);
			cardsContent.add(cardRow(state, row, columns, cardWidth));
			cardsContent.add(Box.createVerticalStrut(4));

			if (selectedCard != null && row.contains(selectedCard))
			{
				cardsContent.add(cardDetail(state, selectedCard));
				cardsContent.add(Box.createVerticalStrut(4));
			}
		}
	}
	private void buildCollections(DopamineState state)
	{
		List<CardCollection> collections = CardCollection.inSet(selectedSet);
		if (collections.isEmpty())
		{
			return;
		}
		int done = CardCollection.tiersIn(state, selectedSet);

		StoneButton toggle = new StoneButton((collectionsExpanded ? "▾" : "▸")
			+ " Collections    " + done + "/" + CardCollection.maxTiersIn(selectedSet)
			+ "    all of them " + multiplierText(1d + CardCollection.FULL_COLLECTION_BONUS));
			toggle.withAccent(done > 0 ? GOLD : Skin.WHITE);
		toggle.setHorizontalAlignment(SwingConstants.LEFT);
		toggle.setMargin(new Insets(3, 5, 3, 5));
		toggle.setAlignmentX(Component.LEFT_ALIGNMENT);
		toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		toggle.addActionListener(e ->
		{
			collectionsExpanded = !collectionsExpanded;
			rebuild();
		});
		cardsContent.add(toggle);
		if (!collectionsExpanded)
		{
			cardsContent.add(Box.createVerticalStrut(6));
			return;
		}
		cardsContent.add(Box.createVerticalStrut(3));
		List<CardCollection> ordered = new ArrayList<>(collections);
		ordered.sort(Comparator.comparingDouble(
			(CardCollection c) -> -(c.ownedIn(state) / (double) Math.max(1, c.size()))));
		for (CardCollection collection : ordered)
		{
			cardsContent.add(collectionRow(state, collection));
			cardsContent.add(Box.createVerticalStrut(3));
		}
		cardsContent.add(Box.createVerticalStrut(6));
	}

	private JPanel collectionRow(DopamineState state, CardCollection collection)
	{
		int owned = collection.ownedIn(state);
		int tier = collection.tierIn(state);
		boolean complete = tier > 0;
		JPanel row = new JPanel(new BorderLayout(6, 0));
		row.setBackground(Skin.CARD_DEEP);
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 2, 0, 0, complete ? GOLD : Color.DARK_GRAY),
			BorderFactory.createEmptyBorder(4, 6, 4, 6)));
		int ascension = state.getAscension(collection.getName());
		JLabel name = new JLabel(collection.getName()
			+ (ascension > 0 ? "  ✦" + ascension : ""));
		name.setFont(FontManager.getRunescapeSmallFont());
		name.setForeground(complete ? GOLD : Skin.WHITE);
		row.add(name, BorderLayout.WEST);
		JLabel progress = new JLabel(complete
			? collection.tierNameIn(state) + "  "
				+ multiplierText(CardCollection.multiplierFor(state, collection.getSet()))
			: owned + "/" + collection.size());
		progress.setFont(FontManager.getRunescapeSmallFont());
		progress.setForeground(complete ? GOLD : Skin.MUTED);
		row.add(progress, BorderLayout.EAST);
		StringBuilder members = new StringBuilder(collection.getDescription());
		members.append("  \u2022  ").append(owned).append('/').append(collection.size())
			.append(" owned");
		for (Card card : collection.getCards())
		{
			if (!state.owns(card.getId()))
			{
				members.append("  \u2022  need ").append(card.getName());
			}
		}
		if (ascension > 0)
		{
			members.append("  •  ascended ").append(ascension).append(" times, ")
				.append(multiplierText(1d + collection.bonusFromAscension(state)))
				.append(" for good");
		}
		row.setToolTipText(members.toString());
		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));

		if (!collection.isMaxed(state))
		{
			row.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));
			return row;
		}

		long cost = collection.ascensionCost(state);
		boolean afford = state.getDust() >= cost;
		StoneButton ascend = new StoneButton(afford
			? "Ascend  " + BigNumbers.format(cost) + " dust"
			: "Ascend  " + BigNumbers.format(cost) + " dust needed");
		ascend.withAccent(afford ? GOLD : Skin.MUTED);
		ascend.setEnabled(afford);
		ascend.setToolTipText("Resets every card in " + collection.getName()
			+ " to no copies. Keeps "
			+ multiplierText(1d + CardCollection.BONUS_PER_ASCENSION) + " on it for good.");
		ascend.addActionListener(e -> plugin.ascendCollection(collection.getName()));

		JPanel block = new JPanel();
		block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
		block.setBackground(Skin.CARD_DEEP);
		block.setAlignmentX(Component.LEFT_ALIGNMENT);
		block.add(row);
		block.add(ascend);
		block.setMaximumSize(new Dimension(Integer.MAX_VALUE, block.getPreferredSize().height));
		return block;
	}

	private int cardColumns()
	{
		int available = availableWidth();
		int fits = (available + CARD_GAP) / (CARD_MIN_WIDTH + CARD_GAP);
		return Math.max(CARD_MIN_COLUMNS, Math.min(CARD_MAX_COLUMNS, fits));
	}

	private int cardWidthFor(int columns)
	{
		int available = availableWidth() - (columns - 1) * CARD_GAP;
		return Math.max(CARD_MIN_WIDTH, Math.min(CARD_MAX_WIDTH, available / columns));
	}
	private JPanel cardRow(DopamineState state, List<Card> row, int columns, int cardWidth)
	{
		JPanel grid = new JPanel(new GridLayout(1, columns, CARD_GAP, 0))
		{
			@Override
			public Dimension getMaximumSize()
			{
				return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
			}
		};
		grid.setBackground(Skin.BG);
		grid.setAlignmentX(Component.LEFT_ALIGNMENT);
		for (Card card : row)
		{
			int copies = state.getCopies(card.getId());
			int stars = state.getStars(card.getId());
			CardComponent component = new CardComponent(card, stars, copies > 0, cardWidth,
				plugin.getCardArtService(), state.isShiny(card.getId()),
				state.isGilded(card.getId()));
			component.setToolTipText(cardTooltip(card, copies, stars, copies > 0));
			component.setOnClick(clicked ->
			{
				selectedCard = clicked.equals(selectedCard) ? null : clicked;
				rebuild();
			});
			grid.add(component);
		}

		for (int i = row.size(); i < columns; i++)
		{
			JPanel filler = new JPanel();
			filler.setBackground(Skin.BG);
			grid.add(filler);
		}
		return grid;
	}

	private JPanel cardDetail(DopamineState state, Card card)
	{
		int copies = state.getCopies(card.getId());
		int stars = state.getStars(card.getId());
		boolean owned = copies > 0;
		JPanel detail = new JPanel(new BorderLayout(8, 0));
		detail.setBackground(Skin.CARD_DEEP);
		detail.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 2, 0, 0, card.getRarity().getColour()),
			BorderFactory.createEmptyBorder(8, 8, 8, 8)));
		detail.setAlignmentX(Component.LEFT_ALIGNMENT);
		CardComponent big = new CardComponent(card, stars, owned, 72, plugin.getCardArtService(),
			state.isShiny(card.getId()), state.isGilded(card.getId()));
		big.playIntro();

		big.setOnClick(plugin::openCardViewer);
		big.setToolTipText("Click to view in-game");
		detail.add(big, BorderLayout.WEST);
		JPanel text = new JPanel();
		text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
		text.setBackground(Skin.CARD_DEEP);
		JLabel name = new JLabel(owned ? card.getName() : "???");
		name.setFont(FontManager.getRunescapeBoldFont());
		name.setForeground(card.getRarity().getColour());
		name.setAlignmentX(Component.LEFT_ALIGNMENT);
		text.add(name);
		JLabel meta = new JLabel(card.getRarity().getDisplayName()
			+ "  •  " + card.getSet().getDisplayName());
		meta.setFont(FontManager.getRunescapeSmallFont());
		meta.setForeground(Skin.MUTED);
		meta.setAlignmentX(Component.LEFT_ALIGNMENT);
		text.add(meta);

		CardOrigin origin = CardOrigins.of(card);
		if (origin.isExclusive())
		{
			JLabel source = new JLabel(origin.getDisplayName() + "  •  "
				+ origin.getShortHint());
			source.setFont(FontManager.getRunescapeSmallFont());
			source.setForeground(origin.getColour());
			source.setAlignmentX(Component.LEFT_ALIGNMENT);
			text.add(source);
		}
		text.add(Box.createVerticalStrut(6));
		if (!owned)
		{
			text.add(hint(origin.isExclusive()
				? "Not owned. " + origin.getDescription()
				: "Not owned. A Curated pack aimed at "
					+ card.getSet().getDisplayName() + " is the best source.",
				detailTextWidth()));
			detail.add(text, BorderLayout.CENTER);
			return capHeight(detail);
		}
		JLabel tier = new JLabel(stars + " / " + Rarity.MAX_STARS + "★   •   "
			+ copies + " copies");
		tier.setFont(FontManager.getRunescapeSmallFont());
		tier.setForeground(Skin.WHITE);
		tier.setAlignmentX(Component.LEFT_ALIGNMENT);
		text.add(tier);
		text.add(Box.createVerticalStrut(4));
		text.add(cardEffectLine(state, card, stars));

		int maxStars = card.getSet().isUnlockSet() ? 1 : Rarity.MAX_STARS;
		if (stars < maxStars)
		{
			int per = Dust.costPerCopy(card.getRarity());
			boolean enough = state.getDust() >= per;
			StoneButton buy = new StoneButton(enough
				? "Spend " + per + " dust  (+1 copy)"
				: per + " dust needed  (" + state.getDust() + ")");
			buy.withAccent(enough ? GOLD : Skin.MUTED);
			buy.setEnabled(enough);
			buy.setAlignmentX(Component.LEFT_ALIGNMENT);
			buy.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
			buy.setToolTipText("Dust comes from spare copies of finished cards."
				+ " It is the only way to pick a specific card.");
			buy.addActionListener(e -> plugin.spendDustOn(card, 1));
			text.add(Box.createVerticalStrut(4));
			text.add(buy);
		}

		int next = card.getRarity().copiesForNextStar(copies);
		if (next > 0)
		{
			int previous = stars == 0 ? 0 : card.getRarity().starThresholds()[stars - 1];
			int span = Math.max(1, next - previous);
			JProgressBar bar = new JProgressBar(0, span);
			bar.setValue(Math.max(0, copies - previous));
			bar.setStringPainted(true);
			bar.setString((next - copies) + " more for " + (stars + 1) + "★");
			bar.setFont(FontManager.getRunescapeSmallFont());
			bar.setForeground(card.getRarity().getColour());
			bar.setBackground(Skin.BG);
			bar.setAlignmentX(Component.LEFT_ALIGNMENT);
			bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 16));
			text.add(bar);
		}
		else
		{
			text.add(hint("Fully upgraded.", detailTextWidth()));
		}
		detail.add(text, BorderLayout.CENTER);
		return capHeight(detail);
	}

	private static JPanel capHeight(JPanel panel)
	{
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));
		return panel;
	}

	private JComponent cardEffectLine(DopamineState state, Card card, int stars)
	{
		PointSource powers = CollectionBonus.sourceFor(card.getSet());
		StringBuilder text = new StringBuilder();
		String affinity = CardAffinity.describe(card);
		if (affinity != null)
		{
			text.append(affinity).append(": +")
				.append(CardAffinity.percentFor(state, card)).append('%');
			if (stars < Rarity.MAX_STARS)
			{
				text.append(" (+").append(CardAffinity.percentAtStars(stars + 1))
					.append("% at ").append(stars + 1).append("★)");
			}
		}

		for (CardCollection collection : CardCollection.forCard(card))
		{
			if (text.length() > 0)
			{
				text.append("  ");
			}
			text.append(collection.getName()).append(": ")
				.append(collection.ownedIn(state)).append('/')
				.append(collection.size()).append('.');
		}
		WrappedLabel label = new WrappedLabel(text.toString(),
			FontManager.getRunescapeSmallFont(), GOLD, Math.max(60, detailTextWidth()));
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		return label;
	}
	private int detailTextWidth()
	{
		return availableWidth() - 106;
	}
	private static int ownedIn(DopamineState state, CardSet set)
	{
		return ownedIn(state, CardCatalogue.bySet(set));
	}
	private static int ownedIn(DopamineState state, List<Card> cards)
	{
		return (int) cards.stream().filter(c -> state.owns(c.getId())).count();
	}
	private static String cardTooltip(Card card, int copies, int stars, boolean owned)
	{
		if (!owned)
		{
			return "??? \u2022 " + card.getRarity().getDisplayName() + ", not yet found";
		}
		int next = card.getRarity().copiesForNextStar(copies);
		return card.getName()
			+ "  \u2022  " + card.getRarity().getDisplayName()
			+ "  \u2022  " + stars + "\u2605"
			+ "  \u2022  " + copies + " copies"
			+ (next > 0 ? "  \u2022  " + (next - copies) + " more for " + (stars + 1) + "\u2605"
				: "  \u2022  maxed");
	}
	private void startCascade()
	{
		if (cascadeTimer != null && cascadeTimer.isRunning())
		{
			return;
		}
		if (plugin.getRewards().isEmpty())
		{
			return;
		}

		cascadeTimer = new Timer(CASCADE_INTERVAL_MS, null);
		cascadeTimer.addActionListener(e ->
		{
			RewardQueue queue = plugin.getRewards();
			Reward reward = queue == null ? null : queue.claim();
			if (reward == null)
			{
				cascadeTimer.stop();
				rebuild();
				return;
			}

			try
			{
				acceptRevealed(reward);
			}
			catch (RuntimeException ex)
			{
				LOGGER.warn("Reveal effects failed for {}", reward.getTitle(), ex);
			}
			if (selectedTab == Tab.PLAY)
			{
				rebuild();
			}
		});
		cascadeTimer.start();
	}

	private void revealEverythingNow()
	{
		if (cascadeTimer != null)
		{
			cascadeTimer.stop();
		}
		for (Reward reward : plugin.getRewards().claimAll())
		{
			acceptRevealed(reward);
		}
		rebuild();
	}
	private void acceptRevealed(Reward reward)
	{
		plugin.flash(reward);
	}
	private JPanel revealQueueStrip()
	{
		int depth = plugin.getRewards().depth();
		if (depth <= 0)
		{
			return null;
		}

		JPanel header = new JPanel(new BorderLayout(4, 0));
		header.setBackground(Skin.BG);
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));

		JLabel label = new JLabel(depth + (depth == 1 ? " card waiting" : " cards waiting"));
		label.setFont(FontManager.getRunescapeBoldFont());
		label.setForeground(GOLD);
		header.add(label, BorderLayout.WEST);

		StoneButton skip = new StoneButton(config.autoReveal() ? "Skip" : "Reveal");
		skip.addActionListener(e -> revealEverythingNow());
		header.add(skip, BorderLayout.EAST);
		return header;
	}

	private Segmented buildQuantitySelector()
	{
		int[] quantities = {1, 5, 10, 25};
		String[] labels = new String[quantities.length];
		int selected = 0;
		for (int i = 0; i < quantities.length; i++)
		{
			labels[i] = "x" + quantities[i];
			if (buyQuantity == quantities[i])
			{
				selected = i;
			}
		}
		Segmented row = new Segmented(labels, selected, index ->
		{
			buyQuantity = quantities[index];
			rebuild();
		});
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		return row;
	}

	private PointsHeader pointsLine(DopamineState state)
	{
		double perHour = plugin.getIncomeTracker().totalPerHour(state.getTick());
		PointsHeader header = new PointsHeader(state.getPoints(), perHour,
			plugin.getClickState() != null
				&& plugin.getClickState().isSurging(System.currentTimeMillis()));
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 62));
		return header;
	}
	private JComponent prestigeBlock(DopamineState state)
	{
		double lifetime = state.getLifetimePoints();
		int insight = state.getInsight();
		int gain = Prestige.gainFrom(lifetime, insight);
		boolean ready = Prestige.canPrestige(lifetime);

		JPanel block = new JPanel();
		block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
		block.setBackground(Skin.BG);
		block.setAlignmentX(Component.LEFT_ALIGNMENT);

		int free = state.getFreeInsight();
		boolean maxed = Prestige.isMaxed(insight);

		if (free > 0)
		{
			block.add(sectionLabel("Insight", free + " insight left"));
		}
		else if (insight > 0)
		{
			block.add(sectionLabel("Insight", "all " + insight + " spent"));
		}
		else
		{
			block.add(sectionLabel("Insight"));
		}

		if (!ready && insight == 0)
		{
			block.add(hint("Reset points and upgrades for insight, which buys permanent"
				+ " perks. Cards are kept. Needs "
				+ BigNumbers.format(Prestige.pointsUntilPrestige(lifetime)) + " more earned."));
			return block;
		}

		if (insight > 0)
		{
			block.add(perkList(state));
			block.add(Box.createVerticalStrut(6));
		}

		if (!ready)
		{
			block.add(hint("Earn " + BigNumbers.format(Prestige.pointsUntilPrestige(lifetime))
				+ " more this run before starting again."));
			return block;
		}

		String why;
		if (gain > 0)
		{
			why = "Clears points and upgrade levels.";
		}
		else if (maxed)
		{
			why = "Every insight there is has been earned, so this adds none. Resetting"
				+ " still hands back every rank to spend again.";
		}
		else
		{
			why = "This run has not beaten your best, so it adds no insight. Resetting"
				+ " still hands back every rank to spend again.";
		}
		block.add(hint(why + " Cards, stars, dust and collections are kept."));
		block.add(Box.createVerticalStrut(4));

		StoneButton go = new StoneButton(gain > 0 ? "Reset for " + gain + " insight" : "Reset");
		go.setAlignmentX(Component.LEFT_ALIGNMENT);
		go.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
		go.setToolTipText(maxed
			? "Every insight there is has been earned. Resetting refunds your ranks."
			: "Insight is your best run, not a running total."
				+ " Only a better run adds more.");
		go.addActionListener(e -> plugin.prestige());
		block.add(go);

		if (gain == 0 && !maxed)
		{
			block.add(Box.createVerticalStrut(4));
			block.add(hint("Reach " + BigNumbers.format(Prestige.lifetimeForInsight(insight + 1))
				+ " in one run for the next insight. "
				+ BigNumbers.format(Prestige.lifetimeForInsight(insight + 1) - lifetime)
				+ " to go."));
		}
		return block;
	}

	private JComponent perkList(DopamineState state)
	{
		JPanel list = new JPanel();
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		list.setBackground(Skin.BG);
		list.setAlignmentX(Component.LEFT_ALIGNMENT);

		for (InsightPerk perk : InsightPerk.values())
		{
			int rank = state.getPerkRank(perk);
			boolean full = rank >= perk.getMaxRanks();
			boolean affordable = !full && state.getFreeInsight() >= perk.getCostPerRank();

			ShopRow row = new ShopRow(
				perk.getDisplayName(),
				perk.effectAt(full ? rank : rank + 1),
				full ? 0d : perk.getCostPerRank(),
				perk.getColour(),
				full ? "MAX" : rank + "/" + perk.getMaxRanks(),
				affordable,
				full ? 1d : (double) state.getFreeInsight() / perk.getCostPerRank(),
				r -> plugin.allocatePerk(perk));
			row.setToolTipText(perk.getDescription()
				+ "  •  " + perk.getCostPerRank() + " insight a rank"
				+ "  •  kept until your next reset"
				+ (full ? "  •  fully bought"
					: "  •  now " + perk.effectAt(rank) + ", next " + perk.effectAt(rank + 1)));
			row.setAlignmentX(Component.LEFT_ALIGNMENT);
			list.add(row);
			list.add(Box.createVerticalStrut(3));
		}

		if (state.getSpentInsight() > 0)
		{
			list.add(hint("Ranks stay put until you reset, which hands them all back."));
		}
		return list;
	}

	private WrappedLabel featLine(DopamineState state)
	{
		int ranks = Feats.tiersEarned(state);
		Feat closest = null;
		double best = -1d;
		for (Feat feat : Feat.values())
		{
			long progress = Feats.progressOf(state, feat);
			long next = feat.nextThreshold(progress);
			if (next <= 0)
			{
				continue;
			}
			double fraction = progress / (double) next;
			if (fraction > best)
			{
				best = fraction;
				closest = feat;
			}
		}

		String next = closest == null
			? "every rank earned"
			: "next: " + closest.getDisplayName() + " at "
				+ BigNumbers.format(closest.nextThreshold(Feats.progressOf(state, closest)))
				+ " " + closest.getTrack().getUnit();
		return hint("Feats x" + String.format("%.2f", Feats.multiplierFor(state))
			+ " from " + ranks + " ranks, " + next);
	}

	private WrappedLabel milestoneLine(DopamineState state)
	{
		double next = Milestones.nextAt(state.getLifetimePoints());
		return hint("Milestone bonus x"
			+ String.format("%.1f", Milestones.globalMultiplier(state.getLifetimePoints(), state))
			+ (next > 0 ? ", next at " + BigNumbers.format(next) + " lifetime" : ", all earned"));
	}

	private void buildFeatsTab(DopamineState state)
	{
		featsContent.add(featsToggle());
		featsContent.add(Box.createVerticalStrut(8));

		if (showingAchievements)
		{
			buildAchievements(state);
			return;
		}

		int earned = Feats.tiersEarned(state);
		int total = Feat.totalTiers();

		JLabel header = new JLabel(Feats.titleFor(state));
		header.setFont(FontManager.getRunescapeBoldFont());
		header.setForeground(GOLD);
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		featsContent.add(header);
		featsContent.add(Box.createVerticalStrut(3));

		JProgressBar overall = new JProgressBar(0, Math.max(1, total));
		overall.setValue(earned);
		overall.setStringPainted(true);
		overall.setString(earned + "/" + total + " ranks earned");
		overall.setFont(FontManager.getRunescapeSmallFont());
		overall.setForeground(GOLD);
		overall.setBackground(Skin.CARD_DEEP);
		overall.setAlignmentX(Component.LEFT_ALIGNMENT);
		overall.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
		featsContent.add(overall);
		featsContent.add(Box.createVerticalStrut(5));
		featsContent.add(hint("x" + String.format("%.2f", Feats.multiplierFor(state))
			+ " to everything you earn. Feats come from playing, not points."));
		featsContent.add(Box.createVerticalStrut(8));

		for (Feat feat : Feat.values())
		{
			featsContent.add(featRow(state, feat));
		}
	}

	private Segmented featsToggle()
	{
		Segmented row = new Segmented(new String[]{"Ranks", "Achievements"},
			showingAchievements ? 1 : 0, index ->
			{
				showingAchievements = index == 1;
				rebuild();
			});
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		return row;
	}

	private void buildAchievements(DopamineState state)
	{
		int earned = 0;
		for (Achievement achievement : Achievement.values())
		{
			earned += state.hasAchievement(achievement.name()) ? 1 : 0;
		}

		JProgressBar overall = new JProgressBar(0, Achievement.values().length);
		overall.setValue(earned);
		overall.setStringPainted(true);
		overall.setString(earned + "/" + Achievement.values().length + " earned");
		overall.setFont(FontManager.getRunescapeSmallFont());
		overall.setForeground(GOLD);
		overall.setBackground(Skin.CARD_DEEP);
		overall.setAlignmentX(Component.LEFT_ALIGNMENT);
		overall.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
		featsContent.add(overall);
		featsContent.add(Box.createVerticalStrut(5));
		featsContent.add(Box.createVerticalStrut(8));

		for (Achievement achievement : Achievement.values())
		{
			featsContent.add(achievementRow(state, achievement));
			featsContent.add(Box.createVerticalStrut(2));
		}
	}

	private JPanel achievementRow(DopamineState state, Achievement achievement)
	{
		boolean earned = state.hasAchievement(achievement.name());
		boolean secret = achievement.isHidden() && !earned;

		JPanel row = new JPanel(new BorderLayout(6, 0));
		row.setBackground(Skin.CARD_DEEP);
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
		row.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 2, 0, 0, earned ? GOLD : Color.DARK_GRAY),
			BorderFactory.createEmptyBorder(4, 6, 4, 6)));

		JLabel name = new JLabel(secret ? "Hidden" : achievement.getDisplayName());
		name.setFont(FontManager.getRunescapeSmallFont());
		name.setForeground(earned ? GOLD : Skin.WHITE);
		row.add(name, BorderLayout.NORTH);

		JLabel detail = new JLabel(secret
			? "Earn it to find out what it was"
			: achievement.getDescription());
		detail.setFont(FontManager.getRunescapeSmallFont());
		detail.setForeground(Skin.MUTED);
		row.add(detail, BorderLayout.SOUTH);
		return row;
	}

	private FeatRow featRow(DopamineState state, Feat feat)
	{
		long progress = Feats.progressOf(state, feat);
		int tier = Feats.tierOf(state, feat);
		long next = feat.nextThreshold(progress);
		String unit = feat.getTrack().getUnit();

		String detail = tier >= feat.maxTier()
			? "Mastered  •  " + BigNumbers.format(progress) + " " + unit
			: BigNumbers.format(progress) + " / " + BigNumbers.format(next) + " " + unit;

		FeatRow row = new FeatRow(feat, tier, detail,
			next <= 0 ? 1d : progress / (double) next);
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.setToolTipText(feat.getDescription()
			+ "  •  " + (tier >= feat.maxTier() ? "mastered"
				: "rank " + (tier + 1) + " adds "
					+ String.format("%.1f", (tier + 1) * Feat.BONUS_PER_RANK_SHARE * 100d)
					+ "% to everything you earn"));
		return row;
	}

	@Override
	public void onActivate()
	{
		super.onActivate();
		surgeTimer.start();
		rebuild();
	}

	@Override
	public void onDeactivate()
	{
		super.onDeactivate();
		surgeTimer.stop();
	}

	public void dispose()
	{
		surgeTimer.stop();
		resizeTimer.stop();
		if (cascadeTimer != null)
		{
			cascadeTimer.stop();
			cascadeTimer = null;
		}
		if (clickButton != null)
		{
			clickButton.dispose();
		}
	}

	private JComponent buildBackSelector(DopamineState state)
	{
		List<CardBack> owned = new ArrayList<>();
		for (CardBack back : CardBack.values())
		{
			if (state.hasBack(back.name()))
			{
				owned.add(back);
			}
		}
		if (owned.size() < 2)
		{
			return null;
		}

		JComboBox<CardBack> picker = new JComboBox<>(owned.toArray(new CardBack[0]));
		picker.setSelectedItem(CardBack.byId(state.getSelectedBack()));
		picker.setFont(FontManager.getRunescapeSmallFont());
		picker.setFocusable(false);
		picker.setBackground(Skin.CARD_DEEP);
		picker.setForeground(Skin.WHITE);
		picker.setAlignmentX(Component.LEFT_ALIGNMENT);
		picker.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
		picker.setToolTipText("Card back shown while a pack is flipping");
		picker.setRenderer(new DefaultListCellRenderer()
		{
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index,
				boolean selected, boolean focus)
			{
				super.getListCellRendererComponent(list, value, index, selected, focus);
				CardBack back = (CardBack) value;
				setText("Back: " + back.getDisplayName());
				setForeground(selected ? Color.WHITE : back.getTrim());
				return this;
			}
		});
		picker.addActionListener(e ->
		{
			CardBack chosen = (CardBack) picker.getSelectedItem();
			if (chosen != null && !chosen.name().equals(state.getSelectedBack()))
			{
				plugin.selectCardBack(chosen.name());
			}
		});
		return picker;
	}

	private ShopRow sized(ShopRow row)
	{
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, ShopRow.HEIGHT));
		return row;
	}
	private SectionHeader sectionLabel(String text)
	{
		return sectionLabel(text, null);
	}
	private SectionHeader sectionLabel(String text, String trailing)
	{
		SectionHeader header = new SectionHeader(text, trailing);
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
		return header;
	}
	private WrappedLabel hint(String text)
	{
		return hint(text, availableWidth());
	}
	private WrappedLabel hint(String text, int width)
	{
		WrappedLabel label = new WrappedLabel(text, FontManager.getRunescapeFont(),
			Skin.MUTED, Math.max(60, width));
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		return label;
	}
	private int availableWidth()
	{
		int viewport = scrollPane.getViewport().getWidth();
		return viewport > 40 ? viewport - 8 : PANEL_WIDTH - 30;
	}
}
