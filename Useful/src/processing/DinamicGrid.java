package processing;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

public class DinamicGrid extends PApplet{
	private static final long serialVersionUID = 1L;
	private List<Point> fillCells;
	
	PImage pimg;
	//COORDONNÉES DE LA BALLE DANS LE SYSTÈME DE LA GRILLE
	int x, y;

	public void setup() {
		frameRate(8);
		size(640,480);
		fillCells = new ArrayList<Point>();
	}

	public void draw() {

        fillCell(20,0);
        fillCell(1,0);
        fillCell(2,2);
        fillCell(20,15);
		g.fill(255, 255, 255);
		grille(30, 30); 
		
		for (Point fillCell : fillCells) {
		    int cellX = fillCell.x * 30;
		    int cellY = fillCell.y * 30;
		    g.fill(255, 0, 0);
		    g.rect(cellX, cellY, 30, 30);
		}
		

	}
	
	private void grille(int gwidth,int gheight){
		int nl = width/gwidth;
		int nc = height/gheight;
		  // GRILLE
		  for(int i=0;i<nl;i++){
		    for(int j=0;j<nc;j++){
		      rect(0 + i*gwidth, 0 + j*gheight, gwidth, gheight);
		    }
		  }
	}
	
    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));
        repaint();
    }

	public void mousePressed() {
	  // CONVERSION DES COORDONNÉES DE LA SOURIS DANS LE SYSTEME DE LA GRILLE
	  x = (int)(mouseX/30.0); 
	  y = (int)(mouseY/30.0);
	  fillCell(x,y);
	  println("("+x+","+y+")");
	  println("("+mouseX+","+mouseY+")");
	}

}
