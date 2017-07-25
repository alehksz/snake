package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener, ActionListener {

	private static final int width = 400;
	private static final int height = 400;

	private Graphics2D g2d;
	private BufferedImage image;
	
	private Thread thread;
	private boolean running;
	private long targetTime;
	
	private int SIZE = 10;
	private Entity head;
	private Entity apple;
	private ArrayList<Entity> snake;
	private int Score;
	
	private int dx;
	private int dy;
	
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	private boolean start;
	
	
	
	public GamePanel(){
		setPreferredSize(new Dimension(width,height));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);	
		
	}
	
	@Override
	public void addNotify(){
		super.addNotify();
		thread = new Thread (this);
		thread.start();
	}
	private void setFPS(int fps){
		targetTime =1000/fps;
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		
		if (key == KeyEvent.VK_UP) up = true;
		if (key == KeyEvent.VK_DOWN) down = true;
		if (key == KeyEvent.VK_LEFT) left = true;
		if (key == KeyEvent.VK_RIGHT) right = true;
		if (key == KeyEvent.VK_ENTER) start = true;
	}

	@Override
	public void keyReleased(KeyEvent k) {
		int key = k.getKeyCode();
		
		if (key == KeyEvent.VK_UP) up = false;
		if (key == KeyEvent.VK_DOWN) down = false;
		if (key == KeyEvent.VK_LEFT) left = false;
		if (key == KeyEvent.VK_RIGHT) right = false;
		if (key == KeyEvent.VK_ENTER) start = false;
	}

	@Override
	public void keyTyped(KeyEvent k) {
		
	}

	@Override
	public void run() {
		if(running) return;
		init();
		long startTime;
		long elapsed;
		long wait;
		while(running){
			startTime = System.nanoTime();
			update();
			requestRender();
			
			elapsed = System.nanoTime() - startTime;
			wait = targetTime -elapsed/1000000;
			if(wait>0){
				try{
					Thread.sleep(wait);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private void init() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2d = image.createGraphics();
		running =true;
		setUpLevel();
		setFPS(10);
	}
	
	private void setUpLevel(){
		snake = new ArrayList<Entity>();
		head = new Entity(SIZE);
		head.setPostion(width/2, height/2);
		snake.add(head);
		
		for(int i =1; i < 4; i++){
			Entity e =new Entity(SIZE);
			e.setPostion(head.getX()+(i*SIZE), head.getY());
			snake.add(e);
		}
		
		apple = new Entity(SIZE);
		setApple();
		Score =0;
	}
	
	public void setApple(){
		int x = (int)(Math.random()*(width-SIZE));
		int y = (int)(Math.random()*(height - SIZE));
		x = x-(x % SIZE);
		y = y-(y% SIZE);
		apple.setPostion(x, y);
	}

	private void requestRender() {
		render(g2d);
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}

	private void update() {
		if(up && dy == 0){
			dy = -SIZE;
			dx = 0;
		}
		
		if(down && dy == 0){
			dy = SIZE;
			dx = 0;
		}
		if(left && dx == 0){
			dy = 0;
			dx = -SIZE;
		}
		if(right && dx == 0){
			dy = 0;
			dx = SIZE;
		}
		if(dx != 0 || dy != 0){
		for(int i = snake.size() -1; i>0; i--){
			snake.get(i).setPostion(snake.get(i-1).getX(), snake.get(i-1).getY());
		}
		head.move(dx,dy);
		}
		if(apple.isCollsion(head)){
			Score++;
			setApple();
			Entity e =new Entity(SIZE);
			e.setPostion(-100,-100);
			snake.add(e);
			
		}
		if(head.getX()<0)head.setX(width);
		if(head.getY()<0)head.setY(height);
		if(head.getX()> width)head.setX(0);
		if(head.getY()>height)head.setY(0);
	}
	
	public void render(Graphics2D g2d){
		g2d.clearRect(0, 0, width, height);
		g2d.setColor(Color.GREEN);
		for(Entity e: snake){
			e.render(g2d);
		}
		
	g2d.setColor(Color.RED);
	apple.render(g2d);
	
	g2d.setColor(Color.WHITE);
	g2d.drawString("score: " + Score , 10, 10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}