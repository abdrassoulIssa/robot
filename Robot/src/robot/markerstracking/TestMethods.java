package robot.markerstracking;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class TestMethods {
	
	public static String[] loadPatternFilenames(String path) {
	    File folder = new File(path);
	    FilenameFilter pattFilter = new FilenameFilter() {
	      public boolean accept(File dir, String name) {
	        return name.toLowerCase().endsWith(".patt");
	      }
	    }; 
	     return folder.list(pattFilter); 
	} 
	
	public static void main(String[]args){
		
		String patternPath = "resources/patterns/";
		System.out.println(patternPath);
		String [] patterns = loadPatternFilenames(patternPath);
		System.out.println(Arrays.toString(patterns));
		
	}

}
