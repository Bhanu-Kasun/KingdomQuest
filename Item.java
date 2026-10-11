
public abstract class Item {
    private String name;
    private int value;

    // Default constructor
    public Item() {
        this("Unknown Item", 0);
    }

    // Overloaded constructor
    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    // Every item type describes itself differently
    public abstract String describe();

    @Override
    public String toString() {
        return describe();
    }
}
