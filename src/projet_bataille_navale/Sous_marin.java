package projet_bataille_navale;

/**
 * Class definissant un bateau de type sous-marin
 *
 */
public class Sous_marin extends Bateau {

	private final static int taille = 3;
	private final static String nom = "sous marin";

	/**
	 * Constructeur d'un sous-marin
	 * @param p_orientation orientation du bateau courant
	 */
	public Sous_marin(int p_orientation) {
		super(nom, taille, p_orientation);
	}

	/**
	 * @return the taille
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
