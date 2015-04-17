package robot.algo.astar.robot;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import processing.core.PApplet;
import processing.core.PGraphics;
import robot.algo.astar.AStarPathFinder;
import robot.algo.astar.Path;
import robot.algo.astar.PathFinder;

@SuppressWarnings("serial")
public class DrawPath extends PApplet{
	private HashMap<String, Cell> cells;

	private PGraphics pg;  // create a image in the buffer
	@SuppressWarnings("unused")
	private Cell startPoint;
	private Cell wayPoint = new Cell(0, 0);
	
	private RobotMap map;
	private PathFinder finder;
	private boolean diagMovment = false;
	private static final int maxSearchDistance = 1000;
	private static final int cellsize = 30;
	private int rows, cols;
	

	public void setup(){
		size(640, 480);
		pg    = createGraphics(width, height);
		cells = new HashMap<String, Cell>();
		cols  = width/cellsize;
		rows  = height/cellsize;
		print(width);
	}
	
	public void draw(){
		
		fillCell(0, 0, Color.BLUE);
		g.fill(255);
		dynamicGrid();
		
		Iterator<String> keySetIterator = cells.keySet().iterator();
		while(keySetIterator.hasNext()){
		  String key = keySetIterator.next();
		  Cell cell  = cells.get(key);
		  int cellX  = cell.getX() * cellsize;
		  int cellY  = cell.getY() * cellsize;
		  int cellColor = cell.getColor();
		  g.fill(cellColor);
		  g.rect(cellX, cellY, cellsize, cellsize);
		}
		
		//The blocks will be colored by red
		if(isMapNotEmpty()){
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if(map.getTerrain(i, j) == 1){
						fillCell(j, i, Color.RED);
					}
				}
			}
		}
		
	}
	private void dynamicGrid(){
		pg.beginDraw(); 
		  pg.fill(255, 255, 255, 0);
		  for(int i=0;i<cols;i++){
		    for(int j=0;j<rows;j++){
		      int x = i*cellsize;
		      int y = j*cellsize;
		      pg.rect(x, y, cellsize, cellsize);
		    }
		  }
		pg.endDraw();
		image(pg,0,0);
	}

    public void fillCell(int x, int y) {
    	cells.put(new String(x+""+y), new Cell(x,y));
    	repaint();
    }
    public void fillCell(int x, int y, Color color) {
    	cells.put(new String(x+""+y), new Cell(x,y,color));
    	repaint();
    }
	public void setCellColor(int x,int y, Color color) {
		String key = new String(x+""+y);
		Cell cell  = cells.get(key);
		cell.setColor(color);
	}

    
	public void mousePressed() {
	  // TRANSLATION OF MOUSE COORDINATES  IN THE SYSTEM OF THE GRID
	  int x = mouseX/30; 
	  int y = mouseY/30;
	  println(x+"--"+y);
	  cells.remove(wayPoint.getX()+""+wayPoint.getY());
	  restart();
	  fillCell(x, y, Color.BLACK);
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
	
	private boolean isMapNotEmpty(){
		return map != null;
	}
	
	public void authorizeDiagMovment(boolean diagMovment){
		this.diagMovment = diagMovment;
	}
	
	public void restart() {
		if(cells != null){
			cells.clear();
			g.clear();
			pg.clear();
			background(255, 255, 255, 0);
		}
	}
	
	public void start(){
		  if(isMapNotEmpty()){
			  Path path = finder.findPath(new Robot(1), 0, 0,
					  		wayPoint.getX(), wayPoint.getY());
			  if(path != null){
				  for (int i = 0; i < path.getLength()-1; i++) {
					  int x = path.getY(i);
					  int y = path.getX(i);
					  fillCell(x,y,Color.green);
				  }
			  }
		  }
	}
}
