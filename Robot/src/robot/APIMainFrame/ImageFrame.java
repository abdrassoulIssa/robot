package robot.APIMainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import robot.algo.otsu.ImageProcessing;

@SuppressWarnings("serial")
public class ImageFrame extends JPanel implements ActionListener{
	
	private JPanel panCenter, panButton;
	private JButton open,save,binarize;
	private ImageProcessing imageProcessing;

	public ImageFrame(){
		build();
	}
	
	private void build(){
		setLayout(new BorderLayout());
		panCenter = new JPanel(new BorderLayout());
		panCenter.setBackground(new Color(255, 255, 255));
		panCenter.setLayout(new BoxLayout(panCenter, BoxLayout.Y_AXIS));
		panCenter.setBorder(BorderFactory.createCompoundBorder(
              null,
              BorderFactory.createEmptyBorder(10,10,10,10)));
		//panCenter.setSize(640, 600);
		imageProcessing = new ImageProcessing();
		imageProcessing.init();
		imageProcessing.start();
		panCenter.add(imageProcessing);
	  
		Dimension duttonDim = new Dimension(100,40);
		panButton = new JPanel(new FlowLayout());
		
		open = new JButton ("Open");
		open.addActionListener(this);
		open.setPreferredSize(duttonDim);
		panButton.add(open);
		
		binarize = new JButton ("Binarize");
		binarize.addActionListener(this);
		binarize.setPreferredSize(duttonDim);
		panButton.add(binarize);
		
		save = new JButton ("save");
		save.addActionListener(this);
		save.setPreferredSize(duttonDim);
		panButton.add(save);
				  
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panCenter,panButton);
		splitPane.setDividerLocation(600);
		splitPane.setAutoscrolls(true);
		splitPane.setOneTouchExpandable(true);
		add(splitPane);
		setBounds(150, 150, 685, 550);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 String cmd = e.getActionCommand();
		    
			if (cmd.equalsIgnoreCase(open.getText())){
				
			    JFileChooser chooser;
		        File currentFolder = null;             
				try {
					currentFolder = new File(".").getCanonicalFile();
					chooser = new JFileChooser(currentFolder) ;
			        FileNameExtensionFilter filter = new FileNameExtensionFilter(
			                "JPG & GIF Images", "jpg", "gif");
			        chooser.setFileFilter(filter);
					int returnVal=chooser.showOpenDialog(ImageFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File choosedfile = chooser.getSelectedFile();
						imageProcessing.addImage(choosedfile.toString());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (cmd.equalsIgnoreCase(save.getText())) {
				@SuppressWarnings("serial")
				JFileChooser chooser = new JFileChooser();
				
				try {
					File currentFolder = new File(".").getCanonicalFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG & GIF Images", "jpg", "gif");
				chooser.addChoosableFileFilter(filter);
				chooser.setAccessory(chooser);
				int returnVal=chooser.showOpenDialog(ImageFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File choosedfile = chooser.getSelectedFile();
					imageProcessing.addImage(choosedfile.toString());
				}
			}

			if(cmd.equalsIgnoreCase(binarize.getText())){
				imageProcessing.binairiseImage();
			}
	}

}
