import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class TankProjectile extends GameObject
{
	private int projSize = 10;
	private Tank tank;
	private boolean shot = false;



	private static final int speed = 6;

	public TankProjectile(int xCoord, int yCoord, int width, int height, Tank t)
	{
		setX(xCoord);
		//System.out.println("xCoord " + xCoord);
		setY(yCoord);
		setWidth(width);
		setHeight(height);
		tank = t;
	}

	@Override
	public GameObjectType getType()
	{
		return GameObjectType.TankProjectile;
	}

	public void setShot(boolean s)
	{
		shot = s;
	}

	public boolean getShot()
	{
		return shot;
	}

	
	

	@Override
	public void draw(Graphics page)
	{
		page.setColor(Color.black);
		page.fillOval(getX(),  getY(), projSize, projSize);
		
		drawBounds(page);

	}

	public boolean update(int speed)
	{
		updateBounds();
		
		if(((getX() >= 0 && getX() <= 800) && (getY() >= 0 && getY() <= 800)))
			{
				if(getDirection() == EnumDirections.UP)
					setY(getY()-speed);
				if(getDirection() == EnumDirections.DOWN)
					setY(getY()+speed);
				if(getDirection() == EnumDirections.LEFT)
					setX(getX()-speed);
				if(getDirection() == EnumDirections.RIGHT)
					setX(getX()+speed);
				return false;
			}
			else
				return true;
		 


		//updateBounds();
	}

}