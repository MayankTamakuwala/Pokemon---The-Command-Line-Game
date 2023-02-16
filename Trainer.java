import java.awt.*;
import java.util.*;

public class Trainer extends Entity {

  private int money;
  private int potions;
  private int pokeballs;
  private Point loc;
  private ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();

  /**
   * Trainer Constructor
   * 
   * @param n The name of the trainer
   * @param p The trainer's initial pokemon that they chose
   */
  public Trainer(String n, Pokemon p) {
    super(n, p.getHp(), p.getMaxHp());
    money = 25;
    potions = 2;
    pokeballs = 4;
    pokemon.add(p);
    loc = Map.getInstance().findStart();
  }

  /**
   * Returns how much money the player has
   * 
   * @return the player's money
   */
  public int getMoney() {
    // get money for trainer
    return money;
  }

  /**
   * The player spends money for different items
   * 
   * @param amt How much the player is spending
   * @return true or false depending if the player had sufficient money to make a
   *         purchase
   */
  public boolean spendMoney(int amt) {
    // trainer spends money based on amount left
    if (money >= amt) {
      money -= amt;
      return true;
    } else {
      return false;
    }
  }

  /**
   * The player receives money in different encounters
   * 
   * @param amt How much money the player is receiving
   */
  public void receiveMoney(int amt) {
    // trainer receives money to add to current amount
    money += amt;
  }

  /**
   * Checks if the player has potions in their inventory
   * 
   * @return True or false depending if the player has potions in their inventory
   */
  public boolean hasPotion() {
    // trainer has potions in inventory
    if (potions > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * The player receives a potion
   */
  public void receivePotion() {
    // trainer receives a potion
    potions += 1;
  }

  /**
   * The player uses a potion on one of their Pokemon during battle
   * 
   * @param pokeIndex The chosen index in the arraylist of pokemon
   */
  public void usePotion(int pokeIndex) {
    // potion is used here for pokemon
    Pokemon pokemonToHeal = pokemon.get(pokeIndex);
    pokemonToHeal.heal();
    potions--;
  }

  /**
   * Checks if the player has pokeballs in their inventory
   * 
   * @return True or false if the player has pokeballs in their inventory
   */
  public boolean hasPokeball() {
    // player checks for poke balls in inventory
    if (pokeballs > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * The player receives a pokeball
   */
  public void receivePokeball() {
    // player receives a poke ball
    pokeballs += 1;
  }

  /**
   * Calculates the chances of catching a wild Pokekmon and catches pokemon.
   * 
   * @param p The pokemon the player is trying to catch @ return True or false is
   *          the pokemon is caught
   */
  public boolean catchPokemon(Pokemon p) {
    if (pokeballs > 0) {
      double percentHp = ((double) p.getHp() / (double) p.getMaxHp());
      double catchRate = 1 - percentHp;
      double randNum = Math.random();
      if (randNum <= catchRate) {
        pokemon.add(p); // pokemon captured
        return true;
      }
    }
    pokeballs--;
    return false; // unsuccessful capture
  }

  /**
   * Finds the player's location on the map
   * 
   * @return player's location on the map as a Point
   */
  public Point getLocation() {
    return loc;
  }

  /**
   * The player chooses to go north. Checks if that move is valid before changing
   * the player's location
   * 
   * @return the char at the new location on the map. Returns 'b' if the move is
   *         invalid
   */
  public char goNorth() {
    Map.getInstance().reveal(loc);
    loc.translate(-1, 0);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
   * The player chooses to go south. Checks if that move is valid before changing
   * the player's location
   * 
   * @return the char at the new location on the map. Returns 'b' if the move is
   *         invalid
   */
  public char goSouth() {
    Map.getInstance().reveal(loc);
    loc.translate(1, 0);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
   * The player chooses to go east. Checks if that move is valid before changing
   * the player's location
   * 
   * @return the char at the new location on the map. Returns 'b' if the move is
   *         invalid
   */
  public char goEast() {
    Map.getInstance().reveal(loc);
    loc.translate(0, 1);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
   * The player chooses to go west. Checks if that move is valid before changing
   * the player's location
   * 
   * @return the char at the new location on the map. Returns 'b' if the move is
   *         invalid
   */
  public char goWest() {
    Map.getInstance().reveal(loc);
    loc.translate(0, -1);
    return Map.getInstance().getCharAtLoc(loc);
  }

  /**
   * Get how many pokemon the player has
   * 
   * @return how many pokemon the player has
   */
  public int getNumPokemon() {
    return pokemon.size();
  }

  /**
   * Heals all of the player's pokemon
   */
  public void healAllPokemon() {
    for (Pokemon i : pokemon) {
      i.heal();
    }
  }

  /**
   * buffAllPokemon will buff all pokemons randomly from the list 
   */
  public void buffAllPokemon() {
    for (int i = 0; i < pokemon.size(); i++) {
      pokemon.set(i, PokemonGenerator.getInstance().addRandomBuff(pokemon.get(i)));
    }
  }

  /**
   * debuffPokemon will debuff a pokemon randomly during fights
   */
  public void debuffPokemon(int index) {
    pokemon.set(index, PokemonGenerator.getInstance().addRandomDebuff(pokemon.get(index)));
  }

  /**
   * Get the Pokemon at the specified index in the pokemon arraylist
   * 
   * @param index within the pokemon arraylist
   * @return the Pokemon at index
   */
  public Pokemon getPokemon(int index) {
    return pokemon.get(index);
  }

  /**
   * The list of the player's pokemon and their hp's
   * @return the player's list of pokemon as a String
   */
  public String getPokemonList() {
    String pokemonList = "";
    for (int i = 0; i < pokemon.size(); i++) {
      pokemonList += ((i + 1) + ". " + pokemon.get(i).getName() + " HP: " + pokemon.get(i).getHp() + "/"
          + pokemon.get(i).getMaxHp() + "\n");
    }
    return pokemonList;
  }

  /**
   * Remove the Pokemon at the specified index in the pokemon arraylist
   * 
   * @param index within the pokemon arraylist
   * @return the removed Pokemon at index
   */
  public Pokemon removePokemon(int index) {
    Pokemon removedPokemon = pokemon.get(index);
    pokemon.remove(index);
    return removedPokemon;
  }

  /**
   * The trainer represented as a string including: name, hp, maxHp, money,
   * potions, pokeballs, list of pokemon, and map with the current state
   * 
   * @return Trainer class as a string
   */
  @Override
  public String toString() {
    return "---------Trainer Info----------\n" +getName() + " HP: " + getHp() + "/" + getMaxHp() + "\nMoney: " + money + "\nPotions: " + potions
        + "\nPoke Balls: " + pokeballs +"\n-------------Pokemons-----------\n" + getPokemonList() + "--------------------------------" +"\nMap:\n"
        + Map.getInstance().mapToString(loc);
  }
}