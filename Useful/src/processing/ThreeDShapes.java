package processing;
import processing.core.PApplet;
import processing.opengl.*;

@SuppressWarnings({ "unused", "serial" })
public class ThreeDShapes extends PApplet {

	public void setup() {
	 size(640, 480, OPENGL);
	 System.out.println(System.getProperty("user.dir"));
	}

	public void draw()
	{
	  background(100, 100, 150);
	 
	  pushMatrix(); 
		  translate(150, 150, 0);
		  rotateY(0.5f);
		  rotateZ(0.3f);
		  box(100);
	  popMatrix();
	  translate(250, 210, -100);
	  sphere(100);
	}

}
