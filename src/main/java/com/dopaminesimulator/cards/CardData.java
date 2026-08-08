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

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CardData
{
	private static final String CARDS = "/com/dopaminesimulator/cards/cards.json";

	private static final String COLLECTIONS = "/com/dopaminesimulator/cards/collections.json";

	private static final String ORIGINS = "/com/dopaminesimulator/cards/origins.json";

	private static final String NPC_ART = "/com/dopaminesimulator/cards/npc-card-art.json";

	public static final class Entry
	{
		public String id;
		public String name;
		public String set;
		public String rarity;
		public int itemId = -1;
		public int spriteId = -1;
	}

	public static final class CollectionEntry
	{
		public String name;
		public String set;
		public String description;
		public List<String> members = new ArrayList<>();
	}

	public static final class NpcArtEntry
	{
		public String id;
		public String displayName;
		public int npcId;
		public int animationId;
		public int zoom;
		public int verticalOffset;
		public int backdropColour;
		public int sceneryModelId = -1;
		public int sceneryZoom;
		public int accentColour;
		public String epithet;
		public String roleTag;
		public String mechanicTag;
		public int metalDark;
		public int metalMid;
		public int metalLight;
		public int plateColour;
		public int glowColour;
		public int pillarModelId = -1;
		public int pillarModelIdRight = -1;
		public int pillarZoom;
		public int pillarRotation;
		public int pillarRotationRight;
		public int foreModelId = -1;
		public int foreZoom;
		public int foreRotation;
		public int altNpcId = -1;
		public int altAnimationId = -1;
		public int altZoom;
		public int altVerticalOffset;
		public boolean bloodDrips;
	}

	public static final class OriginEntry
	{
		public String pool;
		public String origin;
		public List<String> members = new ArrayList<>();
	}

	private CardData()
	{
	}

	public static List<Card> load()
	{
		List<Entry> entries = readEntries();
		List<Card> cards = new ArrayList<>(entries.size());
		for (Entry e : entries)
		{
			cards.add(new Card(
				e.id,
				e.name,
				CardSet.valueOf(e.set),
				Rarity.valueOf(e.rarity),
				e.itemId,
				e.spriteId));
		}
		return Collections.unmodifiableList(cards);
	}

	static List<Entry> readEntries()
	{
		return read(CARDS, reader ->
		{
			Entry e = new Entry();
			reader.beginObject();
			while (reader.hasNext())
			{
				switch (reader.nextName())
				{
					case "id":
						e.id = reader.nextString();
						break;
					case "name":
						e.name = reader.nextString();
						break;
					case "set":
						e.set = reader.nextString();
						break;
					case "rarity":
						e.rarity = reader.nextString();
						break;
					case "itemId":
						e.itemId = reader.nextInt();
						break;
					case "spriteId":
						e.spriteId = reader.nextInt();
						break;
					default:
						reader.skipValue();
				}
			}
			reader.endObject();
			return e;
		});
	}

	public static List<CollectionEntry> loadCollections()
	{
		return read(COLLECTIONS, reader ->
		{
			CollectionEntry e = new CollectionEntry();
			reader.beginObject();
			while (reader.hasNext())
			{
				switch (reader.nextName())
				{
					case "name":
						e.name = reader.nextString();
						break;
					case "set":
						e.set = reader.nextString();
						break;
					case "description":
						e.description = reader.nextString();
						break;
					case "members":
						e.members = readStrings(reader);
						break;
					default:
						reader.skipValue();
				}
			}
			reader.endObject();
			return e;
		});
	}

	public static List<OriginEntry> loadOrigins()
	{
		return read(ORIGINS, reader ->
		{
			OriginEntry e = new OriginEntry();
			reader.beginObject();
			while (reader.hasNext())
			{
				switch (reader.nextName())
				{
					case "pool":
						e.pool = reader.nextString();
						break;
					case "origin":
						e.origin = reader.nextString();
						break;
					case "members":
						e.members = readStrings(reader);
						break;
					default:
						reader.skipValue();
				}
			}
			reader.endObject();
			return e;
		});
	}

	public static List<NpcArtEntry> loadNpcArt()
	{
		return read(NPC_ART, reader ->
		{
			NpcArtEntry e = new NpcArtEntry();
			reader.beginObject();
			while (reader.hasNext())
			{
				switch (reader.nextName())
				{
					case "id":
						e.id = reader.nextString();
						break;
					case "displayName":
						e.displayName = reader.nextString();
						break;
					case "epithet":
						e.epithet = reader.nextString();
						break;
					case "roleTag":
						e.roleTag = reader.nextString();
						break;
					case "mechanicTag":
						e.mechanicTag = reader.nextString();
						break;
					case "backdropColour":
						e.backdropColour = colour(reader.nextString());
						break;
					case "accentColour":
						e.accentColour = colour(reader.nextString());
						break;
					case "metalDark":
						e.metalDark = colour(reader.nextString());
						break;
					case "metalMid":
						e.metalMid = colour(reader.nextString());
						break;
					case "metalLight":
						e.metalLight = colour(reader.nextString());
						break;
					case "plateColour":
						e.plateColour = colour(reader.nextString());
						break;
					case "glowColour":
						e.glowColour = colour(reader.nextString());
						break;
					case "npcId":
						e.npcId = reader.nextInt();
						break;
					case "animationId":
						e.animationId = reader.nextInt();
						break;
					case "zoom":
						e.zoom = reader.nextInt();
						break;
					case "verticalOffset":
						e.verticalOffset = reader.nextInt();
						break;
					case "sceneryModelId":
						e.sceneryModelId = reader.nextInt();
						break;
					case "sceneryZoom":
						e.sceneryZoom = reader.nextInt();
						break;
					case "pillarModelId":
						e.pillarModelId = reader.nextInt();
						break;
					case "pillarModelIdRight":
						e.pillarModelIdRight = reader.nextInt();
						break;
					case "pillarZoom":
						e.pillarZoom = reader.nextInt();
						break;
					case "pillarRotation":
						e.pillarRotation = reader.nextInt();
						break;
					case "pillarRotationRight":
						e.pillarRotationRight = reader.nextInt();
						break;
					case "foreModelId":
						e.foreModelId = reader.nextInt();
						break;
					case "foreZoom":
						e.foreZoom = reader.nextInt();
						break;
					case "foreRotation":
						e.foreRotation = reader.nextInt();
						break;
					case "altNpcId":
						e.altNpcId = reader.nextInt();
						break;
					case "altAnimationId":
						e.altAnimationId = reader.nextInt();
						break;
					case "altZoom":
						e.altZoom = reader.nextInt();
						break;
					case "altVerticalOffset":
						e.altVerticalOffset = reader.nextInt();
						break;
					case "bloodDrips":
						e.bloodDrips = reader.nextBoolean();
						break;
					default:
						reader.skipValue();
				}
			}
			reader.endObject();
			return e;
		});
	}

	private static int colour(String hex)
	{
		String digits = hex.startsWith("#") ? hex.substring(1) : hex;
		return Integer.parseInt(digits, 16);
	}

	private interface ElementReader<T>
	{
		T read(JsonReader reader) throws IOException;
	}

	private static List<String> readStrings(JsonReader reader) throws IOException
	{
		List<String> out = new ArrayList<>();
		reader.beginArray();
		while (reader.hasNext())
		{
			out.add(reader.nextString());
		}
		reader.endArray();
		return out;
	}

	private static <T> List<T> read(String resource, ElementReader<T> element)
	{
		try (InputStream in = CardData.class.getResourceAsStream(resource))
		{
			if (in == null)
			{
				throw new IllegalStateException("missing resource " + resource);
			}

			List<T> entries = new ArrayList<>();
			try (Reader chars = new InputStreamReader(in, StandardCharsets.UTF_8);
				JsonReader reader = new JsonReader(chars))
			{
				reader.beginArray();
				while (reader.hasNext())
				{
					entries.add(element.read(reader));
				}
				reader.endArray();
			}

			if (entries.isEmpty())
			{
				throw new IllegalStateException(resource + " parsed to nothing");
			}
			return entries;
		}
		catch (IOException ex)
		{
			throw new UncheckedIOException("could not read " + resource, ex);
		}
	}
}
