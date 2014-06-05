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
	private JMenu m4 = new JMenu("Current Weapon");
	private ButtonGroup wepgroup = new ButtonGroup();
	private JMenu m5 = new JMenu("Armor");
	private JMenu m7 = new JMenu("Helpful");
	private JMenu m8 = new JMenu("Painful");

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
		JMenu m2 = new JMenu("Inventory");
		JRadioButtonMenuItem rbmi1= new JRadioButtonMenuItem("Fisticuffs");
		rbmi1.addActionListener(this);
		rbmi1.setSelected(true);
		wepgroup.add(rbmi1);
		m4.add(rbmi1);
		m4.addSeparator();
		JMenu m3 = new JMenu("Help");
		JMenuItem mi3 = new JMenuItem("Garendork Help");
		mi3.addActionListener(this);
		m3.add(mi3);
		m3.addSeparator();
		JMenuItem mi4 = new JMenuItem("About");
		mi4.addActionListener(this);
		JMenu m6 = new JMenu("Potions");
		m6.add(m7);
		m6.add(m8);
		m3.add(mi4);
		m2.add(m4);
		m2.add(m5);
		m2.add(m6);
		add(m);
		add(m2);
		add(m3);
		addHelpfulPotion("pot test");
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
	public void addArmor(String name){
		JMenuItem rbmi = new JMenuItem(name);
		rbmi.addActionListener(this);
		m5.add(rbmi);
		m5.addSeparator();
	}
	public void addWeapon(String name){
		JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(name);
		rbmi.addActionListener(this);
		wepgroup.add(rbmi);
		m4.add(rbmi);
		m4.addSeparator();
	}
	public void addSelectedWeapon(String name){
		JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(name);
		rbmi.addActionListener(this);
		wepgroup.add(rbmi);
		m4.add(rbmi);
		m4.addSeparator();
		rbmi.setSelected(true);
	}
	public void addHelpfulPotion(String name){
		JMenuItem rbmi = new JMenuItem(name);
		rbmi.addActionListener(this);
		m7.add(rbmi);
		m7.addSeparator();
	}
	public void addPainfulPotion(String name){
		JMenuItem rbmi = new JMenuItem(name);
		rbmi.addActionListener(this);
		m8.add(rbmi);
		m8.addSeparator();
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
