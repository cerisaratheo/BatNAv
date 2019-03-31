package projet_bataille_navale.graphic;

import java.awt.BorderLayout;
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
	
	public void afficheMessage(String s) {
		txt.setText(s);
		validate();
	}
	
	private void listeBateaux() {
		batlist.clear();
		for (Bateau b : joueur.getListe_bateau()) {
			batlist.addElement(b.toShortString());
		}
		middle.validate();
	}
	
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
		txt = new JLabel("Posez vos bateaux");
		zoneText.add(txt, BorderLayout.EAST);

		middle = new JPanel(new BorderLayout(10,5));
		grille = new GrillePanel(joueur.getGrille());
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
				s.sauve();
			}
		});
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sauvegarde s = new Sauvegarde(joueur, "grph.sauv");
				joueur=s.charge();
				grille.setGrille(joueur.getGrille());
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
	
	public Graphique() {
		super();

		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Joueur j=new Joueur("personne", new Grille(10, 8), new ArrayList<Bateau>());
		setJoueur(j);
	}

	@Override
	public void jouer(Grille g, Joueur j) {
		Grille grilleBateaux = j.getGrille();
		Grille grilleDattaque = new Grille(grilleBateaux.getTailleX(),grilleBateaux.getTailleY());
		grille.setGrille(grilleDattaque);
		afficheMessage("cliquez sur la case a attaquer");
		grille.setReaction(new GrilleAction() {
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
	public void positionnerBateau(Grille g, Joueur j) {
		setJoueur(j);
		bateauxAposer.clear();
		bateauxAposer.addAll(j.getListe_bateau());
		{
			Bateau b = bateauxAposer.get(0);
			afficheMessage("posez le "+b.toShortString());
		}
		grille.setReaction(new GrilleAction() {
			@Override
			public void clicSurGrille(Case c) {
				try {
					Bateau b = bateauxAposer.get(0);
					boolean ok = g.positionnerBateau(b, c);
					System.out.println("clic "+c.getX()+" "+c.getY()+" "+ok+" "+c.etreOccupee());
					if (ok) {
						bateauxAposer.remove(0);
						if (bateauxAposer.size()>0) {
							b = bateauxAposer.get(0);
							afficheMessage("posez le "+b.toShortString());
						} else {
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
	public Object[] initJeu() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
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

		GraphiqueTest gui = new GraphiqueTest();
		Object[] infos = gui.initJeu();
		Grille grille_1 = new Grille((int)infos[0], (int)infos[1]);
		Joueur joueur_1 = new Joueur((String)infos[2], grille_1, liste_bateau);

		g.positionnerBateau(grille_1, joueur_1);

	}
}
