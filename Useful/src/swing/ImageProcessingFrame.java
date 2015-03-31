package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
public class ImageProcessingFrame extends JFrame {
  
	private JMenuBar menuBar;
	private JMenu fileMenu, filterMenu, resizeMenu;
	private ImageProcessing imageProcessing;
	private final String[] EXTENSION=new String[]{"jpg","idx","hash","Hash"};

	public ImageProcessingFrame () 
    {
		super("ImageProcessing");
		imageProcessing = new ImageProcessing();
		setResizable (true);
		setSize (640, 480);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		build();
		setVisible (true);
    }
	

	public void build()
	{
		fileMenu   = new JMenu("File");
		filterMenu = new JMenu("Filter");
		resizeMenu = new JMenu("Resize");
		menuBar	   = new JMenuBar();
		setJMenuBar(menuBar);	
		
		
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new MenuActionListener());
		fileMenu.setMnemonic(KeyEvent.VK_O);
		fileMenu.add(open);
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new MenuActionListener());
		fileMenu.add(save);
		menuBar.add(fileMenu);
		

		JMenuItem binarisationMenu = new JMenuItem("Binarisation");
		binarisationMenu.addActionListener(new MenuActionListener());
		filterMenu.add(binarisationMenu);
		menuBar.add(filterMenu);
		
		
		JMenuItem extend = new JMenuItem("zoom++");			
		extend.addActionListener(new MenuActionListener());
		resizeMenu.add(extend);
		JMenuItem reduce = new JMenuItem("zoom--");
		reduce.addActionListener(new MenuActionListener());
		resizeMenu.add(reduce);
		menuBar.add(resizeMenu);
		
		add(imageProcessing);
	}
	
	public static void main(String... args){
		new ImageProcessingFrame();
	}



	class MenuActionListener implements ActionListener {
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			    String cmd = e.getActionCommand();
			    
				if (cmd.equalsIgnoreCase("open")){
					
				    JFileChooser chooser;
			        File currentFolder = null;             
					try {
						currentFolder = new File(".").getCanonicalFile();
						chooser = new JFileChooser(currentFolder) ;
				        FileNameExtensionFilter filter = new FileNameExtensionFilter(
				                "JPG & GIF Images", "jpg", "gif");
				        chooser.setFileFilter(filter);
						int returnVal=chooser.showOpenDialog(ImageProcessingFrame.this);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							imageProcessing.addImage(chooser.getSelectedFile());
							System.out.println(chooser.getSelectedFile());
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if (cmd.equalsIgnoreCase("save")) {
					String filename = null;
					try {
					    //filename = JOptionPane.showInputDialog("Filename :");
						imageProcessing.saveImage(new File("binarisedimg/"+filename));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						/*JOptionPane.showMessageDialog(ImageProcessingFrame.this, 
						filename+" does not exists", "warning", 
						JOptionPane.ERROR_MESSAGE);
						*/
					}
				}

				else if(cmd.equalsIgnoreCase("binarisation")){
					imageProcessing.binairiseImage();
				}
				else if(cmd.equalsIgnoreCase("zoom++")){
					imageProcessing.extendImage();
				}
				else if(cmd.equalsIgnoreCase("zoom--")){
					imageProcessing.reduceImage();
				}

		  }
	}	
}