package robot.algo.otsu;
 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static robot.algo.otsu.OTSUConstant.*;

import javax.imageio.ImageIO;
 
public class OtsuBinarize {
 
	@SuppressWarnings("unused")
	private static BufferedImage original, grayscale, binarized;
	private static int [][] map = new int[ROWS][COLS];

    public static void main(String[] args) throws IOException {
    	
    	

    	
    	String imgPath = "resources/img/navmap.JPG";
        File original_f = new File(imgPath);
        String output_f = imgPath+"_bin";
        original = ImageIO.read(original_f);
        original = scale(original, WIDTH, HEIGHT);
        binarized = binarize(original);
        /*
        grayscale = toGray(original);
        binarized = binarize(grayscale);

        int [][] map = new int [16][21];
        System.out.println(map.length);
        imageToMatrix(original,map);
       
        for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		*/
        imageToMatrix();
        saveMap("resources/map/map4.dat", map);
        
        saveImage(output_f);         
    }
    
    private static  boolean findObstacle(int xStart, int yStart){
		for(int  x = yStart; x < yStart+CELLSIZE; x++){
			for(int y = xStart; y < xStart+CELLSIZE; y++){				
                int cellX = x/CELLSIZE;
				int cellY = y/CELLSIZE;
                int pixels = new Color(binarized.getRGB(y, x)).getRed();

				if(pixels == 255){
					map[cellX][cellY] = 1;
					System.out.println(x+"-"+y);
					break;
				}
			}
		}
    	return true;
    }
    
    public static void imageToMatrix(){
		for (int i = 0; i <= (WIDTH - CELLSIZE); i = i + CELLSIZE) {
			for (int j = 0; j <=(HEIGHT -CELLSIZE); j = j + CELLSIZE) {
				findObstacle(i, j);
			}
		}
    }
    
    public static void imageToMatrix(BufferedImage image, int [][] map){
    	//BufferedImage binarized = binarize(toGray(image));
    	binarized = binarize(image);
    	for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                // Get pixels
            	int x = Math.abs(i*30 - 16);
            	int y = Math.abs(j*30 - 21);
            	//System.out.println("x = "+x+" y = "+y);
                int pixels = new Color(binarized.getRGB(y, x)).getRed();
                        	
                if(pixels == 255) {
                	map[i][j] = 1;
                }
                else {
                	map[i][j] = 0;
                }
            }
        }
    	
    }
    
    public static void saveMap(String filename,int [][]map) throws IOException{
    	File fp = new File(filename);
		if(!fp.exists()){
			fp.createNewFile();
		}
		
		try(BufferedWriter buffer = new BufferedWriter(new FileWriter(fp))) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					buffer.write(map[i][j]+" ");
				}
				buffer.newLine();
			}
			buffer.flush();
		}
    }
 
    private static void saveImage(String output) throws IOException {
        File file = new File(output+".jpg");
        ImageIO.write(binarized, "jpg", file);
    }
    public static BufferedImage scale(BufferedImage source, int width, int height) {
        BufferedImage buf = new BufferedImage(width, height, BufferedImage.SCALE_DEFAULT);
        Graphics2D g = buf.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return buf;
    }
 
    // Return histogram of grayscale image
    public static int[] imageHistogram(BufferedImage input) {
 
        int[] histogram = new int[256];
 
        for(int i=0; i<histogram.length; i++) histogram[i] = 0;
 
        for(int i=0; i<input.getWidth(); i++) {
            for(int j=0; j<input.getHeight(); j++) {
                int red = new Color(input.getRGB (i, j)).getRed();
                histogram[red]++;
            }
        }
        return histogram;
 
    }
 
    // The luminance method
	@SuppressWarnings("unused")
	private static BufferedImage toGray(BufferedImage original) {
 
        int alpha, red, green, blue;
        int newPixel;
 
        BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
 
        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {
 
                // Get pixels by R, G, B
                alpha = new Color(original.getRGB(i, j)).getAlpha();
                red   = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue  = new Color(original.getRGB(i, j)).getBlue();
 
                red = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
                // Return back to original format
                newPixel = colorToRGB(alpha, red, red, red);
 
                // Write pixels into image
                lum.setRGB(i, j, newPixel);
 
            }
        }
 
        return lum;
 
    }
 
    // Get binary treshold using Otsu's method
    private static int otsuTreshold(BufferedImage original) {
 
        int[] histogram = imageHistogram(original);
        int total = original.getHeight() * original.getWidth();
 
        float sum = 0;
        for(int i=0; i<256; i++) sum += i * histogram[i];
 
        float sumB = 0;
        int wB = 0;
        int wF = 0;
 
        float varMax = 0;
        int threshold = 0;
 
        for(int i=0 ; i<256 ; i++) {
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
 
    public static BufferedImage binarize(BufferedImage original) {
 
        int red;
        int newPixel;
 
        int threshold = otsuTreshold(original);
 
        BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
 
        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {
 
                // Get pixels
                red = new Color(original.getRGB(i, j)).getRed();
                int alpha = new Color(original.getRGB(i, j)).getAlpha();
                if(red > threshold) {
                    newPixel = 255;
                }
                else {
                    newPixel = 0;
                }
                newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                binarized.setRGB(i, j, newPixel); 
 
            }
        }
        return binarized;
    }
 
    // Convert R, G, B, Alpha to standard 8 bit
    private static int colorToRGB(int alpha, int red, int green, int blue) {
 
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
 
        return newPixel;
 
    }
 
}