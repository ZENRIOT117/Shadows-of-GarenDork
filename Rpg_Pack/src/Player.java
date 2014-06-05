import java.util.ArrayList;

public class Player {
	private String name;
	private int level;
	private int health;
	private int speed;
	private String type;
	private int damage;
	private boolean isAlive= true;
	private ArrayList<String> armorEffects = new ArrayList<String>();
	private ArrayList<String> armorSideEffects = new ArrayList<String>();
	private String potionEffect = "NONE";
	private String currentMonsterEffect = "NONE";
	private Inventory inventory= new Inventory();
	private Weapon fists= new Weapon("Fisticuffs",1,"Ye bare knuckles.", 0);
	private int monstersKilled = 0;
	private int currentMonstersKilled = 0;
	private int goldPieces = 0;
	private int merchvisits=2;
	private Player_Button pb;
	
	public Player(){
		//yay for undefined everything
	}
	public Player(String n){
		name=n;
		health= 1;
		speed=1;
		type="UND";
		damage=1;
		level=1;
		armorEffects.add("NONE");
		armorSideEffects.add("NONE");
		inventory.addToInvent(fists);
	}
	//constructs a basic player SHOULD NEVER BE USED ON ITS OWN
	public boolean isDead(){
		if(!(isAlive))
			return true;
		else
			return false;
	}
	//returns if the player has died or not
	public int getLevel(){
		return level;
	}
	//the current level of the player based on number of monsters killed or treasures found
	public void setLevel(int lev){
		if(lev>=1)
			level=lev;
	}
	//sets player level, NOT to be used to increase level normally
	public boolean monsterKilled(){
		currentMonstersKilled++;
		monstersKilled++;
		if(currentMonstersKilled >= 5){
			level++;
			merchvisits=2;
			currentMonstersKilled = 0;
			return true;
		}
		return false;
	}
	//used to level the player up under normal conditions
	public int getMerchVisits(){
		return merchvisits;
	}
	//returns the amount of times that a player can visit a merchant per level
	public void removeMerchVisit(){
		if(merchvisits>0)
			merchvisits--;
	}
	//removes a merchant visit
	public int getMonstersKilled(){
		return monstersKilled;
	}
	public int getCurrentMonstersKilled(){
		return currentMonstersKilled;
	}
	public int getDamage(){
		return damage;
	}
	//the amount of damage a player can do with current weapon
	public void setDamage(int dama){
		damage=dama;
	}
	//sets player damage
	public void addDamage(int damag){
		if (damag>=1)
			damage+=damag;
	}
	//adds damage done based on new weapons or potions
	public void removeDamage(int dam){
		if(damage-dam>=1)
			damage=damage-dam;
		else
			damage=1;
	}
	//removes damage done based on new weapons or potions
	public String getType(){
		return type;
	}
	//what class a player is
	public void setType(String typ){
		type=typ;
	}
	//sets the string type of the player for use in comparison
	public int getHealth(){
		return health;
	}
	//the current health of the player
	public void setHealth(int healt){
		if(healt>0)
			health=healt;
	}
	//sets player health
	public void addHealth(int heal){
		health+=heal;
	}
	//adds health based on potions or food
	public void removeHealth(int hea){
		if(health-hea>0)
			health= health-hea;
		else
			isAlive=false;
	}
	//removes player health based on damage taken from monsters or potions
	public int getSpeed(){
		return speed;
	}
	//the speed that a player can currently move at accounting for pack weight
	public void setSpeed(int spee){
		if(spee>0)
			speed=spee;
	}
	//sets player speed
	public void addSpeed(int spe){
		speed+=spe;
	}
	//adds speed based on potions and items
	public void removeSpeed(int sp){
		if(speed-sp>0)
			speed=speed-sp;
		else
			speed=1;
	}
	//takes player speed away based on potions or items
	public String getName(){
		return name;
	}
	//returns the players chosen name FOR DISPLAY PURPOSE ONLY
	public boolean canDamage(Monster m, Weapon current){
		if(getDamage()+current.damageModifier()>m.getDamage())
			return true;
		else 
			return false;
	}
	//determines if a player can damage a monster or not
	public void attack(Monster m, Weapon current){
		if(canDamage(m,current))
			m.removeHealth((getDamage()+current.damageModifier()));
		else
			removeHealth(m.getDamage());
	}
	//attacks an enemy once and removes the correct amount of health
	public void canAttack(Monster x){
		
	}
	//determines if a player can attack or not
	public boolean canRun(Monster x){
		if((getSpeed()-inventory.getPackWeight())>=x.getSpeed())
			return true;
		else
			return false;
	}
	//determines if a player can flee from an monster returns true if yes and false if not
	public boolean canBlock(Weapon current){
		if(current instanceof Sword)
			return true;
		else
			return false;	
	}
	//determines if a player can block a monsters blow or not
	public void blockBlow(Monster x, Weapon current){
		removeHealth(x.getDamage()-((Sword)current).getBlockPower());
		x.removeHealth(current.damageModifier()-2);
	}
	//blocks the blow to the degree of stopping power of the players sword and inflicts less than normal damage on enemy
	public boolean canThrow(Weapon current){
		if(current instanceof Dagger)
			return true;
		else
			return false;
	}
	//determines if you can throw a weapon or not
	public void throwDagger(Monster x, Weapon current){
		x.removeHealth((int)(((double)current.damageModifier()*(((Dagger)current)).getThrow())+(current.damageModifier())));
		inventory.removeFromInvent(current);
	}
	//player throws dagger and causes "radical damage man", then removes it from player inventory
	public boolean canSmite(Weapon current){
		if(current instanceof Staff)
			return true;
		else
			return false;
	}
	//determines if a player can smite a monster or not
	public void staffSmite(Monster x, Weapon current){
		int rand= (int)(30*Math.random()-1);
		x.removeHealth(current.damageModifier()+rand);
	}
	//smites a monster with a random damage of base damage+ random damage between 1 and 10
	public boolean canSmash(Weapon current){
		if(current instanceof Hammer)
			return true;
		else
			return false;
	}
	//determines if a player can use smash attack
	public void hammerSmash(Monster x, Weapon current){
		x.removeHealth(current.damageModifier()*(((Hammer)current).getSwingStrength()));
	}
	//uses hammer smash attack against monster
	public void usePotion(Potion x){
		String[] EFFECT_TYPES= x.getEffectTypes();
		int degree= x.getDegree();
		
		if(x.getEffect().equals(EFFECT_TYPES[0])){
			addHealth(degree);
			potionEffect = EFFECT_TYPES[0];
		}
		else if(x.getEffect().equals(EFFECT_TYPES[1])){
			removeHealth(degree);
			potionEffect = EFFECT_TYPES[1];
		}
		else if(x.getEffect().equals(EFFECT_TYPES[2])){
			addDamage(degree);
			potionEffect = EFFECT_TYPES[2];
		}
		else if(x.getEffect().equals(EFFECT_TYPES[3])){
			removeDamage(degree);
			potionEffect = EFFECT_TYPES[3];
		}
		else if(x.getEffect().equals(EFFECT_TYPES[4])){
			removeSpeed(degree);
			potionEffect = EFFECT_TYPES[4];
		}
		else if(x.getEffect().equals(EFFECT_TYPES[5])){
			addSpeed(degree);
			potionEffect = EFFECT_TYPES[5];
		}
		removeFromInventory(x);
	}
	//allows a player to use one of the potions that they have in their inventory
	public void useArmorItem(ArmorItem x){
		String[] EFFECT_TYPES= x.getEffectTypes();
		int degree= x.getEffect();
		int side= x.getSideEffect(); // hi there!
		
		if(x.getEffectType().equals(EFFECT_TYPES[0])){
			addHealth(degree);
			armorEffects.add(EFFECT_TYPES[0]);
		}
		else if(x.getEffectType().equals(EFFECT_TYPES[1])){
			removeHealth(degree);
			armorEffects.add(EFFECT_TYPES[1]);
		}
		else if(x.getEffectType().equals(EFFECT_TYPES[2])){
			addDamage(degree);
			armorEffects.add(EFFECT_TYPES[2]);
		}
		else if(x.getEffectType().equals(EFFECT_TYPES[3])){
			removeDamage(degree);
			armorEffects.add(EFFECT_TYPES[3]);
		}
		else if(x.getEffectType().equals(EFFECT_TYPES[4])){
			removeSpeed(degree);
			armorEffects.add(EFFECT_TYPES[4]);
		}
		else if(x.getEffectType().equals(EFFECT_TYPES[5])){
			addSpeed(degree);
			armorEffects.add(EFFECT_TYPES[5]);
		}
		if (armorEffects.get(0).equals("NONE"))
			armorEffects.remove(0);
		//^ effects  v side effects
		if(x.isSideEffect()){
			if(x.getSideEffectType().equals(EFFECT_TYPES[0])){
				addHealth(side);
				armorSideEffects.add(EFFECT_TYPES[0]);
			}
			else if(x.getSideEffectType().equals(EFFECT_TYPES[1])){
				removeHealth(side);
				armorSideEffects.add(EFFECT_TYPES[1]);
			}
			else if(x.getSideEffectType().equals(EFFECT_TYPES[2])){
				addDamage(side);
				armorSideEffects.add(EFFECT_TYPES[2]);
			}
			else if(x.getSideEffectType().equals(EFFECT_TYPES[3])){
				removeDamage(side);
				armorSideEffects.add(EFFECT_TYPES[3]);
			}
			else if(x.getSideEffectType().equals(EFFECT_TYPES[4])){
				removeSpeed(side);
				armorSideEffects.add(EFFECT_TYPES[4]);
			}
			else if(x.getSideEffectType().equals(EFFECT_TYPES[5])){
				addSpeed(side);
				armorSideEffects.add(EFFECT_TYPES[5]);
			}
			if (armorSideEffects.get(0).equals("NONE"))
				armorSideEffects.remove(0);
		}
	}
	//adds the effects and side effects of an armor item to a player
	public void addToInventory(Item i){
		inventory.addToInvent(i);
	}
	//adds an item to the players inventory
	public void removeFromInventory(Item e){
		if(e.getName().equals("Fisticuffs"))
			return;
		//catches it from removing fisticuffs, should be caught in mes_panel though
		else
			inventory.removeFromInvent(e);
	}
	//removes an item from the players inventory if its not fisticuffs
	public ArrayList<Item> getInventory(){
		return inventory.getInventory();
	}
	//returns a player's inventory
	public String getEffect(){
		return currentMonsterEffect;
	}
	//returns current effect applied by monster to player
	public void applyEffect(String s){
		currentMonsterEffect = s;
	}
	//changes the current effect applied to player
	public void removeEffect(){
		currentMonsterEffect = "NONE";
	}
	//removes current effect applied to player
	public String getStats(){
		String stats = "Name: "+name;
		String l = Integer.toString(level);
		String h = Integer.toString(health);
		String d = Integer.toString(damage);
		String s = Integer.toString(speed);
		stats = stats+"\nLevel: "+l+"\nHealth: "+h+"\nDamage: "+d+"\nSpeed: "+s+"\n"
				+ "Current Monster Effect: "+currentMonsterEffect+"\n"
				+ "Current Potion Effect: "+potionEffect+"\n"
				+ "Current Armor Effects: ";
		for (String str:armorEffects){
			stats+=str+" , ";
		}
		stats+="\nCurrent Armor Side Effects: ";
		for (String str:armorSideEffects){
			stats+=str+" , ";
		}
		stats = stats+"\n\nGold Pieces: "+goldPieces;
		return stats;
	}
	//returns a string with the details of the player
	public ArrayList<Weapon> getWepsInInventory(){
		ArrayList<Weapon> weps= new ArrayList<Weapon>();
		ArrayList<Item> item= inventory.getInventory();
		for(int i=0; i<item.size(); i++){
			if(item.get(i) instanceof Weapon)
				weps.add((Weapon)item.get(i));
		}
		if(weps.size()>0)
			return weps;
		else
			return null;
	}
	//returns all of the weapons in the players inventory
	public ArrayList<Potion> getPotsInInventory(){
		ArrayList<Potion> pots= new ArrayList<Potion>();
		ArrayList<Item> item= inventory.getInventory();
		for(int i=0; i<item.size(); i++){
			if(item.get(i) instanceof Potion)
				pots.add((Potion)item.get(i));
		}
		if(pots.size()>0)
			return pots;
		else
			return null;
	}
	//returns all of the potions in the players inventory
	public ArrayList<ArmorItem> getArmsInInventory(){
		ArrayList<ArmorItem> arms= new ArrayList<ArmorItem>();
		ArrayList<Item> item= inventory.getInventory();
		for(int i=0; i<item.size(); i++){
			if(item.get(i) instanceof ArmorItem)
				arms.add((ArmorItem)item.get(i));
		}
		if(arms.size()>0)
			return arms;
		else
			return null;
	}
	//returns all of the armor items in the players inventory
	public int getGold(){
		return goldPieces;
	}
	//returns players current gold
	public void addGold(int g){
		goldPieces += g;
	}
	//adds gold to the players wallet
	public void removeGold(int g){
		goldPieces -= g;
	}
	//removes gold from the players wallet
	public Inventory getInventoryObject(){
		return inventory;
	}
	//returns the inventory object of a players inventory for pack weight usage mostly
	public void setButton( Player_Button p){
		pb = p;
	}
	public Player_Button getButton(){
		return pb;
	}
}
