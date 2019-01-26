import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SourisListener implements MouseListener {

	private Controller control;
	private MyCanvas mc;
	private int x,y;
	
	public SourisListener (Controller control, MyCanvas mc) {
		this.control=control;
		this.mc=mc;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (!control.getCourante().hasWon(control.getCount())) {
			x = arg0.getX();
			int nbcol = control.getCourante().getNbColonne();
			int numColonne = x / (mc.getWidth()/(nbcol));
			control.playCourante(numColonne);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}
