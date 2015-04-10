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
	private static final int maxSearchDistance = 500;
	

	public void setup(){
		size(600, 600);
		pg        = createGraphics(width, height);
		cells     = new HashMap<String, Cell>();
	}
	
	public void draw(){
		
		fillCell(0, 0, Color.BLUE);
		g.fill(255);
		dynamicGrid(30,30);
		
		Iterator<String> keySetIterator = cells.keySet().iterator();
		while(keySetIterator.hasNext()){
		  String key = keySetIterator.next();
		  Cell cell  = cells.get(key);
		  int cellX  = cell.getX() * 30;
		  int cellY  = cell.getY() * 30;
		  int cellColor = cell.getColor();
		  g.fill(cellColor);
		  g.rect(cellX, cellY, 30, 30);
		}
		
		//The blocks will be colored by red
		if(isMapNotEmpty()){
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					if(map.getTerrain(i, j) == 1){
						fillCell(i, j, Color.RED);
					}
				}
			}
		}
		
	}
	private void dynamicGrid(int gwidth,int gheight){
		int nl = width/gwidth;
		int nc = height/gheight;
		pg.beginDraw(); 
		  pg.fill(255, 255, 255, 0);
		  for(int i=0;i<nl;i++){
		    for(int j=0;j<nc;j++){
		      pg.rect(0 + i*gwidth, 0 + j*gheight, gwidth, gheight);
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
	  cells.remove(wayPoint.getX()+""+wayPoint.getY());
	  restart();
	  fillCell(x, y, Color.BLACK);
	  wayPoint = new Cell(x, y, Color.BLACK);
	  println("("+x+","+y+")");
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
					  fillCell(path.getX(i),path.getY(i),Color.green);
				  }
			  }
		  }
	}
}
