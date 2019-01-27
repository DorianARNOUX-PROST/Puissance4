package Model;
import java.util.concurrent.ThreadLocalRandom;

public class IA {
	
	// Simule un coup du joueur passe en parametre sur la grille
	public static void play(Grid grille, int joueur) {
		boolean quit = false;
		boolean played = false;
		int ennemi = joueur % 2 + 1;
		Grid test = new Grid(grille);
		
		// Verification victoire en un coup
		for (int i = 0; i < test.getNbColonne() && !quit; i++) {
			test = new Grid(grille);
			test.play(i, joueur);
			if (test.hasWon(joueur)) {
				grille.play(i, joueur);
				quit = true;
				played = true;
			}
		}
		
		// Verification victoire ennemi
		if (!quit) {
			test = new Grid(grille);
			for (int i = 0; i < test.getNbColonne() && !quit; i++) {
				test = new Grid(grille);
				test.play(i, ennemi);
				if (test.hasWon(ennemi)) {
					grille.play(i, joueur);
					quit = true;
					played = true;
				}
			}
		}
		
		if (!quit) {
			// Premier coup
			int count = 0;
			for (int i = 0; i < test.getNbLigne(); i++) {
				for (int j = 0; j < test.getNbColonne(); j++) {
					if (grille.getSlot(i,j).getValeur() == 1 || grille.getSlot(i,j).getValeur() == 2) {
						count++;
					}
				}
			}
			if (count < 2) {
				while(!quit) {
					if (grille.play(ThreadLocalRandom.current().nextInt(0, grille.getNbColonne()), joueur)) {
						quit = true;
						played = true;
					}
				}
			}
			// Anticipation sur plusieurs coups jusqu'a  4
			else {
				for (int i = 0; i < test.getNbColonne() && !quit; i++) {
					test = new Grid(grille);
					test.play(i, joueur);
					for (int j = 0; j < test.getNbColonne() && !quit; j++) {
						test = new Grid(grille);
						test.play(i, joueur);
						test.play(j, joueur);
						if (test.hasWon(joueur)) {
							played = grille.play(i, joueur);
							quit = true;
						}
						for (int k = 0; k < test.getNbColonne() && !quit; k++) {
							test = new Grid(grille);
							test.play(i, joueur);
							test.play(j, joueur);
							test.play(k, joueur);
							if (test.hasWon(joueur)) {
								played = grille.play(i, joueur);
								quit = true;
							}
							for (int l = 0; l < test.getNbColonne() && !quit; l++) {
								test = new Grid(grille);
								test.play(i, joueur);
								test.play(j, joueur);
								test.play(k, joueur);
								test.play(l, joueur);
								if (test.hasWon(joueur)) {
									played = grille.play(i, joueur);
									quit = true;
								}
							}
						}
					}
				}
			}
		}
		
		// Au cas ou si rien a ete trouve (Fin de partie par exemple)
		if (!quit || !played) {
			boolean quit2 = false;
			while(!quit2) {
				if (grille.play(ThreadLocalRandom.current().nextInt(0, grille.getNbColonne()), joueur)) {
					quit2 = true;
				}
			}
		}
		
	}
	
}
