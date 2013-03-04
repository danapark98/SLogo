package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;


/**
 * Represents query of the turtle's current heading as an instruction. Takes no
 * arguments and returns the current heading of the turtle in degrees
 * counterclockwise from the positive x axis.<br>
 * <br>
 * <u> Examples:</u> <br>
 * heading ---> give the turtle current heading <br>
 *  
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Heading extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -1343102259227622948L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute(Model model) {
        Turtle turtle = model.getTurtle();
        return (int) turtle.getAngle();
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
