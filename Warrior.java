
public class Warrior extends GameCharacter {

    public Warrior() {
        super("Unnamed Warrior", 120, 12);
    }

    public Warrior(String name) {
        super(name, 120, 12);
    }

    @Override
    public int attack() {
        int damage = getAttackPower() + getLevel() * 2 + weaponBonus() + Dice.roll(4);
        if (getHealth() < getMaxHealth() / 2) {
            System.out.println(getName() + " is enraged! Bonus damage!");
            damage += 5;
        }
        return damage;
    }

    @Override
    public String getClassType() {
        return "Warrior";
    }
}
