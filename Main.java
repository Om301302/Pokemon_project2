/**
 * Main method.
 * 
 * @author Gabriel Villanueva
 * @author Om Kakadiya

 * @author Dev Paul (contributions in Pokemon Project 2)
 */

class Main {

	public static void main(String[] args) {

		boolean gameRunning = true;

		while (gameRunning) {
			int trainerLvl = 1; // initial Level value

			// Welcome the user and prompt user to enter a name and choose a pokemon.
			System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
			String trainerName = CheckInput.getString();
			System.out.println();
			System.out.println("Great to meet you, " + trainerName + "!");
			System.out.println("Choose your starter pokemon: \n1. Charmander\n2. Bulbasaur\n3. Squirtle");
			System.out.println();

			// Get number from user to determine the pokemon, and declare variable
			// starterPokemon.
			int starterPokemonNum = CheckInput.getIntRange(1, 3);
			Pokemon starterPokemon;

			// Determine which pokemon is chosen, 1.Charmander,2.Bulbasaur,3.Squirtle.
			if (starterPokemonNum == 1) {
				starterPokemon = PokemonGenerator.getInstance().getPokemon("Charmander");

			} else if (starterPokemonNum == 2) {
				starterPokemon = PokemonGenerator.getInstance().getPokemon("Bulbasaur");

			} else {
				starterPokemon = PokemonGenerator.getInstance().getPokemon("Squirtle");

			}

			/*
			 * Instantiate the map, load the first map, instantiate and construct the
			 * trainer, then reveal the start location.
			 */
			Map map = Map.getInstance();
			map.loadMap(1);
			Trainer trainer = new Trainer(trainerName, starterPokemon);
			map.reveal(trainer.getLocation());

			/*
			 * variable mapNum initialized to one to represent which map to load next and
			 * variable choice to represent the choice of user.
			 */
			int mapNum = 1;
			int choice = 0;

			// While loop to continue until game ends when choice is 5 or trainer's hp is
			// zero.
			while (choice != 5 && trainer.getHp() != 0) {

				/*
				 * Display the trainer stats/inventory/map and display the menu of choices user
				 * will input.
				 */
				System.out.println(trainer);
				choice = mainMenu();
				/*
				 * character variable declared to initialize what character is returned after
				 * user enters in a choice.
				 */
				char character = 'o';

				/*
				 * Depending on choice 1-5, trainer either goes in a direction where a character
				 * is returned, or the trainer quits and the program ends.
				 */
				if (choice == 1) {
					character = trainer.goNorth();

				} else if (choice == 2) {
					character = trainer.goSouth();

				} else if (choice == 3) {
					character = trainer.goEast();

				} else if (choice == 4) {
					character = trainer.goWest();

				} else {
					System.out.println("Game Over");
					gameRunning = false;
					break;

				}

				/*
				 * When the character returned is 'i', a random item is created, generate a
				 * random number from 1-2.
				 */
				if (character == 'i') {
					int randomItemNum = (int) (Math.random() * 2) + 1;

					/*
					 * if random number is one give the trainer a pokeball and if it is 2 give the
					 * trainer a potion, then remove the character i from the map.
					 */
					if (randomItemNum == 1) {
						trainer.receivePokeball();
						System.out.println("" + trainer.getName() + " found a Poke Ball!");

					} else {
						trainer.receivePotion();
						System.out.println("" + trainer.getName() + " found a potion!");

					}
					System.out.println();
					map.removeCharAtLoc(trainer.getLocation());

				}

				// if the character returned is n, then display that nothing is there.
				if (character == 'n') {
					System.out.println("There's nothing here...");
					System.out.println();
				}

				/*
				 * if the character returned is w, generate a random pokemon, then call the
				 * trainerAttack method to run an encounter between the trainer and the wild
				 * pokemon.
				 */
				if (character == 'w') {
					// Pokemon wild = chooseRandomPokemon();
					Pokemon wild = PokemonGenerator.getInstance().generateRandomPokemon(trainerLvl);

					int f = 0; // f for fainted
					while ((wild.getHp() != 0) || (f == trainer.getNumPokemon())) {
						System.out.println(wild);
						// code referenced from Dev's Pokemon Project 1 ##############################
						f = 0; // f for fainted
						for (int i = 0; i < trainer.getNumPokemon(); i++) {
							if (trainer.getPokemon(i).getHp() <= 0) {
								f++;
							}
						}
						if (f == trainer.getNumPokemon()) {
							// System.out.println("Game Over");
							gameRunning = false;
							break;
						}
							System.out.println("A wild " + wild.getName() + " has appeared.");
							// Variable isAllFainted false, true when all pokemon have hp of zero.
							boolean isAllFainted = false;

							// Loop encounter until wild pokemon has fainted or all trainer's pokemon has
							// fainted.
							System.out.println("What do you want to do?");
							System.out.println("1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away");
							// Integer trainerChoice for user input to choose an action for the trainer.
							int trainerChoice = CheckInput.getIntRange(1, 4);
							System.out.println();

							// Trainer chooses to fight.
							if (trainerChoice == 1) {

//ATTACK HERE
								wild = trainerAttack(trainer, wild);

							}

							// Trainer chooses to use potion.
							if (trainerChoice == 2) {

								// If trainer has a potion choose which pokemon to heal.
								if (trainer.hasPotion()) {

									// Prompt trainer's pokemon list and get the number of the pokemon.
									System.out.println("Choose a pokemon to use the potion on: ");
									System.out.println(trainer.getPokemonList());
									int pokemonToHeal = CheckInput.getIntRange(1, trainer.getNumPokemon());

									// Subtract the number to get the true index of the pokemon then heal it.
									trainer.usePotion(pokemonToHeal - 1);

									// Give a random buff to the healed pokemon (if 1, attackUp; if 2, hpUp).
									int randomBuff = (int) (Math.random() * 2) + 1;
									if (randomBuff == 1) {
										Pokemon pokemonToBuff = trainer.getPokemon(pokemonToHeal - 1);
										pokemonToBuff = new AttackUp(pokemonToBuff);
										System.out.println("" + pokemonToBuff + "'s attack was buffed.");
									} else if (randomBuff == 2) {
										Pokemon pokemonToBuff = trainer.getPokemon(pokemonToHeal - 1);
										pokemonToBuff = new HpUp(pokemonToBuff);
										System.out.println("" + pokemonToBuff + "'s HP was buffed.");
									}

									System.out.println("" + trainer.getPokemon(pokemonToHeal - 1) + " was fully healed!");

									// If no potion display that there are no potions..
								} else {

									// Print you have no potions.
									System.out.println("You have no potions!");

								}
							}

							// Trainer chooses to throw a pokeball.
							if (trainerChoice == 3) {

								// If trainer has a pokeball try to catch it..
								if (trainer.hasPokeball()) {

									// If pokemon is caught.
									if (trainer.catchPokemon(wild)) {

										// Print that trainer caught it and break from loop/interaction.
										System.out.println("You have caught " + wild.getName() + "!");

										// If trainer surpasses number of Pokemon they can have (6), they will be
										// prompted to remove a Pokemon from their list.
										if (trainer.getNumPokemon() >= 6) {
											System.out.println(
													"Maximum Pokemon Capacity has been exceeded. Please get rid of a pokemon: "
															+ trainer.getPokemonList());
											int pokeRid = CheckInput.getIntRange(1, trainer.getNumPokemon());
											System.out.println(
													trainer.removePokemon(pokeRid).getName() + " has been removed...");
										}

										break;

										// If pokemon was not caught.
									} else {

										// Pokemon broke free from pokeball.
										System.out.println("" + wild.getName() + " broke free from the pokeball.");
									}

									// If trainer has no pokeball.
								} else {

									// Print no pokeballs.
									System.out.println("You have no pokeballs!");
								}

							}

							// Trainer chooses to run away.
							if (trainerChoice == 4) {

								// Generate a random number for a random direction to travel.
								int randomDirection = (int) (Math.random() * 4) + 1;

								// 1 is north, 2 is south, 3 is east, 4 is west.
								if (randomDirection == 1) {
									trainer.goNorth();
								} else if (randomDirection == 2) {
									trainer.goSouth();
								} else if (randomDirection == 3) {
									trainer.goEast();
								} else if (randomDirection == 4) {
									trainer.goWest();
								}

								// Print you ran away then break from the loop/interaction.
								System.out.println("You ran away.");
								break;
							}

							// Loop to check if the health of the pokemon.
							for (int numPoke = 0; numPoke < trainer.getNumPokemon(); numPoke++) {

								// If a pokemon is healthy break from for loop and isAllFainted stays false.
								if (trainer.getPokemon(numPoke).getHp() > 0) {
									break;
								}

								// If at the end of the loop all pokemon are fainted, isAllFainted is true.
								if (numPoke + 1 == trainer.getNumPokemon()) {
									isAllFainted = true;

									// Random damage is done to the trainer.
									int randomTrainerDamage = (int) (Math.random() * 5) + 1;
									trainer.takeDamage(randomTrainerDamage);

									/*
									 * Display that all pokemon have fainted and that trainer is delt random damage
									 * while wild pokemon runs off.
									 */
									System.out.println("All your Pokemon have fainted...");
									System.out.println("" + wild.getName() + " smacks " + trainer.getName() + " for "
											+ randomTrainerDamage + " damage and runs off.");

								}

							}

						}

					if (f == trainer.getNumPokemon()) {
						System.out.println("Game Over");
						gameRunning = false;
						break;
						// Game Over Statement
					}
					// end of code reference ##############################

					/*
					 * if the wild pokemon faints or if the pokemon is caught remove the character
					 * 'w' from the map.
					 */
					if (wild.getHp() == 0 || trainer.getPokemon(trainer.getNumPokemon() - 1) == wild) {
						map.removeCharAtLoc(trainer.getLocation());
					}
					System.out.println();
				}

				/*
				 * if the character returned is p then generate a random number 1-5, and
				 * depending on the number will do damage, or give the trainer some items, the
				 * remove 'p' from the map.
				 */
				if (character == 'p') {
					int randomPersonNum = (int) (Math.random() * 5) + 1;

					if (randomPersonNum == 1) {
						trainer.takeDamage(3);
						System.out.println("Team Rocket jumped " + trainer.getName() + " and did 3 damage.");

					} else if (randomPersonNum == 2) {
						trainer.receiveMoney(10);
						System.out.println("" + trainer.getName() + " defeated a trainer in battle and received $10.");

					} else if (randomPersonNum == 3) {
						trainer.receivePotion();
						System.out.println("" + trainer.getName() + " received a potion from a nice old lady.");

					} else if (randomPersonNum == 4) {
						trainer.receivePokeball();
						System.out.println("" + trainer.getName()
								+ " received a Poke Ball from a salesman who was advertising on by the road.");

					} else {
						trainer.receiveMoney(5);
						trainer.receivePotion();
						trainer.receivePokeball();
						System.out.println("" + trainer.getName()
								+ " met up with Misty and received a parcel from her, which contains $5, a potion, and a Poke Ball.");

					}

					System.out.println();
					map.removeCharAtLoc(trainer.getLocation());

				}

				/*
				 * if the character returned is c, let the user input num 1-2, if 1 trainer goes
				 * to the store, and if 2, trainer goes to the Pokemon Center to heal all of
				 * their pokemon.
				 */
				if (character == 'c') {
					System.out.println("You are in the city! Where do you want to go?");
					System.out.println("1. Store, 2. Pokemon Center");
					int cityChoice = CheckInput.getIntRange(1, 2);
					if (cityChoice == 1) {
						store(trainer);

					} else {
						System.out.println(
								"Welcome to the Pokemon Center, I'll fix your poor Pokemon in a jiffy! There you go. See you soon again!");
						trainer.healAllPokemon();

					}
					System.out.println();
				}

				/*
				 * if the character returned is f, the map is reloaded, if the integer variable
				 * mapNum equals 1 or 2, increment it and load that specific map number, and if
				 * mapNum equals 3, subtract 2 from it to load map 1.
				 */
				if (character == 'f') {
					System.out.println("You have been issued a challenge by a gym leader!");
					Pokemon p = PokemonGenerator.getInstance().generateRandomPokemon(trainerLvl + 2);

					// ######################################################################################################

					// Variable isAllFainted false, true when all pokemon have hp of zero.
					boolean isAllFainted = false;

					// Loop encounter until wild pokemon has fainted or all trainer's pokemon has
					// fainted.
					while (p.getHp() != 0 && isAllFainted == false) {
						System.out.println(p);
						System.out.println("What do you want to do?");
						System.out.println("1. Fight\n2. Use Potion");
						// Integer trainerChoice for user input to choose an action for the trainer.
						int trainerChoice = CheckInput.getIntRange(1, 2);
						System.out.println();

						// Trainer chooses to fight.
						if (trainerChoice == 1) {
							System.out.println("Choose a Pokemon: \n");
							System.out.println(trainer.getPokemonList());

							// Trainer chooses Pokemon from their collection.
							int trainerPokemonChoice = CheckInput.getIntRange(1, trainer.getNumPokemon());
							Pokemon currentPokemon = trainer.getPokemon(trainerPokemonChoice - 1);

							/* If trainer chooses a fainted pokemon, continue. */
							if (currentPokemon.getHp() == 0) {
								System.out.println(
										"" + currentPokemon.getName() + " has fainted...\nChoose another Pokemon \n");
								continue;
							}

							// Pokemon is chosen.
							System.out.println("" + currentPokemon.getName() + ", I choose you!");

							// Display the attack menu and get the type of attack from user input (1,2).
							System.out.println(currentPokemon.getAttackTypeMenu());
							int attackType = CheckInput.getIntRange(1, currentPokemon.getNumAttackTypeMenuItems());

							// Basic Attack Prompt if user enter enters 1, then inputs the basicAttack.
							System.out.println(currentPokemon.getAttackMenu(attackType));
							int move = currentPokemon.getNumAttackMenuItems(attackType);
							System.out.println(currentPokemon.attack(p, attackType, move));

							// If gym pokemon fainted..
							if (p.getHp() == 0) {
								System.out.println("" + p.getName() + " has fainted.");
								System.out.println("You won!");
								trainerLvl += 1;

								/*
								 * If gym pokemon is not fainted generate a random attack using a random number
								 * generator for the attackType and the specifc move for the wild pokemon.
								 */
							} else if (p.getHp() > 0) {

								// Generate a random attack for the wild pokemon.
								int wildAttackType = (int) (Math.random() * 2) + 1;
								int wildAttackMove = (int) (Math.random() * 3) + 1;

								System.out.println(p.attack(currentPokemon, wildAttackType, wildAttackMove));

							}
						}

						// Trainer chooses to use potion.
						if (trainerChoice == 2) {

							// If trainer has a potion choose which pokemon to heal.
							if (trainer.hasPotion()) {

								// Prompt trainer's pokemon list and get the number of the pokemon.
								System.out.println("Choose a pokemon to use the potion on: ");
								System.out.println(trainer.getPokemonList());
								int pokemonToHeal = CheckInput.getIntRange(1, trainer.getNumPokemon());

								// Subtract the number to get the true index of the pokemon then heal it.
								trainer.usePotion(pokemonToHeal - 1);
								System.out.println("" + trainer.getPokemon(pokemonToHeal - 1) + " was fully healed!");

								// If no potion display that there are no potions..
							} else {

								// Print you have no potions.
								System.out.println("You have no potions!");

							}
						}

						// Loop to check if the health of the pokemon.
						for (int numPoke = 0; numPoke < trainer.getNumPokemon(); numPoke++) {

							// If a pokemon is healthy break from for loop and isAllFainted stays false.
							if (trainer.getPokemon(numPoke).getHp() > 0) {
								break;
							}

							// If at the end of the loop all pokemon are fainted, isAllFainted is true.
							if (numPoke + 1 == trainer.getNumPokemon()) {
								isAllFainted = true;

								// Random damage is done to the trainer.
								int randomTrainerDamage = (int) (Math.random() * 5) + 1;
								trainer.takeDamage(randomTrainerDamage);

								/*
								 * Display that all pokemon have fainted and that trainer is dealt random damage
								 * while gym leader runs off.
								 */
								System.out.println("All your Pokemon have fainted...");
								System.out.println("Maybe next time...");

							}

						}

					}

					// ######################################################################################################

					System.out.println("You have entered a new area.");
					if (mapNum == 1) {
						mapNum += 1;
						map.loadMap(mapNum);
						map.reveal(trainer.getLocation());

					} else if (mapNum == 2) {
						mapNum += 1;
						map.loadMap(mapNum);
						map.reveal(trainer.getLocation());

					} else if (mapNum == 3) {
						mapNum -= 2;
						map.loadMap(mapNum);
						map.reveal(trainer.getLocation());

					}
					System.out.println();
				}

				/*
				 * check if the trainers hp is zero, and if it is display Game Over, then the
				 * program ends.
				 */
				if (trainer.getHp() == 0) {
					System.out.println("" + trainer.getName() + " has 0 health.");
					System.out.println("Game Over");
					gameRunning = false;
					break;

				}
			}
		}

	}

