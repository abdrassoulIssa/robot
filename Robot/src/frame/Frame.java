package frame;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7673339013524116854L;

	public Frame() {
		super("RCRTL");
		init();
		setSize(640, 800);
		Dimension dimScreen = getToolkit().getScreenSize();
		setLocation((dimScreen.width-getWidth())/2,(dimScreen.height-getHeight())/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void init(){
		Container container = getContentPane();
	
		ImageIcon icon = null;
		JTabbedPane tabbedPane = new JTabbedPane();
		Component panel = new Simulation();
		
		tabbedPane.addTab("Simulation", icon,panel, "Alstar algorithm");
		tabbedPane.setSelectedIndex(0);//DEFINIT L'ONGLET ACTIF AU DEMARRAGE DE L'API
		
		panel = new Capture();
		tabbedPane.addTab("Video", icon, panel, "Capture vidéo");

		container.setLayout(new GridLayout(1, 1));
		container.add(tabbedPane);
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		new Frame();
	}

}
