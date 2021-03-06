package projet_bataille_navale;

/**
 * Class definissant un bateau de type croiseur
 *
 */
public class Croiseur extends Bateau {

	private static final int taille = 4;
	private final static String nom = "croiseur";

	/**
	 * Constructeur d'un croiseur
	 * @param p_orientation orientation du bateau courant
	 */
	public Croiseur(int p_orientation) {
		super(nom, taille, p_orientation);
	}

	/**
	 * @return la taille du bateau
	 */
	public int getTaille() {
		return taille;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
