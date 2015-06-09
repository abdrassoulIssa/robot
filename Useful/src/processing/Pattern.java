package processing;

import processing.core.PApplet;
import processing.core.PVector;

public class Pattern extends PApplet{

	//THE MAXIMUM ROTATION SPEED
	private static final float mS = (float) 0.2;
	//KEEP TRACK OF THE CURRENT THE ID OF THE MARKER
	private int pID;
	private PVector rot;
	private PVector speed;
	public Pattern(int pID) {
		super();
		this.pID = pID;
		this.rot = new PVector(random(TWO_PI), random(TWO_PI), random(TWO_PI));
		this.speed = new PVector(random(-mS,mS), random(-mS,mS), random(-mS,mS));
	}
	
	public void run(){
		rot.add(speed);
		//CHECKS THE OBJECT'S CORRESPONDING MARKER THROUGH THE ID
		//IF THE MARKER IS FOUND DISPLAY THE CUBE
		
	}
	
	public void display(){
		
	}
	

}
