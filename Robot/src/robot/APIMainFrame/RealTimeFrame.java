package robot.APIMainFrame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import robot.ProcessingGSvideo.ProcessingVideo;

public class RealTimeFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4089537970246333497L;
	ProcessingVideo pvideo;
	public RealTimeFrame() {
		pvideo = new ProcessingVideo();
		pvideo.init();
		pvideo.start();
		build();
	}
	
	private void build(){
		setLayout(new BorderLayout());

		JPanel panelButton = new JPanel();
		panelButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, 
													Color.GRAY, Color.GRAY));

		add(BorderLayout.CENTER,pvideo);
		add(BorderLayout.SOUTH, panelButton);
	}

}
