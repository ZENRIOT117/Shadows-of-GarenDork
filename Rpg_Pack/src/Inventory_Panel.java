import javax.swing.*; 

import java.awt.*; 
import java.util.ArrayList;


public class Inventory_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static JTextArea txtarea = new JTextArea("Hello!\nYou haven't created a player yet\n\nYour Inventory:\n\nEmpty :(");
	private Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	
	public Inventory_Panel(){
		setLayout(new BorderLayout());
		ScrollPane p = new ScrollPane();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		txtarea.setFont(segoe);
		txtarea.setEditable(false);
		txtarea.setLineWrap(false);
		panel.add(txtarea, BorderLayout.CENTER);
		p.add(panel);
		add(p,BorderLayout.CENTER);
	}
	public static void updateDisplay(){
		  ArrayList<Weapon>weps = (MES_Panel.USER).getWepsInInventory();
		  ArrayList<Potion>pots = (MES_Panel.USER).getPotsInInventory();
		  ArrayList<ArmorItem>arms = (MES_Panel.USER).getArmsInInventory();
		  txtarea.setText("Hello "+MES_Panel.USER.getName()+"!\n"+"You are a: "+MES_Panel.USER.getType()+"\n");
		  txtarea.append("\n"+MES_Panel.USER.getStats()+"\n");
		  txtarea.append("\nYour Current Weapon Is: "+(MES_Panel.w).getName()+"\n");
		  txtarea.append("\nYour Inventory:\n");
		  if (weps != null && weps.size() > 0){
		    for(Weapon wep:weps){
			  txtarea.append(wep.getStats()+"\nDescription on tag: "+wep.getDescription()+"\n\n");
		    }
		  }
		  if (pots != null && pots.size() > 0){
		    for(Potion pot:pots){
			  txtarea.append(pot.getStats()+"\nDescription on tag: "+pot.getDescription()+"\n\n");
		    }
		  }
		  if (arms != null && arms.size() > 0){
		    for(ArmorItem arm:arms){
			  txtarea.append(arm.getStats()+"\nDescription on tag: "+arm.getDescription()+"\n\n");
		    }
		  }
	  }
}
