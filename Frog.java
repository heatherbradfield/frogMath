import java.awt.*;

/**
 * Created by Heather on 9/11/16.
 */
public class Frog {

    private int x, y, width, height;
    private String operation;
    private boolean num1 = false;
    private boolean num2 = false;

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
        sum(f);
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

    private void sum (Fly f) {
        if (f.getFlyClick()) {

        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(this.x,this.y,this.width,this.height);
    }
}

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
