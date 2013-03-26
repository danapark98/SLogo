package instructions.extra;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;


/**
 * Instruction that exits SLogo.
 * 
 * @author Scott Valentine
 *
 */
public class Exit extends BaseInstruction {

    /**
     * Auto-generated ID for I/O
     */
    private static final long serialVersionUID = -7771574845023822201L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    /**
     * Default Exit instruction with 0 arguments.
     */
    public Exit() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        System.exit(-1);
        return 0;
    }

}
