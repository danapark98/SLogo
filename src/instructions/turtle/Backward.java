package instructions.turtle;

import exceptions.IllegalInstructionException;
import simulation.Model;


/**
 * Represents the backward instruction.
 * 
 * i.e.
 * bk 50 moves the turtle 50 pixels in the opposite direction than it is facing.
 * 
 * @author Scott Valentine
 * 
 */
public class Backward extends Translate {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7749994394524662088L;

    @Override
    protected int getMagnitude (Model model) throws IllegalInstructionException {
        return -1*nextOperand().execute(model);
    }
}
