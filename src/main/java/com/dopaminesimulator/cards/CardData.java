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
