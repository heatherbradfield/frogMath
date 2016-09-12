import java.awt.*;

/**
 * Created by Heather on 9/11/16.
 */
public class Fly {

    private int x = 400;
    private int y = 25;
    private double dx = 1;
    private double dy = 1;
    private int radius = 20; //pixels

    public Fly() {

    }

    public Fly(int i, int j) {
        x = i;
        y = j;
    }

    public void update(StartingPoint sp) {
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
    }

    public void paint(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x-radius,y-radius,radius*2,radius*2);
    }
}
