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
import java.awt.LinearGradientPaint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public final class WishReveal
{
	public static final long BURST_MS = 520L;
	public static final long STAR_STEP_MS = 130L;

	private static final long SEAL_MS = 620L;
	private static final long TELL_HIGH_MS = 520L;
	private static final long TELL_LOW_MS = 220L;
	private static final long STRIKE_MS = 90L;
	private static final long SETTLE_MS = 460L;
	private static final long STAMP_LEAD_MS = 320L;
	private static final long SHOCKWAVE_MS = 620L;
	private static final long STAR_POP_MS = 340L;

	public static final long LIFETIME_MS = 5_200L;

	private static boolean isHighTier(Rarity rarity, boolean modelArt)
	{
		return modelArt || rarity.ordinal() >= Rarity.EPIC.ordinal();
	}

	private static long strikeAt(boolean highTier)
	{
		return SEAL_MS + (highTier ? TELL_HIGH_MS : TELL_LOW_MS);
	}

	public static long strikeTime(Rarity rarity, boolean modelArt)
	{
		return strikeAt(isHighTier(rarity, modelArt));
	}

	public static long starDue(int index, Rarity rarity, boolean modelArt)
	{
		return strikeAt(isHighTier(rarity, modelArt)) + STRIKE_MS + STAMP_LEAD_MS
			+ 90L + (long) index * (150L - 9L * index);
	}

	private static double cardScale(long age, boolean highTier)
	{
		long settleAt = strikeAt(highTier) + STRIKE_MS;
		if (age < settleAt)
		{
			return age < strikeAt(highTier) ? 0d : 0.94d;
		}
		double t = clamp01((age - settleAt) / (double) SETTLE_MS);
		double c1 = 1.70158d;
		double c3 = c1 + 1d;
		double back = 1d + c3 * Math.pow(t - 1d, 3d) + c1 * Math.pow(t - 1d, 2d);
		return 0.94d + 0.06d * back;
	}

	private static final Color SEAL_NEUTRAL = new Color(0x6B, 0x64, 0x78);

	private static final int RAYS = 16;
	private static final int MOTES = 22;


	private static final int CARD_WIDTH = 260;
	private static final int CARD_HEIGHT = 364;

	private WishReveal()
	{
	}

	public static final int DIAMOND_STARS = 6;

	public static int starsFor(Rarity rarity, boolean diamond)
	{
		return diamond ? DIAMOND_STARS : starsFor(rarity);
	}

	public static int starsFor(Rarity rarity)
	{
		return rarity.ordinal() + 1;
	}

	public static void draw(Graphics2D g, int width, int height, Card card, BufferedImage art,
		long age, float alpha)
	{
		draw(g, width, height, card, art, age, alpha, false);
	}

	public static java.awt.Rectangle draw(Graphics2D g, int width, int height, Card card,
		BufferedImage art, long age, float alpha, boolean modelArt)
	{
		return draw(g, width, height, card, art, age, alpha, modelArt, DEFAULT_MODEL_FOIL);
	}

	public static final float DEFAULT_MODEL_FOIL = 0f;

	public static java.awt.Rectangle draw(Graphics2D g, int width, int height, Card card,
		BufferedImage art, long age, float alpha, boolean modelArt, float foilIntensity)
	{
		Rarity rarity = card.getRarity();
		Color glow = rarity.getColour();
		int cx = width / 2;
		int cy = (int) (height * 0.44d);

		boolean highTier = isHighTier(rarity, modelArt);
		long strikeAt = strikeAt(highTier);
		double scale = cardScale(age, highTier);
		Color tierColour = modelArt ? PRISM_GLOW : glow;

		Composite before = g.getComposite();
		java.awt.Shape clipBefore = g.getClip();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		if (age < strikeAt)
		{
			boolean told = age >= SEAL_MS;
			double ambient = told ? clamp01((age - SEAL_MS) / 260d) : 0d;
			if (told)
			{
				drawWash(g, width, height, cx, cy, tierColour, ambient * 0.7d, alpha);
			}
			drawSeal(g, cx, cy, told ? tierColour : SEAL_NEUTRAL, told, highTier, age, alpha);
			g.setClip(clipBefore);
			g.setComposite(before);
			return null;
		}

		long sinceStrike = age - strikeAt;
		double eased = clamp01(sinceStrike / (double) SETTLE_MS);

		java.awt.Rectangle modelBox = modelArt ? modelBounds(cx, cy, scale) : null;
		if (modelBox != null)
		{
			java.awt.geom.Area visible = new java.awt.geom.Area(
				clipBefore != null ? clipBefore : new java.awt.Rectangle(0, 0, width, height));
			visible.subtract(new java.awt.geom.Area(modelBox));
			g.setClip(visible);
		}

		drawWash(g, width, height, cx, cy, tierColour, eased, alpha);
		if (modelArt)
		{
			drawPrismaticRays(g, cx, cy, age, eased, alpha);
			drawPrismaticRings(g, cx, cy, age, eased, alpha);
			drawMotes(g, cx, cy, PRISM_GLOW, age, alpha);
		}
		else
		{
			drawRays(g, cx, cy, glow, age, eased, alpha);
			drawRings(g, cx, cy, glow, age, eased, alpha);
			drawMotes(g, cx, cy, glow, age, alpha);
		}
		drawShockwave(g, cx, cy, tierColour, sinceStrike, highTier ? 1d : 0.6d, alpha);
		drawCard(g, cx, cy, card, art, scale, age, modelArt);

		if (modelBox != null)
		{
			g.setClip(clipBefore);
			CardRenderer.drawArtVignette(g, modelBox);
			CardRenderer.drawFoilOver(g, card, rarity, modelBox, System.currentTimeMillis(),
				foilIntensity);
			drawChrome(g, cx, cy, card, scale, age);
		}

		drawStars(g, cx, cy, glow, starsFor(rarity, modelArt),
			age - (strikeAt + STRIKE_MS + STAMP_LEAD_MS), alpha, modelArt);
		drawTitle(g, cx, cy, card, tierColour, age, alpha);

		g.setClip(clipBefore);
		drawFlash(g, width, height, sinceStrike, highTier, alpha);

		g.setComposite(before);
		return modelBox;
	}

	private static void drawSeal(Graphics2D g, int cx, int cy, Color colour,
		boolean told, boolean highTier, long age, float alpha)
	{
		int w = (int) (CARD_WIDTH * 0.94d);
		int h = (int) (CARD_HEIGHT * 0.94d);
		int x = cx - w / 2;
		int y = cy - h / 2;
		int arc = Math.max(10, h / 14);

		Shape clipBefore = g.getClip();
		Stroke strokeBefore = g.getStroke();

		g.setPaint(new LinearGradientPaint(
			new Point2D.Float(x, y), new Point2D.Float(x, y + h),
			new float[]{0f, 0.55f, 1f},
			new Color[]{withAlpha(new Color(0x16, 0x12, 0x1E), (int) (250 * alpha)),
				withAlpha(new Color(0x0B, 0x09, 0x10), (int) (250 * alpha)),
				withAlpha(mix(new Color(0x0B, 0x09, 0x10), colour, 0.22d), (int) (250 * alpha))}));
		g.fillRoundRect(x, y, w, h, arc, arc);

		g.setClip(new RoundRectangle2D.Float(x, y, w, h, arc, arc));
		g.setStroke(new BasicStroke(1f));
		g.setColor(withAlpha(colour, (int) (16 * alpha)));
		int step = Math.max(7, h / 24);
		for (int offset = -h; offset < w + h; offset += step)
		{
			g.drawLine(x + offset, y, x + offset + h, y + h);
			g.drawLine(x + offset + h, y, x + offset, y + h);
		}
		g.setClip(clipBefore);

		int inset = Math.max(5, h / 26);
		g.setStroke(new BasicStroke(1.4f));
		g.setColor(withAlpha(mix(colour, Color.WHITE, 0.25d), (int) (150 * alpha)));
		g.drawRoundRect(x, y, w - 1, h - 1, arc, arc);
		g.setStroke(new BasicStroke(1f));
		g.setColor(withAlpha(colour, (int) (70 * alpha)));
		g.drawRoundRect(x + inset, y + inset, w - inset * 2 - 1, h - inset * 2 - 1,
			arc / 2, arc / 2);

		int tick = Math.max(4, h / 30);
		g.setStroke(new BasicStroke(1.6f));
		g.setColor(withAlpha(mix(colour, Color.WHITE, 0.4d), (int) (190 * alpha)));
		for (int sx = 0; sx < 2; sx++)
		{
			for (int sy = 0; sy < 2; sy++)
			{
				int px = x + inset + (sx == 0 ? 0 : w - inset * 2);
				int py = y + inset + (sy == 0 ? 0 : h - inset * 2);
				g.drawLine(px, py, px + (sx == 0 ? tick : -tick), py);
				g.drawLine(px, py, px, py + (sy == 0 ? tick : -tick));
			}
		}

		double pulse = told ? 1.06d : 1d + 0.09d * Math.sin(age / 1100d * Math.PI * 2d);
		int radius = (int) (h * 0.155d * pulse);

		g.setColor(withAlpha(colour, (int) ((told ? 70 : 34) * alpha)));
		int halo = radius + radius / 4;
		g.fillOval(cx - halo, cy - halo, halo * 2, halo * 2);

		g.setStroke(new BasicStroke(Math.max(1.6f, h / 150f)));
		g.setColor(withAlpha(colour, (int) (255 * alpha)));
		g.drawOval(cx - radius, cy - radius, radius * 2, radius * 2);
		g.setStroke(new BasicStroke(1f));
		g.setColor(withAlpha(colour, (int) (110 * alpha)));
		int inner = (int) (radius * 0.78d);
		g.drawOval(cx - inner, cy - inner, inner * 2, inner * 2);

		for (int i = 0; i < 16; i++)
		{
			double angle = Math.PI * 2d * i / 16d;
			int from = radius + 2;
			int to = from + (i % 4 == 0 ? tick : tick / 2);
			g.setColor(withAlpha(colour, (int) ((i % 4 == 0 ? 190 : 90) * alpha)));
			g.drawLine(cx + (int) (Math.cos(angle) * from), cy + (int) (Math.sin(angle) * from),
				cx + (int) (Math.cos(angle) * to), cy + (int) (Math.sin(angle) * to));
		}

		g.setColor(withAlpha(colour, (int) (255 * alpha)));
		int sigil = (int) (h * 0.072d);
		if (told && highTier)
		{
			fillStar(g, cx, cy, sigil);
		}
		else
		{
			Path2D.Double diamond = new Path2D.Double();
			diamond.moveTo(cx, cy - sigil);
			diamond.lineTo(cx + sigil * 0.86d, cy);
			diamond.lineTo(cx, cy + sigil);
			diamond.lineTo(cx - sigil * 0.86d, cy);
			diamond.closePath();
			g.fill(diamond);
		}

		g.setStroke(strokeBefore);
	}

	private static Color mix(Color from, Color to, double amount)
	{
		return new Color(
			(int) (from.getRed() + (to.getRed() - from.getRed()) * amount),
			(int) (from.getGreen() + (to.getGreen() - from.getGreen()) * amount),
			(int) (from.getBlue() + (to.getBlue() - from.getBlue()) * amount));
	}

	private static void drawFlash(Graphics2D g, int width, int height, long sinceStrike,
		boolean highTier, float alpha)
	{
		if (sinceStrike > 220L)
		{
			return;
		}
		double t = sinceStrike / 220d;
		double intensity = Math.sin(Math.PI * t) * (highTier ? 0.95d : 0.5d);
		g.setColor(new Color(1f, 1f, 1f, (float) Math.max(0d, intensity) * alpha));
		g.fillRect(0, 0, width, height);
	}

	private static void drawShockwave(Graphics2D g, int cx, int cy, Color colour,
		long sinceStrike, double scale, float alpha)
	{
		if (sinceStrike > SHOCKWAVE_MS)
		{
			return;
		}
		double t = sinceStrike / (double) SHOCKWAVE_MS;
		double eased = 1d - Math.pow(1d - t, 3d);
		int radius = (int) (20 + eased * 260 * scale);
		int fade = (int) (230 * (1d - t) * alpha);
		if (fade <= 0)
		{
			return;
		}
		g.setStroke(new BasicStroke((float) Math.max(1d, 4d * (1d - t))));
		g.setColor(withAlpha(colour, fade));
		g.drawOval(cx - radius, cy - radius, radius * 2, radius * 2);
	}

	public static java.awt.Rectangle modelBounds(int width, int height, long age)
	{
		double scale = cardScale(age, true);
		return scale <= 0d ? null : modelBounds(width / 2, (int) (height * 0.44d), scale);
	}

	private static java.awt.Rectangle modelBounds(int cx, int cy, double scale)
	{
		double bob = 0d;

		java.awt.Rectangle local = CardRenderer.artBounds(CARD_WIDTH, CARD_HEIGHT);
		double originX = cx - CARD_WIDTH / 2d * scale;
		double originY = cy + bob - CARD_HEIGHT / 2d * scale;
		return new java.awt.Rectangle(
			(int) Math.round(originX + local.x * scale),
			(int) Math.round(originY + local.y * scale),
			(int) Math.round(local.width * scale),
			(int) Math.round(local.height * scale));
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

	private static final Color PRISM_GLOW = new Color(0xE6, 0xEA, 0xF4);

	private static void drawPrismaticRays(Graphics2D g, int cx, int cy, long age, double eased,
		float alpha)
	{
		float hueDrift = (age % 7000L) / 7000f;
		for (int ring = 0; ring < 2; ring++)
		{
			AffineTransform before = g.getTransform();
			g.translate(cx, cy);
			g.rotate(ring == 0 ? age / 3600d : -age / 2200d);

			int shards = ring == 0 ? RAYS : RAYS / 2;
			double reach = (ring == 0 ? 210d : 138d) * eased;
			for (int i = 0; i < shards; i++)
			{
				double angle = Math.PI * 2d * i / shards + (ring == 0 ? 0d : Math.PI / shards);
				double sway = 0.08d + 0.04d * Math.sin(age / 260d + i);
				double length = reach * (i % 2 == 0 ? 1d : 0.62d);
				double waist = length * 0.34d;

				Path2D.Double shard = new Path2D.Double();
				shard.moveTo(0, 0);
				shard.lineTo(Math.cos(angle - sway) * waist, Math.sin(angle - sway) * waist);
				shard.lineTo(Math.cos(angle) * length, Math.sin(angle) * length);
				shard.lineTo(Math.cos(angle + sway) * waist, Math.sin(angle + sway) * waist);
				shard.closePath();

				Color hue = Color.getHSBColor(
					(hueDrift + i / (float) shards + ring * 0.5f) % 1f, 0.5f, 1f);
				g.setColor(withAlpha(hue, (int) ((i % 2 == 0 ? 46 : 26) * alpha)));
				g.fill(shard);
			}
			g.setTransform(before);
		}
	}

	private static void drawPrismaticRings(Graphics2D g, int cx, int cy, long age, double eased,
		float alpha)
	{
		Color[] split = {new Color(0xFF, 0x5C, 0x8A), new Color(0x5C, 0xFF, 0xC0),
			new Color(0x6C, 0x9C, 0xFF)};
		for (int i = 0; i < 3; i++)
		{
			double offset = (age / 900d + i / 3d) % 1d;
			int fade = (int) (70 * (1d - offset) * alpha);
			if (fade <= 0)
			{
				continue;
			}
			g.setStroke(new BasicStroke(2f));
			for (int channel = 0; channel < split.length; channel++)
			{
				int radius = (int) (60 + offset * 190 * eased) + channel * 3;
				g.setColor(withAlpha(split[channel], fade));
				g.drawOval(cx - radius, cy - radius, radius * 2, radius * 2);
			}
		}
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

	private static void drawChrome(Graphics2D g, int cx, int cy, Card card, double scale, long age)
	{
		double bob = Math.sin(age / 700d) * 4d;

		AffineTransform before = g.getTransform();
		g.translate(cx, cy + bob);
		g.scale(scale, scale);
		CardRenderer.drawChrome(g, card, -CARD_WIDTH / 2, -CARD_HEIGHT / 2,
			CARD_WIDTH, CARD_HEIGHT, false, false);
		g.setTransform(before);
	}

	private static void drawCard(Graphics2D g, int cx, int cy, Card card, BufferedImage art,
		double scale, long age, boolean modelArt)
	{
		double bob = Math.sin(age / 700d) * 4d;

		AffineTransform before = g.getTransform();
		g.translate(cx, cy + bob);
		g.scale(scale, scale);
		CardRenderer.draw(g, card, -CARD_WIDTH / 2, -CARD_HEIGHT / 2,
			CARD_WIDTH, CARD_HEIGHT,
			0, true, System.currentTimeMillis(), art, false, false, modelArt);
		g.setTransform(before);
	}

	private static void drawStars(Graphics2D g, int cx, int cy, Color glow, int stars,
		long sinceStamp, float alpha, boolean prismatic)
	{
		int y = cy + CARD_HEIGHT / 2 + 26;
		int size = 11;
		int gap = 7;
		int total = stars * size * 2 + (stars - 1) * gap;
		int x = cx - total / 2 + size;
		float hueDrift = (Math.max(0L, sinceStamp) % 7000L) / 7000f;

		for (int i = 0; i < stars; i++)
		{
			long due = 90L + (long) i * (150L - 9L * i);
			if (sinceStamp < due)
			{
				continue;
			}
			double pop = clamp01((sinceStamp - due) / (double) STAR_POP_MS);
			double c1 = 1.70158d;
			double c3 = c1 + 1d;
			double back = 1d + c3 * Math.pow(pop - 1d, 3d) + c1 * Math.pow(pop - 1d, 2d);
			double grow = size * back;

			if (i == stars - 1 && stars >= DIAMOND_STARS)
			{
				double punch = clamp01((sinceStamp - due) / 420d);
				grow *= 1d + 0.7d * Math.sin(Math.PI * punch);
			}

			Color colour = prismatic
				? Color.getHSBColor((hueDrift + i / (float) stars) % 1f, 0.45f, 1f)
				: glow;
			g.setColor(withAlpha(colour, (int) (255 * alpha)));
			fillStar(g, x + i * (size * 2 + gap), y, Math.max(1d, grow));
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
