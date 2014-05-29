import java.util.ArrayList;

public class TreasureRoom {
	private TreasureChest chest= new TreasureChest();
	private ArrayList<Item> all= chest.getItems();
	private ArrayList<Potion> bads2= getBadPotions();
	
	public TreasureRoom(){
		
	}
	public boolean isReward(){
		if(Math.random()>0.5)
			return true;
		else
			return false;
	}
	//checks to see if a player is getting a reward, or getting screwed over *hehe*
	public Item getReward(){
		return chest.getReward();
	}
	//returns a positive result
	public Potion getAntiReward(){
		Potion x;
		int rand= (int)(bads2.size()*Math.random());
		x= bads2.get(rand);
		return x;
	}
	//returns the negative result
	public ArrayList<Potion> getBadPotions(){
		ArrayList<Potion> bads= new ArrayList<Potion>();
		Potion pot= new Potion();
		String[] types= pot.getEffectTypes();
		for(int i=0;i<all.size();i++){
			if(all.get(i) instanceof Potion){
				if(((((Potion)all.get(i)).getEffect()).equals(types[1]))||(((Potion)all.get(i)).getEffect().equals(types[3]))||(((Potion)all.get(i)).getEffect().equals(types[4])))
					bads.add((Potion)all.get(i));
			}
		}
		return bads;
	}
	//returns array list of harmful potions that may be thrown at player by a chest goblin

}
