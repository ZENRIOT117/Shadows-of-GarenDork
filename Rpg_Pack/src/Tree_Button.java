import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tree_Button extends JButton {
	private static final long serialVersionUID = 1L;
	private int row = -1;
	private int col = -1;
	private Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	
	public Tree_Button(){
		setFont(segoe);
		setToolTipText("Just a tree, nothing to see here");
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}
	
	public Tree_Button(ImageIcon icon){
		super(icon);
		setFont(segoe);
		setToolTipText("Just a tree, nothing to see here");
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

