public class Dagger extends Weapon {
	private double throwdamage; //MUST be greater than 1, its a multiplier
	private String desc;
	
	public Dagger(String n, int d, double t, String des, int val) {
		super(n, d, des, val);
		throwdamage=t;
		desc=des;
	}
	public String getType(){
		return "Dagger";
	}
	public double getThrow(){
		return throwdamage;
	}
	public String getDescription(){
		return desc;
	}
	public String getStats(){
		String d = Integer.toString(this.damageModifier());
		String t = Double.toString(throwdamage);
		String stats = this.getName()+"\nDamage: "+d+"\nThrowing Damage Multiplier: "+t;
		return stats;
	}
	//returns a string with the details of the dagger
}
