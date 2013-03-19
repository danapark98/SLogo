package instructions.turtle;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

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

    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        // TODO Auto-generated method stub
        return 0;
    }

}
