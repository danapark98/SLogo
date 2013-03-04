package instructions.user_defined;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.Instruction;

public class If extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 845349488293601623L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int condition = nextOperand().execute(model);
        Instruction trueInstruction = nextOperand();
        if (condition != 0) {
            return trueInstruction.execute(model);
        }
        return 0;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
