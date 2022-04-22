/**
* Class for increasing damage
* @author Dev Paul
* Last Updated: 11/30/21
*/

public class AttackUp extends PokemonDecorator{
	
	/**
	 * Adds +ATK sticker to Pokemon name via PokemonDecorator
	 * @param p - given pokemon
	 */
	public AttackUp(Pokemon p){
		super(p, "+ATK", 0);
    
	}
	
	/**
	 * Adds an attack bonus of 1-2+ damage
	 * @param type - provided type [IGNORED IN THIS CLASS]
	 * @return increased damaged
	 */
	public int getAttackBonus(int type) {
		//min val = 1
		//max val = 2
		//range = 2 - 1 + 1 = 2
		return (int)(Math.random() * 2) + 1;
		//Damage increased by 1 or 2
	}
}
