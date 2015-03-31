package robot.gsvideo;

import codeanticode.gsvideo.GSCapture;
import processing.core.PApplet;

public class ProcessingGSVideo extends PApplet {
	private static final long serialVersionUID = 1L;
	GSCapture video;
	
	public void setup(){
		size(640,780);
		background(10);
		//video = new GSCapture(this, width, height);
		video = new GSCapture(this, 640, 360, "/dev/video1");
		video.start();
		
	}
	
	public void draw(){
		if(video.available()){
			video.read();
			video.loadPixels();
			image(video, 0, 0);
		}
	}

}