	/**
	 * Method displays the main menu returns an integer choice.
	 * 
	 * @return the integer choice 1-5 that the user inputs.
	 * @author Om Kakadiya
	 **/
	public static int mainMenu() {
		System.out.println("Main Menu:");
		System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
		int choice = CheckInput.getIntRange(1, 5);
		return choice;
	}

	/*
	 * Method for when a trainer encounters a wild pokemon, display that a wild
	 * pokemon has appeared, and in a while loop, loop the encounter until the wild
	 * pokemon's hp is zero or if all of the trainer's pokemon has fainted.
	 * 
	 * @param t holds for a trainer object that is going against a wild pokemon.
	 * 
	 * @param wild holds for a randomly generated wild pokemon that goes against the
	 * trainer.
	 * 
	 * @author Gabriel Villanueva
	 **/
	public static Pokemon trainerAttack(Trainer t, Pokemon wild) {
        System.out.println("Choose a Pokemon: \n");
        System.out.println(t.getPokemonList());

        //Trainer chooses Pokemon from their collection.
        int trainerPokemonChoice = CheckInput.getIntRange(1, t.getNumPokemon());
        Pokemon currentPokemon = t.getPokemon(trainerPokemonChoice - 1);

        /*If trainer chooses a fainted pokemon, continue.*/
        if(currentPokemon.getHp() == 0){
          System.out.println("" + currentPokemon.getName() + " has fainted...\nChoose another Pokemon \n");
          System.out.println(t.getPokemonList());

          //Trainer chooses Pokemon from their collection.
          trainerPokemonChoice = CheckInput.getIntRange(1, t.getNumPokemon());
          currentPokemon = t.getPokemon(trainerPokemonChoice - 1);
        }
        
        //Pokemon is chosen.
        System.out.println("" + currentPokemon.getName() + ", I choose you!");
        
        //Display the attack type menu and get the type of attack from user input (1,2).
        System.out.println(currentPokemon.getAttackTypeMenu());
        int attackType = CheckInput.getIntRange(1, currentPokemon.getNumAttackTypeMenuItems());

        //Get the menu of attacks based on attackType, and display the attack sequence.
        System.out.println(currentPokemon.getAttackMenu(attackType));
        int move = currentPokemon.getNumAttackMenuItems(attackType);
        System.out.println(currentPokemon.attack(wild, attackType, move));

        //Debuff chance.
        int debuffChance;
        int randomBuff;

        //currentPokemon debuffs wild (25% chance).
        debuffChance = (int) (Math.random() * 100) + 1;
        if(debuffChance <= 25){
          randomBuff = (int) (Math.random() * 2) + 1;
          if(randomBuff == 1){
            wild = new AttackDown(wild);
            System.out.println("" + wild + "'s attack fell.");
          }
          else {
            wild = new HpDown(wild);
            System.out.println("" + wild + "'s HP fell.");
          }
        }
        
        //If wild pokemon hp is zero idsplay it fainted..
        if(wild.getHp() == 0){
          System.out.println("" + wild.getName() + " has fainted.");

        /*If wild pokemon is not fainted generate a random attack using a random
        number generator for the attackType and the specifc move for the wild
        pokemon.*/
        } else if(wild.getHp() > 0){
          
          //Generate a random attack for the wild pokemon and attack currentPokemon.
          int wildAttackType = (int) (Math.random() * 2) + 1;
          int wildAttackMove = (int) (Math.random() * 3) + 1;
          System.out.println(wild.attack(currentPokemon, wildAttackType, wildAttackMove));

          //wild debuffs currentPokemon (25% chance)
          debuffChance = (int) (Math.random() * 100) + 1;
          if(debuffChance <= 10){
            randomBuff = (int) (Math.random() * 2) + 1;
            if(randomBuff == 1){
              currentPokemon = new AttackDown(currentPokemon);
              System.out.println("" + currentPokemon + "'s attack fell.");
            }
            else {
              currentPokemon = new HpDown(currentPokemon);
              System.out.println("" + currentPokemon + "'s HP fell.");
          }
        }
        }
		return wild;

	}// attack

