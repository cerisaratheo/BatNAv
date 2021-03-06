package projet_bataille_navale;

import java.io.Serializable;

/**
 * Class bateau representant un bateau.
 *
 */
public abstract class Bateau implements Serializable {

	private int longueur, orientation, pv;
	private String nom;

	/**
	 * Constructeur d'un bateau
	 * @param p_nom nom du bateau
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
	 * @param orientation orientation du bateau
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	/**
	 * decremente les points de vie du bateau si ils sont positifs
	 */
	public void moinsUnPv() {
		if (this.pv > 0) {
			this.pv = this.pv-1;
		}
		else {
			this.pv = 0;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  (this.getNom() + " " + this.getLongueur()+" cases "+this.getPv() + " pv.");
	}

	/**
	 * @return the pv
	 */
	public int getPv() {
		return pv;
	}
}
