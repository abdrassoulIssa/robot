package robot.cmd.ctrl;

import processing.core.PApplet;
import processing.serial.*;
import processing.video.Capture;

@SuppressWarnings("serial")
public class MoveRobot extends PApplet{
	 //Variable for communication port (used for Xbee)
	private Serial port;
	private Capture cam;

	
	public void setup(){
		size(640, 480);
		//Initialize Xbee communication port
		int nbPorts = Serial.list().length;
		String XBeePort = Serial.list()[nbPorts -1];  
		println(XBeePort);
		port = new Serial(this, XBeePort, 38400);
		
		//Initialize webcam capture
		String[] cameras = Capture.list();
		if (cameras.length == 0) {
		  println("There are no cameras available for capture.");
		  exit();
		}
		cam = new Capture(this, cameras[1]);
		cam.start();      
	}

	public void draw() {
		if (cam.available() == true) {
			  cam.read();
		}
		image(cam, 0, 0);
	}
	
	public void keyPressed(){
		 //To control the ground robot manually
		   if (keyCode==UP){//Forward +
		  		port.write("d"+"003.200"+"a"+"000.000"+"f");
		      println("Forward");
		            }  
		   if (keyCode==DOWN){//Backward 
		  		port.write("d"+"000.000"+"a"+"000.000"+"f");
		      println("Backward");
		   }
		   if (keyCode==LEFT){//LEFT
		  		port.write("d"+"000.800"+"a"+"000.985"+"f");
		      println("LEFT");
		   }
		   if (keyCode==RIGHT){//RIGHT 
		  		port.write("d"+"000.800"+"a"+"-000.985"+"f");
		      println("RIGHT");
		   }  
	}
	
	

}
