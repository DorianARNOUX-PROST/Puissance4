public class Grid {
	
	private int nbLigne, nbColonne;
	private Slot[][] tabGrille;
	private int lastPlayedX;
	private int lastPlayedY;
	
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
		
		// On v�rifie la colonne
		if(lastPlayedY >= 3) {
			for(int i = lastPlayedY-1; (i >= lastPlayedY-3) && !quit; i--) {
				
				if(tabGrille[i][lastPlayedX].getValeur() == joueur) {
					c++;
				}
				else {
					quit = true;
				}
			}
			quit = false;
		}
		
		if(c == 4) {
			res = true;
		}
		else {
			// On v�rifie la ligne � droite
			for(int i = lastPlayedX+1; (i < nbColonne) && !quit; i++) {
				if(tabGrille[lastPlayedY][i].getValeur() == joueur) {
					l++;
				}
				else {
					quit = true;
				}
			}
			quit = false;
			// On v�rifie la ligne � gauche
			for(int i = lastPlayedX-1; (i >= 0) && !quit; i--) {
				if(tabGrille[lastPlayedY][i].getValeur() == joueur) {
					l++;
				}
				else {
					quit = true;
				}
			}
			quit = false;
			
			if(l >= 4) {
				res = true;
			}
			else {
				// On v�rifie la premi�re diagonale
				for(int i = lastPlayedX-1; (i >= 0) && !quit; i--) {
					if(tabGrille[lastPlayedY][i].getValeur() == joueur) {
						l++;
					}
					else {
						quit = true;
					}
				}
				quit = false;
			}
		}
		
		return res;
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
	
	
	
}
