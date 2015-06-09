package robot.algo.astar.robot;

import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.serial.*;
import processing.video.Capture;
import robot.algo.astar.robot.Grid;

import static robot.algo.otsu.OTSUConstant.*;

@SuppressWarnings("serial")
public class MovingRobot extends PApplet{
	 //Variable for communication port (used for Xbee)
	private static Serial port;
	private Capture cam;
	private static String distance = "000.800";
	private PGraphics pg;
	private Grid grid;

	
	public void setup(){
		size(MWIDTH, MHEIGHT);
		pg	= createGraphics(MWIDTH, MHEIGHT);
		grid= new Grid(pg);
		//Initialize Xbee communication port
		/*
		int nbPorts = Serial.list().length;
		String XBeePort = Serial.list()[nbPorts -1];  
		println(XBeePort);
		port = new Serial(this, XBeePort, 38400);
		*/
		//Initialize webcam capture
		String[] cameras = Capture.list();
		if (cameras.length == 0) {
		  println("There are no cameras available for capture.");
		  exit();
		}
		System.out.println(Arrays.toString(cameras));
		cam = new Capture(this, cameras[0]);
		cam.start();      
	}

	public void draw() {
		if (cam.available() == true) {
			  cam.read();
		}
		grid.drawGrid();
		image(cam, 0, 0, width, height);
		image(pg,0,0);
	}
	
	public void keyPressed(){
		String chain = "ARALA";
		
		if(keyCode == ENTER){
			ActionsPerforming(chain);
		}
		 //To control the ground robot manually
		if (keyCode==UP){
		 GOSTRAIGHT();
		 println("GOSTRAIGHT");
		}  
		else if (keyCode==DOWN){
		  sendTrame(setCMD("000.000", "000.000"));
		  println("Backward");
		}
		else if (keyCode==LEFT){
		  GOLEFT();
		  println("LEFT");
		}
		else if (keyCode==RIGHT){
		  GORIGHT();
		  println("RIGHT");
		} 	   
	}
	
	public static void ActionsPerforming(String chain){
		try {
			char cmd;
			for (int i = 0; i < chain.length(); i++) {
				cmd = chain.charAt(i);
				switch (cmd) {
					case 'A':
					{
						GOSTRAIGHT();
						
							Thread.sleep(10000);
	
						break;
					}
					case 'R':
					{
						GORIGHT();
						Thread.sleep(15000);
						break;
					}
					case 'L':
					{
						GOLEFT();
						Thread.sleep(15000);
						break;
					}
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void GOSTRAIGHT(){
		String cmd = setCMD(distance,"000.000");
		System.out.println(cmd);
		sendTrame(cmd);
	}

	public static void GOLEFT(){
		sendTrame(setCMD("000.150","001.570"));
	}

	public static void GORIGHT(){
		sendTrame(setCMD("000.150","-001.570"));
	}
	
	private static String setCMD(String distance, String theta){
		return "d"+distance+"a"+theta+"f";
	}
	
	private static void sendTrame(String trame){
		port.write(trame);
	}
	

}
