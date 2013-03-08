package instructions.booleans;

import exceptions.IllegalInstructionException;
import simulation.Model;


/**
 * Represents the instruction for logical or, and returns 1 if both arguments
 * are equal to 1, otherwise returns 0. <br>
 * <br>
 * <u>Example:</u> <br>
 * or 8 8 ---> 0 <br>
 * or 1 1 ---> 1 <br>
 * or 10 1 ---> 0
 * 
 * 
 * @author Scott Valentine
 * 
 */
public class Or extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 6607652502391503222L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    
    public Or () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public boolean executeBoolean(Model model)
                                              throws IllegalInstructionException {
        return nextOperand().execute(model) == 1 ||
               nextOperand().execute(model) == 1;
    }

}
