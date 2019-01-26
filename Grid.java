import java.util.Observable;

public class Grid{
	
	private int nbLigne, nbColonne;
	private Slot[][] tabGrille;
	private int lastPlayedX;
	private int lastPlayedY;
	private int alignement;
	
	public Grid() {
		this.nbLigne = 6;
		this.nbColonne = 7;
		tabGrille = new Slot[nbLigne][nbColonne];
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				tabGrille[i][j] = new Slot(0);
			}
		}
		this.lastPlayedX = 0;
		this.lastPlayedY = 0;
		this.alignement = 4;
	}
	
	public Grid(Grid grille) {

		this.nbLigne=grille.getNbLigne();
		this.nbColonne=grille.getNbColonne();
		this.alignement=grille.getAlignement();
		this.lastPlayedX=grille.getLastPlayedX();
		this.lastPlayedY=grille.getLastPlayedY();
		this.tabGrille = new Slot[nbLigne][nbColonne];
		for (int i = 0; i < this.nbLigne; i++) {
			for (int j = 0; j < this.nbColonne; j++) {
				this.tabGrille[i][j] = new Slot(grille.getSlot(i,j).getValeur());
			}
		}
	}
	
	public int getLastPlayedX() {
		return lastPlayedX;
	}

	public void setLastPlayedX(int lastPlayedX) {
		this.lastPlayedX = lastPlayedX;
	}

	public int getLastPlayedY() {
		return lastPlayedY;
	}

	public void setLastPlayedY(int lastPlayedY) {
		this.lastPlayedY = lastPlayedY;
	}

	public Grid (int nbColonne, int nbLigne,int alignement) {
		this.nbLigne = nbLigne;
		this.nbColonne = nbColonne;
		this.alignement = alignement;
		tabGrille = new Slot[nbLigne][nbColonne];
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				tabGrille[i][j] = new Slot(0);
			}
		}
		this.lastPlayedX = 0;
		this.lastPlayedY = 0;
		
	}
	
	public boolean play(int column, int joueur) {
		boolean quit = false;
		int i;
		for(i = 0; (i < nbLigne) && !quit; i++) {
			if(tabGrille[i][column].getValeur() == 0) {
				tabGrille[i][column].setValeur(joueur);
				quit = true;
			}
		}
		this.lastPlayedX = column;
		this.lastPlayedY = i - 1;
		return quit;
	}
	
	public boolean hasWon(int joueur) {
		int c = 1, l = 1, d1 = 1, d2 = 1;
		boolean quit = false;
		boolean res = false;
		
		// On vérifie la colonne
		if(lastPlayedY >= alignement-1) {
			for(int i = lastPlayedY-1; i >= 0 && !quit; i--) {
				
				if(tabGrille[i][lastPlayedX].getValeur() == joueur) {
					c++;
				}
				else {
					quit = true;
				}
			}
			quit = false;
		}
		
		if(c >= alignement) {
			res = true;
		}
		else {
			// On vérifie la ligne à droite
			for(int i = lastPlayedX+1; (i < nbColonne) && !quit; i++) {
				if(tabGrille[lastPlayedY][i].getValeur() == joueur) {
					l++;
				}
				else {
					quit = true;
				}
			}
			quit = false;
			// On vérifie la ligne à gauche
			for(int i = lastPlayedX-1; (i >= 0) && !quit; i--) {
				if(tabGrille[lastPlayedY][i].getValeur() == joueur) {
					l++;
				}
				else {
					quit = true;
				}
			}
			quit = false;
			
			if(l >= alignement) {
				res = true;
			}
			else {
				// On vérifie la première diagonale
				int baseY = lastPlayedY - 1;
				for(int i = lastPlayedX-1; (baseY >= 0) && (i >= 0) && !quit; i--, baseY--) {
					if(tabGrille[baseY][i].getValeur() == joueur) {
						d1++;
					}
					else {
						quit = true;
					}
				}
				quit = false;
				baseY = lastPlayedY + 1;
				for(int i = lastPlayedX+1; (baseY < nbLigne) && (i < nbColonne) && !quit; i++, baseY++) {
					if(tabGrille[baseY][i].getValeur() == joueur) {
						d1++;
					}
					else {
						quit = true;
					}
				}
				if(d1 >= alignement) {
					res = true;
				}
				else {
					// On vérifie la deuxième diagonale
					baseY = lastPlayedY + 1;
					quit = false;
					for(int i = lastPlayedX-1; (baseY < nbLigne) && (i >= 0) && !quit; i--, baseY++) {
						if(tabGrille[baseY][i].getValeur() == joueur) {
							d2++;
						}
						else {
							quit = true;
						}
					}
					quit = false;
					baseY = lastPlayedY - 1;
					for(int i = lastPlayedX+1; (baseY >= 0) && (i < nbColonne) && !quit; i++, baseY--) {
						if(tabGrille[baseY][i].getValeur() == joueur) {
							d2++;
						}
						else {
							quit = true;
						}
					}
					if(d2 >= alignement) {
						res = true;
					}
				}
			}
		}
		
		return res;
	}

	public int getAlignement() {
		return alignement;
	}

	public void setAlignement(int alignement) {
		this.alignement = alignement;
	}

	public int getNbLigne() {
		return nbLigne;
	}

	public void setNbLigne(int nbLigne) {
		this.nbLigne = nbLigne;
	}

	public int getNbColonne() {
		return nbColonne;
	}

	public void setNbColonne(int nbColonne) {
		this.nbColonne = nbColonne;
	}

	public Slot getSlot(int x, int y) {
		return tabGrille[x][y];
	}

	public void setSlot(Slot slot, int x, int y) {
		this.tabGrille[x][y] = slot;
	}
	public Slot[][] getJeu(){
		return tabGrille;
	}
	
	
	
}
