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

import com.dopaminesimulator.pass.PassReward;
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

public class PassTierRow extends JComponent
{
	public static final int HEIGHT = 48;

	private static final int DISC = 26;
	private static final int DISC_X = 7;

	private final int tier;
	private final boolean milestone;
	private final boolean reached;
	private final Color accent;
	private final PassReward free;
	private final PassReward premium;
	private final boolean freeClaimed;
	private final boolean premiumClaimed;
	private final boolean premiumOwned;
	private final BufferedImage freeIcon;
	private final BufferedImage premiumIcon;

	public PassTierRow(int tier, boolean milestone, boolean reached, boolean first, boolean last,
		Color accent, PassReward free, PassReward premium, boolean freeClaimed,
		boolean premiumClaimed, boolean premiumOwned, BufferedImage freeIcon,
		BufferedImage premiumIcon, Consumer<Boolean> onClaim)
	{
		this.tier = tier;
		this.milestone = milestone;
		this.reached = reached;
		this.accent = Skin.vivid(accent);
		this.free = free;
		this.premium = premium;
		this.freeClaimed = freeClaimed;
		this.premiumClaimed = premiumClaimed;
		this.premiumOwned = premiumOwned;
		this.freeIcon = freeIcon;
		this.premiumIcon = premiumIcon;

		setPreferredSize(new Dimension(0, HEIGHT));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, HEIGHT));
		setMinimumSize(new Dimension(0, HEIGHT));
		setOpaque(false);

		if (onClaim != null)
		{
			addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent e)
				{
					boolean lower = e.getY() > HEIGHT / 2;
					if (!reached)
					{
						return;
					}
					if (lower && premiumOwned && !premiumClaimed)
					{
						onClaim.accept(true);
					}
					else if (!lower && !freeClaimed)
					{
						onClaim.accept(false);
					}
				}
			});
		}
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		Skin.smooth(g);

		int width = getWidth();
		int height = HEIGHT - 3;
		boolean claimable = reached
			&& (!freeClaimed || (premiumOwned && !premiumClaimed));

		Skin.card(g, 0, 0, width, height, reached ? Skin.CARD : Skin.CARD_DEEP);
		if (claimable)
		{

			g.setStroke(new BasicStroke(1.5f));
			g.setColor(Skin.GOLD);
			g.drawRoundRect(1, 1, width - 3, height - 3, 5, 5);
		}
		drawDisc(g, height);

		int textX = DISC_X + DISC + 7;
		drawReward(g, free, freeIcon, textX, 19, freeClaimed, false);
		drawReward(g, premium, premiumIcon, textX, 36, premiumClaimed, true);

		g.dispose();
	}

	private void drawDisc(Graphics2D g, int height)
	{
		int y = (height - DISC) / 2;
		Color tint = milestone ? Skin.GOLD : accent;
		g.setColor(reached ? Skin.withAlpha(tint, 45) : Skin.CARD_DEEP);
		g.fillOval(DISC_X, y, DISC, DISC);
		g.setColor(reached ? tint : Skin.FADED);
		g.drawOval(DISC_X, y, DISC - 1, DISC - 1);

		g.setFont(Skin.body());
		FontMetrics metrics = g.getFontMetrics();
		String label = String.valueOf(tier);
		Skin.text(g, label, DISC_X + (DISC - metrics.stringWidth(label)) / 2,
			y + (DISC + metrics.getAscent()) / 2 - 2, reached ? tint : Skin.FADED);
	}

	private void drawReward(Graphics2D g, PassReward reward, BufferedImage icon, int x, int baseline,
		boolean claimed, boolean isPremium)
	{
		int size = 14;
		int iconY = baseline - size + 2;
		boolean available = reached && !claimed && (!isPremium || premiumOwned);
		boolean faded = claimed || !reached || (isPremium && !premiumOwned);

		if (icon != null)
		{
			Composite before = g.getComposite();
			if (faded)
			{
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
			}
			g.drawImage(icon, x, iconY, size, size, null);
			g.setComposite(before);
		}
		else
		{
			g.setColor(faded ? Skin.FADED : Skin.vivid(reward.colour()));
			g.fillOval(x + 3, iconY + 3, size - 6, size - 6);
		}

		g.setFont(Skin.body());
		FontMetrics metrics = g.getFontMetrics();
		String text = reward.describe();
		if (isPremium && !premiumOwned)
		{
			text = "Premium: " + text;
		}

		String status = claimed ? "done" : available ? "claim" : null;
		int statusWidth = status == null ? 4 : metrics.stringWidth(status) + 10;
		int textX = x + size + 6;
		Skin.text(g, Skin.elide(metrics, text, getWidth() - textX - statusWidth - 8), textX,
			baseline, claimed ? Skin.FADED : available ? Skin.WHITE : Skin.MUTED);

		if (status != null)
		{
			Skin.right(g, status, getWidth() - 8, baseline, claimed ? Skin.GREEN : Skin.GOLD);
		}
	}
}
