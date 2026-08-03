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

public enum ItemsCards implements CardGroup
{
	LOGS("Logs", Rarity.COMMON, ItemID.LOGS, -1),
	OAK_LOGS("Oak Logs", Rarity.COMMON, ItemID.OAK_LOGS, -1),
	WILLOW_LOGS("Willow Logs", Rarity.COMMON, ItemID.WILLOW_LOGS, -1),
	MAPLE_LOGS("Maple Logs", Rarity.COMMON, ItemID.MAPLE_LOGS, -1),
	COPPER_ORE("Copper Ore", Rarity.COMMON, ItemID.COPPER_ORE, -1),
	TIN_ORE("Tin Ore", Rarity.COMMON, ItemID.TIN_ORE, -1),
	IRON_ORE("Iron Ore", Rarity.COMMON, ItemID.IRON_ORE, -1),
	COAL("Coal", Rarity.COMMON, ItemID.COAL, -1),
	RAW_TROUT("Raw Trout", Rarity.COMMON, ItemID.RAW_TROUT, -1),
	RAW_SALMON("Raw Salmon", Rarity.COMMON, ItemID.RAW_SALMON, -1),
	BONES("Bones", Rarity.COMMON, ItemID.BONES, -1),
	CABBAGE("Cabbage", Rarity.COMMON, ItemID.CABBAGE, -1),
	BREAD("Bread", Rarity.COMMON, ItemID.BREAD, -1),
	TINDERBOX("Tinderbox", Rarity.COMMON, ItemID.TINDERBOX, -1),
	ROPE("Rope", Rarity.COMMON, ItemID.ROPE, -1),
	HAMMER("Hammer", Rarity.COMMON, ItemID.HAMMER, -1),
	CHISEL("Chisel", Rarity.COMMON, ItemID.CHISEL, -1),
	KNIFE("Knife", Rarity.COMMON, ItemID.KNIFE, -1),
	SPADE("Spade", Rarity.COMMON, ItemID.SPADE, -1),
	FEATHER("Feather", Rarity.COMMON, ItemID.FEATHER, -1),
	FLAX("Flax", Rarity.COMMON, ItemID.FLAX, -1),
	GRAIN("Grain", Rarity.COMMON, ItemID.GRAIN, -1),
	BUCKET_OF_SAND("Bucket of Sand", Rarity.COMMON, ItemID.BUCKET_SAND, -1),
	EMPTY_VIAL("Empty Vial", Rarity.COMMON, ItemID.VIAL_EMPTY, -1),
	CAKE("Cake", Rarity.COMMON, ItemID.CAKE, -1),
	BRONZE_DAGGER("Bronze Dagger", Rarity.COMMON, ItemID.BRONZE_DAGGER, -1),
	BRONZE_SWORD("Bronze Sword", Rarity.COMMON, ItemID.BRONZE_SWORD, -1),
	GUAM_LEAF("Guam Leaf", Rarity.COMMON, ItemID.GUAM_LEAF, -1),
	BIG_BONES("Big Bones", Rarity.COMMON, ItemID.BIG_BONES, -1),
	AIR_RUNE("Air Rune", Rarity.COMMON, ItemID.AIRRUNE, -1),
	WATER_RUNE("Water Rune", Rarity.COMMON, ItemID.WATERRUNE, -1),
	EARTH_RUNE("Earth Rune", Rarity.COMMON, ItemID.EARTHRUNE, -1),
	FIRE_RUNE("Fire Rune", Rarity.COMMON, ItemID.FIRERUNE, -1),
	MIND_RUNE("Mind Rune", Rarity.COMMON, ItemID.MINDRUNE, -1),
	BODY_RUNE("Body Rune", Rarity.COMMON, ItemID.BODYRUNE, -1),
	MARRENTILL("Marrentill", Rarity.COMMON, ItemID.MARENTILL, -1),
	TARROMIN("Tarromin", Rarity.COMMON, ItemID.TARROMIN, -1),
	RAW_SHRIMPS("Raw Shrimps", Rarity.COMMON, ItemID.RAW_SHRIMP, -1),
	RAW_ANCHOVIES("Raw Anchovies", Rarity.COMMON, ItemID.RAW_ANCHOVIES, -1),
	RAW_SARDINE("Raw Sardine", Rarity.COMMON, ItemID.RAW_SARDINE, -1),
	RAW_HERRING("Raw Herring", Rarity.COMMON, ItemID.RAW_HERRING, -1),
	RAW_MACKEREL("Raw Mackerel", Rarity.COMMON, ItemID.RAW_MACKEREL, -1),
	RAW_COD("Raw Cod", Rarity.COMMON, ItemID.RAW_COD, -1),
	RAW_PIKE("Raw Pike", Rarity.COMMON, ItemID.RAW_PIKE, -1),
	ZULRAH_S_SCALES("Zulrah's Scales", Rarity.COMMON, ItemID.SNAKEBOSS_SCALE, -1),
	YEW_LOGS("Yew Logs", Rarity.UNCOMMON, ItemID.YEW_LOGS, -1),
	MAGIC_LOGS("Magic Logs", Rarity.UNCOMMON, ItemID.MAGIC_LOGS, -1),
	REDWOOD_LOGS("Redwood Logs", Rarity.UNCOMMON, ItemID.REDWOOD_LOGS, -1),
	GOLD_ORE("Gold Ore", Rarity.UNCOMMON, ItemID.GOLD_ORE, -1),
	MITHRIL_ORE("Mithril Ore", Rarity.UNCOMMON, ItemID.MITHRIL_ORE, -1),
	ADAMANTITE_ORE("Adamantite Ore", Rarity.UNCOMMON, ItemID.ADAMANTITE_ORE, -1),
	RAW_LOBSTER("Raw Lobster", Rarity.UNCOMMON, ItemID.RAW_LOBSTER, -1),
	RAW_SWORDFISH("Raw Swordfish", Rarity.UNCOMMON, ItemID.RAW_SWORDFISH, -1),
	RANARR_WEED("Ranarr Weed", Rarity.UNCOMMON, ItemID.RANARR_WEED, -1),
	DRAGON_BONES("Dragon Bones", Rarity.UNCOMMON, ItemID.DRAGON_BONES, -1),
	IRON_SCIMITAR("Iron Scimitar", Rarity.UNCOMMON, ItemID.IRON_SCIMITAR, -1),
	STEEL_SCIMITAR("Steel Scimitar", Rarity.UNCOMMON, ItemID.STEEL_SCIMITAR, -1),
	MITHRIL_SCIMITAR("Mithril Scimitar", Rarity.UNCOMMON, ItemID.MITHRIL_SCIMITAR, -1),
	ADAMANT_SCIMITAR("Adamant Scimitar", Rarity.UNCOMMON, ItemID.ADAMANT_SCIMITAR, -1),
	CHAOS_RUNE("Chaos Rune", Rarity.UNCOMMON, ItemID.CHAOSRUNE, -1),
	COSMIC_RUNE("Cosmic Rune", Rarity.UNCOMMON, ItemID.COSMICRUNE, -1),
	NATURE_RUNE("Nature Rune", Rarity.UNCOMMON, ItemID.NATURERUNE, -1),
	LAW_RUNE("Law Rune", Rarity.UNCOMMON, ItemID.LAWRUNE, -1),
	DEATH_RUNE("Death Rune", Rarity.UNCOMMON, ItemID.DEATHRUNE, -1),
	MIST_RUNE("Mist Rune", Rarity.UNCOMMON, ItemID.MISTRUNE, -1),
	DUST_RUNE("Dust Rune", Rarity.UNCOMMON, ItemID.DUSTRUNE, -1),
	SMOKE_RUNE("Smoke Rune", Rarity.UNCOMMON, ItemID.SMOKERUNE, -1),
	STEAM_RUNE("Steam Rune", Rarity.UNCOMMON, ItemID.STEAMRUNE, -1),
	LAVA_RUNE("Lava Rune", Rarity.UNCOMMON, ItemID.LAVARUNE, -1),
	HARRALANDER("Harralander", Rarity.UNCOMMON, ItemID.HARRALANDER, -1),
	TOADFLAX("Toadflax", Rarity.UNCOMMON, ItemID.TOADFLAX, -1),
	IRIT_LEAF("Irit Leaf", Rarity.UNCOMMON, ItemID.IRIT_LEAF, -1),
	AVANTOE("Avantoe", Rarity.UNCOMMON, ItemID.AVANTOE, -1),
	KWUARM("Kwuarm", Rarity.UNCOMMON, ItemID.KWUARM, -1),
	RAW_TUNA("Raw Tuna", Rarity.UNCOMMON, ItemID.RAW_TUNA, -1),
	RAW_BASS("Raw Bass", Rarity.UNCOMMON, ItemID.RAW_BASS, -1),
	RAW_MONKFISH("Raw Monkfish", Rarity.UNCOMMON, ItemID.RAW_MONKFISH, -1),
	RAW_KARAMBWAN("Raw Karambwan", Rarity.UNCOMMON, ItemID.TBWT_RAW_KARAMBWAN, -1),
	GRANITE_HAMMER("Granite Hammer", Rarity.UNCOMMON, ItemID.GRANITE_HAMMER, -1),
	SMOKE_BATTLESTAFF("Smoke Battlestaff", Rarity.UNCOMMON, ItemID.SMOKE_BATTLESTAFF, -1),
	RUNITE_ORE("Runite Ore", Rarity.RARE, ItemID.RUNITE_ORE, -1),
	RAW_SHARK("Raw Shark", Rarity.RARE, ItemID.RAW_SHARK, -1),
	SHARK("Shark", Rarity.RARE, ItemID.SHARK, -1),
	RAW_ANGLERFISH("Raw Anglerfish", Rarity.RARE, ItemID.RAW_ANGLERFISH, -1),
	SNAPDRAGON("Snapdragon", Rarity.RARE, ItemID.SNAPDRAGON, -1),
	TORSTOL("Torstol", Rarity.RARE, ItemID.TORSTOL, -1),
	AMETHYST("Amethyst", Rarity.RARE, ItemID.AMETHYST, -1),
	RUNE_SCIMITAR("Rune Scimitar", Rarity.RARE, ItemID.RUNE_SCIMITAR, -1),
	DRAGON_SCIMITAR("Dragon Scimitar", Rarity.RARE, ItemID.DRAGON_SCIMITAR, -1),
	GRANITE_MAUL("Granite Maul", Rarity.RARE, ItemID.GRANITE_MAUL, -1),
	DRAGON_PICKAXE("Dragon Pickaxe", Rarity.RARE, ItemID.DRAGON_PICKAXE, -1),
	OCCULT_NECKLACE("Occult Necklace", Rarity.RARE, ItemID.OCCULT_NECKLACE, -1),
	CRYSTAL_BOW("Crystal Bow", Rarity.RARE, ItemID.CRYSTAL_BOW, -1),
	MUD_RUNE("Mud Rune", Rarity.RARE, ItemID.MUDRUNE, -1),
	BLOOD_RUNE("Blood Rune", Rarity.RARE, ItemID.BLOODRUNE, -1),
	SOUL_RUNE("Soul Rune", Rarity.RARE, ItemID.SOULRUNE, -1),
	ASTRAL_RUNE("Astral Rune", Rarity.RARE, ItemID.ASTRALRUNE, -1),
	WRATH_RUNE("Wrath Rune", Rarity.RARE, ItemID.WRATHRUNE, -1),
	HUASCA("Huasca", Rarity.RARE, ItemID.HUASCA, -1),
	CADANTINE("Cadantine", Rarity.RARE, ItemID.CADANTINE, -1),
	LANTADYME("Lantadyme", Rarity.RARE, ItemID.LANTADYME, -1),
	DWARF_WEED("Dwarf Weed", Rarity.RARE, ItemID.DWARF_WEED, -1),
	RAW_SEA_TURTLE("Raw Sea Turtle", Rarity.RARE, ItemID.RAW_SEATURTLE, -1),
	RAW_MANTA_RAY("Raw Manta Ray", Rarity.RARE, ItemID.RAW_MANTARAY, -1),
	RAW_DARK_CRAB("Raw Dark Crab", Rarity.RARE, ItemID.RAW_DARK_CRAB, -1),
	DEXTEROUS_PRAYER_SCROLL("Dexterous Prayer Scroll", Rarity.RARE, ItemID.RAIDS_PRAYERSCROLL, -1),
	ARCANE_PRAYER_SCROLL("Arcane Prayer Scroll", Rarity.RARE, ItemID.RAIDS_PRAYERSCROLL_AUGURY, -1),
	SARADOMIN_SWORD("Saradomin Sword", Rarity.RARE, ItemID.SARADOMIN_SWORD, -1),
	ZAMORAKIAN_SPEAR("Zamorakian Spear", Rarity.RARE, ItemID.ZAMORAK_SPEAR, -1),
	ABYSSAL_DAGGER("Abyssal Dagger", Rarity.RARE, ItemID.ABYSSAL_DAGGER, -1),
	KRAKEN_TENTACLE("Kraken Tentacle", Rarity.RARE, ItemID.KRAKEN_TENTACLE, -1),
	HYDRA_TAIL("Hydra Tail", Rarity.RARE, ItemID.HYDRA_TAIL, -1),
	HYDRA_LEATHER("Hydra Leather", Rarity.RARE, ItemID.HYDRA_LEATHER, -1),
	SARACHNIS_CUDGEL("Sarachnis Cudgel", Rarity.RARE, ItemID.SARACHNIS_CUDGEL, -1),
	SMOULDERING_STONE("Smouldering Stone", Rarity.RARE, ItemID.SMOULDERING_STONE, -1),
	SERPENTINE_VISAGE("Serpentine Visage", Rarity.RARE, ItemID.SERPENTINE_VISAGE, -1),
	TANZANITE_FANG("Tanzanite Fang", Rarity.RARE, ItemID.BLOWPIPE_FANG, -1),
	MAGIC_FANG("Magic Fang", Rarity.RARE, ItemID.MAGIC_FANG, -1),
	DRAGON_HARPOON("Dragon Harpoon", Rarity.RARE, ItemID.DRAGON_HARPOON, -1),
	TOME_OF_FIRE("Tome of Fire", Rarity.RARE, ItemID.TOME_OF_FIRE, -1),
	TOME_OF_WATER("Tome of Water", Rarity.RARE, ItemID.TOME_OF_WATER, -1),
	RING_OF_THE_GODS("Ring of the Gods", Rarity.RARE, ItemID.ROTG, -1),
	TREASONOUS_RING("Treasonous Ring", Rarity.RARE, ItemID.SHARP_RING, -1),
	TYRANNICAL_RING("Tyrannical Ring", Rarity.RARE, ItemID.HEAVY_RING, -1),
	ABYSSAL_WHIP("Abyssal Whip", Rarity.EPIC, ItemID.ABYSSAL_WHIP, -1),
	DRAGON_CLAWS("Dragon Claws", Rarity.EPIC, ItemID.DRAGON_CLAWS, -1),
	TOXIC_BLOWPIPE("Toxic Blowpipe", Rarity.EPIC, ItemID.TOXIC_BLOWPIPE, -1),
	ARMADYL_CHESTPLATE("Armadyl Chestplate", Rarity.EPIC, ItemID.ARMADYL_CHESTPLATE, -1),
	BANDOS_CHESTPLATE("Bandos Chestplate", Rarity.EPIC, ItemID.BANDOS_CHESTPLATE, -1),
	PRIMORDIAL_BOOTS("Primordial Boots", Rarity.EPIC, ItemID.PRIMORDIAL_BOOTS, -1),
	ETERNAL_BOOTS("Eternal Boots", Rarity.EPIC, ItemID.ETERNAL_BOOTS, -1),
	PEGASIAN_BOOTS("Pegasian Boots", Rarity.EPIC, ItemID.PEGASIAN_BOOTS, -1),
	SANGUINESTI_STAFF("Sanguinesti Staff", Rarity.EPIC, ItemID.SANGUINESTI_STAFF, -1),
	KODAI_WAND("Kodai Wand", Rarity.EPIC, ItemID.KODAI_WAND, -1),
	ANCESTRAL_HAT("Ancestral Hat", Rarity.EPIC, ItemID.ANCESTRAL_HAT, -1),
	INFERNAL_CAPE("Infernal Cape", Rarity.EPIC, ItemID.INFERNAL_CAPE, -1),
	SUNFIRE_RUNE("Sunfire Rune", Rarity.EPIC, ItemID.SUNFIRERUNE, -1),
	AETHER_RUNE("Aether Rune", Rarity.EPIC, ItemID.AETHERRUNE, -1),
	UNCUT_ONYX("Uncut Onyx", Rarity.EPIC, ItemID.UNCUT_ONYX, -1),
	TWISTED_BUCKLER("Twisted Buckler", Rarity.EPIC, ItemID.TWISTED_BUCKLER, -1),
	DRAGON_HUNTER_CROSSBOW("Dragon Hunter Crossbow", Rarity.EPIC, ItemID.DRAGONHUNTER_XBOW, -1),
	DINH_S_BULWARK("Dinh's Bulwark", Rarity.EPIC, ItemID.DINHS_BULWARK, -1),
	ANCESTRAL_ROBE_TOP("Ancestral Robe Top", Rarity.EPIC, ItemID.ANCESTRAL_ROBE_TOP, -1),
	JUSTICIAR_FACEGUARD("Justiciar Faceguard", Rarity.EPIC, ItemID.JUSTICIAR_FACEGUARD, -1),
	JUSTICIAR_CHESTGUARD("Justiciar Chestguard", Rarity.EPIC, ItemID.JUSTICIAR_CHESTGUARD, -1),
	AVERNIC_DEFENDER("Avernic Defender", Rarity.EPIC, ItemID.INFERNAL_DEFENDER, -1),
	OSMUMTEN_S_FANG("Osmumten's Fang", Rarity.EPIC, ItemID.OSMUMTENS_FANG, -1),
	LIGHTBEARER("Lightbearer", Rarity.EPIC, ItemID.LIGHTBEARER, -1),
	ELIDINIS_WARD("Elidinis' Ward", Rarity.EPIC, ItemID.ELIDINIS_WARD, -1),
	MASORI_MASK("Masori Mask", Rarity.EPIC, ItemID.MASORI_MASK, -1),
	MASORI_BODY("Masori Body", Rarity.EPIC, ItemID.MASORI_BODY, -1),
	ARMADYL_CROSSBOW("Armadyl Crossbow", Rarity.EPIC, ItemID.ACB, -1),
	STAFF_OF_THE_DEAD("Staff of the Dead", Rarity.EPIC, ItemID.SOTD, -1),
	BANDOS_TASSETS("Bandos Tassets", Rarity.EPIC, ItemID.BANDOS_SKIRT, -1),
	ARMADYL_HELMET("Armadyl Helmet", Rarity.EPIC, ItemID.ARMADYL_HELMET, -1),
	ZARYTE_VAMBRACES("Zaryte Vambraces", Rarity.EPIC, ItemID.ZARYTE_VAMBRACES, -1),
	VIRTUS_MASK("Virtus Mask", Rarity.EPIC, ItemID.VIRTUS_MASK, -1),
	VIRTUS_ROBE_TOP("Virtus Robe Top", Rarity.EPIC, ItemID.VIRTUS_TOP, -1),
	NIGHTMARE_STAFF("Nightmare Staff", Rarity.EPIC, ItemID.NIGHTMARE_STAFF, -1),
	VOLATILE_ORB("Volatile Orb", Rarity.EPIC, ItemID.VOLATILE_ORB, -1),
	ELDRITCH_ORB("Eldritch Orb", Rarity.EPIC, ItemID.ELDRITCH_ORB, -1),
	MAGUS_VESTIGE("Magus Vestige", Rarity.EPIC, ItemID.MAGUS_VESTIGE, -1),
	VENATOR_VESTIGE("Venator Vestige", Rarity.EPIC, ItemID.VENATOR_VESTIGE, -1),
	BELLATOR_VESTIGE("Bellator Vestige", Rarity.EPIC, ItemID.BELLATOR_VESTIGE, -1),
	AWAKENER_S_ORB("Awakener's Orb", Rarity.EPIC, ItemID.DT2_AWAKENERS_ORB, -1),
	ARAXYTE_FANG("Araxyte Fang", Rarity.EPIC, ItemID.ARAXYTE_FANG, -1),
	AMULET_OF_RANCOUR("Amulet of Rancour", Rarity.EPIC, ItemID.AMULET_OF_RANCOUR, -1),
	ABYSSAL_BLUDGEON("Abyssal Bludgeon", Rarity.EPIC, ItemID.ABYSSAL_BLUDGEON, -1),
	HYDRA_S_CLAW("Hydra's Claw", Rarity.EPIC, ItemID.HYDRA_CLAW, -1),
	PRIMORDIAL_CRYSTAL("Primordial Crystal", Rarity.EPIC, ItemID.PRIMORDIAL_CRYSTAL, -1),
	PEGASIAN_CRYSTAL("Pegasian Crystal", Rarity.EPIC, ItemID.PEGASIAN_CRYSTAL, -1),
	ETERNAL_CRYSTAL("Eternal Crystal", Rarity.EPIC, ItemID.ETERNAL_CRYSTAL, -1),
	DRACONIC_VISAGE("Draconic Visage", Rarity.EPIC, ItemID.DRAGONFIRE_VISAGE, -1),
	ELDER_MAUL("Elder Maul", Rarity.LEGENDARY, ItemID.ELDER_MAUL, -1),
	GHRAZI_RAPIER("Ghrazi Rapier", Rarity.LEGENDARY, ItemID.GHRAZI_RAPIER, -1),
	INQUISITOR_S_MACE("Inquisitor's Mace", Rarity.LEGENDARY, ItemID.INQUISITORS_MACE, -1),
	VOIDWAKER("Voidwaker", Rarity.LEGENDARY, ItemID.VOIDWAKER, -1),
	TWISTED_BOW("Twisted Bow", Rarity.LEGENDARY, ItemID.TWISTED_BOW, -1),
	SCYTHE_OF_VITUR("Scythe of Vitur", Rarity.LEGENDARY, ItemID.SCYTHE_OF_VITUR, -1),
	TUMEKEN_S_SHADOW("Tumeken's Shadow", Rarity.LEGENDARY, ItemID.TUMEKENS_SHADOW, -1),
	TORVA_FULL_HELM("Torva Full Helm", Rarity.LEGENDARY, ItemID.TORVA_HELM, -1),
	TORVA_PLATEBODY("Torva Platebody", Rarity.LEGENDARY, ItemID.TORVA_CHEST, -1),
	HARMONISED_ORB("Harmonised Orb", Rarity.LEGENDARY, ItemID.HARMONISED_ORB, -1),
	ULTOR_VESTIGE("Ultor Vestige", Rarity.LEGENDARY, ItemID.ULTOR_VESTIGE, -1),
	NOXIOUS_HALBERD("Noxious Halberd", Rarity.LEGENDARY, ItemID.NOXIOUS_HALBERD, -1),
	ELYSIAN_SIGIL("Elysian Sigil", Rarity.LEGENDARY, ItemID.ELYSIAN_SIGIL, -1);

	private final Card card;

	ItemsCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.ITEMS, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
