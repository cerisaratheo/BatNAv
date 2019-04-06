package projet_bataille_navale.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Contre_torpilleur;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;

class GrilleTests {

	@Test
	void testGrille() {
		Grille g = new Grille(20, 20);
		assertEquals("la grille ne devrait pas etre nulle", g, g.getGrille());
	}

	@Test
	void testGetGrille() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	void testGetCase() {
		fail("Not yet implemented");
	}

}
