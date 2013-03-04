package view;

import control.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

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
    public static final int DEFAULT_FD_MAG = 10;
    /**
     * 
     */
    public static final int BORDER_OFFSET = 5;
    /**
     * 
     */
    public static final int DEFAULT_TURN_MAG = 15;
    /**
     * 
     */
    public static final String FORWARD_COMMAND = "ForwardCommand";
    /**
     * 
     */
    public static final String SUBMIT_COMMAND = "SubmitCommand";
    /**
     * 
     */
    public static final String FD = "fd ";
    
    /**
     * 
     */
    public static final Dimension PREFERRED_CONSOLE_SIZE = new Dimension(250, 100);
    /**
     * 
     */
    public static final Dimension PREFERRED_HISTORY_SIZE = new Dimension(350, 200);
    /**
     * 
     */
    private static final String LEFT_COMMAND = "left ";
    /**
     * 
     */
    private static final String RIGHT_COMMAND = "right ";
    private static final long serialVersionUID = 1L;
    
    private static final String BACKWARD_COMMAND = "BackwardCommand";
    private static final String CANVAS_NAME = "Canvas";
    private static final String WORKSPACE_NAME = "Workspace";
    private static final String HISTORY_NAME = "History";
    private static final String INPUT_NAME = "Input";
    private static final String SLOGO_NAME = "SLogo";
    private static final String RESOURCE_LOCATION = "/resources/images/";
    private static final String TURTLE_IMG_FILENAME = "turtle_art.png";
    private static final String RIGHT = "RightButton";
    private static final String LEFT = "LeftButton";

    private JTextArea myConsole;
    private JTextArea myHistory;

    /*
     * TODO: Implement CLEAR?
     * TODO: Implement the NEW workspace command
     * TODO: Labels from the Resources
     * TODO: REFACTOR CODE!
     * TODO: add turn button
     */
    /*
     * private ActionListener myActionListener;
     * private KeyListener myKeyListener;
     * private MouseListener myMouseListener;
     * private MouseMotionListener myMouseMotionListener;
     * private FocusListener myFocusListener;
     */

    /**
     * Creates an instance of the View.
     * 
     * @param title The title of this View
     * @param language The desired language for the View
     */
    public SLogoView (String title, String language) {
        super(title, language);
        getContentPane().add(makeMainPanel(), BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    // TODO: merge this and appendHistory,they are the same
    @Override
    public void displayText (String text) {
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
     * *******************************************************************************
     * 
     * @return
     */
    private JTabbedPane makeMainPanel () {
        JTabbedPane workspace = new JTabbedPane();
        JPanel contentPanel = new JPanel();
        workspace.addTab(WORKSPACE_NAME, null, contentPanel, SLOGO_NAME);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_OFFSET,
                              BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET));
        contentPanel.add(makeCanvasPanel());
        contentPanel.add(makeHistAndInputPanel());
        return workspace;

    }

    @Override
    protected JComponent makeCanvasPanel () {
        JPanel canvasPanel = new JPanel();
        canvasPanel.add(super.getCanvas());
        canvasPanel.setBorder(makeBorder(CANVAS_NAME));
        return canvasPanel;
    }

    private JComponent makeHistAndInputPanel () {
        JPanel hstInpPanel = new JPanel();
        hstInpPanel.setLayout(new BoxLayout(hstInpPanel, BoxLayout.PAGE_AXIS));
        hstInpPanel.add(makeInput());
        hstInpPanel.add(makeHistoryPane());
        return hstInpPanel;
    }

    private JPanel makeHistoryPane () {
        JPanel histPane = new JPanel();
        JTextArea textArea = new JTextArea();
        myHistory = textArea;
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setPreferredSize(PREFERRED_HISTORY_SIZE);
        histPane.setLayout(new BoxLayout(histPane, BoxLayout.PAGE_AXIS));
        histPane.add(scrollPane);
        histPane.setBorder(makeBorder(HISTORY_NAME));
        return histPane;
    }

    @Override
    protected JComponent makeInput () {
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));
        result.setBorder(makeBorder(INPUT_NAME));
        result.add(makeTurtleMoveButtons());
        result.add(makeCommandConsole());
        result.add(makeSubmitButton());
        return result;
    }
    
    private JComponent makeTurtleMoveButtons () {
        JPanel turtleMovePane = new JPanel();
        ImageIcon icon = new ImageIcon(getClass().getResource( RESOURCE_LOCATION+TURTLE_IMG_FILENAME));
        JLabel label = new JLabel(icon);
        turtleMovePane.setLayout(new BorderLayout());
        turtleMovePane.add(makeForwardButton(), BorderLayout.NORTH);
        turtleMovePane.add(makeLeftButton(), BorderLayout.LINE_START);
        turtleMovePane.add(makeRightButton(), BorderLayout.LINE_END);
        turtleMovePane.add(makeBackwardButton(), BorderLayout.SOUTH);
        turtleMovePane.add(label, BorderLayout.CENTER);
        
        return turtleMovePane;
    }
    
    private JButton makeLeftButton () {
        final String COMMAND = LEFT_COMMAND + DEFAULT_TURN_MAG;
        return makeJButtonCommand(super.getResources().getString(LEFT), COMMAND);
    }
    
    private JButton makeRightButton () {
        final String COMMAND = RIGHT_COMMAND + DEFAULT_TURN_MAG;
        return makeJButtonCommand(super.getResources().getString(RIGHT), COMMAND);
    }

    private JScrollPane makeCommandConsole () {
        JTextArea textArea = new JTextArea();
        myConsole = textArea;
        JScrollPane pane = new JScrollPane(myConsole);
        pane.setPreferredSize(PREFERRED_CONSOLE_SIZE);
        return pane;
    }

    private JButton makeForwardButton () {
        final String COMMAND = FD + DEFAULT_FD_MAG;
        return makeJButtonCommand(super.getResources().getString(FORWARD_COMMAND), COMMAND);
    }

    private JButton makeBackwardButton () {
        // TODO: change fd mag to a variable from an input slider
        final String COMMAND = FD + -DEFAULT_FD_MAG;
        return makeJButtonCommand(super.getResources().getString(BACKWARD_COMMAND), COMMAND);
    }

    private JButton makeSubmitButton () {
        JButton button = new JButton(super.getResources().getString(SUBMIT_COMMAND));
        final Controller CONTROLLER = super.getController();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                String command = myConsole.getText();
                CONTROLLER.createRunInstruction(command);
                myConsole.setText("");
            }
        });
        return button;
    }

    private Border makeBorder (String panelName) {
        Border border;
        border = BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
             super.getResources().getString(panelName)),
                                                    BorderFactory.createEmptyBorder(BORDER_OFFSET,
                                                    BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET));
     
        return border;
    }

    private JButton makeJButtonCommand (String name, final String command) {
        JButton button = new JButton(name);
        final Controller CONTROLLER = super.getController();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                CONTROLLER.createRunInstruction(command);
                myConsole.setText("");
            }
        });
        return button;
    }
}
