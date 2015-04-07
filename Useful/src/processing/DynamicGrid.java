package processing;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class DynamicGrid extends PApplet{
	private static final long serialVersionUID = 1L;
	private List<Point> fillCells;
	private PGraphics pg;  // create a image in the buffer
	private PImage pimg;  // prepare a image
	private int x; //Mouse click x coordinate
	private int y; //Mouse click y coordinate

	public void setup() {
		frameRate(8);
		size(640,480);
		fillCells = new ArrayList<Point>();
		pg     = createGraphics(width, height);
		pimg   = loadImage("../img/blobs.png");
	}

	public void draw() {

        fillCell(20,0);
        fillCell(1,0);
        fillCell(2,2);
        fillCell(20,15);
        
		g.fill(255, 255, 255);
		image(pimg,0,0, width,height);
		dynamicGrid(30, 30); 
		for (Point fillCell : fillCells) {
		    int cellX = fillCell.x * 30;
		    int cellY = fillCell.y * 30;
		    g.fill(255, 0, 0);
		    g.rect(cellX, cellY, 30, 30);
		}
		

	}
	
	@SuppressWarnings("unused")
	private void staticGrid(int gwidth,int gheight){
		int nl = width/gwidth;
		int nc = height/gheight;

		for(int i=0;i<nl;i++){
		    for(int j=0;j<nc;j++){
		      rect(0 + i*gwidth, 0 + j*gheight, gwidth, gheight);
		    }
		  }
	}
	
	private void dynamicGrid(int gwidth,int gheight){
		int nl = width/gwidth;
		int nc = height/gheight;
		pg.beginDraw(); 
		//pg.fill(0, 0, 0, 0);
		  pg.fill(255, 255, 255, 0);
		  for(int i=0;i<nl;i++){
		    for(int j=0;j<nc;j++){
		      pg.rect(0 + i*gwidth, 0 + j*gheight, gwidth, gheight);
		    }
		  }
		pg.endDraw();
		image(pg,0,0);
	}
	
    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));
        repaint();
    }

	public void mousePressed() {
	  // TRANSLATION OF MOUSE COORDINATES  IN THE SYSTEM OF THE GRID
	  x = (int)(mouseX/30.0); 
	  y = (int)(mouseY/30.0);
	  fillCell(x,y);
	  println("("+x+","+y+")");
	  println("("+mouseX+","+mouseY+")");
	}

}
