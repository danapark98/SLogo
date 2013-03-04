package instructions.math;

import exceptions.IllegalInstructionException;
import simulation.Model;
import instructions.BaseInstruction;

/**
 * Represents a subtraction math operation as an instruction.
 * <br><br>
 * <u> Examples:</u>
 * <br> difference 10 10 ---> 0
 * <br> difference -4 -8 ---> -12
 * 
 * @author Scott Valentine
 *
 */
public class Difference extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 2086351414691071818L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) - nextOperand().execute(model);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}