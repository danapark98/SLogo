package instructions.turtle;

import exceptions.IllegalInstructionException;
import simulation.Model;

/**
 * Instruction for the turtle to rotate to the left
 * 
 * @author Scott Valentine
 * 
 */
public class Left extends Rotate {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -6371140632719070106L;

    @Override
    protected int getRotation (Model model) throws IllegalInstructionException {
        return -1 * nextOperand().execute(model);
    }
}
