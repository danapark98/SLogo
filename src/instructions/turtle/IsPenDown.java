package instructions.turtle;

import instructions.booleans.BooleanInstruction;
import simulation.Model;


/**
 * Represents a boolean of whether or not the pen of the turtle is currently
 * drawing as an instruction. Takes zero arguments and returns 1 if the pen is
 * down (drawing), or 0 otherwise. <br>
 * <br>
 * <u> Examples:</u> <br>
 * pendown? ---> if pen is down returns 1, 0 otherwise <br>
 * pendownp ---> if pen is down returns 1, 0 otherwise
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class IsPenDown extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 5215411259109944598L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Initializes a "Is the Turtle's pen down?" boolean instruction.
     */
    public IsPenDown() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public boolean executeBoolean(Model model) {
        return model.getTurtle().getPen().penColor().getAlpha() != 0;
    }
}
