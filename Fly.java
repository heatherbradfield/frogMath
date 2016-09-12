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
    private int num;
    private double numX;
    private double numY;


    public Fly() {
        Random r = new Random();  //FIX
        this.x = r.nextInt(100);
        this.numX = this.x + 2;
        this.y = r.nextInt(200) + 50;
        this.numY = this.y + 2;
        this.num = r.nextInt(11);
    }

    public Fly(int i, int j) {
        this.x = i;
        this.numX = this.x + 2;
        this.y = j;
        this.numY = this.y + 2;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
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

    public void setRadius(int radius) {
        this.radius = radius;
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
