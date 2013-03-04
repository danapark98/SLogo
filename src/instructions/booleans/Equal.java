package instructions.booleans;

import exceptions.IllegalInstructionException;
import simulation.Model;


/**
 * Represents the instruction for ==, and returns 1 if the two arguments are
 * equal, otherwise returns 0. <br>
 * <br>
 * <u>Example:</u> <br>
 * equal? 8 8 ---> 1 <br>
 * equalp 9 10 ----> 0
 * 
 * 
 * @author Scott Valentine
 * 
 */
public class Equal extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -9085755361792996511L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public boolean executeBoolean(Model model)
                                              throws IllegalInstructionException {
        return nextOperand().execute(model) == nextOperand().execute(model);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
