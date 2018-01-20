import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainCode extends JPanel implements KeyListener
{
	Sound sound;
	private int score = 0;
	private boolean end;

//	private JFrame restart;
	private JFrame start;

	private final int gameSize = 500;
	private final int scorex = gameSize/5;
	private final int scorey = gameSize-120;
	
	private boolean gameOver = false;
	private int lives = 3;
	
	private GameObject Tank1 = new GameObject();
	private GameObject Tank2 = new GameObject();


	// Constructor
	public MainCode()
	{
		setFocusable(true);
		// Register for mouse events on the panel
		addKeyListener(this);
	}


	public void init(int level)
	{
		sound = new Sound();

		setPreferredSize(new Dimension(gameSize, gameSize));
		Tank1.setGameHeight(gameSize);

		// launch game
		JFrame frame = new JFrame("Sample Frame");
		frame.add(this);
		frame.setTitle("Game Title");

		JOptionPane.showMessageDialog(start, "Game Instructions");

		//Sets the speed of the game for each mode
		if (level == 1)		// easy
		{

		}

		if (level == 2)		// medium
		{

		}

		if (level == 3)		// hard
		{

		}

		playMusicMain();

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerWindow();
		frame.setLocationRelativeTo(TitleScreen.theApp);
		
		Tank1.setX(gameSize/4);
		Tank2.setX(gameSize/3);
		Tank1.setY(Tank1.getGameHeight()/2);
		Tank2.setY(Tank2.getGameHeight()/2);
		
		// runs the mainLoop
		ActionListener timerAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainLoop();

			}

		};

		// Frame rate, updates the frame every 15ms --- 60fps
		Timer timer = new Timer(15, timerAction);
		timer.setRepeats(true);
		timer.start();


	}

	public void MainLoop()
	{
		// updateGame();
		repaint();
		
		if(lives <1)
		{
				gameOver = true;
				gameEnding();
		}
	}


	public void playMusicMain()
	{
		//sound.play("IngameMusic.wav");
	}

	public void playSoundEffect()
	{
		//sound.play("SMACK Sound Effect.wav");
	}

	// Centers the window
	public void centerWindow()
	{
		// gets top level window
		Window window;
		Container c = getParent();
		while (c.getParent() != null)
			c = c.getParent();

		// center window
		if (c instanceof Window)// if it is the top window...
		{
			// centers it
			window = (Window) c;
			window.pack();
			window.setLocationRelativeTo(null);
		}
	}

	//
	//When the game ends
	//
	public void gameEnding()
	{

		sound.stop();

		int result = JOptionPane.showConfirmDialog(this, 
				"Your Score: " + score + " - Play Again?", 
				"Game Over", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.NO_OPTION)
		{
			// no
			System.exit(0);
		}
		else
		{
			// yes, play again
			resetGame();
		}
	}

	private void resetGame()
	{
		gameOver = false;
	}

	public void displayScore(Graphics page)
	{
		//Displays the Score
		page.setColor(Color.black);
		page.setFont(new Font("Comic Sans MS", Font.PLAIN, gameSize/20));
		page.drawString("SCORE: " + Integer.toString(score), scorex, scorey);
	}

	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);		// paint baseclass members too
		
		// drawGame(page);
		displayScore(page);
	}

	public int getScore()
	{
		return score;
	}




	public void keyPressed(KeyEvent arg0) 
	{
		int c = arg0.getKeyCode();

		//Pressing the keys
		if (c == KeyEvent.VK_NUMPAD1)
		{

		}

		if (c == KeyEvent.VK_NUMPAD2) 
		{

		}

		if (c == KeyEvent.VK_NUMPAD3)
		{

		}
	}



	public void keyReleased(KeyEvent arg0) 
	{
		int c = arg0.getKeyCode();

		//When S is pressed the music stops
		if (c == KeyEvent.VK_S) 
		{
			sound.toggle();
		}

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

