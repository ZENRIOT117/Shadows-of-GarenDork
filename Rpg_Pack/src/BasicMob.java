import javax.swing.ImageIcon;

public class BasicMob implements Monster {
	private String name= "UND";
	private int level= 1;
	private int health= 10;
	private int damage= 3;
	private int speed= 2;
	@SuppressWarnings("unused")
	private String potionEffect = "NONE";
	private boolean isAlive= true;
	private Monster_Button butt;
	
	public BasicMob(String n, int l, int h, int d, int s){
		name = n;
		level = l;
		health = h;
		damage = d;
		speed = s;
		butt = new Monster_Button(new ImageIcon("img/GrayMonster.png"),name);
	}
	public BasicMob(){
		//undefined is the best defined
	}
	public boolean isDead(){
		if(!(isAlive))
			return true;
		else 
			return false;
	}
	public String getName() {
		return name;
	}
	public int getLevel() {
		return level;
	}
	public int getHealth() {
		return health;
	}
	public void removeHealth(int hea){
		if(health-hea>0)
			health= health-hea;
		else
			isAlive=false;
	}
	public int getDamage() {
		return damage;
	}
	public int getSpeed() {
		return speed;
	}
	public void addHealth(int heal){
		health+=heal;
	}
	//adds health based on potions or food
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
	//takes speed away based on potions or items
	public String getStats(){
		String stats = "Name: "+name;
		String l = Integer.toString(level);
		String h = Integer.toString(health);
		String d = Integer.toString(damage);
		String s = Integer.toString(speed);
		stats = stats+"\nLevel: "+l+"\nHealth: "+h+"\nDamage: "+d+"\nSpeed: "+s+"\n";
		return stats;
	}
	//returns a string with the details of the monster
	public void usePotionOn(Potion x){
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
			removeHealth(degree);
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
	}
	public Monster_Button getButton(){
		return butt;
		
	}
	public void setButton(Monster_Button b){
		butt = b;
	}
}

