package graph;

import java.util.ArrayList;

/**
 * Queue class represents a Queue
 * @author Vachia Thoj
 *
 */
public class Queue 
{
	//Represents a queue
	private ArrayList<Node> queue;
	
	//Number of Nodes in queue
	private int size;
	
	/**
	 * Constructor
	 */
	public Queue()
	{
		this.queue = new ArrayList<Node>();
		this.size = 0;
	}
	
	public ArrayList<Node> getQueue() {return queue;}
	public int getSize() {return size;}
	public boolean isEmpty()
	{
		if(size == 0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Adds a node to the Queue
	 * @param node A Node object
	 */
	public void enqueue(Node node)
	{
		queue.add(node);
		++size;
	}
	
	/**
	 * Method that removes the Node at the front of the Queue
	 * @return A Node object
	 */
	public Node dequeue()
	{
		if(size == 0)
		{
			return null;
		}
		
		Node temp = queue.get(0);
		queue.remove(0);
		--size;
		
		return temp;
	}
	
	/**
	 * Method that peeks at what is at the front of the Queue
	 * @return A Node object
	 */
	public Node peek()
	{
		if(size == 0)
		{
			return null;
		}
		
		return queue.get(0);
	}
	
	/**
	 * Method that removes all Nodes from queue
	 */
	public void clear()
	{
		queue.clear();
		size = 0;
	}
}
