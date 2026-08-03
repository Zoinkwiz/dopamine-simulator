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
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class SectionHeader extends JComponent
{
	private static final int HEIGHT = 20;

	private final String title;
	private final String trailing;

	public SectionHeader(String title)
	{
		this(title, null);
	}

	public SectionHeader(String title, String trailing)
	{
		this.title = title;
		this.trailing = trailing;
		setPreferredSize(new Dimension(0, HEIGHT));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, HEIGHT));
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		Skin.smooth(g);

		int width = getWidth();
		int room = width - 2;

		if (trailing != null && !trailing.isEmpty())
		{
			g.setFont(Skin.small());
			FontMetrics metrics = g.getFontMetrics();
			Skin.right(g, trailing, width, HEIGHT - 8, Skin.MUTED);
			room -= metrics.stringWidth(trailing) + 8;
		}

		g.setFont(Skin.heading());
		FontMetrics metrics = g.getFontMetrics();
		String shown = Skin.elide(metrics, title, Math.max(20, room));
		Skin.text(g, shown, 0, HEIGHT - 8, Skin.ORANGE);

		int ruleX = metrics.stringWidth(shown) + 7;
		if (width - ruleX > 12)
		{
			g.setStroke(new BasicStroke(1f));
			g.setPaint(new GradientPaint(ruleX, 0, Skin.GOLD_DEEP, width, 0,
				new Color(0, 0, 0, 0)));
			g.drawLine(ruleX, HEIGHT - 12, width, HEIGHT - 12);
		}

		g.dispose();
	}
}
