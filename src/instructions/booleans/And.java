package instructions.booleans;

import exceptions.IllegalInstructionException;
import simulation.Model;


/**
 * Represents the logical and operation on two values.
 * Examples:
 * and 1 0 --> 0
 * and 1 1 --> 1
 * 
 * @author Scott Valentine
 * 
 */
public class And extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 3595351121913558476L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    
    public And() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public boolean executeBoolean (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) == 1 &&
               nextOperand().execute(model) == 1;
    }
}
