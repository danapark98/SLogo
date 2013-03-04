package instructions.turtle;

import instructions.BaseInstruction;
import java.awt.Color;
import simulation.Model;
import simulation.Turtle;


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

    @Override
    public int execute(Model model) {
        Turtle turtle = model.getTurtle();
        turtle.changePen(Color.BLACK);
        return 1;
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
