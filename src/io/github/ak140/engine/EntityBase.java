package io.github.ak140.engine;

/**
 * A base for entity implemention
 * @author Lambo993
 * @since version 1.1.0
 */
public class EntityBase {

	private int x, y, xVelocity , yVelocity;

	/**
	 * Gets the <code>Entity</code> X Location of the screen
	 * @return the X Location of the screen
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the <code>Entity</code> X Velocity
	 * @return The X velocity speed
	 */
	public int getXVelocity() {
		return xVelocity;
	}

	/**
	 * Gets the <code>Entity</code> Y Location of the screen
	 * @return the Y Location of the screen
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets the <code>Entity</code> Y Velocity
	 * @return The Y velocity speed
	 */
	public int getYVelocity() {
		return yVelocity;
	}

	/**
	 * Sets the <code>Entity</code> X coordinate of the screen
	 * @param x the new X Location of the screen
	 */
	public void setX(int x) {
		this.x = x;
	}

	public void setXVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	/**
	 * Sets the <code>Entity</code> Y coordinate of the screen
	 * @param y the new Y Location of the screen
	 */
	public void setY(int y) {
		this.y = y;
	}

	public void setYVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	public boolean isMoving() {
		return getXVelocity() != 0 || getYVelocity() != 0;
	}
}