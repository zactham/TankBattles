import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject 
{

	protected int x = 0;
	protected int y = 0;
	
	protected int width = 0;
	protected int height = 0;


	protected static final int projectileWidth = 5;
	protected static final int projectileHeight = 18;
	public static final int expSize = 20;
	private Rectangle bounds = null;
	private EnumDirections direction;

	

	private static int gameWidth = 800;
	private static int gameHeight = 800;
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setWidth(int w)
	{
		width = w;
	}
	
	public void setHeight(int h)
	{
		height = h;
	}
	
	
	public void setDirection(EnumDirections d)
	{
		direction = d;
	}
	
	public EnumDirections getDirection()
	{
		return direction;
	}
	
	



	public int getX()
	{
		return x;
	}

	public void setX(int xPos)
	{
		x = xPos;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int yPos)
	{
		y = yPos;
	}

	public static int getExplosionSize()
	{
		return expSize;
	}

	public static int getGameWidth()
	{
		return gameWidth;
	}

	public static void setGameWidth(int gw)
	{
		gameWidth = gw;
	}

	public static int getGameHeight()
	{
		return gameHeight;
	}

	public static void setGameHeight(int gh)
	{
		gameHeight = gh;
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public void createBounds(int x, int y, int width, int height)
	{
		if(getBounds() == null)
		{
			bounds = new Rectangle(x, y, width, height);

		}
	}

	protected void drawBounds(Graphics page)
	{
		//page.setColor(Color.white);
		//page.drawRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int) bounds.getHeight());

	}

	public void updateBounds()
	{
		if (getBounds() != null)
			bounds.setLocation(x, y);


		//updateBounds();
	}

	public void draw (Graphics page)
	{
		//drawBounds(page);
	}
	
	public GameObjectType getType()
	{
		return GameObjectType.NONE;
	}




}