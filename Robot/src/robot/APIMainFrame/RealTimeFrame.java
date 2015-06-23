package robot.APIMainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import robot.ProcessingGSvideo.ProcessingVideo;

public class RealTimeFrame extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4089537970246333497L;
	private ProcessingVideo pvideo;
	private JButton start, stop;
	public RealTimeFrame() {
		pvideo = new ProcessingVideo();
		build();
	}
	
	
	private void build(){
		setLayout(new BorderLayout());
		Dimension duttonDim = new Dimension(100,40);
		start = new JButton("Start");
		stop  = new JButton("Stop");
		start.addActionListener(new ActionStart());
		stop.addActionListener(new ActionStop());
		start.setPreferredSize(duttonDim);
		stop.setPreferredSize(duttonDim);
		JPanel panelButton = new JPanel();
		panelButton.add(start);
		panelButton.add(stop);
		panelButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, 
													Color.GRAY, Color.GRAY));

		add(BorderLayout.CENTER,pvideo);
		add(BorderLayout.SOUTH, panelButton);
	}
	
	class ActionStart implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pvideo.startVideo();
		}
	}
	
	class ActionStop implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pvideo.stopVideo();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		pvideo.init();
	    pvideo.start();
	}

}
