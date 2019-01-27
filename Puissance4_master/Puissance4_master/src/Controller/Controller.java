package Controller;
import java.awt.Color;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import Model.Grid;
import Model.IA;
import View.View;

public class Controller extends Observable {
	
	private View console;
	private ArrayList<Grid> grids;
	private Grid courante;
	private boolean won;
	private int count;
	private int play;
	private Color j1;
	private Color j2;
	private boolean withIA;
	
	public Controller() {
		this.console = new View();
		this.grids = new ArrayList<Grid>();
		this.won = false;
		this.count = 0;
		this.play = 0;
		this.courante = new Grid();
		grids.add(courante);
		this.j1=Color.yellow;
		this.j2=Color.RED;
		this.withIA = false;
	}

	public void game() { 																//Fonction de jeu pour la console
		if (!withIA) {				
			Scanner scan = new Scanner(System.in);
			courante = new Grid(courante.getNbColonne(),courante.getNbLigne(),courante.getAlignement()) ;
			grids.add(courante);
			int column;
			while (!won) {																//On joue tant que l'on a pas gagne
				console.afficher(courante);
				play ++;
				count = (count) % 2 + 1;
				System.out.println("Pirate numero " + count + ", choisis ta colonne (1 a 7)");
				boolean nonEntier = true;
				while(nonEntier) {														//Tant que le joueur n'entre pas un entier, il ne joue pas 
					try {
						column = scan.nextInt() - 1;
						if (column < 0 || column > 6) {
							System.out.println("Entre une valeur entre 1 et 7 capitaine");
						}
						else {
							if(!courante.play(column, count)) {
								System.out.println("La colonne est deja pleine, rentre un autre entier");
							}
							else {
								grids.add(courante);
								this.setChanged();
								this.notifyObservers();
								nonEntier = false;
							}
						}
					}catch (InputMismatchException e) {
						System.out.println("Entre un entier moussaillon");
						scan = new Scanner(System.in);
					}
					
				}
				if(play > 7 && courante.hasWon(count)) {
					System.out.println("Bravo pirate " + count + " tu as gagne!");
					console.afficher(courante);
					won = true;
				}
			}
			scan.close();
		}
		else {																					//Si le joueur joue avec une ia on appelle la fonction de jeu avec ia
			gameWithIA();
		}
	}
	
	public void gameWithIA() {																	//Fonction pour si le joueur joue avec une ia dans la console
		Scanner scan = new Scanner(System.in);
		courante = new Grid(courante.getNbColonne(),courante.getNbLigne(),courante.getAlignement()) ;
		grids.add(courante);
		int column;
		while (!won) {
			console.afficher(courante);
			play ++;
			count = (count) % 2 + 1;
			if(count == 1) {
				System.out.println("Pirate numero " + count + ", choisis ta colonne (1 a7)");
				boolean nonEntier = true;
				while(nonEntier) {
					try {
						column = scan.nextInt() - 1;
						if (column < 0 || column > 6) {
							System.out.println("Entre une valeur entre 1 et 7 capitaine");
						}
						else {
							if(!courante.play(column, count)) {
								System.out.println("La colonne est deja pleine, rentre un autre entier");
							}
							else {
								grids.add(courante);
								this.setChanged();
								this.notifyObservers();
								nonEntier = false;
							}
						}
					}catch (InputMismatchException e) {
						System.out.println("Entre un entier moussaillon");
						scan = new Scanner(System.in);
					}
					
				}
			}
			else {
				IA.play(courante, count);
				grids.add(courante);
				this.setChanged();
				this.notifyObservers();
			}
			if(play > 6 && courante.hasWon(count)) {
				System.out.println("Bravo pirate " + count + " tu as gagne!");
				console.afficher(courante);
				won = true;
			}
		}
		scan.close();
		
	}
	
	public Grid getCourante() {
		if (grids.size()>=1){
			return grids.get(grids.size()-1);
		}
		else {
			return new Grid();
		}
	}
	public void setCourante(Grid grille) {
		this.grids = new ArrayList <Grid>();
		this.courante = grille;
		grids.add(courante);
		this.setChanged();
		this.notifyObservers();
	}
	public void resetGame() {			//Fonction pour remettre à 0 le jeu (on remet les variables a 0)
		grids = new ArrayList<Grid>();
		courante = new Grid();
		grids.add(courante);
		count = 1;
		play = 1;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void resetGame(int colonne , int ligne, int align) { //Fonction pour remmettre le jeu a 0 avec des parametres de grille specifique
		grids = new ArrayList<Grid>();
		courante = new Grid ( colonne, ligne, align);
		grids.add(courante);
		count = 1;
		play = 1;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void playCourante(int col) {		//Fonction de jeu pour interface graphique
		if (!withIA) {
			courante = new Grid(courante);
			play ++;
			count = (count) % 2 + 1;
			if(courante.play(col, count)) {
				grids.add(courante);
				this.setChanged();
				this.notifyObservers();
			}
			else {
				play --;
				count = count%2+1;
			}
		}
		else {								//Si le joueur joue avec une ia, on appelle la fonction spécifique
			playCouranteWithIA(col);
		}
	}
	
	public void playCouranteWithIA(int col) {
		
		courante = new Grid(courante);
		count = 2;
		if(courante.play(col, 2)) {
			grids.add(courante);
			this.setChanged();
			this.notifyObservers();
			if(!courante.hasWon(2)) {
				count = 1;
				IA.play(courante, 1);
				this.setChanged();
				this.notifyObservers();
			}
		}
	}

	public int getCount() {
		return count;
	}
	public int getPlay() {
		return play;
	}
	public void setCount(int count) {
		this.count=count;
	}
	
	public boolean getWithIA() {
		return withIA;
	}
	public void setWithIA(boolean withIA) {
		this.withIA=withIA;
	}
	
	
	public void undo() {			//Fonction de retour en arriere (on enleve le coup precedement joue du tableau)
		if (grids.size()>=2) {
			courante = grids.get(grids.size()-2);
			grids.remove(grids.size()-1);
			play -- ; 
			count = (count)%2+1;
			this.setChanged();
			this.notifyObservers();
		}
	}
	public Color getJ1() {
		return j1;
	}

	public void setJ1(Color j1) {
		this.j1 = j1;
		this.setChanged();
		this.notifyObservers();
	}

	public Color getJ2() {
		return j2;
	}

	public void setJ2(Color j2) {
		this.j2 = j2;
		this.setChanged();
		this.notifyObservers();
	}

	
}
