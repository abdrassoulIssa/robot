package robot.ProcessingGSvideo;
// Daniel Shiffman
// http://www.learningprocessing.com
// Example 16-13: Simple motion detection

// Modified by Davide Rocchesso - 2012

import processing.core.PApplet;
import processing.core.PImage;
import codeanticode.gsvideo.*;

public class RedTracking extends PApplet{
  // Variable for capture device
  GSCapture video;
  // Previous Frame
  PImage prevFrame;
  // How different must a pixel be to be a "motion" pixel
  float threshold = 150;

  public void setup() {
    size(640,480);
    video = new GSCapture(this, width, height, 30);
    video.start();
  }

  public void draw() {
    
    // Capture video
    if (video.available()) {
      video.read();
    }
    
   // loadPixels();
    video.loadPixels();
    // Begin loop to walk through every pixel
    for (int x = 0; x < video.width; x ++ ) {
      for (int y = 0; y < video.height; y ++ ) {
        
        int loc = x + y*video.width;            // Step 1, what is the 1D pixel location
        int current = video.pixels[loc];      // Step 2, what is the current color
        
        float red_value = red(current);
        if (red_value > threshold) { 
         // video.pixels[loc] = color(0);
        } else {
          video.pixels[loc] = color(255);
        }
      }
    }
    updatePixels();
    image(video, 0, 0);
  }

}