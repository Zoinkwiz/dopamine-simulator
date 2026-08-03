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
package com.dopaminesimulator.cards.sets;

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.CardGroup;
import com.dopaminesimulator.cards.CardSet;
import com.dopaminesimulator.cards.Rarity;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.SpriteID;

public enum FishingCards implements CardGroup
{
	BIG_FISHING_NET("Big fishing net", Rarity.COMMON, ItemID.BIG_NET, -1),
	FISHING_ROD("Fishing rod", Rarity.COMMON, ItemID.FISHING_ROD, -1),
	FLY_FISHING_ROD("Fly fishing rod", Rarity.COMMON, ItemID.FLY_FISHING_ROD, -1),
	HARPOON("Harpoon", Rarity.COMMON, ItemID.HARPOON, -1),
	KARAMBWAN_VESSEL("Karambwan vessel", Rarity.COMMON, ItemID.TBWT_KARAMBWAN_VESSEL, -1),
	LOBSTER_POT("Lobster pot", Rarity.COMMON, ItemID.LOBSTER_POT, -1),
	PYSK_FISH("Pysk fish", Rarity.COMMON, ItemID.RAIDS_FISH0_RAW, -1),
	SHRIMP("Shrimp", Rarity.COMMON, ItemID.RAW_SHRIMP, -1),
	SMALL_FISHING_NET("Small fishing net", Rarity.COMMON, ItemID.NET, -1),
	KARAMBWANJI("Karambwanji", Rarity.COMMON, ItemID.TBWT_RAW_KARAMBWANJI, -1),
	SARDINE("Sardine", Rarity.COMMON, ItemID.RAW_SARDINE, -1),
	GUPPY("Guppy", Rarity.COMMON, ItemID.RAW_GUPPY, -1),
	HERRING("Herring", Rarity.COMMON, ItemID.RAW_HERRING, -1),
	ANCHOVIES("Anchovies", Rarity.COMMON, ItemID.RAW_ANCHOVIES, -1),
	SUPHI_FISH("Suphi fish", Rarity.COMMON, ItemID.RAIDS_FISH1_RAW, -1),
	CASKET("Casket", Rarity.COMMON, ItemID.CASKET, -1),
	MACKEREL("Mackerel", Rarity.COMMON, ItemID.RAW_MACKEREL, -1),
	OYSTER("Oyster", Rarity.COMMON, ItemID.OYSTERSHELL, -1),
	BREAM("Bream", Rarity.UNCOMMON, ItemID.BREAM_FISH_RAW, -1),
	CAVEFISH("Cavefish", Rarity.UNCOMMON, ItemID.RAW_CAVEFISH, -1),
	TROUT("Trout", Rarity.UNCOMMON, ItemID.RAW_TROUT, -1),
	CODBIG_FISHING_NET("CodBig fishing net", Rarity.UNCOMMON, ItemID.RAW_COD, -1),
	PIKE("Pike", Rarity.UNCOMMON, ItemID.RAW_PIKE, -1),
	SLIMY_EEL("Slimy eel", Rarity.UNCOMMON, ItemID.MORT_SLIMEY_EEL, -1),
	LECKISH_FISH("Leckish fish", Rarity.UNCOMMON, ItemID.RAIDS_FISH2_RAW, -1),
	SALMON("Salmon", Rarity.UNCOMMON, ItemID.RAW_SALMON, -1),
	GIANT_FROGSPAWN("Giant frogspawn", Rarity.UNCOMMON, ItemID.GIANT_FROGSPAWN, -1),
	TETRA("Tetra", Rarity.UNCOMMON, ItemID.RAW_TETRA, -1),
	ANGLER_S_OUTFIT("Angler's outfit", Rarity.UNCOMMON, ItemID.TRAWLER_REWARD_HAT, -1),
	FIGHTING_TEMPOROSS("Fighting Tempoross", Rarity.UNCOMMON, ItemID.TEMPOROSS_HARPOONFISH, -1),
	TUNA("Tuna", Rarity.UNCOMMON, ItemID.RAW_TUNA, -1),
	CAVE_EEL("Cave eel", Rarity.UNCOMMON, ItemID.RAW_CAVE_EEL, -1),
	RAINBOW_FISH("Rainbow fish", Rarity.UNCOMMON, ItemID.HUNTING_RAW_FISH_SPECIAL, -1),
	LOBSTER("Lobster", Rarity.RARE, ItemID.RAW_LOBSTER, -1),
	BLUEGILL("Bluegill", Rarity.RARE, ItemID.AERIAL_FISHING_BLUEGILL, -1),
	BRAWK_FISH("Brawk fish", Rarity.RARE, ItemID.RAIDS_FISH3_RAW, -1),
	BASS("Bass", Rarity.RARE, ItemID.RAW_BASS, -1),
	CATFISH("Catfish", Rarity.RARE, ItemID.RAW_CATFISH, -1),
	BARBARIAN_ROD("Barbarian rod", Rarity.RARE, ItemID.BRUT_FISHING_ROD, -1),
	LEAPING_TROUT("Leaping trout", Rarity.RARE, ItemID.BRUT_SPAWNING_TROUT, -1),
	SWORDFISH("Swordfish", Rarity.RARE, ItemID.RAW_SWORDFISH, -1),
	SWORDTIP_SQUID("Swordtip squid", Rarity.RARE, ItemID.RAW_SWORDTIP_SQUID, -1),
	LAVA_EEL("Lava eel", Rarity.RARE, ItemID.RAW_LAVA_EEL, -1),
	HARPOONFISH_BAREHANDED("Harpoonfish (barehanded)", Rarity.RARE, ItemID.TEMPOROSS_RAW_HARPOONFISH, -1),
	COMMON_TENCH("Common tench", Rarity.RARE, ItemID.AERIAL_FISHING_COMMON_TENCH, -1),
	LEAPING_SALMON("Leaping salmon", Rarity.RARE, ItemID.BRUT_SPAWNING_SALMON, -1),
	MYCIL_FISH("Mycil fish", Rarity.EPIC, ItemID.RAIDS_FISH4_RAW, -1),
	DRAGON_HARPOON("Dragon harpoon", Rarity.EPIC, ItemID.DRAGON_HARPOON, -1),
	MONKFISH("Monkfish", Rarity.EPIC, ItemID.RAW_MONKFISH, -1),
	KARAMBWAN("Karambwan", Rarity.EPIC, ItemID.TBWT_RAW_KARAMBWAN, -1),
	GIANT_KRILL_SHOAL("Giant krill shoal", Rarity.EPIC, ItemID.RAW_GIANT_KRILL, -1),
	JUMBO_SQUID("Jumbo squid", Rarity.EPIC, ItemID.RAW_JUMBO_SQUID, -1),
	SHIMMERING_SHOAL("Shimmering shoal", Rarity.EPIC, ItemID.RAW_GIANT_KRILL, -1),
	LEAPING_STURGEON("Leaping sturgeon", Rarity.EPIC, ItemID.BRUT_STURGEON, -1),
	CRYSTAL_HARPOON("Crystal harpoon", Rarity.EPIC, ItemID.CRYSTAL_HARPOON, -1),
	HADDOCK_SHOAL("Haddock shoal", Rarity.EPIC, ItemID.RAW_HADDOCK, -1),
	MOTTLED_EEL("Mottled eel", Rarity.EPIC, ItemID.AERIAL_FISHING_MOTTLED_EEL, -1),
	INFERNAL_HARPOON("Infernal harpoon", Rarity.EPIC, ItemID.INFERNAL_HARPOON, -1),
	ROQED_FISH("Roqed fish", Rarity.EPIC, ItemID.RAIDS_FISH5_RAW, -1),
	GLISTENING_SHOAL("Glistening shoal", Rarity.EPIC, ItemID.RAW_YELLOWFIN, -1),
	SHARK("Shark", Rarity.EPIC, ItemID.RAW_SHARK, -1),
	LEECHFIN("Leechfin", Rarity.EPIC, ItemID.LEECHFIN, -1),
	SEA_TURTLE("Sea turtle", Rarity.EPIC, ItemID.RAW_SEATURTLE, -1),
	YELLOWFIN_SHOAL("Yellowfin shoal", Rarity.EPIC, ItemID.RAW_YELLOWFIN, -1),
	INFERNAL_EEL("Infernal eel", Rarity.LEGENDARY, ItemID.INFERNAL_EEL, -1),
	MANTA_RAY("Manta ray", Rarity.LEGENDARY, ItemID.RAW_MANTARAY, -1),
	ANGLERFISH("Anglerfish", Rarity.LEGENDARY, ItemID.RAW_ANGLERFISH, -1),
	MINNOW("Minnow", Rarity.LEGENDARY, ItemID.CERT_STRUNG_SAPPHIRE_AMULET_5, -1),
	HALIBUT_SHOAL("Halibut shoal", Rarity.LEGENDARY, ItemID.RAW_HALIBUT, -1),
	DARK_CRAB("Dark crab", Rarity.LEGENDARY, ItemID.RAW_DARK_CRAB, -1),
	VIBRANT_SHOAL("Vibrant shoal", Rarity.LEGENDARY, ItemID.RAW_BLUEFIN, -1),
	BLUEFIN_SHOAL("Bluefin shoal", Rarity.LEGENDARY, ItemID.RAW_BLUEFIN, -1),
	SACRED_EEL("Sacred eel", Rarity.LEGENDARY, ItemID.SNAKEBOSS_EEL, -1),
	KYREN_FISH("Kyren fish", Rarity.LEGENDARY, ItemID.RAIDS_FISH6_RAW, -1),
	MARLIN_SHOAL("Marlin shoal", Rarity.LEGENDARY, ItemID.RAW_MARLIN, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_FISHING, -1);

	private final Card card;

	FishingCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.FISHING, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
