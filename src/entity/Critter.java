package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import graph.*;
import main.GamePanel;
import map.TileMap;

/**
 * Critter class represents a Critter
 * @author Vachia Thoj
 *
 */
public class Critter extends Entity 
{
	//Speed of Critter
	private int speed;
	
	//Color of Critter
	private Color color;
	
	//Direction Critter is moving
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
		
	//Flag to see if player is moving
	private boolean moving;
	
	private TileMap tileMap;
	private Graph graph;
	private ArrayList<Node> route;
	private int currentRoute;
	
	//Target x and y coordinate Critter should move to
	private int targetX;
	private int targetY;
	
	//Timer and delay for Critter to move
	private long moveTimer;
	private long moveDelay;
	
	//Timer and delay to "highlight" Critter when it has eaten food
	private boolean highlight;
	private long highlightTimer;
	private long highlightDelay;
	
	/**
	 * Constructor
	 * 
	 * @param x (int) x-coordinate of Critter
	 * @param y (int) y-coordinate of Critter
	 * @param width (int) width of the Critter
	 * @param height (int) height of the Critter
	 * @param tileMap (TileMap) a TileMap
	 */
	public Critter(int x, int y, int width, int height, TileMap tileMap)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tileMap = tileMap;
		
		this.speed = 4;
		this.color = Color.RED;
		
		this.left = false;
		this.right = false;
		this.up = false;
		this.down = false;
		this.moving = false;
		
		this.graph = new Graph(tileMap);
		this.route = null;
		this.currentRoute = 0;
		
		this.moveTimer = System.nanoTime();
		this.moveDelay = 10;
		
		this.highlight = false;
		this.highlightDelay = 250;
	}
	
	//Getter methods
	public int getSpeed() {return speed;}
	public Color getColor() {return color;}
	public boolean isMoving() {return moving;}
	public ArrayList<Node> getRoute() {return route;}
	
	//Setter methods
	public void setSpeed(int speed) {this.speed = speed;}
	public void setColor(Color color) {this.color = color;}
	
	public void highlightCritter() 
	{
		highlight = true;
		highlightTimer = System.nanoTime();
	}
	
	/**
	 * Method that determines the next direction for Critter to move
	 */
	private void findDirection()
	{
		//do not find direction if Critter is still moving or if there is no route
		if(moving == true || route == null)
		{
			return;
		}
		
		//determine the next x and y coordinate for Critter to move to 
		targetX = (route.get(currentRoute).getId() % tileMap.getNumCols()) * tileMap.getTileSize();
		targetY = (route.get(currentRoute).getId() / tileMap.getNumCols()) * tileMap.getTileSize();
		
		//determine which direction to move to
		if(targetX < x) //move left
		{
			left = true;
			moving = true;
		}
		else if(targetX > x) //move right
		{
			right = true;
			moving = true;
		}
		else if(targetY < y) //move up
		{
			up = true;
			moving = true;
		}
		else if(targetY > y) //move down
		{
			down = true;
			moving = true;
		}
	}
	
	/**
	 * Method that increments the currentRoute index
	 */
	private void nextLocation()
	{
		if(route == null)
		{
			return;
		}
		
		if(targetX == x && targetY == y)
		{
			++currentRoute;
		}
		
		if(currentRoute >= route.size())
		{
			currentRoute = 0;
			route = null;
		}
	}
	
	/**
	 * Method that moves Critter
	 */
	private void move()
	{
		if(moving == true)
		{
			if(((System.nanoTime() - moveTimer) / 1000000) < moveDelay)
			{
				return;
			}
			else
			{
				moveTimer = System.nanoTime();
			}
		}
		
		if(left == true) //moving left
		{
			
			if((x - speed) < targetX)
			{
				x = targetX;
			}
			else
			{
				x = x - speed;
			}
			
			if(targetX == x)
			{
				left = false;
				moving = false;
			}
		}
		else if(right == true) //moving right
		{
			if((x + speed) > targetX)
			{
				x = targetX;
			}
			else
			{
				x = x + speed;
			}
			
			if(targetX == x)
			{
				right = false;
				moving = false;
			}
		}
		else if(up == true) //moving up
		{
			if((y - speed) < targetY)
			{
				y = targetY;
			}
			else
			{
				y = y - speed;
			}
			
			if(targetY == y)
			{
				up = false;
				moving = false;
			}
		}
		else if(down == true) //moving down
		{
			if(y + speed > targetY)
			{
				y = targetY;
			}
			else
			{
				y = y + speed;
			}
			
			if(targetY == y)
			{
				down = false;
				moving = false;
			}
		}
	}
	
	/**
	 * Method that checks if Player is at the edges of window
	 * Won't allow Player to go beyond boundaries
	 */
	private void checkBoundary()
	{
		if(x < 0) //Left boundary
		{
			x = 0;
		}
		else if(x > GamePanel.WIDTH - width) //Right boundary
		{
			x = GamePanel.WIDTH - width;
		}
		
		if(y < 0) //Top boundary
		{
			y = 0;
		}
		else if(y > GamePanel.HEIGHT - height) //Bottom boundary
		{
			y = GamePanel.HEIGHT - height;
		}
	}
	
	/**
	 * Method that finds a path for Critter to travel
	 * @param startLocation (int) nodeList start index
	 * @param endLocation (int) nodeList end index
	 */
	public void findPath(int startLocation, int endLocation)
	{	
		Node[] nodeList = graph.getNodeList();
		
		for(int i = 0; i < nodeList.length; i++)
		{
			nodeList[i].setPrevious(null);
			nodeList[i].setVisited(false);
		}
		
		ShortestPath shortestPath = new ShortestPath(nodeList, nodeList[startLocation], nodeList[endLocation]);
		//Use breadth first search to find shortest path
		shortestPath.bfs();
		
		route = shortestPath.getRoute();
	}
	
	/**
	 * Method that updates the Critter
	 */
	public void update()
	{
		//Determine which direction to move 
		findDirection();
		
		//Move Critter
		move();
		
		//Obtain next location to move to
		nextLocation();
		
		//Check if Critter has touched edges of window
		checkBoundary();
		
		if(highlight == true)
		{
			//Stop highlighting Critter if time is up
			if(((System.nanoTime() - highlightTimer) / 1000000) > highlightDelay)
			{
				highlight = false;
			}
		}
	}
	
	/**
	 * Method that draws the Critter
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		//Draw Critter
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		if(highlight == true)
		{ 
			g.setColor(new Color(126, 10, 16));
			g.setStroke(new BasicStroke(5));
			g.drawRect(x, y, width, height);
		}
	}
}
