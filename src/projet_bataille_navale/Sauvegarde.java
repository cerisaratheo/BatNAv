package projet_bataille_navale;

import java.io.*;

/**
 * Class permettant de sauvegarder un objet
 */
public class Sauvegarde {

	private Joueur joueur;
	private String nomFichier;
	public Grille grilleDattaque=null;

	/**
	 * Constructeur d'une sauvegarde
	 * @param p_joueur joueur a sauvegarder
	 * @param p_nomFichier nom du fichier dans lequel on sauvegarde
	 */
	public Sauvegarde(Joueur p_joueur, String p_nomFichier) {
		this.joueur = p_joueur;
		this.nomFichier = p_nomFichier;
	}

	/**
	 * Methode permettant de sauvegarder un objet
	 */
	public void sauve() {
		sauve(null);
	}
	public void sauve(Grille g) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier));
			if (g==null) oos.writeInt(0); // mode sans grille
			else oos.writeInt(1); // mode avec grille
			oos.writeObject(joueur);
			if (g!=null) oos.writeObject(g);
			oos.close();
		} catch (IOException e) {
			System.out.println("erreur d'E/S");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("erreur hors E/S");
			e.printStackTrace();
		}
		System.out.println("fichier sauvegarde dans "+(new File(nomFichier)).getAbsolutePath());
	}

	/**
	 * Methode permettant de charger un objet
	 */
	public Joueur charge() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier));
			int m = ois.readInt();
			joueur = (Joueur)(ois.readObject());
			if (m==0) grilleDattaque=null;
			else grilleDattaque = (Grille)(ois.readObject());
			ois.close();
			return joueur;
		} catch (IOException e) {
			System.out.println("erreur d'E/S");
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println("erreur hors E/S");
			e.printStackTrace();
			return null;
		}
	}
}
