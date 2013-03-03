package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Vector;


/**
 * represents the forward instruction
 * 
 * i.e.
 * fd 50 moves the turtle 50 pixels in its current direction
 * 
 * @author Scott Valentine
 * 
 */
public class Forward extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 443555056700673405L;
    private static final int NUMBER_OF_ARGUMENTS = 1;


    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int magnitude = nextOperand().execute(model);
        Turtle turtle = model.getTurtle();
        turtle.translate(new Vector(turtle.getAngle(), magnitude));
        return magnitude;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
    
}
