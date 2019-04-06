package projet_bataille_navale.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Case;
import projet_bataille_navale.Contre_torpilleur;
import projet_bataille_navale.Croiseur;
import projet_bataille_navale.ExceptionCaseImpactee;
import projet_bataille_navale.ExceptionGrille;
import projet_bataille_navale.ExceptionHorsDuTableau;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;
import projet_bataille_navale.Porte_avion;
import projet_bataille_navale.Torpilleur;

class JoueurTests {

	@Test
	void testJoueur() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		l.add(new Torpilleur(0));
		Joueur j = new Joueur("toto", g, l);
		assert j!=null;
		assertEquals("le joueur ne devrait pas etre nul", "toto", j.getNom());
	}

	@Test
	void testGetNom() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		l.add(new Porte_avion(0));
		Joueur j = new Joueur("toto", g, l);
		assertEquals("le joueur doit s'appeler toto", "toto", j.getNom());
	}

	@Test
	void testGetListe_bateau() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		Bateau b1 = new Contre_torpilleur(0);
		Bateau b2 = new Croiseur(0);
		l.add(b1);
		l.add(b2);
		Joueur j = new Joueur("toto", g, l);
		List<Bateau> ll = j.getListe_bateau();
		assertEquals("la liste ll devrait contenir b1" , true, ll.contains(b1));
		assertEquals("la liste ll devrait contenir b2", true, ll.contains(b2));
		assertEquals("la liste devrait etre de taille 2", 2, ll.size());
	}

	@Test
	void testAfficherListe_bateauParTaille() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		Bateau b1 = new Contre_torpilleur(0);
		Bateau b2 = new Croiseur(0);
		l.add(b1);
		l.add(b2);
		Joueur j = new Joueur("toto", g, l);
		j.afficherListe_bateauParTaille();
		List<Bateau> lb = j.getListe_bateau();
		assertEquals("le premier element doit etre b2", b2, lb.get(0));
		assertEquals("le deuxieme element doit etre b1", b1, lb.get(1));
	}

	@Test
	void testAfficherListe_bateauParImpact() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		Bateau b1 = new Contre_torpilleur(0);
		Bateau b2 = new Croiseur(0);
		l.add(b1);
		l.add(b2);
		b2.moinsUnPv();
		b2.moinsUnPv();
		Joueur j = new Joueur("toto", g, l);
		j.afficherListe_bateauParTaille();
		List<Bateau> lb = j.getListe_bateau();
		assertEquals("le premier element doit etre b1", b1, lb.get(1));
		assertEquals("le deuxieme element doit etre b2", b2, lb.get(0));
	}

	@Test
	void testGetGrille() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		Bateau b1 = new Contre_torpilleur(0);
		l.add(b1);
		Joueur j = new Joueur("toto", g, l);
		assertEquals("la grille doit etre la grille g", g, j.getGrille());
	}

	@Test
	void testSubirTir() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		Bateau b1 = new Contre_torpilleur(1);
		l.add(b1);
		Joueur j = new Joueur("toto", g, l);
		try {
			g.positionnerBateau(b1, g.getCase(0, 0));
		} catch (ExceptionHorsDuTableau e) {
			e.printStackTrace();
		} catch (ExceptionGrille e) {
			e.printStackTrace();
		}
		Case c1=null;
		try {
			c1 = g.getCase(0, 0);
		} catch (ExceptionHorsDuTableau e) {
			e.printStackTrace();
		}
		assertEquals("le bateau doit avoir subi un tir" ,true, j.subirTir(c1, c1));
		assertEquals("b1 devrait avoir 2 pv", 2, b1.getPv());
	}

	@Test
	void testLancerTir() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		Bateau b1 = new Contre_torpilleur(1);
		l.add(b1);
		Joueur j = new Joueur("toto", g, l);
		try {
			g.positionnerBateau(b1, g.getCase(0, 0));
		} catch (ExceptionHorsDuTableau e) {
			e.printStackTrace();
		} catch (ExceptionGrille e) {
			e.printStackTrace();
		}
		Case c1=null;
		try {
			c1 = g.getCase(0, 0);
		} catch (ExceptionHorsDuTableau e) {
			e.printStackTrace();
		}
		try {
			j.lancerTir(c1, c1);
		} catch (ExceptionCaseImpactee e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("b1 devrait avoir 2 pv", 2, b1.getPv());
	}

	@Test
	void testAPerdu() {
		Grille g = new Grille(1567, 234);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		Bateau b1 = new Torpilleur(1);
		l.add(b1);
		Joueur j = new Joueur("toto", g, l);
		b1.moinsUnPv();
		b1.moinsUnPv();
		assertEquals("le joueur devrait avoir perdu", true, j.aPerdu());
	}

}
