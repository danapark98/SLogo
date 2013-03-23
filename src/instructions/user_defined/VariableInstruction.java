package instructions.user_defined;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.Instruction;

/**
 * Used for variables so that they can be loaded by the parser, and then looked
 * up at a later point during the execution step.
 *
 */
public class VariableInstruction implements Instruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 774078238165210357L;
    private static final String ERROR_MESSAGE = "Using an undefined variable";
    private String myName;
    
    public VariableInstruction (String name) {
        myName = name;
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        try {
            Instruction variable = model.getEnvironment().getInstruction(myName);
            return variable.execute(model);
        }
        catch (IllegalInstructionException e){
            throw new IllegalInstructionException(ERROR_MESSAGE);
        }
    }
    
    @Override
    public String toString() {
        return myName; 
    }


}
