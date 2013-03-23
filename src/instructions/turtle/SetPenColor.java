package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import java.awt.Color;
import simulation.Model;

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
    public SetPenColor() {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }
    
    @Override
    public int execute(Model model) throws IllegalInstructionException {
        int index = this.nextOperand().execute(model);
        Color c = model.getPalette().getColor(index);
        model.getTurtle().getPen().changeColor(c);
        return index;
    }

}
