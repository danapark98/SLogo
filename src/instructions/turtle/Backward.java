package instructions.turtle;

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

    private static final long serialVersionUID = 443555056700673405L;

    @Override
    public int getMagnitude(Model model) {
        return -nextOperand().execute(model);
    }
}
