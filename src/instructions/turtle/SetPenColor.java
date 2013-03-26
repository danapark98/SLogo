package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import exceptions.IllegalInstructionException;


/**
 * Changes the pen color that the turtle draws with. These colors are predefined values
 * that the user can select from.
 * 
 * @author Scott Valentine
 * 
 */
public class SetPenColor extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 5820283386042014110L;

    private static final int NUMBER_OF_ARGUMENTS = 1;

    /**
     * Default Constructor for BaseInstructions subclasses
     */
    public SetPenColor () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int index = nextOperand().execute(model);
        model.getTurtle().getPen().changeColor(index);
        return index;
    }

}
