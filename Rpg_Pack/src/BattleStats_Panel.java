import javax.swing.*; 

import java.awt.*; 


public class BattleStats_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static JTextArea txtarea = new JTextArea("Monsters Killed = 0\n\n"
			+ "Monsters to kill before next level = 5\n\n"
			+ "You aren't fighting anything right now");
	private Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	
	public BattleStats_Panel(){
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
		if ((MES_Panel.m)!= null)
		txtarea.setText("Monsters Killed = "+(MES_Panel.USER).getMonstersKilled()+"\n\n"
				+ "Monsters to kill before next level = "+(5-(MES_Panel.USER).getCurrentMonstersKilled())+"\n\n"
				+ "You are fighting a: "+"\n"+(MES_Panel.m).getName()+": "+(MES_Panel.m).getStats());
		else
			txtarea.setText("Monsters Killed = "+(MES_Panel.USER).getMonstersKilled()+"\n\n"
					+ "Monsters to kill before next level = "+(5-(MES_Panel.USER).getCurrentMonstersKilled())+"\n\n"
					+ "You aren't fighting anything right now");
	}
}
