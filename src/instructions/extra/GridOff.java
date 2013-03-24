package instructions.extra;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

/**
 * Instruction to turn the grid off.
 * 
 * @author Ellango
 *
 */
public class GridOff extends BaseInstruction {
    
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -3151378582568279919L;
    private static final int NUMBER_OF_ARGUMENTS = 0;
    
    /**
     * Initializes a instruction to toggle the grid off.
     */
    public GridOff () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute (Model model) throws IllegalInstructionException {
        model.getBackground().gridOff();
        return 0;
    }

}
