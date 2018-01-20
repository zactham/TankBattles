import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import javax.swing.*;
public class TankGame extends JPanel implements KeyListener
{
	private Sound sound;


	private InputManager inputManager;
 
	private final int gameboardSize = 800;
	private final int tankWidth = 30;
	private final int tankHeight = 50;


	private int ammoSize = 5;
	private boolean deleteBullet1 = false;
	private boolean deleteBullet2 = false;
	private int bulletIndex1 = 0;
	private int bulletIndex2 = 0;
	private final int projSize = 5;
	private final int projSpeed = 6;


	private boolean move1 = false;
	private boolean move2 = false;

	//	private boolean tanksCollided = false;


	// tank speed must be a even factor of squareSize
	private int tankSpeed = tankWidth/10;

	private final int tankClipSize = 10;

	private int tankClip1Size = 10;
	private int tankClip2Size = 10;
	
	private int bullet1Added = 0;

	public ArrayList <TankProjectile> tank1Clip = new ArrayList <TankProjectile> (tankClipSize);

	public ArrayList <TankProjectile> tank2Clip = new ArrayList <TankProjectile> (tankClipSize);
	//TODO delete offscreen

	Tank tank1 = new Tank(0 + 10,gameboardSize - tankHeight - 10,tankWidth, tankHeight, Color.CYAN.darker(), move1);

	Tank tank2 = new Tank(gameboardSize-tankWidth - 15,0,tankWidth,tankHeight, Color.RED.darker(), move2);

	Powerup ammo = new Powerup(0, 0, ammoSize, Color.red);

	private int scoreP1 = 0;
	private int scoreP2 = 0;

	private JFrame gameOver;
	private JFrame start;


	// Constructor
	public TankGame()
	{
		setFocusable(true);
		// Register for mouse events on the panel
		addKeyListener(this);

		scoreP1 = 0;
		scoreP2 = 0;
	}


