package instructions.turtle;

import exceptions.IllegalInstructionException;
import simulation.Model;

/**
 * Represents forward movement of the turtle as an instruction. Takes one
 * argument that is the distance (in pixels) the turtle travels<br>
 * <br>
 * <u> Examples:</u> <br>
 * forward 10 ---> turtle shifts forward 10 pixels <br>
 * forward -4 ---> turtle shifts backward 4 (forward -4) pixels <br>
 * fd 5---> turtle shifts forward 5 pixels
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
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

