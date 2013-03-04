package instructions.booleans;

import exceptions.IllegalInstructionException;
import simulation.Model;

/**
 * Represents the instruction for >, and returns 1 if the first argument is
 * less than the second, otherwise returns 0. <br>
 * <br>
 * <u>Example:</u> <br>
 * less? 8 8 ---> 0 <br>
 * lessp 9 10 ---> 1 <br>
 * less? 10 1 ---> 0
 * 
 * 
 * @author Scott Valentine
 * 
 */
public class LessThan extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -7617375173556456877L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public boolean executeBoolean (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) < nextOperand().execute(model);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