	public void init()
	{

		sound = new Sound();

		inputManager = new InputManager();

		setPreferredSize(new Dimension(gameboardSize, gameboardSize));

		// launch game
		JFrame frame = new JFrame("Sample Frame");
		frame.getContentPane().add(this);
		frame.setTitle("Tank");
		frame.setBackground(Color.WHITE);

		JOptionPane.showMessageDialog(start, "Player 1 use WASD and Player 2 use the arrow keys to move the tank around ");
		tank1.createBounds(tank1.getX(),tank1.getY(),tankWidth, tankHeight);
		tank2.createBounds(tank2.getX(),tank2.getY(),tankWidth, tankHeight);

		//setAmmoLocation();



		//gameMode();


		//playInGameMusic();

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		centerWindow();
		frame.setLocationRelativeTo(TitleScreen.theApp);


		// runs the mainLoop every 15 milliseconds or 60 frames per seconds 
		ActionListener timerAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainLoop();

			}

		};


		// Frame rate, updates the frame every 15ms --- 60fps
		Timer timer = new Timer(15, timerAction);
		timer.setRepeats(true);
		timer.start();


		tank1.setDirection(EnumDirections.NONE);
		tank2.setDirection(EnumDirections.NONE);
	}

	/*
	public void gameMode()
	{
		//Sets the speed of the game for each mode
		if (TitleScreen.easy == true)
		{
			tankSpeed = squareSize/10;
			//speed = 2
		}



		if (TitleScreen.med == true)
		{
			tankSpeed = squareSize/5;
			//speed = 4
		}



		if (TitleScreen.hard == true)
		{
			tankSpeed = squareSize/4;
			//speed = 5
		}
	}
	 */

	public void MainLoop()
	{
		checkKeys();
		updateGame();
		collide();
		repaint();
	}


	public void updateGame()
	{
		/*
		//If the tank goes outside the grid the game ends
		if (tank1.getX()<0 || tank1.getX() + tank1.getWidth()>=gameboardSize || tank1.getY()<0 || tank1.getY() + tank1.getHeight()>=gameboardSize )
			gameEnding();

		//If the tank goes outside the grid the game ends
		if (tank2.getX()<0 || tank2.getX()>=gameboardSize || tank2.getY()<0 || tank2.getY()>=gameboardSize )
			gameEnding();
		 */

		tank1.update(tankSpeed);
		tank2.update(tankSpeed);

		for(int i = bulletIndex1; i < tank1Clip.size(); i++)
		{
			deleteBullet1 = tank1Clip.get(i).update(projSpeed);
		}

		for(int i = bulletIndex2; i < tank2Clip.size(); i++)
		{
			deleteBullet2 = tank2Clip.get(i).update(projSpeed);
		}

	}

	public void tankCollisionReset()
	{

		if(scoreP1 >0)
			scoreP1 -=1;
		if(scoreP2 >0)
			scoreP2 -=1;

		tank1.setDirection(EnumDirections.NONE);
		tank2.setDirection(EnumDirections.NONE);

		tank1.setX(0 + 10);
		tank1.setY(gameboardSize - tankHeight - 10);

		tank2.setX(gameboardSize-tankWidth - 15);
		tank2.setY(0);


		tankSpeed = tankWidth/10;
		//ammo = new Powerup(0, 0, ammoSize, Color.red);

		System.out.println("Tanks Collided");



	}


	public void collide()
	{

		if(tank1.getBounds().intersects(tank2.getBounds()))
		{
			tankCollisionReset();

		}
		

		

		if (bullet1Added > 0)
		{
			if(tank1Clip.get(bulletIndex1).getBounds().intersects(tank2.getBounds()))
			{
				System.out.println("Tank 1 bullet hit tank 2");
				
			}
		}
	}

	/*
		if(tank1Clip.get(bulletIndex1).getShot())
		{
			//Checks if the player hits the alien
			for(int i = alienManager.getNumAliens() - 1; i >-1; i--)
			{
				if(alienManager.getAlien(i).getBounds().intersects(player.getShot().getBounds()))
				{

					player.removeShot();
					sound.play("sounds/alien_hit.wav");
					alienHit(alienManager.getAlien(i));
					increaseScore(alienManager.getAlien(i));
					alienManager.removeAlien(alienManager.getAlien(i));


					break;
				}
			}
		}
	}

	if(tank1.collide(ammo))
	{
		System.out.println("Collide");
		ammoAmount+=3;
		setAmmoLocation();
	}

	if(tank2.collide(ammo))
	{
		System.out.println("Collide");
		ammoAmount+=3;
		setAmmoLocation();
	}
}



/*
	public void playInGameMusic()

	{
		sound.play("IngameMusic.wav");
	}


	public void playSoundEffect()
	{
		sound.play("SMACK Sound Effect.wav");
	}




	/*
	public void setAmmoLocation()
	{
		//400-20 = 380
		int randomX = (int) (Math.random() *((gameboardSize-ammoSize)/ammoSize));
		randomX *= ammoSize;

		//System.out.println(randomX);

		int randomY = (int) (Math.random() *((gameboardSize-ammoSize))/ammoSize);
		randomY *= ammoSize;

	//	System.out.println(randomY);

		ammo.setX(randomX);
		ammo.setY(randomY);
	}
	 */

	public void drawGame(Graphics page)
	{
		//ammo.draw(page);
		//System.out.println("Draw Game");
		tank1.draw(page);		
		tank2.draw(page);


		for(int i = bulletIndex1; i < tank1Clip.size(); i++)
		{

			if(!deleteBullet1)
				tank1Clip.get(i).draw(page);
			else
				bulletIndex1 = i+1;


		}

		for(int i = bulletIndex2; i < tank2Clip.size(); i++)
		{
			if(!deleteBullet2)
				tank2Clip.get(i).draw(page);
			else
				bulletIndex2 = i+1;
		}

	
	
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


	public void gameEnding()
	{
		//When the game ends

		//sound.stop();

		int result = JOptionPane.showConfirmDialog(this, 
				"Player 1 Score: " + scoreP1 + " Player 2 Score: " + scoreP2 + "  - Play Again?", 
				"Game Over", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.NO_OPTION)
		{
			// no
			System.exit(0);
		}
		else
		{
			// yes, play again
			//	resetGame();

		}
	}

	//
	// reset the game so we can play again
	// reset direction, oldDirection, tankLengthCounter, Score, apple location, ...
	//
	/*
	private void resetGame()
	{
		//	playInGameMusic();
		scoreP1 = 0;
		scoreP2 = 0;

		//setAmmoLocation();

		tank1Direction = EnumDirections.NONE;
		tank2Direction = EnumDirections.NONE;
		shot1Direction = EnumDirections.NONE;
		shot2Direction = EnumDirections.NONE;

		tankSpeed = tankWidth/10;

		tank1 = new Tank(0,gameboardSize,tankWidth, tankHeight, Color.GREEN.darker(), move1);
		tank2 = new Tank(gameboardSize-tankWidth - 15,0,tankWidth,tankHeight, Color.RED.darker(), move2);

		shot1 = new TankProjectile(tank1.getX(), tank1.getY(), projSize, projSize, tank1);
		shot2 = new TankProjectile(tank2.getX(), tank2.getY(), projSize, projSize, tank2);

		ammo = new Powerup(0, 0, ammoSize, Color.red);

	}
	 */

	public void displayScore(Graphics page)
	{
		//Displays the Score
		page.setColor(Color.black);
		page.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		page.drawString("SCORE: ", (gameboardSize/4), gameboardSize-50);
		page.drawString("P1: " + Integer.toString(scoreP1), (gameboardSize/4)+220, gameboardSize-50);
		page.drawString("P2: " + Integer.toString(scoreP2), (gameboardSize/4)+370, gameboardSize-50);

		page.drawString("P1 Shots: " + Integer.toString(tankClip1Size),  (gameboardSize/4)+220, gameboardSize-30);
		page.drawString("P2 Shots: " + Integer.toString(tankClip2Size),  (gameboardSize/4)+370, gameboardSize-30);
	}

	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);		// paint baseclass members too

		displayScore(page);
		drawGame(page);
	}

	public int getPlayer1Score()
	{
		return scoreP1;
	}

	public int getPlayer2Score()
	{
		return scoreP2;
	}

	public void checkKeys()
	{
		///// Player 1
		if (inputManager.getKeyPressed(KeyEvent.VK_W) == true)//+2
		{
			tank1.setDirection(EnumDirections.UP);
			tank1.setMove (true);
		}

		else if (inputManager.getKeyPressed(KeyEvent.VK_A) == true)//+2
		{
			tank1.setDirection(EnumDirections.LEFT);
			tank1.setMove (true);
		}

		else if (inputManager.getKeyPressed(KeyEvent.VK_S) == true)//+2
		{
			tank1.setDirection(EnumDirections.DOWN);
			tank1.setMove (true);
		}

		else if (inputManager.getKeyPressed(KeyEvent.VK_D) == true)//+2
		{
			tank1.setDirection(EnumDirections.RIGHT);
			tank1.setMove (true);
		}


		///Player 2
		if (inputManager.getKeyPressed(KeyEvent.VK_UP) == true)//-2
		{
			tank2.setDirection(EnumDirections.UP);
			tank2.setMove (true);
		}

		else if (inputManager.getKeyPressed(KeyEvent.VK_DOWN) == true) //+2
		{	
			tank2.setDirection(EnumDirections.DOWN);
			tank2.setMove (true);
		}

		else if (inputManager.getKeyPressed(KeyEvent.VK_RIGHT) == true)//-2
		{	
			tank2.setDirection(EnumDirections.RIGHT);
			tank2.setMove (true);
		}
		else if (inputManager.getKeyPressed(KeyEvent.VK_LEFT) == true)//+2
		{
			tank2.setDirection(EnumDirections.LEFT);
			tank2.setMove (true);
		}

		/*
		//When L is pressed the music stops
		if (inputManager.getKeyPressed(KeyEvent.VK_L) == true) 
		{
			sound.toggle();
		}
		 */


	}




	public void keyPressed(KeyEvent arg0) 
	{
		int c = arg0.getKeyCode();

		Integer v = Integer.valueOf(c);

		//Allows the user to only click once and not hold down
		if(c == KeyEvent.VK_V)
		{
			if (tankClip1Size > 0)
			{
				System.out.println("Player 1 Fired");
				TankProjectile shot1 = new TankProjectile(tank1.getX() + tank1.getWidth()/2
						- projSize/2 - 3, tank1.getY()+tank1.getHeight()/2 - projSize/2 - 3,
						projSize, projSize, tank1);
				shot1.setShot(true);
				shot1.setDirection(tank1.getDirection());
				shot1.createBounds(shot1.getX(), shot1.getY(), shot1.getWidth(), shot1.getHeight());
				tank1Clip.add(shot1);
				tankClip1Size--;
				bullet1Added++;
			}
			return;
		}
		else if (c == KeyEvent.VK_DELETE)
		{
			if (tankClip2Size > 0)
			{
				System.out.println("Player 2 Fired");
				TankProjectile shot2 = new TankProjectile(tank2.getX() + tank2.getWidth()/2
						- projSize/2 - 3, tank2.getY()+tank2.getHeight()/2 - projSize/2 - 3,
						projSize, projSize, tank2);
				shot2.setShot(true);
				shot2.setDirection(tank2.getDirection());
				tank2Clip.add(shot2);
				tankClip2Size--;
			}
			return;
		}
		else
			inputManager.setKeyPressed(c, true);
	}



	public void keyReleased(KeyEvent arg0) 
	{
		tank1.setMove (false);
		tank2.setMove (false);

		int c = arg0.getKeyCode();
		inputManager.setKeyPressed(c, false);

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}