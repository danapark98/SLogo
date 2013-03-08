package simulation;

import instructions.BaseInstruction;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import control.Environment;
import exceptions.IllegalInstructionException;


/**
 * Represents the simulation of the drawings on the screen. The model holds the drawer (the turtle)
 * and all of the lines that have been drawn.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class Model implements DisplayEditor {
    private Turtle myTurtle;
    private Collection<Line> myLines;
    private Environment myEnvironment;

    /**
     * Instantiates a model with a turtle and a collection of lines.
     */
    public Model () {
        myTurtle = new Turtle(this);
        myLines = new ArrayList<Line>();
    }

    /**
     * Updates all of the elements of model.
     * 
     * @param elapsedTime is the time since the last update.
     * @param bounds is the current bounds of the canvas in the view. (This is the area where the
     *        lines and turtle are displayed).
     */
    public void update (double elapsedTime, Dimension bounds) {
        myTurtle.update(elapsedTime, bounds);
    }

    /**
     * Paints all current elements (turtles and lines) of the model.
     * 
     * @param pen is the graphic that is used to paint lins and turtles.
     */
    public void paint (Graphics2D pen) {
        myTurtle.paint(pen);
        for (Line line : myLines) {
            line.paint(pen);
        }
    }

    @Override
    public void addLine (Line line) {
        myLines.add(line);
    }

    /**
     * Clears all lines from the model.
     */
    public void clearLines () {
        myLines.clear();
    }

    /**
     * Gives the current active turtle in the model.
     * 
     * @return The active turtle in the model.
     */
    public Turtle getTurtle () {
        return myTurtle;
    }

    /**
     * Sets the environment that contains necessary instruction information.
     * 
     * @param environment is environment that contains all relevant instruction information.
     */
    public void setEnvironment (Environment environment) {
        myEnvironment = environment;
    }

    /**
     * Adds a new user defined instruction to the environment.
     * 
     * @param keyword associated with the instruction for future calls
     * @param instruction to be added to the environment.
     */
    public void addInstruction (String keyword, BaseInstruction instruction) {
        myEnvironment.addUserDefinedInstruction(keyword, instruction);
    }

    /**
     * Gives the Instruction associated with the passed keyword.
     * 
     * @param commandName - the keyword for the instruction
     * @return The Instruction associated with the keyword
     * @throws IllegalInstructionException This occurs when the keyword is not
     *         found in the environment.
     */
    public BaseInstruction getVariableInstruction (String name) throws IllegalInstructionException {
        return myEnvironment.systemInstructionSkeleton(name);
    }

    /**
     * Removes a variable from the environment. This is done in the context
     * of local variables, in which they should only be visible during a
     * function call.
     * 
     * @param variableName
     */
    public void removeVariable (String variableName) {
        myEnvironment.removeInstruction(variableName);
    }
}
