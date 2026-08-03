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
package com.dopaminesimulator.cosmetics;

import java.awt.Color;
import lombok.Getter;

@Getter
public enum CardBack
{
	STANDARD("Standard", new Color(0x2B, 0x2F, 0x3A), new Color(0x55, 0x5E, 0x72), Pattern.CROSS),
	MIDNIGHT("Midnight", new Color(0x12, 0x14, 0x22), new Color(0x3D, 0x46, 0x8C), Pattern.RINGS),
	EMBER("Ember", new Color(0x2A, 0x14, 0x10), new Color(0xC1, 0x53, 0x2A), Pattern.RAYS),
	VERDANT("Verdant", new Color(0x11, 0x22, 0x16), new Color(0x4C, 0x9A, 0x5E), Pattern.LATTICE),
	BULLION("Bullion", new Color(0x24, 0x1E, 0x0E), new Color(0xD9, 0xA8, 0x33), Pattern.RINGS),
	TIDE("Tide", new Color(0x0E, 0x1F, 0x24), new Color(0x4F, 0xC3, 0xD9), Pattern.RAYS),
	VOID("Void", new Color(0x0B, 0x0B, 0x0D), new Color(0x7E, 0x57, 0xC2), Pattern.LATTICE);

	public enum Pattern
	{
		CROSS,
		RINGS,
		RAYS,
		LATTICE
	}

	private final String displayName;
	private final Color base;
	private final Color trim;
	private final Pattern pattern;

	CardBack(String displayName, Color base, Color trim, Pattern pattern)
	{
		this.displayName = displayName;
		this.base = base;
		this.trim = trim;
		this.pattern = pattern;
	}

	public static CardBack byId(String id)
	{
		for (CardBack back : values())
		{
			if (back.name().equals(id))
			{
				return back;
			}
		}
		return STANDARD;
	}
}
