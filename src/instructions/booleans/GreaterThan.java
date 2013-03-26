package instructions.booleans;

import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents the instruction for >, and returns 1 if the first argument is
 * greater than the second, otherwise returns 0. <br>
 * <br>
 * <u>Example:</u> <br>
 * greater? 8 8 ---> 0 <br>
 * greaterp 9 10 ---> 0 <br>
 * greaterp 10 1 ---> 1
 * 
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public class GreaterThan extends BooleanInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -5228113262586227488L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * Initializes an '>' instruction.
     */
    public GreaterThan () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public boolean executeBoolean (Model model)
                                               throws IllegalInstructionException {
        return nextOperand().execute(model) > nextOperand().execute(model);
    }

}
