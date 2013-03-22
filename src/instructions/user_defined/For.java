package instructions.user_defined;

import control.Environment;
import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.Instruction;

/**
 * Represents the for loop instruction
 * 
 * @author Ellango
 *
 */
public class For extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -7289643996059819573L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    
    public For() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        Environment environment = model.getEnvironment();
        CompoundInstruction initialization = (CompoundInstruction) nextOperand();
        Instruction commandsToExecute = nextOperand();
        VariableInstruction variable = (VariableInstruction) initialization.getInstruction(0);
        int start = initialization.getInstruction(1).execute(model);
        int end = initialization.getInstruction(2).execute(model);
        int increment = initialization.getInstruction(3).execute(model);
        
        int last = 0;
        for (int i = start; i < end; i+= increment) {
            environment.addLocalVar(variable,i);
            last = commandsToExecute.execute(model);
        }
        environment.removeInstruction(variable.getName());
        return last;
    }

}
