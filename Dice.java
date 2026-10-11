import java.util.Random;

public class Dice {
    private static Random random = new Random();

    public static void setSeed(long seed) {
        random = new Random(seed);
    }

    // Returns a number from 1 to max (inclusive)
    public static int roll(int max) {
        return random.nextInt(max) + 1;
    }
}
