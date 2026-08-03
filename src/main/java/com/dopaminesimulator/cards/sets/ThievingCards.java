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

public enum ThievingCards implements CardGroup
{
	CITIZEN("Citizen", Rarity.COMMON, ItemID.PICKPOCKET_GUIDE_MAN, -1),
	SORCERESS_S_GARDEN_MINIGAME_WINTERAL_KHARID_REGION("Sorceress's Garden minigame - WinterAl Kharid region", Rarity.COMMON, ItemID._100_OSMAN_WINTER_ICON, -1),
	VEGETABLE_STALL("Vegetable stall", Rarity.COMMON, ItemID.CABBAGE, -1),
	CAKE_STALL("Cake stall", Rarity.COMMON, ItemID.CAKE, -1),
	CRAFTING_STALL("Crafting stall", Rarity.COMMON, ItemID.CHISEL, -1),
	MONKEY_FOOD_STALL("Monkey food stall", Rarity.COMMON, ItemID.BANANA, -1),
	TEA_STALL("Tea stall", Rarity.COMMON, ItemID.CUP_OF_TEA, -1),
	FARMER("Farmer", Rarity.COMMON, ItemID.PICKPOCKET_GUIDE_FARMER, -1),
	CHESTS_IN_ARDOUGNE_RELLEKKA_AND_THE_WILDERNESS("Chests in Ardougne, Rellekka and the Wilderness", Rarity.COMMON, ItemID.COINS_25, -1),
	CAN_STEAL_COWBELLS_FROM_DAIRY_COWS("Can steal cowbells from dairy cows", Rarity.COMMON, ItemID.PENG_COWBELL, -1),
	H_A_M_FOLLOWER("H.A.M. follower", Rarity.COMMON, ItemID.PICKPOCKET_GUIDE_FHAM, -1),
	SILK_STALL("Silk stall", Rarity.UNCOMMON, ItemID.SILK, -1),
	PYRAMID_PLUNDER_MINIGAME_ROOM_1IN_THE_JALSAVRAH_PYRAMID_IN_SOPHANEM("Pyramid Plunder minigame - Room 1In the Jalsavrah Pyramid in Sophanem", Rarity.UNCOMMON, ItemID.NTK_IVORY_COMB, -1),
	WINE_STALL("Wine stall", Rarity.UNCOMMON, ItemID.RAG_BOTTLE_WINE, -1),
	FRUIT_STALL("Fruit stall", Rarity.UNCOMMON, ItemID.GOLOVANOVA_TOP, -1),
	SORCERESS_S_GARDEN_MINIGAME_SPRINGAL_KHARID_REGION("Sorceress's Garden minigame - SpringAl Kharid region", Rarity.UNCOMMON, ItemID._100_OSMAN_SPRING_ICON, -1),
	WARRIOR("Warrior", Rarity.UNCOMMON, ItemID.PICKPOCKET_GUIDE_WARRIOR, -1),
	SEED_STALL("Seed stall", Rarity.UNCOMMON, ItemID.SEEDS_SKILLGUIDE, -1),
	CHEST_AT_THE_ISLE_OF_SOULS("Chest at the Isle of Souls", Rarity.UNCOMMON, ItemID.SW_DUNGEON_CHEST_KEY, -1),
	CHESTS_UPSTAIRS_IN_ARDOUGNE_AND_RELLEKKA("Chests upstairs in Ardougne and Rellekka", Rarity.UNCOMMON, ItemID.NATURERUNE, -1),
	VILLAGER("Villager", Rarity.UNCOMMON, ItemID.PICKPOCKET_GUIDE_VILLAGER, -1),
	PYRAMID_PLUNDER_ROOM_2("Pyramid Plunder - Room 2", Rarity.UNCOMMON, ItemID.NTK_SCARAB_POTTERY, -1),
	ROGUE("Rogue", Rarity.UNCOMMON, ItemID.PICKPOCKET_GUIDE_ROGUE, -1),
	RUSTY_PIRATE_CHESTS("Rusty Pirate Chests", Rarity.UNCOMMON, ItemID.BRONZE_CANNONBALL, -1),
	FUR_STALL("Fur stall", Rarity.UNCOMMON, ItemID.FUR, -1),
	CAVE_GOBLIN("Cave goblin", Rarity.UNCOMMON, ItemID.PICKPOCKET_GUIDE_DORGESH, -1),
	CHESTS_IN_ALDARIN_VILLAS("Chests in Aldarin Villas", Rarity.UNCOMMON, ItemID.ECLIPSE_WINE, -1),
	MASTER_FARMER("Master farmer", Rarity.UNCOMMON, ItemID.PICKPOCKET_GUIDE_MASTER_FARMER, -1),
	GUARD("Guard", Rarity.RARE, ItemID.PICKPOCKET_GUIDE_GUARD, -1),
	PYRAMID_PLUNDER_ROOM_3("Pyramid Plunder - Room 3", Rarity.RARE, ItemID.NTK_STATUETTE_POTTERY, -1),
	FISH_STALL("Fish stall", Rarity.RARE, ItemID.RAW_SALMON, -1),
	CHEST_UPSTAIRS_IN_ARDOUGNE("Chest upstairs in Ardougne", Rarity.RARE, ItemID.COINS_250, -1),
	CAN_STEAL_WIRE_FROM_THE_DORGESHUUN_WIRE_MACHINE("Can steal wire from the Dorgeshuun wire machine", Rarity.RARE, ItemID.DORGESH_WIRE, -1),
	BEARDED_POLLNIVNIAN_BANDIT("Bearded Pollnivnian bandit", Rarity.RARE, ItemID.PICKPOCKET_GUIDE_FEUD_ARABIAN_GUARD2, -1),
	FREMENNIK("Fremennik", Rarity.RARE, ItemID.PICKPOCKET_GUIDE_FREMENNIK_CITIZEN, -1),
	SORCERESS_S_GARDEN_MINIGAME_AUTUMNAL_KHARID_REGION("Sorceress's Garden minigame - AutumnAl Kharid region", Rarity.RARE, ItemID._100_OSMAN_AUTUMN_ICON, -1),
	CHESTS_IN_HEMENSTER_AND_RELLEKKA("Chests in Hemenster and Rellekka", Rarity.RARE, ItemID.STEEL_ARROWHEADS, -1),
	CAN_PICK_LOCKS_IN_PORT_PISCARILIUS("Can pick locks in Port Piscarilius", Rarity.RARE, ItemID.PISCARILIUS_STOLEN_PENDANT, -1),
	CROSSBOW_STALL("Crossbow stall", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BRONZE, -1),
	CAN_CRACK_THE_WALL_SAFES_IN_THE_ROGUES_DEN("Can crack the wall safes in the Rogues' Den", Rarity.RARE, ItemID.UNCUT_SAPPHIRE, -1),
	CAN_ROB_HOUSES_IN_CIVITAS_ILLA_FORTIS("Can rob houses in Civitas illa Fortis", Rarity.RARE, ItemID.VARLAMORE_THIEVING_HOUSE_KEY_2, -1),
	SILVER_STALL("Silver stall", Rarity.RARE, ItemID.SILVER_ORE, -1),
	WEALTHY_CITIZEN("Wealthy citizen", Rarity.RARE, ItemID.PICKPOCKET_GUIDE_WEALTHY_CITIZEN, -1),
	PYRAMID_PLUNDER_ROOM_4("Pyramid Plunder - Room 4", Rarity.RARE, ItemID.NTK_SEAL_STONE, -1),
	DORGESH_KAAN_AVERAGE_CHESTS("Dorgesh-Kaan average chests", Rarity.RARE, ItemID.OIL_LAMP_UNLIT, -1),
	DESERT_BANDIT("Desert bandit", Rarity.RARE, ItemID.PICKPOCKET_GUIDE_DESERT_BANDIT, -1),
	TARNISHED_PIRATE_CHESTS("Tarnished Pirate Chests", Rarity.RARE, ItemID.MCANNONBALL, -1),
	KNIGHT("Knight", Rarity.RARE, ItemID.PICKPOCKET_GUIDE_KNIGHT, -1),
	POLLNIVNIAN_BANDIT("Pollnivnian bandit", Rarity.RARE, ItemID.PICKPOCKET_GUIDE_FEUD_ARABIAN_GUARD1, -1),
	CAN_PICK_LOCK_THE_DOOR_TO_THE_GRUBBY_CHEST_IN_THE_FORTHOS_DUNGEON("Can pick-lock the door to the Grubby Chest in the Forthos Dungeon", Rarity.RARE, ItemID.HOSDUN_GRUBBY_KEY, -1),
	CHEST_IN_THE_CHAOS_DRUID_TOWER_NORTH_OF_ARDOUGNE("Chest in the Chaos Druid Tower north of Ardougne", Rarity.RARE, ItemID.BLOODRUNE, -1),
	PIRATE("Pirate", Rarity.EPIC, ItemID.PICKPOCKET_GUIDE_PIRATE, -1),
	PYRAMID_PLUNDER_ROOM_5("Pyramid Plunder - Room 5", Rarity.EPIC, ItemID.NTK_STATUETTE_STONE, -1),
	LIZARDMAN_TEMPLE_CHEST_BENEATH_MOLCH("Lizardman Temple chest beneath Molch", Rarity.EPIC, ItemID.XERIC_FABRIC, -1),
	MAGIC_STALL("Magic stall", Rarity.EPIC, ItemID.AIRRUNE, -1),
	MENAPHITE_THUG("Menaphite thug", Rarity.EPIC, ItemID.PICKPOCKET_GUIDE_FEUD_EGYPTIAN_DOORMAN, -1),
	SCIMITAR_STALL("Scimitar stall", Rarity.EPIC, ItemID.IRON_SCIMITAR, -1),
	SORCERESS_S_GARDEN_MINIGAME_SUMMERAL_KHARID_REGION("Sorceress's Garden minigame - SummerAl Kharid region", Rarity.EPIC, ItemID._100_OSMAN_SUMMER_ICON, -1),
	SPICES_STALL("Spices stall", Rarity.EPIC, ItemID.SPICESPOT, -1),
	WATCHMAN("Watchman", Rarity.EPIC, ItemID.PICKPOCKET_GUIDE_WATCHMAN, -1),
	HALLOWED_SEPULCHRE_CAN_PICK_LOCK_THE_COFFINS_FOUND_ON_EACH_FLOOR("Hallowed Sepulchre - Can pick-lock the coffins found on each floor", Rarity.EPIC, ItemID.HALLOWED_TOKEN, -1),
	PALADIN("Paladin", Rarity.EPIC, ItemID.PICKPOCKET_GUIDE_PALADIN, -1),
	PYRAMID_PLUNDER_ROOM_6("Pyramid Plunder - Room 6", Rarity.EPIC, ItemID.NTK_SEAL_GOLD, -1),
	ARDOUGNE_CASTLE_CHEST("Ardougne Castle chest", Rarity.EPIC, ItemID.RAW_SHARK, -1),
	GEM_STALL("Gem stall", Rarity.EPIC, ItemID.SAPPHIRE, -1),
	GNOME("Gnome", Rarity.EPIC, ItemID.PICKPOCKET_GUIDE_GNOME, -1),
	REINFORCED_PIRATE_CHESTS("Reinforced Pirate Chests", Rarity.EPIC, ItemID.ADAMANT_CANNONBALL, -1),
	DORGESH_KAAN_RICH_CHESTS("Dorgesh-Kaan rich chests", Rarity.EPIC, ItemID.UNCUT_SAPPHIRE, -1),
	HERO("Hero", Rarity.LEGENDARY, ItemID.PICKPOCKET_GUIDE_HERO, -1),
	PYRAMID_PLUNDER_ROOM_7("Pyramid Plunder - Room 7", Rarity.LEGENDARY, ItemID.NTK_SCARAB_GOLD, -1),
	ORE_STALL("Ore stall", Rarity.LEGENDARY, ItemID.RUNITE_ORE, -1),
	VYRE("Vyre", Rarity.LEGENDARY, ItemID.PICKPOCKET_GUIDE_VYRE, -1),
	WILDERNESS_ROGUES_CASTLE_CHEST("Wilderness Rogues' Castle chest", Rarity.LEGENDARY, ItemID.DRAGONSTONE, -1),
	ELF("Elf", Rarity.LEGENDARY, ItemID.PICKPOCKET_GUIDE_WOODELF, -1),
	CANNONBALL_STALL("Cannonball stall", Rarity.LEGENDARY, ItemID.MCANNONBALL, -1),
	TZHAAR("TzHaar", Rarity.LEGENDARY, ItemID.PICKPOCKET_GUIDE_TZHAAR, -1),
	PYRAMID_PLUNDER_ROOM_8("Pyramid Plunder - Room 8", Rarity.LEGENDARY, ItemID.NTK_STATUETTE_GOLD, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_THIEVING, -1);

	private final Card card;

	ThievingCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.THIEVING, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
