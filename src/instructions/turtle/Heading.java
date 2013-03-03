package instructions.turtle;

import simulation.Model;
import simulation.Turtle;
import instructions.BaseInstruction;

public class Heading extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = -1343102259227622948L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute (Model model) {
        Turtle turtle = model.getTurtle();
        return (int) turtle.getAngle();
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
