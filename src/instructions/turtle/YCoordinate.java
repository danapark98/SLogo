package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;


/**
 * Represents query of the turtle's current y-coordinate as an instruction.
 * Takes no
 * arguments and returns the current y-coordinate of the turtle in pixels.<br>
 * <br>
 * <u> Examples:</u> <br>
 * Suppose turtle is at (-19, 46), <br>
 * 
 * ycor ---> returns 46. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class YCoordinate extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -2404496602465804576L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute(Model model) {
        Turtle turtle = model.getTurtle();
        return (int) turtle.getLocationOnCanvas().getY();
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
