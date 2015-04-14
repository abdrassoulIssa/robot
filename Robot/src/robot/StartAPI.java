package robot;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import robot.APIMainFrame.MainFrame;

public class StartAPI {

	public static void main(String[] args) {
	       try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	new MainFrame();
	            }
	        });
		
	}

}
