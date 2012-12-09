package exec.SpaceShadows;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

/**
 * A sprite to be displayed on the screen. Note that a sprite
 * contains no state information, i.e. its just the image and 
 * not the location. This allows us to use a single sprite in
 * lots of different places without having to store multiple 
 * copies of the image.
 * 
 * @author Kevin Glass
 */
public class Sprite {
	/** The image to be drawn for this sprite */
	private Image image;
	
	/**
	 * Create a new sprite based on an image
	 * 
	 * @param image The image that is this sprite
	 */
	public Image getImage()
	{
		return image;
	}
	public Sprite(Image image) {
		this.image = image;
	}
	
	/**
	 * Get the width of the drawn sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Get the height of the drawn sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight() {
		return image.getHeight(null);
	}
	
	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param g The graphics context on which to draw the sprite
	 * @param x The x location at which to draw the sprite
	 * @param y The y location at which to draw the sprite
	 */
	public void draw(Graphics g,int x,int y) {
		g.drawImage(image,x,y,null);
	}
	public void drawrotation(Graphics2D g,int x,int y, double angle) {
	/*
		//Graphics2D g2d = (Graphics2D)g;
		Graphics2D g2d = (Graphics2D)g;
	     AffineTransform origXform = g2d.getTransform();
	     AffineTransform newXform = (AffineTransform)(origXform.clone());
	     //center of rotation is center of the panel
	     int xRot = getWidth()/2;
	     int yRot = getHeight()/2;
	     newXform.rotate(Math.toRadians(angle), xRot, yRot);
	     g2d.setTransform(newXform);
	     //draw image centered in panel
	     int xx = (x + getWidth())/2;
	     int yy = (x + getHeight())/2;
	     g2d.drawImage(image, xx, yy, null);
	     g2d.setTransform(origXform);

		double angle2 = angle;
		while (angle2 < 0)
		{
			angle2 += 2 * Math.PI;
		}
		while (angle2 > Math.PI / 2)
		{
			angle2 -= Math.PI / 2;
		}
		int centralisation = (int) ((Math.cos(angle2 - Math.PI / 4) * Math.sqrt(2) * getWidth() - getWidth()));
		//System.out.println(angle2);
		x +=  centralisation;
		y -=  centralisation / 2;
		
		int xx = (int)  (Math.cos(-angle + Math.atan2(y, x)) * Math.sqrt(x * x + y * y));
		int yy = (int)  (Math.sin(-angle + Math.atan2(y, x)) * Math.sqrt(x * x + y * y));
		
		g.drawString(String.valueOf(x).concat(" ".concat(String.valueOf(y))), x, y - 80);
		g.drawString(String.valueOf(xx).concat(" ".concat(String.valueOf(yy))), x, y - 40);
		g.rotate(angle);
		g.drawImage(image, xx, yy, null);
		g.rotate(-angle);
		*/
	}
	
}