	/**
	 * Method for when the trainer goes to the store.
	 * 
	 * @param t holds for a trainer object that is going to go to the store to buy
	 *          items.
	 **/
	public static void store(Trainer t) {
		// pokeballsPrice and potionPrice to hold for the cost of these items
		final int pokeballsPrice = 3;
		final int potionPrice = 5;
		// Boolean variable shopping to hold for true unitl user leaves. ,
		boolean shopping = true;

		// Looping until user leaves.
		while (shopping) {
			// Prompt user to enter a number 1,3 depending on what they want to do.
			System.out.println("What can I help you with?\n1. Buy Potion - $5\n2. Buy Poke Ball - $3\n3. Exit");
			int choice = CheckInput.getIntRange(1, 3);

			/*
			 * if 1 check to see if they can afford a potion, and if they can add potion to
			 * inventory.
			 */
			if (choice == 1) {
				if (t.spendMoney(potionPrice)) {
					t.receivePotion();
					System.out.println("Purchased a potion!");

				} else {
					System.out.println("You don't have enough money.");
				}
			}

			/*
			 * if 2 check to see if they can afford a pokeball, and if they can add pokeball
			 * to inventory.
			 */
			else if (choice == 2) {
				if (t.spendMoney(pokeballsPrice)) {
					t.receivePokeball();
					System.out.println("Purchased a poke ball!");

				} else {
					System.out.println("You don't have enough money.");

				}
			}

			/* if 3 thank the user and break from the loop. */
			else if (choice == 3) {
				System.out.println("Thank you! Come again soon!");
				shopping = false;

			}
			System.out.println();

		}

	}

}