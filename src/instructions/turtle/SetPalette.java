package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Sets the color at a given indexed to a custom value. <br>
 * <u> Examples:</u> <br>
 * setpallete 0 10 10 10 ---> Sets color at index 0 to 10,10,10 in rgd. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class SetPalette extends BaseInstruction {

    /**
     * Auto-generated ID
     */
    private static final long serialVersionUID = -4016715369982980312L;
    private static final int NUMBER_OF_ARGUMENTS = 4;

    /**
     * Default Constructor for base instruction types
     */
    public SetPalette () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int index = nextOperand().execute(model);
        int r = nextOperand().execute(model);
        int g = nextOperand().execute(model);
        int b = nextOperand().execute(model);
        model.getPalette().addColor(index, r, g, b);
        return index;
    }

}
