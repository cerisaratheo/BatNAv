package projet_bataille_navale;

import java.io.*;

/**
 * Class permettant de sauvegarder un objet
 */
public class Sauvegarde {

	private Joueur joueur;
	private String nomFichier;

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
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier));
			oos.writeObject(joueur);
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
			joueur = (Joueur)(ois.readObject());
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
