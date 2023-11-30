package button;

import entity.Entity;
import helper.Collision;
import manager.MouseManager;


/**
 * An abstract class that represents a Button
 * @author Vachia Thoj
 *
 */
public abstract class Button extends Entity
{	
	//To manage mouse events
	protected transient MouseManager mouseManager;
	
	//Flag to see if mouse is touching/clicking Button
	protected transient boolean mouseTouchingButton;
	protected transient boolean mouseClickingButton;
	
	//Flag to see if Button is disabled
	protected transient boolean disabled;
	
	//Flag to see if Button is visible
	protected transient boolean visible;
	
	/**
	 * Constructor
	 */
	protected Button()
	{
		this.mouseManager = MouseManager.instance();
		this.mouseClickingButton = false;
		this.mouseTouchingButton = false;
	}
	
	//Getter methods
	public boolean isMouseTouchingButton() {return mouseTouchingButton;}
	public boolean isMouseClickingButton() {return mouseClickingButton;}
	public boolean isDisabled() {return disabled;}
	public boolean isVisible() {return visible;}
	
	//Setter Methods
	public void setMouseTouchingButton(boolean b) {mouseTouchingButton = b;}
	public void setMouseClickingButton(boolean b) {mouseClickingButton = b;}
	public void setDisabled(boolean b) {disabled = b;}
	public void setVisible(boolean b) {visible = b;}
	
	/**
	 * Method that checks if mouse has clicked (pressed and released) the Button
	 * @return true if mouse has clicked Button, otherwise false
	 */
	protected boolean pressedAndReleased()
	{
		//If mouse has been clicked; pressed and released
		if(mouseManager.getPressedPoint() != null && mouseManager.getReleasedPoint() != null)
		{
			//Obtain the coordinates where the mouse was pressed
			int pressedX = mouseManager.getPressedPoint().getX();
			int pressedY = mouseManager.getPressedPoint().getY();
	    	
			//Obtain the coordinates where the mouse was released
			int releasedX = mouseManager.getReleasedPoint().getX();
			int releasedY = mouseManager.getReleasedPoint().getY();
	    	
			//Check if mouse clicked (pressed and released) the Button
			if(Collision.pointEntityCollision(pressedX, pressedY, this) && 
			   Collision.pointEntityCollision(releasedX, releasedY, this))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Method that checks if the mouse has touched the Button
	 * Method will set the (boolean) mouseTouchingButton
	 * to true/false depending if mouse is touching the Button
	 */
	public void checkIfMouseTouchingButton()
	{
		//Check if mouse is touching Button
		if(mouseManager.getCurrentPoint() != null)
		{
			//x and y location of mouse point
			int mouseX = mouseManager.getCurrentPoint().getX();
			int mouseY = mouseManager.getCurrentPoint().getY();
			
			//Check if mouse is touching the Button and flag it accordingly
			if(Collision.pointEntityCollision(mouseX, mouseY, this))
			{
				mouseTouchingButton = true;
			}
			else
			{
				mouseTouchingButton = false;
			}
		}
	}
	
	/**
	 * Method that checks if mouse has clicked (pressed and released) the Button
	 * Method will set (boolean) mouseClickingButton to true if the mouse
	 * has clicked the Button
	 */
	public void checkIfMouseClickingButton()
	{
		if(mouseManager.isMouseReleased() == true)
		{
			//Check if mouse is clicking button
			if(pressedAndReleased() == true)
			{
				mouseClickingButton = true;
    			
				//Clear the pressed and released Points
				mouseManager.clearPressedPoint();
				mouseManager.clearReleasedPoint();
			}
		}
	}
}