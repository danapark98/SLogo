package instructions.math;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents a subtraction math operation as an instruction. <br>
 * <br>
 * <u> Examples:</u> <br>
 * difference 10 10 ---> 0 <br>
 * difference -4 -8 ---> -12
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class Difference extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 2086351414691071818L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * Initializes a subtraction operation instruction.
     */
    public Difference () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) - nextOperand().execute(model);
    }
}
