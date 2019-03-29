package projet_bataille_navale;

public interface GUI {
	public void jouer(Grille g, Joueur j);
	public void positionnerBateau(Grille g, Joueur j);
	public Object[] initJeu();
}
