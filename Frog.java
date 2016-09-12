import java.awt.*;

/**
 * A class that creates a Frog, a hit box, and Tongue selector
 *
 * @author Heather Bradfield
 * @version 09/11/2016
 */
public class Frog {

    private int x, y, width, height;
    private String operation = "+";
    private boolean num1 = false;
    private boolean num2 = false;
    private Tongue t = new Tongue(0,0);

    /**
     * Default Constructor:
     * Sets position and size of frog to defaults.
     *
     * TODO: import frog image and tongue image
     */
    public Frog() {
        x = 325;
        y = 480;
        width = 120;
        height = 80;
    }

    /**
     * Checks for collisions between Frog and Flies.
     * Moves flies away from Frog.
     *
     * TODO: Sums two Fly numbers selected by user.
     *
     * @param sp the current level and game environment
     * @param f a Fly object
     */
    public void update(StartingPoint sp, Fly f) {
        checkForCollision(f);
        eatFly(t,f);
        sum(f);
    }

    /**
     * Checks for collisions between the Frog and a Fly.
     * If collided, moves Fly away from Frog.
     *
     * TODO: Fix hit box dimensions.
     *
     * @param f a Fly object
     */
    private void checkForCollision(Fly f) {
        double flyX = f.getX();
        double flyY = f.getY();
        double radius = f.getRadius();

        if (flyY + radius > this.y && flyY + radius < this.y + this.height) {
            if (flyX > this.x && flyX < this.x + this.width) {
                f.setY(this.y - radius);
                f.setDy(-1 * f.getDy());
            }
        }
    }

    /**
     * If fly is clicked, shoot tongue out at fly.
     *
     * TODO: implement tongue animation.
     *
     * @param tongue the Frog's tongue
     * @param f a Fly object
     */
    private void eatFly(Tongue tongue, Fly f) {

    }

    /**
     * If two flies have been selected, check if both fly numbers are a pair
     *
     * TODO: implement logic to check fly pairs.
     *
     * @param f a Fly object
     */
    private void sum (Fly f) {
        if (f.getFlyClick()) {

        }
    }

    /**
     * Paints Frog Image, Frog Tongue, hitboxes, and fly number if fly selected
     *
     * TODO: import Frog image; implement tongue animation; check flyClick
     *
     * @param g the Frog's graphics
     */
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(this.x,this.y,this.width,this.height);
        Font font = new Font("Serif",Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.DARK_GRAY);
        g.drawString(operation,this.x+(this.width/2 - 15),this.y+(this.height/2 + 10));
    }
}

/**
 * Frog's tongue object
 *
 * TODO: position tongue in front of Frog
 */
class Tongue {
    private int x, y, dx, dy;
    private int width, height;

    Tongue (int x, int y) {
        this.x = x;
        this.y = y;
        dx = 1;
        dy = 1;
        width = 5;
        height = 10;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}
