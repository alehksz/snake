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
/**
 * The Class StartPanel.
 */
public class StartPanel extends JPanel implements Runnable, KeyListener, ActionListener {

	private long targetTime;
	private boolean start;
	private boolean running;
	private static final String Command_Start = "Start";
	private Thread thread;
	private JFrame startFrame;
	private JFrame gameframe;
	private JButton startbtn;
	
	/**
	 * Instantiates a new start panel.
	 *
	 * @param frame the calling frame
	 * @param start the start button
	 */
	public StartPanel(JFrame frame, JButton start) {
		this.startFrame = frame;
		this.startbtn = start;
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		startbtn.addActionListener(this);
		startbtn.addKeyListener(this);
		gameframe = new JFrame("Green Snake");
		gameframe.setContentPane(new GamePanel(startFrame));
		gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameframe.setResizable(false);
		gameframe.pack();
		gameframe.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		gameframe.setLocation(WIDTH / 2, HEIGHT / 2);
		gameframe.setVisible(false);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * Running method, multi threading
	 *
	 * 
	 */
	@Override
	public void run() {
		if (running)
			return;

		long startTime;
		long elapsed;
		long wait;
		while (running) {
			startTime = System.nanoTime();
			elapsed = System.nanoTime() - startTime;
			wait = targetTime - elapsed / 1000000;
			if (wait > 0) {
				try {
					Thread.sleep(wait);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}
	/**
	 * Event listener for start command. Sets the gameframe to visible 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals(Command_Start)) {
			// start=true;
			gameframe.setVisible(true);
		}

	}
	/**
	 * Key press event
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();

		if (key == KeyEvent.VK_ENTER) {
			start = true;
			gameframe.setVisible(true);
		}
	}
	/**
	 * Key released event
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent k) {
		int key = k.getKeyCode();
		if (key == KeyEvent.VK_ENTER)
			start = false;
	}

	@Override
	public void keyTyped(KeyEvent k) {
	}

}
