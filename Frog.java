import java.awt.*;

/**
 * Created by Heather on 9/11/16.
 */
public class Frog {

    private int x, y, width, height;
    private String operation;

    public Frog() {
        x = 325;
        y = 480;
        width = 120;
        height = 80;
    }

    public Frog(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public void update(StartingPoint sp, Fly f) {
        checkForCollision(f);

    }

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

    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(this.x,this.y,this.width,this.height);
    }
}
