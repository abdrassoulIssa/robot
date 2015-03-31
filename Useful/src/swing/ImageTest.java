package swing;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;

class ImageTest {

    public static int getRGB(int x, int y, BufferedImage image) {
        return image.getRGB(x,y);
    }

    public static Color getColor(int x, int y, BufferedImage image) {
        int rgb = image.getRGB(x,y);
        Color c = new Color(rgb);
        return c;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage image = ImageIO.read(new File("img/simpsons.jpg"));
        int w = image.getWidth();
        int h = image.getHeight();
        
        for(int i = 0;i < w;i++){
        	for(int j = 0;j<h;j++){
        		System.out.print(ImageTest.getColor(i, j, image));
        	}
        	System.out.println();
        }
    }
}