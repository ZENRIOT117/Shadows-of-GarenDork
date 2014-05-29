public class Assassin extends Player {
	private Weapon wep= new Dagger("Lil Stabber",3,1.2,"High speed, low drag.", 2);
	public Assassin() {
		super();
	}
	public Assassin(String n) {
		super(n);
		super.setDamage(6);
		super.setHealth(50);
		super.setSpeed(6);
		super.setType("Assassin");
		super.addToInventory(wep);
		super.applyEffect("NONE");
	}
}
