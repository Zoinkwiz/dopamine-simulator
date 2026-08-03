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

public enum PrayerCards implements CardGroup
{
	BAT_BONES("Bat bones", Rarity.COMMON, ItemID.BAT_BONES, -1),
	BONES("Bones", Rarity.COMMON, ItemID.BONES, -1),
	BURNT_BONES("Burnt bones", Rarity.COMMON, ItemID.BONES_BURNT, -1),
	MONKEY_BONES("Monkey bones", Rarity.COMMON, ItemID.MM_NORMAL_MONKEY_BONES, -1),
	WOLF_BONES("Wolf bones", Rarity.COMMON, ItemID.WOLF_BONES, -1),
	BABYDRAGON_BONES("Babydragon bones", Rarity.UNCOMMON, ItemID.BABYDRAGON_BONES, -1),
	BABYWYRM_BONES("Babywyrm bones", Rarity.UNCOMMON, ItemID.BABYWYRM_BONES, -1),
	BIG_BONES("Big bones", Rarity.UNCOMMON, ItemID.BIG_BONES, -1),
	JOGRE_BONES("Jogre bones", Rarity.UNCOMMON, ItemID.TBWT_JOGRE_BONES, -1),
	ZOGRE_BONES("Zogre bones", Rarity.UNCOMMON, ItemID.ZOGRE_BONES, -1),
	VESTMENT_ROBE_LEGS("Vestment robe legs", Rarity.UNCOMMON, ItemID.TRAIL_SARADOMIN_ROBE_L, -1),
	VESTMENT_ROBE_TOP("Vestment robe top", Rarity.UNCOMMON, ItemID.TRAIL_SARADOMIN_ROBE_T, -1),
	RALOS_RISE_SACRIFICES("Ralos' Rise sacrifices", Rarity.UNCOMMON, ItemID.JUG_SUNFIRE_WINE_BLESSED, -1),
	HOLY_SANDALS("Holy sandals", Rarity.UNCOMMON, ItemID.HOLY_SANDALS, -1),
	HOLY_WRAP("Holy wrap", Rarity.UNCOMMON, ItemID.HOLY_WRAPS, -1),
	DRAGON_BONES("Dragon bones", Rarity.RARE, ItemID.DRAGON_BONES, -1),
	STRYKEWYRM_BONES("Strykewyrm bones", Rarity.RARE, ItemID.STRYKEWYRM_BONES, -1),
	WYRM_BONES("Wyrm bones", Rarity.RARE, ItemID.WYRM_BONES, -1),
	WYVERN_BONES("Wyvern bones", Rarity.RARE, ItemID.WYVERN_BONES, -1),
	ECTOPLASMATOR("Ectoplasmator", Rarity.RARE, ItemID.SOUL_WARS_ECTOPLASMATOR, -1),
	VESTMENT_CLOAK("Vestment cloak", Rarity.RARE, ItemID.TRAIL_SARADOMIN_CLOAK, -1),
	VESTMENT_MITRE("Vestment mitre", Rarity.RARE, ItemID.TRAIL_SARADOMIN_MITRE, -1),
	ANTLER_GUARD("Antler Guard", Rarity.RARE, ItemID.CUSTODIAN_ANTLER_GUARD, -1),
	USE_BOLOGA_S_GRAPE_BLESSINGS("Use Bologa's grape blessings", Rarity.RARE, ItemID.GRAPE_BLESSING, -1),
	HALLOWED_SEPULCHRE_PORTAL_SACRIFICE("Hallowed Sepulchre - Portal sacrifice", Rarity.RARE, ItemID.HALLOWED_TOKEN, -1),
	DRAKE_BONES("Drake bones", Rarity.EPIC, ItemID.DRAKE_BONES, -1),
	FAYRG_BONES("Fayrg bones", Rarity.EPIC, ItemID.ZOGRE_ANCESTRAL_BONES_FAYG, -1),
	FROST_DRAGON_BONES("Frost dragon bones", Rarity.EPIC, ItemID.FROST_DRAGON_BONES, -1),
	HYDRA_BONES("Hydra bones", Rarity.EPIC, ItemID.HYDRA_BONES, -1),
	LAVA_DRAGON_BONES("Lava dragon bones", Rarity.EPIC, ItemID.LAVA_DRAGON_BONES, -1),
	RAURG_BONES("Raurg bones", Rarity.EPIC, ItemID.ZOGRE_ANCESTRAL_BONES_RAURG, -1),
	CROZIER("Crozier", Rarity.EPIC, ItemID.TRAIL_SARADOMIN_STAFF, -1),
	DEVOUT_BOOTS("Devout boots", Rarity.EPIC, ItemID.DEVOUT_BOOTS, -1),
	VESTMENT_STOLE("Vestment stole", Rarity.EPIC, ItemID.TRAIL_SARADOMIN_SCARF, -1),
	N3RD_AGE_DRUIDIC_ROBES("3rd Age Druidic robes", Rarity.EPIC, ItemID._3A_DRUIDIC_TOP, -1),
	N3RD_AGE_DRUIDIC_STAFF("3rd Age Druidic staff", Rarity.EPIC, ItemID._3A_DRUIDIC_STAFF, -1),
	BONECRUSHER_NECKLACE("Bonecrusher necklace", Rarity.EPIC, ItemID.BONECRUSHER_NECKLACE, -1),
	DRAGONBONE_NECKLACE("Dragonbone necklace", Rarity.EPIC, ItemID.DRAGONBONE_NECKLACE, -1),
	SOUL_CAPE("Soul cape", Rarity.EPIC, ItemID.SOUL_CAPE_BLUE, -1),
	USE_SUPERIOR_DRAGON_BONES("Use superior dragon bones", Rarity.EPIC, ItemID.DRAGON_BONES_SUPERIOR, -1),
	DAGANNOTH_BONES("Dagannoth bones", Rarity.LEGENDARY, ItemID.DAGANNOTH_KING_BONES, -1),
	OURG_BONES("Ourg bones", Rarity.LEGENDARY, ItemID.ZOGRE_ANCESTRAL_BONES_OURG, -1),
	SUPERIOR_DRAGON_BONES("Superior dragon bones", Rarity.LEGENDARY, ItemID.DRAGON_BONES_SUPERIOR, -1),
	FORTIFY_ELIDINIS_WARD("Fortify Elidinis' Ward", Rarity.LEGENDARY, ItemID.ELIDINIS_WARD_FORTIFIED, -1);

	private final Card card;

	PrayerCards(String name, Rarity rarity, int itemId, int spriteId)
	{
		this.card = CardGroup.build(CardSet.PRAYER, name, rarity, itemId, spriteId);
	}

	@Override
	public Card getCard()
	{
		return card;
	}
}
