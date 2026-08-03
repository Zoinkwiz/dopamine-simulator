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
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.function.IntConsumer;
import javax.swing.JComponent;

public class Segmented extends JComponent
{
	public static final int HEIGHT = 21;

	private final String[] labels;
	private final int selected;

	public Segmented(String[] labels, int selected, IntConsumer onPick)
	{
		this.labels = labels;
		this.selected = selected;

		setPreferredSize(new Dimension(0, HEIGHT));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, HEIGHT));
		setMinimumSize(new Dimension(0, HEIGHT));
		setOpaque(false);

		if (onPick != null)
		{
			addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent e)
				{
					int index = e.getX() * labels.length / Math.max(1, getWidth());
					onPick.accept(Math.max(0, Math.min(labels.length - 1, index)));
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
		Shape pill = new RoundRectangle2D.Float(0, 0, width, HEIGHT, 6, 6);
		g.setColor(Skin.CARD_DEEP);
		g.fill(pill);

		Shape clip = g.getClip();
		g.clip(pill);
		g.setFont(Skin.body());
		FontMetrics metrics = g.getFontMetrics();
		int baseline = (HEIGHT + metrics.getAscent()) / 2 - 2;

		for (int i = 0; i < labels.length; i++)
		{
			int x = width * i / labels.length;
			int w = width * (i + 1) / labels.length - x;

			if (i == selected)
			{

				g.setColor(Skin.GOLD);
				g.fillRect(x, 0, w, HEIGHT);

				String label = labels[i];
				int labelX = x + (w - metrics.stringWidth(label)) / 2;
				g.setColor(Skin.withAlpha(Color.WHITE, 90));
				g.drawString(label, labelX, baseline + 1);
				g.setColor(new Color(0x2A, 0x1C, 0x00));
				g.drawString(label, labelX, baseline);
				continue;
			}

			if (i > 0)
			{
				g.setStroke(new BasicStroke(1f));
				g.setColor(Skin.BG);
				g.drawLine(x, 4, x, HEIGHT - 5);
			}
			Skin.centred(g, labels[i], x, w, baseline, Skin.MUTED);
		}
		g.setClip(clip);

		g.setStroke(new BasicStroke(1f));
		g.setColor(Skin.LINE);
		g.draw(new RoundRectangle2D.Float(0, 0, width - 1, HEIGHT - 1, 6, 6));

		g.dispose();
	}
}
