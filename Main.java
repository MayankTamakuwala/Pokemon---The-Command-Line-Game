/**
@author Mayank Tamakuwala
Date: 12/1/2021
Description: This program allows a player who is a pokemon trainer to explore the game and encounter new places and events and face other pokemon. We access the trainer's pokemon using getPokemon method and getting damage/heal in return,while also providing the player items like money potions and poke balls, and also allowing the player to earn money through battles or finding them on item spaces in the map. Pokemon can now debuff eachother in combat. Pokemon can also be buffed from potions that the user uses. The trainer can fight gym leaders located in the end of a map and become buffed once the gym is destroyed. The trainer can dicover new maps and catch new pokemon. The trainer will lose if he/she looses all hp and faints or decides to exit the program.
*/
public class Main {
    public static void main(String args[]) {
        int level = 1;
        // initializes
        Pokemon firstPoke = null;
        // Intro Sequence
        System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
        String name = CheckInput.getString();
        System.out.println("Great to meet you, " + name + "!");
        System.out.println("Choose your first pokemon:\n1. Charmander\n2. Squirtle\n3. Bulbasaur");

        // User chooses his/her's pokemon
        int pokeSelec = CheckInput.getIntRange(1, 3);
        if (pokeSelec == 1) {
            int randomHp = (int) (Math.random() * (30 - 20 + 1) + 20);
            firstPoke = new Fire("Charmander", randomHp, randomHp);
        } else if (pokeSelec == 2) {
            int randomHp = (int) (Math.random() * (30 - 20 + 1) + 20);
            firstPoke = new Water("Squirtle", randomHp, randomHp);
        } else if (pokeSelec == 3) {
            int randomHp = (int) (Math.random() * (30 - 20 + 1) + 20);
            firstPoke = new Grass("Bulbasaur", randomHp, randomHp);
        }

        // intializes map and mapNum
        Map m = Map.getInstance();
        int mapNum = 1;
        m.loadMap(mapNum);

        // intializes trainer and menu num
        Trainer user = new Trainer(name, firstPoke);

        int option = 0;

        // loops until user is fainted
        while (option != 5 && user.getHp() > 0) {
            // prints out interface
            System.out.println("\n" + user.toString() + "\n");
            option = mainMenu();
            char currectCharacter;
            if (option == 1) {
                if (user.getLocation().getX() - 1 >= 0) {
                    currectCharacter = user.goNorth();
                    level = mapChar(currectCharacter, user, level, mapNum, m);
                } else {
                    System.out.println("You cannot go that way.\n");
                }
            } else if (option == 2) {
                if (user.getLocation().getX() + 1 < 5) {
                    currectCharacter = user.goSouth();
                    level = mapChar(currectCharacter, user, level, mapNum, m);
                } else {
                    System.out.println("You cannot go that way.\n");
                }
            }

            else if (option == 3) {
                if (user.getLocation().getY() + 1 < 5) {
                    currectCharacter = user.goEast();
                    level = mapChar(currectCharacter, user, level, mapNum, m);
                } else {
                    System.out.println("You cannot go that way.\n");
                }
            } else if (option == 4) {
                if (user.getLocation().getY() - 1 >= 0) {
                    currectCharacter = user.goWest();
                    level = mapChar(currectCharacter, user, level, mapNum, m);
                } else {
                    System.out.println("You cannot go that way.\n");
                }
            }
        }
        System.out.println("Game Over");
    }

