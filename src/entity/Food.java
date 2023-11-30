package entity;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Food class represents Food
 * @author Vachia Thoj
 *
 */
public class Food extends Entity 
{
	//Color of Food
	private Color color;
	
	//width and height Food starts out as
	private int startWidth;
	private int startHeight;
	
	//Speed that food grows
	private int growthRate;
	
	//Growth timer and delay
	private long growTimer;
	private long growDelay;
	
	//Flag to see if food has finished growing
	private boolean doneGrowing;
	
	//Default color of Food
	private static final Color DEFAULT_COLOR = Color.BLUE;
	
	/**
	 * Constructor
	 * @param x (int) x-coordinate of Food
	 * @param y (int) y-coordinate of Food
	 * @param width (int) width of the Food
	 * @param height (int) height of the Food
	 */
	public Food(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.growthRate = 4;
		this.startWidth = width / 4;
		this.startHeight = height / 4;
		
		this.growTimer = System.nanoTime();
		this.growDelay = 50;
		this.doneGrowing = false;
		
		this.color = DEFAULT_COLOR;
	}
	
	//Getter Methods
	public boolean isDoneGrowing() {return doneGrowing;}
	public Color getColor() {return color;}
	
	//Setter Methods
	public void setDoneGrowing(boolean b) {doneGrowing = b;}
	public void setColor(Color color) {this.color = color;}
	
	/**
	 * Method that updates Food
	 */
	public void update()
	{
		if(((System.nanoTime() - growTimer) / 1000000) > growDelay)
		{
			grow();
		}
	}
	
	/**
	 * Method that makes Food "grow"
	 */
	private void grow()
	{
		//stop growing if target width and height are reached
		if(startWidth == width || startHeight == height)
		{
			doneGrowing = true;
			return;
		}
		
		//increase width and height of Food
		startWidth += growthRate;
		startHeight += growthRate;
		
		growTimer = System.nanoTime();
	}
	
	/**
	 * Method that draws the Food
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		int newX = ((x + x + width) / 2) - (startWidth / 2);
		int newY = ((y + y + height) / 2) - (startHeight / 2);
		g.fillRect(newX, newY, startWidth, startHeight);
	}
}