package robot.markerstracking;

import java.io.File;
import java.io.FilenameFilter;

import processing.core.PApplet;

public class TestMethods extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setup(){
		
	}
	
	public void draw(){
		
	}
	
	public static String[] loadPatternFilenames(String path) {
	    File folder = new File(path);
	    FilenameFilter pattFilter = new FilenameFilter() {
	      public boolean accept(File dir, String name) {
	        return name.toLowerCase().endsWith(".patt");
	      }
	    }; // fin filenameFilter
	     return folder.list(pattFilter); // renvoi le tableau de String
	} // fin loadPatternFilenames
	
	public static void main(String[]args){
		String patternPath = "/resources/data/1.patt";
		String [] patterns = TestMethods.loadPatternFilenames(patternPath);
		System.out.println("Bonjour");
		System.out.println(patterns.toString());
		
	}

}
