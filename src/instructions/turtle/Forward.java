package instructions.turtle;

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

    private static final long serialVersionUID = 443555056700673405L;

    @Override
    public int getMagnitude(Model model) {
        return nextOperand().execute(model);
    }
    
}
