import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GUI_Menubar extends JMenuBar implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	private JFrame frame = new JFrame();
	private JTextArea txtarea = new JTextArea();
	private ButtonGroup group;
	private JMenu m2;

	public GUI_Menubar(){
		frame.setDefaultCloseOperation( JFrame. HIDE_ON_CLOSE );
		txtarea.setFont(segoe);
		txtarea.setEditable(false);
		txtarea.setPreferredSize(new Dimension(700,500));
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(700,500));
		panel.add(txtarea);
		frame.setSize(700,500);
		(frame.getContentPane()).add(panel);
		frame.setResizable(false);
		frame.setVisible(false);
		JMenu m = new JMenu("File");
		JMenuItem mi1 = new JMenuItem("Save");
		mi1.addActionListener(this);
		m.add(mi1);
		m.addSeparator();
		JMenuItem mi2 = new JMenuItem("Load");
		mi2.addActionListener(this);
		m.add(mi2);
		m2 = new JMenu("Player");
		group = new ButtonGroup();
		JRadioButtonMenuItem rbmi1= new JRadioButtonMenuItem("Fisticuffs");
		rbmi1.addActionListener(this);
		rbmi1.setSelected(true);
		group.add(rbmi1);
		m2.add(rbmi1);
		JMenu m3 = new JMenu("Help");
		JMenuItem mi3 = new JMenuItem("Garendork Help");
		mi3.addActionListener(this);
		m3.add(mi3);
		m3.addSeparator();
		JMenuItem mi4 = new JMenuItem("About");
		mi4.addActionListener(this);
		m3.add(mi4);
		add(m);
		add(m2);
		add(m3);
	}
	public void displayHelpWindow(){
		frame.setTitle("Help");
		txtarea.setText("  ↑ - Moves player forwards (clicking on the square in front also works)\n"
	    		+ "  ↓ - Moves player backwards (clicking on the square in back also works)\n"
	    		+ "  ← - Moves Player left (clicking on the square to the left also works)\n"
	    		+ "  → - Moves Player right (clicking on the square to the right also works)\n"
	    		+ "  a - Attacks an adjacent monster\n"
	    		+ "  s - Uses your current weapon's special attack on an adjacent monster\n");
		frame.setVisible(true);
	}
	public void displayAboutWindow(){
		frame.setTitle("About");
		txtarea.setText("  Shadows of Garendork is a free and open-source project\n\n"
				+ "  Made possible by:\n"
				+ "  Theo Fleck and Zack Nichols\n\n"
				+ "  With addittional support from:\n"
				+ "  Methacton High School and Mr. Sawyer");
		frame.setVisible(true);
	}
	public void addWeapon(String name){
		JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(name);
		rbmi.addActionListener(this);
		group.add(rbmi);
		m2.add(rbmi);
	}
	public void addSelectedWeapon(String name){
		JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(name);
		rbmi.addActionListener(this);
		group.add(rbmi);
		m2.add(rbmi);
		rbmi.setSelected(true);
	}
	public void setCurrentWeapon(Object obj){
		JRadioButtonMenuItem rb = (JRadioButtonMenuItem)obj;
		ArrayList<Weapon>weps = MesGUI_Panel.USER.getWepsInInventory();
		String name = rb.getText();
		  for (Weapon wep:weps){
			  if (wep.getName().equals(name)){
				  MesGUI_Panel.W = wep;
				  return;
			  }
		  }
	}
	public void actionPerformed(ActionEvent e) { 
    	if ((((JMenuItem)e.getSource()).getText()).equals("Garendork Help"))
    		displayHelpWindow();
    	else if ((((JMenuItem)e.getSource()).getText()).equals("About"))
    		displayAboutWindow();
    	else if (e.getSource() instanceof JRadioButtonMenuItem)
    		setCurrentWeapon(e.getSource());
	}
	
}
