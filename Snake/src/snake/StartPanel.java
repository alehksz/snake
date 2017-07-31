package snake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class StartPanel extends JPanel implements Runnable, KeyListener, ActionListener {
	
	private static final int width = 400;
    private static final int height = 400;
    private long targetTime;
    private boolean start;
    private boolean running;
	private static final String Command_Start = "Start";
	private Thread thread;
	private JFrame startFrame;
	private JFrame gameframe;
	private JButton startbtn;
	
	public StartPanel(JFrame frame,JButton start){
		this.startFrame=frame;				
		this.startbtn = start;				
        //setPreferredSize(new Dimension(width,height));
        setFocusable(true);                       	    	    
        requestFocus();        
        addKeyListener(this);
        //BtnInit();
        //TextInit();                        
        startbtn.addActionListener(this);
	    startbtn.addKeyListener(this);
        //player= new PlayerText();
        //BtnInit();
        //TextInit();
        //startFrame.pack();
        
        gameframe = new JFrame("Snake");
        gameframe.setContentPane(new GamePanel(startFrame));
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameframe.setResizable(false);
        gameframe.pack();
        gameframe.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
        gameframe.setLocation(WIDTH/2,HEIGHT/2);
        gameframe.setVisible(false);
	}
	
	@Override
    public void addNotify(){
        super.addNotify();
        thread = new Thread (this);
        thread.start();
    }
	
	 @Override
	    public void run() {
	        if(running) return;
	        
	        long startTime;
	        long elapsed;
	        long wait;
	        while(running){
	            startTime = System.nanoTime();
	            //requestRender();
	            
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
	/*private void BtnInit()
	   {
		   //JPanel commandPanel = new JPanel();
	       commandPanel.setLayout(new BoxLayout(commandPanel,BoxLayout.Y_AXIS));
	       JButton startBtn = new JButton(Command_Start);
	       commandPanel.add(startBtn);	       
	       startBtn.setActionCommand(Command_Start);
	       startBtn.addActionListener(this);
	       startBtn.addKeyListener(this);
	   }
	private void TextInit()
	{
		//JPanel textPanel = new JPanel();
		
		textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.Y_AXIS));
		player = new PlayerText();
		textPanel.add(player);
	}*/
	@Override
    public void actionPerformed(ActionEvent e) {
    	String command = e.getActionCommand();
		if (command.equals(Command_Start)) 
		{
			start=true;
			
			//gameframe = new JFrame("Snake");
			//startFrame.setContentPane(new GamePanel(startFrame));
			//JFrame gameframeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//JFrame gameframeframe.setResizable(false);
			//JFrame gameframeframe.pack();
			//JFrame gameframeframe.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
			//meframe.setLocationRelativeTo(null);
			gameframe.setVisible(true);
			
			//BtnInit();
			//this.setVisible(false);
			
		}
       
    }
	@Override
    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();
    
        if (key == KeyEvent.VK_ENTER)
        {
        	start = true;
        	//JFrame frame = new JFrame("Snake");
    		//startFrame.setContentPane(new GamePanel(startFrame));
    		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		//frame.setResizable(false);
    		//frame.pack();
    		//frame.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
    		//frame.setLocationRelativeTo(null);
        	gameframe.setVisible(true);
        	//BtnInit();
        	//this.setVisible(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();
       
        if (key == KeyEvent.VK_ENTER) start = false;
    }

    @Override
    public void keyTyped(KeyEvent k) {
       
    }

    
}
