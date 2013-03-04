package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;
import util.Vector;


/**
 * Represents forward movement of the turtle as an instruction. Takes one
 * argument that is the distance (in pixels) the turtle travels<br>
 * <br>
 * <u> Examples:</u> <br>
 * forward 10 ---> turtle shifts forward 10 pixels <br>
 * forward -4 ---> turtle shifts backward 4 (forward -4) pixels <br>
 * fd 5---> turtle shifts forward 5 pixels
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
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
