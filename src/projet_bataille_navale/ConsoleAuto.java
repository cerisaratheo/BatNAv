package projet_bataille_navale;

import java.util.InputMismatchException;
import java.util.Random;

public class ConsoleAuto implements GUI {

	private Random rd = new Random();

	@Override
	/**
	 * methode pour jouer une partie
	 * @param g contient une grille avec des bateaux poses 
	 * @param j joueur qui joue la partie 
	 */
	public void jouer(Grille g, Joueur j) {
		// test de la sauvegarde et du load
		Sauvegarde sauveur = new Sauvegarde(j, "jeu.sauve");
		sauveur.sauve();
		j = sauveur.charge();

		Grille gtirs = new Grille(g.getTailleX(), g.getTailleY());
		//Iteration de jeu
		while (!j.aPerdu()) {
			try {
				//attaque joueur 1
				int x = rd.nextInt(100)-1;
				int y = rd.nextInt(100)-1;
				boolean touche = j.lancerTir(j.getGrille().getCase(x, y), gtirs.getCase(x, y));
				if (touche) {
					System.out.println("coup: "+x+" "+y+" touche: "+touche);
					j.afficherListe_bateauParTaille();
					j.afficherListe_bateauParImpact();
				}
			} catch (ExceptionCaseImpactee e) {
			} catch (ExceptionHorsDuTableau e) {
			}
		}
		gtirs.afficherGrille();
		j.afficherListe_bateau();

		System.out.println("Arret de jeu.");
		g.afficherGrille();
	}

	@Override
	/**
	* methode pour positionner un bateau sur la grille
	* @param g grille sur laquelle on veut positionner le bateau
	* @param j joueur a qui appartient le bateau
	*/
	public void positionnerBateau(Grille g, Joueur j) {
		for (int i=0; i<j.getListe_bateau().size(); i++) {
			try {
				int x = rd.nextInt(100);
				int y = rd.nextInt(100);
				// on autorise expres des valeurs qui depassent les bornes
				int orientation = rd.nextInt(3);
				System.out.println("pose bateau "+x+" "+y+" "+orientation+" "+i);
				if (orientation == Grille.HORIZONTAL) j.getListe_bateau().get(i).setOrientation(Grille.HORIZONTAL);
				else {
					if (orientation == Grille.VERTICAL) j.getListe_bateau().get(i).setOrientation(Grille.VERTICAL);
					else {
						--i;
						continue;
					}
				}
				Case position_encrage = j.getGrille().getCase(x-1,y-1);
				g.positionnerBateau(j.getListe_bateau().get(i), position_encrage);
			} catch (ExceptionGrille e) {
				--i;
			} catch (ExceptionHorsDuTableau e) {
				--i;
			} catch (InputMismatchException e) {
				--i;
			} finally {
			}
		}
		System.out.println("tous les bateaux sont places");
	}

	@Override
	/**
	 * methode pour donner au jeu les informations necessaires (nom du joueur, taille de la grille)
	 * @return un tableau d'objet qui contient les informations du jeu (nom du joueur, taille de la grille)
	 */
	public Object[] initJeu() {
		// +8 car il faut une taille mini pour pouvoir poser tous les bateaux
		int taille_grille_x = rd.nextInt(20)+8;
		int taille_grille_y = rd.nextInt(20)+8;
		System.out.println("taille du jeu "+taille_grille_x+" "+taille_grille_y);
		String nom1 = "autojoueur";
		Object[] infos = {taille_grille_x,taille_grille_y,nom1};
		return infos;
	}

}
