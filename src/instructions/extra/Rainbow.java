package instructions.extra;

import instructions.BaseInstruction;
import simulation.Model;
import drawing.lines.RainbowLine;
import exceptions.IllegalInstructionException;


/**
 * Instruction that changes the style of the line drawn by the turtle to a rainbow. <br>
 * <u> Examples:</u> <br>
 * rainbow ---> all lines are now rainbow colored :D <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Rainbow extends BaseInstruction {

    /**
     * Auto-generated ID
     */
    private static final long serialVersionUID = 1L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Initializes a create Rainbow Line instruction.
     */
    public Rainbow () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        model.getTurtle().getPen().changeLineStyle(RainbowLine.RAINBOW_INDEX);
        return RainbowLine.RAINBOW_INDEX;
    }

}
