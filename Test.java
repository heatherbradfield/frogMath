/**
 * Created by Joshua on 9/6/2016.
 */

import java.applet.Applet; // Provides the Applet class.
import java.awt.*;         // Provides Button class, etc.
import java.awt.event.*;   // Provides ActionEvent, ActionListener

public class Test extends Applet
{
    TextField titleText = new TextField(10);
    Button    play    = new Button("Play!");

    public void init( )
    {
        // Some messages for the top of the Applet:
        addHorizontalLine(Color.orange);
        addNewLine( );

        addNewLine( );
        add(new Label("Play FROGS!"));
        addNewLine( );
        add(play);
        addNewLine( );

        // A text area for printing the answers:

        // Tell the button what it should do when clicked:
        play.addActionListener(new LaunchListener( ));
    }


    public void addBackground(Color c)
    {
        //add background canvas
        Canvas back = new Canvas();
        back.setSize(700, 500);
        back.setBackground(c);
        add(back);
    }

    class LaunchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {

        }
    }
    private void addHorizontalLine(Color c)
    {
        // Add a Canvas 10000 pixels wide but only 1 pixel high, which acts as
        // a horizontal line to separate one group of components from the next.
        Canvas line = new Canvas( );
        line.setSize(10000,1);
        line.setBackground(c);
        add(line);
    }


    private void addNewLine( )
    {
        // Add a horizontal line in the background color. The line itself is
        // invisible, but it serves to force the next Component onto a new line.
        addHorizontalLine(getBackground( ));
    }
}
