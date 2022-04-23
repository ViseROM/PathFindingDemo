package transition;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * VerticalSplit class executes a vertical split transition animation
 * @author Vachia Thoj
 *
 */
public class VerticalSplit extends Transition
{
	//x and y coordinate of the left "rectangle"
	private int leftX;
	private int leftY;
	
	//x and y coordinate of the right "rectangle"
	private int rightX;
	private int rightY;
	
	//The current and max width of "rectangle" used for vertical split transition
	private int currentWidth;
	private int maxWidth;
	
	//Timer and delay for vertical split animation (length of each animation update)
	private long timer;
	private long delay;
	
	//Timer and delay for how long to wait to declare that transition is done
	private long doneTimer;
	private long doneDelay;
	
	//Color of the "rectangle"/transition
	private Color color;
	
	//Constructor
	public VerticalSplit(int width, int height)
	{
		super(width, height);
		
		this.currentWidth = 0;
		
		this.leftX = 0;
		this.leftY = 0;
		
		this.rightX = width - currentWidth;
		this.rightY = 0;
		
		if(width % 2 == 1)
		{
			++width;
		}
		
		this.maxWidth = width / 2; 
		
		this.speed = 10;
		this.timer = System.nanoTime();
		this.delay = 10;
		
		this.doneTimer = 0;
		this.doneDelay = 400;
		
		this.color = Color.BLACK;
	}
	
	/**
	 * Method that updates the vertical split transition
	 */
	public void update()
	{
		if(running == true)
		{
			if(currentWidth >= maxWidth)
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
				
				if(currentWidth >= maxWidth)
				{
					currentWidth = maxWidth;
				}
				
				rightX = width - currentWidth;
				
				timer = System.nanoTime();
			}
		}
	}
	
	/**
	 * Method that draws the vertical split transition
	 * 
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		if(running == true)
		{
			g.setColor(color);
			g.fillRect(leftX, leftY, currentWidth, height);
			g.fillRect(rightX, rightY, currentWidth, height);
		}
	}
}
