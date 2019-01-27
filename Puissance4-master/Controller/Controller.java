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

	public void game() {
		if (!withIA) {
			Scanner scan = new Scanner(System.in);
			courante = new Grid(courante.getNbColonne(),courante.getNbLigne(),courante.getAlignement()) ;
			grids.add(courante);
			int column;
			while (!won) {
				console.afficher(courante);
				play ++;
				count = (count) % 2 + 1;
				System.out.println("Pirate num�ro " + count + ", choisis ta colonne (1 � 7)");
				boolean nonEntier = true;
				while(nonEntier) {
					try {
						column = scan.nextInt() - 1;
						if (column < 0 || column > 6) {
							System.out.println("Entre une valeur entre 1 et 7 capitaine");
						}
						else {
							if(!courante.play(column, count)) {
								System.out.println("La colonne est d�j� pleine, rentre un autre entier");
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
					System.out.println("Bravo pirate " + count + " tu as gagn�!");
					console.afficher(courante);
					won = true;
				}
			}
			scan.close();
		}
		else {
			gameWithIA();
		}
	}
	
	public void gameWithIA() {
		Scanner scan = new Scanner(System.in);
		courante = new Grid(courante.getNbColonne(),courante.getNbLigne(),courante.getAlignement()) ;
		grids.add(courante);
		int column;
		while (!won) {
			console.afficher(courante);
			play ++;
			count = (count) % 2 + 1;
			if(count == 1) {
				System.out.println("Pirate num�ro " + count + ", choisis ta colonne (1 � 7)");
				boolean nonEntier = true;
				while(nonEntier) {
					try {
						column = scan.nextInt() - 1;
						if (column < 0 || column > 6) {
							System.out.println("Entre une valeur entre 1 et 7 capitaine");
						}
						else {
							if(!courante.play(column, count)) {
								System.out.println("La colonne est d�j� pleine, rentre un autre entier");
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
				System.out.println("Bravo pirate " + count + " tu as gagn�!");
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
	public void resetGame() {
		grids = new ArrayList<Grid>();
		courante = new Grid();
		grids.add(courante);
		count = 1;
		play = 1;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void resetGame(int colonne , int ligne, int align) {
		grids = new ArrayList<Grid>();
		courante = new Grid ( colonne, ligne, align);
		grids.add(courante);
		count = 1;
		play = 1;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void playCourante(int col) {
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
		else {
			playCouranteWithIA(col);
		}
	}
	
	public void playCouranteWithIA(int col) {
		
		courante = new Grid(courante);
		count = 1;
		if(courante.play(col, 1)) {
			grids.add(courante);
			this.setChanged();
			this.notifyObservers();
			count = 2;
			IA.play(courante, 2);
			this.setChanged();
			this.notifyObservers();
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
	
	
	public void undo() {
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
