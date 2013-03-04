package instructions.user_defined;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.Instruction;
import simulation.Model;


public class ForLoop extends BaseInstruction {
    
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -6196556606163979681L;
    private static final int NUMBER_OF_ARGUMENTS = 2;


    @Override
    public int execute (Model model) throws IllegalInstructionException { 
        int numberOfIterations = nextOperand().execute(model);
        Instruction commandsToLoop = nextOperand();
        int last = 0;
        for (int i = 0; i < numberOfIterations; ++i) {
            last = commandsToLoop.execute(model);
        }
        return last;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
