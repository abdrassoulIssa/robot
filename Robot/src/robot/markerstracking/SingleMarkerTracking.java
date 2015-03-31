package robot.markerstracking;
import java.io.File;
import java.io.FilenameFilter;

import processing.core.*;
import jp.nyatla.nyar4psg.*;
import codeanticode.gsvideo.GSCapture;


public class SingleMarkerTracking extends PApplet {
	private static final long serialVersionUID = 1L;
	//private final String patternPath = "/resources/data/1.patt";
	//private final String PARAM_FILE = "/resources/data/camera_para.dat";
	GSCapture cam;
	NyARBoard nya;
	PFont font;

	public void setup() {
		//size(640, 480, OPENGL);
		size(640, 480);
		colorMode(RGB, 100);
		font = createFont("FFScala", 32);
		cam = new GSCapture(this, width, height,"/dev/video1");
		cam.start(); // start capturing
		// Left hand projection matrix
		nya = new NyARBoard(this, width, height, "camera_para.dat", "1.patt",80);
		//String[] patterns = loadPatternFilenames(patternPath);
		//Right hand projection matrix
		nya.gsThreshold = 120; // (0<n<255) default=110
		nya.cfThreshold = 0.4;// (0.0<n<1.0) default=0.4
	}

	void drawMarkerPos(PVector [] points) {
		PVector [] pos2d = nya.getMarkerVertex2D();
		//textFont(font, 10.0);
		stroke(100, 0, 0);
		fill(100, 0, 0);
		for (int i = 0; i < 4; i++) {
			ellipse(pos2d[i].x, pos2d[i].y, 5, 5);
		}
		fill(0, 0, 0);
		for (int i = 0; i < 4; i++) {
			text("(" + pos2d[i].x + "," + pos2d[i].y + ")",pos2d[i].x, pos2d[i].y);
		}
	}

	String angle2text(float a) {
		int i = (int) degrees(a);
		i = (i > 0 ? i : i + 360);
		return (i < 100 ? "  " : i < 10 ? " " : "") + Integer.toString(i);
	}

	String trans2text(float i) {
		return (i < 100 ? "  " : i < 10 ? " " : "") + Integer.toString((int) i);
	}

	public void draw() {
		  background(255);
		  if (cam.available() !=true) {
		    return;
		  }
		  cam.read();
		  image(cam,0,0);		  
		  if(cam.available()){
			PVector [] pos2d = nya.getMarkerVertex2D();
			//PMatrix3D trans = nya.getMarkerMatrix();
		    //textFont(font,25.0);
		    fill((int)((1.0-nya.confidence)*100),(int)(nya.confidence*100),0);
		    text((int)(nya.confidence*100)+"%",width-60,height-20);
		    
		    pushMatrix();
		    //textFont(font,10.0);
		    fill(0,100,0,80);
		    translate((pos2d[0].x+pos2d[1].x+pos2d[2].x+pos2d[3].x)/4+50,(pos2d[0].y+pos2d[1].y+pos2d[2].y+pos2d[3].y)/4+50);
		    //text("TRANS "+trans2text(trans.)+","+trans2text(nya.trans.y)+","+trans2text(nya.trans.z),0,0);
		    //text("ANGLE "+angle2text(nya.angle.x)+","+angle2text(nya.angle.y)+","+angle2text(nya.angle.z),0,15);
		    popMatrix();    
		    drawMarkerPos(pos2d);			    
		   /*
		    PGraphicsOpenGL pgl =	((PGraphicsOpenGL) g);
		    nya.beginTransform(pgl);
		    
		    stroke(255,200,0);
		    translate(0,0,20);
		    box(40);
		    nya.endTransform();
		    */
		  }
	}
	public String[] loadPatternFilenames(String path) {
	    File folder = new File(path);
	    FilenameFilter pattFilter = new FilenameFilter() {
	      public boolean accept(File dir, String name) {
	        return name.toLowerCase().endsWith(".patt");
	      }
	    }; // fin filenameFilter
	     return folder.list(pattFilter); // renvoi le tableau de String
	} // fin loadPatternFilenames

	public static void main(String args[]) {
		new SingleMarkerTracking().setVisible(true);

	}
} 






