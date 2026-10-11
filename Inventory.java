
public class Inventory {
    private static final int CAPACITY = 5;
    private Item[] items = new Item[CAPACITY];
    private int count = 0;

    public boolean isFull() {
        return count == CAPACITY;
    }

    // Adds one item
    public void addItem(Item item) throws InventoryFullException {
        if (isFull()) {
            throw new InventoryFullException("Inventory is full! Cannot add " + item.getName());
        }
        items[count] = item;
        count++;
    }

    // Overloaded: adds the same item several times
    public void addItem(Item item, int quantity) throws InventoryFullException {
        for (int i = 0; i < quantity; i++) {
            addItem(item);
        }
    }

    // Removes and returns the item at an index (or null if invalid)
    public Item removeItem(int index) {
        if (index < 0 || index >= count) {
            return null;
        }
        Item removed = items[index];
        for (int i = index; i < count - 1; i++) {
            items[i] = items[i + 1];
        }
        items[count - 1] = null;
        count--;
        return removed;
    }

    public Item getItem(int index) {
        return (index >= 0 && index < count) ? items[index] : null;
    }

    // Index of the first potion, or -1 if none
    public int findPotion() {
        for (int i = 0; i < count; i++) {
            if (items[i] instanceof Potion) {
                return i;
            }
        }
        return -1;
    }

    public int size() { return count; }

    public void showItems() {
        System.out.println("--- Inventory (" + count + "/" + CAPACITY + ") ---");
        if (count == 0) {
            System.out.println("(empty)");
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + items[i].describe());
        }
    }
}
