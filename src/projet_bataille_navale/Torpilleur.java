package projet_bataille_navale;

/**
 * Class definissant un bateau de type torpilleur
 *
 */
public class Torpilleur extends Bateau {
	
	private final static int taille = 2;
	private final static String nom = "torpilleur";

	/**
	 * Constructeur d'un torpilleur
	 * @param p_orientation orientation du bateau courant
	 */
	public Torpilleur(int p_orientation) {
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
