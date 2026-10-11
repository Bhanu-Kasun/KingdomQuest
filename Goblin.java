
public class Goblin extends Enemy {

    public Goblin() {
        super("Goblin", 50, 8, 60, 15);
    }

    @Override
    public int attack() {
        int damage = super.attack();
        if (Dice.roll(10) > 8) {
            System.out.println("The Goblin backstabs!");
            damage += 5;
        }
        return damage;
    }
}
