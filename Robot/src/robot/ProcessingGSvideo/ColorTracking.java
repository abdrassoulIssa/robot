package robot.ProcessingGSvideo;
import processing.core.PApplet;
//www.learningprocessing.com // Example 16-11: Simple color tracking 
// Modified by Davide Rocchesso - 2012 
import codeanticode.gsvideo.*; 
public class ColorTracking extends PApplet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -593105168032214352L;
	// Variable for capture device 
	GSCapture video; 
	// A variable for the color we are searching for. 
	int trackColor; 
	public void setup() 
	{ 
		size(320,240); 
		video = new GSCapture(this,width,height,15); 
		video.start(); 
		// Start off tracking for red
	    trackColor = color(255,0,0); 
		smooth(); 
	} 

	public void draw() 
	{ 
		// Capture and display the video 
		if (video.available()) { 
			video.read(); 
		} 
		video.loadPixels(); 
		image(video,0,0); 
		// Before we begin searching, the "world record" for closest color 
		//is set to a high number that is easy for the first pixel to beat. 
		float worldRecord = 500; 
		// XY coordinate of closest color 
		int closestX = 0; 
		int closestY = 0; 
		// Begin loop to walk through every pixel 
		for (int x = 0; x < video.width; x ++ ) { 
			for (int y = 0; y < video.height; y ++ ) { 
				int loc = x + y*video.width; 
				// What is current color  
				int currentColor = video.pixels[loc]; 
				float r1 = red(currentColor); 
				float g1 = green(currentColor); 
				float b1 = blue(currentColor); 
				float r2 = red(trackColor); 
				float g2 = green(trackColor); 
				float b2 = blue(trackColor); 
				// Using euclidean distance to compare colors 
				float d = dist(r1,g1,b1,r2,g2,b2); 
				// We are using the dist( ) function to compare 
				//the current color with the color we are tracking. 
				// If current color is more similar to tracked color than 
				// closest color, save current location and current difference 
				if (d < worldRecord) { 
					worldRecord = d; 
					closestX = x; 
					closestY = y; 
				} 
			} 
		} 
		// We only consider the color found if its color distance is less than 10. 
		// This threshold of 10 is arbitrary and you can adjust this number 
		//depending on how accurate you require the tracking to be. 
		if (worldRecord < 10) { 
			// Draw a circle at the tracked pixel 
			fill(trackColor); 
			strokeWeight(4.0f); 
			stroke(0); 
			ellipse(closestX,closestY,16,16); 
		} 
	} 

	public void mousePressed() { 
		// Save color where the mouse is clicked in trackColor variable 
		int loc = mouseX + mouseY*video.width; 
		trackColor = video.pixels[loc]; 
	}
}