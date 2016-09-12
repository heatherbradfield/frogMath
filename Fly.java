import java.awt.*;
import java.util.Random;

/**
 * Created by Heather on 9/11/16.
 */
public class Fly {

    private double x = 400;
    private double y = 25;
    private double dx = 0.5;
    private double dy = 0.5;
    private double radius = 20; //pixels
    private int num = 0;
    private double numX = 0;
    private double numY = 0;
    private boolean flyClick = false;


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
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getRadius() {
        return radius;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getNum() {
        return this.num;
    }

    public void setFlyClick(Boolean b) {
        this.flyClick = b;
    }

    public boolean getFlyClick()
    {
        return this.flyClick;
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

        numX = x;
        numY = y;

        checkForCollision();
    }

    private void checkForCollision() {

    }


    public void paint(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval((int)(x-radius),(int)(y-radius),(int)radius*2,(int)radius*2);
        String numString = Integer.toString(this.num);
        Font font = new Font("Serif",Font.BOLD,16);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(numString,(int)numX,(int)numY);
    }
}