    /**
     * Print the Main Menu and takes the input from the user
     * 
     * @return The valid input from the user
     */
    public static int mainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
        int menuSelect = CheckInput.getIntRange(1, 5);
        return menuSelect;
    }

    /**
     * Description: allows for trainer to attack wild pokemon and possibly capture
     * it
     *
     * @param t:    trainer passes in method
     *
     * @param wild: wild pokemon passes into method
     */
    public static Pokemon trainerAttack(Trainer t, Pokemon wild) {
        System.out.println("Choose your Pokemon: \n" + t.getPokemonList());
        int pokemonChoice = CheckInput.getIntRange(1, t.getNumPokemon());
        Pokemon pokemonUsed = t.getPokemon(pokemonChoice - 1);
        // pokemon is chosen for attack
        if (pokemonUsed.getHp() > 0) {
            System.out.println(pokemonUsed.getName() + ", I choose you!");
            System.out.println(pokemonUsed.getAttackTypeMenu());
            int attackChoice = CheckInput.getIntRange(1, pokemonUsed.getNumAttackTypeMenuItems());
            // menu is given here for pokemon used in basic and special attacks with wild
            // pokemon
            System.out.println(pokemonUsed.getAttackMenu(attackChoice));
            int move = CheckInput.getIntRange(1, pokemonUsed.getNumAttackMenuItems(attackChoice));
            System.out.println(pokemonUsed.attack(wild, attackChoice, move));
            int debuffchance = (int) (Math.random() * (100 - 1 + 1) + 1);
            if (debuffchance < 25) {
                wild = PokemonGenerator.getInstance().addRandomDebuff(wild);
            }
        } // else the trainer decides to run away
        else {
            System.out.println(pokemonUsed.getName() + " won't be able to fight");
        }

        if (wild.getHp() > 0) {
            // trainer will be receiving different attack types here
            int receivingAttackType = (int) (Math.random() * (wild.getNumAttackTypeMenuItems() - 1 + 1) + 1);
            int receivingAttackMove = (int) (Math.random()
                    * (wild.getNumAttackMenuItems(receivingAttackType) - 1 + 1) + 1);
            System.out.println(wild.attack(pokemonUsed, receivingAttackType, receivingAttackMove));
            // wild pokemon is demonstrated its hp here
            System.out.println(wild.toString());
            int debuffchance = (int) (Math.random() * (100 - 1 + 1) + 1);
            if (debuffchance < 10) {
                t.debuffPokemon(pokemonChoice - 1);
            }
        }
        return wild;
    }

    /**
     * Description: allows for trainer to pass into store and choose from buying a
     * pokeball, potions, and exiting
     *
     * @param t: passes in trainer to add items to trainer
     */
    public static void store(Trainer t) {

        // intitializes i variable
        int i = 0;

        while (i == 0) {
            // loops menu until trainer decides to exit
            System.out
                    .println("Hello! What can I help you with?.\n1. Buy Potions - 5$\n2. Buy Poke Balls - 3$\n3. Exit");
            int choice = CheckInput.getIntRange(1, 3);
            // trainer decides to buy pokeball for 5$
            if (choice == 1) {
                if (t.getMoney() >= 5) {
                    t.spendMoney(5);
                    t.receivePotion();
                    System.out.println("Here's your potion. Nice");
                }
            }

            // trainer decides to buy potions for 3$
            if (choice == 2) {
                if (t.getMoney() >= 3) {
                    t.spendMoney(3);
                    t.receivePokeball();
                    System.out.println("Hope you catch good pokemon with those.");
                }
            }
            // trainer leaves store
            if (choice == 3) {
                System.out.println("Thanks for visiting");
                i++;
            }
        }
    }

    /**
     * This Function checkes the map character and calls into the switch case to do
     * actions
     * 
     * @param currectCharacter The current map character
     * @param user             The trainer object
     * @param level            The level of the trainer
     * @param mapNum           The number current map
     * @param m                The Map object
     * @return The updated level of the trainer
     */
    public static int mapChar(char currectCharacter, Trainer user, int level, int mapNum, Map m) {
        switch (currectCharacter) {
            // case f allow for next map to load
            case 'f':
                System.out.println("You have arrived to gym. Defeat the Gym Leader to go to the next level.");
                Pokemon wild = PokemonGenerator.getInstance().generateRandomPokemon(level + 2);
                int totalHp = 0;
                int choice = -1;
                System.out.println("Gym Leader's Pokemon is " + wild.toString());

                for (int i = 0; i < user.getNumPokemon(); i++) {
                    totalHp += user.getPokemon(i).getHp();
                }
                while (totalHp > 0 && wild.getHp() > 0) {
                    System.out.println("What do you want to do?\n1. Fight\n2. Use Potion");
                    choice = CheckInput.getIntRange(1, 2);
                    if (choice == 1) {
                        wild = trainerAttack(user, wild);
                    } else if (choice == 2) {
                        if (user.hasPotion()) {
                            System.out.println("Choose the Pokemon you want to use potion on: ");
                            System.out.println(user.getPokemonList());
                            int pokemonChoice = CheckInput.getIntRange(1, user.getNumPokemon());
                            Pokemon pokemonUsed = user.getPokemon(pokemonChoice - 1);
                            if (pokemonUsed.getHp() < pokemonUsed.getMaxHp()) {
                                user.usePotion(pokemonChoice - 1);
                            } else {
                                System.out.println(pokemonUsed.getName() + " is already on a max health.");
                            }
                        } else {
                            System.out.println("You have no potion left.");
                        }
                    }
                }
                level += 1;
                System.out.println("\nYou've found the finish...You have passed to next level.");
                user.buffAllPokemon();
                if (mapNum == 1) {
                    mapNum = 2;
                    m.loadMap(mapNum);
                } else if (mapNum == 2) {
                    mapNum = 3;
                    m.loadMap(mapNum);
                } else if (mapNum == 3) {
                    mapNum = 1;
                    m.loadMap(mapNum);
                }
                break;

            // case i allows for random item to be found
            case 'i':
                int rand = (int) (Math.random() * 2);
                if (rand == 0) {
                    // trainer will find and receive a poke ball or potion
                    System.out.println("You found a poke ball");
                    user.receivePokeball();
                } else {
                    System.out.println("You found a potion");
                    user.receivePotion();
                }
                m.removeCharAtLoc(user.getLocation());
                break;

            // case n is nothing
            case 'n':
                System.out.println("There's nothing here...");
                break;

            // case w is encountering a wild animal
            case 'w':
                wild = PokemonGenerator.getInstance().generateRandomPokemon(level);

                System.out.println("You have Encountered " + wild.toString());

                System.out.println(
                        "\nWhat do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away");
                choice = CheckInput.getIntRange(1, 4);

                while (choice != 4) {
                    if (choice == 1) {
                        if (wild.getHp() > 0) {
                            wild = trainerAttack(user, wild);
                        } else {
                            System.out.println(wild.getName() + " is fainted and you can throw pokeball to catch it.");
                        }
                    } else if (choice == 2) {
                        if (user.hasPotion()) {
                            System.out.println("Choose the Pokemon you want to use potion on: ");
                            System.out.println(user.getPokemonList());
                            int pokemonChoice = CheckInput.getIntRange(1, user.getNumPokemon());
                            Pokemon pokemonUsed = user.getPokemon(pokemonChoice - 1);
                            if (pokemonUsed.getHp() < pokemonUsed.getMaxHp()) {
                                user.usePotion(pokemonChoice - 1);
                                pokemonUsed = PokemonGenerator.getInstance().addRandomBuff(pokemonUsed);
                            } else {
                                System.out.println(pokemonUsed.getName() + " is already on a max health.");
                            }
                        } else {
                            System.out.println("You have no potion left.");
                        }
                    } else if (choice == 3) {
                        // catch and remove pokemon
                        if (user.getNumPokemon() < 6) {
                            if (user.hasPokeball()) {
                                if (user.catchPokemon(wild)) {
                                    // catch the wild pokemon
                                    System.out
                                            .println("Shake...Shake...Shake\nYou caught " + wild.getName());
                                    choice = 4;
                                    break;
                                } else {
                                    // wild pokemon is still fighting
                                    System.out.println(wild.getName() + " is still capable to fight");
                                }
                            } else {
                                System.out.println("You have no Pokeball left.");
                            }
                        } else {
                            System.out.println(
                                    "You can only have in total 6 Pokemons.\nDo you want to remove a pokemon?");
                            System.out.println("1. Yes\n2. No");
                            choice = CheckInput.getIntRange(1, 2);
                            if (choice == 1) {
                                System.out.println("Choose the Pokemon You want to remove:\n" + user.getPokemonList());
                                choice = CheckInput.getIntRange(1, user.getNumPokemon());
                                Pokemon removedPokemon = user.removePokemon(choice - 1);
                                System.out.println("You removed " + removedPokemon.toString());
                                choice = 3;
                            } else {
                                choice = 4;
                            }
                        }
                    }
                    System.out.println(
                            "\nWhat do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away");
                    choice = CheckInput.getIntRange(1, 4);
                }
                break;

            case 'p':
                int encounter = 0;
                encounter = (int) ((Math.random() * 3) + 1);
                // attacks trainer
                if (encounter == 1) {
                    System.out.println(
                            "You encounter Team Rocket!\nJessie: Give us your pokemon or we'll beat you up\nJames: You choice. We gave you a chance\nYou are dealt 4 damage");
                    user.takeDamage(4);
                    m.removeCharAtLoc(user.getLocation());
                }
                // gives trainer money
                else if (encounter == 2) {
                    System.out
                            .println(
                                    "You run into Brock\nBrock: You need money?\nBrock: I got you.\nYou recive 5$ from Brock");
                    user.receiveMoney(5);
                    m.removeCharAtLoc(user.getLocation());
                }
                // gives trainer items
                else if (encounter == 3) {
                    System.out
                            .println(
                                    "You encounter May\nMay: hey looks like you need potions\nMay: I have some to spare.Goodl");
                    user.receivePotion();
                    m.removeCharAtLoc(user.getLocation());

                }
                break;

            // case c allows for user to enter the city
            case 'c':
                System.out.println(
                        "You entered the city. Where would you like to go?\n1. Store\n2. Pokemon Hospital");

                choice = CheckInput.getIntRange(1, 2);
                if (choice == 1) {
                    store(user);
                } else if (choice == 2) {
                    System.out.println(
                            "Welcome to the pokemon hospital.\nI'll fix your pokemon in a bit.\nThere you go all healed up.");

                    user.healAllPokemon();
                }
                break;
        }
        return level;
    }
}