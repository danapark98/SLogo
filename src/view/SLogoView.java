package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import control.Controller;


/**
 * The View is comprised of everything visible to the user.
 * The View is the interactive space containing a file menu, buttons, and the Canvas.
 * 
 * @author srwareham, yoshi
 * 
 */
public class SLogoView extends View {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String USER_DIR = "user.dir";
    public static final String FORWARD_COMMAND = "ForwardCommand";
    public static final String SUBMIT_COMMAND = "SubmitCommand";
    public static final String FD = "fd ";
    public static final int DEFAULT_FD_MAG = 10;
    public static final Dimension PREFERRED_CONSOLE_SIZE = new Dimension(250, 50);
    public static final Dimension PREFERRED_HISTORY_SIZE = new Dimension(350, 200);
    
    private static final String TURN_MAGNITUDE_LABEL = "TurnMagnitude";
    public static final int MIN_DISPLACEMENT_MAGNITUDE = 0;
    public static final int MAX_DISPLACEMENT_MAGNITUDE = 500;
    public static final int INITIAL_DISPLACEMENT_MAGNITUDE = 50;
    private static final String BACKWARD_COMMAND = "BackwardCommand";
    
    private JFileChooser myChooser;
    
    /*
     * TODO: Implement correctly the menu bar
     * TODO: Implement SAVE, CLEAR
     * TODO: Implement the NEW workspace command
     * TODO: REFACTOR CODE!
     */
    /*
     * private ActionListener myActionListener;
     * private KeyListener myKeyListener;
     * private MouseListener myMouseListener;
     * private MouseMotionListener myMouseMotionListener;
     * private FocusListener myFocusListener;
     */
    
    private JTextArea myConsole;
    private JTextArea myHistory;
    
    /**
     * Creates an instance of the View.
     * 
     * @param title The title of this View
     * @param language The desired language for the View
     */
    public SLogoView (String title, String language) {
        super(title, language);
        getContentPane().add(makeMainPanel(), BorderLayout.CENTER);
        getContentPane().add(makeMenus(), BorderLayout.NORTH);
        pack();
        setVisible(true);
        
    }
    
    private JTabbedPane makeMainPanel () {
        JTabbedPane workspace = new JTabbedPane();
        JPanel contentPanel = new JPanel();
        workspace.addTab("Worspace 1", null, contentPanel, "SLogo");
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPanel.add(makeCanvasPanel());
        contentPanel.add(makeHistAndInputPanel());
        return workspace;
        
    }
    
    private JComponent makeCanvasPanel () {
        JPanel canvasPanel = new JPanel();
        canvasPanel.add(myCanvas);
        canvasPanel
        .setBorder(BorderFactory.createCompoundBorder(
                                                      BorderFactory
                                                      .createTitledBorder("Canvas"),
                                                      BorderFactory.createEmptyBorder(5, 5,
                                                                                      5, 5)));
        return canvasPanel;
    }
    
    private JComponent makeHistAndInputPanel () {
        JPanel hstInpPanel = new JPanel();
        hstInpPanel.setLayout(new BoxLayout(hstInpPanel, BoxLayout.PAGE_AXIS));
        hstInpPanel.add(makeInput());
        hstInpPanel.add(makeHistory());
        return hstInpPanel;
    }
    
    // TODO: merge this and appendHistory,they are the same
    @Override
    public void displayText (String text) {
        appendHistory(text);
    }
    
