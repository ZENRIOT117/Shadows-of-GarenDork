import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class Main_Runner {
	
	public static GUI_Menubar menu;
	private static Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	
	public static void main(String[] args) {
		 JFrame frame = new JFrame(); 
		 frame.setDefaultCloseOperation( JFrame. HIDE_ON_CLOSE );
		 @SuppressWarnings("unused") Splash_Screen splash = new Splash_Screen("src/img/FullWaveLogo.png",frame,5000);
		 JFrame f = new JFrame("Shadows of GarenDork"); 
		 f.setDefaultCloseOperation( JFrame. HIDE_ON_CLOSE );
		 Dimension screensize= Toolkit.getDefaultToolkit().getScreenSize();
		 double height = screensize.getHeight();
		 double width = screensize.getWidth();
		 height = height-40;
		 f.setSize((int)width,(int)height);
		 f.setResizable(false);
		 f.setLayout(new BorderLayout());
		 Container pane = f.getContentPane(); 
		 pane.setLayout( new BorderLayout() );
		 JPanel p1 = new JPanel();
		 p1.setPreferredSize(new Dimension((int)width,(int)(height/2)));
		 p1.setLayout(new BorderLayout());
		 JButton btn1 = new JButton("Graphical Version");
		 btn1.setPreferredSize(new Dimension((int)(width/8),(int)(width/8)));
		 btn1.addMouseListener(new Butts());
		 btn1.setBackground(Color.WHITE);
		 btn1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		 btn1.setFont(segoe);
		 ImageIcon icon = new ImageIcon("src/img/GUI_Screenshot.png");
		 Image ic = icon.getImage();
		 ic = ic.getScaledInstance((int)(width*(7.0/8.0)),(int)(height/2),Image.SCALE_DEFAULT);
		 icon.setImage(ic);
		 JLabel lbl1 = new JLabel(icon);
		 p1.add(btn1 ,BorderLayout.EAST);
		 p1.add(lbl1, BorderLayout.CENTER);
		 //Break
		 JPanel p2 = new JPanel();
		 p2.setPreferredSize(new Dimension((int)width,(int)(height/2)));
		 p2.setLayout(new BorderLayout());
		 JButton btn2 = new JButton("Text Version");
		 btn2.setPreferredSize(new Dimension((int)(width/8),(int)(width/8)));
		 btn2.addMouseListener(new Butts());
		 btn2.setBackground(Color.WHITE);
		 btn2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		 btn2.setFont(segoe);
		 ImageIcon icon2 = new ImageIcon("src/img/Text_Screenshot.png");
		 Image ic2 = icon2.getImage();
		 ic2 = ic2.getScaledInstance((int)(width*(7.0/8.0)),(int)(height/2),Image.SCALE_DEFAULT);
		 icon2.setImage(ic2);
		 JLabel lbl2 = new JLabel(icon2);
		 p2.add(btn2 ,BorderLayout.EAST);
		 p2.add(lbl2, BorderLayout.CENTER);
		 pane.add(p1,BorderLayout.NORTH);
		 pane.add(p2,BorderLayout.SOUTH);
		 f.setVisible(true);
		 btn1.grabFocus();
		 f.requestFocus();
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
			}
		}
	}
}
