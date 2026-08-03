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
package com.dopaminesimulator.ui;

import com.dopaminesimulator.incremental.BigNumbers;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleSupplier;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.swing.JComponent;
import javax.swing.Timer;

public class ClickButton extends JComponent
{
	private static final int HEIGHT = 100;
	private static final int FRAME_MS = 33;
	private static final long PARTICLE_LIFETIME_MS = 900L;
	private static final long PRESS_LIFETIME_MS = 160L;
	private static final int MAX_PARTICLES = 18;

	private final DoubleSupplier pointsPerClick;
	private final Runnable onClick;
	private final Random random = new Random();
	private final List<Particle> particles = new ArrayList<>();
	private final Timer animator;
	private BufferedImage icon;
	private String status;
	private String danger;
	private boolean surging;
	private boolean hovered;
	private long pressedAt;
	private static final class Particle
	{
		private final String text;
		private final long start;
		private final int driftX;
		private Particle(String text, int driftX)
		{
			this.text = text;
			this.driftX = driftX;
			this.start = System.currentTimeMillis();
		}
		private float progress()
		{
			return Math.min(1f, (System.currentTimeMillis() - start) / (float) PARTICLE_LIFETIME_MS);
		}
		private boolean expired()
		{
			return progress() >= 1f;
		}
	}
	public ClickButton(DoubleSupplier pointsPerClick, Runnable onClick)
	{
		this.pointsPerClick = pointsPerClick;
		this.onClick = onClick;
		setPreferredSize(new Dimension(0, HEIGHT));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, HEIGHT));
		setMinimumSize(new Dimension(0, HEIGHT));
		setOpaque(false);
		setToolTipText("Click for points. Occasionally this surges.");
		animator = new Timer(FRAME_MS, null);
		animator.addActionListener(e ->
		{
			particles.removeIf(Particle::expired);
			repaint();
			if (particles.isEmpty() && !hovered && !surging && danger == null
				&& System.currentTimeMillis() - pressedAt > PRESS_LIFETIME_MS)
			{
				animator.stop();
			}
		});
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				hovered = true;
				wake();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				hovered = false;
				wake();
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				requestFocusInWindow();
				press();
			}
		});
		bindKeys();
	}
	public void setIcon(BufferedImage icon)
	{
		this.icon = icon;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	public void setSurging(boolean surging)
	{
		this.surging = surging;
		if (surging)
		{
			wake();
		}
	}

	/**
	 * Turns the plate red and labels it, for a dish that should not be clicked or
	 * for the sour left by one that was. Null puts it back to gold.
	 */
	public void setDanger(String danger)
	{
		this.danger = danger;
		if (danger != null)
		{
			wake();
		}
	}

	private void bindKeys()
	{
		setFocusable(true);
		getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("SPACE"), "press");
		getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "press");
		getActionMap().put("press", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				press();
			}
		});
		addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				repaint();
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				repaint();
			}
		});
	}

	@Override
	// A bare JComponent has none, and asking for one without this throws.
	public AccessibleContext getAccessibleContext()
	{
		if (accessibleContext == null)
		{
			accessibleContext = new AccessibleJComponent()
			{
				@Override
				public AccessibleRole getAccessibleRole()
				{
					return AccessibleRole.PUSH_BUTTON;
				}
			};
			accessibleContext.setAccessibleName("Collect points");
		}
		return accessibleContext;
	}

	private void press()
	{
		pressedAt = System.currentTimeMillis();
		spawnParticle();
		wake();
		onClick.run();
	}

	private void wake()
	{
		if (!animator.isRunning())
		{
			animator.start();
		}
		repaint();
	}
	private void spawnParticle()
	{
		int count = surging ? 3 : 1;
		for (int i = 0; i < count; i++)
		{
			particles.add(new Particle("+" + BigNumbers.format(pointsPerClick.getAsDouble()),
				random.nextInt(70) - 35));
		}
		while (particles.size() > MAX_PARTICLES)
		{
			particles.remove(0);
		}
	}
	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics.create();
		Skin.smooth(g);

		int width = getWidth();
		int height = getHeight();
		int centreX = width / 2;
		int centreY = (height - 16) / 2;
		int plate = (int) (Math.min(width, height - 16) * 0.78d * currentScale());

		drawGlow(g, centreX, centreY, plate);
		if (isFocusOwner())
		{

			g.setStroke(new BasicStroke(1.5f));
			g.setColor(Skin.withAlpha(Skin.GOLD, 170));
			int ring = plate + 8;
			g.drawOval(centreX - ring / 2, centreY - ring / 2, ring, ring);
		}
		drawPlate(g, centreX, centreY, plate);
		drawIcon(g, centreX, centreY, plate);
		drawLabel(g, width, height);
		drawParticles(g, centreX, centreY);

		g.dispose();
	}

	/** Anything worth pulsing over: a dish being served, or one that soured. */
	private boolean loud()
	{
		return surging || danger != null;
	}

	private Color tint()
	{
		if (danger != null)
		{
			return Skin.RED;
		}
		return surging ? Skin.YELLOW : Skin.GOLD;
	}

	private double currentScale()
	{
		double scale = hovered ? 1.04d : 1.0d;
		long sincePress = System.currentTimeMillis() - pressedAt;
		if (sincePress < PRESS_LIFETIME_MS)
		{
			scale *= 0.91d + 0.09d * (sincePress / (double) PRESS_LIFETIME_MS);
		}
		if (loud())
		{
			scale *= 1.03d + 0.03d * Math.sin(System.currentTimeMillis() / 120d);
		}
		return scale;
	}

	private void drawGlow(Graphics2D g, int centreX, int centreY, int plate)
	{
		float radius = plate * (loud() ? 1.35f : 1.0f);
		Color tint = tint();
		g.setPaint(new RadialGradientPaint(
			new Point2D.Float(centreX, centreY), radius,
			new float[]{0f, 1f},
			new Color[]{Skin.withAlpha(tint, loud() ? 120 : hovered ? 70 : 45),
				Skin.withAlpha(tint, 0)},
			MultipleGradientPaint.CycleMethod.NO_CYCLE));
		g.fillOval((int) (centreX - radius), (int) (centreY - radius),
			(int) (radius * 2), (int) (radius * 2));
	}

	private void drawPlate(Graphics2D g, int centreX, int centreY, int plate)
	{
		int x = centreX - plate / 2;
		int y = centreY - plate / 2;

		g.setPaint(new GradientPaint(x, y, Skin.mix(Skin.GOLD_DEEP, Skin.CARD, 0.45f),
			x, y + plate, new Color(0x14, 0x12, 0x10)));
		g.fillOval(x, y, plate, plate);

		g.setStroke(new BasicStroke(2f));
		g.setPaint(new GradientPaint(x, y, tint(),
			x, y + plate, danger != null ? Skin.RED.darker() : Skin.GOLD_DEEP));
		g.drawOval(x, y, plate - 1, plate - 1);

		if (!loud())
		{
			return;
		}

		double pulse = 0.5d + 0.5d * Math.sin(System.currentTimeMillis() / 150d);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
			(float) (0.25d + 0.5d * pulse)));
		g.setStroke(new BasicStroke(1.5f));
		g.setColor(tint());
		int spread = (int) (5 + 7 * pulse);
		g.drawOval(x - spread, y - spread, plate + spread * 2, plate + spread * 2);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	private void drawIcon(Graphics2D g, int centreX, int centreY, int plate)
	{
		if (icon == null)
		{
			return;
		}
		int size = (int) (plate * 0.58d);
		Object previous = g.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g.drawImage(icon, centreX - size / 2, centreY - size / 2, size, size, null);
		if (previous != null)
		{
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, previous);
		}
	}

	private void drawLabel(Graphics2D g, int width, int height)
	{
		if (status != null && !status.isEmpty())
		{
			g.setFont(Skin.body());
			Skin.centred(g, status, 0, width, height - 4, Skin.MUTED);
			return;
		}
		g.setFont(Skin.heading());
		String payout = "+" + BigNumbers.format(pointsPerClick.getAsDouble());
		if (danger != null)
		{
			payout = danger + "  " + payout;
		}
		else if (surging)
		{
			payout = "SURGE  " + payout;
		}
		Skin.centred(g, payout, 0, width, height - 4, tint());
	}

	private void drawParticles(Graphics2D g, int centreX, int centreY)
	{
		Composite before = g.getComposite();
		g.setFont(Skin.body());
		FontMetrics metrics = g.getFontMetrics();
		for (Particle particle : particles)
		{
			float progress = particle.progress();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				Math.max(0f, 1f - progress)));
			int x = centreX - metrics.stringWidth(particle.text) / 2
				+ (int) (particle.driftX * progress);
			Skin.text(g, particle.text, x, centreY - (int) (progress * 46), tint());
		}
		g.setComposite(before);
	}

	public void dispose()
	{
		animator.stop();
	}
}
