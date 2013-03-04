package instructions.user_defined;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import instructions.Instruction;
import simulation.Model;


/**
 * Represents a for loop of another instruction as an instruction. Takes two
 * arguments: how many times to loop, and the instruction to execute with every
 * loop. Note that the second argument (the instruction) must be contained in
 * brackets.<br>
 * <br>
 * <u> Examples:</u> <br>
 * <i>repeat 4 [ fd 50 rt 90 ]</i> ---> turtle goes forward 50 pixels and then
 * turns
 * right 90 degrees 4 times. This returns the value of the last instruction
 * executed (here rt 90). This example will draw a square. <br>
 * <i>repeat -4 [ fd 50 ]</i> ---> does nothing since -4 is less than 0. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class ForLoop extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -6196556606163979681L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int numberOfIterations = nextOperand().execute(model);
        Instruction commandsToLoop = nextOperand();
        int last = 0;
        for (int i = 0; i < numberOfIterations; ++i) {
            last = commandsToLoop.execute(model);
        }
        return last;
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
