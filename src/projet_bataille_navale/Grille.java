package projet_bataille_navale;

/**
 * Classe representant la grille de jeu.
 * 
 */
public class Grille {

	private Case[][] grille;
	private int taille_x, taille_y;

	/**
	 * Constructeur pour initialiser une grille a partir d'une taille
	 * @param taille_x taille de la grille
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
	 * @return the grille
	 */
	public Case[][] getGrille() {
		return grille;
	}

	/**
	 * @return the taille
	 */
	public int getTaille() {
		return taille_x;
	}

	/**
	 * Methode qui positionne un bateau sur la grille.
	 * @param p_grille grille sur laquelle va se placer le bateau
	 * @param p_bateau bateau qui va etre place sur p_grille
	 * @param p_case point d'encrage du bateau sur la grille
	 * @return true si le bateau a bien ete place
	 */
	public boolean positionnerBateau(Bateau p_bateau, Case p_case) throws ExceptionHorsDuTableau, ExceptionGrille {
		int longueur = p_bateau.getLongueur();
		if (p_case.getX() > this.taille_x + 1 || p_case.getX() < 0 || p_case.getY() > this.taille_y + 1 || p_case.getY() < 0) {
			throw new ExceptionHorsDuTableau();
		} else if (p_case.etreOccupee() == false) {
			if (p_bateau.getOrientation() == 1) {
				// horizontal
				grille[p_case.getX()][p_case.getY()].devenirOccupee(p_bateau);
				for (int i = 1; i < longueur; i++) {
					Case e = grille[p_case.getX()][p_case.getY()+i];
					if (e.getX() > this.taille_x + 1 || e.getX() < 0 || e.getY() > this.taille_y + 1 || e.getY() < 0) {
						throw new ExceptionHorsDuTableau();
					} else {
						grille[p_case.getX()][p_case.getY()+i].devenirOccupee(p_bateau);
					}
				}
				return true;
			} else if (p_bateau.getOrientation() == 2) {
				// vertical
				grille[p_case.getX()][p_case.getY()].devenirOccupee(p_bateau);
				for (int i = 1; i < longueur; i++) {
					Case e = grille[p_case.getX()+i][p_case.getY()];
					if (e.getX() > this.taille_x + 1 || e.getX() < 0 || e.getY() > this.taille_y + 1 || e.getY() < 0) {
						throw new ExceptionHorsDuTableau();
					} else {
						grille[p_case.getX()+i][p_case.getY()].devenirOccupee(p_bateau);
					}
				}
				return true;
			}
		} else {
			throw new ExceptionGrille();
		}
		return false;
	}
	
//	/**
//	 * Methode qui positionne un bateau sur la grille.
//	 * 
//	 * @param p_grille
//	 *            grille sur laquelle va se placer le bateau
//	 * @param p_bateau
//	 *            bateau qui va etre place sur p_grille
//	 * @param p_case
//	 *            point d'encrage du bateau sur la grille
//	 * @return booleen indiquant true sur le bateau a bien ete place
//	 */
//	public boolean positionnerBateau(Bateau p_bateau, Case p_case) throws ExceptionHorsDuTableau, ExceptionGrille {
//		System.out.println("case, x : "+p_case.getX()+" y : "+p_case.getY());
//		if (p_case.getX() > this.taille + 1 || p_case.getX() < 0 || p_case.getY() > this.taille + 1 || p_case.getY() < 0) {
//			throw new ExceptionHorsDuTableau();
//		}
//		if (p_case.etreOccupee() == false) {
//			if (p_bateau.getOrientation() == 1) {
//				// horizontal
//				p_case.devenirOccupee(p_bateau);
//				for (int i = 1; i < p_bateau.getLongueur(); i++) {
//					if (p_case.getX() > this.taille + 1 || p_case.getX() < 0 || p_case.getY() > this.taille + 1 || p_case.getY() < 0) {
//						throw new ExceptionHorsDuTableau();
//					}
//					this.grille[p_case.getY()][p_case.getX() + i].devenirOccupee(p_bateau);
//				}
//				return true;
//			} else if (p_bateau.getOrientation() == 2) {
//				// vertical
//				p_case.devenirOccupee(p_bateau);
//				for (int i = 1; i < p_bateau.getLongueur(); i++) {
//					if (p_case.getX() > this.taille + 1 || p_case.getX() < 0 || p_case.getY() > this.taille + 1 || p_case.getY() < 0) {
//						throw new ExceptionHorsDuTableau();
//					}
//					this.grille[p_case.getY() + i][p_case.getX()].devenirOccupee(p_bateau);
//				}
//				return true;
//			}
//		} else {
//			throw new ExceptionGrille();
//		}
//		return false;
//	}

	/**
	 * Methode qui sert a afficher la grille dans le terminal.
	 */
	public void afficherGrille() {
		System.out.print("\n");
		for (int i = 0; i < taille_x; i++) {
			for (int j = 0; j < taille_y; j++) {
				if (i == 0) {
					if (j == 0) {
						System.out.print("\n||||||");
					} else if (j < 10) {
						System.out.print("| " + j + " |");
					} else {
						System.out.print("| " + j + "|");
					}

				} else if (j == 0) {
					if (i < 10) {
						System.out.print("\n| " + i + "  |");
					} else {
						System.out.print("\n| " + i + " |");
					}
				} else {
					System.out.print(grille[i][j].toString());
				}
			}
		}
	}

	/**
	 * Methode trouvant une case grace a ses coordonnes
	 * @param p_x coordonnee x de la case cherchee
	 * @param p_y coordonnee y de la case cherchee
	 * @return la case cherchee
	 */
	public Case getCase(int p_x, int p_y) throws ExceptionHorsDuTableau {
		if (p_x < 1 || p_x > taille_x || p_y < 1 || p_y > taille_y) {
			throw new ExceptionHorsDuTableau();
		}
		return this.grille[p_x][p_y];
	}

}
