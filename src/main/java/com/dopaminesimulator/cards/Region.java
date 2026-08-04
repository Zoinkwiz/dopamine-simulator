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
package com.dopaminesimulator.cards;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import lombok.Getter;

@Getter
public enum Region
{
	MISTHALIN("Misthalin", "Roots of Misthalin", new Color(0x6F, 0x9B, 0xD1),
		"lumbridge", "draynor", "varrock", "misthalin", "cook", "goblin", "romeo", "ernest",
		"restless ghost", "sheep", "rune mysteries", "dorgesh", "lost tribe", "wizard",
		"imp catcher", "vampyre slayer", "demon slayer", "shield of arrav", "prince ali",
		"tower of life", "what lies below", "swan", "misthalin mystery", "daddy"),

	ASGARNIA("Asgarnia", "Under the White Banner", new Color(0xC7, 0xCE, 0xDA),
		"falador", "asgarnia", "taverley", "burthorpe", "black knight", "white knight",
		"dwarf", "doric", "knight's sword", "recruitment drive", "wanted", "grim tales",
		"death plateau", "troll", "eadgar", "between a rock", "giant dwarf", "forgettable",
		"cabin fever", "heroes", "scorpion catcher", "below ice"),

	KANDARIN("Kandarin", "The Seers' Compact", new Color(0x6E, 0xC6, 0xB4),
		"kandarin", "ardougne", "seers", "catherby", "yanille", "camelot", "merlin",
		"fishing contest", "gnome", "grand tree", "tree gnome", "watchtower", "biohazard",
		"plague city", "sea slug", "slug menace", "elemental workshop", "hazeel",
		"one small favour", "family crest", "monkey madness", "eagles", "fight arena",
		"tourist trap", "holy grail", "murder mystery", "creature of fenkenstrain"),

	MORYTANIA("Morytania", "The Sanguine Vigil", new Color(0x8E, 0x6B, 0xB8),
		"morytania", "canifis", "myreque", "vampyre", "barrows", "nature spirit",
		"priest in peril", "haunted mine", "shades of mort", "ghosts ahoy", "darkness of",
		"sins of the father", "taste of hope", "in aid of", "in search of the", "tarn",
		"theatre of blood", "verzik", "abyssal", "salve", "ivandis", "blisterwood",
		"a night at the theatre", "sanguinesti", "scythe", "ghommal"),

	DESERT("Kharidian Desert", "The Sun-Scarred Waste", new Color(0xE0, 0xB9, 0x62),
		"desert", "al kharid", "menaphos", "sophanem", "nardah", "pollnivneach", "keris",
		"icthlarin", "feud", "tourist", "spirits of the elid", "enakhra", "contact",
		"dealing with scabaras", "beneath cursed sands", "tombs of amascut", "osmumten",
		"tumeken", "elidinis", "masori", "kalphite", "pyramid plunder", "sceptre",
		"desert treasure", "smoke devil", "ugthanki"),

	FREMENNIK("Fremennik", "Songs of the Longhall", new Color(0x7E, 0xA8, 0xD8),
		"fremennik", "rellekka", "neitiznot", "jatizso", "miscellania", "lunar", "dagannoth",
		"waterbirth", "olaf", "royal trouble", "throne of", "blast furnace", "keldagrim",
		"barbarian", "mountain daughter", "viking", "yak", "berserker", "archer ring",
		"seers ring", "warrior ring", "the fremennik", "moon", "perilous"),

	KARAMJA("Karamja", "Ashes of Karamja", new Color(0xD8, 0x7A, 0x4E),
		"karamja", "brimhaven", "shilo", "tai bwo", "jungle", "volcanic", "zogre",
		"jiggig", "legends", "heroes' quest", "grand seed", "nature altar", "agility arena",
		"fight caves", "inferno", "tzhaar", "jad", "zuk", "fire cape", "karambwan",
		"gnome restaurant", "trouble brewing", "cairn isle"),

	WILDERNESS("Wilderness", "No Man's Land", new Color(0xC0, 0x51, 0x51),
		"wilderness", "revenant", "callisto", "venenatis", "vet'ion", "chaos elemental",
		"chaos fanatic", "scorpia", "king black dragon", "corporeal", "mage arena",
		"lava dragon", "abyss", "enter the abyss", "wilderness sword", "last man standing",
		"crazy archaeologist", "deranged", "artio", "calvarion", "spindel", "voidwaker",
		"dragon pickaxe", "curse of the empty"),

	TIRANNWN("Tirannwn", "Beneath the Crystal Canopy", new Color(0x7F, 0xD6, 0x8A),
		"tirannwn", "elf", "prifddinas", "iorwerth", "lletya", "regicide", "underground pass",
		"roving elves", "mourning", "song of the elves", "crystal", "zalcano", "gauntlet",
		"corrupted", "blade of saeldor", "bow of faerdhinen", "enhanced", "path of glouphrie",
		"eyes of glouphrie", "waterfall"),

	KOUREND("Great Kourend", "The Five Houses", new Color(0x5E, 0xB8, 0xC9),
		"kourend", "kebos", "zeah", "arceuus", "hosidius", "lovakengj", "piscarilius",
		"shayzien", "xeric", "chambers of xeric", "twisted", "lizardman", "wintertodt",
		"tempoross", "sulliuscep", "konar", "hydra", "cerberus", "brimstone", "rada",
		"depths of despair", "queen of thieves", "tale of the righteous", "forsaken tower",
		"ascent of arceuus", "kingdom divided", "sins of", "woodcutting guild"),

	VARLAMORE("Varlamore", "Gilded Varlamore", new Color(0xE8, 0x9F, 0x4C),
		"varlamore", "civitas", "avium", "hunter guild", "quetzal", "sunfire", "moons of peril",
		"blood moon", "blue moon", "eclipse", "children of the sun", "twilight", "at first light",
		"perilous moons", "ribbiting", "heart of darkness", "death on the isle",
		"meat and greet", "ethically", "aldarin", "colosseum", "araxxor", "amoxliatl",
		"the final dawn", "hueycoatl");

	private final String area;
	private final String seasonName;
	private final Color colour;
	private final List<String> keywords;

	Region(String area, String seasonName, Color colour, String... keywords)
	{
		this.area = area;
		this.seasonName = seasonName;
		this.colour = colour;
		this.keywords = Collections.unmodifiableList(Arrays.asList(keywords));
	}

	public boolean claims(Card card)
	{
		String name = card.getName().toLowerCase(Locale.ROOT);
		for (String keyword : keywords)
		{
			if (name.contains(keyword))
			{
				return true;
			}
		}
		return false;
	}

	// Cached: a pack used to rebuild this for every card it drew.
	//
	// Only ever pack cards. Regions claim by keyword, so without this filter a
	// seasonal or mythic card whose name happens to mention an area would be
	// dropped by that region's pass packs.
	public List<Card> pool()
	{
		if (pool == null)
		{
			List<Card> mine = new ArrayList<>();
			for (Card card : CardCatalogue.all())
			{
				if (claims(card) && !CardOrigins.isExclusive(card))
				{
					mine.add(card);
				}
			}
			pool = Collections.unmodifiableList(mine);
		}
		return pool;
	}

	private List<Card> pool;

	public static Region forSeason(int season)
	{
		Region[] all = values();
		return all[Math.floorMod(season - 1, all.length)];
	}
}
