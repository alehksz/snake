package snake;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Snake{
	private static JTextField player;
	private static JSplitPane splitPane;
	private static JPanel commandPanel;
	private static JPanel textPanel;
	private static final String Command_Start = "Start";
	//File file = new File("scores.txt");
	
	public static void main(String[] args) throws FileNotFoundException {

		new StartFrame().setVisible(true);
		
	}
	public static class StartFrame extends JFrame 
    {
    	
    	
    	public  StartFrame() throws FileNotFoundException
    	{
    		Map<Integer, String> scores = highestScore();
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
            JPanel textAreaPanel = new JPanel();
            
            btnPanel.add(startBtn);
            commandPanel.add(btnPanel);
            btnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            startBtn.setActionCommand(Command_Start);
            //newstuff
            JTextArea textArea= new JTextArea(5,15);
            textArea.setEditable(false);
            textArea.append("Highest Scores"+"\n");
            for(Map.Entry<Integer, String> entry : scores.entrySet())
            {
            	textArea.append(entry.getKey()+" "+entry.getValue()+"\n");
            }
            commandPanel.add(textArea);
            JTextArea topTextArea = new JTextArea(1,20);
            topTextArea.setEditable(false);
            topTextArea.append("                   Enter Name");
            textAreaPanel.add(topTextArea);
            textAreaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    	    //--------
    	    player=new JTextField(20);
    	    textPanel.add(player);
    	    textPanel.add(textAreaPanel);
    	    //Color Stuff
    	    textPanel.setBackground(Color.BLACK);
    	    btnPanel.setBackground(Color.yellow);
    	    //-----------
    	    frame.add(splitPane);
    		frame.pack();
    		frame.setVisible(true);
    		
    		StartPanel startgame = new StartPanel(frame, startBtn);
    		
    		
    	}
    	public static String getPlayerText()
    	{
    		return player.getText();
    	}
    	public Map<Integer, String> highestScore() throws FileNotFoundException
    	{
    		Map<Integer, String> scores = new TreeMap<Integer, String>();
    		
    		try{
    			FileWriter out=new FileWriter("scores.txt",true);
       	    	out.close();	
    		FileInputStream fr = new FileInputStream("scores.txt");
    		InputStreamReader isr = new InputStreamReader(fr);
    		//FileReader reader =	new FileReader(new File("scores.txt"));
    		BufferedReader buffRead = new BufferedReader(isr);
    		String line = null;
    		while((line=buffRead.readLine()) != null){
    			String[] playerScores = line.split(",");
    			scores.put(Integer.valueOf(playerScores[0]), playerScores[1]);
    		}
    		buffRead.close();
    		}catch(IOException exc){
       	    	throw new FileNotFoundException();}
    	Map<Integer, String> sortedScores = new TreeMap(Collections.reverseOrder());
    	sortedScores.putAll(scores);
    		return sortedScores;
    	}
    	
    }
}

