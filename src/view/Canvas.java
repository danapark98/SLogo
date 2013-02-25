package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.Timer;

import simulation.Model;
/**
 * The Canvas is the space within the view that will contain a turtle and any lines drawn by it.
 * @author srwareham
 *
 */
public class Canvas extends JComponent {
    /**
     * Frames_Per_Second for running the simulation.
     */
    public static final int FRAMES_PER_SECOND = 30;
    /**\
     * Number of milliseconds in a second.
     */
    public static final int ONE_SECOND = 1000;
    /**
     * Default delay time
     */
    public static final int DEFAULT_DELY = ONE_SECOND / FRAMES_PER_SECOND;
    /**
     * Defined value for when no key is pressed.
     */
    public static final int NO_KEY_PRESSED = -1;
    /**
     * Defined value for when no mouse click has occured.
     */
    public static final Point NO_MOUSE_PRESSED = null;

    private Timer myTime;
    private Model mySimulation;
    private View myView;

    private int myLastKeyPressed;
    private Set<Integer> myKeys;
/**
 * Creates a Canvas of the given size.
 * @param size Desired Dimension for the Canvas
 * @param view Parent view for this Canvas
 */
    public Canvas (Dimension size, View view) {
        myView = view;
        setPreferredSize(size);
        setSize(size);
        setFocusable(true);
        requestFocus();
        setInputListeners();
    }

    public void setInputListeners(){

    }

    public void paintComponent( Graphics pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getSize().width, getSize().height);
        if (mySimulation != null) {
            mySimulation.paint((Graphics2D) pen);//TODO: we need a public paint method within the Model...will need to be added to the API
        }
    }

    public void loadModel() {
        Model model = new Model();

    }
}
