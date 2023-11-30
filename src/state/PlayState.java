package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import button.*;
import entity.*;
import graph.Path;
import helper.Collision;
import helper.TextSize;
import main.GamePanel;
import manager.ImageManager;
import manager.MouseManager;
import map.TileMap;
import transition.*;

/**
 * PlayState class represents the "play area" of the game 
 * This is where the user sees the game being played
 * 
 * @author Vachia Thoj
 *
 */
public class PlayState extends State
{
	//To manage images
	private ImageManager imageManager;
	
	//TileMap
	private TileMap tileMap;
	
	//Critter
	private Critter critter;
	
	//Food
	private Food food;
	
	//Path
	private Path path;
	
	//Buttons
	private ImageButton pauseButton;
	private ImageButton playButton;
	private ImageButton pathButton;
	private ImageButton gridButton;
	private ImageButton speedButton;
	private ImageButton mapButton;
	
	//Speed text
	private String[] speedText;
	private int currentSpeedText;
	
	//Map name text
	private String[] mapText;
	private int currentMapText;
	
	//Pause text
	private String pauseText;
	
	//true if PlayState is paused otherwise false
	private boolean pause;
	
	//true if path is being shown otherwise false
	private boolean showPath;
	
	//Transition
	private HorizontalSplit horizontalSplit;
	
	/**
	 * Constructor
	 */
	public PlayState()
	{
		this.imageManager = ImageManager.instance();
		
		createTileMap();
		createPath();
		createCritter();
		createFood();
		createButton();
		createText();
		createTransitions();
		
		this.pause = false;
		this.showPath = true;
	}
	
////////////////////////////////////////////// CREATE METHODS //////////////////////////////////////////////
	
	private void createTileMap()
	{
		this.tileMap = new TileMap("/files/Map1.txt");
	}
	
	private void createCritter()
	{
		this.critter = new Critter(0, 0, 32, 32, tileMap);
	}
	
	private void createFood()
	{
		this.food = null;
	}
	
	private void createPath()
	{
		this.path = new Path(tileMap);
	}
	
	private void createButton()
	{
		BufferedImage[] buttonImages =  imageManager.getButtonImages();
		
		this.pauseButton = new ImageButton(buttonImages[0], buttonImages[1]);
		this.pauseButton.setX(0);
		this.pauseButton.setY(GamePanel.HEIGHT - pauseButton.getHeight());
		
		this.playButton = new ImageButton(buttonImages[2], buttonImages[3]);
		this.playButton.setX(0);
		this.playButton.setY(GamePanel.HEIGHT - pauseButton.getHeight());
		
		this.pathButton = new ImageButton(buttonImages[4], buttonImages[5]);
		this.pathButton.setX(pauseButton.getX() + pauseButton.getWidth());
		this.pathButton.setY(GamePanel.HEIGHT - pathButton.getHeight());
		
		this.gridButton = new ImageButton(buttonImages[6], buttonImages[7]);
		this.gridButton.setX(pathButton.getX() + pathButton.getWidth());
		this.gridButton.setY(GamePanel.HEIGHT - gridButton.getHeight());
		
		this.speedButton = new ImageButton(buttonImages[8], buttonImages[9]);
		this.speedButton.setX(GamePanel.WIDTH - (speedButton.getWidth() * 8));
		this.speedButton.setY(GamePanel.HEIGHT - speedButton.getHeight());
		
		this.mapButton = new ImageButton(buttonImages[10], buttonImages[11]);
		this.mapButton.setX(GamePanel.WIDTH - (mapButton.getWidth() * 4));
		this.mapButton.setY(GamePanel.HEIGHT - mapButton.getHeight());
	}
	
	private void createText()
	{
		this.speedText = new String[3];
		this.speedText[0] = "SLOW";
		this.speedText[1] = "NORMAL";
		this.speedText[2] = "FAST";
		this.currentSpeedText = 1;
		
		this.mapText = new String[3];
		this.mapText[0] = "MAP 1";
		this.mapText[1] = "MAP 2";
		this.mapText[2] = "MAP 3";
		this.currentMapText = 0;
		
		this.pauseText = "- PAUSED -";
	}
	
