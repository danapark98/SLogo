package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;

/**
 * Represents any instruction that changes the heading of the turtle. 
 * These include the instructions:
 * <ul>
 * <li> Left
 * <li> Right
 * <li> SetHeading
 * <li> etc.
 * </ul>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public abstract class Rotate extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -8001794506937082081L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int rotation = getRotation(model);        
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() + rotation);
        return nextOperand().execute(model);
    }

    protected abstract int getRotation (Model model) throws IllegalInstructionException;

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
