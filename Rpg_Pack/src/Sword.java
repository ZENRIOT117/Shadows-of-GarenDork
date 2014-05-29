public class Sword extends Weapon {
	private int blockpower; //must be less then 10
	private String desc;
	
	public Sword(String n, int d, int b, String des, int val) {
		super(n, d, des, val);
		blockpower=b;
		desc=des;
	}
	public int getBlockPower(){
		return blockpower;
	}
	public String getType(){
		return "Sword";
	}
	public String getDescription(){
		return desc;
	}
	public String getStats(){
		String d = Integer.toString(this.damageModifier());
		String b = Integer.toString(blockpower);
		String stats = this.getName()+"\nDamage: "+d+"\nBlock: "+b;
		return stats;
	}
	//returns a string with the details of the sword
}
