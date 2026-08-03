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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public final class WishReveal
{
	public static final long BURST_MS = 520L;
	public static final long STAR_STEP_MS = 130L;
	public static final long LIFETIME_MS = 3_600L;

	private static final int RAYS = 16;
	private static final int MOTES = 22;
	private static final int CARD_WIDTH = 150;
	private static final int CARD_HEIGHT = 210;

	private WishReveal()
	{
	}

	public static int starsFor(Rarity rarity)
	{
		return rarity.ordinal() + 1;
	}

	public static void draw(Graphics2D g, int width, int height, Card card, BufferedImage art,
		long age, float alpha)
	{
		Rarity rarity = card.getRarity();
		Color glow = rarity.getColour();
		int cx = width / 2;
		int cy = (int) (height * 0.44d);

		double burst = clamp01(age / (double) BURST_MS);
		double eased = 1d - Math.pow(1d - burst, 3d);

		Composite before = g.getComposite();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		drawWash(g, width, height, cx, cy, glow, eased, alpha);
		drawRays(g, cx, cy, glow, age, eased, alpha);
		drawRings(g, cx, cy, glow, age, eased, alpha);
		drawMotes(g, cx, cy, glow, age, alpha);
		drawCard(g, cx, cy, card, art, burst, age);
		drawStars(g, cx, cy, glow, starsFor(rarity), age, alpha);
		drawTitle(g, cx, cy, card, glow, age, alpha);

		g.setComposite(before);
	}

	private static void drawWash(Graphics2D g, int width, int height, int cx, int cy,
		Color glow, double eased, float alpha)
	{
		float radius = (float) (Math.max(width, height) * 0.62d * eased);
		if (radius <= 1f)
		{
			return;
		}
		g.setPaint(new RadialGradientPaint(new Point2D.Float(cx, cy), radius,
			new float[]{0f, 0.45f, 1f},
			new Color[]{withAlpha(glow, (int) (110 * alpha)),
				withAlpha(glow, (int) (34 * alpha)), withAlpha(glow, 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(0, 0, width, height);
	}

	private static void drawRays(Graphics2D g, int cx, int cy, Color glow, long age,
		double eased, float alpha)
	{
		AffineTransform before = g.getTransform();
		g.translate(cx, cy);
		g.rotate(age / 3600d);

		double reach = 210d * eased;
		for (int i = 0; i < RAYS; i++)
		{
			double angle = Math.PI * 2d * i / RAYS;
			double sway = 0.10d + 0.05d * Math.sin(age / 260d + i);
			double length = reach * (i % 2 == 0 ? 1d : 0.66d);

			Path2D.Double ray = new Path2D.Double();
			ray.moveTo(0, 0);
			ray.lineTo(Math.cos(angle - sway) * length, Math.sin(angle - sway) * length);
			ray.lineTo(Math.cos(angle + sway) * length, Math.sin(angle + sway) * length);
			ray.closePath();

			g.setColor(withAlpha(glow, (int) ((i % 2 == 0 ? 40 : 22) * alpha)));
			g.fill(ray);
		}
		g.setTransform(before);
	}

	private static void drawRings(Graphics2D g, int cx, int cy, Color glow, long age,
		double eased, float alpha)
	{
		for (int i = 0; i < 3; i++)
		{
			double offset = (age / 900d + i / 3d) % 1d;
			int radius = (int) (60 + offset * 190 * eased);
			int fade = (int) (90 * (1d - offset) * alpha);
			if (fade <= 0)
			{
				continue;
			}
			g.setColor(withAlpha(glow, fade));
			g.setStroke(new BasicStroke(2f));
			g.drawOval(cx - radius, cy - radius, radius * 2, radius * 2);
		}
	}

	private static void drawMotes(Graphics2D g, int cx, int cy, Color glow, long age, float alpha)
	{
		for (int i = 0; i < MOTES; i++)
		{
			double seed = i * 2.399963d;
			double drift = ((age / 2600d) + i / (double) MOTES) % 1d;
			double radius = 70 + (i % 5) * 26;
			double angle = seed + age / 5200d;
			int x = (int) (cx + Math.cos(angle) * radius);
			int y = (int) (cy + Math.sin(angle) * radius - drift * 120);
			int size = 2 + (i % 3);
			int fade = (int) (170 * (1d - drift) * alpha);
			if (fade <= 0)
			{
				continue;
			}
			g.setColor(withAlpha(glow, fade));
			g.fillOval(x, y, size, size);
		}
	}

	private static void drawCard(Graphics2D g, int cx, int cy, Card card, BufferedImage art,
		double burst, long age)
	{
		double overshoot = 1d + 0.16d * Math.sin(Math.PI * Math.min(1d, burst));
		double scale = (0.35d + 0.65d * burst) * overshoot;
		double bob = Math.sin(age / 700d) * 4d;

		AffineTransform before = g.getTransform();
		g.translate(cx, cy + bob);
		g.scale(scale, scale);
		CardRenderer.draw(g, card, -CARD_WIDTH / 2, -CARD_HEIGHT / 2, CARD_WIDTH, CARD_HEIGHT,
			0, true, System.currentTimeMillis(), art);
		g.setTransform(before);
	}

	private static void drawStars(Graphics2D g, int cx, int cy, Color glow, int stars,
		long age, float alpha)
	{
		int y = cy + CARD_HEIGHT / 2 + 26;
		int size = 11;
		int gap = 7;
		int total = stars * size * 2 + (stars - 1) * gap;
		int x = cx - total / 2 + size;

		for (int i = 0; i < stars; i++)
		{
			long due = BURST_MS + i * STAR_STEP_MS;
			if (age < due)
			{
				continue;
			}
			double pop = clamp01((age - due) / 180d);
			double grow = size * (1d + 0.5d * (1d - pop));
			g.setColor(withAlpha(glow, (int) (255 * alpha)));
			fillStar(g, x + i * (size * 2 + gap), y, grow);
		}
	}

	private static void fillStar(Graphics2D g, double cx, double cy, double radius)
	{
		Path2D.Double star = new Path2D.Double();
		for (int i = 0; i < 10; i++)
		{
			double angle = -Math.PI / 2d + Math.PI * i / 5d;
			double r = i % 2 == 0 ? radius : radius * 0.44d;
			double x = cx + Math.cos(angle) * r;
			double y = cy + Math.sin(angle) * r;
			if (i == 0)
			{
				star.moveTo(x, y);
			}
			else
			{
				star.lineTo(x, y);
			}
		}
		star.closePath();
		g.fill(star);
	}

	private static void drawTitle(Graphics2D g, int cx, int cy, Card card, Color glow,
		long age, float alpha)
	{
		if (age < BURST_MS)
		{
			return;
		}

		int y = cy + CARD_HEIGHT / 2 + 62;
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 9));
		FontMetrics metrics = g.getFontMetrics();
		String eyebrow = "FEATURED";
		g.setColor(withAlpha(glow, (int) (200 * alpha)));
		g.drawString(eyebrow, cx - metrics.stringWidth(eyebrow) / 2, y - 16);

		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		metrics = g.getFontMetrics();
		String name = card.getName();
		g.setColor(new Color(0x0A, 0x0A, 0x0C));
		g.drawString(name, cx - metrics.stringWidth(name) / 2 + 1, y + 1);
		g.setColor(Color.WHITE);
		g.drawString(name, cx - metrics.stringWidth(name) / 2, y);

		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
		metrics = g.getFontMetrics();
		String set = card.getSet().getDisplayName();
		g.setColor(withAlpha(glow, (int) (190 * alpha)));
		g.drawString(set, cx - metrics.stringWidth(set) / 2, y + 18);
	}

	private static double clamp01(double value)
	{
		return Math.max(0d, Math.min(1d, value));
	}

	private static Color withAlpha(Color colour, int a)
	{
		return new Color(colour.getRed(), colour.getGreen(), colour.getBlue(),
			Math.max(0, Math.min(255, a)));
	}
}
