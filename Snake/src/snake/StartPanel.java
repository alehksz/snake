package snake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements Runnable, KeyListener, ActionListener {
	
	private static final int width = 400;
    private static final int height = 400;
    private long targetTime;
    private boolean start;
    private boolean running;
	private static final String Command_Start = "Start";
	private Thread thread;
	
	public StartPanel(){
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
	
	 @Override
	    public void run() {
	        if(running) return;
	        BtnInit();
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
	private void BtnInit()
	   {
		   JPanel commandPanel = new JPanel();
	       add(BorderLayout.SOUTH, commandPanel);
	       JButton startBtn = new JButton(Command_Start);
	       commandPanel.add(startBtn);
	       startBtn.setActionCommand(Command_Start);
	       startBtn.addActionListener(this);
	       startBtn.addKeyListener(this);
	   }
	@Override
    public void actionPerformed(ActionEvent e) {
    	String command = e.getActionCommand();
		if (command.equals(Command_Start)) 
		{
			start=true;
			
			JFrame frame = new JFrame("Snake");
			frame.setContentPane(new GamePanel());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.pack();
			frame.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
       
    }
	@Override
    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();
    
        if (key == KeyEvent.VK_ENTER)
        {
        	start = true;
        	JFrame frame = new JFrame("Snake");
    		frame.setContentPane(new GamePanel());
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.setResizable(false);
    		frame.pack();
    		frame.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
    		frame.setLocationRelativeTo(null);
    		frame.setVisible(true);
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
