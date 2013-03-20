package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;


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

    /**
     * Auto-generated ID.
     */
    private static final long serialVersionUID = -6331249525599036720L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /** 
     * Default constructor for Base Instruction types.
     */
    public PenColor() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        return model.getPalette().currentColorIndex();
    }

}
