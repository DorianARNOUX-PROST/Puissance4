package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewListener implements ActionListener{
	
	private Controller control;
	
	public NewListener ( Controller control) {
		this.control=control;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		control.resetGame(control.getCourante().getNbColonne(),control.getCourante().getNbLigne(),control.getCourante().getAlignement());
	}

}
