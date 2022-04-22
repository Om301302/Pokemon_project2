/**
*This class represents a Pokemon object which extends from Entity.
@author Om Kakadiya
**/
public abstract class Pokemon extends Entity {

  //holds for damage multiplier of special attacks.
  protected static final double [][] battleTable = {{1, .5, 2}, {2, 1, .5}, {.5, 2, 1}};

  /**
  * This constructs a Pokemon object, where n is passed into super, along with an initial health of
  * 25 hp.
  * @param n holds for the name of the Pokemon 
  * @param h holds for the health of the Pokemon
  * @param m holds for the maxHp of the Pokemon
  */
  public Pokemon(String n, int h, int m)  {
    super(n, h, m);

  }
  /**
  * Method to be overrided. Gets the menu of special attacks.
  @return the attack type menu
  */
  public String getAttackTypeMenu(){
    return "1: Basic Attacks\n2: Special Attacks";
  }

  /**
  * Gets number of attack type.
  @return the number of menu types
  */
  public int getNumAttackTypeMenuItems(){
    return 2;

  }

  /**
  * Method that gets the basic attack menu of the pokemon.
  * @return the menu of basic attacks.
  */
  public String getAttackMenu(int atkType) {
    return "Attacks:\n1. Slam\n2. Tackle\n3. Punch";

  }
  /**
  * Method that gets input from user about which he wants to select from the basic attack menu.
  * It has range of three Slam, Tackle, and punch.
  * @return the number depands on which type of basic attack user wants to do.tack menu
  */
  public int getNumAttackMenuItems(int atkType) {
    int basicAttackNum = CheckInput.getIntRange(1,3);
    return basicAttackNum;
  }
  
  /**
  * Method that does a basic attack on another pokemon. Basic attack depends on
  * the integer the user chooses. Calls other methods below to get the string of 
  * the attack sequence.
  * @param p holds for the defending pokemon
  * @param move holds for the integer that corresponds to the basic attack in menu.
  * @return the string of the attack sequence 
  */
  public String attack(Pokemon p,int atkType, int move) {
      int damage;
      damage = (int) (this.getAttackDamage(atkType, move) * this.getAttackMultiplier(p, atkType)) + this.getAttackBonus(atkType);
      p.takeDamage(damage);
      return "" + this.getName() + " " + this.getAttackString(atkType, move) + " " + p.getName() + " and deals " + damage + " damage.";
       
  }

  /**
  * Method that gets the move of a pokemon.
  * @return the partial string of chosen move. 
  */
  public String getAttackString(int atkType, int move) {
    if (move == 1){
      return " SLAMMED ";

    }
    else if (move == 2){
      return " TACKLED ";
      
    }
    else if (move == 3){
      return " PUNCHED ";
    }
    return null;
  }

  /**
  * Method that gets input from usrer about which attack user wants to do.
  * In range of 1-2, basic and special attack.
  * @return the number corresponds on which type of attack user wants to do.
  */
  public int getAttackDamage(int atkType, int move) {
    if (move == 1){
      int randNumDamage = (int) (Math.random() * 5);
      return randNumDamage;
      
    }
    else if (move == 2){
      int randNumDamage = (int) (Math.random() * 3) + 2;
      return randNumDamage;
      
    }
    else if (move == 3){
      int randNumDamage = (int) (Math.random() * 4) + 1;
      return randNumDamage;
    }
    return 0;
  }

  /**
  *Method which multiplies the damage.
  *@return the multiplier for a move.
  */
  public double getAttackMultiplier(Pokemon p, int atkType){
    return 1;
  }

  /**
  *Gets the bonus froma buff.
  *@return the attack bonus
  */
  public int getAttackBonus(int atkType){
    return 0;
  }

  /**
  * Method that returns an integer 0-2 that represents the type of pokemon,
  * whether it is fire(Charmander,Ponyta), water(Squirtle, Staryu), or grass(Bulbasaur,Oddish).
  * @return the integer that corresponds to the type of pokemon.
  */
  public int getType() {
    //intialize the integer
    int numType = 0;
  
    if(this instanceof Fire){
      numType = 0;

    } else if(this instanceof Water){
      numType = 1;

    } else if(this instanceof Grass){
      numType = 2;
    } else{
      return -1;
    }
    
    return numType;
  }
}