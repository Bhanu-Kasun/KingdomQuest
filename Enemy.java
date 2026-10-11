
public class Enemy implements Battleable {
    private String name;
    private int health;
    private int attackPower;
    private int xpReward;
    private int goldReward;

    public Enemy() {
        this("Wild Creature", 30, 5, 20, 5);
    }

    public Enemy(String name, int health, int attackPower, int xpReward, int goldReward) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.xpReward = xpReward;
        this.goldReward = goldReward;
    }

    public int attack() {
        return attackPower + Dice.roll(4);
    }

    @Override
    public boolean isAlive() { return health > 0; }

    @Override
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    @Override
    public String getName() { return name; }
    @Override
    public int getHealth() { return health; }
    public int getAttackPower() { return attackPower; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }
    public int getXpReward() { return xpReward; }
    public int getGoldReward() { return goldReward; }

    @Override
    public String toString() {
        return name + " (HP " + health + ", ATK " + attackPower + ")";
    }
}
