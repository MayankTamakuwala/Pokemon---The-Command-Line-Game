abstract class Entity {
    private String name;
    private int hp;
    private int maxHp;

    /**
     * Initializes name to the name that passes in and hp, maxHp to the number
     * passed in with mHp
     * 
     * @param n passes in string name
     * @param h passes in int HP
     * @param m passes in int MaxHP
     **/
    public Entity(String n, int h, int m) {
        name = n;
        hp = h;
        maxHp = m;
    }

    /**
     * Returns hp value
     *
     * @return The HP of the Entity
     **/
    public int getHp() {
        return hp;
    }

    /**
     * Returns maxHp value
     *
     * @return The Max HP of the Entity
     **/
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Returns name string
     *
     * @return The name of the Entity
     **/
    public String getName() {
        return name;
    }

    /**
     * Allows for damage to subtract from hp
     *
     * @param d Damage taken
     **/
    public void takeDamage(int d) {
        hp = hp - d;
        if (hp < 0) {
            hp = 0;
        }
    }

    /**
     * Fills hp to max hp
     **/
    public void heal() {
        hp = maxHp;
    }

    /**
     * Returns String to print out
     *
     * @return The string in a "<name> : <hp>/<maxHp>" manor
     **/
    @Override
    public String toString() {
        return (name + " HP: " + hp + "/" + maxHp);
    }
}