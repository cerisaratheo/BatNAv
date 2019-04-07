package projet_bataille_navale.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Contre_torpilleur;
import projet_bataille_navale.Porte_avion;
import projet_bataille_navale.Torpilleur;

class BateauTests {

	/**
	 * test constructeur
	 */
	@Test
	void testBateau() {
		Bateau b1 = new Contre_torpilleur(0);
		assert b1!=null;
		assertEquals("b1 ne doit pas etre nul", "contre torpilleur", b1.getNom());
	}

	/**
	 * On enlève tous ses points de vie a un bateau, il doit etre coule
	 */
	@Test
	void testEtreCoule() {
		Bateau b1 = new Porte_avion(0);
		assertEquals("le bateau ne devrait pas etre coule", false, b1.etreCoule());
		for (int i=0; i<b1.getLongueur(); i++) {
			b1.moinsUnPv();	
		}
		assertEquals("le bateau devrait etre coule", true, b1.etreCoule());
		}

	/**
	 * On teste si le nom retourne est bien le bon
	 */
	@Test
	void testGetNom() {
		Bateau b1 = new Contre_torpilleur(0);
		assertEquals("b1 doit s'appeller 'contre torpilleur'", "contre torpilleur", b1.getNom());
		Bateau b2 = new Porte_avion(0);
		assertEquals("b1 doit s'appeller 'porte avion'", "porte avion", b2.getNom());
	}

	/**
	 * On teste si la longueur retournee est bien la bonne
	 */
	@Test
	void testGetLongueur() {
		Bateau b1 = new Contre_torpilleur(0);
		assertEquals("b1 doit etre de longueur 3", 3, b1.getLongueur());
		Bateau b2 = new Porte_avion(0);
		assertEquals("b2 doit etre de longueur 5", 5, b2.getLongueur());
	}

	/**
	 * On teste si l'orientation retournee est bien la bonne
	 */
	@Test
	void testGetOrientation() {
		Bateau b1 = new Porte_avion(1);
		assertEquals("l'orientation doit etre horizontale", 1, b1.getOrientation());
		Bateau b2 = new Porte_avion(2);
		assertEquals("l'orientation doit etre verticale", 2, b2.getOrientation());
	}

	/**
	 * On change l'orientation d'un bateau
	 */
	@Test
	void testSetOrientation() {
		Bateau b1 = new Porte_avion(0);
		b1.setOrientation(1);
		assertEquals("l'orientation doit etre horizontale", 1, b1.getOrientation());
	}

	/**
	 * On enlève tous ses pv à un bateau et on teste a chaque fois si le nombre de pv est le bon
	 */
	@Test
	void testMoinsUnPv() {
		Bateau b1 = new Contre_torpilleur(0);
		assertEquals("b1 devrait avoir 3 pv", 3, b1.getPv());
		b1.moinsUnPv();
		assertEquals("b1 devrait avoir 2 pv", 2, b1.getPv());
		b1.moinsUnPv();
		assertEquals("b1 devrait avoir 1 pv", 1, b1.getPv());
		b1.moinsUnPv();
		assertEquals("b1 devrait avoir 0 pv", 0, b1.getPv());
		b1.moinsUnPv();
		assertEquals("b1 devrait avoir 0 pv", 0, b1.getPv());
	}

	/**
	 * On teste si le nombre de pv retourne est le bon
	 */
	@Test
	void testGetPv() {
		Bateau b1 = new Contre_torpilleur(0);
		assertEquals("b1 devrait avoir 3 pv", 3, b1.getPv());
		Bateau b2 = new Torpilleur(0);
		assertEquals("b2 devrait avoir 2 pv", 2, b2.getPv());
	}

}
