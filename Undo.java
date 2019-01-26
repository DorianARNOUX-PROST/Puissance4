import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Undo implements ActionListener{

	private Controller control;
	
	public Undo (Controller control) {
		this.control=control;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		control.undo();
	}

}
