public class HpDown extends PokemonDecorator {
    
    /**
     * HpDown will decrease hp from pokemon
     */
    public HpDown(Pokemon p) {
        super(p, "-HP", ((int) (Math.random() * 2 + 1) * (-1)));
    }
}