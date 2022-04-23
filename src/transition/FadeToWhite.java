package transition;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * FadeToWite class executes a fade to white transition animation
 * @author Vachia Thoj
 *
 */
public class FadeToWhite extends Transition
{
	//x and y coordinate of Transition
	private int x;
	private int y;
	
	//For white color opacity
	private int alpha;
	
	//Timer and delay for fade to white animation (length of each animation update)
	private long timer;
	private long delay;
	
	//Timer and delay for how long to wait to declare that transition is done
	private long doneTimer;
	private long doneDelay;
	
	//min and max value of alpha (opacity)
	private static final int MIN_ALPHA = 0;
	private static final int MAX_ALPHA = 255;
	
	//Constructor
	public FadeToWhite(int width, int height)
	{
		super(width, height);
		
		this.x = 0;
		this.y = 0;
		this.alpha = MIN_ALPHA;
		this.speed = 10;
		
		this.timer = System.nanoTime();
		this.delay = 20;
		
		this.doneTimer = 0;
		this.doneDelay = 500;
		
	}
	
	/**
	 * Method that updates the fade to white transition
	 */
	public void update()
	{
		//Execute fade to white transition if running
		if(running == true)
		{
			if(alpha >= MAX_ALPHA)
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
				alpha = alpha + speed;
				
				if(alpha > MAX_ALPHA)
				{
					alpha = MAX_ALPHA;
				}
				
				timer = System.nanoTime();
			}
		}
	}
	
	/**
	 * Method that draws the fade to white transition
	 * 
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		if(running == true)
		{
			g.setColor(new Color(255, 255, 255, alpha));
			g.fillRect(x, y, width, height);
		}
	}
}