	private void createTransitions()
	{
		this.horizontalSplit = new HorizontalSplit(tileMap.getWidth(), tileMap.getHeight());
	}
	
	/**
	 * Method that changes the tileMap
	 */
	private void newMap()
	{
		//Change tileMap
		switch(currentMapText)
		{
			case 0:
				tileMap = new TileMap("/files/Map1.txt");
				break;
			case 1:
				tileMap = new TileMap("/files/Map2.txt");
				break;
			case 2:
				tileMap = new TileMap("/files/Map3.txt");
				break;
			default:
				tileMap = new TileMap("/files/Map1.txt");
				break;
		}
		
		createCritter();
		createFood();
		createPath();
		currentSpeedText = 1;
	}
	
	/**
	 * Method that creates/generate Food
	 */
	private void newFood()
	{
		while(true)
		{	
			//Create Food at a random location
			Random random = new Random();
			int x = (random.nextInt(tileMap.getNumCols()));
			int y = (random.nextInt(tileMap.getNumRows()));
			food = new Food(
					x * tileMap.getTileSize(), y * tileMap.getTileSize(), 
					tileMap.getTileSize(), tileMap.getTileSize()
			);
			
			//Make sure Food that is being created is not overlapping Critter
			//nor overlapping a "blocked" tile
			if(Collision.aabbCollision(critter, food) == false && tileMap.getTile(x, y).isBlocked() == false)
			{
				return;
			}
		}
	}
	
////////////////////////////////////////////// UPDATE METHODS //////////////////////////////////////////////
	
	/**
	 * Method that updates the Transitions
	 */
	private void updateTransitions()
	{
		horizontalSplit.update();
	}
	
	/**
	 * Method that updates the Buttons
	 */
	private void updateButtons()
	{	
		//Update Buttons
		if(pause == true)
		{
			playButton.update();
		}
		else
		{
			pauseButton.update();
		}
						
		pathButton.update();
		gridButton.update();
		speedButton.update();
		mapButton.update();
		
		//Check if an action needs to occur if Button has been clicked
		performButtonAction();
	}
	
	/**
	 * Method that performs an action when a Button has been clicked on
	 */
	private void performButtonAction()
	{
		if(pauseButton.isMouseClickingButton() == true)
		{
			pauseButton.setMouseClickingButton(false);
			
			//Pause
			pause = true;
		}
		else if(playButton.isMouseClickingButton() == true)
		{
			playButton.setMouseClickingButton(false);
			
			//Play
			pause = false;
		}
		else if(pathButton.isMouseClickingButton() == true)
		{
			pathButton.setMouseClickingButton(false);
			
			//Show or hide the path finding route
			showPath = (!showPath);
		}
		else if(gridButton.isMouseClickingButton() == true)
		{
			gridButton.setMouseClickingButton(false);
			
			//Show or hide the grid
			tileMap.setOutlined((!tileMap.isOutlined()));
		}
		else if(speedButton.isMouseClickingButton() == true)
		{
			speedButton.setMouseClickingButton(false);
			
			++currentSpeedText;			
			if(currentSpeedText >= 3)
			{
				currentSpeedText = 0;
			}
			
			//Change Critter speed
			switch(currentSpeedText)
			{
				case 0:
					critter.setSpeed(2);
					break;
				case 1:
					critter.setSpeed(4);
					break;
				case 2:
					critter.setSpeed(16);
					break;
				default:
					break;
			}
		}
		else if(mapButton.isMouseClickingButton() == true)
		{
			mapButton.setMouseClickingButton(false);
			
			++currentMapText;
			if(currentMapText >= 3)
			{
				currentMapText = 0;
			}
			
			//Run the horizontalSplit transition
			horizontalSplit.setRunning(true);
		}
	}
	
	/**
	 * Method that updates food
	 */
	private void updateFood()
	{
		food.update();
	}
	
	/**
	 * Method that updates Critter
	 */
	private void updateCritter()
	{
		critter.update();
	}
	
