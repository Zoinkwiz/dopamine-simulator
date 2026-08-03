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

public enum SlayerCards implements CardGroup
{
	CRAWLING_HAND("Crawling Hand", Rarity.COMMON, ItemID.SLAYERGUIDE_CRAWLINGHAND, -1),
	CAVE_BUG("Cave Bug", Rarity.COMMON, ItemID.SWAMP_CAVE_BUG, -1),
	CAVE_CRAWLER("Cave Crawler", Rarity.COMMON, ItemID.SLAYERGUIDE_CAVECRAWLER, -1),
	BANSHEE("Banshee", Rarity.COMMON, ItemID.SLAYERGUIDE_BANSHEE, -1),
	CAVE_SLIME("Cave Slime", Rarity.COMMON, ItemID.SWAMP_CAVE_SLIME, -1),
	ROCKSLUG("Rockslug", Rarity.COMMON, ItemID.SLAYERGUIDE_ROCKSLUG, -1),
	DESERT_LIZARD("Desert Lizard", Rarity.COMMON, ItemID.SLAYERGUIDE_LIZARD, -1),
	COCKATRICE("Cockatrice", Rarity.COMMON, ItemID.SLAYERGUIDE_COCKATRICE, -1),
	PYREFIEND("Pyrefiend", Rarity.COMMON, ItemID.SLAYERGUIDE_PYRFIEND, -1),
	MOGRE("Mogre", Rarity.COMMON, ItemID.SLAYERGUIDE_MOGRE, -1),
	HARPIE_BUG_SWARM("Harpie Bug Swarm", Rarity.COMMON, ItemID.SLAYERGUIDE_SWARM, -1),
	WALL_BEAST("Wall Beast", Rarity.COMMON, ItemID.SWAMP_WALLBEAST, -1),
	KILLERWATT("Killerwatt", Rarity.COMMON, ItemID.SLAYERGUIDE_KILLERWATT, -1),
	VYRE("Vyre", Rarity.COMMON, ItemID.PICKPOCKET_GUIDE_VYRE, -1),
	MOLANISK("Molanisk", Rarity.COMMON, ItemID.SLAYERGUIDE_MOLANISK, -1),
	BURTHORPE_MASTER("Burthorpe master", Rarity.COMMON, ItemID.SLAYER_GEM, -1),
	CANIFIS_MASTER("Canifis master", Rarity.COMMON, ItemID.SLAYER_GEM, -1),
	DRAYNOR_VILLAGE_MASTER("Draynor Village master", Rarity.COMMON, ItemID.SLAYER_GEM, -1),
	EDGEVILLE_DUNGEON_MASTER("Edgeville Dungeon master", Rarity.COMMON, ItemID.SLAYER_GEM, -1),
	GNOME_STRONGHOLD_MASTER("Gnome Stronghold master", Rarity.COMMON, ItemID.SLAYER_GEM, -1),
	MOUNT_KARUULM_MASTER("Mount Karuulm master", Rarity.COMMON, ItemID.SLAYER_GEM, -1),
	REINFORCED_GOGGLES("Reinforced Goggles", Rarity.COMMON, ItemID.SLAYER_REINFORCED_GOGGLES, -1),
	ROCK_HAMMER_ROCK_THROWNHAMMER("Rock hammer & Rock Thrownhammer", Rarity.COMMON, ItemID.SLAYER_ROCK_HAMMER, -1),
	SPINY_HELMET("Spiny helmet", Rarity.COMMON, ItemID.WALLBEAST_SPIKE_HELMET, -1),
	WILDERNESS_MASTER("Wilderness master", Rarity.COMMON, ItemID.SLAYER_GEM, -1),
	ZANARIS_MASTER("Zanaris master", Rarity.COMMON, ItemID.SLAYER_GEM, -1),
	FACEMASK("Facemask", Rarity.COMMON, ItemID.SLAYER_FACEMASK, -1),
	EARMUFFS("Earmuffs", Rarity.COMMON, ItemID.SLAYER_EARMUFFS, -1),
	BASILISK("Basilisk", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_BASILISK, -1),
	SEA_SNAKE("Sea Snake", Rarity.UNCOMMON, ItemID.CERT_FISHING_SPOT_ICON_DUMMY, -1),
	TERROR_DOG("Terror Dog", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_TERRORDOG, -1),
	FEVER_SPIDER("Fever Spider", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_FEVER_SPIDER, -1),
	SULPHUR_LIZARD("Sulphur Lizard", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_SULPHURLIZARDS, -1),
	INFERNAL_MAGE("Infernal Mage", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_INFERNALMAGE, -1),
	BRINE_RAT("Brine Rat", Rarity.UNCOMMON, ItemID.OLAF2_BRINE_RAT_INV, -1),
	LESSER_NAGUA("Lesser Nagua", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_LESSER_NAGUA, -1),
	BLOODVELD("Bloodveld", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_BLOODVELD, -1),
	GRYPHON("Gryphon", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_GRYPHON, -1),
	JELLY("Jelly", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_JELLY, -1),
	JUVENILE_CUSTODIAN_STALKER("Juvenile Custodian Stalker", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_CUSTODIAN_STALKER_JUVENILE, -1),
	TUROTH("Turoth", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_TUROTH, -1),
	WARPED_CREATURE("Warped Creature", Rarity.UNCOMMON, ItemID.POG_SLAYER_DUMMY_WARPED_TERRORBIRD, -1),
	MUTATED_ZYGOMITE("Mutated Zygomite", Rarity.UNCOMMON, ItemID.SLAYER_ZYGOMITE_OBJECT, -1),
	CAVE_HORROR("Cave Horror", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_HARMLESS_CAVE_HORROR, -1),
	FISHING_EXPLOSIVE("Fishing explosive", Rarity.UNCOMMON, ItemID.SLAYERGUIDE_FISHING_EXPLOSIVE, -1),
	WITCHWOOD_ICON("Witchwood icon", Rarity.UNCOMMON, ItemID.WITCHWOOD_ICON, -1),
	INSULATED_BOOTS("Insulated boots", Rarity.UNCOMMON, ItemID.SLAYER_BOOTS, -1),
	SLAYER_BELL("Slayer bell", Rarity.UNCOMMON, ItemID.MOLANISK_BELL, -1),
	ABERRANT_SPECTRE("Aberrant Spectre", Rarity.RARE, ItemID.SLAYERGUIDE_ABERRANTSPECTER, -1),
	BASILISK_KNIGHT("Basilisk Knight", Rarity.RARE, ItemID.SLAYERGUIDE_BASILISK_KNIGHT, -1),
	WYRM("Wyrm", Rarity.RARE, ItemID.SLAYERGUIDE_WYRM, -1),
	SPIRITUAL_RANGER("Spiritual Ranger", Rarity.RARE, ItemID.GODWARS_SPIRITUAL_RANGER_INV, -1),
	DUST_DEVIL("Dust Devil", Rarity.RARE, ItemID.SLAYERGUIDE_DUSTDEVIL, -1),
	FOSSIL_ISLAND_WYVERN("Fossil Island Wyvern", Rarity.RARE, ItemID.SLAYERGUIDE_FOSSILWYVERN, -1),
	MATURE_CUSTODIAN_STALKER("Mature Custodian Stalker", Rarity.RARE, ItemID.SLAYERGUIDE_CUSTODIAN_STALKER_MATURE, -1),
	SPIRITUAL_WARRIOR("Spiritual Warrior", Rarity.RARE, ItemID.GODWARS_SPIRITUAL_WARRIOR_INV, -1),
	KURASK("Kurask", Rarity.RARE, ItemID.SLAYERGUIDE_KURASK, -1),
	SKELETAL_WYVERN("Skeletal Wyvern", Rarity.RARE, ItemID.SLAYERGUIDE_SKELETALWYVERN, -1),
	VENATOR("Venator", Rarity.RARE, ItemID.SLAYERGUIDE_VENATOR, -1),
	SLAYER_GLOVES("Slayer gloves", Rarity.RARE, ItemID.DEAL_SLAYER_GLOVES, -1),
	BOOTS_OF_STONE("Boots of Stone", Rarity.RARE, ItemID.BOOTS_OF_STONE, -1),
	SHILO_VILLAGE_MASTER("Shilo Village master", Rarity.RARE, ItemID.SLAYER_GEM, -1),
	BROAD_ARROWS("Broad arrows", Rarity.RARE, ItemID.SLAYERGUIDE_BROAD_ARROWS, -1),
	BROAD_BOLTS("Broad bolts", Rarity.RARE, ItemID.CERT_PICKPOCKET_GUIDE_FHAM, -1),
	LEAF_BLADED_BATTLEAXE("Leaf-bladed battleaxe", Rarity.RARE, ItemID.LEAFBLADED_BATTLEAXE, -1),
	LEAF_BLADED_SPEAR_AND_SWORD("Leaf-bladed spear and sword", Rarity.RARE, ItemID.SLAYER_LEAFBLADED_SPEAR, -1),
	SLAYER_S_STAFF("Slayer's staff", Rarity.RARE, ItemID.SLAYER_STAFF, -1),
	SLAYER_S_STAFF_E("Slayer's staff (e)", Rarity.RARE, ItemID.SLAYER_STAFF_ENCHANTED, -1),
	FUNGICIDE_SPRAY("Fungicide spray", Rarity.RARE, ItemID.SLAYER_SPRAY_PUMP_10, -1),
	GARGOYLE("Gargoyle", Rarity.EPIC, ItemID.SLAYERGUIDE_GARGOYLE, -1),
	ELDER_CUSTODIAN_STALKER("Elder Custodian Stalker", Rarity.EPIC, ItemID.SLAYERGUIDE_CUSTODIAN_STALKER_ELDER, -1),
	BRUTAL_BLACK_DRAGON("Brutal Black Dragon", Rarity.EPIC, ItemID.SLAYERGUIDE_BRUTAL_BLACK, -1),
	AQUANITE("Aquanite", Rarity.EPIC, ItemID.SLAYERGUIDE_AQUANITE, -1),
	NECHRYAEL("Nechryael", Rarity.EPIC, ItemID.SLAYERGUIDE_NECHRYAEL, -1),
	ANCIENT_WYVERN("Ancient Wyvern", Rarity.EPIC, ItemID.SLAYERGUIDE_FOSSILANCIENTWYVERN, -1),
	SPIRITUAL_MAGE("Spiritual Mage", Rarity.EPIC, ItemID.GODWARS_SPIRITUAL_MAGE_INV, -1),
	DRAKE("Drake", Rarity.EPIC, ItemID.SLAYERGUIDE_DRAKE, -1),
	ABYSSAL_DEMON("Abyssal Demon", Rarity.EPIC, ItemID.SLAYERGUIDE_ABYSSALDEMON, -1),
	CAVE_KRAKEN("Cave Kraken", Rarity.EPIC, ItemID.CERT_EADGAR_FADE_TO_BLACK_INV, -1),
	NOSE_PEG("Nose peg", Rarity.EPIC, ItemID.SLAYER_NOSEPEG, -1),
	V_S_SHIELD("V's shield", Rarity.EPIC, ItemID.V_SHIELD, -1),
	AMETHYST_BROAD_BOLTS("Amethyst broad bolts", Rarity.EPIC, ItemID.REINIT, -1),
	WYRMSCRAIG_MASTER("Wyrmscraig master", Rarity.EPIC, ItemID.SLAYER_GEM, -1),
	DARK_BEAST("Dark Beast", Rarity.LEGENDARY, ItemID.SLAYERGUIDE_DARK_BEAST, -1),
	ARAXYTE("Araxyte", Rarity.LEGENDARY, ItemID.POG_SLAYER_DUMMY_ARAXYTES, -1),
	SMOKE_DEVIL("Smoke Devil", Rarity.LEGENDARY, ItemID.CERT_GUIDE_ICON_DUMMY, -1),
	HYDRA("Hydra", Rarity.LEGENDARY, ItemID.SLAYERGUIDE_HYDRA, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_SLAYER, -1);

	private final Card card;

	SlayerCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.SLAYER, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
