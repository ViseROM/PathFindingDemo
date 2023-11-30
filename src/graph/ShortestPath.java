package graph;

import java.util.ArrayList;

/**
 * ShortestPath class represents the shortest path of a Graph
 * Finds the shortest path of a Graph
 * @author Vachia Thoj
 *
 */
public class ShortestPath 
{
	//A list of Nodes
	private Node[] nodeList;
	
	//A route or path of Nodes
	private ArrayList<Node> route;
	
	//The first Node of the route
	private Node start;
	
	//The last Node of the route
	private Node end;
	
	//A Queue
	private Queue queue;
	
	/**
	 * Constructor
	 * @param nodeList (Node[]) An array of Nodes
	 * @param start (Node) The starting Node
	 * @param end (Node) The end Node
	 */
	public ShortestPath(Node[] nodeList, Node start, Node end)
	{
		this.route = new ArrayList<Node>();
		this.nodeList = nodeList;
		this.start = start;
		this.end = end;
	}
	
	public ArrayList<Node> getRoute() {return route;}
	
	/**
	 * Method that performs the Breadth First Search algorithm
	 */
	public void bfs()
	{
		queue = new Queue();
		
		start.setVisited(true);
		queue.enqueue(start);
		
		//While the queue is NOT empty
		while(queue.isEmpty() == false)
		{
			//Dequeue Node from queue
			Node currentNode = queue.dequeue();
			
			//Obtain Node's neighbors
			ArrayList<Integer> neighbors = currentNode.getNeighbors();
			
			//Loop through neighbors' Node to find the end Node
			for(int i = 0; i < currentNode.getNumNeighbors(); i++)
			{
				if(nodeList[neighbors.get(i)].isVisited() == false)
				{
					//Visit Node's neighbor and add Node to the queue
					nodeList[neighbors.get(i)].setVisited(true);
					queue.enqueue(nodeList[neighbors.get(i)]);
					
					nodeList[neighbors.get(i)].setPrevious(currentNode);
					
					//If reached the end node then stop BFS
					if(nodeList[neighbors.get(i)].equals(end))
					{
						queue.clear();
						break;
					}
				}
			}
		}
		
		traceRoute();
	}
	
	private void traceRoute()
	{
		Node temp = end;
		
		ArrayList<Node> reverseList = new ArrayList<Node>();
		
		while(temp != null)
		{
			reverseList.add(temp);
			temp = temp.getPrevious();
		}
		
		for(int  i = (reverseList.size() - 1); i >= 0; i--)
		{
			route.add(reverseList.get(i));
		}
	}
	
	/**
	 * Prints the route
	 */
	public void printRoute()
	{
		for(int i = 0; i < route.size(); i++)
		{
			System.out.print(route.get(i).getId());
			
			if(i < route.size() - 1)
			{
				System.out.print("->");
			}
		}
		System.out.println();
	}
}