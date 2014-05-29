import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;

public class MES_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txt1 = new JTextField();
	private JButton btn1 = new JButton( "Submit" );
	//receives all MES_Panel.USER input
	private String str1 = "";
	//input from MES_Panel.USER
	public static Player USER;
	//represents current player
	private String name;
	//represents player name
	private JTextArea txtarea = new JTextArea("Enter the name you want for your character\n\nEnter the name in the form of /name=tommy");
	//contains game output
	private Font segoe = new Font("Segoe UI", Font.PLAIN, 18);
	//font for entire game
	private MonsterPool pool = new MonsterPool();
	//instance of monster pool for getting monsters
	public static Monster m;
	//current monster MES_Panel.USER is fighting
	public static Weapon w;
	//current weapon MES_Panel.USER is using
	private boolean bool = true;
	//used for figuring out if MES_Panel.USER mutilated the bodies for an extra level yet
	private TreasureChest chest = new TreasureChest();
	//instance of treasure chest for rewards
	private Merchant mihir=new Merchant();
	//merchant for purchases
	
  public MES_Panel() {
    Dimension screensize= Toolkit.getDefaultToolkit().getScreenSize();
	double height = screensize.getHeight();
	double width = screensize.getWidth();
	setPreferredSize(new Dimension((int)width,((int)height/5)-40));
	setLayout( new BorderLayout() );
	txtarea.setFont(segoe);
	txtarea.setEditable(false);
	txtarea.setLineWrap(false);
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(1,2));
	txt1.setPreferredSize(new Dimension(50,50));
	btn1.setPreferredSize(new Dimension(50,50));
	txt1.setFont(segoe);
	btn1.setFont(segoe);
	panel.add(txt1);
	panel.add(btn1);
	btn1.addActionListener( new TextField() );
	txt1.addKeyListener(new Key1());
	ScrollPane p = new ScrollPane();
	JPanel panels = new JPanel();
	panels.setLayout(new BorderLayout());
	panels.add(txtarea,BorderLayout.CENTER);
	panels.add(panel,BorderLayout.SOUTH);
	p.add(panels);
	add(p);
  }
  public void help(){
    txtarea.setText("/fight -(Deprecated) spawns a monster for you to fight\n"
    		+ "/attack - attacks the monster you are fighting with your current weapon\n"
    		+ "/run - attempts to escape the monster\n"
    		+ "/currentWeapon=(weapon name) - sets your current weapon, weapon name should be entered as it appears in your inventory\n"
    		+ "/displayInventory - displays your weapons,potions,and armor\n"
    		+ "/usePotion=(Potion name) - uses a Potion from your inventory, potion name shoudl be entered as it appears in your inventory\n"
    		+ "/throwDagger - throws your dagger, provided the dagger your current weapon\n"
    		+ "/hammerSmash - uses your hammer to bash the enemy's skull in, provided the hammer is your current weapon\n"
    		+ "/staffSmite - uses your staff to smite a monster, provided the staff is your current weapon\n"
    		+ "/swordBlock - uses your sword to block a monster's blow and damage it some, provided the sword is your current weapon\n"
    		+ "/stats - displays current player stats\n"
    		+ "/equipArmor=(Armor piece name) - equips a piece of armor onto your player\n"
    		+ "/dropItem=(Item name) - drops an item from your inventory to lower pack weight\n"
    		+ "/usePotionOn=(Potion name) - Uses a potion on the current enemy\n"
    		+ "/advance - moves player forward in the game, to be used at the end of each main event\n"
    		+ "/seeMerchant - allows the player to use a merchant visit to see a merchants wares\n"
    		+ "/buy=(Item name) - allows a player to purchase one of a merchant's items\n"
    		+ "/exit - exits the game");    
  }
  //displays all game commands
  public void askPlayerType(){
	  txtarea.setText("Enter the class you want to be in the box, from the choices below, ex. /mage (/ first is required)\n"
	  		+ "\nAssassin - low health, high damage, high speed - starts with Lil Stabber\n"
	  		+ "\nBrute - high health, low damage, low speed - starts with Smashy Smashy\n"
	  		+ "\nMage - low health, high damage, med speed - starts with Wacky Stick\n"
	  		+ "\nWarrior - med health, med damage, med speed - starts with Big Bertha\n");
  }

  public void setPlayerName(String n){
	  name = n.substring(6);
	  askPlayerType();
  }
  public void setPlayerType(String type){
	  type = type.substring(1);
	  type = type.toLowerCase();
	  if (type.equals("assassin"))
		  MES_Panel.USER = new Assassin(name);
	  else if (type.equals("brute"))
		  MES_Panel.USER = new Brute(name);
	  else if (type.equals("mage"))
		  MES_Panel.USER = new Mage(name);
	  else
		  MES_Panel.USER = new Warrior(name);
	  playerStats();
	  txtarea.append("\nEnter /fight to play or /help for commmands"
	  		+ "\nEnjoy the Game!");
	  updatePanels();
  }
  public void playerStats(){
	  txtarea.setText("Hello "+MES_Panel.USER.getName()+"!\n"+"You are a: "+MES_Panel.USER.getType()+"\n");
	  ArrayList<Weapon>weps = MES_Panel.USER.getWepsInInventory();
	  for (Weapon wep:weps){
		  txtarea.append(wep.getStats()+", Description on tag: "+wep.getDescription()+"\n");
	  }
	  txtarea.append("\n"+MES_Panel.USER.getStats()+"\n");
	  w = weps.get(1);
	  updatePanels();
  }
  public void advancePlayer(){
	 double rand= Math.random();
	 if(rand<0.6)
		 fightMonster();
	 else
		 encounterTreasureRoom();
	 updatePanels();
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
	  updatePanels();
  }
  public void seeMerchant(){
	  if(USER.getMerchVisits()>0){
		  mihir=new Merchant();
		  ArrayList<Item> wares= mihir.getWares();
		  txtarea.setText("You stumble upon a humble merchant in the woods and he offers to sell you:\n");
		  for(Item i: wares)
			  txtarea.append(i.getStats()+", Description on tag: "+i.getDescription()+", Cost:"+i.getValue()+"\n");
		  txtarea.append("\nIf you wish to purchase an item, use that /buy=(item name) command.");
		  USER.removeMerchVisit();
	  }
	  else
		  txtarea.setText("You cannot seem to find the merchant... oh well.");
	  updatePanels();	  
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
	  updatePanels();
  }
  public void fightMonster(){
	  if (m == null){
	  m = pool.getMonster(MES_Panel.USER.getLevel());
	  txtarea.setText("You have encountered a monster!"+"\n"+m.getName()+": "+m.getStats()+"\n          VS:\n"+MES_Panel.USER.getStats()+"\n\nTo attack "
		  		+ "say /attack\nTo use a potion, say /usePotion=(potion name)\n To use a potion on the monster, say /usePotionOn=(potion name)"
		  		+ "\nTo attempt to run, say /run\nTo drop items from your pack to lower pack weight, say /dropItem=(Item name)\nOr /help for commands\n");
	  }
	  else
	    updateBattleStats();
	  updatePanels();
  }
  public void updateBattleStats(){
	  if (m != null && m.isDead() == false){
	  txtarea.setText("You are fighting a: "+"\n"+m.getName()+": "+m.getStats()+"\n\tVS:\n"+MES_Panel.USER.getStats()+"\n\nTo attack "
		  		+ "say /attack\nTo use a potion, say /usePotion=(potion name)\n To use a potion on the monster, say /usePotionOn=(potion name)"
		  		+ "\nTo attempt to run, say /run\nTo drop items from your pack to lower pack weight, say /dropItem=(Item name)\nOr /help for commands\n");
	  }
	  else if (m == null && bool == false)
		  txtarea.setText("Ummm, stop attacking the air, what did it do to you? Enter /fight to fight a monster, or /help for commands");
	  else if( m != null && m.isDead())
			 awardTreasure();
		 else{
			 if (bool){
				 if (MES_Panel.USER.getLevel() < 9){
			       txtarea.setText("Haha! You mutilate the bodies and get an extra kill! (usable only once)\nEnter /fight to fight a monster, or /help for commands");			 
			        boolean b = MES_Panel.USER.monsterKilled();
			        if(b)
			        	txtarea.append("\n\nHooray! You Gained a Level!");
				 }
				 else
					 txtarea.setText("Sorry, you can't mutilate the bodies to get to level 10!, you have to beat the last boss first!\n Enter /fight to fight a monster, or /help for commands");
			 bool = false;
			 }
			 else
				 txtarea.setText("You have already mutilated the bodies! There's nothing left to attack\n Enter /fight to fight a monster, or /help for commands");
		 }
	  updatePanels(); 
  }
  public void attackMonster(){
	 if (m != null && m.isDead()==false){
		  MES_Panel.USER.attack(m,w);
		  updateBattleStats();
	 }
	 else if (m == null && bool == false)
	  txtarea.setText("Ummm, stop attacking the air, what did it ever do to you?\n\nEnter /fight to fight a monster, or /help for commands");
	 else if( m != null && m.isDead())
		 awardTreasure();
	 else{
		 if(bool){
			 if (MES_Panel.USER.getLevel() < 9){
		       txtarea.setText("Haha! You mutilate the bodies and get an extra kill (usable only once)\n\nEnter /fight to fight a monster, or /help for commands");			 
		        MES_Panel.USER.monsterKilled();
			 }
			 else
				 txtarea.setText("Sorry, you can't mutilate the bodies to get to level 10!, you have to beat the last boss first!\n\nEnter /fight to fight a monster, or /help for commands");
			 bool = false;
		 }
		 else
			 txtarea.setText("You have already mutilated the bodies! There's nothing left to attack\n\nEnter /fight to fight a monster, or /help for commands");
	 }
	 updatePanels();
  } 
  public void awardTreasure(){
	  int gold = getGold();
	  Item reward = chest.getReward();
	  MES_Panel.USER.addToInventory(reward);
	  MES_Panel.USER.addGold(gold);
	  txtarea.setText("Congratulations, you defeated a monster, and captured its treasure! You got a(n):\n"
	  		+ "\n"+reward.getType()+": "+reward.getStats()+"\nDescription on tag: "+reward.getDescription()+"\n\nAnd you got "+new Integer(gold).toString()+" gold pieces!"+"\n\nEnter /fight to fight a monster, or /help for commands");
	  m = null;
	  boolean b = MES_Panel.USER.monsterKilled();
	  if (b)
		  txtarea.append("\n\nHooray! You Gained a Level!");
	  updatePanels();
  }
  public void setCurrentWeapon(String str){
	  ArrayList<Weapon>weps = MES_Panel.USER.getWepsInInventory();
	  str = str.substring(15);
	  for (Weapon wep:weps){
		  if ((wep.getName().toLowerCase()).equals(str)){
			  w = wep;
			  txtarea.setText("Your current weapon is now:\n"+wep.getName()+"\n\nEnter /fight to fight a monster, or /help for commands");
			  updatePanels();
			  return;
		  }
	  }
	  txtarea.setText("That Weapon name is invalid, please try again\n\nEnter /fight to fight a monster, or /help for commands");
	  updatePanels();
  }
  public void displayInventory(){
	  ArrayList<Weapon>weps = MES_Panel.USER.getWepsInInventory();
	  ArrayList<Potion>pots = MES_Panel.USER.getPotsInInventory();
	  ArrayList<ArmorItem>arms = MES_Panel.USER.getArmsInInventory();
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
	  txtarea.append("\n Enter /fight to fight a monster, or /help for commands");
	  updatePanels();
  }
  public void usePotion(String str){
	  String pname = str.substring(11);
	  ArrayList<Potion>pots = MES_Panel.USER.getPotsInInventory();
	  for(Potion pot:pots){
		  if ((pot.getName().toLowerCase()).equals(pname)){
			  MES_Panel.USER.usePotion(pot);
			  txtarea.setText("You used a: "+pot.getName()+" potion!\n Enter /fight to fight a monster, or /help for commands");
			  updatePanels();
			  return;
		  }
	  }
	  txtarea.setText("That Potion name is invalid, please try again\nEnter /fight to fight a monster, or /help for commands");
	  updatePanels();
  }
  public void throwDagger(){
	  if (MES_Panel.USER.canThrow(w)){
		  MES_Panel.USER.throwDagger(m,w);
		  ArrayList<Weapon>weps = MES_Panel.USER.getWepsInInventory();
		  w = weps.get(0);
		  updateBattleStats();
		  txtarea.append("\n\nYou have lost your dagger!");
	  }
	  else
		  txtarea.setText("That's not a dagger, so you can't throw it. Try changing your current weapon, or /help");
	   
	  updatePanels();
  }
  public void hammerTime(){
	  if (MES_Panel.USER.canSmash(w)){
		  MES_Panel.USER.hammerSmash(m,w);
		  updateBattleStats();
	  }
	  else
		  txtarea.setText("That's not a hammer, so you can't bash heads in. Try changing your current weapon, or /help");
		 
	  updatePanels();
  }
  public void staffAttack(){
	  if (MES_Panel.USER.canSmite(w)){
		  MES_Panel.USER.staffSmite(m,w);
		  updateBattleStats();
	  }
	  else
		  txtarea.setText("That's not a staff, so you can't smite monsters with it. Try changing your current weapon, or /help");
	  updatePanels();
  }
  public void swordBlock(){
	  if (MES_Panel.USER.canBlock(w)){
		  MES_Panel.USER.blockBlow(m,w);
		  updateBattleStats();
	  }
	  else
		  txtarea.setText("That's not a sword, so you can't block attacks or lop heads off with it. Try changing your current weapon, or /help");
	  updatePanels();
  }
  public void runAway(){
	  if (MES_Panel.USER.canRun(m)){
		  txtarea.setText("Whew, got away in one piece\nEnter /fight to fight a monster, or /help for commands");
		  m = null;
	  }
	  else
		  txtarea.setText("You can't outrun that monster!, try emptying things from your inventory to lose weight!\nEnter dropItem=(Item name) to lower your pack weight\nEnter /fight to fight a monster, or /help for commands");
	  updatePanels();
  }
  public void equipArmor(){
	  String ename = str1.substring(12);
	  ArrayList<ArmorItem>arms = MES_Panel.USER.getArmsInInventory();
	  for(ArmorItem arm:arms){
		  if ((arm.getName().toLowerCase()).equals(ename)){
			  MES_Panel.USER.useArmorItem(arm);
			  txtarea.setText("You equipped a: "+arm.getName()+" Armor Item!\n Enter /fight to fight a monster, or /help for commands");
			  updatePanels();
			  return;
		  }
	  }
	  txtarea.setText("That Armor Item name is invalid, please try again\n\nEnter /fight to fight a monster, or /help for commands");
	  updatePanels();
  }
  public void usePotionOn(){
	  String pname = str1.substring(13);
	  ArrayList<Potion>pots = MES_Panel.USER.getPotsInInventory();
	  for(Potion pot:pots){
		  if ((pot.getName().toLowerCase()).equals(pname)){
			  m.usePotionOn(pot);
			  txtarea.setText("You used a: "+pot.getName()+" potion on "+m.getName()+"!\n Enter /fight to fight a monster, or /help for commands");
			  updatePanels();
			  return;
		  }
	  }
	  txtarea.setText("That Potion name is invalid, please try again\nEnter /fight to fight a monster, or /help for commands");
	  updatePanels(); 
  }
  public void dropItem(){
	  String iname = str1.substring(9);
	  if(iname.equals("Fisticuffs")){
		  txtarea.setText("You can't lop off your hands! Keep your Fisticuffs!");
		  return;
	  }
	  else{ 
		  ArrayList<Item>items = (MES_Panel.USER).getInventory();
		  for(Item i:items){
			  if (i.getName().equals(iname))
				  (MES_Panel.USER).removeFromInventory(i); 
		  }
	  }	 
	updatePanels();	 
  }
  public int getGold(){
	  if(m == null)
		  return 0;
	  return ((int)(50*Math.random())+1)*m.getLevel();
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
	  else if (str1.equals("/advance"))
		  advancePlayer();
	  else if (str1.equals("/fight"))  
	      fightMonster();
	  else if (str1.equals("/attack"))
		  attackMonster();
	  else if (str1.equals("/run"))
		  runAway();
	  else if (str1.equals("/exit"))
		  System.exit(0);
	  else if (str1.length() > 15 && str1.substring(0,15).equals("/currentweapon="))
		  setCurrentWeapon(str1);
	  else if (str1.equals("/displayinventory"))
		  displayInventory();
	  else if (str1.length() > 11 && str1.substring(0,11).equals("/usepotion="))
		  usePotion(str1);
	  else if (str1.equals("/throwdagger"))
		  throwDagger();
	  else if (str1.equals("/hammersmash"))
		  hammerTime(); //stahp, hamma' time
	  else if (str1.equals("/staffsmite"))
		  staffAttack();
	  else if (str1.equals("/swordblock"))
		  swordBlock();
	  else if (str1.equals("/help"))
		  help();
	  else if(str1.equals("/seemerchant"))
		  seeMerchant();
	  else if (str1.equals("/stats"))
		  playerStats();
	  else if (str1.length() > 12 && str1.substring(0,12).equals("/equiparmor="))
		  equipArmor();
	  else if (str1.length() > 13 && str1.substring(0,13).equals("/usepotionon="))
		  usePotionOn();
	  else if (str1.length() > 5 && str1.substring(0,5).equals("/buy="))
		  purchaseItem(str1.substring(5,str1.length()));
	  else if (str1.length() > 9 && str1.substring(0,9).equals("/dropitem="))
		  dropItem();
	  else
		  txtarea.setText("Sorry, invalid command, try /help for the commands list");
	  txt1.setText("");
  }
  public void updatePanels(){
	  Inventory_Panel.updateDisplay();
	  BattleStats_Panel.updateDisplay();
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
}

