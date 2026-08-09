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

import com.dopaminesimulator.cards.CardCatalogue;
import com.dopaminesimulator.cards.CharacterDeed;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.JComponent;
import net.runelite.client.ui.FontManager;

public class CharacterPackRow extends JComponent
{
	public static final int HEIGHT = 54;

	private static final int PACK_W = 30;
	private static final int PACK_H = 40;
	private static final int PACK_X = 8;

	private final CharacterDeed deed;
	private final int held;
	private final int packsToPity;
	private final boolean owned;
	private final CardRenderer.Style style;

	private boolean hovered;

	public CharacterPackRow(CharacterDeed deed, int held, int packsToPity, boolean owned,
							Consumer<CharacterDeed> onOpen)
	{
		this.deed = deed;
		this.held = held;
		this.packsToPity = packsToPity;
		this.owned = owned;
		this.style = CardRenderer.styleFor(CardCatalogue.byId(deed.getCardId()));
		setPreferredSize(new Dimension(0, HEIGHT));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, HEIGHT));
		setMinimumSize(new Dimension(0, HEIGHT));
		setOpaque(false);
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				hovered = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				hovered = false;
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (onOpen != null && held > 0)
				{
					onOpen.accept(deed);
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();

		g.setColor(hovered && held > 0 ? Skin.CARD_HOVER : Skin.CARD);
		g.fillRoundRect(0, 0, width, HEIGHT - 2, 6, 6);
		g.setColor(Skin.withAlpha(style.metalMid, hovered ? 190 : 110));
		g.drawRoundRect(0, 0, width - 1, HEIGHT - 3, 6, 6);

		CardRenderer.drawPackArt(g, PACK_X, (HEIGHT - 2 - PACK_H) / 2, PACK_W, PACK_H, style,
			CardRenderer.BackMotif.of(deed.getArt() == null ? null : deed.getArt().getBackMotif()),
			System.currentTimeMillis());

		int textX = PACK_X + PACK_W + 9;
		g.setFont(FontManager.getRunescapeBoldFont());
		g.setColor(Skin.vivid(style.metalLight));
		String name = deed.getCharacterName() + (held > 1 ? "  x" + held : "");
		g.drawString(name, textX, 19);

		g.setFont(FontManager.getRunescapeSmallFont());
		g.setColor(Skin.MUTED);
		FontMetrics small = g.getFontMetrics();
		g.drawString(ellipsise(small, deed.getDeed(), width - textX - 54), textX, 32);

		String pity = owned ? "collected"
			: packsToPity + " to guaranteed";
		g.setColor(Skin.withAlpha(style.glow, 190));
		g.drawString(pity, textX, 45);

		String action = held > 0 ? "OPEN" : "";
		if (!action.isEmpty())
		{
			g.setFont(FontManager.getRunescapeBoldFont());
			FontMetrics bold = g.getFontMetrics();
			int aw = bold.stringWidth(action);
			int ax = width - aw - 12;
			g.setColor(Skin.withAlpha(style.glow, hovered ? 235 : 165));
			g.drawString(action, ax, HEIGHT / 2 + 4);
		}
		g.dispose();
	}

	private static String ellipsise(FontMetrics metrics, String text, int width)
	{
		if (width <= 8 || metrics.stringWidth(text) <= width)
		{
			return text;
		}
		StringBuilder shortened = new StringBuilder(text);
		while (shortened.length() > 1 && metrics.stringWidth(shortened + "...") > width)
		{
			shortened.setLength(shortened.length() - 1);
		}
		return shortened + "...";
	}
}
