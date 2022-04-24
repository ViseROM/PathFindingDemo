package state;

import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import main.GamePanel;
import entity.*;
import manager.ImageManager;
import manager.TransitionManager;
import map.TileMap;
import graph.Path;
import helper.TextSize;
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
	
	//To manage transition
	private TransitionManager transitionManager;
	
	//TileMap
	private TileMap tileMap;
	
	//Critter
	private Critter critter;
	
	//Food
	private Food food;
	
	//Path
	private Path path;
	
	//Buttons
	private Button pauseButton;
	private Button playButton;
	private Button pathButton;
	private Button gridButton;
	private Button speedButton;
	private Button mapButton;
	
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
	
	/**
	 * Constructor
	 */
	public PlayState()
	{
		this.imageManager = ImageManager.instance();
		
		this.tileMap = new TileMap("/files/Map1.txt");
		
		this.transitionManager = new TransitionManager(tileMap.getWidth(), tileMap.getHeight());
		this.transitionManager.setTransition(TransitionType.HORIZONTAL_SPLIT);
		
		this.critter = new Critter(0, 0, 32, 32, tileMap);
		
		this.food = null;
		
		this.path = new Path(tileMap);
		
		createButton();
		
		this.pause = false;
		this.showPath = true;
		
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
	
	/**
	 * Method that creates/initializes Buttons
	 */
	private void createButton()
	{
		BufferedImage[] buttonImages =  imageManager.getButtonImages();
		
		pauseButton = new Button(buttonImages[0], buttonImages[1]);
		pauseButton.setX(0);
		pauseButton.setY(GamePanel.HEIGHT - pauseButton.getHeight());
		
		playButton = new Button(buttonImages[2], buttonImages[3]);
		playButton.setX(0);
		playButton.setY(GamePanel.HEIGHT - pauseButton.getHeight());
		
		pathButton = new Button(buttonImages[4], buttonImages[5]);
		pathButton.setX(pauseButton.getX() + pauseButton.getWidth());
		pathButton.setY(GamePanel.HEIGHT - pathButton.getHeight());
		
		gridButton = new Button(buttonImages[6], buttonImages[7]);
		gridButton.setX(pathButton.getX() + pathButton.getWidth());
		gridButton.setY(GamePanel.HEIGHT - gridButton.getHeight());
		
		speedButton = new Button(buttonImages[8], buttonImages[9]);
		speedButton.setX(GamePanel.WIDTH - (speedButton.getWidth() * 8));
		speedButton.setY(GamePanel.HEIGHT - speedButton.getHeight());
		
		mapButton = new Button(buttonImages[10], buttonImages[11]);
		mapButton.setX(GamePanel.WIDTH - (mapButton.getWidth() * 4));
		mapButton.setY(GamePanel.HEIGHT - mapButton.getHeight());
		
		buttonImages = null;
	}
	
	/**
	 * Method that checks for collision between two entities
	 * Uses AABB collision algorithm
	 * @param e1 An Entity
	 * @param e2 An Entity
	 * @return true if both entities have collided, otherwise false
	 */
	private boolean collided(Entity e1, Entity e2)
	{
		if(e1.getX() < e2.getX() + e2.getWidth() &&
		   e1.getX() + e1.getWidth() > e2.getX() &&
		   e1.getY() < e2.getY() + e2.getHeight() &&
		   e1.getY() + e1.getHeight() > e2.getY())
		{
			return true;
		}
		
		return false;
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
		
		critter = new Critter(0, 0, 32, 32, tileMap);
		food = null;
		path = new Path(tileMap);
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
			food = new Food(x * tileMap.getTileSize(), y * tileMap.getTileSize(), 
						    tileMap.getTileSize(), tileMap.getTileSize());
			
			//Make sure Food that is being created is not overlapping Critter
			//nor overlapping a "blocked" tile
			if(collided(critter, food) == false && tileMap.getTile(x, y).isBlocked() == false)
			{
				return;
			}
		}
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
	}
	
	/**
	 * Method that performs an action when a Button has been clicked on
	 */
	private void buttonActions()
	{
		if(pauseButton.isMouseClickingButton() == true)
		{
			//Pause
			pause = true;
			pauseButton.setMouseClickingButton(false);
		}
		else if(playButton.isMouseClickingButton() == true)
		{
			//Play
			pause = false;
			playButton.setMouseClickingButton(false);
		}
		else if(pathButton.isMouseClickingButton() == true)
		{
			//Show or hide the path finding route
			showPath = (!showPath);
			pathButton.setMouseClickingButton(false);
		}
		else if(gridButton.isMouseClickingButton() == true)
		{
			//Show or hide the grid
			tileMap.setOutlined((!tileMap.isOutlined()));
			gridButton.setMouseClickingButton(false);
		}
		else if(speedButton.isMouseClickingButton() == true)
		{
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
					critter.setSpeed(8);
					break;
				default:
					break;
			}
			
			speedButton.setMouseClickingButton(false);
		}
		else if(mapButton.isMouseClickingButton() == true)
		{
			++currentMapText;
			if(currentMapText >= 3)
			{
				currentMapText = 0;
			}
			
			//Start transition
			transitionManager.startTransition();
			
			mapButton.setMouseClickingButton(false);
			
			//Disable Buttons
			pauseButton.setDisabled(true);
			playButton.setDisabled(true);
			pathButton.setDisabled(true);
			gridButton.setDisabled(true);
			speedButton.setDisabled(true);
			mapButton.setDisabled(true);
		}
	}
	
	/**
	 * Method that updates the PlayState
	 */
	public void update()
	{
		//If the transition is finished
		if(transitionManager.isDone())
		{	
			//Clear transition
			transitionManager.setCurrentTransition(null);
			
			//New Map
			newMap();
			
			//Enable Buttons
			pauseButton.setDisabled(false);
			playButton.setDisabled(false);
			pathButton.setDisabled(false);
			gridButton.setDisabled(false);
			speedButton.setDisabled(false);
			mapButton.setDisabled(false);
		}
				
		if(transitionManager.isRunning())
		{
			//Update Transition
			transitionManager.update();
			updateButtons();
			return;
		}
		
		//Update Button
		updateButtons();
		
		//Perform an action if Button has been clicked
		buttonActions();
		
		if(pause == true)
		{
			return;
		}
		
		//If there is no Food on the tileMap
		if(food == null)
		{
			//Create new Food
			newFood();
		}
		else
		{
			//Update Food
			food.update();
		}
		
		//Determine and obtain route for Critter to find Food
		if(food != null && food.isDoneGrowing() && critter.getRoute() == null)
		{
			//Calculate Critter location
			int critterLocation = (critter.getX() / tileMap.getTileSize())  +
								  ((critter.getY() / tileMap.getTileSize()) * tileMap.getNumCols());
			
			//Calculate Food location
			int foodLocation = (food.getX() / tileMap.getTileSize()) +
							   ((food.getY() / tileMap.getTileSize()) * tileMap.getNumCols());
			
			//Calculate a path for Critter to get to Food
			critter.findPath(critterLocation, foodLocation);
			
			//Obtain the route/path from Critter to Food
			path.setRoute(critter.getRoute());
		}
		
		//Update Critter
		critter.update();
		
		if(food != null)
		{
			//Check if Critter and Food have collided
			if(collided(critter, food) == true)
			{			
				food = null;
				critter.highlightCritter();
			}
		}
	}
	
	/**
	 * Method that draws the background
	 * @param g The Graphics2D object to be drawn on
	 */
	private void drawBackground(Graphics2D g)
	{
		//Draw Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	
	/**
	 * Method that draws the Path
	 * @param g The Graphics2D object to be drawn on
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
	 * @param g The Graphics2D object to be drawn on
	 */
	private void drawCritter(Graphics2D g)
	{
		//Draw critter
		critter.draw(g);
	}
	
	/**
	 * Method that draws the Food
	 * @param g The Graphics2D object to be drawn on
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
	 * @param g The Graphics2D object to be drawn on
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
	 * @param g The Graphics2D object to be drawn on
	 */
	private void drawStrings(Graphics2D g)
	{
		//Draw Strings
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.BOLD, 20));
		g.drawString(speedText[currentSpeedText], GamePanel.WIDTH - 220, GamePanel.HEIGHT - 10);
		g.drawString(mapText[currentMapText], GamePanel.WIDTH - 90, GamePanel.HEIGHT - 10);
		
		if(pause == true)
		{
			g.setColor(new Color(34, 177, 76));
			g.setFont(new Font("Courier New", Font.BOLD, 48));
			int pauseWidth = TextSize.getTextWidth(pauseText, g);
			g.drawString(pauseText, (GamePanel.WIDTH / 2) - (pauseWidth / 2), (GamePanel.HEIGHT / 2));
			
		}
	}
	
	/**
	 * Method that draws the Transition
	 * @param g The Graphics2D object to be drawn on
	 */
	private void drawTransition(Graphics2D g)
	{
		//If transition is running, draw Transition
		if(transitionManager.isRunning() == true)
		{
			transitionManager.draw(g);
		}
	}
	
	/**
	 * Method that draws the PlayState
	 * 
	 * @param g The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		//Draw Background
		drawBackground(g);
		
		//Draw tileMap
		tileMap.draw(g);
		
		//Draw path
		drawPath(g);
		
		//Draw critter
		drawCritter(g);
		
		//Draw food
		drawFood(g);
		
		//Draw Buttons
		drawButtons(g);
		
		//Draw Strings
		drawStrings(g);
		
		if(transitionManager.isDone())
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		
		//Draw Transition if necessary
		drawTransition(g);
	}
}
