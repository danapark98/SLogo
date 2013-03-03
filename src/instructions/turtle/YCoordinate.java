package instructions.turtle;

import simulation.Model;
import simulation.Turtle;
import instructions.BaseInstruction;

public class YCoordinate extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -2404496602465804576L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute (Model model) {
        Turtle turtle = model.getTurtle();
        return (int) turtle.getLocationOnCanvas().getY();
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
