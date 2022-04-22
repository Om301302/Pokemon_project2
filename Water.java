/**
* Interface for water type pokemon, specific attack types.
* @author Phillip Huynh (Original Author, Pokemon Project 1)
* @coauthor Dev Paul (Updated for Pokemon Project 2)
* Last Updated: 12/01/21 
*/
public class Water extends Pokemon{
  
  //class methods
  /**
   * 
   * @param n holds for naming a pokemon
   * @param h holds for describing the HP of a pokemon
   * @param m holds for describing the maxHP of a pokemon
   */
  public Water(String n, int h, int m){
	  super(n, h, m);
  }
  
  /**
   * Retrieves attackMenu, based on the atkType (1 for Basic, 2 for Special)
   * @param atkType - determiner of which attack type the Pokemon will use
   * @return String attackMenu (Basic or Special)
   */
  @Override
  public String getAttackMenu(int atkType) {
	  
	  if(atkType == 1) { //Basic Attack
		  return super.getAttackMenu(atkType);  
	  } else if(atkType == 2) { //Special Attack
		  return "Special Attacks:\n1. Water Gun\n2. Bubble Beam\n3. Waterfall";
	  } else {
		  System.out.println("Invalid Move. Please try again");
		  return null;
	  }
	   
  }
  
  /**
   * Retrieves number of attackMenuItems, based on the atkType (1 for Basic, 2 for Special)
   * @param atkType - determiner of which attack type the Pokemon will use
   * @return int number of attackMenuItems (Basic or Special)
   */
  @Override
  public int getNumAttackMenuItems(int atkType) {
	  
	  if(atkType == 1) { //Basic Attack
		  return super.getNumAttackMenuItems(atkType); 
		  //3 moves: Slam, Tackle, Punch
	  } else if(atkType == 2) { //Special Attack
		    int specialAttackNum = CheckInput.getIntRange(1,3);
        return specialAttackNum;
		  //3 moves: Water Gun, Bubble Beam, Waterfall
	  } else {
		  System.out.println("Invalid Move. Please try again");
		  return -1;
	  }
	  
  }
  
  /**
   * Generates a partial string based on atkType and selected move
   * @param atkType - determiner of which attack type the Pokemon will use
   * @param move - determiner of which attack the Pokemon will use
   * @return partial string
   */
  @Override
  public String getAttackString(int atkType, int move) {
	  
	  if(atkType == 1) { //Basic Attack
		return super.getAttackString(atkType, move);

	  } else if (atkType == 2){ //Special Attack
		  if(move == 1) { 
			  //Water Gun
			  	  return "WATER GUNNED";
			  } else if(move == 2) { 
				//Bubble Beam
				  return "BUBBLED BEAMED";
			  } else if (move == 3){ 
				//Waterfall
				  return "WATERFALLED";
			  }
	  } else {
		  System.out.println("Invalid Move. Please try again");
	  }
    return null;
  }
  
  /**
   * Randomizes damage based on the selected move of selected atkType
   * @param atkType - type of atk, basic or special
   * @param move - selected move
   * @return randomly generated damage of selected move
   */
  @Override
  public int getAttackDamage(int atkType, int move) {
	  
	  if(atkType == 1) { //Basic Attack
			return super.getAttackDamage(atkType, move); 

		  } else if (atkType == 2){ //Special Attack
			  if(move == 1) { 
				  //Water Gun
				  	  return (int) (Math.random() * 4) + 2;
				  } else if(move == 2) { 
					//Bubble Beam
					  return (int) (Math.random() * 3) + 1;
				  } else if (move == 3){ 
					//Waterfall
					  return (int) (Math.random() * 4) + 1;
				  }
		  } else {
			  System.out.println("Invalid Move. Please try again");
		  }
      return -1;
  }

  /**
   * Generates a Damage Multiplier based on Pokemon Type (BattleTable) and Buffs/Debufs
   * @param p - pokemon the user is fighting against
   * @param atkType - type of attack chosen
   * @return the damage multiplier
   */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType) {
	
	  if(atkType == 1) { //Basic Attack
			return super.getAttackMultiplier(p, atkType); 

		  } else if (atkType == 2){ //Special Attack
			  return (int) battleTable[this.getType()][p.getType()];
				  
		  } else {
			  System.out.println("Invalid Move. Please try again");
			  return -1;
		  }
  }


}