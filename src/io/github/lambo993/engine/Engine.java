package io.github.lambo993.engine;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public abstract class Engine extends JFrame implements Runnable {

	private static final long serialVersionUID = 8091963846194630714L;
	private boolean isEnabled = false;
	private static SoundManager sm;
	private static boolean isMuted = false;
	private static long lastPressMs = 0;
	private int score = 0;
	public static final Logger LOGGER = Logger.getLogger("Main");

	public Engine() {
		this(null, 0, 0, false);
	}

	protected Engine(String title, int width, int height, boolean createWindows) throws HeadlessException {
		if (createWindows) {
			setEnabled(true);
			setSize(width, height);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			setTitle(title);
		}
	}

	@Override
	public void paint(Graphics g) {
		BufferedImage dbImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D dbg = dbImg.createGraphics();
		draw(dbg);
		g.drawImage(dbImg, 0, 0, this);
	}

	public abstract void draw(final Graphics2D g);

	/**
	 * Loads an Image
	 * @param path Path to the Image File
	 * @param useDirectory true for load image in jar false for comp directory
	 * @return the loaded <code>Image</code> object
	 * @since version 1.7.8_Alpha
	 */
	public static Image loadImage(String path, boolean useDirectory) {
		if (path == null) {
			throw new IllegalArgumentException("path cannot be null!");
		}
		ImageIcon img;
		if (!useDirectory) {
			img = new ImageIcon(Engine.class.getResource(path));
			return img.getImage();
		} else {
			img = new ImageIcon(path);
			return img.getImage();
		}
	}

	public static boolean isMuted() {
		return isMuted;
	}

	public static void setMuted(boolean muted) {
		isMuted  = muted;
	}

	/**
	 * Plays The sound
	 * @param path Path to the Sound File
	 * @param loop How Many Times The Sound Loop
	 * @since version 1.7.4_Alpha
	 */
	public static void playSound(final String path, final int loop) {
		try {
			if (!isMuted()) {
				sm = new SoundManager(path);
				if (loop != 0) {
					sm.loop(loop);
				} else {
					sm.start();
				}
			} else {
				sm.stopMusic();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.warning("Error: " + ex.getMessage());
			setMuted(true);
		}
	}

	/**
	 * Delays the bullet shooting
	 * @param delay How much the delay in Milliseconds
	 * @return True if it's longer than the delay time false otherwise
	 * @since version 1.7.5_Alpha
	 */
	public static boolean delayButton(long delay) {
		if (System.currentTimeMillis() - lastPressMs  < delay) {
			return false;
		}
		lastPressMs = System.currentTimeMillis();
		return true;
	}

	@Override
	public void setEnabled(final boolean enabled) {
		if (isEnabled() != enabled) {
			isEnabled = enabled;

			if (isEnabled) {
				onEnable();
			} else {
				try {
					onDisable();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error on disabling forcing close", "Disabling Error", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	protected abstract void onEnable();

	protected abstract void onDisable() throws Exception;

	/**
	 * Gets the score
	 * @return The Value of the Score
	 * @since version 1.4_Alpha
	 */
	public int getScore() {
		if (score < 0) {
			score = 0;
		}
		return score;
	}

	/**
	 * Sets the score
	 * @param score sets the score
	 * @since version 1.4_Alpha
	 */
	public void setScore(int newScore) {
		score = newScore;
	}

	/**
	 * For adding scores
	 * @param addScore Adds the score
	 * @throws IllegalArgumentException When using negatives to <code>addScore</code>
	 * @since version 1.5_Alpha
	 */
	public void addScore(int addScore) {
		score += addScore;
		if (addScore < 0) {
			throw new IllegalArgumentException("You can't use negative");
		}
	}

	/**
	 * Removes the score if the score is more than 0
	 * @param reduceScore Removes the score
	 * @throws IllegalArgumentException When using negatives to <code>removeScore</code>
	 * @since version 1.5_Alpha
	 */
	public void reduceScore(int reduceScore) {
		if (score > 0) {
			score -= reduceScore;
		}
		if (reduceScore < 0) {
			throw new IllegalArgumentException("You can't use negative");
		}
	}
}