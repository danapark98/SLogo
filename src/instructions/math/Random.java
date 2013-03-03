package instructions.math;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

public class Random extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 8555520449896539004L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int max = nextOperand().execute(model);
        if (max < 0) {
            throw new IllegalInstructionException(IllegalInstructionException.NEGATIVE);
        }
        return new java.util.Random().nextInt(max);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
