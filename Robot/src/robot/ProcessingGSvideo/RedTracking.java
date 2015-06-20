package robot.ProcessingGSvideo;
// Daniel Shiffman
// http://www.learningprocessing.com
// Example 16-13: Simple motion detection

// Modified by Davide Rocchesso - 2012

import processing.core.PApplet;
import robot.algo.otsu.Otsuprocessing;
import codeanticode.gsvideo.*;

public class RedTracking extends PApplet{
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  //Variable for capture device
  private GSCapture video;
  // How different must a pixel be to be a "motion" pixel
  private float threshold = 0;
  private Otsuprocessing otsu;
  public void setup() {
    size(640,480);
	otsu  = new Otsuprocessing(this);
    video = new GSCapture(this, width, height, 30);
    video.start();
  }

  public void draw() {
    
    // Capture video
    if (video.available()) {
      video.read();
    }
    threshold = otsu.otsuTreshold(video.get());
   // loadPixels();
    video.loadPixels();
    // Begin loop to walk through every pixel
    for (int x = 0; x < video.width; x ++ ) {
      for (int y = 0; y < video.height; y ++ ) {
        
        int loc = x + y*video.width;            
        int current = video.pixels[loc];      
        
        float red_value = red(current);
        if (red_value > threshold) { 
          video.pixels[loc] = color(255,0,0);
        } else {
          //video.pixels[loc] = color(255,255,255);
        }
      }
    }
    updatePixels();
    image(video, 0, 0);
  }

}