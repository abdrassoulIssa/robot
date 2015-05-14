package robot.algo.astar.robot;

import static robot.algo.otsu.OTSUConstant.CELLSIZE;
import static robot.algo.otsu.OTSUConstant.MCOLS;
import static robot.algo.otsu.OTSUConstant.MROWS;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;

import processing.core.PGraphics;

public class Grid {
	private PGraphics parent; 
	private HashMap<String, Cell> cells;
	
	public Grid(PGraphics p) {
		parent = p;
		cells = new HashMap<String, Cell>();
	}
	
	/**
	 * 
	 */
	public void drawGrid(){
		parent.beginDraw(); 
		  parent.fill(255, 255, 255, 0);
		  for(int i=0;i<MCOLS;i++){
		    for(int j=0;j<MROWS;j++){
		      int x = i*CELLSIZE;
		      int y = j*CELLSIZE;
		      parent.rect(x, y, CELLSIZE, CELLSIZE);
		    }
		  }
		parent.endDraw();
	}
	
	/**
	 * The unit can be :
	 * -robot
	 * -obstacle
	 * -nothing
	 * in the multi-robot system.
	 */
	public void drawUnits(RobotMap map){
		Iterator<String> keySetIterator = cells.keySet().iterator();
		while(keySetIterator.hasNext()){
		  String key = keySetIterator.next();
		  Cell cell  = cells.get(key);
		  int cellX  = cell.getX() * CELLSIZE;
		  int cellY  = cell.getY() * CELLSIZE;
		  int cellColor = cell.getColor();
		  parent.beginDraw();
		  parent.fill(cellColor);
		  parent.rect(cellX, cellY, CELLSIZE, CELLSIZE);
		  parent.endDraw();
		}
		
		if(map != null)
		for (int i = 0; i < MROWS; i++) {
			for (int j = 0; j < MCOLS; j++) {
				if(map.getTerrain(i, j) == 1){
					fillCell(j, i, Color.RED);
				}
			}
		}
	}
	
	public void fillCell(int x, int y) {
    	cells.put(new String(x+""+y), new Cell(x,y));
    }
    public void fillCell(int x, int y, Color color) {
    	cells.put(new String(x+""+y), new Cell(x,y,color));
    }
	public void setCellColor(int x,int y, Color color) {
		String key = new String(x+""+y);
		Cell cell  = cells.get(key);
		cell.setColor(color);
	}
	
	public void removeAllUnits(){
		if(cells.size() !=0)
			cells.clear();
	}
	public void removeUnit(String key){
		cells.remove(key);
	}
	public void cleanGraphic(){
		parent.clear();
	}

}
