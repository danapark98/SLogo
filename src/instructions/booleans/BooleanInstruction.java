package instructions.booleans;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

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
