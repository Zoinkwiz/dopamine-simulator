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

public enum AreasCards implements CardGroup
{
	RIMMINGTON("Rimmington", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.POTTERY_WHEEL),
	BARBARIAN_VILLAGE("Barbarian Village", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.STONEMASON),
	DRAYNOR_VILLAGE("Draynor Village", Rarity.UNCOMMON, ItemID.TELETAB_DRAYNOR, -1),
	EDGEVILLE("Edgeville", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.BANK),
	HEMENSTER("Hemenster", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.FISHING_SPOT),
	TAI_BWO_WANNAI("Tai Bwo Wannai", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.WOODCUTTING_STUMP),
	MOLCH("Molch", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.HUNTER_TRAINING),
	BURGH_DE_ROTT("Burgh de Rott", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.PUB),
	MEIYERDITCH("Meiyerditch", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.MINING_SITE),
	MORT_TON("Mort’ton", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.APOTHECARY),
	JATIZSO("Jatizso", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.MINING_SHOP),
	NEITIZNOT("Neitiznot", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.SAWMILL),
	ETCETERIA("Etceteria", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.ESTATE_AGENT),
	WATERBIRTH_ISLAND("Waterbirth Island", Rarity.UNCOMMON, -1, SpriteID.Mapfunction.DUNGEON),
	BURTHORPE("Burthorpe", Rarity.RARE, -1, SpriteID.Mapfunction.COMBAT_TRAINING),
	PORT_SARIM("Port Sarim", Rarity.RARE, -1, SpriteID.Mapfunction.FISHING_SHOP),
	TAVERLEY("Taverley", Rarity.RARE, -1, SpriteID.Mapfunction.HERBALIST),
	CATHERBY("Catherby", Rarity.RARE, -1, SpriteID.Mapfunction.FARMING_PATCH),
	YANILLE("Yanille", Rarity.RARE, -1, SpriteID.Mapfunction.MAGIC_SHOP),
	SEERS_VILLAGE("Seers’ Village", Rarity.RARE, -1, SpriteID.Mapfunction.SPINNING_WHEEL),
	AL_KHARID("Al Kharid", Rarity.RARE, -1, SpriteID.Mapfunction.FURNACE),
	POLLNIVNEACH("Pollnivneach", Rarity.RARE, -1, SpriteID.Mapfunction.AGILITY_TRAINING),
	SOPHANEM("Sophanem", Rarity.RARE, -1, SpriteID.Mapfunction.ALTAR),
	NARDAH("Nardah", Rarity.RARE, -1, SpriteID.Mapfunction.WATER_SOURCE),
	CANIFIS("Canifis", Rarity.RARE, ItemID.TELETAB_FENK, -1),
	PORT_PHASMATYS("Port Phasmatys", Rarity.RARE, -1, SpriteID.Mapfunction.BREWERY),
	BRIMHAVEN("Brimhaven", Rarity.RARE, -1, SpriteID.Mapfunction.AGILITY_TRAINING),
	SHILO_VILLAGE("Shilo Village", Rarity.RARE, -1, SpriteID.Mapfunction.GEM_SHOP),
	RELLEKKA("Rellekka", Rarity.RARE, -1, SpriteID.Mapfunction.FUR_TRADER),
	MISCELLANIA("Miscellania", Rarity.RARE, -1, SpriteID.Mapfunction.ESTATE_AGENT),
	LLETYA("Lletya", Rarity.RARE, -1, SpriteID.Mapfunction.RARE_TREES),
	ARCEUUS("Arceuus", Rarity.RARE, ItemID.TELETAB_MIND_ALTAR, -1),
	HOSIDIUS("Hosidius", Rarity.RARE, -1, SpriteID.Mapfunction.FARMING_SHOP),
	LOVAKENGJ("Lovakengj", Rarity.RARE, -1, SpriteID.Mapfunction.SANDPIT),
	PORT_PISCARILIUS("Port Piscarilius", Rarity.RARE, -1, SpriteID.Mapfunction.FISHING_SPOT),
	SHAYZIEN("Shayzien", Rarity.RARE, -1, SpriteID.Mapfunction.COMBAT_TRAINING),
	VARROCK("Varrock", Rarity.EPIC, ItemID.POH_TABLET_VARROCKTELEPORT, -1),
	FALADOR("Falador", Rarity.EPIC, ItemID.POH_TABLET_FALADORTELEPORT, -1),
	ARDOUGNE("Ardougne", Rarity.EPIC, ItemID.POH_TABLET_ARDOUGNETELEPORT, -1),
	CAMELOT("Camelot", Rarity.EPIC, ItemID.POH_TABLET_CAMELOTTELEPORT, -1),
	LUMBRIDGE("Lumbridge", Rarity.EPIC, ItemID.POH_TABLET_LUMBRIDGETELEPORT, -1),
	PRIFDDINAS("Prifddinas", Rarity.EPIC, -1, SpriteID.Mapfunction.HOUSE_PORTAL),
	CIVITAS_ILLA_FORTIS("Civitas illa Fortis", Rarity.EPIC, ItemID.POH_TABLET_FORTISTELEPORT, -1),
	DARKMEYER("Darkmeyer", Rarity.EPIC, -1, SpriteID.Mapfunction.JEWELLERY_SHOP),
	KELDAGRIM("Keldagrim", Rarity.EPIC, -1, SpriteID.Mapfunction.ANVIL),
	APE_ATOLL("Ape Atoll", Rarity.EPIC, ItemID.TELETAB_APE, -1),
	LUNAR_ISLE("Lunar Isle", Rarity.EPIC, -1, SpriteID.Mapfunction.ALTAR),
	TROLLHEIM("Trollheim", Rarity.EPIC, -1, SpriteID.Mapfunction.DUNGEON),
	ASGARNIA("Asgarnia", Rarity.LEGENDARY, ItemID.WHITE_PLATEBODY, -1),
	FREMENNIK_PROVINCE("Fremennik Province", Rarity.LEGENDARY, -1, SpriteID.Mapfunction.FUR_TRADER),
	GREAT_KOUREND("Great Kourend", Rarity.LEGENDARY, ItemID.XERIC_HAT, -1),
	KANDARIN("Kandarin", Rarity.LEGENDARY, -1, SpriteID.Mapfunction.SILK_TRADER),
	KARAMJA("Karamja", Rarity.LEGENDARY, ItemID.BANANA, -1),
	KEBOS_LOWLANDS("Kebos Lowlands", Rarity.LEGENDARY, ItemID.LOVAKENGJ_SULPHUR, -1),
	KHARIDIAN_DESERT("Kharidian Desert", Rarity.LEGENDARY, -1, SpriteID.Mapfunction.SPICE_SHOP),
	MISTHALIN("Misthalin", Rarity.LEGENDARY, ItemID.CABBAGE, -1),
	MORYTANIA("Morytania", Rarity.LEGENDARY, ItemID.AMULET_OF_GHOSTSPEAK, -1),
	TIRANNWN("Tirannwn", Rarity.LEGENDARY, -1, SpriteID.Mapfunction.RARE_TREES),
	VARLAMORE("Varlamore", Rarity.LEGENDARY, ItemID.VARLAMORE_MINING_TELEPORT, -1),
	FELDIP_HILLS("Feldip Hills", Rarity.LEGENDARY, ItemID.OGRE_BOW, -1),
	TROLL_COUNTRY("Troll Country", Rarity.LEGENDARY, -1, SpriteID.Mapfunction.DUNGEON);

	private final Card card;

	AreasCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.AREAS, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
