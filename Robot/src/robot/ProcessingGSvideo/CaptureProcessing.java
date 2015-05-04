package robot.ProcessingGSvideo;
import processing.core.PApplet;
import processing.video.*;
@SuppressWarnings("serial")
public class CaptureProcessing extends PApplet{
	Capture cam;

	public void setup() {
		size(640, 480);
		String[] cameras = Capture.list();
		
		if (cameras.length == 0) {
		  println("There are no cameras available for capture.");
		  exit();
		} else {
		  println("Available cameras:");
		  for (int i = 0; i < cameras.length; i++) {
		    println(cameras[i]);
		  }
		  
		  // The camera can be initialized directly using an 
		  // element from the array returned by list():
		  cam = new Capture(this, cameras[0]);
		  cam.start();     
		}      
	}

	public void draw() {
		if (cam.available() == true) {
		  cam.read();
		}
		//cam.resize(WIDTH, HEIGHT);
		image(cam, 0, 0);
	}
	public void keyPressed() {
		if(keyCode == ENTER)
		  cam.stop();
	}
}