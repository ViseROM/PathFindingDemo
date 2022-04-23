package transition;

import java.awt.Graphics2D;

/**
 * TransitionManager class manages and executes Transitions
 * @author Vachia Thoj
 *
 */
public class TransitionManager 
{
	//Width and height of the area the Transition will occur on
	private int width;
	private int height;
	
	//The current Transition
	private Transition currentTransition;
	
	//The type of Transition
	private TransitionType transitionType;
	
	//Constructor
	public TransitionManager(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.currentTransition = null;
		this.transitionType = null;
	}
	
	public int getSpeed()
	{
		if(currentTransition != null)
		{
			return currentTransition.getSpeed();
		}
		
		return 0;
	}
	
	
	public boolean isRunning()
	{
		if(currentTransition == null)
		{
			return false;
		}
		
		if(currentTransition.isRunning() == true)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean isDone()
	{
		if(currentTransition == null)
		{
			return false;
		}
		
		if(currentTransition.isDone() == true)
		{
			return true;
		}
		
		return false;
	}
	
	public void setSpeed(int speed)
	{
		if(currentTransition != null)
		{
			currentTransition.setSpeed(speed);
		}
	}
	
	public void setCurrentTransition(Transition currentTransition)
	{
		this.currentTransition = currentTransition;
	}
	
	public void setTransition(TransitionType transitionType)
	{
		this.transitionType = transitionType;
		
		switch(transitionType)
		{
			case SLIDE_LEFT:
				currentTransition = new SlideLeft(width, height);
				break;
			case SLIDE_RIGHT:
				currentTransition = new SlideRight(width, height);
				break;
			case SLIDE_UP:
				currentTransition = new SlideUp(width, height);
				break;
			case SLIDE_DOWN:
				currentTransition = new SlideDown(width, height);
				break;
			case HORIZONTAL_SPLIT:
				currentTransition = new HorizontalSplit(width, height);
				break;
			case VERTICAL_SPLIT:
				currentTransition = new VerticalSplit(width, height);
				break;
			case FADE_TO_BLACK:
				currentTransition = new FadeToBlack(width, height);
				break;
			case FADE_TO_WHITE:
				currentTransition = new FadeToWhite(width, height);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Method that tells Transition to start
	 */
	public void startTransition()
	{
		if(transitionType != null)
		{
			setTransition(transitionType);
			currentTransition.setRunning(true);
		}
	}
	
	/**
	 * Method that updates the Transition
	 */
	public void update()
	{
		if(currentTransition != null && currentTransition.running == true)
		{
			currentTransition.update();
		}
	}
	
	/**
	 * Method that draws the Transition
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		//Draw Transition
		if(currentTransition != null && currentTransition.running == true)
		{
			currentTransition.draw(g);
		}
	}
}
