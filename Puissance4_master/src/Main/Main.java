package Main;
import Controller.Controller;
import View.MyFrame;

public class Main {

	public static void main(String[] args) {
		Controller controller = new Controller();
		MyFrame mf = new MyFrame (controller);
		controller.game();

	}

}
