package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class Snake extends JFrame implements KeyListener {
		
	private static final String COMMAND_W = "W";
	private static final String COMMAND_A = "A";
	private static final String COMMAND_S = "S";
	private static final String COMMAND_D = "D";
	
	
	public Snake() {
		setVisible(true);
		//set size of app
		setMinimumSize(new Dimension(800,600));
		//centres to middle screen
		setLocationRelativeTo(null);
		//terminates program on close
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//title
		setTitle("SNAAKE");
		//cannot resize
		setResizable(false);

	}

	@Override
	public void keyPressed(KeyEvent event) { }

	@Override
	public void keyReleased(KeyEvent event) { }

	@Override
	public void keyTyped(KeyEvent event) {
       char c = event.getKeyChar();
		if (c == COMMAND_W.charAt(0)) {
		}
		else if (c == COMMAND_A.charAt(0)) {
			//GamePanel.update();
		}    
		else if (c == COMMAND_S.charAt(0)) {
			//GamePanel.update();
		}  
		else if (c == COMMAND_D.charAt(0)) {
			//GamePanel.update();
		}  
	}
	

	public class SnakeTail {		
		
		private int x;
		private int y;
		private int r;

		public SnakeTail(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}

	}
	
	// ***********************************************************************
	

	
	// ***********************************************************************
	
	
	public static void main(String[] args) {
	    new Snake();
	}

}
