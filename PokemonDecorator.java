/*
* This class represents a PokemonDecorator which will decorate base pokemon types of 
* Fire, Water, or Grass with different types of buffs and debuffs to update pokemon's stats.
* @author Gabriel Villanueva
*/
public abstract class PokemonDecorator extends Pokemon {

  private Pokemon pokemon;
  /*
  *This constructs a Decorator that adds buffs/debuffs to a pokemon.
  *@param p - gets pokemon to be buffed.
  *@param extraName - gets the string buff.
  *@param extraHp - gets the extra hp buff.
  */
  public PokemonDecorator(Pokemon p, String extraName, int extraHp){
    super(p.getName()+""+extraName, p.getHp() + extraHp, p.getMaxHp()+ extraHp);
    pokemon = p;
    
  }
  
  /*
  *Method to get the attack menu of the decorated pokemon.
  *@param atkType - attack type
  *@return the attack menu.
  */
  public String getAttackMenu(int atkType){
    return pokemon.getAttackMenu(atkType);
  }

  /*
  *Method to get the attack menu items of the decorated pokemon.
  *@param atkType - attack type
  *@return the attack menu items.
  */
  public int getNumAttackMenuItems(int atkType){
    return pokemon.getNumAttackMenuItems(atkType);
  }

  /*
  *Method to get the attack string of the decorated pokemon.
  *@param move - move of pokemon
  *@param atkType - attack type
  *@return the attack string.
  */
  public String getAttackString(int atkType, int move){
    return pokemon.getAttackString(atkType, move);
  }

  /*
  *Method to get the attack damage of the decorated pokemon.
  *@param move - move of pokemon
  *@param atkType - attack type
  *@return the attack damage.
  */
  public int getAttackDamage(int atkType, int move){
    return pokemon.getAttackDamage(atkType, move);
  }
  /*
  *Method to get the attack multiplier of the decorated pokemon.
  *@param p - pokemon to attack
  *@param atkType - attack type
  *@return the multiplier.
  */
  public double getAttackMultiplier(Pokemon p, int atkType){
    return pokemon.getAttackMultiplier(p, atkType);
  }
  /*
  *Method to get the attack bonus of the decorated pokemon.
  *@param atkType - attack type
  *@return the bonus.
  */
  public int getAttackBonus(int atkType){
    return pokemon.getAttackBonus(atkType);
  }
  /*
  *Method to get the type decorated pokemon.
  *@return the type.
  */
  public int getType(){
    return pokemon.getType();
  }
} 
