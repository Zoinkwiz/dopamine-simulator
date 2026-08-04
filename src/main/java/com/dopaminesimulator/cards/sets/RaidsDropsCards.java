/*
 * Copyright (c) 2026, Haavardaw <https://github.com/Haavardaw>
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

public enum RaidsDropsCards implements CardGroup
{
	// Chambers of Xeric
	OLM_PET("Olmlet", Rarity.LEGENDARY, ItemID.OLMPET, -1),
	METAMORPHIC_DUST("Metamorphic Dust", Rarity.EPIC, ItemID.RAIDS_CHALLENGE_MORPH, -1),
	TWISTED_BOW("Twisted bow", Rarity.LEGENDARY, ItemID.TWISTED_BOW, -1),
	ELDER_MAUL("Elder maul", Rarity.LEGENDARY, ItemID.ELDER_MAUL, -1),
	KODAI_INSIGNIA("Kodai insignia", Rarity.LEGENDARY, ItemID.KODAI_INSIGNIA, -1),
	DRAGON_CLAWS("Dragon Claws", Rarity.EPIC, ItemID.DRAGON_CLAWS, -1),
	ANCESTRAL_HAT("Ancestral hat", Rarity.EPIC, ItemID.ANCESTRAL_HAT, -1),
	ANCESTRAL_ROBE_TOP("Ancestral robe top", Rarity.EPIC, ItemID.ANCESTRAL_ROBE_TOP, -1),
	ANCESTRAL_ROBE_BOTTOM("Ancestral robe bottom", Rarity.EPIC, ItemID.ANCESTRAL_ROBE_BOTTOM, -1),
	DINHS_BULWARK("Dinh's bulwark", Rarity.RARE, ItemID.DINHS_BULWARK, -1),
	RAIDS_PRAYERSCROLL("Dexterous prayer scroll", Rarity.EPIC, ItemID.RAIDS_PRAYERSCROLL, -1),
	RAIDS_PRAYERSCROLL_AUGURY("Arcane prayer scroll", Rarity.EPIC, ItemID.RAIDS_PRAYERSCROLL_AUGURY, -1),
	DRAGON_HUNTER_CROSSBOW("Dragon hunter crossbow", Rarity.EPIC, ItemID.DRAGONHUNTER_XBOW, -1),
	TWISTED_BUCKLER("Twisted buckler", Rarity.RARE, ItemID.TWISTED_BUCKLER, -1),
	RAIDS_PRAYERSCROLL_PRESERVE("Torn prayer scroll", Rarity.UNCOMMON, ItemID.RAIDS_PRAYERSCROLL_PRESERVE, -1),
	RAIDS_ANCIENT_RELIC("Dark relic", Rarity.UNCOMMON, ItemID.RAIDS_ANCIENT_RELIC, -1),
	ONYX("Onyx", Rarity.RARE, ItemID.ONYX, -1),
	ANCESTRAL_ROBES_TWISTED_KIT("Twisted ancestral colour kit", Rarity.EPIC, ItemID.ANCESTRAL_ROBES_TWISTED_KIT, -1),
	COX_CHALLENGE_CAPE_T1("Xeric's guard", Rarity.RARE, ItemID.COX_CHALLENGE_CAPE_T1, -1),
	COX_CHALLENGE_CAPE_T2("Xeric's warrior", Rarity.RARE, ItemID.COX_CHALLENGE_CAPE_T2, -1),
	COX_CHALLENGE_CAPE_T3("Xeric's sentinel", Rarity.EPIC, ItemID.COX_CHALLENGE_CAPE_T3, -1),
	COX_CHALLENGE_CAPE_T4("Xeric's general", Rarity.EPIC, ItemID.COX_CHALLENGE_CAPE_T4, -1),
	COX_CHALLENGE_CAPE_T5("Xeric's champion", Rarity.EPIC, ItemID.COX_CHALLENGE_CAPE_T5, -1),
	// Theatre of Blood
	VERZIKPET("Lil' zik", Rarity.LEGENDARY, ItemID.VERZIKPET, -1),
	SCYTHE_OF_VITUR_UNCHARGED("Scythe of vitur (uncharged)", Rarity.LEGENDARY, ItemID.SCYTHE_OF_VITUR_UNCHARGED, -1),
	GHRAZI_RAPIER("Ghrazi rapier", Rarity.EPIC, ItemID.GHRAZI_RAPIER, -1),
	SANGUINESTI_STAFF_UNCHARGED("Sanguinesti staff (uncharged)", Rarity.EPIC, ItemID.SANGUINESTI_STAFF_UNCHARGED, -1),
	JUSTICIAR_FACEGUARD("Justiciar faceguard", Rarity.RARE, ItemID.JUSTICIAR_FACEGUARD, -1),
	JUSTICIAR_CHESTGUARD("Justiciar chestguard", Rarity.RARE, ItemID.JUSTICIAR_CHESTGUARD, -1),
	JUSTICIAR_LEG_GUARDS("Justiciar legguards", Rarity.RARE, ItemID.JUSTICIAR_LEG_GUARDS, -1),
	INFERNAL_DEFENDER_HILT("Avernic defender hilt", Rarity.EPIC, ItemID.INFERNAL_DEFENDER_HILT, -1),
	VIAL_BLOOD("Vial of blood", Rarity.UNCOMMON, ItemID.VIAL_BLOOD, -1),
	SINHAZA_SHROUD_TIER1("Sinhaza shroud tier 1", Rarity.RARE, ItemID.SINHAZA_SHROUD_TIER1, -1),
	SINHAZA_SHROUD_TIER2("Sinhaza shroud tier 2", Rarity.RARE, ItemID.SINHAZA_SHROUD_TIER2, -1),
	SINHAZA_SHROUD_TIER3("Sinhaza shroud tier 3", Rarity.EPIC, ItemID.SINHAZA_SHROUD_TIER3, -1),
	SINHAZA_SHROUD_TIER4("Sinhaza shroud tier 4", Rarity.EPIC, ItemID.SINHAZA_SHROUD_TIER4, -1),
	SINHAZA_SHROUD_TIER5("Sinhaza shroud tier 5", Rarity.EPIC, ItemID.SINHAZA_SHROUD_TIER5, -1),
	SANGUINE_DUST("Sanguine dust", Rarity.EPIC, ItemID.TOB_HARDMODE_DUST, -1),
	HOLY_ORNAMENT_KIT("Holy ornament kit", Rarity.EPIC, ItemID.TOB_HARDMODE_KIT, -1),
	SANGUINE_ORNAMENT_KIT("Sanguine ornament kit", Rarity.EPIC, ItemID.TOB_HARDMODE_KIT_BLOOD, -1),
	// Tombs of Amascut
	WARDENPET_TUMEKEN("Tumeken's guardian", Rarity.LEGENDARY, ItemID.WARDENPET_TUMEKEN, -1),
	TUMEKENS_SHADOW_UNCHARGED("Tumeken's shadow (uncharged)", Rarity.LEGENDARY, ItemID.TUMEKENS_SHADOW_UNCHARGED, -1),
	ELIDINIS_WARD("Elidinis' ward", Rarity.RARE, ItemID.ELIDINIS_WARD, -1),
	MASORI_MASK("Masori mask", Rarity.EPIC, ItemID.MASORI_MASK, -1),
	MASORI_BODY("Masori body", Rarity.EPIC, ItemID.MASORI_BODY, -1),
	MASORI_CHAPS("Masori chaps", Rarity.EPIC, ItemID.MASORI_CHAPS, -1),
	LIGHTBEARER("Lightbearer", Rarity.RARE, ItemID.LIGHTBEARER, -1),
	OSMUMTENS_FANG("Osmumten's fang", Rarity.EPIC, ItemID.OSMUMTENS_FANG, -1),
	THREAD_OF_ELIDINIS("Thread of elidinis", Rarity.UNCOMMON, ItemID.THREAD_OF_ELIDINIS, -1),
	BREACH_OF_THE_SCARAB("Breach of the scarab", Rarity.RARE, ItemID.BREACH_OF_THE_SCARAB, -1),
	EYE_OF_THE_CORRUPTOR("Eye of the corruptor", Rarity.RARE, ItemID.EYE_OF_THE_CORRUPTOR, -1),
	JEWEL_OF_THE_SUN("Jewel of the sun", Rarity.RARE, ItemID.JEWEL_OF_THE_SUN, -1),
	JEWEL_OF_AMASCUT("Jewel of amascut", Rarity.RARE, ItemID.JEWEL_OF_AMASCUT, -1),
	ELIDINIS_WARD_ORNAMENT_KIT("Menaphite ornament kit", Rarity.EPIC, ItemID.ELIDINIS_WARD_ORNAMENT_KIT, -1),
	OSMUMTENS_FANG_ORNAMENT_KIT("Cursed phalanx", Rarity.EPIC, ItemID.OSMUMTENS_FANG_ORNAMENT_KIT, -1),
	AVAS_ASSEMBLER_ORNAMENT_KIT("Masori crafting kit", Rarity.RARE, ItemID.AVAS_ASSEMBLER_ORNAMENT_KIT, -1),
	TOA_RUNE_CACHE("Cache of runes", Rarity.UNCOMMON, ItemID.TOA_RUNE_CACHE, -1),
	ICTHLARINS_SHROUD_1("Icthlarin's shroud (tier 1)", Rarity.RARE, ItemID.ICTHLARINS_SHROUD_1, -1),
	ICTHLARINS_SHROUD_2("Icthlarin's shroud (tier 2)", Rarity.RARE, ItemID.ICTHLARINS_SHROUD_2, -1),
	ICTHLARINS_SHROUD_3("Icthlarin's shroud (tier 3)", Rarity.EPIC, ItemID.ICTHLARINS_SHROUD_3, -1),
	ICTHLARINS_SHROUD_4("Icthlarin's shroud (tier 4)", Rarity.EPIC, ItemID.ICTHLARINS_SHROUD_4, -1),
	ICTHLARINS_SHROUD_5("Icthlarin's shroud (tier 5)", Rarity.EPIC, ItemID.ICTHLARINS_SHROUD_5, -1),
	TOA_PET_MORPH_AKKHA("Remnant of akkha", Rarity.EPIC, ItemID.TOA_PET_MORPH_AKKHA, -1),
	TOA_PET_MORPH_BABA("Remnant of ba-ba", Rarity.EPIC, ItemID.TOA_PET_MORPH_BABA, -1),
	TOA_PET_MORPH_KEPHRI("Remnant of kephri", Rarity.EPIC, ItemID.TOA_PET_MORPH_KEPHRI, -1),
	TOA_PET_MORPH_ZEBAK("Remnant of zebak", Rarity.EPIC, ItemID.TOA_PET_MORPH_ZEBAK, -1),
	TOA_PET_MORPH_WARDENS("Ancient remnant", Rarity.EPIC, ItemID.TOA_PET_MORPH_WARDENS, -1);

	private final Card card;

	RaidsDropsCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.RAIDS_DROPS, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
