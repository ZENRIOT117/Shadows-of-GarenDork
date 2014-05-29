public class ArmorItem implements Item {
	private String name;
	private int effect;
	private int sideeffect=0;
	private String effecttype;
	private String sideeffecttype;
	private String desc;
	private int value;
	private final String[] EFFECT_TYPES={"HEAL","POISON","DAMAGE","WEAK","SLOW","SWIFT"};
	
	public ArmorItem(String n, String et, String set, String des, int e, int s, int val){
		name=n;
		effect=e;
		sideeffect=s;
		effecttype=et;
		sideeffecttype=set;
		desc=des;
		value=val;
	}
	public int getValue(){
		return value;
	}
	public String getType() {
		return "Armor";
	}
	public String getName() {
		return name;
	}
	public int getEffect(){
		return effect;
	}
	//returns the positive effect of the armor
	public int getSideEffect(){
		return sideeffect;
	}
	//returns the negative effect if any of an armor item
	public boolean isSideEffect(){
		if (sideeffect>0)
			return true;
		else 
			return false;
	}
	//checks if an armor item has a side effect or not
	public String[] getEffectTypes(){
		return EFFECT_TYPES;
	}
	//returns effect types possible
	public String getEffectType(){
		return effecttype;
	}
	//returns the primary effect of the armor item
	public String getSideEffectType(){
		return sideeffecttype;
	}
	//returns the side effect type of the armor item
	public String getDescription(){
		return desc;
	}
	//returns the item description
	public String getStats() {
		String e = Integer.toString(effect);
		String s = Integer.toString(sideeffect);
		String stats = name+"\nEffect Type: "+effecttype+"\nDegree(Severity): "+e+"\nSide Effect Type: "+sideeffecttype+"\nDegree(Severity): "+s;
		return stats;
	}
	//returns a string with the details of the armor item

}
