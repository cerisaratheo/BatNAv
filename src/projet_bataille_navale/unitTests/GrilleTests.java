package projet_bataille_navale.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Case;
import projet_bataille_navale.Contre_torpilleur;
import projet_bataille_navale.ExceptionGrille;
import projet_bataille_navale.ExceptionHorsDuTableau;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;
import projet_bataille_navale.Torpilleur;

class GrilleTests {

	/**
	 * test constructeur
	 */
	@Test
	void testGrille() {
		Grille g = new Grille(19, 20);
		assert g!=null;
		assertEquals("x doit etre egal a 19", 19, g.getTailleX());
		assertEquals("y doit etre egal a 20", 20, g.getTailleY());

	}

	/**
	 * On teste getTailleX() et getTailleY()
	 */
	@Test
	void testGetTaille() {
		Grille g = new Grille(20, 19);
		assertEquals("la grille doit etre de hauteur 20", 20, g.getTailleX());
		assertEquals("la grille doit etre de largeur 19", 19, g.getTailleY());
	}

	/**
	 * On positionne un bateau sur la grille et on verifie si il est bien sur la bonne case
	 */
	@Test
	void testPositionnerBateau() {
		Grille g = new Grille(10, 10);
		Bateau b = new Torpilleur(1);
		Case c = null;
		try {
			c = g.getCase(5, 5);
		} catch (ExceptionHorsDuTableau e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			g.positionnerBateau(b, c);
		} catch (ExceptionHorsDuTableau | ExceptionGrille e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Case c2 = null;
		try {
			c2 = g.getCase(5, 5);
		} catch (ExceptionHorsDuTableau e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("le bateau devrait etre sur la grille", true, c2.etreOccupee());
	}

	/**
	 * On teste si les coordonnees retournees sont les bonnes
	 */
	@Test
	void testGetCase() {
		Grille g = new Grille(12, 10);
		try {
			Case c = g.getCase(5, 2);
			assertEquals("x doit etre egal a 5", 5, c.getX());
			assertEquals("y doit etre egal a 2", 2, c.getY());
		} catch (ExceptionHorsDuTableau e) {
			e.printStackTrace();
		}
	}

}
