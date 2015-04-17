package processing;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

@SuppressWarnings("serial")
public class GrayScale extends PApplet{
	PImage map;
	int cellsize = 30;
	PGraphics grid;
	int cols, rows;
	// Declare 2D array
	
	
	public void setup(){
		 size(640,480); 
		 grid = createGraphics(640, 480);
		 println(System.getProperty("user.dir"));
		 map= loadImage("../img/navmap.JPG");
		
		 // Initialize columns and rows
		 cols = width/cellsize;
		 rows = height/cellsize;
	}
	
	public void draw(){
		map.loadPixels();
		
		int[][] myArray = new int[width][height];  
		// Initialize 2D array values
		for (int i = 0; i < width; i++) {
		  for (int j = 0; j < height; j++) {
			  int index = i+j*map.width;
		      double grayscale = (0.299 *red(map.pixels[index])) + (0.587 * green (map.pixels[index])) + (0.114 * blue (map.pixels[index]));
		   	  myArray[i][j] = (int)grayscale;
		  }
		}  
		 
		// Begin loop for columns
		  for (int i = 0; i < cols; i++) {
		    // Begin loop for rows
		    for (int j = 0; j < rows; j++) {
		      
		      // Scaling up to draw a rectangle at (x,y)
		      int x = i*cellsize;
		      int y = j*cellsize;
		      grid.beginDraw();
		      grid.stroke(0);
		      // For every column and row, a rectangle is drawn at an (x,y) location scaled and sized by videoScale.
		      fill(myArray[x][y]);
		      rect(x,y,cellsize,cellsize); 
		      grid.endDraw();
		    }
		  }
		image(grid, 0, 0);
	}
}

