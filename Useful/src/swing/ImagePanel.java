package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 7952119619331504986L;
    private BufferedImage image;
    

    public ImagePanel() { 
    	setBackground(Color.white);
    }

    public ImagePanel(String resName) throws IOException {
        loadFromResource(resName);
    }

    public ImagePanel(BufferedImage image) {
        this.image = image;
     }

    @Override
    public void paintComponent(Graphics g) {
    	if(image != null)
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters

    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Load the Image from a File
     * @param path image name and path
     * @throws IOException
     */
    public void loadFromFile(String path) throws IOException {
        image = ImageIO.read(new java.io.File(path));
    }

    /**
     * Load Image from resource item
     * @param resName name of the resource (e.g. : image.png)
     * @throws IOException
     */
    public void loadFromResource(String resName) throws IOException { 
        URL url = this.getClass().getResource(resName);
        BufferedImage img = ImageIO.read(url);
        image = img;
    }
    
	public Color getColor(int x, int y){
	    int rgb = image.getRGB(x,y);
	    Color c = new Color(rgb);
	    return c;
	}
	
    public int getRGB(int x, int y) {
        return image.getRGB(x,y);
    }
}