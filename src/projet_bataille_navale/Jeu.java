package projet_bataille_navale;

import java.util.*;

public class Jeu {
	public static void main(String[] args) {
		///////////////////////////////////
		///////////MONO-JOUEUR/////////////
		///////////////////////////////////
		
		//Initialisation des variables.
		Scanner sc = new Scanner(System.in);
		Grille grille_1, grille_2;
		Joueur joueur_1, joueur_2;
		Case position_encrage;
		int taille_grille_x, taille_grille_y, x, y, orientation;
		
		//Remplissage liste de bateau type.
		ArrayList<Bateau> liste_bateau = new ArrayList<Bateau>();
		liste_bateau.add(new Porte_avion(0));
		liste_bateau.add(new Croiseur(0));
		liste_bateau.add(new Contre_torpilleur(0));
		liste_bateau.add(new Sous_marin(0));
		liste_bateau.add(new Torpilleur(0));
		
		//Debut de l'affichage, on demande � l'utilisateurs les informations requises
		System.out.println("\nBataille navale.\nMode de jeu : Mono-Joueur");
		System.out.println("Choix de le taille de la grille ,\nx : \n");	
		taille_grille_x = sc.nextInt();
		taille_grille_x += 1;
		System.out.println("y : \n");	
		taille_grille_y = sc.nextInt();
		taille_grille_y += 1;
		
		grille_1 = new Grille(taille_grille_x, taille_grille_y);
		grille_2 = new Grille(taille_grille_x, taille_grille_y);
		
		System.out.println("Choix du nom de joueur 1 :\n");
		String nom1 = sc.next();
		joueur_1 = new Joueur(nom1, grille_1, liste_bateau);
		
		//Stats joueur_1
		System.out.println("\nJoueur 1, " + nom1 + " :\n");
		joueur_1.afficherListe_bateau();
		grille_1.afficherGrille();
		System.out.println("\nPlacement des bâteaux sur la grille :\n");
		
		//placement des bâteaux : joueur_1
		for (int i=0; i<joueur_1.getListe_bateau().size(); i++) {
			try {
				System.out.println("\nBateau de type : " + joueur_1.getListe_bateau().get(i).getNom() + " de longueur : " + joueur_1.getListe_bateau().get(i).getLongueur());
				System.out.println("\nEntrer position x : ");
				x = sc.nextInt();
				System.out.println("Entrer position y : ");
				y = sc.nextInt();
				System.out.println("Entrer orientation du bateau : (1 = horizontal, 2 = vertical)");
				orientation = sc.nextInt();
				joueur_1.getListe_bateau().get(i).setOrientation(orientation);
				position_encrage = joueur_1.getGrille().getCase(y, x);
				grille_1.positionnerBateau(joueur_1.getListe_bateau().get(i), position_encrage);
				grille_1.afficherGrille();
			} catch (ExceptionGrille e) {
				//trouver moyen de relancer le placement du bateau qui a été sauté.
				System.out.println("Un bâteau se trouve déjà à cet endroit. Ou le point d'encrage n'est pas correct.\n");
			} catch (ExceptionHorsDuTableau e) {
				System.out.println("Coordonnées spécifiées se trouvent hors de la grille.");
			} catch (InputMismatchException e) {
				System.out.println("Entrer des valeurs numériques svp.");
			}
		}
		
		//Iteration de jeu
		while (true) {
			try {
				boolean etat1;
				String continuer;
				
				//attaque joueur 1
				System.out.println("\nJoueur 1. Effectuez un tir : entrer les coordonnees de la case visee : ");
				System.out.println("\nEntrer position x : ");
				x = sc.nextInt();
				System.out.println("Entrer position y : ");
				y = sc.nextInt();
				etat1 = joueur_1.lancerTir(joueur_1.getGrille().getCase(x, y));
				if (etat1 == true) {
					System.out.println("Le tir a touche");
					if (joueur_1.aPerdu() == true) {
						System.out.println("Le joueur 2 : "+joueur_1.getNom()+" a gagne la partie suite a ce tir.");
					}
				} else {
					System.out.println("Tir manque");
				}
				System.out.println("Voici VOTRE grille : ");
				joueur_1.getGrille().afficherGrille();
				System.out.println("Afficher votre liste de bateau ? (oui/non)");
				continuer = sc.next();
				if (continuer.equals("oui")) {
					joueur_1.afficherListe_bateau();
				}
				System.out.println("Voulez-vous continuer de jouer ? (oui/non)");
				continuer = sc.next();
				if (continuer.equals("non")) {
					break;
				}
				
			} catch (ExceptionCaseImpactee e) {
				System.out.println("La case spécifiée a déjà été touchée par un tir, vous passez votre tour.");
			} catch (ExceptionHorsDuTableau e) {
				System.out.println("La case spécifiée se trouve hors de la grille.");
			}
		}
		System.out.println("Arrêt de jeu.");
		sc.close();
	}

}
