package projet_bataille_navale;

import java.io.Serializable;

/**
 * Class representant une case de la grille de jeu.
 *
 */
public class Case implements Serializable {

	private int x, y;
	private boolean touchee, occupee;
	private Bateau occupeePar;
	
	/**
	 * Constructeur permettant d'initialiser une case
	 * @param p_x abscisse de la case
	 * @param p_y ordonee de la case
	 */
	public Case(int p_x, int p_y) {
		this.x = p_x;
		this.y = p_y;
		this.touchee = false;
		this.occupee = false;
		this.occupeePar = null;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Methode indiquant si une case est touchee par un tir ou pas
	 * @return true si la case est touchee
	 */
	public boolean etreTouchee() {
		if (this.touchee == false) {
			return false;
		}
		return true;
	}
	
	/**
	 * Methode pour rendre une case touchee
	 */
	public void devenirTouchee() {
		this.touchee = true;
	}
	
	/**
	 * Methode indiquant si une case est occupee par un bateau ou non
	 * @return true si la case est occupee
	 */
	public boolean etreOccupee() {
		if (occupee == true) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Methode servant a rendre une case occupee
	 * @param p_bateau bateau qui occupe la case en question
	 */
	public void devenirOccupee(Bateau p_bateau) {
		this.occupee = true;
		this.occupeePar = p_bateau;
	}
	
	/**
	 * Methode indiquant par quel bateau est occupee la case
	 * @return le bateau qui occupe la case en question
	 */
	public Bateau occupePar() {
		return this.occupeePar;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (etreOccupee() && etreTouchee()) return " # ";
		if (this.etreOccupee()) return " X ";
		if (this.etreTouchee()) return " o ";
		return " . ";
	}
}
