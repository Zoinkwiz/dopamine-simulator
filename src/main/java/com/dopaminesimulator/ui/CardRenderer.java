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
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Rarity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.LinearGradientPaint;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import net.runelite.client.ui.FontManager;
import java.util.ArrayList;
import java.util.List;

public final class CardRenderer
{
	private static volatile Function<CardSet, BufferedImage> BADGE_SOURCE;

	public static void setBadgeSource(Function<CardSet, BufferedImage> source)
	{
		BADGE_SOURCE = source;
	}

	public static final double ASPECT = 1.4d;
	private static final Color BODY_TOP = new Color(0x26, 0x26, 0x2A);
	private static final Color BODY_BOTTOM = new Color(0x12, 0x12, 0x14);
	private static final Color PLATE = new Color(0x0D, 0x0D, 0x0F);
	private static final Color UNOWNED_BODY = new Color(0x1A, 0x1A, 0x1C);
	private static final Color UNOWNED_EDGE = new Color(0x30, 0x30, 0x34);
	private static final Color STAR_EMPTY = new Color(0x3A, 0x3A, 0x3E);
	private static final Color SHADOW = new Color(0, 0, 0, 70);
	private CardRenderer()
	{
	}
	public static int widthForHeight(int height)
	{
		return (int) Math.round(height / ASPECT);
	}
	public static int heightForWidth(int width)
	{
		return (int) Math.round(width * ASPECT);
	}

	public static void draw(Graphics2D graphics, Card card, int x, int y, int width, int height,
							int stars, boolean owned, long animMs, BufferedImage art)
	{
		draw(graphics, card, x, y, width, height, stars, owned, animMs, art, false, false);
	}

	public static void draw(Graphics2D graphics, Card card, int x, int y, int width, int height,
							int stars, boolean owned, long animMs, BufferedImage art, boolean shiny)
	{
		draw(graphics, card, x, y, width, height, stars, owned, animMs, art, shiny, false);
	}

	public static void draw(Graphics2D graphics, Card card, int x, int y, int width, int height,
							int stars, boolean owned, long animMs, BufferedImage art, boolean shiny,
							boolean gilded)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.translate(x, y);
		int radius = Math.max(3, height / 14);
		Rarity rarity = card.getRarity();
		drawShadow(g, width, height, radius);
		if (!owned)
		{
			drawLocked(g, card, width, height, radius);
			g.dispose();
			return;
		}
		drawFrame(g, rarity, width, height, radius, frameColour(rarity, shiny, gilded));
		int border = borderWidth(height);
		int innerX = border;
		int innerY = border;
		int innerW = width - border * 2;
		int innerH = height - border * 2;
		int innerRadius = Math.max(2, radius - border);

		drawBody(g, innerX, innerY, innerW, innerH, innerRadius);
		boolean compact = isCompact(height);
		int artX = innerX + Math.max(1, height / 40);
		int artY = innerY + Math.max(1, height / 40);
		int artW = innerW - (artX - innerX) * 2;
		int artH = (int) (innerH * (compact ? 0.74d : 0.48d));
		drawArtWindow(g, card, artX, artY, artW, artH, art);
		if (!compact)
		{
			drawNamePlate(g, card, innerX, artY + artH, innerW,
				innerH - artH - (artY - innerY), height);
		}

		drawStars(g, rarity, innerX, innerY + innerH, innerW, height, stars, compact);
		drawRarityPip(g, rarity, width, height, shiny, gilded);
		drawSetBadge(g, card, height);
		if (rarity.ordinal() >= Rarity.RARE.ordinal())
		{
			drawFoil(g, rarity, artX, artY, artW, artH, animMs);
		}
		if (gilded)
		{
			drawGildedTrim(g, artX, artY, artW, artH);
		}
		if (shiny)
		{
			drawShine(g, 0, 0, width, height, animMs);
		}

