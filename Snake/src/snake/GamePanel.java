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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

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
    private Entity poison;
    private Entity apple;
    private ArrayList<Entity> snake;
    private ArrayList<Entity> apples;
    private ArrayList<Entity> poisons;
    private ArrayList<String> spawned=new ArrayList<String>();
    private int SnakeSize;
    private int score;
    private int level;
    private boolean gameover;
    private int timer=0;
    private File file;
   
    private int dx;
    private int dy;
   
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private boolean start;
    private JFrame startFrame;
    
    
   
   
   
    public GamePanel(JFrame frame){
        setPreferredSize(new Dimension(width,height));
        setLayout(new BorderLayout());
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        setVisible(true);
        this.startFrame=frame;
        file = new File("scores.txt");
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
    public void actionPerformed(ActionEvent e)
    {
	   String command = e.getActionCommand();
		if (command.equals("Start"))
		{
			start=true;
			
		}
    }
    @Override
    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();
       
        if (key == KeyEvent.VK_UP) 
        	up = true;
        if (key == KeyEvent.VK_DOWN) 
        	down = true;
        if (key == KeyEvent.VK_LEFT) 
        	left = true;
        if (key == KeyEvent.VK_RIGHT) 
        	right = true;
        if (key == KeyEvent.VK_ENTER) 
        	start = true;
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
//        JPanel commandPanel = new JPanel();
//        add(BorderLayout.SOUTH,commandPanel);
//        JButton startButton = new JButton("Start");
//        commandPanel.add(startButton);
//        startButton.setActionCommand("Start");
//        addKeyListener(this);
          setUpLevel();
        
    }
   
    private void setUpLevel(){
        snake = new ArrayList<Entity>();
        apples = new ArrayList<Entity>();
        poisons = new ArrayList<Entity>();
        head = new Entity(SIZE);
        head.setPostion(width/2, height/2);
        snake.add(head);
       
        for(int i =1; i < 4; i++){
            Entity e =new Entity(SIZE);
            e.setPostion(head.getX()+(i*SIZE), head.getY());
            snake.add(e);
        }
        /*for(int j =0; j < 15; j++){
            Entity e =new Entity(SIZE);
            int x = (int)(Math.random()*(width-SIZE));
            int y = (int)(Math.random()*(height - SIZE));
            x = x-(x % SIZE);
            y = y-(y% SIZE);
            e.setPostion(x, y);
            apples.add(e);
         }*/
        //for(int k = 0; k < 4; k++){
        createApples();
        createPoisons();
        SnakeSize = 2;
        score =0;
        gameover = false;
        level = 1;
        dx = dy = 0;
        setFPS(10);
        //poison = new Entity(SIZE);
        //setPoison();
       
       
    }
    private void createPoisons()
    {
    	if(poisons.size()<=4)
    		{
    			Entity e = new Entity(SIZE);
    			int x = (int)(Math.random()*(width-SIZE));
    			int y = (int)(Math.random()*(height - SIZE));
    			
        
    			if((x>=width/2 - SIZE) &&(x<=width/2 + 5*SIZE) &&(y<=height/2 + 2*SIZE)&&(y>=height/2 - 2*SIZE))
    				{
    					createPoisons();
    				}
    			else
    				{
    					x = x-(x % SIZE);
    					y = y-(y% SIZE);
    					e.setPostion(x, y);
    					if(spawned.size()>0)
    					{
    					if(!checkSpawn(e))
    					{
    						poisons.add(e);
    						spawned.add(e.toString());
    					}
    					else if(checkSpawn(e))
    					{
    						createPoisons();
    					}
    					}
    					else
    					{
    						poisons.add(e);
    						spawned.add(e.toString());
    					}
    					
    					
    					
    				}
    			createPoisons();
    		}
    	
    }
    
    private void createApples()
    {
    	if(apples.size()<=15)
		{
			Entity e = new Entity(SIZE);
			int x = (int)(Math.random()*(width-SIZE));
			int y = (int)(Math.random()*(height - SIZE));
			
    
			if((x>=width/2 - SIZE) &&(x<=width/2 + 5*SIZE) &&(y<=height/2 + 2*SIZE)&&(y>=height/2 - 2*SIZE))
				{
					createApples();
				}
			else
				{
					x = x-(x % SIZE);
					y = y-(y% SIZE);
					e.setPostion(x, y);
					if(spawned.size()>0)
					{
					if(!checkSpawn(e))
					{
						apples.add(e);
						spawned.add(e.toString());
					}
					else if(checkSpawn(e))
					{
						createApples();
					}
					}
					else
					{
						apples.add(e);
						spawned.add(e.toString());
					}
					
					
					
				}
			createApples();
		}
    }
    private boolean checkSpawn(Entity e)
    {
    	String ePos = e.toString();
    	for(int x = 0; x<spawned.size();x++)
    	{
    		if(ePos.equals(spawned.get(x)))
    				{
    					return true;
    				}
    		
    	}
    	
    	return false;
    
    	
    }
   
