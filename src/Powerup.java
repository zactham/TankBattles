import java.awt.Color;
import java.awt.Graphics;

public class Powerup extends GameObject
{
	private int x;
	private int y;
	private int size;//size of the sides of the squares
	private Color c;//color of the square
	private EnumDirections direction;

	public Powerup(int xCoord, int yCoord, int tankSize, Color col)
	{
		x = xCoord;
		y = yCoord;
		size = tankSize;
		c = col;
	}
	
	public void draw(Graphics page, Color outline)
	{
		page.setColor(c);
		page.fillRect(x, y, size, size);
		if (outline != c)
		{
			page.setColor(outline);
			page.drawRect(x, y, size, size);
		}
	}

}