		g.dispose();
	}

	private static void drawShine(Graphics2D g, int x, int y, int width, int height, long animMs)
	{
		Shape clip = g.getClip();
		int radius = Math.max(2, height / 8);
		g.setClip(new RoundRectangle2D.Float(x, y, width, height, radius, radius));

		float phase = (animMs % 2600L) / 2600f;
		float sweep = -1.2f + phase * 3.4f;
		float cx = x + width * sweep;

		Color[] bands = {
			new Color(255, 90, 90, 0),
			new Color(255, 190, 60, 120),
			new Color(120, 255, 140, 150),
			new Color(90, 200, 255, 140),
			new Color(210, 120, 255, 110),
			new Color(255, 90, 90, 0),
		};
		float[] stops = {0f, 0.22f, 0.44f, 0.62f, 0.82f, 1f};

		g.setPaint(new LinearGradientPaint(
			new Point2D.Float(cx - width * 0.55f, y),
			new Point2D.Float(cx + width * 0.55f, y + height),
			stops, bands));
		Composite previous = g.getComposite();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
		g.fillRect(x, y, width, height);
		g.setComposite(previous);
		g.setClip(clip);
	}

	private static void drawGildedTrim(Graphics2D g, int artX, int artY, int artW, int artH)
	{
		int artRadius = Math.max(2, artH / 8);
		g.setStroke(new BasicStroke(Math.max(1f, artH / 34f)));
		g.setPaint(new LinearGradientPaint(
			new Point2D.Float(artX, artY), new Point2D.Float(artX + artW, artY + artH),
			new float[]{0f, 0.5f, 1f},
			new Color[]{
				new Color(0xFF, 0xEF, 0xBE),
				new Color(0xC9, 0x95, 0x22),
				new Color(0xFF, 0xF6, 0xD4),
			}));
		g.drawRoundRect(artX, artY, artW - 1, artH - 1, artRadius, artRadius);
	}

	private static void drawShadow(Graphics2D g, int width, int height, int radius)
	{
		g.setColor(SHADOW);
		g.fillRoundRect(1, 2, width, height, radius, radius);
	}
	private static Color frameColour(Rarity rarity, boolean shiny, boolean gilded)
	{
		if (shiny && gilded)
		{
			return new Color(0xF7, 0xE4, 0xB0);
		}
		if (gilded)
		{
			return new Color(0xD9, 0xA8, 0x33);
		}
		if (shiny)
		{
			return new Color(0x7C, 0xE6, 0xD6);
		}
		return rarity.getColour();
	}

	private static void drawFrame(Graphics2D g, Rarity rarity, int width, int height, int radius,
								  Color base)
	{
		Color light = lighten(base, 0.45d);
		Color dark = darken(base, 0.45d);
		g.setPaint(new GradientPaint(0, 0, light, width, height, dark));
		g.fillRoundRect(0, 0, width, height, radius, radius);

		g.setColor(base);
		g.setStroke(new BasicStroke(1f));
		g.drawRoundRect(0, 0, width - 1, height - 1, radius, radius);

		int inset = Math.max(1, height / 45);
		switch (rarity)
		{
			case COMMON:
				break;
			case UNCOMMON:
				g.setColor(withAlpha(lighten(base, 0.6d), 150));
				g.drawLine(radius, 1, width - radius, 1);
				break;
			case RARE:
				g.setColor(withAlpha(lighten(base, 0.55d), 170));
				g.drawRoundRect(inset, inset, width - inset * 2 - 1, height - inset * 2 - 1,
					radius, radius);
				break;
			case EPIC:
				g.setStroke(new BasicStroke(1.4f));
				g.setColor(withAlpha(lighten(base, 0.7d), 200));
				g.drawRoundRect(inset, inset, width - inset * 2 - 1, height - inset * 2 - 1,
					radius, radius);
				g.setColor(withAlpha(Color.WHITE, 60));
				g.drawRoundRect(inset * 2, inset * 2, width - inset * 4 - 1, height - inset * 4 - 1,
					radius, radius);
				break;
			default:
				g.setStroke(new BasicStroke(Math.max(1.6f, height / 70f)));
				g.setColor(withAlpha(new Color(0xFF, 0xF3, 0xC4), 230));
				g.drawRoundRect(inset, inset, width - inset * 2 - 1, height - inset * 2 - 1,
					radius, radius);
				g.setStroke(new BasicStroke(1f));
				g.setColor(withAlpha(Color.WHITE, 110));
				g.drawRoundRect(inset * 3, inset * 3, width - inset * 6 - 1, height - inset * 6 - 1,
					radius, radius);
				drawCornerStuds(g, width, height, Math.max(2, height / 34));
				break;
		}
	}
	private static void drawRadiantBurst(Graphics2D g, Color base, int x, int y,
										 int width, int height)
	{
		double cx = x + width / 2d;
		double cy = y + height * 0.45d;
		double reach = Math.max(width, height) * 1.2d;
		g.setColor(withAlpha(lighten(base, 0.55d), 46));
		for (int i = 0; i < 12; i++)
		{
			double angle = i * Math.PI / 6d;
			Path2D.Double ray = new Path2D.Double();
			ray.moveTo(cx, cy);
			ray.lineTo(cx + Math.cos(angle - 0.10d) * reach, cy + Math.sin(angle - 0.10d) * reach);
			ray.lineTo(cx + Math.cos(angle + 0.10d) * reach, cy + Math.sin(angle + 0.10d) * reach);
			ray.closePath();
			g.fill(ray);
		}
	}
	private static void drawCornerStuds(Graphics2D g, int width, int height, int size)
	{
		g.setColor(withAlpha(new Color(0xFF, 0xF7, 0xD8), 200));
		int pad = size;
		g.fillOval(pad, pad, size, size);
		g.fillOval(width - pad - size, pad, size, size);
		g.fillOval(pad, height - pad - size, size, size);
		g.fillOval(width - pad - size, height - pad - size, size, size);
	}
	private static void drawBody(Graphics2D g, int x, int y, int width, int height, int radius)
	{
		g.setPaint(new GradientPaint(x, y, BODY_TOP, x, y + height, BODY_BOTTOM));
		g.fillRoundRect(x, y, width, height, radius, radius);
	}
	private static void drawArtWindow(Graphics2D g, Card card, int x, int y, int width, int height,
									  BufferedImage art)
	{
		if (width <= 0 || height <= 0)
		{
			return;
		}
		Color base = card.getRarity().getColour();
		Shape clip = g.getClip();
		int radius = Math.max(2, height / 8);
		g.setClip(new RoundRectangle2D.Float(x, y, width, height, radius, radius));
		g.setPaint(new GradientPaint(x, y, darken(base, 0.80d), x, y + height, new Color(0x0A, 0x0A, 0x0C)));
		g.fillRect(x, y, width, height);
		float bloomRadius = Math.max(width, height) * 0.75f;
		int bloomAlpha = width < 44 ? 130 : 90;
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(x + width / 2f, y + height * 0.42f),
			bloomRadius,
			new float[]{0f, 1f},
			new Color[]{withAlpha(lighten(base, 0.3d), bloomAlpha), withAlpha(base, 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(x, y, width, height);
		if (card.getRarity() == Rarity.LEGENDARY)
		{
			drawRadiantBurst(g, base, x, y, width, height);
		}
		drawSigil(g, card, x, y, width, height);

		if (art != null)
		{
			drawArtImage(g, art, x, y, width, height);
		}

		drawSetSymbol(g, card.getSet(), x + width - symbolSize(height) - 3,
			y + height - symbolSize(height) - 3, symbolSize(height), 70);
		g.setClip(clip);
		g.setColor(withAlpha(darken(base, 0.3d), 160));
		g.setStroke(new BasicStroke(1f));
		g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	}
	private static void drawSigil(Graphics2D g, Card card, int x, int y, int width, int height)
	{
		int seed = card.getId().hashCode();
		Color base = card.getRarity().getColour();
		double cx = x + width / 2d;
		double cy = y + height / 2d;
		double maxRadius = Math.min(width, height) * 0.42d;
		int points = 3 + Math.floorMod(seed, 5);
		int rings = 2 + Math.floorMod(seed >> 3, 3);
		double baseRotation = Math.floorMod(seed >> 6, 360) * Math.PI / 180d;

		g.setPaint(new RadialGradientPaint(
			new Point2D.Double(cx, cy),
			(float) maxRadius,
			new float[]{0f, 1f},
			new Color[]{withAlpha(lighten(base, 0.15d), 90), withAlpha(base, 10)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fill(new Ellipse2D.Double(cx - maxRadius, cy - maxRadius, maxRadius * 2, maxRadius * 2));
		g.setStroke(new BasicStroke(Math.max(1.2f, height / 42f)));
		for (int ring = 0; ring < rings; ring++)
		{
			double scale = 1d - ring * (0.26d / rings * 2);
			double rotation = baseRotation + ring * (Math.PI / points);
			int alpha = 150 - ring * 34;
			if (alpha <= 0)
			{
				continue;
			}
			g.setColor(withAlpha(lighten(base, 0.5d), alpha));
			Path2D.Double path = new Path2D.Double();
			for (int i = 0; i < points; i++)
			{
				double angle = rotation + i * 2 * Math.PI / points;
				double px = cx + Math.cos(angle) * maxRadius * scale;
				double py = cy + Math.sin(angle) * maxRadius * scale;
				if (i == 0)
				{
					path.moveTo(px, py);
				}
				else
				{
					path.lineTo(px, py);
				}
			}
			path.closePath();
			g.draw(path);
		}
	}
	private static void drawArtImage(Graphics2D g, BufferedImage art, int x, int y,
									 int width, int height)
	{
		double scale = Math.min(width * 0.78d / art.getWidth(), height * 0.78d / art.getHeight());
		int drawW = (int) Math.round(art.getWidth() * scale);
		int drawH = (int) Math.round(art.getHeight() * scale);
		Object previous = g.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

		g.drawImage(art, x + (width - drawW) / 2, y + (height - drawH) / 2, drawW, drawH, null);
		if (previous != null)
		{
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, previous);
		}
	}
	private static void drawNamePlate(Graphics2D g, Card card, int x, int y, int width, int height,
									  int cardHeight)
	{
		int starStrip = starStripHeight(cardHeight);
		int plateHeight = Math.max(1, height - starStrip);
		if (plateHeight <= 2 || width <= 2)
		{
			return;
		}
		int inset = Math.max(1, cardHeight / 40);
		int plateX = x + inset;
		int plateW = width - inset * 2;
		g.setColor(PLATE);
		g.fillRoundRect(plateX, y + 1, plateW, plateHeight - 2, 3, 3);
		g.setColor(withAlpha(card.getRarity().getColour(), 120));
		g.setStroke(new BasicStroke(1f));
		g.drawLine(plateX + 2, y + 1, plateX + plateW - 2, y + 1);

		g.setColor(Color.WHITE);
		g.setFont(cardHeight >= 150
			? FontManager.getRunescapeBoldFont()
			: FontManager.getRunescapeSmallFont());
		int available = plateW - 6;
		List<String> lines = wrapToLines(g, card.getName(), available, 3);

		FontMetrics metrics = g.getFontMetrics();
		int lineHeight = metrics.getHeight();
		int block = lines.size() * lineHeight;
		int top = y + (plateHeight - block) / 2 + metrics.getAscent();
		for (int i = 0; i < lines.size(); i++)
		{
			String line = ellipsise(metrics, lines.get(i), available);
			g.drawString(line, plateX + (plateW - metrics.stringWidth(line)) / 2,
				top + i * lineHeight);
		}
	}
	private static List<String> wrapToLines(Graphics2D g, String text, int width, int maxLines)
	{
		FontMetrics metrics = g.getFontMetrics();
		List<String> lines = new ArrayList<>();
		StringBuilder current = new StringBuilder();
		for (String word : text.split(" "))
		{
			String candidate = current.length() == 0 ? word : current + " " + word;

			if (metrics.stringWidth(candidate) <= width || current.length() == 0)
			{
				current.setLength(0);
				current.append(candidate);
				continue;
			}
			lines.add(current.toString());
			current.setLength(0);
			current.append(word);
		}
		if (current.length() > 0)
		{
			lines.add(current.toString());
		}
		if (lines.size() <= maxLines)
		{
			return lines;
		}
		List<String> trimmed = new ArrayList<>(lines.subList(0, maxLines));
		int last = maxLines - 1;
		trimmed.set(last, ellipsise(metrics, trimmed.get(last) + "…", width));
		return trimmed;
	}
	private static void drawSetSymbol(Graphics2D g, CardSet set, int x, int y, int size, int alpha)
	{
		if (size < 4)
		{
			return;
		}
		int half = size / 2;
		g.setColor(new Color(0xC8, 0xC8, 0xD2, alpha));
		g.setStroke(new BasicStroke(Math.max(1f, size / 7f)));

		switch (set)
		{
			case QUESTS:

				g.drawLine(x + half, y, x + half, y + size - 2);
				g.fillRect(x + half, y + size - 1, 1, 1);
				break;
			case SKILLS:
				g.drawLine(x, y + size, x + half, y);
				g.drawLine(x + half, y, x + size, y + size);
				break;
			case BOSSES:
				Path2D.Double diamond = new Path2D.Double();
				diamond.moveTo(x + half, y);
				diamond.lineTo(x + size, y + half);
				diamond.lineTo(x + half, y + size);
				diamond.lineTo(x, y + half);
				diamond.closePath();
				g.draw(diamond);
				break;
			case ITEMS:
				g.drawRect(x, y, size, size);
				break;
			case MINIGAMES:
				g.drawOval(x, y, size, size);
				break;
			case SLAYER:
				g.drawLine(x, y, x + size, y + size);
				g.drawLine(x + size, y, x, y + size);
				break;
			default:
				g.drawLine(x, y + half, x + size, y + half);
				g.drawLine(x + half, y, x + half, y + size);
				break;
		}
	}
	private static void drawStars(Graphics2D g, Rarity rarity, int x, int bottom, int width,
								  int cardHeight, int stars, boolean compact)
	{
		drawStarSegments(g, rarity, x, bottom, width, cardHeight, stars, Rarity.MAX_STARS, compact);
	}
	private static void drawStarSegments(Graphics2D g, Rarity rarity, int x, int bottom, int width,
										 int cardHeight, int stars, int total, boolean compact)
	{
		int strip = compact ? compactStarStripHeight(cardHeight) : starStripHeight(cardHeight);
		int gap = 1;
		int inset = 3;
		String count = stars + "/" + total;
		int countWidth = 0;
		if (!compact)
		{
			g.setFont(FontManager.getRunescapeSmallFont());
			countWidth = g.getFontMetrics().stringWidth(count) + 4;
		}
		int available = width - inset * 2 - countWidth - gap * (total - 1);
		int segmentWidth = Math.max(2, available / total);
		int segmentHeight = Math.max(2, Math.round(strip * (compact ? 0.42f : 0.5f)));
		int barWidth = total * segmentWidth + (total - 1) * gap;
		int startX = x + (width - barWidth - countWidth) / 2;
		int segmentY = bottom - strip + (strip - segmentHeight) / 2;
		for (int i = 0; i < total; i++)
		{
			g.setColor(i < stars ? lighten(rarity.getColour(), 0.35d) : STAR_EMPTY);
			g.fillRoundRect(startX + i * (segmentWidth + gap), segmentY,
				segmentWidth, segmentHeight, 2, 2);
		}
		if (!compact)
		{
			FontMetrics metrics = g.getFontMetrics();
			g.setColor(stars > 0 ? lighten(rarity.getColour(), 0.35d) : STAR_EMPTY);
			g.drawString(count, startX + barWidth + 4,
				bottom - strip + (strip + metrics.getAscent()) / 2 - 1);
		}
	}

	private static void drawSetBadge(Graphics2D g, Card card, int height)
	{
		if (BADGE_SOURCE == null || card == null)
		{
			return;
		}
		BufferedImage badge = BADGE_SOURCE.apply(card.getSet());
		if (badge == null)
		{
			return;
		}
		int size = Math.max(7, height / 9);
		int pad = Math.max(2, height / 30);
		Composite before = g.getComposite();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
		g.drawImage(badge, pad, pad, size, size, null);
		g.setComposite(before);
	}

	private static void drawRarityPip(Graphics2D g, Rarity rarity, int width, int height,
									  boolean shiny, boolean gilded)
	{
		int size = Math.max(4, height / 13);
		int x = width - size - Math.max(2, height / 30);
		int y = Math.max(2, height / 30);
		g.setColor(new Color(0, 0, 0, 150));
		g.fillOval(x - 1, y - 1, size + 2, size + 2);

		if (shiny && gilded)
		{
			g.setPaint(new LinearGradientPaint(
				new Point2D.Float(x, y), new Point2D.Float(x + size, y + size),
				new float[]{0f, 0.5f, 1f},
				new Color[]{new Color(0xFF, 0xEF, 0xBE), new Color(0x7C, 0xE6, 0xD6),
					new Color(0xC9, 0x95, 0x22)}));
		}
		else if (gilded)
		{
			g.setPaint(new GradientPaint(x, y, new Color(0xFF, 0xEF, 0xBE),
				x, y + size, new Color(0xB8, 0x84, 0x14)));
		}
		else if (shiny)
		{
			g.setPaint(new LinearGradientPaint(
				new Point2D.Float(x, y), new Point2D.Float(x + size, y + size),
				new float[]{0f, 0.35f, 0.7f, 1f},
				new Color[]{new Color(0xFF, 0x9A, 0x9A), new Color(0x9A, 0xFF, 0xB4),
					new Color(0x9A, 0xC8, 0xFF), new Color(0xD8, 0x9A, 0xFF)}));
		}
		else
		{
			g.setPaint(new GradientPaint(x, y, lighten(rarity.getColour(), 0.5d),
				x, y + size, darken(rarity.getColour(), 0.3d)));
		}
		g.fillOval(x, y, size, size);

		if (shiny || gilded)
		{
			g.setStroke(new BasicStroke(1f));
			g.setColor(new Color(255, 255, 255, 190));
			g.drawOval(x, y, size - 1, size - 1);
		}

		g.setColor(new Color(255, 255, 255, 120));
		g.fillOval(x + size / 4, y + size / 5, Math.max(1, size / 4), Math.max(1, size / 5));
	}
	private static void drawFoil(Graphics2D g, Rarity rarity, int x, int y, int width, int height,
								 long animMs)
	{
		Shape clip = g.getClip();
		int radius = Math.max(2, height / 8);
		g.setClip(new RoundRectangle2D.Float(x, y, width, height, radius, radius));
		double phase = animMs == 0 ? 0.35d : ((animMs % 4200L) / 4200d) * 1.8d - 0.4d;
		float sweep = (float) (phase * width * 2 - width * 0.5f);
		Color tint = rarity == Rarity.LEGENDARY
			? new Color(255, 236, 170)
			: rarity == Rarity.EPIC ? new Color(226, 190, 255)
				: new Color(200, 226, 255);
		int peak = rarity == Rarity.LEGENDARY ? 62 : rarity == Rarity.EPIC ? 46 : 30;
		g.setPaint(new GradientPaint(
			x + sweep, y, withAlpha(tint, 0),
			x + sweep + width * 0.42f, y + height, withAlpha(tint, peak),
			false));
		g.fillRect(x, y, width, height);
		g.setPaint(new GradientPaint(
			x + sweep + width * 0.42f, y, withAlpha(tint, peak),
			x + sweep + width * 0.84f, y + height, withAlpha(tint, 0),
			false));
		g.fillRect(x, y, width, height);
		g.setStroke(new BasicStroke(Math.max(1f, height / 60f)));
		g.setPaint(new GradientPaint(
			x + sweep + width * 0.30f, y, withAlpha(tint, 0),
			x + sweep + width * 0.42f, y + height * 0.5f, withAlpha(tint, 200),
			true));
		g.drawLine((int) (x + sweep + width * 0.42f), y - height,
			(int) (x + sweep + width * 0.9f), y + height * 2);
		g.setClip(clip);
	}

	private static void drawLocked(Graphics2D g, Card card, int width, int height, int radius)
	{
		g.setColor(UNOWNED_BODY);
		g.fillRoundRect(0, 0, width, height, radius, radius);
		g.setColor(UNOWNED_EDGE);
		g.setStroke(new BasicStroke(1f));
		g.drawRoundRect(0, 0, width - 1, height - 1, radius, radius);

		g.setColor(withAlpha(card.getRarity().getColour(), 90));
		int pip = Math.max(3, height / 16);
		g.fillOval(width - pip - Math.max(2, height / 30), Math.max(2, height / 30), pip, pip);
		g.setColor(new Color(0x4A, 0x4A, 0x50));
		g.setFont(height >= 150 ? FontManager.getRunescapeBoldFont()
			: FontManager.getRunescapeSmallFont());
		FontMetrics metrics = g.getFontMetrics();
		String glyph = "?";
		g.drawString(glyph, (width - metrics.stringWidth(glyph)) / 2,
			height / 2 + metrics.getAscent() / 2 - metrics.getDescent() / 2);
	}
	private static int borderWidth(int height)
	{
		return Math.max(1, Math.round(height / 34f));
	}
	private static boolean isCompact(int height)
	{
		return height < 78;
	}
	private static int starStripHeight(int height)
	{
		return Math.max(6, Math.round(height * 0.13f));
	}

	private static int compactStarStripHeight(int height)
	{
		return Math.max(6, Math.round(height * 0.19f));
	}
	private static int symbolSize(int height)
	{
		return Math.max(3, Math.round(height * 0.055f));
	}
	private static String ellipsise(FontMetrics metrics, String text, int availableWidth)
	{
		if (metrics.stringWidth(text) <= availableWidth)
		{
			return text;
		}
		for (int length = text.length() - 1; length > 1; length--)
		{
			String candidate = text.substring(0, length) + "…";
			if (metrics.stringWidth(candidate) <= availableWidth)
			{
				return candidate;
			}
		}
		return text.substring(0, 1);
	}
	private static Color lighten(Color colour, double amount)
	{
		return new Color(
			(int) Math.min(255, colour.getRed() + 255 * amount),
			(int) Math.min(255, colour.getGreen() + 255 * amount),
			(int) Math.min(255, colour.getBlue() + 255 * amount));
	}
	private static Color darken(Color colour, double amount)
	{
		return new Color(
			(int) Math.max(0, colour.getRed() * (1 - amount)),
			(int) Math.max(0, colour.getGreen() * (1 - amount)),
			(int) Math.max(0, colour.getBlue() * (1 - amount)));
	}
	private static Color withAlpha(Color colour, int alpha)
	{
		return new Color(colour.getRed(), colour.getGreen(), colour.getBlue(),
			Math.max(0, Math.min(255, alpha)));
	}
}
