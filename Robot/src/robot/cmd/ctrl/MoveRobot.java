package robot.cmd.ctrl;

import processing.core.PApplet;
import processing.serial.*;

@SuppressWarnings("serial")
public class MoveRobot extends PApplet{
	 //Variable for communication port (used for Xbee)
	Serial port;
	
	public void setup(){
		//Initialize Xbee communication port
		int nbPorts = Serial.list().length;
		String XBeePort = Serial.list()[nbPorts -1];  
		println(XBeePort);
		port = new Serial(this, XBeePort, 38400);
	}
/*
	public void draw() {
		
	}
	*/
	

}
