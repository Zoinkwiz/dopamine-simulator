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

import com.dopaminesimulator.incremental.BigNumbers;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class PointsHeader extends JComponent
{
	private static final int HEIGHT = 54;

	private final double points;
	private final double perHour;
	private final boolean surging;

	public PointsHeader(double points, double perHour, boolean surging)
	{
		this.points = points;
		this.perHour = perHour;
		this.surging = surging;

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
		int height = getHeight();

		g.setPaint(new GradientPaint(0, 0, Skin.CARD, 0, height, Skin.CARD_DEEP));
		g.fillRoundRect(0, 0, width, height, 6, 6);
		Skin.edge(g, 0, 0, width, height, 1d, surging ? Skin.YELLOW : Skin.GOLD_DEEP);

		String value = BigNumbers.format(points);
		g.setFont(Skin.heading().deriveFont(Font.BOLD, fontSizeFor(value)));
		Skin.centred(g, value, 0, width, height - 21, surging ? Skin.YELLOW : Skin.GOLD);

		g.setFont(Skin.small());
		Skin.centred(g, BigNumbers.format(perHour) + " per hour", 0, width, height - 8,
			surging ? Skin.YELLOW : Skin.MUTED);

		g.dispose();
	}

	private static float fontSizeFor(String value)
	{
		if (value.length() <= 6)
		{
			return 26f;
		}
		return value.length() <= 8 ? 22f : 18f;
	}
}