    @Override
    protected JComponent makeInput () {
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));
        result.setBorder(BorderFactory.createCompoundBorder(
                                                            BorderFactory
                                                            .createTitledBorder("Input"),
                                                            BorderFactory.createEmptyBorder(5, 5,
                                                                                            5, 5)));
        result.add(makeForwardButton());
        result.add(makeBackwardButton());
        result.add(makeCommandConsole());
        result.add(makeSubmitButton());
        // result.add(makeTurnMagnitudeSlider());
        return result;
    }
    
    @Override
    protected JComponent makeDisplay () {
        JPanel panel = new JPanel();
        panel.add(myCanvas);
        panel.add(makeHistory());
        return panel;
    }
    
    private JButton makeForwardButton () {
        // TODO: change fd mag to a variable from an input slider
        // we also need to look into this final usage
        final String command = "" + FD + DEFAULT_FD_MAG;
        final Controller controller = super.myController;
        JButton button = new JButton(super.myResources.getString(FORWARD_COMMAND));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                // TODO: this was a change to the API we noticed
                controller.createRunInstruction(command);
                appendHistory(command);
                System.out.println(command);
            }
        });
        return button;
    }
    
    private JButton makeBackwardButton () {
        // TODO: change fd mag to a variable from an input slider
        // we also need to look into this final usage
        final String command = FD + -DEFAULT_FD_MAG;
        final Controller controller = super.myController;
        JButton button = new JButton(super.myResources.getString(BACKWARD_COMMAND));
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
    
    private JButton makeSubmitButton () {
        final Controller controller = super.myController;
        JButton button = new JButton(super.myResources.getString(SUBMIT_COMMAND));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                // TODO: add contoller command here.
                // appendHistory(myConsole.getText());
                // this is redunadant with view method for sending a command
                controller.createRunInstruction(myConsole.getText());
                myConsole.setText("");
            }
        });
        return button;
    }
    
    /**
     * Create a menu to appear at the top of the frame,
     * usually File, Edit, App Specific Actions, Help
     */
    protected JMenuBar makeMenus () {
        JMenuBar result = new JMenuBar();
        result.add(makeFileMenu());
        return result;
    }
    
    /**
     * Create a menu that will pop up when the menu button is pressed in the
     * frame. File menu usually contains Open, Save, and Exit
     * 
     * Note, since these classes will not ever be used by any other class, make
     * them inline (i.e., as anonymous inner classes) --- saves making a
     * separate file for one line of actual code.
     */
    protected JMenu makeFileMenu () {
        JMenu result = new JMenu(myResources.getString("FileMenu"));
        result.add(new AbstractAction(myResources.getString("OpenCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        echo(new FileReader(myChooser.getSelectedFile()));
                    }
                }
                catch (IOException io) {
                    // let user know an error occurred, but keep going
                   // showError(io.toString());
                }
            }
        });
        result.add(new AbstractAction(myResources.getString("SaveCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    echo(new FileWriter("demo.out"));
                }
                catch (IOException io) {
                    // let user know an error occurred, but keep going
                    //showError(io.toString());
                }
            }
        });
        result.add(new JSeparator());
        result.add(new AbstractAction(myResources.getString("QuitCommand")) {
            @Override
            public void actionPerformed (ActionEvent e) {
                // clean up any open resources, then
                // end program
                System.exit(0);
            }
        });
        return result;
    }
    
    private void appendHistory (String text) {
        if (text.length() > 0) {
            if (myHistory.getText().length() == 0) {
                myHistory.append(text);
            }
            else {
                myHistory.append("\n" + text);
            }
        }
    }
    
    /**
     * Echo display to writer
     */
    private void echo (Writer w) {
        PrintWriter output = new PrintWriter(w);
        // output.println(myHistory.getText());
        output.flush();
        output.close();
    }
    /**
     * Echo data read from reader to display
     */
    private void echo (Reader r) {
        try {
            String s = "";
            BufferedReader input = new BufferedReader(r);
            String line = input.readLine();
            while (line != null) {
                s += line + "\n";
                line = input.readLine();
            }
            myHistory.append("\n"+s);
        }
        catch (IOException e) {
           // showError(e.toString());
        }
    }
    
    // TODO: should subsume these two methods
    private JScrollPane makeCommandConsole () {
        JTextArea textArea = new JTextArea();
        myConsole = textArea;
        //textArea.setEditable(false);
        JScrollPane pane = new JScrollPane(textArea);
        pane.setPreferredSize(PREFERRED_CONSOLE_SIZE);
        
        return pane;
    }
    
    private JPanel makeHistory () {
        JTextArea text = new JTextArea();
        JPanel histPane = new JPanel();
        myHistory = text;
        text.setEditable(false);
        JScrollPane pane = new JScrollPane(text);
       
        
        histPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory
                                                              .createTitledBorder("History"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        histPane.add(pane);
        text.setPreferredSize(PREFERRED_HISTORY_SIZE);
        return histPane;
    }
    
    // TODO: maybe add slider. A default value is acceptable practice
    // private JSlider makeTurnMagnitudeSlider () {
    // JLabel turnLabel = new JLabel(myResources.getString(TURN_MAGNITUDE_LABEL));
    // JSlider mag = new JSlider(JSlider.HORIZONTAL,
    // MIN_DISPLACEMENT_MAGNITUDE,
    // MAX_DISPLACEMENT_MAGNITUDE,
    // INITIAL_DISPLACEMENT_MAGNITUDE);
    // mag.setMajorTickSpacing(10);
    // mag.setMinorTickSpacing(1);
    // mag.setPaintTicks(true);
    // mag.setPaintLabels(true);
    // // TODO: having difficulty adding listener for the slider...
    // return mag;
    //
    // }
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
}
