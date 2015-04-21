package robot.algo.otsu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageProcessing extends PApplet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3565779079810954270L;
	PImage img = null;
	private static final int cellsize = 30;
	private int rows, cols;
	
	public void setup(){
		size(640,480);
		cols  = width/cellsize;
		rows  = height/cellsize;
	}
	
	public void draw(){
		
		if(img != null){
			image(img, 0, 0);
		}
		else{
			for(int i =0;i<cols;i++){
				for(int j = 0;j<rows;j++){
					int x = i*cellsize;
					int y = j*cellsize;
					g.rect(x, y, cellsize, cellsize);
				}
			}
		}
	}
	
	
	public void addImage(String filename){
		img = loadImage(filename);
		img.resize(width, height);
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
	
	private BufferedImage toBufferedImage(PImage pimage) {
		return (BufferedImage) pimage.getNative();
	} 
	
	private PImage toPImage(BufferedImage bimage) {
		 return new PImage(bimage);
	} 

}
