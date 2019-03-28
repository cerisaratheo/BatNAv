
/**
 * Class definissant un bateau de type Contre_torpilleur
 *
 */
public class Contre_torpilleur extends Bateau {
	
	private static final int taille = 3;

	/**
	 * Constructeur d'un Contre_torpilleur
	 * @param p_orientation orientation du bateau courant
	 */
	public Contre_torpilleur(int p_orientation) {
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
