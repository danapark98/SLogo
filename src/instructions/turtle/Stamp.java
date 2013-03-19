package instructions.turtle;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

/**
 * Represents an instruction which makes a stamp of the current shape on the workspace<br>
 * <br>
 * <u> Examples:</u> <br>
 * stamp ---> makes a triangle shape when the turtle is a triangle shape <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Stamp extends BaseInstruction {

    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        // TODO Auto-generated method stub
        return 0;
    }

}
