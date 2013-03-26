package instructions.math;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents modular division as an instruction. This is the remainder of a
 * number after being divided by another number.
 * Throws an illegal instruction exception if the user tries to divide by 0. <br>
 * <br>
 * <u> Examples:</u> <br>
 * remainder 10 10 ---> 0 <br>
 * remainder -4 0 ---> throws illegal instruction exception "dividing by zero" <br>
 * remainder 5 2 ---> 1
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Remainder extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -4013823908746631449L;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    private static final String ERROR_MESSAGE = "Dividing by zero";

    /**
     * Initializes a remainder operation instruction (i.e. x = input1 mod input2).
     */
    public Remainder () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int arg0 = nextOperand().execute(model);
        int arg1 = nextOperand().execute(model);

        if (arg1 == 0) throw new IllegalInstructionException(ERROR_MESSAGE);
        return arg0 % arg1;
    }
}
