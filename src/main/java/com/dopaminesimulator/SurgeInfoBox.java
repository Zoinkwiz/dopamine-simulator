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

import com.dopaminesimulator.points.ClickState;
import com.dopaminesimulator.points.GnomeFood;
import java.awt.Color;
import java.awt.image.BufferedImage;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBox;

public class SurgeInfoBox extends InfoBox
{
	private static final Color SURGE = new Color(0xFF, 0xE0, 0x82);
	private final DopamineSimulatorConfig config;
	private final ClickState clickState;
	SurgeInfoBox(BufferedImage image, Plugin plugin, DopamineSimulatorConfig config,
				 ClickState clickState)
	{
		super(image, plugin);
		this.config = config;
		this.clickState = clickState;
		setTooltip("A gnome dish is being served");
	}
	@Override
	public String getText()
	{
		long now = System.currentTimeMillis();
		GnomeFood food = clickState.getActive(now);
		if (food != null)
		{
			setTooltip(food.getDisplayName() + ": " + food.getBlurb());
		}
		return String.format("%.0f", clickState.secondsRemaining(now));
	}

	@Override
	public Color getTextColor()
	{
		return SURGE;
	}

	@Override
	public boolean render()
	{
		return config.showPackInfobox() && clickState.isSurging(System.currentTimeMillis());
	}
}
