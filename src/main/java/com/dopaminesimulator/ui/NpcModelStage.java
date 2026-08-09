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
	private static final long REAPPEAR_GAP_MS = 250L;
	private static final int TILT_X = 66;
	private static final int PARALLAX_YAW = 50;
	private static final int CARD_YAW = 65;
	private static final double SPIN_PER_PIXEL = 5d;

	private int handSpin;

	public void addHandSpin(int dragPixels)
	{
		handSpin = (int) Math.round(handSpin + dragPixels * SPIN_PER_PIXEL);
	}

	public void clearHandSpin()
	{
		handSpin = 0;
	}

	private final Client client;

	private Widget container;
	private Widget scenery;
	private final List<Widget> pillars = new ArrayList<>();
	private final List<Widget> forePillars = new ArrayList<>();

	private final List<Widget> parts = new ArrayList<>();
	private int builtFor = -1;

	private long shownSinceMs;
	private long lastShowMs;

	private int zoomOverride;
	private int offsetX;
	private int offsetY;
	private int sceneryOverride;
	private int sceneryZoomOverride;
	private int pillarZoomOverride;
	private int pillarSpreadPct = 34;
	private int pillarRotationOverride = -1;
	private int pillarRotationRightOverride = -1;
	private int foreZoomOverride;
	private int foreSpreadPct = 40;
	private int foreOffsetY;
	private int layerMask = 15;
	private int sceneryOffsetY;
	private double px;
	private double py;
	private boolean altForm;

	public void tune(int zoomOverride, int offsetX, int offsetY, int sceneryOverride,
					 int sceneryZoomOverride, int pillarZoomOverride, int pillarSpreadPct,
					 int sceneryOffsetY, int pillarRotationOverride,
					 int pillarRotationRightOverride, int foreZoomOverride, int foreSpreadPct,
					 int foreOffsetY, int layerMask)
	{
		if (layerMask >= 0 && layerMask != this.layerMask)
		{
			this.layerMask = layerMask;
			builtFor = -1;
		}
		this.foreZoomOverride = foreZoomOverride;
		this.foreOffsetY = foreOffsetY;
		if (foreSpreadPct != 0)
		{
			this.foreSpreadPct = foreSpreadPct;
		}
		this.pillarRotationOverride = pillarRotationOverride;
		this.pillarRotationRightOverride = pillarRotationRightOverride;
		this.pillarZoomOverride = pillarZoomOverride;
		if (pillarSpreadPct != 0)
		{
			this.pillarSpreadPct = pillarSpreadPct;
		}
		this.sceneryOffsetY = sceneryOffsetY;
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

	public boolean toggleForm(NpcCardArt art)
	{
		if (art == null || art.getAltNpcId() <= 0)
		{
			return false;
		}
		altForm = !altForm;

		builtFor = -1;
		shownSinceMs = System.currentTimeMillis();
		return true;
	}

	private static int wrap(int rotation)
	{
		return (rotation % 2048 + 2048) % 2048;
	}

	private int npcFor(NpcCardArt art)
	{
		return altForm && art.getAltNpcId() > 0 ? art.getAltNpcId() : art.getNpcId();
	}

	private int zoomFor(NpcCardArt art)
	{
		if (zoomOverride != 0)
		{
			return zoomOverride;
		}
		return altForm && art.getAltZoom() > 0 ? art.getAltZoom() : art.getZoom();
	}

	private int floorOffsetFor(NpcCardArt art)
	{
		return altForm && art.getAltNpcId() > 0 && art.getAltVerticalOffset() > 0
			? art.getAltVerticalOffset() : art.getVerticalOffset();
	}

	private int animFor(NpcCardArt art)
	{
		return altForm && art.getAltNpcId() > 0 ? art.getAltAnimationId() : art.getAnimationId();
	}

	public void setPointer(double px, double py)
	{
		this.px = px;
		this.py = py;
	}

	public String tuning()
	{
		return "zoom=" + (zoomOverride == 0 ? "card" : zoomOverride)
			+ " dx=" + offsetX + " dy=" + offsetY
			+ " scenery=" + (sceneryOverride == 0 ? "card" : sceneryOverride)
			+ " szoom=" + (sceneryZoomOverride == 0 ? "card" : sceneryZoomOverride)
			+ " sdy=" + sceneryOffsetY
			+ " pzoom=" + (pillarZoomOverride == 0 ? "card" : pillarZoomOverride)
			+ " pspread=" + pillarSpreadPct
			+ " prot=" + (pillarRotationOverride < 0 ? "card" : pillarRotationOverride)
			+ " prot2=" + (pillarRotationRightOverride < 0 ? "card" : pillarRotationRightOverride)
			+ " fzoom=" + (foreZoomOverride == 0 ? "card" : foreZoomOverride)
			+ " fspread=" + foreSpreadPct + " fdy=" + foreOffsetY
			+ " layers=" + layerMask;
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

		if (builtFor != npcFor(art) || container == null)
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
			rotation = 0d;
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
		int cardYaw = (int) (-px * CARD_YAW);
		int nearX = (int) (-px * box.width * 0.10d);
		int nearY = 0;
		int nearYaw = (int) (-px * PARALLAX_YAW);
		int baseY = box.height * art.getVerticalOffset() / 100;
		int charY = box.height * floorOffsetFor(art) / 100;

		if (scenery != null)
		{
			scenery.setHidden(false);
			scenery.setOriginalX(modelX);
			scenery.setOriginalY(modelY + baseY + sceneryOffsetY);
			scenery.setOriginalWidth(size);
			scenery.setOriginalHeight(size);
			scenery.setRotationY(0);
			int sceneryZoom = sceneryZoomOverride != 0 ? sceneryZoomOverride
				: art.getSceneryZoom() > 0 ? art.getSceneryZoom() : art.getZoom();
			scenery.setModelZoom(sceneryZoom);
			scenery.setRotationX(TILT_X);
			scenery.setRotationZ(wrap(cardYaw));
			scenery.revalidate();
		}

		for (int i = 0; i < pillars.size(); i++)
		{
			Widget pillar = pillars.get(i);
			int side = i == 0 ? -1 : 1;
			pillar.setHidden(false);
			pillar.setOriginalX(modelX + side * (int) (box.width * pillarSpreadPct / 100d));
			pillar.setOriginalY(modelY + baseY);
			pillar.setOriginalWidth(size);
			pillar.setOriginalHeight(size);
			pillar.setModelZoom(pillarZoomOverride != 0 ? pillarZoomOverride
				: art.getPillarZoom() > 0 ? art.getPillarZoom() : art.getZoom());
			pillar.setRotationX(TILT_X);
			int baseRot;
			if (i == 1)
			{
				baseRot = pillarRotationRightOverride >= 0 ? pillarRotationRightOverride
					: art.getPillarRotationRight();
			}
			else
			{
				baseRot = pillarRotationOverride >= 0 ? pillarRotationOverride
					: art.getPillarRotation();
			}
			pillar.setRotationZ(wrap(baseRot + cardYaw));
			pillar.setAnimationId(-1);
			pillar.setHidden(true);
			pillar.revalidate();
		}

		for (int i = 0; i < forePillars.size(); i++)
		{
			Widget fore = forePillars.get(i);
			int side = i == 0 ? -1 : 1;
			fore.setHidden(false);
			fore.setOriginalX(modelX + side * (int) (box.width * foreSpreadPct / 100d) + nearX);
			fore.setOriginalY(modelY + foreOffsetY);
			fore.setOriginalWidth(size);
			fore.setOriginalHeight(size);
			fore.setModelZoom(foreZoomOverride != 0 ? foreZoomOverride
				: art.getForeZoom() > 0 ? art.getForeZoom() : art.getZoom());
			fore.setRotationX(TILT_X);
			fore.setRotationZ(wrap(art.getForeRotation() + nearYaw + cardYaw));
			fore.setAnimationId(-1);
			fore.setHidden(true);
			fore.revalidate();
		}

		for (Widget part : parts)
		{
			part.setHidden(false);
			part.setOriginalX(modelX);
			part.setOriginalY(modelY + charY);
			part.setOriginalWidth(size);
			part.setOriginalHeight(size);
			part.setModelZoom(zoomFor(art));
			part.setRotationX(TILT_X);
			part.setRotationZ(wrap(rotationZ + cardYaw + handSpin));
			part.setAnimationId(animFor(art));
			part.revalidate();
		}
	}

	public void hide()
	{
		hide(scenery);
		for (Widget pillar : pillars)
		{
			hide(pillar);
		}
		for (Widget fore : forePillars)
		{
			hide(fore);
		}
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
		pillars.clear();
		forePillars.clear();
		scenery = null;
		builtFor = -1;
		lastShowMs = 0L;
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
		parts.clear();
		pillars.clear();
		forePillars.clear();
		scenery = null;
		builtFor = -1;
		if (container != null)
		{
			container.deleteAllChildren();
			container.setHidden(true);
			container.revalidate();
		}

		int[] models = modelsFor(npcFor(art));
		if (models == null || models.length == 0)
		{
			return;
		}

		container = parent.createChild(-1, WidgetType.LAYER);
		container.setHidden(false);

		int sceneryModel = (layerMask & 1) == 0 ? -1 : sceneryModelFor(art);
		if (sceneryModel > 0)
		{
			scenery = container.createChild(-1, WidgetType.MODEL);
			scenery.setModelType(WidgetModelType.MODEL);
			scenery.setModelId(sceneryModel);
			scenery.setRotationY(0);
			scenery.setAnimationId(-1);
			scenery.setHidden(true);
		}

		if ((layerMask & 2) != 0 && art.getPillarModelId() > 0)
		{
			for (int i = 0; i < 2; i++)
			{
				int model = i == 1 && art.getPillarModelIdRight() > 0
					? art.getPillarModelIdRight() : art.getPillarModelId();
				Widget pillar = container.createChild(-1, WidgetType.MODEL);
				pillar.setModelType(WidgetModelType.MODEL);
				pillar.setModelId(model);
				pillar.setRotationY(0);
				pillar.setAnimationId(-1);
				pillar.setHidden(true);
				pillars.add(pillar);
			}
		}

		for (int model : (layerMask & 8) == 0 ? new int[0] : models)
		{
			Widget part = container.createChild(-1, WidgetType.MODEL);
			part.setModelType(WidgetModelType.MODEL);
			part.setModelId(model);
			part.setRotationY(0);
			part.setAnimationId(-1);
			part.setHidden(true);
			parts.add(part);
		}

		if ((layerMask & 4) != 0 && art.getForeModelId() > 0)
		{
			for (int i = 0; i < 2; i++)
			{
				Widget fore = container.createChild(-1, WidgetType.MODEL);
				fore.setModelType(WidgetModelType.MODEL);
				fore.setModelId(art.getForeModelId());
				fore.setRotationY(0);
				fore.setAnimationId(-1);
				fore.setHidden(true);
				forePillars.add(fore);
			}
		}
		builtFor = npcFor(art);
	}

	private int[] modelsFor(int npcId)
	{
		NPCComposition npc = client.getNpcDefinition(npcId);
		return npc == null ? null : npc.getModels();
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
