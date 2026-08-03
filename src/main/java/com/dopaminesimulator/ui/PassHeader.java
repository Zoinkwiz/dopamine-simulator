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
package com.dopaminesimulator.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class PassHeader extends JComponent
{
	public static final int HEIGHT = 84;

	private final int season;
	private final String theme;
	private final Color accent;
	private final int tier;
	private final int tiers;
	private final double into;
	private final double need;
	private final boolean premium;
	private final String remaining;

	public PassHeader(int season, String theme, Color accent, int tier, int tiers,
		double into, double need, boolean premium, String remaining)
	{
		this.season = season;
		this.theme = theme;
		this.accent = Skin.vivid(accent);
		this.tier = tier;
		this.tiers = tiers;
		this.into = into;
		this.need = need;
		this.premium = premium;
		this.remaining = remaining;
		setPreferredSize(new Dimension(0, HEIGHT));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, HEIGHT));
		setMinimumSize(new Dimension(0, HEIGHT));
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		Skin.smooth(g);

		int width = getWidth();
		Skin.hero(g, 0, 0, width, HEIGHT, accent);

		g.setFont(Skin.small());
		FontMetrics small = g.getFontMetrics();
		Skin.text(g, "SEASON " + season, 10, 20, Skin.withAlpha(Skin.GOLD, 200));

		String badge = premium ? "PREMIUM" : "FREE";
		drawBadge(g, width - 10 - small.stringWidth(badge) - 12, 9, badge, small);

		g.setFont(Skin.heading());
		FontMetrics heading = g.getFontMetrics();
		Skin.text(g, Skin.elide(heading, theme, width - 20), 10, 41, Skin.WHITE);

		g.setFont(Skin.small());
		Skin.right(g, remaining, width - 10, 41, Skin.MUTED);

		int barY = HEIGHT - 32;
		Skin.bar(g, 10, barY, width - 20, 13, need <= 0d ? 1d : into / need, accent,
			"TIER " + tier + " / " + tiers);

		Skin.text(g, need <= 0d
			? "Season complete"
			: (long) into + " / " + (long) need + " xp to next tier", 10, HEIGHT - 9, Skin.MUTED);

		g.dispose();
	}

	private void drawBadge(Graphics2D g, int x, int y, String badge, FontMetrics metrics)
	{
		int w = metrics.stringWidth(badge) + 12;
		g.setColor(premium ? Skin.GOLD : Skin.CARD_DEEP);
		g.fillRoundRect(x, y, w, 14, 7, 7);
		if (!premium)
		{
			g.setColor(Skin.FADED);
			g.drawRoundRect(x, y, w - 1, 13, 7, 7);
		}
		g.setColor(premium ? new Color(0x24, 0x1A, 0x02) : Skin.MUTED);
		g.drawString(badge, x + 6, y + 11);
	}
}
