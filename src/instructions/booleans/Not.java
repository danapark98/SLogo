package instructions.booleans;

import simulation.Model;
import exceptions.IllegalInstructionException;

public class Not extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1645427597906420824L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public boolean executeBoolean (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) == 0;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
