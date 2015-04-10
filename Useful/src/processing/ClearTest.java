package processing;

import processing.core.PApplet;
import processing.core.PGraphics;

@SuppressWarnings("serial")
public class ClearTest extends PApplet {

	PGraphics pg;
	
	public void setup() {
	  size(200, 200);
	  pg = createGraphics(100, 100);
	}
	
	public void draw() {
	  background(204);
	  pg.beginDraw();
	  pg.stroke(0, 102, 153);
	  pg.line(0, 0, mouseX, mouseY);
	  pg.endDraw();
	  image(pg, 50, 50); 
	}
	
	// Click to clear the PGraphics object
	public void mousePressed() {
	  pg.clear();
	}

}
