package instructions.turtle;

import exceptions.IllegalInstructionException;
import simulation.Model;

/**
 * Instruction for the turtle to rotate to the right
 * 
 * @author Scott Valentine
 * 
 */
public class Right extends Rotate {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -592793134932727653L;

    @Override
    protected int getRotation (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model);
    }

}
