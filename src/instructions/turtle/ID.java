package instructions.turtle;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

/**
 * Represents ID instruction, which returns a Turtle's ID number
 * 
 * @author Ellango
 *
 */
public class ID extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -2120905604185358919L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes an ID instruction.
     */
    public ID() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        return model.getTurtleID();
    }

}
