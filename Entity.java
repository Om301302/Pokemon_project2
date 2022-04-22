/**
*This class represents an Entity object with different attributes of name, hp, and maxHp.
* @author Phillip Huynh (Original Author, Pokemon Project 1)
* @coauthor Dev Paul (Updated for Pokemon Project 2)
* Last Updated: 12/01/21
**/
public abstract class Entity {

  private String name;
  private int hp;
  private int maxHp;
  
  /**
  *This constructs an Entity object with name, hp, and maxHp.
  @param n - holds for an Entity's name.
  @param h - holds for an Entity's hp.
  @param m - holds for an Entity's max hp.
  **/
  public Entity(String n, int h, int m) {
    name = n;
    maxHp = m;
    hp = h;
  }
  
  /**
  *Method that gets hp from user as it is in trainer parameter. 
  *@return the number of hp that pokemon has
  */
  public int getHp() {
    return hp;
  }

  /**
  *Method that gets the highest amount of hp which is given in the guidlines.
  *@return the highest amount of health of pokemon.
  */
  public int getMaxHp() {
    return maxHp;
 }

  /**
  * Method that takeout points from hp after fighting with other pokemon. 
  *@param d would be the number that is damaged by other pokemon.
  *Also this method will do subtraction from previous hp and if that go less than zero, it will override hp and give zero hp in retuurn.
  */
  public void takeDamage(int d) {
    hp -= d;
    if (hp <= 0) {
      hp = 0;
    }
  }

  /**
  *Method that will give maximum health to pokemon. So, basically their hp will be max. 
  */
  public void heal() {
    hp = maxHp;
  }

  /**
  *Method that gets the name as a string.
  *@return a string which will have the name.
  */

  public String getName() {
    return name;
  }

  /**
  * Method that gets the pokemon's hp and max hp.
  * @return a string of the pokemon name with hp and maxhp.
  */
  @Override
  public String toString() {
    return name + " HP: " + hp + "/" + maxHp + " ";  
  }

  }






