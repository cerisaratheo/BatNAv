
/**
 * Class definissant un bateau de type torpilleur
 *
 */
public class Torpilleur extends Bateau {
	
	private static final int taille = 2;

	/**
	 * Constructeur d'un torpilleur
	 * @param p_orientation orientation du bateau courant
	 */
	public Torpilleur(int p_orientation) {
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
