package robot.APIMainFrame;
import java.io.*;

/** Filter to work with JFileChooser to select java file types. **/
public class FileFilter extends javax.swing.filechooser.FileFilter{
	@Override
	public boolean accept (File f) {
		return 
				f.getName ().toLowerCase ().endsWith (".jpg") || 
				f.getName ().toLowerCase ().endsWith (".gif") || 
				f.isDirectory ();
	}
	@Override
	public String getDescription () {
		return "JPG & GIF Images";
	}
}