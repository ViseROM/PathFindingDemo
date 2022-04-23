package transition;

import java.awt.Graphics2D;

/**
 * Abstract class that represents a Transition
 * @author Vachia Thoj
 *
 */
public abstract class Transition 
{
	//width and height of the area that the Transition will occur on
	protected int width;
	protected int height;
	
	//Speed of Transition
	protected int speed;
	
	//Flag to indicate if Transition is running/executing
	protected boolean running;
	
	//Flag to indicate if the Transition has been completed
	protected boolean done;
	
	//Default speed of transition
	private static final int DEFAULT_SPEED = 5;
	
	//Constructor
	protected Transition(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		speed = DEFAULT_SPEED;
	}
	
	//Getter methods
	protected int getWidth() {return width;}
	protected int getHeight() {return height;}
	protected int getSpeed() {return speed;}
	protected boolean isRunning() {return running;}
	protected boolean isDone() {return done;}
	
	//Setter methods
	protected void setSpeed(int speed) {this.speed = speed;}
	protected void setRunning(boolean b) {running = b;}
	protected void setDone(boolean b) {done = b;}
	
	protected abstract void update();
	protected abstract void draw(Graphics2D g);
}
