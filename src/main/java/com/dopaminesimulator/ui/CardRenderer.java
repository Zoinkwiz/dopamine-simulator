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
import com.dopaminesimulator.cards.NpcCardArt;
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
		draw(graphics, card, x, y, width, height, stars, owned, animMs, art, shiny, gilded, false);
	}

	public static java.awt.Rectangle artBounds(int width, int height)
	{
		int border = borderWidth(height);
		int innerW = width - border * 2;
		int innerH = height - border * 2;
		int inset = Math.max(1, height / 40);
		int artX = border + inset;
		int artY = border + inset;
		return new java.awt.Rectangle(artX, artY, innerW - inset * 2,
			(int) (innerH * (isCompact(height) ? 0.74d : 0.65d)));
	}

	public static Shape artShape(int width, int height)
	{
		java.awt.Rectangle b = artBounds(width, height);
		int radius = Math.max(2, b.height / 8);
		return new RoundRectangle2D.Float(b.x, b.y, b.width, b.height, radius, radius);
	}

	/**
	 * A card turned about its vertical and horizontal axes. Carries the projection rather than a
	 * transform, because perspective is not affine and both halves of a model card must agree
	 * on it exactly.
	 */
	/** The face is rendered at this multiple and comes down through the warp, so text survives. */
	public static final int SUPERSAMPLE = 2;

	public static final class Turn
	{
		private static final double EYE = 2.6d;
		private static final int STRIPS = 30;

		private final double cx;
		private final double cy;
		private final double scale;
		private final double yaw;
		private final double pitch;
		private final int w;
		private final int h;

		public Turn(double cx, double cy, double scale, double yaw, double pitch, int w, int h)
		{
			this.cx = cx;
			this.cy = cy;
			this.scale = scale;
			this.yaw = yaw;
			this.pitch = pitch;
			this.w = w;
			this.h = h;
		}

		/** Projects a card-local point onto the canvas. */
		public Point2D.Double project(double lx, double ly)
		{
			double u = lx - w / 2d;
			double v = ly - h / 2d;
			double eye = w * EYE;
			double depth = eye / (eye + u * Math.sin(yaw));
			double x = cx + u * Math.cos(yaw) * depth * scale;
			double y = cy + v * depth * scale * Math.cos(pitch)
				+ Math.sin(pitch) * h * 0.5d * depth * scale * 0.12d;
			return new Point2D.Double(x, y);
		}

		/** The turned outline of a card-local shape, corners and all. */
		public Shape outline(Shape local)
		{
			Path2D.Double turned = new Path2D.Double();
			double[] seg = new double[6];
			for (java.awt.geom.PathIterator it = local.getPathIterator(null, 0.6d);
				 !it.isDone(); it.next())
			{
				Point2D.Double p;
				switch (it.currentSegment(seg))
				{
					case java.awt.geom.PathIterator.SEG_MOVETO:
						p = project(seg[0], seg[1]);
						turned.moveTo(p.x, p.y);
						break;
					case java.awt.geom.PathIterator.SEG_LINETO:
						p = project(seg[0], seg[1]);
						turned.lineTo(p.x, p.y);
						break;
					case java.awt.geom.PathIterator.SEG_CLOSE:
						turned.closePath();
						break;
					default:
						break;
				}
			}
			return turned;
		}
	}

	/**
	 * Draws a rendered card face as if it were turned.
	 *
	 * <p>Each strip is placed by projecting its own column, so the edge nearest the eye comes
	 * out taller than the one going away - the keystone a shear cannot produce. Strips are drawn
	 * a pixel wide to cover the seams between them.
	 */
	public static void drawTurned(Graphics2D graphics, BufferedImage face, Turn turn)
	{
		int sw = face.getWidth();
		int sh = face.getHeight();
		Object hintBefore = graphics.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		// Boundaries are computed once and shared, so strip i ends exactly where i+1 begins.
		// Widening each strip to cover the seams is what blurred a card that was not even turned.
		int[] bx = new int[Turn.STRIPS + 1];
		int[] top = new int[Turn.STRIPS + 1];
		int[] bottom = new int[Turn.STRIPS + 1];
		for (int i = 0; i <= Turn.STRIPS; i++)
		{
			double lx = turn.w * (double) i / Turn.STRIPS;
			bx[i] = (int) Math.round(turn.project(lx, 0).x);
			top[i] = (int) Math.round(turn.project(lx, 0).y);
			bottom[i] = (int) Math.round(turn.project(lx, turn.h).y);
		}

		for (int i = 0; i < Turn.STRIPS; i++)
		{
			int sx0 = sw * i / Turn.STRIPS;
			int sx1 = sw * (i + 1) / Turn.STRIPS;
			int dx0 = Math.min(bx[i], bx[i + 1]);
			int dx1 = Math.max(bx[i], bx[i + 1]);
			int dy0 = Math.min(top[i], top[i + 1]);
			int dy1 = Math.max(bottom[i], bottom[i + 1]);
			if (sx1 <= sx0 || dx1 <= dx0 || dy1 <= dy0)
			{
				continue;
			}
			graphics.drawImage(face, dx0, dy0, dx1, dy1, sx0, 0, sx1, sh, null);
		}

		if (hintBefore != null)
		{
			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hintBefore);
		}
	}

	public enum BackMotif
	{
		COOKIE,
		EYES;

		public static BackMotif of(String name)
		{
			if (name != null)
			{
				for (BackMotif motif : values())
				{
					if (motif.name().equalsIgnoreCase(name))
					{
						return motif;
					}
				}
			}
			return COOKIE;
		}
	}

	public static void drawCardBack(Graphics2D graphics, int x, int y, int width, int height,
									Color base, Color trim, Color motifColour, BackMotif motif,
									long animMs, float alpha, boolean lit)
	{
		if (width < 8 || height < 8)
		{
			return;
		}
		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.translate(x, y);
		int arc = Math.max(8, height / 14);
		int a = (int) (255 * alpha);

		g.setPaint(new LinearGradientPaint(
			new Point2D.Float(0, 0), new Point2D.Float(0, height),
			new float[]{0f, 0.55f, 1f},
			new Color[]{withAlpha(lighten(base, 0.35d), a), withAlpha(base, a),
				withAlpha(darken(base, 0.45d), a)}));
		g.fillRoundRect(0, 0, width, height, arc, arc);

		Shape clipBefore = g.getClip();
		g.setClip(new RoundRectangle2D.Float(0, 0, width, height, arc, arc));
		g.setStroke(new BasicStroke(1f));
		g.setColor(withAlpha(trim, (int) (18 * alpha)));
		int step = Math.max(7, height / 24);
		for (int offset = -height; offset < width + height; offset += step)
		{
			g.drawLine(offset, 0, offset + height, height);
			g.drawLine(offset + height, 0, offset, height);
		}
		g.setClip(clipBefore);

		int inset = Math.max(5, height / 26);
		g.setStroke(new BasicStroke(Math.max(1.2f, height / 220f)));
		g.setColor(withAlpha(trim, (int) (170 * alpha)));
		g.drawRoundRect(0, 0, width - 1, height - 1, arc, arc);
		g.setStroke(new BasicStroke(1f));
		g.setColor(withAlpha(trim, (int) (80 * alpha)));
		g.drawRoundRect(inset, inset, width - inset * 2 - 1, height - inset * 2 - 1,
			arc / 2, arc / 2);

		int tick = Math.max(4, height / 30);
		g.setStroke(new BasicStroke(1.6f));
		g.setColor(withAlpha(lighten(trim, 0.4d), (int) (200 * alpha)));
		for (int sx = 0; sx < 2; sx++)
		{
			for (int sy = 0; sy < 2; sy++)
			{
				int px = inset + (sx == 0 ? 0 : width - inset * 2);
				int py = inset + (sy == 0 ? 0 : height - inset * 2);
				g.drawLine(px, py, px + (sx == 0 ? tick : -tick), py);
				g.drawLine(px, py, px, py + (sy == 0 ? tick : -tick));
			}
		}

		int cx = width / 2;
		int cy = height / 2;
		int radius = (int) (height * 0.155d);
		if (motif == BackMotif.EYES)
		{
			drawEyesMotif(g, cx, cy, radius, motifColour, animMs, alpha, lit);
		}
		else
		{
			drawCookieMotif(g, cx, cy, radius, motifColour, animMs, alpha, lit);
		}
		g.dispose();
	}

	private static void drawCookieMotif(Graphics2D g, int cx, int cy, int radius, Color glow,
										long animMs, float alpha, boolean lit)
	{
		int a = (int) (255 * alpha);
		int halo = radius + radius / 3;
		g.setPaint(new RadialGradientPaint(new Point2D.Float(cx, cy), Math.max(1f, halo),
			new float[]{0f, 1f},
			new Color[]{withAlpha(glow, (int) ((lit ? 90 : 45) * alpha)), withAlpha(glow, 0)}));
		g.fillOval(cx - halo, cy - halo, halo * 2, halo * 2);

		// An irregular rim, so it reads as baked rather than as a printed circle.
		Path2D.Double disc = new Path2D.Double();
		int points = 48;
		for (int i = 0; i <= points; i++)
		{
			double t = Math.PI * 2d * i / points;
			double wobble = 1d + 0.035d * Math.sin(t * 7d) + 0.02d * Math.sin(t * 3d + 1.1d);
			double px = cx + Math.cos(t) * radius * wobble;
			double py = cy + Math.sin(t) * radius * wobble;
			if (i == 0)
			{
				disc.moveTo(px, py);
			}
			else
			{
				disc.lineTo(px, py);
			}
		}
		disc.closePath();

		Color dough = new Color(0xC2, 0x8E, 0x4E);
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(cx - radius * 0.3f, cy - radius * 0.35f), Math.max(1f, radius * 1.6f),
			new float[]{0f, 0.55f, 1f},
			new Color[]{withAlpha(lighten(dough, 0.32d), a), withAlpha(dough, a),
				withAlpha(darken(dough, 0.42d), a)}));
		g.fill(disc);
		g.setStroke(new BasicStroke(Math.max(1f, radius / 16f)));
		g.setColor(withAlpha(darken(dough, 0.55d), (int) (200 * alpha)));
		g.draw(disc);

		// Chips at fixed offsets: a run that reshuffles every frame reads as noise.
		double[][] chips = {
			{-0.42d, -0.30d, 0.20d}, {0.28d, -0.44d, 0.16d}, {0.46d, 0.16d, 0.19d},
			{-0.18d, 0.34d, 0.22d}, {-0.52d, 0.22d, 0.14d}, {0.06d, -0.06d, 0.17d},
			{0.30d, 0.48d, 0.13d},
		};
		Color chip = new Color(0x3A, 0x21, 0x12);
		for (double[] c : chips)
		{
			int size = (int) (radius * c[2]);
			int px = (int) (cx + radius * c[0]) - size / 2;
			int py = (int) (cy + radius * c[1]) - size / 2;
			g.setColor(withAlpha(chip, (int) (235 * alpha)));
			g.fillOval(px, py, size * 2, (int) (size * 1.7d));
			g.setColor(withAlpha(lighten(chip, 0.35d), (int) (120 * alpha)));
			g.fillOval(px + size / 3, py + size / 4, Math.max(1, size), Math.max(1, size / 2));
		}

		// A slow sheen across the dough, so the emblem is not static.
		double sweep = (animMs % 4200L) / 4200d;
		int gx = (int) (cx - radius + sweep * radius * 2);
		g.setClip(disc);
		g.setPaint(new LinearGradientPaint(
			new Point2D.Float(gx - radius * 0.35f, cy), new Point2D.Float(gx + radius * 0.35f, cy),
			new float[]{0f, 0.5f, 1f},
			new Color[]{withAlpha(Color.WHITE, 0), withAlpha(Color.WHITE, (int) (34 * alpha)),
				withAlpha(Color.WHITE, 0)}));
		g.fillOval(cx - radius * 2, cy - radius, radius * 4, radius * 2);
		g.setClip(null);
	}

	private static void drawEyesMotif(Graphics2D g, int cx, int cy, int radius, Color glow,
									  long animMs, float alpha, boolean lit)
	{
		// Six eyes in the dark, the middle pair largest. Each breathes out of phase, so the
		// cluster looks alive rather than like a pattern.
		double[][] eyes = {
			{-1.05d, -0.30d, 0.30d}, {0d, -0.42d, 0.42d}, {1.05d, -0.30d, 0.30d},
			{-0.72d, 0.36d, 0.24d}, {0d, 0.30d, 0.30d}, {0.72d, 0.36d, 0.24d},
		};
		for (int i = 0; i < eyes.length; i++)
		{
			double breathe = 0.82d + 0.18d * Math.sin((animMs / 900d) + i * 0.9d);
			int size = Math.max(2, (int) (radius * eyes[i][2] * (lit ? 1.15d : 1d)));
			int ex = (int) (cx + radius * eyes[i][0]);
			int ey = (int) (cy + radius * eyes[i][1]);

			int halo = (int) (size * 3.2d * breathe);
			g.setPaint(new RadialGradientPaint(new Point2D.Float(ex, ey), Math.max(1f, halo),
				new float[]{0f, 0.45f, 1f},
				new Color[]{withAlpha(glow, (int) ((lit ? 150 : 105) * alpha * breathe)),
					withAlpha(glow, (int) (40 * alpha * breathe)), withAlpha(glow, 0)}));
			g.fillOval(ex - halo, ey - halo, halo * 2, halo * 2);

			Path2D.Double lens = new Path2D.Double();
			lens.moveTo(ex - size, ey);
			lens.quadTo(ex, ey - size * 0.78d, ex + size, ey);
			lens.quadTo(ex, ey + size * 0.78d, ex - size, ey);
			lens.closePath();
			g.setPaint(new RadialGradientPaint(
				new Point2D.Float(ex, ey), Math.max(1f, size),
				new float[]{0f, 0.6f, 1f},
				new Color[]{withAlpha(Color.WHITE, (int) (235 * alpha)),
					withAlpha(glow, (int) (245 * alpha)),
					withAlpha(darken(glow, 0.6d), (int) (235 * alpha))}));
			g.fill(lens);

			g.setColor(withAlpha(new Color(0x18, 0x02, 0x06), (int) (230 * alpha)));
			int slit = Math.max(1, size / 5);
			g.fillOval(ex - slit / 2, ey - (int) (size * 0.52d), slit, (int) (size * 1.04d));
		}
	}

	public static final int ORN_FILIGREE = 1;
	public static final int ORN_GEMS = 2;
	public static final int ORN_PRISM = 4;
	public static final int ORN_RAIL = 8;
	public static final int ORN_DRIPS = 16;

	/**
	 * A card's palette and which ornament it earns. Authored cards bring their own ramp from
	 * npc-card-art.json; every other card derives one from its rarity, so the same renderer
	 * draws a common and a six-star without branching on which it has.
	 */
	public static final class Style
	{
		final Color metalDark;
		final Color metalMid;
		final Color metalLight;
		final Color plate;
		final Color glow;
		final Color accent;
		final int ornament;
		final NpcCardArt art;
		/** How far up its own ramp a tier may go. Below 1 it never reaches metalLight. */
		final double polish;

		Style(Color metalDark, Color metalMid, Color metalLight, Color plate, Color glow,
			  Color accent, int ornament, NpcCardArt art, double polish)
		{
			this.polish = polish;
			this.metalDark = metalDark;
			this.metalMid = metalMid;
			this.metalLight = metalLight;
			this.plate = plate;
			this.glow = glow;
			this.accent = accent;
			this.ornament = ornament;
			this.art = art;
		}

		boolean has(int flag)
		{
			return (ornament & flag) != 0;
		}
	}

	public static Style styleFor(Card card)
	{
		NpcCardArt art = NpcCardArt.forCard(card);
		if (art != null)
		{
			int ornament = ORN_FILIGREE | ORN_GEMS | ORN_PRISM | ORN_RAIL
				| (art.isBloodDrips() ? ORN_DRIPS : 0);
			return new Style(new Color(art.getMetalDark()), new Color(art.getMetalMid()),
				new Color(art.getMetalLight()), new Color(art.getPlateColour()),
				new Color(art.getGlowColour()), new Color(art.getAccentColour()), ornament, art,
				1d);
		}

		Rarity rarity = card == null ? Rarity.COMMON : card.getRarity();
		int[] tier = TIERS[rarity.ordinal()];
		return new Style(new Color(tier[0]), new Color(tier[1]), new Color(tier[2]),
			new Color(tier[3]), new Color(tier[4]), new Color(tier[2]),
			ornamentFor(rarity), null, POLISH[rarity.ordinal()]);
	}

	/**
	 * metalDark, metalMid, metalLight, plate, glow per rarity: bronze, steel, mithril, adamant,
	 * rune. Third age is reserved for the authored six-star set.
	 */
	private static final int[][] TIERS = {
		{0x4A331B, 0x8A6335, 0xA67E4B, 0x140E08, 0x6B431C},
		{0x333A43, 0x6B7783, 0xA3AEBA, 0x0D1014, 0x55677A},
		{0x1C2660, 0x4560B8, 0x8FA4EA, 0x080A16, 0x4A68D8},
		{0x0F4034, 0x2F9575, 0x7AE0BB, 0x050F0C, 0x2FBE8A},
		{0x0C4450, 0x22A2B8, 0x88F2FF, 0x031014, 0x37CDE6},
	};

	private static final double[] POLISH = {0d, 0.35d, 0.7d, 1d, 1d};

	private static int ornamentFor(Rarity rarity)
	{
		switch (rarity)
		{
			case LEGENDARY:
				return ORN_FILIGREE | ORN_GEMS | ORN_RAIL;
			case EPIC:
				return ORN_FILIGREE | ORN_GEMS;
			case RARE:
				return ORN_FILIGREE;
			default:
				return 0;
		}
	}

	public static void draw(Graphics2D graphics, Card card, int x, int y, int width, int height,
							int stars, boolean owned, long animMs, BufferedImage art, boolean shiny,
							boolean gilded, boolean modelArt)
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
		Style style = styleFor(card);
		boolean rich = !isCompact(height);
		drawShadow(g, width, height, radius);
		if (rich && rarity.ordinal() >= Rarity.RARE.ordinal())
		{
			drawOuterGlow(g, style, width, height, radius, animMs);
		}
		if (!owned)
		{
			drawLocked(g, card, width, height, radius);
			g.dispose();
			return;
		}
		drawFrame(g, rarity, width, height, radius,
			rich ? style.metalMid : frameColour(rarity, shiny, gilded));
		if (rich)
		{
			drawMetalFrame(g, style, width, height, radius);
			if (rarity.ordinal() >= Rarity.UNCOMMON.ordinal())
			{
				drawFrameBevel(g, width, height, radius);
			}
			if (style.has(ORN_FILIGREE))
			{
				drawFiligree(g, style, width, height, radius);
			}
			drawDrips(g, style, width, height, radius, animMs);
			if (style.has(ORN_GEMS))
			{
				drawCornerGems(g, style, width, height, animMs);
			}
		}
		int border = borderWidth(height);
		int innerX = border;
		int innerY = border;
		int innerW = width - border * 2;
		int innerH = height - border * 2;
		int innerRadius = Math.max(2, radius - border);

		boolean compact = isCompact(height);
		int artX = innerX + Math.max(1, height / 40);
		int artY = innerY + Math.max(1, height / 40);
		int artW = innerW - (artX - innerX) * 2;
		int artH = (int) (innerH * (compact ? 0.74d : 0.65d));

		Color[] body = rich ? bodyTint(style) : new Color[]{BODY_TOP, BODY_BOTTOM};
		drawBody(g, innerX, innerY, innerW, innerH, innerRadius,
			modelArt ? artShape(width, height) : null, body[0], body[1]);
		drawArtWindow(g, card, style, artX, artY, artW, artH, modelArt ? null : art, modelArt,
			animMs);
		if (!compact)
		{
			drawNamePlate(g, card, style, innerX, artY + artH, innerW,
				innerH - artH - (artY - innerY), height, style.has(ORN_PRISM), animMs);
		}

		if (style.art == null || compact)
		{
			drawStars(g, rarity, innerX, innerY + innerH, innerW, height, stars, compact);
		}
		if (gilded)
		{
			drawGildedTrim(g, artX, artY, artW, artH);
		}
		if (style.art == null)
		{
			if (!modelArt && rarity.ordinal() >= Rarity.LEGENDARY.ordinal())
			{
				drawFoil(g, rarity, artX, artY, artW, artH, animMs);
			}
			drawRarityPip(g, rarity, width, height, shiny, gilded);
			drawSetBadge(g, card, height);
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
	private static void drawBody(Graphics2D g, int x, int y, int width, int height, int radius,
								 Shape hole, Color top, Color bottom)
	{
		Shape body = new RoundRectangle2D.Float(x, y, width, height, radius, radius);
		if (hole != null)
		{
			java.awt.geom.Area area = new java.awt.geom.Area(body);
			area.subtract(new java.awt.geom.Area(hole));
			body = area;
		}
		g.setPaint(new GradientPaint(x, y, top, x, y + height, bottom));
		g.fill(body);
	}
	/**
	 * The ground a card's art sits on: its metal's opposite. Cyan rune metal over a warm gold
	 * ground is the pairing that reads best, and taking the complement gives every tier the
	 * same relationship instead of leaving it to whichever colours happened to collide.
	 */
	/**
	 * One shared slate under common through epic, so the ladder is carried by the metal, the
	 * polish and the ornament rather than by a different ground each tier. Legendary keeps its
	 * gold against the rune ramp - the pairing the lower tiers are measured against.
	 */
	private static final int SLATE = 0x414B60;
	private static final int[] GROUNDS = {SLATE, SLATE, SLATE, SLATE, 0xC79A3A};

	private static Color groundFor(Card card, Style style)
	{
		if (style.art != null)
		{
			return style.accent;
		}
		return new Color(GROUNDS[(card == null ? Rarity.COMMON : card.getRarity()).ordinal()]);
	}

	private static void drawArtWindow(Graphics2D g, Card card, Style style, int x, int y,
									  int width, int height, BufferedImage art, boolean modelArt,
									  long animMs)
	{
		if (width <= 0 || height <= 0)
		{
			return;
		}
		Color base = groundFor(card, style);
		Shape clip = g.getClip();
		int radius = Math.max(2, height / 8);
		g.setClip(new RoundRectangle2D.Float(x, y, width, height, radius, radius));
		if (!modelArt)
		{
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
			drawHalo(g, style, x, y, width, height);
		}

		if (art != null)
		{
			drawArtImage(g, art, x, y, width, height);
		}

		drawSetSymbol(g, card.getSet(), x + width - symbolSize(height) - 3,
			y + height - symbolSize(height) - 3, symbolSize(height), 70);
		g.setClip(clip);

		if (modelArt)
		{
			g.setStroke(new BasicStroke(Math.max(1f, height / 34f)));
			g.setPaint(new GradientPaint(x, y, withAlpha(Color.WHITE, 110),
				x + width, y + height, withAlpha(Color.BLACK, 150)));
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
			g.setStroke(new BasicStroke(1f));
			g.setPaint(new GradientPaint(x, y, withAlpha(Color.WHITE, 120),
				x + width, y, withAlpha(Color.WHITE, 30)));
			g.drawLine(x + radius, y + 1, x + width - radius, y + 1);
			return;
		}

		g.setColor(withAlpha(darken(base, 0.3d), 160));
		g.setStroke(new BasicStroke(1f));
		g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	}
	private static void drawHalo(Graphics2D g, Style style, int x, int y, int width, int height)
	{
		float radius = Math.min(width, height) * 0.52f;
		if (radius < 4f)
		{
			return;
		}
		float cx = x + width / 2f;
		float cy = y + height * 0.44f;
		Color light = lighten(style.metalLight, 0.15d);
		g.setPaint(new RadialGradientPaint(new Point2D.Float(cx, cy), radius,
			new float[]{0f, 0.42f, 0.78f, 1f},
			new Color[]{withAlpha(light, 78), withAlpha(light, 46), withAlpha(light, 14),
				withAlpha(light, 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(x, y, width, height);

		g.setStroke(new BasicStroke(Math.max(1f, height / 90f)));
		g.setColor(withAlpha(light, 30));
		int ring = (int) (radius * 0.82d);
		g.drawOval((int) (cx - ring), (int) (cy - ring), ring * 2, ring * 2);
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
	private static void drawNamePlate(Graphics2D g, Card card, Style style, int x, int y,
									  int width, int height, int cardHeight, boolean prismatic,
									  long animMs)
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
		g.setPaint(new GradientPaint(plateX, y, characterShade(style, 0.26f),
			plateX, y + plateHeight, characterShade(style, 0.13f)));
		g.fillRoundRect(plateX, y + 1, plateW, plateHeight - 2, 3, 3);
		if (prismatic)
		{
			g.setPaint(new GradientPaint(plateX, y, withAlpha(style.metalLight, 200),
				plateX + plateW, y, withAlpha(style.metalMid, 150)));
		}
		else
		{
			g.setColor(withAlpha(card.getRarity().getColour(), 120));
		}
		g.setStroke(new BasicStroke(1f));
		g.drawLine(plateX + 2, y + 1, plateX + plateW - 2, y + 1);

		int available = plateW - 6;
		int centreX = plateX + plateW / 2;

		boolean serif = cardHeight >= 150;
		if (!serif)
		{
			g.setColor(Color.WHITE);
			g.setFont(FontManager.getRunescapeSmallFont());
			FontMetrics small = g.getFontMetrics();
			String line = ellipsise(small, card.getName(), available);
			g.drawString(line, centreX - small.stringWidth(line) / 2,
				y + plateHeight / 2 + small.getAscent() / 2);
			return;
		}

		Font font = nameFont(cardHeight);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics();
		List<String> lines = wrapToLines(g, card.getName(), available, 2);

		int lineHeight = metrics.getHeight();
		int tagHeight = Math.max(7, cardHeight / 32);
		int block = lines.size() * lineHeight + tagHeight + 4;
		int top = y + Math.max(4, (plateHeight - block) / 2) + metrics.getAscent();

		for (int i = 0; i < lines.size(); i++)
		{
			String line = ellipsise(metrics, lines.get(i), available);
			int lineY = top + i * lineHeight;
			drawTrackedText(g, line, font, 0.045f, centreX + 1, lineY + 1,
				new Color(0, 0, 0, 190));
			java.awt.Paint fill = prismatic
				? new GradientPaint(0, lineY - metrics.getAscent(), Color.WHITE,
					0, lineY, style.metalLight)
				: new GradientPaint(0, lineY - metrics.getAscent(), Color.WHITE,
					0, lineY, lighten(card.getRarity().getColour(), 0.25d));
			drawTrackedText(g, line, font, 0.045f, centreX, lineY, fill);
		}

		NpcCardArt art = NpcCardArt.forCard(card);
		Font tagFont = FontManager.getRunescapeSmallFont().deriveFont((float) tagHeight);
		int tagY = top + (lines.size() - 1) * lineHeight + tagHeight + 5;
		Color tagColour = prismatic ? PLATINUM : card.getRarity().getColour();

		String subtitle = art != null && art.getEpithet() != null
			? art.getEpithet()
			: card.getSet().getDisplayName().toUpperCase(java.util.Locale.ROOT);
		drawTrackedText(g, subtitle, tagFont, 0.22f, centreX, tagY, withAlpha(tagColour, 165));

		if (prismatic)
		{
			drawPlateProgress(g, card, plateX, tagY + Math.max(6, cardHeight / 30), plateW,
				cardHeight, animMs);
		}
	}

	private static java.util.function.ToIntFunction<String> copiesLookup;

	public static void setCopiesLookup(java.util.function.ToIntFunction<String> lookup)
	{
		copiesLookup = lookup;
	}

	private static int copiesOf(Card card)
	{
		return copiesLookup == null || card == null ? 0 : copiesLookup.applyAsInt(card.getId());
	}

	private static void drawPlateProgress(Graphics2D g, Card card, int x, int y, int width,
										  int cardHeight, long animMs)
	{
		NpcCardArt art = NpcCardArt.forCard(card);
		if (art == null)
		{
			return;
		}
		int barH = Math.max(4, cardHeight / 78);
		int labelH = Math.max(10, cardHeight / 26);

		g.setColor(new Color(255, 255, 255, 24));
		g.fillRoundRect(x, y, width, barH, barH, barH);

		int copies = copiesOf(card);
		int held = Math.min(copies, WishReveal.DIAMOND_STARS);
		int fill = width * held / WishReveal.DIAMOND_STARS;
		if (fill > 0)
		{
			g.setPaint(new LinearGradientPaint(
				new Point2D.Float(x, y), new Point2D.Float(x + fill, y),
				new float[]{0f, 0.6f, 1f},
				new Color[]{darken(new Color(art.getGlowColour()), 0.55d),
					new Color(art.getGlowColour()),
					lighten(new Color(art.getGlowColour()), 0.55d)}));
			g.fillRoundRect(x, y, fill, barH, barH, barH);
		}

		g.setColor(new Color(0, 0, 0, 190));
		g.setStroke(new BasicStroke(1f));
		for (int i = 1; i < WishReveal.DIAMOND_STARS; i++)
		{
			int mx = x + width * i / WishReveal.DIAMOND_STARS;
			g.drawLine(mx, y, mx, y + barH);
		}
		g.setColor(new Color(0, 0, 0, 150));
		g.drawRoundRect(x, y, width, barH, barH, barH);

		Font labelFont = FontManager.getRunescapeSmallFont().deriveFont((float) labelH);
		int labelY = y + barH + labelH + 2;
		Color label = withAlpha(new Color(art.getMetalLight()), 130);
		drawTrackedText(g, "COLLECTED", labelFont, 0.2f,
			x + g.getFontMetrics(labelFont).stringWidth("COLLECTED") / 2, labelY, label);
		String right = copies + " / " + WishReveal.DIAMOND_STARS;
		int rw = g.getFontMetrics(labelFont).stringWidth(right);
		drawTrackedText(g, right, labelFont, 0.2f, x + width - rw / 2, labelY, label);
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
		drawSetSymbol(g, set, x, y, size, alpha, new Color(0xC8, 0xC8, 0xD2));
	}

	private static void drawSetSymbol(Graphics2D g, CardSet set, int x, int y, int size, int alpha,
									  Color colour)
	{
		if (size < 4)
		{
			return;
		}
		int half = size / 2;
		g.setColor(withAlpha(colour, alpha));
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
		drawRarityPip(g, rarity, width, height, shiny, gilded, false, 0L);
	}

	private static void drawRarityPip(Graphics2D g, Rarity rarity, int width, int height,
									  boolean shiny, boolean gilded, boolean prismatic,
									  long animMs)
	{
		int size = Math.max(4, height / 13);
		int x = width - size - Math.max(2, height / 30);
		int y = Math.max(2, height / 30);
		g.setColor(new Color(0, 0, 0, 150));
		g.fillOval(x - 1, y - 1, size + 2, size + 2);

		if (prismatic)
		{
			float hueDrift = animMs == 0 ? 0f : (animMs % 7000L) / 7000f;
			float[] fractions = new float[PRISM_STOPS + 1];
			Color[] prism = new Color[PRISM_STOPS + 1];
			for (int i = 0; i <= PRISM_STOPS; i++)
			{
				fractions[i] = i / (float) PRISM_STOPS;
				prism[i] = Color.getHSBColor((hueDrift + fractions[i] * 0.85f) % 1f, 0.5f, 1f);
			}
			g.setPaint(new LinearGradientPaint(
				new Point2D.Float(x, y), new Point2D.Float(x + size, y + size),
				fractions, prism));
		}
		else if (shiny && gilded)
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

	private static void drawMetalFrame(Graphics2D g, Style style, int width, int height,
									   int radius)
	{
		Color m1 = style.metalDark;
		Color m2 = style.metalMid;
		Color m3 = style.metalLight;
		float[] stops;
		Color[] ramp;
		if (style.polish < 0.2d)
		{
			stops = new float[]{0f, 0.34f, 0.62f, 0.88f, 1f};
			ramp = new Color[]{m1, m2, m1, m2, m1};
		}
		else if (style.polish < 0.6d)
		{
			stops = new float[]{0f, 0.26f, 0.40f, 0.58f, 0.82f, 1f};
			ramp = new Color[]{m1, m2, m3, m2, m1, m2};
		}
		else
		{
			stops = new float[]{0f, 0.18f, 0.32f, 0.46f, 0.60f, 0.76f, 0.88f, 1f};
			ramp = new Color[]{m1, m2, m3, m2, m1, m2, m3, m1};
		}
		g.setPaint(new LinearGradientPaint(
			new Point2D.Float(0, 0), new Point2D.Float(width, height), stops, ramp));
		g.fillRoundRect(0, 0, width, height, radius, radius);
	}

	private static void drawBadges(Graphics2D g, Card card, int width, int height, int artTop,
								   int artBottom)
	{
		NpcCardArt art = NpcCardArt.forCard(card);
		if (art == null)
		{
			return;
		}
		Color m2 = new Color(art.getMetalMid());
		Color m3 = new Color(art.getMetalLight());
		int pad = Math.max(4, height / 44);

		float bannerPt = height / 30f;
		if (art.getRoleTag() != null && bannerPt >= 9f)
		{
			Font f = FontManager.getRunescapeSmallFont().deriveFont(bannerPt);
			g.setFont(f);
			FontMetrics fm = g.getFontMetrics();
			int tw = fm.stringWidth(art.getRoleTag()) + Math.max(14, height / 22);
			int th = fm.getHeight() + 3;
			int bx = 0;
			int by = Math.max(2, artTop - th - 2);
			Path2D.Double flag = new Path2D.Double();
			flag.moveTo(bx, by);
			flag.lineTo(bx + tw, by);
			flag.lineTo(bx + tw - th * 0.34d, by + th / 2d);
			flag.lineTo(bx + tw, by + th);
			flag.lineTo(bx, by + th);
			flag.closePath();
			g.setPaint(new GradientPaint(bx, by, m3, bx, by + th, m2));
			g.fill(flag);
			g.setColor(new Color(0x2A, 0x04, 0x09));
			g.drawString(art.getRoleTag(), bx + Math.max(5, height / 60),
				by + fm.getAscent() + 1);
		}

		int orbD = Math.max(9, height / 17);
		int ox = width - orbD - pad;
		int oy = pad;
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(ox + orbD * 0.34f, oy + orbD * 0.28f), Math.max(1, orbD),
			new float[]{0f, 0.42f, 1f},
			new Color[]{Color.WHITE, new Color(art.getGlowColour()), new Color(0x2C, 0x02, 0x06)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillOval(ox, oy, orbD, orbD);
		g.setColor(new Color(0, 0, 0, 150));
		g.setStroke(new BasicStroke(1f));
		g.drawOval(ox, oy, orbD, orbD);

		float chipPt = height / 32f;
		if (art.getMechanicTag() != null && chipPt >= 9f)
		{
			Font f = FontManager.getRunescapeSmallFont().deriveFont(chipPt);
			g.setFont(f);
			FontMetrics fm = g.getFontMetrics();
			int cw = fm.stringWidth(art.getMechanicTag()) + Math.max(10, height / 26);
			int ch = fm.getHeight() + 2;
			int cxp = pad + Math.max(3, height / 60);
			int cyp = artBottom - ch - Math.max(4, height / 50);
			g.setColor(new Color(10, 2, 4, 205));
			g.fillRoundRect(cxp, cyp, cw, ch, 4, 4);
			g.setColor(new Color(255, 255, 255, 26));
			g.drawRoundRect(cxp, cyp, cw, ch, 4, 4);
			g.setColor(m3);
			g.drawString(art.getMechanicTag(), cxp + Math.max(5, height / 60),
				cyp + fm.getAscent() + 1);
		}
	}

	private static void drawOuterGlow(Graphics2D g, Style style, int width, int height, int radius,
									  long animMs)
	{
		double beat = (animMs % 1500L) / 1500d;
		double pulse = 1d + 0.55d * Math.exp(-beat * 9d) + 0.30d * Math.exp(-Math.max(0d, beat - 0.24d) * 9d);
		Color glow = style.glow;
		int spread = (int) (Math.max(6, height / 12) * pulse);
		for (int i = spread; i > 0; i -= Math.max(1, spread / 7))
		{
			int alpha = (int) (34 * (1d - i / (double) spread));
			if (alpha <= 0)
			{
				continue;
			}
			g.setColor(withAlpha(glow, alpha));
			g.fillRoundRect(-i, -i + height / 40, width + i * 2, height + i * 2,
				radius + i, radius + i);
		}
	}

	private static void drawDrips(Graphics2D g, Style style, int width, int height, int radius,
								  long animMs)
	{
		if (!style.has(ORN_DRIPS) || height < 120)
		{
			return;
		}
		Shape clip = g.getClip();
		g.setClip(new RoundRectangle2D.Float(0, 0, width, height, radius, radius));
		int top = borderWidth(height) - 2;
		Color blood = new Color(0xC1, 0x12, 0x2B);
		float w = Math.max(1f, height / 250f);

		int[] atPct = {18, 34, 57, 79};
		long[] offset = {0L, 2600L, 1200L, 4100L};
		int[] runPct = {13, 8, 16, 10};
		for (int i = 0; i < atPct.length; i++)
		{
			double t = ((animMs + offset[i]) % 8000L) / 8000d;
			double len;
			int alpha;
			if (t < 0.70d)
			{
				len = (t / 0.70d) * (height * runPct[i] / 100d);
				alpha = (int) ((t < 0.08d ? 255 * t / 0.08d : 255) * 0.62d);
			}
			else if (t < 0.86d)
			{
				len = height * runPct[i] / 100d;
				alpha = (int) (158 * (1d - (t - 0.70d) / 0.16d));
			}
			else
			{
				continue;
			}
			if (len < 1d)
			{
				continue;
			}

			int x = width * atPct[i] / 100;
			g.setPaint(new LinearGradientPaint(
				new Point2D.Float(x, top), new Point2D.Float(x, (float) (top + len)),
				new float[]{0f, 0.55f, 1f},
				new Color[]{withAlpha(blood, Math.max(0, alpha)),
					withAlpha(blood, Math.max(0, (int) (alpha * 0.8d))),
					withAlpha(blood, 0)}));
			g.fillRect(x, top, (int) Math.ceil(w), (int) len);
		}
		g.setClip(clip);
	}

	private static void drawFiligree(Graphics2D g, Style style, int width, int height, int radius)
	{
		if (height < 120)
		{
			return;
		}
		int inset = Math.max(3, borderWidth(height) / 2);
		g.setColor(withAlpha(style.metalLight, 110));
		g.setStroke(new BasicStroke(Math.max(1f, height / 240f), BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10f,
			new float[]{1.6f, 2.4f, 1.6f, 5.2f}, 0f));
		g.drawRoundRect(inset, inset, width - inset * 2 - 1, height - inset * 2 - 1,
			radius, radius);
	}

	private static final Color PLATINUM = new Color(0xDC, 0xE0, 0xEA);

	private static void drawFrameBevel(Graphics2D g, int width, int height, int radius)
	{
		g.setStroke(new BasicStroke(1f));
		g.setColor(new Color(255, 255, 255, 46));
		g.drawRoundRect(1, 1, width - 3, height - 3, radius, radius);
		g.setColor(new Color(0, 0, 0, 190));
		g.drawRoundRect(0, 0, width - 1, height - 1, radius, radius);
	}

	private static void drawCornerGems(Graphics2D g, Style style, int width, int height,
									   long animMs)
	{
		int size = Math.max(3, height / 52);
		int pad = Math.max(2, height / 60);
		int[][] at = {
			{pad, pad}, {width - size - pad, pad},
			{pad, height - size - pad}, {width - size - pad, height - size - pad}};
		for (int i = 0; i < at.length; i++)
		{
			int gx = at[i][0];
			int gy = at[i][1];
			g.setPaint(new RadialGradientPaint(
				new Point2D.Float(gx + size * 0.34f, gy + size * 0.30f), Math.max(1f, size),
				new float[]{0f, 0.3f, 1f},
				new Color[]{Color.WHITE, gemHue(style, animMs, i), new Color(0, 0, 0, 220)},
				MultipleGradientPaint.CycleMethod.NO_CYCLE));
			g.fillOval(gx, gy, size, size);
		}
	}

	private static Color gemHue(Style style, long animMs, int index)
	{
		float[] hsb = Color.RGBtoHSB(style.accent.getRed(), style.accent.getGreen(),
			style.accent.getBlue(), null);
		float drift = ((animMs % 7000L) / 7000f + index * 0.25f) % 1f;
		return Color.getHSBColor((hsb[0] + (drift - 0.5f) * 0.12f + 1f) % 1f, 0.4f, 1f);
	}

	private static Font nameFont(int cardHeight)
	{
		return new Font(Font.SERIF, Font.BOLD, Math.max(9, Math.round(cardHeight / 17.5f)));
	}

	private static int drawTrackedText(Graphics2D g, String text, Font font, float tracking,
									   int centreX, int baseline, java.awt.Paint paint)
	{
		if (text == null || text.isEmpty())
		{
			return 0;
		}
		java.text.AttributedString styled = new java.text.AttributedString(text);
		styled.addAttribute(java.awt.font.TextAttribute.FONT, font);
		styled.addAttribute(java.awt.font.TextAttribute.TRACKING, tracking);
		java.awt.font.TextLayout layout =
			new java.awt.font.TextLayout(styled.getIterator(), g.getFontRenderContext());
		int width = (int) Math.ceil(layout.getAdvance());
		Shape outline = layout.getOutline(
			java.awt.geom.AffineTransform.getTranslateInstance(centreX - width / 2d, baseline));
		g.setPaint(paint);
		g.fill(outline);
		return width;
	}

	public static void drawSpecular(Graphics2D graphics, Card card, int x, int y, int width,
									int height, double pointerX, double pointerY,
									boolean prismatic, long animMs)
	{
		if (width <= 0 || height <= 0)
		{
			return;
		}
		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int radius = Math.max(3, height / 14);
		java.awt.geom.Area area = new java.awt.geom.Area(
			new RoundRectangle2D.Float(x, y, width, height, radius, radius));
		if (prismatic)
		{
			area.subtract(new java.awt.geom.Area(java.awt.geom.AffineTransform
				.getTranslateInstance(x, y).createTransformedShape(artShape(width, height))));
		}
		g.setClip(area);

		Rarity rarity = card.getRarity();
		int peak = prismatic ? 40
			: rarity.ordinal() >= Rarity.EPIC.ordinal() ? 90
			: rarity.ordinal() >= Rarity.RARE.ordinal() ? 60 : 42;
		Color tint = prismatic
			? Color.getHSBColor((animMs % 7000L) / 7000f, 0.12f, 1f)
			: rarity.ordinal() >= Rarity.EPIC.ordinal() ? rarity.getColour() : Color.WHITE;

		float glintX = (float) (x + pointerX * width);
		float glintY = (float) (y + pointerY * height);
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(glintX, glintY),
			Math.max(width, height) * 0.8f,
			new float[]{0f, 1f},
			new Color[]{withAlpha(tint, peak), withAlpha(tint, 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(x, y, width, height);
		g.dispose();
	}


	private static final double FLOOR_AT = 0.80d;

	private static Color characterShade(Style style, float brightness)
	{
		Color base = style.art != null ? new Color(style.art.getBackdropColour()) : style.plate;
		float[] hsb = Color.RGBtoHSB(base.getRed(), base.getGreen(), base.getBlue(), null);
		return Color.getHSBColor(hsb[0], Math.max(0.55f, hsb[1]), brightness);
	}

	private static Color[] bodyTint(Style style)
	{
		return new Color[]{characterShade(style, 0.34f), characterShade(style, 0.17f)};
	}

	public static void drawChrome(Graphics2D graphics, Card card, int x, int y, int width,
								  int height, boolean shiny, boolean gilded)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.translate(x, y);
		if (NpcCardArt.forCard(card) == null)
		{
			drawRarityPip(g, card.getRarity(), width, height, shiny, gilded, true,
				System.currentTimeMillis());
		}
		else
		{
			java.awt.Rectangle b = artBounds(width, height);
			drawBadges(g, card, width, height, b.y, b.y + b.height);
		}
		if (NpcCardArt.forCard(card) == null)
		{
			drawSetBadge(g, card, height);
		}
		g.dispose();
	}


	private static final int PRISM_STOPS = 5;

	public static void drawArtScene(Graphics2D graphics, Card card, java.awt.Rectangle box)
	{
		drawArtScene(graphics, card, box, 0d, 0d);
	}

	public static void drawArtScene(Graphics2D graphics, Card card, java.awt.Rectangle box,
									double px, double py)
	{
		NpcCardArt art = NpcCardArt.forCard(card);

		if (box == null || box.width < 8 || box.height < 8 || art == null)
		{
			return;
		}
		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int radius = Math.max(2, box.height / 8);
		g.setClip(new RoundRectangle2D.Float(box.x, box.y, box.width, box.height, radius, radius));

		Color accent = new Color(art.getAccentColour());
		g.setColor(characterShade(styleFor(card), 0.06f));
		g.fillRect(box.x, box.y, box.width, box.height);

		int floorY = box.y + (int) (box.height * FLOOR_AT);

		float haloR = Math.max(4f, Math.min(box.width, box.height) * 0.46f);
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(box.x + box.width / 2f, box.y + box.height * 0.46f), haloR,
			new float[]{0f, 0.40f, 0.72f, 1f},
			new Color[]{withAlpha(accent, 86), withAlpha(accent, 42), withAlpha(accent, 12),
				withAlpha(accent, 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(box.x, box.y, box.width, box.height);

		int poolW = (int) (box.width * 0.86d);
		int poolH = (int) (box.height * 0.30d);
		g.setPaint(new RadialGradientPaint(
			new java.awt.geom.Rectangle2D.Float(box.x + (box.width - poolW) / 2f,
				floorY - poolH * 0.42f, poolW, poolH),
			new float[]{0f, 0.55f, 1f},
			new Color[]{withAlpha(accent, 74), withAlpha(accent, 26), withAlpha(accent, 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(box.x, floorY - poolH / 2, box.width, box.y + box.height - floorY + poolH / 2);

		g.setStroke(new BasicStroke(1f));
		g.setPaint(new LinearGradientPaint(
			new Point2D.Float(box.x, floorY), new Point2D.Float(box.x + box.width, floorY),
			new float[]{0f, 0.5f, 1f},
			new Color[]{withAlpha(accent, 0), withAlpha(accent, 110), withAlpha(accent, 0)}));
		g.drawLine(box.x, floorY, box.x + box.width, floorY);

		drawArtVignette(g, box);
		g.dispose();
	}

	public static void drawArtVignette(Graphics2D graphics, java.awt.Rectangle box)
	{
		if (box == null || box.width <= 0 || box.height <= 0)
		{
			return;
		}
		Graphics2D g = (Graphics2D) graphics.create();
		int radius = Math.max(2, box.height / 8);
		g.setClip(new RoundRectangle2D.Float(box.x, box.y, box.width, box.height, radius, radius));
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(box.x + box.width / 2f, box.y + box.height * 0.42f),
			Math.max(box.width, box.height) * 0.72f,
			new float[]{0.42f, 1f},
			new Color[]{new Color(0, 0, 0, 0), new Color(0, 0, 0, 185)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillRect(box.x, box.y, box.width, box.height);
		g.setPaint(new GradientPaint(box.x, box.y, new Color(0, 0, 0, 90),
			box.x, box.y + box.height * 0.18f, new Color(0, 0, 0, 0)));
		g.fillRect(box.x, box.y, box.width, (int) (box.height * 0.18f));
		g.dispose();
	}

	private static Color spectrumAt(Card card, float t)
	{
		NpcCardArt art = NpcCardArt.forCard(card);
		if (art == null)
		{
			return Color.getHSBColor(t % 1f, 0.5f, 1f);
		}
		Color glow = new Color(art.getGlowColour());
		float[] hsb = Color.RGBtoHSB(glow.getRed(), glow.getGreen(), glow.getBlue(), null);
		float hue = (hsb[0] + (float) Math.sin(t * Math.PI * 2d) * 0.09f + 1f) % 1f;
		return Color.getHSBColor(hue, 0.62f, 1f);
	}

	public static void drawFoilOver(Graphics2D graphics, Card card, Rarity rarity,
									java.awt.Rectangle box, long animMs, float intensity)
	{
		if (rarity == null || box == null || box.width <= 0 || box.height <= 0
			|| intensity <= 0f || rarity.ordinal() < Rarity.RARE.ordinal())
		{
			return;
		}
		Graphics2D g = (Graphics2D) graphics.create();
		if (intensity < 1f)
		{
			Composite existing = g.getComposite();
			float base = existing instanceof AlphaComposite
				? ((AlphaComposite) existing).getAlpha() : 1f;
			g.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, Math.max(0f, Math.min(1f, base * intensity))));
		}

		int radius = Math.max(2, box.height / 8);
		g.setClip(new RoundRectangle2D.Float(box.x, box.y, box.width, box.height, radius, radius));

		float phase = animMs == 0 ? 0.35f : (animMs % 5200L) / 5200f;
		float sweep = phase * box.width * 2f - box.width * 0.6f;
		float hueDrift = animMs == 0 ? 0f : (animMs % 9000L) / 9000f;
		Color[] band = new Color[PRISM_STOPS + 1];
		float[] fractions = new float[PRISM_STOPS + 1];
		for (int i = 0; i <= PRISM_STOPS; i++)
		{
			fractions[i] = i / (float) PRISM_STOPS;
			Color hue = spectrumAt(card, hueDrift + fractions[i] * 0.6f);
			double falloff = Math.sin(Math.PI * fractions[i]);
			band[i] = withAlpha(hue, (int) Math.round(96 * falloff));
		}
		LinearGradientPaint prism = new LinearGradientPaint(
			new Point2D.Float(box.x + sweep, box.y),
			new Point2D.Float(box.x + sweep + box.width * 0.9f, box.y + box.height),
			fractions, band);

		BufferedImage scratch = foilScratch(box.width, box.height);
		Graphics2D lg = scratch.createGraphics();
		lg.setComposite(AlphaComposite.Clear);
		lg.fillRect(0, 0, box.width, box.height);
		lg.setComposite(AlphaComposite.SrcOver);
		lg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		lg.translate(-box.x, -box.y);
		lg.setPaint(prism);
		lg.fillRect(box.x, box.y, box.width, box.height);
		lg.translate(box.x, box.y);

		lg.setComposite(AlphaComposite.DstIn);
		lg.setPaint(new RadialGradientPaint(
			new Point2D.Float(box.width * 0.5f, box.height * 0.52f),
			Math.max(box.width, box.height) * 0.58f,
			new float[]{0.12f, 0.78f},
			new Color[]{new Color(0, 0, 0, 0), new Color(0, 0, 0, 255)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		lg.fillRect(0, 0, box.width, box.height);
		lg.dispose();

		g.drawImage(scratch, box.x, box.y, box.x + box.width, box.y + box.height,
			0, 0, box.width, box.height, null);
		g.dispose();
	}

	private static BufferedImage foilScratch;

	private static BufferedImage foilScratch(int width, int height)
	{
		if (foilScratch == null || foilScratch.getWidth() < width
			|| foilScratch.getHeight() < height)
		{
			foilScratch = new BufferedImage(Math.max(1, width), Math.max(1, height),
				BufferedImage.TYPE_INT_ARGB);
		}
		return foilScratch;
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
