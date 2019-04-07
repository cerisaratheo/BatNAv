package projet_bataille_navale.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Case;
import projet_bataille_navale.ExceptionHorsDuTableau;
import projet_bataille_navale.Grille;

public class GrillePanel extends JPanel {

	private Grille g;
	private HashMap<Bateau, Color> bat2col = new HashMap<Bateau, Color>();
	private GrilleAction reaction = null;
	private ArrayList<int[]> casesFantomes = new ArrayList<int[]>();

	/**
	 * methode pour effacer les cases affichee en transparence avant de poser le bateau
	 */
	public void clearCasesFantomes() {
		casesFantomes.clear();
	}

	/**
	 * methode pour ajouter les cases affichee en transparence avant de poser le bateau
	 * @param x,y coordonnées des cases en transparence
	 */
	public void addCasesFantomes(int x, int y) {
		int[] cc = {x,y};
		casesFantomes.add(cc);
	}

	/**
	 * methode pour définir comment le systeme réagit aux actions de la souris sur la grille
	 * @param a objet qui implémente les actions a réaliser lorsque la souris bouge ou clique
	 */
	public void setReaction(GrilleAction a) {
		this.requestFocusInWindow();
		reaction = a;
	}
	
	/**
	 * constructeur 
	 */
	public GrillePanel() {
		super();
	
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int sx = e.getX();
				int sy = e.getY();
				Case c = getCaseOnClick(sx, sy);
				if (reaction!=null) reaction.moveSurGrille(c);
			}
		});	
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int sx = e.getX();
				int sy = e.getY();
				Case c = getCaseOnClick(sx, sy);
				if (c!=null) {
					if (SwingUtilities.isRightMouseButton(e)) {
						if (reaction!=null) reaction.rotate(c);
					} else if (SwingUtilities.isLeftMouseButton(e)) {
						if (reaction!=null) reaction.clicSurGrille(c);
					}
				}
			}
		});
	}

	/**
	 * methode pour définir la grille a afficher
	 * @param g grille a afficher
	 */
	public void setGrille(Grille g) {
		this.g=g;
		repaint();
	}

	/**
	 * methode pour recuperer la case sur laquelle on clique
	 * @param x,y coordonnées de la souris au moment du clic
	 * @return la case sur laquelle on veut cliquer
	 */
	private Case getCaseOnClick(int x, int y) {
		if (g==null) return null;
		int w = getWidth();
		int h = getHeight();
		int caseLargeurX = h/g.getTailleX();
		int caseLargeurY = w/g.getTailleY();
		int caseX = y / caseLargeurX;
		int caseY = x / caseLargeurY;
		try {
			return g.getCase(caseX, caseY);
		} catch (ExceptionHorsDuTableau e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * methode pour convertir les coordonees d'une case en coordonnees d'ecran
	 * @param cx ligne de la case
	 * @param cy colonne de la case
	 * @return un tableau d'entier qui contient les coordonnees du coin de la case et la hauteur et largeur de la case en pixels
	 */
	private int[] getXY(int cx, int cy) {
		int w = getWidth();
		int h = getHeight();
		int xsc = h/g.getTailleX();
		int y = xsc*cx;
		int y2 = xsc;
		int ysc = w/g.getTailleY();
		int x = ysc*cy;
		int x2 = ysc;
		int[] res = {x,y,x2,y2}; 
		return res;
	}

	/**
	 * methode pour afficher les elements de la fenetre
	 * @param gr objet Graphics provenant de awt
	 */
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		int w = getWidth();
		int h = getHeight();
		gr.setColor(Color.LIGHT_GRAY);
		gr.fillRect(0, 0, w, h);
		gr.setColor(Color.black);

		// affichage de la grille
		int xsc = h/g.getTailleX();
		for (int i=0;i<g.getTailleX();i++) {
			int y = xsc*i;
			gr.drawLine(0, y, w, y);
		}

		int ysc = w/g.getTailleY();
		for (int i=0;i<g.getTailleY();i++) {
			int x = ysc*i;
			gr.drawLine(x, 0, x, h);
		}

		// affichage des bateaux
		Random rd = new Random();
		for (int x=0;x<g.getTailleX();x++) {
			for (int y=0;y<g.getTailleY();y++) {
				try {
					Case c = g.getCase(x, y);
					int[] xy = getXY(x, y);
					if (c.etreOccupee()) {
						Bateau b = c.occupePar();
						Color co = bat2col.get(b);
						if (co==null) {
							float cr = rd.nextFloat();
							float cg = rd.nextFloat();
							float cb = rd.nextFloat();
							co = new Color(cr, cg, cb);
							bat2col.put(b, co);
						}
						gr.setColor(co);
						gr.fillRoundRect(xy[0]+1, xy[1]+1, xy[2]-2, xy[3]-2,5,5);
					}
				} catch (ExceptionHorsDuTableau e) {

				}
			}
		}
		// affichage des tirs
		for (int x=0;x<g.getTailleX();x++) {
			for (int y=0;y<g.getTailleY();y++) {
				try {
					Case c = g.getCase(x, y);
					int[] xy = getXY(x, y);
					if (c.etreTouchee()) {
						gr.setColor(Color.RED);
						gr.drawLine(xy[0]+1, xy[1]+1, xy[0]+1+xy[2]-2, xy[1]+1+xy[3]-2);
						gr.drawLine(xy[0]+1, xy[1]+1+xy[3]-2, xy[0]+1+xy[2]-2, xy[1]+1);
					}
				} catch (ExceptionHorsDuTableau e) {

				}
			}
		}
		// affichage des bateaux fantomes
		for (int i=0;i<casesFantomes.size();i++) {
			int[] cc = casesFantomes.get(i);
			int[] xy = getXY(cc[0],cc[1]);
			gr.setColor(Color.GRAY);
			gr.fillRoundRect(xy[0]+1, xy[1]+1, xy[2]-2, xy[3]-2,5,5);
		}
	}
}
