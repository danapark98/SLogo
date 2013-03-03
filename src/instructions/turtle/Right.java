package instructions.turtle;

import simulation.Model;

/**
 * Instruction for the turtle to rotate to the right
 * 
 * @author Scott Valentine
 * 
 */
public class Right extends Rotate {

    /**
     * Generated serial id
     */
    private static final long serialVersionUID = -592793134932727653L;

    @Override
    public int getRotation(Model model) {
        return nextOperand().execute(model);
    }

}
