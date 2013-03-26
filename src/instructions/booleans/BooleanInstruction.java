package instructions.booleans;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * a
 * Represents an boolean as an instruction. <br>
 * <br>
 * ex: and, or, greater, less, equal, notequal
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 */
public abstract class BooleanInstruction extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 2984237637135898140L;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        boolean b = executeBoolean(model);
        return b ? 1 : 0;
    }

    /**
     * Executes a boolean instruction on the model.
     * 
     * @param model the model on which the boolean operates.
     * @return a boolean that represents the value of this evaluation.
     * @throws IllegalInstructionException when the parameters do not match the expected.
     */
    public abstract boolean executeBoolean (Model model) throws IllegalInstructionException;

}
