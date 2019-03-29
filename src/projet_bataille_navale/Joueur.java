package projet_bataille_navale;

import java.util.*;

/**
 * Class representant un joueur
 * 
 */
public class Joueur {

	private String nom;
	private ArrayList<Bateau> liste_bateau;
	private Grille grille;

	/**
	 * Constructeur d'un joueur
	 * @param p_nom nom du joueur
	 * @param p_grille grille du joueur
	 * @param p_liste_bateau liste de bateau du joueur
	 */
	public Joueur(String p_nom, Grille p_grille, ArrayList<Bateau> p_liste_bateau) {
		this.nom = p_nom;
		this.liste_bateau = p_liste_bateau;
		this.grille = p_grille;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the liste_bateau
	 */
	public List<Bateau> getListe_bateau() {
		return liste_bateau;
	}
	
	/**
	 * Methode qui affiche la liste des bateaux d'un joueur
	 */
	public void afficherListe_bateau() {
		for(int i=0; i < this.liste_bateau.size(); i++) {			
			System.out.println(this.liste_bateau.get(i).toString());
		}
	}

	/**
	 * @return grille du joueur
	 */
	public Grille getGrille() {
		return this.grille;
	}

	/**
	 * Methode qui permet au bateau courant de subir une attaque
	 * @param p_bateau bateau victime
	 * @param p_position case sur laquelle subir le tir
	 */
	public void subirTir(Bateau p_bateau, Case p_position) throws ExceptionCaseImpactee {
		p_position.devenirTouchee();
		if (p_position.etreOccupee() == true) {
			if (p_position.occupePar() == p_bateau) {
				p_bateau.moinsUnPv();
			}
		} else {
			System.out.println("\nTir porté sur une case vide.\n");
		}
	}

	/**
	 * Methode permettant a un bateau de lancer un tir
	 * @param p_position case sur laquelle tirer
	 * @return booleen indiquant true si l'attaque a bien ete portee
	 * @throws ExceptionCaseImpactee
	 */
	public boolean lancerTir(Case p_position) throws ExceptionCaseImpactee {
		if (p_position.etreTouchee() == true) {
			throw new ExceptionCaseImpactee();
		}
		if (p_position.etreOccupee() == true) {
			subirTir(p_position.occupePar(), p_position);
			return true;
		}
		return false;
	}
	
	/**
	 * Methode permettant de savoir si le joueur courant a perdu
	 * @return true si le joueur courant a perdu
	 */
	public boolean aPerdu() {
		boolean tmp = false;
		int i = 0;
		while (i < liste_bateau.size()) {
			if (liste_bateau.get(i).etreCoule() == true) {
				tmp = true;
			} else {				
				tmp = false;
				return tmp;
			}
			i++;
		}
		return tmp;
	}

}
