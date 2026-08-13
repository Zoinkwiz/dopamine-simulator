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

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.Rarity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class BannerHeader extends JComponent
{
	public static final int HEIGHT = 94;

	private static final int CARD_H = 78;
	private static final int PAD = 9;

	private final Card featured;
	private final Rarity rarity;
	private final String name;
	private final BufferedImage art;
	private final int pity;
	private final int hardPity;
	private final double rate;
	private final String remaining;
	private final int ownedStars;
	private final int ownedCopies;

	public BannerHeader(Card featured, Rarity rarity, String name, BufferedImage art,
		int pity, int hardPity, double rate, String remaining, int ownedStars, int ownedCopies)
	{
		this.featured = featured;
		this.rarity = rarity;
		this.name = name;
		this.art = art;
		this.pity = pity;
		this.hardPity = hardPity;
		this.rate = rate;
		this.remaining = remaining;
		this.ownedStars = ownedStars;
		this.ownedCopies = ownedCopies;
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
		Color accent = Skin.vivid(rarity.getColour());
		Skin.hero(g, 0, 0, width, HEIGHT, accent);

		int cardW = CardRenderer.widthForHeight(CARD_H);
		int cardX = width - cardW - PAD;
		int cardY = (HEIGHT - CARD_H) / 2;

		CardRenderer.draw(g, featured, cardX, cardY, cardW, CARD_H, 0, true, 0L, art);

		int room = Math.max(30, cardX - PAD * 2);

		g.setFont(Skin.heading());
		Skin.text(g, Skin.elide(g.getFontMetrics(), name, room), PAD, 22, Skin.GOLD);

		g.setFont(Skin.body());
		FontMetrics small = g.getFontMetrics();
		Skin.text(g, Skin.elide(small, featured.getName(), room), PAD, 38, Skin.WHITE);

		int stars = WishReveal.starsFor(rarity);
		drawStars(g, PAD, 49, stars);

		int afterStars = PAD + (int) (stars * 12d) + 5;
		String owned = ownedCopies <= 0 ? "not owned"
			: ownedStars >= Rarity.MAX_STARS ? "MAXED"
			: "you have " + ownedStars + "★ (" + ownedCopies + ")";
		Skin.text(g, Skin.elide(small, owned, Math.max(0, room - afterStars + PAD)),
			afterStars, 53, ownedStars >= Rarity.MAX_STARS ? Skin.GOLD : Skin.FADED);

		String chance = String.format("%.1f%%", rate * 100d);
		Skin.text(g, chance, PAD, 66, Skin.WHITE);
		Skin.text(g, Skin.elide(small, remaining,
			room - small.stringWidth(chance) - 6), PAD + small.stringWidth(chance) + 6, 66,
			Skin.ORANGE);

		double progress = hardPity <= 0 ? 0d : Math.min(1d, pity / (double) hardPity);
		Skin.bar(g, PAD, HEIGHT - 20, room, 12, progress, accent,
			"guaranteed in " + Math.max(0, hardPity - pity));

		g.dispose();
	}

	private void drawStars(Graphics2D g, int x, int y, int stars)
	{
		double radius = 4.5d;
		double gap = 3d;
		for (int i = 0; i < stars; i++)
		{
			double cx = x + radius + i * (radius * 2 + gap);
			g.setColor(Skin.SHADOW);
			fillStar(g, cx + 1, y + 1, radius);
			g.setColor(Skin.GOLD);
			fillStar(g, cx, y, radius);
		}
	}

	private static void fillStar(Graphics2D g, double cx, double cy, double radius)
	{
		Path2D.Double star = new Path2D.Double();
		for (int i = 0; i < 10; i++)
		{
			double angle = -Math.PI / 2d + Math.PI * i / 5d;
			double r = i % 2 == 0 ? radius : radius * 0.45d;
			double px = cx + Math.cos(angle) * r;
			double py = cy + Math.sin(angle) * r;
			if (i == 0)
			{
				star.moveTo(px, py);
			}
			else
			{
				star.lineTo(px, py);
			}
		}
		star.closePath();
		g.fill(star);
	}
}
