import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;

public class MesGUI_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int rows = 10;
	private int cols;
	private JButton[][] grid;
	//represents grid of things the user can click on/see
	private Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	//font for entire game
	public static Player USER;
	//represents current player 
	private MonsterPool pool = new MonsterPool();
	//instance of monster pool for getting monsters
	public static Monster M;
	//current monster MES_Panel.USER is fighting
	public static Weapon W;
	//current weapon MES_Panel.USER is using
	//private boolean bool = true;
	//used for figuring out if MES_Panel.USER mutilated the bodies for an extra level yet
	private TreasureChest chest = new TreasureChest();
	//instance of treasure chest for rewards
	private Merchant mihir=new Merchant();
	//merchant for purchases
	private JTextField txt1 = new JTextField();
	//receives all MES_Panel.USER input
	private JButton btn1;
	private String str1 = "";
	//input from USER
	private String name;
	//represents player name
	private JPanel panel2;
	private JFrame frame;
	private JTextArea txtarea;

  public MesGUI_Panel() {
	  Dimension screensize= Toolkit.getDefaultToolkit().getScreenSize();
	  double height = screensize.getHeight();
      double width = screensize.getWidth();
	  panel2 = new JPanel();
      panel2.setPreferredSize(new Dimension((int)width,(int)height));
      height = ((int)height-((int)height/5)-40);
      width = (int)width;
      cols = (int)(rows*(width/height));
      grid = new JButton[rows][cols];
	  panel2.setLayout(new GridLayout(rows,cols));
	  ImageIcon icon = new ImageIcon("src/img/Tree3.png");
	  Image ic = icon.getImage();
	  ic = ic.getScaledInstance((int)width/cols,(int)height/rows,Image.SCALE_DEFAULT);
	  icon.setImage(ic);
	   for(int r = 0; r < rows; r++){
		  for (int c = 0; c < cols; c++){
			  grid[r][c] = new Tree_Button(icon);
			  panel2.add(grid[r][c]);
			  ((Tree_Button)grid[r][c]).setSpot(r,c);
			  grid[r][c].addActionListener(new Buttons());
			  grid[r][c].addKeyListener(new Keys());
		  }
	  }
	  setLayout(new BorderLayout());
	  add(panel2, BorderLayout.CENTER);
	  setSize(new Dimension((int)width,((int)height)));
	  setVisible(true);
  }
  
  public void buttonClicked(Object obj){
	  if (obj instanceof Player_Button){
		  txtarea.setText(MesGUI_Panel.USER.getStats()+"\n\n\n\n"+W.getName());
		  btn1.setVisible(false);
		  txt1.setVisible(false);
		  frame.setSize(800,500);
		  frame.setTitle("Current Player Stats");
		  frame.setVisible(true);
	  }
	  else if(obj instanceof Monster_Button){
		  txtarea.setText(MesGUI_Panel.M.getStats());
		  btn1.setVisible(false);
		  txt1.setVisible(false);
		  frame.setSize(800,500);
		  frame.setTitle("Current Monster Stats");
		  frame.setVisible(true);
	  }
	  else if ((((Tree_Button)obj).getRow()) == 0 || (((Tree_Button)obj).getCol()) == 0){ 
		  return;
	  }
	  else if ((((Tree_Button)obj).getCol()) != 0 && (grid[(((Tree_Button)obj).getRow())][(((Tree_Button)obj).getCol()-1)]) instanceof Player_Button){
		  movePlayerRight();
	  }
	  else if ((((Tree_Button)obj).getCol()) != cols-1 && (grid[(((Tree_Button)obj).getRow())][(((Tree_Button)obj).getCol()+1)]) instanceof Player_Button){
		  movePlayerLeft();
	  }
	  else if ((((Tree_Button)obj).getRow()) != 0 && (grid[(((Tree_Button)obj).getRow()-1)][(((Tree_Button)obj).getCol())]) instanceof Player_Button){
		  movePlayerDown();
	  }
	  else if ((((Tree_Button)obj).getRow()) != rows-1 && (grid[(((Tree_Button)obj).getRow()+1)][(((Tree_Button)obj).getCol())]) instanceof Player_Button){
		  movePlayerUp();
	  }
  }
  
	  public void askPlayerType(){
			txtarea.setText("Enter the class you want to be in the box, from the choices below, ex. /mage (/ first is required)\n"
			  		+ "\nAssassin - low health, high damage, high speed - starts with Lil Stabber\n"
			  		+ "\nBrute - high health, low damage, low speed - starts with Smashy Smashy\n"
			  		+ "\nMage - low health, high damage, medium speed - starts with Wacky Stick\n"
			  		+ "\nWarrior - medium health, medium damage, medium speed - starts with Big Bertha\n");
			frame.setVisible(true);
	  }

	  public void askPlayerName(){
		    frame = new JFrame("Create Character");
			frame.setDefaultCloseOperation( JFrame. HIDE_ON_CLOSE );
			txtarea = new JTextArea("Enter the name you want for your character\n\nEnter the name in the form of /name=tommy");
			txtarea.setFont(segoe);
			txtarea.setEditable(false);
			txtarea.setPreferredSize(new Dimension(790,500));
			JPanel p = new JPanel();
			JPanel p2 = new JPanel();
			p2.setPreferredSize(new Dimension(790,500));
			p2.add(txtarea);
			p.setLayout(new BorderLayout());
			p.setPreferredSize(new Dimension(60,60));
			txt1 = new JTextField();
			btn1 = new JButton("Submit");
			btn1.setBackground(Color.WHITE);
			btn1.addActionListener(new TextField());
			txt1.addKeyListener(new Key1());
			txt1.setFont(segoe);
			btn1.setFont(segoe);
			btn1.setPreferredSize(new Dimension(800/3,800/3));
			txt1.setPreferredSize(new Dimension((800-(800/3)),(800-(800/3))));
			p.add(txt1, BorderLayout.WEST);
			p.add(btn1, BorderLayout.EAST);
			JPanel p3 = new JPanel();
			p3.setLayout(new BorderLayout());
			p3.add(p2, BorderLayout.NORTH);
			p3.add(p, BorderLayout.SOUTH);
			frame.setSize(800,590);
			(frame.getContentPane()).add(p3);
			frame.setResizable(false);
			frame.setVisible(true);
			p3.grabFocus();
			txt1.grabFocus();
	  }
	  public void setPlayerName(String n){
		  name = n.substring(6);
		  askPlayerType();
	  }
	  public void setPlayerType(String type){
		  type = type.substring(1);
		  type = type.toLowerCase();
		  if (type.equals("assassin")){
			  MesGUI_Panel.USER = new Assassin(name);
			  grid[5][cols/2] = new Player_Button(new ImageIcon("img/Brute.png"));
		  }
		  else if (type.equals("brute")){
			  MesGUI_Panel.USER = new Brute(name);
			  grid[5][cols/2] = new Player_Button(new ImageIcon("img/Brute.png"));
		  }
		  else if (type.equals("mage")){
			  MesGUI_Panel.USER = new Mage(name);
			  grid[5][cols/2] = new Player_Button(new ImageIcon("img/Warrior.png"));
		  }
		  else{
			  MesGUI_Panel.USER = new Warrior(name);
			  grid[5][cols/2] = new Player_Button(new ImageIcon("img/Warrior.png"));
		  
		  }
		  M = pool.getMonster(USER.getLevel());
		  ArrayList<Weapon> weps = USER.getWepsInInventory();
		  (Main_Runner.menu).addSelectedWeapon(weps.get(1).getName());
		  W = weps.get(1);
		  grid[0][cols/2] = M.getButton();
		  grid[0][cols/2].addActionListener(new Buttons());
		  grid[0][cols/2].addKeyListener(new Keys());
		  grid[5][cols/2].addActionListener(new Buttons());
		  grid[5][cols/2].addKeyListener(new Keys());
		  ((Player_Button)grid[5][cols/2]).setSpot(5,cols/2);
		  ((Monster_Button)grid[0][cols/2]).setSpot(0,cols/2);
		  USER.setButton((Player_Button)grid[5][cols/2]);
		  frame.setVisible(false);
		  reDisplay();
	  }
	  public void playerStats(){
		  txtarea.setText("Hello "+MES_Panel.USER.getName()+"!\n"+"You are a: "+MES_Panel.USER.getType()+"\n");
		  ArrayList<Weapon>weps = MES_Panel.USER.getWepsInInventory();
		  for (Weapon wep:weps){
			  txtarea.append(wep.getStats()+", Description on tag: "+wep.getDescription()+"\n");
		  }
		  txtarea.append("\n"+MES_Panel.USER.getStats()+"\n");
		  W = weps.get(1);
	  }
	  public void encounterTreasureRoom(){
		  TreasureRoom t= new TreasureRoom();
		  if(t.isReward()){
			  Item reward = (Item)t.getReward();
			  USER.addToInventory(reward);
			  txtarea.setText("Congratulations, you found a secret treasure room! You got a(n):\n"
				  		+ "\n"+reward.getType()+": "+reward.getStats()+"\nDescription on tag: "+reward.getDescription()+"\n\nEnter /advance to move forward, or /help for commands");
		  }
		  else{
			  Potion x= t.getAntiReward();
			  USER.usePotion(x);
			  txtarea.setText("Oh no, an evil chest goblin chucked a splash potion at you! He threw a(n):\n"
				  		+ "\n"+x.getType()+": "+x.getStats()+"\nDescription of damage: "+x.getDescription()+"\n\nEnter /advance to move forward, or /help for commands");
		  }
	  }
	  public void seeMerchant(){
		  if(USER.getMerchVisits()>0){
			  mihir=new Merchant();
			  ArrayList<Item> wares= mihir.getWares();
			  txtarea.setText("You stumble upon a humble merchant in the woods and he offers to sell you:\n");
			  for(Item i: wares)
				  txtarea.append(i.getStats()+", Description on tag: "+i.getDescription()+", Cost:"+i.getValue()+"\n");
			  txtarea.append("\nIf you wish to purchase an item, use that /buy=(item name) command.");
		  }
		  else
			  txtarea.setText("You cannot seem to find the merchant... oh well.");  
	  }
	  public void purchaseItem(String name){
		  ArrayList<Item> wares= mihir.getWares();
		  name= name.toLowerCase();
		  for(int i= 0; i<wares.size(); i++){
			  if(name.equals(wares.get(i).getName().toLowerCase())){
				  int value= wares.get(i).getValue();
				  if(value<= USER.getGold()){
					  USER.addToInventory(wares.get(i));
					  USER.removeGold(value);
					  txtarea.setText("You have purchased:"+wares.get(i).getName()); 
				  }
				  else
					  txtarea.setText("You do not have enough gold to purchase that item"); 
			  }
		  }
	  }
	  public void updateBattleStats(){
		  
	  }
	  public boolean checkAdjacent(int row, int col, int r, int c){
		  if (((row + 1) == r) && (col == c))
			  return true;
		  if(((row - 1) == r) && (col == c))
			  return true;
		  if(((col + 1) == c) && (row == r))
			  return true;
		  if (((col - 1) == c) && (row == r))
			  return true;
		  return false;
	  }
	  public void attackMonster(){
		  int row = USER.getButton().getRow();
		  int col = USER.getButton().getCol();
		  if(M == null){
			  spawnMonster();
			  return;
		  }
		  int r = M.getButton().getRow();
		  int c = M.getButton().getCol();
		  if(W != null){
			  if (checkAdjacent(row,col,r,c)){
				  if (M.isDead() == false)
					  USER.attack(M,W);
				  if (M.isDead())
					  awardTreasure();
			  }
		 }
		 reDisplay();
	  }
	  public void awardTreasure(){
		  int gold = getGold();
		  Item reward = chest.getReward();
		  if(reward instanceof Weapon)
			  (Main_Runner.menu).addWeapon(reward.getName());
		  else if(reward instanceof ArmorItem)
			  (Main_Runner.menu).addArmor(reward.getName());
		  else if(reward instanceof Potion && (((Potion)reward).getEffect().equals("HEAL") || ((Potion)reward).getEffect().equals("SWIFT")))
			  (Main_Runner.menu).addHelpfulPotion(reward.getName());
		  else if(reward instanceof Potion)
			  (Main_Runner.menu).addPainfulPotion(reward.getName());
		  USER.addToInventory(reward);
		  USER.addGold(gold);
		  grid[M.getButton().getRow()][M.getButton().getCol()] = new Tree_Button(new ImageIcon("img/Tree3.png"));
		  M = null;
		  spawnMonster();
		  boolean b = USER.monsterKilled();
		  if (b){
			  txtarea.setText("Congratulations, you defeated a monster, and captured its treasure! You got a(n):\n"
				  		+ "\n"+reward.getType()+": "+reward.getStats()+"\nDescription on tag: "+reward.getDescription()
				  		+"\n\nAnd you got "+new Integer(gold).toString()+" gold pieces!"
				  		+"\n\n\nHooray! You gained a Level!!");
			  frame.setTitle("You Gained A Level!");
		  }
		  else{
			  txtarea.setText("Congratulations, you defeated a monster, and captured its treasure! You got a(n):\n"
			  		+ "\n"+reward.getType()+": "+reward.getStats()+"\nDescription on tag: "+reward.getDescription()
			  		+"\n\nAnd you got "+new Integer(gold).toString()+" gold pieces!");
			  frame.setTitle("You Defeated A Monster!");
		  }
		  btn1.setVisible(false);
		  txt1.setVisible(false);
		  frame.setSize(800,500);
		  frame.setVisible(true);
		  reDisplay();
	  }
	  public void spawnMonster(){
		  int r = (int)(rows*Math.random());
		  int c = (int)(cols*Math.random());
		  int row = USER.getButton().getRow();
		  int col = USER.getButton().getCol();
		  while (row == r && col == c){
			  r = (int)(rows*Math.random());
		      c = (int)(cols*Math.random());
		  }
		  M = pool.getMonster(USER.getLevel());
		  grid[r][c] = M.getButton();
		  grid[r][c].addKeyListener(new Keys());
		  grid[r][c].addActionListener(new Buttons());
		  (M.getButton()).setSpot(r,c);
		  reDisplay();
	  }
	  public void displayInventory(){
		  ArrayList<Weapon>weps = USER.getWepsInInventory();
		  ArrayList<Potion>pots = USER.getPotsInInventory();
		  ArrayList<ArmorItem>arms = USER.getArmsInInventory();
		  txtarea.setText("Your inventory contains:\n");
		  if (weps != null && weps.size() > 0){
		    for(Weapon wep:weps){
			  txtarea.append(wep.getStats()+", Description on tag: "+wep.getDescription()+"\n");
		    }
		  }
		  if (pots != null && pots.size() > 0){
		    for(Potion pot:pots){
			  txtarea.append(pot.getStats()+", Description on tag: "+pot.getDescription()+"\n");
		    }
		  }
		  if (arms != null && arms.size() > 0){
		    for(ArmorItem arm:arms){
			  txtarea.append(arm.getStats()+", Description on tag: "+arm.getDescription()+"\n");
		    }
		  }
		  txtarea.append("\n Enter /advance to move forwards, or /help for commands");
	  }
	  public void usePotion(String str){
		  String pname = str.substring(11);
		  ArrayList<Potion>pots = MES_Panel.USER.getPotsInInventory();
		  for(Potion pot:pots){
			  if ((pot.getName().toLowerCase()).equals(pname)){
				  MES_Panel.USER.usePotion(pot);
				  txtarea.setText("You used a: "+pot.getName()+" potion!\n Enter /advance to move forwards, or /help for commands");
				  return;
			  }
		  }
	  }
	  public void throwDagger(){
		  if (USER.canThrow(W)){
			  USER.throwDagger(M,W);
			  ArrayList<Weapon>weps = USER.getWepsInInventory();
			  W = weps.get(0);
		  }
		  reDisplay();
		  if( M != null && M.isDead())
				 awardTreasure();
	  }
	  public void hammerTime(){
		  if (USER.canSmash(W)){
			  USER.hammerSmash(M,W);
		  }
		  reDisplay();
		  if( M != null && M.isDead())
				 awardTreasure();

	  }
	  public void staffAttack(){
		  if (USER.canSmite(W)){
			  USER.staffSmite(M,W);
		  }
		  reDisplay();
		  if( M != null && M.isDead())
				 awardTreasure();

	  }
	  public void swordBlock(){
		  if (USER.canBlock(W)){
			  USER.blockBlow(M,W);
		  }
		  reDisplay();
		  if( M != null && M.isDead())
				 awardTreasure();

	  }
	  public boolean runAway(){
		  if (USER.canRun(M)){
			  M = null;
			  return true;
		  }
		  return false;
	  }
	  public void equipArmor(){
		  String ename = str1.substring(12);
		  ArrayList<ArmorItem>arms = USER.getArmsInInventory();
		  for(ArmorItem arm:arms){
			  if ((arm.getName().toLowerCase()).equals(ename)){
				  USER.useArmorItem(arm);
				  txtarea.setText("You equipped a: "+arm.getName()+" Armor Item!\n Enter /advance to move forwards, or /help for commands");
				  return;
			  }
		  }
		  txtarea.setText("That Armor Item name is invalid, please try again\n\nEnter /advance to move forwards, or /help for commands");
	  }
	  public void usePotionOn(){
		  String pname = str1.substring(13);
		  ArrayList<Potion>pots = USER.getPotsInInventory();
		  for(Potion pot:pots){
			  if ((pot.getName().toLowerCase()).equals(pname)){
				  M.usePotionOn(pot);
				  txtarea.setText("You used a: "+pot.getName()+" potion on "+M.getName()+"!\n Enter /advance to move forwards, or /help for commands");
				  return;
			  }
		  }
		  txtarea.setText("That Potion name is invalid, please try again\nEnter /advance to move forwards, or /help for commands");
	  }
	  public void dropItem(){
		  String iname = str1.substring(9);
		  if(iname.equals("Fisticuffs")){
			  txtarea.setText("You can't lop off your hands! Keep your Fisticuffs!");
			  return;
		  }
		  else{ 
			  ArrayList<Item>items = (USER).getInventory();
			  for(Item i:items){
				  if (i.getName().equals(iname))
					  (USER).removeFromInventory(i); 
			  }
		  }	  
	  }
	  public int getGold(){
		  if(M == null)
			  return 0;
		  return ((int)(50*Math.random())+1)*M.getLevel();
	  }
	  public void specialAttack(){
		  int row = USER.getButton().getRow();
		  int col = USER.getButton().getCol();
		  if(M == null){
			  spawnMonster();
			  return;
		  }
		  int r = M.getButton().getRow();
		  int c = M.getButton().getCol();
		  if((W != null) && (M.isDead()==false)){
			  if (checkAdjacent(row,col,r,c)){
				  if(USER instanceof Assassin){
					  throwDagger();
				  }
				  else if (USER instanceof Brute){
					  hammerTime();
				  }
				  else if (USER instanceof Mage){
					  staffAttack();
				  }
				  else if (USER instanceof Warrior){
					  swordBlock();
				  }
			  }
		  }
	  }
	  
	  public void getFromTextBox(){
	  String str = txt1.getText(); 
	  str1 = str;
	  String n = str1;
	  str1 = str1.toLowerCase();
	  if ((n.length() > 5) && (n.substring(0,5).equals("/name") || n.substring(0,5).equals("/Name")))
	   setPlayerName(n);
	  else if (str1.equals("/assassin") || (str1.equals("/brute")) || (str1.equals("/mage")) || (str1.equals("/warrior")))
		  setPlayerType(str1); 
	  txt1.setText("");
  }
  public void movePlayerRight(){
	  Player_Button p = USER.getButton();
	  int r = p.getRow();
	  int c = p.getCol();
	  if (c+1 < cols && (grid[r][c+1]) instanceof Tree_Button){
		  JButton t = grid[r][c+1];
		  grid[r][c+1] = grid[r][c];
		  grid[r][c] = t;
		  ((Player_Button)grid[r][c+1]).setSpot(r,c+1);
		  ((Tree_Button)grid[r][c]).setSpot(r,c);
	  }  
	  reDisplay();  
  }
  public void movePlayerLeft(){
	  Player_Button p = USER.getButton();
	  int r = p.getRow();
	  int c = p.getCol();
	  if (c-1 >= 0 && (grid[r][c-1]) instanceof Tree_Button){
		  JButton t = grid[r][c-1];
		  grid[r][c-1] = grid[r][c];
		  grid[r][c] = t;
		  ((Player_Button)grid[r][c-1]).setSpot(r,c-1);
		  ((Tree_Button)grid[r][c]).setSpot(r,c);
	  }  
	  reDisplay();  
  }
  public void movePlayerUp(){
	  Player_Button p = USER.getButton();
	  int r = p.getRow();
	  int c = p.getCol();
	  if (r-1 >= 0 && (grid[r-1][c]) instanceof Tree_Button){
		  JButton t = grid[r-1][c];
		  grid[r-1][c] = grid[r][c];
		  grid[r][c] = t;
		  ((Player_Button)grid[r-1][c]).setSpot(r-1,c);
		  ((Tree_Button)grid[r][c]).setSpot(r,c);
	  }  
	  reDisplay();  
  }
  public void movePlayerDown(){
	  Player_Button p = USER.getButton();
	  int r = p.getRow();
	  int c = p.getCol();
	  if (r+1 < rows && (grid[r+1][c]) instanceof Tree_Button){
		  JButton t = grid[r+1][c];
		  grid[r+1][c] = grid[r][c];
		  grid[r][c] = t;
		  ((Player_Button)grid[r+1][c]).setSpot(r+1,c);
		  ((Tree_Button)grid[r][c]).setSpot(r,c);
	  }  
	  reDisplay();  
  }
  private class Buttons implements ActionListener { 
	  public void actionPerformed(ActionEvent e) { 
		  buttonClicked(e.getSource());
  }  
}
  private class TextField implements ActionListener { 
	  public void actionPerformed(ActionEvent e) { 
	  getFromTextBox();
  }  
}
  private class Key1 implements KeyListener {
	  public void keyPressed( KeyEvent e){
		 if (e.getKeyCode() == KeyEvent.VK_ENTER) 
		   getFromTextBox();
	  }
	  public void keyReleased( KeyEvent e){ 
		  
	  }
	  public void keyTyped( KeyEvent e){
	     if (e.getKeyCode() == KeyEvent.VK_ENTER) 
		  getFromTextBox();
	  }
  }
  private class Keys extends KeyAdapter {
	  public void keyPressed( KeyEvent e){
		 if (e.getKeyCode() == KeyEvent.VK_KP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT){ 
		   movePlayerRight();
		 }
		 else if (e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) 
			   movePlayerLeft();
		 else if (e.getKeyCode() == KeyEvent.VK_KP_DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) 
			   movePlayerDown();
		 else if (e.getKeyCode() == KeyEvent.VK_KP_UP || e.getKeyCode() == KeyEvent.VK_UP) 
			   movePlayerUp();
		 else if (e.getKeyCode() == KeyEvent.VK_A)
			 attackMonster();
		 else if (e.getKeyCode() == KeyEvent.VK_S)
			 specialAttack();
	  }
	  public void keyTyped( KeyEvent e){
		  if (e.getKeyCode() == KeyEvent.VK_KP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT){ 
			   movePlayerRight();
			 }
			 else if (e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) 
				   movePlayerLeft();
		     else if (e.getKeyCode() == KeyEvent.VK_KP_DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) 
				   movePlayerDown();
			 else if (e.getKeyCode() == KeyEvent.VK_KP_UP || e.getKeyCode() == KeyEvent.VK_UP) 
				   movePlayerUp();
			 else if (e.getKeyCode() == KeyEvent.VK_A)
				 attackMonster();
			 else if (e.getKeyCode() == KeyEvent.VK_S)
				 specialAttack();
	  }
  }
  public void reDisplay(){
	  JPanel panel = new JPanel();
	  panel.setLayout(new GridLayout(rows,cols));
	  for(int r = 0; r < rows; r++){
		  for (int c = 0; c < cols; c++){
			  panel.add(grid[r][c]); 
		  }
	  }
	  panel2.setVisible(false);
	  panel.setVisible(true);
	  add(panel, BorderLayout.CENTER );
	  setVisible(true);
	  grid[0][0].grabFocus();
	  panel2 = panel;
  }
}