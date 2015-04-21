package swing;


import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class Test {
	
	//Image resolution
	private static final int WIDTH    = 20;
	private static final int HEIGHT   = 10;
	
	//The navigation map parameters
	private static final int CELLSIZE = 5;
	private static final int ROWS     = HEIGHT/CELLSIZE;
	private static final int COLS     = WIDTH/CELLSIZE;
	private static int [][] map = new int[ROWS][COLS];
	
	//The binarized image is represented as some pixels table
	private static int [][] pixels = { 
			{0,  0, 0, 0,   0,   0,   0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0}, 
			{0,  0, 0, 0,   0,   0,   0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0}, 
			{0,  0, 0, 0,   0,   0,   0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0}, 
			{0,  0, 0, 0,   0,   0,   0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0}, 
			{0,  0, 0, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0},
			{0,  0, 0, 0,   0,   255, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0},
			{0,  0, 0, 0,   0,   255, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0},
			{0,  0, 0, 255, 225, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0},
			{0,  0, 0, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0},
			{0,  0, 0, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0}
	};
	
	/**
	 * This method determine the property (obstable, void) of the corresponding cell 
	 * @param xStart the abscissa start position from which we explore the correspoding cell
	 * @param yStart the ordinate start position from which we explore the cell
	 * @return return true if the cell is an obstable
	 */
    private static  boolean findObstacle(int xStart, int yStart){
		PrintStream out = System.out;
		for(int  x = yStart; x < yStart+CELLSIZE; x++){
			for(int y = xStart; y < xStart+CELLSIZE; y++){
				
				int cellX = x/CELLSIZE;
				int cellY = y/CELLSIZE;
				if(pixels[x][y] == 255){
					map[cellX][cellY] = 1;
					break;
				}
				out.print("("+x+" "+y+") ");
			}
			out.print("\n");
		}
		out.println();
    	return true;
    }
    
    /**
     * We must to explore all grid cell determining obstacles
     */
    public static void imageToMatrix(){
		for (int i = 0; i <= (WIDTH - CELLSIZE); i = i + CELLSIZE) {
			for (int j = 0; j <=(HEIGHT -CELLSIZE); j = j + CELLSIZE) {
				findObstacle(i, j);
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
    }
    
	public static void main(String[] args) throws IOException {
		imageToMatrix();
	}
}
