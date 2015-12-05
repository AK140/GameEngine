package io.github.ak140.engine;

import javax.sound.sampled.*;

public class SoundManager {

	private final Clip clip;
	private String path;

	public SoundManager(final String path) throws Exception {
		this(path, false);
	}

	public SoundManager(final String path, boolean asStream) throws Exception {
		this.path = path;
		AudioInputStream audioIn;
		if (!asStream) {
			audioIn = AudioSystem.getAudioInputStream(SoundManager.class.getResource(path));
		} else {
			audioIn = AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream(path));
		}
		clip = AudioSystem.getClip();
		clip.open(audioIn);
	}

	public String getPath() {
		return path;
	}

	public Clip getClip() {
		return clip;
	}

	public void start() {
		clip.start();
	}

	public void stopMusic() {
		if (clip.isActive() || clip.isRunning()) {
			clip.stop();
		}
	}

	public void loop(int count) {
		clip.loop(count);
	}
}