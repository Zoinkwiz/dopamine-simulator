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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.NPCComposition;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetModelType;
import net.runelite.api.widgets.WidgetType;

public class ModelLab
{
	private static final Map<String, int[]> PRESETS = new LinkedHashMap<>();

	static
	{
		PRESETS.put("verzik", new int[]{8250, 8051});
		PRESETS.put("vanescula", new int[]{9574, 8701});
		PRESETS.put("seren", new int[]{8775, 8372});
		PRESETS.put("konar", new int[]{8623, 8219});
		PRESETS.put("amascut", new int[]{11696, 808});
		PRESETS.put("ilfeen", new int[]{8676, 808});
		PRESETS.put("maisa", new int[]{3876, 808});
		PRESETS.put("zilyana", new int[]{2205, 6966});
		PRESETS.put("nieve", new int[]{6797, 813});
		PRESETS.put("guildmaster", new int[]{8586, 808});
		PRESETS.put("jane", new int[]{12951, 1796});
		PRESETS.put("dusk", new int[]{15621, 7797});
		PRESETS.put("spider", new int[]{8374, 8120});
		PRESETS.put("spiderstatic", new int[]{8374, -1});
		PRESETS.put("flight", new int[]{8371, 8112});
	}

	private final Client client;

	private Widget container;
	private Widget background;
	private final List<Widget> parts = new ArrayList<>();

	private int npcId = -1;
	private int animId = -1;
	private int zoom = 3000;
	private int tilt = 66;
	private int spinStep = 2;
	private int vy = 0;
	private int boxSize = 160;
	private int isolatePart = -1;
	private int spin;

	public ModelLab(Client client)
	{
		this.client = client;
	}

	public boolean isOpen()
	{
		return !parts.isEmpty();
	}

	public void command(String[] args)
	{
		if (args != null && args.length > 0 && "off".equalsIgnoreCase(args[0]))
		{
			close();
			chat("[modellab] closed.");
			return;
		}

		boolean reopen = false;
		if (args != null)
		{
			for (String arg : args)
			{
				int[] preset = PRESETS.get(arg.toLowerCase());
				if (preset != null)
				{
					npcId = preset[0];
					animId = preset[1];
					reopen = true;
					continue;
				}

				int eq = arg.indexOf('=');
				if (eq <= 0)
				{
					continue;
				}
				String key = arg.substring(0, eq).trim().toLowerCase();
				int value;
				try
				{
					value = Integer.parseInt(arg.substring(eq + 1).trim());
				}
				catch (NumberFormatException ex)
				{
					continue;
				}

				switch (key)
				{
					case "npc": npcId = value; reopen = true; break;
					case "anim": animId = value; break;
					case "zoom": zoom = value; break;
					case "tilt": tilt = value; break;
					case "spin": spinStep = value; break;
					case "vy": vy = value; break;
					case "size": boxSize = value; break;
					case "part": isolatePart = value; break;
					default: break;
				}
			}
		}

		if (npcId <= 0)
		{
			chat("[modellab] presets: " + String.join(", ", PRESETS.keySet()));
			chat("[modellab] or: ::modellab npc=<id> anim=<id>");
			return;
		}

		if (reopen || parts.isEmpty())
		{
			open();
		}
		else
		{
			applyFraming();
		}

		chat("[modellab] npc=" + npcId + " anim=" + animId + " zoom=" + zoom + " tilt=" + tilt
			+ " spin=" + spinStep + " vy=" + vy + " size=" + boxSize
			+ " part=" + (isolatePart < 0 ? "all" : isolatePart) + " (" + parts.size() + " part widgets)");
	}

	public void tick()
	{
		if (parts.isEmpty())
		{
			return;
		}
		spin -= spinStep;
		int rotZ = spin & 0x7FF;
		for (Widget part : parts)
		{
			part.setRotationZ(rotZ);
			part.revalidate();
		}
	}

	public void close()
	{
		parts.clear();
		background = null;
		spin = 0;
		if (container != null)
		{
			container.deleteAllChildren();
			container.setHidden(true);
			container.revalidate();
		}
	}

	private void open()
	{
		close();

		Widget parent = topLevel();
		if (parent == null)
		{
			chat("[modellab] no top-level widget - log in first.");
			return;
		}

		NPCComposition npc = client.getNpcDefinition(npcId);
		if (npc == null)
		{
			chat("[modellab] npc " + npcId + " not in cache.");
			return;
		}
		int[] models = npc.getModels();
		if (models == null || models.length == 0)
		{
			chat("[modellab] npc " + npcId + " \"" + npc.getName() + "\" has no body models.");
			return;
		}

		container = parent.createChild(-1, WidgetType.LAYER);
		container.setOriginalX(0);
		container.setOriginalY(0);
		container.setOriginalWidth(parent.getWidth());
		container.setOriginalHeight(parent.getHeight());
		container.setHidden(false);
		container.revalidate();

		background = container.createChild(-1, WidgetType.RECTANGLE);
		background.setTextColor(0x0e0e0c);
		background.setFilled(true);
		background.setOpacity(60);

		for (int i = 0; i < models.length; i++)
		{
			if (isolatePart >= 0 && i != isolatePart)
			{
				continue;
			}
			Widget part = container.createChild(-1, WidgetType.MODEL);
			part.setModelType(WidgetModelType.MODEL);
			part.setModelId(models[i]);
			part.setRotationY(0);
			parts.add(part);
		}

		applyFraming();

		StringBuilder ids = new StringBuilder();
		for (int i = 0; i < models.length; i++)
		{
			if (i > 0)
			{
				ids.append(',');
			}
			ids.append(models[i]);
		}

		chat("[modellab] " + npc.getName() + " (npc " + npcId + "): " + models.length
			+ " part(s) [" + ids + "], anim " + animId);
	}

	private void applyFraming()
	{
		Widget parent = topLevel();
		if (parent == null)
		{
			return;
		}
		int x = Math.max(0, (parent.getWidth() - boxSize) / 2);
		int y = Math.max(0, (parent.getHeight() - boxSize) / 2);

		if (background != null)
		{
			background.setOriginalX(x);
			background.setOriginalY(y);
			background.setOriginalWidth(boxSize);
			background.setOriginalHeight(boxSize);
			background.revalidate();
		}

		for (Widget part : parts)
		{
			part.setOriginalX(x);
			part.setOriginalY(y + vy);
			part.setOriginalWidth(boxSize);
			part.setOriginalHeight(boxSize);
			part.setModelZoom(zoom);
			part.setRotationX(tilt);
			part.setAnimationId(animId);
			part.revalidate();
		}
	}

	private Widget topLevel()
	{
		Widget classic = client.getWidget(InterfaceID.ToplevelOsrsStretch.HUD_CONTAINER_FRONT);
		if (classic != null && !classic.isHidden())
		{
			return classic;
		}
		Widget modern = client.getWidget(InterfaceID.ToplevelPreEoc.HUD_CONTAINER_FRONT);
		if (modern != null && !modern.isHidden())
		{
			return modern;
		}
		return client.getWidget(InterfaceID.Toplevel.MAIN);
	}

	private void chat(String message)
	{
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message, null);
	}
}
