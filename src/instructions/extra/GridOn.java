package instructions.extra;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;


/**
 * Instruction to turn the grid on.
 * 
 * @author Ellango
 *
 */
public class GridOn extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7190993316963729653L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a instruction to toggle the grid on.
     */
    public GridOn () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute (Model model) throws IllegalInstructionException {
        model.getBackground().gridOn();
        return 1;
    }

}
