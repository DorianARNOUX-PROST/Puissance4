import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CouleurJoueur2 implements ActionListener{
	Color couleur;
	Controller control;
	
	public CouleurJoueur2(Controller control,Color couleur) {
		this.control=control;
		this.couleur=couleur;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		control.setJ2(couleur);
	}

}