package robot.APIMainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import robot.algo.otsu.ImageProcessing;

@SuppressWarnings("serial")
public class ImageFrame extends JPanel implements ActionListener{
	
	private JPanel panCenter, panButton;
	private JButton open,save,binarize;
	private ImageProcessing imageProcessing;
	File file = new File ("filename.jpg");
	FileFilter fileFilter = new FileFilter();


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

	
	
	
	/**
	* Use a JFileChooser in Open mode to select files
	* to open. Use a filter for FileFilter subclass to select
	* for *.JPG and *.GIF
	**/
	private boolean openFile () {

		JFileChooser fc = new JFileChooser ();
		fc.setDialogTitle ("Open File");

		// Choose only files, not directories
		fc.setFileSelectionMode ( JFileChooser.FILES_ONLY);

		// Start in current directory
		fc.setCurrentDirectory (new File ("."));

		// Set filter for Java source files.
		fc.setFileFilter (fileFilter);

		// Now open chooser
		int result = fc.showOpenDialog (this);

		if (result == JFileChooser.CANCEL_OPTION) {
			return true;
		} 
		else if (result == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile ();
			imageProcessing.addImage(file.toString());
		} 
		else {
			return false;
		}
		return true;
	} // openFile

	
	/**
	* Use a JFileChooser in Save mode to select files
	* to open. Use a filter for FileFilter subclass to select
	* for "*.JPG and *.GIF" files. 
	**/
	private boolean saveFile () {
		JFileChooser fc = new JFileChooser ();

		// Start in current directory
		fc.setCurrentDirectory (new File ("."));

		// Set filter for Java source files.
		fc.setFileFilter (fileFilter);

		// Set to a default name for save.
		fc.setSelectedFile (file);

		// Open chooser dialog
		int result = fc.showSaveDialog (this);

		if (result == JFileChooser.CANCEL_OPTION) {
			return true;
		} 
		else if (result == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile ();
			if (file.exists ()) {
				int response = JOptionPane.showConfirmDialog (null,
				"Overwrite existing file?","Confirm Overwrite",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.CANCEL_OPTION) return false;
			}
			return imageProcessing.saveImage(file);
		} 
		else {
			return false;
		}
	} // saveFile
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		boolean status = false;
		if (cmd.equalsIgnoreCase(open.getText()) ) {
			// Open a file
			status = openFile ();
			if (!status)
			JOptionPane.showMessageDialog (
				null,
				"Error opening file!", "File Open Error",
				JOptionPane.ERROR_MESSAGE
			);
		} 
		else if(cmd.equalsIgnoreCase(save.getText())) {
			// Save a file
			status = saveFile ();
			if (!status){
				JOptionPane.showMessageDialog (
					null,
					"IO error in saving file!!", "File Save Error",
					JOptionPane.ERROR_MESSAGE
				);
			}
		}
		else{
			imageProcessing.binairiseImage();
		}
	}

}
