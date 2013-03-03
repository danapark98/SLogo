package instructions.turtle;

import simulation.Model;
import simulation.Turtle;
import instructions.BaseInstruction;


public class ShowTurtle extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 6890687622386597570L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute (Model model) {
        Turtle turtle = model.getTurtle();
        turtle.setView(Turtle.DEFAULT_IMAGE);
        return 1;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }
}
