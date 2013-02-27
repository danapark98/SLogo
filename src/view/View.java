package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;
import control.Controller;


/**
 * The View is comprised of everything visible to the user.
 * The View is the interactive space containing a file menu, buttons, and the Canvas.
 * 
 * @author srwareham
 * 
 */
public class View extends SLogoView {
    private static final String TURN_MAGNITUDE_LABEL = "TurnMagnitude";

    public static final String USER_DIR = "user.dir";
    
    public static final int MIN_DISPLACEMENT_MAGNITUDE = 0;
    public static final int MAX_DISPLACEMENT_MAGNITUDE = 500;
    public static final int INITIAL_DISPLACEMENT_MAGNITUDE = 50;
    public static final String FORWARD_COMMAND = "ForwardCommand";
    public static final String SUBMIT_COMMAND = "SubmitCommand";
    public static final String FD = "fd ";
    public static final int DEFAULT_FD_MAG = 10;
    public static final Dimension PREFERRED_CONSOLE_SIZE = new Dimension(200, 200);
    public static final Dimension PREFERRED_HISTORY_SIZE = new Dimension(200, 200);
    
    
    private JFileChooser myChooser;
    private ActionListener myActionListener;
    private KeyListener myKeyListener;
    private MouseListener myMouseListener;
    private MouseMotionListener myMouseMotionListener;
    private FocusListener myFocusListener;
    
    
    private JTextArea myConsole;
    private JTextArea myHistory;

    /**
     * Creates an instance of the View.
     * 
     * @param title The title of this View
     * @param language The desired language for the View
     */
    public View (String title, String language) {
        super(title, language);
        getContentPane().add(makeInput(), BorderLayout.NORTH);
        getContentPane().add(makeDisplay(), BorderLayout.CENTER);
        pack();
        setVisible(true);

    }

    @Override
    public void displayText (String text) {

    }

    @Override
    protected JComponent makeInput () {
        JPanel result = new JPanel();
        result.add(makeForwardButton());
        result.add(makeSubmitButton());
        return result;
    }

    @Override
    protected JComponent makeDisplay () {
        JPanel panel = new JPanel();
        panel.add(myCanvas);
        panel.add(makeCommandConsole());
        panel.add(makeHistory());
        return panel;
    }

    private JButton makeForwardButton () {
        // TODO: change fd mag to a variable from an input slider
        // we also need to look into this final usage
        final String command = FD + DEFAULT_FD_MAG;
        final Controller controller = super.myController;
        JButton button = new JButton(super.myResources.getString(FORWARD_COMMAND));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                // TODO: this was a change to the API we noticed
                controller.createRunInstruction(command);
                System.out.println("Forward Button");
            }
        });
        return button;
    }
    
    private JButton makeSubmitButton() {
        JButton button = new JButton(super.myResources.getString(SUBMIT_COMMAND));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                //TODO: add contoller command here.
                if (myHistory.getText().length() == 0) {
                    myHistory.append(myConsole.getText());
                }
                else {
                    myHistory.append("\n" + myConsole.getText());
                }
                myConsole.setText("");
            }
        });
        return button;
    }
    
    
    //TODO: should subsume these two methods
    private JScrollPane makeCommandConsole() {
        JTextArea textArea = new JTextArea();
        myConsole = textArea;
//        textArea.setEditable(false);
        JScrollPane pane = new JScrollPane(textArea);
        pane.setPreferredSize(PREFERRED_CONSOLE_SIZE);
        
        return pane;
    }
    
    private JScrollPane makeHistory() {
        JTextArea text = new JTextArea();
        myHistory = text;
        text.setEditable(false);
        JScrollPane pane = new JScrollPane(text);
        pane.setPreferredSize(PREFERRED_CONSOLE_SIZE);
        return pane;
    }
    
    private JSlider makeTurnMagnitudeSlider() {
        JLabel turnLabel = new JLabel(myResources.getString(TURN_MAGNITUDE_LABEL));
        JSlider mag = new JSlider(JSlider.HORIZONTAL,
                                  MIN_DISPLACEMENT_MAGNITUDE,
                                  MAX_DISPLACEMENT_MAGNITUDE,
                                 INITIAL_DISPLACEMENT_MAGNITUDE);
        mag.setMajorTickSpacing(10);
        mag.setMinorTickSpacing(1);
        mag.setPaintTicks(true);
        mag.setPaintLabels(true);
        //TODO: having difficulty adding listener for the slider...
        return mag;
        
    }

    // myController.sendString(s);

    // TODO: we may add addJComponent(JComponent j) to our controller so that it can recieve
    // instances of swing objects so that it can use "j.addKeyListener( new .....)" would need to
    // document as a change to our API
    // the issue stems from the fact that the view holds the JComponent, while the Control hods the
    // definiton for what the actionPerformed should be. May also need seperate one for JButton to
    // be able to addActionListener for some reason
    /*
     * Example:
     * protected JButton makeClear () {
     * JButton result = new JButton(myResources.getString("ClearCommand"));
     * result.addActionListener(new ActionListener() {
     * 
     * @Override
     * public void actionPerformed (ActionEvent e) {
     * myTextArea.setText("");
     * }
     * });
     * return result;
     * }
     * 
     * probably we want to send the button or any other componenet type (not sure if we want several
     * specific methods or 1 more general one) and then add the listener and action performed to it
     * 
     * otherwise stuck wondering if there is a way to pass off a JButton with an ActionListener to
     * the control class. Then have the control class override the actionPerformed so that it does
     * what control knows it needs to
     * suppose we could pass off the JComponents with as much info as possible from the view. Then
     * let the controller read all of its Listener info, create a new instance with the proper
     * actionPerformed, and then
     * place this newer ActionListener w/ complete actionPerformed into the JButton
     */

    public static void main (String[] args) {
        new View("HI", "English");

    }

}
