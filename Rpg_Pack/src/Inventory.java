import java.util.ArrayList;

public class Inventory {
	ArrayList<Item> invent= new ArrayList<Item>();
	public Inventory(){
		//default items set based on type of player
		
	}
	public ArrayList<Item> getInventory(){
		//returns a players inventory for use
		return invent;
	}
	public void addToInvent(Item i){
		//adds an item to players inventory
		invent.add(i);
	}
	public void removeFromInvent(Item e){
		//removes an item from players inventory
		if(invent.size()>0){
			for(int i=invent.size()-1; i>=0; i--){
				if(e.getName().equals((invent.get(i)).getName()))
					invent.remove(i);
			}
		}
	}
	public int getPackWeight(){
		//returns the weight of a players inventory for player speed calculation
		if(invent.size()>1)
			return (invent.size()/3);
		else
			return invent.size();
	}
	public int find(Item e){
		for(int i=invent.size()-1; i>=0; i--)
			if(e.getName().equals((invent.get(i)).getName()))
				return i;
		return -1;
	}
}
