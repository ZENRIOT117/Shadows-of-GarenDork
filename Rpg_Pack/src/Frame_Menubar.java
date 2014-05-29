import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame_Menubar extends JMenuBar implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Font segoe = new Font("Segoe UI", Font.PLAIN, 18);

	public Frame_Menubar(){
		JMenu m = new JMenu("Menu");
		JMenuItem mi1 = new JMenuItem("Help");
		mi1.addActionListener(this);
		m.add(mi1);
		JMenuItem mi2 = new JMenuItem("Save");
		mi2.addActionListener(this);
		m.add(mi2);
		JMenuItem mi3 = new JMenuItem("Load");
		mi3.addActionListener(this);
		m.add(mi3);
		JMenuItem mi4 = new JMenuItem("About");
		mi4.addActionListener(this);
		m.add(mi4);
		add(m);
	}
	public void displayHelpWindow(){
		JFrame frame = new JFrame("Help");
		frame.setDefaultCloseOperation( JFrame. HIDE_ON_CLOSE );
		JTextArea txtarea = new JTextArea("  /fight -(Deprecated) spawns a monster for you to fight\n"
	    		+ "  /attack - attacks the monster you are fighting with your current weapon\n"
	    		+ "  /run - attempts to escape the monster\n"
	    		+ "  /currentWeapon=(weapon name) - sets your current weapon, weapon name should be entered as it appears in your inventory\n"
	    		+ "  /displayInventory - displays your weapons,potions,and armor\n"
	    		+ "  /usePotion=(Potion name) - uses a Potion from your inventory, potion name shoudl be entered as it appears in your inventory\n"
	    		+ "  /throwDagger - throws your dagger, provided the dagger your current weapon\n"
	    		+ "  /hammerSmash - uses your hammer to bash the enemy's skull in, provided the hammer is your current weapon\n"
	    		+ "  /staffSmite - uses your staff to smite a monster, provided the staff is your current weapon\n"
	    		+ "  /swordBlock - uses your sword to block a monster's blow and damage it some, provided the sword is your current weapon\n"
	    		+ "  /stats - displays current player stats\n"
	    		+ "  /equipArmor=(Armor piece name) - equips a piece of armor onto your player\n"
	    		+ "  /dropItem=(Item name) - drops an item from your inventory to lower pack weight\n"
	    		+ "  /usePotionOn=(Potion name) - Uses a potion on the current enemy\n"
	    		+ "  /advance - moves player forward in the game, to be used at the end of each main event\n"
	    		+ "  /seeMerchant - allows the player to use a merchant visit to see a merchants wares\n"
	    		+ "  /buy=(Item name) - allows a player to purchase one of a merchant's items");
		txtarea.setFont(segoe);
		txtarea.setEditable(false);
		txtarea.setPreferredSize(new Dimension(700,500));
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(700,500));
		panel.add(txtarea);
		frame.setSize(700,500);
		(frame.getContentPane()).add(panel);
		frame.setResizable(false);
		frame.setVisible(true);
	}	
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) { 
    	if ((((JMenuItem)e.getSource()).getLabel()).equals("Help"))
    		displayHelpWindow();
	}
	
}
