import java.awt.Color;
import java.awt.Font;

import javax.swing.*; 

public class Player_Button extends JButton {
	
	private static final long serialVersionUID = 1L;
	private Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	//font for entire game
	private int row = -1;
	private int col = -1;
	
	public Player_Button(){
		
	}
	public Player_Button(ImageIcon icon){
		super(icon);
		setFont(segoe);
		setToolTipText((MesGUI_Panel.USER).getName());
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}
	public void setRow(int r){
		row = r;
	}
	public void setCol(int c){
		col = c;
	}
	public void setSpot(int r, int c){
		row = r;
		col = c;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
}
