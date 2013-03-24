package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import util.Pixmap;


/**
 * Sets the shape of turtle to one of several predefined values.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 *
 */
public class SetShape extends BaseInstruction {

    /**
     *  Eclipse auto-generated ID to implement Serializable interface.
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
        int index = nextOperand().execute(model);
        Pixmap image = model.getPalette().getImage(index);
        model.getTurtle().setView(image);
        return index;
    }

}
