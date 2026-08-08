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

import com.dopaminesimulator.cards.Rarity;
import java.io.ByteArrayInputStream;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.audio.AudioPlayer;

@Slf4j
@Singleton
public class RevealSoundService
{
	private static final int SAMPLE_RATE = 44_100;
	private static final int WAV_HEADER_BYTES = 44;
	private static final int EDGE_FADE_SAMPLES = 220;
	private final AudioPlayer audioPlayer;
	private final DopamineSimulatorConfig config;
	@Inject
	public RevealSoundService(AudioPlayer audioPlayer, DopamineSimulatorConfig config)
	{
		this.audioPlayer = audioPlayer;
		this.config = config;
	}

	public void cardDealt()
	{
		play(320d, 70, 0.20d, 26d);
	}

	public void strike(boolean highTier)
	{
		play(highTier ? 62d : 82d, highTier ? 420 : 260, 0.34d, 9d);
	}

	public void starStamped(int index, int total)
	{
		double frequency = 523.25d * Math.pow(2d, index * 2d / 12d);
		boolean last = index == total - 1;
		if (last)
		{
			frequency *= 1.5d;
		}
		play(frequency, last ? 520 : 240, last ? 0.30d : 0.22d, last ? 6d : 16d);
	}

	public void cardRevealed(Rarity rarity)
	{
		double frequency;
		int lengthMs;
		switch (rarity)
		{
			case LEGENDARY:
				frequency = 1174.7d;
				lengthMs = 620;
				break;
			case EPIC:
				frequency = 880.0d;
				lengthMs = 480;
				break;
			case RARE:
				frequency = 659.3d;
				lengthMs = 340;
				break;
			case UNCOMMON:
				frequency = 523.3d;
				lengthMs = 220;
				break;
			default:
				frequency = 440.0d;
				lengthMs = 150;
				break;
		}
		play(frequency, lengthMs, 0.28d, rarity.isPityWorthy() ? 5d : 14d);
	}
	private void play(double frequency, int lengthMs, double amplitude, double decayPerSecond)
	{
		if (!config.playRevealSounds())
		{
			return;
		}

		try
		{
			byte[] wav = synthesise(frequency, lengthMs, amplitude, decayPerSecond);
			audioPlayer.play(new ByteArrayInputStream(wav), gainDecibels(config.revealVolume() / 100d));
		}
		catch (Exception e)
		{
			log.debug("Could not play reveal sound", e);
		}
	}
	private static float gainDecibels(double linear)
	{
		if (linear <= 0d)
		{
			return -80f;
		}
		return (float) (20d * Math.log10(Math.min(1d, linear)));
	}
	private static byte[] synthesise(double frequency, int lengthMs, double amplitude,
									 double decayPerSecond)
	{
		int sampleCount = SAMPLE_RATE * lengthMs / 1000;
		byte[] out = new byte[WAV_HEADER_BYTES + sampleCount * 2];

		writeWavHeader(out, sampleCount);
		for (int i = 0; i < sampleCount; i++)
		{
			double t = i / (double) SAMPLE_RATE;
			double envelope = Math.exp(-t * decayPerSecond);
			double value = Math.sin(2d * Math.PI * frequency * t)
				+ 0.30d * Math.sin(4d * Math.PI * frequency * t);
			value *= envelope * amplitude * edgeFade(i, sampleCount);

			short sample = (short) Math.max(Short.MIN_VALUE,
				Math.min(Short.MAX_VALUE, value * Short.MAX_VALUE));
			int offset = WAV_HEADER_BYTES + i * 2;
			out[offset] = (byte) (sample & 0xFF);
			out[offset + 1] = (byte) ((sample >> 8) & 0xFF);
		}
		return out;
	}
	private static double edgeFade(int index, int sampleCount)
	{
		if (index < EDGE_FADE_SAMPLES)
		{
			return index / (double) EDGE_FADE_SAMPLES;
		}
		int fromEnd = sampleCount - index;
		if (fromEnd < EDGE_FADE_SAMPLES)
		{
			return fromEnd / (double) EDGE_FADE_SAMPLES;
		}
		return 1d;
	}
	private static void writeWavHeader(byte[] out, int sampleCount)
	{
		int dataBytes = sampleCount * 2;
		int byteRate = SAMPLE_RATE * 2;
		writeAscii(out, 0, "RIFF");
		writeIntLE(out, 4, 36 + dataBytes);
		writeAscii(out, 8, "WAVE");
		writeAscii(out, 12, "fmt ");
		writeIntLE(out, 16, 16);
		writeShortLE(out, 20, (short) 1);
		writeShortLE(out, 22, (short) 1);
		writeIntLE(out, 24, SAMPLE_RATE);
		writeIntLE(out, 28, byteRate);
		writeShortLE(out, 32, (short) 2);
		writeShortLE(out, 34, (short) 16);
		writeAscii(out, 36, "data");
		writeIntLE(out, 40, dataBytes);
	}
	private static void writeAscii(byte[] out, int offset, String text)
	{
		for (int i = 0; i < text.length(); i++)
		{
			out[offset + i] = (byte) text.charAt(i);
		}
	}
	private static void writeIntLE(byte[] out, int offset, int value)
	{
		out[offset] = (byte) (value & 0xFF);
		out[offset + 1] = (byte) ((value >> 8) & 0xFF);
		out[offset + 2] = (byte) ((value >> 16) & 0xFF);
		out[offset + 3] = (byte) ((value >> 24) & 0xFF);
	}
	private static void writeShortLE(byte[] out, int offset, short value)
	{
		out[offset] = (byte) (value & 0xFF);
		out[offset + 1] = (byte) ((value >> 8) & 0xFF);
	}
}
