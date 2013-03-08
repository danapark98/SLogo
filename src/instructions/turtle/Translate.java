package instructions.turtle;


import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Vector;

/**
 * Represents any instruction that shifts the turtle. These include the instructions:
 * <ul>
 * <li> Forward
 * <li> Backward
 * <li> SetPostion
 * <li> Home
 * <li> etc.
 * </ul>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public abstract class Translate extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -8721896768117828518L;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int magnitude = getMagnitude(model);
        Turtle turtle = model.getTurtle();
        turtle.translate(new Vector(turtle.getAngle(), magnitude));
        return nextOperand().execute(model);
    }

    protected abstract int getMagnitude (Model model) throws IllegalInstructionException;
}