package instructions.turtle;

import drawing.PaletteFactory;
import instructions.booleans.BooleanInstruction;
import simulation.Model;


/**
 * Represents a boolean of whether or not the turtle is currently
 * showing as an instruction. Takes zero arguments and returns 1 if the turtle is
 * showing, or 0 otherwise. <br>
 * <br>
 * <u> Examples:</u> <br>
 * showing? ---> if turtle is showing returns 1, 0 otherwise <br>
 * showingp ---> if turtle is showing returns 1, 0 otherwise
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class IsShowing extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -3384186474769687332L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Initializes a "Is the Turtle Showing?" boolean instruction.
     */
    public IsShowing() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public boolean executeBoolean (Model model) {
        return model.getPalette().getCurrentImageIndex() != PaletteFactory.DEFAULT_CLEAR_INDEX;
    }

}
