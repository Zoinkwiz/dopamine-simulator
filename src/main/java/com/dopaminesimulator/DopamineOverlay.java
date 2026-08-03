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

import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.IncomeTracker;
import com.dopaminesimulator.core.RewardQueue;
import com.dopaminesimulator.incremental.BigNumbers;
import com.dopaminesimulator.points.ClickState;
import com.dopaminesimulator.points.GnomeFood;
import com.dopaminesimulator.points.PointSource;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Map;
import com.dopaminesimulator.ui.Skin;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.SplitComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class DopamineOverlay extends OverlayPanel
{
	private static final Color POINTS = Skin.ORANGE;
	private static final Color SURGE = new Color(0xFF, 0xE0, 0x82);
	private static final int ICON_SIZE = 16;
	private final DopamineSimulatorPlugin plugin;
	private final DopamineSimulatorConfig config;
	DopamineOverlay(DopamineSimulatorPlugin plugin, DopamineSimulatorConfig config)
	{
		super(plugin);
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.TOP_LEFT);
		panelComponent.setPreferredSize(new Dimension(150, 0));
	}
	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.showOverlay() || plugin.getEngine() == null)
		{
			return null;
		}
		DopamineState state = plugin.getEngine().getState();
		RewardQueue rewards = plugin.getRewards();
		IncomeTracker income = plugin.getIncomeTracker();
		ClickState clicks = plugin.getClickState();
		long now = System.currentTimeMillis();
		if (config.hideOverlayWhenEmpty() && state.getLifetimePoints() <= 0d && rewards.isEmpty())
		{
			return null;
		}

		panelComponent.getChildren().add(TitleComponent.builder()
			.text("Dopamine")
			.color(POINTS)
			.build());

		panelComponent.getChildren().add(LineComponent.builder()
			.left("Points")
			.right(BigNumbers.format(state.getPoints()))
			.rightColor(POINTS)
			.build());
		if (config.showIncomeRate())
		{
			double perHour = income.totalPerHour(state.getTick());

			panelComponent.getChildren().add(LineComponent.builder()
				.left("Rate")
				.right(BigNumbers.format(perHour) + "/hr")
				.rightColor(Color.LIGHT_GRAY)
				.build());
			renderTopSources(state, income);
		}
		if (clicks != null && clicks.isSurging(now))
		{
			GnomeFood serving = clicks.getActive(now);

			// Named, so a dish that quadruples everything says so somewhere.
			double lift = clicks.clickPayoutMultiplier(now);
			panelComponent.getChildren().add(LineComponent.builder()
				.left(serving == null ? "SURGE" : serving.getDisplayName())
				.right((lift > 1d ? "x" + BigNumbers.format(lift) + "  " : "")
					+ String.format("%.0fs", clicks.secondsRemaining(now)))
				.leftColor(SURGE)
				.rightColor(SURGE)
				.build());
		}
		if (!rewards.isEmpty())
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Revealing")
				.right(String.valueOf(rewards.depth()))
				.rightColor(POINTS)
				.build());
		}
		return super.render(graphics);
	}
	private void renderTopSources(DopamineState state, IncomeTracker income)
	{
		Map<PointSource, Double> rates = income.breakdown(state.getTick());

		PointSource best = null;
		PointSource second = null;
		for (Map.Entry<PointSource, Double> entry : rates.entrySet())
		{
			if (entry.getValue() <= 0d)
			{
				continue;
			}
			if (best == null || entry.getValue() > rates.get(best))
			{
				second = best;
				best = entry.getKey();
			}
			else if (second == null || entry.getValue() > rates.get(second))
			{
				second = entry.getKey();
			}
		}
		for (PointSource source : new PointSource[]{best, second})
		{
			if (source == null)
			{
				continue;
			}
			LineComponent line = LineComponent.builder()
				.left(source.getDisplayName())
				.right(BigNumbers.format(rates.get(source)) + "/hr")
				.leftColor(source.getColour())
				.rightColor(Color.GRAY)
				.build();
			BufferedImage icon = plugin.getGameIcons().forSource(source);
			panelComponent.getChildren().add(icon == null ? line
				: SplitComponent.builder()
					.first(new ImageComponent(scaled(icon)))
					.second(line)
					.orientation(ComponentOrientation.HORIZONTAL)
					.gap(new Point(4, 0))
					.build());
		}
	}

	private final java.util.Map<BufferedImage, BufferedImage> scaledCache =
		new java.util.HashMap<>();

	private BufferedImage scaled(BufferedImage source)
	{
		BufferedImage cached = scaledCache.get(source);
		if (cached != null)
		{
			return cached;
		}
		BufferedImage out = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = out.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g.drawImage(source, 0, 0, ICON_SIZE, ICON_SIZE, null);
		g.dispose();
		scaledCache.put(source, out);
		return out;
	}
}
