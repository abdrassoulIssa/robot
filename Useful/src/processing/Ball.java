package processing;

import processing.core.PApplet;
import processing.core.PImage;

public class Ball extends PApplet{
	private static final long serialVersionUID = 1L;
	
	PImage pimg;
	//COORDONNÉES DE LA BALLE DANS LE SYSTÈME DE LA GRILLE
	int x = (int)(Math.random()*21 + 0);
	int y = (int)(Math.random()*20 + 0); 
	int dx = 1;
	int dy = 1;

	public void setup() {
		size(640,600);
		frameRate(8);
		size(640,480);
	}

	public void draw() {
	  x += dx;
	  y += dy;
	  if (x >= 20 || x <= 0) dx = -dx;
	  if (y >= 19 || y <= 0) dy = -dy;
	  grille(30, 30); 
	  /*
	  // BALLE
	  fill(0);
	  ellipse(x*30 + 10, y*30 + 10, 20, 20);
	  fill(255);
	  */
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

	public void mousePressed() {
	  // CONVERSION DES COORDONNÉES DE LA SOURIS DANS LE SYSTEME DE LA GRILLE
	  x = (int)(mouseX/30.0); 
	  y = (int)(mouseY/30.0);
	  println("("+x+","+y+")");
	}

}
