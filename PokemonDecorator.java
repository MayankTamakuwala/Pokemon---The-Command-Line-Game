public class PokemonDecorator extends Pokemon {
    private Pokemon pokemon;

    /**
    * Pokemon are construsted and extraName and extraHp are moved to Pokemon
    * 
    * @param p Passes Pokemon
    * @param extraName Passes in String for extra name for buffs or debuffs
    * @param extraHp Passes in int for extra hp for buff and debuffs
    **/
    public PokemonDecorator(Pokemon p, String extraName, int extraHp) {
        super(p.getName() + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
        pokemon = p;
    }

    /**
    * The object pokemon calls pokemon class method getAttackMenu
    * 
    * @param atkType passes attack Type of special or normal attack
    * 
    * @return the operation getAttackMenu in Pokemon class
    **/
    public String getAttackMenu(int atkType) {
        return pokemon.getAttackMenu(atkType);
    }

    /**
    * The object pokemon calls pokemon class method getNumAttackMenuItems
    *
    * @param atkType passes attack Type of special or normal attack
    * 
    * @return the operation getAttackMenuItems in Pokemon class
    **/
    public int getNumAttackMenuItems(int atkType) {
        return pokemon.getNumAttackMenuItems(atkType);
    }

    /**
    * The object pokemon calls pokemon class method getAttackString
    * 
    * @param atkType passes attack Type of special or normal attack
    * 
    * @return the operation getAttackString in Pokemon class    
    **/
    public String getAttackString(int atkType, int move) {
        return pokemon.getAttackString(atkType, move);
    }

    /**
    * The object pokemon calls pokemon class method getAttackDamage
    * 
    * @param atkType passes attack Type of special or normal attack
    * 
    * @return the operation getAttackDamage in Pokemon class
    **/
    public int getAttackDamage(int atkType, int move) {
        return pokemon.getAttackDamage(atkType, move);
    }

    /**
    * The object pokemon calls pokemon class method getAttackMultiplier
    * 
    * @param p passes Pokemon
    * @param atkType passes attack Type of special or normal attack
    * 
    * @return the operation getAttackMultiplier in Pokemon class
    **/
    public double getAttackMultipier(Pokemon p, int type) {
        return pokemon.getAttackMultipier(p, type);
    }

    public int getAttackBonus(int type) {
        return pokemon.getAttackBonus(type);
    }

    /**
    * The object pokemon calls pokemon class method getType
    * 
    * @return the operation getType in Pokemon class
    **/
    public int getType() {
        return pokemon.getType();
    }
}