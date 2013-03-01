package instructions.turtle;

import simulation.Model;

/**
 * Instruction for the turtle to rotate to the right
 * 
 * @author Scott Valentine
 * 
 */
public class Right extends Rotate {

    /** keyword that calls this instruction */
    public static final String KEYWORD = "right";
    public static final String KEYWORD0 = "rt";

    @Override
    public int getRotation(Model model) {
        return nextOperand().execute(model);
    }

}
