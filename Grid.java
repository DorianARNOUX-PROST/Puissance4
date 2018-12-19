
public class Grid {
	
	private int nbLigne, nbColonne;
	private Slot[][] tabGrille;
	
	public Grid() {
		this.nbLigne = 6;
		this.nbColonne = 7;
		tabGrille = new Slot[nbLigne][nbColonne];
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				tabGrille[i][j] = new Slot(0);
			}
		}
	}
	
	public boolean play(int column, int joueur) {
		boolean quit = false;
		for(int i = 0; (i < nbLigne) && (!quit); i++) {
			if(tabGrille[i][column].getValeur() == 0) {
				tabGrille[i][column].setValeur(joueur);
				quit = true;
			}
		}
		return quit;
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