//    public void setApple(){
//        int x = (int)(Math.random()*(width-SIZE));
//        int y = (int)(Math.random()*(height - SIZE));
//        x = x-(x % SIZE);
//        y = y-(y% SIZE);
//        apple.setPostion(x, y);
//    }
//    public void setPoison(){
//        int x = (int)(Math.random()*(width-SIZE));
//        int y = (int)(Math.random()*(height - SIZE));
//        x = x-(x % SIZE);
//        y = y-(y% SIZE);
//        /*if((apple.getX()==poison.getX())&&(apple.getY()==poison.getY()))
//        {
//        	setPoison();
//        }
//        else{
//        poison.setPostion(x, y);
//        }
//        */
//    }

    private void requestRender() {
        render(g2d);
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    private void update() {

       timer++;
       if(timer==1000)
       {
    	   
       }
        if(gameover)
        {
        	//------------------------------------------------------
        	running=false;       	
    		startFrame.requestFocus();
    		try{
    		recordScore(score);
    		}catch(FileNotFoundException exc){System.out.println("File not found");}
    		running=true;
    		start=true;
    		run();
        	//------------------------------------------------------
            if(start)
            {
                setUpLevel();
                gameover=false;
                   
            }
               
                return;
        }
       
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
        if(right && dx == 0 && dy != 0 ){
            dy = 0;
            dx = SIZE;
        }
        if(dx != 0 || dy != 0){
        for(int i = snake.size() -1; i>0; i--){
            snake.get(i).setPostion(snake.get(i-1).getX(), snake.get(i-1).getY());
        }
        head.move(dx,dy);
        }
       
        for(Entity e : snake){
            if(e.isCollision(head)){
                gameover = true;
               
            }
        }
       
       
       /* if(apple.isCollision(head)){
        	score++;
			setApple();
			//increases tail length by 1-3
			Random rand = new Random();
			int  n = rand.nextInt(3) + 1;
			for(int i =0; i < n; i++){
				Entity e =new Entity(SIZE);
				e.setPostion(head.getX(), head.getY());
				snake.add(e);
				SnakeSize++;
			}
			
		}
		*/
        //if(poison.isCollision(head))
       // {
        	//gameover = true;
        //}
        
        
        for(Entity e: apples){
        	if(e.isCollision(head)){
            	score++;
    			//increases tail length by 1-3
    			Random rand = new Random();
    			int  n = rand.nextInt(3) + 1;
    			for(int i =0; i < n; i++){
    				Entity a =new Entity(SIZE);
    				a.setPostion(head.getX(), head.getY());
    				snake.add(a);
    				SnakeSize++;
        		e.setVisible(false);
        	}

        }

        
        
        if(head.getX()<0)gameover=true;
        if(head.getY()<0)gameover=true;
        if(head.getX()> width-10)gameover=true;
        if(head.getY()>height-10)gameover=true;
        }
        for(Entity e: poisons){
        	if(e.isCollision(head)){
            	gameover=true;

        }

        
        
        if(head.getX()<0)gameover=true;
        if(head.getY()<0)gameover=true;
        if(head.getX()> width-10)gameover=true;
        if(head.getY()>height-10)gameover=true;
        }
        
    }
   
    private void recordScore(int score) throws FileNotFoundException 
    {
    	String highscore = Snake.StartFrame.getPlayerText() + ": " + score;
   	     if(file.exists())
   	     {
   	    	try(
   	    	 PrintWriter out = new PrintWriter(file)){
   	    	out.println(highscore);
   	    	out.close();
   	    	}
   	    	throw new FileNotFoundException();
   	     }
   	     else
   	     {
   	    	 file = new File("scores.txt");
   	    	try(
   	   	    	 PrintWriter out = new PrintWriter(file)){
   	   	    	out.println(highscore);
   	   	    	out.close();
   	   	    	}
   	   	    	throw new FileNotFoundException();
   	     }
   	     
             
   	     
       	
   	  
      }
     
    
    
    public void render(Graphics2D g2d){
        g2d.clearRect(0, 0, width, height);
        g2d.setColor(Color.GREEN);
        for(Entity e: snake){
            e.render(g2d);
        }
       

    for(Entity e: apples){
    	if(e.getVisible()){
        g2d.setColor(Color.RED);
    	e.render(g2d);
    	}
        
    for(Entity p: poisons){
        if(p.getVisible()){
        g2d.setColor(Color.YELLOW);
        p.render(g2d);
        }
   
    /*g2d.setColor(Color.YELLOW);
    poison.render(g2d);
    */if(gameover){
        g2d.drawString("GameOver!", 150, 200);
        // Record Highscore
        // Grab text from StartPanel textbox
        // write to txt file with score
        //String highscore = Snake.StartFrame.getPlayerText() + ": " + score;
        
        
    }
    
    g2d.setColor(Color.WHITE);
    g2d.drawString("snake size:" + SnakeSize + "  Level: " + level +"  Score: "+ score, 10, 10);
    if(dx == 0 && dy == 0 ){
        g2d.drawString("Ready!", 150, 200);
    }
    }
   
   
    }
   
   
   

    
    
    }
    }