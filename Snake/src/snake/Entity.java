package snake;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	private int x;
	private int y;
	private int Size;
	
	public Entity(int Size){
		this.Size = Size;
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setPostion(int x, int y)
	{
		this.x= x;
		this.y =y;
	}
	
	public void move(int dx, int dy){
		x +=dx;
		y +=dy;
	}
	public Rectangle getBound(){
		return new Rectangle (x,y,Size, Size);
	}
	
	public boolean isCollision(Entity e){
		if(e == this)return false;
		return getBound().intersects(e.getBound());
	}
	public void render (Graphics2D g2d){
		g2d.fillRect(x +1, y +1, Size-2, Size-2);
	}
	
	

}