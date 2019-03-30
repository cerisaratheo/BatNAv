package projet_bataille_navale.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Croiseur;
import projet_bataille_navale.ExceptionGrille;
import projet_bataille_navale.ExceptionHorsDuTableau;
import projet_bataille_navale.GUI;
import projet_bataille_navale.Grille;
import projet_bataille_navale.Joueur;
import projet_bataille_navale.Sauvegarde;

public class Graphique extends JPanel implements GUI {

	public static int jfw=800, jfh=500;

	private JPanel zoneText, zoneBoutons;
	private GrillePanel grille;
	private Joueur joueur;
	private JTextField userinput = new JTextField(80);

	public Graphique() {
		super();

		this.joueur=new Joueur("personne", new Grille(10, 8), new ArrayList<Bateau>());
		try {
			this.joueur.getGrille().positionnerBateau(new Croiseur(1), joueur.getGrille().getCase(2, 1));
			System.out.println("hhhh "+joueur.getGrille().getCase(2, 1));
		} catch (ExceptionHorsDuTableau e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExceptionGrille e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLayout(new BorderLayout());

		zoneText = new JPanel(new FlowLayout());
		add(zoneText, BorderLayout.NORTH);
		JLabel nom = new JLabel(joueur.getNom());
		nom.setBorder(LineBorder.createBlackLineBorder());
		zoneText.add(nom);
		JLabel txt = new JLabel("Posez vos bateaux");
		zoneText.add(txt);
		zoneText.add(userinput);

		JPanel middle = new JPanel(new FlowLayout());
		add(middle, BorderLayout.CENTER);
		grille = new GrillePanel(joueur.getGrille());
		grille.setPreferredSize(new Dimension((int)((float)jfw*0.6), (int)((float)jfh*0.8)));
		middle.add(grille);
		JTextArea liste = new JTextArea();
		middle.add(liste);

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
				String l = "";
				for (Bateau b : joueur.getListe_bateau()) {
					l+=b.toString()+"\n";
				}
				liste.setText(l);
				repaint();
			}
		});
		listeImpact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				joueur.afficherListe_bateauParImpact();
				String l = "";
				for (Bateau b : joueur.getListe_bateau()) {
					l+=b.toString()+"\n";
				}
				liste.setText(l);
				repaint();
			}
		});

	}

	@Override
	public void jouer(Grille g, Joueur j) {
		// TODO Auto-generated method stub

	}

	@Override
	public void positionnerBateau(Grille g, Joueur j) {
		// TODO Auto-generated method stub

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
		jfw=800; jfh=500;
		jf.setSize(jfw, jfh);
		jf.setVisible(true);
	}
}
