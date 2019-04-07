package projet_bataille_navale.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Case;
import projet_bataille_navale.Contre_torpilleur;
import projet_bataille_navale.Croiseur;
import projet_bataille_navale.ExceptionCaseImpactee;
import projet_bataille_navale.ExceptionGrille;
import projet_bataille_navale.ExceptionHorsDuTableau;
import projet_bataille_navale.GUI;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;
import projet_bataille_navale.Porte_avion;
import projet_bataille_navale.Sauvegarde;
import projet_bataille_navale.Sous_marin;
import projet_bataille_navale.Torpilleur;
import projet_bataille_navale.test.GraphiqueTest;

public class Graphique extends JPanel implements GUI {

	public static int jfw=1000, jfh=700;

	private JPanel zoneText, zoneBoutons, middle;
	private JLabel txt;
	private GrillePanel grille;
	private Joueur joueur;
	private JTextField userinput = new JTextField();
	// utilise pour l'affichage de la liste des bateaux
	private DefaultListModel<String> batlist = new DefaultListModel<String>();
	private List<Bateau> bateauxAposer = new ArrayList<Bateau>();
	// cette grille n'est utilisee que pour l'affiche graphique, pas pour l'affichage console
	// elle sert a n'afficher que les cases attaquees, et pas les bateaux qui sont sur la Grille dans joueur et doivent etre caches
	private Grille grilleDattaque=null;
	
	// utilises seulement pendant la phase d'intitialisation
	private Joueur initjr=null;

	/**
	 * methode pour afficher un message destine au joueur
	 * @param s message a afficher
	 */
	public void afficheMessage(String s) {
		txt.setText(s);
		validate();
	}
	
	/**
	 * methode pour remplir la liste des bateaux
	 */
	private void listeBateaux() {
		batlist.clear();
		for (Bateau b : joueur.getListe_bateau()) {
			batlist.addElement(b.toString());
		}
		middle.validate();
	}
	
