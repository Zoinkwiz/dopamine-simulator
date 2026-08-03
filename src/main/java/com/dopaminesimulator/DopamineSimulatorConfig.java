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

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup(DopamineSimulatorConfig.GROUP)
public interface DopamineSimulatorConfig extends Config
{
	String GROUP = "dopaminesimulator";

	@ConfigSection(
		name = "Overlay",
		description = "What is drawn on the game screen",
		position = 0
	)
	String overlaySection = "overlaySection";

	@ConfigItem(
		keyName = "showOverlay",
		name = "Show overlay",
		description = "Draw the pack progress and reward backlog over the game",
		section = overlaySection,
		position = 1
	)
	default boolean showOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showProgressBar",
		name = "Show pack progress",
		description = "Include the progress bar toward your next pack",
		section = overlaySection,
		position = 2
	)
	default boolean showProgressBar()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hideOverlayWhenEmpty",
		name = "Hide when nothing is pending",
		description = "Only draw the overlay when you have packs or rewards waiting",
		section = overlaySection,
		position = 3
	)
	default boolean hideOverlayWhenEmpty()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showIncomeRate",
		name = "Show earning rate",
		description = "Show how many packs an hour your current activity is earning",
		section = overlaySection,
		position = 4
	)
	default boolean showIncomeRate()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showRewardFlashes",
		name = "Show card reveals",
		description = "Animate cards over the game screen as they are revealed",
		section = overlaySection,
		position = 5
	)
	default boolean showRewardFlashes()
	{
		return true;
	}

	@ConfigItem(
		keyName = "dimScreenOnReveal",
		name = "Dim screen during reveals",
		description = "Darken the game behind cards as they are revealed",
		section = overlaySection,
		position = 7
	)
	default boolean dimScreenOnReveal()
	{
		return true;
	}

	@ConfigItem(
		keyName = "playRevealSounds",
		name = "Reveal sounds",
		description = "Play a tone as cards land and flip, pitched by rarity",
		section = overlaySection,
		position = 8
	)
	default boolean playRevealSounds()
	{
		return true;
	}

	@Range(min = 0, max = 100)
	@ConfigItem(
		keyName = "revealVolume",
		name = "Reveal volume",
		description = "Volume of the reveal tones, 0 to 100",
		section = overlaySection,
		position = 9
	)
	default int revealVolume()
	{
		return 55;
	}

	@ConfigItem(
		keyName = "showPackInfobox",
		name = "Show pack infobox",
		description = "Show an infobox while you have unopened packs",
		section = overlaySection,
		position = 6
	)
	default boolean showPackInfobox()
	{
		return true;
	}

	@ConfigSection(
		name = "In-world effects",
		description = "What is drawn on the player and in the scene",
		position = 20
	)
	String worldSection = "worldSection";

	@ConfigItem(
		keyName = "showFloatingText",
		name = "Floating text",
		description = "Show text rising off your character as you earn things",
		section = worldSection,
		position = 21
	)
	default boolean showFloatingText()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showEnergyFloaters",
		name = "Show progress ticks",
		description = "Periodically show how much closer you are to the next pack",
		section = worldSection,
		position = 22
	)
	default boolean showEnergyFloaters()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showCardFloaters",
		name = "Show card names",
		description = "Show each revealed card rising off your character",
		section = worldSection,
		position = 23
	)
	default boolean showCardFloaters()
	{
		return true;
	}

	@ConfigSection(
		name = "Notifications",
		description = "How rewards are announced",
		position = 10
	)
	String notificationSection = "notificationSection";

	@ConfigItem(
		keyName = "autoReveal",
		name = "Reveal rewards automatically",
		description = "Play the reveal cascade as rewards arrive, instead of waiting for you to claim them",
		section = notificationSection,
		position = 10
	)
	default boolean autoReveal()
	{
		return true;
	}

	@ConfigItem(
		keyName = "chatMessageOnNewCard",
		name = "Chat message for new cards",
		description = "Post a game chat message when you pull a card you did not own",
		section = notificationSection,
		position = 11
	)
	default boolean chatMessageOnNewCard()
	{
		return true;
	}

	@ConfigItem(
		keyName = "minimumChatRarity",
		name = "Minimum chat rarity",
		description = "Only announce cards at or above this rarity in chat",
		section = notificationSection,
		position = 12
	)
	default ChatRarityThreshold minimumChatRarity()
	{
		return ChatRarityThreshold.RARE;
	}

	enum ChatRarityThreshold
	{
		COMMON,
		UNCOMMON,
		RARE,
		EPIC,
		LEGENDARY
	}
}
