package processing;

import processing.core.PApplet;

public class GrilleTest extends PApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int division=20;
	float largeur;
	float hauteur;

	public void setup(){
	 size(640,480);
	 largeur=width/division;
	 hauteur=height/division;
	}

	public void draw(){
	 background(255);
	 for(int y=0;y<height;y+=hauteur){
	   for(int x=0;x<width;x+=largeur){
	     if(random(10)> 3){
	       line(x,y,x+largeur,y+hauteur);
	       
	     } else {
	       line(x,y+hauteur,x+hauteur,y);
	     }
	   }
	 }
	 //noLoop();
	}

	public void keyPressed(){
		//loop();
		noLoop();
	}

}
