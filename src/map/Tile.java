package map;

import entity.Entity;

/**
 * Tile class represents a single Tile
 * @author Vachia Thoj
 *
 */
public class Tile extends Entity 
{
	//integer value of Tile
	private int value;
	
	//Flag to see if tile is blocked; blocked meaning tile cannot be "stepped" on or collided with
	private boolean blocked;
	
	//min and max possible values for Tile
	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 1;
	private static final int DEFAULT_VALUE = MIN_VALUE;
	
	/**
	 * Constructor
	 * 
	 * @param x integer x coordinate of Tile
	 * @param y integer y coordinate of Tile
	 * @param width integer width (pixels) of Tile
	 * @param height integer height (pixels) of Tile
	 * @param value integer value associated with Tile
	 */
	public Tile(int x, int y, int width, int height, int value)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setValue(value);
		
		this.blocked = false;
	}
	
	//Getter Methods
	public int getValue() {return value;}
	public boolean isBlocked() {return blocked;}
	
	
	//Setter Methods
	public void setValue(int value) 
	{
		if(value < MIN_VALUE || value > MAX_VALUE)
		{
			this.value = DEFAULT_VALUE;
		}
		else
		{
			this.value = value;
		}
	}
	public void setBlocked(boolean b) {blocked = b;}
}