import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A class of bags whose entries are stored in a chain of linked nodes.
 * The bag is never full.
 *
 * @author Heather Bradfield
 * @version 09/11/2016
 */
public class StartingPoint extends Applet implements Runnable, MouseListener{

    private Image i;
    private Graphics doubleG;
    ArrayList<ArrayList<Pair>> targets = new ArrayList<>();
    private ArrayList<Fly> flies = new ArrayList<>();
    private Frog frog;
    private int targetSum;
    private int score = 0;
    private int waterX = 0;
    private Image water;
    private boolean flyClick = false;

    /**
     * Counts the number of times a given entry appears in this bag.
     */
    @Override
    public void init() {
        setSize(800,600);
        addMouseListener(this);
        water = getImage(getDocumentBase(),"Images/background.png");
    }

    /**
     * Counts the number of times a given entry appears in this bag.
     */
    private void addMouseListener(StartingPoint sp) {

    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     */
    @Override
    public void start() {
        Random r = new Random();
        targetSum = r.nextInt(8)+2;
        frog = new Frog();

        int maxTarget = 10;
        generateTargets(targets, maxTarget);
        generateFlies(targets, targetSum);
        score += 1;

        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param targets the entry to be counted
     * @param maxTarget the entry to be counted
     */
    public void generateTargets(ArrayList<ArrayList<Pair>> targets, int maxTarget) {
        ArrayList<Pair> sums;
        Pair p;
        Fly f1, f2;
        int i,j, k;
        for (int n = 0; n < maxTarget - 2; n++) {
            sums = new ArrayList<>();
            j = 1;
            i = n + 2;
            while (j <= i / 2) {
                k = i - j;
                f1 = new Fly(j);
                f2 = new Fly(k);
                p = new Pair(f1, f2);
                sums.add(p);
                j++;
            }
            targets.add(sums);
        }
    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param targets the entry to be counted
     * @param targetSum the entry to be counted
     */
    public void generateFlies(ArrayList<ArrayList<Pair>> targets, int targetSum) {
        for (int i = 0; i < targets.get(targetSum - 2).size(); i++) {
            Pair p = targets.get(targetSum - 2).get(i);
            flies.add(p.getF1());
            flies.add(p.getF2());
        }
    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     */
    @Override
    public void run() {
        //thread info
        while (true) {
            for (Fly f : flies) {
                f.update(this);
                frog.update(this,f);
            }
            repaint();
            try {
                Thread.sleep(17); //60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     */
    @Override
    public void stop() {

    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     */
    @Override
    public void destroy() {

    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param g the entry to be counted
     */
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

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param g the entry to be counted
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(44,185,255));
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(water,waterX,0,this);
        frog.paint(g);

        for (Fly f : flies) {
            f.paint(g);
        }

        String t = Integer.toString(targetSum);
        Font font = new Font("Serif",Font.BOLD,72);
        g.setFont(font);
        g.setColor(new Color(162,5,240));
        g.drawString(t,getWidth()-100,100);
    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param e the entry to be counted
     */
    public void mouseClicked(MouseEvent e) {
        for (Fly f : flies) {
            int xPos = e.getX();
            int yPos = e.getY();
            double fX = f.getX();
            double fY = f.getY();
            double fR = f.getRadius();
            if (xPos >= fX && xPos <= fX + fR && yPos >= fY && yPos <= fY + fR) {
                f.setFlyClick(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param e the entry to be counted
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param e the entry to be counted
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param e the entry to be counted
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

}

class Pair {
    private double x;
    private double y;
    private Fly f1;
    private Fly f2;

    Pair (Fly one, Fly two) {
        this.f1 = one;
        this.f2 = two;
        this.x = f1.getNum();
        this.y = f2.getNum();
    }

    public Fly getF1() {
        return f1;
    }

    public Fly getF2() {
        return f2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

