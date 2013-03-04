package instructions.booleans;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;

/**a
 * Represents an boolean as an instruction. 
 * <br><br>
 * ex: and, or, greater, less, equal, notequal
 * 
 * @author Scott Valentine
 *
 */
public abstract class BooleanInstruction extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 2984237637135898140L;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        boolean b = executeBoolean(model);
        return b? 1:0;
    }
    
    public abstract boolean executeBoolean(Model model) throws IllegalInstructionException; 


}
