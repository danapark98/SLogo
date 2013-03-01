package control;

import instructions.Instruction;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import simulation.Model;
import view.SLogoView;
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
     * by having the parser generate an instruction.  It then runs the 
     * instruction that was created.
     * @param s is a String that is passed from the view that represents the
     * command that the user wants to run
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
     * Saves the state of the workspace
     * @param fw
     */
    public void saveState (FileWriter fw) {
        try {
            new BufferedWriter(fw);
        }
        catch (Exception e) {
            myView.displayText(e.toString());
        }
    }
    
    /**
     * This loads the state of a saved workspace
     * @param f is a file that can be loaded to a workspace
     * @throws IncorrectFileFormatException which is thrown when a file
     * is not formatted correctly to be loaded into  an SLogo workspace
     */
    public void loadState (File f) throws IncorrectFileFormatException {
        try {
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                new Scanner(input.nextLine());
            }

            input.close();
        }
        catch (IllegalStateException e) {
            myView.displayText(e.toString());
            throw new IncorrectFileFormatException();

        }
        catch (FileNotFoundException e) {
            myView.displayText(e.toString());
            throw new IncorrectFileFormatException();

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
