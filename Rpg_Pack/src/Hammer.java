public class Hammer extends Weapon {
	private int swingstrength;
	//should be low, remember that it is a multiplier
	private String desc;
	
	public Hammer(String n, int d, int s, String des, int val) {
		super(n, d, des, val);
		swingstrength=s;
		desc=des;
	}
	public String getType(){
		return "Hammer";
	}
	public int getSwingStrength(){
		return swingstrength;
	}
	public String getDescription(){
		return desc;
	}
	public String getStats(){
		String d = Integer.toString(this.damageModifier());
		String s = Integer.toString(swingstrength);
		String stats = this.getName()+"\nDamage: "+d+"\nSwing Strength Multiplier "+s;
		return stats;
	}
	//returns a string with the details of the hammer

}
