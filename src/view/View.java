package view;

import control.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;


/**
 * The Abstract that all implementations of a view for this SLogo implementaiton will use.
 * 
 * @author srwareham, yoshi
 * 
 */
public abstract class View extends JFrame {

    /**
     * Preferred Dimensions of the Canvas.
     */
    public static final Dimension PREFERRED_CANVAS_SIZE = new Dimension(400, 500);
    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String ENGLISH = "English";
    private static final String USER_DIR = "user.dir";

    private ResourceBundle myResources;
    private Canvas myCanvas;
    private Controller myController;
    private String myLanguage;
    private JFileChooser myChooser;

    /**
     * Creates a SLogoView.
     * 
     * @param title The title of this View
     * @param language The desired language for the View
     */
    public View (String title, String language) {
        myLanguage = language;
        try {
            myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        }
        catch (MissingResourceException e) {
            myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + ENGLISH);
        }

        setTitle(title);
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        setCanvas();
        getContentPane().add(makeMenus(), BorderLayout.NORTH);
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
    protected abstract JComponent makeCanvasPanel ();

    /**
     * Create a menu to appear at the top of the frame,
     * usually File, Edit, App Specific Actions, Help
     */
    protected JMenuBar makeMenus () {
        JMenuBar result = new JMenuBar();
        ViewFileMenu menu = new ViewFileMenu(this);
        result.add(menu.makeFileMenu());
        return result;
    }

    /**
     * Method to display a text to the user in a display Box.
     * 
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
     * 
     */
    public void setCanvas () {
        myCanvas = new Canvas(PREFERRED_CANVAS_SIZE);
        myCanvas.start(this);
    }

    protected Controller getController () {
        return myController;
    }

    protected Canvas getCanvas () {
        return myCanvas;
    }

    /**
     * Returns the Resources from the View.
     * 
     * @return
     */
    public ResourceBundle getResources () {
        return myResources;
    }

    /**
     * returns the JFileChooser for the View.
     * 
     * @return
     */
    public JFileChooser getChooser () {
        return myChooser;
    }

    /**
     * Returns the current language of the View.
     * 
     * @return
     */
    public String getLanguage () {
        return myLanguage;
    }
}
