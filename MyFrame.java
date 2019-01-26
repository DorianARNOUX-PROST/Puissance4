import java.awt.*;

public class MyFrame  extends Frame{
	public static final int LARGEUR = 500 ; 
	public static final int HAUTEUR = 500 ; 
	private Controller control;
	public void main (String[] args) {
		new MyFrame(control);
	}
	public MyFrame (Controller control) {
		this.setTitle("Le puissance 4 du pirate");
		this.setSize(LARGEUR, HAUTEUR);
		this.setLayout(new BorderLayout());
		MyCanvas mc = new MyCanvas(control);
		
		Button nouveau = new Button("Nouvelle partie");
		Button undo = new Button ("Undo");
		Panel p = new Panel();
		
		MenuBar menu = new MenuBar();
		Menu nbCol = new Menu ("Colonnes");
		MenuItem c1 = new MenuItem ("3");
		MenuItem c2 = new MenuItem ("4");
		MenuItem c3 = new MenuItem ("5");
		MenuItem c4 = new MenuItem ("6");
		MenuItem c5 = new MenuItem ("7");
		MenuItem c6 = new MenuItem ("8");
		MenuItem c7 = new MenuItem ("9");
		MenuItem c8 = new MenuItem ("10");
		MenuItem c9 = new MenuItem ("11");
		MenuItem c10 = new MenuItem ("12");
		
		Menu nbLig = new Menu ("Lignes");
		MenuItem l1 = new MenuItem ("3");
		MenuItem l2 = new MenuItem ("4");
		MenuItem l3 = new MenuItem ("5");
		MenuItem l4 = new MenuItem ("6");
		MenuItem l5 = new MenuItem ("7");
		MenuItem l6 = new MenuItem ("8");
		MenuItem l7 = new MenuItem ("9");
		MenuItem l8 = new MenuItem ("10");
		MenuItem l9 = new MenuItem ("11");
		MenuItem l10 = new MenuItem ("12");

		Menu objectif = new Menu ("Objectif");
		MenuItem o1 = new MenuItem ("3");
		MenuItem o2 = new MenuItem ("4");
		MenuItem o3 = new MenuItem ("5");
		MenuItem o4 = new MenuItem ("6");
		MenuItem o5 = new MenuItem ("7");
		MenuItem o6 = new MenuItem ("8");
		MenuItem o7 = new MenuItem ("9");
		MenuItem o8 = new MenuItem ("10");
		
		Menu sauv = new Menu("Sauvegardes");
		MenuItem s1 = new MenuItem("Sauvegarder");
		MenuItem s2 = new MenuItem("Récupérer");
		
		Menu j1 = new Menu ("Joueur 1");
		MenuItem jaune = new MenuItem ("Jaune");
		MenuItem orange = new MenuItem("Orange");
		MenuItem rose = new MenuItem("Rose");
		MenuItem gris = new MenuItem("Gris");
		
		Menu j2 = new Menu ("Joueur 2");
		MenuItem rouge = new MenuItem ("Rouge");
		MenuItem cyan = new MenuItem("cyan");
		MenuItem noir = new MenuItem("Noir");
		MenuItem vert = new MenuItem("Vert");
		
		c1.addActionListener(new SetColonneListener(control,3));
		c2.addActionListener(new SetColonneListener(control,4));
		c3.addActionListener(new SetColonneListener(control,5));
		c4.addActionListener(new SetColonneListener(control,6));
		c5.addActionListener(new SetColonneListener(control,7));
		c6.addActionListener(new SetColonneListener(control,8));
		c7.addActionListener(new SetColonneListener(control,9));
		c8.addActionListener(new SetColonneListener(control,10));
		c9.addActionListener(new SetColonneListener(control,11));
		c10.addActionListener(new SetColonneListener(control,12));
		
		l1.addActionListener(new SetLigneListener(control,3));
		l2.addActionListener(new SetLigneListener(control,4));
		l3.addActionListener(new SetLigneListener(control,5));
		l4.addActionListener(new SetLigneListener(control,6));
		l5.addActionListener(new SetLigneListener(control,7));
		l6.addActionListener(new SetLigneListener(control,8));
		l7.addActionListener(new SetLigneListener(control,9));
		l8.addActionListener(new SetLigneListener(control,10));
		l9.addActionListener(new SetLigneListener(control,11));
		l10.addActionListener(new SetLigneListener(control,12));
		
		o1.addActionListener(new SetAliListener(control,3));
		o2.addActionListener(new SetAliListener(control,4));
		o3.addActionListener(new SetAliListener(control,5));
		o4.addActionListener(new SetAliListener(control,6));
		o5.addActionListener(new SetAliListener(control,7));
		o6.addActionListener(new SetAliListener(control,8));
		o7.addActionListener(new SetAliListener(control,9));
		o8.addActionListener(new SetAliListener(control,10));
		
		s1.addActionListener(new Save(control,"partie"));
		s2.addActionListener(new Charge(control,"partie"));
		
		jaune.addActionListener(new CouleurJoueur1(control, Color.YELLOW));
		orange.addActionListener(new CouleurJoueur1(control, Color.ORANGE));
		rose.addActionListener(new CouleurJoueur1(control, Color.PINK));
		gris.addActionListener(new CouleurJoueur1(control, Color.LIGHT_GRAY));
		
		rouge.addActionListener(new CouleurJoueur2(control, Color.RED));
		cyan.addActionListener(new CouleurJoueur2(control, Color.CYAN));
		noir.addActionListener(new CouleurJoueur2(control, Color.BLACK));
		vert.addActionListener(new CouleurJoueur2(control, Color.GREEN));
		
		nouveau.addActionListener(new NewListener(control));
		undo.addActionListener(new Undo(control));
		
		nbCol.add(c1);
		nbCol.add(c2);
		nbCol.add(c3);
		nbCol.add(c4);
		nbCol.add(c5);
		nbCol.add(c6);
		nbCol.add(c7);
		nbCol.add(c8);
		nbCol.add(c9);
		nbCol.add(c10);
		
		nbLig.add(l1);
		nbLig.add(l2);
		nbLig.add(l3);
		nbLig.add(l4);
		nbLig.add(l5);
		nbLig.add(l6);
		nbLig.add(l7);
		nbLig.add(l8);
		nbLig.add(l9);
		nbLig.add(l10);
		
		objectif.add(o1);
		objectif.add(o2);
		objectif.add(o3);
		objectif.add(o4);
		objectif.add(o5);
		objectif.add(o6);
		objectif.add(o7);
		objectif.add(o8);
		
		sauv.add(s1);
		sauv.add(s2);
		
		j1.add(jaune);
		j1.add(orange);
		j1.add(rose);
		j1.add(gris);
		
		j2.add(rouge);
		j2.add(cyan);
		j2.add(noir);
		j2.add(vert);
		
		menu.add(objectif);
		menu.add(nbCol);
		menu.add(nbLig);
		menu.add(sauv);
		menu.add(j1);
		menu.add(j2);
		p.add(nouveau);
		p.add(undo);
		
		this.setMenuBar(menu);
		this.add(mc,BorderLayout.CENTER);
		this.add(p,BorderLayout.SOUTH);
		
		
		this.addWindowListener(new Close(this));
		mc.addMouseListener(new SourisListener(control,mc));
		
		this.setVisible(true);
	}
	
	
	
	
}
