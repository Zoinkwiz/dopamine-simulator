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

public enum HerbloreCards implements CardGroup
{
	ATTACK_POTION("Attack potion", Rarity.COMMON, ItemID.EYE_OF_NEWT, -1),
	GUAM_LEAF("Guam leaf", Rarity.COMMON, ItemID.GUAM_LEAF, -1),
	ROGUE_S_PURSE("Rogue's purse", Rarity.COMMON, ItemID.ROGUES_PURSE, -1),
	SNAKE_WEED("Snake weed", Rarity.COMMON, ItemID.SNAKE_WEED, -1),
	ATTACK_MIX("Attack mix", Rarity.COMMON, ItemID.BRUTAL_2DOSE1ATTACK, -1),
	ANTI_POISON_POTION("Anti-poison potion", Rarity.COMMON, ItemID.UNICORN_HORN_DUST, -1),
	MARRENTILL("Marrentill", Rarity.COMMON, ItemID.MARENTILL, -1),
	ANTIPOISON_MIX("Antipoison mix", Rarity.COMMON, ItemID.BRUTAL_2DOSEANTIPOISON, -1),
	RELICYM_S_BALM("Relicym's balm", Rarity.COMMON, ItemID.ROGUES_PURSE, -1),
	RELICYM_S_MIX("Relicym's mix", Rarity.COMMON, ItemID.BRUTAL_RELICYMS_BALM2, -1),
	TARROMIN("Tarromin", Rarity.COMMON, ItemID.TARROMIN, -1),
	STRENGTH_POTION("Strength potion", Rarity.COMMON, ItemID.LIMPWURT_ROOT, -1),
	STRENGTH_MIX("Strength mix", Rarity.COMMON, ItemID.BRUTAL_2DOSE1STRENGTH, -1),
	SERUM_207TARROMIN_ASHES("Serum 207Tarromin & ashes", Rarity.COMMON, ItemID.ASHES, -1),
	GUAM_TAR("Guam tar", Rarity.COMMON, ItemID.SALAMANDER_TAR_GREEN, -1),
	HARRALANDER("Harralander", Rarity.UNCOMMON, ItemID.HARRALANDER, -1),
	COMPOST_POTION("Compost potion", Rarity.UNCOMMON, ItemID.FOSSIL_VOLCANIC_ASH, -1),
	GUTHIX_BALANCE_POTION("Guthix balance potion", Rarity.UNCOMMON, ItemID.GARLIC, -1),
	STAT_RESTORE_POTION("Stat restore potion", Rarity.UNCOMMON, ItemID.RED_SPIDERS_EGGS, -1),
	RESTORE_MIX("Restore mix", Rarity.UNCOMMON, ItemID.BRUTAL_2DOSESTATRESTORE, -1),
	RANARR_WEED("Ranarr weed", Rarity.UNCOMMON, ItemID.RANARR_WEED, -1),
	ENERGY_POTION("Energy potion", Rarity.UNCOMMON, ItemID.CHOCOLATE_DUST, -1),
	ENERGY_MIX("Energy mix", Rarity.UNCOMMON, ItemID.BRUTAL_2DOSE1ENERGY, -1),
	DEFENCE_POTION("Defence potion", Rarity.UNCOMMON, ItemID.WHITE_BERRIES, -1),
	TOADFLAX("Toadflax", Rarity.UNCOMMON, ItemID.TOADFLAX, -1),
	HERBIBOAR("Herbiboar", Rarity.UNCOMMON, ItemID.HUNTGUIDE_HERBIBOAR, -1),
	MARRENTILL_TAR("Marrentill tar", Rarity.UNCOMMON, ItemID.SALAMANDER_TAR_ORANGE, -1),
	DEFENCE_MIX("Defence mix", Rarity.UNCOMMON, ItemID.BRUTAL_2DOSE1DEFENSE, -1),
	AGILITY_POTION("Agility potion", Rarity.UNCOMMON, ItemID.TOADS_LEGS, -1),
	COMBAT_POTION("Combat potion", Rarity.UNCOMMON, ItemID.GROUND_DESERT_GOAT_HORN, -1),
	AGILITY_MIX("Agility mix", Rarity.UNCOMMON, ItemID.BRUTAL_2DOSE1AGILITY, -1),
	MOONLIGHT_POTION("Moonlight Potion", Rarity.UNCOMMON, ItemID.MOONLIGHT_GRUB, -1),
	PRAYER_RESTORE_POTION("Prayer restore potion", Rarity.UNCOMMON, ItemID.SNAPE_GRASS, -1),
	TARROMIN_TAR("Tarromin tar", Rarity.UNCOMMON, ItemID.SALAMANDER_TAR_RED, -1),
	COMBAT_MIX("Combat mix", Rarity.RARE, ItemID.BRUTAL_2DOSECOMBAT, -1),
	IRIT_LEAF("Irit leaf", Rarity.RARE, ItemID.IRIT_LEAF, -1),
	PRAYER_MIX("Prayer mix", Rarity.RARE, ItemID.BRUTAL_2DOSEPRAYERRESTORE, -1),
	HARRALANDER_TAR("Harralander tar", Rarity.RARE, ItemID.SALAMANDER_TAR_BLACK, -1),
	SUPER_ATTACK_POTION("Super attack potion", Rarity.RARE, ItemID.EYE_OF_NEWT, -1),
	ELDER_MINUS_GOLPAR_STINKHORN_MUSHROOM("Elder (-)Golpar & Stinkhorn mushroom", Rarity.RARE, ItemID.RAIDS_VIAL_ELDER_WEAK_4, -1),
	KODAI_MINUS_GOLPAR_ENDARKENED_JUICE("Kodai (-)Golpar & Endarkened juice", Rarity.RARE, ItemID.RAIDS_VIAL_KODAI_WEAK_4, -1),
	SUPER_ATTACK_MIX("Super attack mix", Rarity.RARE, ItemID.BRUTAL_2DOSE2ATTACK, -1),
	TWISTED_MINUS_GOLPAR_CICELY("Twisted (-)Golpar & Cicely", Rarity.RARE, ItemID.RAIDS_VIAL_TWISTED_WEAK_4, -1),
	AVANTOE("Avantoe", Rarity.RARE, ItemID.AVANTOE, -1),
	SUPER_ANTI_POISON_POTION("Super anti-poison potion", Rarity.RARE, ItemID.UNICORN_HORN_DUST, -1),
	ANTI_ODOUR_SALT("Anti-odour salt", Rarity.RARE, ItemID.ANTI_ODOUR_SALT, -1),
	FISHING_POTION("Fishing potion", Rarity.RARE, ItemID.SNAPE_GRASS, -1),
	ANTI_POISON_SUPERMIX("Anti-poison supermix", Rarity.RARE, ItemID.BRUTAL_2DOSE2ANTIPOISON, -1),
	PRAYER_ENHANCE_MINUS_BUCHU_LEAF_CICELY("Prayer enhance (-)Buchu leaf & Cicely", Rarity.RARE, ItemID.RAIDS_VIAL_PRAYER_WEAK_4, -1),
	REVITALISATION_MINUS_BUCHU_LEAF_STINKHORN_MUSHROOM("Revitalisation (-)Buchu leaf & Stinkhorn mushroom", Rarity.RARE, ItemID.RAIDS_VIAL_REVITALISATION_WEAK_4, -1),
	SUPER_ENERGY_POTION("Super energy potion", Rarity.RARE, ItemID.MORTMYREMUSHROOM, -1),
	XERIC_S_AID_MINUS_BUCHU_LEAF_ENDARKENED_JUICE("Xeric's Aid (-)Buchu leaf & Endarkened juice", Rarity.RARE, ItemID.RAIDS_VIAL_XERICAID_WEAK_4, -1),
	FISHING_MIX("Fishing mix", Rarity.RARE, ItemID.BRUTAL_2DOSEFISHERSPOTION, -1),
	HUNTER_POTION("Hunter potion", Rarity.RARE, ItemID.HUNTINGBEAST_SABRETEETH, -1),
	GOADING_POTION("Goading potion", Rarity.RARE, ItemID.ALDARIUM, -1),
	KWUARM("Kwuarm", Rarity.RARE, ItemID.KWUARM, -1),
	IRIT_TAR("Irit tar", Rarity.RARE, ItemID.SALAMANDER_TAR_MOUNTAIN, -1),
	SUPER_STRENGTH_POTION("Super strength potion", Rarity.RARE, ItemID.LIMPWURT_ROOT, -1),
	HAEMOSTATIC_DRESSING("Haemostatic dressing", Rarity.RARE, ItemID.SQUID_PASTE, -1),
	SUPER_ENERGY_MIX("Super energy mix", Rarity.RARE, ItemID.BRUTAL_2DOSE2ENERGY, -1),
	MAGIC_ESSENCE_POTION("Magic essence potion", Rarity.RARE, ItemID.FAIRYTALE2_GORAK_CLAWS, -1),
	HERB_SACK("Herb Sack", Rarity.RARE, ItemID.SLAYER_HERB_SACK, -1),
	HUASCA("Huasca", Rarity.RARE, ItemID.HUASCA, -1),
	HUNTING_MIX("Hunting mix", Rarity.RARE, ItemID.BRUTAL_2DOSE1HUNTING, -1),
	PRAYER_REGENERATION_POTION("Prayer regeneration potion", Rarity.RARE, ItemID.ALDARIUM, -1),
	ELDER("Elder", Rarity.RARE, ItemID.RAIDS_VIAL_ELDER_4, -1),
	KODAI("Kodai", Rarity.RARE, ItemID.RAIDS_VIAL_KODAI_4, -1),
	SNAPDRAGON("Snapdragon", Rarity.RARE, ItemID.SNAPDRAGON, -1),
	SUPER_STRENGTH_MIX("Super strength mix", Rarity.RARE, ItemID.BRUTAL_2DOSE2STRENGTH, -1),
	TWISTED("Twisted", Rarity.RARE, ItemID.RAIDS_VIAL_TWISTED_4, -1),
	ALCO_AUGMENTATOR("Alco-augmentator", Rarity.EPIC, ItemID.MM_POTION_AAA_UNFINISHED, -1),
	ANTIPOISON_MINUS_NOXIFER_CICELY("Antipoison (-)Noxifer & Cicely", Rarity.EPIC, ItemID.RAIDS_VIAL_ANTIPOISON_WEAK_4, -1),
	LIPLACK_LIQUOR("Liplack liquor", Rarity.EPIC, ItemID.MM_POTION_LLL_UNFINISHED, -1),
	MAMMOTH_MIGHT_MIX("Mammoth-might mix", Rarity.EPIC, ItemID.MM_POTION_MMM_UNFINISHED, -1),
	OVERLOAD_MINUS_NOXIFER_ELDER_MINUS_TWISTED_MINUS_KODAI_MINUS("Overload (-)Noxifer, Elder (-), Twisted (-) & Kodai (-)", Rarity.EPIC, ItemID.RAIDS_VIAL_OVERLOAD_WEAK_4, -1),
	WEAPON_POISON("Weapon poison", Rarity.EPIC, ItemID.DRAGON_SCALE_DUST, -1),
	MAGIC_ESSENCE_MIX("Magic essence mix", Rarity.EPIC, ItemID.BRUTAL_2DOSEMAGICESS, -1),
	SUPER_FISHING_POTION("Super fishing potion", Rarity.EPIC, ItemID.HADDOCK_EYE, -1),
	MYSTIC_MANA_AMALGAM("Mystic mana amalgam", Rarity.EPIC, ItemID.MM_POTION_MMA_UNFINISHED, -1),
	SUPER_RESTORE_POTION("Super restore potion", Rarity.EPIC, ItemID.RED_SPIDERS_EGGS, -1),
	CADANTINE("Cadantine", Rarity.EPIC, ItemID.CADANTINE, -1),
	PRAYER_ENHANCE("Prayer enhance", Rarity.EPIC, ItemID.RAIDS_VIAL_PRAYER_4, -1),
	REVITALISATION("Revitalisation", Rarity.EPIC, ItemID.RAIDS_VIAL_REVITALISATION_4, -1),
	SANFEW_SERUM("Sanfew serum", Rarity.EPIC, ItemID.NAIL_BEAST_NAIL, -1),
	XERIC_S_AID("Xeric's Aid", Rarity.EPIC, ItemID.RAIDS_VIAL_XERICAID_4, -1),
	EXTREME_ENERGY_POTION("Extreme energy potion", Rarity.EPIC, ItemID.YELLOW_FIN, -1),
	MARLEY_S_MOONLIGHT("Marley's moonlight", Rarity.EPIC, ItemID.MM_POTION_MML_UNFINISHED, -1),
	SUPER_DEFENCE_POTION("Super defence potion", Rarity.EPIC, ItemID.WHITE_BERRIES, -1),
	LANTADYME("Lantadyme", Rarity.EPIC, ItemID.LANTADYME, -1),
	SUPER_HUNTER_POTION("Super hunter potion", Rarity.EPIC, ItemID.CRAB_PASTE, -1),
	SUPER_RESTORE_MIX("Super restore mix", Rarity.EPIC, ItemID.BRUTAL_2DOSE2RESTORE, -1),
	ANTIDOTE_COCONUT_MILK_TOADFLAX_YEW_ROOTS("Antidote+Coconut milk, toadflax & yew roots", Rarity.EPIC, ItemID.YEW_ROOTS, -1),
	ANTI_FIREBREATH_POTION("Anti-firebreath potion", Rarity.EPIC, ItemID.DRAGON_SCALE_DUST, -1),
	AZURE_AURA_MIX("Azure aura mix", Rarity.EPIC, ItemID.MM_POTION_AAM_UNFINISHED, -1),
	DIVINE_SUPER_ATTACK_POTION("Divine super attack potion", Rarity.EPIC, ItemID.PRIF_CRYSTAL_SHARD_CRUSHED, -1),
	DIVINE_SUPER_DEFENCE_POTION("Divine super defence potion", Rarity.EPIC, ItemID.PRIF_CRYSTAL_SHARD_CRUSHED, -1),
	DIVINE_SUPER_STRENGTH_POTION("Divine super strength potion", Rarity.EPIC, ItemID.PRIF_CRYSTAL_SHARD_CRUSHED, -1),
	DWARF_WEED("Dwarf weed", Rarity.EPIC, ItemID.DWARF_WEED, -1),
	ELDER_PLUS_GOLPAR_STINKHORN_MUSHROOM("Elder (+)Golpar & Stinkhorn mushroom", Rarity.EPIC, ItemID.RAIDS_VIAL_ELDER_4, -1),
	KODAI_PLUS_GOLPAR_ENDARKENED_JUICE("Kodai (+)Golpar & Endarkened juice", Rarity.EPIC, ItemID.RAIDS_VIAL_KODAI_4, -1),
	TWISTED_PLUS_GOLPAR_CICELY("Twisted (+)Golpar & Cicely", Rarity.EPIC, ItemID.RAIDS_VIAL_TWISTED_4, -1),
	SUPER_DEFENCE_MIX("Super defence mix", Rarity.EPIC, ItemID.BRUTAL_2DOSE2DEFENSE, -1),
	AQUALUX_AMALGAM("Aqualux amalgam", Rarity.EPIC, ItemID.MM_POTION_AAL_UNFINISHED, -1),
	RANGING_POTION("Ranging potion", Rarity.EPIC, ItemID.WINE_OF_ZAMORAK, -1),
	WEAPON_POISON_PLUS_COCONUT_MILK_CACTUS_SPINE_RED_SPIDERS_EGGS("Weapon poison(+)Coconut milk, cactus spine & red spiders' eggs", Rarity.EPIC, ItemID.CACTUS_SPINE, -1),
	ANTIDOTE_MIX("Antidote+ mix", Rarity.EPIC, ItemID.BRUTAL_ANTIDOTE_2, -1),
	DIVINE_RANGING_POTION("Divine ranging potion", Rarity.EPIC, ItemID.PRIF_CRYSTAL_SHARD_CRUSHED, -1),
	ANTIFIRE_MIX("Antifire mix", Rarity.EPIC, ItemID.BRUTAL_2DOSE1ANTIDRAGON, -1),
	ANTIPOISON("Antipoison", Rarity.EPIC, ItemID.RAIDS_VIAL_ANTIPOISON_4, -1),
	MEGALITE_LIQUID("Megalite liquid", Rarity.EPIC, ItemID.MM_POTION_LLM_UNFINISHED, -1),
	OVERLOAD("Overload", Rarity.EPIC, ItemID.RAIDS_VIAL_OVERLOAD_4, -1),
	TORSTOL("Torstol", Rarity.EPIC, ItemID.TORSTOL, -1),
	MAGIC_POTION("Magic potion", Rarity.EPIC, ItemID.CACTUS_POTATO, -1),
	STAMINA_POTION("Stamina potion", Rarity.EPIC, ItemID.CERT_REINITIALISATION_05_INACTIVE, -1),
	ANTI_LEECH_LOTION("Anti-leech lotion", Rarity.EPIC, ItemID.MM_POTION_LLA_UNFINISHED, -1),
	DIVINE_MAGIC_POTION("Divine magic potion", Rarity.EPIC, ItemID.PRIF_CRYSTAL_SHARD_CRUSHED, -1),
	PRAYER_ENHANCE_PLUS_BUCHU_LEAF_CICELY("Prayer enhance (+)Buchu leaf & Cicely", Rarity.EPIC, ItemID.RAIDS_VIAL_PRAYER_STRONG_4, -1),
	REVITALISATION_PLUS_BUCHU_LEAF_STINKHORN_MUSHROOM("Revitalisation (+)Buchu leaf & Stinkhorn mushroom", Rarity.EPIC, ItemID.RAIDS_VIAL_REVITALISATION_STRONG_4, -1),
	XERIC_S_AID_PLUS_BUCHU_LEAF_ENDARKENED_JUICE("Xeric's Aid (+)Buchu leaf & Endarkened juice", Rarity.EPIC, ItemID.RAIDS_VIAL_XERICAID_STRONG_4, -1),
	ZAMORAK_BREW("Zamorak brew", Rarity.EPIC, ItemID.JANGERBERRIES, -1),
	ANTIDOTE_COCONUT_MILK_IRIT_LEAF_MAGIC_TREE_ROOTS("Antidote++Coconut milk, irit leaf & magic tree roots", Rarity.EPIC, ItemID.MAGIC_ROOTS, -1),
	BASTION_POTION("Bastion potion", Rarity.LEGENDARY, ItemID.WINE_OF_ZAMORAK, -1),
	BATTLEMAGE_POTION("Battlemage potion", Rarity.LEGENDARY, ItemID.CACTUS_POTATO, -1),
	RANGING_MIX("Ranging mix", Rarity.LEGENDARY, ItemID.BRUTAL_2DOSERANGERSPOTION, -1),
	MIXALOT("Mixalot", Rarity.LEGENDARY, ItemID.MM_POTION_MAL_UNFINISHED, -1),
	SARADOMIN_BREW("Saradomin brew", Rarity.LEGENDARY, ItemID.CRUSHED_BIRD_NEST, -1),
	SECONDARY_POUCH("Secondary Pouch", Rarity.LEGENDARY, ItemID.MM_SECONDARY_POUCH, -1),
	SURGE_POTION("Surge potion", Rarity.LEGENDARY, ItemID.DEMONIC_TALLOW, -1),
	WEAPON_POISON_COCONUT_MILK_NIGHTSHADE_POISON_IVY_BERRIES("Weapon poison(++)Coconut milk, nightshade & poison ivy berries", Rarity.LEGENDARY, ItemID.POISONIVY_BERRIES, -1),
	MAGIC_MIX("Magic mix", Rarity.LEGENDARY, ItemID.BRUTAL_2DOSE1MAGIC, -1),
	EXTENDED_ANTIFIRE_POTION("Extended antifire potion", Rarity.LEGENDARY, ItemID.CERT_PICKPOCKET_GUIDE_PALADIN, -1),
	ANCIENT_BREW("Ancient brew", Rarity.LEGENDARY, ItemID.NIHIL_DUST, -1),
	EXTENDED_STAMINA_POTION("Extended stamina potion", Rarity.LEGENDARY, ItemID.YELLOW_FIN, -1),
	ZAMORAK_MIX("Zamorak mix", Rarity.LEGENDARY, ItemID.BRUTAL_2DOSEPOTIONOFZAMORAK, -1),
	DIVINE_BASTION_POTION("Divine bastion potion", Rarity.LEGENDARY, ItemID.PRIF_CRYSTAL_SHARD_CRUSHED, -1),
	DIVINE_BATTLEMAGE_POTION("Divine battlemage potion", Rarity.LEGENDARY, ItemID.PRIF_CRYSTAL_SHARD_CRUSHED, -1),
	STAMINA_MIX("Stamina mix", Rarity.LEGENDARY, ItemID.BRUTAL_2DOSESTAMINA, -1),
	ANTI_VENOM("Anti-venom", Rarity.LEGENDARY, ItemID.CERT_REINITIALISATION_23_INACTIVE, -1),
	MENAPHITE_REMEDY("Menaphite remedy", Rarity.LEGENDARY, ItemID.LILY_OF_THE_SANDS, -1),
	ARMADYL_BREW("Armadyl brew", Rarity.LEGENDARY, ItemID.RAINBOW_CRAB_PASTE, -1),
	ANTIPOISON_PLUS_NOXIFER_CICELY("Antipoison (+)Noxifer & Cicely", Rarity.LEGENDARY, ItemID.RAIDS_VIAL_ANTIPOISON_STRONG_4, -1),
	OVERLOAD_PLUS_NOXIFER_ELDER_PLUS_TWISTED_PLUS_KODAI_PLUS("Overload (+)Noxifer, Elder (+), Twisted (+) & Kodai (+)", Rarity.LEGENDARY, ItemID.RAIDS_VIAL_OVERLOAD_STRONG_4, -1),
	SUPER_COMBAT_POTION("Super combat potion", Rarity.LEGENDARY, ItemID.TORSTOL, -1),
	EXTENDED_ANTIFIRE_MIX("Extended antifire mix", Rarity.LEGENDARY, ItemID.BRUTAL_2DOSE2ANTIDRAGON, -1),
	FORGOTTEN_BREW("Forgotten brew", Rarity.LEGENDARY, ItemID.ANCIENT_ESSENCE, -1),
	ANCIENT_MIX("Ancient mix", Rarity.LEGENDARY, ItemID.BRUTAL_2DOSEANCIENTBREW, -1),
	SUPER_ANTIFIRE_POTION("Super antifire potion", Rarity.LEGENDARY, ItemID.CRUSHED_DRAGON_BONES, -1),
	ANTI_VENOM_ANTI_VENOM_TORSTOL("Anti-venom+Anti-venom & torstol", Rarity.LEGENDARY, ItemID.TORSTOL, -1),
	EXTENDED_ANTI_VENOM_ANTI_VENOM_ARAXYTE_VENOM_SACK("Extended anti-venom+Anti-venom+ & araxyte venom sack", Rarity.LEGENDARY, ItemID.ARAXYTE_VENOM_SACK, -1),
	DIVINE_SUPER_COMBAT_POTION("Divine super combat potion", Rarity.LEGENDARY, ItemID.PRIF_CRYSTAL_SHARD_CRUSHED, -1),
	EXTENDED_SUPER_ANTIFIRES("Extended super antifires", Rarity.LEGENDARY, ItemID.CERT_PICKPOCKET_GUIDE_PALADIN, -1),
	SUPER_ANTIFIRE_MIX("Super antifire mix", Rarity.LEGENDARY, ItemID.BRUTAL_2DOSE3ANTIDRAGON, -1),
	EXTENDED_SUPER_ANTIFIRE_MIX("Extended super antifire mix", Rarity.LEGENDARY, ItemID.BRUTAL_2DOSE4ANTIDRAGON, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_HERBLORE, -1);

	private final Card card;

	HerbloreCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.HERBLORE, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
