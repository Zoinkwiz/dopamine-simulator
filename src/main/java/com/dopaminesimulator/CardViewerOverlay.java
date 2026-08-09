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
package com.dopaminesimulator;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.NpcCardArt;
import com.dopaminesimulator.ui.CardArtService;
import com.dopaminesimulator.ui.CardRenderer;
import com.dopaminesimulator.ui.NpcModelStage;
import com.dopaminesimulator.ui.WishReveal;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import net.runelite.api.Client;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class CardViewerOverlay extends Overlay
{
	private static final int CARD_WIDTH = 260;
	private static final int CARD_HEIGHT = 364;
	private static final long OPEN_MS = 220L;
	private static final Color DIM = new Color(0, 0, 0, 120);
	private static final Color HINT = new Color(0xC8, 0xCC, 0xD6);

	private final Client client;
	private final CardArtService artService;
	private final NpcModelStage modelStage;
	private final CardSceneOverlay scene;

	/** Rotation about the card's vertical and horizontal axes, radians. */
	private static final double MAX_YAW = 0.20d;
	private static final double MAX_PITCH = 0.13d;

	private java.awt.image.BufferedImage face;
	/** Set by ::cardface; writes the next rendered face to a PNG so it can be inspected. */
	private static boolean dumpFace;

	public static void dumpNextFace()
	{
		dumpFace = true;
	}
	private static final long PULSE_MS = 420L;

	private Card card;
	private int stars;
	private boolean shiny;
	private boolean gilded;
	private long openedAt;
	private long pulsedAt;
	private double pointerX = 0.5d;
	private double pointerY = 0.5d;

	public void click()
	{
		pulsedAt = System.currentTimeMillis();
		if (!modelStage.toggleForm(NpcCardArt.forCard(card)))
		{
			modelStage.restartTurn();
		}
	}

	public boolean containsCanvas(int x, int y)
	{
		if (card == null)
		{
			return false;
		}
		int cx = client.getCanvasWidth() / 2;
		int cy = client.getCanvasHeight() / 2;
		int halfW = (int) (CARD_WIDTH * 0.62d);
		int halfH = (int) (CARD_HEIGHT * 0.62d);
		return Math.abs(x - cx) <= halfW && Math.abs(y - cy) <= halfH;
	}

	CardViewerOverlay(Client client, CardArtService artService, CardSceneOverlay scene)
	{
		this.client = client;
		this.artService = artService;
		this.scene = scene;
		this.modelStage = new NpcModelStage(client);
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
	}

	public void dispose()
	{
		card = null;
		modelStage.dispose();
	}

	public void show(Card card, int stars, boolean shiny, boolean gilded)
	{
		this.card = card;
		this.stars = stars;
		this.shiny = shiny;
		this.gilded = gilded;
		this.openedAt = System.currentTimeMillis();
	}

	public boolean isOpen()
	{
		return card != null;
	}

	public void close()
	{
		card = null;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		Card showing = card;
		if (showing == null)
		{
			modelStage.hide();
			return null;
		}

		java.awt.Font fontBefore = graphics.getFont();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Composite compositeBefore = graphics.getComposite();
		AffineTransform transformBefore = graphics.getTransform();
		java.awt.Shape clipBefore = graphics.getClip();

		int canvasWidth = client.getCanvasWidth();
		int canvasHeight = client.getCanvasHeight();
		int cx = canvasWidth / 2;
		int cy = canvasHeight / 2;

		long now = System.currentTimeMillis();
		long age = now - openedAt;
		double open = Math.min(1d, age / (double) OPEN_MS);
		float alpha = (float) open;
		double scale = 0.86d + 0.14d * (1d - Math.pow(1d - open, 3d));

		if (pulsedAt != 0L)
		{
			double pulse = (now - pulsedAt) / (double) PULSE_MS;
			if (pulse >= 1d)
			{
				pulsedAt = 0L;
			}
			else
			{
				scale *= 1d + 0.09d * Math.sin(Math.PI * pulse) * (1d - pulse);
			}
		}

		trackPointer(cx, cy, scale);

		NpcCardArt npcArt = NpcCardArt.forCard(showing);

		CardRenderer.Turn turn = new CardRenderer.Turn(cx, cy, scale,
			(pointerX - 0.5d) * 2d * MAX_YAW, (pointerY - 0.5d) * 2d * MAX_PITCH,
			CARD_WIDTH, CARD_HEIGHT);

		Rectangle artLocal = CardRenderer.artBounds(CARD_WIDTH, CARD_HEIGHT);
		java.awt.Shape artRounded = CardRenderer.artShape(CARD_WIDTH, CARD_HEIGHT);
		Rectangle modelBox = npcArt == null ? null
			: turn.outline(artRounded).getBounds();

		java.awt.Shape artShape = null;
		if (npcArt != null)
		{
			artShape = turn.outline(artRounded);

			java.awt.geom.Area undimmed = new java.awt.geom.Area(clipBefore != null
				? clipBefore : new Rectangle(0, 0, canvasWidth, canvasHeight));
			undimmed.subtract(new java.awt.geom.Area(artShape));
			graphics.setClip(undimmed);
		}
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		graphics.setColor(DIM);
		graphics.fillRect(0, 0, canvasWidth, canvasHeight);

		graphics.setClip(clipBefore);
		CardRenderer.drawTurned(graphics, renderFace(showing, now, npcArt != null), turn);

		if (modelBox != null)
		{
			modelStage.setPointer((pointerX - 0.5d) * 2d, (pointerY - 0.5d) * 2d);
			modelStage.showAt(npcArt, modelBox);
			scene.submit(showing, artLocal, turn, CARD_WIDTH, CARD_HEIGHT,
				(pointerX - 0.5d) * 2d, (pointerY - 0.5d) * 2d);
			CardRenderer.drawFoilOver(graphics, showing, showing.getRarity(), modelBox, now,
				WishReveal.DEFAULT_MODEL_FOIL);
		}



		graphics.setFont(FontManager.getRunescapeSmallFont());
		graphics.setColor(HINT);
		String hint = "Click the card for a closer look  -  click away to close";
		int hintWidth = graphics.getFontMetrics().stringWidth(hint);
		graphics.drawString(hint, cx - hintWidth / 2,
			cy + (int) (CARD_HEIGHT * scale / 2) + 22);

		graphics.setComposite(compositeBefore);
		graphics.setFont(fontBefore);
		return null;
	}

	private java.awt.image.BufferedImage renderFace(Card showing, long now, boolean modelArt)
	{
		int ss = CardRenderer.SUPERSAMPLE;
		if (face == null)
		{
			face = new java.awt.image.BufferedImage(CARD_WIDTH * ss, CARD_HEIGHT * ss,
				java.awt.image.BufferedImage.TYPE_INT_ARGB);
		}
		Graphics2D g = face.createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, face.getWidth(), face.getHeight());
		g.setComposite(AlphaComposite.SrcOver);
		g.scale(ss, ss);
		g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
			java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
			java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		CardRenderer.draw(g, showing, 0, 0, CARD_WIDTH, CARD_HEIGHT, stars, true, now,
			artService.get(showing), shiny, gilded, modelArt);
		if (modelArt)
		{
			CardRenderer.drawChrome(g, showing, 0, 0, CARD_WIDTH, CARD_HEIGHT, shiny, gilded);
		}
		CardRenderer.drawSpecular(g, showing, 0, 0, CARD_WIDTH, CARD_HEIGHT, pointerX, pointerY,
			modelArt, now);
		if (modelArt)
		{
			g.setComposite(AlphaComposite.Clear);
			g.fill(CardRenderer.artShape(CARD_WIDTH, CARD_HEIGHT));
			g.setComposite(AlphaComposite.SrcOver);
		}
		g.dispose();
		if (dumpFace)
		{
			dumpFace = false;
			try
			{
				java.io.File out = new java.io.File(System.getProperty("java.io.tmpdir"),
					"cardface.png");
				javax.imageio.ImageIO.write(face, "png", out);
			}
			catch (java.io.IOException ignored)
			{
			}
		}
		return face;
	}


	private void trackPointer(int cx, int cy, double scale)
	{
		net.runelite.api.Point mouse = client.getMouseCanvasPosition();
		double targetX = 0.5d;
		double targetY = 0.5d;
		if (mouse != null && containsCanvas(mouse.getX(), mouse.getY()))
		{
			targetX = clamp01((mouse.getX() - (cx - CARD_WIDTH * scale / 2d))
				/ (CARD_WIDTH * scale));
			targetY = clamp01((mouse.getY() - (cy - CARD_HEIGHT * scale / 2d))
				/ (CARD_HEIGHT * scale));
		}
		pointerX += (targetX - pointerX) * 0.22d;
		pointerY += (targetY - pointerY) * 0.22d;
	}

	private static double clamp01(double value)
	{
		return value < 0d ? 0d : Math.min(value, 1d);
	}

	}
