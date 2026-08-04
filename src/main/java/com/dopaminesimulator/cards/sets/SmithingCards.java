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

public enum SmithingCards implements CardGroup
{
	BRONZE("Bronze", Rarity.COMMON, ItemID.BRONZE_BAR, -1),
	BRONZE_AXES1_BAR("Bronze axes", Rarity.COMMON, ItemID.BRONZE_AXE, -1),
	BRONZE_DAGGERS1_BAR("Bronze daggers", Rarity.COMMON, ItemID.BRONZE_DAGGER, -1),
	CONVERT_BRONZE_FELLING_AXES("Convert bronze felling axes", Rarity.COMMON, ItemID.BRONZE_AXE_2H, -1),
	CONVERT_IRON_FELLING_AXES("Convert iron felling axes", Rarity.COMMON, ItemID.IRON_AXE_2H, -1),
	BRONZE_MACES1_BAR("Bronze maces", Rarity.COMMON, ItemID.BRONZE_MACE, -1),
	BRONZE_CROSSBOW_BOLTS("Bronze crossbow bolts", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_BRONZE_UNFEATHERED, -1),
	BRONZE_MEDIUM_HELMS1_BAR("Bronze medium helms", Rarity.COMMON, ItemID.BRONZE_MED_HELM, -1),
	BRONZE_DART_TIPS("Bronze dart tips", Rarity.COMMON, ItemID.BRONZE_DART_TIP, -1),
	BRONZE_NAILS("Bronze nails", Rarity.COMMON, ItemID.NAILS_BRONZE, -1),
	BRONZE_SWORDS1_BAR("Bronze swords", Rarity.COMMON, ItemID.BRONZE_SWORD, -1),
	BRONZE_WIRE1_BAR("Bronze wire", Rarity.COMMON, ItemID.BRONZECRAFTWIRE, -1),
	BRONZE_ARROWHEADS("Bronze arrowheads", Rarity.COMMON, ItemID.BRONZE_ARROWHEADS, -1),
	BRONZE_CANNONBALLS("Bronze cannonballs", Rarity.COMMON, ItemID.BRONZE_CANNONBALL, -1),
	BRONZE_HASTAE("Bronze hastae", Rarity.COMMON, ItemID.BRUT_BRONZE_SPEAR, -1),
	BRONZE_SCIMITARS("Bronze scimitars", Rarity.COMMON, ItemID.BRONZE_SCIMITAR, -1),
	BRONZE_SPEARS("Bronze spears", Rarity.COMMON, ItemID.BRONZE_SPEAR, -1),
	BRONZE_CROSSBOW_LIMBS1_BAR("Bronze crossbow limbs", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_LIMBS_BRONZE, -1),
	BRONZE_JAVELIN_TIPS("Bronze javelin tips", Rarity.COMMON, ItemID.CERT_MACRO_CUBE_BLUESQUARE, -1),
	BRONZE_LONGSWORDS("Bronze longswords", Rarity.COMMON, ItemID.BRONZE_LONGSWORD, -1),
	CONVERT_STEEL_FELLING_AXES("Convert steel felling axes", Rarity.COMMON, ItemID.STEEL_AXE_2H, -1),
	BRONZE_FULL_HELMS("Bronze full helms", Rarity.COMMON, ItemID.BRONZE_FULL_HELM, -1),
	BRONZE_THROWING_KNIVES("Bronze throwing knives", Rarity.COMMON, ItemID.BRONZE_KNIFE, -1),
	BLURITE_CROSSBOW_BOLTS("Blurite crossbow bolts", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_BLURITE_UNFEATHERED, -1),
	BRONZE_SQUARE_SHIELDS("Bronze square shields", Rarity.COMMON, ItemID.BRONZE_SQ_SHIELD, -1),
	BRONZE_WARHAMMERS("Bronze warhammers", Rarity.COMMON, ItemID.BRONZE_WARHAMMER, -1),
	BRONZE_BATTLEAXES("Bronze battleaxes", Rarity.COMMON, ItemID.BRONZE_BATTLEAXE, -1),
	BRONZE_KEEL_PARTS("Bronze keel parts", Rarity.COMMON, ItemID.SAILING_BOAT_KEEL_PART_BRONZE, -1),
	BRONZE_CHAINBODIES("Bronze chainbodies", Rarity.COMMON, ItemID.BRONZE_CHAINBODY, -1),
	CONVERT_BLACK_FELLING_AXES("Convert black felling axes", Rarity.COMMON, ItemID.BLACK_AXE_2H, -1),
	BRONZE_KITESHIELDS("Bronze kiteshields", Rarity.COMMON, ItemID.BRONZE_KITESHIELD, -1),
	BLURITE("Blurite", Rarity.COMMON, ItemID.BLURITE_BAR, -1),
	BLURITE_CROSSBOW_LIMBS("Blurite crossbow limbs", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_LIMBS_BLURITE, -1),
	BRONZE_CLAWS("Bronze claws", Rarity.COMMON, ItemID.BRONZE_CLAWS, -1),
	BRONZE_TWO_HANDED_SWORDS("Bronze two-handed swords", Rarity.COMMON, ItemID.BRONZE_2H_SWORD, -1),
	IRON("Iron", Rarity.COMMON, ItemID.IRON_BAR, -1),
	IRON_DAGGERS1_BAR("Iron daggers", Rarity.COMMON, ItemID.IRON_DAGGER, -1),
	BRONZE_PLATELEGS("Bronze platelegs", Rarity.COMMON, ItemID.BRONZE_PLATELEGS, -1),
	BRONZE_PLATESKIRTS("Bronze plateskirts", Rarity.COMMON, ItemID.BRONZE_PLATESKIRT, -1),
	IRON_AXES1_BAR("Iron axes", Rarity.COMMON, ItemID.IRON_AXE, -1),
	IRON_MACES1_BAR("Iron maces", Rarity.COMMON, ItemID.IRON_MACE, -1),
	IRON_SPITS1_BAR("Iron spits", Rarity.COMMON, ItemID.SPIT_IRON, -1),
	BRONZE_PLATEBODIES("Bronze platebodies", Rarity.COMMON, ItemID.BRONZE_PLATEBODY, -1),
	IRON_CROSSBOW_BOLTS("Iron crossbow bolts", Rarity.COMMON, ItemID.XBOWS_CROSSBOW_BOLTS_IRON_UNFEATHERED, -1),
	IRON_MEDIUM_HELMS1_BAR("Iron medium helms", Rarity.COMMON, ItemID.IRON_MED_HELM, -1),
	IRON_DART_TIPS("Iron dart tips", Rarity.COMMON, ItemID.IRON_DART_TIP, -1),
	IRON_NAILS("Iron nails", Rarity.COMMON, ItemID.NAILS_IRON, -1),
	IRON_SWORDS1_BAR("Iron swords", Rarity.COMMON, ItemID.IRON_SWORD, -1),
	IRON_ARROWHEADS("Iron arrowheads", Rarity.UNCOMMON, ItemID.IRON_ARROWHEADS, -1),
	IRON_CANNONBALLS("Iron cannonballs", Rarity.UNCOMMON, ItemID.IRON_CANNONBALL, -1),
	IRON_HASTAE("Iron hastae", Rarity.UNCOMMON, ItemID.BRUT_IRON_SPEAR, -1),
	IRON_SCIMITARS("Iron scimitars", Rarity.UNCOMMON, ItemID.IRON_SCIMITAR, -1),
	IRON_SPEARS("Iron spears", Rarity.UNCOMMON, ItemID.IRON_SPEAR, -1),
	SILVER("Silver", Rarity.UNCOMMON, ItemID.SILVER_BAR, -1),
	CONVERT_MITHRIL_FELLING_AXES("Convert mithril felling axes", Rarity.UNCOMMON, ItemID.MITHRIL_AXE_2H, -1),
	IRON_JAVELIN_TIPS("Iron javelin tips", Rarity.UNCOMMON, ItemID.CERT_MACRO_CUBE_REDSTAR, -1),
	IRON_LONGSWORDS("Iron longswords", Rarity.UNCOMMON, ItemID.IRON_LONGSWORD, -1),
	IRON_FULL_HELMS("Iron full helms", Rarity.UNCOMMON, ItemID.IRON_FULL_HELM, -1),
	IRON_KEEL_PARTS("Iron keel parts", Rarity.UNCOMMON, ItemID.SAILING_BOAT_KEEL_PART_IRON, -1),
	IRON_THROWING_KNIVES("Iron throwing knives", Rarity.UNCOMMON, ItemID.IRON_KNIFE, -1),
	IRON_CROSSBOW_LIMBS1_BAR("Iron crossbow limbs", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_LIMBS_IRON, -1),
	IRON_SQUARE_SHIELDS("Iron square shields", Rarity.UNCOMMON, ItemID.IRON_SQ_SHIELD, -1),
	IRON_WARHAMMERS("Iron warhammers", Rarity.UNCOMMON, ItemID.IRON_WARHAMMER, -1),
	IRON_BATTLEAXES("Iron battleaxes", Rarity.UNCOMMON, ItemID.IRON_BATTLEAXE, -1),
	LEAD("Lead", Rarity.UNCOMMON, ItemID.LEAD_BAR, -1),
	IRON_CHAINBODIES("Iron chainbodies", Rarity.UNCOMMON, ItemID.IRON_CHAINBODY, -1),
	OIL_LANTERN_FRAMES1_BAR("Oil lantern frames", Rarity.UNCOMMON, ItemID.OIL_LANTERN_FRAME, -1),
	IRON_KITESHIELDS("Iron kiteshields", Rarity.UNCOMMON, ItemID.IRON_KITESHIELD, -1),
	IRON_CLAWS("Iron claws", Rarity.UNCOMMON, ItemID.IRON_CLAWS, -1),
	IRON_TWO_HANDED_SWORDS("Iron two-handed swords", Rarity.UNCOMMON, ItemID.IRON_2H_SWORD, -1),
	STEEL("Steel", Rarity.UNCOMMON, ItemID.STEEL_BAR, -1),
	STEEL_DAGGERS1_BAR("Steel daggers", Rarity.UNCOMMON, ItemID.STEEL_DAGGER, -1),
	CONVERT_ADAMANT_FELLING_AXES("Convert adamant felling axes", Rarity.UNCOMMON, ItemID.ADAMANT_AXE_2H, -1),
	IRON_PLATELEGS("Iron platelegs", Rarity.UNCOMMON, ItemID.IRON_PLATELEGS, -1),
	IRON_PLATESKIRTS("Iron plateskirts", Rarity.UNCOMMON, ItemID.IRON_PLATESKIRT, -1),
	STEEL_AXES1_BAR("Steel axes", Rarity.UNCOMMON, ItemID.STEEL_AXE, -1),
	STEEL_MACES1_BAR("Steel maces", Rarity.UNCOMMON, ItemID.STEEL_MACE, -1),
	IRON_PLATEBODIES("Iron platebodies", Rarity.UNCOMMON, ItemID.IRON_PLATEBODY, -1),
	STEEL_CROSSBOW_BOLTS("Steel crossbow bolts", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_BOLTS_STEEL_UNFEATHERED, -1),
	STEEL_MEDIUM_HELMS1_BAR("Steel medium helms", Rarity.UNCOMMON, ItemID.STEEL_MED_HELM, -1),
	CHAIN1_BAR("Chain", Rarity.UNCOMMON, ItemID.CHAIN, -1),
	STEEL_DART_TIPS("Steel dart tips", Rarity.UNCOMMON, ItemID.STEEL_DART_TIP, -1),
	STEEL_NAILS("Steel nails", Rarity.UNCOMMON, ItemID.NAILS, -1),
	STEEL_SWORDS1_BAR("Steel swords", Rarity.UNCOMMON, ItemID.STEEL_SWORD, -1),
	BONE_MACE("Bone mace", Rarity.UNCOMMON, ItemID.RAT_BONE_MACE, -1),
	SECATEURS_ATTACHMENT("Secateurs Attachment", Rarity.UNCOMMON, ItemID.FORESTRY_SECATEURS_ATTACHMENT, -1),
	STEEL_ARROWHEADS("Steel arrowheads", Rarity.UNCOMMON, ItemID.STEEL_ARROWHEADS, -1),
	STEEL_CANNONBALLS("Steel cannonballs", Rarity.UNCOMMON, ItemID.MCANNONBALL, -1),
	STEEL_HASTAE("Steel hastae", Rarity.UNCOMMON, ItemID.BRUT_STEEL_SPEAR, -1),
	STEEL_SCIMITARS("Steel scimitars", Rarity.UNCOMMON, ItemID.STEEL_SCIMITAR, -1),
	STEEL_SPEARS("Steel spears", Rarity.UNCOMMON, ItemID.STEEL_SPEAR, -1),
	STEEL_CROSSBOW_LIMBS1_BAR("Steel crossbow limbs", Rarity.UNCOMMON, ItemID.XBOWS_CROSSBOW_LIMBS_STEEL, -1),
	STEEL_JAVELIN_TIPS("Steel javelin tips", Rarity.UNCOMMON, ItemID.CERT_MACRO_CUBE_BLUEHALFMOON, -1),
	STEEL_LONGSWORDS("Steel longswords", Rarity.UNCOMMON, ItemID.STEEL_LONGSWORD, -1),
	STEEL_STUDS1_BAR("Steel studs", Rarity.UNCOMMON, ItemID.STUDS, -1),
	STEEL_FULL_HELMS("Steel full helms", Rarity.UNCOMMON, ItemID.STEEL_FULL_HELM, -1),
	STEEL_THROWING_KNIVES("Steel throwing knives", Rarity.UNCOMMON, ItemID.STEEL_KNIFE, -1),
	STEEL_KEEL_PARTS("Steel keel parts", Rarity.UNCOMMON, ItemID.SAILING_BOAT_KEEL_PART_STEEL, -1),
	STEEL_SQUARE_SHIELDS("Steel square shields", Rarity.UNCOMMON, ItemID.STEEL_SQ_SHIELD, -1),
	STEEL_WARHAMMERS("Steel warhammers", Rarity.UNCOMMON, ItemID.STEEL_WARHAMMER, -1),
	GOLD("Gold", Rarity.RARE, ItemID.GOLD_BAR, -1),
	STEEL_BATTLEAXES("Steel battleaxes", Rarity.RARE, ItemID.STEEL_BATTLEAXE, -1),
	CONVERT_RUNE_FELLING_AXES("Convert rune felling axes", Rarity.RARE, ItemID.RUNE_AXE_2H, -1),
	STEEL_CHAINBODIES("Steel chainbodies", Rarity.RARE, ItemID.STEEL_CHAINBODY, -1),
	STEEL_KITESHIELDS("Steel kiteshields", Rarity.RARE, ItemID.STEEL_KITESHIELD, -1),
	STEEL_CLAWS("Steel claws", Rarity.RARE, ItemID.STEEL_CLAWS, -1),
	STEEL_TWO_HANDED_SWORDS("Steel two-handed swords", Rarity.RARE, ItemID.STEEL_2H_SWORD, -1),
	LOVAKITE("Lovakite", Rarity.RARE, ItemID.LOVAKITE_BAR, -1),
	SHAYZIEN_GLOVES_1_1_BAR("Shayzien gloves (1)", Rarity.RARE, ItemID.SHAYZIEN_GLOVES_1, -1),
	STEEL_PLATELEGS("Steel platelegs", Rarity.RARE, ItemID.STEEL_PLATELEGS, -1),
	STEEL_PLATESKIRTS("Steel plateskirts", Rarity.RARE, ItemID.STEEL_PLATESKIRT, -1),
	SHAYZIEN_BOOTS_1_1_BAR("Shayzien boots (1)", Rarity.RARE, ItemID.SHAYZIEN_BOOTS_1, -1),
	STEEL_PLATEBODIES("Steel platebodies", Rarity.RARE, ItemID.STEEL_PLATEBODY, -1),
	BULLSEYE_LANTERN_FRAMES1_BAR("Bullseye lantern frames", Rarity.RARE, ItemID.BULLSEYE_LANTERN_NOLENS, -1),
	SHAYZIEN_HELM_1_2_BARS("Shayzien helm (1)", Rarity.RARE, ItemID.SHAYZIEN_HELM_1, -1),
	MITHRIL("Mithril", Rarity.RARE, ItemID.MITHRIL_BAR, -1),
	MITHRIL_DAGGERS1_BAR("Mithril daggers", Rarity.RARE, ItemID.MITHRIL_DAGGER, -1),
	MITHRIL_AXES1_BAR("Mithril axes", Rarity.RARE, ItemID.MITHRIL_AXE, -1),
	SHAYZIEN_GREAVES_1_3_BARS("Shayzien greaves (1)", Rarity.RARE, ItemID.SHAYZIEN_LEGS_1, -1),
	MITHRIL_MACES1_BAR("Mithril maces", Rarity.RARE, ItemID.MITHRIL_MACE, -1),
	MITHRIL_CROSSBOW_BOLTS("Mithril crossbow bolts", Rarity.RARE, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_UNFEATHERED, -1),
	MITHRIL_MEDIUM_HELMS1_BAR("Mithril medium helms", Rarity.RARE, ItemID.MITHRIL_MED_HELM, -1),
	SHAYZIEN_PLATEBODY_1_4_BARS("Shayzien platebody (1)", Rarity.RARE, ItemID.SHAYZIEN_BODY_1, -1),
	MITHRIL_DART_TIPS("Mithril dart tips", Rarity.RARE, ItemID.MITHRIL_DART_TIP, -1),
	MITHRIL_NAILS("Mithril nails", Rarity.RARE, ItemID.NAILS_MITHRIL, -1),
	MITHRIL_SWORDS1_BAR("Mithril swords", Rarity.RARE, ItemID.MITHRIL_SWORD, -1),
	MITHRIL_ARROWHEADS("Mithril arrowheads", Rarity.RARE, ItemID.MITHRIL_ARROWHEADS, -1),
	MITHRIL_CANNONBALLS("Mithril cannonballs", Rarity.RARE, ItemID.MITHRIL_CANNONBALL, -1),
	MITHRIL_HASTAE("Mithril hastae", Rarity.RARE, ItemID.BRUT_MITHRIL_SPEAR, -1),
	MITHRIL_SCIMITARS("Mithril scimitars", Rarity.RARE, ItemID.MITHRIL_SCIMITAR, -1),
	MITHRIL_SPEARS("Mithril spears", Rarity.RARE, ItemID.MITHRIL_SPEAR, -1),
	SHAYZIEN_GLOVES_2_1_BAR("Shayzien gloves (2)", Rarity.RARE, ItemID.SHAYZIEN_GLOVES_2, -1),
	MITHRIL_CROSSBOW_LIMBS1_BAR("Mithril crossbow limbs", Rarity.RARE, ItemID.XBOWS_CROSSBOW_LIMBS_MITHRIL, -1),
	MITHRIL_JAVELIN_TIPS("Mithril javelin tips", Rarity.RARE, ItemID.CERT_PICKPOCKET_GUIDE_WARRIOR, -1),
	MITHRIL_KEEL_PARTS("Mithril keel parts", Rarity.RARE, ItemID.SAILING_BOAT_KEEL_PART_MITHRIL, -1),
	MITHRIL_LONGSWORDS("Mithril longswords", Rarity.RARE, ItemID.MITHRIL_LONGSWORD, -1),
	MITHRIL_FULL_HELMS("Mithril full helms", Rarity.RARE, ItemID.MITHRIL_FULL_HELM, -1),
	MITHRIL_THROWING_KNIVES("Mithril throwing knives", Rarity.RARE, ItemID.MITHRIL_KNIFE, -1),
	SHAYZIEN_BOOTS_2_1_BAR("Shayzien boots (2)", Rarity.RARE, ItemID.SHAYZIEN_BOOTS_2, -1),
	MITHRIL_SQUARE_SHIELDS("Mithril square shields", Rarity.RARE, ItemID.MITHRIL_SQ_SHIELD, -1),
	MITHRIL_CROSSBOW_GRAPPLE_TIPS1_BAR("Mithril crossbow grapple tips", Rarity.RARE, ItemID.XBOWS_GRAPPLE_TIP_MITHRIL, -1),
	MITHRIL_WARHAMMERS("Mithril warhammers", Rarity.RARE, ItemID.MITHRIL_WARHAMMER, -1),
	SHAYZIEN_HELM_2_2_BARS("Shayzien helm (2)", Rarity.RARE, ItemID.SHAYZIEN_HELM_2, -1),
	AQUANITE_HOPPERS("Aquanite hoppers", Rarity.EPIC, ItemID.AQUANITE_HOPPER, -1),
	DRAGON_SQUARE_SHIELD("Dragon square shield", Rarity.EPIC, ItemID.DRAGON_SQ_SHIELD, -1),
	MITHRIL_BATTLEAXES("Mithril battleaxes", Rarity.EPIC, ItemID.MITHRIL_BATTLEAXE, -1),
	CONVERT_3RD_AGE_FELLING_AXES("Convert 3rd Age felling axes", Rarity.EPIC, ItemID._3A_AXE_2H, -1),
	CONVERT_DRAGON_FELLING_AXES("Convert dragon felling axes", Rarity.EPIC, ItemID.DRAGON_AXE_2H, -1),
	MITHRIL_CHAINBODIES("Mithril chainbodies", Rarity.EPIC, ItemID.MITHRIL_CHAINBODY, -1),
	SHAYZIEN_GREAVES_2_3_BARS("Shayzien greaves (2)", Rarity.EPIC, ItemID.SHAYZIEN_LEGS_2, -1),
	MITHRIL_KITESHIELDS("Mithril kiteshields", Rarity.EPIC, ItemID.MITHRIL_KITESHIELD, -1),
	MITHRIL_CLAWS("Mithril claws", Rarity.EPIC, ItemID.MITHRIL_CLAWS, -1),
	SHAYZIEN_PLATEBODY_2_4_BARS("Shayzien platebody (2)", Rarity.EPIC, ItemID.SHAYZIEN_BODY_2, -1),
	MITHRIL_TWO_HANDED_SWORDS("Mithril two-handed swords", Rarity.EPIC, ItemID.MITHRIL_2H_SWORD, -1),
	SHAYZIEN_GLOVES_3_1_BAR("Shayzien gloves (3)", Rarity.EPIC, ItemID.SHAYZIEN_GLOVES_3, -1),
	ANCIENT_WYVERN_SHIELD("Ancient Wyvern shield", Rarity.EPIC, ItemID.WYVERN_SHIELD, -1),
	MITHRIL_PLATELEGS("Mithril platelegs", Rarity.EPIC, ItemID.MITHRIL_PLATELEGS, -1),
	MITHRIL_PLATESKIRTS("Mithril plateskirts", Rarity.EPIC, ItemID.MITHRIL_PLATESKIRT, -1),
	SHAYZIEN_BOOTS_3_1_BAR("Shayzien boots (3)", Rarity.EPIC, ItemID.SHAYZIEN_BOOTS_3, -1),
	MITHRIL_PLATEBODIES("Mithril platebodies", Rarity.EPIC, ItemID.MITHRIL_PLATEBODY, -1),
	SHAYZIEN_HELM_3_2_BARS("Shayzien helm (3)", Rarity.EPIC, ItemID.SHAYZIEN_HELM_3, -1),
	ADAMANT("Adamant", Rarity.EPIC, ItemID.ADAMANTITE_BAR, -1),
	ADAMANT_DAGGERS1_BAR("Adamant daggers", Rarity.EPIC, ItemID.ADAMANT_DAGGER, -1),
	CELESTIAL_SIGNET("Celestial signet", Rarity.EPIC, ItemID.CELESTIAL_SIGNET_CHARGED, -1),
	CONFLICTION_GAUNTLETS("Confliction gauntlets", Rarity.EPIC, ItemID.CONFLICTION_GAUNTLETS, -1),
	CRYSTAL_HELM("Crystal helm", Rarity.EPIC, ItemID.CRYSTAL_HELMET, -1),
	ZOMBIE_AXES_AND_HELMETS("Zombie axes and helmets", Rarity.EPIC, ItemID.ZOMBIE_AXE, -1),
	ADAMANT_AXES1_BAR("Adamant axes", Rarity.EPIC, ItemID.ADAMANT_AXE, -1),
	SHAYZIEN_GREAVES_3_3_BARS("Shayzien greaves (3)", Rarity.EPIC, ItemID.SHAYZIEN_LEGS_3, -1),
	ADAMANT_MACES1_BAR("Adamant maces", Rarity.EPIC, ItemID.ADAMANT_MACE, -1),
	CRYSTAL_LEGS("Crystal legs", Rarity.EPIC, ItemID.CRYSTAL_PLATELEGS, -1),
	NOXIOUS_HALBERD("Noxious Halberd", Rarity.EPIC, ItemID.NOXIOUS_HALBERD, -1),
	ADAMANT_CROSSBOW_BOLTS("Adamant crossbow bolts", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_UNFEATHERED, -1),
	ADAMANT_MEDIUM_HELMS1_BAR("Adamant medium helms", Rarity.EPIC, ItemID.ADAMANT_MED_HELM, -1),
	SHAYZIEN_PLATEBODY_3_4_BARS("Shayzien platebody (3)", Rarity.EPIC, ItemID.SHAYZIEN_BODY_3, -1),
	ADAMANT_DART_TIPS("Adamant dart tips", Rarity.EPIC, ItemID.ADAMANT_DART_TIP, -1),
	ADAMANT_KEEL_PARTS("Adamant keel parts", Rarity.EPIC, ItemID.SAILING_BOAT_KEEL_PART_ADAMANT, -1),
	ADAMANT_NAILS("Adamant nails", Rarity.EPIC, ItemID.NAILS_ADAMANT, -1),
	ADAMANT_SWORDS1_BAR("Adamant swords", Rarity.EPIC, ItemID.ADAMANT_SWORD, -1),
	CRYSTAL_BODY("Crystal body", Rarity.EPIC, ItemID.CRYSTAL_CHESTPLATE, -1),
	CUPRONICKEL("Cupronickel", Rarity.EPIC, ItemID.CUPRONICKEL_BAR, -1),
	EMBERLIGHT("Emberlight", Rarity.EPIC, ItemID.EMBERLIGHT, -1),
	ADAMANT_ARROWHEADS("Adamant arrowheads", Rarity.EPIC, ItemID.ADAMANT_ARROWHEADS, -1),
	ADAMANT_CANNONBALLS("Adamant cannonballs", Rarity.EPIC, ItemID.ADAMANT_CANNONBALL, -1),
	ADAMANT_HASTAE("Adamant hastae", Rarity.EPIC, ItemID.BRUT_ADAMANT_SPEAR, -1),
	ADAMANT_SCIMITARS("Adamant scimitars", Rarity.EPIC, ItemID.ADAMANT_SCIMITAR, -1),
	ADAMANT_SPEARS("Adamant spears", Rarity.EPIC, ItemID.ADAMANT_SPEAR, -1),
	DRAGON_KITE_SHIELD("Dragon kite shield", Rarity.EPIC, ItemID.DRAGON_KITESHIELD, -1),
	SHAYZIEN_GLOVES_4_1_BAR("Shayzien gloves (4)", Rarity.EPIC, ItemID.SHAYZIEN_GLOVES_4, -1),
	ADAMANT_CROSSBOW_LIMBS1_BAR("Adamant crossbow limbs", Rarity.EPIC, ItemID.XBOWS_CROSSBOW_LIMBS_ADAMANTITE, -1),
	ADAMANT_JAVELIN_TIPS("Adamant javelin tips", Rarity.EPIC, ItemID.CERT_REINITIALISATION_03, -1),
	ADAMANT_LONGSWORDS("Adamant longswords", Rarity.EPIC, ItemID.ADAMANT_LONGSWORD, -1),
	CRYSTAL_AXE("Crystal axe", Rarity.EPIC, ItemID.CRYSTAL_AXE, -1),
	CRYSTAL_FELLING_AXE("Crystal felling axe", Rarity.EPIC, ItemID.CRYSTAL_AXE_2H, -1),
	CRYSTAL_HARPOON("Crystal harpoon", Rarity.EPIC, ItemID.CRYSTAL_HARPOON, -1),
	CRYSTAL_PICKAXE("Crystal pickaxe", Rarity.EPIC, ItemID.CRYSTAL_PICKAXE, -1),
	ADAMANT_FULL_HELMS("Adamant full helms", Rarity.EPIC, ItemID.ADAMANT_FULL_HELM, -1),
	ADAMANT_THROWING_KNIVES("Adamant throwing knives", Rarity.EPIC, ItemID.ADAMANT_KNIFE, -1),
	SHAYZIEN_BOOTS_4_1_BAR("Shayzien boots (4)", Rarity.EPIC, ItemID.SHAYZIEN_BOOTS_4, -1),
	ADAMANT_SQUARE_SHIELDS("Adamant square shields", Rarity.EPIC, ItemID.ADAMANT_SQ_SHIELD, -1),
	CRYSTAL_BOW("Crystal bow", Rarity.EPIC, ItemID.CRYSTAL_BOW, -1),
	CRYSTAL_HALBERD("Crystal halberd", Rarity.EPIC, ItemID.CRYSTAL_HALBERD, -1),
	CRYSTAL_SHIELD("Crystal shield", Rarity.EPIC, ItemID.CRYSTAL_SHIELD, -1),
	ADAMANT_WARHAMMERS("Adamant warhammers", Rarity.EPIC, ItemID.ADAMNT_WARHAMMER, -1),
	SHAYZIEN_HELM_4_2_BARS("Shayzien helm (4)", Rarity.EPIC, ItemID.SHAYZIEN_HELM_4, -1),
	ADAMANT_BATTLEAXES("Adamant battleaxes", Rarity.LEGENDARY, ItemID.ADAMANT_BATTLEAXE, -1),
	ENHANCED_CRYSTAL_KEY("Enhanced crystal key", Rarity.LEGENDARY, ItemID.PRIF_CRYSTAL_KEY, -1),
	ETERNAL_TELEPORT_CRYSTAL("Eternal teleport crystal", Rarity.LEGENDARY, ItemID.PRIF_TELEPORT_CRYSTAL, -1),
	ADAMANT_CHAINBODIES("Adamant chainbodies", Rarity.LEGENDARY, ItemID.ADAMANT_CHAINBODY, -1),
	SHAYZIEN_GREAVES_4_3_BARS("Shayzien greaves (4)", Rarity.LEGENDARY, ItemID.SHAYZIEN_LEGS_4, -1),
	ADAMANT_KITESHIELDS("Adamant kiteshields", Rarity.LEGENDARY, ItemID.ADAMANT_KITESHIELD, -1),
	BLADE_OF_SAELDOR("Blade of Saeldor", Rarity.LEGENDARY, ItemID.BLADE_OF_SAELDOR, -1),
	BLADE_OF_SAELDOR_C("Blade of Saeldor (c)", Rarity.LEGENDARY, ItemID.BLADE_OF_SAELDOR_INFINITE, -1),
	BOW_OF_FAERDHINEN("Bow of Faerdhinen", Rarity.LEGENDARY, ItemID.BOW_OF_FAERDHINEN, -1),
	BOW_OF_FAERDHINEN_C("Bow of Faerdhinen (c)", Rarity.LEGENDARY, ItemID.BOW_OF_FAERDHINEN_INFINITE, -1),
	ADAMANT_CLAWS("Adamant claws", Rarity.LEGENDARY, ItemID.ADAMANT_CLAWS, -1),
	OATHPLATE("Oathplate", Rarity.LEGENDARY, ItemID.OATHPLATE_HELM, -1),
	SHAYZIEN_PLATEBODY_4_4_BARS("Shayzien platebody (4)", Rarity.LEGENDARY, ItemID.SHAYZIEN_BODY_4, -1),
	ADAMANT_TWO_HANDED_SWORDS("Adamant two-handed swords", Rarity.LEGENDARY, ItemID.ADAMANT_2H_SWORD, -1),
	RUNE_DAGGERS1_BAR("Rune daggers", Rarity.LEGENDARY, ItemID.RUNE_DAGGER, -1),
	RUNITE("Runite", Rarity.LEGENDARY, ItemID.RUNITE_BAR, -1),
	SHAYZIEN_GLOVES_5_1_BAR("Shayzien gloves (5)", Rarity.LEGENDARY, ItemID.SHAYZIEN_GLOVES_5, -1),
	ADAMANT_PLATELEGS("Adamant platelegs", Rarity.LEGENDARY, ItemID.ADAMANT_PLATELEGS, -1),
	ADAMANT_PLATESKIRTS("Adamant plateskirts", Rarity.LEGENDARY, ItemID.ADAMANT_PLATESKIRT, -1),
	RUNE_AXES1_BAR("Rune axes", Rarity.LEGENDARY, ItemID.RUNE_AXE, -1),
	RUNE_KEEL_PARTS("Rune keel parts", Rarity.LEGENDARY, ItemID.SAILING_BOAT_KEEL_PART_RUNE, -1),
	RUNE_MACES1_BAR("Rune maces", Rarity.LEGENDARY, ItemID.RUNE_MACE, -1),
	SHAYZIEN_BOOTS_5_1_BAR("Shayzien boots (5)", Rarity.LEGENDARY, ItemID.SHAYZIEN_BOOTS_5, -1),
	ADAMANT_PLATEBODIES("Adamant platebodies", Rarity.LEGENDARY, ItemID.ADAMANT_PLATEBODY, -1),
	RUNE_CROSSBOW_BOLTS("Rune crossbow bolts", Rarity.LEGENDARY, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_UNFEATHERED, -1),
	RUNE_MEDIUM_HELMS1_BAR("Rune medium helms", Rarity.LEGENDARY, ItemID.RUNE_MED_HELM, -1),
	RUNE_DART_TIPS("Rune dart tips", Rarity.LEGENDARY, ItemID.RUNE_DART_TIP, -1),
	RUNE_NAILS("Rune nails", Rarity.LEGENDARY, ItemID.NAILS_RUNE, -1),
	RUNE_SWORDS1_BAR("Rune swords", Rarity.LEGENDARY, ItemID.RUNE_SWORD, -1),
	SHAYZIEN_HELM_5_2_BARS("Shayzien helm (5)", Rarity.LEGENDARY, ItemID.SHAYZIEN_HELM_5, -1),
	DRAGON_PLATEBODY("Dragon platebody", Rarity.LEGENDARY, ItemID.DRAGON_PLATEBODY, -1),
	RUNE_ARROWHEADS("Rune arrowheads", Rarity.LEGENDARY, ItemID.RUNE_ARROWHEADS, -1),
	RUNE_CANNONBALLS("Rune cannonballs", Rarity.LEGENDARY, ItemID.RUNE_CANNONBALL, -1),
	RUNE_HASTAE("Rune hastae", Rarity.LEGENDARY, ItemID.BRUT_RUNE_SPEAR, -1),
	RUNE_SCIMITARS("Rune scimitars", Rarity.LEGENDARY, ItemID.RUNE_SCIMITAR, -1),
	RUNE_SPEARS("Rune spears", Rarity.LEGENDARY, ItemID.RUNE_SPEAR, -1),
	RUNE_CROSSBOW_LIMBS1_BAR("Rune crossbow limbs", Rarity.LEGENDARY, ItemID.XBOWS_CROSSBOW_LIMBS_RUNITE, -1),
	RUNE_JAVELIN_TIPS("Rune javelin tips", Rarity.LEGENDARY, ItemID.CERT_REINITIALISATION_07, -1),
	RUNE_LONGSWORDS("Rune longswords", Rarity.LEGENDARY, ItemID.RUNE_LONGSWORD, -1),
	SHAYZIEN_GREAVES_5_3_BARS("Shayzien greaves (5)", Rarity.LEGENDARY, ItemID.SHAYZIEN_LEGS_5, -1),
	DRAGON_NAILS("Dragon nails", Rarity.LEGENDARY, ItemID.NAILS_DRAGON, -1),
	RUNE_FULL_HELMS("Rune full helms", Rarity.LEGENDARY, ItemID.RUNE_FULL_HELM, -1),
	RUNE_THROWING_KNIVES("Rune throwing knives", Rarity.LEGENDARY, ItemID.RUNE_KNIFE, -1),
	RUNE_SQUARE_SHIELDS("Rune square shields", Rarity.LEGENDARY, ItemID.RUNE_SQ_SHIELD, -1),
	SHAYZIEN_PLATEBODY_5_4_BARS("Shayzien platebody (5)", Rarity.LEGENDARY, ItemID.SHAYZIEN_BODY_5, -1),
	DRAGON_KEEL_PARTS("Dragon keel parts", Rarity.LEGENDARY, ItemID.SAILING_BOAT_KEEL_PART_DRAGON, -1),
	RUNE_WARHAMMERS("Rune warhammers", Rarity.LEGENDARY, ItemID.RUNE_WARHAMMER, -1),
	RUNE_BATTLEAXES("Rune battleaxes", Rarity.LEGENDARY, ItemID.RUNE_BATTLEAXE, -1),
	RUNE_CHAINBODIES("Rune chainbodies", Rarity.LEGENDARY, ItemID.RUNE_CHAINBODY, -1),
	RUNE_KITESHIELDS("Rune kiteshields", Rarity.LEGENDARY, ItemID.RUNE_KITESHIELD, -1),
	RUNE_CLAWS("Rune claws", Rarity.LEGENDARY, ItemID.RUNE_CLAWS, -1),
	RUNE_PLATEBODIES("Rune platebodies", Rarity.LEGENDARY, ItemID.RUNE_PLATEBODY, -1),
	RUNE_PLATELEGS("Rune platelegs", Rarity.LEGENDARY, ItemID.RUNE_PLATELEGS, -1),
	RUNE_PLATESKIRTS("Rune plateskirts", Rarity.LEGENDARY, ItemID.RUNE_PLATESKIRT, -1),
	RUNE_TWO_HANDED_SWORDS("Rune two-handed swords", Rarity.LEGENDARY, ItemID.RUNE_2H_SWORD, -1),
	SKILLCAPE("Skillcape", Rarity.LEGENDARY, ItemID.SKILLCAPE_SMITHING, -1);

	private final String label;
	private final Rarity rarity;
	private final int itemId;
	private final int spriteId;

	private Card card;

	SmithingCards(String label, Rarity rarity, int itemId, int spriteId)
	{
		this.label = label;
		this.rarity = rarity;
		this.itemId = itemId;
		this.spriteId = spriteId;
	}

	/**
	 * Ids here come from the constant name rather than the label, so the text on a
	 * card can be corrected without renaming the save key underneath it.
	 *
	 * The labels used to carry the bar cost — "Bronze axes1 bar" — and every id that
	 * has shipped was built from that. The constants still spell it, so slugging
	 * BRONZE_AXES1_BAR gives back the id the label used to produce. Renaming a
	 * constant in here now moves somebody's cards; renaming a label does not.
	 *
	 * A static block rather than the constructor, because name() is not set on a
	 * constant until after its constructor has run.
	 */
	static
	{
		for (SmithingCards entry : values())
		{
			entry.card = CardGroup.withId(CardSet.SMITHING,
				CardGroup.idFor(CardSet.SMITHING, entry.name()),
				entry.label, entry.rarity, entry.itemId, entry.spriteId);
		}
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
