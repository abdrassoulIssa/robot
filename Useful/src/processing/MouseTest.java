package processing;

import processing.core.PApplet;

public class MouseTest extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	public void draw(){
		background(255);
	}
	
	public void mouseFollowing(){
		background(255);
		ellipse(x, y, 10, 10);
		x = mouseX;
		y = mouseY;
		print("(%d,%d)\n",x,y);
	}
	
	public void keyPressed()
	{
		switch (keyCode) {
			case ENTER:print("ENTER");break;
			case UP:print("UP");break;
			case DOWN:print("DOWN");break;
			case LEFT:print("LEFT");break;
			case RIGHT:print("RIGHT");break;
	
			default:
				break;
		}
	}
	
	public void mousePressed(){
		x = mouseX;
		y = mouseY;
		print("("+x+","+y+")\n");
	}

	

}
