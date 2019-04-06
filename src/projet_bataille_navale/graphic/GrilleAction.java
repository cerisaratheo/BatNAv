package projet_bataille_navale.graphic;

import projet_bataille_navale.Case;

public interface GrilleAction {
	public void rotate(Case c);
	public void clicSurGrille(Case c);
	public void moveSurGrille(Case c);
}
