import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * A class that creates a Fly object and a hit box
 *
 * @author Heather Bradfield
 * @version 09/11/2016
 */
public class Fly {

    private double x = 400;             //!< x-coordinate of hit box
    private double y = 25;              //!< y-coordinate of hit box
    private double dx = .5;             //!< change in x of hit box for movement
    private double dy = .5;             //!< change in y of hit box for movement
    private double radius = 30;         //!< radius in pixels for hit box
    private int num = 0;                //!< num represented on Fly
    private double numX = 0;            //!< num graphic x-coordinate
    private double numY = 0;            //!< num graphic y-coordinate
    private boolean flyClick = false;   //!< true if fly was selected by mouse

    /**
     * Sets position, and change in position of fly to defaults.
     *
     * TODO: Import fly Image.
     * TODO: Fix random position generation to be more random.
     * TODO: Maybe slow down flies.
     *
     * @param num generated num chosen in StartingPoint
     */
    public Fly(int num) {
        Random r = new Random();
        this.x = r.nextInt(400) + 20 * 5;
        this.numX = this.x + 2;
        r = new Random();
        this.y = r.nextInt(400) + 20;
        this.numY = this.y + 2;
        if (r.nextBoolean()) {
            this.dx = -this.dx;
            this.numX = -this.numX;
        }
        this.num = num;
    } // end default constructor:

    /**
     * @return x Fly's current x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * @return y Fly's current y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets Fly's y position to new y coordinate.
     *
     * @param y a new Fly y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return dx Fly's current change in x.
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return dy Fly's current change in y.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets Fly's change in y new dy.
     *
     * @param dy a new Fly y coordinate
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * @return radius Fly's radius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets Fly's number.
     *
     * @param num a new Fly number
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return this.num Fly's number.
     */
    public double getNum() {
        return this.num;
    }

    /**
     * If fly was clicked by mouse, flyClick = true.
     *
     * @param b boolean flag for fly selection
     */
    public void setFlyClick(Boolean b) {
        this.flyClick = b;
    }

    /**
     * @return this.num Fly's number.
     */
    public boolean getFlyClick()
    {
        return this.flyClick;
    }

    /**
     * Changes Fly's position if wall collision detected.
     *
     * @param sp game environment
     * @param flies flies to update
     */
    public void update(StartingPoint sp, ArrayList<Fly> flies) {
        if ((x + dx) > sp.getWidth() - radius - 1) {
            x = sp.getWidth() - radius - 1;
            dx = -dx;
        }
        else if ((x + dx) < radius) {
            x = radius;
            dx = -dx;
        }
        else {
            x += dx;
        }

        if ((y + dy) > sp.getHeight() - radius - 1) {
            y = sp.getHeight() - radius - 1;
            dy = -dy;
        }
        else if ((y + dy) < radius) {
            y = radius;
            dy = -dy;
        }
        else {
            y += dy;
        }

        numX = x;
        numY = y;

        checkForCollision(flies);
    }

    /**
     * Checks if this fly has collided with other fly
     *
     * TODO: Implement collision checks
     * TODO: If collided, use pythagorean thm to change direction
     *
     * @param flies other flies to check collision with
     */
    private void checkForCollision(ArrayList<Fly> flies) {
        for (Fly fly : flies) {
            if (this != fly) {
                if (this.x <= fly.x + fly.radius && this.x >= fly.x - fly.radius
                        && this.y <= fly.y + fly.radius && this.y <= fly.y - fly.radius) {
//                    this.dx = -this.dx;
//                    this.dy = -this.dy;
//                    fly.dx = -fly.dx;
                }
            }
        }
    }

    /**
     * Paints Fly Image, hit box, and number
     *
     * TODO: Paint Frog image; paint "selection sphere"
     *
     * @param g the Frog's graphics
     */
    public void paint(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval((int)(x-radius),(int)(y-radius),(int)radius*2,(int)radius*2);
        String numString = Integer.toString(this.num);
        Font font = new Font("Serif",Font.BOLD,24);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(numString,(int)numX,(int)numY);

        //selection circle
        if(flyClick) {
            g.setColor(Color.GREEN);
            g.drawOval((int)(x-radius),(int)(y-radius),(int)(radius*2)+1,(int)(radius*2)+1);
        }
    }
}
