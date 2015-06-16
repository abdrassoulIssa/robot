package processing;

import processing.core.PApplet;
import processing.video.Capture;

public class RealTimeBinarise extends PApplet {

	private Capture cam;

	public  void setup() {
		size(640, 480);
		
		//Initialize webcam capture
		String[] cameras = Capture.list();
		if (cameras.length == 0) {
		  println("There are no cameras available for capture.");
		  exit();
		} 
		else {
		  // The camera can be initialized directly using an 
		  // element from the array returned by list():
			cam = new Capture(this, cameras[0]);
			cam.start();     
		}      
	}

	public  void draw() {
		if (cam.available() == true) {
		  cam.read();
		}
		//cam.resize(WIDTH, HEIGHT);
		//image(cam, 0, 0);
		image(cam, 0, 0, 640, 480);
	
		delay(2000);
	}

}
