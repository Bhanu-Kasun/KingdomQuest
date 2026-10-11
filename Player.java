
public class Player {
    private String playerName;
    private GameCharacter hero;
    private Inventory inventory;
    private Quest[] quests = new Quest[5];
    private int questCount = 0;
    private int gold = 0;

    public Player(String playerName) {
        this.playerName = playerName;
        this.inventory = new Inventory();
    }

    public void addQuest(Quest quest) {
        if (questCount < quests.length) {
            quests[questCount++] = quest;
        }
    }

    // Called after every victory: updates every quest and pays rewards
    public void updateQuests(String defeatedEnemy) {
        for (int i = 0; i < questCount; i++) {
            if (quests[i].recordKill(defeatedEnemy)) {
                Reward r = quests[i].getReward();
                System.out.println(">>> QUEST COMPLETE: " + quests[i].getTitle() + " <<<");
                hero.gainXP(r.getXp());
                gold += r.getGold();
                if (r.getBonusItem() != null) {
                    try {
                        inventory.addItem(r.getBonusItem());
                        System.out.println("Received item: " + r.getBonusItem().getName());
                    } catch (InventoryFullException e) {
                        System.out.println(e.getMessage() + " (reward item lost)");
                    }
                }
            }
        }
    }

    public void showQuests() {
        System.out.println("--- Quests ---");
        if (questCount == 0) {
            System.out.println("(none)");
        }
        for (int i = 0; i < questCount; i++) {
            System.out.println((i + 1) + ". " + quests[i]);
        }
    }

    public String getPlayerName() { return playerName; }
    public GameCharacter getHero() { return hero; }
    public void setHero(GameCharacter hero) { this.hero = hero; }
    public Inventory getInventory() { return inventory; }
    public int getGold() { return gold; }
    public void addGold(int amount) { gold += amount; }
}
