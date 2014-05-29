public class Mage extends Player {
	private Weapon wep= new Staff("Wacky Stick",2,"It seems useless, but it works, I promise.", 2);
	
	public Mage() {
		super();
	}
	public Mage(String n) {
		super(n);
		super.setDamage(5);
		super.setHealth(60);
		super.setSpeed(3);
		super.setType("Mage");
		super.addToInventory(wep);
		super.applyEffect("NONE");
	}
}
