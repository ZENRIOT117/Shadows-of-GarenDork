public class Warrior extends Player {
	private Weapon wep= new Sword("Big Bertha",3,3,"A great sword for a great(ish) warrior.", 2);
	public Warrior(){
		super();
	}
	public Warrior(String n){
		super(n);
		super.setDamage(4);
		super.setHealth(75);
		super.setSpeed(2);
		super.setType("Warrior");
		super.addToInventory(wep);
		super.applyEffect("NONE");
	}
}
