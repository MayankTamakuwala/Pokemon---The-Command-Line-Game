abstract class Pokemon extends Entity {
    static final double[][] battleTable = { { 1, 0.5, 2 }, { 2, 1, 0.5 }, { 0.5, 2, 1 } };

    /**
     * Constructor of the Pokemon class, calling the the super class constructor
     * where the first parameter is name of the pokemon and another parameter is HP
     * of the Pokemon which will be generated randomly between 20 and 30.
     * 
     * @param n name of the pokemon
     */
    public Pokemon(String n, int h, int m) {
        super(n, h, m);
    }

    /**
     * returns the attack selection options
     * 
     * @return The Attack type menu
     */
    public String getAttackTypeMenu() {
        return ("Choose attack type:\n1. Basic Attack\n2. Special Attack");
    }

    /**
     * Function of the number of type of attack
     * 
     * @return Number of types of attack
     */
    public int getNumAttackTypeMenuItems() {
        return 2;
    }

    /**
     * returns the attack menu based on attack type
     * 
     * @param atkType The Attack Type
     * @return The menu string
     */
    public String getAttackMenu(int atkType) {
        return ("Choose Attack:\n1. Slam\n2. Tackle\n3. Punch");
    }

    /**
     * returns the number of moves in the above Attack menu
     * 
     * @param atkType The Attack Type
     * @return The number of moves in the above Attack menu
     */
    public int getNumAttackMenuItems(int atkType) {
        return 3;
    }

    /**
     * This function calculated the total damage and creates the interactive string
     * 
     * @param p       Pokemon on which attack is done
     * @param atkType The attack type
     * @param move    The move which we used to attack
     * @return The interactive string which includes defendant name, attacker name,
     *         damage dealt and move used
     */
    public String attack(Pokemon p, int atkType, int move) {
        int totaldamage;
        totaldamage = (int) (this.getAttackDamage(atkType, move) * this.getAttackMultipier(p, atkType)
                + this.getAttackBonus(atkType));
        if (totaldamage <= 0) {
            totaldamage = 0;
        }
        p.takeDamage(totaldamage);
        return (p.getName() + " is " + this.getAttackString(atkType, move) + " by " + this.getName() + " and takes "
                + totaldamage + " damage.");
    }

    /**
     * Selects the partial attack string
     * 
     * @param atkType The attack type
     * @param move    The move which we used to attack
     * @return The partial attack string
     */
    public String getAttackString(int atkType, int move) {
        if (move == 1) {
            return "SLAMMED";
        } else if (move == 2) {
            return "TACKLED";
        } else {
            return "PUNCHED";
        }
    }

    /**
     * Calculated the damage of the attack
     * 
     * @param atkType The attack type
     * @param move    The move which we used to attack
     * @return The damage before calculating the totalDamage
     */
    public int getAttackDamage(int atkType, int move) {
        if (move == 1) {
            int maxDamage = 5;
            int minDamage = 0;
            int slamDamage = (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
            return slamDamage;
        } else if (move == 2) {
            int maxDamage = 3;
            int minDamage = 2;
            int tackleDamage = (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
            return tackleDamage;
        } else {
            int maxDamage = 4;
            int minDamage = 1;
            int punchDamage = (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
            return punchDamage;
        }
    }

    /**
     * The multipier is used to calculate the effect of the move on the pokemon
     * 
     * @param p       The pokemon object
     * @param atkType The attack type
     * @return The multiplying value
     */
    public double getAttackMultipier(Pokemon p, int atkType) {
        return 1;
    }

    /**
     * The bonus is used to add the value into buffs and debuffs
     * 
     * @param atkType The attack type
     * @return The bonus value
     */
    public int getAttackBonus(int atkType) {
        return 0;
    }

    /**
     * Check the instance of the pokemon and returns "0" if it is a Fire type, "1"
     * if water, and "2" if Grass type
     * 
     * @return the integer value according to their type
     */
    public int getType() {
        if (this instanceof Fire) {
            return 0;
        } else if (this instanceof Water) {
            return 1;
        } else {
            return 2;
        }
    }
}