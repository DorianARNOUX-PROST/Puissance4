
public class View {
	
	
	public void afficher(Grid grille) {
		String tmp;
		for (int i = grille.getNbLigne() - 1; i >= 0; i--) {
			tmp = "|";
			for (int j = 0; j < grille.getNbColonne(); j++) {
				if (grille.getSlot(i,j).getValeur() == 0) {
					tmp += " |";
				}
				else if (grille.getSlot(i,j).getValeur() == 1) {
					tmp += "X|";
				}
				else if (grille.getSlot(i,j).getValeur() == 2) {
					tmp += "O|";
				}
			}
			System.out.println(tmp);
		}
		System.out.println("\n");
	}
	
}
