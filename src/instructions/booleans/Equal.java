package instructions.booleans;

import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents the instruction for ==, and returns 1 if the two arguments are
 * equal, otherwise returns 0. <br>
 * <br>
 * <u>Example:</u> <br>
 * equal? 8 8 ---> 1 <br>
 * equalp 9 10 ----> 0
 * 
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class Equal extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -9085755361792996511L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * Initializes an '==' instruction.
     */
    public Equal () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public boolean executeBoolean (Model model)
                                               throws IllegalInstructionException {
        return nextOperand().execute(model) == nextOperand().execute(model);
    }
}
