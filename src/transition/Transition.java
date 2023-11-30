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
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getSpeed() {return speed;}
	public boolean isRunning() {return running;}
	public boolean isDone() {return done;}
	
	//Setter methods
	public void setSpeed(int speed) {this.speed = speed;}
	public void setRunning(boolean b) {running = b;}
	public void setDone(boolean b) {done = b;}
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
}
