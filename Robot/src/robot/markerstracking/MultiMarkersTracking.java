package robot.markerstracking;
import java.io.*; // for the loadPatternFilenames() function

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import jp.nyatla.nyar4psg.*; // The NyARToolkit Processing library
import codeanticode.gsvideo.*; // The GSVideo library

public class MultiMarkersTracking extends PApplet{
	private static final long serialVersionUID = 1L;
	private String camPara     = "/home/issa/workspaceSTAGE/Robot/resources/camera_para.dat";
	private String patternPath = "/home/issa/workspaceSTAGE/Robot/resources/patterns/";
	private static final int SCREEN_WIDTH  = 640;
	private static final int SCREEN_HEIGHT = 360;
	private static  float displayScale;
	private PImage pimage;
	private int numMarkers = 0;
	
	GSCapture cam;
	MultiMarker nya;

	public void setup() {
		size(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		cam = new GSCapture(this, SCREEN_WIDTH, SCREEN_HEIGHT,"/dev/video1"); // initializing the webcam capture at a specific resolution
		cam.start(); // start capturing
		noStroke(); 
		displayScale = (float) width / SCREEN_WIDTH;
		// create a new MultiMarker at a specific resolution (SCREEN_WIDTH x SCREEN_HEIGHT), with the default camera calibration and coordinate system
		nya = new MultiMarker(this, SCREEN_WIDTH, SCREEN_HEIGHT, camPara, NyAR4PsgConfig.CONFIG_DEFAULT);
		// set the delay after which a lost marker is no longer displayed. by default set to something higher, but here manually set to immediate.
		nya.setLostDelay(1);
		String[] patterns = loadPatternFilenames(patternPath);
		numMarkers = patterns.length;
		// for the selected number of markers, add the marker for detection
		// create an individual scale, noiseScale and maximum mountainHeight for that marker (= mountain)
		for(int i=0;i<numMarkers;i++){
			nya.addARMarker(patternPath + "/" + patterns[i], 80);
		}
	}
	
	public void draw() {
		//IF THERE IS A CAM IMAGE COMING IN 
		if (cam.available()) {
			cam.read(); // READ THE CAM IMAGE
			image(cam, 0, 0, width, height); // DISPLAY THE IMAGE AT THE WIDTH AN HEIGHT OF THE SKETCH WINDOW
			//CREATE A COPY OF THE CAM IMAGE AT THE RESOLUTION OF THE AR DETECTION
			pimage = cam.get();
			pimage.resize(SCREEN_WIDTH, SCREEN_HEIGHT);
			nya.detect(pimage); // detect markers in the image
			drawMarkers(); //DRAW DINAMICALLY FOLLOWING MARKERS ON THE DETECTED MARKERS(3D)
		}
	}

	//THIS FUNCTION DRAWS THE MARKER COORDINATES
	//NOT THAT THIS IS COMPLETELY 2D AND BASED ON AR DIMENSIONS
	public void drawMarkers() {
		//SET THE TEXT ALIGNMENT TO THE LEFT AND SIZE SMALL
		textAlign(LEFT, TOP);
		textSize(10);
		noStroke();
		//SCALE FROM AR DETECTION SIZE TO SKETCH DISPLAY SIZE
		scale(displayScale);
		//FOR ALL THE MARKERS
		for (int i=0;i<numMarkers;i++){
			if((!nya.isExistMarker(i))){ 
				continue; 
			}
			//THE FOLLOWING CODE IS ONLY REACHED AND RUN IF THE MARKER DOES EXIST
			//GET THE FOUR MARKER COORDINATES INTO AN ARRY OF 2D PVectors
			PVector[] pos2d = nya.getMarkerVertex2D(i);
			//DRAW EACH VECTOR BOTH TEXTUALLY AND WIDTH A RED DOT
			for (int j=0; j<pos2d.length;j++ ){
				String s = "(" + (int)(pos2d[j].x) + "," + (int)(pos2d[j].y) + ")";
				fill(255);
				rect(pos2d[j].x, pos2d[j].y, textWidth(s) + 3, textAscent() + textDescent() + 3);
				fill(0);
				text(s, pos2d[j].x + 2, pos2d[j].y + 2);
				fill(255, 0, 0);
				ellipse(pos2d[j].x, pos2d[j].y, 5, 5);
			}
		}
	}

	//THIS FUNCTIONS LOADS .patt FILENAMES INTO A LIST OF STRINGS BASED ON A FULL PATH TO A DIRECTORY
	public String[] loadPatternFilenames(String path) {
		File folder = new File(path);
		FilenameFilter pattFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".patt");
			}
		};
		return folder.list(pattFilter);
	}

}
