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
package com.dopaminesimulator;

import com.dopaminesimulator.core.DopamineState;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLite;

@Slf4j
@Singleton
public class SaveManager
{
	private static final File SAVE_DIR = new File(RuneLite.RUNELITE_DIR, "dopamine-simulator");
	private final Gson gson;
	@Inject
	public SaveManager(Gson gson)
	{
		this.gson = gson.newBuilder().setPrettyPrinting().create();
	}

	// Defaults used to run only on load, leaving a first session with no backs.
	public static DopamineState freshState()
	{
		DopamineState state = new DopamineState();
		state.ensureInitialised();
		return state;
	}

	public DopamineState load(long accountHash)
	{
		File file = saveFile(accountHash);
		if (!file.exists())
		{
			return freshState();
		}
		try
		{
			String json = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
			DopamineState state = gson.fromJson(json, DopamineState.class);
			if (state == null)
			{
				return freshState();
			}
			state.ensureInitialised();
			return state;
		}
		catch (IOException | JsonSyntaxException | IllegalStateException e)
		{

			log.warn("Could not read Dopamine Simulator save {}, kept a copy at {}",
				file, quarantine(file), e);
			return freshState();
		}
	}
	private File quarantine(File file)
	{
		File copy = new File(SAVE_DIR, file.getName() + ".unreadable");
		for (int i = 1; copy.exists() && i < 100; i++)
		{
			copy = new File(SAVE_DIR, file.getName() + ".unreadable." + i);
		}
		try
		{
			Files.copy(file.toPath(), copy.toPath());
			return copy;
		}
		catch (IOException e)
		{
			log.warn("Could not preserve unreadable save {}", file, e);
			return file;
		}
	}

	public void save(long accountHash, DopamineState state)
	{
		if (state == null)
		{
			return;
		}
		File file = saveFile(accountHash);
		try
		{
			Files.createDirectories(SAVE_DIR.toPath());
			Path temp = new File(SAVE_DIR, file.getName() + ".tmp").toPath();
			Files.write(temp, gson.toJson(state).getBytes(StandardCharsets.UTF_8));
			Files.move(temp, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e)
		{
			log.warn("Could not write Dopamine Simulator save {}", file, e);
		}
	}
	private File saveFile(long accountHash)
	{
		String name = accountHash == -1L ? "default" : Long.toUnsignedString(accountHash);
		return new File(SAVE_DIR, name + ".json");
	}
}
