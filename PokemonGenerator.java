/**
* Class for generating Pokemon
* @author Dev Paul
* Last Updated: 12/01/21
*/

import java.util.HashMap;
import java.io.*;
import java.util.Scanner;
import java.util.Set;

public class PokemonGenerator {
//EPP: Error Potential Point - Points in the code with the possibility of error.
	
	
	HashMap<String, String> pokemon = new HashMap <String, String>();
	//Pokemon<String_Name, String_Type>
	//Ex: Pokemon<Squirtle,Water>
	
	static PokemonGenerator instance = null;
	//By default the instance is null, until called upon...
	
	/**
	 * Reads through the PokemonList.text files and sorts information of Pokemon name and type to Pokemon HashMap.
	 * Example: Line 2: Charmander,Fire -> HashMap<StringName = Charmander, StringType = Fire> -> HashMap organizes information
	 */
	private PokemonGenerator(){ //no parameters
		//sets default values of data members
		try {
			Scanner reader = new Scanner( new File( "PokemonList.txt" ) );
			//Attempt to access file: PokemonList.txt
			while( reader.hasNext() ) {
				 String[] pokeInfo = reader.nextLine().split(",");
				 //reader.nextLine() - Is the next line of the file (Ex: Bulbasaur,Grass)
				 //.split(",") - Splits the provided line into an array of 2 strings (Ex: Bulbasaur [Index: 0], Grass [Index 1]
				 pokemon.put(pokeInfo[0],pokeInfo[1]);
				 //.put - Stores information into the Hashmap<String, String>. We will add index 0 of our new array to the first string and index 1 to the second string
				 //Ideally the code looks like this: pokemon.put(StringPokemon = [Index 0: Bulbasaur], StringType = [Index 1: Grass])
			}
			//While there's another line available, reader will continue to organize information it finds
			reader.close(); 
			//File closes
		} 
		catch( FileNotFoundException fnf ) {
			   System.out.println("Error: File not found");
			   //EPP 1: File Not Found
		}
		 
	}
	 /**
	  * Retrieves an instance of PokemonGenerator
	  * @return an instance of PokemonGenerator() if not running already
	  */
	public static PokemonGenerator getInstance() {
		if(instance == null) {
			  instance = new PokemonGenerator();
		  }
		  return instance;
	}
	//class methods

	/**
	 * @param level - Level of randomly Generated Pokemon
	 * @return randomly Generated Pokemon of random type, name, and buffs (if any)
	 */
	public Pokemon generateRandomPokemon(int level) {
		
		//min val = 0
		//max val = pokemon.size()
		//range = pokemon.size()[max] - 0[min] = pokemon.size()
		int chance = (int)(Math.random() * (pokemon.size())) + 0;
		Set<String> keys = PokemonGenerator.getInstance().pokemon.keySet();
		int i = 0;
		Pokemon random;
		for( String s : keys ) {
			if(chance == i) {
				String name = s;
				//retrieves the RandomPokemon's name
				
				random = this.getPokemon(name);
        //Gets a pokemon from HashMap
				
				/* Then for each level greater than one, 
				 * repeatedly decorate it with a random buff (ex. a level 3 pokemon
				 * might be a Fire pokemon decorated with +ATK and +HP), this will 
				 * cause the constructed pokemon to gain an additional title, increase 
				 * its hp and maxHp, and/or do additional damage.
				 */
				for(int j = 1; j < level; j++) {
					random = this.addRandomBuff(random);
				}
        return random;
			}
			   i++;
			}
		return null;
		//EPP 2: Random Generation Failed
	}
	
	/**
	 * Constructs Pokemon Object of corresponding type
	 * @param name: name of provided Pokemon
	 * @return Pokemon of type (fire, water, or grass), with random generated HP
	 */
	public Pokemon getPokemon(String name) {
		String type = pokemon.get(name);
		//retrieves the RandomPokemon's Type information
		
		//min val = 20
		//max val = 25
		//range = 25[max] - 20[min] + 1 = 6
		int hp = (int)(Math.random() * 6) + 20;
		
		if(type.equals("Fire")) {
			return new Fire(name, hp, hp);
		}else if(type.equals("Water")) {
			return new Water(name, hp, hp);
		}else {
			return new Grass(name, hp, hp);
		}
	}
	
	/**
	 * @param p - provided Pokemon
	 * @return pokemon with an added buff
	 */
	public Pokemon addRandomBuff(Pokemon p) {
		//min val = 0
		//max val = 1
		//range = 1[max] - 0[min] + 1 = 2
		int c = (int)(Math.random() * 2) + 0;
		if(c == 0) {
			AttackUp atk = new AttackUp(p);
			p = atk;
		} else {
			HpUp hp = new HpUp(p);
			p = hp;
		}
		return p;
	}
	
	/**
	 * @param p - provided Pokemon
	 * @return pokemon with an added debuff
	 */
	public Pokemon addRandomDebuff(Pokemon p) {
		//min val = 0
				//max val = 1
				//range = 1[max] - 0[min] + 1 = 2
				int c = (int)(Math.random() * 2) + 0;
				if(c == 0) {
					AttackDown atk = new AttackDown(p);
					p = atk;
				} else {
					HpDown hp = new HpDown(p);
					p = hp;
				}
				return p;
	}
	
	/*
	public static void main(String args[]) {
		//Tester Method
		//System.out.println(PokemonGenerator.getInstance().pokemon.toString());
		Set<String> keys = PokemonGenerator.getInstance().pokemon.keySet();
		for( String s : keys ) {
			   System.out.println( s + " got a " + PokemonGenerator.getInstance().pokemon.get( s ) );
			}
	}
	*/
}
