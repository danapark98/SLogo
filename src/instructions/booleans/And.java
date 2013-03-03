package instructions.booleans;

import instructions.BaseInstruction;
import simulation.Model;


/**
 * Represents the logical and operation on two values.
 * Examples:
 * and 1 0 --> 0
 * and 1 1 --> 1
 * 
 * @author Scott Valentine
 * 
 */
public class And extends BaseInstruction {

    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * 
     */
    private static final long serialVersionUID = 3595351121913558476L;

    @Override
    public int execute(Model model) {

        //TODO: use binary operators
        if (nextOperand().execute(model) == 1 && 
            nextOperand().execute(model) == 1) { 
            return 1; 
        }
        return 0;
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
