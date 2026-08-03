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

import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.core.PointListener;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.core.RewardType;
import com.dopaminesimulator.incremental.BigNumbers;
import com.dopaminesimulator.points.PointSource;
import com.dopaminesimulator.ui.GameIcons;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class FloatingTextOverlay extends Overlay implements PointListener
{
	private static final int MAX_VISIBLE = 12;
	private static final long NORMAL_DURATION_MS = 1600L;
	private static final long MAJOR_DURATION_MS = 2600L;
	private static final int RISE_FROM = 60;
	private static final int RISE_TO = 230;
	private static final int LANE_HEIGHT = 15;
	private static final long SIMULTANEOUS_WINDOW_MS = 220L;

	private static final int LANES = 5;

	private static final float HOLD_FRACTION = 0.5f;
	private static final long TRICKLE_FLUSH_INTERVAL_MS = 5000L;
	private static final double MIN_POINTS_TO_SHOW = 1d;
	private final Client client;
	private final DopamineSimulatorConfig config;
	private final GameIcons icons;
	private final Deque<FloatingText> texts = new ConcurrentLinkedDeque<>();
	private final Map<PointSource, Double> trickle = new EnumMap<>(PointSource.class);
	private long lastTrickleFlush;

	private long lastPushAt;
	private int lastLane;

	private static final int ICON_SIZE = 16;
	private static final int ICON_GAP = 3;
	private static final class FloatingText
	{
		private final String text;
		private final Color colour;
		private final float fontSize;
		private final long start;
		private final long duration;
		private final int lane;
		private final BufferedImage icon;
		private FloatingText(String text, Color colour, float fontSize, long duration, int lane,
							 BufferedImage icon)
		{
			this.text = text;
			this.colour = colour;
			this.fontSize = fontSize;
			this.duration = duration;
			this.lane = lane;
			this.icon = icon;
			this.start = System.currentTimeMillis();
		}
		private long age()
		{
			return System.currentTimeMillis() - start;
		}
		private boolean expired()
		{
			return age() > duration;
		}
		private float progress()
		{
			return Math.min(1f, age() / (float) duration);
		}
		private float alpha()
		{
			float progress = progress();
			if (progress < HOLD_FRACTION)
			{
				return 1f;
			}
			return Math.max(0f, 1f - (progress - HOLD_FRACTION) / (1f - HOLD_FRACTION));
		}
	}
	FloatingTextOverlay(Client client, DopamineSimulatorConfig config, GameIcons icons)
	{
		this.client = client;
		this.config = config;
		this.icons = icons;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}
	@Override
	public void onPointsGained(PointSource source, String detail, double amount, long tick)
	{
		if (isContinuous(source))
		{
			trickle.merge(source, amount, Double::sum);
			return;
		}
		if (!config.showFloatingText() || !config.showEnergyFloaters() || amount <= 0d)
		{
			return;
		}
		push("+" + BigNumbers.format(amount), source.getColour(), 13f, NORMAL_DURATION_MS,
			icons.forSource(source));
	}
	private static boolean isContinuous(PointSource source)
	{
		return source == PointSource.IDLING;
	}
	public void flushPending()
	{
		if (!config.showFloatingText() || !config.showEnergyFloaters())
		{
			trickle.clear();
			return;
		}
		long now = System.currentTimeMillis();
		if (now - lastTrickleFlush < TRICKLE_FLUSH_INTERVAL_MS)
		{
			return;
		}
		boolean shown = false;
		for (PointSource source : PointSource.values())
		{
			double accumulated = trickle.getOrDefault(source, 0d);
			if (accumulated < MIN_POINTS_TO_SHOW)
			{
				continue;
			}
			trickle.put(source, 0d);
			shown = true;
			push("+" + BigNumbers.format(accumulated), source.getColour(), 12f, NORMAL_DURATION_MS,
				icons.forSource(source));
		}
		if (shown)
		{
			lastTrickleFlush = now;
		}
	}

	public void reward(Reward reward)
	{
		if (!config.showFloatingText() || !config.showCardFloaters() || reward == null)
		{
			return;
		}
		if (reward.getType() == RewardType.SOURCE_UNLOCKED)
		{
			return;
		}
		boolean major = reward.getType() == RewardType.SET_COMPLETE
			|| (reward.getRarity() != null && reward.getRarity().ordinal() >= Rarity.EPIC.ordinal());
		Color colour = reward.getRarity() != null ? reward.getRarity().getColour() : Color.WHITE;
		String text;
		switch (reward.getType())
		{
			case DUPLICATE:
				text = reward.getTitle() + " +" + reward.getAmount();
				break;
			case STAR_UP:
				text = reward.getTitle() + " " + reward.getAmount() + "*";
				break;
			default:
				text = reward.getTitle();
		}
		push(text, colour, major ? 17f : 13f, major ? MAJOR_DURATION_MS : NORMAL_DURATION_MS);
	}
	private void push(String text, Color colour, float fontSize, long duration)
	{
		push(text, colour, fontSize, duration, null);
	}
	private void push(String text, Color colour, float fontSize, long duration, BufferedImage icon)
	{
		long now = System.currentTimeMillis();
		int lane = now - lastPushAt < SIMULTANEOUS_WINDOW_MS ? (lastLane + 1) % LANES : 0;
		lastPushAt = now;
		lastLane = lane;
		texts.addLast(new FloatingText(text, colour, fontSize, duration, lane, icon));
		while (texts.size() > MAX_VISIBLE)
		{
			texts.removeFirst();
		}
	}
	public void clear()
	{
		texts.clear();
		trickle.clear();
		lastLane = 0;
		lastPushAt = 0L;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.showFloatingText())
		{
			texts.clear();
			return null;
		}

		for (Iterator<FloatingText> it = texts.iterator(); it.hasNext(); )
		{
			if (it.next().expired())
			{
				it.remove();
			}
		}
		if (texts.isEmpty())
		{
			return null;
		}
		Player player = client.getLocalPlayer();
		if (player == null)
		{
			return null;
		}
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Composite originalComposite = graphics.getComposite();
		Font originalFont = graphics.getFont();
		for (FloatingText text : texts)
		{
			draw(graphics, player, text);
		}
		graphics.setComposite(originalComposite);
		graphics.setFont(originalFont);
		return null;
	}
	private void draw(Graphics2D graphics, Player player, FloatingText text)
	{
		float alpha = text.alpha();
		if (alpha <= 0f)
		{
			return;
		}
		graphics.setFont(FontManager.getRunescapeBoldFont().deriveFont(Font.BOLD, text.fontSize));

		int zOffset = RISE_FROM + Math.round(text.progress() * (RISE_TO - RISE_FROM));
		Point location = player.getCanvasTextLocation(graphics, text.text, zOffset);
		if (location == null)
		{
			return;
		}

		int iconSpace = text.icon == null ? 0 : ICON_SIZE + ICON_GAP;
		int x = location.getX() + iconSpace / 2;
		int y = location.getY() - text.lane * LANE_HEIGHT;
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		if (text.icon != null)
		{
			Object previous = graphics.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

			graphics.drawImage(text.icon, x - iconSpace, y - ICON_SIZE + 3,
				ICON_SIZE, ICON_SIZE, null);
			if (previous != null)
			{
				graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, previous);
			}
		}
		graphics.setColor(Color.BLACK);
		graphics.drawString(text.text, x + 1, y + 1);
		graphics.setColor(text.colour);
		graphics.drawString(text.text, x, y);
	}
}
