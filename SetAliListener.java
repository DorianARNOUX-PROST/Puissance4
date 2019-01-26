import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetAliListener implements ActionListener{

	private Controller control; 
	private int alignement ;
	
	public SetAliListener ( Controller control, int alignement) {
		this.control = control;
		this.alignement = alignement; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		control.resetGame(control.getCourante().getNbColonne(), control.getCourante().getNbLigne(), alignement);
	}

}
