package View;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import Controller.Controller;
import Model.Grid;
import Model.Slot;
public class MyCanvas extends Canvas implements Observer{

	private Controller control;
	public MyCanvas ( Controller control) {
		this.control = control;
		control.addObserver(this);
	}
	public void paint(Graphics g) {
		Grid courante = control.getCourante();		
		Slot [][] jeu = courante.getJeu();
		int hauteur = this.getHeight();
		int largeur = this.getWidth();
		int nbcol = courante.getNbColonne();
		int nblig = courante.getNbLigne();
		if (control.getCount()!=0) {
			if (!courante.hasWon(control.getCount())){			//Si le dernier joueur a avoir pose un pion n a pas gagne, on affiche la grille en fonction des pions poses
				g.setColor(new Color(0,0,180));
				g.fillRoundRect(2,2 , largeur-4 ,hauteur-4 , 40, 40);
				for(int i=0; i<nblig ; i++) {
					for(int j=0; j<nbcol ; j++) {
						switch(jeu[i][j].getValeur()) {
						case 1 : 
							g.setColor(control.getJ1());
							break;
						case 2 : 
							g.setColor(control.getJ2());
							break;
						default : 
							g.setColor(Color.white);
							break;
						}
						g.fillOval(largeur/20+(j*(largeur/nbcol)-largeur/50), hauteur-( hauteur/(nblig)+5+(i*(hauteur/nblig))-hauteur/20), largeur/(2*nbcol), hauteur/(2*nblig) );
					}
				}
			}
			else {						//Si un joueur a gagne, un petite animation de victoire apparait suivit d'un message de felicitation
				int i = 0;
				while ( i<200000) {
					g.setColor(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
					int alX = (int)(Math.random() * (largeur+1));
					int alY = (int)(Math.random() * (hauteur+1));
					g.drawOval(alX, alY, 5, 5);
					i++;
				}
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial",Font.PLAIN,20));
				g.drawString("Bravo pirate "+ (control.getCount()%2+1)+" tu as gagne !", largeur/2-100, hauteur/2+3);
			}
		}
		else {										//Si aucun joueur n'a gagne on affiche une grille vide
			g.setColor(new Color(0,0,180));
			g.fillRoundRect(2,2 , largeur-4 ,hauteur*(nblig)/(nblig+1) , 40, 40);
			for(int i=0; i<nblig ; i++) {
				for(int j=0; j<nbcol ; j++) {
					g.setColor(Color.white);
					g.fillOval(largeur/20+(j*(largeur/nbcol)-largeur/50), hauteur-( hauteur/(nblig-1)+(i*(hauteur/nblig))-hauteur/20), largeur/(2*nbcol), hauteur/(2*nblig) );
				}
			}
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.repaint();
	}
	
}
