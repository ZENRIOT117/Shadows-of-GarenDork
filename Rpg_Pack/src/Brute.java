public class Brute extends Player {
	private Weapon wep= new Hammer("Smashy Smashy",2,3,"About as subtle as ...well... a sledgehammer.", 2);
	public Brute() {
		super();
	}
	public Brute(String n) {
		super(n);
		super.setDamage(2);
		super.setHealth(100);
		super.setSpeed(1);
		super.setType("Brute");
		super.addToInventory(wep);
		super.applyEffect("NONE");
	}
}
