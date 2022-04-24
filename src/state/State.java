package state;

import java.awt.Graphics2D;

/**
 * Abstract class of a State
 * @author Vachia Thoj
 *
 */
public abstract class State 
{
	protected State()
	{
		
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
}