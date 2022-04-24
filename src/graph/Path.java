package graph;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

import map.TileMap;

/**
 * Path class represents a Path
 * @author Vachia Thoj
 *
 */
public class Path 
{
	//The TileMap
	private TileMap tileMap;
	
	//A route or path of Nodes
	private ArrayList<Node> route;
	
	public Path(TileMap tileMap)
	{
		this.tileMap = tileMap;
		route = null;
	}
	
	public void setRoute(ArrayList<Node> route) {this.route = route;}
	
	/**
	 * Method that draws the Path
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		if(route != null)
		{
			for(int i = 0; i < route.size(); i++)
			{
				int x = (route.get(i).getId() % tileMap.getNumCols()) * tileMap.getTileSize();
				int y = (route.get(i).getId() / tileMap.getNumCols()) * tileMap.getTileSize();
				
				if(i == 0)
				{
					g.setColor(new Color(255, 0 ,0, 75));
					g.fillRect(x, y, tileMap.getTileSize(), tileMap.getTileSize());
					
				}
				
				if(i > 0 && i != (route.size() - 1))
				{
					g.setColor(new Color(255, 255, 0, 50));
					g.fillRect(x, y, tileMap.getTileSize(), tileMap.getTileSize());
				}
				
			}
		}
	}
}
