package instructions.booleans;

import exceptions.IllegalInstructionException;
import simulation.Model;

/**
 * Represents the instruction for not, and returns 1 if argument is
 * 0, otherwise returns 0. <br>
 * <br>
 * <u>Example:</u> <br>
 * not 1 ---> 0 <br>
 * not 0 ---> 1 <br>
 * not 190 ---> 0
 * 
 * 
 * @author Scott Valentine
 * 
 */
public class Not extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1645427597906420824L;
    private static final int NUMBER_OF_ARGUMENTS = 1;
    
    public Not() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public boolean executeBoolean (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) == 0;
    }
}
