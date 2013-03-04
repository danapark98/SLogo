package instructions.math;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;

/**
 * Represents a negation of a value as an instruction.
 * <br><br>
 * <u> Examples:</u>
 * <br> minus 10 ---> -10
 * <br> minus -4 ---> 4
 * 
 * @author Scott Valentine
 *
 */
public class Minus extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 2286830618398845175L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        return -1*nextOperand().execute(model);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
