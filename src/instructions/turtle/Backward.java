package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Vector;


/**
 * Represents the backward instruction.
 * 
 * i.e.
 * bk 50 moves the turtle 50 pixels in the opposite direction than it is facing.
 * 
 * @author Scott Valentine
 * 
 */
public class Backward extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7749994394524662088L;
    private static final int NUMBER_OF_ARGUMENTS = 1;


    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int magnitude = nextOperand().execute(model);
        Turtle turtle = model.getTurtle();
        turtle.translate(new Vector(turtle.getAngle(), -1*magnitude));
        return magnitude;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
