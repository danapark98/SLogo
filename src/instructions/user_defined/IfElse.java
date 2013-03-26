package instructions.user_defined;

import instructions.BaseInstruction;
import instructions.Instruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents an if and else statement as an instruction. Takes three
 * arguments: a boolean value (1 or else as an integer), the instruction to
 * execute if the boolean evaluates true, and the instruction to evaluate otherwise.
 * Note that the second and third arguments (the
 * instructions) must be contained in
 * brackets.<br>
 * <br>
 * <u> Examples:</u> <br>
 * <i>ifelse 1 [ fd 50 rt 90 ] [ bk 50] </i> ---> Turtle goes forward 50 pixels and then
 * turns
 * right 90 degrees. This returns the value of the last instruction
 * executed (here rt 90). <br>
 * <i>ifelse ispendownp [ fd 50 ] [ bk 50 ]</i> ---> Turtle will move forward 50 pixels if the
 * pen is down, otherwise turtle will move backwards 50 pixels. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class IfElse extends BaseInstruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -2767016588772946054L;
    private static final int NUMBER_OF_ARGUMENTS = 3;

    /**
     * Initializes user defined ifelse instruction.
     */
    public IfElse () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int condition = nextOperand().execute(model);
        Instruction trueInstruction = nextOperand();
        Instruction falseInstruction = nextOperand();
        if (condition != 0) {
            model.getEnvironment().inScope();

            int res = trueInstruction.execute(model);
            model.getEnvironment().outScope();

            return res;
        }
        else {
            model.getEnvironment().inScope();

            int res = falseInstruction.execute(model);
            model.getEnvironment().outScope();

            return res;
        }
    }
}
