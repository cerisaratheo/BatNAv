package projet_bataille_navale;

import java.util.*;

public abstract class Bateau {

	public abstract String toString();
	protected int taille;
	private ArrayList<Case> estSur = new ArrayList<Case>();
	
	public int getTaille() {
		return taille;
	}
}

