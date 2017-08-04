package snake;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	private int x;
	private int y;
	private int Size;
	private boolean visible = true;

	public Entity(int Size) {
		this.Size = Size;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean getVisible() {
		return visible;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPostion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public Rectangle getBound() {
		return new Rectangle(x, y, Size, Size);
	}

	public boolean isCollision(Entity e) {
		if (e == this)
			return false;
		return getBound().intersects(e.getBound());
	}

	public void render(Graphics2D g2d) {
		g2d.fillRect(x + 1, y + 1, Size - 2, Size - 2);
	}

	public boolean CompareTo(Entity e1, Entity e2) {
		if ((e1.getX() == e2.getX()) && (e1.getY() == e2.getY())) {
			return true;
		} else {
			return false;
		}

	}

	public String toString() {
		return ("" + x + "," + y);
	}

}