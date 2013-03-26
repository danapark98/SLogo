package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import exceptions.IllegalInstructionException;


/**
 * Represents any instruction that changes the heading of the turtle.
 * These include the instructions:
 * <ul>
 * <li>Left
 * <li>Right
 * <li>SetHeading
 * <li>etc.
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

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int rotation = getRotation(model);
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() + rotation);
        return nextOperand().execute(model);
    }

    /**
     * Returns the angle of rotation for this instruction to rotate the turtle.
     * 
     * @param model is the model on which this instruction acts.
     * @return The magnitude of the angle of rotation.
     * @throws IllegalInstructionException This occurs when the parameters do not match expected.
     */
    protected abstract int getRotation (Model model) throws IllegalInstructionException;
}
