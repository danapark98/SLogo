package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;

/**
 * Instruction for the turtle to rotate to the left
 * 
 * @author Scott Valentine
 * 
 */
public class Left extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -6371140632719070106L;
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public int execute (Model model) throws IllegalInstructionException {
        int rotation = nextOperand().execute(model);        
        Turtle turtle = model.getTurtle();
        turtle.setAngle(turtle.getAngle() - rotation);
        return rotation;
    }
    
    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
