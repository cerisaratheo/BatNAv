package projet_bataille_navale;

import java.io.Serializable;

/**
 * Classe representant la grille de jeu.
 * 
 */
public class Grille implements Serializable {

	public final static int HORIZONTAL = 1;
	public final static int VERTICAL= 2;

	private Case[][] grille;
	private int taille_x, taille_y;

	/**
	 * Constructeur pour initialiser une grille a partir d'une taille
	 * @param p_taille_x hauteur de la grille
	 * @param p_taille_y largeur de la grille
	 */
	public Grille(int p_taille_x, int p_taille_y) {
		this.taille_x = p_taille_x;
		this.taille_y = p_taille_y;
		this.grille = new Case[p_taille_x][p_taille_y];
		for (int i = 0; i < p_taille_x; i++) {
			for (int j = 0; j < p_taille_y; j++) {
				this.grille[i][j] = new Case(i, j);
			}
		}
	}

	/**
	 * @return la hauteur de la grille
	 */
	public int getTailleX() {
		return taille_x;
	}
	/**
	 * @return la largeur de la grille
	 */
	public int getTailleY() {
		return taille_y;
	}

	/**
	 * Methode qui positionne un bateau sur la grille.
	 * @param p_bateau bateau qui va etre place sur p_grille
	 * @param p_case point d'encrage du bateau sur la grille
	 * @return true si le bateau a bien ete place
	 * @throws ExceptionHorsDuTableau
	 * @throws ExceptionGrille
	 */
	public boolean positionnerBateau(Bateau p_bateau, Case p_case) throws ExceptionHorsDuTableau, ExceptionGrille {
		int longueur = p_bateau.getLongueur();
		if (testPositionnerBateau(p_bateau, p_case)) {
			if (p_bateau.getOrientation() == HORIZONTAL) {
				// tout est bon, on peut poser
				for (int i = 0; i < longueur; i++) {
					Case c = grille[p_case.getX()][p_case.getY()+i];
					c.devenirOccupee(p_bateau);
				}
				return true;
			} else if (p_bateau.getOrientation() == VERTICAL) {
				// tout est bon, on peut poser
				for (int i = 0; i < longueur; i++) {
					grille[p_case.getX()+i][p_case.getY()].devenirOccupee(p_bateau);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * methode qui sert a tester si un bateau peut etre pose sur une case
	 * @param p_bateau bateau a positionner
	 * @param p_case case a tester
	 * @return un boolen qui vaut true si le bateau peut etre pose
	 * @throws ExceptionHorsDuTableau
	 * @throws ExceptionGrille
	 */
	public boolean testPositionnerBateau(Bateau p_bateau, Case p_case) throws ExceptionHorsDuTableau, ExceptionGrille {
		int longueur = p_bateau.getLongueur();
		if (p_bateau.getOrientation() == HORIZONTAL) {
			// horizontal
			if (p_case.getY()+longueur>this.taille_y) 
				throw new ExceptionHorsDuTableau();
			// on verifie que toutes les cases sont OK
			for (int i = 0; i < longueur; i++) {
				Case e = grille[p_case.getX()][p_case.getY()+i];
				if (e.etreOccupee() == true) {
					throw new ExceptionGrille();
				}
				if (e.getX() >= this.taille_x || e.getX() < 0 || e.getY() >= this.taille_y || e.getY() < 0) {
					throw new ExceptionHorsDuTableau();
				}
			}
			return true;
		} else if (p_bateau.getOrientation() == VERTICAL) {
			// vertical
			if (p_case.getX()+longueur>this.taille_x) {
				throw new ExceptionHorsDuTableau();
			}
			// on verifie que toutes les cases sont OK
			for (int i = 0; i < longueur; i++) {
				Case e = grille[p_case.getX()+i][p_case.getY()];
				if (e.etreOccupee() == true) {
					throw new ExceptionGrille();
				}
				if (e.getX() >= this.taille_x || e.getX() < 0 || e.getY() >= this.taille_y || e.getY() < 0) {
					throw new ExceptionHorsDuTableau();
				}
			}
			return true;
		}
		return false;
	}




	/**
	 * Methode qui sert a afficher la grille dans le terminal.
	 */
	public void afficherGrille() {
		System.out.print("\n");

		// longueur maximale du nb X
		int maxX = Integer.toString(taille_x).length();

		System.out.print("\n||");
		for (int i=0;i<maxX+2;i++) {
			System.out.print("|");
		}
		for (int j = 1; j <= taille_y; j++) {
			System.out.print("| " + j + " |");
		}

		for (int i = 1; i <= taille_x; i++) {
			System.out.print("\n| " + i);
			for (int k = 0; k<maxX-Integer.toString(i).length(); k++) {
				System.out.print(" ");
			}
			System.out.print(" |");
			for (int j = 1; j <= taille_y; j++) {
				System.out.print('|'+grille[i-1][j-1].toString());
				for (int k=0;k<Integer.toString(j).length()-1;k++) {
					System.out.print(" ");
				}
				System.out.print('|');
			}
		}
		System.out.print("\n");
	}

	/**
	 * Methode trouvant une case grace a ses coordonnes
	 * @param p_x coordonnee x de la case cherchee
	 * @param p_y coordonnee y de la case cherchee
	 * @return la case cherchee
	 * @throws ExceptionHorsDuTableau
	 */
	public Case getCase(int p_x, int p_y) throws ExceptionHorsDuTableau {
		if (p_x < 0 || p_x >= taille_x || p_y < 0 || p_y >= taille_y) {
			throw new ExceptionHorsDuTableau();
		}
		return this.grille[p_x][p_y];
	}

}
