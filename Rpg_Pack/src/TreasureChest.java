import java.util.ArrayList;

public class TreasureChest {
	//define all items that may appear below and add them all to ITEMS
	private Weapon wep1 = new Sword("Long Broadsword",6,10,"A nice, big sword great for blocking",25);
	private Weapon wep2 = new Dagger("Dark, Sneaky Dagger",4,1.6,"A creepy little dagger that's great for stabbing from behind",30);
	private Weapon wep3 = new Hammer("TENderizing Hammer",5,3,"A nice little hammer, great for whacking things",25);
	private Weapon wep4 = new Hammer("ELEVENderizing Hammer",8,4,"Well, it's one more than a TENderizing hammer, obviously",30);
	private Weapon wep5 = new Staff("Old Classic",5,"Your nice, ol' standard typical wand",35);
	private Weapon wep6 = new Sword("Two-sided Short Sword",8,4,"A rather menacing-looking double ended skewer",60);
	private Weapon wep7 = new Sword("Nobitsura Kage",15,2,"A thin but powerful katana infused with the power of the demonic underworld",100);
	private Potion pot1 = new Potion(30,"HEAL","Small Health Pack","Adds a small amount of health to your HP",20);
	private Potion pot2 = new Potion(60,"HEAL","Large Health Pack","Adds a large amount of health to your HP",35);
	private Potion pot3 = new Potion(2,"SWIFT","Speed Boost","Ups your speed stat a small amount",35);
	private Potion pot4 = new Potion(4,"SWIFT","Turbo Boost", "Ups your speed stat a lot",45);
	private Potion pot5 = new Potion(4,"DAMAGE","Pretty Butterflies","Briefly distracts the enemy, causing you to do extra damage",35);
	private Potion pot6 = new Potion(15,"DAMAGE","Small RPG","Portable, Shoulder-Launching RPG that really puts a crimp in that monsters day",60);
	private Potion pot7 = new Potion(5,"DAMAGE","Hey!, Look over there!","Temporarily confuses the enemy, while he tries to figure out what the hell you're pointing at, which allows you to do extra damage",40);
	private Potion pot8 = new Potion(8,"POISON","Small Bottle of HCl","A bone-disintegrating formula of hydrochloric acid, great for melting down enemies",50);
	private Potion pot9 = new Potion(4,"SLOW","2-Part Epoxy","Now only if we could get him to stand still long enough for it to fully harden",40);
	private Potion pot10 = new Potion(3,"WEAK","Parasitic Sushi","Gives the monster a terrible upset stomach, and slowly chews up his muscles",25);
	private ArmorItem arm1 = new ArmorItem("Helm of Headbutting", "HEAL","SLOW","Great for using your head as a battering ram",7,2,35);
	private ArmorItem arm2 = new ArmorItem("Boots of Running Really Fast", "SWIFT","DAMAGE","Great for escaping unwanted monsters, but also twisting ankles",3,1,35);
	
	Item[] ITEMS={wep1,wep2,wep3,wep4,wep5,wep6,wep7,pot1,pot2,pot3,pot4,pot5,pot6,pot7,pot8,pot9,pot10,arm1,arm2};
	
	public TreasureChest(){
		
	}
	public Item getReward(){
		Item x;
		int rand= (int)(ITEMS.length*Math.random());
		x= ITEMS[rand];
		return x;
	}
	public ArrayList<Item> getItems(){
		ArrayList<Item> its= new ArrayList<Item>();
		for(int i=0; i<ITEMS.length;i++)
			its.add(ITEMS[i]);
		return its;
	}

}
