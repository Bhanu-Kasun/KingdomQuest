
public class Reward {
    private int xp;
    private int gold;
    private Item bonusItem;   // may be null

    // Overloaded constructors
    public Reward(int xp, int gold) {
        this(xp, gold, null);
    }

    public Reward(int xp, int gold, Item bonusItem) {
        this.xp = xp;
        this.gold = gold;
        this.bonusItem = bonusItem;
    }

    public int getXp() { return xp; }
    public int getGold() { return gold; }
    public Item getBonusItem() { return bonusItem; }

    @Override
    public String toString() {
        String item = (bonusItem != null) ? ", " + bonusItem.getName() : "";
        return xp + " XP, " + gold + " gold" + item;
    }
}
