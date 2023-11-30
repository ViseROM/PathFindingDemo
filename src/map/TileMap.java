package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Color;
import java.awt.BasicStroke;

/**
 * TileMap class represents a map (Tile Map)
 * @author Vachia Thoj
 *
 */
public class TileMap 
{
	//x and y coordinate of where tileMap starts
	private int startX;
	private int startY;
	
	//x and y coordinate of where tileMap ends
	private int endX;
	private int endY;
	
	//Total width and height of tileMap
	private int width;
	private int height;
	
	//Size of a tile in tileMap
	private int tileSize;
	
	//Number columns and rows of tiles
	private int numCols;
	private int numRows;
	
	//The tileMap
	private Tile[][] tileMap;
	
	//Flag to outline map
	private boolean outlined;
	
	/**
	 * Constructor
	 * @param address String address of TileMap file
	 */
	public TileMap(String address)
	{
		this.startX = 0;
		this.startY = 0;
		this.tileSize = 32;
		
		loadMap(address);
		
		this.width = numCols * tileSize;
		this.height = numRows * tileSize;
		
		this.endX = startX + width;
		this.endY = startY + height;
		
		this.outlined = true;
	}
	
	/**
	 * Method that attempts to load a tile map from a given tile map file
	 * @param address file path of tile map data 
	 */
	private void loadMap(String address)
	{
		try
		{
			InputStream inputStream = getClass().getResourceAsStream(address);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			numCols = Integer.parseInt(bufferedReader.readLine());
			numRows = Integer.parseInt(bufferedReader.readLine());
			
			tileMap = new Tile[numRows][numCols];
			
			String delimeter = "\\s+";
			
			for(int i = 0; i < numRows; i++)
			{
				String line = bufferedReader.readLine();
				String [] tokens = line.split(delimeter);
				
				for(int j = 0; j < numCols; j++)
				{
					int x = j * tileSize;
					int y = i * tileSize;
					int value = Integer.parseInt(tokens[j]);
					tileMap[i][j] = new Tile(x, y, tileSize, tileSize, value);
					
					if(value == 1)
					{
						tileMap[i][j].setBlocked(true);
					}
				}
			}
			
			inputStream.close();
			bufferedReader.close();
		}
		catch(Exception e)
		{
			System.out.println("Error loading Map");
			e.printStackTrace();
		}
	}
	
	//Getter Methods
	public int getStartX() {return startX;}
	public int getStartY() {return startY;}
	public int getEndX() {return endX;}
	public int getEndY() {return endY;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getTileSize() {return tileSize;}
	public int getNumCols() {return numCols;}
	public int getNumRows() {return numRows;}
	public Tile[][] getTileMap() {return tileMap;}
	public boolean isOutlined() {return outlined;}
	
	public void setOutlined(boolean b) {outlined = b;}
	
	public Tile getTile(int col, int row) 
	{	
		if(row < 0 || row > (numRows - 1) ||
		   col < 0 || col > (numCols - 1))
		{
			return null;
		}
		
		return tileMap[row][col];
	}
	
	
	/**
	 * Method that draws the TileMap
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		//Draw tileMap
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				Tile temp = tileMap[i][j];
				g.setStroke(new BasicStroke(1));
				
				if(temp.getValue() == 0)
				{
					g.setColor(Color.WHITE);
				}
				else
				{
					g.setColor(Color.BLACK);
				}
				
				g.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				
				if(outlined == true)
				{
					g.setColor(Color.BLACK);
					g.drawRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				}
			}
		}
	}
}