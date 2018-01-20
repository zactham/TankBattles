import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank extends GameObject
{
	//private int x = getX();
	//private int y = getY();
	//private int width;
	//	private int height;
	private Color c;//color of the square

	private boolean fire = false;
	private boolean move = false;

	private TankProjectile shot;



	public Tank(int xCoord, int yCoord, int tankWidth, int tankHeight, Color col, boolean m)
	{
		setX(xCoord);
		setY(yCoord);
		setWidth(tankWidth);
		setHeight(tankHeight);
		c = col;
		move = m;

	}

	public void setMove(boolean isMoving)
	{
		move = isMoving;
	}

	public void draw(Graphics page)
	{
		page.setColor(c);
		
		drawBounds(page);

		if(getDirection() == EnumDirections.NONE)
		{	
			page.fillRect(getX(),getY(), getWidth(), getHeight());

			page.setColor(Color.black);
			page.fillRect(getX()-10,getY(), getWidth()-20, getHeight());
			page.fillRect(getX() + 30, getY(), getWidth()-20, getHeight());

			page.fillOval(getX() + 4, getY() + 15, getWidth() - 10, getHeight() - 30);
			page.fillRect(getX() + 12,  getY() + 15, getWidth() - 25, getHeight() - 10);

		}


		if(getDirection() == EnumDirections.DOWN)
		{
			page.fillRect(getX(),getY(), getWidth(), getHeight());

			page.setColor(Color.black);
			page.fillRect(getX()-10,getY(), getWidth()-20, getHeight());
			page.fillRect(getX() + 30, getY(), getWidth()-20, getHeight());

			page.fillOval(getX() + 4, getY() + 15, getWidth() - 10, getHeight() - 30);
			page.fillRect(getX() + 12,  getY() + 15, getWidth() - 25, getHeight() - 10);
		}

		else if(getDirection() == EnumDirections.UP)
		{
			page.fillRect(getX(),getY(), getWidth(), getHeight());

			page.setColor(Color.black);
			page.fillRect(getX()-10,getY(), getWidth()-20, getHeight());
			page.fillRect(getX() + 30, getY(), getWidth()-20, getHeight());

			page.fillOval(getX() + 4, getY() + 15, getWidth() - 10, getHeight() - 30);
			page.fillRect(getX() + 12,  getY() -5, getWidth() - 25, getHeight() - 10);
		}

		else if(getDirection() == EnumDirections.LEFT)
		{
			page.fillRect(getX(),getY(), getHeight(), getWidth());

			page.setColor(Color.black);
			page.fillRect(getX(),getY()-10, getHeight(), getWidth()-20);
			page.fillRect(getX(), getY()+30, getHeight(), getWidth()-20);

			page.fillOval(getX() + 15, getY() + 4, getWidth() - 10, getHeight() - 30);
			page.fillRect(getX() - 15,  getY() + 12, 40, 5);
		}

		else if(getDirection() == EnumDirections.RIGHT)
		{
			page.fillRect(getX(),getY(), getHeight(), getWidth());

			page.setColor(Color.black);
			page.fillRect(getX(),getY()-10, getHeight(), getWidth()-20);
			page.fillRect(getX(), getY()+30, getHeight(), getWidth()-20);

			page.fillOval(getX() + 15, getY() + 4, getWidth() - 10, getHeight() - 30);
			page.fillRect(getX() + 15,  getY() + 12, 40, 5);
		}

		if (fire)
		{
			System.out.println("Shot Fired");
			shot.draw(page);
		}
	}

	//returns true if the tank collides (intersects) with another Square object that is passed in
	public boolean collide(Powerup a)
	{
		if(this.getX() == a.getX() && this.getY() == a.getY())
			return true;
		return false;
	}




	public void update(int speed)
	{
		updateBounds();
		if (move)
		{ 
			
				if(getDirection() == EnumDirections.UP)
					this.setY(this.getY()-speed);
				if(getDirection() == EnumDirections.DOWN)
					this.setY(this.getY()+speed);
				if(getDirection() == EnumDirections.LEFT)
					this.setX(this.getX()-speed);
				if(getDirection() == EnumDirections.RIGHT)
					this.setX(this.getX()+speed);
			
		}
		
		
		if(getX() <= 0)
			setX(0);
		
		if(getX() + getWidth()>= getGameWidth())
			setX(getGameWidth() - getWidth());
		
		if(getY() <= 0)
			setY(0);
		
		if(getY() + getHeight() >= getGameHeight())
			setY(getGameHeight() - getHeight());
		
		
		


	}
	public boolean onGrid (int gridSize)
	{
		if (this.getX() % gridSize == 0 && this.getY() % gridSize == 0)
		{
			return true;
		}
		else
			return false;
	}
}