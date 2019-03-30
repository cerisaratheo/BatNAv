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
		System.out.println("xxx "+xsc+" "+h+" "+g.getTailleX());
		int y = xsc*cx;
		int y2 = y+xsc;
		int ysc = w/g.getTailleY();
		int x = ysc*cy;
		int x2 = x+ysc;
		int[] res = {x,y,x2,y2}; 
		return res;
	}

	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		int w = getWidth();
		int h = getHeight();
		gr.setColor(Color.LIGHT_GRAY);
		System.out.println("comp "+w+" "+h);
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
		gr.setColor(Color.blue);
		for (int x=0;x<g.getTailleX();x++) {
			for (int y=0;y<g.getTailleY();y++) {
				try {
					Case c = g.getCase(x, y);
					if (c.etreOccupee()) {
						int[] xy = getXY(x, y);
						System.out.println("fill "+xy[0]+" "+xy[1]+" "+xy[2]+" "+xy[3]);
						gr.fillRect(xy[0], xy[1], xy[2], xy[3]);
						break;
					}
				} catch (ExceptionHorsDuTableau e) {

				}
			}
		}
	}
}
