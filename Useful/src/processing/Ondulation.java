package processing;

import processing.core.PApplet;

public class Ondulation extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float ondulation=0;

	public void setup() {
	 size(600, 600);
	 fill(0);
	}

	public void draw() {
	 background(255);
	 translate(width/2, height/2);
	 float angle= (float) (sin(ondulation)*0.4); // mouvement de balancier
	 rotate(angle);
	 
	 line(-200, 0, 200, 0); // grande barre
	 text(angle, 205, 0);
	 
	 // barre d'arret
	 if (angle > 0) {
	   line(190, 0, 190, -30);
	 }
	 else {
	   line(-190, 0, -190, -30);
	 }
	 
	 // boule en mouvement
	 float roulis=map(angle, -0.4f, 0.4f, -180f, 180f);
	 ellipse(roulis, -10, 20, 20);

	 ondulation+=0.1;
	}
}
