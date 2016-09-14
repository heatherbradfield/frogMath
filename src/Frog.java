import java.awt.*;

/**
 * A class that creates a Frog object, a hit box, and Tongue selector
 *
 * TODO: Randomly generate operation (+,-) in StartingPoint
 * TODO: Display selected numbers on Frog
 *
 * @author Heather Bradfield
 * @version 09/11/2016
 */
public class Frog {

    private int x, y, width, height;        //!< coordinates and size of hit box
    private String operation;               //!< operation (addition or subtraction)
    private String num1 = "";               //!< first selected fly
    private String num2 = "";               //!< second selected fly
    private Tongue t = new Tongue(0,0);     //!< Frog's Tongue object

    /**
     * Sets position and size of frog to defaults.
     *
     * TODO: Import frog image and tongue image
     */
    public Frog(String operation) {
        x = 325;
        y = 480;
        width = 120;
        height = 80;
        this.operation = operation;
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
     * TODO: Implement tongue animation.
     * TODO: Set num1 and num2.
     * TODO: Fix duplicate issue
     *
     * @param tongue the Frog's tongue
     * @param f a Fly object
     */
    private void eatFly(Tongue tongue, Fly f) {
        if (f.getFlyClick()) {
            if (num1.equals("")) {
                num1 = (int)f.getNum() + "";
            } else if (!num1.equals("") && num2.equals("")){
                num2 = (int)f.getNum() + "";
            }
        }
    }

    /**
     * If two flies have been selected, check if both fly numbers are a pair
     *
     * TODO: Implement logic to check fly pairs.
     * TODO: If flies are a pair, remove flies from environment
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
     * TODO: Import Frog image; implement tongue animation; check flyClick
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

        font = new Font("Serif",Font.BOLD,30);
        g.setFont(font);
        g.setColor(Color.MAGENTA);
        if (!num1.equals("")) {
            g.drawString(num1, this.x + (this.width / 2 - 50), this.y + (this.height / 2 + 10));
        }
//        if (!num2.equals("")) {
//            g.drawString(num2, this.x+(this.width/2 + 30),this.y+(this.height/2 + 10));
//        }
    }
}

/**
 * Frog's tongue object
 *
 * TODO: Position tongue in front of Frog and set defaults.
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
