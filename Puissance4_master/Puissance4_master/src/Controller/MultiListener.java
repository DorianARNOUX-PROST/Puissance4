package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiListener implements ActionListener {
	
	private Controller control ; 
	private boolean ia ;
	
	public MultiListener (Controller control, boolean ia) {
		this.control=control;
		this.ia=ia;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		control.resetGame();
		control.setWithIA(ia);
	}

}
