package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

import simulation.Model;

/**
 * SLogoCanvas is the abstract class that contains all the necessary 
 * methods a Canvas must implement.
 * @author Yoshi
 *
 */
public abstract class SLogoCanvas extends JComponent{

    /**
     * default serialization ID
     */
    private static final long serialVersionUID = 1L;
    /**
     * Frames_Per_Second for running the simulation.
     */
    public static final int FRAMES_PER_SECOND = 30;
    /**
     * 
     * Number of milliseconds in a second.
     */
    public static final int ONE_SECOND = 1000;
    /**
     * Default delay time
     */
    public static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;

    private Model mySimulation;
    private SLogoView myView;
    protected Dimension myBounds;
    private Timer myTimer;


    public SLogoCanvas(Dimension size, SLogoView view, Model model){
	myView = view;
	mySimulation = model;
	myBounds = size;
	setPreferredSize(size);
	setSize(size);
    }

    /**
     * Paint the contents of the canvas.
     * 
     * @param pen used to paint shape on the screen
     */
    @Override
    public void paintComponent (Graphics pen) {
	pen.setColor(Color.WHITE);
	pen.fillRect(0, 0, getSize().width, getSize().height);
	//  TODO: Verify if this if condition is really necessary
	if (mySimulation != null) {
	    mySimulation.paint((Graphics2D) pen);
	}
    }
    
    //TODO: Must reach an agreement on whether we should change the model constructor or add an get/setModel
    /**
     * Starts the timer which is responsible for updating the model.
     * This is within the purview of the View because at every frame the view
     * requires something to display, it gets a new snapshot from the model.
     */
    public void start () {
        // create a timer to animate the canvas
        myTimer = new Timer(DEFAULT_DELAY,
                            new ActionListener() {
                    @Override
                    public void actionPerformed (ActionEvent e) {
                	//mySimulation.getModel();
                        step(mySimulation);
                    }
                });
        myTimer.start();
    }

    // TODO: we will need to add stop to the api
    /**
     * Stops the timer animating the simulation.
     */
    public void stop () {
        myTimer.stop();
    }

    /**
     * Increments the animation one step.
     * 
     * @param simulation The model to be stepped
     */
    private void step (Model simulation) {
	simulation.update((double) FRAMES_PER_SECOND / ONE_SECOND, myBounds);
        repaint();
    }


}
