package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import control.Controller;


/**
 * The Abstract that all implementations of a view for this SLogo implementaiton will use.
 * 
 * @author srwareham, yoshi
 * 
 */
public abstract class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String ENGLISH = "English";
    private static final String USER_DIR = "user.dir";
    private static final String OPEN = "OpenCommand";
    private static final String FILE = "FileMenu";
    private static final String QUIT = "QuitCommand";
    private static final String NEW = "NewCommand";
    private static final String SAVE = "SaveCommand";
    private JFileChooser myChooser;
    /*
     * private ActionListener myActionListener;
     * private KeyListener myKeyListener;
     * private MouseListener myMouseListener;
     * private MouseMotionListener myMouseMotionListener;
     * private FocusListener myFocusListener;
     */
    /**
     * Preferred Dimensions of the Canvas.
     */
    public static final Dimension PREFERRED_CANVAS_SIZE = new Dimension(400, 500);
//TODO: make getter for resources
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
        result.add(makeFileMenu());
        return result;
    }
//_________________vvv FILE MENU STUFF
    /**
     * Create a menu that will pop up when the menu button is pressed in the
     * frame. File menu usually contains Open, Save, and Exit
     * 
     * Note, since these classes will not ever be used by any other class, make
     * them inline (i.e., as anonymous inner classes) --- saves making a
     * separate file for one line of actual code.
     */
    protected JMenu makeFileMenu () {
        JMenu result = new JMenu(myResources.getString(FILE));
        result.add(makeMenuBarNew());
        result.add(makeMenuBarOpen());
        result.add(makeMenuBarSave());
        result.add(new JSeparator());
        result.add(makeMenuBarQuit());
        return result;
    }
    private AbstractAction makeMenuBarNew() {
        return new AbstractAction(myResources.getString(NEW)) {
            /**
             * 
             */
            private static final long serialVersionUID = -686883125108316843L;

            @Override
            public void actionPerformed (ActionEvent e) {
                //TODO: how do we want a new workspace??  currently this will cascade down.
                View newView = new SLogoView("English", "SLogo");
            }
        };
    }
    
    private AbstractAction makeMenuBarOpen() {
        return new AbstractAction(myResources.getString(OPEN)) {
            /**
             * 
             */
            private static final long serialVersionUID = -3471532304609267535L;

            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        InputStream in = new FileInputStream(myChooser.getSelectedFile());
                        myController.loadState(in);
                    }
                }
                catch (IOException io) {
                    //TODO: add exception handeling
                }
            }
        };
    }
    
    private AbstractAction makeMenuBarSave() {
        return new AbstractAction(myResources.getString(SAVE)) {
            /**
             * 
             */
            private static final long serialVersionUID = -686883125108316843L;

            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myChooser.showSaveDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        OutputStream out = new FileOutputStream(myChooser.getSelectedFile());
                        myController.saveState(out);
                    }
                }
                catch (IOException io) {
                    //TODO: add exception handeling
                }
            }
        };
    }
    
    private AbstractAction makeMenuBarQuit() {
        return new AbstractAction(myResources.getString(QUIT)) {
            /**
             * 
             */
            private static final long serialVersionUID = 1514963101036925921L;

            @Override
            public void actionPerformed (ActionEvent e) {
                System.exit(0);
            }
        };
    }
    
//_____________________^^^^FILE MENU STUFF    
    

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
