package projet_bataille_navale.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Croiseur;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;
import projet_bataille_navale.Porte_avion;
import projet_bataille_navale.Sauvegarde;
import projet_bataille_navale.Torpilleur;

class SauvegardeTests {

	/**
	 * On teste la sauvegarde et le chargement d'une partie en cours
	 */
	@Test
	void testSauve() {
		Grille g = new Grille(15, 12);
		ArrayList<Bateau> l = new ArrayList<Bateau>();
		l.add(new Torpilleur(0));
		Joueur j = new Joueur("toto", g, l);
		l.get(0).moinsUnPv();
		Sauvegarde s = new Sauvegarde(j, "jeu.sauv");
		s.sauve();
		Joueur j2 = s.charge();
		List<Bateau> l2 = j2.getListe_bateau();
		assertEquals("le bateau doit avoir 1 pv", 1, l2.get(0).getPv());
		assertEquals("les listes doivent etre de la meme taille", l.size(), l2.size());
	}
}
