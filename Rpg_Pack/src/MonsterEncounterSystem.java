import javax.swing.*; 

import java.awt.*; 

public class MonsterEncounterSystem {
	
	public static void main(String[] args){
		 JFrame frame = new JFrame(); 
		 frame.setDefaultCloseOperation( JFrame. EXIT_ON_CLOSE );
		 @SuppressWarnings("unused") Splash_Screen splash = new Splash_Screen("src/FullWaveLogo.png",frame,5000);
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
		 Frame_Menubar menu = new Frame_Menubar();
		 f.setJMenuBar(menu);
		 MES_Panel p1 = new MES_Panel ();
		 p1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		 Inventory_Panel p2 = new Inventory_Panel();
		 p2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		 p2.setPreferredSize(new Dimension((int)(width/4),(int)(height/4)));
		 BattleStats_Panel p3 = new BattleStats_Panel();
		 p3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		 p3.setPreferredSize(new Dimension((int)(width/4),(int)(height/4))); 
		 pane.add( p1, BorderLayout.CENTER);
		 pane.add( p2, BorderLayout.EAST ); 
		 pane.add( p3, BorderLayout.WEST );
		 f.pack(); 
		 f.setVisible( true );
		 
	}
}
