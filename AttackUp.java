public class AttackUp extends PokemonDecorator {

    /**
     * Constructor of AttackUp Class calling the super class constructor
     * 
     * @param p Pokemon on which AttackUp is performed
     */
    public AttackUp(Pokemon p) {
        super(p, "+ATK", 0);
    }

    /**
     * Randomly generate 1 or 2 attack damage points to increase
     * 
     * @param type The Attack Type
     * @return The total attack bonus value
     */
    @Override
    public int getAttackBonus(int type) {
        return super.getAttackBonus(type) + ((int) Math.random() * (2) + 1);
    }
}