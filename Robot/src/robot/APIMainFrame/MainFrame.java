package robot.APIMainFrame;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7673339013524116854L;

	public MainFrame() {
		super("RCRTL");
		init();
		setSize(670, 640);
		//Dimension dimScreen = getToolkit().getScreenSize();
		//setLocation((dimScreen.width-getWidth())/2,(dimScreen.height-getHeight())/2);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void init(){
		Container container = getContentPane();
	
		ImageIcon icon = null;
		JTabbedPane tabbedPane = new JTabbedPane();
		Component panel = new SimulationFrame();
		
		
		//tabbedPane.setBounds(0, 0, 500, 500);
		tabbedPane.addTab("A Star simulation", icon,panel, "Astar algorithm");
		tabbedPane.setSelectedIndex(0);//DEFINIT L'ONGLET ACTIF AU DEMARRAGE DE L'API
		
		JPanel scenepan = new JPanel();
		scenepan.setPreferredSize(new Dimension(600,600));
		scenepan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panel = new SceneCaptureFrame();
		scenepan.add(panel);
		tabbedPane.addTab("Image processing", icon, new ImageFrame(), "Binarize image");
		tabbedPane.addTab("Scene Capture ", icon, scenepan, "Video");

		container.setLayout(new GridLayout(1, 1));
		container.add(tabbedPane);
	}
}
