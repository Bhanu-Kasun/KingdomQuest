
public class Weapon extends Item {
    private int damageBonus;

    public Weapon() {
        super();
        this.damageBonus = 0;
    }

    public Weapon(String name, int value, int damageBonus) {
        super(name, value);
        this.damageBonus = damageBonus;
    }

    public int getDamageBonus() { return damageBonus; }
    public void setDamageBonus(int damageBonus) { this.damageBonus = damageBonus; }

    @Override
    public String describe() {
        return "Weapon: " + getName() + " (+" + damageBonus + " damage)";
    }
}
