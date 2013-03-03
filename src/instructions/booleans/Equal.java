package instructions.booleans;

import exceptions.IllegalInstructionException;
import simulation.Model;

public class Equal extends BooleanInstruction {
    
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -9085755361792996511L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    
    @Override
    public boolean executeBoolean (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) == nextOperand().execute(model);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
