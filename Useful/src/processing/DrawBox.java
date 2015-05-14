package processing;


import java.io.*; // for the loadPatternFilenames() function
import jp.nyatla.nyar4psg.MultiMarker;
import jp.nyatla.nyar4psg.NyAR4PsgConfig; // the NyARToolkit Processing library
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.opengl.*;

/**
 * @author issa
 * 
 */
public class DrawBox extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2818873783875290971L;
	String camPara = "/home/issa/workspaceSTAGE/Useful/resources/camera_para.dat";
	// the full path to the .patt pattern files
	String patternPath = "/home/issa/workspaceSTAGE/Useful/resources/patterns/";
	// the dimensions at which the AR will take place. 
	int arWidth = 1280;
	int arHeight = 720;
	// the number of pattern markers (from the complete list of .patt files)
	// that will be detected.
	int numMarkers = 10;

	MultiMarker nya;
	float displayScale;

	int [] colors = new int[numMarkers];
	float[] scaler = new float[numMarkers];
	PImage pimage;

	public void setup() {
		size(1280, 720, OPENGL); 
		textFont(createFont("Arial", 80));
		pimage = loadImage("pimage.jpg");
		pimage.resize(arWidth, arHeight);
				
		//to correct for the scale difference between the AR detection coordinates 
		//and the size at which the result is displayed
		displayScale = (float) width / arWidth;
		// create a new MultiMarker at a specific resolution (arWidth x arHeight),
		// with the default camera calibration and coordinate system
		nya = new MultiMarker(this, arWidth, arHeight, camPara, NyAR4PsgConfig.CONFIG_DEFAULT);
		// set the delay after which a lost marker is no longer displayed. 
		// by default set to something higher, but here manually set to immediate.
		nya.setLostDelay(1);
		String[] patterns = loadPatternFilenames(patternPath);
		// for the selected number of markers, add the marker for detection
		// create an individual color and scale for that marker (= box)
		for (int i=0;i<numMarkers;i++){
		 	nya.addARMarker(patternPath + "/" + patterns[i], 80);
			colors[i] = color(random(255), random(255), random(255), 160); // random color, always at a transparency of 160
			scaler[i] = random(0.5f, 1.9f); // scaled at half to double size
		}
	}

	public void draw() {
		background(0); // a background call is needed for correct display of the marker results
		image(pimage, 0, 0, width, height); // display the image at the width and height of the sketch window
		nya.detect(pimage); // detect markers in the pimage image at the correct resolution (incorrect resolution will give assertion error)
		drawMarkers(); // draw the coordinates of the detected markers (2D)
		drawBoxes(); // draw boxes on the detected markers (3D)
	}

	// this function draws the marker coordinates, note that this is completely 2D and based on the AR dimensions (not the final display size)
	public void drawMarkers() {
		// set the text alignment (to the left) and size (small)
		textAlign(LEFT, TOP);
		textSize(10);
		noStroke();
		// scale from AR detection size to sketch display size (changes the display of the coordinates, not the values)
		scale(displayScale);
		// for all the markers...
		for (int i=0; i <numMarkers;i++){// if the marker does NOT exist (the ! exlamation mark negates it) continue to the next marker, aka do nothing
			if ( (!nya.isExistMarker(i)) ) { continue; }
				// the following code is only reached and run if the marker DOES EXIST
				// get the four marker coordinates into an array of 2D PVectors
				PVector[] pos2d = nya.getMarkerVertex2D(i);
				// draw each vector both textually and with a red dot
				for (int j=0; j <pos2d.length;j++){
					String s = "(" + (int)pos2d[j].x + "," + (int)pos2d[j].y + ")";
					fill(255);
					rect(pos2d[j].x, pos2d[j].y, textWidth(s) + 3, textAscent() + textDescent() + 3);
					fill(0);
					text(s, pos2d[j].x + 2, pos2d[j].y + 2);
					fill(255, 0, 0);
					ellipse(pos2d[j].x, pos2d[j].y, 5, 5);
				}
		}
	}

	// this function draws correctly placed 3D boxes on top of detected markers
	public void drawBoxes() {
		// set the AR perspective uniformly, this general point-of-view is the same for all markers
		nya.setARPerspective();
		// set the text alignment (full centered) and size (big)
		textAlign(CENTER, CENTER);
		textSize(20);
		// for all the markers...
		for (int i=0; i<numMarkers;i++){ // if the marker does NOT exist (the ! exlamation mark negates it) continue to the next marker, aka do nothing
			if ((!nya.isExistMarker(i))) { continue; }
			// the following code is only reached and run if the marker DOES EXIST
			// get the Matrix for this marker and use it (through setMatrix)
			setMatrix(nya.getMarkerMatrix(i));
			scale(1, -1); // turn things upside down to work intuitively for Processing users
			scale(scaler[i]); // scale the box by it's individual scaler
			translate(0, 0, 20); // translate the box by half (20) of it's size (40)
			lights(); // turn on some lights
			stroke(0); // give the box a black stroke
			fill(colors[i]); // fill the box by it's individual color
			box(40); // the BOX! ;-)
			noLights(); // turn off the lights
			translate(0, 0, 20.1f); // translate to just slightly above the box (to prevent OPENGL uglyness)
			noStroke();
			fill(255, 50);
			rect(-20, -20, 40, 40); // display a transparent white rectangle right above the box
			translate(0, 0, 0.1f); // translate to just slightly above the rectangle (to prevent OPENGL uglyness)
			fill(0);
			text("" + i, -20, -20, 40, 40); // display the ID of the box in black text centered in the rectangle
		}
		// reset to the default perspective
		perspective();
	}
	
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
