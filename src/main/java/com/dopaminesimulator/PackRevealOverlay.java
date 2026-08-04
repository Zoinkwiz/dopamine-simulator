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

import com.dopaminesimulator.cards.Card;
import com.dopaminesimulator.cards.Rarity;
import com.dopaminesimulator.core.DopamineState;
import com.dopaminesimulator.core.Reward;
import com.dopaminesimulator.cosmetics.CardBack;
import com.dopaminesimulator.core.RewardType;
import com.dopaminesimulator.feats.Feat;
import com.dopaminesimulator.ui.CardArtService;
import com.dopaminesimulator.ui.CardRenderer;
import com.dopaminesimulator.ui.FeatBanner;
import com.dopaminesimulator.ui.WishReveal;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Supplier;
import net.runelite.api.Client;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class PackRevealOverlay extends Overlay
{
	private static final int CARD_HEIGHT = 172;
	private static final int CARD_WIDTH = 123;
	private static final int CARD_GAP = 12;
	private static final int MAX_ON_SCREEN = 4;
	private static final long STAGGER_MS = 260L;
	private static final long MIN_STAGGER_MS = 90L;
	private static final long MIN_HOLD_MS = 400L;
	private static final int MAX_PENDING = 8;
	private static final long MAX_QUEUE_AHEAD_MS = 5000L;
	private static final long DEAL_MS = 340L;
	private static final long MIN_DEAL_MS = 120L;
	private static final long MIN_FLIP_MS = 120L;
	private static final long HEADLINE_LEAD_MS = 900L;
	private static final long RAPID_STAGGER_MS = 190L;
	private static final long RAPID_DEAL_MS = 90L;
	static final long RAPID_FLIP_MS = 110L;
	private static final long RAPID_HOLD_MS = 260L;
	private static final long BANNER_ENTRANCE_MS = 260L;
	private static final double BANNER_TOP = 0.06d;
	private static final long BANNER_SPARK_MS = 620L;
	private static final int BANNER_SPARKS = 10;
	private static final Color ACHIEVEMENT = new Color(0xFF, 0xB3, 0x00);
	static final long FLIP_MS = 300L;

	/**
	 * Below this, a flip is not a flip. Half of it is card back and the card is
	 * squashed edge-on for the rest, so a bulk reveal reads as a stutter of grey
	 * rectangles rather than cards. Anything this quick is dealt face up instead.
	 */
	private static final long FACE_UP_BELOW_MS = 160L;
	private static final long HOLD_MS = 1300L;
	private static final long MAJOR_HOLD_MS = 2400L;
	private static final long FADE_MS = 450L;
	private static final int DEAL_FROM_BELOW = 150;

	private static final Color DIM = new Color(0, 0, 0);
	private static final Color CARD_FACE = new Color(0x1B, 0x1B, 0x1B);
	private static final Color CARD_BACK = new Color(0x2B, 0x2F, 0x3A);
	private static final Color CARD_BACK_TRIM = new Color(0x55, 0x5E, 0x72);
	private final Client client;
	private final DopamineSimulatorConfig config;
	private final RevealSoundService sounds;
	private final CardArtService artService;
	private final Supplier<DopamineState> stateSupplier;
	private final Deque<RevealCard> cards = new ConcurrentLinkedDeque<>();
	private long nextAvailableSlot;
	private static final class RevealCard
	{
		private final String title;
		private final String detail;
		private final Rarity rarity;
		private final Color colour;
		private final boolean major;
		private long start;
		private final long holdMs;
		private final long dealMs;
		private final long flipMs;

		// Derived, not passed in: every caller that wants a fast reveal already says
		// so by asking for a short flip, and the wish showcase keeps its full one.
		private final boolean dealtFaceUp;

		private final Card card;
		private final int stars;
		private final boolean shiny;
		private final boolean gilded;
		private int quantity;
		private final boolean stackable;
		private final boolean feat;
		private final boolean wish;
		private final int featTier;
		private boolean dealSoundPlayed;
		private boolean revealSoundPlayed;
		private RevealCard(String title, String detail, Rarity rarity, Color colour,
						   boolean major, long start, Card card, int stars, boolean shiny, boolean gilded, int quantity, boolean stackable,
						   boolean feat, boolean wish, int featTier, long holdMs, long dealMs, long flipMs)
		{
			this.title = title;
			this.detail = detail;
			this.rarity = rarity;
			this.colour = colour;
			this.major = major;
			this.start = start;
			this.card = card;
			this.stars = stars;
			this.shiny = shiny;
			this.gilded = gilded;
			this.quantity = quantity;
			this.stackable = stackable;
			this.feat = feat;
			this.wish = wish;
			this.featTier = featTier;
			this.holdMs = holdMs;
			this.dealMs = dealMs;
			this.flipMs = flipMs;
			this.dealtFaceUp = flipMs < FACE_UP_BELOW_MS;
		}
		private long age()
		{
			return System.currentTimeMillis() - start;
		}
		private long lifetime()
		{
			return dealMs + flipMs + holdMs + FADE_MS;
		}
		private boolean pending()
		{
			return age() < 0;
		}
		private boolean expired()
		{
			return age() > lifetime();
		}
		private boolean fading()
		{
			return age() > lifetime() - FADE_MS;
		}
	}
	PackRevealOverlay(Client client, DopamineSimulatorConfig config, RevealSoundService sounds,
					  CardArtService artService, Supplier<DopamineState> stateSupplier)
	{
		this.client = client;
		this.config = config;
		this.sounds = sounds;
		this.artService = artService;
		this.stateSupplier = stateSupplier;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
	}

	public void push(Reward reward)
	{
		if (reward == null || !config.showRewardFlashes())
		{
			return;
		}

		if (reward.getType() == RewardType.SOURCE_UNLOCKED
			|| reward.getType() == RewardType.SHINY
			|| reward.getType() == RewardType.GILDED)
		{
			return;
		}

		if (reward.getType() == RewardType.BANNER_WIN)
		{
			pushWish(reward);
			return;
		}

		long now = System.currentTimeMillis();
		if (stackOntoExisting(reward))
		{
			return;
		}

		int pending = pendingCount();
		if (pending >= MAX_PENDING)
		{
			return;
		}

		long startAt = Math.max(now, nextAvailableSlot);
		if (startAt - now > MAX_QUEUE_AHEAD_MS)
		{
			return;
		}

		long stagger = staggerFor(pending);
		nextAvailableSlot = startAt + stagger;

		boolean shiny = reward.isShiny();
		boolean gilded = reward.isGilded();

		boolean major = reward.getType() == RewardType.SET_COMPLETE
			|| reward.getType() == RewardType.FEAT
			|| reward.getType() == RewardType.ACHIEVEMENT
			|| reward.getType() == RewardType.BANNER_WIN
			|| shiny
			|| gilded
			|| (reward.getRarity() != null && reward.getRarity().ordinal() >= Rarity.EPIC.ordinal());
		Color colour;
		if (reward.getType() == RewardType.FEAT)
		{
			colour = Feat.tierColour((int) reward.getAmount());
		}
		else if (reward.getType() == RewardType.ACHIEVEMENT)
		{
			colour = ACHIEVEMENT;
		}
		else
		{
			colour = reward.getRarity() != null ? reward.getRarity().getColour() : Color.WHITE;
		}
		int stars = reward.getCard() == null || stateSupplier == null
			? 0
			: stateSupplier.get().getStars(reward.getCard().getId());

		double speed = stagger / (double) STAGGER_MS;
		long hold = Math.max(MIN_HOLD_MS,
			Math.round((major ? MAJOR_HOLD_MS : HOLD_MS) * speed));
		long deal = Math.max(MIN_DEAL_MS, Math.round(DEAL_MS * speed));
		long flip = flipMsFor(stagger);

		cards.addLast(new RevealCard(reward.getTitle(), variantDetail(reward, shiny, gilded),
			reward.getRarity(), colour, major, startAt, reward.getCard(), stars, shiny, gilded, Math.max(1, reward.getCopies()),
			isStackable(reward),
			reward.getType() == RewardType.FEAT || reward.getType() == RewardType.ACHIEVEMENT,
			reward.getType() == RewardType.BANNER_WIN,
			(int) reward.getAmount(),
			hold, deal, flip));
	}

	// The queue tightens as it fills: more waiting cards, less time each.
	static long staggerFor(int pending)
	{
		return Math.max(MIN_STAGGER_MS, STAGGER_MS - pending * 25L);
	}

	static long flipMsFor(long stagger)
	{
		return Math.max(MIN_FLIP_MS, Math.round(FLIP_MS * (stagger / (double) STAGGER_MS)));
	}

	static boolean dealsFaceUp(long flipMs)
	{
		return flipMs < FACE_UP_BELOW_MS;
	}

	public void pushBatch(List<Reward> ordered)
	{
		if (ordered.isEmpty() || !config.showRewardFlashes())
		{
			return;
		}

		push(ordered.get(0));
		long headlineDone = nextAvailableSlot + HEADLINE_LEAD_MS;

		for (int i = 1; i < ordered.size(); i++)
		{
			Reward reward = ordered.get(i);
			if (reward.getCard() == null)
			{
				continue;
			}
			long startAt = headlineDone + (i - 1) * RAPID_STAGGER_MS;
			cards.addLast(new RevealCard(reward.getTitle(),
				variantDetail(reward, reward.isShiny(), reward.isGilded()),
				reward.getRarity(),
				reward.getRarity() == null ? Color.WHITE : reward.getRarity().getColour(),
				false, startAt, reward.getCard(), 0, reward.isShiny(), reward.isGilded(),
				Math.max(1, reward.getCopies()), false, false, false, 0,
				RAPID_HOLD_MS, RAPID_DEAL_MS, RAPID_FLIP_MS));
			nextAvailableSlot = startAt + RAPID_STAGGER_MS;
		}
	}

	private void pushWish(Reward reward)
	{
		long now = System.currentTimeMillis();
		cards.removeIf(card -> card.wish);

		for (RevealCard queued : cards)
		{
			queued.start = Math.max(queued.start, now + WishReveal.LIFETIME_MS);
		}
		nextAvailableSlot = now + WishReveal.LIFETIME_MS;
		cards.addLast(new RevealCard(reward.getTitle(), reward.getDetail(), reward.getRarity(),
			reward.getRarity() == null ? Color.WHITE : reward.getRarity().getColour(),
			true, now, reward.getCard(), 0, false, false, 1, false, false, true, 0,
			WishReveal.LIFETIME_MS - DEAL_MS - FLIP_MS - FADE_MS, DEAL_MS, FLIP_MS));
	}

	private boolean stackOntoExisting(Reward reward)
	{
		Card card = reward.getCard();
		if (card == null || !isStackable(reward))
		{
			return false;
		}

		for (RevealCard existing : cards)
		{
			if (existing.stackable
				&& existing.card != null
				&& existing.card.getId().equals(card.getId())
				&& !existing.expired()
				&& !existing.fading())
			{
				existing.quantity += Math.max(1, reward.getCopies());
				return true;
			}
		}
		return false;
	}

	private static String variantDetail(Reward reward, boolean shiny, boolean gilded)
	{
		if (shiny && gilded)
		{
			return "Shiny and gilded!";
		}
		if (shiny)
		{
			return "Shiny!";
		}
		if (gilded)
		{
			return "Gilded!";
		}
		return reward.getDetail();
	}

	private static boolean isStackable(Reward reward)
	{
		RewardType type = reward.getType();
		return type != RewardType.SHINY
			&& type != RewardType.GILDED
			&& type != RewardType.FEAT
			&& type != RewardType.ACHIEVEMENT
			&& type != RewardType.BANNER_WIN
			&& type != RewardType.SET_COMPLETE;
	}

	private int pendingCount()
	{
		int pending = 0;
		for (RevealCard card : cards)
		{
			if (card.pending())
			{
				pending++;
			}
		}
		return pending;
	}

	public void clear()
	{
		cards.clear();
		nextAvailableSlot = 0L;
	}

	public void makeWayForBatch()
	{
		cards.removeIf(RevealCard::pending);
		nextAvailableSlot = System.currentTimeMillis();
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		// RuneLite sets the font once a layer, not once an overlay.
		java.awt.Font fontBefore = graphics.getFont();
		if (!config.showRewardFlashes())
		{
			cards.clear();
			graphics.setFont(fontBefore);
			return null;
		}
		for (Iterator<RevealCard> it = cards.iterator(); it.hasNext(); )
		{
			if (it.next().expired())
			{
				it.remove();
			}
		}
		if (cards.isEmpty())
		{
			graphics.setFont(fontBefore);
			return null;
		}
		List<RevealCard> visible = new ArrayList<>();
		for (RevealCard card : cards)
		{
			if (!card.pending())
			{
				visible.add(card);
			}
		}
		if (visible.isEmpty())
		{
			graphics.setFont(fontBefore);
			return null;
		}

		if (visible.size() > MAX_ON_SCREEN)
		{
			visible = visible.subList(visible.size() - MAX_ON_SCREEN, visible.size());
		}

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Composite originalComposite = graphics.getComposite();
		AffineTransform originalTransform = graphics.getTransform();
		int canvasWidth = client.getCanvasWidth();
		int canvasHeight = client.getCanvasHeight();
		int centreX = canvasWidth / 2;
		int rowY = (int) (canvasHeight * 0.34d);

		for (RevealCard card : visible)
		{
			if (card.wish && card.card != null)
			{
				drawDim(graphics, visible, canvasWidth, canvasHeight);
				WishReveal.draw(graphics, canvasWidth, canvasHeight, card.card,
					artService.get(card.card), card.age(), cardAlpha(card));
				graphics.setComposite(originalComposite);
				graphics.setTransform(originalTransform);
				graphics.setFont(fontBefore);
				return null;
			}
		}

		List<RevealCard> banners = new ArrayList<>();
		List<RevealCard> inRow = new ArrayList<>();
		for (RevealCard card : visible)
		{
			(card.feat ? banners : inRow).add(card);
		}

		drawDim(graphics, inRow, canvasWidth, canvasHeight);

		int totalWidth = inRow.size() * CARD_WIDTH + (inRow.size() - 1) * CARD_GAP;
		int startX = centreX - totalWidth / 2;

		for (int i = 0; i < inRow.size(); i++)
		{
			int slotX = startX + i * (CARD_WIDTH + CARD_GAP);
			drawCard(graphics, inRow.get(i), slotX, rowY);
		}

		int bannerY = (int) (canvasHeight * BANNER_TOP);
		for (int i = 0; i < banners.size(); i++)
		{
			drawBanner(graphics, banners.get(i), centreX - FeatBanner.WIDTH / 2,
				bannerY + i * (FeatBanner.HEIGHT + 8));
		}

		graphics.setComposite(originalComposite);
		graphics.setTransform(originalTransform);
		graphics.setFont(fontBefore);
		return null;
	}
	private void drawDim(Graphics2D graphics, List<RevealCard> visible, int width, int height)
	{
		if (!config.dimScreenOnReveal())
		{
			return;
		}

		float strongest = 0f;
		for (RevealCard card : visible)
		{
			strongest = Math.max(strongest, cardAlpha(card));
		}

		if (strongest <= 0f)
		{
			return;
		}
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, strongest * 0.45f));
		graphics.setColor(DIM);
		graphics.fillRect(0, 0, width, height);
	}
	private void drawStackBehind(Graphics2D graphics, RevealCard card)
	{
		if (card.quantity < 2)
		{
			return;
		}

		int layers = Math.min(3, card.quantity - 1);
		int step = Math.max(3, CARD_HEIGHT / 34);
		for (int i = layers; i >= 1; i--)
		{
			int offset = step * i;
			graphics.setColor(new Color(0x14, 0x14, 0x18, 190));
			graphics.fillRoundRect(offset, -offset, CARD_WIDTH, CARD_HEIGHT, 10, 10);
			graphics.setColor(card.colour == null
				? CARD_BACK_TRIM
				: new Color(card.colour.getRed(), card.colour.getGreen(), card.colour.getBlue(),
					110 - i * 20));
			graphics.setStroke(new BasicStroke(1f));
			graphics.drawRoundRect(offset, -offset, CARD_WIDTH - 1, CARD_HEIGHT - 1, 10, 10);
		}
	}

	private static final Color STACK_COUNT = new Color(0xFF, 0xD9, 0x1F);

	private void drawStackCount(Graphics2D graphics, RevealCard card)
	{
		if (card.quantity < 2)
		{
			return;
		}

		String label = "x" + card.quantity;
		graphics.setFont(FontManager.getRunescapeBoldFont());
		FontMetrics metrics = graphics.getFontMetrics();
		int x = (CARD_WIDTH - metrics.stringWidth(label)) / 2;
		int y = 6 + metrics.getAscent();

		graphics.setColor(new Color(0, 0, 0, 210));
		graphics.drawString(label, x + 1, y + 1);
		graphics.setColor(STACK_COUNT);
		graphics.drawString(label, x, y);
	}

	private void drawBanner(Graphics2D graphics, RevealCard card, int slotX, int slotY)
	{
		long age = card.age();
		float alpha = cardAlpha(card);
		if (alpha <= 0f)
		{
			return;
		}

		double raw = clamp01(age / (double) BANNER_ENTRANCE_MS);
		double entrance = smoothstep(raw);
		int y = (int) Math.round(slotY + (1d - entrance) * 14d);
		if (raw >= 1d && !card.revealSoundPlayed)
		{
			card.revealSoundPlayed = true;
			sounds.cardRevealed(Rarity.EPIC);
		}

		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
			(float) (alpha * entrance)));
		AffineTransform before = graphics.getTransform();
		graphics.translate(slotX + FeatBanner.WIDTH / 2d, y + FeatBanner.HEIGHT / 2d);

		double pop = 0.86d + 0.14d * backOut(raw);
		graphics.scale(pop, pop);
		graphics.translate(-FeatBanner.WIDTH / 2d, -FeatBanner.HEIGHT / 2d);

		drawBannerGlow(graphics, card, alpha);
		FeatBanner.draw(graphics, card.title, card.detail, card.colour, card.featTier,
			clamp01((age - BANNER_ENTRANCE_MS) / 900d));
		drawBannerSparks(graphics, card, alpha);
		graphics.setTransform(before);
	}

	private void drawBannerGlow(Graphics2D graphics, RevealCard card, float alpha)
	{
		double pulse = 0.75d + 0.25d * Math.sin(card.age() / 190d);
		Composite before = graphics.getComposite();
		for (int i = 5; i >= 1; i--)
		{
			int spread = i * 5;
			int a = (int) (alpha * pulse * (16 - i * 2));
			if (a <= 0)
			{
				continue;
			}
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				Math.min(1f, a / 255f * 4f)));
			graphics.setColor(card.colour);
			graphics.fillRoundRect(-spread, -spread, FeatBanner.WIDTH + spread * 2,
				FeatBanner.HEIGHT + spread * 2, 12 + spread, 12 + spread);
		}
		graphics.setComposite(before);
	}

	private void drawBannerSparks(Graphics2D graphics, RevealCard card, float alpha)
	{
		long age = card.age();
		double life = clamp01((age - BANNER_ENTRANCE_MS / 2L) / (double) BANNER_SPARK_MS);
		if (life <= 0d || life >= 1d)
		{
			return;
		}

		Composite before = graphics.getComposite();
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
			(float) (alpha * (1d - life))));
		graphics.setColor(card.colour);
		graphics.setStroke(new BasicStroke(2f));

		double centreX = 42d;
		double centreY = FeatBanner.HEIGHT / 2d;
		double travel = 14d + life * 26d;
		for (int i = 0; i < BANNER_SPARKS; i++)
		{
			double angle = Math.PI * 2d * i / BANNER_SPARKS + life * 0.6d;
			double x = centreX + Math.cos(angle) * travel;
			double y = centreY + Math.sin(angle) * travel;
			double tail = 4d + (1d - life) * 5d;
			graphics.drawLine((int) Math.round(x), (int) Math.round(y),
				(int) Math.round(x + Math.cos(angle) * tail),
				(int) Math.round(y + Math.sin(angle) * tail));
		}
		graphics.setComposite(before);
	}

	private static double backOut(double t)
	{
		double s = 1.9d;
		double p = t - 1d;
		return 1d + p * p * ((s + 1d) * p + s);
	}

	private void drawCard(Graphics2D graphics, RevealCard card, int slotX, int slotY)
	{
		long age = card.age();
		float alpha = cardAlpha(card);
		if (alpha <= 0f)
		{
			return;
		}

		double dealProgress = clamp01(age / (double) card.dealMs);
		double eased = smoothstep(dealProgress);
		int y = (int) (slotY + (1d - eased) * DEAL_FROM_BELOW);
		if (dealProgress >= 1d && !card.dealSoundPlayed)
		{
			card.dealSoundPlayed = true;
			sounds.cardDealt();
		}

		double scaleX;
		boolean faceUp;
		if (card.dealtFaceUp)
		{
			// Already turned over on the way in. The card still flies up into its
			// slot, so a bulk open reads as a hand of cards going past rather than
			// a row of backs that turn over after you have stopped looking. flipMs
			// is left in the lifetime on purpose: it becomes extra legible time.
			scaleX = 1d;
			faceUp = true;
		}
		else
		{
			long flipAge = age - card.dealMs;
			double flipProgress = flipAge <= 0 ? 0d : clamp01(flipAge / (double) card.flipMs);
			scaleX = Math.max(0.06d, Math.abs(Math.cos(Math.PI * flipProgress)));
			faceUp = flipProgress >= 0.5d;
		}

		// Face-up cards hold the reveal until they land, so it still lines up with
		// the card arriving rather than firing while it is off the bottom.
		if (faceUp && !card.revealSoundPlayed && (!card.dealtFaceUp || dealProgress >= 1d))
		{
			card.revealSoundPlayed = true;
			sounds.cardRevealed(card.rarity == null ? Rarity.COMMON : card.rarity);
		}
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		AffineTransform beforeCard = graphics.getTransform();
		graphics.translate(slotX + CARD_WIDTH / 2d, y + CARD_HEIGHT / 2d);
		graphics.scale(scaleX, 1d);
		graphics.translate(-CARD_WIDTH / 2d, -CARD_HEIGHT / 2d);
		if (faceUp)
		{
			drawStackBehind(graphics, card);
			drawFace(graphics, card, alpha);
			drawStackCount(graphics, card);
		}
		else
		{
			drawBack(graphics);
		}
		graphics.setTransform(beforeCard);
	}
	private void drawFace(Graphics2D graphics, RevealCard card, float alpha)
	{
		if (card.major)
		{
			drawGlow(graphics, card, alpha);
		}

		if (card.card != null)
		{
			CardRenderer.draw(graphics, card.card, 0, 0, CARD_WIDTH, CARD_HEIGHT,
				card.stars, true, System.currentTimeMillis(), artService.get(card.card),
				card.shiny, card.gilded);
			return;
		}

		graphics.setColor(CARD_FACE);
		graphics.fillRoundRect(0, 0, CARD_WIDTH, CARD_HEIGHT, 8, 8);

		graphics.setColor(card.colour);
		graphics.setStroke(new BasicStroke(card.major ? 2.5f : 1.5f));
		graphics.drawRoundRect(0, 0, CARD_WIDTH, CARD_HEIGHT, 8, 8);
		graphics.fillRoundRect(4, 5, CARD_WIDTH - 8, 5, 4, 4);
		graphics.setFont(FontManager.getRunescapeSmallFont());
		graphics.setColor(Color.WHITE);
		drawWrapped(graphics, card.title, CARD_WIDTH, 30);
		if (card.detail != null && !card.detail.isEmpty())
		{
			graphics.setColor(Color.GRAY);
			drawCentred(graphics, card.detail, CARD_WIDTH, CARD_HEIGHT - 10);
		}
	}

	private void drawBack(Graphics2D graphics)
	{
		CardBack back = CardBack.STANDARD;
		if (stateSupplier != null)
		{
			back = CardBack.byId(stateSupplier.get().getSelectedBack());
		}

		graphics.setColor(back.getBase());
		graphics.fillRoundRect(0, 0, CARD_WIDTH, CARD_HEIGHT, 8, 8);
		graphics.setColor(back.getTrim());
		graphics.setStroke(new BasicStroke(1.5f));
		graphics.drawRoundRect(0, 0, CARD_WIDTH, CARD_HEIGHT, 8, 8);
		graphics.drawRoundRect(7, 7, CARD_WIDTH - 14, CARD_HEIGHT - 14, 6, 6);

		int cx = CARD_WIDTH / 2;
		int cy = CARD_HEIGHT / 2;
		switch (back.getPattern())
		{
			case RINGS:
				for (int r = 14; r <= 44; r += 10)
				{
					graphics.drawOval(cx - r, cy - r, r * 2, r * 2);
				}
				break;
			case RAYS:
				for (int i = 0; i < 12; i++)
				{
					double angle = Math.PI * 2d * i / 12d;
					graphics.drawLine(cx + (int) (Math.cos(angle) * 14),
						cy + (int) (Math.sin(angle) * 14),
						cx + (int) (Math.cos(angle) * 42),
						cy + (int) (Math.sin(angle) * 42));
				}
				break;
			case LATTICE:
				for (int offset = -CARD_HEIGHT; offset < CARD_WIDTH; offset += 14)
				{
					graphics.drawLine(offset, 8, offset + CARD_HEIGHT, CARD_HEIGHT - 8);
					graphics.drawLine(offset + CARD_HEIGHT, 8, offset, CARD_HEIGHT - 8);
				}
				break;
			default:
				graphics.drawLine(cx, 18, cx, CARD_HEIGHT - 18);
				graphics.drawLine(18, cy, CARD_WIDTH - 18, cy);
				break;
		}
	}

	private void drawGlow(Graphics2D graphics, RevealCard card, float alpha)
	{
		double pulse = 0.7d + 0.3d * Math.sin(card.age() / 170d);
		Composite before = graphics.getComposite();
		Shape clipBefore = graphics.getClip();
		graphics.setClip(null);
		for (int i = 4; i >= 1; i--)
		{
			float glowAlpha = (float) (alpha * pulse * 0.13d / i);
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				Math.max(0f, Math.min(1f, glowAlpha))));
			graphics.setColor(card.colour);
			int spread = i * 4;
			graphics.fillRoundRect(-spread, -spread,
				CARD_WIDTH + spread * 2, CARD_HEIGHT + spread * 2, 14, 14);
		}

		graphics.setClip(clipBefore);
		graphics.setComposite(before);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}
	private static float cardAlpha(RevealCard card)
	{
		long age = card.age();
		if (age < 0)
		{
			return 0f;
		}
		long fadeStart = card.dealMs + card.flipMs + card.holdMs;
		if (age < fadeStart)
		{
			return 1f;
		}
		long into = age - fadeStart;
		return Math.max(0f, 1f - into / (float) FADE_MS);
	}
	private static void drawCentred(Graphics2D graphics, String text, int width, int y)
	{
		FontMetrics metrics = graphics.getFontMetrics();
		graphics.drawString(text, (width - metrics.stringWidth(text)) / 2, y);
	}
	private static void drawWrapped(Graphics2D graphics, String text, int width, int y)
	{
		FontMetrics metrics = graphics.getFontMetrics();
		if (metrics.stringWidth(text) <= width - 8)
		{
			drawCentred(graphics, text, width, y);
			return;
		}
		int split = text.lastIndexOf(' ', text.length() / 2 + 4);
		if (split <= 0)
		{
			split = text.length() / 2;
		}
		drawCentred(graphics, text.substring(0, split).trim(), width, y);
		drawCentred(graphics, text.substring(split).trim(), width, y + metrics.getHeight());
	}
	private static double clamp01(double value)
	{
		return value < 0d ? 0d : Math.min(value, 1d);
	}
	private static double smoothstep(double t)
	{
		double clamped = clamp01(t);
		return clamped * clamped * (3d - 2d * clamped);
	}
}
