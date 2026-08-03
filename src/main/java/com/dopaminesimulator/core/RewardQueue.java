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
package com.dopaminesimulator.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class RewardQueue
{
	private final Deque<Reward> pending = new ConcurrentLinkedDeque<>();
	private final List<Consumer<RewardQueue>> listeners = new CopyOnWriteArrayList<>();
	public void push(Reward reward)
	{
		pending.addLast(reward);
		notifyListeners();
	}
	public int depth()
	{
		return pending.size();
	}
	public boolean isEmpty()
	{
		return pending.isEmpty();
	}

	public Reward claim()
	{
		Reward reward = pending.pollFirst();
		if (reward != null)
		{
			notifyListeners();
		}
		return reward;
	}
	public List<Reward> claimAll()
	{
		List<Reward> all = new ArrayList<>();
		Reward reward;
		while ((reward = pending.pollFirst()) != null)
		{
			all.add(reward);
		}
		if (all.isEmpty())
		{
			return Collections.emptyList();
		}
		notifyListeners();
		return all;
	}

	public void addListener(Consumer<RewardQueue> listener)
	{
		listeners.add(listener);
	}
	public void clearListeners()
	{
		listeners.clear();
	}

	private void notifyListeners()
	{
		for (Consumer<RewardQueue> listener : listeners)
		{
			listener.accept(this);
		}
	}
}
