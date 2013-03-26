package instructions.user_defined;

import instructions.BaseInstruction;
import instructions.Instruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents an if statement as an instruction. Takes two
 * arguments: a boolean value (1 or else as an integer), and the instruction to
 * execute if the boolean evaluates true. Note that the second argument (the
 * instruction) must be contained in
 * brackets.<br>
 * <br>
 * <u> Examples:</u> <br>
 * <i>if 1 [ fd 50 rt 90 ]</i> ---> Turtle goes forward 50 pixels and then
 * turns
 * right 90 degrees. This returns the value of the last instruction
 * executed (here rt 90). <br>
 * <i>if ispendownp [ fd 50 ]</i> ---> Turtle will move forward 50 pixels if the
 * pen is down, otherwise it will not do anything. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class If extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 845349488293601623L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * Initializes user defined if instruction.
     */
    public If () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int condition = nextOperand().execute(model);
        Instruction trueInstruction = nextOperand();
        if (condition != 0) {
            model.getEnvironment().inScope();

            int res = trueInstruction.execute(model);

            model.getEnvironment().outScope();

            return res;
        }
        return 0;
    }
}
