package instructions.turtle;

import simulation.Model;
import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;

/**
 * Sets the shape of turtle to one of several predefined values.
 * 
 * @author Scott Valentine
 *
 */
public class SetShape extends BaseInstruction {

    /**
     * 
     */
    private static final long serialVersionUID = -5120713375977061810L;
    private static final int NUMBER_OF_ARGUMENTS = 1;
    
    /**
     * Initializes a set shape instruction
     */
    public SetShape() {
    	setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        model.setShape(index);
        return model.getShape();
    }

}
