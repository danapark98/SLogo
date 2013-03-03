package instructions.turtle;

import simulation.Model;

/**
 * Instruction for the turtle to rotate to the left
 * 
 * @author Scott Valentine
 * 
 */
public class Left extends Rotate {

    /**
     * Left unique serial number
     */
    private static final long serialVersionUID = -6371140632719070106L;

    @Override
    public int getRotation (Model model) {
        return -1*nextOperand().execute(model);
    }
}
