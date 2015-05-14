package robot.algo.otsu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import robot.algo.astar.robot.Grid;
import static robot.algo.otsu.OTSUConstant.*;
public class ImageProcessing extends PApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8896851793354753712L;
	private PImage img = null;
	private PImage imgcopy;
	private Grid grid;
	private PGraphics pg;
	public void setup(){
		size(MWIDTH,MHEIGHT);
		pg	= createGraphics(MWIDTH, MHEIGHT);
		grid= new Grid(pg);
	}
	
	public void draw(){
		
		if(img != null){
			image(img, 0, 0);
		}
		else{
			grid.drawGrid();
			image(pg, 0, 0);
		}
	}
	
	
	public void addImage(String filename){
		img = loadImage(filename);
		img.resize(MWIDTH, MHEIGHT);
		imgcopy = img;
		repaint();
	}
	
	public void binairiseImage(){
		BufferedImage binarized = OtsuBinarize.binarize(toBufferedImage(img));
		img = toPImage(binarized);
		image(img, 0, 0);      
		repaint();
	}
	
	public boolean saveImage(File file) {
		String format ="JPG";
		BufferedImage image = toBufferedImage(img);
		try {
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}   
		return true;
	}
	
	public void reset(){
		img = imgcopy;
	}
	
	public static BufferedImage toBufferedImage(PImage pimage) {
		return (BufferedImage) pimage.getNative();
	} 
	
	public static PImage toPImage(BufferedImage bimage) {
		 return new PImage(bimage);
	} 

}
