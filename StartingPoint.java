import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Heather on 9/11/16.
 */
public class StartingPoint extends Applet implements Runnable, MouseListener {

    private Image i;
    private Graphics doubleG;
    Fly f[] = new Fly[2];
    Frog frog;
    int target;
    int score;
    int backgroundX = 0;
    Image water;
    boolean mouseClick = false;
    

    @Override
    public void init() {
        setSize(800,600);
        addMouseListener(this);
        water = getImage(getCodeBase(), "background.png");
    }

    private void addMouseListener(StartingPoint sp) {

    }

    @Override
    public void start() {
        frog = new Frog();
        target = 0;
        score = 0;

        for (int i = 0; i < f.length; i++) {
            f[i] = new Fly();
        }
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        //thread info
        while (true) {
            for (int i = 0; i < f.length; i++) {
                f[i].update(this);
                frog.update(this,f[i]);
            }
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
        g.drawImage(water,backgroundX,0,this);
        frog.paint(g);
        for (int i = 0; i < f.length; i++) {
            f[i].paint(g);
        }

        String t = Integer.toString(target);
        Font font = new Font("Serif",Font.BOLD,48);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString(t,getWidth()-150,50);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() == f[1].getX()) {
            mouseClick = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

