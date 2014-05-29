public class Staff extends Weapon {
	//base damage of a staff should be rather low
	private String desc;
	public Staff(String n, int d, String des, int val) {
		super(n, d, des, val);
		desc=des;
	}
	public String getType(){
		return "Staff";
	}
	public String getDescription(){
		return desc;
	}
	public String getStats(){
		String d = Integer.toString(this.damageModifier());
		String stats = this.getName()+"\nDamage: "+d;
		return stats;
	}
	//returns a string with the details of the staff

}
