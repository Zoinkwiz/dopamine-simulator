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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
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
		public int itemId;
		public int spriteId;
	}

	public static final class CollectionEntry
	{
		public String name;
		public String set;
		public String description;
		public List<String> members;
	}

	public static final class OriginEntry
	{
		public String pool;
		public String origin;
		public List<String> members;
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
		return read(CARDS, new TypeToken<List<Entry>>()
		{
		}.getType());
	}

	public static List<CollectionEntry> loadCollections()
	{
		return read(COLLECTIONS, new TypeToken<List<CollectionEntry>>()
		{
		}.getType());
	}

	public static List<OriginEntry> loadOrigins()
	{
		return read(ORIGINS, new TypeToken<List<OriginEntry>>()
		{
		}.getType());
	}

	private static <T> List<T> read(String resource, Type type)
	{
		try (InputStream in = CardData.class.getResourceAsStream(resource))
		{
			if (in == null)
			{
				throw new IllegalStateException("missing resource " + resource);
			}

			try (Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8))
			{
				List<T> entries = new Gson().fromJson(reader, type);
				if (entries == null || entries.isEmpty())
				{
					throw new IllegalStateException(resource + " parsed to nothing");
				}
				return entries;
			}
		}
		catch (IOException ex)
		{
			throw new UncheckedIOException("could not read " + resource, ex);
		}
	}
}
