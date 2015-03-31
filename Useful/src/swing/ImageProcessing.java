package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageProcessing extends JPanel {
	
	BufferedImage img = null;
	public ImageProcessing(){
	    setBackground (Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(img != null){
			g.drawImage(img, 0, 0,this.getWidth()-50,this.getHeight()-50, null); 
		}
		else{
			for(int i =0;i<20;i++){
				for(int j = 0;j<13;j++){
					g.drawRect(i*30, j*30, 30, 30);
				}
			}
		}
	}
	
	protected void addImage(File file) throws IOException{
		img = ImageIO.read(file);
		repaint();
	}
	
	protected void binairiseImage(){
		BufferedImage imgBinaire = new BufferedImage(img.getWidth(), img.getHeight(), 
													BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D surfaceImg = imgBinaire.createGraphics();
		surfaceImg.drawImage(img, null, null);      
		img = imgBinaire;
		repaint();
	}
	
	protected BufferedImage getImage()
	{      
		// récupérer une image du panneau
		int width  = this.getWidth();
		int height = this.getHeight();
		BufferedImage image = new BufferedImage(width, height,  
									BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		paintAll(g);
		g.dispose();
		return image;
	}
	
	protected String getImageName(){
		return null;
		
	}
	protected void saveImage(File file) throws IOException
	{
		String format ="JPG";
		BufferedImage image = getImage();
		ImageIO.write(image, format, file);   
	}
	
	protected void reduceImage()
	{
		BufferedImage reducedImage = new BufferedImage((int)(img.getWidth()*0.5),
									(int)( img.getHeight()*0.5), img.getType());
		AffineTransform reduce = AffineTransform.getScaleInstance(0.5, 0.5);	
		int interpolation = AffineTransformOp.TYPE_BICUBIC;
		AffineTransformOp resizeImage = new AffineTransformOp(reduce, interpolation);
		resizeImage.filter(img, reducedImage );
		img = reducedImage ;
		repaint();
	}
	protected void extendImage(){
		
	}
	
	public Color getColor(int x, int y){
	    int rgb = img.getRGB(x,y);
	    Color c = new Color(rgb);
	    return c;
	}
	
    public int getRGB(int x, int y) {
        return img.getRGB(x,y);
    }


}
