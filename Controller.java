import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
	
	private View console;
	private Grid grid;
	
	public Controller() {
		this.console = new View();
		this.grid = new Grid();
	}

	public void game() {
		boolean won = false;
		int count = 0, play = 0;
		Scanner scan = new Scanner(System.in);
		int column;
		while (!won) {
			console.afficher(grid);
			play ++;
			count = (count) % 2 + 1;
			System.out.println("Pirate numéro " + count + ", choisis ta colonne (1 à 7)");
			boolean nonEntier = true;
			while(nonEntier) {
				try {
					column = scan.nextInt() - 1;
					if (column < 0 || column > 6) {
						System.out.println("Entre une valeur entre 1 et 7 capitaine");
					}
					else {
						if(!grid.play(column, count)) {
							System.out.println("La colonne est déjà pleine, rentre un autre entier");
						}
						else nonEntier = false;
					}
				}catch (InputMismatchException e) {
					System.out.println("Entre un entier moussaillon");
					scan = new Scanner(System.in);
				}
				
			}
			if(play > 7 && grid.hasWon(count)) {
				System.out.println("Bravo pirate " + count + " tu as gagné!");
				console.afficher(grid);
				won = true;
			}
		}
		scan.close();
		
	}

}
