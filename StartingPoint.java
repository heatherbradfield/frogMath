import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A class to act as a StartingPoint for Graphics/Mechanics Modules.
 * Substitute for the "GameManager" during development.
 * Allows for testing of in-game graphics and mechanics.
 * Sets up a default level of the game.
 *
 * @author Heather Bradfield
 * @version 09/11/2016
 */
public class StartingPoint extends Applet implements Runnable, MouseListener{

    private Image i;
    private Graphics doubleG;
    private ArrayList<ArrayList<Pair>> targets = new ArrayList<>();
    private ArrayList<Fly> flies = new ArrayList<>();
    private Frog frog;
    private int targetSum;
    private int maxTarget = 10;
    private Image water;

    /**
     * Initializes applet window size and MouseListener.
     * Imports background Image.
     *
     * TODO: create Menus, Level Map, and actual Game Manager
     * TODO: fix background image import
     */
    @Override
    public void init() {
        setSize(800,600);
        addMouseListener(this);
        water = getImage(getDocumentBase(),"Images/background.png");
    }


    /**
     * Starts default level.
     * Generates possible pairs for all targets.
     * Generates swarm of flies.
     * Starts new Thread.
     * TODO: create Level class with increasing levels of difficulty
     *
     */
    @Override
    public void start() {
        Random r = new Random();

        targetSum = r.nextInt(8)+2;
        generatePairs(targets, maxTarget);
        generateFlies(targets, targetSum);

        frog = new Frog();

        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Given the maxTarget num, for all nums 2 to maxTarget
     * generate Pairs of flies that sum up to a target.
     *
     * @param targets the List of targets and their corresponding pairs of flies
     * @param maxTarget the max target number
     */
    public void generatePairs(ArrayList<ArrayList<Pair>> targets, int maxTarget) {
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
     * Generates a swarm of flies.
     *
     * @param targets the List of targets and their corresponding pairs of flies
     * @param targetSum the target number for the current level
     */
    public void generateFlies(ArrayList<ArrayList<Pair>> targets, int targetSum) {
        for (int i = 0; i < targets.get(targetSum - 2).size(); i++) {
            Pair p = targets.get(targetSum - 2).get(i);
            flies.add(p.getF1());
            flies.add(p.getF2());
        }
    }

    /**
     * Runs the applet and updates graphics.
     *
     */
    @Override
    public void run() {
        //thread info
        while (true) {
            for (Fly f : flies) {
                f.update(this,f);
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
     * Stops the applet.
     * TODO: implement drop down menu with option to pause game.
     */
    @Override
    public void stop() {

    }

    /**
     * Terminates the applet.
     * TODO: implement drop down menu with option to exit game.
     *
     */
    @Override
    public void destroy() {

    }

    /**
     * Display old pixels and repaint over (double buffering)
     * Deals with flickering problems.
     *
     * @param g the game graphics
     */
    @Override
    public void update(Graphics g) {

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
     * Paints graphics, images, titles.
     * TODO: paint current level number
     *
     * @param g the game graphics
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(44,185,255));
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(water,0,0,this);
        frog.paint(g);

        for (Fly f : flies) {
            f.paint(g);
        }

        String gameTitle = "FROG MATH";
        String t = Integer.toString(targetSum);
        Font font = new Font("Serif",Font.BOLD,72);
        g.setFont(font);
        g.setColor(new Color(162,5,240));
        //g.drawString(gameTitle,10,100);
        g.drawString(t,getWidth()-100,100);
    }

    /**
     * Checks if the mouse has clicked a fly.
     * TODO: if fly is selected, paint "selection circle" around fly.
     * TODO: paint fly's number on frog.
     *
     * @param e the mouse event being checked
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

    /**
     * Checks if the mouse has pressed on fly.
     * TODO: if fly is selected, paint "selection circle" around fly.
     * TODO: paint fly's number on frog.
     *
     * @param e the mouse event being checked
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Checks if mouse was released.
     *
     * @param e the mouse event being checked
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Checks if mouse entered a specific area in the applet
     *
     * @param e the mouse event being checked
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Checks if mouse exited the applet.
     *
     * @param e the mouse event being checked
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

}


