package instructions.user_defined;

import instructions.BaseInstruction;
import instructions.CompoundInstruction;
import instructions.ConstantInstruction;
import instructions.Instruction;
import simulation.Model;
import control.Environment;
import exceptions.IllegalInstructionException;


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

    private static final int VARIABLE_NAME_INDEX = 0;
    private static final int START_INDEX = 1;
    private static final int END_INDEX = 2;
    private static final int INCREMENT_INDEX = 3;

    /**
     * Default constructor for new instance ready to be loaded.
     */
    public For () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        CompoundInstruction initialization = (CompoundInstruction) nextOperand();
        Instruction commandsToExecute = nextOperand();
        VariableInstruction variable = (VariableInstruction) initialization.getInstruction(
                VARIABLE_NAME_INDEX);
        int start = initialization.getInstruction(START_INDEX).execute(model);
        int end = initialization.getInstruction(END_INDEX).execute(model);
        int increment = initialization.getInstruction(INCREMENT_INDEX).execute(model);

        Environment environment = model.getEnvironment();
        int last = 0;
        for (int i = start; i < end; i += increment) {
            environment.inScope();
            Instruction constInstruct = new ConstantInstruction(i);
            environment.addInstruction(variable.toString(), constInstruct);
            last = commandsToExecute.execute(model);
            environment.outScope();
        }
        return last;
    }

}
