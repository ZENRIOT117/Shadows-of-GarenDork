public class MonsterPool {
	private Monster mon1 = new BasicMob("Potted Plant",1,3,1,0);
	private Monster mon2 = new BasicMob("Dumb Goblin",1,3,2,1);
	private Monster mon3 = new BasicMob("Tired Tortoise",1,1,4,0);
	private Monster mon4 = new BasicMob("Lame Lemon",1,2,3,0);
	private Monster mon5 = new BasicMob("Slightly Threatening Zombie",1,3,4,1);
	//Level Split (For organizational sanity)
	private Monster mon6 = new BasicMob("Creature of Habit",2,3,1,0);
	private Monster mon7 = new BasicMob("Furious Tortoise",2,3,1,0);
	private Monster mon8 = new BasicMob("Cardboard Box Full of Explosives",2,3,1,0);
	private Monster mon9 = new BasicMob("Jar of Petroleum Jelly",2,3,1,0);
	private Monster mon10 = new BasicMob("Your Best Friend's Mum",2,3,1,0);
	//Level Split (For organizational sanity)
	private Monster mon11 = new BasicMob("The Knight Who Says Ni",3,3,1,0);
	private Monster mon12 = new BasicMob("Angry Zombie",3,3,1,0);
	private Monster mon13 = new BasicMob("Cloaker With a Taser",3,3,1,0);
	private Monster mon14 = new BasicMob("Hissing Creeper",3,3,1,0);
	private Monster mon15 = new BasicMob("Really Slow Freight Train",3,3,1,0);
	//Level Split (For organizational sanity)
	private Monster mon16 = new BasicMob("Vladimir Putin Riding a Bear",4,3,1,0);
	private Monster mon17 = new BasicMob("Really Spicy Burrito",4,3,1,0);
	private Monster mon18 = new BasicMob("Al Capone With Wings",4,3,1,0);
	private Monster mon19 = new BasicMob("Debanik Armed With His English Homework",4,3,1,0);
	private Monster mon20 = new BasicMob("A Really Pissed Off Ball of Moss",4,3,1,0);
	//Level Split (For organizational sanity)
	private Monster mon21 = new BasicMob("Lo Wang (And he wants his sword back)",5,3,1,0);
	private Monster mon22 = new BasicMob("Random Member of ZZ Top",5,3,1,0);
	private Monster mon23 = new BasicMob("A Really Long Book Full of Useless Information",5,3,1,0);
	private Monster mon24 = new BasicMob("Mr. Foley",5,3,1,0);
	private Monster mon25 = new BasicMob("Wolf With About 6 Teeth",5,3,1,0);
	//Level Split (For organizational sanity)
	private Monster mon26 = new BasicMob("Bank Robber",6,3,1,0);
	private Monster mon27 = new BasicMob("Tuco Salamanca (You were messin' with his Tio)",6,3,1,0);
	private Monster mon28 = new BasicMob("Undead Redneck",6,3,1,0);
	private Monster mon29 = new BasicMob("Talking Cow",6,3,1,0);
	private Monster mon30 = new BasicMob("Mike Ehrmantraut",6,3,1,0);
	//Level Split (For organizational sanity)
	private Monster mon31 = new BasicMob("Yahtzee Croshaw",7,3,1,0);
	private Monster mon32 = new BasicMob("GENSEC Convoy",7,3,1,0);
	private Monster mon33 = new BasicMob("Slightly Loopy Programmer",7,3,1,0);
	private Monster mon34 = new BasicMob("Gordon Ramsay",7,3,1,0);
	private Monster mon35 = new BasicMob("Evil Hobgoblin",7,3,1,0);
	//Level Split (For organizational sanity)
	private Monster mon36 = new BasicMob("Drunken Dwarf",8,3,1,0);
	private Monster mon37 = new BasicMob("Ravenous Were-Wolf",8,3,1,0);
	private Monster mon38 = new BasicMob("Agent 47",8,3,1,0);
	private Monster mon39 = new BasicMob("Gabe Newell (Bow down before the Mighty Gaben)",8,3,1,0);
	private Monster mon40 = new BasicMob("Cave Troll",8,3,1,0);
	//Level Split (For organizational sanity)
	private Monster mon41 = new BasicMob("Audrey II (Who is laughing at the potted plant now)",9,3,1,0);
	private Monster mon42 = new BasicMob("Bain",9,3,1,0);
	private Monster mon43 = new BasicMob("Vaas Montenegro",9,3,1,0);
	private Monster mon44 = new BasicMob("Really Tall Elf",9,3,1,0);
	private Monster mon45 = new BasicMob("Sauron",9,3,1,0);
	//Level Split (For organizational sanity)
	private Monster mon46 = new BasicMob("Were-Rabbit",10,30,20,4);
	private Monster mon47 = new BasicMob("Furious Godzilla",10,40,13,6);
	private Monster mon48 = new BasicMob("Fully Loaded SWAT Truck",10,50,10,8);
	private Monster mon49 = new BasicMob("Angry Bulldozer",10,50,10,7);
	private Monster mon50 = new BasicMob("Chuck Norris the Mystical Wizard",10,60,10,8);

	Monster[] MONSTERS = {mon1,mon2,mon3,mon4,mon5,mon6,mon7,mon8,mon9,mon10,mon11,mon12,mon13,mon14,mon15,mon16,mon17,mon18,mon19,mon20,mon21,mon22,mon23,mon24,mon25,mon26,mon27,mon28,mon29,mon30,mon31,mon32,mon33,mon34,mon35,mon36,mon37,mon38,mon39,mon40,mon41,mon42,mon43,mon44,mon45,mon46,mon47,mon48,mon49,mon50};
	
	public MonsterPool(){
	}
	public Monster getMonster(int level){
		Monster[] temp = new Monster[5];
		int dex = 0;
		for (int i = 0; i < MONSTERS.length; i++){
			if ((MONSTERS[i]).getLevel() == level){
				temp[dex] = MONSTERS[i];
				dex++;
			}
		}
		int num = (int)(temp.length*Math.random());
		return temp[num];
	}
	public Monster returnMonster(String name){
		for(int i=0; i<MONSTERS.length;i++)
			if(name.toLowerCase().equals(MONSTERS[i].getName().toLowerCase()))
				return MONSTERS[i];
		return null; 
		
	}
}
