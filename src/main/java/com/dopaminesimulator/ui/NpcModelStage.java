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

import com.dopaminesimulator.cards.NpcCardArt;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import net.runelite.api.Client;
import net.runelite.api.NPCComposition;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetModelType;
import net.runelite.api.widgets.WidgetType;

public class NpcModelStage
{
	private static final long TURN_MS = 1500L;

	private static final int SWING = 240;
	private static final double SWING_PERIOD_MS = 5200d;

	private static final long REAPPEAR_GAP_MS = 250L;

	private static final int TILT_X = 66;

	private final Client client;

	private Widget container;
	private Widget scenery;

	private final List<Widget> parts = new ArrayList<>();
	private int builtFor = -1;

	private long shownSinceMs;

	private long lastShowMs;

	private int zoomOverride;
	private int offsetX;
	private int offsetY;

	private int sceneryOverride;
	private int sceneryZoomOverride;

	public void tune(int zoomOverride, int offsetX, int offsetY, int sceneryOverride,
					 int sceneryZoomOverride)
	{
		if (this.sceneryOverride != sceneryOverride)
		{
			builtFor = -1;
		}
		this.zoomOverride = zoomOverride;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.sceneryOverride = sceneryOverride;
		this.sceneryZoomOverride = sceneryZoomOverride;
	}

	public void restartTurn()
	{
		shownSinceMs = System.currentTimeMillis();
	}

	public String tuning()
	{
		return "zoom=" + (zoomOverride == 0 ? "card" : zoomOverride)
			+ " dx=" + offsetX + " dy=" + offsetY
			+ " scenery=" + (sceneryOverride == 0 ? "card" : sceneryOverride)
			+ " szoom=" + (sceneryZoomOverride == 0 ? "card" : sceneryZoomOverride);
	}

	private int sceneryModelFor(NpcCardArt art)
	{
		return sceneryOverride == 0 ? art.getSceneryModelId() : sceneryOverride;
	}

	public NpcModelStage(Client client)
	{
		this.client = client;
	}

	public void showAt(NpcCardArt art, Rectangle box)
	{
		if (art == null || box == null || box.width <= 0 || box.height <= 0)
		{
			hide();
			return;
		}

		Widget parent = topLevel();
		if (parent == null)
		{
			hide();
			return;
		}

		if (builtFor != art.getNpcId() || container == null || parts.isEmpty())
		{
			build(parent, art);
			if (parts.isEmpty())
			{
				return;
			}
		}

		long now = System.currentTimeMillis();

		boolean fresh = lastShowMs == 0L || now - lastShowMs > REAPPEAR_GAP_MS;
		if (fresh)
		{
			shownSinceMs = now;
		}
		lastShowMs = now;

		long elapsed = now - shownSinceMs;
		double rotation;
		if (elapsed < TURN_MS)
		{
			double t = elapsed / (double) TURN_MS;
			rotation = 2048d * (1d - Math.pow(1d - t, 3d));
		}
		else
		{
			rotation = SWING * Math.sin((elapsed - TURN_MS) * 2d * Math.PI / SWING_PERIOD_MS);
		}
		int rotationZ = (((int) Math.round(rotation)) % 2048 + 2048) % 2048;

		net.runelite.api.Point origin = parent.getCanvasLocation();
		int x = box.x - (origin == null ? 0 : origin.getX());
		int y = box.y - (origin == null ? 0 : origin.getY());
		int size = Math.min(box.width, box.height);

		container.setOriginalX(x);
		container.setOriginalY(y);
		container.setOriginalWidth(box.width);
		container.setOriginalHeight(box.height);
		container.revalidate();

		int modelX = (box.width - size) / 2 + offsetX;
		int modelY = (box.height - size) / 2 + offsetY;

		if (scenery != null)
		{
			scenery.setHidden(false);
			scenery.setOriginalX(modelX);
			scenery.setOriginalY(modelY);
			scenery.setOriginalWidth(size);
			scenery.setOriginalHeight(size);
			int sceneryZoom = sceneryZoomOverride != 0 ? sceneryZoomOverride
				: art.getSceneryZoom() > 0 ? art.getSceneryZoom() : art.getZoom();
			scenery.setModelZoom(sceneryZoom);
			scenery.setRotationX(TILT_X);
			scenery.setRotationZ(rotationZ);
			scenery.revalidate();
		}

		for (Widget part : parts)
		{
			part.setHidden(false);
			part.setOriginalX(modelX);

			part.setOriginalY(modelY + box.height * art.getVerticalOffset() / 100);
			part.setOriginalWidth(size);
			part.setOriginalHeight(size);
			part.setModelZoom(zoomOverride == 0 ? art.getZoom() : zoomOverride);
			part.setRotationX(TILT_X);
			part.setRotationZ(rotationZ);
			part.setAnimationId(art.getAnimationId());
			part.revalidate();
		}
	}

	public void hide()
	{
			hide(scenery);
		for (Widget part : parts)
		{
			hide(part);
		}
	}

	private static void hide(Widget widget)
	{
		if (widget != null && !widget.isHidden())
		{
			widget.setHidden(true);
			widget.revalidate();
		}
	}

	public void dispose()
	{
		parts.clear();
		scenery = null;
		builtFor = -1;
		if (container != null)
		{
			container.deleteAllChildren();
			container.setHidden(true);
			container.revalidate();
			container = null;
		}
	}

	private void build(Widget parent, NpcCardArt art)
	{
		dispose();

		NPCComposition npc = client.getNpcDefinition(art.getNpcId());
		if (npc == null)
		{
			return;
		}
		int[] models = npc.getModels();
		if (models == null || models.length == 0)
		{
			return;
		}

		container = parent.createChild(-1, WidgetType.LAYER);
		container.setHidden(false);

		int sceneryModel = sceneryModelFor(art);
		if (sceneryModel > 0)
		{
			scenery = container.createChild(-1, WidgetType.MODEL);
			scenery.setModelType(WidgetModelType.MODEL);
			scenery.setModelId(sceneryModel);
			scenery.setRotationY(0);
		}

		for (int model : models)
		{
			Widget part = container.createChild(-1, WidgetType.MODEL);
			part.setModelType(WidgetModelType.MODEL);
			part.setModelId(model);
			part.setRotationY(0);
			parts.add(part);
		}
		builtFor = art.getNpcId();
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
}
