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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.border.Border;

public class StoneButton extends JButton
{
	private Color accent = Skin.GOLD;

	public StoneButton(String text)
	{
		super(text);
		setFont(Skin.body());
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(false);
		setRolloverEnabled(true);
		setForeground(Skin.GOLD);
	}

	public StoneButton withAccent(Color colour)
	{
		this.accent = Skin.vivid(colour);
		setForeground(this.accent);
		return this;
	}

	@Override
	public void setBorder(Border border)
	{

	}

	@Override
	public Insets getInsets()
	{
		return new Insets(4, 10, 4, 10);
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		Skin.smooth(g);

		int w = getWidth();
		int h = getHeight();
		boolean held = getModel().isArmed() && getModel().isPressed();
		boolean hovered = getModel().isRollover() && isEnabled();

		if (!isEnabled())
		{
			g.setColor(Skin.CARD_DEEP);
			g.fillRoundRect(0, 0, w, h, 6, 6);
		}
		else
		{
			g.setPaint(held
				? new GradientPaint(0, 0, Skin.CARD_DEEP, 0, h, Skin.CARD)
				: new GradientPaint(0, 0, hovered ? Skin.CARD_HOVER : Skin.CARD, 0, h,
					Skin.CARD_DEEP));
			g.fillRoundRect(0, 0, w, h, 6, 6);

			g.setStroke(new BasicStroke(1f));
			g.setColor(hovered ? Skin.GOLD : Skin.withAlpha(accent, 130));
			g.drawRoundRect(0, 0, w - 1, h - 1, 6, 6);
		}
		g.dispose();

		Color wanted = isEnabled() ? accent : Skin.FADED;
		if (!wanted.equals(getForeground()))
		{
			setForeground(wanted);
		}
		super.paintComponent(graphics);
	}
}
