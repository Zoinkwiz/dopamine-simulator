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

import com.dopaminesimulator.feats.Feat;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

public final class FeatBanner
{
	public static final int WIDTH = 300;
	public static final int HEIGHT = 64;

	private static final int MEDAL = 40;

	private FeatBanner()
	{
	}

	public static void draw(Graphics2D g, String title, String caption, Color rank,
		int tier, double shine)
	{
		Skin.smooth(g);
		Color tint = Skin.vivid(rank);
		Skin.hero(g, 0, 0, WIDTH, HEIGHT, tint);

		drawMedal(g, tint, tier);

		int textX = MEDAL + 20;
		int room = WIDTH - textX - 12;

		g.setFont(Skin.small());
		Skin.text(g, tier > 0 ? "FEAT EARNED" : "ACHIEVEMENT", textX, 19,
			Skin.withAlpha(Skin.GOLD, 210));

		g.setFont(Skin.heading());
		Skin.text(g, Skin.elide(g.getFontMetrics(), title, room), textX, 39, Skin.WHITE);

		g.setFont(Skin.small());
		FontMetrics small = g.getFontMetrics();
		Skin.text(g, Skin.elide(small, tier > 0
			? "Rank " + tier + " of " + Feat.RANKS + "   +"
				+ String.format("%.1f", tier * (tier + 1) / 2d
					* Feat.BONUS_PER_RANK_SHARE * 100d) + "% to everything"
			: caption, room), textX, 55, tint);

		drawSweep(g, shine);
	}

	private static void drawMedal(Graphics2D g, Color rank, int tier)
	{
		int y = (HEIGHT - MEDAL) / 2;
		g.setPaint(new GradientPaint(12, y, Skin.withAlpha(rank, 110), 12, y + MEDAL,
			Skin.withAlpha(rank, 30)));
		g.fillOval(12, y, MEDAL, MEDAL);
		g.setColor(Skin.GOLD);
		g.drawOval(12, y, MEDAL - 1, MEDAL - 1);

		String label = tier > 0 ? String.valueOf(tier) : "*";
		g.setFont(Skin.heading());
		FontMetrics metrics = g.getFontMetrics();
		Skin.text(g, label, 12 + (MEDAL - metrics.stringWidth(label)) / 2,
			y + (MEDAL + metrics.getAscent()) / 2 - 2, Skin.WHITE);
	}

	private static void drawSweep(Graphics2D g, double shine)
	{
		int x = (int) Math.round(-WIDTH * 0.4d + shine * WIDTH * 1.6d);
		Shape clip = g.getClip();
		g.clip(new RoundRectangle2D.Float(2, 2, WIDTH - 4, HEIGHT - 4, 7, 7));
		g.setPaint(new GradientPaint(x, 0, Skin.withAlpha(Color.WHITE, 0),
			x + 45, 0, Skin.withAlpha(Color.WHITE, 55)));
		g.fillRect(x, 2, 90, HEIGHT - 4);
		g.setClip(clip);
	}
}
