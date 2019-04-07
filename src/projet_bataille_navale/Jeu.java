package projet_bataille_navale;

import java.util.*;

public class Jeu {
	public static void jeuConsole() {
		///////////////////////////////////
		///////////MONO-JOUEUR/////////////
		///////////////////////////////////

		//Initialisation des variables.
		Grille grille_1;
		Joueur joueur_1;

		GUI gui = new Console();

		//Remplissage liste de bateau type.
		ArrayList<Bateau> liste_bateau = new ArrayList<Bateau>();
		liste_bateau.add(new Porte_avion(0));
		liste_bateau.add(new Croiseur(0));
		liste_bateau.add(new Contre_torpilleur(0));
		liste_bateau.add(new Sous_marin(0));
		liste_bateau.add(new Torpilleur(0));

		//Debut de l'affichage, on demande a l'utilisateurs les informations requises
		Object[] infos = gui.initJeu();
		grille_1 = new Grille((int)infos[0], (int)infos[1]);
		joueur_1 = new Joueur((String)infos[2], grille_1, liste_bateau);

		gui.positionnerBateau(grille_1, joueur_1);

		gui.jouer(grille_1, joueur_1);
	}

}
