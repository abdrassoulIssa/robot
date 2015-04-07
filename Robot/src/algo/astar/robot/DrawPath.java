package algo.astar.robot;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;

import algo.astar.AStarPathFinder;
import algo.astar.Path;
import algo.astar.PathFinder;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

@SuppressWarnings("serial")
public class DrawPath extends PApplet{
	private HashMap<String, Cell> cells;

	private PGraphics pg;  // create a image in the buffer
	@SuppressWarnings("unused")
	private PImage pimg;  // prepare a image
	private RobotMap map;
	private PathFinder finder;
	private static final int maxSearchDistance = 500;
	private static final String MAPPATH = "../data/map2.dat";

	
	public void setup(){
		size(640, 480);
		pg        = createGraphics(width, height);
		cells     = new HashMap<String, Cell>();
		try {
			map    = new RobotMap(MAPPATH);
			finder = new AStarPathFinder(map, maxSearchDistance, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if(map.getTerrain(i, j) == 1){
					fillCell(i, j, Color.RED);
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
	public void setColor(int x,int y, Color color) {
		String key = new String(x+""+y);
		Cell cell  = cells.get(key);
		cell.setColor(color);
	}

    
	public void mousePressed() {
	  // TRANSLATION OF MOUSE COORDINATES  IN THE SYSTEM OF THE GRID
	  int x = mouseX/30; 
	  int y = mouseY/30;
	  
	  cells.clear();
	  Path path = finder.findPath(new Robot(1), 0, 0, x, y);
	  if(path != null){
		  for (int i = 0; i < path.getLength(); i++) {
			  fillCell(path.getX(i),path.getY(i),Color.green);
		  }
	  }

	  println("("+x+","+y+")");
	}
}
