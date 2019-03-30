package projet_bataille_navale;

import java.io.Serializable;

/**
 * Class bateau representant un bateau.
 *
 */
public class Bateau implements Serializable {

	private int longueur, orientation, pv;
	private String nom;

	/**
	 * Constructeur d'un bateau
	 * @param p_longueur longueur du bateau
	 * @param p_orientation orientation du bateau sur la grille
	 */
	public Bateau(String p_nom, int p_longueur, int p_orientation) {
		this.pv = p_longueur;
		this.longueur = p_longueur;
		this.orientation = p_orientation;
		this.nom = p_nom;
	}

	/**
	 * Methode permettant de savoir si un bateau est mort ou non
	 * @return true si le bateau est mort
	 */
	public boolean etreCoule() {
		return this.pv<=0;
	}

	/**
	 * @return le nom du bateau
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @return longueur du bateau
	 */
	public int getLongueur() {
		return longueur;
	}

	/**
	 * @return the orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	/**
	 * @param pv the pv to set
	 */
	public void moinsUnPv() {
		this.pv = this.pv-1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ("Bateau de type : " + this.getNom() + ", de taille : " + this.getLongueur() + " dispose de : " + this.getPv() + " pv.");
	}

	/**
	 * @return the pv
	 */
	public int getPv() {
		return pv;
	}
}
