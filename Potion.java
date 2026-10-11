
public class Potion extends Item {
    private int healAmount;

    public Potion() {
        super("Health Potion", 10);
        this.healAmount = 30;
    }

    public Potion(String name, int value, int healAmount) {
        super(name, value);
        this.healAmount = healAmount;
    }

    public int getHealAmount() { return healAmount; }
    public void setHealAmount(int healAmount) { this.healAmount = healAmount; }

    @Override
    public String describe() {
        return "Potion: " + getName() + " (heals " + healAmount + " HP)";
    }
}
