package view;

import control.Controller;
import control.Controller.SaveOption;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.event.MenuListener;


/**
 * An object to a create a FileMenuBar
 * 
 * @author srwareham
 * @author Yoshi
 * 
 */
public class ViewFileMenu {
    private static final String AUTOSAVE = "AutoSave";
    private static final String MANUALSAVE = "ManualSave";
    private static final String INSTRUCTION_HELP_HTML = "instruction_help.html";
    private static final String RESOURCE_DIR = "/src/resources/";
    private static final String USER_DIR = "user.dir";
    private static final String HELP = "Help";
    private static final String LOAD = "LoadCommand";
    private static final String FILE = "FileMenu";
    private static final String QUIT = "QuitCommand";
    private static final String NEW = "NewCommand";
    private static final String SAVE = "SaveCommand";
    private static final String COLOR = "ColorMenu";
    private static final String CHANGEPENCOLOR = "ChangePenColor";

    private View myView;
    
    /**
     * Creates a new object to create a FileMenuBar for the View
     * 
     * @param view The standard View.
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
        JMenu result = new JMenu(getResourceLocation(FILE));
        result.add(makeMenuBarNew());
        result.add(makeMenuBarOpen());
        // result.add(makeMenuBarSave());
        result.add(new JSeparator());
        result.add(makeMenuBarHelp());
        result.add(makeSaveMenu());
        result.add(new JSeparator());
        result.add(makeMenuBarQuit());
        return result;
    }
    
    protected JMenu makeColorMenu () {
        JMenu result = new JMenu(getResourceLocation(COLOR));
        
        ActionListener acl = new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                @SuppressWarnings("unused")
                ColorChooser c = new ColorChooser(myView);
            }
        };
        
        JMenuItem item = makeMenuItem(CHANGEPENCOLOR, 'P', acl);
        result.add(item);
        return result;
    }
    
    private JMenuItem makeMenuBarNew () {
        ActionListener acl = new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                @SuppressWarnings("unused")
                View newView = new SLogoView(myView.getTitle(), myView.getLanguage());
            }
        };
        return makeMenuItem(NEW, NEW.charAt(0), acl);
    }
    
    private JMenuItem makeMenuBarOpen () {
        ActionListener acl = new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = getViewChooser().showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        InputStream in = new FileInputStream(getViewChooser().getSelectedFile());
                        getViewController().loadState(in);
                    }
                }
                catch (IOException io) {
                    // This should never occur because the picks a file
                    viewDisplayText(io.toString());
                }
            }
        };
        return makeMenuItem(LOAD, LOAD.charAt(0), acl);
    }
    
    private JMenuItem makeMenuBarAutoSave (final SaveOption status) {
        ActionListener acl = new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                myView.getController().setSaveOption(status);
            }
        };
        return makeMenuItem(getSaveOptionName(status), null, acl);
    }
    
    // TODO: do you guys know how to put this as a method in the enum object?
    private String getSaveOptionName (SaveOption input) {
        String s = (input.equals(SaveOption.AUTO)) ? AUTOSAVE : MANUALSAVE;
        return s;
    }
    
    private JMenuItem makeMenuBarSave () {
        ActionListener acl = new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                try {
                    int response = getViewChooser().showSaveDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        OutputStream o =
                                new FileOutputStream(getViewChooser().getSelectedFile());
                        getViewController().saveState(o);
                    }
                }
                catch (IOException io) {
                    // This should never occur because the makes a file
                    viewDisplayText(io.toString());
                }
            }
        };
        return makeMenuItem(SAVE, SAVE.charAt(0), acl);
    }
    
    private JMenuItem makeMenuBarHelp () {
        ActionListener acl = new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                Desktop hi = Desktop.getDesktop();
                String here = System.getProperty(USER_DIR);
                try {
                    File file = new File(here + RESOURCE_DIR + INSTRUCTION_HELP_HTML);
                    hi.open(file);
                }
                catch (IOException e1) {
                    viewDisplayText(e1.toString());
                }
            }
        };
        return makeMenuItem(HELP, HELP.charAt(0), acl);
    }
    
    private JMenu makeSaveMenu () {
        JMenu saveOptions = new JMenu("Save Options");
        saveOptions.add(makeMenuBarSave());
        saveOptions.add(new JSeparator());
        saveOptions.add(makeMenuBarAutoSave(SaveOption.AUTO));
        saveOptions.add(makeMenuBarAutoSave(SaveOption.MANUAL));
        return saveOptions;
    }
    
    private JFileChooser getViewChooser () {
        return myView.getChooser();
    }
    
    private String getResourceLocation (String input) {
        return myView.getResources().getString(input);
        
    }
    
    private Controller getViewController () {
        return myView.getController();
    }
    
    private void viewDisplayText (String input) {
        myView.displayText(input);
    }
    
    private JMenuItem makeMenuBarQuit () {
        ActionListener acl = new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                System.exit(0);
            }
        };
        return makeMenuItem(QUIT, QUIT.charAt(0), acl);
    }
    
    private JMenuItem makeMenuItem (String name, Character key, ActionListener a) {
        
        JMenuItem newItem = new JMenuItem(getResourceLocation(name));
        if (key != null) {
            newItem.setAccelerator(
                    KeyStroke.getKeyStroke(KeyEvent.getExtendedKeyCodeForChar(key),
                                           Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        }
        newItem.addActionListener(a);
        return newItem;
        
    }
    
}
