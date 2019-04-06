package projet_bataille_navale.test;

import java.util.ArrayList;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.ConsoleAuto;
import projet_bataille_navale.Contre_torpilleur;
import projet_bataille_navale.Croiseur;
import projet_bataille_navale.GUI;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;
import projet_bataille_navale.Porte_avion;
import projet_bataille_navale.Sous_marin;
import projet_bataille_navale.Torpilleur;

public class JeuTest {
	public static void main(String[] args) {
		//Initialisation des variables.
		Grille grille_1;
		Joueur joueur_1;

		GUI gui = new ConsoleAuto();

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
