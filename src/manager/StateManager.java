package manager;

import java.awt.Graphics2D;

import state.*;

/**
 * StateManager class manages the different States
 * @author Vachia Thoj
 *
 */
public class StateManager 
{
	//For singleton
	private static StateManager stateManager;
	
	//CurrentState
	private State currentState;
	
	/**
	 * Constructor (private)
	 */
	private StateManager()
	{
		currentState = new PlayState();
	}
	
	/**
	 * Method to be called to obtain StateManager object (Singleton)
	 * @return StateManager object
	 */
	public static StateManager instance()
	{
		if(stateManager == null)
		{
			stateManager = new StateManager();
		}
		
		return stateManager;
	}
	
	/**
	 * Method that updates the current state
	 */
	public void update()
	{
		currentState.update();
	}
	
	/**
	 * Method that draws the current state
	 * 
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		currentState.draw(g);
	}
}