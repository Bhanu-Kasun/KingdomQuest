
public class BattleSimulator {
    private static final int MAX_ROUNDS = 50;

    // Returns true if the player wins
    public static boolean fight(Player player, Enemy enemy) {
        GameCharacter hero = player.getHero();
        System.out.println("\n=== BATTLE: " + hero.getName() + " vs " + enemy.getName() + " ===");
        int round = 1;

        while (hero.isAlive() && enemy.isAlive() && round <= MAX_ROUNDS) {
            System.out.println("-- Round " + round + " --");

            // Drink a potion automatically when health is below 30%
            if (hero.getHealth() < hero.getMaxHealth() * 0.3) {
                int index = player.getInventory().findPotion();
                if (index != -1) {
                    Potion potion = (Potion) player.getInventory().removeItem(index);
                    hero.heal(potion);
                    System.out.println(hero.getName() + " drinks " + potion.getName()
                            + " and now has " + hero.getHealth() + " HP.");
                }
            }

            int heroDamage = hero.attack();
            enemy.takeDamage(heroDamage);
            System.out.println(hero.getName() + " hits " + enemy.getName() + " for "
                    + heroDamage + " (" + enemy.getName() + " HP: " + enemy.getHealth() + ")");

            if (enemy.isAlive()) {
                int enemyDamage = enemy.attack();
                hero.takeDamage(enemyDamage);
                System.out.println(enemy.getName() + " hits " + hero.getName() + " for "
                        + enemyDamage + " (" + hero.getName() + " HP: " + hero.getHealth() + ")");
            }
            round++;
        }

        if (hero.isAlive() && !enemy.isAlive()) {
            System.out.println("VICTORY! " + enemy.getName() + " has been defeated.");
            hero.gainXP(enemy.getXpReward());
            player.addGold(enemy.getGoldReward());
            System.out.println("Looted " + enemy.getGoldReward() + " gold.");
            player.updateQuests(enemy.getName());
            return true;
        } else if (!hero.isAlive()) {
            System.out.println("DEFEAT! " + hero.getName() + " has fallen... but is revived at half health.");
            hero.setHealth(hero.getMaxHealth() / 2);
            return false;
        } else {
            System.out.println("The battle ends in a stalemate.");
            return false;
        }
    }
}
