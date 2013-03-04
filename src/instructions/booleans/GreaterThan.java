package instructions.booleans;

import exceptions.IllegalInstructionException;
import simulation.Model;

/**
 * Represents the instruction for >, and returns 1 if the first argument is
 * greater than the second, otherwise returns 0. <br>
 * <br>
 * <u>Example:</u> <br>
 * greater? 8 8 ---> 0 <br>
 * greaterp 9 10 ---> 0 <br>
 * greaterp 10 1 ---> 1
 * 
 * 
 * @author Scott Valentine
 * 
 */
public class GreaterThan extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -5228113262586227488L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public boolean executeBoolean(Model model)
                                              throws IllegalInstructionException {
        return nextOperand().execute(model) > nextOperand().execute(model);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
