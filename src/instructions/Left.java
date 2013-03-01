package instructions;

import simulation.Model;

/**
 * Instruction for the turtle to rotate to the left
 * 
 * @author Scott Valentine
 * 
 */
public class Left extends Rotate {

    /** keyword that calls this instruction */
    public static final String KEYWORD = "left";
    public static final String KEYWORD1 = "lt";

    @Override
    public int getRotation (Model model) {
        return -1*nextOperand().execute(model);
    }
}
