package helper;

import java.awt.Graphics2D;

/**
 * TextSize class is a helper class that determines the width and height
 * of Strings on a given Graphics2D object
 * 
 * @author Vachia Thoj
 *
 */
public class TextSize 
{
	/**
	 * Method that determines the width of a String
	 * @param text The String that you want the width for
	 * @param g The Graphics2D objects that the String is to be drawn on
	 * @return the width of the String text as an integer
	 */
	public static int getTextWidth(String text, Graphics2D g)
	{
		int width = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
		
		return width;
	}
	
	/**
	 * Method that determines the height of a String
	 * @param text The String that you want the height for
	 * @param g The Graphics2D objects that the String is to be drawn on
	 * @return the height of the String text as an integer
	 */
	public static int getTextHeight(String text, Graphics2D g)
	{
		int height = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		
		return height;
	}
}