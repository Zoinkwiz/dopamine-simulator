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

public enum AttackCards implements CardGroup
{
	BRONZE_WEAPONS("Bronze weapons", Rarity.COMMON, ItemID.BRONZE_DAGGER, -1),
	IRON_WEAPONS("Iron weapons", Rarity.COMMON, ItemID.IRON_DAGGER, -1),
	STEEL_WEAPONS("Steel weapons", Rarity.COMMON, ItemID.STEEL_DAGGER, -1),
	BLACK_WEAPONS("Black weapons", Rarity.COMMON, ItemID.BLACK_DAGGER, -1),
	WHITE_WEAPONS("White weapons", Rarity.COMMON, ItemID.WHITE_DAGGER, -1),
	MITHRIL_WEAPONS("Mithril weapons", Rarity.UNCOMMON, ItemID.MITHRIL_DAGGER, -1),
	ADAMANT_WEAPONS("Adamant weapons", Rarity.UNCOMMON, ItemID.ADAMANT_DAGGER, -1),
	BARRONITE_MACE("Barronite mace", Rarity.RARE, ItemID.BARRONITE_MACE, -1),
	BRINE_SABRE("Brine sabre", Rarity.RARE, ItemID.OLAF2_BRINE_SABRE, -1),
	IVANDIS_FLAIL("Ivandis Flail", Rarity.RARE, ItemID.IVANDIS_FLAIL, -1),
	RUNE_WEAPONS("Rune weapons", Rarity.RARE, ItemID.RUNE_DAGGER, -1),
	BLISTERWOOD("Blisterwood", Rarity.RARE, ItemID.BLISTERWOOD_FLAIL, -1),
	BONE_MACE("Bone mace", Rarity.RARE, ItemID.RAT_BONE_MACE, -1),
	CRYSTAL_HALBERD("Crystal halberd", Rarity.RARE, ItemID.CRYSTAL_HALBERD, -1),
	GRANITE_LONGSWORD("Granite longsword", Rarity.RARE, ItemID.GRANITE_LONGSWORD, -1),
	GRANITE_MAUL("Granite maul", Rarity.RARE, ItemID.GRANITE_MAUL, -1),
	KERIS_DAGGER("Keris dagger", Rarity.RARE, ItemID.CONTACT_KERIS, -1),
	EARTHBOUND_TECPATL("Earthbound tecpatl", Rarity.RARE, ItemID.EARTHBOUND_TECPATL, -1),
	GLACIAL_TEMOTLI("Glacial temotli", Rarity.RARE, ItemID.GLACIAL_TEMOTLI, -1),
	SULPHUR_BLADES("Sulphur blades", Rarity.RARE, ItemID.SULPHUR_BLADES, -1),
	ARKAN_BLADE("Arkan blade", Rarity.EPIC, ItemID.ARKAN_BLADE, -1),
	BARRELCHEST_ANCHOR("Barrelchest Anchor", Rarity.EPIC, ItemID.BRAIN_ANCHOR, -1),
	BURNING_CLAWS("Burning claws", Rarity.EPIC, ItemID.BONE_CLAWS, -1),
	COLOSSAL_BLADE("Colossal Blade", Rarity.EPIC, ItemID.GIANTS_FOUNDRY_COLOSSAL_BLADE, -1),
	CRIMSON_KISTEN("Crimson Kisten", Rarity.EPIC, ItemID.CRIMSON_KISTEN, -1),
	DRAGON_WEAPONS("Dragon weapons", Rarity.EPIC, ItemID.DRAGON_DAGGER, -1),
	OBSIDIAN_MELEE_WEAPONS("Obsidian melee weapons", Rarity.EPIC, ItemID.TZHAAR_SPLITSWORD, -1),
	VIGGORA_S_CHAINMACE("Viggora's Chainmace", Rarity.EPIC, ItemID.WILD_CAVE_CHAINMACE_CHARGED, -1),
	N3RD_AGE_WEAPONS("3rd Age weapons", Rarity.EPIC, ItemID.TRAIL_FIGHTER_SWORD, -1),
	BELLE_S_FOLLY("Belle's Folly", Rarity.EPIC, ItemID.BELLES_FOLLY, -1),
	KERIS_PARTISAN("Keris partisan", Rarity.EPIC, ItemID.KERIS_PARTISAN, -1),
	SARACHNIS_CUDGEL("Sarachnis cudgel", Rarity.EPIC, ItemID.SARACHNIS_CUDGEL, -1),
	ZOMBIE_AXE("Zombie axe", Rarity.EPIC, ItemID.ZOMBIE_AXE, -1),
	ABYSSAL_BLUDGEON("Abyssal bludgeon", Rarity.EPIC, ItemID.ABYSSAL_BLUDGEON, -1),
	ABYSSAL_WHIP_DAGGER("Abyssal whip & dagger", Rarity.EPIC, ItemID.ABYSSAL_WHIP, -1),
	AVERNIC_DEFENDER("Avernic defender", Rarity.EPIC, ItemID.INFERNAL_DEFENDER, -1),
	DHAROK_S_GREATAXE("Dharok's greataxe", Rarity.EPIC, ItemID.BARROWS_DHAROK_WEAPON, -1),
	DUAL_MACUAHUITL("Dual Macuahuitl", Rarity.EPIC, ItemID.DUAL_MACUAHUITL, -1),
	GUTHAN_S_WARSPEAR("Guthan's warspear", Rarity.EPIC, ItemID.BARROWS_GUTHAN_WEAPON, -1),
	SARADOMIN_SWORD("Saradomin sword", Rarity.EPIC, ItemID.SARADOMIN_SWORD, -1),
	TORAG_S_HAMMERS("Torag's hammers", Rarity.EPIC, ItemID.BARROWS_TORAG_WEAPON, -1),
	URSINE_CHAINMACE("Ursine Chainmace", Rarity.EPIC, ItemID.WILD_CAVE_URSINE_CHARGED, -1),
	VERAC_S_FLAIL("Verac's flail", Rarity.EPIC, ItemID.BARROWS_VERAC_WEAPON, -1),
	ZAMORAKIAN_SPEAR("Zamorakian spear", Rarity.EPIC, ItemID.ZAMORAK_SPEAR, -1),
	ABYSSAL_TENTACLE("Abyssal tentacle", Rarity.EPIC, ItemID.ABYSSAL_TENTACLE, -1),
	ARCLIGHT("Arclight", Rarity.EPIC, ItemID.ARCLIGHT, -1),
	BLESSED_SARADOMIN_SWORD("Blessed Saradomin sword", Rarity.EPIC, ItemID.BLESSED_SARADOMIN_SWORD, -1),
	DINH_S_BULWARK("Dinh's bulwark", Rarity.EPIC, ItemID.DINHS_BULWARK, -1),
	ELDER_MAUL("Elder maul", Rarity.EPIC, ItemID.ELDER_MAUL, -1),
	GODSWORDS("Godswords", Rarity.EPIC, ItemID.ZGS, -1),
	HALLOWFELL("Hallowfell", Rarity.EPIC, 34027, -1),
	VESTA_S_BLIGHTED_LONGSWORD("Vesta's blighted longsword", Rarity.EPIC, ItemID.BH_VESTAS_LONGSWORD, -1),
	VOIDWAKER("Voidwaker", Rarity.EPIC, ItemID.VOIDWAKER, -1),
	EMBERLIGHT("Emberlight", Rarity.EPIC, ItemID.EMBERLIGHT, -1),
	DRAGON_HUNTER_LANCE("Dragon hunter lance", Rarity.EPIC, ItemID.DRAGONHUNTER_LANCE, -1),
	BLADE_OF_SAELDOR("Blade of Saeldor", Rarity.LEGENDARY, ItemID.BLADE_OF_SAELDOR, -1),
	FEROCIOUS_GLOVES("Ferocious Gloves", Rarity.LEGENDARY, ItemID.FEROCIOUS_GLOVES, -1),
	GHRAZI_RAPIER("Ghrazi rapier", Rarity.LEGENDARY, ItemID.GHRAZI_RAPIER, -1),
	INQUISITOR_S_MACE("Inquisitor's mace", Rarity.LEGENDARY, ItemID.INQUISITORS_MACE, -1),
	NOXIOUS_HALBERD("Noxious Halberd", Rarity.LEGENDARY, ItemID.NOXIOUS_HALBERD, -1),
	SCYTHE_OF_VITUR("Scythe of Vitur", Rarity.LEGENDARY, ItemID.SCYTHE_OF_VITUR, -1),
	SOULREAPER_AXE("Soulreaper axe", Rarity.LEGENDARY, ItemID.SOULREAPER, -1),
	VARIANTS_OF_KERIS_PARTISAN("Variants of Keris partisan", Rarity.LEGENDARY, ItemID.KERIS_PARTISAN_CORRUPTION, -1),
	OSMUMTEN_S_FANG("Osmumten's Fang", Rarity.LEGENDARY, ItemID.OSMUMTENS_FANG, -1);

	private final Card card;

	AttackCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.ATTACK, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
