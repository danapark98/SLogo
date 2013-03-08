package instructions.turtle;

import instructions.BaseInstruction;
import java.awt.Color;
import simulation.Model;
import simulation.Turtle;


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
    
    public PenUp() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) {
        Turtle turtle = model.getTurtle();
        // set 0 for alpha value so that it is invisible.
        turtle.changePen(new Color(0, 0, 0, 0));
        return 0;
    }

}
