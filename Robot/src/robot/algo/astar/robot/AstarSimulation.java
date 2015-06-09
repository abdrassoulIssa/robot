package robot.algo.astar.robot;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.serial.Serial;
import processing.video.Capture;
import robot.algo.astar.AStarPathFinder;
import robot.algo.astar.Path;
import robot.algo.astar.PathFinder;
import robot.algo.otsu.OtsuBinarize;
import static robot.algo.otsu.OTSUConstant.*;
import static robot.algo.otsu.ImageProcessing.*;
import static robot.algo.astar.robot.RobotActionPlanning.*;
import static robot.algo.astar.robot.MovingRobot.*;

@SuppressWarnings("serial")
public class AstarSimulation extends PApplet{
	
	//For drawing grid which represent the environment structure
	private Grid grid;
	//Create a image in the buffers
	private PGraphics pg;  
	private PImage pimage;
	//The point to which the robot moves.
	private Cell goalPoint = new Cell(0, 0);
	//Grabbing image using GSVideo
	private Capture cam;
	//For Robot navigation environment.
	private RobotMap map;
	private PathFinder finder;
	

	public void setup(){
		size(MWIDTH, MHEIGHT);
		pg	= createGraphics(MWIDTH, MHEIGHT);
		grid= new Grid(pg);
		
		//Initialize Xbee communication port
		InitXBeeCom(this);
		
		//Initialize webcam capture
		String[] cameras = Capture.list();
		if (cameras.length == 0) {
		  println("There are no cameras available for capture.");
		  exit();
		}
		
		println(Arrays.toString(cameras));
		cam = new Capture(this, cameras[0]);
		cam.start(); 
	}
	
	public void draw(){
		/*
		if (cam.available() == true) {
			  cam.read();
		}*/	
		grid.fillCell(0, 0, Color.BLUE);
		pg.fill(255);
		grid.drawGrid();
		grid.drawUnits(map);
		image(cam, 0, 0, width, height);
		image(pg,0,0);
	}

	public void mousePressed() {
	  // TRANSLATION OF MOUSE COORDINATES  IN THE SYSTEM OF THE GRID
	  int x = mouseX/CELLSIZE; 
	  int y = mouseY/CELLSIZE;
	  grid.removeUnit(goalPoint.getX()+""+goalPoint.getY());
	  restart();
	  grid.fillCell(x, y, Color.BLACK);
	  goalPoint = new Cell(y, x, Color.BLACK);
	}
	
	public void addMap(String filename){
		try {
			if (isMapNotEmpty()) {
				restart();
			}
			map    = new RobotMap(filename);
			finder = new AStarPathFinder(map, maxSearchDistance, diagMovment);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMap(int [][]robotmap){
		if (isMapNotEmpty()) {
			restart();
		}
		map    = new RobotMap(robotmap);
		finder = new AStarPathFinder(map, maxSearchDistance, diagMovment);
	}
	
	public void addImage(String filename){
		pimage = loadImage(filename);
		pimage.resize(MWIDTH, MHEIGHT);
		image(pimage,0,0, MWIDTH,MHEIGHT);
		
		OtsuBinarize.binarized = OtsuBinarize.binarize(toBufferedImage(pimage));
		int [][] map = new int[MROWS][MCOLS];
		OtsuBinarize.imageToMatrix(map);
		addMap(map);
	}
	
	private boolean isMapNotEmpty(){
		return map != null;
	}
	
	public void restart() {
		grid.removeAllUnits();
		grid.cleanGraphic();;
		background(255, 255, 255, 0);
		if(pimage != null)
		image(pimage,0,0, MWIDTH,MHEIGHT);
	}
	
	public void start(){
		  if(isMapNotEmpty()){
			  Path path = finder.findPath(new Robot(1), 0, 0,
					  		goalPoint.getX(), goalPoint.getY());
			  if(path != null){
				  for (int i = 0; i < path.getLength()-1; i++) {
					  int x = path.getY(i);
					  int y = path.getX(i);
					  grid.fillCell(x,y,Color.green);
				  }
			  }
		  }
	}
}
