package transition;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * SlideDown class executes a slide down transition animation
 * @author Vachia Thoj
 *
 */
public class SlideDown extends Transition
{
	//x and y coordinate of Transition
	private int x;
	private int y;
	
	//Current height of the "rectangle" used for the slide transition
	private int currentHeight;
	
	//Timer and delay for slide down animation (length of each animation update)
	private long timer;
	private long delay;
	
	//Timer and delay for how long to wait to declare that transition is done
	private long doneTimer;
	private long doneDelay;
	
	//Color of the "rectangle"/transition 
	private Color color;
	
	//Constructor
	public SlideDown(int width, int height)
	{
		super(width, height);
		
		this.x = 0;
		this.y = 0;
		this.currentHeight = 0;
		
		this.speed = 20;
		this.timer = System.nanoTime();
		this.delay = 10;
		
		this.doneTimer = 0;
		this.doneDelay = 400;
		
		this.color = Color.BLACK;
	}
	
	/**
	 * Method that updates the slide down transition
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
				timer = System.nanoTime();
			}
		}
	}
	
	/**
	 * Method that draws the slide down transition
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
