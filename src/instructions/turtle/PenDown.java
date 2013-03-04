package instructions.turtle;

import java.awt.Color;
import simulation.Model;
import simulation.Turtle;
import instructions.BaseInstruction;

public class PenDown extends BaseInstruction {

    /**
     * Eclipse auto-generated ID to implement Serializable interface.
     */
    private static final long serialVersionUID = 4039119828110366434L;
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public int execute (Model model) {
        Turtle turtle = model.getTurtle();
        turtle.changePen(Color.BLACK);
        return 1;
    }

    @Override
    public int getNumberOfArguments () {
        return NUMBER_OF_ARGUMENTS;
    }

}
