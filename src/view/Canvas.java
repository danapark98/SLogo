package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
     * 
     */
    private static final long serialVersionUID = 1L;
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
    public static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;

    private Timer myTimer;
    private Model mySimulation;
    private SLogoView myView;

    private int myLastKeyPressed;
    private Set<Integer> myKeys;
    private Dimension myBounds;
/**
 * Creates a Canvas of the given size.
 * @param size Desired Dimension for the Canvas
 * @param view Parent view for this Canvas
 * @param model The simulation environment to be displayed in the canvas
 */
    public Canvas (Dimension size, SLogoView view, Model model) {
        myView = view;
        mySimulation = model;
        myBounds = size;
        setPreferredSize(size);
        setSize(size);
        //do  not believe this needs to be focusable because there is no user interaction
        //with the canvas display being showed
    }

    /**\
     * This method paints all Graphics objects such as the turtle and the lines.
     * @param pen 
     */
    public void paintComponent(Graphics pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getSize().width, getSize().height);
        if (mySimulation != null) {
            mySimulation.paint((Graphics2D) pen);
        }
    }
    /**
     * Starts the timer which is responsible for updating the model.
     * This is within the purview of the View because at every frame the view
     * requires something to display, it gets a new snapshot from the model.
     */
    public void start () {
        // create a timer to animate the canvas
        myTimer = new Timer(DEFAULT_DELAY, 
            new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    step(mySimulation);
                }
            });
        // start animation
//        mySimulation = new Model(myView);
//        loadModel();
        myTimer.start();
    }
    
    //TODO: we will need to add stop to the api
    /**
     * Stops the timer animating the simulation.
     */
    public void stop() {
        myTimer.stop();
    }
    //TODO: we will need to add step to the api might be able to keep this private
    /**
     * Increments the animation one step.
     * @param simulation The model to be stepped
     */
    public void step (Model simulation) {
        simulation.update((double)FRAMES_PER_SECOND / ONE_SECOND, myBounds);
        repaint();
    }
}
