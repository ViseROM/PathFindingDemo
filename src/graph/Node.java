package graph;

import java.util.ArrayList;

/**
 * Node class represents a Node
 * @author Vachia Thoj
 *
 */
public class Node 
{
	//ID to represent the Node
	private int id;
	
	//List of Node's neighbors
	private ArrayList<Integer> neighbors;
	
	//Number of neighbors Node has
	private int numNeighbors;
	
	//Flag to see this Node has been visited
	private boolean visited;
	
	//To track a previous Node
	private Node previous;
	
	/**
	 * Constructor 
	 * @param id (int) The Id of the Node
	 */
	public Node(int id)
	{
		this.id = id;
		
		this.neighbors = new ArrayList<Integer>();
		this.numNeighbors = 0;
		
		this.visited = false;
		
		this.previous = null;
	}
	
	//Getter methods
	public int getId() {return id;}
	public ArrayList<Integer> getNeighbors() {return neighbors;}
	public int getNumNeighbors() {return numNeighbors;}
	public boolean isVisited() {return visited;}
	public Node getPrevious() {return previous;}
	
	//Setter methods
	public void setVisited(boolean b) {visited = b;}
	public void setPrevious(Node previous) {this.previous = previous;}
	
	/**
	 * Method adds a neighbor to the Node
	 * @param id (int) Id of the neighboring Node
	 */
	public void addNeighbor(int id)
	{
		neighbors.add(id);
		++numNeighbors;
	}
}
