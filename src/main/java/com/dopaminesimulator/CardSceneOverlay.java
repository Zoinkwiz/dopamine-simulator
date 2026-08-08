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
import com.dopaminesimulator.ui.CardRenderer;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class CardSceneOverlay extends Overlay
{
	private static final long STALE_MS = 120L;

	private Card card;
	private java.awt.Rectangle box;
	private AffineTransform transform;
	private double px;
	private double py;
	private long submittedAt;

	CardSceneOverlay()
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.UNDER_WIDGETS);
	}

	public void submit(Card card, java.awt.Rectangle box, AffineTransform transform,
					   double px, double py)
	{
		this.px = px;
		this.py = py;
		this.card = card;
		this.box = box;
		this.transform = transform;
		this.submittedAt = System.currentTimeMillis();
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (card == null || box == null
			|| System.currentTimeMillis() - submittedAt > STALE_MS)
		{
			return null;
		}

		AffineTransform transformBefore = graphics.getTransform();
		Shape clipBefore = graphics.getClip();
		if (transform != null)
		{
			graphics.transform(transform);
		}
		CardRenderer.drawArtScene(graphics, card, box, px, py);
		graphics.setTransform(transformBefore);
		graphics.setClip(clipBefore);
		return null;
	}
}
