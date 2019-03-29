package projet_bataille_navale;

import java.util.*;

public class Jeu {
	public static void main(String[] args) {
		///////////////////////////////////
		///////////MONO-JOUEUR/////////////
		///////////////////////////////////
		
		//Initialisation des variables.
		Grille grille_1;
		Joueur joueur_1;
		Case position_encrage;
		int taille_grille_x, taille_grille_y, x, y, orientation;
		
		Console gui = new Console();
		
		//Remplissage liste de bateau type.
		ArrayList<Bateau> liste_bateau = new ArrayList<Bateau>();
		liste_bateau.add(new Porte_avion(0));
		/*
		liste_bateau.add(new Croiseur(0));
		liste_bateau.add(new Contre_torpilleur(0));
		liste_bateau.add(new Sous_marin(0));
		liste_bateau.add(new Torpilleur(0));
		*/
		
		//Debut de l'affichage, on demande � l'utilisateurs les informations requises
		Object[] infos = gui.initJeu();
		grille_1 = new Grille((int)infos[0], (int)infos[1]);
		joueur_1 = new Joueur((String)infos[2], grille_1, liste_bateau);
		
		gui.positionnerBateau(grille_1, joueur_1);
		
		gui.jouer(grille_1, joueur_1);
	}

}
