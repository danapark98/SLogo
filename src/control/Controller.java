package control;

import instructions.Instruction;
import java.io.InputStream;
import java.io.OutputStream;
import simulation.Model;
import view.SLogoView;
import exceptions.FileSavingException;
import exceptions.IllegalInstructionException;
import exceptions.IncorrectFileFormatException;


/**
 * Passes instructions to the parser and executes those instructions on the model.
 * Saves and loads the state of the model
 * 
 * @author Ryan Fishel
 * 
 */
public class Controller {

    private Model myModel;
    private SLogoView myView;
    private Parser myParser;
    private Environment myEnvironment;

    /**
     * This creates a new controller with a model, a view, an environment,
     * and a parser.
     * 
     * @param model is a Model that represents the state of the simulation
     * @param view is a View that represents what will be displayed
     */
    public Controller (Model model, SLogoView view) {
        myModel = model;
        myView = view;
        myEnvironment = new Environment();
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
        myView.displayText(">> " + s);
        try {
            Instruction instruction = myParser.generateInstruction(s);
            myView.displayText("" + instruction.execute(myModel));
        }
        catch (IllegalInstructionException e) {
            myView.displayText(e.toString());
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
            myView.displayText(e.getMessage());
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
            myView.displayText(e.getMessage());
        }

    }

    /**
     * Clears the lines in the model and resets the turtle to the center of
     * the screen
     */
    public void clear () {
        myModel.clearLines();
        myModel.getTurtle().resetTurtle();
    }

}
