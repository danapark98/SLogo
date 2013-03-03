package instructions.turtle;

import simulation.Model;
import simulation.Turtle;
import instructions.BaseInstruction;

public class HideTurtle extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 7797738746693384437L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute (Model model) {
        Turtle turtle = model.getTurtle();
        turtle.setView(Turtle.NO_IMAGE);
        return 0;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
