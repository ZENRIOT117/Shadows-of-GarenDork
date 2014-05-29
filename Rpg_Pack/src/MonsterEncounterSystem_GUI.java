import javax.swing.*; 

import java.awt.*; 

public class MonsterEncounterSystem_GUI {
	public static GUI_Menubar menu;
	public static void main(String[] args){
		 JFrame frame = new JFrame(); 
		 frame.setDefaultCloseOperation( JFrame. EXIT_ON_CLOSE );
		 @SuppressWarnings("unused") Splash_Screen splash = new Splash_Screen("src/img/FullWaveLogo.png",frame,5000);
		 JFrame f = new JFrame("Shadows of Garendork"); 
		 f.setDefaultCloseOperation( JFrame. EXIT_ON_CLOSE );
		 Dimension screensize= Toolkit.getDefaultToolkit().getScreenSize();
		 double height = screensize.getHeight();
		 height = height-40;
		 double width = screensize.getWidth();
		 screensize.setSize(width,height);
		 f.setPreferredSize(screensize);
		 Container pane = f.getContentPane(); 
		 pane.setLayout( new BorderLayout() );
		 menu = new GUI_Menubar();
		 f.setJMenuBar(menu);
		 MesGUI_Panel p4 = new MesGUI_Panel();
		 pane.add( p4, BorderLayout.CENTER);
		 f.pack(); 
		 f.setVisible( true );
		 p4.askPlayerName();
	}
}

