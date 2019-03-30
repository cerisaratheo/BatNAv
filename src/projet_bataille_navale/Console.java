package projet_bataille_navale;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console implements GUI {

	private Scanner sc = new Scanner(System.in);

	@Override
	/**
	 * @param g contient une grille avec des bateaux poses  
	 */
	public void jouer(Grille g, Joueur j) {
		// on cree une grille vide pour les tirs
		Grille gtirs = new Grille(g.getTailleX(), g.getTailleY());
		//Iteration de jeu
		while (!j.aPerdu()) {
			try {
				boolean etat1;
				String continuer;

				// pour cacher les bateaux poses
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println("Voici VOTRE grille : ");
				gtirs.afficherGrille();

				//attaque joueur 1
				System.out.println("\nJoueur 1. Effectuez un tir : entrer les coordonnees de la case visee : ");
				System.out.println("\nEntrer position x : ");
				int x = sc.nextInt()-1;
				System.out.println("Entrer position y : ");
				int y = sc.nextInt()-1;
				etat1 = j.lancerTir(j.getGrille().getCase(x, y), gtirs.getCase(x, y));
				if (etat1 == true) {
					System.out.println("Le tir a touche");
					if (j.aPerdu() == true) {
						System.out.println("Le joueur 2 : "+j.getNom()+" a gagne la partie suite a ce tir.");
					}
				} else {
					System.out.println("Tir manque");
				}
				System.out.println("Afficher votre liste de bateau ? (1: tri par taille; 2: tri par % impact; 3: rien)");
				continuer = sc.next();
				switch(continuer) {
				case "1": 
					j.afficherListe_bateauParTaille(); 
					break;
				case "2": 
					j.afficherListe_bateauParImpact(); 
					break;
				default: 
					break;
				}
				if (continuer.equals("oui")) {
					j.afficherListe_bateau();
				}
				System.out.println("Voulez-vous continuer de jouer ? (oui/non)");
				continuer = sc.next();
				if (continuer.equals("non")) {
					break;
				}

			} catch (ExceptionCaseImpactee e) {
				System.out.println("La case specifiee a deja ete touchee par un tir, vous passez votre tour.");
			} catch (ExceptionHorsDuTableau e) {
				System.out.println("La case specifiee se trouve hors de la grille.");
			}
		}
		System.out.println("Arret de jeu.");
		sc.close();
	}

	@Override
	public void positionnerBateau(Grille g, Joueur j) {
		//Stats joueur_1
		System.out.println("\nJoueur 1, " + j.getNom() + " :\n");
		j.afficherListe_bateau();
		g.afficherGrille();
		System.out.println("\nPlacement des bateaux sur la grille :\n");
		//placement des bateaux : joueur_1
		for (int i=0; i<j.getListe_bateau().size(); i++) {
			try {
				System.out.println("\nBateau de type : " + j.getListe_bateau().get(i).getNom() + " de longueur : " + j.getListe_bateau().get(i).getLongueur());
				System.out.println("\nEntrer position x : ");
				int x = sc.nextInt();
				System.out.println("Entrer position y : ");
				int y = sc.nextInt();
				System.out.println("Entrer orientation du bateau : ("+Grille.HORIZONTAL+" = horizontal, "+Grille.VERTICAL+" = vertical)");
				int orientation = sc.nextInt();
				if (orientation == Grille.HORIZONTAL) {
					j.getListe_bateau().get(i).setOrientation(Grille.HORIZONTAL);
				}
				else {
					if (orientation == Grille.VERTICAL) {
						j.getListe_bateau().get(i).setOrientation(Grille.VERTICAL);
					}
					else {
						System.out.println("Mauvaise orientation, recommencez.");
						--i;
						g.afficherGrille();
						continue;
					}
				}
				Case position_encrage = j.getGrille().getCase(x-1,y-1);
				g.positionnerBateau(j.getListe_bateau().get(i), position_encrage);
			} catch (ExceptionGrille e) {
				System.out.println("Un bateau se trouve deja a cet endroit. Ou le point d'encrage n'est pas correct.\n");
				--i;
			} catch (ExceptionHorsDuTableau e) {
				System.out.println("Coordonnees specifiees se trouvent hors de la grille.");
				--i;
			} catch (InputMismatchException e) {
				System.out.println("Entrer des valeurs numeriques svp.");
				--i;
			} finally {
				g.afficherGrille();
			}
		}
	}

	@Override
	public Object[] initJeu() {
		System.out.println("\nBataille navale.\nMode de jeu : Mono-Joueur");
		System.out.println("Choix de le taille de la grille ,\nx : \n");	
		int taille_grille_x = sc.nextInt();
		System.out.println("y : \n");	
		int taille_grille_y = sc.nextInt();
		System.out.println("Choix du nom de joueur 1 :\n");
		String nom1 = sc.next();
		Object[] infos = {taille_grille_x,taille_grille_y,nom1};
		return infos;
	}

}
