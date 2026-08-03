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
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class FeatRow extends JComponent
{
	public static final int HEIGHT = 46;

	private static final int MEDAL = 30;
	private static final int MEDAL_X = 8;

	private final String name;
	private final String progressText;
	private final int tier;
	private final int maxTier;
	private final double fraction;
	private final boolean mastered;

	public FeatRow(Feat feat, int tier, String progressText, double fraction)
	{
		this.name = feat.getDisplayName();
		this.progressText = progressText;
		this.tier = tier;
		this.maxTier = feat.maxTier();
		this.fraction = Math.max(0d, Math.min(1d, fraction));
		this.mastered = tier >= feat.maxTier();
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
		int height = HEIGHT - 3;
		Color rank = Skin.vivid(Feat.tierColour(tier));

		Skin.card(g, 0, 0, width, height, Skin.CARD);

		drawMedal(g, height, rank);

		int textX = MEDAL_X + MEDAL + 8;
		g.setFont(Skin.body());
		FontMetrics small = g.getFontMetrics();
		String ranks = tier + " / " + maxTier;
		Skin.right(g, ranks, width - 8, 19, mastered ? Skin.GOLD : Skin.MUTED);

		g.setFont(Skin.body());
		Skin.text(g, Skin.elide(g.getFontMetrics(), name,
			width - textX - small.stringWidth(ranks) - 16), textX, 19,
			tier > 0 ? Skin.WHITE : Skin.MUTED);

		g.setFont(Skin.body());
		if (mastered)
		{
			Skin.text(g, Skin.elide(small, progressText, width - textX - 10), textX, 33,
				Skin.GREEN);
		}
		else
		{
			int barW = 52;
			int barX = width - 8 - barW;
			Skin.text(g, Skin.elide(small, progressText, barX - textX - 8), textX, 33, Skin.MUTED);
			Skin.bar(g, barX, 25, barW, 9, fraction, tier > 0 ? rank : Skin.FADED);
		}

		g.dispose();
	}

	private void drawMedal(Graphics2D g, int height, Color rank)
	{
		int y = (height - MEDAL) / 2;
		g.setColor(tier > 0 ? Skin.withAlpha(rank, 40) : Skin.CARD_DEEP);
		g.fillOval(MEDAL_X, y, MEDAL, MEDAL);
		g.setColor(tier > 0 ? rank : Skin.FADED);
		g.drawOval(MEDAL_X, y, MEDAL - 1, MEDAL - 1);

		String label = tier > 0 ? String.valueOf(tier) : "-";
		g.setFont(Skin.heading());
		FontMetrics metrics = g.getFontMetrics();
		Skin.text(g, label, MEDAL_X + (MEDAL - metrics.stringWidth(label)) / 2,
			y + (MEDAL + metrics.getAscent()) / 2 - 2, tier > 0 ? rank : Skin.FADED);
	}
}
