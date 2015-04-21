package swing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

public class Test {
	private static final int CELLSIZE = 5;
	private static final int WIDTH    = 20;
	private static final int HEIGHT   = 10;
	int rows     = HEIGHT/CELLSIZE;
	int cols     = WIDTH/CELLSIZE;
	private int [][] map;
	private int [][] pixels = { 
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
	

	
	public Test(){
		map = new int[rows][cols];
	}
	
    private  boolean findObstacle(int width, int height, int cellsize){
		PrintStream out = System.out;
		for(int  x = height; x < height+cellsize; x++){
			for(int y = width; y < width+cellsize; y++){
				
				int cellX = x/CELLSIZE;
				int cellY = y/CELLSIZE;
				if(pixels[x][y] == 255){
					map[cellX][cellY] = 1;
				}else{
					map[cellX][cellY] = 0;
				}
				out.print("("+x+" "+y+") ");
			}
			out.print("\n");
		}
		out.println();
    	return true;
    }
    
    private  boolean findObstacle(){
		PrintStream out = System.out;
		boolean res = false;
		for(int  x = HEIGHT; x < HEIGHT+CELLSIZE; x++){
			for(int y = WIDTH; y < WIDTH+CELLSIZE; y++){
				
				int cellX = x/CELLSIZE;
				int cellY = y/CELLSIZE;
				if(pixels[x][y] == 255){
					map[cellX][cellY] = 1;
					res = true;
					break;
				}
			}
			if(res = true) break;
		}
    	return res;
    }
    
    public void imageTOMatrix(){
		for (int i = 0; i <= (WIDTH - CELLSIZE); i = i + CELLSIZE) {
			for (int j = 0; j <=(HEIGHT -CELLSIZE); j = j + CELLSIZE) {
				findObstacle(i, j,CELLSIZE);
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
    }
    
	public static void main(String[] args) throws IOException {
		Test t = new Test();
		t.imageTOMatrix();

	}
}
