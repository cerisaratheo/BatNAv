package projet_bataille_navale;

import java.util.*;

public class Bateau {
	
	private String nomType;
	private int taille;
	private boolean estTouche;
	
	public boolean isEstTouche() {
		return estTouche;
	}

	public void setEstTouché(boolean estTouche) {
		this.estTouche = estTouche;
	}

	public Bateau(String n) {
		this.nomType=n;
		switch (n) {
		case "porte-avion":
			this.nomType=n;
			this.taille=5;
			break;
		case "croiseur":
			this.nomType=n;
			this.taille=4;
			break;
		case "contre-torpilleur":
			this.nomType=n;
			this.taille=3;
			break;
		case "sous-marin":
			this.nomType=n;
			this.taille=3;
			break;
		case "torpilleur":
			this.nomType=n;
			this.taille=2;
			break;
		}
	}
}
