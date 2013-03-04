package instructions.turtle;

import exceptions.IllegalInstructionException;
import simulation.Model;


/**
 * represents the forward instruction
 * 
 * i.e.
 * fd 50 moves the turtle 50 pixels in its current direction
 * 
 * @author Scott Valentine
 * 
 */
public class Forward extends Translate {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 443555056700673405L;

    @Override
    protected int getMagnitude (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model);
    }

}
