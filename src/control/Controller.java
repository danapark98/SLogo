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
	
	public enum SaveOption {
		AUTO, NON_AUTO;
	}
    
    /** String that indicated a return result from a user input */
    public static final String PRINT_INDICATOR = ">> ";
    private static final String FILE_SAVING_ERROR_MESSAGE = "Error: Could not save to file.";
    private static final String FILE_LOADING_ERROR_MESSAGE = "Error: File format is not compatible";
    private Model myModel;
    private View myView;
    private Parser myParser;
    private Environment myEnvironment;
    
    private SaveOption saveOption;
    
    public static final String USER_DIR = "user.dir";

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
        myParser = new Parser(myEnvironment);
        saveOption = SaveOption.NON_AUTO;
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
            if(saveOption == SaveOption.AUTO) {
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
     * The Exception will be thrown when the Environment fails to load its state 
     * from the provided InputStream. This could be because the InputStream 
     * cannot be read from, because the SLogo program requires loading only 
     * from sources that were saved previously by the same program.
     * 
     */
    public void loadState (InputStream is) {        
        ObjectInput in;
        try {
            in = new ObjectInputStream(is);
            myEnvironment = (Environment) in.readObject();
        }
        catch (ClassNotFoundException | IOException e) {
            myModel.informView(FILE_LOADING_ERROR_MESSAGE);
        }
    }

    /**
     * Saves the instructions, variables and palette that are available 
     * to the user.
     * 
     * @param os is the stream to write to.
     * 
     * The Exception is thrown when the Environment fails to save its
     * current state. This might happen if the OutputStream provided cannot be
     * written to. The end-user should then try to provide a different way of
     * saving the state depending on the channel he or she wants to save to.
     * (e.g. picking a different file)
     */
    public void saveState (OutputStream os) {

        ObjectOutput out;
        try {
            out = new ObjectOutputStream(os);
            out.writeObject(myEnvironment);
        }
        catch (IOException e) {
            myModel.informView(FILE_SAVING_ERROR_MESSAGE);
        }
    }

    /**
     * Clears the lines in the model and resets the turtle to the center of
     * the screen
     */
    public void clear () {
        new ClearScreen().execute(myModel);
    }
    
    private void autoSave() {
    	File file = new File(System.getProperty(USER_DIR) + "/autoSLogo");   
    	try {
			OutputStream os = new FileOutputStream(file);
			saveState(os);
		} catch (FileNotFoundException e) {
			myModel.informView(e.toString());
		}
    }
    
    public void setSaveOption(SaveOption save) {
    	saveOption = save;
    }

}
