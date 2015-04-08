package swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import processing.MyProcessingSketch;

public class LayoutTest extends JFrame {
	MyProcessingSketch sketch = new MyProcessingSketch();
	public LayoutTest() {
		super("RCRTL");
		
		init();
		setSize(640, 480);
		Dimension dimScreen = getToolkit().getScreenSize();
		setLocation((dimScreen.width-getWidth())/2,(dimScreen.height-getHeight())/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	private void init(){
		sketch.init();
		sketch.start();
		ImageIcon icon = null;
		JTabbedPane tabbedPane = new JTabbedPane();
		Component panel = makePanelWithBorderLayout();
		tabbedPane.addTab("<html><i>BorderLayout</i></html>", icon,panel, "info bulle");
		tabbedPane.setSelectedIndex(0);//DEFINIT L'ONGLET ACTIF AU DEMARRAGE DE L'API
		
		panel = makePanelWithGridLayout();
		tabbedPane.addTab("GridLayout", icon, panel, "avec des JButton");
		panel = makePanelWithFlowLayout();
		tabbedPane.addTab("FlowLayout", icon, panel, "une info bulle");

		getContentPane().setLayout(new GridLayout(1, 1));
		getContentPane().add(tabbedPane);
	}
	
	private JPanel makePanelWithBorderLayout() {
		JPanel panelBorderLayout = new JPanel(false);
		panelBorderLayout.setLayout(new BorderLayout());
		panelBorderLayout.add("North",new JButton("North"));
		panelBorderLayout.add("South",new JButton("South"));
		panelBorderLayout.add("East",new JButton("East"));
		panelBorderLayout.add("West",new JButton("West"));
		panelBorderLayout.add("Center",sketch);
		return panelBorderLayout;
	}
	private JPanel makePanelWithFlowLayout() {
		JPanel panelFlowLayout = new JPanel(false);
		panelFlowLayout.setLayout(new FlowLayout());
		for (int i=0;i<10;++i) {
			panelFlowLayout.add(new JButton(String.valueOf(i)));
		}
		return panelFlowLayout;
	}
	private JPanel makePanelWithGridLayout() {
		JPanel panelGridLayout = new JPanel(false);
		panelGridLayout.setLayout(new GridLayout(2,2));
		for (int i=0;i<4;++i) {
			panelGridLayout.add(new JButton(String.valueOf(i)));
		}
		return panelGridLayout;
	}
	
	public static void main(String[] args) {
		new LayoutTest();
	}
		  
}
