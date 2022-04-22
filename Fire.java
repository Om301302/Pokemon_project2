/**
* Class for fire type pokemon, specific attack types.
* @author Gabriel Villanueva
*
*/
public class Fire extends Pokemon{
  
  //class methods
  /**
   * 
   * @param n holds for naming a pokemon
   * @param h holds for describing the HP of a pokemon
   * @param m holds for describing the maxHP of a pokemon
   */
  public Fire(String n, int h, int m){
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
		  return "Special Attacks:\n1. Ember\n2. Fire Blast\n3. Fire Punch";
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
		  //3 moves: Ember, Fire Blast, Fire Punch
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
		return super.getAttackString(atkType, move); //PENDING<!>: Pokemon Class Update

	  } else if (atkType == 2){ //Special Attack
		  if(move == 1) { 
			  //Ember
			  	  return "EMBERED";
			  } else if(move == 2) { 
				//Fire Blast
				  return "FIRE BLASTED";
			  } else if (move == 3){ 
				//Fire Punch
				  return "FIRE PUNCHED";
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
			return super.getAttackDamage(atkType, move); //PENDING<!>: Pokemon Class Update

		  } else if (atkType == 2){ //Special Attack
			  if(move == 1) { 
				  //Ember
				  	  return (int) (Math.random() * 4);
				  } else if(move == 2) { 
					//Fire Blast
					  return (int) (Math.random() * 4) + 1;
				  } else if (move == 3){ 
					//Fire Punch
					  return (int) (Math.random() * 3) + 1;
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

//