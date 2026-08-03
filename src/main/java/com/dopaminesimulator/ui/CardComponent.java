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
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class CardComponent extends JComponent
{
	private static final int INSET = 3;
	private static final double MAX_SHEAR_X = 0.10d;
	private static final double MAX_SHEAR_Y = 0.05d;
	private final Card card;
	private final int stars;
	private final boolean owned;
	private final CardArtService artService;
	private final boolean shiny;
	private final boolean gilded;
	private static final int INTRO_MS = 260;
	private long introStartedAt;
	private boolean hovered;
	private double pointerX = 0.5d;
	private double pointerY = 0.5d;
	private Consumer<Card> onClick;
	public CardComponent(Card card, int stars, boolean owned, int width, CardArtService artService)
	{
		this(card, stars, owned, width, artService, false);
	}

	public CardComponent(Card card, int stars, boolean owned, int width, CardArtService artService,
		boolean shiny)
	{
		this(card, stars, owned, width, artService, shiny, false);
	}

	public CardComponent(Card card, int stars, boolean owned, int width, CardArtService artService,
		boolean shiny, boolean gilded)
	{
		this.card = card;
		this.stars = stars;
		this.owned = owned;
		this.artService = artService;
		this.shiny = shiny;
		this.gilded = gilded;

		int height = CardRenderer.heightForWidth(width) + INSET * 2;
		Dimension size = new Dimension(width + INSET * 2, height);
		setPreferredSize(size);
		setMinimumSize(size);
		setOpaque(false);
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				hovered = true;
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				hovered = false;
				pointerX = 0.5d;
				pointerY = 0.5d;
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (onClick != null)
				{
					onClick.accept(card);
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				pointerX = clamp01(e.getX() / (double) Math.max(1, getWidth()));
				pointerY = clamp01(e.getY() / (double) Math.max(1, getHeight()));
				repaint();
			}
		});
		if (owned)
		{
			artService.onLoaded(card, () -> SwingUtilities.invokeLater(this::repaint));
		}
	}
	public void setOnClick(Consumer<Card> onClick)
	{
		this.onClick = onClick;
	}
	public void playIntro()
	{
		introStartedAt = System.currentTimeMillis();
		Timer timer = new Timer(16, null);
		timer.addActionListener(e ->
		{
			repaint();
			if (introProgress() >= 1d)
			{
				timer.stop();
			}
		});
		timer.start();
	}

	private double introProgress()
	{
		if (introStartedAt == 0L)
		{
			return 1d;
		}
		double t = (System.currentTimeMillis() - introStartedAt) / (double) INTRO_MS;
		if (t >= 1d)
		{
			return 1d;
		}
		return t * t * (3d - 2d * t);
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int available = getWidth() - INSET * 2;
		int cardWidth = Math.min(available, CardRenderer.widthForHeight(getHeight() - INSET * 2));
		int cardHeight = CardRenderer.heightForWidth(cardWidth);

		int x = (getWidth() - cardWidth) / 2;
		int y = (getHeight() - cardHeight) / 2;
		AffineTransform before = g.getTransform();

		double intro = introProgress();
		if (intro < 1d)
		{
			double centreX = x + cardWidth / 2d;
			double centreY = y + cardHeight / 2d;
			double scale = 0.55d + 0.45d * intro;
			g.translate(centreX, centreY);
			g.scale(scale, 1d);
			g.rotate((1d - intro) * -0.35d);
			g.translate(-centreX, -centreY);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				(float) Math.min(1d, intro * 1.6d)));
		}
		else if (hovered)
		{
			applyTilt(g, x + cardWidth / 2d, y + cardHeight / 2d);
		}
		CardRenderer.draw(g, card, x, y, cardWidth, cardHeight, stars, owned,
			shiny ? System.currentTimeMillis() : 0L,
			owned ? artService.get(card) : null, shiny, gilded);
		if (hovered && owned)
		{
			drawSpecular(g, x, y, cardWidth, cardHeight);
		}
		g.setTransform(before);
		g.dispose();
	}
	private void applyTilt(Graphics2D g, double centreX, double centreY)
	{
		double dx = (pointerX - 0.5d) * 2d;
		double dy = (pointerY - 0.5d) * 2d;
		g.translate(centreX, centreY);
		g.shear(-dy * MAX_SHEAR_X, -dx * MAX_SHEAR_Y);
		g.scale(1.03d, 1.03d);
		g.translate(-centreX, -centreY);
	}

	private void drawSpecular(Graphics2D g, int x, int y, int width, int height)
	{
		Rarity rarity = card.getRarity();
		int peak = rarity.ordinal() >= Rarity.EPIC.ordinal() ? 90
			: rarity.ordinal() >= Rarity.RARE.ordinal() ? 60 : 42;
		Color tint = rarity.ordinal() >= Rarity.EPIC.ordinal()
			? rarity.getColour()
			: Color.WHITE;
		java.awt.Shape clip = g.getClip();
		int radius = Math.max(3, height / 14);
		g.setClip(new RoundRectangle2D.Float(x, y, width, height, radius, radius));
		float glintX = (float) (x + pointerX * width);
		float glintY = (float) (y + pointerY * height);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(glintX, glintY),
			Math.max(width, height) * 0.8f,
			new float[]{0f, 1f},
			new Color[]{
				new Color(tint.getRed(), tint.getGreen(), tint.getBlue(), peak),
				new Color(tint.getRed(), tint.getGreen(), tint.getBlue(), 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(x, y, width, height);
		g.setClip(clip);
	}

	private static double clamp01(double value)
	{
		return value < 0d ? 0d : Math.min(value, 1d);
	}
}
