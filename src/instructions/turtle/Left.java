package instructions.turtle;

import simulation.Model;
import exceptions.IllegalInstructionException;


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
public class Left extends Rotate {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -6371140632719070106L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    /**
     * Initializes a left rotation instruction.
     */
    public Left () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    protected int getRotation (Model model) throws IllegalInstructionException {
        return -1 * nextOperand().execute(model);
    }
}
