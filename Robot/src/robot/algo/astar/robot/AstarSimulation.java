package robot.algo.astar.robot;

import java.awt.Color;
import java.io.IOException;


import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import robot.algo.astar.AStarPathFinder;
import robot.algo.astar.Path;
import robot.algo.astar.PathFinder;
import robot.algo.otsu.OtsuBinarize;
import static robot.algo.otsu.OTSUConstant.*;
import static robot.algo.otsu.ImageProcessing.*;
import static robot.algo.astar.robot.RobotActionPlanning.*;

@SuppressWarnings("serial")
public class AstarSimulation extends PApplet{
	
	private Grid grid;
	private PGraphics pg;  // create a image in the buffer
	private PImage pimage;
	@SuppressWarnings("unused")
	private Cell startPoint;
	private Cell wayPoint = new Cell(0, 0);
	
	private RobotMap map;
	private PathFinder finder;
	

	public void setup(){
		size(MWIDTH, MHEIGHT);
		pg	= createGraphics(MWIDTH, MHEIGHT);
		grid= new Grid(pg);
	}
	
	public void draw(){
		grid.fillCell(0, 0, Color.BLUE);
		pg.fill(255);
		grid.drawGrid();
		grid.drawUnits(map);
		
		image(pg,0,0);
	}

	public void mousePressed() {
	  // TRANSLATION OF MOUSE COORDINATES  IN THE SYSTEM OF THE GRID
	  int x = mouseX/CELLSIZE; 
	  int y = mouseY/CELLSIZE;
	  grid.removeUnit(wayPoint.getX()+""+wayPoint.getY());
	  restart();
	  grid.fillCell(x, y, Color.BLACK);
	  wayPoint = new Cell(y, x, Color.BLACK);
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
					  		wayPoint.getX(), wayPoint.getY());
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
