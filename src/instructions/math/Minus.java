package instructions.math;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

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
