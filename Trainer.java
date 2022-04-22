import java.util.ArrayList;
import java.awt.Point;

/**
*This class represents a trainer object which extends from Entity.
@author Om Kakadiya
**/
public class Trainer extends Entity{
  private int money;
  private int potions;
  private int pokeballs;
  private Point loc;
  private ArrayList <Pokemon> pokemon;

  /**
	 * This constructs a Trainer object with given string name, a pokemon.
   * Initialize the health to 25 of trainer, initialize money, potions, and pokeballs of trainer,
   * initialize the location of the location of the trainer, and add the starter pokemon to the list.
	 * @param n holds for the name of the trainer entity.
	 * @param p holds for the starter pokemon of the trainer.
	 * **/
  public Trainer(String n,Pokemon p){
    super(n,25, 25);
    money = 20;
    potions = 3;
    pokeballs = 5;
    loc = Map.getInstance().findStart();
    pokemon = new ArrayList <Pokemon>();
    pokemon.add(p);
  
  }

  /**
  * Method to get the money of the trainer.
  * @return total money that the trainer has.
  */
  public int getMoney(){
    return money;   
  }

  /**
  * Method to determine if the trainer can afford something.
  * @param amount holds for the cost of an item the trainer wants to buy.
  * @return true or false, if the user has enough for a pokeball or potion in the shop.
  */
  public boolean spendMoney(int amt){
    if (money-amt >= 0){
      money -= amt;
      return true;
    }
    return false;
  }

  /**
  * Method to give a certain amount money to the trainer.
  * @param amount holds for the amount to give to the trainer.
  */
  public void receiveMoney(int amt){
    money += amt;
  }

  /**
  * Method to determine if trainer has potions.
  * @return true or false if trainer has a potion.
  */
  public boolean hasPotion(){
    if(potions > 0){
      return true;
    }
    return false;
  }

  /**
  * Method to give a potion to the trainer.
  */
  public void receivePotion(){
    potions += 1;
  }

  /**
  * Method for trainer to use a potion one of their pokemon and remove a potion
  * from their inventory.
  * @param pokeIndex holds for the index of a pokemon in their ArrayList of pokemon.
  * 
  */
  public void usePotion(int pokeIndex){
    pokemon.get(pokeIndex).heal();
    potions -= 1;
  }

  /**
  * Method to determine if trainer has a pokeball.
  * @return true or false if trainer has a pokeball or not. 
  */
  public boolean hasPokeball(){
    if (pokeballs > 0 ){
      return true;
    }
    return false;
  }

  /**
  * Method to add a pokeball to the trainer's inventory.
  */
  public void receivePokeball(){
    pokeballs += 1;
  }

  /**
  * Method determines if trainer caught a pokemon. Creates a random integer from 1-100, and if it is
  * greater than the percentage of the wild pokemon, the pokemon is caught and added to the 
  * trainer's ArrayList of pokemon.
  * @param p holds for a wild pokemon that is trying to be caught.
  * @return true or false if the pokemon was caught or not.
  */
  public boolean catchPokemon(Pokemon p){
    pokeballs -= 1;
    //Generates a random number 1-100 and comapres it to the percentage of health of wild pokemon.
    double healthPoints = ((p.getHp() / p.getMaxHp()) * 100);
    int chance = (int) (Math.random() * 100) + 1 ;

    //if the random number is greater, the pokemon is caught, else pokemon breaks free.
    if (chance > healthPoints){
      pokemon.add(p);
      return true;
    }
    return false;
  }

  /**
  * Method to get a Point location of the trainer on the map.
  * @return the location of the trainer on the map.
  */
  public Point getLocation(){
    return loc;
  }

  /**
  * Method for trainer to go up on the map. Creates a new point object and gets the postion of
  * the trainer's location, validates whether that location is valid, and if it is, the trainer
  * goes their and returns the char on the map. If not, trainer stays where they're at.
  * @return the character of the location they went to on the map.
  */
  public char goNorth(){
    //Create a new point of the desired direction the trainer wants to go to.
    Point north = new Point();
    int x = (int) getLocation().getX();
    int y = (int) getLocation().getY();
    north.move(x,y);
    north.translate(-1,0);

    //If the character is out of bounds ('X'), display you can't go their and return X
    char northChar = Map.getInstance().getCharAtLoc(north);
    if(northChar == 'X'){
      System.out.println("You cannot go there.");
      return 'X';

    }
    //Trainer goes to location and character is returned.
    loc.translate(-1,0);
    Map.getInstance().reveal(loc);
    return northChar; 

  }

  /**
  * Method for trainer to go down on the map. Creates a new point object and gets the postion of
  * the trainer's location, validates whether that location is valid, and if it is, the trainer
  * goes their and returns the char on the map. If not, trainer stays where they're at.
  * @return the character of the location they went to on the map.
  */
  public char goSouth(){
    //Create a new point of the desired direction the trainer wants to go to.
    Point south = new Point();
    int x = (int) getLocation().getX();
    int y = (int) getLocation().getY();
    south.move(x,y);
    south.translate(1,0);

    //If the character is out of bounds ('X'), display you can't go their and return X
    char southChar = Map.getInstance().getCharAtLoc(south);
    if(southChar == 'X'){
      System.out.println("You cannot go there.");
      return 'X';

    }
    //Trainer goes to location and character is returned.
    loc.translate(1,0);
    Map.getInstance().reveal(loc);
    return southChar;

  }

