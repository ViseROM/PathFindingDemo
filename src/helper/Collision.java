package helper;

import entity.Entity;

/**
 * Collision class is a helper class that determines collisions
 * between objects
 * 
 * @author Vachia Thoj
 *
 */
public class Collision 
{
	/**
	 * Method that determines if two Entities have collided
	 * Uses the Axis-Aligned Bounding Box (AABB) collision detection
	 * 
	 * @param e1 An Entity object
	 * @param e2 An Entity object
	 * @return true if the two Entities have collided, otherwise false
	 */
	public static boolean aabbCollision(Entity e1, Entity e2)
	{
		if(e1.getX() < e2.getX() + e2.getWidth() &&
		   e1.getX() + e1.getWidth() > e2.getX() &&
		   e1.getY() < e2.getY() + e2.getHeight() &&
		   e1.getY() + e1.getHeight() > e2.getY())
		{
			return true;
		}
				
		return false;
	}
	
	/**
	 * Method that determines if an Entity has collided with a coordinate/point
	 * @param x The x coordinate of the point
	 * @param y The y coordinate of the point
	 * @param e The Entity object
	 * @return true if Entity has collided with the point/coordinate, otherwise false
	 */
	public static boolean pointEntityCollision(int x, int y, Entity e)
	{
		if(x >= e.getX() && x <= (e.getX() + e.getWidth()) &&
		   y >= e.getY() && y <= (e.getY() + e.getHeight()))
		{
			return true;
		}
		
		return false;
	}
}
