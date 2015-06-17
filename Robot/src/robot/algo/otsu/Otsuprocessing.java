package robot.algo.otsu;
import static robot.algo.otsu.OTSUConstant.*;

import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Otsuprocessing extends PApplet{
 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PImage binarized_image;
	PImage pp;
	public Otsuprocessing(){
		binarized_image = 
				new PImage(MWIDTH,MHEIGHT,PConstants.ARGB);
    }
	
	
	public void setup(){
		size(MWIDTH,MHEIGHT);
		pp = loadImage("../resources/img/navmap.JPG");
		pp.resize(MWIDTH, MHEIGHT);
		println(otsuTreshold(pp));
		pp = redBinarize(pp);
		int [][] map = new int[MROWS][MCOLS];
		imageToMatrix(map);
		
		for (int i = 0; i < map.length; i++) {
			println(" "+Arrays.toString(map[i]));
		}
		image(pp, 0, 0);
	}

    
    //Detect if corresponding tile may be an obstacle
    private boolean findObstacle(int xStart, int yStart, int [][]map){
		for(int  x = yStart; x < yStart+CELLSIZE; x++){
			for(int y = xStart; y < xStart+CELLSIZE; y++){				
                int cellX = x/CELLSIZE;
				int cellY = y/CELLSIZE;
                int pixels = (int) red(binarized_image.pixels[y + x*binarized_image.width]);
				if(pixels == 255){
					map[cellX][cellY] = 1;
					break;
				}
			}
		}
    	return true;
    }
    
    //Convert the binary image to the navigation map
    public void imageToMatrix(int [][]map){
		for (int i = 0; i <= (MWIDTH - CELLSIZE); i = i + CELLSIZE) {
			for (int j = 0; j <=(MHEIGHT -CELLSIZE); j = j + CELLSIZE) {
				findObstacle(i, j,map);
			}
		}
    }
    
 
    // Return histogram of grayscale image
    public  int[] imageHistogram(PImage pimage) {
        int indexofpixel  = 0;
        int current_pixel = 0;
        int[] histogram = new int[256];
        pimage.loadPixels();
        for(int x = 0; x < pimage.width;x++) {
            for(int y = 0; y < pimage.height; y++) {       
                indexofpixel = x + y*pimage.width;
                current_pixel = pimage.pixels[indexofpixel];
                int red_value = (int) red(current_pixel);
                histogram[red_value]++;
            }
        }
        return histogram;
    }
 
    // The luminance method
	public PImage toGray(PImage pimage) {
 
        double red_value, green_value, blue_value;
        int current_pixel, grayscale, indexofpixel;
 
        PImage lum = new PImage(MWIDTH,MHEIGHT,PConstants.ARGB);
 
        for(int x = 0; x< pimage.width; x++) {
            for(int y = 0; y < pimage.height; y++) {
                // Get pixels by R, G, B
                indexofpixel  = x + y*pimage.width; 
                current_pixel = pimage.pixels[indexofpixel];
                
                red_value   = red(current_pixel);
                green_value = green(current_pixel);
                blue_value  = blue(current_pixel);
 
                grayscale = (int) (0.2126 * red_value + 0.7152 * green_value + 0.0722 * blue_value); 
                // Write pixels into image
                lum.pixels[indexofpixel] = grayscale;
            }
        }
        lum.updatePixels();
        return lum;
    }
 
    // Get binary treshold using Otsu's method
    private int otsuTreshold(PImage pimage) {
 
        int[] histogram = imageHistogram(pimage);
        int total = pimage.width * pimage.height;
 
        float sum = 0;
        for(int i = 0; i < 256; i++) {
            sum += i * histogram[i];
        }
 
        float sumB = 0;
        int wB = 0;
        int wF = 0;
 
        float varMax = 0;
        int threshold = 0;
 
        for(int i = 0 ; i < 256 ; i++) {
            wB += histogram[i];
            
            if(wB == 0) continue;
            wF = total - wB;
            if(wF == 0) break;
 
            sumB += i * histogram[i];
            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;
 
            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);
 
            if(varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }
 
    public  PImage redBinarize(PImage pimage) {
 
        double red_value;
        int current_pixel;
        int indexofpixel;
 
        int threshold = otsuTreshold(pimage);
 
        PImage binarized = new PImage(MWIDTH,MHEIGHT,PConstants.ARGB);

        for(int x = 0; x < pimage.width; x++) {
            for(int y = 0; y < pimage.height; y++) {
                
                indexofpixel  = x + y*pimage.width; 
                current_pixel = pimage.pixels[indexofpixel];
                red_value = red(current_pixel);

                if(red_value > threshold) {
                    binarized.pixels[indexofpixel] = color(255);
                }
                else {
                    binarized.pixels[indexofpixel] = color(0);
                }
            }
        }
        binarized.updatePixels(); 
        binarized_image = binarized;
        return binarized;
    }
    
    public PImage addImage(String filename){
    	PImage  pimage = loadImage(filename);
		return pimage;
    }
 
}