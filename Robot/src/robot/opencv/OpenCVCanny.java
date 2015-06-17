package robot.opencv;

import codeanticode.gsvideo.GSCapture;
import processing.core.PApplet;
import monclubelec.javacvPro.OpenCV;

import org.opencv.*;

public class OpenCVCanny extends PApplet {
	GSCapture cam;
	OpenCV opencv; // déclare un objet OpenCV principal
	int sw = 320, sh = 240;

	public void setup() { // fonction d'initialisation exécutée 1 fois au démarrage
	  size( 2*sw, sh );
	  //--- initialise OpenCV ---
	  opencv = new OpenCV(this); // initialise objet OpenCV à partir du parent This
	  cam = new GSCapture(this, sw, sh);
	  cam.start();
	}

	public void  draw() { // fonction exécutée en boucle

	  if (cam.available() == true) {
	    cam.read();
	  }
	  //-- charge image utilisée --- 
	  //img=loadImage(url, "jpg"); // crée un PImage contenant le fichier à partir adresse web
	  opencv.allocate(cam.width, cam.height); // initialise les buffers OpenCv à la taille de l'image
	  opencv.copy(cam); // charge le PImage dans le buffer OpenCV

	  //--- affiche image de départ ---         
	  image(opencv.getBuffer(), 0, 0); // affiche le buffer principal OpenCV dans la fenêtre Processing

	  //--- opérations sur image ---

	  //-- toutes ces formes sont possibles : 
	  //opencv.canny(); // applique le filtre de canny sur le buffer principal OpenCV avec paramètres par défaut
	  //opencv.canny(100,200); //applique le filtre de canny sur le buffer principal OpenCV avec paramètres - noyau 3x3 par défaut
	  opencv.canny(1000, 2000, 5); //applique le filtre de canny sur le buffer OpenCV désigné avec paramètres

	  //opencv.canny(opencv.Buffer,100,400); //applique le filtre de canny sur le buffer OpenCV désigné avec paramètres - noyau 3x3 par défaut
	  //opencv.canny(opencv.Buffer,100,200,3); //applique le filtre de canny sur le buffer OpenCV désigné avec paramètres

	  //opencv.invert(); // pour dessin au trait noir sur blanc

	  //--- affiche image finale --- 
	  image(opencv.getBuffer(), opencv.width(), 0); // affiche le buffer principal OpenCV dans la fenêtre Processing
	}

}
