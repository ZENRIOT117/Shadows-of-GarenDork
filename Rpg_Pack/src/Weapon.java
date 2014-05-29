public class Weapon implements Item {
	private String name;
	private int damage;
	private String desc;
	private int value;
	
	public Weapon(String n, int d, String des, int val){
		name=n;
		damage=d;
		desc=des;
		value=val;
	}
	public int getValue(){
		return value;
	}
	public String getType() {
		return "Weapon";
	}
	public String getName() {
		return name;
	}
	public int damageModifier(){
		return damage;
	}
	public String getDescription(){
		return desc;
	}
	public String getStats(){
		String d = Integer.toString(damage);
		String stats = name+"\nDamage: "+d;
		return stats;
	}
	//returns a string with the details of the weapon
}
