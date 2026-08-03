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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import net.runelite.client.ui.FontManager;

public final class Skin
{
	public static final Color BG = new Color(0x1E, 0x1E, 0x21);
	public static final Color CARD = new Color(0x2E, 0x2E, 0x34);
	public static final Color CARD_HOVER = new Color(0x3C, 0x3C, 0x44);
	public static final Color CARD_DEEP = new Color(0x24, 0x24, 0x28);
	public static final Color LINE = new Color(0x14, 0x14, 0x17);

	public static final Color WHITE = new Color(0xF0, 0xF0, 0xF4);
	public static final Color MUTED = new Color(0xB4, 0xB4, 0xC2);
	public static final Color FADED = new Color(0x96, 0x96, 0xA4);

	public static final Color ORANGE = new Color(0xFF, 0x98, 0x1F);
	public static final Color GOLD = new Color(0xFF, 0xC8, 0x45);
	public static final Color GOLD_DEEP = new Color(0xA0, 0x70, 0x1C);
	public static final Color YELLOW = new Color(0xFF, 0xD9, 0x1F);
	public static final Color GREEN = new Color(0x4C, 0xC4, 0x4C);
	public static final Color RED = new Color(0xD8, 0x44, 0x3C);
	public static final Color SHADOW = new Color(0, 0, 0, 190);

	private static final int RADIUS = 5;
	private static final int HERO_RADIUS = 8;

	private Skin()
	{
	}

	public static Font heading()
	{
		return FontManager.getRunescapeBoldFont();
	}

	public static Font body()
	{
		return FontManager.getRunescapeFont();
	}

	public static Font small()
	{
		return FontManager.getRunescapeSmallFont();
	}

	public static void smooth(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}

	public static void card(Graphics2D g, int x, int y, int w, int h, Color base)
	{
		if (w <= 0 || h <= 0)
		{
			return;
		}
		g.setColor(base);
		g.fillRoundRect(x, y, w, h, RADIUS, RADIUS);
	}

	public static void edge(Graphics2D g, int x, int y, int w, int h, double progress, Color colour)
	{
		Shape clip = g.getClip();
		g.clip(new RoundRectangle2D.Float(x, y, w, h, RADIUS, RADIUS));
		g.setColor(new Color(0, 0, 0, 90));
		g.fillRect(x, y + h - 3, w, 3);
		g.setColor(colour);
		g.fillRect(x, y + h - 3, (int) Math.round(w * Math.max(0d, Math.min(1d, progress))), 3);
		g.setClip(clip);
	}

