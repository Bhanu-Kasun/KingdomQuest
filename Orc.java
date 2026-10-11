
public class Orc extends Enemy {

    public Orc() {
        super("Orc", 90, 12, 100, 30);
    }

    @Override
    public int attack() {
        return (int) (super.attack() * 1.5);
    }
}
