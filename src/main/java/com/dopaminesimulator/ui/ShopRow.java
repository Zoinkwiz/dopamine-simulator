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

import com.dopaminesimulator.incremental.BigNumbers;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import javax.swing.JComponent;

public class ShopRow extends JComponent
{
	public static final int HEIGHT = 46;

	private static final int ICON = 32;
	private static final int ICON_X = 8;

	private final String title;
	private final String effect;
	private final double cost;
	private final Color accent;
	private final String badge;
	private final boolean affordable;
	private final double progressToAfford;

	private BufferedImage icon;

	public ShopRow(String title, String effect, double cost, Color accent, String badge,
				   boolean affordable, double progressToAfford, Consumer<ShopRow> onBuy)
	{
		this.title = title;
		this.effect = effect;
		this.cost = cost;
		this.accent = Skin.vivid(accent);
		this.badge = badge;
		this.affordable = affordable;
		this.progressToAfford = Math.max(0d, Math.min(1d, progressToAfford));
		setPreferredSize(new Dimension(0, HEIGHT));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, HEIGHT));
		setMinimumSize(new Dimension(0, HEIGHT));
		setOpaque(false);
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (onBuy != null && affordable)
				{
					onBuy.accept(ShopRow.this);
				}
			}
		});
	}

	public void setIcon(BufferedImage icon)
	{
		this.icon = icon;
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		Skin.smooth(g);

		int width = getWidth();
		int height = HEIGHT - 3;
		boolean hovered = affordable && isShowing() && getMousePosition() != null;

		Skin.card(g, 0, 0, width, height, hovered ? Skin.CARD_HOVER : Skin.CARD);
		if (hovered)
		{
			g.setStroke(new BasicStroke(1f));
			g.setColor(Skin.GOLD);
			g.drawRoundRect(0, 0, width - 1, height - 1, 5, 5);
		}

		g.setColor(affordable ? accent : Skin.withAlpha(accent, 90));
		g.fillRoundRect(2, 8, 3, height - 16, 2, 2);

		drawIcon(g, height);

		int textX = ICON_X + ICON + 8;

		String price = cost > 0d ? BigNumbers.format(cost) : "—";

		g.setFont(Skin.body());
		FontMetrics bodyMetrics = g.getFontMetrics();
		int priceWidth = bodyMetrics.stringWidth(price);
		Skin.right(g, price, width - 8, 19, affordable ? Skin.GOLD : Skin.FADED);
		Skin.text(g, Skin.elide(bodyMetrics, title, width - textX - priceWidth - 16), textX, 19,
			affordable ? Skin.WHITE : Skin.FADED);

		g.setFont(Skin.body());
		FontMetrics small = g.getFontMetrics();

		if (affordable)
		{
			Skin.text(g, Skin.elide(small, effect, width - textX - 10), textX, 33, Skin.MUTED);
		}
		else
		{

			int barW = 52;
			int barX = width - 8 - barW;
			Skin.text(g, Skin.elide(small, effect, barX - textX - 8), textX, 33, Skin.FADED);
			Skin.bar(g, barX, 25, barW, 9, progressToAfford, Skin.GOLD_DEEP);
		}

		g.dispose();
	}

	private void drawIcon(Graphics2D g, int height)
	{
		int y = (height - ICON) / 2;
		if (icon != null)
		{
			double scale = Math.min((double) ICON / icon.getWidth(), (double) ICON / icon.getHeight());
			int w = (int) Math.round(icon.getWidth() * scale);
			int h = (int) Math.round(icon.getHeight() * scale);
			Composite before = g.getComposite();
			if (!affordable)
			{
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
			}
			g.drawImage(icon, ICON_X + (ICON - w) / 2, y + (ICON - h) / 2, w, h, null);
			g.setComposite(before);
		}

		if (badge == null || badge.isEmpty())
		{
			return;
		}
		g.setFont(Skin.small());
		if (icon == null)
		{
			Skin.centred(g, badge, ICON_X, ICON, y + ICON / 2 + 4,
				affordable ? Skin.WHITE : Skin.FADED);
			return;
		}

		Skin.text(g, badge, ICON_X - 2, y + 9, affordable ? Skin.YELLOW : Skin.FADED);
	}
}
