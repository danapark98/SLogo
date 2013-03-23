package instructions.extra;

import drawing.lines.LineBuilder;
import drawing.lines.RainbowLine;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;

/**
 * Instruction that changes the style of the line drawn by the turtle to a rainbow.
 * <br>
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
    public Rainbow() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        LineBuilder lb = model.getPalette().getLineStyle(RainbowLine.RAINBOW_INDEX);

        model.getTurtle().getPen().changeLineStyle(lb);
        return RainbowLine.RAINBOW_INDEX;
    }

}