	/**
	* methode pour initialiser le plateau pour un joueur
	* @param j joueur pour lequel on initialise le plateau
	*/
	public void setJoueur(Joueur j) {
		this.joueur=j;
		
		removeAll();
		setLayout(new BorderLayout(10,5));

		zoneText = new JPanel(new BorderLayout(10,5));
		add(zoneText, BorderLayout.NORTH);
		JLabel nom = new JLabel(joueur.getNom());
		nom.setBorder(LineBorder.createBlackLineBorder());
		zoneText.add(nom, BorderLayout.WEST);
		zoneText.add(userinput, BorderLayout.CENTER);
		txt = new JLabel("Posez vos bateaux                ");
		txt.setBackground(Color.green);
		txt.setOpaque(true);
		zoneText.add(txt, BorderLayout.EAST);

		middle = new JPanel(new BorderLayout(10,5));
		grille = new GrillePanel();
		grille.setGrille(j.getGrille());
		middle.add(grille, BorderLayout.CENTER);
		JList<String> liste = new JList<String>(batlist);
		liste.setFixedCellWidth(180);
		listeBateaux();
		middle.add(liste, BorderLayout.EAST);
		add(middle, BorderLayout.CENTER);

		zoneBoutons = new JPanel(new FlowLayout());
		add(zoneBoutons, BorderLayout.SOUTH);
		JButton save = new JButton("save");
		zoneBoutons.add(save);
		JButton load = new JButton("load");
		zoneBoutons.add(load);
		JButton listeTaille = new JButton("liste taille");
		zoneBoutons.add(listeTaille);
		JButton listeImpact = new JButton("liste impact");
		zoneBoutons.add(listeImpact);

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sauvegarde s = new Sauvegarde(joueur, "grph.sauv");
				s.sauve(grilleDattaque);
			}
		});
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sauvegarde s = new Sauvegarde(joueur, "grph.sauv");
				joueur=s.charge();
				grille.setGrille(joueur.getGrille());
				if (s.grilleDattaque!=null) {
					// on est dans le mode "jouer"
					grilleDattaque=s.grilleDattaque;
					jouer(joueur.getGrille(),joueur);
				} else {
					// on est dans le mode "pose des bateaux"
					grilleDattaque=null;
					positionnerBateau(joueur.getGrille(), joueur);
				}
			}
		});
		listeTaille.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				joueur.afficherListe_bateauParTaille();
				listeBateaux();
			}
		});
		listeImpact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				joueur.afficherListe_bateauParImpact();
				listeBateaux();
			}
		});
		validate();
	}
	
	/**
	 * contructeur de l'interface graphique
	 */
	public Graphique() {
		super();

		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Joueur j=new Joueur("personne", new Grille(10, 8), new ArrayList<Bateau>());
		setJoueur(j);
	}

	@Override
	/**
	 * methode pour jouer
	 * @param g grille avec laquelle on joue
	 * @param j joueur qui joue
	 */
	public void jouer(Grille g, Joueur j) {
		Grille grilleBateaux = j.getGrille();
		if (grilleDattaque==null) grilleDattaque = new Grille(grilleBateaux.getTailleX(),grilleBateaux.getTailleY());
		grille.setGrille(grilleDattaque);
		afficheMessage("cliquez sur la case a attaquer");
		grille.setReaction(new GrilleAction() {
			@Override
			public void rotate(Case c) {}

			@Override
			public void moveSurGrille(Case c) {}

			@Override
			public void clicSurGrille(Case c) {
				int x = c.getX();
				int y = c.getY();
				try {
					boolean touche = j.lancerTir(grilleBateaux.getCase(x, y), grilleDattaque.getCase(x, y));
					if (touche) {
						if (j.aPerdu()) {
							afficheMessage("Tous les bateaux sont coules");
							grille.setReaction(null);
						} else {
							afficheMessage("Touche ! Continuez a tirer...");
						}
					} else {
						afficheMessage("Plouf dans l'eau. Continuez a tirer...");
					}
				} catch (ExceptionCaseImpactee e) {
					e.printStackTrace();
				} catch (ExceptionHorsDuTableau e) {
					e.printStackTrace();
				}
				grille.repaint();
			}
		});
	}

	@Override
	/**
	 * methode pour positionner un bateau sur la grille
	 * @param g grille sur laquelle on veut positionner le bateau
	 * @param j joueur qui positionne le bateau
	 */
	public void positionnerBateau(Grille g, Joueur j) {
		// dans cette phase de pose des bateaux, on ne doit pas avoir de grille de tirs
		grilleDattaque=null;
		setJoueur(j);
		bateauxAposer.clear();
		bateauxAposer.addAll(j.getListe_bateau());
		{
			Bateau b = bateauxAposer.get(0);
			afficheMessage("posez le "+b.toString());
		}
		grille.setReaction(new GrilleAction() {
			@Override
			/**
			 * methode pour changer l'orientation du bateau en cours de pose
			 * @param c case sur laquelle se trouve la souris
			 */
			public void rotate(Case c) {
				Bateau b = bateauxAposer.get(0);
				if (b!=null && c!=null) {
					int ori0 = b.getOrientation();
					if (ori0==Grille.HORIZONTAL) b.setOrientation(Grille.VERTICAL);
					else b.setOrientation(Grille.HORIZONTAL);
					try {
						boolean ok = g.testPositionnerBateau(b, c);
						if (ok) {
							moveSurGrille(c);
						} else {
							// rotate seulement si c'est possible
							b.setOrientation(ori0);
						}
					} catch (Exception e) {
						b.setOrientation(ori0);
					}
				}
			}

			@Override
			/**
			 * methode pour afficher le bateau en transparence sur la grille avant de le poser
			 * @param c case sur laquelle se trouve la souris
			 */
			public void moveSurGrille(Case c) {
				grille.clearCasesFantomes();
				try {
					Bateau b = bateauxAposer.get(0);
					if (b!=null && c!=null) {
						boolean ok = g.testPositionnerBateau(b, c);
						if (ok) {
							int longueur = b.getLongueur();
							if (b.getOrientation() == Grille.HORIZONTAL) {
								for (int i = 0; i < longueur; i++) {
									grille.addCasesFantomes(c.getX(),c.getY()+i);
								}
							} else if (b.getOrientation() == Grille.VERTICAL) {
								for (int i = 0; i < longueur; i++) {
									grille.addCasesFantomes(c.getX()+i,c.getY());
								}
							}
						}
					}
				} catch (ExceptionHorsDuTableau e) {
				} catch (ExceptionGrille e) {
				}
				grille.repaint();
			}

			@Override
			/**
			 * methode pour poser le bateau une fois qu'on a clique
			 * @param c case sur laquelle se trouve la souris
			 */
			public void clicSurGrille(Case c) {
				try {
					Bateau b = bateauxAposer.get(0);
					boolean ok = g.positionnerBateau(b, c);
					if (ok) {
						bateauxAposer.remove(0);
						if (bateauxAposer.size()>0) {
							b = bateauxAposer.get(0);
							afficheMessage("posez le "+b.toString());
						} else {
							grille.clearCasesFantomes();
							grille.setReaction(null);
							// attaquer directement
							afficheMessage("");
							jouer(g, j);
						}
					}
				} catch (ExceptionHorsDuTableau e) {
					e.printStackTrace();
				} catch (ExceptionGrille e) {
					e.printStackTrace();
				}
				grille.repaint();
			}
		});
	}

	@Override
	/**
	 * methode pour donner au jeu les informations necessaires (nom du joueur, taille de la grille)
	 * @return un tableau d'objet qui contient les informations du jeu (nom du joueur, taille de la grille)
	 */
	public Object[] initJeu() {
		// dans cette phase de pose des bateaux, on ne doit pas avoir de grille de tirs
		grilleDattaque=null;
		int taille_grille_x = 10;
		int taille_grille_y = 10;
		String nom1 = "joueur1";
		Object[] infos = {taille_grille_x,taille_grille_y,nom1};
		
		afficheMessage("Tailles X et Y du plateau ?");
		userinput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = userinput.getText();
				String[] st = s.split(" ");
				if (st.length==2) {
					try {
						int x = Integer.parseInt(st[0]);
						int y = Integer.parseInt(st[1]);
						Grille grille_1 = new Grille(x,y);
						Joueur joueur_1 = new Joueur(initjr.getNom(), grille_1, initjr.getListe_bateau());
						initjr = joueur_1;
						
						// 2eme phase: poser les bateaux
						afficheMessage("Vous pouvez positionner les bateaux");
						positionnerBateau(grille_1, joueur_1);
					} catch (Exception e1) {
						afficheMessage("Tailles X et Y du plateau ? (Entrez 2 entiers separes par un espace !)");
					}
				}
			}
		});

		return infos;
	}

	/**
	 * metode pour afficher la fenetre graphique
	 */
	public static void jeuGraphique() {
		JFrame jf = new JFrame("Bataille navale");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Graphique g = new Graphique();
		jf.setContentPane(g);
		jfw=1000; jfh=700;
		jf.setSize(jfw, jfh);
		jf.setVisible(true);
		
		// test temporaire
		ArrayList<Bateau> liste_bateau = new ArrayList<Bateau>();
		liste_bateau.add(new Porte_avion(Grille.HORIZONTAL));
		liste_bateau.add(new Croiseur(Grille.HORIZONTAL));
		liste_bateau.add(new Contre_torpilleur(Grille.HORIZONTAL));
		liste_bateau.add(new Sous_marin(Grille.HORIZONTAL));
		liste_bateau.add(new Torpilleur(Grille.HORIZONTAL));

		Object[] infos = g.initJeu();
		Grille grille_1 = new Grille((int)infos[0], (int)infos[1]);
		Joueur joueur_1 = new Joueur((String)infos[2], grille_1, liste_bateau);

		g.initjr = joueur_1;
	}
}
