package processing;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

@SuppressWarnings("serial")
public class Binarize extends PApplet{

	PImage map;
	PImage map_bin = new PImage(640,480,PConstants.ARGB);
	double tolerance = 150.0;
	double red_value;
	int i;
	int numPixels;


	public void setup()
	{
		size(640,480); 
		numPixels = width * height;
		map= loadImage("../resources/img/navmap.JPG");
		map.resize(640,480);
	}

	public void draw(){
		 // Binarize image
		 for (i =  0; i < numPixels; i++){
				red_value = red (map.pixels[i]);
				if(red_value > tolerance){
				 	map_bin.pixels[i] = color(0, 0, 0);  
				}
				else{
				 	map_bin.pixels[i] = color(255, 255, 255);
				} 
		}
		map_bin.updatePixels(); 
		image(map_bin, 0, 0); 
	}
}



