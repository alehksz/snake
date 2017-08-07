package snake;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	private int x;
	private int y;
	private int Size;
	private boolean visible = true;

	/**
	 * Instantiates a new entity.
	 *
	 * @param Size the size
	 */
	public Entity(int Size) {
		this.Size = Size;
	}
	/**
	 * Default constructor for Entity
	 */
	public Entity()
	{
		this.Size=10;
		this.x=0;
		this.y=0;
	}
	/**
	 * Gets the x point.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Sets the x point.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Gets the visible.
	 *
	 * @return the visible
	 */
	public boolean getVisible() {
		return visible;
	}
	/**
	 * Gets the y point.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sets the y point.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Sets the postion of x,y point.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void setPostion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Sets the visible.
	 *
	 * @param visible the new visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	 * Move of points x and y from their postion.
	 *
	 * @param dx the dx
	 * @param dy the dy
	 */
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}
	/**
	 * Gets the bound.
	 *
	 * @return the bound
	 */
	public Rectangle getBound() {
		return new Rectangle(x, y, Size, Size);
	}
	/**
	 * Checks if is collision.
	 *
	 * @param e the e
	 * @return true, if is collision
	 */
	public boolean isCollision(Entity e) {
		if (e == this)
			return false;
		return getBound().intersects(e.getBound());
	}
	/**
	 * Render the graphics.
	 *
	 * @param g2d the g2d
	 */
	public void render(Graphics2D g2d) {
		g2d.fillRect(x + 1, y + 1, Size - 2, Size - 2);
	}
	/**
	 * Compare to.
	 *
	 * @param e1 the e 1
	 * @param e2 the e 2
	 * @return true, if successful
	 */
	public boolean CompareTo(Entity e1, Entity e2) {
		if ((e1.getX() == e2.getX()) && (e1.getY() == e2.getY())) {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * return the x and y value in string format
	 */
	public String toString() {
		return ("" + x + "," + y);
	}

}