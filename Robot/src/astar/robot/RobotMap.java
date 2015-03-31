package astar.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import astar.Mover;
import astar.TileBasedMap;

/**
 * The data map from our example game. This holds the state and context of each tile
 * on the map. It also implements the interface required by the path finder. It's implementation
 * of the path finder related methods add specific handling for the types of units
 * and terrain in the example game.
 * 
 * @author Issa
 */
public class RobotMap implements TileBasedMap {
	/** The map width in tiles */
	public static final int WIDTH  = 20;
	/** The map height in tiles */
	public static final int HEIGHT = 20;
	public static final int ROBOT  = 2;
	public static final int BLOCK  = 1;
		
	/** The terrain settings for each tile in the map */
	private int[][] terrain;
	
	//Create a new test map with some default configuration
	public RobotMap() {
		// create some test data
		terrain= new int[WIDTH][HEIGHT];
		fillArea(1,1,5,2,BLOCK);
		fillArea(6,1,2,5,BLOCK);
		fillArea(0,6,5,2,BLOCK);
		fillArea(8,4,5,2,BLOCK);
		fillArea(8,8,6,10,BLOCK);
	}
	
	public RobotMap(String filename){
		try
		{
			File fp=new File(filename);
		    @SuppressWarnings("resource")
			Scanner sc = new Scanner(fp);
		    sc.useLocale(Locale.US);
		    int nl=sc.nextInt();
		    int nc=sc.nextInt();
		    terrain = new int[nl][nc];
		    for (int i=0; i<nl; i++)
		    	 for (int j=0; j<nc; j++)
		    terrain[i][j]= sc.nextInt();
		 }
		 catch (FileNotFoundException e){
		  System.out.println("File not found.");
		 }		
	}

	/**
	 * Fill an area with a given terrain type
	 * 
	 * @param x The x coordinate to start filling at
	 * @param y The y coordinate to start filling at
	 * @param width The width of the area to fill
	 * @param height The height of the area to fill
	 * @param type The terrain type to fill with
	 */
	private void fillArea(int x, int y, int width, int height, int type) {
		for (int xp=x;xp<x+width;xp++) {
			for (int yp=y;yp<y+height;yp++) {
				terrain[xp][yp] = type;
			}
		}
	}
	
	
	/**
	 * Get the terrain at a given location
	 * 
	 * @param x The x coordinate of the terrain tile to retrieve
	 * @param y The y coordinate of the terrain tile to retrieve
	 * @return The terrain tile at the given location
	 */
	public int getTerrain(int x, int y) {
		return terrain[x][y];
	}
	
	/**
	 * @see TileBasedMap#blocked(Mover, int, int)
	 */
	public boolean blocked(Mover mover, int x, int y) {
		// if there's a unit at the location, then it's blocked
		if (getTerrain(x,y) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * @see TileBasedMap#getCost(Mover, int, int, int, int)
	 */
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}

	/**
	 * @see TileBasedMap#getHeightInTiles()
	 */
	public int getHeightInTiles() {
		return WIDTH;
	}

	/**
	 * @see TileBasedMap#getWidthInTiles()
	 */
	public int getWidthInTiles() {
		return HEIGHT;
	}


	public  void display(){
		for(int i=0;i<WIDTH;i++){
			System.out.println(Arrays.toString(terrain[i]));
		}
	}
	
	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[]args){
		//RobotMap robot = new RobotMap("data/map.dat");
		RobotMap robot = new RobotMap();
		robot.display();
	}
}