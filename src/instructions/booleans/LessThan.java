package instructions.booleans;

import simulation.Model;
import exceptions.IllegalInstructionException;


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
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class LessThan extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -7617375173556456877L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

/**
     * Initializes a '<' instruction.
     */
    public LessThan () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public boolean executeBoolean (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) < nextOperand().execute(model);
    }
}
