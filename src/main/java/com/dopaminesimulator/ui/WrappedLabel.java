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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

public class WrappedLabel extends JComponent
{
	private final String text;
	private final Font font;
	private final Color colour;
	private final int wrapWidth;
	private List<String> lines;
	private int lineHeight;
	public WrappedLabel(String text, Font font, Color colour, int wrapWidth)
	{
		this.text = text;
		this.font = font;
		this.colour = colour;
		this.wrapWidth = wrapWidth;

		setOpaque(false);
		layoutText();
	}
	private void layoutText()
	{
		FontMetrics metrics = getFontMetrics(font);
		lineHeight = metrics.getHeight();
		lines = new ArrayList<>();
		StringBuilder current = new StringBuilder();
		for (String word : text.split(" "))
		{
			String candidate = current.length() == 0 ? word : current + " " + word;
			if (metrics.stringWidth(candidate) <= wrapWidth || current.length() == 0)
			{
				current.setLength(0);
				current.append(candidate);
			}
			else
			{
				lines.add(current.toString());
				current.setLength(0);
				current.append(word);
			}
		}

		if (current.length() > 0)
		{
			lines.add(current.toString());
		}
		Dimension size = new Dimension(wrapWidth, Math.max(lineHeight, lines.size() * lineHeight));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}
	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.setFont(font);
		g.setColor(colour);
		int y = g.getFontMetrics().getAscent();
		for (String line : lines)
		{
			g.drawString(line, 0, y);
			y += lineHeight;
		}
		g.dispose();
	}
}
