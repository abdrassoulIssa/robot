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
	private static final int SWIDTH = 640;
	private static final int SHEIGHT = 480;
	
	public void setup(){
		setSize(SWIDTH, SHEIGHT);
	}
	
	public void draw(){
		
		
		if(img != null){
			image(img, 0, 0);
		}
		else{
			for(int i =0;i<21;i++){
				for(int j = 0;j<16;j++){
					g.rect(i*30, j*30, 30, 30);
				}
			}
		}
	}
	
	
	public void addImage(String filename){
		img = loadImage(filename);
		img.resize(SWIDTH, SHEIGHT);
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
