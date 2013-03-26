package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Represents an instruction which clears any stamps present in the current workspace.<br>
 * <br>
 * <u> Examples:</u> <br>
 * clearstamps ---> clears all stamps from the workspace. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class ClearStamps extends BaseInstruction {

    /**
     * Auto-generated ID
     */
    private static final long serialVersionUID = -2967947234478531694L;

    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Default constructor for Base Instruction types
     */
    public ClearStamps () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        model.clearStamps();
        return 1;
    }

}
