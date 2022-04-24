package graph;

import map.*;

/**
 * Graph class represents a Graph
 * @author Vachia Thoj
 *
 */
public class Graph 
{
	//The TileMap
	private TileMap tileMap;
	
	//A List of Nodes
	private Node[] nodeList;
	
	//Number of Nodes in nodeList
	private int numNodes;
	
	/**
	 * Constructor
	 * 
	 * @param tileMap a TileMap; to be converted into a Graph
	 */
	public Graph(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
		this.numNodes = tileMap.getNumCols() * tileMap.getNumRows();
		this.nodeList = new Node[numNodes];
		
		createNodeList();
	}
	
	/**
	 * Method that creates a list of Nodes
	 */
	private void createNodeList()
	{
		Tile[][] map = tileMap.getTileMap();
		
		int index = 0;
		
		for(int i = 0; i < tileMap.getNumRows(); i++)
		{
			for(int j = 0; j < tileMap.getNumCols(); j++)
			{
				//Create Node and add it to nodeList
				nodeList[index] = new Node(index);
				
				//Find the Node's neighbor and add them to nodeList's neighbors
				
				//Check for left neighbor
				if(findTile(map, (j - 1), i) == true)	
				{
					int number = (i * tileMap.getNumCols()) + (j - 1);
					
					nodeList[index].addNeighbor(number);
				}
				
				//Check for right neighbor
				if(findTile(map, (j + 1), i) == true)
				{
					int number = (i * tileMap.getNumCols()) + (j + 1);
					
					nodeList[index].addNeighbor(number);
				}
				
				//Check for up neighbor
				if(findTile(map, j, (i - 1)) == true)
				{
					int number =  ((i - 1) * tileMap.getNumRows()) + j;
					
					nodeList[index].addNeighbor(number);
				}
				
				//Check for down neighbor
				if(findTile(map, j, (i + 1)) == true)
				{
					int number =  ((i + 1) * tileMap.getNumRows()) + j;
					
					nodeList[index].addNeighbor(number);
				}
				
				++index;
			}
		}
	}
	
	/**
	 * Method that determines if a tile that is not blocked exists
	 * 
	 * @param map the map of Tiles
	 * @param col integer column index
	 * @param row integer row index
	 * @return true if Tile was found at targeted column and row, otherwise false
	 */
	private boolean findTile(Tile[][] map, int col, int row)
	{
		if(col < 0 || col > (tileMap.getNumCols() - 1) ||
		   row < 0 || row > (tileMap.getNumRows() - 1))
		{
			return false;
		}
		
		if(map[row][col].isBlocked())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//Getter Methods
	public TileMap getTileMap() {return tileMap;}
	public Node[] getNodeList() {return nodeList;}
	public int getNumNodes() {return numNodes;}
	
	/**
	 * Prints content of NodeList
	 */
	public void printNodeList()
	{
		for(int i = 0; i < numNodes; i++)
		{
			System.out.print(nodeList[i].getId() + " : ");
			
			for(int j = 0; j < nodeList[i].getNumNeighbors(); j++)
			{
				System.out.print(nodeList[i].getNeighbors().get(j) + " ");
			}
			
			System.out.println();
		}
	}
}