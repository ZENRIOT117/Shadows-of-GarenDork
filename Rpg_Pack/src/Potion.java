
public class Potion implements Item {
	private final String[] EFFECT_TYPES={"HEAL","POISON","DAMAGE","WEAK","SLOW","SWIFT"};
	private int degree;
	private String name;
	private String type;
	private String desc;
	private int value;
	
	public Potion(){
		degree=0;
		type=null;
		name="UND";
		desc=null;
	}
	public Potion(int d, String t, String n, String des, int val){
		degree=d;
		type=t;
		name=n;
		desc=des;
		value=val;
	}
	public int getValue(){
		return value;
	}
	public String getType() {
		return "Potion";
	}
	public String getName() {
		return name;
	}
	public String getEffect(){
		return type;
	}
	public String[] getEffectTypes(){
		return EFFECT_TYPES;
	}
	public int getDegree(){
		return degree;
	}
	public String getDescription(){
		return desc;
	}
	public String getStats(){
		String d = Integer.toString(degree);
		String stats = name+"\nType: "+type+"\nDegree(Severity): "+d;
		return stats;
	}
	//returns a string with the details of the potion
}
