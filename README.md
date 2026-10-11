# Kingdom Quest Adventure Game

A console-based fantasy RPG written in **Java**, built to demonstrate core **Object-Oriented Programming** concepts.
Create a hero, equip weapons, take on quests, battle monsters, and level up.

> Module: **H7DY – Object Oriented Programming Concepts I (EX02)** · Java Institute for Advanced Technology
> Author: **Bhanu Kasun Amarasinghe**

---

## Features

- **3 playable classes** – Warrior, Archer, Wizard (each with a unique attack style)
- **3 enemy types** – Goblin, Orc, Dragon (each with special behaviour)
- **Turn-based battle simulator** with dice-based damage
- **Inventory system** (capacity 5) with potions and weapons
- **Quest system** – kill quests that pay out XP, gold and bonus items
- **XP and level progression** – HP and attack grow on every level up
- **Roster** of up to 3 characters, switch active hero any time
- **Polymorphism demo** option in the menu

## Character Classes

| Class   | HP  | Base ATK | Special ability |
|---------|-----|----------|-----------------|
| Warrior | 120 | 12       | Enraged: +5 damage when HP is below 50% |
| Archer  | 90  | 14       | 25% chance of a critical shot (double damage) |
| Wizard  | 80  | 10       | Fireball costs 10 mana (2x power); weak staff strike when out of mana |

## Enemies

| Enemy  | HP  | ATK | XP  | Gold | Special |
|--------|-----|-----|-----|------|---------|
| Goblin | 50  | 8   | 60  | 15   | Occasional backstab (+5 damage) |
| Orc    | 90  | 12  | 100 | 30   | Attacks deal 1.5x damage |
| Dragon | 200 | 16  | 250 | 100  | Fire breath every 3rd turn (2x damage) |

## OOP Concepts Used

| Concept | Where |
|---------|-------|
| **Abstraction** | `GameCharacter` and `Item` are abstract classes |
| **Interface** | `Battleable` (implemented by `GameCharacter` and `Enemy`) |
| **Inheritance** | `GameCharacter` → `Warrior` / `Archer` / `Wizard`; `Item` → `Weapon` / `Potion`; `Enemy` → `Goblin` / `Orc` / `Dragon` |
| **Encapsulation** | Private fields with getters/setters (e.g. validated `setHealth`) |
| **Polymorphism / Overriding** | `attack()`, `describe()`, `rest()`, `toString()` |
| **Overloading** | `heal(int)` / `heal(Potion)`, `addItem(Item)` / `addItem(Item, int)`, multiple constructors |
| **Composition (HAS-A)** | `Player` has a `GameCharacter`, an `Inventory` and `Quest[]`; `Quest` has a `Reward` |
| **Exception handling** | Custom `InventoryFullException`, `try-catch` for input parsing |
| **Arrays and loops** | Roster, inventory and quest arrays with `for` / `while` loops |

## Class Overview

```
Battleable (interface)
├── GameCharacter (abstract)
│   ├── Warrior
│   ├── Archer
│   └── Wizard
└── Enemy
    ├── Goblin
    ├── Orc
    └── Dragon

Item (abstract)
├── Weapon
└── Potion

Player ── has ──> GameCharacter, Inventory, Quest[]
Quest  ── has ──> Reward ── has ──> Item (bonus)
Others: BattleSimulator, Dice, InventoryFullException, Main
```

## Project Structure

```
KingdomQuest-Adventure-Game/
├── src/
│   ├── Main.java                    # Menu + game loop
│   ├── BattleSimulator.java         # Turn-based battle logic
│   ├── Battleable.java              # Interface
│   ├── GameCharacter.java           # Abstract base character
│   ├── Warrior.java  Archer.java  Wizard.java
│   ├── Enemy.java  Goblin.java  Orc.java  Dragon.java
│   ├── Item.java  Weapon.java  Potion.java
│   ├── Inventory.java  InventoryFullException.java
│   ├── Player.java  Quest.java  Reward.java
│   └── Dice.java
├── nbproject/                       # NetBeans project config
├── build.xml
├── LICENSE
└── README.md
```

## Getting Started

**Requirements:** JDK 8 or newer.

### Run from the command line

```bash
git clone https://github.com/<your-username>/KingdomQuest-Adventure-Game.git
cd KingdomQuest-Adventure-Game

mkdir out
javac -d out src/*.java
java -cp out Main
```

### Run in NetBeans

1. `File → Open Project` and select this folder
2. Press **F6** to run

## How to Play

```
1. Create character   2. View roster   3. Select active character
4. Inventory          5. View quests   6. Battle
7. Rest               8. Polymorphism demo   0. Exit
```

1. Choose **1** and create a Warrior, Archer or Wizard
2. Check **5** to see the available quests (Goblin Slayer, Orc Hunter)
3. Choose **6** to battle; use potions from **4** and rest with **7** to recover
4. Complete quests to earn XP, gold and bonus items

## License

Released under the [MIT License](LICENSE).

