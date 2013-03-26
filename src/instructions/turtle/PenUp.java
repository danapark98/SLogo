package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;


/**
 * Represents setting the turtle to not draw (putting the pen up) as an
 * instruction. Takes zero
 * arguments and always returns 0. <br>
 * <br>
 * <u> Examples:</u> <br>
 * penup ---> returns 0, and turtle can not draw lines <br>
 * pu ---> returns 0, and turtle can not draw lines <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class PenUp extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -45623041636717507L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Initializes a 'put pen up' a.k.a 'do not draw' instruction.
     */
    public PenUp () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) {
        model.getTurtle().getPen().penOff();
        return 0;
    }

}
