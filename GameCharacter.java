
public abstract class GameCharacter implements Battleable {
    private String name;
    private int health;
    private int maxHealth;
    private int level;
    private int xp;
    private int attackPower;
    private Weapon equippedWeapon;

    // Default constructor
    public GameCharacter() {
        this("Unnamed Hero", 100, 10);
    }

    // Overloaded constructor
    public GameCharacter(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.level = 1;
        this.xp = 0;
    }

    // abstract methods: each character type must implement these
    public abstract int attack();
    public abstract String getClassType();

    // Battleable interface
    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    //overloaded heal methods
    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
    }

    public void heal(Potion potion) {
        heal(potion.getHealAmount());
    }

    //  experience and levelling 
    public int xpToNextLevel() {
        return level * 100;
    }

    public void gainXP(int amount) {
        xp += amount;
        System.out.println(name + " gained " + amount + " XP.");
        while (xp >= xpToNextLevel()) {
            xp -= xpToNextLevel();
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        maxHealth += 20;
        attackPower += 3;
        health = maxHealth;
        System.out.println("*** LEVEL UP! " + name + " is now level " + level + " ***");
    }

    // Restores full health (subclasses may extend this)
    public void rest() {
        health = maxHealth;
    }

    protected int weaponBonus() {
        return (equippedWeapon != null) ? equippedWeapon.getDamageBonus() : 0;
    }

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getHealth() { return health; }
    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else if (health > maxHealth) {
            this.health = maxHealth;
        } else {
            this.health = health;
        }
    }
    public int getMaxHealth() { return maxHealth; }
    public int getLevel() { return level; }
    public int getXp() { return xp; }
    public int getAttackPower() { return attackPower; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }
    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public void setEquippedWeapon(Weapon weapon) { this.equippedWeapon = weapon; }

    @Override
    public String toString() {
        String weapon = (equippedWeapon != null) ? equippedWeapon.getName() : "None";
        return "[" + getClassType() + "] " + name + " | HP " + health + "/" + maxHealth
                + " | Lv " + level + " | XP " + xp + "/" + xpToNextLevel()
                + " | ATK " + attackPower + " | Weapon: " + weapon;
    }
}
