import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Charge implements ActionListener {

	private  Controller control;
	private String nomFichier;
	
	public Charge(Controller control, String nomFichier) {
		this.control=control;
		this.nomFichier=nomFichier;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		File fichier = new File(nomFichier);
		try {
			ObjectInputStream ois = new ObjectInputStream ( new FileInputStream(fichier));
			try {
				int count = (int)ois.readObject();
				int ligne = (int)ois.readObject();
				int colonne = (int)ois.readObject();
				int alignement = (int)ois.readObject();
				int[][] tab = (int[][])ois.readObject();
				Grid courante = new Grid (colonne,ligne,alignement);
				for (int i =0; i<ligne;i++) {
					for (int j =0; j<colonne;j++) {
						courante.setSlot(new Slot(tab[i][j]), i, j);
					}
				}
				View console = new View();
				console.afficher(courante);
				control.setCount(count);
				control.setCourante(courante);
				
			}
			catch ( ClassNotFoundException e1){
				System.err.println("ClassNotFoundException ArrayList<Grid>");
			}
			ois.close();
		}
		catch (FileNotFoundException e2) {
			System.err.println("Exception File not found "+ nomFichier);
		}
		catch(IOException e3) {
			System.err.println("IOException "+nomFichier);
		}
	}

}
