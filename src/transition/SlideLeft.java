package transition;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * SlideLeft class executes a slide left transition animation
 * @author Vachia Thoj
 *
 */
public class SlideLeft extends Transition
{
	//x and y coordinate of Transition
	private int x;
	private int y;
	
	//The current width of the "rectangle" used for slide left transition
	private int currentWidth;
	
	//Timer and delay for slide left animation (length of each animation update)
	private long timer;
	private long delay;
	
	//Timer and delay for how long to wait to declare that transition is done
	private long doneTimer;
	private long doneDelay;
	
	//Color of the "rectangle"/transition 
	private Color color;
	
	//Constructor
	public SlideLeft(int width, int height)
	{
		super(width, height);
		
		this.currentWidth = 0;
		this.x = width - currentWidth;
		this.y = 0;
		
		this.speed = 20;
		this.timer = System.nanoTime();
		this.delay = 10;
		
		this.doneTimer = 0;
		this.doneDelay = 400;
		
		this.color = Color.BLACK;
	}
	
	/**
	 * Method that updates the slide left transition
	 */
	public void update()
	{
		if(running == true)
		{
			if(currentWidth >= width)
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
				currentWidth = currentWidth + speed;
				x = width - currentWidth;
				timer = System.nanoTime();
			}
			
		}
	}
	
	/**
	 * Method that draws the slide left transition
	 * 
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		if(running == true)
		{
			g.setColor(color);
			g.fillRect(x, y, currentWidth, height);
		}
	}
}
