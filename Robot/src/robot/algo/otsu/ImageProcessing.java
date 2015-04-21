package robot.algo.otsu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;
import static robot.algo.otsu.OTSUConstant.*;
public class ImageProcessing extends PApplet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1920274727011984197L;
	PImage img = null;
	PImage imgcopy;
	public void setup(){
		size(MWIDTH,MHEIGHT);
	}
	
	public void draw(){
		
		if(img != null){
			image(img, 0, 0);
		}
		else{
			for(int i =0;i<MCOLS;i++){
				for(int j = 0;j<MROWS;j++){
					int x = i*CELLSIZE;
					int y = j*CELLSIZE;
					g.rect(x, y, CELLSIZE, CELLSIZE);
				}
			}
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
	
	private BufferedImage toBufferedImage(PImage pimage) {
		return (BufferedImage) pimage.getNative();
	} 
	
	private PImage toPImage(BufferedImage bimage) {
		 return new PImage(bimage);
	} 

}
