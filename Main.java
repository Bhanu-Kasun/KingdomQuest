import java.util.Scanner;


public class Main {
    private static GameCharacter[] roster = new GameCharacter[3];
    private static int rosterCount = 0;
    private static Player player = new Player("Player One");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        setupStartingGear();

        System.out.println("==============================");
        System.out.println("   KINGDOM QUEST ADVENTURE");
        System.out.println("==============================");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Create character   2. View roster   3. Select active character");
            System.out.println("4. Inventory          5. View quests   6. Battle");
            System.out.println("7. Rest               8. Polymorphism demo   0. Exit");
            int choice = readInt(sc, "Choose: ");

            switch (choice) {
                case 1: createCharacter(sc); break;
                case 2: viewRoster(); break;
                case 3: selectCharacter(sc); break;
                case 4: inventoryMenu(sc); break;
                case 5: player.showQuests(); break;
                case 6: startBattle(sc); break;
                case 7: rest(); break;
                case 8: polymorphismDemo(); break;
                case 0: running = false; break;
                default: System.out.println("Invalid option. Please choose 0-8.");
            }
        }
        System.out.println("Thanks for playing Kingdom Quest! Gold collected: " + player.getGold());
        sc.close();
    }

    private static void setupStartingGear() {
        try {
            player.getInventory().addItem(new Potion(), 2);
            player.getInventory().addItem(new Weapon("Iron Sword", 50, 5));
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
        }
        player.addQuest(new Quest("Goblin Slayer", "Goblin", 2,
                new Reward(50, 20, new Potion("Greater Potion", 25, 60))));
        player.addQuest(new Quest("Orc Hunter", "Orc", 1,
                new Reward(100, 50, new Weapon("Steel Axe", 120, 10))));
    }

    // Character Management module 
    private static void createCharacter(Scanner sc) {
        if (rosterCount >= roster.length) {
            System.out.println("Roster is full (max " + roster.length + " characters).");
            return;
        }
        System.out.println("1. Warrior  2. Archer  3. Wizard");
        int type = readInt(sc, "Class: ");
        System.out.print("Character name: ");
        String name = sc.hasNextLine() ? sc.nextLine().trim() : "Hero";
        if (name.isEmpty()) {
            name = "Hero";
        }

        GameCharacter created;
        switch (type) {
            case 1: created = new Warrior(name); break;
            case 2: created = new Archer(name); break;
            case 3: created = new Wizard(name); break;
            default:
                System.out.println("Invalid class. Character not created.");
                return;
        }
        roster[rosterCount] = created;
        rosterCount++;
        if (player.getHero() == null) {
            player.setHero(created);
        }
        System.out.println("Created: " + created);
    }

    private static void viewRoster() {
        System.out.println("--- Roster ---");
        if (rosterCount == 0) {
            System.out.println("(no characters yet)");
        }
        for (int i = 0; i < rosterCount; i++) {
            String active = (roster[i] == player.getHero()) ? " <ACTIVE>" : "";
            System.out.println((i + 1) + ". " + roster[i] + active);
        }
    }

    private static void selectCharacter(Scanner sc) {
        viewRoster();
        if (rosterCount == 0) {
            return;
        }
        int pick = readInt(sc, "Select number: ");
        if (pick >= 1 && pick <= rosterCount) {
            player.setHero(roster[pick - 1]);
            System.out.println("Active character: " + player.getHero().getName());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // Inventory module
    private static void inventoryMenu(Scanner sc) {
        Inventory inv = player.getInventory();
        inv.showItems();
        if (player.getHero() == null || inv.size() == 0) {
            return;
        }
        System.out.println("1. Use/equip item  0. Back");
        if (readInt(sc, "Choose: ") != 1) {
            return;
        }
        int pick = readInt(sc, "Item number: ");
        Item item = inv.getItem(pick - 1);
        if (item == null) {
            System.out.println("Invalid item.");
        } else if (item instanceof Potion) {
            player.getHero().heal((Potion) item);
            inv.removeItem(pick - 1);
            System.out.println("Used " + item.getName() + ". HP: " + player.getHero().getHealth());
        } else if (item instanceof Weapon) {
            player.getHero().setEquippedWeapon((Weapon) item);
            System.out.println(player.getHero().getName() + " equipped " + item.getName());
        }
    }

    //Battle module
    private static void startBattle(Scanner sc) {
        if (player.getHero() == null) {
            System.out.println("Create a character first!");
            return;
        }
        System.out.println("1. Goblin  2. Orc  3. Dragon");
        int choice = readInt(sc, "Choose enemy: ");
        Enemy enemy;
        switch (choice) {
            case 1: enemy = new Goblin(); break;
            case 2: enemy = new Orc(); break;
            case 3: enemy = new Dragon(); break;
            default:
                System.out.println("Invalid enemy.");
                return;
        }
        BattleSimulator.fight(player, enemy);
    }

    private static void rest() {
        if (player.getHero() == null) {
            System.out.println("Create a character first!");
            return;
        }
        player.getHero().rest();
        System.out.println(player.getHero().getName() + " rests and is fully restored.");
    }

    // Shows polymorphism: same call, different behaviour
    private static void polymorphismDemo() {
        if (rosterCount == 0) {
            System.out.println("Create at least one character first.");
            return;
        }
        System.out.println("--- Polymorphism demo: attack() on each roster character ---");
        for (int i = 0; i < rosterCount; i++) {
            System.out.println(roster[i].getClassType() + " " + roster[i].getName()
                    + " attacks for " + roster[i].attack());
        }
        Battleable[] combatants = { roster[0], new Goblin(), new Dragon() };
        for (Battleable b : combatants) {
            System.out.println(b.getName() + " alive? " + b.isAlive());
        }
    }

    // Reads an integer safely using try-catch
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNextLine()) {
                return 0;
            }
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }
}
