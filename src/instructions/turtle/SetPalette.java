package instructions.turtle;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

/**
 * Sets the color at a given indexed to a custom value.
 * <br>
 * <u> Examples:</u> <br>
 * setpallete 0 10 10 10 ---> Sets color at index 0 to 10,10,10 in rgd. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class SetPalette extends BaseInstruction {

    private static final int NUMBER_OF_ARGUMENTS = 4;

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        // TODO Auto-generated method stub
        return 0;
    }

}
