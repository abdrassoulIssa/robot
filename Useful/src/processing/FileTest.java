package processing;

import java.io.PrintWriter;

import processing.core.PApplet;

public class FileTest extends PApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PrintWriter output;

	public void setup() {
		size(600,400);
		frameRate(24); // on calibre une vitesse d'exécution
		 // Create a new file in the sketch directory
		 output = createWriter("positions.txt");
		background(255);
	}

	public void draw() {
		 point(mouseX, mouseY);
		 output.println(mouseX + " " + mouseY); // ici on a légèrement modifié
	}

	public void keyPressed() {
		 output.flush(); // Writes the remaining data to the file
		 output.close(); // Finishes the file
		 exit(); // Stops the program
	}

}
