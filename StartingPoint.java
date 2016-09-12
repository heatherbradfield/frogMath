import java.applet.Applet;
import java.awt.*;

/**
 * Created by Heather on 9/11/16.
 */
public class StartingPoint extends Applet implements Runnable {

    private Image i;
    private Graphics doubleG;
    Fly fly1, fly2, fly3, fly4;

    @Override
    public void init() {
        setSize(800,600);
    }

    @Override
    public void start() {
        fly1 = new Fly();
        fly2 = new Fly(250,250);
//        fly3 = new Fly();
//        fly4 = new Fly();
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        //thread info
        while (true) {
            fly1.update(this);
            fly2.update(this);
            repaint();
            try {
                Thread.sleep(17); //60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void update(Graphics g) {
        //display old pixels and repaint over (double buffering)
        //deals with flickering problems
        if (i == null) {
            i = createImage(this.getWidth(),this.getHeight());
            doubleG = i.getGraphics();
        }

        doubleG.setColor(getBackground());
        doubleG.fillRect(0,0, this.getWidth(), this.getHeight());

        doubleG.setColor(getForeground());
        paint(doubleG);

        g.drawImage(i, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        fly1.paint(g);
        fly2.paint(g);
    }

}
