/**
*This class represents Hp and extends PokemonDecorator;
@author Om Kakadiya
**/



public class HpDown extends PokemonDecorator{
  /**
	 * Adds -Hp sticker to Pokemon name via PokemonDecorator
	 * @param p - given pokemon
	 */
  public HpDown(Pokemon p){
		super(p, "-HP", -1);
	}
  

  
}
	