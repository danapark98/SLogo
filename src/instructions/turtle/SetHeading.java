package instructions.turtle;

import exceptions.IllegalInstructionException;
import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;


/**
 * Represents rotation of the turtle to a set direction as an instruction. Takes
 * one
 * argument that is the degrees counterclockwise from the positive x-axis to set
 * the heading
 * of the turtle. This instruction returns this value.<br>
 * <br>
 * <u> Examples:</u> <br>
 * setheading 90 ---> returns 90, and positions turtle facing north <br>
 * seth -90 ---> returns -90, and positions turtle facing south<br>
 * setheading 720---> positions the turtle facing east (720 % 360 = 0).
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class SetHeading extends BaseInstruction {
    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 1L;
    private static final int NUMBER_OF_ARGUMENTS = 1;
    
    public SetHeading () {
        setNumberOfArguments(NUMBER_OF_ARGUMENTS);
    }

    @Override
    public int execute(Model model) throws IllegalInstructionException {
        Turtle turtle = model.getTurtle();
        int originalAngle = (int) turtle.getAngle();
        int absoluteHeading = nextOperand().execute(model);
        turtle.setAngle(absoluteHeading);
        return Math.abs(absoluteHeading - originalAngle);
    }

}
