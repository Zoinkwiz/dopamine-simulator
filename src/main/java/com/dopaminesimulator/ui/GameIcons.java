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

import com.dopaminesimulator.packs.PackTier;
import com.dopaminesimulator.points.GnomeFood;
import com.dopaminesimulator.points.PointSource;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.dopaminesimulator.incremental.Milestones;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.SpriteID;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.util.AsyncBufferedImage;

@Singleton
public class GameIcons
{
	private final SpriteManager spriteManager;
	private final ItemManager itemManager;
	private final Map<PointSource, BufferedImage> sourceIcons = new EnumMap<>(PointSource.class);
	private final Map<PackTier, BufferedImage> packIcons = new EnumMap<>(PackTier.class);
	private final Map<GnomeFood, BufferedImage> foodIcons = new EnumMap<>(GnomeFood.class);
	private final Set<GnomeFood> foodRequested = EnumSet.noneOf(GnomeFood.class);
	private Runnable onFoodGilded = () ->
	{
	};
	@Inject
	public GameIcons(SpriteManager spriteManager, ItemManager itemManager)
	{
		this.spriteManager = spriteManager;
		this.itemManager = itemManager;
	}

	public void warm(Runnable onIconReady)
	{
		onFoodGilded = onIconReady;
		for (GnomeFood food : GnomeFood.values())
		{
			gild(food);
		}
		for (PointSource source : PointSource.values())
		{
			int itemId = itemIdFor(source);
			if (itemId > 0)
			{
				BufferedImage image = itemManager.getImage(itemId);
				if (image != null)
				{
					sourceIcons.put(source, image);
					onIconReady.run();
				}
				continue;
			}
			spriteManager.getSpriteAsync(spriteIdFor(source), 0, image ->
			{
				if (image != null)
				{
					sourceIcons.put(source, image);
					onIconReady.run();
				}
			});
		}
		for (PackTier tier : PackTier.values())
		{
			BufferedImage image = itemManager.getImage(itemIdFor(tier));
			if (image != null)
			{
				packIcons.put(tier, image);
			}
		}
		onIconReady.run();
	}
	public BufferedImage forPack(PackTier tier)
	{
		return packIcons.get(tier);
	}
	private static final int[] COIN_LADDER = {
		ItemID.COINS, ItemID.COINS_2, ItemID.COINS_3, ItemID.COINS_4, ItemID.COINS_5,
		ItemID.COINS_25, ItemID.COINS_100, ItemID.COINS_250, ItemID.COINS_1000, ItemID.COINS_10000
	};

	public BufferedImage forClick(double lifetimePoints)
	{
		int reached = Milestones.reached(lifetimePoints);
		int tier = Math.max(0, Math.min(COIN_LADDER.length - 1,
			reached * (COIN_LADDER.length - 1) / Math.max(1, Milestones.MAX_MILESTONES)));
		return itemManager.getImage(COIN_LADDER[tier]);
	}

	public BufferedImage forFood(GnomeFood food)
	{
		BufferedImage gold = foodIcons.get(food);
		return gold != null ? gold : gild(food);
	}

	/**
	 * Item images arrive asynchronously, so the gilding has to wait for the
	 * pixels. The plain icon stands in for the frame or two that takes.
	 */
	private BufferedImage gild(GnomeFood food)
	{
		AsyncBufferedImage image = itemManager.getImage(food.getItemId());
		if (image == null)
		{
			return null;
		}
		if (foodRequested.add(food))
		{
			image.onLoaded(() ->
			{
				foodIcons.put(food, golden(image));
				onFoodGilded.run();
			});
		}
		return foodIcons.getOrDefault(food, image);
	}

	private static final Color GOLD_SHADOW = new Color(0x3C, 0x26, 0x00);
	private static final Color GOLD_MID = new Color(0xD4, 0xAF, 0x37);
	private static final Color GOLD_HIGHLIGHT = new Color(0xFF, 0xF4, 0xC8);

	/**
	 * Re-tones an icon onto a gold ramp. Shape and shading survive because each
	 * pixel keeps its alpha and picks its colour by how bright it started.
	 */
	public static BufferedImage golden(BufferedImage source)
	{
		int width = source.getWidth();
		int height = source.getHeight();
		BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				int argb = source.getRGB(x, y);
				int alpha = argb >>> 24;
				if (alpha == 0)
				{
					continue;
				}
				double luma = (0.299d * ((argb >> 16) & 0xFF)
					+ 0.587d * ((argb >> 8) & 0xFF)
					+ 0.114d * (argb & 0xFF)) / 255d;
				out.setRGB(x, y, (alpha << 24) | ramp(luma));
			}
		}
		return out;
	}

	private static int ramp(double luma)
	{
		boolean dark = luma < 0.5d;
		Color from = dark ? GOLD_SHADOW : GOLD_MID;
		Color to = dark ? GOLD_MID : GOLD_HIGHLIGHT;
		double t = dark ? luma * 2d : (luma - 0.5d) * 2d;
		return (lerp(from.getRed(), to.getRed(), t) << 16)
			| (lerp(from.getGreen(), to.getGreen(), t) << 8)
			| lerp(from.getBlue(), to.getBlue(), t);
	}

	private static int lerp(int from, int to, double t)
	{
		return (int) Math.round(from + (to - from) * t);
	}

	private static int itemIdFor(PackTier tier)
	{
		switch (tier)
		{
			case SCRAP:
				return ItemID.CASKET;
			case STANDARD:
				return ItemID.TRAIL_REWARD_CASKET_BEGINNER;
			case GILDED:
				return ItemID.TRAIL_REWARD_CASKET_EASY;
			case CURATED:
				return ItemID.TRAIL_REWARD_CASKET_MEDIUM;
			case PRISMATIC:
				return ItemID.TRAIL_REWARD_CASKET_HARD;
			case ASCENDANT:
				return ItemID.TRAIL_REWARD_CASKET_ELITE;
			default:
				return ItemID.TRAIL_REWARD_CASKET_MASTER;
		}
	}
	public BufferedImage forSource(PointSource source)
	{
		return sourceIcons.get(source);
	}
	private static int itemIdFor(PointSource source)
	{
		switch (source)
		{
			case WEALTH:
				return ItemID.COINS;
			case CLICK:

				return ItemID.FEROCIOUS_GLOVES;
			default:
				return -1;
		}
	}
	private static int spriteIdFor(PointSource source)
	{
		switch (source)
		{
			case CLICK:
				return SpriteID.Wornicons.HANDS;
			case EXPERIENCE:
				return SpriteID.Staticons2.TOTAL;
			case COMBAT:
				return SpriteID.Staticons.ATTACK;
			case RECOVERY:
				return SpriteID.Staticons.HITPOINTS;
			case TRAVEL:
				return SpriteID.Staticons.AGILITY;
			case IDLING:
				return SpriteID.Mapfunction.BANK;
			case SUFFERING:
				return SpriteID.Staticons.HITPOINTS;
			default:
				return SpriteID.Staticons2.FARMING;
		}
	}
}
