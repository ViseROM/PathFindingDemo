package main;

import javax.swing.JFrame;

/**
 * Main is the class that you run/execute in order to start the program
 *  
 * @author Vachia Thoj
 *
 */
public class Main 
{
	public static void main(String[] args)
	{
		//Create Window
		JFrame window = new JFrame("Path Finding Demo");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create GamePanel
		GamePanel gamePanel = new GamePanel();
		
		//Add gamePanel to content pane
		window.setContentPane(gamePanel);
		
		window.pack();
		
		//Make window not resizable
		window.setResizable(false);
		
		//Center window onto computer screen
		window.setLocationRelativeTo(null);
		
		//Make window visible
		window.setVisible(true);
	}
}
