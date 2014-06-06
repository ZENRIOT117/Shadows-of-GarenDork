import java.io.*;
import java.util.*;
import javax.swing.*;

public class SaveLoad {
	
	//private ArrayList<Item> in = new ArrayList<Item>();
	private Item[] items;
	private Inventory invent;
	private Monster monster;
	private Player user;
	
	public SaveLoad(Player use, Monster mon, Inventory inv){
		invent=inv;
		user=use;
		monster=mon;
	}
	public SaveLoad(){
	}
	public void saveGame(){
		ArrayList<Item> items = invent.getInventory();
		try{
			FileWriter fw = new FileWriter("src/sav.txt");
			fw.write(user.getName()+"@" //0
			+user.getLevel()+"@" //1
			+user.getHealth()+"@" //2
			+user.getDamage()+"@" //3
			+user.getSpeed()+"@" //4
			+user.getType()+"@" //5
			+user.getPotionEffect()+"@" //6
			+user.getEffect()+"@" //7
			+user.getMonstersKilled()+"@" //8
			+user.getCurrentMonstersKilled()+"@"//9
			+user.getMerchVisits()+"@" //10
			+user.getGold()+"@" //11
			+monster.getName()+"@" //12
			+monster.getLevel()+"@"//13
			+monster.getHealth()+"@"//14
			+monster.getSpeed()+"@"//15
			+monster.getDamage()+"@");//16
			if (user.getButton() != null){
				fw.append(user.getButton().getRow()+"@" //17
						+user.getButton().getCol()+"@");//18
			}
			else
				fw.append(" @"+" @");
			if (monster.getButton() != null){
				fw.append(monster.getButton().getRow()+"@" //19
						+monster.getButton().getCol()+"@");//20
			}
			else
				fw.append(" @"+" @");
			fw.append(user.getButton().getPath()+"@"); //22
			fw.append(monster.getButton().getPath()+"@"); //23
			for(Item i:items){
				fw.append(i.getName()+"@");
			}
			fw.close();
		}
		catch(IOException el){
		}
	}
	public void loadGame() {
		String[] list = new String[0];
		try{
			FileReader fileReader = new FileReader("src/sav.txt");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        List<String> lines = new ArrayList<String>();
	        String line = null;
	        while ((line = bufferedReader.readLine()) != null) {
	            lines.add(line);
	        }
	        bufferedReader.close();
	        list = lines.toArray(new String[lines.size()]);
		} catch(IOException e){
		}
		list = list[0].split("@");
		if (list[5].equals("Assassin")){
			user = new Assassin(list[0]);
		}
		else if (list[5].equals("Brute")){
			user = new Brute(list[0]);
		}
		else if (list[5].equals("Mage")){
			user = new Mage(list[0]);
		}
		else if (list[5].equals("Warrior")){
			user = new Warrior(list[0]);
		}
		MonsterPool pool = new MonsterPool();
		TreasureChest chest = new TreasureChest();
		monster = pool.returnMonster(list[12]);
		user.setLevel(Integer.parseInt(list[1]));
		user.setHealth(Integer.parseInt(list[2]));
		user.setDamage(Integer.parseInt(list[3]));
		user.setSpeed(Integer.parseInt(list[4]));
		user.applyPotionEffect((list[6]));
		user.applyEffect((list[7]));
		user.setMonstersKilled(Integer.parseInt(list[8]));
		user.setCurrentMonstersKilled(Integer.parseInt(list[9]));
		user.setMerchVisits(Integer.parseInt(list[10]));
		user.setGold(Integer.parseInt(list[11]));
		if(list[17].equals(" ")){	
		}
		else {
			Player_Button pb = new Player_Button(new ImageIcon(list[21]),list[21],list[0]);
			pb.setSpot(Integer.parseInt(list[17]),Integer.parseInt(list[18]));
			user.setButton(pb);
		}
		monster.setLevel(Integer.parseInt(list[13]));
		monster.setHealth(Integer.parseInt(list[14]));
		monster.setDamage(Integer.parseInt(list[15]));
		monster.setSpeed(Integer.parseInt(list[16]));
		if (list[19].equals(" ")){	
		}
		else{
			Monster_Button mb = new Monster_Button(new ImageIcon(list[22]),list[22],list[12]);
			mb.setSpot( Integer.parseInt(list[19]),Integer.parseInt(list[20]));
			monster.setButton(mb);
		}
		items = new Item[list.length-23];
		int count = 0;
		for(int i = 23; i < list.length; i++ ){
			Item it = chest.returnItem(list[i]);
			//in.add(it);
			//invent.addToInvent(it);
			items[count] = it;
			count++;
		}
		Item i = chest.returnItem(list[23]);
		user.setCurrentPanelWeapon((Weapon)i);
		//user.setInvent(invent);
	}
	public Player getUser(){
		return user;
	}
	public Monster getMonster(){
		return monster;
	}
	public Inventory getInvent(){
		return invent;
	}
	public Item[] getItems(){
		return items;
	}
}
