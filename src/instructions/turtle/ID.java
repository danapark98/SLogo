package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents ID instruction, which returns a Turtle's ID number <br>
 * <u> Examples:</u> <br>
 * id ---> returns the id of current active turtles. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
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
    public ID () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        return model.getTurtleID();
    }

}
