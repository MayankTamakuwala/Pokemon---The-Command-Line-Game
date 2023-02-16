import java.util.*;
import java.io.*;

public class PokemonGenerator {
    private HashMap<String, String> pokemon = new HashMap<String, String>();
    private static PokemonGenerator instance = null;

    /**
     * Constructor of PokemonGenerator Class which initializes the HashMap pokemon
     * by inserting the keys and the respective values to the "pokemon" variable
     * from PokemonList.txt
     */
    private PokemonGenerator() {
        try {
            Scanner read = new Scanner(new File("PokemonList.txt"));
            while (read.hasNext()) {
                String line = read.nextLine();
                int delimeterIndex = line.indexOf(",");
                pokemon.put(line.substring(0, delimeterIndex), line.substring(delimeterIndex + 1));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File not Found");
        }
    }

    /**
     * Makes the instance of the class if it is null
     * 
     * @return The instance of PokemonGenerator
     */
    public static PokemonGenerator getInstance() {
        if (instance == null) {
            instance = new PokemonGenerator();
        }
        return instance;
    }

    /**
     * Generates the random pokemon from the PokemonList.txt by calling
     * getPokemon(name)
     * 
     * @param level Level of Pokemon/Player
     * @return The Pokemon which was randomly generated and buffed
     */
    public Pokemon generateRandomPokemon(int level) {
        int randomPoke = (int) (Math.random() * pokemon.size());
        // String[] keys = (String[]) (pokemon.keySet().toArray());
        Object[] keys = pokemon.keySet().toArray();
        Pokemon poke = getPokemon((String) keys[randomPoke]);
        for (int i = 1; i < level; i++) {
            poke = addRandomBuff(poke);
        }
        return poke;
    }

    /**
     * Constructs the particular elemental pokemon by checking the value of the
     * Hashmap Pokemon
     * 
     * @param name Name of the Pokemon
     * @return The elemental class generated Pokemon
     */
    public Pokemon getPokemon(String name) {
        Pokemon poke;
        if (pokemon.get(name).equals("Fire")) {
            int randomHp = (int) (Math.random() * (30 - 20 + 1) + 20);
            poke = new Fire(name, randomHp, randomHp);
        } else if (pokemon.get(name).equals("Water")) {
            int randomHp = (int) (Math.random() * (30 - 20 + 1) + 20);
            poke = new Water(name, randomHp, randomHp);
        } else {
            int randomHp = (int) (Math.random() * (30 - 20 + 1) + 20);
            poke = new Grass(name, randomHp, randomHp);
        }
        return poke;
    }

    /**
     * Generates the random buff which includes HP increase or Attack Damage
     * increase
     * 
     * @param p The pokemon on which we are doing random buff
     * @return The Buffed Pokemon object
     */
    public Pokemon addRandomBuff(Pokemon p) {
        int randomBuffSelector = (int) (Math.random() * (1 - 0 + 1) + 0);
        if (randomBuffSelector == 0) {
            return new AttackUp(p);
        } else {
            return new HpUp(p);
        }
    }

    /**
     * Generates the random buff which includes HP increase or Attack Damage
     * increase
     * 
     * @param p The pokemon on which we are doing random debuff
     * @return The Debuffed Pokemon object
     */
    public Pokemon addRandomDebuff(Pokemon p) {
        int randomBuffSelector = (int) (Math.random() * (1 - 0 + 1) + 0);
        if (randomBuffSelector == 0) {
            return new AttackDown(p);
        } else {
            return new HpDown(p);
        }
    }
}