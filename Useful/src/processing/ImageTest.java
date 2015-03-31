package processing;

import processing.core.*;

public class ImageTest extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PImage pimg;
	int x,y;
	
	public void draw(){
		//image(pimg, 0, 0);
		//noLoop();
	}

	public void setup()
	{
		
		size(640,480);
		pimg = loadImage("../img/1.jpg");
		pimg.resize(640, 480);
		pimg.loadPixels();
		background(pimg);
	}
	
	public void mousePressed(){
		background(pimg);
		x = mouseX;
		y = mouseY;
		text("("+x+","+y+")", x,y);
		
		double R = red(pimg.pixels[y*width+x]);
		double G = green(pimg.pixels[y*width+x]);
		double B = blue(pimg.pixels[y*width+x]);
		print("R : "+R+"\n");
		print("G : "+G+"\n");
		print("B : "+B+"\n\n");
	}
}
