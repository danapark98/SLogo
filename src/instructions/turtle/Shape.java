package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Returns the index of the current shape
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 * 
 */
public class Shape extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -5707107616926115718L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Initializes a shape instruction
     */
    public Shape () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        return model.getTurtle().getImageIndex();
    }

}
