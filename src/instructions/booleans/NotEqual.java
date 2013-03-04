package instructions.booleans;

import exceptions.IllegalInstructionException;
import simulation.Model;

/**
 * Represents the instruction for !=, and returns 0 if the two arguments are
 * equal, otherwise returns 1. <br>
 * <br>
 * <u>Example:</u> <br>
 * notequal? 8 8 ---> 0 <br>
 * notequalp 9 10 ----> 1
 * 
 * 
 * @author Scott Valentine
 * 
 */
public class NotEqual extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -3010226380769115508L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public boolean executeBoolean (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) != nextOperand().execute(model);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
