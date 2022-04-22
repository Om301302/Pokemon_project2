/**
*This class represents Hp and extends PokemonDecorator;
@author Om Kakadiya
**/

public class HpUp extends PokemonDecorator{

  /**
	 * Adds +Hp sticker to Pokemon name via PokemonDecorator
	 * @param p - given pokemon
	 */
  public HpUp(Pokemon p){
    super(p, "+HP", (int)(Math.random() * 2) + 1);
	}
  



}
	