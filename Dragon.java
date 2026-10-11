
public class Dragon extends Enemy {
    private int turnCounter = 0;

    public Dragon() {
        super("Dragon", 200, 16, 250, 100);
    }

    @Override
    public int attack() {
        turnCounter++;
        if (turnCounter % 3 == 0) {
            System.out.println("The Dragon breathes fire!");
            return super.attack() * 2;
        }
        return super.attack();
    }
}
