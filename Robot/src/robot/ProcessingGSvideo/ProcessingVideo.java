package robot.ProcessingGSvideo;
import processing.core.PApplet;
import processing.video.*;
import static robot.algo.otsu.OTSUConstant.*;

@SuppressWarnings("serial")
public class ProcessingVideo extends PApplet{
	private Capture cam;

	public  void setup() {
		size(MWIDTH, MHEIGHT);
		
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
		image(cam, 0, 0);
	}
}