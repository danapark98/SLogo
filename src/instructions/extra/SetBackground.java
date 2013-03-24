package instructions.extra;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

/**
 * Instruction to set the background color image to a default in the Palette
 * 
 * @author Ellango
 *
 */
public class SetBackground extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1480866910214941880L;
    private static final int NUMBER_OF_ARGUMENTS = 1;
    
    /**
     * Initializes a instruction to set the background image
     */
    public SetBackground () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int index = nextOperand().execute(model);
        model.getBackground().switchColorImage(index);
        return index;
    }

}
