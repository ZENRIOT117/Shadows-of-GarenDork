import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class Main_Runner {
	
	public static GUI_Menubar menu;
	private static Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	private static JFrame fr;
	public static void main(String[] args) {
		 JFrame frame = new JFrame(); 
		 Toolkit toolkit= Toolkit.getDefaultToolkit();
		 frame.setIconImage(toolkit.getImage("img/Tree3.png"));
		 frame.setDefaultCloseOperation( JFrame. HIDE_ON_CLOSE );
		 @SuppressWarnings("unused") Splash_Screen splash = new Splash_Screen("img/FullWaveLogo.png",frame,5000);
		 fr = new JFrame("Shadows of GarenDork"); 
		 fr.setDefaultCloseOperation( JFrame. HIDE_ON_CLOSE );
		 Dimension screensize= toolkit.getScreenSize();
		 double height = screensize.getHeight();
		 double width = screensize.getWidth();
		 height = height-40;
		 fr.setSize((int)width,(int)height);
		 fr.setResizable(false);
		 fr.setLayout(new BorderLayout());
		 Container pane = fr.getContentPane(); 
		 pane.setLayout( new BorderLayout() );
		 JPanel p1 = new JPanel();
		 p1.setPreferredSize(new Dimension((int)width,(int)(height/8)));
		 p1.setLayout(new GridLayout(1,2));
		 JButton btn1 = new JButton("Graphical Version");
		 btn1.setPreferredSize(new Dimension((int)(width/8),(int)(width/8)));
		 btn1.addMouseListener(new Butts());
		 btn1.setBackground(Color.WHITE);
		 btn1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		 btn1.setFont(segoe);;
		 p1.add(btn1);
		 //Break
		 JPanel page= new JPanel();
		 page.setLayout(new BorderLayout());
		 JTextArea txtarea = new JTextArea();
		 txtarea.setEditable(false);
		 txtarea.setFont(segoe);
		 txtarea.setText("Welcome to Shadows of GarenDork v0.1 Alpha!\n"
			 		+ "\nLatest News:\n"
			 		+ "\n5/28/14 - Source code now available on GitHub!\n"
			 		+ "\n\nPlease select either graphical or text version of the game below");
		 page.add(txtarea, BorderLayout.CENTER);
		 //txtarea.setPreferredSize(page.getSize());
		 JButton btn2 = new JButton("Text Version");
		 btn2.setPreferredSize(new Dimension((int)(width/8),(int)(width/8)));
		 btn2.addMouseListener(new Butts());
		 btn2.setBackground(Color.WHITE);
		 btn2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		 btn2.setFont(segoe);
		 p1.add(btn2);
		 pane.add(page,BorderLayout.CENTER);
		 pane.add(p1,BorderLayout.SOUTH);
		 fr.setVisible(true);
		 btn1.grabFocus();
		 fr.requestFocus();
	}
	private static class Butts extends MouseAdapter{
		@SuppressWarnings("deprecation")
		public void mouseClicked(MouseEvent e){
			if(((JButton)e.getSource()).getLabel().equals("Graphical Version")){
					 JFrame frame = new JFrame(); 
					 frame.setDefaultCloseOperation( JFrame. EXIT_ON_CLOSE );
					 JFrame f = new JFrame("Shadows of GarenDork"); 
					 f.setDefaultCloseOperation( JFrame. EXIT_ON_CLOSE );
					 Dimension screensize= Toolkit.getDefaultToolkit().getScreenSize();
					 double height = screensize.getHeight();
					 height = height-40;
					 double width = screensize.getWidth();
					 screensize.setSize(width,height);
					 f.setPreferredSize(screensize);
					 Container pane = f.getContentPane(); 
					 pane.setLayout( new BorderLayout() );
					 MesGUI_Panel p4 = new MesGUI_Panel();
					 pane.add( p4, BorderLayout.CENTER);
					 menu = new GUI_Menubar();
					 f.setJMenuBar(menu);
					 f.pack(); 
					 f.setVisible( true );
					 p4.askPlayerName();
					 fr.dispose();
			}
			else{
				JFrame frame = new JFrame(); 
				 frame.setDefaultCloseOperation( JFrame. EXIT_ON_CLOSE );
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
				 fr.dispose();
			}
		}
	}
}
