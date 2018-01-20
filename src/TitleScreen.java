import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class TitleScreen extends JApplet
{
	private Sound sound;
	
	public static TitleScreen theApp;
	
	private TankGame board;

	private ImageIcon titleScreenImage;//image
	private JFrame start;

	//All buttons

	//Image button
	public JButton 	titleScButton;

	//Main Buttons
	private JFrame help;
	private JFrame credits;

	public void init () 
	{		
		theApp = this;
		
		sound = new Sound();
		playMusic();


		//Adds the image and creates a button out of it
		titleScreenImage = new ImageIcon(this.getClass().getResource("TitleScreenPic.jpg"));//image	
		titleScButton = new JButton (titleScreenImage);//image button
		getContentPane().add(titleScButton);
		setSize(936,570);
		centerWindow();//centers the window

		
		titleScButton.addKeyListener(new KeyAdapter()
		
		{
			public void keyPressed(KeyEvent arg0) 
			{
				// TODO Auto-generated method stub
				int c = arg0.getKeyCode();

				if (c == KeyEvent.VK_ENTER)
				{
			//		sound.stop();

					TitleScreen.theApp.launchTankGame();
				}
							
				
				
				if (c == KeyEvent.VK_S) 
				{
					sound.toggle();
				}
			}
		});
		//JOptionPane.showMessageDialog(start, "Press 'E' for Easy, 'M' for Medium, 'H' for Hard");

		//Based on where they click in easy medium or hard something happens
		titleScButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//Music Toggle
				if (e.getX() > 0 && e.getX() < 27 && e.getY() > 745 && e.getY() < 769)
				{

					//System.out.println("Music toggle");
					if (sound.isPlaying())
					{
						sound.stop();
					}
					else
					{
						sound.resume();
					}

				}
				
				
				//Credits
				if (e.getX() > 770 && e.getX() < 830 && e.getY() > 1 && e.getY() < 14)
				{

					//	System.out.println("Credits");
					JOptionPane.showMessageDialog(help,
							"Created by Zac Thamer and Sauyma Shukla");


				}

			}

			public void mouseEntered(MouseEvent arg0) {

			}

			public void mouseExited(MouseEvent arg0) {

			}

			public void mousePressed(MouseEvent arg0) {

			}

			public void mouseReleased(MouseEvent arg0) {

			}
		});
	}


	public void launchTankGame()
	{
		hideWindow();
		board = new TankGame();
		board.init();
	}

	public void playMusic()
	{
		sound.play("TitleScreenMusic.wav");
	}


	public void hideWindow()
	{
		Container c = getParent();
		while (c.getParent()!=null) 
			c = c.getParent();
		c.setVisible(false);		
	}
	
	//Centers the window
	public void centerWindow()
	{
		//gets top level window
		Window window;
		Container c = getParent();
		while (c.getParent()!=null) 
			c = c.getParent();

		// center window
		if (c instanceof Window)//if it is the top window...
		{
			//centers it
			window = (Window)c;
			window.pack();
			window.setLocationRelativeTo(null);					
		}
	}

}
