
public class Archer extends GameCharacter {

    public Archer() {
        super("Unnamed Archer", 90, 14);
    }

    public Archer(String name) {
        super(name, 90, 14);
    }

    @Override
    public int attack() {
        int damage = getAttackPower() + weaponBonus() + Dice.roll(6);
        boolean critical = Dice.roll(100) <= 25;
        if (critical) {
            System.out.println(getName() + " lands a critical shot!");
        }
        return critical ? damage * 2 : damage;
    }

    @Override
    public String getClassType() {
        return "Archer";
    }
}
