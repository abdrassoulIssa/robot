package swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import processing.StripeMain;

public class LayoutTest extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StripeMain sketch = new StripeMain();
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
		Container container = getContentPane();
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

		container.setLayout(new GridLayout(1, 1));
		container.add(tabbedPane);
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
	
	/**
	 *  Dans cette méthode, il faut mettre System.out dans une variable locale elle-même nomée out. 
	 *  Ceci est pour éviter de recalculer System.out à chaque passage dans la boucle.
	 *  Lorsque l'on programme une méthode, il est important d'éviter de recalculer les mêmes valeurs. 
	 */
	public void printToConsole(){
		PrintStream out = System.out;
		out.print("Bonjour");
	}
		  
}
