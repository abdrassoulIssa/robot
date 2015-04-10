package robot.APIMainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import robot.algo.astar.robot.DrawPath;

public class Simulation extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1001951408054203780L;
	private JButton restart;
	private JButton choosemap;
	private JButton start;
	private JLabel info;
	private DrawPath dpath;
	public Simulation() {
		build();
	}
	
	private void build(){
		setLayout(new BorderLayout());
		dpath = new DrawPath();
		dpath.init();
		dpath.start();
		Dimension buttonDim = new Dimension(100, 40);
		JPanel panel = new JPanel();
		restart   = new JButton("restart");
		choosemap = new JButton("Choose map");
		start     = new JButton("Start");
		info      = new JLabel("No map choosed yet.");

		restart.addActionListener(this);
		choosemap.addActionListener(this);
		restart.setPreferredSize(buttonDim);
		choosemap.setPreferredSize(buttonDim);
		start.setPreferredSize(buttonDim);
		
		panel.add(info);
		panel.add(choosemap);
		panel.add(start);
		panel.add(restart);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY));
		
		add(BorderLayout.CENTER,dpath);
		add(BorderLayout.SOUTH, panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equalsIgnoreCase(restart.getText())){
			dpath.restart();
		}
		if(cmd.equalsIgnoreCase(choosemap.getText())){
			System.out.println("OK");
		}
	}
	

}
