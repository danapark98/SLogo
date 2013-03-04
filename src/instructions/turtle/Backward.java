package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Vector;


/**
 * Represents backward movement of the turtle as an instruction. Takes one
 * argument that is the distance (in pixels) the turtle travels<br>
 * <br>
 * <u> Examples:</u> <br>
 * back 10 ---> turtle shifts backwards 10 pixels <br>
 * bk -4 ---> turtle shifts forwards 4 (backwards -4) pixels <br>
 * bk 5---> turtle shifts backwards 5 pixels
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Backward extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7749994394524662088L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int magnitude = nextOperand().execute(model);
        Turtle turtle = model.getTurtle();
        turtle.translate(new Vector(turtle.getAngle(), -1 * magnitude));
        return magnitude;
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
