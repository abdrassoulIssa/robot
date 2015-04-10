package processing;

import processing.core.PApplet;
@SuppressWarnings("serial")
public class Collision extends PApplet{
	//Déclaration d'une variable contenant le nombre de balles
	int nbreBalle = 3;

	//Déclaration d'une liste d'instances de l'objet Balle
	Balle[] balles = new Balle[nbreBalle];

	public void  setup() {
		smooth(); //Lissage des dessins
		size(400, 200); //Taille de la fenêtre

		//Cette boucle va créer trois balles blanches
		//au centre de l'écran
		for (int i = 0; i < nbreBalle; i++) {
		  balles[i] = new Balle(width/2, height/2,  color(255));
		}
	}

	public void  draw() {
		background(0);
		fill(0, 0, 0, 1); // Couleur avec transparence.
		rect(0, 0, width, height);

		//noStroke();

		//Cette boucle va déplacer et afficher les trois balles
		for (int i = 0; i < nbreBalle; i++) {
		  balles[i].bouge();
		  balles[i].testCollision();
		  balles[i].display();
		}
	}

	class Balle {
		//Déclaration des paramètres de base de la balle
		float x;
		float y;
		float vitesseX;
		float vitesseY;
		int  couleur;

		//Constructeur de la balle
		Balle (float nouvX, float nouvY, int nouvCouleur) {
		  x      = nouvX;
		  y      = nouvY;
		  couleur    = nouvCouleur;

		  vitesseX = 2 + random(-1,1);
		  vitesseY = 2 + random(-1,1);
		}

		//Dessin de la balle
		public void  display() {
		  fill(couleur);
		  ellipse(x, y, 40, 40);
		}

		//Déplacement de la balle
		public void  bouge() {
		  x = x + vitesseX;
		  y = y + vitesseY;
		}

		public void  testCollision() {
		  //Si la balle touche un mur, elle rebondit
		  if (x > width-20 || x < 20) {
		     vitesseX = -vitesseX;
		  }
		  if (y > height-20 || y < 20) {
		     vitesseY = -vitesseY;
		  }
		}
	}
}