	public static void hero(Graphics2D g, int x, int y, int w, int h, Color accent)
	{
		if (w <= 4 || h <= 4)
		{
			return;
		}
		Shape clip = g.getClip();
		Shape shape = new RoundRectangle2D.Float(x, y, w, h, HERO_RADIUS, HERO_RADIUS);

		g.setPaint(new GradientPaint(x, y, mix(accent, new Color(0x26, 0x26, 0x2E), 0.30f),
			x, y + h, new Color(0x10, 0x10, 0x14)));
		g.fill(shape);

		g.clip(shape);
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(x + w * 0.2f, y), Math.max(50f, w * 0.9f),
			new float[]{0f, 1f},
			new Color[]{withAlpha(accent, 105), withAlpha(accent, 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(x, y, w, h);

		int scrimTop = y + (int) (h * 0.32f);
		g.setPaint(new GradientPaint(x, scrimTop, withAlpha(Color.BLACK, 0),
			x, y + h, withAlpha(Color.BLACK, 175)));
		g.fillRect(x, scrimTop, w, y + h - scrimTop);
		g.setClip(clip);

		frame(g, x, y, w, h, accent);
	}

	public static void frame(Graphics2D g, int x, int y, int w, int h, Color accent)
	{
		g.setStroke(new BasicStroke(2f));
		g.setPaint(new GradientPaint(x, y, GOLD, x, y + h, GOLD_DEEP));
		g.drawRoundRect(x + 1, y + 1, w - 3, h - 3, HERO_RADIUS - 1, HERO_RADIUS - 1);

		g.setStroke(new BasicStroke(1f));
		g.setColor(new Color(0, 0, 0, 130));
		g.drawRoundRect(x + 3, y + 3, w - 7, h - 7, HERO_RADIUS - 3, HERO_RADIUS - 3);
	}

	public static void text(Graphics2D g, String value, int x, int baseline, Color colour)
	{
		g.setColor(SHADOW);
		g.drawString(value, x + 1, baseline + 1);
		g.setColor(colour);
		g.drawString(value, x, baseline);
	}

	public static void centred(Graphics2D g, String value, int x, int w, int baseline, Color colour)
	{
		text(g, value, x + (w - g.getFontMetrics().stringWidth(value)) / 2, baseline, colour);
	}

	public static void right(Graphics2D g, String value, int rightEdge, int baseline, Color colour)
	{
		text(g, value, rightEdge - g.getFontMetrics().stringWidth(value), baseline, colour);
	}

	public static void bar(Graphics2D g, int x, int y, int w, int h, double progress, Color fill)
	{
		bar(g, x, y, w, h, progress, fill, null);
	}

	public static void bar(Graphics2D g, int x, int y, int w, int h, double progress, Color fill,
		String label)
	{
		g.setColor(new Color(0x12, 0x12, 0x15));
		g.fillRoundRect(x, y, w, h, h / 2, h / 2);

		int filled = (int) Math.round(w * Math.max(0d, Math.min(1d, progress)));
		if (filled > 2)
		{
			g.setPaint(new GradientPaint(x, y, brighten(fill), x, y + h, fill));
			g.fillRoundRect(x, y, filled, h, h / 2, h / 2);
			if (label == null)
			{

				g.setColor(withAlpha(Color.WHITE, 45));
				g.fillRoundRect(x + 2, y + 1, filled - 4, Math.max(1, h / 2 - 1), h / 3, h / 3);
			}
		}
		g.setColor(new Color(0, 0, 0, 110));
		g.setStroke(new BasicStroke(1f));
		g.drawRoundRect(x, y, w - 1, h - 1, h / 2, h / 2);

		if (label == null)
		{
			return;
		}

		FontMetrics metrics = g.getFontMetrics();
		int labelX = x + (w - metrics.stringWidth(label)) / 2;
		int baseline = y + (h + metrics.getAscent()) / 2 - 1;
		Shape clip = g.getClip();

		g.clipRect(x, y, filled, h);
		g.setColor(new Color(0x0A, 0x0A, 0x0C));
		g.drawString(label, labelX, baseline);
		g.setClip(clip);

		g.clipRect(x + filled, y, w - filled, h);
		text(g, label, labelX, baseline, WHITE);
		g.setClip(clip);
	}

	public static String elide(FontMetrics metrics, String value, int maxWidth)
	{
		if (metrics.stringWidth(value) <= maxWidth)
		{
			return value;
		}
		String trimmed = value;
		while (trimmed.length() > 1 && metrics.stringWidth(trimmed + "...") > maxWidth)
		{
			trimmed = trimmed.substring(0, trimmed.length() - 1);
		}
		return trimmed + "...";
	}

	public static Color vivid(Color colour)
	{
		float[] hsb = Color.RGBtoHSB(colour.getRed(), colour.getGreen(), colour.getBlue(), null);
		return Color.getHSBColor(hsb[0], Math.min(1f, hsb[1] * 1.25f + 0.08f),
			Math.max(0.80f, hsb[2]));
	}

	public static Color brighten(Color colour)
	{
		return new Color(Math.min(255, colour.getRed() + 55),
			Math.min(255, colour.getGreen() + 55), Math.min(255, colour.getBlue() + 55));
	}

	public static Color mix(Color a, Color b, float weightOfA)
	{
		float rest = 1f - weightOfA;
		return new Color(
			(int) (a.getRed() * weightOfA + b.getRed() * rest),
			(int) (a.getGreen() * weightOfA + b.getGreen() * rest),
			(int) (a.getBlue() * weightOfA + b.getBlue() * rest));
	}

	public static Color withAlpha(Color colour, int alpha)
	{
		return new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), alpha);
	}
}
