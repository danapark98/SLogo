package view;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import control.Controller;


/**
 * An object to a create a FileMenuBar
 * 
 * @author srwareham
 * 
 */
public class ViewFileMenu {
    private static final String OPEN = "OpenCommand";
    private static final String FILE = "FileMenu";
    private static final String QUIT = "QuitCommand";
    private static final String NEW = "NewCommand";
    private static final String SAVE = "SaveCommand";
    private View myView;

    /**
     * Creates a new object to create a FileMenuBar for the View
     * 
     * @param view 
     */
    public ViewFileMenu (View view) {
        myView = view;

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
        JMenu result = new JMenu(myView.getResources().getString(FILE));
        result.add(makeMenuBarNew());
        result.add(makeMenuBarOpen());
        result.add(makeMenuBarSave());
        result.add(new JSeparator());
        result.add(makeMenuBarQuit());
        return result;
    }

    private AbstractAction makeMenuBarNew () {
        return new AbstractAction(myView.getResources().getString(NEW)) {
            private static final long serialVersionUID = -6868831251083168422L;

            @Override
            public void actionPerformed (ActionEvent e) {
                @SuppressWarnings("unused")
                View newView = new SLogoView(myView.getTitle(), myView.getLanguage());
            }
        };
    }

    // TODO: refactor the menubar operator methods.
    private AbstractAction makeMenuBarOpen () {
        return new AbstractAction(myView.getResources().getString(OPEN)) {
            /**
             * 
             */
            private static final long serialVersionUID = -3471532304609267535L;

            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myView.getChooser().showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        InputStream in = new FileInputStream(myView.getChooser().getSelectedFile());
                        getViewController().loadState(in);
                    }
                }
                catch (IOException io) {
                    // This should never occur because the picks a file
                    viewDisplayText(io.toString());
                }
            }
        };
    }

    private AbstractAction makeMenuBarSave () {
        return new AbstractAction(myView.getResources().getString(SAVE)) {
            private static final long serialVersionUID = -686883125108316843L;

            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = myView.getChooser().showSaveDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        OutputStream o =
                                new FileOutputStream(myView.getChooser().getSelectedFile());
                        getViewController().saveState(o);
                    }
                }

                catch (IOException io) {
                    // This should never occur because the makes a file
                    viewDisplayText(io.toString());
                }
            }
        };
    }
    
    private Controller getViewController() {
        return myView.getController();
    }
    
    private void viewDisplayText (String input) {
        myView.displayText(input);
    }

    private AbstractAction makeMenuBarQuit () {
        return new AbstractAction(myView.getResources().getString(QUIT)) {
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
}
