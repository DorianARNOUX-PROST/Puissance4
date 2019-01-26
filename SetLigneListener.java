import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetLigneListener implements ActionListener{

	private Controller control; 
	private int ligne ;
	
	public SetLigneListener ( Controller control, int ligne) {
		this.control = control;
		this.ligne = ligne; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		control.resetGame(control.getCourante().getNbColonne(), ligne, control.getCourante().getAlignement());
	}

}
