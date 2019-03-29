package projet_bataille_navale;

/**
 * Class definissant un bateau de type sous-marin
 *
 */
public class Sous_marin extends Bateau {
	
	private static final int taille = 3;

	/**
	 * Constructeur d'un sous-marin
	 * @param p_orientation orientation du bateau courant
	 */
	public Sous_marin(int p_orientation) {
		super(taille, p_orientation);
	}

	/**
	 * @return the taille
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
