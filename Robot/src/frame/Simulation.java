package frame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import algo.astar.robot.DrawPath;

public class Simulation extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1001951408054203780L;
	private JButton restart;
	private DrawPath dpath;
	public Simulation() {
		build();
	}
	
	private void build(){
		setLayout(new BorderLayout());
		dpath = new DrawPath();
		dpath.init();
		dpath.start();
		JPanel buttonpan = new JPanel();
		restart = new JButton("restart");
		buttonpan.add(restart);
		add("Center",dpath);
		add("South", buttonpan);
	}

}
