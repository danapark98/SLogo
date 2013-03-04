package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;


/**
 * Represents a counterclockwise rotation of the turtle as an instruction. Takes
 * one
 * argument that is the angle (in degrees) the turtle rotates. The instruction
 * returns this number.<br>
 * <br>
 * <u> Examples:</u> <br>
 * left 10 ---> turtle rotates to the left 10 degrees. <br>
 * left -90 ---> turtle rotates to the right 90 degrees (to the left -90 degrees). <br>
 * lt 5---> turtle rotates lefts 5 degrees.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Left extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -6371140632719070106L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int rotation = nextOperand().execute(model);
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() - rotation);
        return rotation;
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
