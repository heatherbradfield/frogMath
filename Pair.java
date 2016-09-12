/**
 * A class that creates a Pair of two Flies
 *
 * @author Heather Bradfield
 * @version 09/11/2016
 */
public class Pair {
    private double x;
    private double y;
    private Fly f1;
    private Fly f2;

    Pair (Fly one, Fly two) {
        this.f1 = one;
        this.f2 = two;
        this.x = f1.getNum();
        this.y = f2.getNum();
    }

    public Fly getF1() {
        return f1;
    }

    public Fly getF2() {
        return f2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
