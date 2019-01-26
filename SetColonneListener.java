import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetColonneListener implements ActionListener {

	private Controller control; 
	private int colonne;
	
	public SetColonneListener ( Controller control, int colonne) {
		this.control = control;
		this.colonne = colonne;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		control.resetGame(colonne, control.getCourante().getNbLigne(), control.getCourante().getAlignement());
	}

}
