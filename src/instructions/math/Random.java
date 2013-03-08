package instructions.math;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;


/**
 * Represents a random integer generator as an instruction. Produces a random
 * number between 0 and its argument when executed. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Random extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 8555520449896539004L;
    private static final int NUMBER_OF_ARGUMENTS = 1;
    private static final String ERROR_MESSAGE = "Using a negative argument";
    
    /**
     * Initializes a random number instruction.
     */
    public Random () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int max = nextOperand().execute(model);
        if (max < 0) { throw new IllegalInstructionException(ERROR_MESSAGE); }
        return new java.util.Random().nextInt(max);
    }
}
