package control;

import instructions.Instruction;
import instructions.turtle.ClearScreen;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;
import simulation.Model;
import view.View;
import exceptions.FileSavingException;
import exceptions.IllegalInstructionException;
import exceptions.IncorrectFileFormatException;


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
    private ResourceBundle myResource;

    /**
     * This creates a new controller with a model, a view, an environment,
     * and a parser.
     * 
     * @param model is a Model that represents the state of the simulation
     * @param view is a View that represents what will be displayed
     */
    public Controller (Model model, View view) {
        myModel = model;
        myView = view;
        myResource = view.getResources();
        myEnvironment = new Environment(myResource);
        model.setEnvironment(myEnvironment);
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
            informView(instruction.execute(myModel) + "");
        }
        catch (IllegalInstructionException e) {
            informView(e.toString());
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
            informView(e.toString());
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
            informView(e.toString());
        }

    }

    /**
     * Clears the lines in the model and resets the turtle to the center of
     * the screen
     */
    public void clear () {
        new ClearScreen().execute(myModel);
    }

    /**
     * Calls the view method to display the result of the command, or an error
     * message back to the user. Appends an indicator string to the beginning
     * to differentiate the result from commands issued by the user.
     * 
     * @param s return message
     */
    private void informView (String s) {
        myView.displayText(PRINT_INDICATOR + s);
    }
}
