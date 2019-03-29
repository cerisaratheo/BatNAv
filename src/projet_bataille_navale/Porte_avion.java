package projet_bataille_navale;

/**
 * Class definissant un bateau de type porte-avion
 *
 */
public class Porte_avion extends Bateau {
	
	private final static int taille = 5;
	private final static String nom = "porte avion";

	/**
	 * Constructeur d'un porte-avion
	 * @param p_orientation orientation du bateau courant
	 */
	public Porte_avion(int p_orientation) {
		super(taille, p_orientation);
	}

	/**
	 * @return la taille du bateau
	 */
	public int getTaille() {
		return taille;
	}
	
	/**
	 * @return le nom du bateau
	 */
	public String getNom() {
		return nom;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