  /**
  * Method for trainer to go right on the map. Creates a new point object and gets the postion of
  * the trainer's location, validates whether that location is valid, and if it is, the trainer
  * goes their and returns the char on the map. If not, trainer stays where they're at.
  * @return the character of the location they went to on the map.
  */
  public char goEast(){
    //Create a new point of the desired direction the trainer wants to go to.
    Point east = new Point();
    int x = (int) getLocation().getX();
    int y = (int) getLocation().getY();
    east.move(x,y);
    east.translate(0, 1);
    char eastChar = Map.getInstance().getCharAtLoc(east);
    
    //If the character is out of bounds ('X'), display you can't go their and return X.
    if(eastChar == 'X'){
      System.out.println("You cannot go there.");
      return 'X';

    }
    //Trainer goes to location and character is returned.
    loc.translate(0, 1);
    Map.getInstance().reveal(loc);
    return eastChar; 

  }

  /**
  * Method for trainer to go left on the map. Creates a new point object and gets the postion of
  * the trainer's location, validates whether that location is valid, and if it is, the trainer
  * goes their and returns the char on the map. If not, trainer stays where they're at.
  * @return the character of the location they went to on the map.
  */
  public char goWest(){
    //Create a new point of the desired direction the trainer wants to go to.
    Point west = new Point();
    int x = (int) getLocation().getX();
    int y = (int) getLocation().getY();
    west.move(x,y);
    west.translate(0, -1);
    
    //If the character is out of bounds ('X'), display you can't go their and return X.
    char westChar = Map.getInstance().getCharAtLoc(west);
    if(westChar == 'X'){
      System.out.println("You cannot go there.");
      return 'X';
      
    }
    
    //Trainer goes to location and character is returned.
    loc.translate(0, -1);
    Map.getInstance().reveal(loc);
    return westChar;  
  }

  /**
  * Method to get the number of pokemon the trainer has.
  * @return the number of pokemon the trainer has in their ArrayList of pokemon.
  */
  public int getNumPokemon(){
    return pokemon.size();

  }
  /**
  * Method that will fill all hp from all pokemons.
  *@return the list of pokemons that trainer has and with all maxhp that means all pokemons will have full hp.
  */
  public void healAllPokemon(){
    for(int i = 0; i < pokemon.size(); i++){
      pokemon.get(i).heal();
      
    }
  }
  /**
  * Method that will buff for all pokemons and it is 25 percentage it happens.
  *@param index - index of the pokemon to buff
  */
  public void buffAllPokemon(){
     for(int i = 0; i < pokemon.size(); i++){
        int counter = (int)(Math.random()*2)+1;
        if(counter == 1){
          Pokemon buffedHp = pokemon.get(i);
          buffedHp = new HpUp (buffedHp); //hp up
        }
        else{
          Pokemon buffedAttack = pokemon.get(i);
          buffedAttack = new AttackUp(buffedAttack);
        }
      }
  }
  /**
  * Method that will debuff for all pokemons and it is 25 percentage it happens.
  *@param index - index of the pokemon to debuff
  */
  public void debuffPokemon(int index){
    int counter = (int)(Math.random()*2)+1;

    if(counter == 1){
      Pokemon debuffedHp = pokemon.get(index);
      debuffedHp = new HpDown(debuffedHp);
    }
    else{
      Pokemon debuffedAttack = pokemon.get(index);
      debuffedAttack = new AttackDown(debuffedAttack);
    }
    
  }
  
  /**
  * Method that gets the pokemon from the trainer's ArrayList.
  * @param index holds for the index of the trainer's pokemon.
  * @return the trainer's pokemon at the index.
  */
  public Pokemon getPokemon(int index){
    return pokemon.get(index);

  }
  /**
  * Method that gets the list of all pokemons that trainer has.
  *@return the list of pokemons with their hp's so trainer can know how many pokemons he has with their health.
  */
  public String getPokemonList(){
    String p_list = "";
    
    for(int i=0; i<pokemon.size(); i++){
      int pokemonNumber = i + 1;
      p_list += ((pokemonNumber) + ". " + pokemon.get(i).getName() + " HP: " + pokemon.get(i).getHp() + "/" + pokemon.get(i).getMaxHp() + "\n");  
           
    }
    return p_list;

  }
  /**
  * Method that has the list of pokemon and will remove one from parameter index.
  *@param integer of index.
  *@return the list without one pokemon whixh we remove through the index.
  */
  public Pokemon removePokemon(int index){
    return pokemon.remove(index - 1);
    
  }
  /**
  * Method that gets the trainer's name, hp, inventory, pokemon, and position on the map.
  * @return a string of the trainer's name, hp, inventory, pokemon, and position on the map.
  */
  @Override
  public String toString(){
    String strTrainer = "" + this.getName() + " HP: " + this.getHp() + "/" + this.getMaxHp() + "\n" + "Money: " + money + "\n" + "Potions: " + potions + "\n" + "Poke Balls: " + pokeballs + "\n" + "Pokemon\n-------\n" + getPokemonList() + "\n" + Map.getInstance().mapToString(loc); 
    return strTrainer; 

  }
}
