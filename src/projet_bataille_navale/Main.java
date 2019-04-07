package projet_bataille_navale;

import java.util.Scanner;

import projet_bataille_navale.graphic.Graphique;
import projet_bataille_navale.test.GraphiqueTest;
import projet_bataille_navale.test.JeuTest;

public class Main {

	public static void main(String[] args) {
		System.out.println("Choix du mode de jeu:");
		System.out.println("1- mode console");
		System.out.println("2- mode console automatique");
		System.out.println("3- mode graphique");
		System.out.println("4- mode graphique automatique");
		
		Scanner sc = new Scanner(System.in);
		boolean ok=false;
		while (!ok) {
			int r = sc.nextInt();
			ok=true;
			switch (r) {
			case 1: 
				Jeu.jeuConsole();
				break;
			case 2: 
				JeuTest.jeuConsoleAuto();
				break;
			case 3:
				Graphique.jeuGraphique();
				break;
			case 4:
				GraphiqueTest.jeuGraphiqueAuto();
				break;
			default:
				System.out.println("Veuillez entrer un entier entre 1 et 4");
				ok=false;
			}
		}
		sc.close();
	}

}
