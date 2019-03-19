package projet_bataille_navale;

public class Case {

	private int posX, posY;
	private boolean estTouchee;
	private Bateau bateauDessus = null;

	public Case(int px, int py) {
		this.posX = px;
		this.posY = py;
		this.estTouchee = false;
	}

	public boolean poseBateau(Bateau b) {
		if (bateauDessus != null) {
			return false;
		}
		else {
			bateauDessus = b;
			return true;
		}
	}
}
