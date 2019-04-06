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

	@Test
	void testGrille() {
		Grille g = new Grille(19, 20);
		assert g!=null;
		assertEquals(19, g.getTailleX());
		assertEquals(20, g.getTailleY());

	}

	@Test
	void testGetTailleX() {
		Grille g = new Grille(20, 20);
		assertEquals("la grille doit etre de hauteur 20", 20, g.getTailleX());
	}

	@Test
	void testGetTailleY() {
		Grille g = new Grille(20, 20);
		assertEquals("la grille doit etre de largeur 20", 20, g.getTailleY());
	}

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
		Case c1 = null;
		try {
			c1 = g.getCase(5, 5);
		} catch (ExceptionHorsDuTableau e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("le bateau devrait etre sur la grille", true, c1.etreOccupee());
	}

	@Test
	void testGetCase() {
		Grille g = new Grille(12, 10);
		try {
			Case c = g.getCase(5, 2);
			assertEquals(5, c.getX());
			assertEquals(2, c.getY());
		} catch (ExceptionHorsDuTableau e) {
			e.printStackTrace();
		}
	}

}
