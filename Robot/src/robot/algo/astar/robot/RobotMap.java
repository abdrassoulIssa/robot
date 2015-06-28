package robot.algo.astar.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import robot.algo.astar.Mover;
import robot.algo.astar.TileBasedMap;

/**
 * The data map from multi-robot system. This holds the state and context of each tile
 * on the map. It also implements the interface required by the path finder. It's implementation
 * of the path finder related methods add specific handling for the types of units
 * and terrain in the multi-robot system.
 * 
 * @author Issa
 */
public class RobotMap implements TileBasedMap {
	/** The map width in tiles */
	public int WIDTH;
	/** The map height in tiles */
	public int HEIGHT;
	public static final int ROBOT  = 2;
	public static final int BLOCK  = 1;
		
	/** The terrain settings for each tile in the map */
	private int[][] terrain;
	
	//Create a new test map with some default configuration
	public RobotMap(int rows, int cols) {
		// create some test data
		WIDTH  = cols;
		HEIGHT = rows;
		terrain= new int[WIDTH][HEIGHT];
		fillArea(1,1,5,2,BLOCK);
		fillArea(6,1,2,5,BLOCK);

	}
	
	public RobotMap(String filename) throws IOException{
		readMatrix(filename);
	}
	
	//Constructor of copy
	public RobotMap(int [][]map){
		WIDTH  = map.length;
		HEIGHT = map[0].length;
		terrain= new int[WIDTH][HEIGHT];
		System.arraycopy(map, 0, terrain, 0, WIDTH);
	}
	
	private void readMatrix(String filename){
		try{
			Scanner sc = new Scanner(new File(filename));
		    sc.useLocale(Locale.US);

			WIDTH  = sc.nextInt();
			HEIGHT = sc.nextInt();
		    terrain = new int[WIDTH][HEIGHT];
		    for (int i=0; i<WIDTH; i++)
		    	 for (int j=0; j<HEIGHT; j++)
		    		 terrain[i][j]= sc.nextInt();
		    sc.close();
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
	@Override
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
	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}

	/**
	 * @see TileBasedMap#getHeightInTiles()
	 */
	@Override
	public int getHeightInTiles() {
		return HEIGHT;
	}

	/**
	 * @see TileBasedMap#getWidthInTiles()
	 */
	@Override
	public int getWidthInTiles() {
		return WIDTH;
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
	
}