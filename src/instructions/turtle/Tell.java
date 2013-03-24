package instructions.turtle;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

/**
 * Represents Tell instruction, which changes the active turtle
 * 
 * @author Ellango
 *
 */
public class Tell extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -2120905604185358919L;
    private static final int NUMBER_OF_ARGUMENTS = 1;
    
    /**
     * Initializes a Tell instruction.
     */
    public Tell() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int index = nextOperand().execute(model);   
        model.switchTurtle(index);
        return index;
    }

}
