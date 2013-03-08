package instructions.turtle;

import exceptions.IllegalInstructionException;
import simulation.Model;


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
public class Backward extends Translate {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7749994394524662088L;
    private static final int NUMBER_OF_ARGUMENTS = 1;
    
    /**
     * Initializes a backwards translation instruction.
     */
    public Backward () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    protected int getMagnitude (Model model) throws IllegalInstructionException {
        return -1 * nextOperand().execute(model);
    }
}

