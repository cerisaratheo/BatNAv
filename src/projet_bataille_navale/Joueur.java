package projet_bataille_navale;

import java.io.Serializable;
import java.util.*;

/**
 * Class representant un joueur
 * 
 */
public class Joueur implements Serializable {

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
	 * Methode qui affiche la liste des bateaux d'un joueur
	 */
	public void afficherListe_bateauParTaille() {
		liste_bateau.sort(new Comparator<Bateau>() {
			@Override
			public int compare(Bateau o1, Bateau o2) {
				if (o1.getLongueur()<o2.getLongueur()) return 1;
				if (o1.getLongueur()>o2.getLongueur()) return -1;
				return 0;
			}
		});
		System.out.println("\n======= liste triee par taille =======");
		afficherListe_bateau();
	}

	/**
	 * Methode qui affiche la liste des bateaux d'un joueur
	 */
	public void afficherListe_bateauParImpact() {
		liste_bateau.sort(new Comparator<Bateau>() {
			@Override
			public int compare(Bateau o1, Bateau o2) {
				float pi1 = (float)(o1.getLongueur()-o1.getPv())/(float)(o1.getLongueur());
				float pi2 = (float)(o2.getLongueur()-o2.getPv())/(float)(o2.getLongueur());
				if (pi1>pi2) return -1;
				if (pi1<pi2) return 1;
				return 0;
			}
		});
		System.out.println("\n======= liste triee par % d'impact =======");
		afficherListe_bateau();
	}

	/**
	 * @return grille du joueur
	 */
	public Grille getGrille() {
		return this.grille;
	}

	/**
	 * Methode qui permet au bateau courant de subir une attaque
	 * @param p_position case contenant le bateau sur laquelle subir le tir
	 * @param caseDeTir case pour afficher les tirs
	 */
	public boolean subirTir(Case p_position, Case caseDeTir) {
		caseDeTir.devenirTouchee();
		if (p_position.etreOccupee() == true) {
			Bateau bateau = p_position.occupePar();
			// on ajoute artificiellement un bout de bateau dans la grille des tirs lorsqu'il y avait un bateau a cet endroit, ce qui permet d'afficher differemment un tir avec et sans bateau
			caseDeTir.devenirOccupee(bateau);
			if (!p_position.etreTouchee()) {
				bateau.moinsUnPv();
				p_position.devenirTouchee();
			}
			return true;
		} else {
			p_position.devenirTouchee();
			return false;
		}
	}

	/**
	 * Methode permettant a un bateau de lancer un tir
	 * @param p_position case sur laquelle tirer
	 * @return booleen indiquant true si l'attaque a bien ete portee
	 * @throws ExceptionCaseImpactee
	 */
	public boolean lancerTir(Case p_position, Case caseDeTir) throws ExceptionCaseImpactee {
		if (p_position.etreTouchee() == true) {
			throw new ExceptionCaseImpactee();
		}
		return subirTir(p_position, caseDeTir);
	}

	/**
	 * Methode permettant de savoir si le joueur courant a perdu
	 * @return true si le joueur courant a perdu
	 */
	public boolean aPerdu() {
		List<Bateau> bats = getListe_bateau();
		for (Bateau b: bats) {
			if (!b.etreCoule()) return false;
		}
		return true;
	}
}
