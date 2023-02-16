public class Water extends Pokemon {
    public Water(String n, int h, int m) {
        super(n, h, m);
    }

    /**
    * If user chooses special attack then return special attack menu
    * 
    * @param atkType Passes attack Type of special or normal attack
    * 
    * @return The string for attack options for special attacks
    **/
    @Override
    public String getAttackMenu(int atkType) {
        if (atkType == 2) {
            return ("Choose Attack:\n1. Water Gun\n2. Bubble Beam\n3. Waterfall");
        } else {
            return super.getAttackMenu(1);
        }
    }

    /**
    * Returns the number of special attacks if chosen to be a special attack
    * 
    * @param atkType Passes attack Type of special or normal attack
    * 
    * @return The number for of special attacks
    **/
    @Override
    public int getNumAttackMenuItems(int atkType) {
        if (atkType == 2) {
            return 3;
        } else {
            return super.getNumAttackMenuItems(1);
        }
    }
    
    /**
    * If user chooses special attack then the string for the elemental attacks are returned
    * 
    * @param atkType Passes attack Type of special or normal attack
    * @param move Passes attack Move of the special attack if chosen
    * 
    * @return The string for special attacks
    **/
    @Override
    public String getAttackString(int atkType, int move) {
        if (atkType == 2) {
            if (move == 1) {
                return "WATER GUNED";
            } else if (move == 2) {
                return "BUBBLE BEAMED";
            } else {
                return "encased in WATERFALL";
            }
        } else {
            return super.getAttackString(1, move);
        }
    }

    /**
    * If user chooses special attack then the damage of elemental attacks is returned
    * 
    * @param atkType Passes attack Type of special or normal attack
    * @param move Passes attack Move of the special attack if chosen
    * 
    * @return The is the amount of damage for a special attack
    **/
    @Override
    public int getAttackDamage(int atkType, int move) {
        if (atkType == 2) {
            if (move == 1) {
                int maxDamage = 5;
                int minDamage = 2;
                int waterGunDamage = (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
                return waterGunDamage;
            } else if (move == 2) {
                int maxDamage = 3;
                int minDamage = 1;
                int bubbleBeamDamage = (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
                return bubbleBeamDamage;
            } else {
                int maxDamage = 4;
                int minDamage = 1;
                int waterfallDamage = (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
                return waterfallDamage;
            }
        } else {
            return super.getAttackDamage(1, move);
        }
    }

    /**
    * If user chooses special attack then the double for the multiplier of elemental attacks is returned
    * 
    * @param p passes Pokemon
    * @param atkType passes attack Type of special or normal attack
    * 
    * @return the double which is the multiplier for special attacks
    **/
    @Override
    public double getAttackMultipier(Pokemon p, int atkType) {
        if (atkType == 2) {
            return battleTable[this.getType()][p.getType()];
        } else {
            return super.getAttackMultipier(p, atkType);
        }
    }
}