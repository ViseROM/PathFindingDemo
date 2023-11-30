package manager;

import entity.Point;

/**
 * MouseManager class keeps track and manages the mouses actions
 * 
 * @author Vachia Thoj
 *
 */
public class MouseManager 
{
	//For singleton
	private static MouseManager mouseManager;
	
	//Current location of mouse
	private Point currentPoint;
	
	//Location where the mouse pressed
	private Point pressedPoint;
	
	//Location where the mouse released
	private Point releasedPoint;
	
	//Flag to see if mouse has bee pressed/released
	private boolean mousePressed;
	private boolean mouseReleased;
	
	/**
	 * Constructor (private)
	 */
	private MouseManager()
	{
		currentPoint = null;
		pressedPoint = null;
		releasedPoint = null;
		
		mousePressed = false;
		mouseReleased = false;
	}
	
	/**
	 * Method to be called in order to obtain MouseManager object (Singleton)
	 * @return MouseManager object
	 */
	public static MouseManager instance()
	{
		if(mouseManager == null)
		{
			mouseManager = new MouseManager();
		}
		
		return mouseManager;
	}
	
	//Getter methods
	public Point getPressedPoint() {return pressedPoint;}
	public Point getReleasedPoint() {return releasedPoint;}
	public Point getCurrentPoint() {return currentPoint;}
	public boolean isMousePressed() {return mousePressed;}
	public boolean isMouseReleased() {return mouseReleased;}
		
	//Setter methods
	public void setPressedPoint(int mouseX, int mouseY) {pressedPoint = new Point(mouseX, mouseY);}
	public void setReleasedPoint(int mouseX, int mouseY) {releasedPoint = new Point(mouseX, mouseY);}
	public void setCurrentPoint(int mouseX, int mouseY) {currentPoint = new Point(mouseX, mouseY);}
	public void setMousePressed(boolean b) {mousePressed = b;}
	public void setMouseReleased(boolean b) {mouseReleased = b;}
		
	public void clearPressedPoint() {pressedPoint = null;}
	public void clearReleasedPoint() {releasedPoint = null;}
	public void clearCurrentPoint() {currentPoint = null;}
}