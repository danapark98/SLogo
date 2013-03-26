package control;

import exceptions.IllegalInstructionException;
import instructions.Instruction;
import instructions.turtle.ClearScreen;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;
import simulation.Model;
import view.View;


/**
 * Passes instructions to the parser and executes those instructions on the model.
 * Saves and loads the state of the model
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Controller {


    /** String that indicated a return result from a user input */
    public static final String PRINT_INDICATOR = "printIndicator";
    /** String indicating the current user directory*/
    public static final String USER_DIR = "user.dir";
    private static final String FILE_SAVING_ERROR_MESSAGE = "fileSaveErrorMessage";
    private static final String FILE_LOADING_ERROR_MESSAGE = "fileLoadErrorMessage";
    
    private static final String AUTOSAVE_FILENAME = "/autoSLogo.bk";
    
    private Model myModel;
    private View myView;
    private Parser myParser;
    private Environment myEnvironment;
    private SaveOption mySaveOption;
    private ResourceBundle myResources;

    /**
     * This creates a new controller with a model, a view, an environment,
     * and a parser.
     * 
     * @param model is a Model that represents the state of the simulation
     * @param view is a View that represents what will be displayed
     */
    public Controller (Model model, View view) {
        myView = view;
        myModel = model;
        myModel.setView(view);
        myEnvironment = myModel.initialize();
        myResources = myEnvironment.getResources();
        myParser = new Parser(myEnvironment);
        mySaveOption = SaveOption.MANUAL;
    }

    /**
     * This creates an instruction from a given command from the view
     * by having the parser generate an instruction. It then runs the
     * instruction that was created.
     * 
     * @param s is a String that is passed from the view that represents the
     *        command that the user wants to run
     */
    public void createRunInstruction (String s) {
        myView.displayText(s);
        try {
            Instruction instruction = myParser.generateInstruction(s);
            myModel.informView(instruction.execute(myModel) + "");
            if (mySaveOption == SaveOption.AUTO) {
                autoSave();
            }
        }
        catch (IllegalInstructionException e) {
            myModel.informView(e.toString());
        }
    }

    /**
     * This loads in the state of instructions, variables, and palette
     * from a source that was saved by saveState().
     * 
     * @param is the source to read from
     * 
     *        The Exception will be thrown when the Environment fails to load its state
     *        from the provided InputStream. This could be because the InputStream
     *        cannot be read from, because the SLogo program requires loading only
     *        from sources that were saved previously by the same program.
     * 
     */
    public void loadState (InputStream is) {
        ObjectInput in;
        try {
            in = new ObjectInputStream(is);
            myEnvironment.load(in);
        }
        catch (ClassNotFoundException | ClassCastException | IOException e) {
            myModel.informView(myResources.getString(FILE_LOADING_ERROR_MESSAGE));
        }
    }

    /**
     * Saves the instructions, variables and palette that are available
     * to the user.
     * 
     * @param os is the stream to write to.
     * 
     *        The Exception is thrown when the Environment fails to save its
     *        current state. This might happen if the OutputStream provided cannot be
     *        written to. The end-user should then try to provide a different way of
     *        saving the state depending on the channel he or she wants to save to.
     *        (e.g. picking a different file)
     */
    public void saveState (OutputStream os) {
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(os);
            myEnvironment.save(out);
        }
        catch (IOException e) {
            myModel.informView(myResources.getString(FILE_SAVING_ERROR_MESSAGE));
        }
    }

    /**
     * Autosaves the environment to the file autoSLogo in the user's directory
     */
    private void autoSave () {
        File file = new File(System.getProperty(USER_DIR) + AUTOSAVE_FILENAME);
        try {
            OutputStream os = new FileOutputStream(file);
            saveState(os);
        }
        catch (FileNotFoundException e) {
            myModel.informView(myResources.getString(FILE_SAVING_ERROR_MESSAGE));
        }
    }

    /**
     * The view can set the Controller to save automatically or when the user
     * chooses to by providing a SaveOption
     * 
     * @param option is the SaveOption to set
     */
    public void setSaveOption (SaveOption option) {
        mySaveOption = option;
    }

    /**
     * Clears the lines in the model and resets the turtle to the center of
     * the screen
     */
    public void clear () {
        new ClearScreen().execute(myModel);
    }
    
    /**
     * A type representing whether the Controller should save the environment
     * automatically or only when the user specifies to
     *
     */
    public enum SaveOption {
        /**
         * Auto-save protocol
         */
        AUTO {
            @Override
            public String getResourceName() {
                return AUTO_NAME;
            }
        },
        /**
         * Manual save protocol
         */
        MANUAL {
            @Override
            public String getResourceName() {
                return MANUAL_NAME;
            }
        };
        private static final String MANUAL_NAME = "ManualSave";
        private static final String AUTO_NAME = "AutoSave";

        /**
         * Gives the name of the resource
         * 
         * @return The name of the resource (save)
         */
        public abstract String getResourceName();
    }

}
