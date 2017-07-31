package snake;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;



public class Snake{
	private static JTextField player;
	private static JSplitPane splitPane;
	private static JPanel commandPanel;
	private static JPanel textPanel;
	private static final String Command_Start = "Start";
	
	public static void main(String[] args) {

		new StartFrame().setVisible(true);
		
	}
	public static class StartFrame extends JFrame 
    {
    	
    	
    	public  StartFrame()
    	{
    		JFrame frame = new JFrame("Snake Start");
    		//frame.setContentPane(new StartPanel(frame));
    		//frame.setContentPane(new GamePanel(frame));
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.setResizable(false);
    		frame.setPreferredSize(new Dimension(400, 400));
    		frame.setLocationRelativeTo(null);
    		
    		splitPane = new JSplitPane();
    		
    		commandPanel = new JPanel();
    		textPanel = new JPanel();
    		
    		frame.getContentPane().setLayout(new GridLayout());
    		frame.getContentPane().add(splitPane);
    		
    		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
            splitPane.setDividerLocation(50);
            splitPane.setTopComponent(textPanel);
            splitPane.setBottomComponent(commandPanel);
            
            commandPanel.setLayout(new BoxLayout(commandPanel,BoxLayout.Y_AXIS));
            JButton startBtn = new JButton(Command_Start);
            JPanel btnPanel = new JPanel();
            btnPanel.add(startBtn);
            commandPanel.add(btnPanel);
            btnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            startBtn.setActionCommand(Command_Start);
    	    
    	    player=new JTextField(20);
    	    textPanel.add(player);
    	    
    	    frame.add(splitPane);
    		frame.pack();
    		frame.setVisible(true);
    		
    		StartPanel startgame = new StartPanel(frame, startBtn);
    		
    		
    	}
    	
    }
}

