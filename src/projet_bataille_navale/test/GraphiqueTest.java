package projet_bataille_navale.test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

import javax.swing.JFrame;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Case;
import projet_bataille_navale.Contre_torpilleur;
import projet_bataille_navale.Croiseur;
import projet_bataille_navale.ExceptionCaseImpactee;
import projet_bataille_navale.ExceptionGrille;
import projet_bataille_navale.ExceptionHorsDuTableau;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;
import projet_bataille_navale.Porte_avion;
import projet_bataille_navale.Sous_marin;
import projet_bataille_navale.Torpilleur;
import projet_bataille_navale.graphic.Graphique;

public class GraphiqueTest extends Graphique {

	private Random rd = new Random();

	public GraphiqueTest() {
		super();
	}
	
	@Override
	public void jouer(Grille g, Joueur j) {
		Grille gtirs = new Grille(g.getTailleX(), g.getTailleY());
		//Iteration de jeu
		// pour arreter quand le jeu est fini:
		// while (!j.aPerdu()) {
		// pour arreter quand on a tire sur le tiers du plateau:
		int i;
		for (i=0;i<g.getTailleX()*g.getTailleY()/3;i++) {
			try {
				//attaque joueur 1
				int x = rd.nextInt(g.getTailleX())-1;
				int y = rd.nextInt(g.getTailleY())-1;
				System.out.println("tir "+x+" "+y);
				boolean touche = j.lancerTir(j.getGrille().getCase(x, y), gtirs.getCase(x, y));
				if (touche) {
					System.out.println("coup: "+x+" "+y+" touche: "+touche);
					j.afficherListe_bateauParTaille();
					j.afficherListe_bateauParImpact();
				}
			} catch (ExceptionCaseImpactee e) {
			} catch (ExceptionHorsDuTableau e) {
			}
			this.repaint();
		}
		System.out.println("Arret de jeu. "+i);
	}

	@Override
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
		setJoueur(j);
	}

	@Override
	public Object[] initJeu() {
		// +8 car il faut une taille mini pour pouvoir poser tous les bateaux
		int taille_grille_x = rd.nextInt(20)+8;
		int taille_grille_y = rd.nextInt(20)+8;
		System.out.println("taille du jeu "+taille_grille_x+" "+taille_grille_y);
		String nom1 = "autojoueur";
		Object[] infos = {taille_grille_x,taille_grille_y,nom1};
		return infos;
	}

	public static void main(String[] args) {
		//Initialisation des variables.
		Grille grille_1;
		Joueur joueur_1;

		JFrame jf = new JFrame("Bataille navale");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GraphiqueTest gui = new GraphiqueTest();

		jfw=800; jfh=500;
		jf.setSize(jfw, jfh);
		jf.setContentPane(gui);
		//jf.pack();
		jf.setVisible(true);

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
