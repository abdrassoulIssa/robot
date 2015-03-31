package swing;

import java.io.IOException;

import javax.swing.JFrame;

public class ImagePanelFrame extends JFrame{

	private ImagePanel imagePanel;
	public ImagePanelFrame() throws IOException {
		// TODO Auto-generated constructor stub
		super("ImagePanelFrame");
		imagePanel = new ImagePanel();
		setResizable (true);
		setSize (640, 480);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    imagePanel.loadFromFile("img/hollande.jpg");
		add(imagePanel);
		setVisible (true);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new ImagePanelFrame();
	}

}
