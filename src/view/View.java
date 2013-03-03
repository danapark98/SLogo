package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import simulation.Model;
import control.Controller;


/**
 * The Abstract that all implementations of a view for this SLogo implementaiton will use.
 * 
 * @author srwareham
 * 
 */
public abstract class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String ENGLISH = "English";
    private static final String USER_DIR = "user.dir";
    private JFileChooser myChooser;
    /*
    private ActionListener myActionListener;
    private KeyListener myKeyListener;
    private MouseListener myMouseListener;
    private MouseMotionListener myMouseMotionListener;
    private FocusListener myFocusListener;

    */
    /**
     * Preferred Dimensions of the Canvas.
     */
    public static final Dimension PREFERRED_CANVAS_SIZE = new Dimension(400, 500);

    protected ResourceBundle myResources;
    protected Canvas myCanvas;
    protected Controller myController;

    /**
     * Creates a SLogoView.
     * 
     * @param title The title of this View
     * @param language The desired language for the View
     */
    public View (String title, String language) {
        try {
            myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        }
        catch (MissingResourceException e) {
            myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + ENGLISH);
        }

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        setCanvas();
    }

    /**
     * Method to build all InputAccepting JComponents.
     * 
     * @return
     */
    protected abstract JComponent makeInput ();

    /**
     * Method to build the display area consisting of the Canvas
     * 
     * @return
     */
    protected abstract JComponent makeDisplay ();

    /**
     * Method to display a text to the user in a display Box.
     * TODO:we need this to be called by the controller twice for every parsed phrase.  
     * One repeating the phrase sent with ">>" before it, and then once for the return value
     * @param text Text to display
     */
    
    public abstract void displayText (String text);

    /**
     * Sets the Controller for this View.
     * 
     * @param controller Controller that will watch over all View Actions
     *        / will create listeners
     */
    public void setController (Controller controller) {
        myController = controller;
    }

    /**
     * Sets the canvas for the view. Will be called from the model or controller.
     * Called with command within controller such as myView.super.setCanvas(model)
     * This method also starts the Canvas, which loads the model
     * 
     * @param model Model that should be displayed within the Canvas
     */
    public void setCanvas () {
        myCanvas = new Canvas(PREFERRED_CANVAS_SIZE);
        myCanvas.start(this);
    }

    /**
     * Method to send commands to the controller.
     * 
     * @param command String that needs to be parsed (may be multiple lines)
     */

    protected void sendCommand (String command) {
        myController.createRunInstruction(command);
    }

}
