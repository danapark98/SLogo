package control;

import exceptions.FileSavingException;
import exceptions.IllegalInstructionException;
import exceptions.IncorrectFileFormatException;
import instructions.Instruction;
import instructions.turtle.ClearScreen;
import java.io.InputStream;
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
    
    /** String that indicated a return result from a user input */
    public static final String PRINT_INDICATOR = ">> ";
    private Model myModel;
    private View myView;
    private Parser myParser;
    private Environment myEnvironment;

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
        }
        catch (IllegalInstructionException e) {
            myModel.informView(e.toString());
        }
    }

    /**
     * This loads in the state of instructions and variables from a source
     * that was saved by saveState().
     * 
     * @param is the source to read from
     */
    public void loadState (InputStream is) {
        try {
            myEnvironment.load(is);
        }
        catch (IncorrectFileFormatException e) {
            myModel.informView(e.toString());
        }
    }

    /**
     * Saves the instructions and variables that are available to the user.
     * 
     * @param os is the stream to write to.
     */
    public void saveState (OutputStream os) {

        try {
            myEnvironment.save(os);
        }
        catch (FileSavingException e) {
            myModel.informView(e.toString());
        }
    }

    /**
     * Clears the lines in the model and resets the turtle to the center of
     * the screen
     */
    public void clear () {
        new ClearScreen().execute(myModel);
    }

}
