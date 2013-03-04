package instructions.math;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;

/**
 * Represents a division math operation as an instruction. Uses standard integer division. 
 * Throws an illegal instruction exception if the user tries to divide by 0. <br>
 * <br>
 * <u> Examples:</u> <br>
 * quotient 10 10 ---> 1 
 * <br> quotient -4 0 ---> throws illegal instruction exception "dividing by zero"
 * <br> quotient 5 2 ---> 2
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Quotient extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -6567230868551176878L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int arg0 = nextOperand().execute(model);
        int arg1 = nextOperand().execute(model);

        if (arg1 == 0) { throw new IllegalInstructionException(
                                                       IllegalInstructionException.DIVIDE_BY_ZERO); }
        return arg0 / arg1;
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
