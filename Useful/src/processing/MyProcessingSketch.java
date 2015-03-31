package processing;
import processing.core.*;

public class MyProcessingSketch extends PApplet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	An array of stripes
  Stripe[] stripes = new Stripe[50];

  public void setup() {
    size(640,480);
    // Initialize all "stripes"
    for (int i = 0; i < stripes.length; i++) {
      stripes[i] = new Stripe(this);
    }
  }

  public void draw() {
    background(100);
    // Move and display all "stripes"
    for (int i = 0; i < stripes.length; i++) {
      stripes[i].move();
      stripes[i].display();
    }
  }
}