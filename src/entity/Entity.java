package entity;

/**
 * Abstract class that represents an Entity
 * @author Vachia Thoj
 *
 */
public abstract class Entity 
{
	//x and y coordinate of Entity
	protected int x;
	protected int y;
	
	//width and height of Entity;
	protected int width;
	protected int height;
	
	protected Entity()
	{
		
	}
	
	//Getter Methods
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	//Setter Methods
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setWidth(int width) {this.width = width;}
	public void setHeight(int height) {this.height = height;}
}