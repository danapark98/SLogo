package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;


/**
 * Represents setting the turtle to draw (putting the pen down) as an
 * instruction. Takes zero
 * arguments and always returns 1. <br>
 * <br>
 * <u> Examples:</u> <br>
 * pendown ---> returns 1, and turtle can draw lines <br>
 * pd ---> returns 1, and turtle can draw lines <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class PenDown extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 4039119828110366434L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a put pen down instruction.
     */
    public PenDown() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) {
        model.getTurtle().getPen().penOn();
        return 1;
    }
}
