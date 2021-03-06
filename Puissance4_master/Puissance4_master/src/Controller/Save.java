package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Save implements ActionListener {

	private  Controller control;
	private String nomFichier;
	
	public Save(Controller control, String nomFichier) {
		this.control=control;
		this.nomFichier=nomFichier;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int[][] tab = new int[control.getCourante().getNbLigne()][control.getCourante().getNbColonne()] ; 
		for (int i =0; i< control.getCourante().getNbLigne();i++) {
			for (int j =0; j< control.getCourante().getNbColonne();j++) {
				tab[i][j] = control.getCourante().getSlot(i, j).getValeur();				//On forme un tableau d'entier a partir de la grille que l'on a afin de ppouvoir l'enregistrer dans un fichier
			}
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(nomFichier));	//On cree l'objet d'ecriture de fichier 
			oos.writeObject(control.getCount());												//On entre les uns a la suite des autres les elements pour pouvoir reconstruire le jeu
			oos.writeObject(control.getCourante().getNbLigne());
			oos.writeObject(control.getCourante().getNbColonne());
			oos.writeObject(control.getCourante().getAlignement());
			oos.writeObject(tab);
			oos.close();
		}
		catch(IOException e){
			System.err.println("Exception sur sauvegarde");
		}
	}

}
