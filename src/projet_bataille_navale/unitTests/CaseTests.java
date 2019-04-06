package projet_bataille_navale.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Case;
import projet_bataille_navale.Croiseur;
import projet_bataille_navale.Torpilleur;

class CaseTests {

	@Test
	void testCase() {
		Case c = new Case(1, 2);
		assert c!=null;
		assertEquals("x doit etre egal a 1", 1, c.getX());
		assertEquals("y doit etre egal a 2", 2, c.getY());
	}

	@Test
	void testGetX() {
		Case c = new Case(5, 7);
		assertEquals("x doit etre egal a 5", 5, c.getX());
	}

	@Test
	void testGetY() {
		Case c = new Case(5, 7);
		assertEquals("y doit etre egal a 7", 7, c.getY());
	}

	@Test
	void testEtreTouchee() {
		Case c = new Case(5, 7);
		assertEquals("la case ne doit pas etre touchee", false,  c.etreTouchee());
		c.devenirTouchee();
		assertEquals("c doit etre touchee", true, c.etreTouchee());
	}

	@Test
	void testDevenirTouchee() {
		Case c = new Case(5, 7);
		assertEquals("la case ne doit pas etre touchee", false,  c.etreTouchee());
		c.devenirTouchee();
		assertEquals("c doit etre touchee", true, c.etreTouchee());
	}

	@Test
	void testEtreOccupee() {
		Case c = new Case(8, 7);
		Case c1 = new Case(8, 8);
		Case c2 = new Case(8, 9);
		Case c3 = new Case(8, 10);
		assertEquals("la case ne doit pas etre occupee", false, c.etreOccupee());
		c.devenirOccupee(new Torpilleur(1));
		assertEquals("la case doit etre occupee", true, c.etreOccupee());
	}

	@Test
	void testDevenirOccupee() {
		Case c = new Case(8, 7);
		Case c1 = new Case(8, 8);
		Case c2 = new Case(8, 9);
		Case c3 = new Case(8, 10);
		assertEquals("la case ne doit pas etre occupee", false, c.etreOccupee());
		c.devenirOccupee(new Torpilleur(1));
		assertEquals("la case doit etre occupee", true, c.etreOccupee());
	}

	@Test
	void testOccupePar() {
		Case c = new Case(8, 7);
		Case c1 = new Case(8, 8);
		Case c2 = new Case(8, 9);
		Case c3 = new Case(8, 10);
		Bateau b = new Torpilleur(1);
		c.devenirOccupee(b);
		assertEquals("la case doit etre occupee par b", b, c.occupePar());
	}
}
