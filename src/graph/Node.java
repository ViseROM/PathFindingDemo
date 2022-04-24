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
	
	private boolean visited;
	
	//To track a previous Node
	private Node previous;
	
	public Node(int id)
	{
		this.id = id;
		
		neighbors = new ArrayList<Integer>();
		numNeighbors = 0;
		
		visited = false;
		
		previous = null;
	}
	
	//Getter methods
	public int getId() {return id;}
	public ArrayList<Integer> getNeighbors() {return neighbors;}
	public int getNumNeighbors() {return numNeighbors;}
	public boolean isVisited() {return visited;}
	public Node getPrevious() {return previous;}
	
	public void setVisited(boolean b) {visited = b;}
	public void setPrevious(Node previous) {this.previous = previous;}
	
	
	public void addNeighbor(int id)
	{
		neighbors.add(id);
		++numNeighbors;
	}
}
