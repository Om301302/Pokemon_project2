/**
* Class for decreasing damage
* @author Dev Paul
* Last Updated: 11/30/21
*/

public class AttackDown extends PokemonDecorator{
	
	/**
	 * Adds -ATK sticker to Pokemon name via PokemonDecorator
	 * @param p - given pokemon
	 */
	public AttackDown(Pokemon p){
		super(p, "-ATK", 0);
	}
	
	/**
	 * Adds an attack debuff of -1 damage
	 * @param type - provided type [IGNORED IN THIS CLASS]
	 * @return decreased damaged
	 */
	public int getAttackBonus(int type) {
		return -1; 
		//Damage Reduced by 1
	}
}
