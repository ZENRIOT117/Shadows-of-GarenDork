public interface Monster {

	public String getName();//returns the name of the monster
	public int getLevel();//returns the level of the monster
	public int getHealth();//returns the current health of a monster
	public void removeHealth(int heal);//removes health from a monster
	public int getDamage();//returns the damage that a monster can do to a player
	public int getSpeed();//return the speed that the monster can chase a player at if a player chooses to run
	public String getStats();//returns String to be displayed with the stats of that monster
	public boolean isDead();//returns whether monster is dead or not
	public void usePotionOn(Potion x);//applies a potion to the monster
	public Monster_Button getButton(); // returns monster button associated
}
