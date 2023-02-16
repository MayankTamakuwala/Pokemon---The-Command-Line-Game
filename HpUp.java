public class HpUp extends PokemonDecorator {
    
    /**
     * HpUp will increase hp for pokemon
     */
    public HpUp(Pokemon p) {
        super(p, "+HP", (int) (Math.random() * 2 + 1));
    }
}