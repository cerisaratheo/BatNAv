package projet_bataille_navale.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;

class JoueurTests {

	@Test
	void testJoueur() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		l.add(new Bateau("pedalo", 1, 1));
		Joueur j = new Joueur("toto", g, l);
		assert j!=null;
	}

	@Test
	void testGetNom() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		l.add(new Bateau("pedalo", 1, 1));
		Joueur j = new Joueur("toto", g, l);
		assert j.getNom().equals("toto");
	}

	@Test
	void testGetListe_bateau() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		Bateau b1 = new Bateau("pedalo", 1, 1);
		Bateau b2 = new Bateau("entreprise", 16, 2);
		l.add(b1);
		l.add(b2);
		Joueur j = new Joueur("toto", g, l);
		List<Bateau> ll = j.getListe_bateau();
		assert ll.contains(b1);
		assert ll.contains(b2);
		assert ll.size()==2;
	}

	@Test
	void testAfficherListe_bateau() {
		fail("Not yet implemented");
	}

	@Test
	void testAfficherListe_bateauParTaille() {
		fail("Not yet implemented");
	}

	@Test
	void testAfficherListe_bateauParImpact() {
		fail("Not yet implemented");
	}

	@Test
	void testGetGrille() {
		fail("Not yet implemented");
	}

	@Test
	void testSubirTir() {
		fail("Not yet implemented");
	}

	@Test
	void testLancerTir() {
		fail("Not yet implemented");
	}

	@Test
	void testAPerdu() {
		fail("Not yet implemented");
	}

}
