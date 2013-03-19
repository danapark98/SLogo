package instructions.turtle;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

/**
 * Queries the turtle for the index of the current color of the pen. <br>
 * <br>
 * <u> Examples:</u> <br>
 * pencolor ---> returns 0, if the turtle is using the default color <br>
 * pc ---> returns 1, if the turtle is using the color at index 1 <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class PenColor extends BaseInstruction {

    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        // TODO Auto-generated method stub
        return 0;
    }

}
