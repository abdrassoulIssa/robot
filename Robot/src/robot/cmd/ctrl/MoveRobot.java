package robot.cmd.ctrl;

import processing.core.PApplet;
import processing.serial.*;
import processing.video.Capture;

@SuppressWarnings("serial")
public class MoveRobot extends PApplet{
	 //Variable for communication port (used for Xbee)
	private static Serial port;
	private Capture cam;
	private static double distance = 001.000;
	private static double angle    = 000.985;
	@SuppressWarnings("unused")
	private static String orientation ="E";
	
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
		   if (keyCode==UP){
			   MOVEROBOT();
			   println("Forward");
		   }  
		   else if (keyCode==DOWN){
			   sendTrame(setCMD(000.000, 000.000));
		      println("Backward");
		   }
		   else if (keyCode==LEFT){
			   TURNLEFT();
			   println("LEFT");
		   }
		   else if (keyCode==RIGHT){
			   TURNRIGHT();
			   println("RIGHT");
		   }  
	}
	
	
	public static void MOVEROBOT(){
		sendTrame(setCMD(distance,000.000));
	}

	public static void TURNLEFT(){
		sendTrame(setCMD(000.000,angle));
	}

	public static void TURNRIGHT(){
		sendTrame(setCMD(000.000,-angle));
	}
	private static String setCMD(double distance, double angle){
		return "d"+distance+"a"+angle+"f";
	}
	
	private static void sendTrame(String trame){
		port.write(trame);
	}

}
