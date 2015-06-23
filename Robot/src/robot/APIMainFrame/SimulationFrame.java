package robot.APIMainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import robot.algo.astar.robot.AstarSimulation;

public class SimulationFrame extends JPanel implements ActionListener, Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1001951408054203780L;
	private JButton restart;
	private JButton choosemap;
	private JButton start;
	private JLabel info;
	private AstarSimulation dpath;
	public SimulationFrame() {
		dpath = new AstarSimulation();
		build();
	}
	
	private void build(){
		setLayout(new BorderLayout());

		Dimension buttonDim = new Dimension(100, 40);
		JPanel panel = new JPanel();
		restart   = new JButton("restart");
		choosemap = new JButton("Choose map");
		start     = new JButton("Start");
		info      = new JLabel("No map choosed yet.");
		info.setToolTipText("Choose the nav map");
		start.addActionListener(this);
		restart.addActionListener(this);
		choosemap.addActionListener(this);
		restart.setPreferredSize(buttonDim);
		choosemap.setPreferredSize(buttonDim);
		start.setPreferredSize(buttonDim);
		
		panel.add(info);
		panel.add(choosemap);
		panel.add(start);
		panel.add(restart);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, 
													Color.GRAY, Color.GRAY));

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
		
		if(cmd.equalsIgnoreCase(start.getText())){
			dpath.start();
		}
		
		if(cmd.equalsIgnoreCase(choosemap.getText()) || cmd.equalsIgnoreCase(info.getText()) ){
		    JFileChooser chooser;
	        File currentFolder = null;             
			try {
				currentFolder = new File(".").getCanonicalFile();
				chooser = new JFileChooser(currentFolder) ;
				
		        FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                "JPG & GIF Images", "jpg", "gif");
		        chooser.setFileFilter(filter);
				int returnVal=chooser.showOpenDialog(SimulationFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					dpath.addImage(chooser.getSelectedFile().toString());
					info.setText(chooser.getSelectedFile().getName());
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		dpath.init();
		dpath.start();
	}
	
}
