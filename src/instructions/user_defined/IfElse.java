package instructions.user_defined;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.Instruction;
import simulation.Model;


public class IfElse extends BaseInstruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -2767016588772946054L;
    private static final int NUMBER_OF_ARGUMENTS = 3;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int condition = nextOperand().execute(model);
        Instruction trueInstruction = nextOperand();
        Instruction falseInstruction = nextOperand();
        if (condition != 0) {
            return trueInstruction.execute(model);
        }
        else {
            return falseInstruction.execute(model);
        }
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
