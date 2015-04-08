package processing;
import processing.core.*;

public class MyProcessingSketch extends PApplet {

  private static final long serialVersionUID = 1L;

  public MyProcessingSketch() {
	super();
	// TODO Auto-generated constructor stub
  }

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
    background(0);
    // Move and display all "stripes"
    for (int i = 0; i < stripes.length; i++) {
      stripes[i].move();
      stripes[i].display();
    }
  }
  
  @Override
	public void keyPressed() {
		// TODO Auto-generated method stub
		if(keyCode == ENTER){
			background(255);
			println("Enter");
		}
	}
}