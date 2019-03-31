package projet_bataille_navale.graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import projet_bataille_navale.Bateau;
import projet_bataille_navale.Case;
import projet_bataille_navale.ExceptionHorsDuTableau;
import projet_bataille_navale.Grille;

public class GrillePanel extends JPanel {

	private Grille g;

	public GrillePanel(Grille g) {
		super();
		setGrille(g);
	}

	public void setGrille(Grille g) {
		this.g=g;
		repaint();
	}

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
		for (int x=0;x<g.getTailleX();x++) {
			for (int y=0;y<g.getTailleY();y++) {
				try {
					Case c = g.getCase(x, y);
					int[] xy = getXY(x, y);
					if (c.etreOccupee()) {
						gr.setColor(Color.BLACK);
						gr.fillRoundRect(xy[0]+1, xy[1]+1, xy[2]-2, xy[3]-2,5,5);
					}
					if (c.etreTouchee()) {
						gr.setColor(Color.RED);
						gr.drawLine(xy[0]+1, xy[1]+1, xy[0]+1+xy[2]-2, xy[1]+1+xy[3]-2);
						gr.drawLine(xy[0]+1, xy[1]+1+xy[3]-2, xy[0]+1+xy[2]-2, xy[1]+1);
					}
				} catch (ExceptionHorsDuTableau e) {

				}
			}
		}
	}
}
