package button;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * ImageButton class represents a Button that has an image 
 * @author Vachia Thoj
 *
 */
public class ImageButton extends Button
{		
	//Images for ImageButton
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage currentImage;
	
	/**
	 * Constructor
	 * @param x (int) x-coordinate of ImageButton
	 * @param y (int) y-coordinate of ImageButton
	 * @param image1 (BufferedImage) An image of the Button
	 * @param image2 (BufferedImage) An image of the Button
	 */
	public ImageButton(int x, int y, BufferedImage image1, BufferedImage image2)
	{
		this.x = x;
		this.y = y;
		this.image1 = image1;
		this.image2 = image2;
		
		this.currentImage = image1;
		this.width = currentImage.getWidth();
		this.height = currentImage.getHeight();
		
		this.visible = true;
		this.disabled = false;
	}
	
	/**
	 * Constructor
	 * @param image1 (BufferedImage) An image of the Button
	 * @param image2 (BufferedImage) An image of the Button
	 */
	public ImageButton(BufferedImage image1, BufferedImage image2)
	{
		this.image1 = image1;
		this.image2 = image2;
		
		this.currentImage = image1;
		this.width = currentImage.getWidth();
		this.height = currentImage.getHeight();
		
		this.visible = true;
		this.disabled = false;
	}
	
	//Getter Methods
	public BufferedImage getImage1() {return image1;}
	public BufferedImage getImage2() {return image2;}
	public BufferedImage getCurrentImage() {return currentImage;}
	
	//Setter methods
	public void setCurrentImage(BufferedImage currentImage) {this.currentImage = currentImage;}
	
	/**
	 * Method that updates ImageButton
	 */
	public void update()
	{
		if(disabled == true || visible == false)
		{
			return;
		}
		
		//Check if mouse has touched ImageButton
		checkIfMouseTouchingButton();
		
		//Check if mouse has clicked on ImageButton
		checkIfMouseClickingButton();
		
		//Change image of ImageButton if mouse is touching ImageButton
		if(mouseTouchingButton)
		{
			currentImage = image2;
			width = image2.getWidth();
			height = image2.getHeight();
		}
		else
		{
			currentImage = image1;
			width = image2.getWidth();
			height = image2.getHeight();
		}
	}
	
	/**
	 * Method that draws the current image of ImageButton
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	private void drawCurrentImage(Graphics2D g)
	{
		g.drawImage(currentImage, x, y, null);
	}
	
	/**
	 * Method that draws ImageButton
	 * @param g (Graphics2D) The Graphics2D object to be drawn on
	 */
	public void draw(Graphics2D g)
	{
		if(visible)
		{
			drawCurrentImage(g);
		}
	}
}