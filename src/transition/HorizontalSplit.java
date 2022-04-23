package transition;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * HorizontalSplit class executes a horizontal split transition animation
 * @author Vachia Thoj
 *
 */
public class HorizontalSplit extends Transition
{
	//x and y coordinate of the top "rectangle"
	private int topX;
	private int topY;
	
	//x and y coordinate of the bottom "rectangle"
	private int bottomX;
	private int bottomY;
	
	//The current and max height of "rectangle" used for horizontal split transition
	private int currentHeight;
	private int maxHeight;
	
	//Timer and delay for horizontal split animation (length of each animation update)
	private long timer;
	private long delay;
	
	//Timer and delay for how long to wait to declare that transition is done
	private long doneTimer;
	private long doneDelay;
	
	//Color of the "rectangle"/transition 
	private Color color;
	
	//Constructor
	public HorizontalSplit(int width, int height)
	{
		super(width, height);
		
		this.topX = 0;
		this.topY = 0;
		
		this.bottomX = 0;
		this.bottomY = height;
		
		if(height % 2 == 1)
		{
			++height;
		}
		
		this.currentHeight = 0;
		this.maxHeight = height / 2;
		
		this.speed = 5;
		this.timer = System.nanoTime();
		this.delay = 10;
		
		this.doneTimer = 0;
		this.doneDelay = 400;
		
		color = Color.BLACK;
	}
	
	/**
	 * Method that updates the horizontal split transition
	 */
	public void update()
	{
		if(running == true)
		{
			if(currentHeight >= maxHeight)
			{
				if(doneTimer == 0)
				{
					doneTimer = System.nanoTime();
				}
				else if(((System.nanoTime() - doneTimer) / 1000000) > doneDelay)
				{
					done = true;
					running = false;
				}
			}
			else if(((System.nanoTime() - timer) / 1000000) > delay)
			{
				currentHeight = currentHeight + speed;
				
				if(currentHeight >= maxHeight)
				{
					currentHeight = maxHeight;
				}
				
				bottomY = height - currentHeight;
				
				timer = System.nanoTime();
			}
		}
	}
	
	/**
	 * Method that draws the horizontal split transition
	 * 
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		if(running == true)
		{
			g.setColor(color);
			
			g.fillRect(topX, topY, width, currentHeight);
			g.fillRect(bottomX, bottomY, width, currentHeight);
		}
	}
}
