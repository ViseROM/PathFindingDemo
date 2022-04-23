package transition;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * SlideUp class executes a slide up transition animation
 * @author Vachia Thoj
 *
 */
public class SlideUp extends Transition
{
	//x and y coordinate of Transition
	private int x;
	private int y;
	
	//The current height of the "rectangle" used for the slide up transition
	private int currentHeight;
	
	//Timer and delay for slide up animation (length of each animation update)
	private long timer;
	private long delay;
	
	//Timer and delay for how long to wait to declare that transition is done
	private long doneTimer;
	private long doneDelay;
	
	//Color of the "rectangle"/transition 
	private Color color;
	
	//Constructor
	public SlideUp(int width, int height)
	{
		super(width, height);
		
		this.currentHeight = 0;
		this.x = 0;
		this.y = height - currentHeight;
		
		
		this.speed = 20;
		this.doneTimer = System.nanoTime();
		this.delay = 10;
		
		this.doneTimer = 0;
		this.doneDelay = 400;
		
		this.color = Color.BLACK;
	}
	
	/**
	 * Method that updates the slide up transition
	 */
	public void update()
	{
		if(running == true)
		{
			if(currentHeight >= height)
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
				y = height - currentHeight;
				timer = System.nanoTime();
			}
		}
	}
	
	/**
	 * Method that draws the slide up transition
	 * 
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		if(running == true)
		{
			g.setColor(color);
			g.fillRect(x, y, width, currentHeight);
		}
	}
}
