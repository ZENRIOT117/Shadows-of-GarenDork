import java.util.ArrayList;

public class Merchant {
	private TreasureChest chest= new TreasureChest();
	private ArrayList<Item> wares= new ArrayList<Item>();
	
	public Merchant(){
		int numwares=(int) (10*Math.random());
		for(int i=0; i<numwares;i++)
			wares.add(chest.getReward());
	}
	//adds a random number of random items to wares
	public ArrayList<Item> getWares(){
		return wares;
	}
	//returns what the merchant has for sale
	public Item purchaseItem(String str){ //str is the item name
		for(int i=wares.size()-1; i>=0; i--){
			if((wares.get(i).getName().toLowerCase()).equals(str.toLowerCase()))
				return wares.remove(i);
		}
		return null;
	}
	//returns item from wares and removes purchased item from wares
	
}
