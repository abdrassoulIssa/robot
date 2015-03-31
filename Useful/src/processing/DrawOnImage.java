package processing;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class DrawOnImage extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PImage limage; // préparer une image
	PGraphics pg; // creer une image dans la mémoire

	public void setup(){
		 size(640,480);
		 pg = createGraphics(width, height);
		 limage=loadImage("../img/hollande.jpg");
	}

	public void draw(){
		 background(0);
		 if (keyPressed == false){
			 image(limage,0,0, width,height);
		 }
		 grille(30, 30);
	}
	
	private void grille(int gwidth,int gheight){
		int nl = width/gwidth;
		int nc = height/gheight;
		  // GRILLE
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

}
