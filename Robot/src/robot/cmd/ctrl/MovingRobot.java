package robot.cmd.ctrl;

import java.util.Arrays;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.serial.*;
import processing.video.Capture;
import robot.algo.astar.Path;
import robot.algo.astar.robot.Grid;
import robot.algo.astar.robot.RobotMap;
import robot.algo.otsu.OtsuBinarize;
import static robot.algo.otsu.OTSUConstant.*;
import static robot.algo.otsu.ImageProcessing.*;

@SuppressWarnings("serial")
public class MovingRobot extends PApplet{
	 //Variable for communication port (used for Xbee)
	private static Serial port;
	private Capture cam;
	private static double distance = 001.000;
	private static double theta    = 000.985;
	private PGraphics pg;
	private Grid grid;
	private RobotMap robotmap;
	
	public void setup(){
		size(MWIDTH, MHEIGHT);
		pg	= createGraphics(MWIDTH, MHEIGHT);
		grid= new Grid(pg);
		//Initialize Xbee communication port
		/*
		int nbPorts = Serial.list().length;
		String XBeePort = Serial.list()[nbPorts -1];  
		println(XBeePort);
		port = new Serial(this, XBeePort, 38400);*/
		
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
		loadMaps();
		grid.drawGrid();
		if(robotmap != null){
			pg.beginDraw();
			for (int i = 0; i < MROWS; i++) {
				for (int j = 0; j < MCOLS; j++) {
					if(robotmap.getTerrain(i, j) == 1){
						int x = i*CELLSIZE;
						int y = j*CELLSIZE;
						pg.fill(color(255, 0,0));
						pg.rect(x, y, CELLSIZE, CELLSIZE);
					}
				}
			}
			pg.endDraw();
		}
		image(cam, 0, 0, width, height);
		image(pg,0,0);
		pg.clear();
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
		theta += 90;
		sendTrame(setCMD(000.000,theta));
	}

	public static void TURNRIGHT(){
		theta -= 90;
		sendTrame(setCMD(000.000,-theta));
	}
	public static void orientation(){
		if(theta == 360){
			theta = 0;
		}
		if(theta == -180){
			theta = 180;
		}
	}
	private static String setCMD(double distance, double theta){
		return "d"+distance+"a"+theta+"f";
	}
	
	private static void sendTrame(String trame){
		port.write(trame);
	}

	/**
	 * The robot receives commands as characters to move from one box to another.
	 * N to move upwards.
	 * S to move down.
	 * E to move to the right..
	 * W to move to the left..
	 * @param path
	 * @return chain of travels
	 */

	public String chainOfTravels(Path path){
		StringBuilder chain = new StringBuilder();
		for (int i = 0; i < path.getLength()-1; i++) {
			 int xC = path.getX(i), xF = path.getX(i+1);
			 int yC = path.getY(i), yF = path.getY(i+1);
			 
			 if(xC > xF && yC == yF){
				 chain.append("W");
			 }
			 if(xC < xF && yC == yF){
				 chain.append("E");
			 }
			 if(yC < yF && xC == xF){
				 chain.append("N");
			 }
			 if(yC > yF && xC == xF){
				 chain.append("S");
			 }
		}
		
		return chain.toString();
	}
	
	private void loadMaps(){
		PImage pimage = cam.get(0, 0, MWIDTH, MHEIGHT);
		OtsuBinarize.binarized = OtsuBinarize.binarize(toBufferedImage(pimage));
		int [][] map = new int[MROWS][MCOLS];
		OtsuBinarize.imageToMatrix(map);
		robotmap    = new RobotMap(map);
	}

}
