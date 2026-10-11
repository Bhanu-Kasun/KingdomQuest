
public class Wizard extends GameCharacter {
    private static final int MAX_MANA = 30;
    private int mana;

    public Wizard() {
        super("Unnamed Wizard", 80, 10);
        this.mana = MAX_MANA;
    }

    public Wizard(String name) {
        super(name, 80, 10);
        this.mana = MAX_MANA;
    }

    @Override
    public int attack() {
        if (mana >= 10) {
            mana -= 10;
            System.out.println(getName() + " casts a fireball! (Mana left: " + mana + ")");
            return getAttackPower() * 2 + weaponBonus() + Dice.roll(5);
        } else {
            System.out.println(getName() + " is out of mana and uses a weak staff strike.");
            return getAttackPower() / 2 + Dice.roll(3);
        }
    }

    @Override
    public String getClassType() {
        return "Wizard";
    }

    // Overrides rest() to also restore mana
    @Override
    public void rest() {
        super.rest();
        mana = MAX_MANA;
    }

    public int getMana() { return mana; }

    @Override
    public String toString() {
        return super.toString() + " | Mana " + mana + "/" + MAX_MANA;
    }
}
