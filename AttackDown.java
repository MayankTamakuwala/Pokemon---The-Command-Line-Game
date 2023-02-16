public class AttackDown extends PokemonDecorator {

    /**
     * Constructor of AttackDown Class calling the super class constructor
     * 
     * @param p Pokemon on which AttackDown is performed
     */
    public AttackDown(Pokemon p) {
        super(p, "-ATK", 0);
        // super(p, p.getName() + "-ATK", 0);
    }

    /**
     * Randomly generate 1 or 2 attack damage points to decrease
     * 
     * @param type The Attack Type
     * @return The total attack bonus value
     */
    @Override
    public int getAttackBonus(int type) {
        return super.getAttackBonus(type) - ((int) Math.random() * (2) + 1);
    }
}