package instructions.math;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;


/**
 * Represents arithmetic addition as an instruction. <br>
 * <br>
 * <u> Examples:</u> <br>
 * sum 10 10 ---> 200 <br>
 * sum -4 4 ---> 0 <br>
 * sum -5 2 ---> -3
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Sum extends BaseInstruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -191594058546524267L;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        return nextOperand().execute(model) + nextOperand().execute(model);
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
    
}