	/**
	 * Method that updates the PlayState
	 */
	public void update()
	{
		updateTransitions();
		
		if(horizontalSplit.isRunning())
		{
			return;
		}
		
		//If the transition is finished...
		if(horizontalSplit.isDone())
		{
			MouseManager.instance().clearPressedPoint();
			MouseManager.instance().clearReleasedPoint();
			
			//New Map
			newMap();
			
			//Create a new transition
			horizontalSplit = new HorizontalSplit(tileMap.getWidth(), tileMap.getHeight());
		}
		
		//Update Button
		updateButtons();
		
		if(pause == true)
		{
			return;
		}
		
		//If there is no Food on the tileMap...
		if(food == null)
		{
			//Create new Food
			newFood();
		}
		else
		{
			updateFood();
		}
		
		//Determine and obtain route for Critter to find Food
		if(food != null && food.isDoneGrowing() && critter.getRoute() == null)
		{
			//Calculate Critter location
			int critterLocation =
					(critter.getX() / tileMap.getTileSize()) +
					((critter.getY() / tileMap.getTileSize()) * tileMap.getNumCols());
			
			//Calculate Food location
			int foodLocation =
					(food.getX() / tileMap.getTileSize()) +
					((food.getY() / tileMap.getTileSize()) * tileMap.getNumCols());
			
			//Calculate a path for Critter to get to Food
			critter.findPath(critterLocation, foodLocation);
			
			//Obtain the route/path from Critter to Food
			path.setRoute(critter.getRoute());
		}
		
		updateCritter();
		
		if(food != null)
		{
			//Check if Critter and Food have collided
			if(Collision.aabbCollision(critter, food))
			{			
				food = null;
				critter.highlightCritter();
			}
		}
	}
	
////////////////////////////////////////////// DRAW METHODS //////////////////////////////////////////////
	
	/**
	 * Method that draws the background
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawBackground(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
	/**
	 * Method that draws the tile map
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawTileMap(Graphics2D g)
	{
		tileMap.draw(g);
	}
	
	/**
	 * Method that draws the Path
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawPath(Graphics2D g)
	{
		//Draw path
		if(showPath == true)
		{
			path.draw(g);
		}
	}
	
	/**
	 * Method that draws the Critter 
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawCritter(Graphics2D g)
	{
		//Draw critter
		critter.draw(g);
	}
	
	/**
	 * Method that draws the Food
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawFood(Graphics2D g)
	{
		//Draw food
		if(food != null)
		{
			food.draw(g);
		}
	}
	
	/**
	 * Method that draws the Buttons
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawButtons(Graphics2D g)
	{
		//Draw Buttons
		if(pause == true)
		{
			playButton.draw(g);
		}
		else
		{
			pauseButton.draw(g);
		}
				
		pathButton.draw(g);
		gridButton.draw(g);
		speedButton.draw(g);
		mapButton.draw(g);
	}
	
	/**
	 * Method that draws the Strings
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawStrings(Graphics2D g)
	{
		//Draw Strings
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.BOLD, 20));
		g.drawString(speedText[currentSpeedText], GamePanel.WIDTH - 220, GamePanel.HEIGHT - 10);
		g.drawString(
				mapText[currentMapText],
				GamePanel.WIDTH - 90,
				GamePanel.HEIGHT - 10
		);
		
		if(pause == true)
		{
			g.setColor(new Color(34, 177, 76));
			g.setFont(new Font("Courier New", Font.BOLD, 48));
			int pauseWidth = TextSize.getTextWidth(pauseText, g);
			g.drawString(
					pauseText,
					(GamePanel.WIDTH / 2) - (pauseWidth / 2),
					(GamePanel.HEIGHT / 2)
			);
		}
	}
	
	/**
	 * Method that draws the Transitions
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawTransitions(Graphics2D g)
	{
		horizontalSplit.draw(g);
	}
	
	/**
	 * Method that draws the PlayState
	 * 
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		drawBackground(g);
		drawTileMap(g);
		drawPath(g);
		drawCritter(g);
		drawFood(g);
		drawButtons(g);
		drawStrings(g);
		drawTransitions(g);
	}
}
