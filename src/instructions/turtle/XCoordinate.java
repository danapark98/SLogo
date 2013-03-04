package instructions.turtle;

import instructions.BaseInstruction;
import simulation.Model;
import simulation.Turtle;


/**
 * Represents query of the turtle's current x-coordinate as an instruction.
 * Takes no
 * arguments and returns the current x-coordinate of the turtle in pixels.<br>
 * <br>
 * <u> Examples:</u> <br>
 * Suppose turtle is at (-19, 46), <br>
 * 
 * xcor ---> returns -19. <br>
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class XCoordinate extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -1625057934983841840L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute(Model model) {
        Turtle turtle = model.getTurtle();
        return (int) turtle.getLocationOnCanvas().getX();
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

}
